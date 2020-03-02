package testcases;

import static org.testng.Assert.assertEquals;

/*
 *TC Description : verify On Map popup message is getting displayed after click on Live Incident Report
 * 
 */

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class Locateliveincidentmap extends TestBase {
	

	@Test(dataProvider="getData")
	public void locateliveincidentmap(String Noindicator) throws InterruptedException{
		extentTest = extent.startTest("Locateliveincidentmap");
		
		List<WebElement> kj=  driver.findElements(By.xpath(OR.getProperty("liveincident")));
		
        for(int k=0;k<kj.size();k++){

        String varincidentNm = kj.get(k).getText();

        kj.get(k).click();

        WebElement varpopup = driver.findElement(By.xpath(OR.getProperty("popupincidet")));
        String popupNm = varpopup.getText();

        boolean dd = popupNm.contains(varincidentNm);
   
        if(dd = true){
        	
        	log.debug("On Map popup message "+varincidentNm + " is getting displayed");

        }else {
        	log.debug("On Map popup message "+varincidentNm + " is not getting displayed");
        }

        }
        //Assert.assertEquals("totle", "kk");
       
	}
	
	@DataProvider
	public static Object[][] getData(){		
		return TestUtil.getData("Locateliveincidentmap");
	}

}

