package com.salesforce.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.salesforce.utilities.CommonUtilities;
import com.salesforce.utilities.Constants;
import com.salesforce.utilities.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceBase extends CommonUtilities {

	protected static WebDriver driver;
	protected static By byObject;
	static WebDriverWait wait;
	static Select selectObject;
	protected static SoftAssert softAssert = new SoftAssert();

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static GenerateReports report;
	
	/**
	 * Name of the method : initialTestSetup Brief Description : Salesforce
	 * Application initialTestSetup Arguments : Created By : Automation Team Created
	 * Date : Last Modified Date :
	 */
	@BeforeTest
	public static void initialTestSetup() {
		System.out.println("Before Test Method");
		report = GenerateReports.getInstance();
		//report.startExtentReport();
	}

	/**
	 * Name of the method : setUp Brief Description : Salesforce Application setUp
	 * Arguments : Created By : Automation Team Created Date : Last Modified Date :
	 */
	
	@BeforeMethod
	@Parameters({"browser"})
	public static void setUp(@Optional("chrome")String browser) {
		goToDriver(browser);
		goToURL(CommonUtilities.getApplicationProperty("url"));
	}

	/**
	 * Name of the method : tearDown Brief Description : Salesforce Application
	 * tearDown Arguments : Created By : Automation Team Created Date : Last
	 * Modified Date :
	 */
	@AfterMethod
	public static void tearDown() {
		closeAllDriver();
	}

	/**
	 * Name of the method : finalTearDown Brief Description : Salesforce Application
	 * finalTearDown Arguments : Created By : Automation Team Created Date : Last
	 * Modified Date :
	 */

	@AfterTest
	public static void finalTearDown() {
		//report.endReport();
	}

	/**
	 * Name of the method : goToDriver Brief Description : Salesforce Application
	 * goToDriver Arguments : Created By : Automation Team Created Date : Last
	 * Modified Date :
	 */
	public static void goToDriver(String browser) {
		System.out.println("Browser in Driver : " + browser);
		if (browser.equalsIgnoreCase("Chrome")) {
			System.out.println("Inside Chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			// report.logTestInfo("Chrome driver instance created");
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.out.println("Inside Firefox");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// report.logTestInfo("FireFox driver instance created");
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			// report.logTestInfo("Edge driver instance created");
		} else {
			System.out.println("No browser instance found");
			// report.logTestInfo("No browser instance found");
		}
	}

	/**
	 * Name of the method : goToChromeDriver Brief Description : Salesforce
	 * Application goToChromeDriver Arguments : Created By : Automation Team Created
	 * Date : Last Modified Date :
	 */

	public static void goToChromeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// report.logTestInfo("Chrome driver instance created");
	}

	/**
	 * Name of the method : goToFirefoxDriver Brief Description : Salesforce
	 * Application goToFirefoxDriver Arguments : Created By : Automation Team
	 * Created Date : Last Modified Date :
	 */
	public static void goToFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		// report.logTestInfo("FireFox driver instance created");
	}

	/**
	 * Name of the method : goToEdgeDriver Brief Description : Salesforce
	 * Application goToEdgeDriver Arguments : Created By : Automation Team Created
	 * Date : Last Modified Date :
	 */
	public static void goToEdgeDriver() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		// report.logTestInfo("Edge driver instance created");
	}

	/**
	 * Name of the method : goToURL Brief Description : Salesforce Application
	 * goToURL Arguments : Created By : Automation Team Created Date : Last Modified
	 * Date :
	 */
	public static void goToURL(String url) {
		driver.get(url);
		// report.logTestInfo("URL entered : " + url);
	}
	
	public static WebDriver getDriverInstance(){
		return driver;
	}


	/**
	 * Name of the method : userLogin Brief Description : Salesforce Application
	 * userLogin Arguments : Created By : Automation Team Created Date : Last
	 * Modified Date :
	 */

	public static void userLogin() {
		byObject = getByXpath("//input[@id='username']");
		waitUntilVisibilityLocated(byObject);
		WebElement userNameElement = getWebElement(driver.findElement(byObject));
		sendText(userNameElement, Constants.USER_NAME);
		// report.logTestInfo("User name entered :" + Constants.USER_NAME);
		WebElement passwordElement = getWebElement(driver.findElement(getByXpath("//input[@id='password']")));
		sendText(passwordElement, Constants.PASSWORD);
		// report.logTestInfo("Password entered :" + Constants.PASSWORD);
		clickButton(getWebElement(driver.findElement(getByXpath("//input[@id='Login']"))), Constants.LOGIN);
		// report.logTestInfo("User Logged In");
	}

	/**
	 * Name of the method : userMenu Brief Description : Salesforce Application
	 * userMenu Arguments : Created By : Automation Team Created Date : Last
	 * Modified Date :
	 */

	public static void userMenu() {
		userLogin();
		byObject = getCssSelector("#userNavLabel");
		waitUntilVisibilityLocated(byObject);
		if (getWebElement(driver.findElement(byObject)).isDisplayed()) {
			clickButton(getWebElement(driver.findElement(getCssSelector("#userNavLabel"))), Constants.USER_MENU);
			// report.logTestInfo("User Menu Selected");
		} else {
			System.out.println("User Menu - FAILED");
			// report.logTestInfo("User Menu - FAILED");
		}
	}

	/**
	 * Name of the method : Account Brief Description : Salesforce Application
	 * Account Arguments : Created By : Automation Team Created Date : Last Modified
	 * Date :
	 */

	public static void Account() {
		userLogin();
		//waitUntilVisibilityLocated(getByXpath("//a[contains(text(),'Accounts')]"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement accountElement = getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Accounts')]")));
		if (accountElement.isDisplayed()) {
			switchToElement((accountElement), "Accounts Tab");
			clickButton((accountElement), "Accounts");
			// report.logTestInfo("Accounts Tab Selected");
			byObject = getCssSelector("a#tryLexDialogX.dialogClose");
			waitUntilVisibilityLocated(byObject);
			clickButton((getWebElement(driver.findElement(byObject))), "Close");
		} else {
			System.out.println("Account Tab - FAILED");
			// report.logTestInfo("Account Tab - FAILED");
		}
	}

	/**
	 * Name of the method : Opportunity Brief Description : Salesforce Application
	 * Opportunity Arguments : Created By : Automation Team Created Date : Last
	 * Modified Date :
	 */

	public static void Opportunity() {
		userLogin();
		WebElement opportunityElement = getWebElement(
				driver.findElement(getByXpath("//a[contains(text(),'Opportunities')]")));
		if (opportunityElement.isDisplayed()) {
			switchToElement((opportunityElement), "Opportunity Tab");
			clickButton((opportunityElement), "Opportunities");
			// report.logTestInfo("Opportunities Tab Selected");
			byObject = getCssSelector("a#tryLexDialogX.dialogClose");
			waitUntilVisibilityLocated(byObject);
			clickButton((getWebElement(driver.findElement(byObject))), "Close");
		} else {
			System.out.println("Opportunity Tab FAILED");
			// report.logTestInfo("Opportunity Tab FAILED");
		}

	}

	/**
	 * Name of the method : leads Brief Description : Salesforce Application leads
	 * Arguments : Created By : Automation Team Created Date : Last Modified Date :
	 */

	public static void leads() {
		userLogin();
		byObject = getByXpath("//a[contains(text(),'Leads')]");
		//waitUntilVisibilityLocated(byObject);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement leadsElement = getWebElement(driver.findElement(byObject));
		if (leadsElement.isDisplayed()) {
			switchToElement(leadsElement, "Leads Tab");
			clickButton(leadsElement, "Leads");
			// report.logTestInfo("Leads Tab Selected");
			byObject = getCssSelector("a#tryLexDialogX.dialogClose");
			waitUntilVisibilityLocated(byObject);
			clickButton((getWebElement(driver.findElement(byObject))), Constants.CLOSE);
		} else {
			System.out.println("Leads Tab FAILED");
			// report.logTestInfo("Leads Tab FAILED");
		}

	}
	
	/**
	 * Name of the method : contacts Brief Description : Salesforce Application contacts
	 * Arguments : Created By : Automation Team Created Date : Last Modified Date :
	 */
	public static void contacts() {
		userLogin();
		WebElement contactElement = getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Contacts')]")));
		if (contactElement.isDisplayed()) {
		clickButton(contactElement, "Contacts");
		byObject = getCssSelector("a#tryLexDialogX.dialogClose");
		waitUntilVisibilityLocated(byObject);
		clickButton((getWebElement(driver.findElement(byObject))), "Close");
		}else {
			System.out.println("Contact Tab FAILED");
		}
	}

	/**
	 * Name of the method : logout Brief Description : Salesforce Application logout
	 * Arguments : Created By : Automation Team Created Date : Last Modified Date :
	 */

	public static void logout() {
		waitUntilVisibilityLocated(getCssSelector("#userNavLabel"));
		clickButton(getWebElement(driver.findElement(getCssSelector("#userNavLabel"))), Constants.USER_MENU);
		clickButton(getWebElement(driver.findElement(getByXpath("//a[contains(text(),'Logout')]"))), Constants.LOGOUT);
		// report.logTestInfo("Logged out");
	}

	
	public static By getById(String id) {
		return By.id(id);
	}

	public static By getByTagName(String tagName) {
		return By.tagName(tagName);
	}

	public static By getByName(String name) {
		return By.name(name);
	}

	public static By getByClassName(String className) {
		return By.className(className);
	}

	public static By getByXpath(String xpath) {
		return By.xpath(xpath);
	}

	public static By getCssSelector(String css) {
		return By.cssSelector(css);
	}

	public static WebElement getWebElement(WebElement findElement) {
		return findElement;
	}

	public static WebElement getWebElements(WebElement findElements) {
		return findElements;
	}

	public static void selectMethodByVisibleText(WebElement selectElement, String visibleText) {
		if (selectElement.isDisplayed()) {
			selectObject = new Select(selectElement);
			selectObject.selectByVisibleText(visibleText);
			// report.logTestInfo("Passed Selecting the text " + visibleText);
		} else {
			System.out.println("Failed Selecting the text " + visibleText);
			// report.logTestInfo("Failed Selecting the text " + visibleText);
		}

	}

	public static void selectMethodByVisibleText(WebElement selectElement, String visibleText, String selectText) {
		if (selectElement.isDisplayed()) {
			selectObject = new Select(selectElement);
			selectObject.selectByVisibleText(visibleText);
			// report.logTestInfo("Passed Selecting the text at " + selectText);
		} else {
			System.out.println("Failed Selecting the test at " + selectText);
			// report.logTestInfo("Failed Selecting the test at " + selectText);
		}
	}

	public static void selectMethodByIndex(WebElement selectElement, int index) {
		if (selectElement.isDisplayed()) {
			selectObject = new Select(selectElement);
			selectObject.selectByIndex(index);
			// report.logTestInfo("Passed Selecting at index " + index);
		} else {

			System.out.println("Failed Selecting at index" + index);
			// report.logTestInfo("Failed Selecting at index" + index);
		}
	}

	public static void selectMethodByIndex(WebElement selectElement, int index, String selectText) {
		if (selectElement.isDisplayed()) {
			selectObject = new Select(selectElement);
			selectObject.selectByIndex(index);
			// report.logTestInfo("Passed Selecting " + selectText);
		} else {
			System.out.println("Failed Selecting " + selectText);
			// report.logTestInfo("Failed Selecting " + selectText);
		}
	}

	public static void sendText(WebElement textElement, String sendText) {
		if (textElement.isDisplayed()) {
			textElement.clear();
			textElement.sendKeys(sendText);
			// report.logTestInfo("Passed Sending text : " + sendText);
		} else {
			System.out.println("Failed Sending text : " + sendText);
			// report.logTestInfo("Failed Sending text : " + sendText);
		}
	}

	public static void sendText(WebElement textElement, String sendText, String whatText) {
		if (textElement.isDisplayed()) {
			textElement.clear();
			textElement.sendKeys(sendText);
			// report.logTestInfo("Passed Sending text : " + sendText + "To" + whatText);
		} else {
			System.out.println("Failed Sending text : " + sendText + "To" + whatText);
			// report.logTestInfo("Failed Sending text : " + sendText + "To" + whatText);
		}
	}

	public static void clickButton(WebElement buttonElement, String buttonText) {
		if (buttonElement.isDisplayed()) {
			buttonElement.click();
			// report.logTestInfo("Passed Clicking Button : " + buttonText);
		} else {
			// report.logTestInfo("Failed Clicking Button : " + buttonText);
		}
	}

	public static void switchToElement(WebElement switchTo, String switchText) {
		Actions action = new Actions(driver);
		action.moveToElement(switchTo).build().perform();
		// report.logTestInfo("Switched to Tab : " + switchText);
	}

	public static String switchToWindow(String mainWindowHandle) {
		String returnHandle = null;
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle)) {
				driver.switchTo().window(handle);
			}
			returnHandle = handle;
		}
		return returnHandle;
	}

	public static void mouseOver(WebElement element, String mouseOverText) {
		if (element.isDisplayed()) {
			waitUntilVisibleElement(element, mouseOverText);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			// report.logTestInfo("Mouse Over on : " + mouseOverText);
		} else {
			System.out.println("Mouse over failed on" + mouseOverText);
			// report.logTestInfo("Mouse over failed on" + mouseOverText);
		}
	}

	private static Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		// report.logTestInfo("Alert : " + alert.getText());
		return alert;
	}

	public static void AcceptAlert() {
		waitUntilAlertIsPresent();
		switchToAlert().accept();
		// report.logTestInfo("Alert Accepted");

	}

	public static void dismisAlert() {
		waitUntilAlertIsPresent();
		switchToAlert().dismiss();
		// report.logTestInfo("Alert Dismissed");
	}

	public static void clearText(WebElement element, String clearText) {
		if (element.isDisplayed()) {
			element.clear();
			// report.logTestInfo("Cleared Text on " + clearText);
		} else {
			System.out.println("Failed to Clear text on " + clearText);
			// report.logTestInfo("Failed to Clear text on " + clearText);
		}
	}

	public static String takeScreenShot(String methodName) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// report.logTestInfo("Screeen Shot Captured");
		String screenShotName = "Salesforce_" + methodName + "_" + getTime() + ".jpg";
		String filePath = Constants.SCREENSHOT_PATH + screenShotName;
		File DestFile = new File(filePath);
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	public static void errorMessage(WebElement errorElement, String expectedErrorMessage, String errorTextField) {
		if (errorElement.isDisplayed()) {
			String actualErrorMessage = errorElement.getText();
			System.out.println(actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
			// softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);
			// softAssert.assertAll();
			// report.logTestInfo("Error Message Found" + errorTextField);
		} else {
			System.out.println("No Error Message Found" + errorTextField);
			// report.logTestInfo("No Error Message Found" + errorTextField);
		}
	}

	public static void errorPage(String expectedPage, String actualPage, String errorPage) {
		softAssert.assertEquals(actualPage, expectedPage);
		softAssert.assertAll();
		// report.logTestInfo(errorPage + " Page not Found");
	}

	public static void closeDriver() {
		driver.close();
		// report.logTestInfo("Opened Window closed");
	}

	public static void closeAllDriver() {
		driver.quit();
		// report.logTestInfo("All Opened Windows closed");
	}

	public static void waitUntilVisibilityLocated(By byLocator) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
	}

	public static void waitUntilVisibleElement(WebElement element, String objName) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilAlertIsPresent() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator, String objName) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void fluentWait(By byLocator) {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
}
