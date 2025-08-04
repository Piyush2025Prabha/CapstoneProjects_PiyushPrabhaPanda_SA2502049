package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Base.BaseTest;
import utils.WaitUtils;

public class ProductPage {
	CartPage cartPage = new CartPage();
	private By getProductLocator(String productName) {
		String xpath = "//p[text()='" + productName + "']/ancestor::div[contains(@class,'shelf-item')]//div[text()='Add to cart']";
		return By.xpath(xpath);
	}
	public void addProductToCartByName(String productName) {
		By locator = getProductLocator(productName);
		WebElement product = WaitUtils.VisibilityofLocator(locator);
		BaseTest.scrollDown(product);
		product = WaitUtils.LocatorClickable(locator);
		BaseTest.JSClick(product);
	}
	public void addMultipleProductsToCart(String[] products) {
		for (String product : products) {
			addProductToCartByName(product);
			cartPage.closeCart();
		}		
	}
	public void addProductforCheckout(String productName) {
		addProductToCartByName(productName);
		System.out.println("Added product for the checkout process");		
	}
	public void verifyProductPage() {
		By totalProduct = By.xpath("//small[@class='products-found']");
		WebElement totalProducts = WaitUtils.VisibilityofLocator(totalProduct);
		String count = totalProducts.getText().split(" ")[0];
		System.out.println("Total products found: " + count);
		System.out.println("User remains in product selection page");
	}    
}
