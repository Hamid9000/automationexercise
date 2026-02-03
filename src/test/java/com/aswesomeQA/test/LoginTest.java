package com.aswesomeQA.test;

import com.aswesomeQA.base.CommonToAllTest;
import com.aswesomeQA.pages.LoginPage;
import com.aswesomeQA.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.aswesomeQA.driver.DriverManager.getDriver;

public class LoginTest extends CommonToAllTest {

        @Test(priority = 1)
        public void validLoginTest() {

            LoginPage login = new LoginPage();

            login.goToLoginPage();
            login.enterUsername(PropertiesReader.readKeys("id"));
            login.enterPassword(PropertiesReader.readKeys("password"));
            login.clickLogin();

            String currentUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("account"),
                    "Login failed — not redirected to My Account page");
        }

//        @Test(priority = 2)
//        public void validLoginWithUppercaseEmailTest() {
//
//            LoginPage login = new LoginPage();
//
//            login.goToLoginPage();
//            login.enterUsername("DEMO@OPENCART.COM");
//            login.enterPassword("demo123");
//            login.clickLogin();
//
//            String currentUrl = getDriver().getCurrentUrl();
//            Assert.assertTrue(currentUrl.contains("account"),
//                    "Login failed with uppercase email");
//        }
//
//        @Test(priority = 3)
//        public void rememberMeTest() {
//
//            LoginPage login = new LoginPage();
//
//            login.goToLoginPage();
//            login.checkRememberMe();          // ← you should have this method in LoginPage
//            login.enterUsername("demo@opencart.com");
//            login.enterPassword("demo123");
//            login.clickLogin();
//
//            getDriver().navigate().refresh(); // simulate browser refresh
//
//            String currentUrl = getDriver().getCurrentUrl();
//            Assert.assertTrue(currentUrl.contains("account"),
//                    "User was logged out after refresh");
//        }
//
//        @Test(priority = 4)
//        public void forgotPasswordNavigationTest() {
//
//            LoginPage login = new LoginPage();
//
//            login.goToLoginPage();
//            login.clickForgottenPassword();
//
//            String currentUrl = getDriver().getCurrentUrl();
//            Assert.assertTrue(currentUrl.contains("forgotten"),
//                    "Forgot Password page did not open");
//        }
//
//        @Test(priority = 5)
//        public void backFromForgotPasswordTest() {
//
//            LoginPage login = new LoginPage();
//
//            login.goToLoginPage();
//            login.clickForgottenPassword();
//
//            getDriver().navigate().back();   // go back to login page
//
//            String currentUrl = getDriver().getCurrentUrl();
//            Assert.assertTrue(currentUrl.contains("login"),
//                    "Did not return to Login Page");
//        }
//    }

}
