package com.aswesomeQA.pages;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;

public class LoginPage extends CommonToAllPage {

    // ------------------------------ LOCATORS -----------------------------------

    private final By username =
            By.xpath("//input[@data-qa='login-email']");

    private final By password =
            By.xpath("//input[@data-qa='login-password']");

    private final By loginBtn =
            By.cssSelector("button[data-qa='login-button']");

    private final By invalidLoginError =
            By.xpath("//p[contains(text(),'Your email or password is incorrect')]");

    final By deleteContinueBtn =
            By.xpath("//a[@data-qa='continue-button']");

    private final By forgottenPassword =
            By.linkText("Forgotten Password");

    // ------------------------------ DRIVER -----------------------------------

    // ✅ FIXED CONSTRUCTOR (parallel-safe)
    public LoginPage() {
        super();   // picks DriverManagerTL.getDriver() automatically
    }

    // (kept for backward compatibility — safe, not used)
    public LoginPage(org.openqa.selenium.WebDriver driver) {
        super();
    }

    // ------------------------------ ACTION METHODS -----------------------------------

    public void enterUsername(String user) {
        waitForVisible(username).clear();
        waitForVisible(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        waitForVisible(password).clear();
        waitForVisible(password).sendKeys(pass);
    }

    public void clickLoginBtn() {
        waitForClickable(loginBtn).click();
    }

    public void clickForgottenPassword() {
        waitForClickable(forgottenPassword).click();
    }

    // ------------------------------ LOCATOR GETTERS -----------------------------------

    public By getUsernameField() {
        return username;
    }

    public By getPasswordField() {
        return password;
    }

    // ------------------------------ VERIFICATION -----------------------------------

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public String getInvalidLoginError() {
        return waitForVisible(invalidLoginError).getText();
    }

    // ------------------------------ REWIRED / FORWARD METHOD -------------------

    public HomePage loginAndGoToHome(String user, String pass) {

        enterUsername(user);
        enterPassword(pass);
        clickLoginBtn();

        // ✅ FIX: return without passing driver
        return new HomePage();
    }

    // ------------------------------ NAVIGATION -----------------------------------

    public void goToLoginPage() {
        getDriver().get(
                PropertiesReader.readKeys("baseUrl")
                        + PropertiesReader.readKeys("loginUrl")
        );
    }

    public void clickContinueAfterDelete() {
        waitForClickable(deleteContinueBtn).click();
    }

    public String getLoginErrorMessage() {
        return waitForVisible(
                By.xpath("//p[contains(text(),'incorrect')]")
        ).getText();
    }

    public void login(String email, String password) {
        enterUsername(email);
        enterPassword(password);
        clickLoginBtn();
    }
}
