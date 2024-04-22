package seleniumtraining.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumtraining.AbstractComponents.AbstractComponents;

// Page design pattern
public class CheckOutPage extends AbstractComponents{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="span[class='ng-star-inserted']")
	WebElement checkoutResult;
	
	@FindBy(css=".actions a")
	WebElement select;
	
	By result = By.cssSelector("span[class='ng-star-inserted']");
	
	
	public void selectCountry(String name)
	{
		Actions action = new Actions(driver);
		action.sendKeys(country, name).build().perform();
		waitForElementToAppear(result);
		action.moveToElement(checkoutResult).click().build().perform();
	}
	
	public ConfirmationPage submitOrder()
	{
		select.click();
		return new ConfirmationPage(driver);
	}
}
	
