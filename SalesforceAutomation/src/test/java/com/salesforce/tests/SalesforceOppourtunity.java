package com.salesforce.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.SalesforceBase;

public class SalesforceOppourtunity extends SalesforceBase {

	@Test
	public static void salesforceOppourtunityViewDropDown() {
		Opportunity();
		WebElement viewDropDown = getWebElement(driver.findElement(getByXpath("//select[@id='fcf']")));
		List<WebElement> viewDropDownList = viewDropDown.findElements(getByXpath("//select[@id='fcf']//option"));

		ArrayList<String> expectedViewDropDownList = new ArrayList<String>();
		expectedViewDropDownList.add("All Opportunities");
		expectedViewDropDownList.add("Closing Next Month");
		expectedViewDropDownList.add("Closing This Month");
		expectedViewDropDownList.add("My Opportunities");
		expectedViewDropDownList.add("New Last Week");
		expectedViewDropDownList.add("New This Week");
		expectedViewDropDownList.add("Opportunity Pipeline");
		expectedViewDropDownList.add("Private");
		expectedViewDropDownList.add("Recently Viewed Opportunities");
		expectedViewDropDownList.add("Won");

		ArrayList<String> actualViewDropDownList = new ArrayList<String>();
		for (WebElement dropDown : viewDropDownList) {
			actualViewDropDownList.add(dropDown.getText());
		}

		//Assert.assertEquals(actualViewDropDownList, expectedViewDropDownList);
		softAssert.assertEquals(actualViewDropDownList, expectedViewDropDownList);
		softAssert.assertAll();
		report.logTestInfo("Opportunity View Drop Down Completed");

	}

	@Test
	public static void salesforceCreateOppourtunity() {
		Opportunity();
		clickButton((getWebElement(driver.findElement(getByXpath("//div[@id='createNewButton']")))),
				"Create New Account");
		clickButton((getWebElement(driver.findElement(getByXpath("//a[@class='opportunityMru menuButtonMenuLink']")))),
				"Account");

		String opp_name = "Opportunity_"+getTime();
		String acc_name = "Account_"+getTime();
		//clickButton(getWebElement(driver.findElement(getByXpath("// select[@id='calMonthPicker']"))), "Calender Month Picker");
		// select[@id='calMonthPicker']// select[@id='calYearPicker']
		String close_dt = getTodaysDateMMDDYYYY();
		String probability = "10";
		String primary_compaign = "";

		byObject = getByXpath("//input[@id='opp3']");
		waitUntilVisibilityLocated(byObject);
		sendText(getWebElement(driver.findElement(byObject)), opp_name, "Opportunity Name");

		// **********************************************

		/*
		 * // img[contains(@class,'lookupIconOn')] -- lookup
		 * clickButton(getWebElement(driver.findElement(getByXpath(
		 * "//img[contains(@class,'lookupIconOn')]"))), "Account name Look up"); String
		 * mainWindowHandle = driver.getTitle(); switchToWindow(mainWindowHandle);
		 * sendText(getWebElement(driver.findElement(getByXpath("//input[@id='opp4']")))
		 * , acc_name, "Account Name");
		 * 
		 */
		// ***************************************

		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='opp9']"))), close_dt, "Close Date");
		selectMethodByIndex(getWebElement(driver.findElement(getByXpath("//select[@id='opp11']"))), 1);
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='opp12']"))), probability, "Probability");
		selectMethodByIndex(getWebElement(driver.findElement(getByXpath("//select[@id='opp6']"))), 1);
		// sendText(getWebElement(driver.findElement(getByXpath("//input[@id='opp17']"))),
		// "Primary Compaign");

		clickButton(getWebElement(driver.findElement(getByXpath("//input[@class='btn' and @name='save']"))),
				"Opportunity Save");

		String actualOppTitle = driver.getTitle();
		System.out.println(actualOppTitle);
		String expectedOppTitle1 = "Opportunity: ";
		String expectedOppTitle2 = opp_name + " ~ Salesforce - Developer Edition";
		String expectedOppTitle = expectedOppTitle1 + expectedOppTitle2;
		System.out.println("Actual page : " + actualOppTitle);
		System.out.println("Expected page : " + expectedOppTitle);
		softAssert.assertEquals(actualOppTitle, expectedOppTitle);
		softAssert.assertAll("Opportunity Page Displayed");
	}

	@Test
	public static void salesforceOpportunityPipeline() {
		Opportunity();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Opportunity Pipeline')]"))),
				"Opportunity Pipeline");
		
		String actualOppPipelineTitle = driver.getTitle();
		String expectedOppPipelineTitle = "Opportunity Pipeline ~ Salesforce - Developer Edition";
		softAssert.assertEquals(actualOppPipelineTitle, expectedOppPipelineTitle);
		softAssert.assertAll("Opportunity Pipeline Displayed");

	}
	
	@Test
	public static void salesforceStuckOpportunities() {

		Opportunity();

		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Stuck Opportunities')]"))),
				"Stuck Opportunities");
		
		String actualStuckOpportunityTitle = driver.getTitle();
		String expectedStuckOpportunityTitle = "Stuck Opportunities ~ Salesforce - Developer Edition";
		softAssert.assertEquals(actualStuckOpportunityTitle, expectedStuckOpportunityTitle);
		softAssert.assertAll("Stuck Opportunity Page Displayed");

	}
	
	@Test
	public static void salesforceOpportunityQuarterlySummaryLink() {
		Opportunity();
		
		String expectedLink = "Quarterly Summary link";
		String actualLink = driver.getTitle();
		System.out.println("Link : "+actualLink);
		softAssert.assertEquals(actualLink, expectedLink);
		softAssert.assertAll();
	}

}
