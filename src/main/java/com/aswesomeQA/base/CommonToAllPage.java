package com.aswesomeQA.base;

import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class CommonToAllPage {
    protected WebDriver driver;

    public CommonToAllPage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return driver;
    }


    public void openUrl() {
        getDriver().get(PropertiesReader.readKeys("baseUrl"));
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(15));
    }

    public WebElement waitForVisible(By locator) {
        return getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return getWait()
                .until(ExpectedConditions.elementToBeClickable(locator));
    }


    public WebElement waitForPresent(By locator) {
        return getWait()
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForUrlContains(String text) {
        getWait().until(ExpectedConditions.urlContains(text));
    }

    public String getPageTitle() {
        getWait().until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
        return getDriver().getTitle();
    }
    public String getValidationMessage(By locator) {
        return waitForVisible(locator)
                .getAttribute("validationMessage");
    }
    public void waitForInvisibility(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }




}
