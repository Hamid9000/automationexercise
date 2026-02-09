package com.aswesomeQA.pages;


import com.aswesomeQA.base.CommonToAllPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends CommonToAllPage {

    //----------------------- LOCATORS -------------------------


    private By name =
            By.xpath("//input[@name='name']");

    private By emailAddress =
            By.xpath("//div[@class='signup-form']//input[@name='email']");
    private By signupBtn =
            By.xpath("//button[@data-qa='signup-button']");


    private By accountInformationHeader =
            By.xpath("//h2[normalize-space()='Enter Account Information']");

    private By emailAlreadyExistError =
            By.xpath("//p[contains(text(),'Email Address already exist!')]");

    private By newUserSignUpHeader =
            By.xpath("//h2[normalize-space()='New User Signup!']");

    // ---------------------- Driver --------------------

    public SignUpPage(WebDriver driver) {
        super(driver);
        waitForVisible(newUserSignUpHeader);
    }

    // ---------------------- Actions --------------------

    public void enterUsername(String user) {
        waitForVisible(name).clear();
        waitForVisible(name).sendKeys(user);
    }

    public void enterEmailAddress(String email) {
        waitForVisible(emailAddress).clear();
        waitForVisible(emailAddress).sendKeys(email);
    }
    public void clickSignupBtn() {
        waitForClickable(signupBtn).click();
    }


    // ---------------------- Getters / Verifications --------------------

    public  String getAccountInformationHeader(){
        return waitForVisible(accountInformationHeader).getText();
    }

    public String getEmailAlreadyExistError() {
        return waitForVisible(emailAlreadyExistError).getText();
    }


    public By getNameField() {
        return name;
    }

    public By getEmailField() {
        return emailAddress;
    }

    // ---------------------- Navigation --------------------


    public RegistrationPage clickSignUpAndGoToRegistration() {

        waitForClickable(signupBtn).click();

        waitForVisible(accountInformationHeader);

        return new RegistrationPage(getDriver());
    }
    // ====================== REUSABLE COMPLETE SIGNUP FLOW ======================

    public RegistrationPage completeSignUp(
            String name,
            String email
    ) {

        // 1️⃣ Enter name
        enterUsername(name);

        // 2️⃣ Enter email
        enterEmailAddress(email);

        // 3️⃣ Click Signup button and go to Registration page
        return clickSignUpAndGoToRegistration();
    }



}
