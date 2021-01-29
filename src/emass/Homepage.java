package emass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Homepage {

	
	public static void scan() {
		
		
		WebElement scanButton = Loginemass.driver.findElement(By.id("j_id15:j_id22:0:j_id25:3:j_id27:j_id28"));
//		Actions act = new Actions(Loginemass.driver);
//		act.moveToElement(scanButton);
	
		scanButton.click();
		Loginemass.driver.findElement(By.xpath("//*[@id=\"j_id15:j_id22:0:j_id25:3:j_id27:j_id28:j_id29:link_351:link\"]/span")).click();;
		
		
	}
}
