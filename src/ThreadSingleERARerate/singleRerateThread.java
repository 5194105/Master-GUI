package ThreadSingleERARerate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class singleRerateThread extends Thread{
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
	 rerateType, region, username, password, rsType, company, valDesc,comments,serviceName;
	Boolean running=true;
	int runningCounter;
	data d;
	
	public singleRerateThread(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getRebillL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"era_rerate");
	}
public void run () {
	for(data d: dataArray) {
		if (d.getRunningResult().equals("false")) {
			running=true;
			break;
		}
	}
	
	
	while (running == true) {
		running=false;
		for(data d: dataArray) {
			if (d.getRunningResult().equals("false")) {
				running=true;
				break;
			}
		}
		
	for(data d: dataArray) {
			this.result=d.getResult();
			this.description=d.getDescription();
			this.testInputNbr=d.getTestInputNbr();
			this.tinCount=d.getTinCount();
			this.trkngnbr=d.getTrkngnbr();
			this.invoiceNbr1=d.getInvoiceNbr1();
			this.invoiceNbr2=d.getInvoiceNbr2();
			this.rateWeight=d.getRateWeight();
			this.actualWeight=d.getActualWeight();
			this.wgtType=d.getWgtType();
			this.length=d.getLength();
			this.width=d.getWidth();
			this.height=d.getHeight();
			this.workable=d.getWorkable();
			this.dimType=d.getDimType();
			this.payor=d.getPayor();
			this.billAcctNbr=d.getBillAcctNbr();
			this.serviceType=d.getServiceType();
			this.serviceName=d.getServiceName();
			this.packageType=d.getPackageType();
			this.rerateType=d.getRerateType();
			this.region=d.getRegion();
			this.username=d.getUsername();
			this.password=d.getPassword();
			this.rsType=d.getRsType();
			this.company=d.getCompany();
			this.valDesc=d.getValDesc();
			this.comments=d.getComments();
			
			
		    if (vc.validateRerate(testInputNbr,tinCount,trkngnbr)==true) {
		    	d.setRunningResult("true");
		    	continue;
		    }
		
			try {
				doEraRerate(result, description, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2, rateWeight , actualWeight , wgtType,length,width,height,workable,dimType,payor,billAcctNbr,serviceType,serviceName,
						packageType,rerateType,region,username,password,rsType,company,valDesc,comments);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
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
		
		return;
	}
}


public void doEraRerate(
		String result, String description,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,
		String rateWeight,String actualWeight,String wgtType,String length,String width,String height,
		String workable,String dimType,String payor,String billAcctNbr,String serviceType,String serviceName,String packageType,
		String rerateType,String region ,String username ,String password,
		String rsType,String company,String valDesc,String comments
		) throws InterruptedException {
	
	maxAttempts=1;
	for (int ii=0;ii<maxAttempts;ii++) {
	login();
	
	WebElement element=null;
	JavascriptExecutor js= (JavascriptExecutor) driver;
	
	int packageCounter=0;
	Boolean exist;
	WebElement scrollElement;
	
	wait=new WebDriverWait(driver,20);
	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
	 	
	try {
	//In order for clear button to be clickable need to scroll up
    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    
    //Will hit the clear button. This is for whenever we switch to new tracking number    
    driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a")).click();

    
    //In login we verified this appears and sending our tracking number to it.
    driver.findElement(By.xpath("//*[@id=\"trackingID\"] ")).sendKeys(trkngnbr);

    //Click on Search
    // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button"))));
    driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button")).click(); 
	} 
	catch(Exception e) {
		System.out.println("Failed on Entering Tracking Number");
		 if(source.equals("excel")) {
       	// writeToExcel(rowNumber, 0,"fail");
       //	 writeToExcel(rowNumber, 1,"Failed on Entering Tracking Number");
       	 }
			
   			 String[] resultArray = new String[2];
   			 	resultArray[0]="fail";
   				resultArray[1]="Failed on Entering Tracking Number";
   				 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
   				 return;
        	 }
		 
		
	
	
	//Try to Click Package Tab
	int counter1=0;
	String tempString1;
	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
	
	while (counter1<10) {
	try {  
		counter1++;
		System.out.println("Trying to click package tab");
		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
		js.executeScript("arguments[0].click()", element);    
		tempString1=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/div[1]/span[1]")).getText();
		if(tempString1.equals("Charge Code Description")) {
			System.out.println("Found Code Desc");
			break;
		
		}
	}
	catch(Exception e) {
		try {  
			System.out.println("Trying to click popup");
    		driver.findElement(By.xpath(" /html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys(invoiceNbr1);
    		Thread.sleep(1000);
    		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
    		driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
    		System.out.println("Found Pop Up");
    		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
        	js.executeScript("arguments[0].click()", element);
    }	catch(Exception ee) {
    	System.out.println("Could Not Find Pop Up Or Continue To Charge Code Screen");
       // Assert.fail("Could Not Find Popup Or COntinue to Package Screen");
    	}
	}
	}
	
	if(counter1>=10) {
		 if(source.equals("excel")) {
           //	 writeToExcel(rowNumber, 0,"fail");
          // 	 writeToExcel(rowNumber, 1,"Could Not Get To Charge Code Details");
           	 }
		 if (vc.searchOracleDBError(testInputNbr, tinCount, trkngnbr, invoiceNbr1)==true){
		   		return;
	    		}
	   			 String[] resultArray = new String[2];
	   			 	resultArray[0]="fail";
	   				resultArray[1]="Could Not Get To Charge Code Details";
	   			 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
            	 
    		
 		return;
 	}
	
	

	
	//Getting all the charge codes..
	try{
		packageCounter=0;
            while(true){
            	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
            	exist= driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+packageCounter+"\"]")).isDisplayed();
            	packageCounter++;
                System.out.println("packageCounter "+packageCounter);
                
            }
        }
        catch(NoSuchElementException e){
            System.out.println("No Such Element... Got all the counters for charge codes");
    }
	
	/*
     catch(Exception e){
    	 System.out.println("Failed at Getting Charge Code Count");
    }
	 */
	
	
	//Click on all Charge Codes
	 try {
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
         js.executeScript("window.scrollTo(0,500)");
         scrollElement =  driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]"));
         js.executeScript("arguments[0].scrollIntoView();", scrollElement);
         driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]")).click();

        
        
        System.out.println("Clicked All Stat Codes");
    }
    catch(Exception e){
    	 System.out.println("Could Not Clicked All Stat Codes");
        System.out.println(e);
      return;

    }
	
	

	 
	 
	 
	 
	 
	 /*
	 *****************************************************************************
	 *
	 *Getting Dropdown Details Right Side
	 *
	 *
	 ****************************************************************************
	 */
	 
 	
     try{
    	 System.out.println("Inside getDetails");
         //Getting Action Dropdown. Will RB everytime.
         Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
         actionDropDown.selectByValue("RT");
         Thread.sleep(5000);
  		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
  		 Thread.sleep(2000);
  		 
  		 
  		 try {
  			 driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
    		 String error = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
    		 String[] resultArray = new String[2];
    		 resultArray[0]="fail";
    		 resultArray[1]=error;
    		 if(source.equals("excel")) {
            	// writeToExcel(rowNumber, 0, resultArray[0]);
            	// writeToExcel(rowNumber, 1, resultArray[1]);
            	 }
    		 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
    		 return;
    		 
    	 }
    	 catch(Exception e) {
    		 System.out.println("No Popup");
    	 }
  		 
  		
         
     }
  
  catch(Exception e) {
	  
	  System.out.println("Failed at Drop Down");
	
  }

     driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    System.out.println();
    // driver.findElement(By.xpath("")).sendKeys();
   //  driver.findElement(By.xpath("")).click();
	 
     try {
 if(rerateType.contains("weight")) {
	 if (!rateWeight.equals("")) {
		
		 if (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).isEnabled()) {
			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).clear();		
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).sendKeys(rateWeight);
		 }
		 else if (!driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).isEnabled()) {
			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).clear();	
			 Thread.sleep(1000);
			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).sendKeys(rateWeight);
    		 }
		 
	 }
	 
	 if (!actualWeight.equals("")) {
	 if ( driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).isEnabled()) {
		 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).clear();	
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).sendKeys(actualWeight);
		 }
		 else if (! driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).isEnabled()) {
			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).clear();	
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).sendKeys(actualWeight);
	    		 }
	 }
 }
 
 if(rerateType.contains("dim")) {
	 
	
	 if (!length.equals("")) {
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[2]/div[1]/div/input")).clear();	
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[2]/div[1]/div/input")).sendKeys(length);
	 }
	 if (!width.equals("")) {
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[3]/div[1]/div/input")).clear();	
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[3]/div[1]/div/input")).sendKeys(width);
	 }
	 if (!height.equals("")) {
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[4]/div[1]/div/input")).clear();	
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[4]/div[1]/div/input")).sendKeys(height);
	 }
	 
	 if (!dimType.equals("")) {
		 if (dimType.equals("LB")) {
			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[5]/div[2]/div/div/label/span")).click();
			
			 
		 }
		 if (dimType.equals("CM")) {
			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[5]/div[3]/div/div/label/span")).click();
			
			 
		 }
	 }
	 
	 
 }
 
 
 if(rerateType.contains("payor")) {
	 if (payor.equals("1")) {
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[2]/div[1]/div/label/span")).click();
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[2]/div[2]/div/input")).clear();	
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[2]/div[2]/div/input")).sendKeys(billAcctNbr);
	 }
	 if (payor.equals("2")) {
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[3]/div[1]/div/label/span")).click();
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[3]/div[2]/div/input")).clear();	
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[3]/div[2]/div/input")).sendKeys(billAcctNbr);
	 }
	 if (payor.equals("3")) {
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[4]/div[1]/div/label/span")).click();
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[4]/div[2]/div/input")).clear();	
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[4]/div[2]/div/input")).sendKeys(billAcctNbr);
	 }
 
 }
     }
 catch(Exception e) {
	 String[] resultArray = new String[2];
		resultArray[0]="fail";
		resultArray[1]="Couldnt Correct Input";
		vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
		}
 
 try {
 if(rerateType.contains("service")) {
	Select sel = new Select(driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div[2]/div/div/div/select")));
	if (!serviceType.equals("")) {
	sel.selectByVisibleText(serviceType);
	}
	/*
	System.out.println(sel.getFirstSelectedOption().getText());
	if(sel.getFirstSelectedOption().getText().equals("")) {
		sel.selectByVisibleText(svcType);
		
	} 
	*/
	
	sel = new Select(driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div[3]/div/div/div/select")));
	sel.selectByVisibleText(serviceName);
	
	}
 }
	catch(Exception e) {
		String[] resultArray = new String[2];
		resultArray[0]="fail";
		resultArray[1]="Couldnt Find Service";
		vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
	 
	 return;
	}
	
 
 
 try {
 //Click Rerate
 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[1]")).click();
//  Thread.sleep(10000);
 //Check Popup
 }
 catch(Exception e) {
	 System.out.println(e);
	 System.out.println("Could not click rerate");
	 String[] resultArray = new String[2];
		resultArray[0]="fail";
		resultArray[1]="Could not click rerate";
	 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
	 return;
 }
 
 driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
 int knter=0;
 String tempErrorString="";
	     while(knter<10) {
 try {
	knter++;
	System.out.println(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/div[1]/div/div/div")).getText());
	break;
 }
	catch(Exception e) {	
	try {
	List<WebElement> rowCounts =driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/div/div[1]/div[2]/div/div/div"));
	if ( rowCounts.size()!=0) {
	for(WebElement we:rowCounts) {
		System.out.println(we.getText());
		tempErrorString=tempErrorString+" "+we.getText();
		System.out.println(tempErrorString);
	}
	String tempErr;
	
	if (tempErrorString.length()>300) {
		 tempErr=tempErrorString.substring(0,300);
	}
	else {
		 tempErr=tempErrorString;
	}
		
	
	 if(source.equals("excel")) {
      // 	 writeToExcel(rowNumber, 0,"fail");
      // 	 writeToExcel(rowNumber, 1,tempErr);
       	 }
			 
   			 String[] resultArray = new String[2];
   			 	resultArray[0]="fail";
   				resultArray[1]=tempErr;
   			 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
        	 
			return;
	}
 }
	catch(Exception ee) {
		System.out.println("Cant Continue Rerate.. Retry count: "+ knter);
    }
}
	     }
	     
	     if (knter>=10) {
	    	 if(source.equals("excel")) {
	         //  	 writeToExcel(rowNumber, 0,"fail");
	         //  	 writeToExcel(rowNumber, 1,"Timeout on Rerate Screen");
	           	 }
	    			
	       			 String[] resultArray = new String[2];
	       			 	resultArray[0]="fail";
	       				resultArray[1]="Timeout on Rerate Screen";
	       			 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
	            	 
	    			 return;
	    	 
	     }
	     
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);


 
 
 
 //Click Continue 
 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[3]")).click();

 
 String getText =  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[3]/select[1]")).getText();
 System.out.println(getText);
 
 System.out.println(10000);
 //click process

 int i=0;
 try {
	 driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	    
while (i<10) {
	i++;
	
	driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[9]/div[1]/button")).click();
	
	try {
		//if normal continue
		String tempString11 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).getText();
		if (tempString11.equals("Continue"))	{
			break;
		}
		}
		catch(Exception e) {    		
		}
		    	
		  
		    	
	
	
	try {		
		//if adjust by value
			String tempString11 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[3]/button[2]")).getText();
			if (tempString11.equals("Continue"))	{
				break;
			}
	}
	catch(Exception e) {    		
	}

}

 
 }
catch(Exception e) {
	 
 }
 
 
 
 
 
 
 
 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
 
 //Click Continue
 System.out.println(3000);
 try {
 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[3]/button[2]")).click();
 }
 catch(Exception e) {
	 System.out.println(e);
 }
 
 try {
	 driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
	 String tempE= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
	    if (tempE!=null) {
	 if(source.equals("excel")) {
    	// writeToExcel(rowNumber, 0,"fail");
    	// writeToExcel(rowNumber, 1,tempE);
    	return;
    	 }
		
			 String[] resultArray = new String[2];
			 	resultArray[0]="fail";
				resultArray[1]=tempE;
				 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
				
     	 
		return;
 }
 }
 catch(Exception e) {
	 
	 System.out.println("No Error Before Phone");
 }
 
 
 try{
	 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	 //Click on rebill RPI Complete, Phone, and Continue
      if (username.equals("5194105")){
    	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
     
    	  Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
          contactMethodDropDown.selectByValue("phone");  
          Thread.sleep(1500);  
          //driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
        
      }
      else {
    	  
    	  Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
          contactMethodDropDown.selectByValue("phone");  
          Thread.sleep(1500);  
          //driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
        
      }
  
      //Keep clicking until button is not there... give 10 tries
   
      driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
      int kounter=0;
      while (kounter<10)
      try {
    	  kounter++;
    	  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
          
      }
      catch(Exception e) {
    	  System.out.println("Could Not Click Last Continue... should be rerated now");
    	  break;
      }
      
      
      
      
      Thread.sleep(5000);
      
      
      try {
  
      String tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
		 if (tempError.equals("Conflicting Case check call failed")) {
			 System.out.println(tempError);
			 if(source.equals("excel")) {
               //	 writeToExcel(rowNumber, 0,"fail");
               //	 writeToExcel(rowNumber, 1,"Conflicting Case check call failed");
               	 }
   				
  	   			 String[] resultArray = new String[2];
  	   			 	resultArray[0]="fail";
  	   				resultArray[1]="Conflicting Case check call failed";
  	   			 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
                 	 return;
	}
      }
      catch(Exception e) {
    	  System.out.println(e);
    	  System.out.println("SEEING IF ERROR EXISTED");
    	  
      }
   
 }
 catch(Exception e1) {
	 System.out.println("Failed Selecting Contact Method and Clicking Continue");
 
	 if(source.equals("excel")) {
    	// writeToExcel(rowNumber, 0,"fail");
    	// writeToExcel(rowNumber, 1,"Failed Selecting Contact Method and Clicking Continue");
    	return;
    	 }
		
			 String[] resultArray = new String[2];
			 	resultArray[0]="fail";
				resultArray[1]="Failed Selecting Contact Method and Clicking Continue";
				 vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
				
     	return;
 }
 
 Thread.sleep(15000);
 
 try {
	 if (vc.validateRerate(testInputNbr, tinCount, trkngnbr)==true) {
		vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null); 
		d.setRunningResult("true");
		return;
		
	 }
   
 }
  	  
  	
  	  catch(Exception e) {
  		System.out.println(e);  
  	  }
    	
	 }
	}
}



