package automationpractice.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import automationpractice.Pages.LandingPage;
import automationpractice.Pages.NewUserCreatPage;
import automationpractice.Pages.SignInLinkPage;
import automationpractice.Pages.SignUpPage;
import automationpractice.libraries.Base;
import automationpractice.resources.SignUpTestData;
import automationpractice.validator.Validator;

public class RegressionTest1 extends Base {

	final static Logger logger = Logger.getLogger(RegressionTest1.class);

	@Test(enabled = false, priority = 1)
	public void HomepageTest() throws IOException {
		try {
			LandingPage mt = new LandingPage();
			mt.TCID101();
			mt.TCID201();
			Lib.customWait(1);
			
		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);

		}

	}
	@Test(enabled = false, priority = 2, dataProvider = "getData", dataProviderClass = SignUpTestData.class)
	public void LoginPage(String email, String text) {
		try {
			driver.get(myUrl);
			SignInLinkPage sn = new SignInLinkPage();
			sn.justNavigateToSignIn();

			SignUpPage lp = new SignUpPage();
			lp.userName().sendKeys(email);
			logger.info(text);
			lp.createSubmit().click();
			Lib.customWait(1);
			Validator.verifyElm();
			Lib.customWait(2);

			if (Validator.verifyElm()) {
				NewUserCreatPage ap = new NewUserCreatPage();
				ap.regFormfill();
			}
			Lib.customWait(3);
		} catch (Exception e) {
			logger.info("error" + e);
		}
	}
}