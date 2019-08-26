
package rebill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.config;
import configuration.database;
import configuration.excel;

/**
 *
 * @author 5194105
 */
public class rebillMain {
  
	static ArrayList<rebillData> arrayData = new ArrayList<rebillData>();
  //  ArrayList<rebillData> dataArray;
    WebDriver driver;
    int loginCounter=0, singleTrackingNumber=0,totalTrackingNumber=0,
        packageCounter=0,statCodeCounter1=0,statCodeCounter2=0,trackingNumberCounter=-1,retryGetDetailCounter=0,
        retryGetDetailCounterTotal=0,statCodeCounterTotal1, statCodeCount=0,rebillScreenCounter=0,statCodeCountCounter=0;
    WebDriverWait wait;
    Boolean exist,statCodeBoolean,popupExist,pass,popupBoolean;
    PreparedStatement ps;
    Connection con;
    WebElement element, scroll,scrollElement;
    JavascriptExecutor jse;
    Select actionDropDown,reasonCodeDropDown,contactMethodDropDown;
    String trackingNumber,reasonCode,rebillAccountNumber,login,password,tempLogin,
           result, description, test_input_nbr, tin_count,popUpString="Default", invoice_nbr1;
    Boolean level,error,booleanResult,packageLevelBoolean,reasonCodeBoolean, contactMethodBoolean,rebillBoolean,type,popUpBoo,acctMatch=false;
    String temp;
    int row=1;
    int popupCounter=0,pCounter=0;
    List<WebElement> errorList;
    File ff;
    String tempName;
    JavascriptExecutor js;
    int timeOutCounter;
    File  fileWhat;
    File tempFile,chromeDriverFile;
    Boolean source;
     String dbName,dbPassword,dbResults,retryAttempts,secondTimeout,l2URL,l3URL;
     config c;
     Boolean continueBoolean;
     String url;
     By tempElement;
     int stepOneCounter,stepTwoCounter,stepThreeCounter,stepFourCounter,stepFiveCounter,stepSixCounter,stepSevenCounter;
     Boolean enabledBoolean,overrideBoolean;
     int  enabledCounter;
     String rebillResult;
     Boolean stepOneBoolean,stepTwoBoolean,stepThreeBoolean;
     Boolean stepFourBoolean,stepFiveBoolean,stepSixBoolean,stepSevenBoolean;
     Boolean successfulRebill;
     int 	 stepOneLimit=2,
    		 stepTwoLimit=2,
    		 stepThreeLimit=2,
    		 stepFourLimit=2,
    		 stepFiveLimit=2,
    		 stepSixLimit=2,
    		 stepSevenLimit=2;
     String rebillResultStatus,rebillResultDesc,errorMessage;
     excel e;
     
     String tempError;
     Boolean rebillError=false;
     
        public rebillMain(config c) throws InterruptedException{
        	this.c=c;
        	setUpData();
        	resetCounters();
        	
        	//for (int i=0;i<arrayData.size();i++) {
        	for (int i=0;i<10;i++) {
        		resetCounters();
        		setUpNextTrackingNumber(i);
        		writeResults(i+1);
        	}
        	
        	
        	
        	System.out.println("END OF PROGRAM");

    }

    //Will try to login... if it fails will try again. If it fails 3 times in a row program will end.
    public void eraLogin() throws InterruptedException{
            try{
            	setUpDriver();
            	timeOutCounter=Integer.parseInt(c.getSecondTimeout());
            	
                //timeoutcounter is a notepad variable... will X seconds before moving on.
                wait = new WebDriverWait(driver, 10);
                
                driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(login);
        
                driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password); 
            
                driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
                
                tempElement=By.xpath("//*[@id=\"trackingID\"]");
                
                isElementVisible(tempElement,1);
                
            	}
            
                catch(Exception e) {
                	System.out.println(e);
                	errorHandling(1);
                	}
   
            
            		try {
            			  System.out.println("Made It Here Past Login Screen");
						  getTrackingNumber();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	
    			}
        
    
    
    
    
    
    
    //
    public void getTrackingNumber() throws InterruptedException, SQLException, IOException{
        try{
        	stepOneBoolean=true;
        	continueBoolean=false;
             //Set this to zero.. means successful login was established so resetting login counter.         
          
            //In order for clear button to be clickable need to scroll up
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            
            //Will hit the clear button. This is for whenever we switch to new tracking number
           // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a"))));
           
            tempElement=By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a");
            isElementVisible(tempElement,2);
            driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a")).click();

            
            //In login we verified this appears and sending our tracking number to it.
            tempElement=By.xpath("//*[@id=\"trackingID\"]");
            isElementVisible(tempElement,2);
            driver.findElement(By.xpath("//*[@id=\"trackingID\"] ")).sendKeys(trackingNumber);
           
            
            
            //Click on Search
           // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button"))));
            tempElement=By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button");
            isElementVisible(tempElement,2);
            driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button")).click(); 
            

            //Search For Popup
            
            for (int i=0;i<5;i++) {
            	try {
            		 Thread.sleep(1000);
                     driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a")).click();
                     break;
            }
            	catch(Exception e1) {
            		 System.out.println("Could Not Click Package Tab");
            	try {
            		 driver.findElement(By.xpath(" /html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys(invoice_nbr1);
            		 Thread.sleep(1000);
            		 driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
            		 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
            		 System.out.println("Found Pop Up");
            		 break;
            	}
            	catch(Exception e2) {
            		System.out.println("Could Not find Popup");
            	}
            	}
            	Thread.sleep(2000);
            	}
               
            
           // tryToClickElement(driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a")),2,5);     
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoicegridDataCheckBox0\"]")));
            driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a")).click();
        	    
            tempElement=By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[1]/div[2]/div/div[1]/div/label");
            isElementVisible(tempElement,2);

	        	getStatCodeCount();
	        
            }
            catch(Exception e){
                System.out.println("Could not get past get trk screen");
                System.out.println(e);
                errorHandling(2);
                }
 
        }
    
           
              
           
         
            //If successful go get count of how many stat codes to click on.
          
        

    
    public void getStatCodeCount() throws InterruptedException, SQLException, IOException{
    	stepTwoBoolean=true;
        statCodeBoolean=false;
        //This will reset the trk number .. but overall failures keep adding up.
        singleTrackingNumber=0;
        //totalTrackingNumber=0;    
        packageCounter=0;
       // driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS) ;
       // System.out.println ();
        //Will count all the stat codes. Once it fails it will move to next section
        try{
            try{
              //  Thread.sleep (2000);
                while(true){
                    
                	exist= driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+packageCounter+"\"]")).isDisplayed();
                    
                	packageCounter++;
                    System.out.println("packageCounter "+packageCounter);
                    Thread.sleep (500);
                }
            }
            catch(NoSuchElementException e){
                System.out.println(e);
                clickStatCodeAll(packageCounter);
            }
        }
         catch(Exception ee){
        	 
        	 errorHandling(3);
         //   retryGetStatCodeCount();
        }
       // setUpNextTrack(false,"Unknown Failure");
    }
   

    
        public void clickStatCodeAll(int statCount) throws InterruptedException, SQLException, IOException{
        
        	stepThreeBoolean=true;
        statCodeCount=0;
             try {
                 Thread.sleep (1000);
                 js.executeScript("window.scrollTo(0,500)");
                
                 scrollElement =  driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(statCount-1)+"\"]"));
                 js.executeScript("arguments[0].scrollIntoView();", scrollElement);

                 Thread.sleep (1000);
                driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(statCount-1)+"\"]")).click();
                Thread.sleep (1500);
                getDetails();
                
                System.out.println("Clicked All Stat Codes");
            }
            catch(Exception e){
                System.out.println(e);
                errorHandling(4);
                //This will try to click on it again.
                //retryStatCode1(statCount);
            }
            
           
        }
        
        //Get Reason Code, Action, etc
        public void getDetails() throws InterruptedException, SQLException, IOException{
        	stepFourBoolean=true;
        	System.out.println("Inside getDetails");
            try{
        
            statCodeBoolean=true;
            //Getting Action Dropdown. Will RB everytime.
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pkgaction\"]")));
            
            actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
            actionDropDown.selectByValue("RB");
            
          
            
            //Setting up the reasonCode Dropdown.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
            reasonCodeDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
            Thread.sleep(1000);
            //For domestic.
            if (login.equals("5194105")){
                switch (reasonCode){
                    case "RRA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[2]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[2]")).click();
                        break;
                    case "RSA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[3]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[3]")).click();
                        break;
                    case "RTA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[4]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[4]")).click();
                        break;
                    }
                }
            else {//*[@id="invoice-grid"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select
                 switch (reasonCode){
                    case "RRA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[13]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[18]")).click();
                        break;
                    case "RSA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[17]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[17]")).click();
                        break;
                    case "RTA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[19]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[19]")).click();
                        break;
                    case "KPR" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[1]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[1]")).click();
                        break;
                        
                    case "RSD" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[20]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[20]")).click();
                        break;
                        
                        ////*[@id="invoice-grid"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select
                        
                        
                    }
            }
         
         
          //  tryToClickElement(driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")),2,5);   
          //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
            //Will try to click popup if there is one.
           
//Trying To Rebill A Partial Amount
            
            
            
            rebillError=false;
            
            for (int i=0;i<5;i++) {
            	try {
            		 wait = new WebDriverWait(driver, 2);
            		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
                     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
                     break;
            }
            	catch(Exception e1) {
            		 System.out.println("Could Not Click Package Tab");
            	try {
            		 Thread.sleep(1000);
            		tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
            		if (tempError.equals("Trying To Rebill A Partial Amount")) {
            			rebillError=true;
            			errorMessage=tempError;
            		}
            		 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
            		 System.out.println("Found Pop Up");
            		 break;
            	}
            	catch(Exception e2) {
            		System.out.println("Could Not find Popup");
            	}
            	}
            	Thread.sleep(2000);
            	}
            
           if (rebillError==false) {
            getDetailScreen();
           }
           else 
           {
        	   System.out.println(tempError);
           }
          
    }
            catch(Exception e){
                System.out.println(e);
                errorHandling(5);
            }
     
           
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //This includes PRI, phone, etc
        public void getDetailScreen() throws InterruptedException, SQLException, IOException{
        	stepFiveBoolean=true;
        	try{
                statCodeBoolean=false;
                statCodeCounter2=0;   
           
           
            
            //Click on rebill RPI Complete, Phone, and Continue
            
            if (login.equals("5194105")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));

            driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
            }
        
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rmrks\"]")));
            contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
            contactMethodDropDown.selectByValue("phone");  

          //  tryToClickElement(  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")),2,5);    
          //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
           
            
            
            
            
            for (int i=0;i<5;i++) {
            	try {
            		 wait = new WebDriverWait(driver, 2);
            		 driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
            		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
                     break;
            }
            	catch(Exception e1) {
            		 System.out.println("Could Not Click phone details Tab");
            	try {
            		 Thread.sleep(1000);		 
            		
            		tempError= driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/h4")).getText();
            		
            		 if (tempError.indexOf("Management approval")!=-1) {
            			rebillError=true;
            			errorMessage=tempError;
            		}
            		 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[3]/button[2]")).click();
            		 System.out.println("Found Pop Up");
            		 break;
            	}
            	catch(Exception e2) {
            		System.out.println("Could Not find Popup");
            	}
            	}
            	Thread.sleep(2000);
            	}
            
            
            if (rebillError==false){
            rebillScreen();
            }
            
            
        	}
            catch(Exception e){
                System.out.println("getDetailScreen "+e);
                errorHandling(6);
     
        }
            
        }
        
        
   
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
             public void rebillScreen() throws SQLException, InterruptedException, IOException{
            	 stepSixBoolean=true;
                 
             try{    
                popupCounter=0;
             switch (reasonCode){
            case "RRA" :
                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reciptacct_number\"]")));
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).clear();
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(rebillAccountNumber);
                        break;
                    case "RSA" :
                      //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[7]/di[1]/label")));
                     //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[7]/div[1]/label")));
                     //*[@id="shipacct_number"]
                       
                       //TESTING PURPOSE ONLY! DELETE WHEN RUNNING.
                       //driver.findElement(By.xpath("//*[@id=\"shipacct_number\"]")).clear();
                       //driver.findElement(By.xpath("//*[@id=\"shipacct_number\"]")).sendKeys("39466825");
                       
                       
                       
                       //  Thread.sleep(2000);
                         break;
                    case "RTA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"thirdacct_number\"]")));
                         driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).clear();
                         driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).sendKeys(rebillAccountNumber);
                        break;
                    }
            // Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
            System.out.println("REBILLED!!!!!");
            //driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
            //*[@id="invoice-grid"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]
            

            String temp;
            
            
            
            
            
            
            
            
            //If Rebill Is Not Successful
            
            try {
            	temp =  driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
            	//This will get rebill to continue
            	if (temp.indexOf("Click Rebill to continue")!=-1) {
            		   driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[1]")).click();
            		   //CLICK REBILL

            		}
            	}
            catch(Exception e1) {
            	System.out.println("No Pop Up for Do Not Match exist");
            	
            }
            
            try {
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span")));
            	overrideBoolean=false;
            	errorList=driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span"));
            	popupCounter=1;
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
            	 driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[2]")).click();
            } 
            
            catch(Exception e2) {
            	System.out.println("Did not reach override errors");
            }
          
            


             
            
             
              popupBoolean=false;
             
             }
             catch(Exception e) {
            	 
            	 errorHandling(7);
             }
              

        }



        
        

   
           
        
          
       
        
        
        //Importing all my data
        public void setUpData() {
        	
        	
        	//Setting the URL
        	if(c.getLevel()==false) {
        		url=c.getRebillL2URL();
        	}
        	else if (c.getLevel()==true) {
        		url=c.getRebillL3URL();
        		
        	}
        	
        	
        	
        	
        	//Getting the source. Excel = false and Database=true. I set this in the GUI/Mouse CLass
        	if(c.getSource()==false){
        		//Giving my excel path from GUI (path saved in config class... was passed through gui/mouse class)
        		e = new excel(c.getExcelPath());
        		//Creates a workbook.
        		e.setUpExcelWorkbook();
        		//Sets up the sheet at the a particular index (0 = sheet 1)
        		e.setUpExcelSheet(0);
        		//Counts how much data there is. Parameter is which column it should count. This means will count
        		//how many values/rows are there for column 3 (column 3 is never null.. so will get total rows).
        		//This will exclude row 1 which is for headers.
        		e.setRowCountAutomatically(2);
        		//Get number of columns.
        		e.setColCountAutomatically(0);
        		
        		//You can also give  a fixed number of rows/columns using e.setRowCountManually(x) and e.setColCountManually(x)
        		
        		//This will save all my data into objects from rebillData class. RebillData class will have
        		//getters and setters for every column in excel sheet (tin, tin count, trk, reason code, etc).
        		
        		//I go through each row in excel and save that entire row into a new object and at same time
        		//add that object into an array list. This array list will hold each object (row) of data.
        		for (int i=1;i<e.getRowCount()+1;i++) {
        			//Create new object and add it to my arraylist at same time.
        			//uses the getCellData method which will use row,col paramter.
        			arrayData.add( new rebillData(e.getCellData(i, 0),e.getCellData(i, 1),e.getCellData(i, 2),e.getCellData(i, 3),e.getCellData(i, 4),e.getCellData(i, 5),e.getCellData(i, 6),e.getCellData(i, 7),e.getCellData(i+1, 8),e.getCellData(i, 9),e.getCellData(i, 10),e.getCellData(i, 11),e.getCellData(i, 12),e.getCellData(i+1, 13),e.getCellData(i, 14),e.getCellData(i, 15)));
        		}
        		//Closes the excel sheet.
        		e.saveAndClose();

        	}
        	
        	if(c.getSource()==true){
        		database db = new database();
        		//creates db connection
        		try {
					db.openDB(c.getGtmRevToolsConnection(c.getGtmDbName(), c.getGtmDbPassword()));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		//Gets the data we want via sql query.
        		db.readData("select * from rebill_regression where trkngnbr is not null");
        		ResultSet rs;
        		rs=db.getResultSet();
        		
        		String temp;
        		try {
        			//Same as excel.. creates object and saves in arrayList.
        			while (rs.next()) {
        				arrayData.add(new rebillData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
        			}      			
        			
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}	
        	}
        }  	

        
        public void setUpDriver() {
        	
        	if (c.getDriverType().equals("1")){
        		c.setProperty(c.getIeProperty(),c.getIeDriverPath());
        		driver = new InternetExplorerDriver();
        		js = ((JavascriptExecutor) driver);
        	}
        	
        	if (c.getDriverType().equals("2")){
        		c.setProperty(c.getChromeProperty(),c.getChromeDriverPath());
        		driver = new ChromeDriver();
        	}
        	
        	js = ((JavascriptExecutor) driver);
    		driver.get(url);
    		driver.manage().window().maximize();
        	
        	
        }
        
        
        
        
      
        
       
    	
    	public void resetCounters() {
    		stepOneCounter=0;
    		stepTwoCounter=0;
    		stepThreeCounter=0;
    		stepFourCounter=0;
    		stepFiveCounter=0;
    		stepSixCounter=0;
    		stepSevenCounter=0;
    		//stepEightCounter=0;
    		//stepNineCounter=0;
    		//stepTenCounter=0;
    		//stepElevenCounter=0;
    		stepOneBoolean=false;
    		stepTwoBoolean=false;
    		stepThreeBoolean=false;
    		stepFourBoolean=false;
    		stepFiveBoolean=false;
    		stepSixBoolean=false;
    		stepSevenBoolean=false;
    		successfulRebill=false;
    		errorMessage="";
    	}
    	
    	
 
    	
    	
    	public void setUpNextTrackingNumber(int trackingNumberCounter) {   
  
              //Saves previous login in case login changes.
              if(trackingNumberCounter>0) {
            	  tempLogin=login;
              }
              else {
            	  
            	  tempLogin=arrayData.get(trackingNumberCounter).getLogin();
              }
              
              
              
              //If no tracking number is left then program is completed.
              try{
              trackingNumber=arrayData.get(trackingNumberCounter).getTrkngnbr();
              if (trackingNumber.equals("") ||trackingNumber==null ){
                  System.out.println("REACHED HERE TEJESH!!!!!!!!!!!!!!!");
                  JOptionPane.showMessageDialog(null, "Program Completed");
                  System.exit(0);
                  
              }}
              catch(Exception e){
                  System.out.println("REACHED HERE TEJESH EXCEPTION!!!!!!!!!!!!!!!");
                  JOptionPane.showMessageDialog(null, "Program Completed");
               System.exit(0);
              }
              
              
              
              reasonCode=arrayData.get(trackingNumberCounter).getReason_code();
              rebillAccountNumber=arrayData.get(trackingNumberCounter).getRebill_acct();
              login=arrayData.get(trackingNumberCounter).getLogin();
              password=arrayData.get(trackingNumberCounter).getPassword();
              result=arrayData.get(trackingNumberCounter).getResult();
              description=arrayData.get(trackingNumberCounter).getDescription();
              test_input_nbr=arrayData.get(trackingNumberCounter).getTest_input_nbr();
              tin_count=arrayData.get(trackingNumberCounter).getTin_count();
              invoice_nbr1=arrayData.get(trackingNumberCounter).getInvoice_nbr_1();
              
              
              System.out.println(login);
              
             // System.exit();
              //If login has changed it will close browser and login with new id
              

              
              if (!tempLogin.equals(login)){

             
             try {
            	 if(trackingNumberCounter>0) {
            		 driver.quit();
            	 }
               
				eraLogin();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              }
              
            
              
              //This is for first trk
              if(trackingNumberCounter==0) {
            	  try {
					eraLogin();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              }
              
              else
              {
              //  getTrackingNumber(); 
              }
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	public void writeResults(int x) {
    		
    		if (successfulRebill==false) {
    			rebillResultStatus="Fail";
    		if(stepOneBoolean==true) {
    			rebillResultDesc="Made It To Step One";
    		}
    		if(stepTwoBoolean==true) {
    			rebillResultDesc="Made It To Step Two";
    		}
    		if(stepThreeBoolean==true) {
    			rebillResultDesc="Made It To Step Three";
    		}
    		if(stepFourBoolean==true) {
    			rebillResultDesc="Made It To Step Four";
    		}
    		if(stepFiveBoolean==true) {
    			rebillResultDesc="Made It To Step Five";
    		}
    		if(stepSixBoolean==true) {
    			rebillResultDesc="Made It To Step Six";
    		}
    		if(stepSevenBoolean==true) {
    			rebillResultDesc="Made It To Step Seven";
    			
    		}
    		}
    		else {
    			rebillResultDesc="Succeesful";
    			rebillResultStatus="Pass";
    		}
    		
    		if(c.getSource()==false){
    			e.setCellData(x,0,rebillResultDesc+" "+errorMessage);
    			e.setCellData(x,1,rebillResultStatus);
    			e.saveAndClose();
    		}
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
        public void isElementVisible(By element, int step) throws InterruptedException {
    		
 
    			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    			
    			
    		
    	}
        
        
       public void isElementClickable(By element, int step) throws InterruptedException {
    		
        	continueBoolean=false;
        	
        	try {
        		wait.until(ExpectedConditions.elementToBeClickable(element));
    			continueBoolean=true;
    			}
    		catch(NoSuchElementException e) {
    			//errorHandling(step);
    		}
    	}
       
       
       
       
       public void tryToClickElement(WebElement element, int seconds,int frequency) throws InterruptedException {
   		
    	   enabledBoolean=false;
    	   enabledCounter=0;
    	   
    	   //hello
    	   
    	  while (enabledCounter<frequency) {
    	
    		  
    	  try {
    		  try {
  	    	    temp =  driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
              	driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[1]")).click();
              	throw new Exception();
              }
              catch(Exception ee) {
              	System.out.println("No Popup!");
              
              }
    		  System.out.println("TRYING TO CLICK: "+enabledCounter);
    		  //*[@id="invoicegridDataCheckBox0"]
    		  element.click();
    		  enabledBoolean=true;
    		  break;
       	}
    	  
   		catch(Exception e) {
   			enabledCounter++;
   			System.out.println("Not Enabled "+enabledCounter);
   			Thread.sleep((seconds*1000));
   		}
    }
  	}
    	
    	
    	
    	
    	
     //*[@id="invoicegridDataCheckBox0"]
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
       	public void errorHandling(int step) throws InterruptedException {
        	
    		switch(step) {
    		
    		
    		
    		case 1:
    			
    			if(stepOneCounter<stepOneLimit) {
    				stepOneCounter++;
    				driver.quit();
    				eraLogin();
    			}
    			else if(stepOneCounter>=5){
    				System.out.println("Program Ended Because It Could Not Login");
    				
    			}
    		
    			break;
    			
    			
    			
    			
    			
    			
    			
    			
    		case 2:
    			
    			try {
    				if(stepTwoCounter<stepTwoLimit) {
    					stepTwoCounter++;
					getTrackingNumber();
    				}
    				else {
    					//driver.quit();
        				//eraLogin();
    					
    				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			break;
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    		case 3:
    			
    			
    			if(stepThreeCounter<stepThreeLimit) {
    				stepThreeCounter++;
    				try {
						getStatCodeCount();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					errorHandling(2);	
				}
    			break;
    			
    			
    			
    			
    			
    			
    			
    			
    			
    		case 4:
    			if(stepFourCounter<stepFourLimit) {
    				stepFourCounter++;
    				try {
    					 clickStatCodeAll(packageCounter);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					errorHandling(3);	
				}
    			
    			break;
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    		case 5:
    			if(stepFiveCounter<stepFiveLimit) {
    				stepFiveCounter++;
    				try {
    					getDetails();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					errorHandling(4);	
				}
    			break;
    			
    			
    			
    			
    			
    			
    			
    			
    			
    		case 6:
    			if(stepSixCounter<stepSixLimit) {
    				stepSixCounter++;
    				try {
    					getDetailScreen();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					errorHandling(5);	
				}
    			break;
    			
    					
    		case 7:
    			if(stepSevenCounter<stepSevenLimit) {
    				stepSevenCounter++;
    				try {
    					rebillScreen();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					errorHandling(6);	
				}
    			break;
			
    		case 8:
    			break;
    		case 9:
    			break;
    		case 10:
    			break;
    		case 11:
    			break;
    		
    		}
    	}
   }
