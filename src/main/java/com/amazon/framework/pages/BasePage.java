package com.amazon.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazon.framework.exceptions.ElementNotFoundException;

import java.time.Duration;

/**
 * Base page class that provides common methods for all page objects.
 * Extends this class for all page object implementations.
 */
public abstract class BasePage {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    /**
     * Constructor to initialize page object with WebDriver.
     *
     * @param driver the WebDriver instance
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        int explicitWait = Integer.parseInt(System.getProperty("explicit.wait", "15"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }

    /**
     * Finds a web element and waits for it to be visible.
     *
     * @param locator the By locator of the element
     * @return the WebElement
     * @throws ElementNotFoundException if element is not found
     */
    protected WebElement find(By locator) {
        try {
            logger.debug("Finding element: {}", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not found within timeout: {}", locator);
            throw new ElementNotFoundException(locator);
        }
    }

    /**
     * Clicks on a web element.
     *
     * @param locator the By locator of the element
     * @throws ElementNotFoundException if element is not clickable
     */
    protected void click(By locator) {
        try {
            logger.debug("Clicking element: {}", locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (TimeoutException e) {
            logger.error("Element not clickable: {}", locator);
            throw new ElementNotFoundException(locator);
        }
    }

    /**
     * Types text into a web element.
     *
     * @param locator the By locator of the element
     * @param text the text to type
     * @throws ElementNotFoundException if element is not found
     */
    protected void type(By locator, String text) {
        try {
            logger.debug("Typing '{}' into element: {}", text, locator);
            WebElement element = find(locator);
            element.clear();
            element.sendKeys(text);
        } catch (ElementNotFoundException e) {
            logger.error("Cannot type into element: {}", locator);
            throw e;
        }
    }

    /**
     * Gets text from a web element.
     *
     * @param locator the By locator of the element
     * @return the text of the element
     * @throws ElementNotFoundException if element is not found
     */
    protected String textOf(By locator) {
        try {
            logger.debug("Getting text from element: {}", locator);
            return find(locator).getText().trim();
        } catch (ElementNotFoundException e) {
            logger.error("Cannot get text from element: {}", locator);
            throw e;
        }
    }

    /**
     * Checks if an element is present on the page.
     *
     * @param locator the By locator of the element
     * @return true if element is present, false otherwise
     */
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            logger.debug("Element present: {}", locator);
            return true;
        } catch (Exception e) {
            logger.debug("Element not present: {}", locator);
            return false;
        }
    }

    /**
     * Waits for an element to be visible.
     *
     * @param locator the By locator of the element
     * @return true if element becomes visible, false otherwise
     */
    protected boolean waitForElementToBeVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.debug("Element is now visible: {}", locator);
            return true;
        } catch (TimeoutException e) {
            logger.warn("Timeout waiting for element to be visible: {}", locator);
            return false;
        }
    }
}
