package com.salesforce.utilities;

public class Constants {
	public static final String USER_DIR = System.getProperty("user.dir");
	public static final String APPLICATION_PROPERTIES_PATH = USER_DIR + "/src/test/resources/Data.Properties";
	public static final String SCREENSHOT_PATH = USER_DIR + "/ScreenShots/";
	public static final String GENERATE_REPORT_PATH = USER_DIR + "/ExtentReports/SalesforceReport.html";
	public static final String URL = CommonUtilities.getApplicationProperty("url");
	public static final String USER_NAME = CommonUtilities.getApplicationProperty("username");
	public static final String PASSWORD = CommonUtilities.getApplicationProperty("password");
	public static final String LOGIN = CommonUtilities.getApplicationProperty("login");
	public static final String LOGOUT = CommonUtilities.getApplicationProperty("logout");
	public static final String USER_MENU = CommonUtilities.getApplicationProperty("usermenu");
	public static final String CLOSE = CommonUtilities.getApplicationProperty("close");
	public static final String FORGET_PASSWORD_LINK = CommonUtilities.getApplicationProperty("forgetpassword");
	public static final String WRONG_USER_NAME = CommonUtilities.getApplicationProperty("wrongusername");
	public static final String WRONG_PASSWORD = CommonUtilities.getApplicationProperty("wrongpassword");
	public static final String CHROME_BROWSER = CommonUtilities.getApplicationProperty("chromebrowser");
	public static final String FIREFOX_BROWSER = CommonUtilities.getApplicationProperty("firefoxbrowser");
	public static final String EDGE_BROWSER = CommonUtilities.getApplicationProperty("edgebrowser");
	
}
