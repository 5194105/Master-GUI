package ThreadTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import configuration.config;

public class instantInvoiceThread extends Thread{
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
	String payorAcctNbr, trkngnbr,  username , password;
	int waitTime;
	int attempts=0;
	int maxAttempts=3;
	String level;
	driverClass dc;
	validateClass vc;
	
	public instantInvoiceThread(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getInstantInvoiceL3Url();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		dc = new driverClass(c,levelUrl,waitTime);
		databaseDisabled=c.getDatabaseDisabled();
		vc= new validateClass(c,databaseDisabled,"instant_invoice_device");
	}
	
	public void run () {
		
		for(data d: dataArray) {
			
			//Declare Vars
			trkngnbr=d.getTrkngnbr();
			payorAcctNbr=d.getPayorAcctNbr();
			username =d.getUsername();
			password=d.getPassword(); 
			
			System.out.println(trkngnbr);
			System.out.println(payorAcctNbr);
			System.out.println(username);
			System.out.println(password);
			
			//Check if track is already successful
			
			//writeToFile( trkngnbr,"test");	
			doInstantInvoice( trkngnbr,payorAcctNbr, username , password);
			
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
    		//  System.out.println(e);
    		  
    	  }
    		driver=dc.getDriver();
		    driver.get(levelUrl);
		    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,waitTime);
			driver.manage().window().maximize();
			
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("submit")).click();
			}
    	catch(Exception e) {
    		
    		// Assert.fail("Could Not Login");
    	}
    }
    
    
    
    
    
    public  synchronized void writeToFile(String trk,String desc) {
    	Boolean writtenBoolean=false;
    	
    	try {
			
    		
    		BufferedReader file = new BufferedReader(new FileReader("E:\\Everyone Workspace Folders\\stephen\\Master-GUI\\instantInvoice.txt"));
    		StringBuffer inputBuffer = new StringBuffer();
    		String line;
    		while ((line = file.readLine())!=null) {
    			if (line.equals(trk)) {
    				BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\Everyone Workspace Folders\\stephen\\Master-GUI\\instantInvoice.txt",true));
    				writer.write(trk+" "+desc+"\n");
    				writer.newLine();
    				writtenBoolean=true;
    			}
    			
    		}
    		
    		if (writtenBoolean==false) {
    			BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\Everyone Workspace Folders\\stephen\\Master-GUI\\instantInvoice.txt",true));
    			PrintWriter out = new PrintWriter(writer);
    			writer.write(trk+" "+desc+"\n");
    			writer.newLine();
    			out.close();
    			
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    		
    	
    }
    
    
    
    
    
    
    public void doInstantInvoice( String trkngnbr,String payorAcctNbr,String  login ,String  password) {
    	for (int i=0;i<maxAttempts;i++) {
    	try {	
    		try {
    		driver.navigate().refresh();
    		Thread.sleep(2000);
    		driver.switchTo().frame("content");
    		Boolean isPresent = driver.findElements(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[1]/td[1]/span/span[1]/span[1]/input")).size() > 0;
        	if (isPresent==false) {
        		login();
        		 driver.switchTo().frame("content");
        			}
        	}
        	catch(Exception e) {
        		login();
        		 driver.switchTo().frame("content");
        	}
        	// login();
          	
        	//WebElement dateWidget = driver.findElement(By.id("iiForm:fromDateII_input"));
        	//WebElement dateWidget = driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[1]/td[2]/span"));
          	
        	
        	//List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
        //	System.out.println("HERE");
        	
            driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[1]/td[1]/span/span[1]/span[1]/input")).sendKeys(payorAcctNbr);
         	driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[2]/td[1]/span/span[1]/span[1]/input")).sendKeys(trkngnbr);
         	 Date date = new Date();
             SimpleDateFormat formatter = new SimpleDateFormat("dd");
             String AB = formatter.format(date);
             int tempInt=Integer.parseInt(AB)+8;
         	/*
         	
            driver.findElement(By.xpath("//input[@id='iiForm:fromDateII_input']")).click();
            Select dropdown = new Select (driver.findElement(By.name("iiForm:fromDateII_input_sel_month")));   

            //Just Choosing Jan. Will try this ten times before it ends.
          int  attempts=0;
            while (attempts<10){
                try{
                    dropdown.selectByVisibleText("Jan");
                    driver.findElements(By.className("ui-state-default")).get(9).click();  
                    break;
                    }
                    catch (Exception eee){
                  //  System.out.println(eee);
                    }
                    attempts++;
                }
        	
        	
Thread.sleep(3000);

           
            
            //Getting the second date. Will try 10 times
            attempts=0;
            while (attempts<10){
                try{  
                  
                    driver.findElement(By.xpath("//*[@id=\"iiForm:toDateII_input\"]")).click();
                    break;
                }
                catch (Exception eee){
                   // System.out.println(eee);
                }
                attempts++;
                }
            
            
            attempts=0;
            while (attempts<10){
            try {
            //Will grab the calender and every date in it.. by default it wil select current date.
           
            //Loop through each date.
            int count = driver.findElements(By.className("ui-state-default")).size();
            //Loops through the second date until hits date we want
        
                    Thread.sleep(1000);
                   
                    driver.findElements(By.className("ui-state-default")).get(tempInt).click();
                    break;
            }
                  catch (Exception eee){
                //    System.out.println(eee);
                }
                attempts++;
            }
      
        
        
        
        */
    	}
        	catch(Exception e) {
        		
       		//System.out.println(e);
       	}
    	//System.out.println("STOP");
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	 try{
             //Click on search button
             driver.findElement(By.xpath("//button[@id='iiForm:search_button']")).click();

             //Will this messages displays then no record is found.
             
                 try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}
                 if(driver.findElement(By.xpath("//*[@id='iiForm:messageId_msg']/span[2]")).isDisplayed())
                 {
                     //FAILED.. Will not progress any further.
                     System.out.println("No Record found");
                     writeToFile( trkngnbr,"No Record found");	
                     return;
                 }
             }
             catch(NoSuchElementException a){
                     
            //  System.out.println(a);   
           
             }
             

             
             //If it has passed up to this point it will continue to keep going.
             
                  try{
                     //Will see if there is a row visible for instant invoice. 
                     //Find out if element is disabled or not. 
                	 Boolean enabled = driver.findElement(By.xpath("//*[@id=\"iiForm:instantInvoiceDynTable:instantInvoiceTable:0:_t47\"]")).isEnabled(); 
                     System.out.println(enabled);
                     //If checkbox is enabled it will return true

                     if (enabled == true){
                         //Passed. Will click on checkbox and click on instant invoice. The program will now leave the if (res==true) condidition.
                                                               
                         driver.findElement(By.xpath("//*[@id='iiForm:instantInvoiceDynTable:instantInvoiceTable:_t44']")).click();
                         Thread.sleep(2000);
                         driver.findElement(By.xpath("//*[@id=\"iiForm:instanceInvoice_button\"]")).click();
                         
                     }                               
                     //if Checkbox is false.
                     else{
                         //FAILED. Will set res as false to fail our trk.. maybe get the desc for failure
                         System.out.println("Disabled");
                         return;
                       
                         }
                     }
                     catch(Exception ee){
                            
                            // System.out.println(ee);
                             System.out.println("Could not submit");
                             writeToFile( trkngnbr,"Could not submit");
                             continue;
                           
                         }
                  
                  
                  
                   if( vc.validateInstantInvoice(trkngnbr)==true) {
                  
                 System.out.println("pass");
                 writeToFile( trkngnbr,"pass");
                 	 return;
                 	 
                  }
                  else {
                	 System.out.println("FAILED");
                	 writeToFile( trkngnbr,"FAILED");
                	 continue;
                     }
    }
                  
                 
     // INSTANT INVOICE BUTTON XPATH               
     //driver.findElement(By.xpath("//button[//*[@id='iiForm:instanceInvoice_button']")).click();
    	
  
        
    
    
    }
 
		
}
