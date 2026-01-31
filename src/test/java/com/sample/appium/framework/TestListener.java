package com.sample.appium.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final ExtentReports EXTENT_REPORTS = ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = EXTENT_REPORTS.createTest(result.getName());
        CURRENT_TEST.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CURRENT_TEST.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CURRENT_TEST.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CURRENT_TEST.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT_REPORTS.flush();
    }
}
