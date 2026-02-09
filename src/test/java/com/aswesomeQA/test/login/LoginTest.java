package com.aswesomeQA.tests;

import com.aswesomeQA.base.CommonToAllTest;
import com.aswesomeQA.pages.HomePage;
import com.aswesomeQA.pages.LoginPage;
import com.aswesomeQA.pages.RegistrationPage;
import com.aswesomeQA.pages.SignUpPage;
import com.aswesomeQA.utils.TestDataProvidersCSV;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.aswesomeQA.driver.DriverManager.getDriver;

public class LoginTest extends CommonToAllTest {

    private static final Logger logger =
            LoggerFactory.getLogger(LoginTest.class);

    // ================= NEGATIVE LOGIN TEST =================

    @Test(
            dataProvider = "loginNegativeTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,
            priority = 1
    )
    public void loginNegativeTest(
            String tcId,
            String email,
            String password,
            String expectedError
    ) {

        logger.info("========== STARTING NEGATIVE LOGIN TEST : {} ==========", tcId);

        logger.info("Step 1 → Opening Home Page");
        HomePage homePage = new HomePage(getDriver());
        homePage.goToHomePage();

        logger.info("Step 2 → Navigating to Login Page from Home");
        LoginPage loginPage = homePage.goToLoginPageFromHome();

        logger.info("Step 3 → Entering Email: {}", email);
        loginPage.enterUsername(email);

        logger.info("Step 4 → Entering Password");
        loginPage.enterPassword(password);

        logger.info("Step 5 → Clicking Login button");
        loginPage.clickLoginBtn();

        String actualError;

        // CASE 1: Email blank
        if (email == null || email.trim().isEmpty()) {

            logger.info("HTML5 error on EMAIL (blank)");

            actualError =
                    loginPage.getHtml5ValidationMessage(
                            loginPage.getUsernameField()
                    );
        }

        // ---- CASE 2: EMAIL INVALID FORMAT (REGEX) ----
        else if (!loginPage.isValidEmailFormat(email)) {

            logger.info("Reading HTML5 format error on EMAIL (regex)");

            actualError =
                    loginPage.getHtml5ValidationMessage(
                            loginPage.getUsernameField()
                    );

            logger.info("HTML5 Actual Error: {}", actualError);
        }

        // CASE 3: Password blank
        else if (password == null || password.trim().isEmpty()) {

            logger.info("HTML5 error on PASSWORD (blank)");

            actualError =
                    loginPage.getHtml5ValidationMessage(
                            loginPage.getPasswordField()
                    );
        }

        // CASE 4: Wrong credentials → RED message
        else {

            logger.info("Reading RED invalid login error from UI");

            actualError = loginPage.getInvalidLoginError();
        }

        logger.info("Actual Error : {}", actualError);
        logger.info("Expected Error : {}", expectedError);

        Assert.assertTrue(
                actualError.contains(expectedError),
                "Mismatch for " + tcId +
                        " | Actual: " + actualError +
                        " | Expected: " + expectedError
        );

        logger.info("========== END NEGATIVE LOGIN TEST : {} ==========", tcId);
    }

    // ================= POSITIVE LOGIN TEST =================
    @Test(
            dataProvider = "loginPositiveTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,
            priority = 2
    )
    public void loginPositiveTest(
            String tcid,
            String name,
            String email,
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

        logger.info("===== START POSITIVE LOGIN TEST : {} =====", tcid);

        // 1️⃣ Go to Home
        HomePage homePage = new HomePage(getDriver());
        homePage.goToHomePage();

        // 2️⃣ SIGNUP FLOW
        SignUpPage signUpPage = homePage.goToSignUpPage();

        RegistrationPage reg =
                signUpPage.completeSignUp(name, email);

        HomePage loggedInHome =
                reg.completeRegistrationAndReturnHome(
                        title, password, d, m, y,
                        firstName, lastName, company,
                        address1, country, state, city, zip, mobile
                );

        logger.info("Account created for {}", tcid);

        Assert.assertTrue(
                loggedInHome.isHomePageDisplayed(),
                "Home page not displayed after signup"
        );

        // ────────────────────────────────────────────────
        // 3️⃣ FIRST LOGOUT
        // ────────────────────────────────────────────────
        logger.info("Performing first logout");

        HomePage homeAfterFirstLogout =
                loggedInHome.clickLogoutAndReturnHome();

        Assert.assertTrue(
                homeAfterFirstLogout.isSignupLoginButtonVisible(),
                "First logout failed for " + tcid
        );

        logger.info("First logout successful for {}", tcid);

        // ────────────────────────────────────────────────
        // 4️⃣ LOGIN AGAIN
        // ────────────────────────────────────────────────
        logger.info("Logging in again with same credentials");

        LoginPage loginPage =
                homeAfterFirstLogout.goToLoginPageFromHome();

        HomePage loggedInHomeAgain =
                loginPage.loginAndGoToHome(email, password);

        Assert.assertTrue(
                loggedInHomeAgain.isHomePageDisplayed()
                        && loggedInHomeAgain.isLoggedInUserVisible(),
                "Re-login failed after first logout for " + tcid
        );

        logger.info("Re-login successful for {}", tcid);

        // ────────────────────────────────────────────────
        // 5️⃣ SECOND LOGOUT
        // ────────────────────────────────────────────────
        logger.info("Performing second logout");

        HomePage homeAfterSecondLogout =
                loggedInHomeAgain.clickLogoutAndReturnHome();

        Assert.assertTrue(
                homeAfterSecondLogout.isSignupLoginButtonVisible(),
                "Second logout failed for " + tcid
        );

        logger.info("Second logout successful for {}", tcid);
        logger.info("===== END POSITIVE LOGIN TEST : {} =====", tcid);
    }

    // ================= Negative  TEST : LOGIN AFTER DELTE ACCOUNT =================

    @Test(
            dataProvider = "loginPositiveTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,
            priority = 3
    )
    public void deleteAccountTest(
            String tcid,
            String name,
            String email,
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

        logger.info("===== START DELETE ACCOUNT TEST : {} =====", tcid);

        // 1️⃣ Go to Home
        HomePage homePage = new HomePage(getDriver());
        homePage.goToHomePage();

        // 2️⃣ SIGNUP FLOW
        SignUpPage signUpPage = homePage.goToSignUpPage();

        RegistrationPage reg =
                signUpPage.completeSignUp(name, email);

        HomePage loggedInHome =
                reg.completeRegistrationAndReturnHome(
                        title, password, d, m, y,
                        firstName, lastName, company,
                        address1, country, state, city, zip, mobile
                );

        Assert.assertTrue(
                loggedInHome.isHomePageDisplayed()
                        && loggedInHome.isLoggedInUserVisible(),
                "Signup failed - Home page not loaded for " + tcid
        );

        logger.info("Account created successfully for {}", tcid);

        // ────────────────────────────────────────────────
        // 3️⃣ DELETE ACCOUNT
        // ────────────────────────────────────────────────
        logger.info("Clicking Delete Account");

        LoginPage afterDelete =
                loggedInHome.clickDeleteAccountBtn();

        // click continue after deletion (you may already have this button)
        afterDelete.clickContinueAfterDelete();

        HomePage homeAfterDelete = new HomePage(getDriver());

        Assert.assertTrue(
                homeAfterDelete.isSignupLoginButtonVisible(),
                "Home page not displayed after deleting account for " + tcid
        );

        logger.info("Account deleted successfully for {}", tcid);

        // ────────────────────────────────────────────────
        // 4️⃣ TRY LOGIN WITH SAME CREDENTIALS
        // ────────────────────────────────────────────────
        logger.info("Trying to login with deleted account");

        LoginPage loginPage =
                homeAfterDelete.goToLoginPageFromHome();

        loginPage.login(email, password);

        String actualError =
                loginPage.getLoginErrorMessage();

        String expectedError =
                "Your email or password is incorrect!";

        Assert.assertEquals(
                actualError,
                expectedError,
                "Deleted account should NOT allow login for " + tcid
        );

        logger.info("Verified: Deleted account cannot login for {}", tcid);
        logger.info("===== END DELETE ACCOUNT TEST : {} =====", tcid);
    }


}