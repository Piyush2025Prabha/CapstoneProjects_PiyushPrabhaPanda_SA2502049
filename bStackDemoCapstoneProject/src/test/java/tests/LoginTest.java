package tests;

import org.testng.annotations.Test;
import Base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
public class LoginTest extends BaseTest {
	static LoginPage LoginPage;
  @Test(priority = 1)
  public void testLoginWithNoCredentials(){
	  test = Report.createTest("Login with no credential");
	  LoginPage.signin();
	  LoginPage.loginwithNoData();
	  test.pass("Login Fails");
	  LoginPage.pageRefresh();
  }  
  @Test(priority = 2)
  public void testLoginWithInvalidCredentials(){
	  test = Report.createTest("Login with invalid credentials");
	  LoginPage.loginwithinvalidData();
	  test.pass("Login Fails");
	  LoginPage.pageRefresh();		  
  } 
  @Test(priority = 3)
  public void testLoginWithValidCredentials(){
	  test = Report.createTest("Login with valid credentials");
	  LoginPage.loginwithValidData();
	  test.pass("Login Successful");
  } 
  @BeforeTest
  public void beforeTest() {
	  String ApplicationURL = ConfigReader.getProperty("AppURL");
	  BaseTest.launchBrowser(ApplicationURL);
	  LoginPage = PageFactory.initElements(BaseTest.driver, LoginPage.class);
  }
  @AfterTest
  public void afterTest() {
  }
}
