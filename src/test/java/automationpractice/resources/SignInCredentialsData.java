package automationpractice.resources;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import automationpractice.libraries.Base;

public class SignInCredentialsData extends Base {

	final static Logger logger = Logger.getLogger(SignInCredentialsData.class);


	@DataProvider
	public static Object[][] getDataContent() {

		Object[][] data = new Object[6][3];
		                         // Object[how many set data][size]

		data[0][0] = "qa.training.sh@gmail.com";
		data[0][1] = "xyzBBC";
		data[0][2] ="TCID401-->Sign in using valid email and wrong password";
		
		data[1][0] = "r@gmail.com";
		data[1][1] = "Abc123";
		data[1][2] ="TCID402-->Sign in using invalid email and valid password ";
		
		data[2][0] = "xyz@gmail.com";
		data[2][1] = "xyzabc123";
		data[2][2] ="TCID403-->Sign in using invalid email and invalid password";
		
		data[3][0] = "";
		data[3][1] = "Abc123";
		data[3][2] ="TCID404-->Sign in leaving blank email and valid password ";
		
		data[4][0] = "qa.training.sh@gmail.com";		
		data[4][1] = "";
		data[4][2] ="TCID405-->Sign in using valid email and leaving password field blank ";
		
		data[5][0] = "qa.training.sh@gmail.com";
		data[5][1] = "Abc123";
		data[5][2] ="TCID406-->Sign In using valid email and valid password  ";

		return data;

	}

	

}
