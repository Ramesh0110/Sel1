package automationpractice.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import automationpractice.libraries.Base;

public class SignInLinkPage extends Base {

	final static Logger logger = Logger.getLogger(SignInLinkPage.class);

	public SignInLinkPage justNavigateToSignIn() {
		try {
			driver.findElement(By.cssSelector("a[class='login']")).click();
			
		} catch (Exception e) {
			logger.info("error" + e);

		}
		return this;

	}

}
