package Base;

import java.nio.file.Paths;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.ConfigReader;
import utils.WebDriverFactory;

public class BaseTest {	
	public static WebDriver driver;
	public static ExtentReports Report;
	public static ExtentTest test;
	static String basePath = System.getProperty("user.dir");	
	public static void launchBrowser(String appUrl) {
		driver = WebDriverFactory.getDriver();
		driver.get(appUrl);
	}
	public static void closeBrowser() {
		WebDriverFactory.quitDriver();	
	}
	public static void scrollDown(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500);");		
	}
	public static void JSClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);	
	}
	@BeforeSuite
	public static ExtentReports extentReport() {
		String reportPath = Paths.get(basePath, ConfigReader.get("report.path")).toString();
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(reportPath);
		Report = new ExtentReports();
		Report.attachReporter(htmlReport);	
		Report.setSystemInfo("Project Name", "Maven bStackDemo Project");		
		htmlReport.config().setDocumentTitle("bStackDemo Automation Report");	
		htmlReport.config().setReportName("TestNG Automation Report");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setTimeStampFormat("MM-DD-YYYY");	
		return Report;		
	}	
	@AfterSuite
    public void flushExtent() {
        if (Report != null) {
            Report.flush();
        }
    }
}
