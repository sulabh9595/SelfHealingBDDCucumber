package com.amazon.framework.exceptions;

/**
 * Exception thrown when configuration files or settings are invalid.
 */
public class ConfigurationException extends FrameworkException {
    
    public ConfigurationException(String message) {
        super(message);
    }
    
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
