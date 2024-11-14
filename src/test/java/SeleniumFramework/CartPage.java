package SeleniumFramework;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractConvention.AbstrctConvention;

public class CartPage extends AbstrctConvention{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProduct;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match= cartProduct.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckout() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		checkout.click();
		return new CheckoutPage(driver);
	}

}
