package Scenario2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Scenario1.HRMUtil;
public class LoginHRMUsingPOM {	
	static HRMPOMLoginClass LoginPage;
	static HRMPOMAdminClass AdminPage;
	static WebDriverWait Wait;	
	@Test(priority = 1)
	public static void LoginFunctionality(){		
		Wait = new WebDriverWait(HRMUtil.driver, Duration.ofSeconds(10));		
		LoginPage.login("Admin", "admin123");		
		System.out.println("User is able to Login");		
		String PageName = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']"))).getText();
		System.out.println("The name of the page after logging in is: " + PageName);
		Assert.assertEquals(PageName, "Dashboard", "User is unable to login");
	}
	@Test(priority = 2)
	public static void ValidateAdminMenuAndOpenAdmin() {		
		int count = AdminPage.getMenuCount();
		System.out.println("The Total number of options in Menu is: " + count);
		Assert.assertEquals(count, 12, "Menu option count mismatch!");		
		AdminPage.openAdminPage();		
	}
	@Test(priority = 3)
    public void searchByUsername() {		
		AdminPage.SearchuserByName("Admin");
		int Value = AdminPage.getSearchResultCount();
		System.out.println("The Total result found for Username 'Admin' is: " + Value);
		AdminPage.RefreshPage();		
	}	
	@Test(priority = 4)
    public void searchByUserRole() throws InterruptedException {		
		AdminPage.searchByRole("Admin");
		int value = AdminPage.getSearchResultCount();
		System.out.println("The Total result found for Role 'Admin' is: " + value);
		AdminPage.RefreshPage();		
	}	
	@Test(priority = 5)
    public void searchByStatus() throws InterruptedException {		
		AdminPage.searchByStatusEnabled("Enabled");
		int value = AdminPage.getSearchResultCount();
		System.out.println("The Total result found for Status 'Enabled' is: " + value);
		AdminPage.RefreshPage();		
		AdminPage.searchByStatusDisabled("Disabled");
		int value1 = AdminPage.getSearchResultCount();
		System.out.println("The Total result found for Status 'Disabled' is: " + value1);
		AdminPage.RefreshPage();		
	}	
	@BeforeTest
	public static void BeforeTest() {		
		HRMUtil.LaunchBrowser();
		LoginPage = PageFactory.initElements(HRMUtil.driver,HRMPOMLoginClass.class);
		AdminPage = PageFactory.initElements(HRMUtil.driver, HRMPOMAdminClass.class);		
	}
	@AfterTest
    public static void afterTest() {
        HRMUtil.CloseBrowser();
    }
}
