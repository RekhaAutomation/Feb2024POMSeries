package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.error.AppError;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
//allure --version
//go to windows power shell 
// go to project directory
// type allure allure-serve allure-results-This will launch the report in browser
@Epic("Epic 100: Design Opencart application with shopping flow")
@Story("US 101:Desing open cart application login flow")
@Owner("Naveen automation labs")
@Listeners({ExtentReportListener.class,TestAllureListener.class})
//note: Retry login is not working with Retry with annotation, works only with xml file
public class LoginPageTest extends BaseTest {
	@Description("Checking login page title----")
@Severity(SeverityLevel.MINOR)
@Issue("Login-123")
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginpageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
@Description("Checking login page url----")
@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageUrlTest() {
		String actURL=loginPage.getLoginpageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
				}
@Description("Checking forgot password link on the login page ----")	
@Severity(SeverityLevel.CRITICAL)
@Feature("Login page forgot password feature")
@Owner("Rekha ganib")
@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotPwdLinkExist(), AppError.ELEMENT_NOT_FOUND);
	}
@Description("Checking user is  able to login successfully----")
@Severity(SeverityLevel.BLOCKER)
@Owner("Rekha ganib")
@Feature("Login page feature")
	@Test(priority=4)
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		//when passing password as command line argument....
		//accPage=loginPage.doLogin(prop.getProperty("username"),System.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(),AppConstants.ACCOUNTS_PAGE_TILE,AppError.TITLE_NOT_FOUND);
		//Assert.assertEquals(actAccPageTitle, AppConstants.ACCOUNTS_PAGE_TILE, AppError.TITLE_NOT_FOUND);
	}
}
