package automationpractice.resources;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import automationpractice.libraries.Base;

public class SignUpTestData extends Base {

	final static Logger logger = Logger.getLogger(SignUpTestData.class);


	@DataProvider
	private Object[][] getData() {
		Object[][] data = new Object[3][2];
		data[0][0] = "";
		data[0][1] ="TCID301---> leave email field empty ! Negative test !";	
		
		data[1][0] = "xyz@gmail.com";
		data[1][1] ="TCID302---> using already registrated email ! Negative test !";
		
		data[2][0] = "qa.training.rshrestha00@gmail.com";
		data[2][1] ="TCID303---> using new email ! positive test !";
		
		
		return data;

	}

	
}
