package com.aswesomeQA.pages;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class RegistrationPage extends CommonToAllPage {

    //------------------------------ LOCATORS -----------------------------------

    private By mr = By.id("id_gender1");
    private By mrs = By.id("id_gender2");
    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By day = By.id("days");
    private By month = By.id("months");
    private By year = By.id("years");
    private By newsletter = By.id("newsletter");
    private By offer = By.id("optin");
    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By address2 = By.id("address2");
    private By country = By.id("country");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobileNumber = By.id("mobile_number");
    private By createAccountBtn = By.xpath("//button[@data-qa='create-account']");
    private By subscribeEmail = By.id("susbscribe_email");
    private By subscribeBtn = By.id("subscribe");
    private By accountCreatedHeader =
            By.xpath("//h2[@data-qa='account-created']");

    private By accountCreatedcontinueBtn =
            By.xpath("//a[@data-qa='continue-button']");
    private By logoutBtn = By.xpath("//a[contains(normalize-space(),'Logout')]");
    private By deleteAccountBtn =
            By.xpath("//a[contains(@href,'delete_account')]");

    private By deleteAccountMsg = By.xpath("//h2[@data-qa='account-deleted']");
    private By accountDelteContinueBtn = By.xpath("//a[@data-qa='continue-button']");



    public RegistrationPage(WebDriver driver) {   // 🔥 MUST HAVE
        super(driver);
    }


    // ---------------------- NAVIGATION --------------------------------------

    public void goToRegistrationPage() {
        getDriver().get(
                PropertiesReader.readKeys("baseUrl")
                        + PropertiesReader.readKeys("regUrl")
        );
    }

    // ------------------------ ACTION METHODS --------------------------------

    public void selectMr() {
        waitForVisible(mr).click();
    }

    public void selectMrs() {
        waitForVisible(mrs).click();
    }

    public void enterName(String userName) {
        waitForVisible(nameField).clear();
        waitForVisible(nameField).sendKeys(userName);
    }

    public void enterEmail(String userEmail) {

        WebElement emailBox = waitForVisible(emailField);

        if (emailBox.isEnabled()) {   // <-- IMPORTANT CHECK
            emailBox.clear();
            emailBox.sendKeys(userEmail);
        }
        // else -> do nothing (because site locks email)
    }

    public void enterPassword(String pwd) {
        waitForVisible(passwordField).sendKeys(pwd);
    }

    public void selectDOB(String d, String m, String y) {

        String cleanDay = normalizeNumber(d);
        String cleanMonth = normalizeNumber(m);
        String cleanYear = normalizeNumber(y);

        new Select(waitForVisible(day)).selectByVisibleText(cleanDay);
        new Select(waitForVisible(month)).selectByVisibleText(cleanMonth);
        new Select(waitForVisible(year)).selectByVisibleText(cleanYear);
    }

    // --------- FILTER METHOD (MOST IMPORTANT) ----------
    private String normalizeNumber(String value) {

        if (value == null) return "";

        // Remove .0, spaces, and trailing decimals
        value = value.trim();

        if (value.contains(".")) {
            value = value.substring(0, value.indexOf("."));
        }

        return value;
    }

    public void checkNewsletter() {
        if (!waitForVisible(newsletter).isSelected()) {
            waitForVisible(newsletter).click();
        }
    }

    public void checkOffers() {
        if (!waitForVisible(offer).isSelected()) {
            waitForVisible(offer).click();
        }
    }

    public void enterFirstName(String fname) {
        waitForVisible(firstName).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        waitForVisible(lastName).sendKeys(lname);
    }

    public void enterCompany(String comp) {
        waitForVisible(company).sendKeys(comp);
    }

    public void enterAddress1(String addr1) {
        waitForVisible(address1).sendKeys(addr1);
    }

    public void enterAddress2(String addr2) {
        waitForVisible(address2).sendKeys(addr2);
    }

    public void selectCountry(String countryName) {
        new Select(waitForVisible(country)).selectByVisibleText(countryName);
    }

    public void enterState(String st) {
        waitForVisible(state).sendKeys(st);
    }

    public void enterCity(String ct) {
        waitForVisible(city).sendKeys(ct);
    }

    public void enterZip(String zip) {
        waitForVisible(zipcode).sendKeys(zip);
    }

    public void enterMobile(String mobile) {
        waitForVisible(mobileNumber).sendKeys(mobile);
    }

    public void clickCreateAccount() {
        waitForVisible(createAccountBtn).click();
    }

    public void clickAccountCreateContinueBtn() {
        waitForVisible(accountCreatedcontinueBtn).click();
    }

    public HomePage clickAccountDeleteContinueBtn() {
        waitForClickable(accountDelteContinueBtn).click();
        return new HomePage(getDriver());   // VERY IMPORTANT
    }
    public void clickLogoutBtn(){
        waitForVisible(logoutBtn).click();
    }

    public void clickDeleteAccount() {
        waitForClickable(deleteAccountBtn).click();
    }





    // -------- OPTIONAL: SUBSCRIPTION FOOTER --------

    public void enterSubscribeEmail(String email) {
        waitForVisible(subscribeEmail).sendKeys(email);
    }

    public void clickSubscribe() {
        waitForVisible(subscribeBtn).click();
    }
    // -------- LOCATOR GETTERS FOR VALIDATION (ADD THIS) --------

    public By getAddress1Field() {
        return address1;
    }

    public By getMobileField() {
        return mobileNumber;
    }

    public By getNameField() {
        return nameField;
    }

    public By getEmailField() {
        return emailField;
    }


    // ----------- Verification -----------
    public String getPageTitle() {
        return getDriver().getTitle();
    }


    public String getAccountCreatedHeader() {
        return waitForVisible(accountCreatedHeader).getText();
    }
    public String getAccountDeleteMsg(){
        return waitForVisible(deleteAccountMsg).getText();
    }




}
