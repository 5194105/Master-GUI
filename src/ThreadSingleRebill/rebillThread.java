package ThreadSingleRebill;

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


public class rebillThread extends Thread{

	ArrayList<data> dataArray;
	config c;
	WebDriver driver;
	WebDriverWait wait;
	String source, result, svcType,descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, 
	invoiceNbr2, region , login , password, rsType , company , prerate,workable, rowNumber,length,width,height,actualWeight,
	browser,levelUrl,headless,homePath,databaseDisabled,level;
	int waitTime;
	int attempts=0;
	int maxAttempts;
	driverClass dc;
	validateClass vc;
	Boolean running=true;
	int runningCounter;
	data d;
	
	public rebillThread(ArrayList<data> dataArray,config c) {
		//Gets data we sent on the data array.
		this.dataArray=dataArray;
		//load the config file
		this.c=c;
		//Gets the url
		levelUrl=c.getRebillL3Url();
		//Disables connecting to database
		databaseDisabled=c.getDatabaseDisabled();
		//Determines if source is excel or DB
		source=c.getSource();
		//Gets how long to wait before timing out
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		//Determines which level it is.
		level=c.getLevel();
		//Custom Class that loads the driver.
		dc = new driverClass(c,levelUrl,waitTime);
		//Loads the validation class with flag as era_rebill
		vc= new validateClass(c,databaseDisabled,"era_rebill");
	}
	
	public void run () {
		
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
				length=d.getLength();
				width=d.getWidth();
				height=d.getHeight();
				actualWeight=d.getActualWeight();
				svcType=d.getSvcType();
				
				//Check if track is already successful. If it 
				//is then we remove it from our arraylist
				if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
					dataArray.remove(d);
					continue;
		    }
		   
				try {
					//Executes main method of the class
					doRebill(testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType ,company , prerate,length,width,height,actualWeight,workable);
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
			driver.findElement(By.id("okta-signin-username")).sendKeys(login);
			driver.findElement(By.id("okta-signin-password")).sendKeys(password);
			driver.findElement(By.id("okta-signin-submit")).click();
			}
    		catch(Exception e) {
    			System.out.println(e);
    		}
    	}
    
   
    
    //Main bulk of the code in this method.
    public void doRebill(String testInputNbr,String tinCount,String trkngnbr,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2 ,String region ,String login ,String password,String rsType ,String company ,String prerate,String length,String width,String height,String actualWeight,String workable) throws InterruptedException {
    	//Just declaring variables to be used later.
    	String finalResult="";
    	String finalDesc="";
    	WebElement element=null;
    	
    	int packageCounter=0;
    	Boolean exist;
    	WebElement scrollElement;
    	int tempCounter=0,tempCounter2=0;
    	String tempString;
    	
    	//Going to set the max amount of times to try this trk.
    	maxAttempts=3;
    	for (int i=0;i<maxAttempts;i++) {
    		//Attemp to login.
	    	login();
	    	JavascriptExecutor js= (JavascriptExecutor) driver;
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
    	
    	
    
   
    	 
    	 /*
    	 *****************************************************************************
    	 *
    	 *Getting Dropdown Details Right Side
    	 *
    	 *
    	 ****************************************************************************
    	 */
    	 
     	
        
    	     try{
		    	 System.out.println("Selecting from the rebill dropdown.");
		         //Getting Action Dropdown. Will RB everytime.
		         Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
		         actionDropDown.selectByValue("RB");
		         
		         //For domestic. Since domestic have one set of dropdown values and intl has another set, we must seperate them.
		         if (login.equals("5194105") ){
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
		        	 		//This is a default value.. so need to select anything.
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
    	  	System.out.println("Failed at the dropdown info");
			finalResult="fail";
	   	    finalDesc="failed at the dropdown info";
	   	    
	   	 //Will search for oracle DB error one more time here if it did get this far (dont think it would if oracle error).
	   	 if (vc.searchOracleDBError(testInputNbr, tinCount, trkngnbr, invoiceNbr1)==true){
	   		 return;
	   	 }
	   	 else {
	   		vc.writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,"");
	   	 }
	   		continue;
      }
    	     
    //If requires a prerate, then need to select additional stuff.. need to add more stuff here.	  
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
    	 *Getting to Contact Method Screen.
    	 *
    	 *
    	 ****************************************************************************
    	 */
        
        
    	    	 
    	    	 
    	    	 
    		 
    	    	 
    	    	 
    	   
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
	   		continue;
	    	   				
	      }
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
        	 if (login.equals("5194105")){
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
         	  continue;
    	  } 
    
         //Now we are going to see ithere was a pop up after click continue on contact method screen.
         wait=new WebDriverWait(driver,1);
         driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
         tempCounter=0;
         //Same loop logic again. Will check if it made it to rebill screen. If not, check for popup.. try this 10 times.
         while(tempCounter<=10) {   
        	 tempCounter++;
         	 try {
         	   //Fount rebill screen.
         		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
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

         
         
         
 			/*
         	 *****************************************************************************
         	 *
         	 *Rebill Screen
         	 *
         	 *
         	 ****************************************************************************
         	 */	
         	   			
         	   			
         	   			
            
    	 	
      
             try{    
            	
            	 switch (reasonCode){
             		case "RRA" :
                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reciptacct_number\"]")));
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).clear();
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(rebillAccount);
                       break;
                    case "RSA" :
                       break;
                    case "RTA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"thirdacct_number\"]")));
                        driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).clear();
                        driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).sendKeys(rebillAccount);
                        break;
                }
            	 
         	
            	 //If Length is not null
	             if (!length.equals("")) {
	            	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[1]/input")));
	                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[1]/input")).clear();
	                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[1]/input")).sendKeys(length);
	             }
	             //If Width is not null
	             if (!width.equals("")) {
	            	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[2]/input")));
	            	driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[2]/input")).clear();
	                driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[2]/input")).sendKeys(width);
	             }
	             //If height is not null
	             if (!height.equals("")) {
	            	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[2]/input")));
	                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[3]/input")).clear();
	                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[5]/div[3]/input")).sendKeys(width);
	             }
	             //If actual weight is not null
	             if (!actualWeight.equals("")) {
	              	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("  /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[2]/div[2]/input")));
	                 driver.findElement(By.xpath("  /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[2]/div[2]/input")).clear();
	                 driver.findElement(By.xpath("  /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[3]/div[4]/div[2]/div[2]/input")).sendKeys(actualWeight);
	              }
	             //If sevice type is not null
	             if (!svcType.equals("")) {
	              	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[4]/div[1]/div/input")));
	                 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[4]/div[1]/div/input")).clear();
	                 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[4]/div[1]/div/input")).sendKeys(svcType);
	             }
	               
	            //Click on rebill
	            Thread.sleep(3000);
             	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
             	
             	//Click on rebill again (try to)
             	try {
             		driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
             	}
             	catch(Exception e) {
             		System.out.println("Could not click rebill twice.. thats okay.");
             	}
             	
             }
             catch(Exception e) {
             	System.out.println("Failed on rebill screen");
             	// Assert.fail("Failed Trying to Rebill..");
             	System.out.println(e);
             	continue;
             }
             
             //Give it 10 seconds to process before checking for validation.
             Thread.sleep(10000);
                        
             //Will check if validation is there. Increased the wait time because this part can take longer.
         	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            //If returns true, tracking has passed and will go to next trk.
         	if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
 		    	return;
 		    }

         	//We have reached here because we clicked rebilled, but trk was not in oracle or era db.
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
            
            //If Rebill Is Not Successful and need to override something.
            try {
            	//Getting the pop and will also get the list of errors.
            	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span")));
            	 List<WebElement> errorList;
            	 errorList=driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span"));
            	 int popupCounter=1;
            	 //We will store each element in this popup. For each row there is a checkbox (element 1) and description (element 2).
            	 //We will check if we can override the error checkbox. To this we will select all ODD elements, which is count/2 with mod of 1.
            	 for (WebElement ele: errorList){
            		 popupCounter++;
            		 if (popupCounter%2==1){
            			 System.out.println("This is checkbox");
                         try {
                        	 ele.click();
                         }
                         catch(Exception e) {
                        	 System.out.println("Could Not Override Error");
                        	 //In the ERA DB there will be a table that contains the error.. we will pull from there and move to next trk.
                        	 vc.validateRebill(testInputNbr,tinCount,trkngnbr);
                        	 return;
                         }
                     }
            	 }
            	
            	 //This code will be avalible if we were able to override the errors. The button sometimes
            	 //Change error which is why you will see exception is to click the other button.
            	 try {
            		 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[1]")).click();
            	 }
            	 catch(Exception e) {
            		 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[2]")).click();
                 }
            	 
            	 
            } 
            //Generic error here i think..
            catch(Exception e2) {
            	vc.validateRebill(testInputNbr,tinCount,trkngnbr);
            	return;
            }
            
            
            //Giving it another 10 seconds to process.
            Thread.sleep(10000);
            //Check For Validation again and save result.
            if (vc.validateRebill(testInputNbr,tinCount,trkngnbr)==true) {
            	return;
     		}
            else {
            	return;
            }
         }
    }
             
        

    //Not Ready.
   public synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
		
	//	excelVar.setCellData(rowCountExcel, colCountExcel, outputString);
	//	excelVar.writeCellData();
	}
}
    

