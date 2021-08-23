package automationpractice.Pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import automationpractice.libraries.Base;
import automationpractice.libraries.ExcelManager;

public class NewUserCreatPage extends Base {
	final static Logger logger = Logger.getLogger(NewUserCreatPage.class);

	public NewUserCreatPage regFormfill() throws IOException {
		ExcelManager edata=new ExcelManager();
		ArrayList al=edata.getData("profile");// getting data from excel sheet 
		
		try {
			Lib.clickButton(By.cssSelector("label[for='id_gender1']"));
			Lib.enterText(By.cssSelector("input[id='customer_firstname']"), ""+ al.get(1) +"");
			Lib.enterText(By.cssSelector("input[id='customer_lastname"), ""+ al.get(2) +"");
			Lib.enterText(By.cssSelector("input[id='email']"), ""+ al.get(3) +"");
			Lib.enterText(By.cssSelector("input[id='passwd']"), ""+ al.get(4) +"");
			Lib.selectDropDown("6", By.cssSelector("select[id='days']"));
			Lib.selectDropDown("3", By.cssSelector("select[id='months']"));
			Lib.selectDropDown("2000", By.cssSelector("select[id='years']"));
			Lib.enterText(By.cssSelector("input[id='firstname']"), ""+ al.get(5) +"");
			Lib.enterText(By.cssSelector("input[id='lastname']"), ""+ al.get(6) +"");
			Lib.enterText(By.cssSelector("input[id='address1']"), ""+ al.get(7) +"");
			Lib.enterText(By.cssSelector("input[id='city']"), ""+ al.get(8) +"");
			Lib.selectDropDown("46", By.cssSelector("select[id='id_state']"));
			Lib.enterText(By.cssSelector("input[id='postcode']"), ""+ al.get(9) +"");
			Lib.selectDropDown("21", By.cssSelector("select[id='id_country']"));
			Lib.enterText(By.cssSelector("input[id='phone_mobile']"), ""+ al.get(10) +"");
			Lib.enterText(By.cssSelector("input[id='alias']"), ""+ al.get(11) +"");
			Lib.clickButton(By.cssSelector("button[id='submitAccount']"));

		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);

		}
		return this;
	}
	}

