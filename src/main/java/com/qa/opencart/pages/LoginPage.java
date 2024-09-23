package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// public constructor of the page:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public page actions /methods of the page
	@Step("gettig login page title")
	public String getLoginpageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
//				String title=driver.getTitle();
		System.out.println("Login page title is " + title);
		return title;
	}
	
	@Step("gettig login page Url")
	public String getLoginpageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		// String url=driver.getCurrentUrl();
		System.out.println("Login page url is " + url);
		return url;
	}
	@Step("gettig forgot password link status....")
	public boolean checkForgotPwdLinkExist() {
		// return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.doIsDisplayed(forgotPwdLink);

	}
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink,TimeUtil.DEFAULT_TIME);
		return new RegisterPage(driver);
	}
	@Step("login into application with username :{0} and password:{1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user credentials are "+username+ " " + pwd);
		eleUtil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
//				driver.findElement(emailId).sendKeys(username);
//				driver.findElement(password).sendKeys(pwd);
//				driver.findElement(loginBtn).click();
		// return AccountsPage(driver);
		return new AccountsPage(driver);
	}

}
