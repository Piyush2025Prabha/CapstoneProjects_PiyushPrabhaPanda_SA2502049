package Scenario1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
public class TestingLoginFunctionality{
	WebDriverWait Wait;
	SoftAssert softAssert;	
	ExtentReports report;
	ExtentTest test;	
	File file;
	FileInputStream FIS;
	XSSFWorkbook WB;
	XSSFSheet Sheet;
	XSSFRow Row;
	XSSFCell Cell;	
	boolean hasValidLoginOccurred = false;	
  @Test(priority = 1)
  public void LoginFunctionality() throws InterruptedException{ 
	  Wait = new WebDriverWait(HRMUtil.driver, Duration.ofSeconds(10));
	  softAssert = new SoftAssert();	  
	  int totalrow = Sheet.getLastRowNum();	    
	  for(int i=1;i<=totalrow;i++) {
		  Row = Sheet.getRow(i);
		  String UserName = Row.getCell(0).getStringCellValue();
		  String Password = Row.getCell(1).getStringCellValue();		  
		test = report.createTest("Login Test for user: " + UserName);	
		WebElement UserField = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		UserField.clear();
		UserField.sendKeys(UserName);		
		WebElement PasswordField = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		PasswordField.clear();
		PasswordField.sendKeys(Password);	  
		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))).click();		
		Thread.sleep(2000);		
		String screenshotPath = HRMUtil.captureScreenshot(UserName);
        test.addScreenCaptureFromPath(screenshotPath);	
		boolean isInvalid = HRMUtil.driver.findElements(By.xpath(
			    "//p[normalize-space()='Invalid credentials']"
			)).size() > 0;
			if (UserName.equals("Admin") && Password.equals("admin123")) {
				hasValidLoginOccurred = true;
			    if (isInvalid) {
			        test.fail("Unexpected 'Invalid credentials' shown for valid user.");
			    } else {
			        test.pass("Login successful for valid credentials: " + UserName);
			        System.out.println("Test Cases Passed for: " + UserName);			        
			    }
			    softAssert.assertFalse(isInvalid, "Valid credentials should not show 'Invalid credentials' message.");
			} else {
			    if (!isInvalid) {
			        test.fail("No error shown for invalid credentials: " + UserName);
			    } else {
			        test.pass("Error shown correctly for invalid credentials: " + UserName);
			        System.out.println("Test Cases Failed for : " + UserName);
			    }
			    softAssert.assertTrue(isInvalid, "Invalid login should show error message.");
			}	  
		HRMUtil.driver.navigate().refresh(); // reset the form for next login  
	  }	  
	  softAssert.assertAll(); 
  } 
  @Test(priority = 2,dependsOnMethods = "LoginFunctionality")
	  public void LogoutFunctionality() throws InterruptedException{  
	  if(hasValidLoginOccurred) {
		  String PageName = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']"))).getText();
		  System.out.println("The name of the page after logging in is: " + PageName);
		  softAssert.assertEquals(PageName, "Dashboard", "User is unable to login");		  
		  Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-icon.bi-caret-down-fill.oxd-userdropdown-icon"))).click();
		  Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@role='menuitem' and normalize-space(text())='Logout']"))).click();		  
		  System.out.println("User is able to Logout successfully.");
	  }else {
	        System.out.println("Logout is unsuccessful.");
	    }	 
	  softAssert.assertAll();	  
  }
  @BeforeTest
  public void beforeTest() throws IOException{	  
	  HRMUtil.LaunchBrowser();
	  report = HRMUtil.ExtentReport();
	  String testDatapath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\LoginCredentials.xlsx";
	  file = new File(testDatapath);
	  try {
		FIS = new FileInputStream(file);
		WB = new XSSFWorkbook(FIS);
		Sheet = WB.getSheet("Information");		
	} catch (FileNotFoundException e) {
		System.out.println("The exception message is: " + e.getMessage());
	}
  }
  @AfterTest
  public void afterTest() throws IOException {	  
	  WB.close();
	  FIS.close();
	  report.flush();
	  HRMUtil.CloseBrowser();	  
  }
}
