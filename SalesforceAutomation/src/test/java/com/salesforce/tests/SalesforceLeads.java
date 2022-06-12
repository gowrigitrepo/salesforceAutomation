package com.salesforce.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.SalesforceBase;

public class SalesforceLeads extends SalesforceBase {

	@Test
	public static void salesforceLeads() {
		leads();
		String actualLeadsTitle = driver.getTitle();
		String expectedLeadsTitle = "Leads: Home ~ Salesforce - Developer Edition";
		System.out.println("Actual page : " + actualLeadsTitle);
		System.out.println("Expected page : " + expectedLeadsTitle);
		softAssert.assertEquals(actualLeadsTitle, expectedLeadsTitle);
		softAssert.assertAll("Leads Page Displayed");
		// report.logTestInfo("Leads Completed");
		// report.logTestpassed();
	}

	@Test
	public static void salesforceLeadsDropDown() {
		leads();
		WebElement viewDropDown = getWebElement(driver.findElement(getByXpath("//select[@id='fcf']")));
		List<WebElement> viewDropDownList = viewDropDown.findElements(getByXpath("//select[@id='fcf']//option"));

		ArrayList<String> expectedViewDropDownList = new ArrayList<String>();
		expectedViewDropDownList.add("All Open Leads");
		expectedViewDropDownList.add("My Unread Leads");
		expectedViewDropDownList.add("Recently Viewed Leads");
		expectedViewDropDownList.add("Today's Leads");
		expectedViewDropDownList.add("View - Custom 1");
		expectedViewDropDownList.add("View - Custom 2");

		ArrayList<String> actualViewDropDownList = new ArrayList<String>();
		for (WebElement dropDown : viewDropDownList) {
			actualViewDropDownList.add(dropDown.getText());
		}

		// Assert.assertEquals(actualViewDropDownList, expectedViewDropDownList);
		softAssert.assertEquals(actualViewDropDownList, expectedViewDropDownList);
		softAssert.assertAll();
		// report.logTestInfo("Leads - Drop Down - Completed");
		// report.logTestpassed();

	}

	@Test
	public static void leadsViewTodaysLeadLogoutAndLogin() {
		leads();
		switchToElement(getWebElement(driver.findElement(getByXpath("//select[@name='fcf']"))), "Leads View");
		clickButton(
				getWebElement(driver.findElement(getByXpath("//select//option[contains(text(),\"Today's Lead\")]"))),
				"Today's Lead");
		System.out.println("b4 logout");
		logout();
		System.out.println("after logout");
		userLogin();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("b4 leads");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leads();
		System.out.println("b4 go");
		byObject = getByXpath("//input[@type='button' and @name='go']");
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)), "Go");
		clickButton(getWebElement(driver.findElement(byObject)), "Leads Go");
		System.out.println("afetr go");
	}

	@Test
	public static void leadsViewTodaysLead() {
		leads();
		byObject = getByXpath("//select[@name='fcf']");
		System.out.println("B4 wait lead today");
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)), "Leads View");
		System.out.println("after switch lead today");
		clickButton(
				getWebElement(driver.findElement(getByXpath("//select//option[contains(text(),\"Today's Lead\")]"))),
				"Today's Lead");

		String expectedLeadViewDropDown = "Today's Leads";
		String actualLeadViewDropDown = getWebElement(
				driver.findElement(getByXpath("//select//option[contains(text(),\"Today's Lead\")]"))).getText();
		System.out.println("actualLeadViewDropDown : " + actualLeadViewDropDown);
		Assert.assertEquals(actualLeadViewDropDown, expectedLeadViewDropDown);

	}

	@Test
	public static void salesforceLeadNewButton() {
		leads();
		clickButton(
				getWebElement(driver
						.findElement(getByXpath("//td[contains(@class,'pbButton')]//input[contains(@type,'button')]"))),
				"Lead New Button");

		String lastName = "ABCD";
		String companyName = "ABCD";
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='name_lastlea2']"))), lastName,
				"Lead Edit Last Name");
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='lea3']"))), companyName,
				"Lead Edit Company Name");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@class='btn' and @name='save']"))),
				"Lead Edit Save");
		
		String expectedLeadEditTitle = "Lead: "+lastName+" ~ Salesforce - Developer Edition";
		String actualLeadEditTitle = driver.getTitle();
		Assert.assertEquals(actualLeadEditTitle, expectedLeadEditTitle);
	}

}
