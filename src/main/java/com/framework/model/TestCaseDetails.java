package com.framework.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class TestCaseDetails.
 */
public class TestCaseDetails {

	/** The test categories. */
	@JsonProperty("testCategories")
	private String[] testCategories;

	/** The test case name. */
	@JsonProperty("testCase_Name")
	private String testCase_Name = StringUtils.EMPTY;

	/** The description. */
	@JsonProperty("description")
	private String description = StringUtils.EMPTY;

	/** The test case result. */
	@JsonProperty("testCase_Result")
	private String testCase_Result = StringUtils.EMPTY;

	/** The test case execution time seconds. */
	@JsonProperty("testCase_ExecutionTime_Seconds")
	private int testCase_ExecutionTime_Seconds = 0;

	/** The build name. */
	@JsonProperty("buildName")
	private String buildName;

	/** The test case failure details. */
	@JsonProperty("testCase_Failure_Details")
	private TestCaseFailureDetails testCase_Failure_Details;

	/**
	 * Instantiates a new test case details.
	 */
	public TestCaseDetails() {

	}

	/**
	 * Gets the test categories.
	 *
	 * @return the test categories
	 */
	public String[] getTestCategories() {
		return testCategories;
	}

	/**
	 * Sets the test categories.
	 *
	 * @param testCategories
	 *            the new test categories
	 */
	public void setTestCategories(String testCategories[]) {
		this.testCategories = testCategories;
	}

	/**
	 * Gets the test case name.
	 *
	 * @return the test case name
	 */
	public String getTestCase_Name() {
		return testCase_Name;
	}

	/**
	 * Sets the test case name.
	 *
	 * @param testCase_Name
	 *            the new test case name
	 */
	public void setTestCase_Name(String testCase_Name) {
		this.testCase_Name = testCase_Name;
	}

	/**
	 * Gets the test case result.
	 *
	 * @return the test case result
	 */
	public String getTestCase_Result() {
		return testCase_Result;
	}

	/**
	 * Sets the test case result.
	 *
	 * @param testCase_Result
	 *            the new test case result
	 */
	public void setTestCase_Result(String testCase_Result) {
		this.testCase_Result = testCase_Result;
	}

	/**
	 * Gets the test case execution time seconds.
	 *
	 * @return the test case execution time seconds
	 */
	public int getTestCase_ExecutionTime_Seconds() {
		return testCase_ExecutionTime_Seconds;
	}

	/**
	 * Sets the test case execution time seconds.
	 *
	 * @param testCase_ExecutionTime_Seconds
	 *            the new test case execution time seconds
	 */
	public void setTestCase_ExecutionTime_Seconds(int testCase_ExecutionTime_Seconds) {
		this.testCase_ExecutionTime_Seconds = testCase_ExecutionTime_Seconds;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the builds the name.
	 *
	 * @return the builds the name
	 */
	public String getBuildName() {
		return buildName;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the builds the name.
	 *
	 * @param buildName
	 *            the new builds the name
	 */
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	/**
	 * Sets the test case failure details.
	 *
	 * @param testFailStatus
	 *            the new test case failure details
	 */
	public void setTestCaseFailureDetails(TestCaseFailureDetails testFailStatus) {
		this.testCase_Failure_Details = testFailStatus;
	}
}
