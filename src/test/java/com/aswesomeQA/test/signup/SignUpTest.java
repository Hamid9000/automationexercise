package com.aswesomeQA.test.signup;

import com.aswesomeQA.base.CommonToAllTest;
import com.aswesomeQA.driver.DriverManager;
import com.aswesomeQA.pages.HomePage;
import com.aswesomeQA.pages.SignUpPage;
import com.aswesomeQA.utils.PropertiesReader;
import com.aswesomeQA.utils.TestDataProvidersCSV;   // <-- FIXED IMPORT

import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpTest extends CommonToAllTest {

    private static final Logger logger =
            LoggerFactory.getLogger(SignUpTest.class);

    // =====================================================
    // Negative TEST CASE
    // =====================================================

    @Test(
            dataProvider = "signupNegativeTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,
            priority = 1
    )

    public void invalidSignUpTest(
            String tcId,
            String name,
            String email,
            String expectedResult
    ) {

        logger.info("========== STARTING NEGATIVE TEST: {} ==========", tcId);

        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        SignUpPage signUp = home.clickSignUpLogin();

        logger.info("Entering Name: {}", name);
        signUp.enterUsername(name);

        logger.info("Entering Email: {}", email);
        signUp.enterEmailAddress(email);

        logger.info("Clicking Signup button");
        signUp.clickSignUp();

        String actualError;

        if (name == null || name.trim().isEmpty()) {
            actualError = signUp.getValidationMessage(signUp.getNameField());
            logger.info("Reading NAME Validation message");
        } else {
            actualError = signUp.getValidationMessage(signUp.getEmailField());
            logger.info("Reading EMAIL field Validation message");
        }

        logger.info("Expected Error from Excel : {}", expectedResult);
        logger.info("Actual Error from UI      : {}", actualError);

        Assert.assertTrue(
                actualError.contains(expectedResult),
                "HTML5 validation failed for " + tcId
        );

        logger.info("========== END NEGATIVE TEST: {} ==========", tcId);
    }

    // =====================================================
    // POSITIVE TEST CASE
    // =====================================================

    @Test(
            dataProvider = "signupPositiveTestDataCSV",
            dataProviderClass = TestDataProvidersCSV.class,
            priority = 2
    )
    public void validSignUpTest(
            String tcId,
            String name,
            String email
    ) {

        logger.info("========== STARTING POSITIVE TEST: {} ==========", tcId);

        HomePage home = new HomePage(DriverManager.getDriver());
        home.goToHomePage();

        SignUpPage signUp = home.clickSignUpLogin();

        logger.info("Entering Valid Name: {}", name);
        signUp.enterUsername(name);

        logger.info("Entering Valid Email: {}", email);
        signUp.enterEmailAddress(email);

        logger.info("Clicking Signup button");
        signUp.clickSignUp();

        String expectedTitle =
                PropertiesReader.readKeys("expected.signup.title");

        String expectedHeader =
                PropertiesReader.readKeys("expected.signup.header");

        String actualTitle = signUp.getPageTitle();
        String actualHeader = signUp.getAccountInformationHeader();

        logger.info("Verifying Page Title");
        logger.info("Expected Title : {}", expectedTitle);
        logger.info("Actual Title   : {}", actualTitle);

        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Title mismatch for " + tcId
        );

        logger.info("Verifying Signup Header");
        logger.info("Expected Header : {}", expectedHeader);
        logger.info("Actual Header   : {}", actualHeader);

        Assert.assertEquals(
                actualHeader,
                expectedHeader,
                "Header mismatch for " + tcId
        );

        logger.info("Positive signup passed for {}", tcId);
        logger.info("========== END POSITIVE TEST: {} ==========", tcId);
    }
}
