package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader=By.cssSelector("div#content h1");
	private By productImagesCount=By.cssSelector("div#content a.thumbnail");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By producPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String,String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getProductHeader() {
		String header=eleUtil.doGetText(productHeader);
		System.out.println("product header is "+productHeader);
		return header;
	}
	public int getProductImagesCount() {
		int imagesCount= eleUtil.waitForVisibilityOfElemenetsLocated(productImagesCount, TimeUtil.DEFAULT_MEDIUM_TIME).size();
	return imagesCount;
	}
	
	public Map<String, String> getProductInfoMap() {
		productMap=new HashMap<String,String>();//does not maintain any order
		//productMap=new LinkedHashMap<String,String>();//maintains insertion order
		//productMap=new TreeMap<String,String>();//sort and store
		productMap.put("productName", getProductHeader());
		productMap.put("productImagesCount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
		
	}
	public void getProductMetaData() {
		
	List<WebElement> metaList=eleUtil.getElements(productMetaData);
	for(WebElement e:metaList) {
		String metaData=e.getText();
		String meta[]=metaData.split(":");
		String metaKey=meta[0].trim();
		String metaValue=meta[1].trim();
		productMap.put(metaKey, metaValue);
		
		
	}
	}
	public void getProductPriceData() {
		List<WebElement> priceList=eleUtil.getElements(producPriceData);
		String productPrice=priceList.get(0).getText();
		String exTaxPrice=priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
		
		
	}
	
}
