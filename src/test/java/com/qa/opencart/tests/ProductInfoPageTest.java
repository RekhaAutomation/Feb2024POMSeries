package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.error.AppError;

public class ProductInfoPageTest  extends BaseTest{

	@BeforeClass 
	public void productInfoPagesetUp() {
	accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
	}
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"}
		};	
		
	}
	@Test(dataProvider="getProductData")
	public void productHeaderTest(String searchKey,String productName) {
		searchResultsPage=accPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(),productName ,AppError.HEADER_NOT_FOUND);
	}
	@DataProvider
	public Object[][] getProductImageData(){
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3}
		};	
		
	}
	
	@Test(dataProvider="getProductImageData")
	public void productImageCountTest(String searchKey,String productName,int imagesCount) {
		searchResultsPage=accPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(),imagesCount,AppError.IMAGES_COUNT_MISMATCHED);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage=accPage.doSearch("macbook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap=productInfoPage.getProductInfoMap();
		System.out.println("======product Information====");
		System.out.println(productInfoMap);
		
		softAssert.assertEquals(productInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00");
		
		softAssert.assertAll();//failure information
		
		//if there are more than one assertion in one test method, we can use soft assert instead
		//of hardAssert.
		
	}
}
