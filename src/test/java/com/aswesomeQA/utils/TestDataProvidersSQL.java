package com.aswesomeQA.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvidersSQL {

    @DataProvider(name = "signupPositiveTestDataSQL")
    public Object[][] signupPositiveTestData() {
        return SQLReader.getTestData("signup.positive.query");
    }

    @DataProvider(name = "signupNegativeTestDataSQL")
    public Object[][] signupNegativeTestData() {
        return SQLReader.getTestData("signup.negative.query");
    }

}
