package automationpractice.validator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationpractice.libraries.Base;

public class Validator extends Base {

	final static Logger logger = Logger.getLogger(Validator.class);

	public static boolean validateTestCases() {// Validation for Login credentials

		if (isElmVisible(By.cssSelector("h1[class='page-heading']"))) {
			logger.info("Negative Test passed - found registration form after login");
			return false;
		} else {
			if (driver.findElement(By.cssSelector("a[class='logout']")).isDisplayed()) {
				logger.info("Postitive test passed--- all given condition passed ");
				return true;
			} else {
				logger.info("Postitive test Failed--- you are expecting this to pass but failed ");
				return false;
			}
		}
	}

	public static boolean isElmVisible(By by) {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void validateTitle() {// validation for title verification

		String expectedTitle = "My Store";

		if (expectedTitle.contentEquals(driver.getTitle())) {
			logger.info("Title verified-- test passed ");
		} else {
			logger.info("not matched title as expected--test failed ");
		}

	}

	public static boolean validatSignInLink() { // Validate register link

		if (driver.findElement(By.cssSelector("a[class='login']")).isDisplayed()) {

			logger.info("Sign in Link is working----- test passed");
		} else {
			logger.info("Sign in link is not working-- test failed");
		}
		return false;

	}

	public static boolean verifyElm() {// sign up test validator
		try {

			String ErrorMessage = driver.findElement(By.xpath("//*[@id='create_account_error']")).getText().trim();
			String errMsg = driver
					.findElement(
							By.xpath("//li[contains(text(),'An account using this email address has already be')]"))
					.getText().trim();
			String WelcomeMsg = driver.findElement(By.xpath("//h3[contains(text(),'Your personal information')]"))
					.getText().trim();

			if (ErrorMessage.contains("Invalid email address.")) {
				logger.info("Invalid email --- Negative test passed");
				return false;
			} else if (errMsg.contains(
					"An account using this email address has already been registered. Please enter a valid password or request a new one.")) {
				logger.info(" Email Id is already registrated ----negative test passed");
				return false;

			} else if (WelcomeMsg.contains("Your personal information")) {
				logger.info("New and valid email-- you can register now !");
				return true;
			}
		} catch (NoSuchElementException et) {

		}
		return false;
	}

	public static boolean signInValidator() {// SignIN validator
		try {

			String welComeMessage = driver.findElement(By.cssSelector("p[class='info-account']")).getText();
			if (welComeMessage.contentEquals(
					"Welcome to your account. Here you can manage all of your personal information and orders.")) {
				logger.info("Login Successfull");
				return true;
			} else {

				logger.info(" not a login login credentials -- negative test!");
			}
		} catch (NoSuchElementException e1) {

		}
		return false;

	}
}
