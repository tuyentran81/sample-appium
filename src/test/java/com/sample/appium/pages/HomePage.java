package com.sample.appium.pages;

import com.sample.appium.framework.BasePage;
import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage {
    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isSessionActive() {
        return driver.getSessionId() != null;
    }
}
