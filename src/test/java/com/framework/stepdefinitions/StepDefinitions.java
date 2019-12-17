/**
 * 
 */
package com.framework.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import com.taf.core.TestApplication;
import com.taf.core.TestEngine;
import com.taf.impl.selenium.SeleniumTestEngine;

import io.cucumber.java.After;
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

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		testEngine.set(new SeleniumTestEngine());
		testApplication.set(testEngine.get().start("http://localhost:8181/login"));
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
	 * After scenario.
	 */
	@After
	public void afterScenario() {
		testEngine.get().stop();
	}
}
