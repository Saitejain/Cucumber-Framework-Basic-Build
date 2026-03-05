package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.ElementUtils;

public class LoginPage {
    private final WebDriver driver;
    private final ElementUtils elementUtils;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By signInButton = By.id("login-button");
    private final By loginErrorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }

    public void openLoginPage(String url) {
        driver.get(url);
    }

    public void enterUsername(String userNameValue) {
        elementUtils.doSendKeys(username, userNameValue);
    }

    public void enterPassword(String passwordValue) {
        elementUtils.doSendKeys(password, passwordValue);
    }

    public void clickLogin() {
        elementUtils.doClick(signInButton);
    }

    public boolean isErrorMessageDisplayed() {
        return elementUtils.isDisplayed(loginErrorMessage);
    }

    public String getErrorMessage() {
        return elementUtils.doGetText(loginErrorMessage);
    }
}
