//package com.aswesomeQA.test.flow;
//
//import com.aswesomeQA.base.CommonToAllTest;
//import com.aswesomeQA.pages.SignUpPage;
//import com.aswesomeQA.pages.RegistrationPage;
//import com.aswesomeQA.utils.PropertiesReader;
//import com.aswesomeQA.utils.TestDataProviders;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class SignupAndRegistrationE2ETest extends CommonToAllTest {
//
//    private static final Logger logger =
//            LoggerFactory.getLogger(SignupAndRegistrationE2ETest.class);
//
//    @Test(
//            dataProvider = "e2eSignupRegistrationData",
//            dataProviderClass = TestDataProviders.class
//    )
//    public void signupAndRegistrationE2E(
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
//            String mobile
//    ) {
//
//        logger.info("===== START E2E FLOW: {} =====", tcId);
//
//        // ================= STEP 1 — SIGNUP =================
//
//        SignUpPage signUp = new SignUpPage();
//
//        signUp.goToSignUpPage();
//        signUp.enterUsername(name);
//        signUp.enterEmailAddress(email);
//        signUp.clickSignUp();
//
//        // Verify reached Registration page
//        String expectedSignupTitle =
//                PropertiesReader.readKeys("expected.signup.title");
//
//        Assert.assertEquals(
//                signUp.getPageTitle(),
//                expectedSignupTitle,
//                "Did not reach Registration page"
//        );
//
//        // ================= STEP 2 — REGISTRATION =================
//
//        RegistrationPage reg = new RegistrationPage();
//
//        if (title.equalsIgnoreCase("Mr")) {
//            reg.selectMr();
//        } else {
//            reg.selectMrs();
//        }
//
//        reg.enterPassword(password);
//        reg.selectDOB("10", "May", "1998");
//        reg.enterFirstName(firstName);
//        reg.enterLastName(lastName);
//        reg.enterAddress1(address1);
//        reg.selectCountry(country);
//        reg.enterState(state);
//        reg.enterCity(city);
//        reg.enterZip(zip);
//        reg.enterMobile(mobile);
//        reg.checkNewsletter();
//
//
//        reg.clickCreateAccount();
//
//        // ================= STEP 3 — FINAL VERIFY =================
//
//        String actualTitle = reg.getCurrentTitle();
//        String actualHeader = reg.getAccountCreatedHeader();
//
//        String expectedTitle =
//                PropertiesReader.readKeys("expected.account.page.title");
//
//        String expectedHeader =
//                PropertiesReader.readKeys("expected.account.header");
//
//        Assert.assertEquals(actualTitle, expectedTitle);
//        Assert.assertEquals(actualHeader, expectedHeader);
//
//        logger.info("===== END E2E FLOW: {} =====", tcId);
//    }
//}
