package com.amazon.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for logging test events and framework operations.
 */
public class LoggerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtil.class);

    /**
     * Logs an info level message.
     *
     * @param message the message to log
     */
    public static void info(String message) {
        LOGGER.info(message);
    }

    /**
     * Logs an info level message with arguments.
     *
     * @param message the message to log
     * @param args the arguments for the message
     */
    public static void info(String message, Object... args) {
        LOGGER.info(message, args);
    }

    /**
     * Logs a debug level message.
     *
     * @param message the message to log
     */
    public static void debug(String message) {
        LOGGER.debug(message);
    }

    /**
     * Logs a debug level message with arguments.
     *
     * @param message the message to log
     * @param args the arguments for the message
     */
    public static void debug(String message, Object... args) {
        LOGGER.debug(message, args);
    }

    /**
     * Logs a warning level message.
     *
     * @param message the message to log
     */
    public static void warn(String message) {
        LOGGER.warn(message);
    }

    /**
     * Logs a warning level message with arguments.
     *
     * @param message the message to log
     * @param args the arguments for the message
     */
    public static void warn(String message, Object... args) {
        LOGGER.warn(message, args);
    }

    /**
     * Logs an error level message.
     *
     * @param message the message to log
     */
    public static void error(String message) {
        LOGGER.error(message);
    }

    /**
     * Logs an error level message with exception.
     *
     * @param message the message to log
     * @param throwable the exception
     */
    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
