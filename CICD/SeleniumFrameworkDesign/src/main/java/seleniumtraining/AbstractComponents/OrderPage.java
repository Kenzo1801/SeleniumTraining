package seleniumtraining.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyOrderDisplay(String orderName)
	{
		return productNames.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(orderName));
	}
}
