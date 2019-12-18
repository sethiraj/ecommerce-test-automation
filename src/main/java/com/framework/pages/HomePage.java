/**
 * 
 */
package com.framework.pages;

import org.openqa.selenium.By;

import com.framework.elementproviders.HomePageElementProvider;
import com.taf.core.TestContext;
import com.taf.core.TestPage;
import com.taf.impl.selenide.SelenideTestContext;
import com.taf.impl.selenium.SeleniumTestContext;

/**
 * The Class HomePage.
 *
 * @author surendrane
 */
public class HomePage extends TestPage {

	/** The selenium test context. */
	private final TestContext testContext;

	/** The sign up page element provider. */
	private HomePageElementProvider homePageElementProvider;

	/**
	 * Instantiates a new sign up page.
	 *
	 * @param context
	 *            the context
	 */
	public HomePage(final TestContext context) {
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
		homePageElementProvider = new HomePageElementProvider(testContext);
	}

	/**
	 * Verify admin priviledges.
	 *
	 * @return true, if successful
	 */
	public boolean verifyAdminPriviledges() {
		return homePageElementProvider.verifyAdminLink();
	}

	/**
	 * Click admin priviledges.
	 */
	public void clickAdminPriviledges() {
		testContext.click(homePageElementProvider.getAdminLink());
		testContext.waitForElementToBeClickable(By.xpath("View Products"));
		testContext.click(homePageElementProvider.getLogOut());
	}
}
