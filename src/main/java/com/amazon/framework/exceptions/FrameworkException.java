package com.amazon.framework.exceptions;

/**
 * Base exception class for the automation framework.
 * All framework-specific exceptions should extend this class.
 */
public class FrameworkException extends RuntimeException {
    
    public FrameworkException(String message) {
        super(message);
    }
    
    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FrameworkException(Throwable cause) {
        super(cause);
    }
}
