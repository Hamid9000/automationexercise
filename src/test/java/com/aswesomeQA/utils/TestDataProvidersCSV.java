package com.aswesomeQA.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvidersCSV {

    // -------- REGISTRATION DATA --------

    @DataProvider(name = "registrationPositiveTestDataCSV")
    public Object[][] registrationPositiveCsvData() {

        String resourcePath =
                "testdata/registration/Registration_Positive_TestData_Excel_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }

    @DataProvider(name = "registrationNegativeTestDataCSV")
    public Object[][] registrationNegativeCsvData() {

        String resourcePath =
                "testdata/registration/Registration_Negative_TestData_Excel_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }

    // -------- SIGNUP DATA (CORRECTED PATHS) --------

    @DataProvider(name = "signupPositiveTestDataCSV")
    public Object[][] signupPositiveCsvData() {

        String resourcePath =
                "testdata/signup/Signup_Positive_TestData_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }

    @DataProvider(name = "signupNegativeTestDataCSV")
    public Object[][] signupNegativeCsvData() {

        String resourcePath =
                "testdata/signup/Signup_Negative_TestData_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }
}
