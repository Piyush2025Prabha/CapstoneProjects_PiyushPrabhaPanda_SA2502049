package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigReader;
import utils.WaitUtils;
import org.testng.Assert;
import Base.BaseTest;

public class LoginPage {
	String expectedMsg = "Invalid Username";	
	@FindBy (id = "signin") private WebElement signInButton;
	@FindBy (id = "react-select-2-input") private WebElement userName;
	@FindBy (id = "react-select-3-input") private WebElement passWord;
	@FindBy (id = "login-btn") private WebElement loginButton;
	@FindBy (xpath = "//h3[@class='api-error']") private WebElement validationMessage;
	@FindBy (id = "signin") private WebElement logoutButton;	
	public void signin() {
		WaitUtils.VisibilityofElement(signInButton);
		signInButton.click();
	}
	public void loginwithNoData() {
		WaitUtils.VisibilityofElement(userName);
		loginButton.click();
		WaitUtils.VisibilityofElement(validationMessage);
		String Vmsg = validationMessage.getText();
		Assert.assertEquals(Vmsg.trim(), expectedMsg, "Validation message mismatch!");	
	}
	public void loginwithinvalidData() {
		WaitUtils.VisibilityofElement(userName);
		userName.clear();
		userName.sendKeys(ConfigReader.getProperty("Invalid_Username"));
		userName.sendKeys(Keys.ENTER);
		passWord.clear();
		passWord.sendKeys(ConfigReader.getProperty("Invalid_Password"));
		passWord.sendKeys(Keys.TAB);
		loginButton.click();
		WaitUtils.VisibilityofElement(validationMessage);
		String Vmsg = validationMessage.getText();
		Assert.assertEquals(Vmsg.trim(), expectedMsg, "Validation message mismatch!");	
	}	
	public void loginwithValidData(){
		WaitUtils.VisibilityofElement(userName);
		userName.clear();
		userName.sendKeys(ConfigReader.getProperty("Login_Username"));
		userName.sendKeys(Keys.ENTER);
		passWord.clear();
		passWord.sendKeys(ConfigReader.getProperty("Login_Password"));
		passWord.sendKeys(Keys.ENTER);
		loginButton.click();
	}
	public void pageRefresh() {
		BaseTest.driver.navigate().refresh();
	}
	public void logOut() {
		WaitUtils.VisibilityofElement(logoutButton);
		logoutButton.click();	
	}	
}
