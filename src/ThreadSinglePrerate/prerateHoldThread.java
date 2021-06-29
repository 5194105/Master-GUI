package ThreadSinglePrerate;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;


import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class prerateHoldThread extends Thread{
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
	String result,  description, testInputNbr, tinCount, trkngnbr, prerateTypeCd,  prerateAmt, currencyCd, approvalId, podScan, tinComment, chrgCd2,  chrgAmt2, chrgCd3, chrgAmt3,  chrgCd4, chrgAmt4, valDesc,expectedStatus;
	Boolean running=true;
	int runningCounter;
	data d;
	public prerateHoldThread(ArrayList<data> dataArray,config c) {
		
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getPrerateL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getPrerateSecondTimeout());
		level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"prerate_hold");
	}
	
	public void run () {
		
		while(true) {
			for(data d: new  ArrayList<data>(dataArray)) {
			
			//Declare Vars
			this.result=d.getResult();
			this.description=d.getDescription();
			this.testInputNbr=d.getTestInputNbr();
			this.tinCount=d.getTinCount();
			this.trkngnbr=d.getTrkngnbr();
			this.podScan=d.getPodScan();;
			this.tinComment=d.getTinComment();
			
			//Check if track is already successful
			System.out.println(trkngnbr);
		    
		    if (vc.validatePrerateHold(testInputNbr,tinCount,trkngnbr,tinComment)==true) {
		    	
		    	dataArray.remove(d);
		    	continue;
		    }
		  	try {
				doPrerate(result,description,podScan,testInputNbr, tinCount,trkngnbr, tinComment);
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
			
			driver.findElement(By.id("username")).sendKeys("5194105");
			driver.findElement(By.id("password")).sendKeys("5194105");
			driver.findElement(By.id("submit")).click();
			}
    	catch(Exception e) {
    		System.out.println(e);
    		vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,"fail","Could not login");
    		System.out.println("Could not login");
    	}
    }
    
    
public void homepage() {
    	
    	//Will See if even need to login.. if we can navigate to home page then skips.
    	
    	try {
    		driver.get(levelUrl);
    		driver.switchTo().frame("header");	
    		wait = new WebDriverWait(driver,3);
    		if (!driver.findElement(By.id("preRateEntrySelForm:_t12")).getText().equals("Tracking No:")) {
    			login();
    		}
    	
    	}
    	catch(Exception e) {
    		System.out.println(e);
    		login();
    	}
    		
    	
    }
    
    
    
    
  public void doPrerate(String result, String description,String podScan,String testInputNbr,String tinCount,String trk,String tinComment) throws InterruptedException {
	  maxAttempts=1;
	  String [] resultArray = new String[2];
	  for (int i=0;i<maxAttempts;i++) {
	  System.out.println("Attempt Number :"+(i+1));
		
	 
	  WebElement element;
  	  JavascriptExecutor Executor;

  	
  	
  	String errorMessage;
  	
  	/*

	try {
		driver.get(levelUrl);
		driver.switchTo().frame("header");
		Boolean isPresent = driver.findElements(By.id("preRateEntrySelection")).size() > 0;
	if (isPresent==false) {
		login();
			}
	}
	catch(Exception e) {
		login();
	}
    	
    try {
    	wait = new WebDriverWait(driver,3);
    	driver.switchTo().frame("header");}
    	catch(Exception e) {}
    	*/
	login();
    try {
    	wait = new WebDriverWait(driver,3);
    	driver.switchTo().frame("header");}
    	catch(Exception e) {}
    	try {
    	wait = new WebDriverWait(driver,5);
		driver.findElement(By.id("preRateEntrySelection")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).sendKeys(trkngnbr);
		driver.findElement(By.xpath("//button[@id='preRateEntrySelForm:search_button']")).click();
		}
	catch(Exception e){
		
		System.out.println("Failed on Home Page");
		System.out.println(e);
		
		
		resultArray =checkFailure(trkngnbr);
		if (!resultArray[0].equals("")) {
			vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1]);
			return;
		}
		else {
		vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,"fail","failed on homepage");
		}
		 continue;
		}
	
    	
    	
    	
    	
    	
    	
    	
    	
  	try{
			
  	
		//Fast way to move to next screen... fails if prerate input not there.
  		if (tinComment.equals("ON_HOLD")){
		driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
		element = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[3]/span[2]"));
		Executor = (JavascriptExecutor)driver;
		Executor.executeScript("arguments[0].click();", element);
  		}
  		else if(tinComment.equals("REMOVE")){
  			driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
  			element = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[5]/span[2]"));
  			Executor = (JavascriptExecutor)driver;
  			Executor.executeScript("arguments[0].click();", element);
  		}
  		Thread.sleep(2000);
  		driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
  		
  		Thread.sleep(5000);
  		
  		
  		
  		if (vc.validatePrerateHold(testInputNbr,tinCount,trkngnbr,tinComment)==true) {
			  return;
  		}
  		else {
  			vc.writeToDbPrerate(testInputNbr, tinCount, trkngnbr, "fail", "failed somewhere");}
  		}
			  
			  
  		catch(Exception e) {
  			vc.writeToDbPrerate(testInputNbr, tinCount, trkngnbr, "fail", "failed somewhere");
  			}
  		}
  		
			
		
	  }
  
  
  
  public  String[] checkFailure(String trkngnbr){
		String[] resultArray = new String[2];
		
		/*
		I look to see if it was eligible:
			Not Eligible for Pre-Rate Even if a transaction follows the GREEN flow, it will not be eligible for pre-rate application if:
			• It’s a child package of a multi-piece shipment (MPS) – only the master is eligible for pre-rate.  – IS MASTER  (MPS_PKG_TYPE_CD -- INTL_EXPRS_ONLN_SCHEMA.intl_package)
			• It’s a child package and a Direct Distribution service was used – i.e. IPD, IED, or IDF. NOTE: IPD = FedEx Intl Priority Direct Distribution®, IED = FedEx Intl Economy Distribution®, and IDF = FedEx IP Direct Distribution Freight®  -- SVC CD = 70 , PKG_CD =01, FORM_ID=0430
			• The transaction is billed to a city center number – not a FedEx account number.  – 318844186 (Not city account)
			• The payment method is external credit card (04) or cash (05).  – is cash tho.. (PYMT_MTHD_CD  -- INTL_EXPRS_ONLN_SCHEMA.intl_online_revenue_item  )
			• The service type is FedEx International Mail Service (FIMS)  31 21. (SVC_BASE_CD -- INTL_EXPRS_ONLN_SCHEMA.intl_online_shipment)  
			• The transaction has reached the Earned revenue stage. (ITEM_PRCS_CD  -- INTL_EXPRS_ONLN_SCHEMA.intl_online_revenue_item  )
			
*/
		/*
		 * 
		 * select MPS_PKG_TYPE_CD,PYMT_MTHD_CD,SVC_BASE_CD,item_prcs_cd
from INTL_EXPRS_ONLN_SCHEMA.intl_package a
join INTL_EXPRS_ONLN_SCHEMA.intl_online_revenue_item b
on a.ONLN_REV_ITEM_ID =b.ONLN_REV_ITEM_ID 
join INTL_EXPRS_ONLN_SCHEMA.intl_online_shipment c
on a.ONLN_REV_ITEM_ID =c.ONLN_REV_ITEM_ID 
where pkg_trkng_nbr ='582838858029';
		 * 
		 */
		
		
		
	
		
		PreparedStatement ps = null;
		Connection oreCon= null;
		Boolean oreError=false;
		String resultString="";
	
		 	oreCon=c.getIoreL3DbConnection();
			
	

	try {
		ps = oreCon.prepareStatement(" select MPS_PKG_TYPE_CD,PYMT_MTHD_CD,SVC_BASE_CD,item_prcs_cd from INTL_EXPRS_ONLN_SCHEMA.intl_package a join INTL_EXPRS_ONLN_SCHEMA.intl_online_revenue_item b on a.ONLN_REV_ITEM_ID =b.ONLN_REV_ITEM_ID join INTL_EXPRS_ONLN_SCHEMA.intl_online_shipment c on a.ONLN_REV_ITEM_ID =c.ONLN_REV_ITEM_ID where pkg_trkng_nbr =?");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		e.printStackTrace();
	}
	        //   "select * from INTL_EXPRS_ONLN_SCHEMA.intl_package");

	   
	       try {
			ps.setString(1,trkngnbr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			if (rs.next()==false){
			      System.out.println("Is NULL");
			      resultArray[0]=""; 
			      resultArray[1]="";
			}
			   else{
			      
				   
				   System.out.println("IS NOT NULL");
			    
				   
				   if (rs.getString(1)!=null) {
					   System.out.println("Child MPS Shipment");
					   resultString+="Child MPS Shipment ";
					   oreError=true;
				   }
				   if (rs.getString(2).equals("04")) {
					   System.out.println("PMNT_MTD is 04 ");
					   resultString+="PMNT_MTD is 04 ";
					   oreError=true;
				   }
				   if (rs.getString(2).equals("05")) {
					   System.out.println("PMNT_MTD is 05 ");
					   resultString+="PMNT_MTD is 05 ";
					   oreError=true;
				   }
				   if (rs.getString(3).equals("31")) {
					   System.out.println("SVC_TPY_CD is 31 ");
					   resultString+="SVC_TPY_CD is 31 ";
					   oreError=true;
				   }
				   if (rs.getString(3).equals("21")) {
					   System.out.println("SVC_TPY_CD is 21");
					   resultString+="SVC_TPY_CD is 21 ";
					   oreError=true;
				   }
				   if (rs.getString(3).equals("18")) {
					   System.out.println("SVC_TPY_CD is 18");
					   resultString+="SVC_TPY_CD is 18 (IPD) ";
					   oreError=true;
				   }
				   if (!rs.getString(4).equals("OR")) {
					   System.out.println("ITEM_PRCS_CD is "+rs.getString(4));
					   resultString+="ITEM_PRCS_CD is "+rs.getString(4);
					   
					  
				   }
				   
				   
				   	  resultArray[0]="fail"; 
				   	  if (resultString.equals("")) {
				      resultArray[1]="Unknown Not Eligible Error";
				   	  }
				   	  else	if (!resultString.equals("")) {
					      resultArray[1]=resultString;
					   	  }
				   
			      
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	      
		
		return resultArray;
	}
  
}
