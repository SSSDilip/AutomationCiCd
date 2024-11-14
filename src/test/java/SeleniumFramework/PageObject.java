package SeleniumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponent.BaseTest;

public class PageObject extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogue productCatalogue = landingpage.login(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		// Thread.sleep(3000);
		ConfirmationPage confirmationMessage = checkoutPage.submitOrder();
		String confirmMessage = confirmationMessage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistory() {
		// Zara Coat 3
		ProductCatalogue productCatalogue = landingpage.login("sssdilip101@gmail.com", "Dilip@123");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Data\\Purchase.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
		
		
		// HashMap<String,String> map = new HashMap<String, String>();
		// map.put("email","sssdilip101@gmail.com" );
		// map.put("password", "Dilip@123");
		// map.put("product","ZARA COAT 3");

		// HashMap<String,String> map1 = new HashMap<String, String>();
		// map1.put("email","anshika@gmail.com" );
		// map1.put("password", "Iamking@000");
		// map1.put("product","ADIDAS ORIGINAL");
		

		// return new Object[][] {{map},{map1}};
		// return new Object[][] {{"sssdilip101@gmail.com","Dilip@123","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}

}
