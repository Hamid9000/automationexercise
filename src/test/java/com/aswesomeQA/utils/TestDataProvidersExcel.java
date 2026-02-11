package com.aswesomeQA.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvidersExcel {

    // ---------- SIGNUP DATA ----------

    @DataProvider(name = "signupNegativeTestDataExcel")
    public static Object[][] signupNegativeTestData() {
        return ExcelReader.readSheet(
                ExcelPaths.SIGNUP_NEG_FILE,
                "Sheet1"
        );
    }

    @DataProvider(name = "signupPositiveTestDataExcel")
    public static Object[][] signupPositiveTestData() {
        return ExcelReader.readSheet(
                ExcelPaths.SIGNUP_POS_FILE,
                "Sheet1"
        );
    }

    // ---------- REGISTRATION DATA ----------

    @DataProvider(name = "registrationNegativeTestDataCSV")
    public static Object[][] registrationNegativeTestData() {
        return ExcelReader.readSheet(
                ExcelPaths.REG_NEG_FILE,
                "Sheet1"
        );
    }

    @DataProvider(name = "registrationPositiveTestDataCSV")
    public static Object[][] registrationPositiveTestData() {
        return ExcelReader.readSheet(
                ExcelPaths.REG_POS_FILE,
                "Sheet1"
        );
    }

    // ---------- E2E DATA (CORRECT MATCH WITH YOUR PATH) ----------

    @DataProvider(name = "e2eSignupRegistrationData")
    public static Object[][] e2eSignupRegistrationData() {
        return ExcelReader.readSheet(
                ExcelPaths.E2E_FLOW_FILE,
                "Sheet1"
        );
    }
}
