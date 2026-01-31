package com.sample.appium.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Path;

public final class ExtentReportManager {
    private static final ExtentReports EXTENT_REPORTS = new ExtentReports();

    static {
        Path reportPath = Path.of("test-output", "extent-report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
        EXTENT_REPORTS.attachReporter(sparkReporter);
        EXTENT_REPORTS.setSystemInfo("Framework", "Appium POM");
    }

    private ExtentReportManager() {
    }

    public static ExtentReports getInstance() {
        return EXTENT_REPORTS;
    }
}
