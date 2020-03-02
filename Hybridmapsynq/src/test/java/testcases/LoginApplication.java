package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

/*
 *TC Description : verify correct Error message is getting displayed after providing wrong email and password
 * 
 */

public class LoginApplication extends TestBase {

	@Test(dataProvider="getData")
	public void loginApplication(String userName, String passWord) throws InterruptedException{
		extentTest = extent.startTest("LoginApplication");
		type("username", userName);
		type("password", passWord);
		click("signinlink");
		
		Assert.assertTrue(isElementPresent(OR.getProperty("loggedinUsrName")), "Login not successful");
		Thread.sleep(3000);
	}
	
	@DataProvider
	public static Object[][] getData(){		
		return TestUtil.getData("LoginApplication");
	}

}

