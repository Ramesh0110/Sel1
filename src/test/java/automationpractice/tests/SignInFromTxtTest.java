package automationpractice.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import automationpractice.Pages.SignInLinkPage;
import automationpractice.Pages.SignInPage;
import automationpractice.libraries.Base;

public class SignInFromTxtTest extends Base {
	final static Logger logger = Logger.getLogger(SignInFromTxtTest.class);

	//@Test(enabled=false)
	
//	public void fileRead() throws IOException {
//
//	  int Count = 0;
//		int iteration = 0;
//		String line;
//		FileReader file = new FileReader("src/test/resources/data.txt");
//		BufferedReader br = new BufferedReader(file);
//		while ((line = br.readLine()) != null) {
//			Count = Count + 1;
//			if (Count > 1) {
//				iteration = iteration + 1;
//				String[] inputData = line.split(", ", 2);
//				driver.get(myUrl);
//				SignInLinkPage sn = new SignInLinkPage();
//				sn.justNavigateToSignIn();
//				SignInPage si = new SignInPage();
//
//				si.userEmailElm().sendKeys(inputData[0]);
//				si.passWordElm().sendKeys(inputData[1]);
//
//				si.submitElm().click();
//				Lib.customWait(2);
//			}
//			Count++;
//
//		}
//		br.close();
//		file.close();                
//	}

}
