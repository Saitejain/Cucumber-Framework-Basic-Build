package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public WebDriver init_driver(String browser) {
		System.out.println("browser value is : "+browser);
		if(browser.equals("chrome")) {
			WebDriver driver1 = new ChromeDriver();
			tlDriver.set(driver1);
			}
		else if(browser.equals("firefox")) {
			WebDriver driver1 = new FirefoxDriver();
			tlDriver.set(driver1);
			}
		else {
			System.out.println("Print the current browser value "+browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}
