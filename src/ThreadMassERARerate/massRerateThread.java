package ThreadMassERARerate;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class massRerateThread extends Thread{
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
	int waitTime;
	int attempts=0;
	int maxAttempts=3;
	String level;
	driverClass dc;
	validateClass vc;
	String result,  description, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2,
	 rateWeight, actualWeight, wgtType,  length, width, height, 
	 workable, dimType, payor, billAcctNbr, serviceType, packageType,
	 rerateType, region, username, password, rsType, company, valDesc,comments,serviceName,massRerateCombo;
	int min=0,max=0;
	  ArrayList<Integer> trkList = new ArrayList<Integer>();
	  ArrayList<String> addedTrks = new ArrayList<String>();
	  ArrayList<String> removedTrks = new ArrayList<String>();
	
	public massRerateThread(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getRebillL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"era_rerate_mass");
	}
public void run () {
		
		for(data d: dataArray) {
		
			
			
			this.result=d.getResult();
			this.description=d.getDescription();
			this.testInputNbr=d.getTestInputNbr();
			this.tinCount=d.getTinCount();
			this.trkngnbr=d.getTrkngnbr();
			this.invoiceNbr1=d.getInvoiceNbr1();
			this.invoiceNbr2=d.getInvoiceNbr2();
			this.region=d.getRegion();
			this.username=d.getUsername();
			this.password=d.getPassword();
			this.rateWeight=d.getRateWeight();
			this.rerateType=d.getRerateType();
			this.wgtType=d.getWgtType();
			this.length=d.getLength();
			this.width=d.getWidth();
			this.height=d.getHeight();
			this.dimType=d.getDimType();
			this.rsType=d.getRsType();
			this.company=d.getCompany();
			this.massRerateCombo=d.getMassRerateCombo();
			
			
			
			
		    if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
		    	return;
		    }
		
			try {
				doMassRerate(result, description, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2,region,username,password, rateWeight , wgtType,length,width,height,dimType,rerateType,rsType,company,massRerateCombo);
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
		
									
		driver.findElement(By.id("okta-signin-username")).sendKeys(username);
		driver.findElement(By.id("okta-signin-password")).sendKeys(password);
		driver.findElement(By.id("okta-signin-submit")).click();
		}
	catch(Exception e) {
		
		 Assert.fail("Could Not Login");
	}
}


public void doMassRerate(String result, String descripiton,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String region,String username ,String password,String rateWeight,String wgtType,String length, String height, String width,String dimType, String rerateType,String rsType ,String company ,String massRerateCombo ) throws InterruptedException {


	
	maxAttempts=1;
	for (int ii=0;ii<maxAttempts;ii++) {
	login();
	WebElement element=null;
	JavascriptExecutor js= (JavascriptExecutor) driver;
	
	int packageCounter=0;
	Boolean exist;
	WebElement scrollElement;
	
	wait=new WebDriverWait(driver,20);

	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	try {
	//In order for clear button to be clickable need to scroll up
    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    
    
   
    int waittimer=3000;
    Thread.sleep(waittimer);
  //Will click on mass adjustments
    driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[1]/ul/li[12]/a")).click();
    Thread.sleep(waittimer);
    //Will click on mass rerates
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[2]/button[3]")).click();
    Thread.sleep(waittimer);
    //Will type the trk number to text area
    
    
    
    
    
    int max=0;
	int min=0;
	min=trkList.get(counter2);
	
	max=trkList.get(counter2+1);	
	System.out.println(min);
	System.out.println(max);	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).clear();
	int trkCounter=0;
	for(int i=min;i<max;i++) {
			if(addedTrks.contains(allData[i][4])) {
    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(allData[i][4]);
    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(Keys.chord(Keys.SHIFT,Keys.ENTER));
    			System.out.println("Test Input Number "+allData[i][2]);
    			System.out.println("Tracking Number "+allData[i][4]);
    			System.out.println("Reason Code "+allData[i][5]);
    			System.out.println("Account Number "+allData[i][6]);
    			trkCounter++;
			}
	}
    
    
    
//    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(trk);
  //  Thread.sleep(waittimer);
    //Will click on go
    driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[1]/button[1]")).click();
    Thread.sleep(waittimer);
    
    //Will click on trk
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[6]/div[1]/div/div/div[1]/div/div[1]/div/div/div/div/div/div/div/div/div")).click();
    Thread.sleep(waittimer);
    String lowercaseRerateType=rerate_type.toLowerCase();
    switch(lowercaseRerateType) {
    
  //Rating
    case "rating" :
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[2]")).click();
    break;
    
    		
    //weight
    case "weight" :
    		
    	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[3]")).click();
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[2]/div/div/input")).sendKeys(rate_weight);
    		
    		if (wgt_type.equals("LB")) {
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[3]/div[1]/div/label/span")).click();
    		}
    		if (wgt_type.equals("KG")) {
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[3]/div[2]/div/label/span")).click();
    		}
    		
    		break;
    
    //Dims
    case "dim" :
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[4]")).click();
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[1]/div/input")).sendKeys(length);
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[2]/div/input")).sendKeys(width);
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[3]/div/input")).sendKeys(height);
    		
    		if (dim_type.equals("IN")) {
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[4]/div[1]/div/label/span")).click();
    		}
    		if (dim_type.equals("CM")) {
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[4]/div[2]/div/label/span")).click();
    		}
    		break;
    
	//service
    case "service" :
    		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[5]")).click();
    		
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[3]/div/div/div/div/div/select/optgroup[5]/option[2]")).click();
    		
    }
    
    Thread.sleep(waittimer);
    
    /*
    System.out.println("RERATE");
    String[] resultArray = new String[3];
    resultArray[0]="stephen";
	resultArray[1]="made it to the end";
	resultArray[2]="na";
	writeToDB(testInputNbr,tinCount,trk,resultArray);
    */
    
    
    
    //Click on rerate
    driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[5]/div/div/button")).click();
	
    Thread.sleep(waittimer);
	String temp=driver.findElement(By.xpath(" /html/body/div[7]/div/div/div[1]/h3")).getText();;
	String requestID=temp.replaceAll("\\D+","");
	System.out.println( requestID);
	
   
		String[] resultArray = new String[3];
	 	resultArray[0]="In Progress";
	 	resultArray[1]="Rerate Created";
		resultArray[2]=requestID;
		writeToDB(testInputNbr,tinCount,trk,resultArray);
    
    
	} 
	catch(Exception e) {
		System.out.println("Failed on Entering Tracking Number");
		System.out.println("ERROR!!!!!!!!!");
		System.out.println(e);
		 if(source.equals("excel")) {
      //	 writeToExcel(rowNumber, 0,"fail");
      // 	 writeToExcel(rowNumber, 1,"Failed on Entering Tracking Number");
       	 }
			 if(databaseDisabled.equals("false")) {
   			 String[] resultArray = new String[3];
   			 	resultArray[0]="fail";
   				resultArray[1]="Failed on Entering Tracking Number";
   				resultArray[2]="na";
   				writeToDB(testInputNbr,tinCount,trk,resultArray);
        	 }
		 Assert.fail("Failed on Entering Tracking Number");
		
	}
}
}
}


