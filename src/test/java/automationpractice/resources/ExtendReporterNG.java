package automationpractice.resources;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ExtendReporterNG {
	final static Logger logger = Logger.getLogger(ExtendReporterNG.class);
	public static ExtentReports extent;

	public static ExtentReports getReportObject() {

		try {
			
			// System.getProperty("user.dir")--- gives the current project path
			String path = System.getProperty("user.dir") + "/ExtentReports/index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			extent = new ExtentReports();
			reporter.config().setReportName("Automation Practice Results");
			reporter.config().setDocumentTitle("Test Report");

			extent.attachReporter(reporter);
			extent.setSystemInfo("QA", "Ramesh Shrestha");
			extent.setSystemInfo("Job Id", "1001");
			
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return extent;

	}
}
