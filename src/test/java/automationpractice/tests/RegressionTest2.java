package automationpractice.tests;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import automationpractice.Pages.NewUserCreatPage;
import automationpractice.Pages.SearchSortAddPage;
import automationpractice.Pages.SignInLinkPage;
import automationpractice.Pages.SignInPage;
import automationpractice.Pages.SignUpPage;
import automationpractice.Pages.checkOutPgae;
import automationpractice.libraries.Base;
import automationpractice.resources.SignInCredentialsData;
import automationpractice.resources.SignUpTestData;
import automationpractice.validator.Validator;

public class RegressionTest2 extends Base {

	final static Logger logger = Logger.getLogger(RegressionTest2.class);

	@Test(enabled = true, priority = 3, dataProvider = "getDataContent", dataProviderClass = SignInCredentialsData.class)
	public void signInElmTEst(String emailID, String PassWD, String Text) {
		try {
			driver.get(myUrl);
			SignInLinkPage sn = new SignInLinkPage();
			sn.justNavigateToSignIn();

			SignInPage si = new SignInPage();
			si.userEmailElm().sendKeys(emailID);
			si.passWordElm().sendKeys(PassWD);
			logger.info(Text);
			si.submitElm().click();

			if (Validator.signInValidator()) {
			
				SearchSortAddPage searchAndSort = new SearchSortAddPage();
				searchAndSort.TCID501();
				searchAndSort.TCID502();
				searchAndSort.TCID503();
				searchAndSort.TCID504();
				searchAndSort.TCID601();
				searchAndSort.TCID602();
				searchAndSort.TCID603();
				searchAndSort.TCID604();
				searchAndSort.TCID701();
				checkOutPgae checkOutProcess=new checkOutPgae();
				checkOutProcess.TCID702();
				checkOutProcess.TCID703();
				checkOutProcess.TCID704();
				checkOutProcess.TCID705();
				checkOutProcess.TCID706();
				checkOutProcess.TCID707();
				checkOutProcess.TCID708();
				checkOutProcess.TCID709();
	
			} else {

				//logger.info("not a login credentials----!! Try different email and password..!!! ");
			}
		} catch (Exception e) {
			logger.info("error" + e);			
			assertTrue(false);
		}
	}
}
