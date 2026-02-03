package com.aswesomeQA.test;

import com.aswesomeQA.base.CommonToAllTest;
import com.aswesomeQA.pages.SignUpPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.aswesomeQA.driver.DriverManager.getDriver;

public class SignUpTest extends CommonToAllTest {
    @Test(priority = 1)
    public void validSignUpTest() {


        SignUpPage signUp = new SignUpPage();

        // Step 1 — Open SignUp page
        signUp.goToSignUpPage();

        // Step 2 — Enter data from properties
        signUp.enterUsername(
                PropertiesReader.readKeys("signUpName1")
        );

        signUp.enterEmailAddress(
                PropertiesReader.readKeys("signUpEmail1")
        );

        // Step 3 — Click Signup
        signUp.clickSignUp();

        // Step 4 — Assertion
        String actualMessage = signUp.getActualMessage().toLowerCase();
        String expectedMessage = PropertiesReader.readKeys("expectedSignUpPageMessage").toLowerCase();

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Signup page message did NOT match!"
        );
    }


    @Test(priority = 2)
    public void signUpWithSpacesInNameTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterUsername("Hamid Sheikh");
        signUp.enterEmailAddress("hamid" + System.currentTimeMillis() + "@test.com");
        signUp.clickSignUp();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("success"),
                "Signup failed for name with spaces");
    }

    @Test(priority = 3)
    public void signUpWithUppercaseEmailTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterUsername("Hamid");
        signUp.enterEmailAddress("HAMID" + System.currentTimeMillis() + "@TEST.COM");
        signUp.clickSignUp();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("success"),
                "Signup failed for uppercase email");
    }

    @Test(priority = 4)
    public void emptyNameFieldTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterEmailAddress("hamid@test.com");
        signUp.clickSignUp();

        String pageSource = getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains("Name is required"),
                "No validation shown for empty name");
    }

    @Test(priority = 5)
    public void emptyEmailFieldTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterUsername("Hamid");
        signUp.clickSignUp();

        String pageSource = getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains("Email is required"),
                "No validation shown for empty email");
    }

    @Test(priority = 6)
    public void invalidEmailFormatTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterUsername("Hamid");
        signUp.enterEmailAddress("hamid@com");
        signUp.clickSignUp();

        String pageSource = getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains("valid email"),
                "Invalid email format not validated");
    }

    @Test(priority = 7)
    public void alreadyRegisteredEmailTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterUsername("Hamid");
        signUp.enterEmailAddress("demo@opencart.com"); // already registered
        signUp.clickSignUp();

        String pageSource = getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains("already exists"),
                "Duplicate email validation missing");
    }

    @Test(priority = 8)
    public void shortNameValidationTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterUsername("A");
        signUp.enterEmailAddress("a@test.com");
        signUp.clickSignUp();

        String pageSource = getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains("minimum"),
                "Minimum length validation missing");
    }

    @Test(priority = 9)
    public void nameWithSpecialCharactersTest() {

        SignUpPage signUp = new SignUpPage();

        signUp.goToSignUpPage();
        signUp.enterUsername("@Hamid!");
        signUp.enterEmailAddress("hamid@test.com");
        signUp.clickSignUp();

        String pageSource = getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains("invalid"),
                "Special character validation missing");
    }

}