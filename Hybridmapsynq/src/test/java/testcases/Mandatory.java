package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

/*
 *TC Description : verify  Error message Error Message - Please enter route Start and Destination. is getting displayed properly is getting displayed when user doesnot provide 
 * data on source and destination field
 */

public class Mandatory extends TestBase{

	@Test(dataProvider="getData")
		public void mandatory(String Errormsg) throws InterruptedException{
		extentTest = extent.startTest("Mandatory");

		click("directiontab");
		Thread.sleep(2000);
		click("getdirection");
		Alert alert = driver.switchTo().alert();
		String acterrmeg = alert.getText();
		if(acterrmeg.equals(Errormsg)){
			
				log.info("Error Message - Please enter route Start and Destination. is getting displayed properly");
		}
			else {
				
				log.info("Error Message - Please enter route Start and Destination. is not getting displayed properly");
		
		}
		alert.accept();	
			
			
		}
		
		@DataProvider
		public static Object[][] getData(){		
			return TestUtil.getData("Mandatory");
		}

}
