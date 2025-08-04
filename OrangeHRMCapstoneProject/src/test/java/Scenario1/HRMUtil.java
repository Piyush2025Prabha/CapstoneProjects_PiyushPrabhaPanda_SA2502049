package Scenario1;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HRMUtil {
	public static WebDriver driver;
	static ExtentReports Report;	
	public static void LaunchBrowser(){		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");		
	}	
	public static void CloseBrowser() {		
		driver.quit();	
	}	
	public static String captureScreenshot(String UserName){		
		Date date = new Date();
		String FileName = "Login_" + UserName + "_" + date.toString().replace(":", "_").replace(" ", "_");
		String NewFileName = FileName + ".png";
		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + NewFileName;		
		try {
			Thread.sleep(1000);
			File Source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(Source, new File(screenshotPath));		
		} catch (IOException e) {	
			System.out.println(e.getStackTrace());
		} catch (Exception e) {	
			e.printStackTrace();
		}	
		return screenshotPath;
	}	
	public static ExtentReports ExtentReport() {
		String reportPath = System.getProperty("user.dir") + "/Report" +"/HRMAutomationReport.html";
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(reportPath);
		Report = new ExtentReports();
		Report.attachReporter(htmlReport);	
		Report.setSystemInfo("Project Name", "Maven HRM Automation Project");		
		htmlReport.config().setDocumentTitle("OrangeHRM Automation Report");	
		htmlReport.config().setReportName("TestNG Automation Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setTimeStampFormat("MM-DD-YYYY");	
		return Report;		
	}
}
