package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.TestBase;
import listeners.CustomListeners;
import utilities.TestUtil;
@Listeners(CustomListeners.class)

/*
 *TC Description : verify user is able to reset the password after providing correct email address in Password reset page
 * 
 */

public class Forgotpassword extends TestBase{

	
	
	
	@Test(dataProvider="getData")
		public void forgotpassword(String Emailtxtbx) throws InterruptedException{
	extentTest = extent.startTest("forgotpassword");

		click("signin");

		click("restpwdlink");

		type("emailtxtbx",Emailtxtbx);

		click("restbtn");

		String invaildmail = driver.findElement(By.xpath("//p[@id='error']")).getText();
	     
		if(invaildmail.equals("Invalid email address")){
			
			log.debug("User has entered invalid email address");
		 }else {
			log.debug("Recovery password email has sent successfully");
			
		}
	
			
	}
		
		@DataProvider
		public static Object[][] getData(){		
			return TestUtil.getData("Forgotpassword");
		}

}
