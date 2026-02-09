package com.aswesomeQA.driver;

import com.aswesomeQA.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initDriver() {

        String browser = PropertiesReader.readKeys("browser").toLowerCase();
        String headless = System.getProperty("headless", "false");

        if (driver == null) {

            switch (browser) {

                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");

                    if (headless.equalsIgnoreCase("true")) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--disable-gpu");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");

                    if (headless.equalsIgnoreCase("true")) {
                        edgeOptions.addArguments("--headless=new");
                        edgeOptions.addArguments("--disable-gpu");
                        edgeOptions.addArguments("--window-size=1920,1080");
                    }

                    driver = new EdgeDriver(edgeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized");

                    if (headless.equalsIgnoreCase("true")) {
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--width=1920");
                        firefoxOptions.addArguments("--height=1080");
                    }

                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "safari":
                    SafariOptions safariOptions = new SafariOptions();
                    // Note: Safari does NOT support headless officially
                    driver = new SafariDriver(safariOptions);
                    break;

                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }
        }
    }

    public static void down() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}