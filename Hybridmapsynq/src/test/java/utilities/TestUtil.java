package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;

import base.TestBase;

public class TestUtil extends TestBase{
	
	
	
	
	/*******************************************
	 * Screen Capture Method
	 *******************************************/
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException{
		
		// This screenshot will store it to memory, not in any file
		// To store the screenshot we need an utility from Apache - FileUtils.copyFile
		// This utility capable of throwing exception 
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
	}
	
	
	/*******************************************
	 * DataProvider Method
	 *******************************************/
	public static Object[][] getData(String sheetName){
		
		// Get rows count
		int rows = excel.getRowCount(sheetName);
		
		// Get columns count
		int cols = excel.getColumnCount(sheetName);		
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum = 2; rowNum <= rows; rowNum++){
			for(int colNum = 0; colNum < cols; colNum++){
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		return data;
	}
}
