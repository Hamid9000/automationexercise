package com.aswesomeQA.pages;

import static com.aswesomeQA.driver.DriverManager.getDriver;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;

public class SignUpPage extends CommonToAllPage {

    //----------------------- LOCATORS -------------------------

    private By name =
            By.xpath("//input[@name='name']");

    private By emailAddress =
            By.xpath("//div[@class='signup-form']//input[@name='email']");

    private By signUp =
            By.xpath("//button[text()='Signup']");

    private By signUpHeader =
            By.xpath("//h2[normalize-space()='Enter Account Information']");

    private By emailAlreadyExistError =
            By.xpath("//p[contains(text(),'Email Address already exist!')]");

    // ---------------------- Navigation --------------------

    public void goToSignUpPage() {
        getDriver().get(
                PropertiesReader.readKeys("baseUrl")
                        + PropertiesReader.readKeys("signUpUrl")
        );
    }

    // ---------------------- Actions --------------------

    public void enterUsername(String user) {
        waitForVisible(name).clear();
        waitForVisible(name).sendKeys(user);
    }

    public void enterEmailAddress(String emailAddress) {
        waitForVisible(this.emailAddress).clear();
        waitForVisible(this.emailAddress).sendKeys(emailAddress);
    }

    public void clickSignUp() {
        waitForClickable(signUp).click();
    }

    // ---------------------- Getters / Verifications --------------------

    public String getSignupHeader() {
        return waitForVisible(signUpHeader).getText();
    }


    public String getEmailAlreadyExistError() {
        return waitForVisible(emailAlreadyExistError).getText();
    }

    public String getNameHtmlValidationMessage() {
        return waitForVisible(name)
                .getAttribute("validationMessage");
    }

    public String getEmailHtmlValidationMessage() {
        return waitForVisible(emailAddress)
                .getAttribute("validationMessage");
    }
}





