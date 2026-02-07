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
    // PART–1 : NEGATIVE REGISTRATION TEST (CORRECTED)
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

        // -------- CORRECT NAVIGATION (NEW) --------
        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        SignUpPage signUpPage = home.clickSignUpLogin();
        // -----------------------------------------

        signUpPage.enterUsername(signupName);
        signUpPage.enterEmailAddress(signupEmail);

        // -------- REGISTRATION PAGE --------
        RegistrationPage reg =
                signUpPage.clickSignUpAndGoToRegistration();

        // Title
        if (title.equalsIgnoreCase("Mr")) {
            reg.selectMr();
        } else {
            reg.selectMrs();
        }

        // Basic details
        reg.enterPassword(password);
        reg.selectDOB(d, m, y);
        reg.checkNewsletter();
        reg.checkOffers();

        // Address details
        reg.enterFirstName(firstName);
        reg.enterLastName(lastName);
        reg.enterCompany(company);

        // Address1 required
        reg.enterAddress1(address1);

        // Address2 intentionally skipped
        logger.info("Skipping Address2 intentionally (optional field)");

        reg.selectCountry(country);
        reg.enterState(state);
        reg.enterCity(city);
        reg.enterZip(zip);
        reg.enterMobile(mobile);

        // -------- SUBMIT FORM --------
        logger.info("Clicking Create Account button");
        reg.clickCreateAccount();

        // -------- VERIFICATION --------

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
// PART–2 : POSITIVE REGISTRATION TEST (CORRECTED)
// =====================================================

    //    @Test(
//            dataProvider = "registrationPositiveData",
//            dataProviderClass = TestDataProviders.class,
//            priority = 2
//    )
//    public void positiveRegistrationTest(
//            String tcId,
//            String title,
//            String name,
//            String email,
//            String password,
//            String firstName,
//            String lastName,
//            String address1,
//            String country,
//            String state,
//            String city,
//            String zip,
//            String mobile,
//            String expectedResult
//    ) {
//
//        // Skip any negative rows accidentally present
//        if (!expectedResult.contains("ACCOUNT")) {
//            Reporter.log("Skipping non-positive row: " + tcId, true);
//            return;
//        }
//
//        Reporter.log("===== START POSITIVE TEST : " + tcId + " =====", true);
//
//        RegistrationPage reg = new RegistrationPage();
//        reg.goToRegistrationPage();
//
//        // ---- Fill the form correctly ----
//        if (title.equalsIgnoreCase("Mr")) {
//            reg.selectMr();
//        } else {
//            reg.selectMrs();
//        }
//
//        reg.enterName(name);
//        reg.enterEmail(email);
//        reg.enterPassword(password);
//
//        // DOB from test data is optional; keeping sample format
//        reg.selectDOB("10", "May", "1998");
//
//        reg.checkNewsletter();
//
//        reg.enterFirstName(firstName);
//        reg.enterLastName(lastName);
//        reg.enterAddress1(address1);
//        reg.selectCountry(country);
//        reg.enterState(state);
//        reg.enterCity(city);
//        reg.enterZip(zip);
//        reg.enterMobile(mobile);
//
//        Reporter.log("Clicking Create Account", true);
//        reg.clickCreateAccount();
//
//        // ---------- VERIFICATIONS FROM properties FILE ----------
//
//        String actualTitle = reg.getCurrentTitle();
//        String actualHeader = reg.getAccountCreatedHeader();
//
//        String expectedTitle =
//                PropertiesReader.readData("expected.account.page.title");
//
//        String expectedHeader =
//                PropertiesReader.readData("expected.account.header");
//
//        Reporter.log("Actual Title: " + actualTitle, true);
//        Reporter.log("Expected Title: " + expectedTitle, true);
//
//        Reporter.log("Actual Header: " + actualHeader, true);
//        Reporter.log("Expected Header: " + expectedHeader, true);
//
//        Assert.assertEquals(
//                actualTitle,
//                expectedTitle,
//                "Page Title mismatch after registration"
//        );
//
//        Assert.assertEquals(
//                actualHeader,
//                expectedHeader,
//                "Account created message mismatch"
//        );
//
//        Reporter.log("===== END POSITIVE TEST : " + tcId + " =====", true);
//    }
//
//}
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

        // ==============================
        //  STEP 1: OPEN APPLICATION
        // ==============================

        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        // ==============================
        //  STEP 2: NAVIGATE TO SIGNUP PAGE
        // ==============================

        SignUpPage signUpPage = home.clickSignUpLogin();

        signUpPage.enterUsername(signupName);
        signUpPage.enterEmailAddress(signupEmail);

        // ==============================
        //  STEP 3: FILL REGISTRATION FORM
        // ==============================

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

        // ==============================
        //  STEP 4: CREATE ACCOUNT
        // ==============================

        logger.info("Clicking Create Account");
        reg.clickCreateAccount();

        // ==============================
        //  STEP 5: VERIFY ACCOUNT CREATED
        // ==============================

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

        // ==============================
        //  STEP 6: DELETE ACCOUNT
        // ==============================

        logger.info("Clicking Delete Account");
        reg.clickDeleteAccount();

        String actualDeleteAccountDeleteMsg = reg.getAccountDeleteMsg();
        String expectedDeleteAccountDeletemsg =
                PropertiesReader.readKeys("expected.accountDeleteMsg");

        Assert.assertTrue(
                actualDeleteAccountDeleteMsg.contains(expectedDeleteAccountDeletemsg),
                "Account was NOT deleted!"
        );

        // ==============================
        //  STEP 7: NAVIGATE BACK TO HOME + VERIFY
        // ==============================

        HomePage homeAfterDelete = reg.clickAccountDeleteContinueBtn();

        Assert.assertTrue(
                homeAfterDelete.isHomePageDisplayed(),
                "Home page did NOT open after delete!"
        );

        logger.info("===== END TEST: {} =====", tcId);
    }
}