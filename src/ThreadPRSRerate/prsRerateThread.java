package ThreadPRSRerate;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class prsRerateThread extends Thread{
	ArrayList<data> dataArray;
	Alert alert;
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
	 Robot r;
	 List<WebElement> comboBoxesHandling = new ArrayList<WebElement>();
	String result,  description, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2,
	 rateWeight, actualWeight, wgtType,  length, width, height, 
	 workable, dimType, payor, billAcctNbr, serviceType, packageType,
	 rerateType, region, username, password, rsType, company, valDesc,comments,serviceName,acct1,acct2,trkNo1,trkNo2,service1,service2,requestType,acctType,acctName;
String prsMainUrl,prsCreateUrl,prsPaUrl,startDate,endDate;
String url1 ="https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp";
String url2="https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp";
	public prsRerateThread(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getRebillL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"prs_rerate");
		startDate=c.getStartDate();
		endDate=c.getEndDate();
	}
public void run () {
		
		for(data d: dataArray) {
			testInputNbr=d.getTestInputNbr();
			tinCount=d.getTinCount();
			acct1=d.getAcct1();
			acct2=d.getAcct2();
			trkNo1=d.getTrkNo1();
			trkNo2=d.getTrkNo2();
			invoiceNbr1=d.getInvoiceNbr1();
			invoiceNbr2=d.getInvoiceNbr2();
			service1=d.getService1();
			service2=d.getService2();
			requestType=d.getRequestType();
			acctType=d.getAcctType();
			acctName=d.getAcctName();
			prsMainUrl=c.getPrsRerateMainL3Url();
    		prsCreateUrl=c.getPrsRerateCreateL3Url();
    		prsPaUrl=c.getPrsReratePaL3Url();
			  
			  Boolean ground=false;
			  Boolean express=false;
			  Boolean combo=false;
			  
			  
			  
			  if (service1.equals("Ground International") || service1.equals("Ground Domestic") || service1.equals("SmartPost")) {
					ground=true;
					}
					else {
						express=true;
						}

				if (!service2.equals("")) {
					if (service2.equals("Ground International") || service2.equals("Ground Domestic") || service2.equals("SmartPost")) {
						ground=true;
					}
					
					
					else if (service2.equals("NT") || service2.equals("Express Domestic") || service2.equals("Express International")){
						express=true;
						}
					}
				
					if (express==true && ground==true) {
						combo=true;
						ground=false;
						express=false;
					}
					
			  try {
				r=new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  login();
			  
			  firstPage(r,alert,acctName,acct1,acct2,acctType, testInputNbr, tinCount, trkNo1);
			  secondPage(comboBoxesHandling,service1, service2, requestType, trkNo1, trkNo2, invoiceNbr1, invoiceNbr2, acct1,  acct2, testInputNbr, tinCount);
			  thirdPage(acctName,express,ground,combo, testInputNbr, tinCount, trkNo1);
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
			driver.get(prsMainUrl);
	  		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  		wait = new WebDriverWait( driver,10);
	  		driver.manage().window().maximize();     
			
			
		//	/html/body/div/div/form/div/div[1]/fieldset/label[1]/input
			driver.findElement(By.id("username")).sendKeys("477023");
			driver.findElement(By.id("password")).sendKeys("477023");
			driver.findElement(By.id("submit")).click();

		  
			                                                  
			driver.get(prsCreateUrl);
		
			
			try {
			Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"form1\"]/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[1]/b/font")).size() > 0;
			//element = driver.findElement(By.xpath("//*[@id=\"form1\"]/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[1]/b/font"));
			}
			
			catch(Exception e) {
				driver.quit();
				driver.get(prsMainUrl);	
				driver.manage().window().maximize();     
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
					
				driver.findElement(By.id("username")).sendKeys("477023");
				driver.findElement(By.id("password")).sendKeys("477023");
				driver.findElement(By.id("submit")).click();
				                                                  
				driver.get(prsCreateUrl);
		}
}
	catch(Exception e) {
		System.out.println(e);
	}
}


public void firstPage(Robot r,Alert alert,String name,String acct1,String acct2,String acctType,String  testInputNbr,String  tinCount,String  trk) {
	  
	  
	  
	  
	  Select CEDropDown=null;
	  
	  
	  try {
			//Setting Default Settings.
			driver.findElement(By.name("accName")).sendKeys(name);
			driver.findElement(By.name("vep_ae_num")).sendKeys("1575");
			driver.findElement(By.id("qRC_Y")).click();
			driver.findElement(By.id("qIA_Y")).click();
			driver.findElement(By.name("svcOpt")).click();
		
			
			//If it is nine digit express and will check for combo accounts.
			if (acctType.equals("Express 9-digit")) {
				driver.findElement(By.id("NineDigitlAccountNumbersRadio")).click();
				
				if (acct2==null || acct2 =="" || acct2.equals("")||acct2.equals("null")) {
					driver.findElement(By.id("Txt_Acc_No")).sendKeys(acct1);
				}
				else {
					driver.findElement(By.id("Txt_Acc_No")).sendKeys(acct1+
							Keys.chord(Keys.SHIFT, Keys.ENTER)
							+acct2);
				}
			}
			
			
			//If it is CE Level. Please note that for some reason you MUST select date option before entering in account info.
			//Otherwise when selecting date it will uncheck the box for for selecting all accounts.
			if (acctType.equals("CE Level")) {
			
				  
				  driver.findElement(By.id("ceLevelAccountNumbersRadio")).click();
				  Thread.sleep(1000);
				  CEDropDown = new Select(driver.findElement(By.name("celevel")));
				  CEDropDown.selectByValue("EAN No");
				  driver.findElement(By.name("ceLevelAcc")).sendKeys(acct1);
				  driver.findElement(By.cssSelector("input[type='button'][value='Lookup & Add']")).click();
				  try {
					  Thread.sleep(2000);
					  driver.switchTo().alert().accept();
				  }
				  catch(Exception e) {
					  System.out.println("no popup");
				  }
				  Thread.sleep(6000);
				  driver.findElement(By.name("pricingType")).click();
				  Thread.sleep(6000);
				  driver.findElement(By.cssSelector("input[class='selecctall'][type='checkbox']")).click();
				  Thread.sleep(6000);
				
			}
			
			//If national express.
			if (acctType.equals("Express National")) {
				 driver.findElement(By.cssSelector("input[name='accType'][value='N']")).click();
				 driver.findElement(By.cssSelector("input[name='natl_acc'][type='text']")).sendKeys(acct1);
			}
			
			
			//Select date option. If it was CE Level option then this was already called.
			if (!acctType.equals("CE Level")) {
				driver.findElement(By.name("pricingType")).click();
			}
			
			driver.findElement(By.name("beginDate")).sendKeys(startDate);
			driver.findElement(By.name("RCDate")).sendKeys(endDate);
			driver.findElement(By.name("probDesc")).sendKeys("BST Test");
			driver.findElement(By.name("qED")).click();
			driver.findElement(By.id("Next_Down")).click();
			
			//Will ask if we are sure to continue if CE option selected.
			if (acctType.equals("CE Level")) {
				Thread.sleep(2000);
				r = new Robot();

				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
			}
			
			Thread.sleep(3000);
			
			try {
			Alert a = driver.switchTo().alert();
			// Get the text of the alert or prompt
			System.out.println(a.getText());
			// And acknowledge the alert (equivalent to clicking "OK")
			a.accept();
			
				String[] resultArray = new String[2];
			  	resultArray[0]="fail";
			  	resultArray[1]="Out of Scope";
			  	if(databaseDisabled.contentEquals("false")) {
			  		vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],null);
			  	}
			  	
			}
			catch(Exception  e) {
				System.out.println(e);
			}
			}catch(Exception e) {
				System.out.println("No out of scope Error");
				return;
			}
			
		try {	
//This will handle account if it was actually CE but we put 9 digit account.
if (!acctType.equals("CE Level")) {
try {
	alert=null;
	alert = new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
}
catch(Exception e) {}
if(alert != null)
{
	try {
		r = new Robot();


		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		try {
		driver.findElement(By.id("ceLevelAccountNumbersRadio")).click();
		Thread.sleep(3000);
		}
		catch(Exception eee) {
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			driver.findElement(By.id("ceLevelAccountNumbersRadio")).click();
			Thread.sleep(1000);
			
		}
		CEDropDown = new Select(driver.findElement(By.name("celevel")));
		CEDropDown.selectByValue("EAN No");
		driver.findElement(By.name("ceLevelAcc")).sendKeys(acct1);
		driver.findElement(By.cssSelector("input[type='button'][value='Lookup & Add']")).click();
		Thread.sleep(6000);
		driver.findElement(By.cssSelector("input[class='selecctall'][type='checkbox']")).click();
		Thread.sleep(6000);
		driver.findElement(By.id("Next_Down")).click();
		Thread.sleep(5000);
		if(driver.switchTo().alert() != null){
			  r.keyPress(KeyEvent.VK_ENTER);
			  r.keyRelease(KeyEvent.VK_ENTER);
			}
		Thread.sleep(5000);
	
			  r.keyPress(KeyEvent.VK_ENTER);
			  r.keyRelease(KeyEvent.VK_ENTER);
			  Thread.sleep(3000);
			 // driver.findElement(By.cssSelector("input[type='button'][value='Lookup & Add']")).click();
			  driver.findElement(By.cssSelector("input[type='button'][value='Lookup & Add']")).click();
			
	  Thread.sleep(15000);	  
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 Assert.fail("Failed during CE.");
		} 
	}
}


Thread.sleep(5000);
try {
JavascriptExecutor js= (JavascriptExecutor) driver;
js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
String temp=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[5]/td/b/font")).getText();

//String temp=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[5]/td/b/font/text()[1]")).getText();
//										 /html/body/table/tbody/tr/td/form/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[5]/td/b/font/text()[1]

//if (temp.equals("Please check the account number(s) entered. Following account number(s) is(are) not valid:")) {

	String[] resultArray = new String[2];
	resultArray[0]="fail";
	resultArray[1]="Acct Error";
	if(databaseDisabled.contentEquals("false")) {
		vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],null);
  	}
	}
	



catch(Exception ee) {

System.out.println("No Acct Error");
}
		} catch(Exception e){
			System.out.println(e);
		}
	  }



















			
	public void secondPage( List<WebElement> comboBoxesHandling,String service1,String service2,String rerateType,String trkng1,String trkng2,String inv1,String inv2,String acct1, String acct2,String  testInputNbr,String  tinCount) {

		ArrayList<String>cc1 = new ArrayList<String>();
		ArrayList<String>cc2 = new ArrayList<String>();

		try {
		Thread.sleep(10000);				
		//Determines which checkboxes to select for first trk.
		switch(service1) {
		case "Express Domestic":
			System.out.println("Running Here Dom Exp");
			driver.findElement(By.xpath("//*[@name='svc'][@value='D']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='D']")));
			cc1.add("D");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
			cc2.add("D");
			Thread.sleep(1000);	
			break;
		case "Express International":
			
			driver.findElement(By.xpath("//*[@name='svc'][@value='I']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='I']")));
			cc1.add("I");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
			cc2.add("I");
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
			Thread.sleep(1000);
			break;
			
		case "Ground Domestic":
			driver.findElement(By.xpath("//*[@name='svc'][@value='G']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='G']")));
			cc1.add("G");
			Thread.sleep(1000);
			//driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
			cc2.add("G");
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
			Thread.sleep(1000);
			break;
		
		case "Ground International":
			
			driver.findElement(By.xpath("//*[@name='svc'][@value='N']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='N']")));
			Thread.sleep(1000);
			cc1.add("N");
                                  //driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
		//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
			
			cc2.add("G");
			Thread.sleep(1000);
			break;
		
		case "SmartPost":
			driver.findElement(By.xpath("//*[@name='svc'][@value='P']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='P']")));
			cc1.add("P");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")).click();
			cc2.add("P");
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")));
			Thread.sleep(1000);
			break;
	
		case "NT":
			driver.findElement(By.xpath("//*[@name='svc'][@value='S']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='S']")));
			cc1.add("S");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
			//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
			cc2.add("D");
			Thread.sleep(1000);
			break;
		}
		Thread.sleep(5000);

		//Determines which checkboxes to select for first trk.


		if (service2!=null || service2 !="") {

			switch(service2) {
			case "Express Domestic":
				
				driver.findElement(By.xpath("//*[@name='svc'][@value='D']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='D']")));
				cc1.add("D");
				
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
			//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
				cc2.add("D");
				Thread.sleep(1000);
				break;
			case "Express International":
				
				driver.findElement(By.xpath("//*[@name='svc'][@value='I']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='I']")));
				cc1.add("I");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
			//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
				cc2.add("I");
				Thread.sleep(1000);
				break;
			
			case "Ground Domestic":
				driver.findElement(By.xpath("//*[@name='svc'][@value='G']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='G']")));
				cc1.add("G");
				Thread.sleep(1000);
			////	driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
			//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
			//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
				cc2.add("G");
				Thread.sleep(1000);
				break;
			
			case "Ground International":
				driver.findElement(By.xpath("//*[@name='svc'][@value='N']")).click();
			//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='N']")));
				Thread.sleep(1000);
				cc1.add("N");
			//	driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
			//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
				cc2.add("G");
				Thread.sleep(1000);
				break;
			case "SmartPost":
				driver.findElement(By.xpath("//*[@name='svc'][@value='P']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='P']")));
				cc1.add("P");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")));
				cc2.add("P");
				Thread.sleep(1000);
				break;
		
			case "NT":
				driver.findElement(By.xpath("//*[@name='svc'][@value='S']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='S']")));
				cc1.add("S");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
				//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
				cc2.add("D");
				Thread.sleep(1000);
				break;
			}
		}
		
	Boolean isChecked=false;
		while(isChecked==false) {
		
				 isChecked=true;
				for (int k=0;k<cc1.size();k++) {
					System.out.println("BOOLEAN: "+cc1.get(k));
				//	driver.findElement(By.xpath("//*[@name='svc'][@value='S']")).click();
					System.out.println("BOOLEAN: "+    driver.findElement(By.xpath("//*[@name='svc'][@value='"+cc1.get(k)+"']")).isSelected());
						if(driver.findElement(By.xpath("//*[@name='svc'][@value='"+cc1.get(k)+"']")).isSelected()==false){
							isChecked=false;
							driver.findElement(By.xpath("//*[@name='svc'][@value='"+cc1.get(k)+"']")).click();
							
						}
				}
				
				for (int k=0;k<cc2.size();k++) {
					System.out.println("BOOLEAN: ");
					System.out.println("BOOLEAN: "+ driver.findElement(By.xpath("//*[@name='splHandle'][@value='"+cc2.get(k)+"']")).isSelected());
					
					if(driver.findElement(By.xpath("//*[@name='splHandle'][@value='"+cc2.get(k)+"']")).isSelected()==false){
						isChecked=false;
						driver.findElement(By.xpath("//*[@name='splHandle'][@value='"+cc2.get(k)+"']")).click();
						
					}
					
					Thread.sleep(1000);
					
					
				}

		System.out.println("MADE IT HERE");
		}
	
		
Thread.sleep(2000);
	
		//Date Range
		if (rerateType.equals("Date Range")) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[7]/td/font/input")).click();
			
			//driver.findElement(By.xpath("//*[@name='accType'][@value='A']")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[12]/td[2]/font[1]/input")).sendKeys("12/01/2001");
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td[2]/font[1]/input")).sendKeys(endDate);
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/div/input[4]")).click();	
		}
		
		//Tracking Number
		else if (rerateType.equals("Tracking Number")) {
			driver.findElement(By.xpath("//*[@name='accType'][@value='T']")).click();
				Thread.sleep(2000);
			if (trkng2==null || trkng2 =="" || trkng2.equals("")||trkng2.equals("null")) {
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[17]/td/table/tbody/tr[2]/td[1]/input")).sendKeys(trkng1);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[17]/td/table/tbody/tr[2]/td[2]/input")).sendKeys(acct1);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/div/input[4]")).click();	
			}
			//If two Trk Needed.
			else {
				Thread.sleep(3000);
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[14]/td/font[5]/input[1]")).sendKeys("1");
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[14]/td/font[5]/input[2]")).click();
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[17]/td/table/tbody/tr[2]/td[1]/input")).sendKeys(trkng1);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[17]/td/table/tbody/tr[3]/td[1]/input")).sendKeys(trkng2);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[17]/td/table/tbody/tr[2]/td[2]/input")).sendKeys(acct1);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[17]/td/table/tbody/tr[3]/td[2]/input")).sendKeys(acct2);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/div/input[4]")).click();	
			}
		}
		
		//Invoice
		else if  (rerateType.equals("Invoice")) {
			driver.findElement(By.xpath("//*[@name='accType'][@value='I']")).click();
			Thread.sleep(2000);
			if (inv2==null || inv2 =="" || inv2.equals("")||inv2.equals("null")) {
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[9]/td/font/input")).click();
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td/table/tbody/tr[2]/td[1]/input")).sendKeys(inv1);
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td/table/tbody/tr[2]/td[2]/input")).sendKeys(acct1);
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/div/input[4]")).click();	
			}
			//If Two invoices needed.
			else{
				Thread.sleep(3000);
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[12]/td/font[5]/input[1]")).sendKeys("1");	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[12]/td/font[5]/input[2]")).click();
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td/table/tbody/tr[2]/td[1]/input")).sendKeys(inv1);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td/table/tbody/tr[3]/td[1]/input")).sendKeys(inv2);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td/table/tbody/tr[2]/td[2]/input")).sendKeys(acct1);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td/table/tbody/tr[3]/td[2]/input")).sendKeys(acct2);	
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/div/input[4]")).click();	
				}
			}

		
		

		
	}		
	
	catch(Exception e) {
		
		System.out.println(e);
		
	  	String[] resultArray = new String[2];
	  	resultArray[0]="fail";
	  	resultArray[1]="Failed During Second Page";
	  	if(databaseDisabled.contentEquals("false")) {
	  		vc.writeToDb(testInputNbr,tinCount,trkng1,resultArray[0],resultArray[1],null);
	  	}
	return;
	}
	}





	
	
	
	
	public void thirdPage(String name,Boolean express,Boolean ground,Boolean combo,String testInputNbr,String tinCount,String trk) {
		int count=0;
		String requestId="";
		
		try {
	
		
			driver.get("https://testsso.secure.fedex.com/L3C/PRSApps/inbox/inbox_router.jsp?inbox_id=11");
	
		
		
		  

		wait = new WebDriverWait( driver,60);
		Thread.sleep(5000);
		
	//	count = getRerateId(driver,name,testCounter);
		
	
		
		//System.out.println()
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]")).getText();
		count = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]")).size();
		requestId = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[3]/font")).get(count-1).getText();
		
		 
	
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		
		Thread.sleep(1000);
		
		//driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[1]/input")).get(count-1).sendKeys(r.keyPress(KeyEvent.VK_ENTER));
		driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[1]/input")).get(count-1).click();
		int counter=0;
		
		//added this code because IE SUCKS!!!!!!
		while (true) {
			if (counter==10){
				break;
			}
			if (driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[1]/input")).get(count-1).isSelected()==true) {
				break;
			}
				else {
					driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[1]/input")).get(count-1).click();
					
				}
			}
		
		
		js.executeScript("window.scrollTo(0, 0)");
		Thread.sleep(1000);
		driver.findElement(By.name("btnAcceptTask")).click();
		Thread.sleep(3000);
		int counter2=0;
		while (true) {
			if (counter2==10){
				break;
			}
			if (driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[1]/input")).get(count-1).isSelected()!=true) {
				break;
			}
				else {
					driver.findElement(By.name("btnAcceptTask")).click();
					counter2++;	
				}
			}
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		Thread.sleep(3000);
		driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]")).get(count-1).click();
		Thread.sleep(2000);
		wait = new WebDriverWait( driver,10);
		driver.findElement(By.xpath("//input[@name='pa_actn_cd']/following-sibling::input[1]")).click();
		Select dr1 = new Select(driver.findElement(By.name("root_cause")));
		dr1.selectByVisibleText("Sales Caused");
		Select dr2 = new Select(driver.findElement(By.name("prim_rate_corr_cd")));
		dr2.selectByValue("3");
		Select dr3 = new Select(driver.findElement(By.name("scndy_rate_corr_cd")));
		dr3.selectByValue("3");
		Select dr4 = new Select(driver.findElement(By.name("trtr_rate_corr_cd")));	
		dr4.selectByValue("210");
		Select dr5 = new Select(driver.findElement(By.name("poe_cd")));	
		dr5.selectByVisibleText("Revenue");
		Select dr6 = new Select(driver.findElement(By.name("opp_cd")));	
		dr6.selectByVisibleText("Systemic");
		driver.findElement(By.name("action_taken")).sendKeys("BST Test");
		driver.findElement(By.name("RV01")).sendKeys("0000");
		Select dr7 = new Select(driver.findElement(By.name("prc_exp_grnd_ind")));
		Thread.sleep(3000);
		
		if(express==true) {
			dr7.selectByValue("E");
			
		}
		else if(ground==true) {
			dr7.selectByValue("G");
		}
		else if(combo==true) {
			dr7.selectByValue("C");
		}
		Thread.sleep(3000);
		Select dr8 = new Select(driver.findElement(By.name("prc_error_at")));
		dr8.selectByValue("N");
		driver.findElement(By.name("prc_err_resp_emp")).sendKeys("477023");
		driver.findElement(By.name("continue")).click();
		Alert alertPopup =wait.until(ExpectedConditions.alertIsPresent());	
		alertPopup.accept();
	//	sheet.getRow(i).createCell(14).setCellValue("Processed");
	//	fout = new FileOutputStream(new File(filepath));
	//	wb.write(fout);	

	  	 
	  	String[] resultArray = new String[2];
	  	resultArray[0]="in progress";
	  	resultArray[1]="processed";
	  	if(databaseDisabled.contentEquals("false")) {
	  		vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],requestId);
	  	}
	  	
	  	try {
	  	driver.quit();
		driver.close();
	  	}
	  	catch(Exception e) {
	  		System.out.println(e);
	  	}
		 	 	
	       	 return;
	  	  
	  	  
		
	}

	catch (NoSuchElementException e)
	{
		System.out.println(e);
		
		if(url1.contentEquals("https://testsso.secure.fedex.com/L3C/PRSApps/rerate/iscreen/rrAERerateMain.jsp")||url2.contentEquals("https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp"))
		{
			
			System.out.println("NoSuchElementException "+e);

			
			
			
		  	String[] resultArray = new String[2];
		  	resultArray[0]="fail";
		  	resultArray[1]="NoSuchElementException on third page";
		  	if(databaseDisabled.contentEquals("false")) {
		  		vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],requestId);
		  	}
		
		}
		else
		{
			
			System.out.println("NoSuchElementException "+e);
		
			 
		  	String[] resultArray = new String[2];
		  	resultArray[0]="fail";
		  	resultArray[1]="NoSuchElementException on third page";
		  	if(databaseDisabled.contentEquals("false")) {
		  		vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],requestId);
		  	}
		}
	
	}
	catch (WebDriverException h)
	{
	
		System.out.println("WebDriverException "+h);

		 
	  	String[] resultArray = new String[2];
	  	resultArray[0]="fail";
	  	resultArray[1]="WebDriverException on third page";
	  	if(databaseDisabled.contentEquals("false")) {
	  		vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],requestId);
	  	}
	  	
	}

	catch(NullPointerException k)
	{
		System.out.println("STEPHEN "+k);
		 
	  	String[] resultArray = new String[2];
	  	resultArray[0]="fail";
	  	resultArray[1]="NullPointerException on third page";
	  	if(databaseDisabled.contentEquals("false")) {
	  		vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],requestId);
	  	}
	  	
	
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		System.out.println("InterruptedException "+e);
		
	  	String[] resultArray = new String[2];
	  	resultArray[0]="fail";
	  	resultArray[1]="InterruptedException on third page";
	  	if(databaseDisabled.contentEquals("false")) {
	  
	  	 vc.writeToDb(testInputNbr,tinCount,trk,resultArray[0],resultArray[1],requestId);
	  	}
	  	
	  	
	}
		
	}
	
	






public synchronized int getRerateId(WebDriver driver, String name,int testCounter) {
	int count;
	String requestId;
	//System.out.println()
	driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]")).getText();
	count = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]")).size();
	requestId = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[3]/font")).get(count-1).getText();
	
	
	return count;
	
}


}
		