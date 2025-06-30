package com.telecom.extentreportlistener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentTestListener implements ITestListener, ISuiteListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ISuite suite) {
		
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Suite", suite.getName());
	}

	@Override
	public void onFinish(ISuite suite) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());

		Object currentClass = result.getInstance();
		WebDriver driver = null;

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String filePath = "screenshots/" + result.getName() + "_" + timestamp + ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(filePath);
		dest.getParentFile().mkdirs();

		try {
			FileHandler.copy(src, dest);
			test.get().fail(MediaEntityBuilder.createScreenCaptureFromPath(dest.getAbsolutePath()).build());
		} catch (IOException e) {
			test.get().fail("Screenshot error: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped");
	}

	private String captureScreenshot(WebDriver driver, String screenshotName) {
		String dateName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + dateName
				+ ".png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
}
