/**
 * 
 */
package com.framework.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.model.TestCaseDetails;
import com.framework.model.TestCaseFailureDetails;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import com.taf.core.TestApplication;
import com.taf.core.TestEngine;
import com.taf.impl.selenide.SelenideTestEngine;
import com.taf.impl.selenium.SeleniumTestEngine;
import com.xpaccelerators.elasticsearch.utility.ElasticSearchUtils;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

/**
 * The Class StepDefinitions.
 *
 * @author surendrane
 */
public class StepDefinitions {

	/** The test engine. */
	protected ThreadLocal<TestEngine> testEngine = new ThreadLocal<TestEngine>();

	/** The test application. */
	protected ThreadLocal<TestApplication> testApplication = new ThreadLocal<TestApplication>();

	/** The test case details. */
	protected ThreadLocal<TestCaseDetails> testCaseDetails = new ThreadLocal<TestCaseDetails>();

	/** The test case failure details. */
	protected ThreadLocal<TestCaseFailureDetails> testCaseFailureDetails = new ThreadLocal<TestCaseFailureDetails>();

	/** The build no. */
	final int buildNo = new Random().nextInt(12345);

	/** The build name. */
	final String buildName = "build_" + Integer.toString(buildNo);

	/**
	 * Sets the up.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Before
	public void setUp() throws IOException {
		String toolEngine = System.getProperty("tool");
		if (toolEngine == null) {
			testEngine.set(new SeleniumTestEngine());
		} else {
			testEngine.set(new SelenideTestEngine());
		}
		testApplication.set(testEngine.get().start("http://localhost:8181/login"));
		testCaseDetails.set(new TestCaseDetails());
		testCaseDetails.get().setBuildName(buildName);
		testCaseFailureDetails.set(new TestCaseFailureDetails());
		ElasticSearchUtils.createIndex("test");
	}

	/**
	 * I login to application.
	 *
	 * @param user
	 *            the user
	 */
	@Given("I login to the application with username {string}")
	public void ILoginToApplication(final String user) {
		LoginPage loginPage = testApplication.get().getInstance(LoginPage.class);
		loginPage.loginIntoApplication(user, user);
	}

	/**
	 * I land on home screen.
	 */
	@When("I land on the home screen")
	public void ILandOnHomeScreen() {
		assertEquals("Home", testApplication.get().getTestContext().getPageTitle());
	}

	/**
	 * I validate admin link presence.
	 */
	@Then("I validate the Admin Link is present")
	public void IValidateAdminLinkPresence() {
		HomePage homePage = testApplication.get().getInstance(HomePage.class);
		assertTrue("Admin Priviledge is not seen", homePage.verifyAdminPriviledges());
	}

	/**
	 * I click on admin link.
	 */
	@Then("I clickon the Admin Link")
	public void IClickOnAdminLink() {
		HomePage homePage = testApplication.get().getInstance(HomePage.class);
		homePage.clickAdminPriviledges();
	}

	/**
	 * After step.
	 *
	 * @param scenario
	 *            the scenario
	 */
	@AfterStep
	public void afterStep(Scenario scenario) {

	}

	/**
	 * After scenario.
	 *
	 * @param scenario
	 *            the scenario
	 * @throws JsonProcessingException
	 *             the json processing exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@After
	public void afterScenario(Scenario scenario) throws JsonProcessingException, IOException {
		testEngine.get().stop();

		testCaseDetails.get().setTestCase_Name(scenario.getName());
		testCaseDetails.get().setTestCategories(scenario.getSourceTagNames().stream().toArray(String[]::new));
		if (scenario.isFailed()) {
			testCaseDetails.get().setTestCase_Result("Fail");

		} else {
			testCaseDetails.get().setTestCase_Result("Pass");
		}
		testCaseDetails.get().setTestCaseFailureDetails(testCaseFailureDetails.get());

		ObjectMapper objectMapper = new ObjectMapper();
		ElasticSearchUtils.ingestDataToIndex("test", objectMapper.writeValueAsString(testCaseDetails.get()));
	}
}
