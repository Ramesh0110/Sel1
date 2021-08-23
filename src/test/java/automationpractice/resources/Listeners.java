package automationpractice.resources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automationpractice.libraries.Base;

public class Listeners extends Base implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtendReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {// On Test Start
		test = extent.createTest(result.getMethod().getMethodName());		
		extentTest.set(test);
	}

	
	public void onTestSuccess(ITestResult result) {// On Test Success
		extentTest.get().log(Status.PASS, "Test successfully passed.........!!");
		
	}	
	public void onTestFailure(ITestResult result) {// On Test Failure
		extentTest.get().fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
		}
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}

