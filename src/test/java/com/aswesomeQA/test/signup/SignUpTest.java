package com.aswesomeQA.test.signup;

import com.aswesomeQA.base.CommonToAllTest;
import com.aswesomeQA.driver.DriverManager;
import com.aswesomeQA.pages.HomePage;
import com.aswesomeQA.pages.SignUpPage;
import com.aswesomeQA.utils.PropertiesReader;

import com.aswesomeQA.utils.TestDataProvidersCSV;
import com.aswesomeQA.utils.TestDataProvidersSQL;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SignUpTest extends CommonToAllTest {

    private static final Logger logger =
            LoggerFactory.getLogger(SignUpTest.class);

    // =============================================================
    //                 NEGATIVE SIGNUP TESTS
    // =============================================================

    @Test(
            dataProvider = "signupNegativeTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,   // ← fixed package here too
            priority = 1,
            description = "Verify error messages for invalid/empty signup inputs"
    )
    public void invalidSignUpTest(
            String tcId,
            String name,
            String email,
            String expectedErrorMessageKey
    ) {
        logger.info("===== Starting NEGATIVE test: {} =====", tcId);

        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        SignUpPage signUpPage = home.goToSignUpPage();

        logger.info("Name: '{}', Email: '{}'", name, email);

        signUpPage.enterUsername(name);
        signUpPage.enterEmailAddress(email);
        signUpPage.clickSignupBtn();

        String actualValidationMsg;

        if (name == null || name.trim().isEmpty()) {
            actualValidationMsg = signUpPage.getHtml5ValidationMessage(
                    signUpPage.getNameField()
            );
            logger.info("Reading NAME validation message");
        } else {
            actualValidationMsg = signUpPage.getHtml5ValidationMessage(
                    signUpPage.getEmailField()
            );
            logger.info("Reading EMAIL validation message");
        }

        String expectedError = PropertiesReader.readKeys(expectedErrorMessageKey);

        logger.info("Expected error : {}", expectedError);
        logger.info("Actual error   : {}", actualValidationMsg);

        Assert.assertTrue(
                actualValidationMsg.contains(expectedError),
                String.format(
                        "[%s] Validation mismatch! Expected contains: '%s' | Actual: '%s'",
                        tcId, expectedError, actualValidationMsg
                )
        );

        logger.info("Negative test passed → {}", tcId);
    }

    // =============================================================
    //                 POSITIVE SIGNUP TEST
    // =============================================================

    @Test(
            dataProvider = "signupPositiveTestDataSQL",
            dataProviderClass = TestDataProvidersSQL.class,   // ← fixed here
            priority = 2,
            description = "Verify successful signup with valid data"
    )
    public void validSignUpTest(
            String tcId,
            String name,
            String email
    ) {
        logger.info("===== Starting POSITIVE test: {} =====", tcId);

        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        SignUpPage signUpPage = home.goToSignUpPage();

        signUpPage.enterUsername(name);

        // Unique email for each run
        String uniqueEmail = email.replace("@", System.currentTimeMillis() + "@");

        logger.info("Using unique email: {}", uniqueEmail);
        signUpPage.enterEmailAddress(uniqueEmail);

        signUpPage.clickSignupBtn();

        String expectedTitle = PropertiesReader.readKeys("expected.signup.title");
        String expectedHeader = PropertiesReader.readKeys("expected.signup.header");

        String actualTitle = signUpPage.getPageTitle();
        String actualHeader = signUpPage.getAccountInformationHeader();

        Assert.assertEquals(actualTitle, expectedTitle,
                "Page title mismatch → " + tcId);

        Assert.assertEquals(actualHeader, expectedHeader,
                "Header mismatch → " + tcId);

        logger.info("Positive signup test passed → {}", tcId);
    }
}