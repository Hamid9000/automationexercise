package com.aswesomeQA.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvidersSQL {

    @DataProvider(name = "signupPositiveTestDataSQL")
    public Object[][] signupPositiveTestData() {
        return SQLReader.getSignupPositiveData();
    }
}
