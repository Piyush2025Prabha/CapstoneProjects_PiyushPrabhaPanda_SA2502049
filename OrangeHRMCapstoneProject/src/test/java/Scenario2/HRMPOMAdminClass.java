package Scenario2;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Scenario1.HRMUtil;

public class HRMPOMAdminClass {	
	WebDriverWait Wait = new WebDriverWait(HRMUtil.driver, Duration.ofSeconds(10));	
	@FindBy(xpath = "//ul[contains(@class,'oxd-main-menu')]//li") 
	private List<WebElement> MenuList;	
	@FindBy(xpath = "//ul[contains(@class,'oxd-main-menu')]//li[.//span[text()='Admin']]")
	private WebElement AdminMenu;	
	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement Username;	
	@FindBy(xpath = "//button[@type='submit']") private WebElement Submit;	
	@FindBy(xpath = "//div[@class='oxd-table-card']//div[@role='row']")
	private List<WebElement> RowValue;	
	@FindBy(xpath = "(//div[@class='oxd-select-text--after'] )[1]")
	private WebElement DropDownRole;
	@FindBy(xpath = "//div[@role='listbox']//span[text()='Admin']") private WebElement RoleName;	
	@FindBy(xpath = "(//div[@class='oxd-select-text--after'] )[2]")
	private WebElement DropdownStatus;	
	@FindBy(xpath = "//div[@role='listbox']//span[text()='Enabled']") private WebElement StatusEnabled;	
	@FindBy(xpath = "//div[@role='listbox']//span[text()='Disabled']") private WebElement StatusDisabled;	
	public int getMenuCount() {		
		System.out.println("Menu Options:");
		int i=1;
	    for (WebElement option : MenuList) {
	        String menuText = option.getText().trim();
	        System.out.println(i + ": " + menuText);
	        i++;
	    }
		return MenuList.size();
	}
	public void openAdminPage() {		
		AdminMenu.click();
    }
	public void SearchuserByName(String User) {	
		Wait.until(ExpectedConditions.visibilityOf(Username));
		Username.sendKeys(User);
		Submit.click();
	}
	public void searchByRole(String roleText) throws InterruptedException {		
		Wait.until(ExpectedConditions.visibilityOf(DropDownRole));
		DropDownRole.click();
		Wait.until(ExpectedConditions.visibilityOf(RoleName));
		RoleName.click();
		Submit.click();	
	}
	public void searchByStatusEnabled(String statusText) throws InterruptedException {		
		Wait.until(ExpectedConditions.visibilityOf(DropdownStatus));
		DropdownStatus.click();
		Wait.until(ExpectedConditions.visibilityOf(StatusEnabled));
		StatusEnabled.click();
		Submit.click();			
	}
	public void searchByStatusDisabled(String statusText) throws InterruptedException {		
		Wait.until(ExpectedConditions.visibilityOf(DropdownStatus));
		DropdownStatus.click();
		Wait.until(ExpectedConditions.visibilityOf(StatusDisabled));
		StatusDisabled.click();
		Submit.click();			
	}	
	public int getSearchResultCount() {	
		try{
			Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-card']//div[@role='row']")));
			return RowValue.size();			
		}catch(Exception e){
			System.out.println("No Search Results Found. " + e.getMessage());
			return 0;
		}        
    }
	public void RefreshPage() {
        HRMUtil.driver.navigate().refresh();
    }
}
