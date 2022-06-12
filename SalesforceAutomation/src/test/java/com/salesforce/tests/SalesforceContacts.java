package com.salesforce.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.SalesforceBase;

public class SalesforceContacts extends SalesforceBase {

	@Test
	public static void salesforceContacts() {
		contacts();
		clickButton(getWebElement(driver.findElement(getByXpath("//div[@id='createNewButton']"))),
				"Contacts Create new link");
		clickButton(getWebElement(driver.findElement(getByXpath("//a[@class='contactMru menuButtonMenuLink']"))),
				"Create new link -Contact");
		String lastName = "last_" + getTime();
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='name_lastcon2']"))), lastName, "Last Name");
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='con4']"))), "Account 10", "Account Name");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@class='btn' and @name='save']"))),
				"Save Contact");
	}

	@Test
	public static void salesforceContactsCreateNewView() {
		contacts();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Create New View')]"))),
				"Contacts Create new view link");
		String viewName = "unique_" + getTime();
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='fname']"))), viewName, "Contacts View Name");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@name='save' and @type='submit']"))),
				"Save Contact View");
	}

	@Test
	public static void salesforceContactsRecentlyCreated() {
		contacts();
		clickButton(getWebElement(driver.findElement(getByXpath("//select[@id='hotlist_mode']"))),
				"Recently Created - DropDowm");

	}

	@Test
	public static void salesforceContactsMyContactsView() {
		contacts();
		selectMethodByVisibleText(getWebElement(driver.findElement(getByXpath("//select[@id='fcf']"))),
				"My Contacts View");

	}

	@Test
	public static void salesforceContactsNameFromRecentContactFrame() {
		contacts();
		clickButton(getWebElement(driver.findElement(getByXpath("//table[@class='list']//tr[2]//th[1]"))),
				"Contact Name in Recent Contact Frame");

	}

	@Test
	public static void salesforceContactsCreateNewViewUniqueName() {
		contacts();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Create New View')]"))),
				"Contacts Create new view link");
		String uniqueViewName = "unique_" + getTime();
		byObject = getByXpath("//input[@id='devname']");
		waitUntilVisibilityLocated(byObject);
		sendText(getWebElement(driver.findElement(byObject)), uniqueViewName, "Contacts Unique View Name");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@name='save' and @type='submit']"))),
				"Save Contact View");

		WebElement errorViewNameField = getWebElement(
				driver.findElement(getByXpath("//div[contains(text(),'You must enter a value')]")));
		String expectedErrorInViewNameField = "Error: You must enter a value";
		String actualErrorInViewNameField = errorViewNameField.getText();
		System.out.println(actualErrorInViewNameField);
		Assert.assertEquals(actualErrorInViewNameField, expectedErrorInViewNameField);

	}

	@Test
	public static void salesforceContactsCreateNewViewCancel() {
		contacts();
		
		WebElement beforeViewElement = getWebElement(driver.findElement(getByXpath("//select[@id='fcf']//option[7]")));
		String currentViewName = beforeViewElement.getText();
		System.out.println("currentViewName : "+currentViewName);
		
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Create New View')]"))),
				"Contacts Create new view link");
		String viewName = "unique_" + getTime();
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='fname']"))), viewName, "Contacts View Name");
		String uniqueViewName = "unique_" + getTime();
		byObject = getByXpath("//input[@id='devname']");
		waitUntilVisibilityLocated(byObject);
		sendText(getWebElement(driver.findElement(byObject)), uniqueViewName, "Contacts Unique View Name");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@class='btn' and @name='cancel']"))),
				"Cancel Contact View");
		
		ArrayList<String> expectedViewAndTitle = new ArrayList<String>();
		expectedViewAndTitle.add(currentViewName);
		expectedViewAndTitle.add("Contacts: Home ~ Salesforce - Developer Edition");
		
		WebElement afterViewElement = getWebElement(driver.findElement(getByXpath("//select[@id='fcf']//option[7]")));
		String presentViewName = afterViewElement.getText();
		System.out.println("presentViewName : "+presentViewName);
		
		ArrayList<String> actualViewAndTitle = new ArrayList<String>();
		actualViewAndTitle.add(presentViewName);
		actualViewAndTitle.add(driver.getTitle());
		System.out.println(driver.getTitle());
		
		Assert.assertEquals(actualViewAndTitle, expectedViewAndTitle);
			
	}
	
	@Test
	public static void salesforceContactNewButtonClickSaveAndNew() {
		contacts();
		
		clickButton(getWebElement(driver.findElement(getByXpath("//td[@class='pbButton']//input[@type='button']"))), "Contact New Button");
		String lastNameContact = "last_contact_"+getTime();
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='name_lastcon2']"))), lastNameContact, "Contact Last Name");
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='con4']"))), "Account 3", "Contact Account Name");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@class='btn' and @name='save_new']"))), "Contact Save and New");
		
		String expectedSaveAndNewTitle = "Contact Edit: New Contact ~ Salesforce - Developer Edition";
		String actualSaveAndNewTitle = driver.getTitle();
		System.out.println("actualSaveAndNewTitle : "+actualSaveAndNewTitle);
		Assert.assertEquals(actualSaveAndNewTitle, expectedSaveAndNewTitle);
	}
	
	
	
	
	
	
	
	
	

}
