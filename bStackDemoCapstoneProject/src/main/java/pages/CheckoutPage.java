package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ConfigReader;
import utils.WaitUtils;

public class CheckoutPage {
	private By FirstNameLocator = By.id("firstNameInput");
	private By LastNameLocator = By.id("lastNameInput");
	private By AddressLocator = By.id("addressLine1Input");
	private By StateLocator = By.id("provinceInput");
	private By PostalLocator = By.id("postCodeInput");
	private By submitbuttonLocator = By.id("checkout-shipping-continue");
	private By successmessageLocator = By.id("confirmation-message");
	private By orderNumberLocator = By.xpath("//strong[normalize-space(text())]");	
	public void checkoutForm() {
		WebElement firstName = WaitUtils.VisibilityofLocator(FirstNameLocator);
		firstName.sendKeys(ConfigReader.get("firstname"));		
		WebElement lastName = WaitUtils.VisibilityofLocator(LastNameLocator);
		lastName.sendKeys(ConfigReader.get("lastname"));	
		WebElement addressfield = WaitUtils.VisibilityofLocator(AddressLocator);
		addressfield.sendKeys(ConfigReader.get("address"));		
		WebElement statefield = WaitUtils.VisibilityofLocator(StateLocator);
		statefield.sendKeys(ConfigReader.get("state"));		
		WebElement postalfield = WaitUtils.VisibilityofLocator(PostalLocator);
		postalfield.sendKeys(ConfigReader.get("postal"));		
		System.out.println("User is able to fill up the checkout form");
	}
	public void clickOnSubmit() {
		WebElement submitButton = WaitUtils.VisibilityofLocator(submitbuttonLocator);
		submitButton.click();
		System.out.println("User is able to checkout successfully");
	}
	public void successmessageValidation() {
		WebElement successMessage = WaitUtils.VisibilityofLocator(successmessageLocator);
		String Text = successMessage.getText().trim();
		System.out.println("The Order Confirmation Message is: " + Text);
		Assert.assertEquals(Text, "Your Order has been successfully placed.","Your order is not successful");		
		WebElement orderNumber = WaitUtils.VisibilityofLocator(orderNumberLocator);
		String orderNum = orderNumber.getText().trim();
		System.out.println("The order number generated after success is: "  + orderNum);		
	}
}
