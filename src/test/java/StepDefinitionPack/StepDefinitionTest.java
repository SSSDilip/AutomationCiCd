package StepDefinitionPack;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework.CartPage;
import SeleniumFramework.CheckoutPage;
import SeleniumFramework.ConfirmationPage;
import SeleniumFramework.LandingPage;
import SeleniumFramework.ProductCatalogue;
import TestComponent.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionTest extends BaseTest{
	
	
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationMessage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingpage = launchApplication();
		 
	}
	
	@Given("^I Logged in with userName (.+) and password (.+)$")
	public  void logged_in_username_and_password(String userName, String password) {
		productCatalogue = landingpage.login(userName, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
	
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	//And Checkout <productName> and submit the order
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		// Thread.sleep(3000);
		confirmationMessage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on the ConfirmationPage")
	public void message_displayed_confirmationPage(String string){
		String confirmMessage = confirmationMessage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed")
	public void something_message_is_displayed(String string1) throws Throwable {
		Assert.assertEquals(string1, landingpage.getErrorMessage());
		driver.close();
	}
	
}
