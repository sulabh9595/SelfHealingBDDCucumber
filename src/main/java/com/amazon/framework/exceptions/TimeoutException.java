package com.amazon.framework.exceptions;

/**
 * Exception thrown when an operation times out.
 */
public class TimeoutException extends FrameworkException {
    
    public TimeoutException(String message) {
        super(message);
    }
    
    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
