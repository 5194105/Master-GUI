package ThreadTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import configuration.config;

public class prerateThread {
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
	String result, descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType , company , prerate,workable, rowNumber;
	int waitTime;
	int attempts=0;
	int maxAttempts=3;
	String level;
	driverClass dc;
	validateClass vc;
	
	public prerateThread(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getRebillL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"era_rebill");
	}
	
	public void run () {
		
		for(data d: dataArray) {
			
			//Declare Vars
			result=d.getResult();
			descripiton=d.getDescription(); 
			testInputNbr=d.getTestInputNbr();
			tinCount=d.getTinCount();
			trkngnbr=d.getTrkngnbr();
			reasonCode=d.getReasonCode();
			rebillAccount=d.getBillAcctNbr();
			invoiceNbr1=d.getInvoiceNbr1();
			invoiceNbr2=d.getInvoiceNbr2();
			region =d.getRegion();
			login =d.getUsername();
			password=d.getPassword(); 
			rsType =d.getRs_type();
			company =d.getCompany();
			prerate=d.getRebillPrerate();
			workable=d.getWorkable();
			
			
			
			//Check if track is already successful
			
		    
		    if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
		    	continue;
		    }
		
			try {
				doRebill(testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType ,company , prerate,workable);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
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
			
										
			driver.findElement(By.id("okta-signin-username")).sendKeys(login);
			driver.findElement(By.id("okta-signin-password")).sendKeys(password);
			driver.findElement(By.id("okta-signin-submit")).click();
			}
    	catch(Exception e) {
    		
    		 Assert.fail("Could Not Login");
    	}
    }
}
