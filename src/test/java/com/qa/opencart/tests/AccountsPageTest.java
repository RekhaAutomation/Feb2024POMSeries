package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.error.AppError;

public class AccountsPageTest  extends BaseTest
//create test methods and write assertions
{
@BeforeTest
public void accPageSetup() {
	accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
}
@Test
public void accPageTitleTest() {
	Assert.assertEquals(accPage.getAccountsPageTitle(),AppConstants.ACCOUNTS_PAGE_TILE,AppError.TITLE_NOT_FOUND);
}
public void accPageURLTest() {
	Assert.assertTrue(accPage.getAccountsPageURL().contains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
}
@Test
public void accPageHeadersTest() {
	List<String> accPageHeadersList=accPage.getAccPageHeaders();
	Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST,AppError.LIST_NOT_MATCHED);
}
@DataProvider
public Object[][] getSearchData() {
	return new Object[] [] {
		{"macbook",3},
		{"imac",1},
		{"samsung",2},
		{"Airtel",0}
	};
	
	}
			

	  @Test (dataProvider="getSearchData")
	  public void searchTest(String searchKey,int resultsCount) {
	 searchResultsPage=accPage.doSearch("macbook");
	Assert.assertEquals(searchResultsPage.getSerchResultsCount(),3,AppError.RESULTS_COUNT_MISMATCHED);
	 }
	 
	
	
}

