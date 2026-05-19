package com.amazon.framework.exceptions;

import org.openqa.selenium.By;

/**
 * Exception thrown when a web element is not found on the page.
 */
public class ElementNotFoundException extends FrameworkException {
    
    public ElementNotFoundException(By locator) {
        super("Element not found: " + locator);
    }
    
    public ElementNotFoundException(String message) {
        super(message);
    }
    
    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
