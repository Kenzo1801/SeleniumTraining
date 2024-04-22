package seleniumtraining.tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumtraining.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		// To use selectorhubs plugin
		options.addExtensions(
				new File("C:\\Users\\ulric\\Downloads\\SelectorHubs\\NDGIMIBANHLABGDGJCPBBNDIEHLJCPFH_5_1_7_0.crx"));
		WebDriverManager.chromedriver().setup(); 
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		String productName = "ZARA COAT 3";

		driver.findElement(By.id("userEmail")).sendKeys("ulrich@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ulrich&ka1");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product -> 
			product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cardProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Assert.assertTrue(cardProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName)));
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"Came").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='ng-star-inserted']")));
		action.moveToElement(driver.findElement(By.cssSelector("span[class='ng-star-inserted']"))).click().build().perform();
		driver.findElement(By.cssSelector(".actions a")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().trim().equalsIgnoreCase("Thankyou for the order."));
		
	}
}
