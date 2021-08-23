package automationpractice.Pages;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import automationpractice.libraries.Base;

public class checkOutPgae extends Base {

	final static Logger logger = Logger.getLogger(checkOutPgae.class);

	public checkOutPgae TCID702() {
		try {
			String summaryCount = driver
					.findElement(By.xpath(
							"//div[@class='shopping_cart']//span[@class='ajax_cart_quantity'][contains(text(),'7')]"))
					.getText();
			Assert.assertTrue(summaryCount.contains("7"));
			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
		
			assertTrue(false);
		}
		return this;
	}

	public checkOutPgae TCID703() {
		try {
			driver.findElement(By.xpath(
					"//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]"))
					.click();
			String sameAddress = driver.findElement(By.cssSelector("label[for='addressesAreEquals']")).getText();
			Assert.assertTrue(sameAddress.contains("Use the delivery address as the billing address."));

			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public checkOutPgae TCID704() {
		try {
			driver.findElement(
					By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")).click();
			String shipping = driver.findElement(By.xpath("//strong[contains(text(),'My carrier')]")).getText();
			Assert.assertTrue(shipping.contains("My carrier"));

			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public checkOutPgae TCID705() {
		try {

			driver.findElement(By.xpath("//button[@name='processCarrier']")).click();
			String popUpMessage = driver.findElement(By.xpath("//p[@class='fancybox-error']")).getText();
			WebElement popUp = driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']"));
			popUp.click();
			Assert.assertTrue(popUpMessage.contains("You must agree to the terms of service before continuing."));

			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public checkOutPgae TCID706() {
		try {
			Lib.handleCheckBox(By.xpath("//input[@id='cgv']"), true);
			driver.findElement(By.xpath("//button[@name='processCarrier']")).click();

			String payMentmethod = driver.findElement(By.xpath("//span[@class='navigation_page']")).getText();
			Assert.assertTrue(payMentmethod.contains("Your payment method"));

			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public checkOutPgae TCID707() {
		try {		
			driver.findElement(By.xpath("//a[@class='bankwire']")).click();
			String bankWirePayment = driver.findElement(By.xpath("//span[@class='navigation_page']")).getText().trim();
			Assert.assertTrue(bankWirePayment.contains("Bank-wire payment."));

			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public checkOutPgae TCID708() {
		try {
			driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();

			String orderConfirmation = driver.findElement(By.xpath("//strong[contains(text(),'Your order on My Store is complete.')]")).getText().trim();
			Assert.assertTrue(orderConfirmation.contains("Your order on My Store is complete."));

			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

	public checkOutPgae TCID709() {
		try {
			logger.info("back to homepage after order confirmation");
			driver.findElement(By.xpath("//img[@class='logo img-responsive']")).click();
			Lib.customWait(3);
			String homeUrl = driver.getCurrentUrl();

			Assert.assertTrue(homeUrl.contains("http://automationpractice.com/index.php"));

			Lib.customWait(2);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return this;
	}

}
