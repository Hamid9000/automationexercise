package com.aswesomeQA.pages;


import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;

import static com.aswesomeQA.driver.DriverManager.getDriver;

public class LoginPage extends CommonToAllPage {

    // ---------- LOCATORS ----------
    private By username = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.cssSelector("button[type='submit']");
    private By forgottenPassword = By.linkText("Forgotten Password");




    // ---------- NAVIGATION ----------
    public void goToLoginPage() {
        getDriver().get(PropertiesReader.readKeys("baseUrl") + PropertiesReader.readKeys("loginUrl"));
    }


    public void enterUsername(String user) {
        waitForVisible(username).clear();
        waitForVisible(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        waitForVisible(password).clear();
        waitForVisible(password).sendKeys(pass);
    }

    public void clickLogin() {
        waitForClickable(loginBtn).click();
    }

    public void clickForgottenPassword() {
        waitForClickable(forgottenPassword).click();
    }

}
