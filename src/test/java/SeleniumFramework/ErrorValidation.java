package SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponent.BaseTest;
import TestComponent.Retry;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		landingpage.login("sssdilip101@gmail.com", "Dilip@1231");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		String productName= "ZARA COAT 3";
		ProductCatalogue productCatalogue =landingpage.login("anshika@gmail.com", "Iamking@000");
		List<WebElement> products=productCatalogue.getProductList();
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
	}

}
