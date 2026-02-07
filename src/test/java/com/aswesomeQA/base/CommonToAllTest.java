package com.aswesomeQA.base;

import com.aswesomeQA.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonToAllTest {
    @BeforeMethod
    public void setUp(){
        DriverManager.initDriver();
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.down();
    }
}
