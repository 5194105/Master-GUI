package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class classExample {
	WebDriver driver ;
	WebDriverWait wait;
	WebElement element;
	String levelUrl="https://testsso.secure.fedex.com/L3/PRSApps";
	String chromeSetProperty="webdriver.chrome.driver";
	String chromePath="C:\\Users\\theth\\git\\Master-GUI\\drivers\\chromedriver.exe";

	
  @Test
  public void f() {
	  	System.setProperty(chromeSetProperty,chromePath);
	    	driver = new ChromeDriver();  
	    	driver.get(levelUrl);
	    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  		wait = new WebDriverWait( driver,10);
	  		driver.manage().window().maximize();     	
	  		driver.findElement(By.id("username")).sendKeys("477023");
	  		driver.findElement(By.id("password")).sendKeys("477023");
	  		driver.findElement(By.id("submit")).click();
	  		driver.get("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
	  		System.out.println("Get Home Page "+driver.getTitle());
	  		element=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[1]/td[2]/table/tbody/tr/td[1]/font/b"));
	  		Assert.assertEquals(element.getText(), "Re-rating and Invoice Adjustment","Could Not Find Home Page");
	  
  
 
	}
  
}
