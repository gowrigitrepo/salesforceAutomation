package com.salesforce.tests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.SalesforceBase;

public class SalesforceAccount extends SalesforceBase {

	@Test
	public static void createAccount() throws InterruptedException {
		Account();
		clickButton((getWebElement(driver.findElement(getByXpath("//div[@id='createNewButton']")))),
				"Create New Account");
		clickButton((getWebElement(driver.findElement(getByXpath("//a[@class='accountMru menuButtonMenuLink']")))),
				"Account");
		byObject = getByXpath("//a[@class='accountMru menuButtonMenuLink']");

		String newAccountName = "acc name"+getTime();
		byObject= getByXpath("//table[@class='detailList']//tr[2]/td//input[@id='acc2']");
		waitUntilVisibilityLocated(byObject);
		sendText((getWebElement(driver.findElement(byObject))),newAccountName);

		clickButton((getWebElement(driver.findElement(getByXpath("//input[@name='save']")))), "Save New Account");

		takeScreenShot("New_Acc");
		//report.logTestInfo("Create New Account Completed");
		//report.logTestpassed();

	}

	@Test
	public static void createAccountCreateNewViewLink() {
		Account();
		clickButton((getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Create New View')]")))),
				"Create New View");
		String viewName = "view_"+getTime();
		byObject = getByXpath("//input[@id='fname']");
		waitUntilVisibilityLocated(byObject);
		sendText((getWebElement(driver.findElement(byObject))), viewName);
		clickButton((getWebElement(driver.findElement(getByXpath("//input[@class='btn primary' and @name='save']")))),
				"Save View Link");
		
		takeScreenShot("Acc_new_view");
		
		

		// *******************
		
		/*
		 * WebElement viewDropDown =
		 * getWebElement(driver.findElement(getByXpath("//select[@id='fcf']")));
		 * List<WebElement> viewDropDownList =
		 * viewDropDown.findElements(getByXpath("//select[@id='fcf']//option"));
		 * 
		 * System.out.println("number of items in user menu=" +viewDropDownList.size());
		 * 
		 * ArrayList<String> expectedViewDropDownList = new ArrayList<String>();
		 * expectedViewDropDownList.add("All Accounts");
		 * expectedViewDropDownList.add("My Accounts");
		 * expectedViewDropDownList.add("New Last Week");
		 * expectedViewDropDownList.add("Newly added");
		 * expectedViewDropDownList.add("New View");
		 * expectedViewDropDownList.add("Platinum and Gold SLA Customers");
		 * expectedViewDropDownList.add("Recently Viewed Accounts");
		 * expectedViewDropDownList.add(viewName);
		 * 
		 * System.out.println(expectedViewDropDownList.toString());
		 * 
		 * ArrayList<String> actualViewDropDownList = new ArrayList<String>();
		 * for(WebElement dropDown : viewDropDownList) {
		 * actualViewDropDownList.add(dropDown.getText()); }
		 * System.out.println(actualViewDropDownList.toString());
		 * 
		 * Assert.assertEquals(actualViewDropDownList, expectedViewDropDownList);
		 */		  
		 
		// *******************

	}

	@Test
	public static void createAccountEditLink() {
		Account();

		selectMethodByIndex(getWebElement(driver.findElement(getByXpath("//select[@id='fcf']"))), 0);
		//switchToElement(getWebElement(driver.findElement(By.partialLinkText("View"))),"Switch Account View Selection");
		//clickButton(getWebElement(driver.findElement(By.partialLinkText("View"))),"Account View Selection");

		//byObject = getByXpath("//div//span[@class='fFooter']//a[1]");
		//byObject = getByXpath("//a[text()='Edit']");
		byObject = getByXpath("//div[@class='filterOverview']/form//div/span//span[@class='fFooter']//a[1]");
		//waitUntilVisibilityLocated(byObject);
		clickButton((getWebElement(driver.findElement(byObject))), "Edit link");

		byObject = getByXpath("//input[@id='fname']");
		waitUntilVisibilityLocated(byObject);

		String editViewName = "View_"+getTime();
		sendText((getWebElement(driver.findElement(byObject))), editViewName);

		selectMethodByVisibleText((getWebElement(driver.findElement(getByXpath("//select[@id='fcol1']")))),
				"Account Name");
		selectMethodByVisibleText((getWebElement(driver.findElement(getByXpath("//select[@id='fop1']")))), "contains");

		sendText((getWebElement(driver.findElement(getByXpath("//input[@id='fval1']")))), "<l>");

		clickButton(
				getWebElement(driver.findElement(getByXpath(
						"//table[@class='layout']//tbody//tr//td//select/option[contains(text(),'Last Activity')]"))),
				"Select Last Activity");
		clickButton(
				getWebElement(driver.findElement(
						getByXpath("//a[@id='colselector_select_0_right']//img[@class='rightArrowIcon']"))),
				"Add Arrow");

		clickButton(
				getWebElement(driver
						.findElement(getByXpath("//table/tbody/tr/td/input[@class='btn primary' and @name='save']"))),
				"Save edit view");
		
		takeScreenShot("Edit_View");
	}

	@Test
	public static void createAccountMergeAccount() {
		Account();
		// a[contains(text(),'Merge Accounts')]
		byObject = getByXpath("//a[contains(text(),'Merge Accounts')]");
		// waitUntilVisibilityLocated(byObject);
		clickButton(getWebElement(driver.findElement(byObject)), "Merge Account");
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='srch']"))), "acc","Search Account to Merge");
		clickButton(
				getWebElement(driver.findElement(
						getByXpath("//div[contains(@class,'pbWizardBody')]//input[contains(@type,'submit')]"))),
				"Find");
		waitUntilElementToBeClickable(getByXpath("//input[@id='cid0']"), "check 0");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='cid0']"))), "First Check Box");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='cid1']"))), "Second Check Box");
		
		//byObject = getByXpath("//input[@class='btn' and @name='goNext']");
		byObject = getByXpath("//input[contains(text(),'goNext']");
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)), "Click Go Next");
		clickButton(getWebElement(driver.findElement(byObject)), "Click Go Next");
		
		clickButton(getWebElement(driver.findElement(byObject)), "Click Merge");
		waitUntilAlertIsPresent();
		AcceptAlert();
	}

	@Test
	public static void createAccountCreateAccountReport() {
		Account();
		clickButton(
				getWebElement(driver
						.findElement(getByXpath("//a[contains(text(),'Accounts with last activity > 30 days')]"))),
				"Create Account Report");
		//byObject = getByXpath("//div[contains(text(),'Created Date')]");
		byObject = getByXpath("//div//input[@id='ext-gen20']");
		
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)), "Date Field");
		System.out.println("Between switch and click");
		clickButton(getWebElement(driver.findElement(byObject)), "Created Date");

		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='ext-comp-1042']"))), getTodaysDateMMDDYYYY());
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='ext-comp-1045']"))), getTodaysDateMMDDYYYY());
		
		clickButton(getWebElement(driver.findElement(getByXpath("//button[@id='ext-gen49']"))), "Save Report");
		
		String baseHandle = driver.getWindowHandle();
		switchToWindow(baseHandle);
		String reportName = "report_30_"+getTime();
		sendText(getWebElement(driver.findElement(getCssSelector("#saveReportDlg_reportNameField"))), reportName);
		clickButton(getWebElement(driver.findElement(getCssSelector("#ext-gen306"))), "Save and Run");
		takeScreenShot("Report_30_");
		System.out.println("Report Saved");

		// input[@id='saveReportDlg_reportNameField']

		// report page should be displayed
	}

}
