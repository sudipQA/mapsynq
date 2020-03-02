package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

/*
 *TC Description : verify user is able to login successfully after providing email address and password
 * 
 */

public class Loginverification extends TestBase{

	@Test(dataProvider="getData")
		public void loginverification(String UserNm ,String Passwrd ) throws InterruptedException{
		extentTest = extent.startTest("Loginverification");
	
		click("signin");

		type("userNm",UserNm);

		type("passwrd",Passwrd);

		click("singinbt");

		String loginerrmesg = driver.findElement(By.xpath("//p[@id='notice']")).getText();

		if(loginerrmesg.equals("Invalid user/password combination")){
			log.debug("User has entered invalid user/password combination");
		}else {
			log.debug("Successfully login to application");

		}
		
	}
		
		@DataProvider
		public static Object[][] getData(){		
			return TestUtil.getData("Loginverification");
		}

}
