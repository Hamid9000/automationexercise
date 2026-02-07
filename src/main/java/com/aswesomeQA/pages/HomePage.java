package com.aswesomeQA.pages;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.driver.DriverManager;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends CommonToAllPage {

    // ---------- LOCATORS ----------
    private By homeAutomationExerciseLogo =
            By.xpath("//img[@alt='Website for automation practice']");

    private By signUpLoginBtn =
            By.xpath("//a[contains(.,'Signup / Login')]");

    // ---------- CONSTRUCTOR ----------
    public HomePage(WebDriver driver) {
        super(driver);   // keep this ONLY if CommonToAllPage expects driver
    }

    // ---------- ACTION METHODS ----------

    public void goToHomePage() {
        DriverManager.getDriver()
                .get(PropertiesReader.readKeys("baseUrl"));
    }

    public SignUpPage clickSignUpLogin() {
        waitForClickable(signUpLoginBtn).click();
        return new SignUpPage(DriverManager.getDriver());
    }

    public boolean isHomePageDisplayed() {
        return waitForVisible(homeAutomationExerciseLogo).isDisplayed();
    }
}
