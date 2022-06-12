package com.salesforce.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.SalesforceBase;
import com.salesforce.utilities.Constants;

public class SalesforceLogin extends SalesforceBase {

	@Test
	public static void userLoginCorrectUsernameAndPassword() {
		byObject = getByXpath("//input[@id='username']");
		waitUntilVisibilityLocated(byObject);
		sendText(getWebElement(driver.findElement(byObject)), Constants.USER_NAME);
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='password']"))), Constants.PASSWORD);
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='Login']"))), Constants.LOGIN);	
		
		takeScreenShot("Login-Correct");
		
		String expectedPage = "Welcome to your free trial.";
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualPage = driver.getTitle();
		System.out.println(actualPage);
		errorPage(expectedPage, actualPage,"Trial Window");
		//report.logTestInfo("Login - Correct Username and Password - Completed");
		
		
	}

	@Test
	public static void userLoginClearPassword() {
		byObject = getByXpath("//input[@id='username']");
		waitUntilVisibilityLocated(byObject);
		sendText(getWebElement(driver.findElement(byObject)), Constants.USER_NAME);
		clearText((getWebElement(driver.findElement(getByXpath("//input[@id='password']")))), "Password");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='Login']"))), Constants.LOGIN);

		takeScreenShot("Login-clr-pwd");
		
		WebElement errorElement = getWebElement(driver.findElement(getByXpath("//div[@id='error']")));
		String expectedClearPasswordErrorMessage = "Please enter your password.";
		errorMessage(errorElement, expectedClearPasswordErrorMessage, "Clear Password");
		//report.logTestInfo("Login - Clear Password - Completed");

	}

	@Test//(enabled = false)
	public static void userLoginCheckRememberMe() {
		waitUntilVisibilityLocated(getByXpath("//input[@id='username']"));
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='username']"))), Constants.USER_NAME);
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='password']"))), Constants.PASSWORD);
		
		byObject=getByXpath("//input[@id='rememberUn']");
		waitUntilVisibilityLocated(byObject);
		clickButton(getWebElement(driver.findElement(byObject)), "rememberMeChk");
		takeScreenShot("Login-R-Chk");
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='Login']"))), Constants.LOGIN);
		logout();
		
		byObject = getByXpath("//span[@id='idcard-identity']");
		waitUntilVisibilityLocated(byObject);
		WebElement elementUser = getWebElement(driver.findElement(byObject));
		String expectedUserName = Constants.USER_NAME;
		errorMessage(elementUser, expectedUserName, "Remember me-User Name");
		//report.logTestInfo("Login - Remember Me - Completed");
		//report.logTestpassed();
	}

	@Test//(enabled = false)
	public static void userLoginClickForgetPassword() {
		clickButton(getWebElement(driver.findElement(getByXpath("//a[@id='forgot_password_link']"))),
				Constants.FORGET_PASSWORD_LINK);
		sendText(driver.findElement(getByXpath("//input[@id='un']")), Constants.USER_NAME);
		clickButton(driver.findElement(getByXpath("//input[@id='continue']")), "Continue");
		// input[@type='button'] -- cancel
		takeScreenShot("Login-F-pwd");
		
		String expectedCheckTitle = "Check Your Email | Salesforce";
		String actualCheckTitle = driver.getTitle();
		System.out.println(actualCheckTitle);
		Assert.assertEquals(actualCheckTitle, expectedCheckTitle);
		
		/*
		 * WebElement errorElement = getWebElement(driver .findElement(
		 * getByXpath("//p[contains(text(),'We’ve sent you an email with a link to finish rese')]"
		 * ))); String expectedErrorMessage =
		 * "We’ve sent you an email with a link to finish resetting your password.";
		 * errorMessage(errorElement, expectedErrorMessage, "Forget Password");
		 */
		
		//report.logTestInfo("Login - Forget Password - Completed");

	}

	@Test//(enabled = false)
	public static void userLoginWrongUserAndPassword() {
		byObject = getByXpath("//input[@id='username']");
		waitUntilVisibilityLocated(byObject);
		sendText(getWebElement(driver.findElement(byObject)), Constants.WRONG_USER_NAME);
		//report.logTestInfo("User name entered :" + Constants.WRONG_USER_NAME);
		sendText(getWebElement(driver.findElement(getByXpath("//input[@id='password']"))), Constants.WRONG_PASSWORD);
		//report.logTestInfo("Password entered :" + Constants.WRONG_PASSWORD);
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='Login']"))), Constants.LOGIN);
		takeScreenShot("Login-Wrong");
		WebElement errorWrongUserInputElement = getWebElement(driver.findElement(getByXpath("//div[@id='error']")));
		String expectedWrongErrorMessage = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		errorMessage(errorWrongUserInputElement, expectedWrongErrorMessage, "Wrong Username and password-ErrorMessage");
		//report.logTestInfo("Login - Wrong Username and Password - Completed");

	}

}
