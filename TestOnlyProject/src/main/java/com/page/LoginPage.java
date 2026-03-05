package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
private WebDriver driver;
private By Username = By.xpath("//input[@type=\"text\"]");
private By Password = By.xpath("//input[@type=\"password\"]");
private By SigninButton = By.xpath("//button[@name=\"login\"]");

public LoginPage(WebDriver driver) {
	this.driver=driver;
}
}
