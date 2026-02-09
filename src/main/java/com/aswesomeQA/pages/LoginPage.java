package com.aswesomeQA.pages;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonToAllPage {


// ------------------------------ LOCATORS -----------------------------------

    private By username =
            By.xpath("//input[@data-qa='login-email']");

    private By password =
            By.xpath("//input[@data-qa='login-password']"); // ✅ corrected

    private By loginBtn =
            By.cssSelector("button[data-qa='login-button']"); // ✅ corrected
    private By invalidLoginError =
            By.xpath("//p[contains(text(),'Your email or password is incorrect')]");
    By deleteContinueBtn =
            By.xpath("//a[@data-qa='continue-button']");   // ✅ BEST LOCATOR




    private By forgottenPassword =
            By.linkText("Forgotten Password");


// ------------------------------ DRIVER -----------------------------------

    public LoginPage(WebDriver driver) {
        super(driver);   // 🔥 MUST HAVE
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
// (for common validation method)

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



// ------------------------------ REWIRED / FORWARD METHOD (NEW) -------------------
// ✅ THIS IS THE METHOD YOU ASKED FOR

    public HomePage loginAndGoToHome(String user, String pass) {

        enterUsername(user);
        enterPassword(pass);
        clickLoginBtn();

        // optional wait for home page if you have a locator
        // waitForVisible(homeHeader);

        return new HomePage(getDriver());
    }


// ------------------------------ NAVIGATION (LAST) -----------------------------------

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
