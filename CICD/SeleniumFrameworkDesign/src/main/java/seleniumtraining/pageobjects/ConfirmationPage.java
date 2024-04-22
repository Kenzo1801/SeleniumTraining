package seleniumtraining.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


// Page design pattern
public class ConfirmationPage{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmation;
	
	public boolean messageConfirmed(String message) 
	{
		return confirmation.getText().trim().equalsIgnoreCase("Thankyou for the order.");
	}
	
}
	
