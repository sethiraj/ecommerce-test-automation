/**
 * 
 */
package com.framework.pages;

import com.framework.elementproviders.LoginPageElementProvider;
import com.taf.core.TestContext;
import com.taf.core.TestPage;
import com.taf.impl.selenium.SeleniumTestContext;

/**
 * The Class LoginPage.
 *
 * @author surendrane
 */
public class LoginPage extends TestPage {

	/** The selenium test context. */
	private final SeleniumTestContext seleniumTestContext;

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
		seleniumTestContext = (SeleniumTestContext) context;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.taf.core.TestPage#intializeElementProvider()
	 */
	@Override
	public void intializeElementProvider() {
		loginpageelementprovider = new LoginPageElementProvider(seleniumTestContext);
	}

	/**
	 * Click username.
	 */
	private void clickUsername() {
		seleniumTestContext.clickButton(loginpageelementprovider.getUserName());
	}

	/**
	 * Enter username.
	 *
	 * @param text the text
	 */
	private void enterUsername(final String text) {
		seleniumTestContext.enterTextIn(loginpageelementprovider.getUserName(), text);
	}

	/**
	 * Enter password.
	 *
	 * @param text the text
	 */
	private void enterPassword(final String text) {
		seleniumTestContext.enterTextIn(loginpageelementprovider.getPassword(), text);
	}

	/**
	 * Click submit.
	 */
	private void clickSubmit() {
		seleniumTestContext.clickButton(loginpageelementprovider.getSubmit());
	}

	/**
	 * Login into application.
	 *
	 * @param userName the user name
	 * @param password the password
	 */
	public void loginIntoApplication(final String userName, final String password) {
		clickUsername();
		enterUsername(userName);
		enterPassword(password);
		clickSubmit();

		seleniumTestContext.waitFor(2000);
	}
}
