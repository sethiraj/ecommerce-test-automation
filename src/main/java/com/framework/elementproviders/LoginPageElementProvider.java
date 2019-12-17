package com.framework.elementproviders;

import com.taf.core.PageElementProvider;
import com.taf.core.TestContext;
import com.taf.impl.selenium.SeleniumElementProvider;
import com.taf.impl.selenium.SeleniumTestContext;
import org.openqa.selenium.By;

/**
 * The Class LoginPageElementProvider.
 */
public class LoginPageElementProvider extends PageElementProvider {

	/** The selenium test context. */
	protected SeleniumTestContext seleniumTestContext;

	/** The selenium element provider. */
	protected SeleniumElementProvider seleniumElementProvider;

	/**
	 * Instantiates a new base element provider.
	 *
	 * @param context
	 *            the context
	 */
	public LoginPageElementProvider(final TestContext context) {
		super(context);
		seleniumTestContext = (SeleniumTestContext) context;
		seleniumElementProvider = new SeleniumElementProvider(seleniumTestContext);
	}

	/**
	 * Gets the submit.
	 *
	 * @return the submit
	 */
	public Object getSubmit() {
		seleniumTestContext.waitForElementToDisplayUsingBy(By.xpath("//button[@type='submit']"));
		return seleniumElementProvider.getElementByXpath("//button[@type='submit']");
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public Object getUserName() {
		seleniumTestContext.waitForElementToDisplayUsingBy(By.name("username"));
		return seleniumElementProvider.getElementByName("username");
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public Object getPassword() {
		seleniumTestContext.waitForElementToDisplayUsingBy(By.name("password"));
		return seleniumElementProvider.getElementByName("password");
	}

	/**
	 * Gets the log out.
	 *
	 * @return the log out
	 */
	public Object getLogOut() {
		seleniumTestContext.waitForElementToDisplayUsingBy(By.xpath("//button[contains(.,'Log Out')]"));
		return seleniumElementProvider.getElementByXpath("//button[contains(.,'Log Out')]");
	}
}