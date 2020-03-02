package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import utilities.ExcelReader;
import utilities.MonitoringMail;

public class TestBase {
	
	/****************************************************
	 * We will be initializing in this TestBase class:
	 * WebDriver
	 * Properties
	 * Logs
	 * Mails
	 * DB Connection
	 * Excel Reading
	 *****************************************************/
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static MonitoringMail mail = new MonitoringMail();
	public static FileInputStream fis;	
	public static WebDriverWait wait;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	@BeforeSuite
	public void setUp(){
		if(driver==null){
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Config.load(fis);
				log.debug("Config properties file loaded.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(driver==null){
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				OR.load(fis);
				log.debug("OR properties file loaded.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		if(Config.getProperty("browser").equals("firefox")){
			driver = new FirefoxDriver();
			log.debug("Launching Firefox");
		}else if(Config.getProperty("browser").equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Launching Chrome");
		}else if(Config.getProperty("browser").equals("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.debug("Launching IE");
		}
	}
	
		
		
		
		
		
		
	
	
	/*****************************************************************************************
	 * Custom isElementPresent method, return true/false if locator element is present/absent 
	******************************************************************************************/
	public static boolean isElementPresent(String locator){
		try{
			driver.findElement(By.cssSelector(locator));
			return true;
		}catch(Throwable t){
			return false;
		}
	}
	
	/*******************************
	 * Create custom Keyword - Click
	*********************************/
	public void click(String locator){
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		

	}
	
	/***********************************************
	 * Create custom Keyword - Type <String, String>
	************************************************/
	public void type(String locator, String value){
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
	}
	
	/***********************************************
	 * Create custom Keyword - Type <String, Keys>
	************************************************/
	public void type(String locator, Keys value){
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
	}
	
	/********************************
	 * Create custom Keyword - Select
	**********************************/
	public void select(String locator, String value){
		WebElement dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	}
	
	/********************************
	 * Create custom Keyword - radio button
	**********************************/
	public void radiobt(String locator, String value){
	List<WebElement> radiobuttons = driver.findElements(By.xpath(OR.getProperty(locator)));
		for(int k = 0;k<radiobuttons.size();k++){
			 String radioBtNm = radiobuttons.get(k).getAttribute("value");
			 if(radioBtNm.equals(value)){
				 radiobuttons.get(k).click();
				 break;
			 }
			}
		}
	
	/********************************
	 * Create custom Keyword - checkbox
	**********************************/
	public void checkbx(String locator,String value){
		WebElement objcheckbox = driver.findElement(By.xpath(locator));
		
		if(value.equals("Y")){
			
			objcheckbox.click();	
		}
				
	}
	
	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", false);
		extent.addSystemInfo("Environment", "QA");
	}
  @BeforeMethod	
  
  /**********************************************************************************
	 * Navigate to URL, Maximize the browser window and set Implicit and Explicit wait
	***********************************************************************************/
  public void login() {
	driver.get(Config.getProperty("testsiteurl"));
	log.debug("Navigating to: " + Config.getProperty("testsiteurl"));
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
	wait = new WebDriverWait(driver,Integer.parseInt(Config.getProperty("explicit.wait")));
  }
			
@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestBase.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
	
		
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	@AfterSuite
	public void endreport() {
		extent.flush();
		extent.close();
		extent.endTest(extentTest); 
		driver.quit();
	}
	
	public void failed() {
		
	}
	public void tearDown(){
		
		log.debug("Test Execution Completed!!!");
		
	}
	}
