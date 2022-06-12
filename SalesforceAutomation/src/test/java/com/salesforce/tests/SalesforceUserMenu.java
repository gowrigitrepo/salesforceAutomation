package com.salesforce.tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.base.SalesforceBase;

public class SalesforceUserMenu extends SalesforceBase {

	@Test
	public static void userMenuDropDown() {
		userMenu();
		WebElement userMenuDropDown = getWebElement(driver.findElement(getByXpath("//div[@id='userNav-menuItems']")));
		List<WebElement> userMenuDropDownList = userMenuDropDown
				.findElements(getByXpath("//div[@id='userNav-menuItems']//a"));

		ArrayList<String> expectedUserDropDownList = new ArrayList<String>();
		expectedUserDropDownList.add("My Profile");
		expectedUserDropDownList.add("My Settings");
		expectedUserDropDownList.add("Developer Console");
		expectedUserDropDownList.add("Switch to Lightning Experience");
		expectedUserDropDownList.add("Logout");

		ArrayList<String> actualUserDropDownList = new ArrayList<String>();
		for (WebElement dropDown : userMenuDropDownList) {
			actualUserDropDownList.add(dropDown.getText());
		}

		Assert.assertEquals(actualUserDropDownList, expectedUserDropDownList);

	}

	@Test
	public static void userMenuDropDownMyProfile() {
		userMenu();

		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'My Profile')]"))), "My Profile");

		byObject = getByXpath("//a[@id='moderatorMutton']//b[@class='zen-selectArrow']");
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)), "Down Arrow Switched");
		clickButton(getWebElement(driver.findElement(byObject)), "Down Arrow Clicked");

		byObject = getByXpath("//a[contains(text(),'Edit Profile')]");
		waitUntilVisibilityLocated(byObject);
		switchToElement(getWebElement(driver.findElement(byObject)), "Edit Profile Switch");
		clickButton(getWebElement(driver.findElement(byObject)), "Edit Profile Click");

		System.out.println("Before About tab");
		// getByXpath("//*[@class='zen']//div//ul//li[@id='aboutTab']")
		byObject = getByXpath("//*[@id='aboutTab']");
		waitUntilVisibilityLocated(byObject);
		//switchToElement(getWebElement(driver.findElement(byObject)), "Edit Profile about tab Click");
		clickButton(getWebElement(driver.findElement(byObject)), "Edit Profile about tab Click");
		System.out.println("After About tab");

		System.out.println("After  Edit Profile - About");
		System.out.println("Before  Edit Profile - About-Last Name");
		switchToElement(getWebElement(driver.findElement(getByXpath("//input[@id='lastName']"))), "Last Name Switch");
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='lastName']"))), "laast", "Last Name");
		System.out.println("After  Edit Profile - About-Last Name");
		System.out.println("Before  Edit Profile - About-Last Name - Save");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@class='zen-btn zen-primaryBtn zen-pas']"))),
				"Save in About Tab");
		System.out.println("After  Edit Profile - About-Last Name - Save");

		// span[contains(@class,'publisherattachtext')][contains(text(),'File')] -- File
		// upload
		closeDriver();
	}

	@Test
	public static void userDropDownMySettings() {
		userMenu();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'My Settings')]"))),
				"My Settings");

		byObject = getByXpath("//div[@id='PersonalInfo']/a[@class='header setupFolder']/span[@class='folderText']");
		waitUntilVisibilityLocated(byObject);
		clickButton(getWebElement(driver.findElement(byObject)), "Personal");
		clickButton(getWebElement(driver.findElement(getByXpath("//span[@id='LoginHistory_font']"))),
				"Personal Login History");

		byObject = getByXpath("//div[@id='DisplayAndLayout']");
		clickButton(getWebElement(driver.findElement(byObject)), "Display And Layout");
		clickButton(getWebElement(driver.findElement(getByXpath("//span[@id='CustomizeTabs_font']"))),
				"Customize My Tabs");
		byObject = getByXpath(
				"//div[@class='pbSubsection']//select[@id='p4']//option[contains (text(),'Salesforce Chatter')]");
		waitUntilVisibilityLocated(byObject);
		clickButton(getWebElement(driver.findElement(byObject)), "Select Salesforce Chatter");
		clickButton(
				getWebElement(driver.findElement(getByXpath(
						"//table/tbody/tr/td[@class='selectCell']/select/option[contains(text(),'Reports')]"))),
				" Add Reports");
		clickButton(getWebElement(driver.findElement(getByXpath("// img[contains(@class,'rightArrowIcon')]"))), " Add");
		clickButton(getWebElement(driver.findElement(getByXpath("//table/tbody/tr/td/input[@name='save']"))),
				"Save Reports Tab");

		byObject = getByXpath("//div[@id='EmailSetup']");
		clickButton(getWebElement(driver.findElement(byObject)), "EMail");
		clickButton(getWebElement(driver.findElement(getByXpath("//span[@id='EmailSettings_font']"))),
				"My Email Settings");

		byObject = getByXpath("//input[@id='sender_name']");
		waitUntilVisibilityLocated(byObject);
		sendText(getWebElement(driver.findElement(byObject)), "firstname last");
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='sender_email']"))),
				"gowrivelusamy@gmail.com");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='auto_bcc1']"))), "BCC radio");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@class='btn primary' and @name = 'save']"))),
				"Save Email");

		byObject = getByXpath("//span[@id='CalendarAndReminders_font']");
		clickButton(getWebElement(driver.findElement(byObject)), "Calender and Remainders");
		clickButton(getWebElement(driver.findElement(getByXpath("//a[@id='Reminders_font']"))), "Activity Remainders");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='testbtn']"))), "Open Test Remainders");

		String baseWindowHandle = driver.getWindowHandle();
		switchToWindow(baseWindowHandle);

		String expectedTitle = "Activity Reminders ~ Salesforce - Developer Edition";
		String actualTitle = driver.getTitle();
		closeDriver();
		System.out.println("Window Title : " + actualTitle);
		// Assert.assertEquals(actualTitle, expectedTitle);
		softAssert.assertEquals(actualTitle, expectedTitle);
		softAssert.assertAll("Test Pass");

	}

	@Test
	public static void userMenuDropDownDeveloperConsole() {
		userMenu();
		clickButton(getWebElement(driver.findElement(getByXpath("//a[@class='debugLogLink menuButtonMenuLink']"))),
				"Developer console");

		String baseHandle = driver.getWindowHandle();
		switchToWindow(baseHandle);
		String actualTitle = driver.getTitle();
		String expectedTitle = "Developer Console";
		Assert.assertEquals(actualTitle, expectedTitle);
		//report.logTestInfo("User Menu - Developer Console - Completed");
		closeDriver();
	}

	@Test
	public static void userMenuDropDownLogout() {
		userLogin();
		logout();
	}
}
