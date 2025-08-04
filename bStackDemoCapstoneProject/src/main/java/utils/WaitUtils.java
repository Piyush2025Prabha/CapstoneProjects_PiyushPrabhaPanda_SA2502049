package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.BaseTest;

public class WaitUtils {	
	public void implicitWait() {
		BaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public static void VisibilityofElement(WebElement element) {
		WebDriverWait Wait = new WebDriverWait(BaseTest.driver,Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.visibilityOf(element));	
	}
	public static void ElementClickable1(WebElement element) {
		WebDriverWait Wait = new WebDriverWait(BaseTest.driver,Duration.ofSeconds(10));
		
		Wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static WebElement VisibilityofLocator(By locator) {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement LocatorClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void ElementInvisibilityByLocator(By locator) {
    	WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
