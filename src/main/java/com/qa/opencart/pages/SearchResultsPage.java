package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private By searchResult=By.cssSelector("div.product-thumb");
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
public int getSerchResultsCount() {

	List<WebElement> resultsList=eleUtil.waitForVisibilityOfElemenetsLocated(searchResult, TimeUtil.DEFAULT_MEDIUM_TIME);
	int resultCount=resultsList.size();
	System.out.println("product search results count==="+resultCount);
return resultCount;
}
 public ProductInfoPage selectProduct(String productName) {
	 eleUtil.doClick(By.linkText(productName),TimeUtil.DEFAULT_TIME);
	 return new ProductInfoPage(driver);
 }

}
