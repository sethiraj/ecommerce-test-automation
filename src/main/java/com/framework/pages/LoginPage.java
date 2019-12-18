/**
 * 
 */
package com.framework.pages;

import com.framework.elementproviders.LoginPageElementProvider;
import com.taf.core.TestContext;
import com.taf.core.TestPage;
import com.taf.impl.selenide.SelenideTestContext;
import com.taf.impl.selenium.SeleniumTestContext;

/**
 * The Class LoginPage.
 *
 * @author surendrane
 */
public class LoginPage extends TestPage {

	/** The selenium test context. */
	private final TestContext testContext;

	/** The sign up page element provider. */
	private LoginPageElementProvider loginpageelementprovider;

	/**
	 * Instantiates a new sign up page.
	 *
	 * @param context
	 *            the context
	 */
	public LoginPage(final TestContext context) {
		super(context);
		if (context.getClass().getSimpleName().contains("SeleniumTestContext")) {
			testContext = (SeleniumTestContext) context;
		} else {
			testContext = (SelenideTestContext) context;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.taf.core.TestPage#intializeElementProvider()
	 */
	@Override
	public void intializeElementProvider() {
		loginpageelementprovider = new LoginPageElementProvider(testContext);
	}

	/**
	 * Click username.
	 */
	private void clickUsername() {
		testContext.clickButton(loginpageelementprovider.getUserName());
	}

	/**
	 * Enter username.
	 *
	 * @param text
	 *            the text
	 */
	private void enterUsername(final String text) {
		testContext.enterTextIn(loginpageelementprovider.getUserName(), text);
	}

	/**
	 * Enter password.
	 *
	 * @param text
	 *            the text
	 */
	private void enterPassword(final String text) {
		testContext.enterTextIn(loginpageelementprovider.getPassword(), text);
	}

	/**
	 * Click submit.
	 */
	private void clickSubmit() {
		testContext.clickButton(loginpageelementprovider.getSubmit());
	}

	/**
	 * Login into application.
	 *
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 */
	public void loginIntoApplication(final String userName, final String password) {
		clickUsername();
		enterUsername(userName);
		enterPassword(password);
		clickSubmit();

		testContext.waitFor(2000);
	}
}
