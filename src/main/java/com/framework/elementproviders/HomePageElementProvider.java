/**
 * 
 */
package com.framework.elementproviders;

import org.openqa.selenium.By;

import com.taf.core.PageElementProvider;
import com.taf.core.TestContext;
import com.taf.core.ToolElementProvider;
import com.taf.impl.selenide.SelenideElementProvider;
import com.taf.impl.selenide.SelenideTestContext;
import com.taf.impl.selenium.SeleniumElementProvider;
import com.taf.impl.selenium.SeleniumTestContext;

/**
 * The Class HomePageElementProvider.
 *
 * @author surendrane
 */
public class HomePageElementProvider extends PageElementProvider {

	/** The selenium test context. */
	protected TestContext testContext;

	/** The selenium element provider. */
	protected ToolElementProvider toolElementProvider;

	/**
	 * Instantiates a new home page element provider.
	 *
	 * @param context
	 *            the context
	 */
	public HomePageElementProvider(TestContext context) {
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
	 * Gets the admin link.
	 *
	 * @return the admin link
	 */
	public Object getAdminLink() {
		testContext.waitForElementToDisplayUsingBy(By.linkText("Admin"));
		return toolElementProvider.getElementByLinkText("Admin");
	}

	/**
	 * Verify admin link.
	 *
	 * @return true, if successful
	 */
	public boolean verifyAdminLink() {
		return testContext.waitForElementToDisplayUsingBy(By.linkText("Admin"));
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
