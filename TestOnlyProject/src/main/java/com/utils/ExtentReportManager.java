package com.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {

    private static final ExtentReports extentReports = createExtentReport();
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    private ExtentReportManager() {
    }

    private static ExtentReports createExtentReport() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportPath = "target/extent-reports/extent-spark-" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("Selenium Cucumber Execution Report");
        sparkReporter.config().setDocumentTitle("Automation Test Results");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Framework", "Selenium + Cucumber + TestNG");
        return reports;
    }

    public static void createScenarioTest(String scenarioName) {
        extentTestThreadLocal.set(extentReports.createTest(scenarioName));
    }

    public static ExtentTest getScenarioTest() {
        return extentTestThreadLocal.get();
    }

    public static void flushReport() {
        extentReports.flush();
    }
}
