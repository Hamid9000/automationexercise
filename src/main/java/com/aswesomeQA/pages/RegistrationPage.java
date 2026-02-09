package com.aswesomeQA.pages;

import com.aswesomeQA.base.CommonToAllPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends CommonToAllPage {


// ------------------------------ LOCATORS -----------------------------------

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
    private By accountDelteContinueBtn =
            By.xpath("//a[@data-qa='continue-button']");


// ------------------------------ DRIVER -----------------------------------

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }


// ------------------------------ ACTION METHODS -----------------------------------

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

        if (emailBox.isEnabled()) {
            emailBox.clear();
            emailBox.sendKeys(userEmail);
        }
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
        return new HomePage(getDriver());
    }

    public void clickLogoutBtn(){
        waitForVisible(logoutBtn).click();
    }

    public void clickDeleteAccount() {
        waitForClickable(deleteAccountBtn).click();
    }

    public void enterSubscribeEmail(String email) {
        waitForVisible(subscribeEmail).sendKeys(email);
    }

    public void clickSubscribe() {
        waitForVisible(subscribeBtn).click();
    }


// ------------------------------ VERIFICATION -----------------------------------

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public String getAccountCreatedHeader() {
        return waitForVisible(accountCreatedHeader).getText();
    }

    public String getAccountDeleteMsg(){
        return waitForVisible(deleteAccountMsg).getText();
    }


// ------------------------------ NAVIGATION -----------------------------------

    public void goToRegistrationPage() {
        getDriver().get(
                PropertiesReader.readKeys("baseUrl")
                        + PropertiesReader.readKeys("regUrl")
        );
    }
// ------------------------------ Helper Method -----------------------------------

    public void completeRegistration(
            String title,
            String password,
            String d, String m, String y,
            String firstName,
            String lastName,
            String company,
            String address1,
            String country,
            String state,
            String city,
            String zip,
            String mobile
    ) {

        // --------- STEP 1: TITLE (CLICK ACTION INCLUDED) ---------
        if (title.equalsIgnoreCase("Mr")) {
            waitForVisible(mr).click();      // 👈 CLICK explicitly
        } else {
            waitForVisible(mrs).click();     // 👈 CLICK explicitly
        }

        // --------- STEP 2: REST OF FORM ---------
        enterPassword(password);
        selectDOB(d, m, y);
        checkNewsletter();
        checkOffers();

        enterFirstName(firstName);
        enterLastName(lastName);
        enterCompany(company);
        enterAddress1(address1);

        selectCountry(country);
        enterState(state);
        enterCity(city);
        enterZip(zip);
        enterMobile(mobile);

        // --------- STEP 3: SUBMIT ---------
        clickCreateAccount();
    }
    public HomePage completeRegistrationAndReturnHome(
            String title,
            String password,
            String d, String m, String y,
            String firstName,
            String lastName,
            String company,
            String address1,
            String country,
            String state,
            String city,
            String zip,
            String mobile
    ) {

        // 👉 CALL YOUR EXISTING METHOD (UNCHANGED)
        completeRegistration(
                title, password, d, m, y,
                firstName, lastName, company,
                address1, country, state, city, zip, mobile
        );

        // 👉 CLICK CONTINUE AFTER ACCOUNT CREATED
        clickAccountCreateContinueBtn();

        // 👉 WAIT FOR HOME PAGE (SAFE STEP)
        waitForVisible(By.xpath("//img[@alt='Website for automation practice']"));

        // 👉 RETURN HOME PAGE
        return new HomePage(getDriver());
    }


}
