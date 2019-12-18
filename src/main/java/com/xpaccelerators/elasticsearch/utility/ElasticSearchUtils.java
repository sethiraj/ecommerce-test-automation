/**
 * 
 */
package com.xpaccelerators.elasticsearch.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * The Class ElasticSearchUtils.
 *
 * @author surendrane
 */
public class ElasticSearchUtils {

	/**
	 * Gets the source docs throughterms query.
	 *
	 * @param term
	 *            the term
	 * @param filterData
	 *            the filter data
	 * @param threadSize
	 *            the thread size
	 * @return the source docs throughterms query
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<Map<String, Object>> getSourceDocsThroughtermsQuery(final String term, final String filterData,
			final int threadSize) throws IOException {
		List<Map<String, Object>> listOfSources = new ArrayList<Map<String, Object>>();
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));

		SearchRequest searchRequest = new SearchRequest("logstash-*").source(SearchSourceBuilder.searchSource()
				.size(threadSize).query(QueryBuilders.termsQuery(term, filterData)).sort("@timestamp", SortOrder.DESC));
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

		SearchHit[] searchHits = response.getHits().getHits();
		for (int i = 0; i < searchHits.length; i++) {
			listOfSources.add(searchHits[i].getSourceAsMap());
		}
		client.close();
		return listOfSources;
	}

	/**
	 * Gets the source logs throughterms query.
	 *
	 * @param term
	 *            the term
	 * @param filterData
	 *            the filter data
	 * @param threadSize
	 *            the thread size
	 * @return the source logs throughterms query
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<String> getSourceLogsThroughtermsQuery(final String term, final String filterData,
			final int threadSize) throws IOException {
		List<String> listOfSources = new ArrayList<String>();
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));

		SearchRequest searchRequest = new SearchRequest("logstash-*").source(
				SearchSourceBuilder.searchSource().size(threadSize).query(QueryBuilders.termsQuery(term, filterData))
						.sort(new FieldSortBuilder("@timestamp").order(SortOrder.DESC)));
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

		SearchHit[] searchHits = response.getHits().getHits();
		for (int i = 0; i < searchHits.length; i++) {
			listOfSources.add(searchHits[i].getSourceAsString());
		}
		client.close();
		return listOfSources;
	}

	/**
	 * Creates the index.
	 *
	 * @param indexName
	 *            the index name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static void createIndex(final String indexName) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));
		GetIndexRequest request = new GetIndexRequest();
		request.indices(indexName);
		boolean exists;
		exists = client.indices().exists(request, RequestOptions.DEFAULT);
		if (!exists) {
			createIndexWithMapping(indexName, client);
		}
		client.close();
	}

	/**
	 * Creates the index with mapping.
	 *
	 * @param indexName
	 *            the index name
	 * @param client
	 *            the client
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void createIndexWithMapping(final String indexName, final RestHighLevelClient client)
			throws IOException {
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
		createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 3)
				.put("index.number_of_replicas", 2).put("index.mapping.total_fields.limit", "100000"));
		CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		System.out.println(createIndexResponse.isAcknowledged());
	}

	/**
	 * Ingest data to index.
	 *
	 * @param indexName
	 *            the index name
	 * @param jsonString
	 *            the json string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void ingestDataToIndex(final String indexName, final String jsonString) throws IOException {

		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));
		IndexRequest request = new IndexRequest(indexName);
		request.source(jsonString, XContentType.JSON);
		client.index(request, RequestOptions.DEFAULT);
		client.close();
	}
}
