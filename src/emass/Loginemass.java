package emass;

import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Loginemass {
	static WebDriver driver;
	
	public static void browser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\3715501\\Desktop\\fresh_start\\AutoEmass\\src\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://test.secure.fedex.com/L3/eShipmentGUI/MenuPage.iface");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	
	}
	
	public static void Credentials() {
		
		String id ="3715501";
		WebElement username =driver.findElement(By.id("username"));
		username.sendKeys(id);
		
		WebElement pw =driver.findElement(By.id("password"));
		pw.sendKeys(id);
		
		driver.findElement(By.id("submit")).click();
	
		
	}
	
	
	
	
	
}
