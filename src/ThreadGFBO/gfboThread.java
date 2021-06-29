package ThreadGFBO;

import java.util.ArrayList;
import java.util.List;
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
import configuration.importData;

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
	String level,cycle;
	driverClass dc;
	validateClass vc;
	
	String  gfboUsername, gfboPassword, gfboPaymentLevel, gfboPaymentType, gfboAccount, gfboExpectedResult;
	String gfboResult,gfboDescription;
	
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
		level=c.getLevel();
		cycle=c.getCycle();
	}
	
public void run () {
		
		for(data d: dataArray) {
			
			
			
			//Declare Vars
			result=d.getResult();
			descripiton=d.getDescription(); 
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

// Verify Homepage
	try {
		if (driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[1]/td/div/table/tbody/tr/td[1]/div/label")).getText().contains("Invoice List")) {
			System.out.println("Found Homepage");
		}
		
	}
	catch(Exception e) {
		System.out.println("Could not find homepage");
		return;
	}
	
	
	
	
	
	
	
	
	//Invoice Level
	if (gfboPaymentLevel.equals("Invoice Level")) {
		try {
			
			WebElement table =	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody"));
			List<WebElement> rows =	table.findElements(By.tagName("tr"));
			System.out.println(rows.size());
			int rowCount=rows.size();
			
			for (int i=1;i<rowCount;i++) {
				try {
					driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr["+i+"]/td[1]/table/tbody/tr/td[2]/div/span/img"));
					  driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
				}
				catch(Exception e) {
					driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr["+i+"]/td[1]/table/tbody/tr/td/div/input")).click();
					driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
					Thread.sleep(5000);
					break;
				}
			}
		}
		catch(Exception e) {
			
			System.out.println("Could not Table On First Screen");
		}
		
		
		
		try {
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[4]/tbody/tr/td[2]/div/input[2]")).click();
	
	}
	
	
	catch(Exception e) {

		
		gfboResult="fail";
		gfboDescription="could not find invoice to click";
		vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
		System.out.println("Failed on Invoice || Could Not Click Pay Button");
		return;
		}
	}
	
	
	
	
	
	
	
	else if (gfboPaymentLevel.equals("Package Level")) {
		System.out.println();
		
		try {
			
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr[1]/td[2]/a")).click();
			Thread.sleep(5000);
			
				WebElement table =	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody"));
				List<WebElement> rows =	table.findElements(By.tagName("tr"));
				System.out.println(rows.size());
				int rowCount=rows.size();
				
				for (int i=1;i<rowCount;i++) {
					try {
						  driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr["+i+"]/td[1]/table/tbody/tr/td[2]/div/span/img"));
						  driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					}
					catch(Exception e) {
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr["+i+"]/td[1]/table/tbody/tr/td/div/input")).click();
						driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
						break;
					}
				}
				Thread.sleep(5000);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/div/input")).click();
				
		/*	
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr[1]/td[2]/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr/td[1]/table/tbody/tr/td/div/input")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/div/input")).click();
		Thread.sleep(5000);
		*/
		}
		catch(Exception e) {
			System.out.println();
		}
		
		
		
	}
	
	
	
	
	try {
	if (driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/div/span")).getText().equals("My Payment Cart")) {
		System.out.println("Found Payment Cart");
	}
	
	}
	catch(Exception e) {
		gfboResult="fail";
		gfboDescription="failed at payment cart";
		vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
		System.out.println("Failed at payment cart -- exiting ");
		return;
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	if (gfboPaymentType.equals("Mail Check")){
		try {
			System.out.println("Mail Check");
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[2]/tbody/tr/td/div/div[2]/table[1]/tbody/tr/td[2]/input")).sendKeys("123456");
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[2]/tbody/tr/td/div/div[2]/table[3]/tbody/tr/td[2]/input")).click();
			Thread.sleep(20000);
			
			}
		catch(Exception e) {
			gfboResult="fail";
			gfboDescription="failed on mail check";
			vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
			System.out.println(e);
		}
	}
	
	
	//EFT
	if (gfboPaymentType.equals("EFT Payment")){
		try {
			System.out.println("EFT Payment");
			
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[1]/tbody/tr/td/div/div[1]/div/table")).click();
			Thread.sleep(2000);
			Select profileDropdown = new Select(driver.findElement(By.className("iceSelOneMnu")));
			
			
			System.out.println(profileDropdown.getOptions());
			for (WebElement e: profileDropdown.getOptions()) {
				System.out.println(e.getText());
			}
			profileDropdown.selectByIndex(1);
			Thread.sleep(2000);
			driver.findElement(By.id("mainContentId:EFT")).click();
			Thread.sleep(20000);
			
			
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("Failed At EFT");
			gfboResult="fail";
			gfboDescription="failed on EFT";
			vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
			System.out.println(e);
		}
		
	}
	
	
	
	
	
	//Creidt Card
	
	if (gfboPaymentType.equals("Credit Card")){
		try {
			System.out.println("Credit Card");
		///html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td
			//driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[1]/div/table/tbody/tr")).click();
			
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(" /html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table/tbody/tr/td[2]")).click();
			
			
										
		//	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table/tbody/tr/td[2]/div/input")).click();
			                                                        //    /html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table[1]/tbody/tr[1]/td[2]/select
		//	Select ccDropdown = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[6]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table[1]/tbody/tr[1]/td[2]/select")));
			Select ccDropdown = new Select(driver.findElement(By.className("iceSelOneMnu")));
			
			
			System.out.println(ccDropdown.getOptions());
			for (WebElement e: ccDropdown.getOptions()) {
				System.out.println(e.getText());
			}
			ccDropdown.selectByIndex(1);
			Thread.sleep(2000);
			driver.findElement(By.id("mainContentId:creditCard")).click();
		
				
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("Failed At Credit Card");
			gfboResult="fail";
			gfboDescription="failed on credit card";
			vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
			System.out.println(e);
		}
		
	}
	
	
	
	
	//////PAYPAL
	System.out.println(gfboPaymentType);
	if (gfboPaymentType.equals("Paypal Payment")){
		try {
			
			//Click Paypal
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[4]/tbody/tr/td/table/tbody/tr/td[3]/div/a/img")).click();
			Thread.sleep(10000);
			//username login
			driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/form/div[3]/div[1]/div[2]/div[1]/input")).sendKeys("Paid3@fedex.com");
			driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/form/div[3]/div[2]/button")).click();
			Thread.sleep(10000);
			
			//password login
			driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/form/div[4]/div[1]/div/div/div[1]/input")).sendKeys("Paidtest1");
			driver.findElement(By.xpath("/html/body/div[1]/section[2]/div/div/form/div[4]/div[4]/button")).click();
			Thread.sleep(10000);
			//Cointinue in chinnese
			driver.findElement(By.xpath("/html/body/div[1]/div/div/main/div[7]/div/button")).click();
			Thread.sleep(10000);
			//click on terms of agreement
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td/div/input")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody/tr[5]/td/div/input[1]")).click();
			Thread.sleep(20000);
			
		
			
			
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("Failed At Paypal");
			gfboResult="fail";
			gfboDescription="failed on paypal ";
			vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	
	//Payment Confirmation
	try {
		if (driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td/div/span")).getText().equals("Payment Confirmation")) {
			System.out.println("Payment Confirmation");
			gfboResult="pass";
			gfboDescription="completed";
			vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
		}
		
		}
		catch(Exception e) {
			System.out.println("Failed at payment cart -- exiting ");
			try {
				if (driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[1]/td/div/span")).getText().equals("Duplicate Payment Items")) {
					gfboResult="na";
					gfboDescription="payment already present";
					vc.writeToDb (gfboUsername, gfboPaymentLevel, gfboPaymentType, gfboResult, gfboDescription,level,cycle);
					return;
				}
				
				
			}
			catch(Exception ee) {
				System.out.println(ee);
			}
			return;
		}
}




























public void loginTest() {
	
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
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/table/tbody/tr[6]/td/div/table/tbody/tr/td[1]/div/div[2]/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("REBSL3604245010");
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/table/tbody/tr[6]/td/div/table/tbody/tr/td[1]/div/div[2]/table/tbody/tr[5]/td/table/tbody/tr[3]/td[2]/input")).sendKeys("Test1234");
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/table/tbody/tr[6]/td/div/table/tbody/tr/td[1]/div/div[2]/table/tbody/tr[7]/td/table/tbody/tr/td[3]/input")).click();
		}
	catch(Exception e) {
		
		 Assert.fail("Could Not Login");
	}
}




public gfboThread() {
	importData id = new importData();
	c=id.getConfig();
	c.setDriverType("2");
	System.out.println(c.getGfboSecondTimeout());
	System.out.println(Integer.parseInt(c.getGfboSecondTimeout()));
	levelUrl=c.getGfboUrl();
	databaseDisabled=c.getDatabaseDisabled();
	waitTime=Integer.parseInt(c.getGfboSecondTimeout());
	dc = new driverClass(c,levelUrl,waitTime);
	//vc= new validateClass(c,databaseDisabled,"gfbo");

	loginTest();



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

