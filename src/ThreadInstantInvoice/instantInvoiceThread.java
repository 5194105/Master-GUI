package ThreadInstantInvoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class instantInvoiceThread extends Thread{
	//Custom Classes
	config c;
	driverClass dc;
	validateClass vc;
	
	ArrayList<data> dataArray;
	
	WebDriver driver;
	WebDriverWait wait;
	
	String homePath,testInputNbr,tinCount,browser,levelUrl,headless,databaseDisabled,
	source,payorAcctNbr, trkngnbr,  username , password,level,AB;

	int waitTime,attempts=0, maxAttempts=3,tempInt;
	
	Date date;
	SimpleDateFormat formatter;



	public instantInvoiceThread(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getInstantInvoiceL3Url();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		dc = new driverClass(c,levelUrl,waitTime);
		databaseDisabled=c.getDatabaseDisabled();
		vc= new validateClass(c,databaseDisabled,"instant_invoice_device");
		//Getting Date Info
		date = new Date();
        formatter = new SimpleDateFormat("dd");
        AB = formatter.format(date);
        tempInt=Integer.parseInt(AB)+8;
	}
	
	public void run () {
		
		//This will not reattempt any... yet.
		for(data d: dataArray) {
			
			//Declare Vars
			testInputNbr=d.getTestInputNbr();
			tinCount=d.getTinCount();
			trkngnbr=d.getTrkngnbr();
			payorAcctNbr=d.getPayorAcctNbr();
			username=d.getUsername();
			password=d.getPassword(); 			

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
    		System.out.println("Could not login.");
    	}
    }

	public void doInstantInvoice( String trkngnbr,String payorAcctNbr,String  login ,String  password) {
    	for (int i=0;i<maxAttempts;i++) {
	    	try {	
	    		try {
	    		driver.navigate().refresh();
	    		Thread.sleep(2000);
	    		driver.switchTo().frame("content");
	    		//This is to keep from relogging in.
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
	        	
	    		//Enter acct and trk
	            driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[1]/td[1]/span/span[1]/span[1]/input")).sendKeys(payorAcctNbr);
	         	driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[2]/td[1]/span/span[1]/span[1]/input")).sendKeys(trkngnbr);
    	
	    	}
	        	catch(Exception e) {
	        	System.out.println("Could not enter trk and acct.");
	       	}
	    
	    	try{
	             //Click on search button
	             driver.findElement(By.xpath("//button[@id='iiForm:search_button']")).click();
	
	             //Will this messages displays then no record is found.
	             Thread.sleep(1500);
					
	             if(driver.findElement(By.xpath("//*[@id='iiForm:messageId_msg']/span[2]")).isDisplayed()){
	            	 //FAILED.. Will not progress any further.
	                 System.out.println("No Record found");
	                 //writeToFile( trkngnbr,"No Record found");	
	                 return;
	             }
	         }
	         catch(NoSuchElementException | InterruptedException a){
	        	 System.out.println(a);   
	         }
	             
	    	//If it has passed up to this point it will continue to keep going.
	             
	        try{
	            //Will see if there is a row visible for instant invoice. 
	            //Find out if element is disabled or not. 
	        	//If checkbox is enabled it will return true
	        	 Boolean enabled = driver.findElement(By.xpath("//*[@id=\"iiForm:instantInvoiceDynTable:instantInvoiceTable:0:_t47\"]")).isEnabled(); 
	             
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
            	 System.out.println("Could not submit");
            	 continue;
             }
             
	        if( vc.validateInstantInvoice(trkngnbr)==true) {
	        	System.out.println("pass");
	        	return;
                 	 
            }
            else {
            	System.out.println("FAILED");
               	continue;
            }
    	}
	}
}
    /*
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
    */

