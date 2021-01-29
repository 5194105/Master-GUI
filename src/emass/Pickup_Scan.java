package emass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Pickup_Scan {

	
	public static void pup() throws Exception {
		Actions act= new Actions(Loginemass.driver);
		
		WebElement pupscan = Loginemass.driver.findElement(By.xpath("//*[@id=\"massEntryForm:menuBar\"]/div/div/ul/li[4]/a/span[1]/span"));
		
		act.moveToElement(pupscan).perform();
		
		
		//*[@id="massEntryForm:menuBar"]/div/div/ul/li[4]/ul/li[1]/a/span/span
		Thread.sleep(1000);
		Loginemass.driver.findElement(By.xpath("//*[text()='PUP - Package Pick Up']")).click();
		
		System.out.println("pickup done");
	}
}
