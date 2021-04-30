package ThreadGFBO;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class gfboThread extends Thread{
	
	
	ArrayList<data> dataArray;
	config c;
	String homePath;
	WebDriver driver;
	String browser;
	String levelUrl;
	String headless;
	String databaseDisabled;
	WebDriverWait wait;
	String source;
	String result, svcType,descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType , company , prerate,workable, rowNumber,length,width,height,actualWeight;
	int waitTime;
	int attempts=0;
	int maxAttempts;
	String level;
	driverClass dc;
	validateClass vc;
	
	String  gfboUsername, gfboPassword, gfboPaymentLevel, gfboPaymentType, gfboAccount, gfboExpectedResult;
	
	
	public gfboThread(ArrayList<data> dataArray,config c) {
		System.out.println(c.getGfboSecondTimeout());
		System.out.println(Integer.parseInt(c.getGfboSecondTimeout()));
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getGfboUrl();
		databaseDisabled=c.getDatabaseDisabled();
		waitTime=Integer.parseInt(c.getGfboSecondTimeout());
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"gfbo");
		
		
	}
	
public void run () {
		
		for(data d: dataArray) {
			
			
			
			//Declare Vars
			result=d.getResult();
			descripiton=d.getDescription(); 
			testInputNbr=d.getTestInputNbr();
			gfboUsername=d.getGfboUsername();
			gfboPassword=d.getGfboPassword();
			gfboPaymentLevel=d.getGfboPaymentLevel();
			gfboPaymentType=d.getGfboPaymentType();
			gfboAccount=d.getGfboAccount();
			gfboExpectedResult=d.getGfboExpectedResult();
			
			//Check if track is already successful
			
		    /*
		    if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
		    	continue;
		    }
		    */
		    
		
			doGFBO(testInputNbr,gfboUsername, gfboPassword, gfboPaymentLevel, gfboPaymentType, gfboAccount, gfboExpectedResult);		
		}
	}


public void login() {
	
	//Will See if even need to login.. if we can navigate to home page then skips.
	
	try {
		try { 
    		driver.quit();
    		driver.close();
	  }
	  catch(Exception e) {
		  System.out.println(e);
		  
	  }
		driver=dc.getDriver();
	    driver.get(levelUrl);
	    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,waitTime);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/table/tbody/tr[6]/td/div/table/tbody/tr/td[1]/div/div[2]/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/input")).sendKeys(gfboUsername);
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/table/tbody/tr[6]/td/div/table/tbody/tr/td[1]/div/div[2]/table/tbody/tr[5]/td/table/tbody/tr[3]/td[2]/input")).sendKeys(gfboPassword);
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/table/tbody/tr[6]/td/div/table/tbody/tr/td[1]/div/div[2]/table/tbody/tr[7]/td/table/tbody/tr/td[3]/input")).click();
		}
	catch(Exception e) {
		
		 Assert.fail("Could Not Login");
	}
}


public void doGFBO(String testInputNbr, String  gfboUsername, String gfboPassword,String gfboPaymentLevel,String gfboPaymentType,String gfboAccount,String gfboExpectedResult) {
	
	login();

	if (gfboPaymentLevel.equals("Invoice level")) {
		try {
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/thead/tr/th[1]/table/tbody/tr[2]/td/div/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[4]/tbody/tr/td[2]/div/input[2]")).click();
		//driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[3]/td/table/tbody/tr[1]/td/div/div[2]/table/tbody/tr/td/table/tbody/tr[5]/td/div/input[2]")).click();
		
		try {
			//See if moved to next screen. If failed, hit pay button again.
		wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/div/span")));
		}
		catch(Exception e) {
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[4]/tbody/tr/td[2]/div/input[2]")).click();
			
		}
		
		
	}
	
	
	catch(Exception e) {
		System.out.println("Failed on Invoice");
	}
	}
	
	else if (gfboPaymentLevel.equals("Package level")) {}
	
	
	if (gfboPaymentType.equals("Credit card")){
		try {
			
		///html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td
			//driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[1]/div/table/tbody/tr")).click();
			
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]")).click();
			driver.findElement(By.xpath(" /html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table/tbody/tr/td[2]")).click();
			
			
										
		//	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table/tbody/tr/td[2]/div/input")).click();
			                                                        //    /html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table[1]/tbody/tr[1]/td[2]/select
			Select ccDropdown = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table[1]/tbody/tr[1]/td[2]/select")));
			System.out.println(ccDropdown.getOptions());
			for (WebElement e: ccDropdown.getOptions()) {
				System.out.println(e.getText());
			}
			ccDropdown.selectByIndex(1);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table[2]/tbody/tr[1]/td[1]/input")).click();
		
			wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td/div/span")));
			System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td/div/span")).getText());
			
		}
		catch(Exception e) {
			System.out.println("Failed At Credit Card");
		}
		
	}
	
}

}
