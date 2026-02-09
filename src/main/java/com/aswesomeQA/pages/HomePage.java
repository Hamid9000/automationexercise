package com.aswesomeQA.pages;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.driver.DriverManager;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends CommonToAllPage {

    // ------------------------------ LOCATORS -----------------------------------

    private By homeAutomationExerciseLogo =
            By.xpath("//img[@alt='Website for automation practice']");

    private By signupLoginBtn =
            By.xpath("//a[contains(.,'Signup / Login')]");

    private By deleteAccountBtn =
            By.xpath("//a[contains(@href,'delete_account')]");

    private By logoutBtn =
            By.xpath("//a[contains(@href,'logout')]");

    // ------------------------------ CONSTRUCTOR -----------------------------------

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------ NAVIGATION -----------------------------------

    public void goToHomePage() {
        DriverManager.getDriver()
                .get(PropertiesReader.readKeys("baseUrl"));
    }

    // ------------------------------ ACTION METHODS -----------------------------------

    // 👉 For SIGNUP flow
    public SignUpPage goToSignUpPage() {
        waitForClickable(signupLoginBtn).click();
        return new SignUpPage(getDriver());
    }

    // 👉 For LOGIN flow
    public LoginPage goToLoginPageFromHome() {
        waitForClickable(signupLoginBtn).click();
        return new LoginPage(getDriver());
    }

    public LoginPage clickDeleteAccountBtn() {
        waitForClickable(deleteAccountBtn).click();
        return new LoginPage(getDriver());
    }


    // ✅ CORRECT LOGOUT METHOD (USED IN YOUR TEST)
    public HomePage clickLogoutAndReturnHome() {
        waitForVisible(logoutBtn).click();
        return new HomePage(getDriver());
    }

    // ------------------------------ VERIFICATION METHODS -----------------------------------

    public boolean isHomePageDisplayed() {
        return waitForVisible(homeAutomationExerciseLogo).isDisplayed();
    }

    public boolean isSignupLoginButtonVisible() {
        return waitForVisible(signupLoginBtn).isDisplayed();
    }
    public boolean isLoggedInUserVisible() {
        return waitForVisible(logoutBtn).isDisplayed();
    }


}
