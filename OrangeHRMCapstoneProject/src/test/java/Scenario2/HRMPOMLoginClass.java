package Scenario2;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Scenario1.HRMUtil;

public class HRMPOMLoginClass {	
	@FindBy(name = "username") private WebElement UserNameField;
	@FindBy(name = "password") private WebElement PasswordField;
	@FindBy(xpath = "//button[@type='submit']") private WebElement LoginButton;	
	public void login(String username, String password) {		
		WebDriverWait Wait = new WebDriverWait(HRMUtil.driver, Duration.ofSeconds(10));
        Wait.until(ExpectedConditions.visibilityOf(UserNameField));		
        UserNameField.clear();
		UserNameField.sendKeys(username);
		PasswordField.clear();
		PasswordField.sendKeys(password);
		LoginButton.click();
	}
}
