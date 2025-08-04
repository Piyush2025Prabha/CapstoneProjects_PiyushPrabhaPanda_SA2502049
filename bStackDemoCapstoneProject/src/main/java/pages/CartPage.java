package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Base.BaseTest;
import utils.WaitUtils;

public class CartPage {	
    private By quantityLocator = By.xpath("//span[@class='bag__quantity']");
    private By removeItemLocator = By.xpath("//div[@class='shelf-item__del']"); 
    private By cartOpenLocator = By.cssSelector(".bag.bag--float-cart-closed");
    private By closebuttonLocator = By.xpath("//div[@class='float-cart__close-btn']");
    private By continueShoppingBTNLocator = By.xpath("//div[@class='buy-btn']");
    private By checkoutButtonLocator = By.cssSelector(".buy-btn");
	public void removeItemsFromCart() {
		WaitUtils.LocatorClickable(removeItemLocator);
		List<WebElement> removeButtons = BaseTest.driver.findElements(removeItemLocator);
		for(WebElement removeBTN : removeButtons) {
			WaitUtils.ElementClickable1(removeBTN);
			removeBTN.click();
		}
	}
	public int getCartItemCount() {
		WebElement quantityElement = WaitUtils.VisibilityofLocator(quantityLocator);
        String quantity = quantityElement.getText().trim();
        System.out.println("The total number of items in the cart: " + quantity);
		return Integer.parseInt(quantity);
	}
	public void openCart() {
		WebElement openCartButton = WaitUtils.VisibilityofLocator(cartOpenLocator);
		openCartButton.click();	
		System.out.println("User is able to open cart");
	}
	public void closeCart() {	
		WebElement closebutton = WaitUtils.VisibilityofLocator(closebuttonLocator);
		BaseTest.JSClick(closebutton);		
		WaitUtils.ElementInvisibilityByLocator(closebuttonLocator);	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void assertCartQuantity(String expected) {
        WebElement quantity = WaitUtils.VisibilityofLocator(quantityLocator);
        Assert.assertEquals(quantity.getText().trim(), expected, "Cart quantity mismatch!");
    }
	public void continueShopping() {
		WebElement shoppingButton = WaitUtils.VisibilityofLocator(continueShoppingBTNLocator);
		shoppingButton.click();	
	}
	public void checkoutClick() {
		WebElement ckbtn = WaitUtils.VisibilityofLocator(checkoutButtonLocator);
		String text = ckbtn.getText();
		System.out.println("Button name: " + text.trim());
		Assert.assertEquals(text, "CHECKOUT","This is not a checkout button");
		ckbtn.click();
		System.out.println("User able to click on checkout");
	}
}
