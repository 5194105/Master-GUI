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


import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class massRerateThread extends Thread{
	ArrayList<data> dataArray;
	ArrayList<data> dataArray2 = new ArrayList();
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
	  Boolean execute=false;
	  Boolean running=true;
		int runningCounter;
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
	String tempTin="0";
	
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
			
			
			
			
			
			
	    	int tempNumber=0;
	    	
	    	
	    	if (!testInputNbr.equals(tempTin)) {
	    		if (execute == false) {
	    		tempTin=testInputNbr;
	    		dataArray2.add(d);
	    		execute=true;
	    		}
	    		else {
	    			try {
						doMassRerate();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			execute=false;
	    			dataArray2.clear();
	    			dataArray2.add(d);
	    		}
	    	}
	    	else {
	    		dataArray2.add(d);
	    	}
	    	
	    
	    	
	    	
	    
	    	
	    
	    	System.out.println();
	   
		}
		try {
			doMassRerate();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
	}
}


public void doMassRerate( ) throws InterruptedException {


	
	maxAttempts=3;
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
    int count=0;
  //Will click on mass adjustments
    while (count < 10) {
    	count++;
    
    try {
    	
    driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[1]/ul/li[12]/a")).click();
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea"));
    break;
    }
    catch(Exception e) {
    	try {
    	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea"));
    	}
    	catch(Exception ee) {}
    	}
    }
    Thread.sleep(waittimer);
    //Will click on mass rerates
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[2]/button[3]")).click();
    Thread.sleep(waittimer);
    //Will type the trk number to text area
    
    
    
    
    
    int max=0;
	int min=0;
	
	System.out.println(min);
	System.out.println(max);	
	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).clear();
	int trkCounter=0;
	String wgt_type="";
	String rate_weight="";
	String dim_type="";
	for(data d:dataArray2) {
			
    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(d.getTrkngnbr());
    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(Keys.chord(Keys.SHIFT,Keys.ENTER));
    			System.out.println("Test Input Number "+d.getTestInputNbr());
    			System.out.println("Tracking Number "+d.getTrkngnbr());
    			System.out.println("Reason Code "+d.getReasonCode());
    			System.out.println("Account Number "+d.getBillAcctNbr());
    			rerateType=d.getRerateType();
    			wgt_type=d.getWgtType();
    			rate_weight=d.getRateWeight();
    			dim_type=d.getDimType();
			}
	
    
    
    
//    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(trk);
  //  Thread.sleep(waittimer);
    //Will click on go
    driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[1]/button[1]")).click();
    Thread.sleep(waittimer);
    
    //Will click on trk
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[6]/div[1]/div/div/div[1]/div/div[1]/div/div/div/div/div/div/div/div/div")).click();
    Thread.sleep(waittimer);
    String lowercaseRerateType=rerateType.toLowerCase();
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
	
   
	//	String[] resultArray = new String[3];
	 //	resultArray[0]="In Progress";
	 //	resultArray[1]="Rerate Created";
	//	resultArray[2]=requestID;
	
	for (data d : dataArray2) {
		vc.writeToDb(d.getTestInputNbr(),d.getTinCount(),d.getTrkngnbr(),"In Progress","Rerate Created",requestID);
		d.setRunningResult("true");
	}
	
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
					for (data d : dataArray2) {
						vc.writeToDb(d.getTestInputNbr(),d.getTinCount(),d.getTrkngnbr(),"fail","Unknown Error",null);
        	 }
			 }
				
		
	}
}
}
}


