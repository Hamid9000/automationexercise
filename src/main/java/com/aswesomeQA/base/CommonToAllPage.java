package com.aswesomeQA.base;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.aswesomeQA.driver.DriverManager.getDriver;

public class CommonToAllPage {

    public void openUrl(){
        getDriver().get(PropertiesReader.readKeys("baseUrl"));
    }

    private WebDriverWait getWait(){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public WebElement waitForVisible(By locator){
        return getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator){
        return getWait()
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForPresent(By locator){
        return getWait()
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForUrlContains(String text){
        getWait().until(ExpectedConditions.urlContains(text));
    }

    public void waitForTitle(String title){
        getWait().until(ExpectedConditions.titleContains(title));
    }
}
