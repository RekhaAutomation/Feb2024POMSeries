package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
DriverFactory df;
WebDriver driver;
protected Properties prop;
protected LoginPage loginPage;
protected AccountsPage accPage;
//protected SearchPage searchPage;
protected SearchResultsPage searchResultsPage;
protected ProductInfoPage productInfoPage;
protected RegisterPage regPage;
protected SoftAssert  softAssert;
@Parameters({"browser"})
@BeforeTest
public void setUp(@Optional String browserName) {
	df=new DriverFactory();
	prop=df.initProp();
	
	if(browserName!=null) {
		prop.setProperty("browser", browserName);
	}
	
	driver=df.initDriver(prop);
	loginPage=new LoginPage(driver);
	softAssert=new SoftAssert();
}
@AfterTest
public void tearDown() {
	driver.quit();
}

	//loginPage=new LoginPage(driver);

}