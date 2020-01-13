package rerate;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.config;

public class abc {
	 Boolean isPresent=false;
	int retryCounter=0;
	int i=0;
	Object o;
	WebDriverWait wait;
	config c;
	String browser;
	Boolean level;
	long serviceLong1,serviceLong2;
	long invoiceLong1,invoiceLong2;
	long acctLong1,acctLong2;
	long trkngLong1,trkngLong2;
	String a ;
	int count ;
	String url1 ="https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp";
	String url2="https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp";
	String  name;
	String trkng1,trkng2;
	String acct1,acct2;
	String inv1,inv2;
	String service1,service2;
	String rerateType;
	String acctType;
	String  sh1;
	String filepath;
	String d;
	String d2;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static FileInputStream fin;
	static FileOutputStream fout;
	public int  rowcount;
	//WebDriver driver;
	WebDriver driver;
	static int sheetcount;
	static int j ;
	static Boolean combo,express,ground;
	String temp;
	 Select CEDropDown;
	 Robot r;
	 List<WebElement> comboBoxesHandling = new ArrayList<WebElement>();
	 ArrayList<String> cc1 = new ArrayList<String>();
	 ArrayList<String> cc2 = new ArrayList<String>();
	 Alert alert;
	 Boolean isChecked=false;
         Boolean compatibleMode;
         Boolean chrome;
         String levelUrl;
         
         WebElement element;
/*	
	public abc (String filepath,String d,String d2,String level,boolean broswer,boolean compatibleMode,boolean chrome) throws IOException, InterruptedException{
		
		this.filepath=filepath;
		this.d=d;
		this.d2=d2;
		this.level=level;
		this.browser=broswer;
        this.compatibleMode=compatibleMode;
        System.out.println("HEREEEEE");
        this.chrome=chrome;
   
	
	}	
	*/
	
	public abc (String filepath,String d,String d2,Boolean level,String broswer,boolean compatibleMode,config c) throws IOException, InterruptedException{
		
		this.filepath=filepath;
		this.d=d;
		this.d2=d2;
		this.level=level;
		this.browser=broswer;
        this.compatibleMode=compatibleMode;
        this.c=c;
 
        setGlobalVars();
        setCurrentData();
        /*
        System.out.println(filepath);
		System.out.println(startDateText);
		System.out.println(endDateText);
		System.out.println(level);
		System.out.println(broswer);
		System.out.println(compatibleMode);
		*/
        
	}	
	
	public void setGlobalVars() {
	    if (level==false)
			{
				levelUrl="https://devsso.secure.fedex.com/L2/PRSApps/";
			}
			else if (level==true)
			{
				levelUrl="https://testsso.secure.fedex.com/L3/PRSApps/";
			}

	    if (browser.equals("1")) {
	    	c.setProperty(c.getIeProperty(),c.getIeDriverPath());
    		driver = new InternetExplorerDriver();
	    }
	    if (browser.equals("2")) {
	    	c.setProperty(c.getChromeProperty(),c.getChromeDriverPath());
    		driver = new ChromeDriver();
	    }
	 
	    
	  //  driver.get("https://testsso.secure.fedex.com/L3/PRSApps/");
	    
	    try {
			fin =new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb=new XSSFWorkbook(fin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sheetcount = wb.getNumberOfSheets();
		Object o =null;
	    
		
	}
	
	
	
	public void setCurrentData() {
		
		
		login();
		for(j=0;j<1;j++)
		{
			sheet=	wb.getSheetAt(j);
			sh1 =wb.getSheetAt(j).getSheetName();
			rowcount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			
			while (!getCellValue(o,sheet.getRow(i).getCell(2)).equals("null"))
			{
              i++;                       
				try {
					acct1 =	getCellValue(o,sheet.getRow(i).getCell(2));		
					System.out.println(acct1);	
					acct2=	getCellValue(o,sheet.getRow(i).getCell(3));	
					System.out.println(acct2);
					trkng1=	getCellValue(o,sheet.getRow(i).getCell(4));	
					System.out.println(trkng1);
					trkng2 =	getCellValue(o,sheet.getRow(i).getCell(5));	
					System.out.println(trkng2);	
					inv1 =	getCellValue(o,sheet.getRow(i).getCell(6));	
					System.out.println(inv1);	
					inv2 =	getCellValue(o,sheet.getRow(i).getCell(7));	
					System.out.println(inv2);	
					service1=	getCellValue(o,sheet.getRow(i).getCell(8));	
					System.out.println(service1);	
					service2=	getCellValue(o,sheet.getRow(i).getCell(9));	
					System.out.println(service2);	
					rerateType=	getCellValue(o,sheet.getRow(i).getCell(10));	
					System.out.println(rerateType);
					acctType=	getCellValue(o,sheet.getRow(i).getCell(11));	
					System.out.println(acctType);	
					name=	getCellValue(o,sheet.getRow(i).getCell(12));	
					System.out.println(name);	
					
					//Resets possible combos for each Tracking Number
					ground=false;
					express=false;
					combo=false;
					
					//Setting result of any combo.
					if (service1.equals("Ground International") || service1.equals("Ground Domestic") || service1.equals("SmartPost")) {
						ground=true;
						}
						else {
							express=true;
							}

					if (!service2.equals("null")) {
						if (service2.equals("Ground International") || service2.equals("Ground Domestic") || service2.equals("SmartPost")) {
							ground=true;
						}
						else {
							express=true;
							}
						}
					
					if (express==true && ground==true) {
						combo=true;
						ground=false;
						express=false;
					}
					
	ReadWrite();
			
					//This will retry the trk once.
					if (retryCounter==1) {
						retryCounter=0;
						ReadWrite();
					}
				}
				
				
				catch(Exception e) {
					System.out.println(e);
				}

		}
		}
	}

	
	
	public void login() {
		 	driver.get(levelUrl);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait = new WebDriverWait( driver,10);
			driver.manage().window().maximize();          
			driver.findElement(By.id("username")).sendKeys("477023");
			driver.findElement(By.id("password")).sendKeys("477023");
			driver.findElement(By.id("submit")).click();
	}
	
	
	public void ReadWrite() throws IOException, InterruptedException, UnreachableBrowserException, TimeoutException, StaleElementReferenceException, AWTException {

		if (level==false)
		{                                                   
			driver.get("https://devsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
		}
		else if (level==true)
		{
			driver.get("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
		}
		
		try {
		isPresent = driver.findElements(By.xpath("//*[@id=\"form1\"]/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[1]/b/font")).size() > 0;
		//element = driver.findElement(By.xpath("//*[@id=\"form1\"]/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[1]/b/font"));
		}
		
		catch(Exception e) {
			driver.quit();
			login();
			if (level==false)
			{                                                   
				driver.get("https://devsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
			}
			else if (level==true)
			{
				driver.get("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
			}
		}
		
		
		
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
					
					driver.findElement(By.name("beginDate")).sendKeys(d);
					driver.findElement(By.name("RCDate")).sendKeys(d2);
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
				Thread.sleep(1000);
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
				Thread.sleep(2000);
				if(driver.switchTo().alert() != null){
					  r.keyPress(KeyEvent.VK_ENTER);
					  r.keyRelease(KeyEvent.VK_ENTER);
					}
			  Thread.sleep(15000);	  
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
					
					
					
					
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
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='I']")));
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
						Thread.sleep(1000);
						break;
						
					case "Ground Domestic":
						driver.findElement(By.xpath("//*[@name='svc'][@value='G']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='G']")));
						Thread.sleep(1000);
						//driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
						//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
						Thread.sleep(1000);
						break;
					
					case "Ground International":
						
						driver.findElement(By.xpath("//*[@name='svc'][@value='N']")).click();
						//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='N']")));
						Thread.sleep(1000);
						
                                                //driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
						//comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
						Thread.sleep(1000);
						break;
					
					case "SmartPost":
						driver.findElement(By.xpath("//*[@name='svc'][@value='P']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='P']")));
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")));
						Thread.sleep(1000);
						break;
				
					case "NT":
						driver.findElement(By.xpath("//*[@name='svc'][@value='S']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='S']")));
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
						comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
						Thread.sleep(1000);
						break;
					}
					Thread.sleep(5000);
	
					//Determines which checkboxes to select for first trk.
					if (service2!=null || service2 !="" ||service2.equals("")||service2.equals("null")) {
						switch(service2) {
						case "Express Domestic":
							
							driver.findElement(By.xpath("//*[@name='svc'][@value='D']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='D']")));
							Thread.sleep(1000);
							driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
							Thread.sleep(1000);
							break;
						case "Express International":
							
							driver.findElement(By.xpath("//*[@name='svc'][@value='I']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='I']")));
							Thread.sleep(1000);
							driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
							Thread.sleep(1000);
							break;
						
						case "Ground Domestic":
							driver.findElement(By.xpath("//*[@name='svc'][@value='G']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='G']")));
							Thread.sleep(1000);
						////	driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
						//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
							Thread.sleep(1000);
							driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
							Thread.sleep(1000);
							break;
						
						case "Ground International":
							driver.findElement(By.xpath("//*[@name='svc'][@value='N']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='N']")));
							Thread.sleep(1000);
						//	driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")).click();
						//	comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='I']")));
							Thread.sleep(1000);
							driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='G']")));
							Thread.sleep(1000);
							break;
						case "SmartPost":
							driver.findElement(By.xpath("//*[@name='svc'][@value='P']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='P']")));
							Thread.sleep(1000);
							driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='P']")));
							Thread.sleep(1000);
							break;
					
						case "NT":
							driver.findElement(By.xpath("//*[@name='svc'][@value='S']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='svc'][@value='S']")));
							Thread.sleep(1000);
							driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")).click();
							comboBoxesHandling.add(driver.findElement(By.xpath("//*[@name='splHandle'][@value='D']")));
							Thread.sleep(1000);
							break;
						}
					}
					
				
					while(isChecked==false) {
						checkCheckBoxes();				
					}
					System.out.println("MADE IT HERE");
					
				
					
		Thread.sleep(2000);
				
					//Date Range
					if (rerateType.equals("Date Range")) {
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@name='accType'][@value='A']")).click();
						driver.switchTo().alert().accept();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[12]/td[2]/font[1]/input")).sendKeys("12/01/2001");
						driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td[2]/font[1]/input")).sendKeys(d2);
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

					Thread.sleep(15000);
					
					/*
					
					if ( sh1.equals("DOMESTIC") || sh1.equals("SPECIAL"))
					{
						driver.findElement(By.name("svc")).click();
					}
					else if (sh1.equals("NT"))
					{
						driver.findElement(By.xpath("//input[@name='svc']/following-sibling::input[5]")).click();
					}
					driver.findElement(By.name("splHandle")).click();
					if (sh1.equals("DOMESTIC" )||sh1.equals("NT"))
					{
						driver.findElement(By.xpath("//input[@name='accType']/parent::font/parent::td/parent::tr/following-sibling::tr/td/font/input")).click();
						driver.findElement(By.name("track_acc")).sendKeys(trkng1);
					}
					else if (sh1.equals("SPECIAL"))
					{
						driver.findElement(By.xpath("//input[@name='accType']/parent::font/parent::td/parent::tr/following-sibling::tr[2]/td/font/input")).click();
						//long inv=(long) wb.getSheetAt(j).getRow(i).getCell(4).getNumericCellValue();
					//	inv1 =String.valueOf(inv); 
						driver.findElement(By.name("inv_acc")).sendKeys(inv1);
					}
					driver.findElement(By.name("exp_acc")).sendKeys(acct1);
					driver.findElement(By.name("Continue")).click();
					Thread.sleep(7000);

*/
					if (level==false)
					{
						driver.get("https://devsso.secure.fedex.com/L2/PRSApps/inbox/inbox_router.jsp?inbox_id=11");
					}
					else if (level==true)
					{
						driver.get("https://testsso.secure.fedex.com/L3/PRSApps/inbox/inbox_router.jsp?inbox_id=11");
					}			
					driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]")).getText();
					count = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]")).size();
					a = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[3]/font")).get(count-1).getText();
					sheet=wb.getSheetAt(0);
					sheet.getRow(i).createCell(13).setCellValue(a);
					fout = new FileOutputStream(new File(filepath));
					wb.write(fout);
					driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[1]/input")).get(count-1).click();
					Thread.sleep(1000);
					driver.findElement(By.name("btnAcceptTask")).click();
					Thread.sleep(1000);
					driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]")).get(count-1).click();
					Thread.sleep(2000);
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
					Alert alert =wait.until(ExpectedConditions.alertIsPresent());	
					alert.accept();
					sheet.getRow(i).createCell(14).setCellValue("Processed");
					fout = new FileOutputStream(new File(filepath));
					wb.write(fout);	
				}
				catch (NoSuchElementException e)
				{
					System.out.println(e);
					if(url1.contentEquals("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp")||url2.contentEquals("https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp"))
					{
						System.out.println("STEPHEN "+e);
						sheet.getRow(i).createCell(14).setCellValue("Invalid Account Number");
						fout = new FileOutputStream(new File(filepath));
						wb.write(fout);
			
					}
					else
					{
						System.out.println("STEPHEN "+e);
						sheet.getRow(i).createCell(14).setCellValue("Try This Manually");
						fout = new FileOutputStream(new File(filepath));
						wb.write(fout);
				
					}
				}
				catch (SocketException g)
				{
					System.out.println("STEPHEN "+g);
					sheet.getRow(i).createCell(14).setCellValue("Try This Manually");
					fout = new FileOutputStream(new File(filepath));
					wb.write(fout);
			
				}
				catch (WebDriverException h)
				{
					System.out.println("STEPHEN "+h);
					sheet.getRow(i).createCell(14).setCellValue("Try This Manually");
					fout = new FileOutputStream(new File(filepath));
					wb.write(fout);
				

				}
				
				
				catch (AWTException s)
				{
					System.out.println("STEPHEN "+s);
					sheet.getRow(i).createCell(14).setCellValue("Try This Manually");
					fout = new FileOutputStream(new File(filepath));
					wb.write(fout);
				

				}
			

				catch(NullPointerException k)
				{
					System.out.println("STEPHEN "+k);
				
				}


			}	
		

	
	

	

	
	
	
	
	
	
	
	
	
	public static String getCellValue(Object o,Cell cell) {
	    String temp="";
		if (cell != null) {
	        switch (cell.getCellType()) {
	        case Cell.CELL_TYPE_STRING:
	            o= cell.getStringCellValue();
	            if (o!=null) {
	            	return temp=o.toString();
	            }
	            else {
	            	return "null";
	            }

	        case Cell.CELL_TYPE_BOOLEAN:
	        	 o= cell.getBooleanCellValue();
	        	  if (o!=null) {
	        		  return temp=new BigDecimal(o.toString()).toPlainString();
	        		 // return temp=o.toString();
	                  }
	                  else {
	                  	return "null";
	                  }
	        

	        case Cell.CELL_TYPE_NUMERIC:
	        	 o= cell.getNumericCellValue();
	        	  if (o!=null) {
	                 return temp=new BigDecimal(o.toString()).toPlainString();
	                // return temp=o.toString();
	                  }
	                  else {
	                  	return "null";
	                  }
	        }
	    

	}	
	    return "null";
	}
	
	public void checkCheckBoxes() throws InterruptedException {
		
		 isChecked=true;
		for (int k=0;k<cc1.size();k++) {
			System.out.println("BOOLEAN: ");
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
		
	}
	
	
}




