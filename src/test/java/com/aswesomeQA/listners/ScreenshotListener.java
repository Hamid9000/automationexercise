package com.aswesomeQA.listners;

import com.aswesomeQA.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {


       // WebDriver driver = DriverManagerTL.getDriver();
        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            try {
                attachScreenshot(driver);
                saveScreenshotToFolder(
                        driver,
                        "Failure_" + result.getMethod().getMethodName()
                );
                System.out.println("✅ Screenshot captured for FAILED test: "
                        + result.getMethod().getMethodName());
            } catch (Exception e) {
                System.err.println("❌ Failed to capture failure screenshot: "
                        + e.getMessage());
            }
        } else {
            System.err.println("⚠️ WebDriver is null on failure.");
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }

    public void saveScreenshotToFolder(WebDriver driver, String label) {

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String folderPath = "screenshots";
        String fileName =
                folderPath + "/" + label + "_" + timestamp + ".png";

        try {
            File dest = new File(fileName);
            dest.getParentFile().mkdirs();
            Files.copy(
                    src.toPath(),
                    dest.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot to folder: "
                    + e.getMessage());
        }
    }
}
