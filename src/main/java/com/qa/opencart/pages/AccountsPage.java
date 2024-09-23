package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	
	//pattern to follow for each page class --create constructor
	//create by locators
	//build methods to perform actions on locators 
	
	//1.create a constructor
	private WebDriver driver;
	private ElementUtil eleUtil;
	public  AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
			}
	//step2-create private by locators
	private By logoutLink=By.linkText("Logout");
	private By headers=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("div#search button");
	
	public String getAccountsPageTitle() {
		String title=eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TILE, TimeUtil.DEFAULT_TIME);
		System.out.println("Acc page title is "+title);
		return title;
	}
	public String getAccountsPageURL() {
		String url=eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);;
		System.out.println("Acc page url is "+url);
		return url;
	}
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
		
	}
	public List<String> getAccPageHeaders() {
		List<WebElement> headerList=eleUtil.waitForVisibilityOfElemenetsLocated(headers, TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> headersValList=new ArrayList<String>();
		for(WebElement  e:headerList) {
			String text=e.getText();
			headersValList.add(text);
					}
		return headersValList;
		
	}
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(search);
				
	}
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("searching: "+searchKey);
		//driver.findElement(search).sendKeys(searchKey);
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
			else {
				System.out.println("Search field is not present on the page");
				return null;
			}
		}
		
	}

	