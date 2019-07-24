
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
    ArrayList<rebillData> dataArray;
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
           result, description, test_input_nbr, tin_count,popUpString="Default";
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
     int stepOneCounter;
     
     
     
        public rebillMain(config c) throws InterruptedException{
        	this.c=c;
        	setUpData();
        	resetCounters();
        	eraLogin();
        	for (int i=0;i<dataArray.size();i++) {
        		setUpNextTrackingNumber(i);
        	}

    }

    //Will try to login... if it fails will try again. If it fails 3 times in a row program will end.
    public void eraLogin() throws InterruptedException{
            try{
            	setUpDriver();
            	timeOutCounter=Integer.parseInt(c.getSecondTimeout());

                //timeoutcounter is a notepad variable... will 5 seconds before moving on.
                wait = new WebDriverWait(driver, timeOutCounter);
                
                driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(login);
        
                driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password); 
            
                driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
                
                tempElement=By.xpath("//*[@id=\"trackingID\"]");
                
                isElementVisible(tempElement,1);
                
            	}
            
                catch(Exception e) {
                	errorHandling(1);
                	}
            
            
            	if(continueBoolean==true) {
            		
            		try {
            			  System.out.println("Made It Here Past Login Screen");
						//getTrackingNumber();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
                
                
                /*
                //This will check if tracking number text box is there... proof we logged in.
                exist=driver.findElement(By.xpath("//*[@id=\"trackingID\"]")).isDisplayed();
                
                //Move to next method.
                if (exist==true){
                    getTrackingNumber();
                }
                else{
                    retryLogin();
                }
            }
            //If cant find Tracking Number Box... will reattempt login.
            catch (Exception loginError){
                System.out.println("eraLogin "+loginError);
                System.out.println("Unable to Login");
                retryLogin();
            }
            */
        }
        
    
    
    
    
    
    
    //
    public void getTrackingNumber() throws InterruptedException, SQLException, IOException{
        try{
             //Set this to zero.. means successful login was established so resetting login counter.
            loginCounter=0;
            
          
            //In order for clear button to be clickable need to scroll up
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            
            //Will hit the clear button. This is for whenever we switch to new tracking number
           // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a"))));
            driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a")).click();
                    //*[@id="inquiry-form"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a              
            //In login we verified this appears and sending our tracking number to it.
            driver.findElement(By.xpath("//*[@id=\"trackingID\"] ")).sendKeys(trackingNumber);
            
            //Click on Search
           // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button"))));
            driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button")).click(); 
            
            //If Multiple invoices pop up
            
            //*[@id="1552569853276-grid-container"]/div[2]/div/div[1]
            //*[@id="1552569853276-0-uiGrid-009S-cell"]/div
            //*[@id="1552569853276-1-uiGrid-009S-cell"]/div
            
            
            //*[@id="1552570002170-0-uiGrid-009X-cell"]/div
            //*[@id="1552570002170-1-uiGrid-009X-cell"]/div
            //*[@id="1552570002170-0-uiGrid-009Y-cell"]/div
            //*[@id="1552570002170-1-uiGrid-009Y-cell"]/div
            //*[@id="1552570002170-0-uiGrid-00A0-cell"]
            
            //*[@id="1552570002170-grid-container"]/div[2]/div/div[1]
            
            /*
            
           List<WebElement> testList= driver.findElements(By.xpath("//*[@id=\"1552570310812-grid-container\"]/div[2]/div"));
           //*[@id="1552570310812-grid-container"]/div[2]/div/div[1]
           // List<WebElement> testList= driver.findElements(By.xpath("//*[@id\"1552570002170-grid-container\"]/div[2]/div/div[1]"));
               System.out.println(testList.size());
            for (WebElement ele: testList){
                
            System.out.println(ele.getText());
           }
            
            */

            //*[@id="1552570310812-grid-container"]/div[2]/div
      
            //Verifies that invoice was loaded
           // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoicegridDataCheckBox0\"]")));
           
            packageLevelBoolean=false;
            
            for (int i=0;i<timeOutCounter;i++){
                try{
                    try{
                    driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a")).click();
                    }
                    catch(Exception eee){
                    System.out.println("COuld not click package tab");
                    }
                    packageLevelBoolean=driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[1]/div[2]/div/div[1]/div/label")).isDisplayed();   
                    getStatCodeCount();
                    break;
           }
           catch(Exception e){
                System.out.println("Error in clicking Package Tab");
                if (checkPopUp(false)==true){
                setUpNextTrack(false,"Failed. Multiple Invoices");
                }
           }
                Thread.sleep(1000);
           }
         
            //If successful go get count of how many stat codes to click on.
          
        }
        catch (Exception trackingNumber){
            System.out.println(trackingNumber);
            System.out.println("Cant Find Tracking Number");
            //If Failed it will retry to get tracking number
            retryTrackingNumber();
        
}
         retryTrackingNumber();
    }
    
    public void getStatCodeCount() throws InterruptedException, SQLException, IOException{
        
        statCodeBoolean=false;
        //This will reset the trk number .. but overall failures keep adding up.
        singleTrackingNumber=0;
        //totalTrackingNumber=0;    
        packageCounter=0;
        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS) ;
        System.out.println ();
        //Will count all the stat codes. Once it fails it will move to next section
        try{
            try{
              //  Thread.sleep (2000);
                while(true){
                    exist= driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+packageCounter+"\"]")).isDisplayed();
                    packageCounter++;
                    System.out.println("packageCounter "+packageCounter);
                    Thread.sleep (200);
                }
            }
            catch(NoSuchElementException e){
                System.out.println(e);
                clickStatCodeAll(packageCounter);
            }
        }
         catch(Exception ee){
            retryGetStatCodeCount();
        }
        setUpNextTrack(false,"Unknown Failure");
    }
   

    
        public void clickStatCodeAll(int statCount) throws InterruptedException, SQLException, IOException{
        
        
        statCodeCount=0;
             try {
                 Thread.sleep (1000);
                 js.executeScript("window.scrollTo(0,500)");
                
                 scrollElement =  driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(statCount-1)+"\"]"));
                 js.executeScript("arguments[0].scrollIntoView();", scrollElement);

                 Thread.sleep (3000);
                driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(statCount-1)+"\"]")).click();
                 Thread.sleep (1500);
                getDetails();
            }
            catch(Exception e){
                System.out.println(e);
                //This will try to click on it again.
                retryStatCode1(statCount);
            }
            
           
        }
        
        //Get Reason Code, Action, etc
        public void getDetails() throws InterruptedException, SQLException, IOException{
            System.out.println("Inside getDetails");
            try{
            Thread.sleep(5000);
            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
            statCodeBoolean=true;
            //Getting Action Dropdown. Will RB everytime.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pkgaction\"]")));
            actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
            actionDropDown.selectByValue("RB");
            
            Thread.sleep(1500);
            
            //Setting up the reasonCode Dropdown.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
            reasonCodeDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
            
            //For domestic.
            if (login.equals("5194105")){
                switch (reasonCode){
                    case "RRA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[1]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[1]")).click();
                        break;
                    case "RSA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[2]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[2]")).click();
                        break;
                    case "RTA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[3]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[3]")).click();
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
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[16]")));
                        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[16]")).click();
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
         
             Thread.sleep(1500);
            
            driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
            reasonCodeBoolean=false;
            popUpString="Default";
            pCounter=0;
            for (int i=0;i<timeOutCounter;i++){
                try{
                    
                    reasonCodeBoolean=driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/label")).isDisplayed();
                    break;
           }
           catch(Exception e){
               
               System.out.println("Cannot move to phone method");
                    try{
                         driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
                         Thread.sleep(3000);
                    }
                    catch(Exception ee){}
               pCounter++;
                    if (checkPopUp(false)==true && pCounter>=2){
                        setUpNextTrack(false,popUpString);
                    }
                    else {
                        
                    }
                }
              Thread.sleep(3000);
           }
    }
            catch(Exception e){
                System.out.println(e);
                retryStatCode2();
            }
           getDetailScreen();
        }
        
        //This includes PRI, phone, etc
        public void getDetailScreen() throws InterruptedException, SQLException, IOException{
            error=false;
            try{
                statCodeBoolean=false;
                statCodeCounter2=0;   
            //Waiting to see if a popup comes up for any kind of fee. If it exist we will hit okay.
           // Thread.sleep (2000);       
          
           
            
            //Click on rebill RPI Complete, Phone, and Continue
            
            if (login.equals("5194105")){
           // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
            driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
            }
           // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rmrks\"]")));
            contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
            contactMethodDropDown.selectByValue("phone");
           // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")));
           
           Thread.sleep(2500);
           //This clicks on continue button
           
            //*[@id="invoice-grid"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]
   
            contactMethodBoolean=false;
            popUpString="Default";
            pCounter=0;
             for (int i=0;i<timeOutCounter;i++){    
                try{
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
                     contactMethodBoolean=driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[3]/div[1]/div/div[1]/div[2]/label")).isDisplayed();
                     break;
                }catch(Exception e){
                    
                    System.out.println("Couldnt find Rebill Screen");
                    if(checkPopUp(false)==true && pCounter>=2){
                         setUpNextTrack(false,popUpString);
                    }
                }
                    Thread.sleep(1000);  
                }

            }
            catch(Exception e){
                System.out.println("getDetailScreen "+e);
                retryGetDetail();
            }
            
            if (error==false){
                rebillScreen();
            }
            else {
                setUpNextTrack(false,"Failed at Contact Method");
            }
        }
        
        
        
        
        
        
             public void rebillScreen() throws SQLException, InterruptedException, IOException{
        
                 
             try{    
                popupCounter=0;
             switch (reasonCode){
            case "RRA" :
                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reciptacct_number\"]")));
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).clear();
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(rebillAccountNumber);
                        break;
                    case "RSA" :
                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[7]/div[1]/label")));
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
            //driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
            //*[@id="invoice-grid"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]
            

            //Click Rebill Button
             
            
             
              popupBoolean=false;
             driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
            
             pCounter=0;
                for (int i=0;i<timeOutCounter;i++){
                try{
                    try{
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
                    }
                    catch(Exception eee){
                        System.out.println("Could not find rebill button");
                    }
                    rebillBoolean=driver.findElement(By.xpath("//*[@id=\"pkgsub-tabs\"]/li[6]/a")).isDisplayed();
                   
                    //rebillBoolean=driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[1]/div[2]/div/div[1]/div/label")).isDisplayed();
                    setUpNextTrack(true,"pass");
                    break;
                
                }
           catch(Exception e){
                System.out.println("Checking for validation...");
                 System.out.println(e);
                System.out.println("Checking for validation...");
                pCounter++;
                if (checkPopUp(true)==true && pCounter>=3 && popupBoolean==true){
                    setUpNextTrack(false,"Could Not Get Pass Override Errors");
                     }
                }
              Thread.sleep(1000);
           }
   
            }
            catch(Exception eee){
                 System.out.println();
                 System.out.println();
                 System.out.println();
                 System.out.println();
                 System.out.println();
                 System.out.println();
                System.out.println(eee);
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                
                System.out.println("Will retry trk if still not found what we looking for.");
                        retryRebillScreen();
                       
                }
        }

             public void retryRebillScreen() throws SQLException, InterruptedException, IOException{
             
                 if (rebillScreenCounter<3)
                 {
                 rebillScreenCounter++;
                 rebillScreen();
                 
                 }
                 else {
                     retryTrackingNumber();
                 
                 }
                 
             }
             
        public void validateResultsEnd(){
        
            
           

         driver.findElement(By.xpath(" //*[@id=\"trackingID\"]")).isDisplayed();
         System.out.println("Rebill Successful");
         driver.findElement(By.xpath("//*[@id=\"pkgsub-tabs\"]/li[6]/a")).click();
         System.out.println(driver.findElement(By.xpath(" //*[@id=\"1549044866148-0-uiGrid-00NG-cell\"]/div")).getText());
         
        }
        
        public void retryGetStatCodeCount() throws InterruptedException, SQLException, IOException{
        
            if (statCodeCountCounter<3){
                statCodeCountCounter++;
                getStatCodeCount();
            }
                else {
                         retryTrackingNumber();
                        }
        }
        
        
        public void ifRebilledAlready(){
        
        
        }
        
            /*
// Thread.sleep (5000);
        driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).sendKeys("000000000");
        driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[7]/div[3]/button[1]")).click();
        //Thread.sleep (2000);
        
        
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[1]")).click();
        
        Boolean aaa,bbb;
               
        aaa = driver.findElement(By.xpath("//html/body/div[6]/div/div/div[2]/div[1]/label/span[1]")).isEnabled();
        bbb = driver.findElement(By.xpath("//html/body/div[6]/div/div/div[2]/div[2]/label/span[1]")).isEnabled();
        driver.findElement(By.xpath("//html/body/div[6]/div/div/div[2]/div[2]/label/span[1]")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[1]")).click();
            }
            catch(Exception e){
                driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a")).click();
            }
            }
    */

          public void retryLogin() throws InterruptedException{
        
            driver.quit();
            if(loginCounter <= 2){
                System.out.println("Login Counter: "+loginCounter);
                loginCounter++;
                eraLogin();
            }
            else{
               System.out.println("Could not open eRA");  
               System.exit(0);
            }
        }
          
          
          
          //Will try to reenter a trk number.
        public void retryTrackingNumber() throws InterruptedException, SQLException, IOException{
            //If it keeps failing then it will move to next trk
            if (totalTrackingNumber <=Integer.parseInt(retryAttempts)){
                totalTrackingNumber++;
               //Will try to do trk number 3 times
               System.out.println("Total Tracking Number: "+totalTrackingNumber+" should not be higher than 5");
                if (singleTrackingNumber <=2){
                    try{
                       // Thread.sleep(1000);
                        //Increases the counter.. if successful then it will reset to zero in next method after trk.
                        singleTrackingNumber++;  
                        //Will hit the clear button and make sure the tracking number textbox is there.
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a")));
                        driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a")).click();
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"trackingID\"] ")));
                       
                        getTrackingNumber();
                    }
                    catch(Exception e){
                        //Will log back in if there was some error entering in the tracking number. If this keeps happening
                        //It will end the program.
                        System.out.println("retryTrackingNumber: "+e);
                        //totalTrackingNumber++;
                        retryLogin();
                    }
                }
            else{
                //If it is not finding any tracking numbers over and over it will eventually end the program.
               // totalTrackingNumber++;
                //Makeing the next tracking number counter to zero since new trk is coming.
                
                //Move to next tracking number
                setUpNextTrack(false,"Could Not Find Tracking Numeber");
            }     
        }
            else{
                setUpNextTrack(false,"Could Not Find Tracking Numeber");
               // driver.close();
                System.out.println("Too Many Failures");
                
            }
        }
          
          
           public void retrygetStatCodeCount() throws InterruptedException, SQLException, IOException{
               if (statCodeCount < 3){
                   statCodeCount++;
                   getStatCodeCount();
               }
               else {
                   retryTrackingNumber();
               }
           }
          
            public void retryStatCode1(int statCount) throws InterruptedException, SQLException, IOException{
            //This will try to click the stat code 3 times. If it fails it will try once to reenter the tracking number.
            //If afterwards if it fails again then 3 times it will move to next tracking number 
            
            System.out.println(" Inside retryStatCode1");
            
            if (statCodeCounterTotal1<1){
                if (statCodeCounter1<2){
                    statCodeCounter1++;
                    clickStatCodeAll(statCount);
                }    
                else{
                    statCodeCounterTotal1++;
                    retryTrackingNumber();
                }
            }
            else{
            statCodeCounterTotal1=0;
            setUpNextTrack(false,"Could not get Stat Codes");
            }
        }
        
        public void retryStatCode2() throws InterruptedException, SQLException, IOException{
           //If it cant get stat code then click all option and procede after 3 atempts it will go to next trk.
            if (statCodeCounter2<2){
                 statCodeCounter2++;
                 retryTrackingNumber(); 
            }    
            else{
                setUpNextTrack(false,"Could not select rebill options");
            }
        }
          
          
        
        
        public void retryGetDetail() throws InterruptedException, SQLException, IOException{   
            
            if (retryGetDetailCounter<2){
                    retryGetDetailCounter++;
                    try{
                   // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pkgCancel\"]")));
                   // driver.findElement(By.xpath("//*[@id=\"pkgCancel\"]")).click();
                    getDetailScreen();       
                }
                catch(Exception e){
                     System.out.println("Could not get rebil detail like Phone contact");
                    retryTrackingNumber();
                }
            }
             else{
                 System.out.println("Could not get rebil detail like Phone contact");
                retryTrackingNumber();
               
            }
        }
        
        
        
        
        /*
        
        //*[@id="1552505676896-0-uiGrid-009W-cell"]/div
        //*[@id="1552505676896-1-uiGrid-009W-cell"]/div
        //*[@id="1552505923845-0-uiGrid-00A1-cell"]/div
        //*[@id="1552505923877-0-uiGrid-01X4-cell"]/div
        #\31 552505923877-0-uiGrid-01X4-cell > div
        //*[@id="1552505923877-0-uiGrid-01X2-cell"]/div/div/div
        
        */
           
        public Boolean checkPopUp( Boolean popup){
            popUpBoo=false;
            popupBoolean=false;
            try{
                if(popup==false){
                if (driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]")).isDisplayed()==true){
                    try{
                        
                        popUpString=driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
                       
                    }
                    catch(Exception e){
                        System.out.println("Could not find popup Text");
                    }
                    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[2]")).click();
                    popUpBoo=true;
                }
            }
                  else if (popup==true){
                      
                      try{
                           popUpString=driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
                       if (popUpString.indexOf("acct nbrs do not match")!= -1){
                            driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[1]")).click();
                            acctMatch=true; 
                       }
                       else{acctMatch=false;}
                      }
                      catch(Exception ee){
                        acctMatch=false;
                      }
                      if (acctMatch==false){
                      try{
                       errorList=driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span"));
                        popUpBoo=true;
                           for (WebElement ele: errorList){
                                popupCounter++;
                                if (popupCounter%2==1){
                                     System.out.println("This is checkbox");
                               try{
                                   ele.click();
                                   if (ele.isSelected()){
                                   System.out.println("Could Click");
                                   }
                                   else{
                                   System.out.println("Could Not Click");
                                    popupBoolean=true;
                                   }
                               }
                                   catch(Exception e){
                                           System.out.println("Could not click");
                                            popupBoolean=true;             
                            }
                        }
                    }        
                    
                   
                      }
                      catch(Exception eee){
                          System.out.println("popup                       "+eee);
                      }
                  driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[2]")).click();
                  Thread.sleep(10000);
                }
            }
            }
            catch(Exception e){
                System.out.println("No Popup Found");
               
            
            }
            
            return popUpBoo;
            
        }
            
          
        public void setUpNextTrack(Boolean booResult,String resultString) throws InterruptedException, SQLException, IOException{     
           if (source==true){
                et.writeResults(row,booResult,resultString);
           }
           else {
              UploadResults(booResult,resultString,trackingNumber,test_input_nbr,tin_count);
           }
          row++;
           
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          System.out.println("TRACKING NUMBER IS "+booResult);
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          System.out.println("**************************************************************");
          
          
            tempLogin=login;
            
            totalTrackingNumber=0;
            singleTrackingNumber=0;
             totalTrackingNumber=0;
             singleTrackingNumber=0;
             retryGetDetailCounter=0;
             statCodeCounterTotal1=0;
             statCodeCounter1=0;
             statCodeCounter2=0;
             rebillScreenCounter=0;
             statCodeCountCounter=0;
             error=false;
             booleanResult=false;
             packageLevelBoolean=false;
            //Moving the counter for data set up
            trackingNumberCounter++;
            //Gets next dataset details
            acctMatch=false;
            
            
            try{
            trackingNumber=dataArray.get(trackingNumberCounter).getTrkngnbr();
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
            reasonCode=dataArray.get(trackingNumberCounter).getReason_code();
            rebillAccountNumber=dataArray.get(trackingNumberCounter).getRebill_acct();
            login=dataArray.get(trackingNumberCounter).getLogin();
            password=dataArray.get(trackingNumberCounter).getPassword();
            result=dataArray.get(trackingNumberCounter).getResult();
            description=dataArray.get(trackingNumberCounter).getDescription();
            test_input_nbr=dataArray.get(trackingNumberCounter).getTest_input_nbr();
            tin_count=dataArray.get(trackingNumberCounter).getTin_count();
            
           // System.exit();
            //If login has changed it will close browser and login with new id
            
            System.out.println("TEMP "+tempLogin +"LOGIN "+login);
            
            if (!tempLogin.equals(login)){
           // driver.close();
           driver.quit();
            eraLogin();
            }
            else
            {
              getTrackingNumber(); 
            }
        }

        public void UploadResults(Boolean result,String description,String trk,String tin,String tin_count) throws SQLException{
            db=new databaseConnectionTesting(dbName,dbPassword);   
            con =db.getConnection();
            ps = con.prepareStatement("insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");
            ps.setString(1, tin);
            ps.setString(2, tin_count);
            ps.setString(3, trk);
            if (result==true){
            ps.setString(4, "pass");
            }
            else{
             ps.setString(4, "fail");
            }
            ps.setString(5, description);
            
            ps.execute();
            
            ps = con.prepareStatement("update gtm_rev_tools.rebill_results set result=?,description=? where trkngnbr=?");
            if (result==true){
            ps.setString(1, "pass");
            }
            else{
             ps.setString(1, "fail");
            }      
            ps.setString(2, description);
            ps.setString(3, trk);   
            ps.execute();
            con.close();
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
        		excel e = new excel(c.getExcelPath());
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
        
        
        
        
        public void isElementVisible(By element, int step) throws InterruptedException {
    		
        	continueBoolean=false;
        	
        	try {
    			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    			continueBoolean=true;
    			}
    		catch(NoSuchElementException e) {
    			errorHandling(step);
    		}
    	}
        
       
    	
    	public void resetCounters() {
    		stepOneCounter=0;
    		
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
              
             // System.exit();
              //If login has changed it will close browser and login with new id
              

              
              if (!tempLogin.equals(login)){

            // driver.quit();
            // eraLogin();
              }
              else
              {
              //  getTrackingNumber(); 
              }
    	}
    	
    	
    	
    	
       	public void errorHandling(int step) throws InterruptedException {
        	
    		switch(step) {
    		
    		case 1:
    			
    			if(stepOneCounter<5) {
    				stepOneCounter++;
    				driver.quit();
    				eraLogin();
    			}
    			else if(stepOneCounter>=5){
    				System.out.println("Program Ended Because It Could Not Login");
    				
    			}
    			
    			break;
    		
    		}
    	}
   }
