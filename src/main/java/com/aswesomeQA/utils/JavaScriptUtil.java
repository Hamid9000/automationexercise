package com.aswesomeQA.utils;

import com.aswesomeQA.driver.DriverManager; // DriverManager ko import kiya
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

//    // Ab constructor ki zaroorat nahi hai, hum direct DriverManager se driver lenge
//
//    public static String getValidationMessage(WebElement element) {
//        // DriverManager.getDriver() use karke current driver mil jayega
//        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//        return (String) js.executeScript("return arguments[0].validationMessage;", element);
//    }
//
//    public static void scrollDown() {
//        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//    }
}