package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.error.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

//This is used to initialize the browser based on the browser name passed
public class DriverFactory {
	WebDriver driver;
	OptionsManager optionsManager;
	protected Properties prop;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver=new  ThreadLocal<WebDriver>();
	
 public WebDriver initDriver(Properties prop) {
		// browser logic
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is " + browserName);
		highlight=prop.getProperty("highlight");
		
		OptionsManager optionsManager=new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();-->Regular driver class
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			tlDriver.set (new SafariDriver());
			break;
		default:
			System.out.println("Please pass right browser name..." + browserName);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
 //get the local thread copy of the driver
 public static WebDriver getDriver() {
	 return tlDriver.get();
 }

//This method is used to initialize properties from the config. properties file
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		// mvn clean install -Denv
		String envName = System.getProperty("env");
		System.out.println("Running test suite on env---->"  +envName);
		if(envName==null) {
			System.out.println("env name is null hence running it on QA environment");
			try {
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
		try {
			switch (envName.trim().toLowerCase()) {

			case "qa":
				//ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
				break;
			case "stage":
				ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
				break;
			case "dev":
				ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
				break;
			case "prod":
				ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
				break;
			default:
				throw new FrameworkException("WRONGENVPASSED");
			}
			}
		

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	
	public static String getScreenshot(String methodName) {
		
		File srcFile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")
				+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path);
		return path;
	}
}
