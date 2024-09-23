package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.error.AppError;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void retSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}
	@DataProvider
	public Object[][] userRegTestData(){
		return new Object[][] {
	{"Rekha", "Automation", "1234567893", "test@123", "yes"},
	{"Tom", "Automation","1234567894", "test@123", "np"},
	{"George", "Automation", "1234567895", "test@123", "yes"},
	{"Leu", "Automation", "1234567896", "test@123", "no"},
	};
	}
	
	@Test(dataProvider="userRegTestData")
	public void userRegistrationTest(String firstName,
			String lastName,String telephone,
		String password,String subscribe) {
		Assert.assertTrue(regPage.userRegister(firstName,lastName,
				StringUtils.getRandomEmailId(),telephone,password,subscribe),AppError.USER_REG_NOT_DONE);
	}

}
