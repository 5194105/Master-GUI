package ThreadSinglePrerate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class prerateThread extends Thread{
	config c;
	driverClass dc;
	validateClass vc;
	data d;
	ArrayList<data> dataArray;
	WebDriver driver;
	WebDriverWait wait;
	int waitTime,attempts=0,maxAttempts=3,runningCounter;
	String result,  description, testInputNbr, tinCount, trkngnbr, prerateTypeCd,  prerateAmt, currencyCd, approvalId, chrgCd1, chrgAmt1, chrgCd2,  chrgAmt2, chrgCd3, chrgAmt3,  chrgCd4, chrgAmt4, valDesc,expectedStatus,
	level,source,headless,levelUrl,browser,homePath,databaseDisabled;
	Boolean running=true;
	
	public prerateThread(ArrayList<data> dataArray,config c) {
		//import data array
		this.dataArray=dataArray;
		//import config class
		this.c=c;
		levelUrl=c.getPrerateL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getPrerateSecondTimeout());
		level=c.getLevel();
		//create driver settings
		dc = new driverClass(c,levelUrl,waitTime);
		//creating validation class
		vc= new validateClass(c,databaseDisabled,"prerate_single");
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
				this.result=d.getResult();
				this.description=d.getDescription();
				this.testInputNbr=d.getTestInputNbr();
				this.tinCount=d.getTinCount();
				this.trkngnbr=d.getTrkngnbr();
				this.prerateTypeCd=d.getPrerateTypeCd();
				this.prerateAmt=d.getPrerateAmt();
				this.currencyCd=d.getCurrencyCd();
				this.approvalId=d.getApprovalId();
				this.chrgCd1=d.getChrgCd1();
				this.chrgAmt1=d.getChrgAmt1();
				this.chrgCd2=d.getChrgCd2();
				this.chrgAmt2=d.getChrgAmt2();
				this.chrgCd3=d.getChrgCd3();
				this.chrgAmt3=d.getChrgAmt3();
				this.chrgCd4=d.getChrgCd4();
				this.chrgAmt4=d.getChrgAmt4();
				this.valDesc=d.getValDesc();
				this.expectedStatus=d.getExpectedStatus();
				    
				//Check if track is already successful. If it 
				//is then we remove it from our arraylist
			    if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true) {
			    	dataArray.remove(d);
			    	continue;
			    }
			
				try {
					doPrerate(result,description,testInputNbr, tinCount,trkngnbr, prerateTypeCd, prerateAmt, currencyCd, approvalId, chrgCd1, chrgAmt1, chrgCd2 , chrgAmt2 , chrgCd3, chrgAmt3 , chrgCd4 , chrgAmt4,valDesc);
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
			
			driver.findElement(By.id("username")).sendKeys("5194105");
			driver.findElement(By.id("password")).sendKeys("5194105");
			driver.findElement(By.id("submit")).click();
			driver.get("https://testsso.secure.fedex.com/l3/prerates/");
			
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
    
    public void doPrerate(String result,String description,String testInputNbr,String tinCount,String trkngnbr,String prerateTypeCd,String  prerateAmt,String currencyCd,String approvalId,String chrgCd1,String chrgAmt1,String chrgCd2, String chrgAmt2,String chrgCd3,String chrgAmt3,String  chrgCd4,String chrgAmt4,String valDesc) throws InterruptedException {
    	maxAttempts=1;
    	String [] resultArray = new String[2];
    	WebElement element;
    	JavascriptExecutor Executor;
    	JavascriptExecutor Executor1;
    	JavascriptExecutor Executor2;
    	JavascriptExecutor Executor3;
    	JavascriptExecutor Executor4;
    	WebElement element1;
    	WebElement element2;
    	WebElement element3;
    	WebElement element4;
    	
    	By webElementTemp;
    	String errorMessage;
    	
    	//Will attempt trk x amount of times before moving to next one.
	  for (int i=0;i<maxAttempts;i++) {
		  //Logging in
		  login();
		  try {
			  //We have to swithch to header frame to find the web elements. -- I dont think this is needed anymore.
	    	wait = new WebDriverWait(driver,3);
	    	driver.switchTo().frame("header");}
	    	catch(Exception e) {
	    		System.out.println("Couldt not find header");
	    	}
	    	try {
	    		//Putting in trk and clicking search.
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
	    		//This will check if trk was ever eligible.
	    		resultArray =checkFailure(trkngnbr);
	    		//If we got a error to return.
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
				
	    		//Clicking on first dropdown and second dropdown.
				driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
				element = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[2]/span[2]"));
				Executor = (JavascriptExecutor)driver;
				Executor.executeScript("arguments[0].click();", element);
			
				if (prerateTypeCd.equals("ADDR CHANGE FEE")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
					element1 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[2]/span[2]"));
					Executor1 = (JavascriptExecutor)driver;
					Executor1.executeScript("arguments[0].click();", element1);
				}
				else if(prerateTypeCd.equals("COLD CHAIN")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
					element2 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[4]/span[2]"));
					Executor2 = (JavascriptExecutor)driver;
					Executor2.executeScript("arguments[0].click();", element2);
				}
				else if(prerateTypeCd.equals("Trucking Fees")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
					element3 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[3]/span[2]"));
					Executor3 = (JavascriptExecutor)driver;
					Executor3.executeScript("arguments[0].click();", element3);
				}
				else if(prerateTypeCd.equals("PH LPC")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
					element4 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[8]/span[2]"));
					Executor4 = (JavascriptExecutor)driver;
					Executor4.executeScript("arguments[0].click();", element4);
				}
			} 
	    	catch(Exception e) {
				try{
					//If we cannot select dropdown then look for an error.
					By dropDownError;
					dropDownError=By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[5]/td/span/div/div/table/tbody/tr/td/div/div/div/div[2]/table/tbody/tr/td[3]/div/span");
					errorMessage=driver.findElement(dropDownError).getText();
					resultArray[0]="fail";
					resultArray[1]=errorMessage;
					vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1]);
					continue;
				}
				catch(Exception ee) {
					System.out.println("Didnt find error message from dropdown menu...");
					//Check if elibgle or not again -- not sure if needed here.
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
			}
			
			try {
		
			 //inputing the rest.   	
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:amountId:pymt_amnt_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:amountId:pymt_amnt_input']")).sendKeys(prerateAmt);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:currCodeId:cuu_code_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:currCodeId:cuu_code_input']")).sendKeys(currencyCd);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:rateApprover_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:rateApprover_input']")).sendKeys(approvalId);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde1_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde1_input']")).sendKeys(chrgCd1);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt1_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt1_input']")).sendKeys(chrgAmt1);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde2_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde2_input']")).sendKeys(chrgCd2);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt2_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt2_input']")).sendKeys(chrgAmt2);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(chrgCd3);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(chrgAmt3);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).sendKeys(chrgCd4);
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).clear();
			driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).sendKeys(chrgAmt4);
	
	
			//Click to submit.
			driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
			}
			catch(Exception e) {
				System.out.println(e);
				//Will check just in case already validated before.. if it is it will write
				//results in the vc class to db.
				 if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true) {
					return;
				 }
			resultArray[0]="fail";
			resultArray[1]="Failed on the input menu...";
			vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1]);
			continue;
			}
			
			//Tries to see if back to homepage indicating success
			try {
				//Will wait until homepage element is there.
				wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]")), "Shipment Selection for Pre-Rate Entry"));
				//If able to see homepage.. check if it is validated. If not able to find or validate.. code will continue.
				if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true) {
					  return;
				}
				else {
					 System.out.println("Could not find in EC DB...");
				 }
			}
			catch(Exception e) {
					System.out.println("Did Not Find Prerate Home Page First Time Around");	
					System.out.println(e);	
				}
			//Check for overridable errors.
			try{
				Thread.sleep(2000);
				//See how many checkbox errors are there.
				int count=(driver.findElements(By.xpath("//input[@type='checkbox']")).size());
				for(int k=2;k<count;k++){
					//Will find out if overridable or not.
					Boolean sel=driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).isEnabled();
					//If it is overridable then will click on it. If not we will just check just in case already prerated.
					//Some are designed to be non-overridable errors. We will mark as pass if suppose to be non overridable
					if(sel==true){
						driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).click();
					}
					else {
						if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true) {
							 return;
						 }
						if (expectedStatus.equals("disabled")) {
							resultArray[0]="pass";
						}
						else {
							resultArray[0]="fail";
						}
						resultArray[1]="Override Disabled";
						vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1]);
						return;
					}
				}
				driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
			}
			catch (NoSuchElementException a){
			}
	
			//Check again for success after overriding
			try{
				//Again checking for homepage.
				wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]")), "Shipment Selection for Pre-Rate Entry"));
				if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true) {
					return;
				}
				else {
					System.out.println("Could not find in EC DB...");
				}
			}
			catch(Exception e) {
				System.out.println("Did not find homepage after trying to enable stat codes");	
				System.out.println(e);	
			}
				
			//Check either upper or lower error messages
			try{
				webElementTemp=By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[6]/td/table/tbody/tr[2]/td/span/span/span/span[2]");
				errorMessage=driver.findElement(webElementTemp).getText();
			
				if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true) {
					return;
				 }
				if (!errorMessage.equals("")|| errorMessage != null) {
					resultArray[0]="fail";
					resultArray[1]=errorMessage;
				}
				
				vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1]);
				continue;
	
			}
			catch(Exception e) {
				System.out.println("Did not find either upper or lower error");	
				System.out.println(e);	
			}
						
				
			
			
			//Check either upper or lower error on another location.
			try{
				webElementTemp=By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[4]/td/span/div/div[1]/div[2]/div/table[2]/tbody/tr/td/span/span/span/span[2]");
				errorMessage=driver.findElement(webElementTemp).getText();
				if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true ) {
					return;
				}
				resultArray[0]="fail";
				resultArray[1]=errorMessage;
				vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1]);
				continue;
			}
			catch(Exception e) {
				System.out.println("Did not find either upper or lower error");	
				System.out.println(e);	
			}
	
			try {
				//Actually Should not reach here.
				if (vc.validatePrerate(testInputNbr,tinCount,trkngnbr)==true) {
					return;
				}
				resultArray[0]="fail";
				resultArray[1]="Failed Somewhere... No Error Found Tho";
				vc.writeToDbPrerate(testInputNbr,tinCount,trkngnbr,resultArray[0],resultArray[1]);
				continue;
			}
			catch (NoSuchElementException a){
				System.out.println();
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
