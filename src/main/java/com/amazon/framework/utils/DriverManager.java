package com.amazon.framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazon.framework.exceptions.ConfigurationException;

/**
 * Manages WebDriver lifecycle using ThreadLocal for thread-safe operations.
 * Supports multiple browsers: Chrome, Firefox, and Edge.
 */
public class DriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    /**
     * Gets the WebDriver instance for the current thread.
     * Initializes the driver if not already created.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            initDriver();
        }
        return DRIVER.get();
    }

    /**
     * Initializes the WebDriver based on the browser type from configuration.
     * 
     * @throws ConfigurationException if browser type is not supported
     */
    public static void initDriver() {
        String browser = ConfigReader.getProperty("browser", "chrome").toLowerCase();
        LOGGER.info("Initializing WebDriver for browser: {}", browser);

        try {
            WebDriver driver = switch (browser) {
                case "chrome" -> initChromeDriver();
                case "firefox" -> initFirefoxDriver();
                case "edge" -> initEdgeDriver();
                default -> throw new ConfigurationException("Unsupported browser: " + browser);
            };
            DRIVER.set(driver);
            LOGGER.info("WebDriver initialized successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize WebDriver", e);
            throw new ConfigurationException("Error initializing WebDriver: " + e.getMessage(), e);
        }
    }

    /**
     * Initializes Chrome WebDriver with standard options.
     *
     * @return ChromeDriver instance
     */
    private static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Apply standard options
        applyCommonOptions(options);
        
        // Chrome-specific options
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));
        if (headless) {
            options.addArguments("--headless");
        }
        
        LOGGER.debug("Chrome WebDriver created with options: {}", options);
        return new ChromeDriver(options);
    }

    /**
     * Initializes Firefox WebDriver with standard options.
     *
     * @return FirefoxDriver instance
     */
    private static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        // Apply standard options
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));
        if (headless) {
            options.addArguments("--headless");
        }
        
        LOGGER.debug("Firefox WebDriver created");
        return new FirefoxDriver(options);
    }

    /**
     * Initializes Edge WebDriver with standard options.
     *
     * @return EdgeDriver instance
     */
    private static WebDriver initEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        // Apply standard options
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));
        if (headless) {
            options.addArguments("--headless");
        }
        
        LOGGER.debug("Edge WebDriver created");
        return new EdgeDriver(options);
    }

    /**
     * Applies common options to browser options.
     *
     * @param options The browser options to configure
     */
    private static void applyCommonOptions(Object options) {
        if (options instanceof ChromeOptions chromeOptions) {
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
        }
    }

    /**
     * Quits the WebDriver and removes it from ThreadLocal storage.
     */
    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
                LOGGER.info("WebDriver quit successfully");
            } catch (Exception e) {
                LOGGER.error("Error while quitting WebDriver", e);
            } finally {
                DRIVER.remove();
            }
        }
    }

    /**
     * Checks if a WebDriver instance is currently active.
     *
     * @return true if driver is active, false otherwise
     */
    public static boolean isDriverActive() {
        return DRIVER.get() != null;
    }
}
