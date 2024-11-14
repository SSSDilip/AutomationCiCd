package SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName= "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		JavascriptExecutor js= (JavascriptExecutor) driver;
		driver.findElement(By.id("userEmail")).sendKeys("sssdilip101@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dilip@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> products =driver.findElements(By.cssSelector("div[class*='mb-3']"));
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
		
		
		//for visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//for disappear
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,500)");
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match= cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//driver.findElement(By.xpath("//li/button[.='Checkout']")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

}
