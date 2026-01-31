package com.sample.appium.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class FrameworkConfig {
    private static final Properties PROPERTIES = new Properties();

    static {
        String configPath = System.getProperty("configFile", "src/test/resources/config/framework.properties");
        Path path = Path.of(configPath);
        try (InputStream inputStream = Files.newInputStream(path)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            com.sample.appium.framework.FrameworkLogger.error(
                    "Unable to load configuration from " + path,
                    e
            );
            throw new IllegalStateException("Unable to load configuration: " + path, e);
        }
    }

    private FrameworkConfig() {
    }

    public static String get(String key) {
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }

    public static String getOrDefault(String key, String defaultValue) {
        String value = get(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
