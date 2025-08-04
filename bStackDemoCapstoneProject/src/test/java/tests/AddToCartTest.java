package tests;

import org.testng.annotations.Test;
import Base.BaseTest;
import pages.CartPage;
import pages.ProductPage;
import utils.ConfigReader;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
public class AddToCartTest extends BaseTest{
	static CartPage cartPage;
	static ProductPage productPage;
  @Test(priority = 4)
  public void addSingleItemToCart() throws InterruptedException {
	  test = Report.createTest("Add Single Item");
	  String product = ConfigReader.get("Product1");
	  productPage.addProductToCartByName(product);
	  cartPage.getCartItemCount();
	  cartPage.assertCartQuantity("1");
	  test.pass("Single item added to cart successfully");
	  cartPage.closeCart();
	  System.out.println("Single item added to cart.");
  }
  @Test(priority = 5)
  public static void addMultipleItemsToCart(){
	  test = Report.createTest("Add Multiple Items");
	  String[] products = {
				ConfigReader.get("Product2"),
				ConfigReader.get("Product3"),
				ConfigReader.get("Product4")
			};
	  productPage.addMultipleProductsToCart(products);
	  cartPage.getCartItemCount();
	  cartPage.assertCartQuantity("4");
	  test.pass("Multiple items added to cart successfully");
	  System.out.println("Multiple items Added to Cart");
  }
  @Test(priority = 6)
  public void removeItemsFromCart() {
	  test = Report.createTest("Remove Items");
	  cartPage.openCart();
	  cartPage.removeItemsFromCart();
	  cartPage.getCartItemCount();
	  cartPage.assertCartQuantity("0");
	  test.pass("Multiple items Removed from cart successfully");
	  System.out.println("All items Removed from Cart");
  }
  @BeforeTest
  public void beforeTest() {
	  cartPage = PageFactory.initElements(BaseTest.driver, CartPage.class);  
	  productPage = PageFactory.initElements(BaseTest.driver, ProductPage.class);
  }
  @AfterTest
  public void afterTest() {
  }
}
