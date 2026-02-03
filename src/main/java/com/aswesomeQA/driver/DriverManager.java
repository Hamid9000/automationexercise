package com.aswesomeQA.driver;

import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initDriver() {
        String browser = PropertiesReader.readKeys("browser");
        browser = browser.toLowerCase();

        if (driver == null) {
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");

                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }
        }
    }

    // close browser

    public static void down() {
        if (driver != null) {
            driver.quit();
            driver = null;


        }
    }
}
