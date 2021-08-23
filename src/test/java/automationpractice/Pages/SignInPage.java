package automationpractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automationpractice.libraries.Base;

public class SignInPage extends Base {

	
	
	By email=By.cssSelector("input[id='email']");
	By pass=By.cssSelector("input[id='passwd']");
	By signIn=By.cssSelector("button[id='SubmitLogin']");
					
	public WebElement userEmailElm() {	
		return driver.findElement(email);
	}
	public WebElement passWordElm() {
		return driver.findElement(pass);

	}
	public WebElement submitElm() {
		return driver.findElement(signIn);

	}
}
