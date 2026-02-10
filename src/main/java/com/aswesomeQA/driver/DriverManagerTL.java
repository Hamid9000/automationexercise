package com.aswesomeQA.driver;

import com.aswesomeQA.utils.PropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.atomic.AtomicInteger;

public class DriverManagerTL {

    // Thread-safe driver for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // 🔥 NEW: global counter for window position
    private static AtomicInteger windowCounter = new AtomicInteger(0);

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {

        String browser = PropertiesReader.readKeys("browser").toLowerCase();
        String headless = System.getProperty("headless", "false");

        int parallelPerScreen = Integer.parseInt(
                PropertiesReader.readKeys("parallel.per.screen")
        );

        String windowLayout =
                PropertiesReader.readKeys("window.layout").toLowerCase();

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                int screenWidth = 1920;
                int screenHeight = 1080;

                int width = screenWidth / parallelPerScreen;
                int height = screenHeight;

                // 🔥 CORRECT INDEX (thread-safe)
                int index = windowCounter.getAndIncrement() % parallelPerScreen;
                int xPosition = index * width;

                if (windowLayout.equals("split")) {

                    chromeOptions.addArguments(
                            "--window-size=" + width + "," + height);
                    chromeOptions.addArguments(
                            "--window-position=" + xPosition + ",0");

                } else {
                    chromeOptions.addArguments("--start-maximized");
                }

                if (headless.equalsIgnoreCase("true")) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver.set(new EdgeDriver(edgeOptions));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
