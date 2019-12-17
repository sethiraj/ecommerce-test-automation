/**
 * 
 */
package com.framework.elementproviders;

import org.openqa.selenium.By;

import com.taf.core.PageElementProvider;
import com.taf.core.TestContext;
import com.taf.impl.selenium.SeleniumElementProvider;
import com.taf.impl.selenium.SeleniumTestContext;

/**
 * @author surendrane
 *
 */
public class HomePageElementProvider extends PageElementProvider {

	/** The selenium test context. */
	protected SeleniumTestContext seleniumTestContext;

	/** The selenium element provider. */
	protected SeleniumElementProvider seleniumElementProvider;

	public HomePageElementProvider(TestContext context) {
		super(context);
		seleniumTestContext = (SeleniumTestContext) context;
		seleniumElementProvider = new SeleniumElementProvider(seleniumTestContext);
	}

	public Object getAdminLink() {
		seleniumTestContext.waitForElementToDisplayUsingBy(By.linkText("Admin"));
		return seleniumElementProvider.getElementByLinkText("Admin");
	}

	public boolean verifyAdminLink() {
		return seleniumTestContext.waitForElementToDisplayUsingBy(By.linkText("Admin"));
	}

	public Object getLogOut() {
		seleniumTestContext.waitForElementToDisplayUsingBy(By.xpath("//button[contains(.,'Log Out')]"));
		return seleniumElementProvider.getElementByXpath("//button[contains(.,'Log Out')]");
	}

}
