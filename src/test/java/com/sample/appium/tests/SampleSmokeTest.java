package com.sample.appium.tests;

import com.sample.appium.framework.BaseTest;
import com.sample.appium.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleSmokeTest extends BaseTest {

    @Test(description = "Sample smoke test demonstrating Arrange-Act-Assert")
    public void shouldLaunchSession() {
        // Arrange
        HomePage homePage = new HomePage(driver());

        // Act
        boolean sessionActive = homePage.isSessionActive();

        // Assert
        Assert.assertTrue(sessionActive, "Expected Appium session to be active");
    }
}
