package com.framework.elementproviders;

import com.taf.core.PageElementProvider;
import com.taf.core.TestContext;
import com.taf.core.ToolElementProvider;
import com.taf.impl.selenide.SelenideElementProvider;
import com.taf.impl.selenide.SelenideTestContext;
import com.taf.impl.selenium.SeleniumElementProvider;
import com.taf.impl.selenium.SeleniumTestContext;
import org.openqa.selenium.By;

/**
 * The Class LoginPageElementProvider.
 */
public class LoginPageElementProvider extends PageElementProvider {

	/** The selenium test context. */
	protected TestContext testContext;

	/** The selenium element provider. */
	protected ToolElementProvider toolElementProvider;

	/**
	 * Instantiates a new base element provider.
	 *
	 * @param context
	 *            the context
	 */
	public LoginPageElementProvider(final TestContext context) {
		super(context);
		if (context.getClass().getSimpleName().contains("SeleniumTestContext")) {
			testContext = (SeleniumTestContext) context;
			toolElementProvider = new SeleniumElementProvider(testContext);
		} else {
			testContext = (SelenideTestContext) context;
			toolElementProvider = new SelenideElementProvider(testContext);
		}
	}

	/**
	 * Gets the submit.
	 *
	 * @return the submit
	 */
	public Object getSubmit() {
		testContext.waitForElementToDisplayUsingBy(By.xpath("//button[@type='submit']"));
		return toolElementProvider.getElementByXpath("//button[@type='submit']");
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public Object getUserName() {
		testContext.waitForElementToDisplayUsingBy(By.name("username"));
		return toolElementProvider.getElementByName("username");
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public Object getPassword() {
		testContext.waitForElementToDisplayUsingBy(By.name("password"));
		return toolElementProvider.getElementByName("password");
	}

	/**
	 * Gets the log out.
	 *
	 * @return the log out
	 */
	public Object getLogOut() {
		testContext.waitForElementToDisplayUsingBy(By.xpath("//button[contains(.,'Log Out')]"));
		return toolElementProvider.getElementByXpath("//button[contains(.,'Log Out')]");
	}
}