package emass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class emassentry {

	public static void location() {
		
		
		
		WebElement loc= Loginemass.driver.findElement(By.id("locationField"));
		loc.sendKeys("WOBA");
		Loginemass.driver.findElement(By.className("primaryButton")).click();
		
		
	}
}
