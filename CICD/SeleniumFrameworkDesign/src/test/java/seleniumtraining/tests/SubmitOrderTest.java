package seleniumtraining.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumtraining.AbstractComponents.OrderPage;
import seleniumtraining.TestComponents.BaseTest;
import seleniumtraining.pageobjects.CartPage;
import seleniumtraining.pageobjects.CheckOutPage;
import seleniumtraining.pageobjects.ConfirmationPage;
import seleniumtraining.pageobjects.LandingPage;
import seleniumtraining.pageobjects.ProductCatalogPage;

public class SubmitOrderTest extends BaseTest {
//	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(Map<String, String> input) throws IOException {

		ProductCatalogPage productCatalogPage = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		productCatalogPage.addProductToCart(input.get("product"));	
	
		CartPage cartPage = productCatalogPage.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match); 
		  
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.selectCountry("Came");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		Assert.assertTrue(confirmationPage.messageConfirmed("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
	public void OrderHistorytest(String email, String password, String productName)
	{
		ProductCatalogPage productCatalogPage = landingPage.loginApplication(email, password);
		OrderPage orderPage = productCatalogPage.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<Object, Object> map = new HashMap<Object, Object>();
//		map.put("email", "ulrich@gmail.com");
//		map.put("password", "Ulrich&ka1");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
//		map1.put("email", "kameni@gmail.com");
//		map1.put("password", "Ulrich&ka1");
//		map1.put("product", "ADIDAS ORIGINAL");
//		return new Object[][] {{map}, {map1}};	
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\seleniumtraining\\data\\Purchase.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};

//		return new Object[][] {{"ulrich@gmail.com","Ulrich&ka1", "ZARA COAT 3"}, {"kameni@gmail.com","Ulrich&ka1", "ADIDAS ORIGINAL"}};	
	}
			
}
