package seleniumtraining.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Page design pattern
public class CartPage{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cardProducts;
	
	
	@FindBy(css=".totalRow button")
	WebElement checkout;

	
	public boolean verifyProductDisplay(String productName)
	{
		return cardProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
	}
	
	public CheckOutPage checkOut()
	{
		checkout.click();
		return new CheckOutPage(driver);
	}
	
}
	
