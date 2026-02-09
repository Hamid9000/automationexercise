package com.aswesomeQA.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvidersCSV {

    // ===================== REGISTRATION DATA =====================

    @DataProvider(name = "registrationPositiveTestDataCSV")
    public Object[][] registrationPositiveCsvData() {

        String resourcePath =
                "testdata/registration/Registration_Positive_TestData_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }

    @DataProvider(name = "registrationNegativeTestDataCSV")
    public Object[][] registrationNegativeCsvData() {

        String resourcePath =
                "testdata/registration/Registration_Negative_TestData_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }

    // ===================== SIGNUP DATA =====================

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

    // ===================== LOGIN DATA  =====================

    @DataProvider(name = "loginNegativeTestDataCSV")
    public Object[][] loginNegativeCsvData() {

        String resourcePath =
                "testdata/login/Login_Negative_TestData_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }

    @DataProvider(name = "loginPositiveTestDataCSV")
    public Object[][] loginPositiveCsvData() {

        String resourcePath =
                "testdata/login/Login_Positive_TestData_CSV.csv";

        return CSVReader.readCsvFromResources(resourcePath);
    }
    public class RegistrationDataProvider {

        @DataProvider(name = "completeRegistrationData")
        public Object[][] completeRegistrationData() {

            String resourcePath =
                    "testdata/registration/Complete_Registration_Data.csv";

            return CSVReader.readCsvFromResources(resourcePath);
        }
    }
}
