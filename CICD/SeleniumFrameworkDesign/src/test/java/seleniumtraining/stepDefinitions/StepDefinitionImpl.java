package seleniumtraining.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumtraining.TestComponents.BaseTest;
import seleniumtraining.pageobjects.CartPage;
import seleniumtraining.pageobjects.CheckOutPage;
import seleniumtraining.pageobjects.ConfirmationPage;
import seleniumtraining.pageobjects.LandingPage;
import seleniumtraining.pageobjects.ProductCatalogPage;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogPage productCatalogPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on eCommerce Page")
	public void i_landed_on_eCommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) 
	{
		productCatalogPage = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) 
	{
		//List<WebElement> products = productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogPage.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match); 
		
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.selectCountry("Came");
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmationpage(String string) 
	{
		Assert.assertTrue(confirmationPage.messageConfirmed(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void some_message_is_displayed(String strArg1) throws Throwable 
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
