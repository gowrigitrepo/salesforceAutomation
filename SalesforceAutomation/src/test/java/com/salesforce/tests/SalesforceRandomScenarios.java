package com.salesforce.tests;

import org.testng.annotations.Test;

import com.salesforce.base.SalesforceBase;

public class SalesforceRandomScenarios extends SalesforceBase {

	@Test
	public static void salesforceHomeTabNameLink() {
		userLogin();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Home')]"))), "Home Tab");
		byObject = getCssSelector("a#tryLexDialogX.dialogClose");
		waitUntilVisibilityLocated(byObject);
		clickButton((getWebElement(driver.findElement(byObject))), "Close");
		clickButton(getWebElement(driver.findElement(getByXpath("//div[@class='content']//span[1]//a"))),
				"Firstname Lastname - Link");

	}
	
	@Test
	public static void salesforceHomeTabNameLinkEditProfile() {
		userLogin();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Home')]"))), "Home Tab");
		byObject = getCssSelector("a#tryLexDialogX.dialogClose");
		waitUntilVisibilityLocated(byObject);
		clickButton((getWebElement(driver.findElement(byObject))), "Close");
		clickButton(getWebElement(driver.findElement(getByXpath("//div[@class='content']//span[1]//a"))),
				"Firstname Lastname - Link");
		byObject = getByXpath("//a[@id='moderatorMutton']//b[@class='zen-selectArrow']");
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)), "Down Arrow Switched");
		clickButton(getWebElement(driver.findElement(byObject)), "Down Arrow Clicked");
		
		byObject = getByXpath("//a[contains(text(),'Edit Profile')]");
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)),
				"Edit Profile Switch");
		clickButton(getWebElement(driver.findElement(byObject)),
				"Edit Profile Click");
		
		//Expected contact tab selected. Actual About tab selected

	}
	
	@Test
	public static void salesforceAllTab() {
		userLogin();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Home')]"))), "Home Tab");
		byObject = getCssSelector("a#tryLexDialogX.dialogClose");
		waitUntilVisibilityLocated(byObject);
		clickButton((getWebElement(driver.findElement(byObject))), "Close");
		clickButton(getWebElement(driver.findElement(getByXpath(""))), "All Tab");
		clickButton(getWebElement(driver.findElement(getByXpath(""))), "Customize My Tabs");
		//Select any tab and click remove button
		//click save button
		logout();
		userLogin();
		
	}
	
	@Test
	public static void salesforceHomeCurrentDateLink() {
		userLogin();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Home')]"))), "Home Tab");
		byObject = getCssSelector("a#tryLexDialogX.dialogClose");
		waitUntilVisibilityLocated(byObject);
		clickButton((getWebElement(driver.findElement(byObject))), "Close");
		clickButton(getWebElement(driver.findElement(getByXpath(""))),"Current Date Link");
		clickButton(getWebElement(driver.findElement(getByXpath(""))),"8PM Link");
		
		
	}
	
	
}
