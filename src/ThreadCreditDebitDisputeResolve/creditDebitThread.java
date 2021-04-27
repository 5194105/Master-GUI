package ThreadCreditDebitDisputeResolve;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

public class creditDebitThread extends Thread{

	
	
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
	String result,  descripiton, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2, region , username ,
	password,  workable, reasonCode, reasonCategory, rootCause, valDesc,eraCase;
	int waitTime;
	int attempts=0;
	int maxAttempts=3;
	String level;
	driverClass dc;
	validateClass vc;
	
	public creditDebitThread(ArrayList<data> dataArray,config c) {
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
			 invoiceNbr1=d.getInvoiceNbr1();
			 invoiceNbr2=d.getInvoiceNbr2();
			 region =d.getRegion();
			 username=d.getUsername();
			 password=d.getPassword();
			
			 workable=d.getWorkable();
			 reasonCode=d.getReasonCode();
			 reasonCategory=d.getReasonCategory();
			 rootCause=d.getRootCause();
			 valDesc=d.getValDesc();
			 eraCase=c.getEraCase();
			System.out.println(trkngnbr);
			//Check if track is already successful
			
			 if(eraCase.equals("1")){   
				 vc.setFlag("era_credit");
			 }
			 if(eraCase.equals("2")){   
				 vc.setFlag("era_debit");
			 }
			 if(eraCase.equals("3")){   
				 vc.setFlag("era_dispute");
			 }
			 if(eraCase.equals("4")){   
				 vc.setFlag("era_resolve");
			 }
				 if (vc.validateCreditDebit(testInputNbr,tinCount,trkngnbr,valDesc)==true) {
					// vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null);
				 continue;
				 }
				    
			    	enterDataStep1(testInputNbr,tinCount,trkngnbr,invoiceNbr1);
			    	doCreditDebit(eraCase, result,  descripiton, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2,  region , username , password, workable,reasonCode,reasonCategory,rootCause,valDesc);
			    	enterContactMethodStep3(testInputNbr,tinCount,trkngnbr,invoiceNbr1,username,eraCase,valDesc);
			
			 
			 
		
			 
			 
			
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
    
    
    public void enterDataStep1(String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1) {
    	login();
    	WebElement scrollElement;
    	int packageCounter=0;
    	Boolean exist;
    	WebElement element=null;
    	JavascriptExecutor js= (JavascriptExecutor) driver;
    	
    	   	
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
           //	 writeToExcel(rowNumber, 0,"fail");
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
    	driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
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
               	// writeToExcel(rowNumber, 0,"fail");
               	// writeToExcel(rowNumber, 1,"Could Not Get To Charge Code Details");
               	 }
    				
    	   			 String[] resultArray = new String[2];
    	   			 	resultArray[0]="fail";
    	   				resultArray[1]="Could Not Get To Charge Code Details";
    	   				vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
                	 return;
     	}
    	System.out.println("STOP HERE");
    	
    	
    	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
    	try{
    		
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
     	
    	//Click on all Charge Codes
    	 try {
    		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
             js.executeScript("window.scrollTo(0,500)");
             scrollElement =  driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]"));
             js.executeScript("arguments[0].scrollIntoView();", scrollElement);
             driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]")).click();

            
            
            System.out.println("Clicked All Stat Codes");
        }
        catch(Exception e){
        	 System.out.println("Could Not Clicked All Stat Codes");
            System.out.println(e);
            Assert.fail("Could Not Clicked All Stat Codes");

        }
    	

    		 
    	
    }
    
    
    
    
    
    
    
    




public void doCreditDebit(String eraCase, String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2, String region ,String username ,String password,String workable,String reasonCode,String reasonCategory,String rootCause,String valDesc) {
	

	String spaceString="     ";
	 try{
	  Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
	  
	  String textContent="";
	  List <WebElement> optionsInnerText=null;
	  
	  /*
		List <WebElement> optionsInnerText= actionDropDown.getOptions();
		for(WebElement text: optionsInnerText){
		     textContent = text.getAttribute("textContent");
		    if(textContent.toLowerCase().contains(reasonCode.toLowerCase()))
		    	actionDropDown.selectByVisibleText(textContent);
		    }
	  */
	  
	  
	if (eraCase.equals("1")) {
		 actionDropDown.selectByValue("CR");
		 Thread.sleep(1000);
	}
	else if (eraCase.equals("2")) {
		 actionDropDown.selectByValue("DB");
		 Thread.sleep(1000);
	}
	else if (eraCase.equals("3")) {
		 actionDropDown.selectByValue("D");
		 Thread.sleep(1000);
	}
		 
		 //Reason Category
		 if(!reasonCategory.equals("")) {
		 actionDropDown = new Select (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[4]/select")));
		 optionsInnerText= actionDropDown.getOptions();
			for(WebElement text: optionsInnerText){
			     textContent = text.getAttribute("textContent");
			    if(textContent.toLowerCase().contains(reasonCategory.toLowerCase()))
			    	actionDropDown.selectByVisibleText(textContent);
			    	break;
			    }
		 Thread.sleep(1000);
		 }
		 //Reason code
		 if(!reasonCode.equals("")) { 
			 
		 actionDropDown = new Select (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
		// actionDropDown.selectByVisibleText(reasonCode+spaceString);
		
			 optionsInnerText= actionDropDown.getOptions();
				for(WebElement text: optionsInnerText){
				     textContent = text.getAttribute("textContent");
				    if(textContent.toLowerCase().contains(reasonCode.toLowerCase()))
				    	actionDropDown.selectByVisibleText(textContent);
				    break;
				    }
			 
			 Thread.sleep(1000);
		 }
		 //Root Cause
		 if(!rootCause.equals("")) { 
		 actionDropDown = new Select (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[6]/select")));
		 optionsInnerText= actionDropDown.getOptions();
			for(WebElement text: optionsInnerText){
			     textContent = text.getAttribute("textContent");
			    if(textContent.toLowerCase().contains(rootCause.toLowerCase()))
			    	actionDropDown.selectByVisibleText(textContent);
			    	break;
			    }
		 }
	

	    	
	
	
}
	 catch(Exception e) {
		  System.out.println(e);

		}
		}
	 














































public void enterContactMethodStep3(String testInputNbr,String tinCount,String trk,String invoiceNbr1,String username,String eraCase,String valDesc) {
try {
	 System.out.println("Inside getDetails");
     //Getting Action Dropdown. Will RB everytime.
	 
	 
		driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[9]/div[1]/button")).click();
	 
	
		
		wait=new WebDriverWait(driver,1); 
 	    driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

      	try {
      		//tHIS BUTTON IS BUGGY!!!! click many times!!!!!!!!
      		 Thread.sleep(5000);
      		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();         	
      		}
      	catch(Exception e0) {
      		System.out.println("Clould not click after dropdown");
      		
      	}
      	
     
      	
      	
      	
      	
      	
      	int counter1=0;
      	while(counter1<10) {
      	try { 
      		counter1++;
      		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
             break; 
      }
      	catch(Exception e1) {
	
      		try { 
      			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[3]/button[2]")).click();  
      		}
      		catch(Exception ee) {
      			System.out.println ("Not Asking For Adjust Full Amount");
      			}
      		
      		System.out.println("Could Not Click Rebill After Action Code");
      		 try {
      			 
      			 String tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
      			 if (tempError.equals("Trying To Rebill A Partial Amount")) {
      				 System.out.println(tempError);
      				 if(source.equals("excel")) {
      	               //	 writeToExcel(rowNumber, 0,"fail");
      	               	// writeToExcel(rowNumber, 1,"Trying To Rebill A Partial Amount");
      	               	 }
      	   				 
              	   			 String[] resultArray = new String[2];
              	   			 	resultArray[0]="fail";
              	   				resultArray[1]="Trying To Rebill A Partial Amount";
              	   			vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
              	   			return;
      		}
      			
      				 if (tempError.indexOf("interline")==1) {
      				 System.out.println(tempError);
      				 if(source.equals("excel")) {
      	               //	 writeToExcel(rowNumber, 0,"fail");
      	               //	 writeToExcel(rowNumber, 1,"interline acct");
      	               	 }
      	   				
              	   			 String[] resultArray = new String[2];
              	   			 	resultArray[0]="fail";
              	   				resultArray[1]="interline acct";
              	   			vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
              	   			return;
      		} 
      				 
      				 if (tempError.indexOf("Approval Limit")==1) {
          				 System.out.println(tempError);
          				 if(source.equals("excel")) {
          	               //	 writeToExcel(rowNumber, 0,"fail");
          	               //	 writeToExcel(rowNumber, 1,tempError);
          	               	 }
          	   			
                  	   			 String[] resultArray = new String[2];
                  	   			 	resultArray[0]="fail";
                  	   				resultArray[1]=tempError;
                  	   			vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
                  	   			return;
          		} 
      				 
      				 if (tempError.indexOf("specialist")==1) {
          				 System.out.println(tempError);
          				 if(source.equals("excel")) {
          	              // 	// writeToExcel(rowNumber, 0,"fail");
          	               	// writeToExcel(rowNumber, 1,"specialist error");
          	               	 }
          	   				 
                  	   			 String[] resultArray = new String[2];
                  	   			 	resultArray[0]="fail";
                  	   				resultArray[1]="specialist error";
                  	   			vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
                  	   			return;
          		} 
      				 
      				 if (tempError.indexOf("Cannot Credit An AirBill For More Than The Invoice Amount Due")==1) {
          				 System.out.println(tempError);
          				 if(source.equals("excel")) {
          	               //	 writeToExcel(rowNumber, 0,"fail");
          	               //	 writeToExcel(rowNumber, 1,"Cannot Credit An AirBill For More Than The Invoice Amount Due");
          	               	 }
          	   				
                  	   			 String[] resultArray = new String[2];
                  	   			 	resultArray[0]="fail";
                  	   				resultArray[1]="Cannot Credit An AirBill For More Than The Invoice Amount Due";
                  	   			vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
                  	   			return;
          		}
      				 
      				 if (tempError.indexOf("Adjustment can not be done for discount amount")==1) {
          				 System.out.println(tempError);
          				 if(source.equals("excel")) {
          	               //	 writeToExcel(rowNumber, 0,"fail");
          	               //	 writeToExcel(rowNumber, 1,"Adjustment can not be done for discount amount");
          	               	 }
          	   				
                  	   			 String[] resultArray = new String[2];
                  	   			 	resultArray[0]="fail";
                  	   				resultArray[1]="Adjustment can not be done for discount amount";
                  	   			vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
                  	   			return;
          		}
      				 
      				 
      				
      			
      				 

      		 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
      		 System.out.println("Found Pop Up");
      	    

      			 
      			
      		 }
      		 catch(Exception e) {
      			 System.out.println("Could not move on past dropdown details");
      			 }
      		 }
      	}
      	if(counter1>=10) {
      		 if(source.equals("excel")) {
             	// writeToExcel(rowNumber, 0,"fail");
             	// writeToExcel(rowNumber, 1,"Could Not go to phone detail screen");
             	return;
             	 }
 				
 	   			 String[] resultArray = new String[2];
 	   			 	resultArray[0]="fail";
 	   				resultArray[1]="Could Not go to phone detail screen";
 	   				vc.writeToDb(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1],null);
 	   			return;
      	}
      	
      	
      	
      	
      	        
      	
      	
      	
      	wait=new WebDriverWait(driver,waitTime); 
  	    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
	
      	
      		 
      		 /*
          	 *****************************************************************************
          	 *
          	 *Getting to phone detail
          	 *
          	 *
          	 ****************************************************************************
          	 */
      		 
      		 System.out.println("Phone Details");
      		  Thread.sleep(1500);
   //   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
          try{
         	 //Click on rebill RPI Complete, Phone, and Continue
               if (username.equals("5194105")){
             	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
               }
           Thread.sleep(1500);
               
            Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
            Thread.sleep(1500);
            contactMethodDropDown.selectByValue("phone");  
            Thread.sleep(1500);
      	  
            //CLICK CONTINUE.. THIS WILL CREDIT IT
            //Due to how glitchy it is... we will press this 10 times.. if successful then it will move to another screen.
             try {
            	 driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
            int continueCounter=0;
            while (continueCounter<10){
            	continueCounter++;
            	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
             	Thread.sleep(1000);
             }
             }
             catch (Exception continueException) {
            	 System.out.println("Could Not CLick Continue Again");
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
      	   
          
          
          
          //Dispute Only
           if (eraCase.equals("3")) {
        	   driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/div[2]/div/div/div/input")).sendKeys("Stephen");
        	   driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[3]/div/div/div/input")).sendKeys("Daniel");
        	   driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[4]/div/div/div/input")).sendKeys("1234567890");
        		driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[5]/button[1]")).click();
        	   
        	  
     		 Thread.sleep(1000);
     	}
          
          driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
          System.out.println();
          
          Thread.sleep(10000);
          
          //checkValidation(type,testInputNbr,tinCount,trk,valDesc,rowNumber);
         if ( vc.validateCreditDebit( testInputNbr, tinCount, trkngnbr, valDesc)==true) {
        	 vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null);
        	
         }
         else {
        	 vc.writeToDb(testInputNbr, tinCount, trkngnbr, "fail", "made it to end, but failed", null);
        	 
         }
         
         
          /*
          String[] resultArray = validateResults(trk,type,true);
          if(source.equals("excel")) {
          	 writeToExcel(rowNumber, 0,resultArray[0]);
          	 writeToExcel(rowNumber, 1,resultArray[1]);
          	return;
          	 }
				 if(databaseDisabled.equals("false")) {
	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
	   				
           	 } 
          */
          
         
          
         // endTest(testInputNbr,tinCount,trk,"test","made it to the end");
          
	 
}
catch(Exception e) {
  System.out.println(e);

}
}

	 
    
}