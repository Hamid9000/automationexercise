package com.aswesomeQA.test.registration;

import com.aswesomeQA.base.CommonToAllTest;
import com.aswesomeQA.driver.DriverManager;
import com.aswesomeQA.pages.HomePage;
import com.aswesomeQA.pages.RegistrationPage;
import com.aswesomeQA.pages.SignUpPage;
import com.aswesomeQA.utils.PropertiesReader;
import com.aswesomeQA.utils.TestDataProvidersCSV;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationTest extends CommonToAllTest {

    private static final Logger logger =
            LoggerFactory.getLogger(RegistrationTest.class);

    // =====================================================
    // PART–1 : NEGATIVE REGISTRATION TEST
    // =====================================================
    @Test(
            dataProvider = "registrationNegativeTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,
            priority = 1
    )
    public void negativeRegistrationTest(
            String tcId,
            String signupName,
            String signupEmail,
            String title,
            String password,
            String d,
            String m,
            String y,
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

        logger.info("========== STARTING TEST: {} ==========", tcId);

        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        // ✅ ONLY THIS LINE CHANGED
        SignUpPage signUpPage = home.goToSignUpPage();

        signUpPage.enterUsername(signupName);
        signUpPage.enterEmailAddress(signupEmail);

        RegistrationPage reg =
                signUpPage.clickSignUpAndGoToRegistration();

        if (title.equalsIgnoreCase("Mr")) {
            reg.selectMr();
        } else {
            reg.selectMrs();
        }

        reg.enterPassword(password);
        reg.selectDOB(d, m, y);
        reg.checkNewsletter();
        reg.checkOffers();

        reg.enterFirstName(firstName);
        reg.enterLastName(lastName);
        reg.enterCompany(company);
        reg.enterAddress1(address1);

        logger.info("Skipping Address2 intentionally (optional field)");

        reg.selectCountry(country);
        reg.enterState(state);
        reg.enterCity(city);
        reg.enterZip(zip);
        reg.enterMobile(mobile);

        logger.info("Clicking Create Account button");
        reg.clickCreateAccount();

        String actualPageTitle = reg.getPageTitle();
        String expectedPageTitle =
                PropertiesReader.readKeys(
                        "expected.accountSucessFullCreated.page.title");

        Assert.assertEquals(
                actualPageTitle,
                expectedPageTitle,
                "Account page title mismatch"
        );

        String actualPageHeader = reg.getAccountCreatedHeader();
        String expectedPageHeader =
                PropertiesReader.readKeys(
                        "expected.accountSucessFullCreated.header");

        Assert.assertEquals(
                actualPageHeader,
                expectedPageHeader,
                "Account created header mismatch"
        );

        reg.clickAccountCreateContinueBtn();
        logger.info("========== END TEST: {} ==========", tcId);
    }

    // =====================================================
    // PART–2 : POSITIVE REGISTRATION + DELETE TEST
    // =====================================================

    @Test(
            dataProvider = "registrationPositiveTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,
            priority = 2
    )
    public void registrationAndDeleteAccountTest(
            String tcId,
            String signupName,
            String signupEmail,
            String title,
            String password,
            String d,
            String m,
            String y,
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

        logger.info("===== STARTING POSITIVE TEST + DELETE FLOW: {} =====", tcId);

        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        // ✅ ONLY THIS LINE CHANGED
        SignUpPage signUpPage = home.goToSignUpPage();

        signUpPage.enterUsername(signupName);
        signUpPage.enterEmailAddress(signupEmail);

        RegistrationPage reg =
                signUpPage.clickSignUpAndGoToRegistration();

        if (title.equalsIgnoreCase("Mr")) {
            reg.selectMr();
        } else {
            reg.selectMrs();
        }

        reg.enterPassword(password);
        reg.selectDOB(d, m, y);
        reg.checkNewsletter();
        reg.checkOffers();

        reg.enterFirstName(firstName);
        reg.enterLastName(lastName);
        reg.enterCompany(company);
        reg.enterAddress1(address1);

        logger.info("Skipping Address2 (optional field)");

        reg.selectCountry(country);
        reg.enterState(state);
        reg.enterCity(city);
        reg.enterZip(zip);
        reg.enterMobile(mobile);

        logger.info("Clicking Create Account");
        reg.clickCreateAccount();

        String actualHeader = reg.getAccountCreatedHeader();
        String expectedHeader =
                PropertiesReader.readKeys(
                        "expected.accountSucessFullCreated.header");

        Assert.assertEquals(
                actualHeader,
                expectedHeader,
                "Account created header mismatch"
        );

        reg.clickAccountCreateContinueBtn();

        logger.info("Clicking Delete Account");
        reg.clickDeleteAccount();

        String actualDeleteMsg = reg.getAccountDeleteMsg();
        String expectedDeleteMsg =
                PropertiesReader.readKeys("expected.accountDeleteMsg");

        Assert.assertTrue(
                actualDeleteMsg.contains(expectedDeleteMsg),
                "Account was NOT deleted!"
        );

        HomePage homeAfterDelete = reg.clickAccountDeleteContinueBtn();

        Assert.assertTrue(
                homeAfterDelete.isHomePageDisplayed(),
                "Home page did NOT open after delete!"
        );

        logger.info("===== END TEST: {} =====", tcId);
    }
}
