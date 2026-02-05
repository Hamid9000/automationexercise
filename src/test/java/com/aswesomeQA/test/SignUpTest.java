package com.aswesomeQA.test;

import com.aswesomeQA.base.CommonToAllTest;
import com.aswesomeQA.pages.SignUpPage;
import com.aswesomeQA.utils.UtilsExcel;
import com.aswesomeQA.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpTest extends CommonToAllTest {

    private static final Logger logger =
            LoggerFactory.getLogger(SignUpTest.class);

    // =====================================================
    // FLOW 1 — HTML5 / FRONTEND VALIDATIONS (Negative)
    // =====================================================

    @Test(
            dataProvider = "signupInvalidData",
            dataProviderClass = UtilsExcel.class,
            priority = 1
    )
    public void invalidSignUpTest(
            String tcId,
            String name,
            String email,
            String expectedResult
    ) {

        SignUpPage signUp = new SignUpPage();

        logger.info("========== STARTING NEGATIVE TEST: {} ==========", tcId);
        logger.info("Opening Signup page");
        signUp.goToSignUpPage();

        logger.info("Entering Name: {}", name);
        signUp.enterUsername(name);

        logger.info("Entering Email: {}", email);
        signUp.enterEmailAddress(email);

        logger.info("Clicking Signup button");
        signUp.clickSignUp();


        String actualError;

        // ---- CASE 1: Blank Name ----
        if (name == null || name.trim().isEmpty()) {
            actualError = signUp.getNameHtmlValidationMessage();
            logger.info("Reading NAME field HTML5 message");
        }

        // ---- CASE 2: Blank Email ----
        else if (email == null || email.trim().isEmpty()) {
            actualError = signUp.getEmailHtmlValidationMessage();
            logger.info("Reading EMAIL field HTML5 message (BLANK EMAIL)");
        }

        // ---- CASE 3: Invalid Email ----
        else {
            actualError = signUp.getEmailHtmlValidationMessage();
            logger.info("Reading EMAIL field HTML5 message (INVALID EMAIL)");
        }

        logger.info("Expected Error from Excel : {}", expectedResult);
        logger.info("Actual Error from UI      : {}", actualError);

        Assert.assertTrue(
                actualError.contains(expectedResult),
                "HTML5 validation failed for " + tcId
        );

        logger.info("========== END NEGATIVE TEST: {} ==========", tcId);
    }

//    // =====================================================
//    // FLOW 2 — EXISTING EMAIL (BACKEND VALIDATION)
//    // =====================================================
//    @Test(priority = 2)
//    public void existingEmailTest() {
//
//        SignUpPage signUp = new SignUpPage();
//
//        String name = "Rahul Sharma";
//        String email = "rahul_auto_" + System.currentTimeMillis() + "@test.com";
//
//        // ==========================
//        // STEP 1 — FIRST SIGNUP (SUCCESS)
//        // ==========================
//
//        logger.info("STEP 1 — Creating account first");
//
//        signUp.goToSignUpPage();
//        signUp.enterUsername(name);
//        signUp.enterEmailAddress(email);
//        signUp.clickSignUp();
//
//        // Verify we reached next page
//        Assert.assertTrue(signUp.getSignupHeader().contains("ENTER ACCOUNT INFORMATION"));
//
//        // ==========================
//        // STEP 2 — SAME EMAIL AGAIN
//        // ==========================
//
//        logger.info("STEP 2 — Trying same email again");
//
//        signUp.goToSignUpPage();
//        signUp.enterUsername(name);
//        signUp.enterEmailAddress(email);
//        signUp.clickSignUp();
//
//        String actualError = signUp.getEmailAlreadyExistError();
//
//        String expectedError =
//                PropertiesReader.readKeys("signup.email.exists");
//
//        Assert.assertTrue(
//                actualError.toLowerCase().contains(expectedError.toLowerCase()),
//                "Existing email error NOT shown!"
//        );
//
//    }
//    // =====================================================
    // FLOW 2 — POSITIVE SIGNUP
    // =====================================================

    @Test(
            dataProvider = "signupValidData",
            dataProviderClass = UtilsExcel.class,
            priority = 3
    )
    public void validSignUpTest(
            String tcId,
            String name,
            String email
    ) {

        SignUpPage signUp = new SignUpPage();

        logger.info("========== STARTING POSITIVE TEST: {} ==========", tcId);
        logger.info("Opening Signup page");
        signUp.goToSignUpPage();

        logger.info("Entering Valid Name: {}", name);
        signUp.enterUsername(name);

        logger.info("Entering Valid Email: {}", email);
        signUp.enterEmailAddress(email);

        logger.info("Clicking Signup button");
        signUp.clickSignUp();

        String expectedTitle =
                PropertiesReader.readKeys("expected.signup.title");

        logger.info("Verifying Page Title");
        Assert.assertEquals(
                signUp.getPageTitle(),
                expectedTitle,
                "Title mismatch for " + tcId
        );

        String expectedHeader =
                PropertiesReader.readKeys("expected.signup.header");

        logger.info("Verifying Signup Header");
        Assert.assertEquals(
                signUp.getSignupHeader(),
                expectedHeader,
                "Header mismatch for " + tcId
        );

        logger.info("Positive signup passed for {}", tcId);
        logger.info("========== END POSITIVE TEST: {} ==========", tcId);
    }
}
