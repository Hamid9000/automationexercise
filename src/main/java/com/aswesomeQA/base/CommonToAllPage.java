package com.aswesomeQA.base;



import com.aswesomeQA.driver.DriverManager;

import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonToAllPage {


    // -------------------------- DRIVER MANAGEMENT -------------------------


    protected WebDriver driver;
    public CommonToAllPage() {          // ✅ NEW NO-ARG CONSTRUCTOR
       // this.driver = DriverManagerTL.getDriver();
        this.driver = DriverManager.getDriver();

    }


    public WebDriver getDriver() {
        return driver;
    }


    // --------------------------  WAIT CONFIGURATION  -------------------------


    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(15));
    }

    // --------------------------  ELEMENT WAIT HELPERS -------------------------


    public WebElement waitForVisible(By locator) {
        return getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return getWait()
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForPresent(By locator) {
        return getWait()
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForInvisibility(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


    // --------------------------  NAVIGATION ACTIONS -------------------------

    public void openUrl() {
        getDriver().get(PropertiesReader.readKeys("baseUrl"));
    }

    public void waitForUrlContains(String text) {
        getWait().until(ExpectedConditions.urlContains(text));
    }

    public String getPageTitle() {
        getWait().until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
        return getDriver().getTitle();
    }


    // --------------------------  VALIDATION / GETTERS -------------------------

    // ------------------ HTML5 VALIDATION (GENERIC) ------------------

    public String getHtml5ValidationMessage(By locator) {
        return waitForVisible(locator)
                .getAttribute("validationMessage");
    }

    // --------------------------  NUMBER HELPER  --------------------------
    protected String normalizeNumber(String value) {

        if (value == null) return "";

        value = value.trim();

        if (value.contains(".")) {
            value = value.substring(0, value.indexOf("."));
        }

        return value;
    }

    // ---------------- EMAIL VALIDATION UTILITY ----------------
    public boolean isValidEmailFormat(String email) {

        String emailRegex =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        return email != null && email.matches(emailRegex);
    }
    public String generateUniqueEmail(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "@mailinator.com";
    }
    public String generateStrongPassword() {

        long time = System.currentTimeMillis();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "@#$%&*!";

        char U = upper.charAt((int)(time % upper.length()));
        char L = lower.charAt((int)(time % lower.length()));
        char D = digits.charAt((int)(time % digits.length()));
        char S = special.charAt((int)(time % special.length()));

        return "" + U + L + S + "Test" + (time % 10000) + D;
    }



    // -------------------------- JAVASCRIPT UTILITIES --------------------------
// (e.g. click with JS, scroll, highlight, etc.)

// -------------------------- BROWSER UTILITIES --------------------------
// (switch tabs, alerts, windows, refresh, etc.)

// -------------------------- SCREENSHOT UTILITIES --------------------------
// (take screenshot, attach to report, etc.)

// -------------------------- LOGGING UTILITIES --------------------------
// (info, debug, error logs, etc.)

}
