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
import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class creditDebitThread extends Thread{
	config c;
	driverClass dc;
	validateClass vc;
	data d;
	
	ArrayList<data> dataArray;
	List <WebElement> optionsInnerText,errorList;
	
	WebDriver driver;
	WebDriverWait wait;
	WebElement scrollElement,element;
	Select actionDropDown;
	
	JavascriptExecutor js;
	
	String result,descripiton,result2,descripiton2,testInputNbr,tinCount,trkngnbr,invoiceNbr1,invoiceNbr2,region ,username ,textContent,
		password,workable,reasonCode, reasonCategory, rootCause, valDesc,eraCase,levelUrlTemp,level,disputeNumber,finalDesc,
		company,comments,rsType,billAcctNbr,browser,homePath,levelUrl,headless,databaseDisabled,source,finalResult,tempString;
	
	Boolean running=true,exist,validated,overrideBoolean;
	
	int runningCounter,waitTime,attempts,maxAttempts=3,packageCounter,counter,tempCounter,popupCounter=1;
	
	
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
		levelUrlTemp=levelUrl;
		//Will run forever with this statement.
		while(true) {
			//This will iteriate through entire arraylist. if this ran again.. which will
			//most likely happen as there is probably going to be a trk that fails..
			//then we need to create a new data array with our data array where we deleted
			//some entries because they were successful. that is why new keyword is there.
			for(data d: new  ArrayList<data>(dataArray)) {
			
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
				result2=d.getResult2();
				descripiton2=d.getDescription2();
				reasonCode=d.getReasonCode();
				company=d.getCompany();
				comments=d.getComments();
				rsType=d.getRsType();
				billAcctNbr=d.getBillAcctNbr();
		
				//Setting validation flags
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
				if(eraCase.equals("5")){   
					vc.setFlag("era_rebill_resolve");
				}
				
				//Validate if already completed.
				if (vc.validateCreditDebit(testInputNbr,tinCount,trkngnbr,valDesc)==true) {
					vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null);
					dataArray.remove(d);
					continue;
				}
				
				//Getting Dispute Case Number
				if(eraCase.equals("4")|| eraCase.equals("5")){  
					disputeNumber=vc.getDisputeCase();
					if(disputeNumber.equals("")) {
						System.out.println("No Dispute Found");
						vc.writeToDb(testInputNbr, tinCount, trkngnbr, "fail", "dispute not found", null);
						dataArray.remove(d);
						continue;
					}
				else {
					//This is to add dispute case number at end of url
					levelUrl=levelUrlTemp;
					levelUrl=levelUrl+"index.html?caseID="+disputeNumber;
							 
				}
			}
				
			enterDataStep1(testInputNbr,tinCount,trkngnbr,invoiceNbr1);
			doCreditDebit(eraCase, result,  descripiton, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2,  region , username , password, workable,reasonCode,reasonCategory,rootCause,valDesc);
			enterContactMethodStep3(testInputNbr,tinCount,trkngnbr,invoiceNbr1,username,eraCase,valDesc);
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
    		System.out.println("Could Not Login");
    	}
    }
    
    
    public void enterDataStep1(String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1) {
    	
    	//Do X amount of times
    	maxAttempts=3;
    	for (int i=0;i<maxAttempts;i++) {
    		//Attemp to login.
	    	login();
	    	js= (JavascriptExecutor) driver;
	    	try {
	    	    //Will hit the clear button. This is for whenever we switch to new tracking number (Not needed since we just open new browser  
	    		//driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a")).click();
	    		//Print tracking number to tracking number textbar.
	    		driver.findElement(By.xpath("//*[@id=\"trackingID\"] ")).sendKeys(trkngnbr);
	
		        //Click on Search
		        driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button")).click(); 
		    	} 
	    		//If we see an error in previous block of code.
		    	catch(Exception e) {
		    		System.out.println("Failed on Entering Tracking Number");
		    		finalResult="fail";
		    		finalDesc="Failed on Entering Tracking Number";
			   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		    	}
    		
	    	
	    	
	    	/////////////////////////////////////CLICK PACKAGE TAB///////////////////////////////////////////////////////////////////////////////
	    	
	    	
	    	
	      	//Try to Click Package Tab
	    	//Changing wait time to one second. Since this package tab is buggy,
	    	//we will attempt to click it 10 times, waiting one second for each click.
	    	driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
	    	while (tempCounter<10) {
		    	try {  
		    		tempCounter++;
		    		//Because it is buggy, using Jave Script to help click it.
		    		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
		    		js.executeScript("arguments[0].click()", element);    
		    		//This is going to verify if package tab is clicked. it will do so by seeing if a pre-defined string i gave is visibile on the next screen.
		    		tempString=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/div[1]/span[1]")).getText();
		    		
		    		if(tempString.equals("Charge Code Description")) {
		    			break;
		    		}
		    	}
		    	//If for it cannot click on package tab then it will run this...
		    	//Most of time it will ask which invoice number you want to perform on...
		    	//We give the correct one.
		    	catch(Exception e) {
		    		try {  
		    			driver.findElement(By.xpath(" /html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys(invoiceNbr1);
		        		Thread.sleep(1000);
		        		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
		        		driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
		        		//Will now try to click package tab
		        		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
		            	js.executeScript("arguments[0].click()", element);
		    		}	
		    		catch(Exception ee) {
		    			System.out.println("Could not click on package tab or handle popup box for selecting invoice.");
		    		}
		    	}
		    }
		    //If after 10 times then we will mark as error in gtm db and move to next trk.
	    	if(tempCounter>=10) {
    			//Will check if the invoice is in error in oracel ar.. we dont want to do this
	    		//for all tracking number since it will slow down the program doing this check.
	    		//If it does have an error it will write to DB and skip to next trk.
    	   		if (vc.searchOracleDBError(testInputNbr, tinCount, trkngnbr, invoiceNbr1)==true){
    		   		//If we find the error, we will exit this method and skip to next trk.
    	   			return;
    	    		}
    	    		else {
    	    			finalResult="fail";
    	    	   	    finalDesc="Could Not Get To Charge Code Details";
    	    	   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
    	    		}
    	   		//Skip to next attampt for this trk.
    	   		continue;
             }
        		
     
	    	
	    	
	    	
	    	/* * * * * *    * * * * * * *	* * * * * * * 	*         *
	    	 * 			*	*			*	*				*	    *
	    	 * 			*	*			*	*				*	  *
	    	 * 			*	*			*	*				*	*			
	    	 * 			*	*			*	*				* *
	    	 * * * * * * 	* * * * * * *	*				*
	    	 * 				*			*	*				* *
	    	 * 				*           *	*				*   *
	    	 * 				*			*	*				*	  *			*
	    	 * 				*			*	*				*       *
	    	 * 				*			*	*				*         *
	    	 * 				*			*	* * * * * * *	*           *
	    	 */				
	    	
	    	
	    	
	    	//////////////////////////////////////////////////STAT CODE CLICK////////////////////////////////////////////////////////////
	    	
	    	
	    	
	    //Switching back to normal wait time.
    	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
    	//We will count the number of stat codes there are and then click the very last one.
    	//Why? because this is dynamic value and have to get an actual hard count before we decide which
    	//clickbox to click on.
    	try{
    		packageCounter=0;
                while(true){
                	//We are just seeing this this stat code appears.. if so.. we count up by one.
                	driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
                	exist= driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+packageCounter+"\"]")).isDisplayed();
                	packageCounter++;
                }
            }
    		//If we cannot find another stat code.. we ran out of stat code and now the stat code counter is set.
            catch(NoSuchElementException e){
                System.out.println("No Such Element... Got all the counters for charge codes");
        }
     	
    	//Click on all Charge Codes by selecing last one (net)
    	 try {
    		 driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
    		 //Sometimes if not visible on screen, it wont select.. so we use javascript to make it more accurate.
             js.executeScript("window.scrollTo(0,500)");
             scrollElement =  driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]"));
             js.executeScript("arguments[0].scrollIntoView();", scrollElement);
             //Here we using the package counter to determine which one is the last cone.
             driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]")).click();
             System.out.println("Clicked All Stat Codes");
        }
        catch(Exception e){
        	 System.out.println("Could Not Clicked All Stat Codes");
        	 System.out.println(e);
        	 finalResult="fail";
        	 finalDesc="could not click on all stat codes";
 	   		 vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
        }
    }
}
    
    
    
    
    
    
    
    
    ////////////////////////////////////////////PART TWO////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    public void doCreditDebit(String eraCase, String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2, 
    		String region ,String username ,String password,String workable,String reasonCode,String reasonCategory,String rootCause,String valDesc) {
    	try{
    		 actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
    		 textContent="";
    		 optionsInnerText=null;
    		 
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
			else if (eraCase.equals("4")) {
				 actionDropDown.selectByValue("RC");
				 Thread.sleep(1000);
			}
			else if (eraCase.equals("5")) {
				 actionDropDown.selectByValue("RR");
				 Thread.sleep(1000);
			}
		
		try {
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
		}
		catch(Exception e) {
			System.out.println("reasonCategory probably null");
		}
		
		Thread.sleep(1000);
		//Reason code
		if(!reasonCode.equals("")) { 
			if(eraCase.equals("5")) {
				try {
					if (username.equals("5194105") || username.equals("584168")){
						switch (reasonCode){
							case "RRA" :
								driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[2]")).click();
								break;
							case "RSA" :
								driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[3]")).click();
								break;
							case "RTA" :
								driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[4]")).click();
								break;
							case "RBS" :  
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
			                	 driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[17]")).click();
			                     break;
			                 case "KPR" :
			                	 break;
			                 case "RSD" :
			                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[18]")).click();
			                    break;
			                 case "RBS" :
			                	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[5]")).click();
			                    break;
			             }
			        }
				}
				catch(Exception e) {
					System.out.println("Failed Picking Reason Code");
					vc.writeToDb(testInputNbr,tinCount,trkngnbr,"fail","Failed Picking Reason Code",null);
				}
			}
		
			//Selecting next dropdown.
			actionDropDown = new Select (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
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
	 

//////////////////////////////////PART 3/////////////////////////////////////////////////////////////

	public void enterContactMethodStep3(String testInputNbr,String tinCount,String trk,String invoiceNbr1,String username,String eraCase,String valDesc) {
		try {
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
	      	
	     
	      	
	      	
	      	
	      	 //Trying to click the proceed button
	         try {
	         	Thread.sleep(5000);
	         	driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();         	
	         		}
	         	catch(Exception e0) {
	         		System.out.println("Clould not click proceed button after dropdown");
	         		
	         	}
	         	//We are giving a small wait time again. The reason why is because we are seeing if there is a popup.
	         	//The way it works is that if we see the contact details screen.. we will break this loop and move on.
	         	//If we dont, we will check for popup message/error. We will wait one second for this to open for each attempt.
	         	tempCounter=0;
	         	wait=new WebDriverWait(driver,1); 
	     	    driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
	         	
	     	    
	     	    
	     	    while(tempCounter<10) {
		         	try { 
		         		tempCounter++;
		         		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
		                break; 
		         	}
		         	catch(Exception e1) {
		         		System.out.println("Not able to go to contact screen.");
		         		 try {
		         			 //Checking the popup string
		         			 String tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
		         			 //Depending on what the string says we will do a certain item. Most involve into going into next tracking number.
		         			 if (tempError.equals("Trying To Rebill A Partial Amount")) {
		         				 System.out.println(tempError);
		         				 finalResult="fail";
		                 		 finalDesc="Trying To Rebill A Partial Amount";
		                 		 vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		                 		 return;
		         			 }
		         			 
		         			 if (tempError.contains("Cannot Credit An AirBill For More Than The Invoice Amount Due")) {
		         				 System.out.println(tempError);
		         				 finalResult="fail";
		                 		 finalDesc="Cannot Credit An AirBill For More Than The Invoice Amount Due";
		                 		 vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		                 		 return;
		                      }
		         	   			
		         			  if (tempError.indexOf("interline")==1) {
		         				 System.out.println(tempError);
		         				 finalResult="fail";
		                 		 finalDesc="interline acct";
		                 		 vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		                 		 return;
		                       }
		         			  
		         			  if (tempError.indexOf("Approval Limit")==1) {
		         				  System.out.println(tempError);
		                     	  finalResult="fail";
		                     	  finalDesc=tempError;
		                     	  vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		                     	  return;
		                      }
		             	   	
		         			  if (tempError.indexOf("specialist")==1) {
		         				  System.out.println(tempError);
		         				  finalResult="fail";
		         				  finalDesc="specialist error";
		         				  vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		         				  return;
		                       }
		             	   	
		         			  if (tempError.indexOf("Adjustment can not be done for discount amount")==1) {
		         				  System.out.println(tempError);
		         				  finalResult="fail";
		         				  finalDesc="Adjustment can not be done for discount amount";
		         				  vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		         				  return;
		                       }
		             	   
		         		//Clicking okay on the popup.
		         		System.out.println("Found Pop Up"); 
		          		driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
		         		}
		         	catch(Exception e) {
		         		System.out.println("Could not move on past dropdown details");
		         	}
		         }
		      }
	     	   // Unable to determine why couldnt go to contact menu
		      if(tempCounter>=10) {
				finalResult="fail";
		   	    finalDesc="Could not go to contact detail screen";
		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		   		return;
		    	   				
		      }
		   
	       //Returning to normal wait time.
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
	         		 
	         System.out.println("Contact Menu");
	         //Dont remember why this is here.
	         js.executeScript("window.scrollBy(0,-100)");
	          
	         try{
	          //Click on rebill RPI Complete (dom only), Phone, and Continue .
	        	 if (username.equals("5194105")){
	        		 driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
	             }
	        	 
	        	 //Selecting phone contact method.
	             Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
	             contactMethodDropDown.selectByValue("phone");  
	             Thread.sleep(1500);
	             
	             try {
	            	 //Will try to get to rebill screen. Setting down to 1 second wait again.
	            	 //This continue button is bugged so we are going to keep clicking it 
	            	 //until we dont see it anymore then check if next screen is what we want.
	            	  driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
	                  tempCounter=0;
	                  while (tempCounter<10){
	                	  tempCounter++;
	                	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
	                	  Thread.sleep(1000);
	                  }
	              }
	              catch (Exception continueException) {
	            	  System.out.println("Could Not Click Continue Again");
	              }
	          }
	         //Generic error in contact section.
	          catch(Exception e1) {
	        	  System.out.println("Failed Selecting Contact Method and Clicking Continue");
	        	  finalResult="fail";
	        	  finalDesc="failed on contact menu";
	        	  vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
	         	  return;
	    	  } 
	    
	         //Now we are going to see ithere was a pop up after click continue on contact method screen.
	         wait=new WebDriverWait(driver,1);
	         driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
	         tempCounter=0;
	         //Same loop logic again. Will check if it made it to rebill screen. If not, check for popup.. try this 10 times.
	         while(tempCounter<=10) {   
	        	 tempCounter++;
	         	 try {
	         	   //Found rebill screen.
	         		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
	         		 break;
	         	  }
	         	  catch(Exception e) {
	         	   		try {
	         	   		System.out.println("Checking for popup after contact menu.");
	         	   		String tempError= driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/h4")).getText();
	         	   		
	         	   		if (tempError.indexOf("Management approval")!=-1) {
	         	   			System.out.println(tempError);
	         	   			finalResult="fail";
	                 		finalDesc="Management approval";
	                 		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
	                 		return;
	                     }
	         	   			//Click on okay in popup
		         	   		driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
		         	   	}
	         	   		catch(Exception ee) {
	         	   			System.out.println("Could Not Get to Rebill Screen");
	         	   		}
	         	   	}
	             }
	         
	         if (tempCounter>=10){
	            
	 	   			finalResult="fail";
	 		   	    finalDesc="could not get to rebill screen after contact menu";
	 		   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
	             }

	          
	         //Dispute Only
	         if (eraCase.equals("3")) {
	        	 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/div[2]/div/div/div/input")).sendKeys("Stephen");
	        	 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[3]/div/div/div/input")).sendKeys("Daniel");
	        	 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[4]/div/div/div/input")).sendKeys("1234567890");
	        	 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[5]/button[1]")).click();
	        	 Thread.sleep(1000);
	     	}
	        
	        if (eraCase.equals("5")){
	        	try{    
	        		switch (reasonCode){
	               		case "RRA" :
	               			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reciptacct_number\"]")));
	                        driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).clear();
	                        driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(billAcctNbr);
	                        break;
	                     case "RSA" :
	                    	break;
	                     case "RTA" :
	                    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"thirdacct_number\"]")));
	                         driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).clear();
	                         driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).sendKeys(billAcctNbr);
	                         break;
	                     }
	            }
	        	catch(Exception e) {
	        		System.out.println("Couldnt select case at end");  
	            }
	            
	        	Thread.sleep(2000);
	            driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
	            
	            try {
	            	//If False.. think maybe there is stat codes to select.
	                driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	                
	                if (vc.validateCreditDebit( testInputNbr, tinCount, trkngnbr, valDesc)==true) {
	                	vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null);
	                	return;
	         		}
	            }
	            catch(Exception e) {}
	            
	            try {
	            	//Sometimes just needs to click continue to rebill
	            	tempString =  driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
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
	            	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span")));
	                errorList=driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span"));
	                for (WebElement ele: errorList){
	                	//Check Box
	                	if (popupCounter%2==1){
	                		ele.click();
	                        if (ele.isSelected()){
	                        	System.out.println("Could Click");
	                        }
	                        else{
	                        	System.out.println("Could Not Click");
	                            overrideBoolean=true;
	                            vc.validateRebill(testInputNbr,tinCount,trkngnbr);
	                            return;
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
	             }
	             catch(Exception e) {
	             System.out.println("Couldnt find pop error at end of rebill");
	             }
	             
	            Thread.sleep(10000);
	            if (vc.validateCreditDebit( testInputNbr, tinCount, trkngnbr, valDesc)==true) {
	            	vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null);
	                return;
	           }
	           else {
	        	   vc.writeToDb(testInputNbr, tinCount, trkngnbr, "fail", "error at end of rebill resolve", null);
	           }
	        }
	        driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
	        Thread.sleep(10000);
	       
	        //checkValidation(type,testInputNbr,tinCount,trk,valDesc,rowNumber);
	        if (vc.validateCreditDebit( testInputNbr, tinCount, trkngnbr, valDesc)==true) {
	        	vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null);
	        }
	        else {
	        	vc.writeToDb(testInputNbr, tinCount, trkngnbr, "fail", "made it to end, but failed", null);
	        }
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
