package seleniumtraining.tests;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumtraining.TestComponents.BaseTest;
import seleniumtraining.TestComponents.Retry;
import seleniumtraining.pageobjects.CartPage;
import seleniumtraining.pageobjects.CheckOutPage;
import seleniumtraining.pageobjects.ConfirmationPage;
import seleniumtraining.pageobjects.ProductCatalogPage;

public class Errorvalidation extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {

		ProductCatalogPage productCatalogPage = landingPage.loginApplication("ulrich@gmail.com", "Ulrich&k");
		
		String productName = "ZARA COAT 3";
		productCatalogPage.addProductToCart(productName);	
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException {

		ProductCatalogPage productCatalogPage = landingPage.loginApplication("kameni@gmail.com", "Ulrich&ka1");
		
		String productName = "ZARA COAT 3";
		productCatalogPage.addProductToCart(productName);	
	
		CartPage cartPage = productCatalogPage.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match); 
	}
	
	@Test
	public void checkoutErrorValidation() {
		ProductCatalogPage productCatalogPage = landingPage.loginApplication("ulrich@gmail.com", "Ulrich&ka1");
		
		String productName = "ZARA COAT 3";
		productCatalogPage.addProductToCart(productName);	
	
		CartPage cartPage = productCatalogPage.goToCartPage();
  
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.selectCountry("Came");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		Assert.assertFalse(confirmationPage.messageConfirmed("Thankyou for the order."));
	}
}
