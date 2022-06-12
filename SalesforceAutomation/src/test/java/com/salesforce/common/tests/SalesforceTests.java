package com.salesforce.common.tests;

import org.testng.annotations.Test;

import com.salesforce.base.SalesforceBase;

public class SalesforceTests extends SalesforceBase{
	
	
	public static void salesforceLogin() {
		userLogin();
		//userLoginClearPassword("training@automation.com","", "Login");
		//userLoginCheckRememberMe("training@automation.com","testing@123", "Remember Me Check");
		//userLoginClickForgetPassword("training@automation.com","testing@123", "Forget Password Link");
		//userLoginWrongUserAndPassword("training@au.com","ing@123", "Wrong User Name and Password");
	}
	
	
	public static void salesforceAccountsTab() throws InterruptedException {
		//goToChromeDriver();
		//goToFirefoxDriver();
		goToEdgeDriver();
		goToURL("https://automation-5a-dev-ed.my.salesforce.com/");
		userLogin();
		//createAccountNew("Account 10");
		//createAccountNewViewLink();
		//createAccountMergeAccount();
		//closeDriver();
	}
	
	public static void salesforceUserMenu() {
		
	}
	
	
	public static void salesforceOppourtunitiesTab() {
		goToChromeDriver();
		//goToFirefoxDriver();
		goToURL("https://automation-5a-dev-ed.my.salesforce.com/");
		userLogin();
		//oppourtunitiesTab();
	}
	
	
	public static void salesforceLeadsTab() {
		goToChromeDriver();
		//goToFirefoxDriver();
		goToURL("https://automation-5a-dev-ed.my.salesforce.com/");
		userLogin();
		//leadsTab();
	}
	
}
