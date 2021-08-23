package automationpractice.Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import automationpractice.libraries.Base;
import automationpractice.validator.Validator;

public class LandingPage extends Base {

	final static Logger logger = Logger.getLogger(LandingPage.class);

	public LandingPage TCID101() {
		try {
			logger.info("Verify Page Title");
			driver.get(myUrl);
			String pageTitle = driver.getTitle();
			String expectedTitle = "My Store";
			assertEquals(pageTitle, expectedTitle); // title check

		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);

		}
		return this;
	}

	public LandingPage TCID201() {
		try {
			driver.findElement(By.cssSelector("a[class='login']")).click(); // Click on Register link
			Validator.validatSignInLink();
			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);

		}
		return this;
	}
}
