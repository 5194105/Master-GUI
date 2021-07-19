package ThreadGFBO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;


public class gfboThread extends Thread{
	config c;
	driverClass dc;
	validateClass vc;
	
	ArrayList<data> dataArray;
	

	WebDriver driver;
	WebDriverWait wait;

	String result, svcType,descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, 
		login , password, rsType , company , prerate,workable, rowNumber,length,width,height,actualWeight, level, cycle,
		gfboUsername, gfboPassword, gfboPaymentLevel, gfboPaymentType, gfboAccount, gfboExpectedResult,invoiceNbr2,
		gfboResult,gfboDescription,browser, homePath,levelUrl,headless,databaseDisabled,source,region;
	
	int attempts=0,waitTime,maxAttempts;
	
	public gfboThread(ArrayList<data> dataArray,config c) {
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
			
			//Check if track is already successful (Not Yet Ready)
			
			//Start Main Program.
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
		
		/*
		 * 
		 * Invoice Level
		 * 
		 */
		
		if (gfboPaymentLevel.equals("Invoice Level")) {
			try {
				//This is going to get latest Invoice Number
				WebElement table =	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody"));
				//Get list of rows.
				List<WebElement> rows =	table.findElements(By.tagName("tr"));
				int rowCount=rows.size();
				
				//We are actually going to loop through for ones that ARENT paid already. Paid ones will have $ image.
				for (int i=1;i<rowCount;i++) {
					try {
						//If found image.. then move on.. If didnt find paid image.. go to exception.
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr["+i+"]/td[1]/table/tbody/tr/td[2]/div/span/img"));
						driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					}
					catch(Exception e) {
						//Will click on this invoice since it doesnt have paid image.. probably better way of doing this.
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
				//Click on pay
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
		
		
		/*
		 * 
		 * Package Level
		 * 
		 */
		
		else if (gfboPaymentLevel.equals("Package Level")) {
			try {
				//We are actually going to loop through for ones that ARENT paid already. Paid ones will have $ image.
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div/div/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr[1]/td[2]/a")).click();
				Thread.sleep(5000);
				WebElement table =	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody"));
				List<WebElement> rows =	table.findElements(By.tagName("tr"));
				int rowCount=rows.size();
				
				for (int i=1;i<rowCount;i++) {
					try {
						//If found image.. then move on.. If didnt find paid image.. go to exception.
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr["+i+"]/td[1]/table/tbody/tr/td[2]/div/span/img"));
						driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
						}
					catch(Exception e) {
						//Will click on this invoice since it doesnt have paid image.. probably better way of doing this.
						driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/table/tbody/tr["+i+"]/td[1]/table/tbody/tr/td/div/input")).click();
						driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
						break;
					}
				}
				Thread.sleep(5000);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[7]/td/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/div/div/input")).click();
				}
			catch(Exception e) {
				System.out.println();
			}
		}
		
		
		//See if we are on CART Screen
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
		
		
		
		
		///////////////////////////////////////PAYMENT TYPE/////////////////////////////////////////////////////////
		
		//Mail Check
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
				//Select a profile
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
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(" /html/body/div[1]/div[2]/div/div/div/form/table[2]/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr[5]/td/table/tbody/tr[5]/td/table/tbody/tr/td[2]/div/table[3]/tbody/tr/td/div/div[2]/table/tbody/tr/td[2]")).click();
				
				Select ccDropdown = new Select(driver.findElement(By.className("iceSelOneMnu")));
				
				System.out.println(ccDropdown.getOptions());
				//Select a profile
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
}