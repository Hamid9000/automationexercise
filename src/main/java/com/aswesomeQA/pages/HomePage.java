package com.aswesomeQA.pages;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;

public class HomePage extends CommonToAllPage {

    // ------------------------------ LOCATORS -----------------------------------

    private final By homeAutomationExerciseLogo =
            By.xpath("//img[@alt='Website for automation practice']");

    private final By signupLoginBtn =
            By.xpath("//a[contains(.,'Signup / Login')]");

    private final By deleteAccountBtn =
            By.xpath("//a[contains(@href,'delete_account')]");

    private final By logoutBtn =
            By.xpath("//a[contains(@href,'logout')]");

    // ------------------------------ CONSTRUCTOR -----------------------------------

    // ✅ Correct for parallel (no WebDriver here)
    public HomePage() {
        super();   // picks DriverManagerTL.getDriver() automatically
    }

    // ------------------------------ NAVIGATION -----------------------------------

    public void goToHomePage() {
        getDriver().get(PropertiesReader.readKeys("baseUrl"));
    }

    // ------------------------------ ACTION METHODS -----------------------------------

    // 👉 For SIGNUP flow
    public SignUpPage goToSignUpPage() {
        waitForClickable(signupLoginBtn).click();
        return new SignUpPage();          // ✅ no driver passing
    }

    // 👉 For LOGIN flow
    public LoginPage goToLoginPageFromHome() {
        waitForClickable(signupLoginBtn).click();
        return new LoginPage();           // ✅ no driver passing
    }

    public LoginPage clickDeleteAccountBtn() {
        waitForClickable(deleteAccountBtn).click();
        return new LoginPage();           // ✅ no driver passing
    }

    // ✅ Logout keeps you on HomePage
    public HomePage clickLogoutAndReturnHome() {
        waitForVisible(logoutBtn).click();
        return new HomePage();            // ✅ no driver passing
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
