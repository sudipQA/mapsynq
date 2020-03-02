package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

/*
 *TC Description : verify user is able to register successfully
 */

public class Registrationform extends TestBase{

	@Test(dataProvider="getData")
		public void registrationform(String FirstNm,String LastNm,String Country ,String Address,String Contact,String Gender,String	Dateofbirth,String Email,String	Username,String	Password,String	ConfirmPaswd,String SubtoNewsletter,String Iagreetotermscond) throws InterruptedException{
		extentTest = extent.startTest("Registrationform");

			click("RegisterLink");
			Thread.sleep(8000);
			type("FirstNm",FirstNm);
			type("LastNm",LastNm);
			select("Country",Country);
			type("Address",Address);
			type("Contact",Contact);
			type("Dateofbirth",Dateofbirth);
			radiobt("Gender",Gender);
			type("Email",Email);
			type("Username",Username);
			type("Password",Password);
			type("ConfirmPaswd",ConfirmPaswd);
			checkbx("SubtoNewsletter",SubtoNewsletter);
			checkbx("Iagreetotermscond",Iagreetotermscond);
			click("CreateProfile");
						
			
			String registrationerr = driver.findElement(By.xpath("//div[@class='error_signup']]")).getText();

			if(registrationerr.equals("Incorrect word verification! please try again...")){
				log.debug("User is not able to register due to error");
			}else {
				log.debug("Successfully Register to application");

			}
			
		}
		
		@DataProvider
		public static Object[][] getData(){		
			return TestUtil.getData("Registrationform");
		}

}
