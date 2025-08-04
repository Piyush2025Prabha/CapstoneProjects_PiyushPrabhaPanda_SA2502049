package tests;

import org.testng.annotations.Test;
import Base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;
import utils.ConfigReader;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
public class CheckoutTest extends BaseTest {
	static ProductPage productPage;
	static CartPage cartPage;
	static CheckoutPage checkoutPage;
  @Test(priority = 7)
  public void checkOutWithNoItems() {
	  test = Report.createTest("Check out with no items on cart");
	  cartPage.getCartItemCount();
	  cartPage.assertCartQuantity("0");
	  cartPage.continueShopping();
	  productPage.verifyProductPage();
	  test.pass("Checkout with empty cart handled successfully.");
  }
  @Test(priority = 8)
  public void properCheckOut() throws InterruptedException {
	  test = Report.createTest("Check out with single item on cart");
	  String product = ConfigReader.get("Product8");
	  productPage.addProductforCheckout(product);
	  cartPage.checkoutClick();
	  checkoutPage.checkoutForm();
	  checkoutPage.clickOnSubmit();
	  checkoutPage.successmessageValidation();
	  test.pass("Proper checkout flow completed and validated.");
  }
  @BeforeTest
  public void beforeTest() {
	  productPage = PageFactory.initElements(BaseTest.driver, ProductPage.class);
	  cartPage = PageFactory.initElements(BaseTest.driver, CartPage.class);
	  checkoutPage = PageFactory.initElements(BaseTest.driver, CheckoutPage.class);
  }
  @AfterTest
  public void afterTest() {
	  BaseTest.closeBrowser();
  }
}
