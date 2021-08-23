package automationpractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automationpractice.libraries.Base;

public class SignUpPage  extends Base{

	
		
		By email=By.cssSelector("input[id='email_create']");
		By CreateBtn=By.cssSelector("button[id='SubmitCreate']");	
		
		public WebElement userName() {			
			return driver.findElement(email);
		}		
		public WebElement createSubmit() {
			return driver.findElement(CreateBtn);

		}
}
