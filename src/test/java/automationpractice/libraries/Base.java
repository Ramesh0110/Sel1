package automationpractice.libraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;

import automationpractice.resources.ExtendReporterNG;

public class Base {
	final static Logger logger = Logger.getLogger(Base.class);

	public static WebDriver driver;
	public static SeleniumLibrary Lib;
	public static ExtentReports extent;
	public String myBrowser;
	private String myDemoMode;
	private String mySendEmail;
	public static String myUrl;
	public JavaPropertiesManager properties;

	private String myRemote;

	@BeforeMethod

	public void set_up() {
		try {

			ExtendReporterNG.getReportObject();// getExtent report

			if (myDemoMode.toLowerCase().contains("on")) {
				Lib.setDemoMode(true);
			}

			if (myRemote.toLowerCase().contains("true")) {// Running in remote using selenium Grid Extras
				driver = Lib.startRemoteBrowser("http://192.168.0.16:4444/wd/hub/", myBrowser);

			} else {

				driver = Lib.startBrowser(myBrowser);// local computer browser or just say local hub
			}

		} catch (Exception e) {
			logger.error("Error", e);
			// assertTrue(false);

		}

	}

	@AfterMethod

	public void waitTime(ITestResult result) {

		try {

			if (ITestResult.FAILURE == result.getStatus()) {
				Lib.screenCapture(result.getName());

			}
			if (driver != null) {
				driver.close();
			}
			Thread.sleep(5 * 1000);
		} catch (Exception e) {
			logger.error("Error", e);
			// assertTrue(false);

		}

	}

	@AfterClass

	public void tearing_down() {
		try {

			if (driver != null) {
				driver.quit();
			}
			logger.info("All the test completed.....");

			EmailManager email = new EmailManager();
			email.toAddress = "rameshshrestha01@outlook.com";// Add or remove your email

			Lib.automaticallyAttachErrorImgToEmail();

			Lib.errorScreenshots.add("target/logs/log4j-selenium.log");
			Lib.errorScreenshots.add("target/logs/Selenium-Report.html");

			if (mySendEmail.toLowerCase().contains("true")) {
				email.sendEmail(Lib.errorScreenshots);
			}

		} catch (Exception e) {
			logger.error("Error", e);

		}
	}

	@BeforeClass

	public void beforeAllTests() {
		try {
			logger.info("Starting all the tests...");

			properties = new JavaPropertiesManager("src/test/resources/config.properties");
			myBrowser = properties.readProperty("browserType");
			myDemoMode = properties.readProperty("demoMode");
			mySendEmail = properties.readProperty("sendEmail");
			myRemote = properties.readProperty("remote");
			myUrl = properties.readProperty("url");

			if (myDemoMode.toLowerCase().contains("on")) {
				logger.info("Demo mode is ON ....");
			} else {
				logger.info("Demo mode is OFF ...");
			}

			JavaPropertiesManager propSession = new JavaPropertiesManager(
					"src/test/resources/dynamicConfig.properties");
			Lib = new SeleniumLibrary(driver);
			String tempSessionTime = Lib.getCurrentTime();
			propSession.setProperty("sessionTime", tempSessionTime);
			logger.info("Session Time: " + tempSessionTime);

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}
}
