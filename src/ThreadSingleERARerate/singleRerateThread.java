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
	config c;
	driverClass dc;
	validateClass vc;
	ArrayList<data> dataArray;
	WebDriver driver;
	WebDriverWait wait;
	
	String result,  description, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2,
	 rateWeight, actualWeight, wgtType,  length, width, height, 
	 workable, dimType, payor, billAcctNbr, serviceType, packageType,
	 rerateType, region, username, password, rsType, company, valDesc,comments,serviceName,
	 browser, homePath, levelUrl, headless,databaseDisabled,source,level;
	
	Boolean running=true;
	int runningCounter,waitTime, attempts=0,maxAttempts=3;
	data d;
	
	public singleRerateThread(ArrayList<data> dataArray,config c) {
		//imports our data from base class
		this.dataArray=dataArray;
		//imports config from base class
		this.c=c;
		//This is set in importData class
		levelUrl=c.getRebillL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		//Default wait time allowed.
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		level=c.getLevel();
		//imports our selenium driver info
		dc = new driverClass(c,levelUrl,waitTime);
		//creates our validation class which will validate if successful or not.
		vc= new validateClass(c,databaseDisabled,"era_rerate");
	}
	public void run () {
		//Will run forever with this statement.
		while(true) {
			//This will iteriate through entire arraylist. if this ran again.. which will
			//most likely happen as there is probably going to be a trk that fails..
			//then we need to create a new data array with our data array where we deleted
			//some entries because they were successful. that is why new keyword is there.
			for(data d: new  ArrayList<data>(dataArray)) {
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
				
				//Check if track is already successful. If it 
				//is then we remove it from our arraylist
			    if (vc.validateRerate(testInputNbr,tinCount,trkngnbr)==true) {
			   // 	dataArray.remove(d);
			   //	continue;
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
			//Will try to quit previous driver (there wont be one if this is first trk).
	    	//If it is first track it will just print an exception and continue the code.
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
	
		WebElement element=null,scrollElement;
		
		int packageCounter=0,tempCounter=0;
		String finalResult,finalDesc,tempString;

		//Will attempt each trk what value we set here. IF there is specific error we 
		//can break this max attampt loop and jsut move on to next trk
		maxAttempts=3;
		for (int ii=0;ii<maxAttempts;ii++) {
			//Logins to new browser for each attempt
			login();
			JavascriptExecutor js= (JavascriptExecutor) driver;
			//Setting default wait time here
			wait=new WebDriverWait(driver,20);
			driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
			 	
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
			   		continue;
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
		    		//element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[1]/ul/li[3]/a")).click();
		    		
		    		//js.executeScript("arguments[0].click()", element);    
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
	                	Boolean exist= driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+packageCounter+"\"]")).isDisplayed();
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
	    	
    	
			
		
			 
			 
			 
			 
			 
			 /*
			 *****************************************************************************
			 *
			 *Getting Dropdown Details Right Side
			 *
			 *
			 ****************************************************************************
			 */
			 
		 	
		     try{
		    	 //Getting Action Dropdown. Will RB everytime.
		         Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
		         actionDropDown.selectByValue("RT");
		         Thread.sleep(3000);
		         //Select proceed button
		  		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
		  		 Thread.sleep(2000);
		  		
		  		 try {
		  			 //Checking for popup
		  			 driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
		    		 String error = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
		    		 finalResult="fail";
		        	 finalDesc=error;
		 	   		 vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		    		 return;
		    	 }
		    	 catch(Exception e) {
		    		 System.out.println("No Popup");
		    	 }
		     }
		     catch(Exception e) {
		    	 System.out.println("Failed at Drop Down");
			}
		
		    //Setting default timmer back.
		    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		   
			 
		    
		    
		    /////////////////////////////////////RERATE SCREEN////////////////////////////////////////////
		    
		    
		    
		    
		    
		 try {
			 //If in our DB rerateType is weight, will go here.
			 if(rerateType.contains("weight")) {
				 //Because sometimes actual weight or package weight is disabled in gui.. we will just fill out the weight in gui reguardless of which ever is there.
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
			 
			 //Checking DIM
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
				 //Checking if it is IN (inches -- USA) or CM (centimeter -- non USA). IF blank then will take default
				 if (!dimType.equals("")) {
					 if (dimType.equals("IN")) {
						 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[5]/div[2]/div/div/label/span")).click(); 
					 }
					 if (dimType.equals("CM")) {
						 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[5]/div[3]/div/div/label/span")).click();
 					 }
				 }	 
			 }
			 
			 //Payor option
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
			finalResult="fail";
       	 	finalDesc="Couldnt Correct Input";
	   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		}
		
		//Checking for service
		try {
			if(rerateType.contains("service")) {
				Select sel = new Select(driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div[2]/div/div/div/select")));
				if (!serviceType.equals("")) {
					sel.selectByVisibleText(serviceType);
				}
				
				sel = new Select(driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div[3]/div/div/div/select")));
				sel.selectByVisibleText(serviceName);
				}
			}
		catch(Exception e) {
			finalResult="fail";
	       	finalDesc="Couldnt Find Service";
		   	vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
			return;
		}
			
		 
		 
		 try {
		 //Click Rerate
		 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[1]")).click();
		 }
		 catch(Exception e) {
			 finalResult="fail";
		     finalDesc="Could not click rerate";
			 vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
			 return;
		 }
		 
		 //We will check for a popup
		 driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		 tempCounter=0;
		 tempString="";
		 
		 //Will attempt 10 times
		 while(tempCounter<10) {
			 try {
				 tempCounter++;
				 //This will check if there is a popup by seeing if we can access random element on screen (ie non popup). 
				 //If we have, break the loop and move on. if not.. means we probably hit a popup.
				 System.out.println(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/div[1]/div/div/div")).getText());
				 break;
			 }
			 //If not able to move to next screen and probably found a popup.
			catch(Exception e) {	
				try {
					//Getting amount of errors on popup
					List<WebElement> rowCounts =driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/div/div[1]/div[2]/div/div/div"));
					if ( rowCounts.size()!=0) {
						for(WebElement we:rowCounts) {
							//Getting all the errors from each row and storing it into a string.
							System.out.println(we.getText());
							tempString=tempString+" "+we.getText();
							System.out.println(tempString);
						}
						//Sometimes the string is too long for our DB so we shorten the message to first 300 chars
						String tempErr;
						if (tempString.length()>300) {
							tempErr=tempString.substring(0,300);
						}
						else {
							tempErr=tempString;
						}
						
						finalResult="fail";
		   				finalDesc=tempErr;
		   				vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
		   				return;
					}
				}
				catch(Exception ee) {
					System.out.println("Found error on popup, going to next trk");
				}
			}
		}
		//After 10 attempts if we still not reached the next screen we will send to error and try again.
		if (tempCounter>=10) {
			finalResult="fail";
			finalDesc="Timeout on Rerate Screen";
			vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
			continue;
		}
		
		
		
		
		
	
		
		
		
		
		
		
		
		try {
			//Set default timer back.
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			//Click close on rerate menu and give 10 seconds to process.
	 		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[3]")).click();
			System.out.println(10000);
		}
		catch(Exception e) {
			finalResult="fail";
			finalDesc="could not click process after rerate screen";
			vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
			continue;
		}
		
		////////////////////////AFTER RERATE SCREEN//////////////////////////////////////////////
		
		
		 try {
			 driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		     tempCounter=0;
		     
		     while (tempCounter<10) {
		    	 tempCounter++;
		    	 //This will scroll the page till the element is found which is the process button
		    	 element = driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[9]/div[1]/button"));
		    	 js.executeScript("arguments[0].scrollIntoView();", element);
			     driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[9]/div[1]/button")).click();
				try {
					//if normal continue
					tempString = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).getText();
					if (tempString.equals("Continue"))	{
						break;
					}
				}
				catch(Exception e) {    		
					//Should something be here?
					}
					    	
				try {		
					//if adjust by value
					tempString = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[3]/button[2]")).getText();
					if (tempString.equals("Continue"))	{
						break;
					}
				}
				catch(Exception e) {    		
				}
			}
		}
		catch(Exception e) {
			finalResult="fail";
			finalDesc="found adjustment screen but failed there";
			vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
			continue; 
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
		 //Check if popup is there. If not it will go to exception and continue.
		 try {
			 driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
			 tempString= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
			    if (tempString!=null) {
			    	finalResult="fail";
					finalDesc=tempString;
					vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
					return;
			    }
		 }
		 catch(Exception e) {
			 System.out.println("No Error Before Phone");
		 }
		 
		 
		 
		 
		 
		 //////////////////////////Contact Method////////////////////////////////////////
		 
		 
		 //Contact Method Menu
		 try{
			 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			 //Click on rebill RPI Complete (if dom), Phone, and Continue
		      if (username.equals("5194105")){
		    	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
		      }
		      
		      Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
	          contactMethodDropDown.selectByValue("phone");  
	          Thread.sleep(1500);  
		  
		      //Keep clicking until button is not there... give 10 tries
	          driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	          tempCounter=0;
		      while (tempCounter<10)
		    	  try {
		    		  tempCounter++;
		    		  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
		    	  }
		      	  catch(Exception e) {
		      		  System.out.println("Could Not Click Last Continue... should be rerated now");
		      		  break;
		      	  }
		      Thread.sleep(5000);
		      //Checking if popup is there.
		      try {
		    	  String tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
		    	  if (tempError.equals("Conflicting Case check call failed")) {
		    		  System.out.println(tempError);
		    		  finalResult="fail";
		    		  finalDesc="Conflicting Case check call failed";
		    		  vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
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
			 finalResult="fail";
			 finalDesc="Failed Selecting Contact Method and Clicking Continue";
    		 vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
    		 return;
		 }
		 
		 
		 
		 
		 
		 ////////////////////////////PROCESSING////////////////////////////////////
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 //Waiting 15 seconds to process.
		 Thread.sleep(15000);
		 
		 try {
			 if (vc.validateRerate(testInputNbr, tinCount, trkngnbr)==true) {
				vc.writeToDb(testInputNbr, tinCount, trkngnbr, "pass", "completed", null); 
				return;
			}
		 }
		 catch(Exception e) {
		  	System.out.println(e);  
		 }
		}
	}
}
	
	

