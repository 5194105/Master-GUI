package ThreadTest;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configuration.config;
import configuration.excel;
import configuration.importData;

public class rebillThread extends Thread{

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
	String result, descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType , company , prerate,workable, rowNumber,length,width,height,actualWeight;
	int waitTime;
	int attempts=0;
	int maxAttempts;
	String level;
	driverClass dc;
	validateClass vc;
	
	public rebillThread(ArrayList<data> dataArray,config c) {
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
			System.out.println(trkngnbr);
			length=d.getLength();
			width=d.getWidth();
			height=d.getHeight();
			actualWeight=d.getActualWeight();
			
			//Check if track is already successful
			
		    
		    if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
		    	continue;
		    }
		    
		    
		
			try {
				doRebill(testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType ,company , prerate,length,width,height,actualWeight,workable);
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
    
   
    public void doRebill(String testInputNbr,String tinCount,String trkngnbr,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2 ,String region ,String login ,String password,String rsType ,String company ,String prerate,String length,String width,String height,String actualWeight,String workable) throws InterruptedException {
    	String finalResult="";
    	String finalDesc="";
    	maxAttempts=1;
    	for (int i=0;i<maxAttempts;i++) {
    	login();
    	WebElement element=null;
    	JavascriptExecutor js= (JavascriptExecutor) driver;
    	int packageCounter=0;
    	Boolean exist;
    	WebElement scrollElement;
    	
       	
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
    		finalResult="fail";
    		finalDesc="Failed on Entering Tracking Number";
	   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
    		
	   		
	   		
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
    			
    	   		
    	   		if (vc.searchOracleDBError(testInputNbr, tinCount, trkngnbr, invoiceNbr1)==true){
    		   		return;
    	    		}
    	    		else {
    	    			finalResult="fail";
    	    	   	    finalDesc="Could Not Get To Charge Code Details";
    	    	   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
    	    			
    	    		}
    	   		
    	   		
    	   		continue;
                	 }
        		
     
    	
    	
 
    	
    	
    	
    	//Getting all the charge codes..
    	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
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
		         actionDropDown.selectByValue("RB");
     
	       
		        
		         
		System.out.println("")   ;      
	         //For domestic.
	         if (login.equals("5194105") || login.equals("584168")){
	             switch (reasonCode){
         
                 case "RRA" :
                	
                 	//reasonCodeDropDown.selectByValue("RRA - REBILL RECIP ACCT   ");
                 								
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[2]")).click();
                     break;
                 case "RSA" :
                
        	        
                 //	reasonCodeDropDown.selectByVisibleText("RSA - REBILL SHIPPER ACCT ");
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[3]")).click();
                     break;
                 case "RTA" :
                
                 //	reasonCodeDropDown.selectByValue("RTA - REBILL THIRD PARTY  ");
                     driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[4]")).click();
                	 break;
                 case "RBS" :  
                
                 //	reasonCodeDropDown.selectByValue("RBS - REBILL SAME ACCOUNT");
                 	break;
                 }
             }
	         else {
              switch (reasonCode){
               
              	case "RRA" :
              	
              			driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[14]")).click();
                     break;
                 case "RSA" :
                           	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[17]")).click();
                     break;
                 case "RTA" :
                 	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[21]")).click();
                     break;
                 case "KPR" :
                
                     break;
                 case "RSD" :
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[18]")).click();
                     break;
                 case "RBS" :  
                 	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[6]")).click();
                    								  
                 	break;
                 }
	         }
         }
      
      catch(Exception e) {
    	  
    	  	System.out.println("Failed at Drop Down");
			finalResult="fail";
	   	    finalDesc="Could Not Find Rebill Dropdown";
	   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
	   		continue;
      }
    	     
    	     
    if(!prerate.equals("")) {
         try {
        	 
    	     driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[9]/label[2]/span[1]")).click();
    	     Thread.sleep(5000);
    	     driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
     		 Thread.sleep(2000);
    	     driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[2]/label[2]/span")).click();
    	     Thread.sleep(2000);
    	     driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[3]/button[1]")).click();
     	       	     
    }
         catch(Exception e) {
        	 System.out.println(e);
         }
    	     }
    	     else {
    	
    	 /*
    	 *****************************************************************************
    	 *
    	 *Getting to phone detail screen
    	 *
    	 *
    	 ****************************************************************************
    	 */
        
        
    	    	 
    	    	 
    	    	 
    		 
    	    	 
    	    	 
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
         		
    
         	counter1=0;
         	while(counter1<10) {
         	try { 
         		counter1++;
         		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
                break; 
         }
         	catch(Exception e1) {
	
         		System.out.println("Could Not Click Rebill After Action Code");
         		 try {
         			 
         			 String tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
         			 if (tempError.equals("Trying To Rebill A Partial Amount")) {
         				 System.out.println(tempError);
         						finalResult="fail";
                 		   	    finalDesc="Trying To Rebill A Partial Amount";
                 		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
                 		   		continue;
         		}
         			 if (tempError.contains("Cannot Credit An AirBill For More Than The Invoice Amount Due")) {
         				 System.out.println(tempError);
         					finalResult="fail";
                 		   	    finalDesc="Cannot Credit An AirBill For More Than The Invoice Amount Due";
                 		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
                 		   		continue;
                      }
         	   			
         			  if (tempError.indexOf("interline")==1) {
         				 System.out.println(tempError);
         				                  	   			finalResult="fail";
                 		   	    finalDesc="interline acct";
                 		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
                 		   		continue;
                       }
         	   		
         				 
         			 if (tempError.indexOf("Approval Limit")==1) {
             		
                     	   			finalResult="fail";
                     		   	    finalDesc=tempError;
                     		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
                     		   		continue;
                      }
             	   	
         			if (tempError.indexOf("specialist")==1) {
             				 System.out.println(tempError);
             				
                     	   			finalResult="fail";
                     		   	    finalDesc="specialist error";
                     		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
                     		   		continue;
                                    	 }
             	   	
         			         				 
         			if (tempError.indexOf("Adjustment can not be done for discount amount")==1) {
             				 System.out.println(tempError);
             						finalResult="fail";
                     		   	    finalDesc="Adjustment can not be done for discount amount";
                     		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
                     		   		continue;
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
         				finalResult="fail";
         		   	    finalDesc="Could Not go to phone detail screen";
         		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
         		   		continue;
    	   				
                 	 }
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
     
             try{
            	 //Click on rebill RPI Complete, Phone, and Continue
                  if (login.equals("5194105")|| login.equals("584168")){
                	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
                  }
              
                  
               Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
               contactMethodDropDown.selectByValue("phone");  
               Thread.sleep(1500);
               
               
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
                   	 System.out.println("Could Not Click Continue Again");
                    }
               
         	  
             }
             catch(Exception e1) {
            	 System.out.println("Failed Selecting Contact Method and Clicking Continue");
             
    	   				finalResult="fail";
         		   	    finalDesc="Failed Selecting Contact Method and Clicking Continue";
         		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
         		   		continue;
    	   				
                 	 } 
    
             wait=new WebDriverWait(driver,1);
             driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
             counter1=0;
         	  
             
             while(counter1<=10) {   
         	   counter1++;
         	   try {
         	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
             	break;
         	   }
         	   		catch(Exception e) {
         	   			try {
         	   				System.out.println("Could not select phone or click RPI..");
         	   				String tempError= driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/h4")).getText();
         	   				if (tempError.indexOf("Management approval")!=-1) {
         	   					System.out.println(tempError);
         	   			
                 	   			finalResult="fail";
                 		   	    finalDesc="Management approval";
                 		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
                 		   		continue;
                                	 }
         	   			
         	   				 
         	   				driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
         	   					}
         	   				catch(Exception ee) {
         	   				 System.out.println(ee+"Could Not Get to Rebill Screen");
         	   				
         	   				}
         	   			}
             		}
             
             
             
             if (counter1>=10){
            
 	   			finalResult="fail";
 		   	    finalDesc="Could Not Get to Rebill Screen";
 		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
             }

             			/*
                     	 *****************************************************************************
                     	 *
                     	 *Rebill Screen
                     	 *
                     	 *
                     	 ****************************************************************************
                     	 */	
         	   			
         	   			
         	   			
            
    	 	
             Boolean validated;
             try{    
            	
             switch (reasonCode){
             		case "RRA" :
                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reciptacct_number\"]")));
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).clear();
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(rebillAccount);
                       break;
                    case "RSA" :
                       //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[7]/di[1]/label")));
                       //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[7]/div[1]/label")));
                       //*[@id="shipacct_number"] 
                       //TESTING PURPOSE ONLY! DELETE WHEN RUNNING.
                       //driver.findElement(By.xpath("//*[@id=\"shipacct_number\"]")).clear();
                       //driver.findElement(By.xpath("//*[@id=\"shipacct_number\"]")).sendKeys("39466825");
                       //Thread.sleep(2000);
                        break;
                    case "RTA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"thirdacct_number\"]")));
                        driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).clear();
                        driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).sendKeys(rebillAccount);
                        break;
                    }
         	System.out.println("")   ; 
             if (!length.equals("")) {
            	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[1]/input")));
                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[1]/input")).clear();
                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[1]/input")).sendKeys(length);
             }
             if (!width.equals("")) {
              	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[2]/input")));
                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[3]/input")).clear();
                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[3]/input")).sendKeys(width);
             }
             if (!height.equals("")) {
              	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[4]/input")));
                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[4]/input")).clear();
                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[4]/input")).sendKeys(height);
             }
             if (!actualWeight.equals("")) {
              	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("  /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[2]/div[2]/input")));
                 driver.findElement(By.xpath("  /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[2]/div[2]/input")).clear();
                 driver.findElement(By.xpath("  /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[2]/div[2]/input")).sendKeys(actualWeight);
             }
            
             
         	System.out.println("")   ;   
           
            
             
             
             Thread.sleep(2000);
             	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
             	Thread.sleep(10000);
             	}
             	catch(Exception e) {
             		System.out.println("Failed Trying to Rebill..");
             		// Assert.fail("Failed Trying to Rebill..");
             	}

            
             try {
            	 
            //Check For Validation
             }
             catch (Exception e) {
            	 System.out.println("Failed Validating in DB");
            	 Assert.fail("Failed Validating in DB");
             }
            
             //If False.. think maybe there is stat codes to select.
         	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            
         	 if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
 		    	return;
 		    }

            	 try {
            		 //Sometimes just needs to click continue to rebill
            		String tempString =  driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
            		if (tempString.indexOf("Click Rebill to continue")!=-1) {
             		   	driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[1]")).click();
             		   	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();    
             		}
            	 }
            	 catch(Exception e) {
            		System.out.println("Did not find popup about continuing");
            		 
            	 }
            
            //If Rebill Is Not Successful
            
            	 try {
            		 Boolean overrideBoolean;
            		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span")));
            		 List<WebElement> errorList;
            		 errorList=driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span"));
            		 int popupCounter=1;
            	 for (WebElement ele: errorList){
            		 if (popupCounter%2==1){
                         System.out.println("This is checkbox");
                         ele.click();
                         if (ele.isSelected()){
                        	 System.out.println("Could Click");
                         	}
                         else{
                        	 System.out.println("Could Not Click");
                        	 overrideBoolean=true;
                       }
            		 }
            		 popupCounter++;
            	 }
            	 try {
            	 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[1]")).click();
            	 }
            	 catch(Exception e) {
            		 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[2]")).click();
                 	
            		 
            	 }
            	 Thread.sleep(10000);
            } 
            
            catch(Exception e2) {
            	System.out.println("Did not reach override errors or failed here");
            	finalResult="fail";
 		   	    finalDesc="Failed at the end with unknown error";
 		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
 		   		continue;
            }
            	 
            	 //Check For Validation again and save result.
            	 if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
            		 return;
     		    }
            	
            	
          }
    	}
             
        

    
   public synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
		
	//	excelVar.setCellData(rowCountExcel, colCountExcel, outputString);
	//	excelVar.writeCellData();
	}

public void readTrk(String trk) {
	System.out.println(trk);
}
    }
    

