package com.sample.appium.driver;

import com.sample.appium.config.FrameworkConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class DriverFactory {
    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void createDriver() {
        String platformName = FrameworkConfig.getOrDefault("platformName", "Android");
        String appType = FrameworkConfig.getOrDefault("appType", "native");
        String remoteUrl = FrameworkConfig.getOrDefault("remoteUrl", "http://127.0.0.1:4723/");

        MutableCapabilities options = buildOptions(platformName, appType);
        try {
            AppiumDriver driver = new AppiumDriver(new URL(remoteUrl), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            DRIVER.set(driver);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Appium server URL: " + remoteUrl, e);
        }
    }

    public static AppiumDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {
        AppiumDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    private static MutableCapabilities buildOptions(String platformName, String appType) {
        if ("ios".equalsIgnoreCase(platformName)) {
            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName(FrameworkConfig.getOrDefault("deviceName", "iPhone 15"));
            options.setPlatformVersion(FrameworkConfig.getOrDefault("platformVersion", "17.0"));
            options.setAutomationName(FrameworkConfig.getOrDefault("automationName", "XCUITest"));
            options.setUdid(FrameworkConfig.get("udid"));
            if (!"web".equalsIgnoreCase(appType)) {
                options.setApp(FrameworkConfig.get("app"));
            }
            if ("hybrid".equalsIgnoreCase(appType)) {
                options.setAutoWebview(true);
            }
            if ("web".equalsIgnoreCase(appType)) {
                options.setCapability("browserName", FrameworkConfig.getOrDefault("browserName", "Safari"));
            }
            return options;
        }

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(FrameworkConfig.getOrDefault("deviceName", "Android Emulator"));
        options.setPlatformVersion(FrameworkConfig.getOrDefault("platformVersion", "14"));
        options.setAutomationName(FrameworkConfig.getOrDefault("automationName", "UiAutomator2"));
        options.setUdid(FrameworkConfig.get("udid"));
        if (!"web".equalsIgnoreCase(appType)) {
            options.setApp(FrameworkConfig.get("app"));
        }
        if ("hybrid".equalsIgnoreCase(appType)) {
            options.setAutoWebview(true);
        }
        if ("web".equalsIgnoreCase(appType)) {
            options.setCapability("browserName", FrameworkConfig.getOrDefault("browserName", "Chrome"));
        }
        return options;
    }
}
