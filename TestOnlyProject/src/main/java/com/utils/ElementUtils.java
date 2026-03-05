package com.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME_SECONDS));
    }

    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void doSendKeys(By locator, String value) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void doClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public String doGetText(By locator) {
        return waitForElementVisible(locator).getText().trim();
    }

    public boolean isDisplayed(By locator) {
        return waitForElementVisible(locator).isDisplayed();
    }
}
