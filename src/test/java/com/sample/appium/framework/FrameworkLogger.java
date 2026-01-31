package com.sample.appium.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FrameworkLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger("AppiumFramework");

    private FrameworkLogger() {
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
