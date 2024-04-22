package seleniumtraining.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import seleniumtraining.AbstractComponents.AbstractComponents;

// Page design pattern
public class ProductCatalogPage extends AbstractComponents{

	WebDriver driver;
	
	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".mb-3")
	List<WebElement> products; // findElements function will be called because of declaring the List
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cardProducts;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	
	
	By productsBy = By.cssSelector(".mb-3");
	By addtoCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessageBy = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addtoCart).click();
		waitForElementToAppear(toastMessageBy);
		waitForElementToDisappear(spinner);
	}	
}
	