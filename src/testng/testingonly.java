package testng;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.SocketException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import configuration.config;
import configuration.excel;

public class testingonly {
	  
	String homePath,tempFile,configFile;
	excel e;
	 Boolean isPresent=false;
	int retryCounter=0;
	int i=0;
	Object o;
	WebDriverWait wait1,wait2,wait3,wait4;
	config c;
	
	Boolean level;
	long serviceLong1,serviceLong2;
	long invoiceLong1,invoiceLong2;
	long acctLong1,acctLong2;
	long trkngLong1,trkngLong2;
	String a ;
	int count1,count2,count3,count4 ;
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
	String d="02/20/2020";
	String d2="02/21/2020";;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static FileInputStream fin;
	static FileOutputStream fout;
	public int  rowcount;
	//WebDriver driver;
	WebDriver driver,driver1,driver2,driver3,driver4;
	static int sheetcount;
	static int j ;
	static Boolean combo1=false,express1=false,ground1=false;
	static Boolean combo2=false,express2=false,ground2=false;
	static Boolean combo3=false,express3=false,ground3=false;
	static Boolean combo4=false,express4=false,ground4=false;
	
	
	String temp;
	 Select CEDropDown1,CEDropDown2,CEDropDown3,CEDropDown4;
	 Robot r1,r2,r3,r4;
	 List<WebElement> comboBoxesHandling1 = new ArrayList<WebElement>();
	 List<WebElement> comboBoxesHandling2 = new ArrayList<WebElement>();
	 List<WebElement> comboBoxesHandling3 = new ArrayList<WebElement>();
	 List<WebElement> comboBoxesHandling4 = new ArrayList<WebElement>();
	 ArrayList<String> cc11 = new ArrayList<String>();
	 ArrayList<String> cc12 = new ArrayList<String>();
	 ArrayList<String> cc21 = new ArrayList<String>();
	 ArrayList<String> cc22 = new ArrayList<String>();
	 ArrayList<String> cc31 = new ArrayList<String>();
	 ArrayList<String> cc32 = new ArrayList<String>();
	 ArrayList<String> cc41 = new ArrayList<String>();
	 ArrayList<String> cc42 = new ArrayList<String>();
	 Alert alert1,alert2,alert3,alert4;
	 Boolean isChecked1=false,isChecked2=false,isChecked3=false,isChecked4=false;
        Boolean compatibleMode;
        Boolean chrome;
        
        
        WebElement element;
	
        
       String browser="2";
        String chromeSetProperty="webdriver.chrome.driver";
		String ieSetProperty="webdriver.ie.driver";
		//String firefoxSetProperty="";
		
		String chromePath="C:\\Users\\theth\\git\\Master-GUI\\drivers\\chromedriver.exe";
		String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
        
		String levelUrl="https://testsso.secure.fedex.com/L3/PRSApps";
		
		
		classExample ce;
		int rowCount;
		int colCount;
		int total;
		
		
		

		
		
		@BeforeClass
		public void setupExcel() {
			//or from eclipse.
	    	homePath=System.getProperty("user.dir");
	    	System.out.println("homePath" +System.getProperty("user.dir"));
	    	e = new excel(homePath+"\\test data\\rerate2.xlsx");
	    	e.setUpExcelWorkbook();
	    	e.setUpExcelSheet(0);
	    	e.setRowCountAutomatically(0);
	    	e.setColCountAutomatically(0);
	    	rowCount=e.getRowCount();
	    	colCount=e.getColCount()-2;
	    	total= rowCount/4;
	    	
		}
		
		
		
	    @DataProvider(name = "data-provider1")
	    public Object[][] dataProviderMethod1() { 
	   	 int totalRows=total;
    	 System.out.println("MOD "+rowCount%4);
    	 if (rowCount%4!=0) {
    		 totalRows++;
    	 }
    	 
	    System.out.println("total rows "+ totalRows);
	    	Object [][] obj = new Object[totalRows][colCount];
	    	int objCount=0;
	    	for(int i=0;i<=rowCount;i+=4) {
	    		for(int j=0;j<colCount;j++) {
	    			System.out.println(e.getCellData(i, j));
	    				obj[objCount][j]=e.getCellData(i, j);
	    			
	    		}
	    		objCount++;
	    	}

	    	return obj;
	    
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    @DataProvider(name = "data-provider2")
	    public Object[][] dataProviderMethod2() { 
	    	
	    	 int totalRows=total;
	    	 System.out.println("MOD "+rowCount%3);
	    	 if (rowCount%3!=0) {
	    		 totalRows++;
	    	 }
	    	
	  
	    	 
	    	Object [][] obj = new Object[totalRows][colCount];
	    	int objCount=0;
	    	for(int i=1;i<rowCount;i+=4) {
	    		for(int j=0;j<colCount;j++) {
	    			obj[objCount][j]=e.getCellData(i, j);
	    		}
	    		objCount++;
	    	}

	    	return obj;
	    
	    }
	    
	    @DataProvider(name = "data-provider3")
	    public Object[][] dataProviderMethod3() { 
	    	 int	totalRows=total;
	    	 System.out.println("MOD "+rowCount%2);
	    	 if (rowCount%2!=0) {
	    		 totalRows++;
	    	 }
	    	Object [][] obj = new Object[totalRows][colCount];
	    	int objCount=0;
	    	for(int i=2;i<rowCount;i+=4) {
	    		for(int j=0;j<colCount;j++) {
	    			obj[objCount][j]=e.getCellData(i, j);
	    		}
	    		objCount++;
	    	}

	    	return obj;
	    
	    }
	    
	    @DataProvider(name = "data-provider4")
	    public Object[][] dataProviderMethod4() { 
	    	 int	totalRows=total;
	    	 System.out.println("MOD "+rowCount%1);
	    	if (rowCount%1!=0) {
	    		 totalRows++;
	    	 }
	    	Object [][] obj = new Object[totalRows][colCount];
	    	int objCount=0;
	    	for(int i=3;i<rowCount;i+=4) {
	    		for(int j=0;j<colCount;j++) {
	    			obj[objCount][j]=e.getCellData(i, j);
	    		}
	    		objCount++;
	    	}

	    	return obj;
	    
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	  @Test(dataProvider="data-provider1")
	  public void testMethod(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name) {
	   // @Test
	  //  public void testMethod1() {
 try {
			  
			  driver1.quit();
		  }
		  catch(Exception eee) {
			  System.out.println(eee);
			  
		  }
		  
  	    System.setProperty(chromeSetProperty,chromePath);
  	    driver1 = new ChromeDriver();
  	    System.out.println(trkng1);
  	    
  	    
  	  if (service1.equals("Ground International") || service1.equals("Ground Domestic") || service1.equals("SmartPost")) {
			ground1=true;
			}
			else {
				express1=true;
				}

		if (!service2.equals("null")) {
			if (service2.equals("Ground International") || service2.equals("Ground Domestic") || service2.equals("SmartPost")) {
				ground1=true;
			}
			else {
				express1=true;
				}
			}
		
			if (express1==true && ground1==true) {
				combo1=true;
				ground1=false;
				express1=false;
			}

			System.out.println("Hello 1");
			doWork(driver1,wait1,CEDropDown1,alert1,r1,cc11,cc12,comboBoxesHandling1,isChecked1,count1,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,combo1,ground1,express1);
			
  	   
	    }  
	    
	  
	  
	  
	  @Test(dataProvider="data-provider2")
	   public void testMethod2(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name) {
//@Test
//public void testMethod2() {
 try {
			  
	 driver2.quit();
		  }
		  catch(Exception eee) {
			  System.out.println(eee);
			  
		  }
		  
  	    System.setProperty(chromeSetProperty,chromePath);
  	  driver2 = new ChromeDriver();
  	    System.out.println(trkng1);
  	    
  	    
  	  if (service1.equals("Ground International") || service1.equals("Ground Domestic") || service1.equals("SmartPost")) {
			ground2=true;
			}
			else {
				express2=true;
				}

		if (!service2.equals("null")) {
			if (service2.equals("Ground International") || service2.equals("Ground Domestic") || service2.equals("SmartPost")) {
				ground2=true;
			}
			else {
				express2=true;
				}
			}
		
			if (express2==true && ground2==true) {
				combo2=true;
				ground2=false;
				express2=false;
			}

			System.out.println("Hello 1");
			doWork(driver2,wait2,CEDropDown2,alert2,r2,cc21,cc22,comboBoxesHandling2,isChecked2,count2,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,combo2,ground2,express2);
			

	  	
	    }  
	  @Test(dataProvider="data-provider3")
	    public void testMethod3(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name) {
	//    @Test
	//    public void testMethod3() {
 try {
			  
			  driver3.quit();
		  }
		  catch(Exception eee) {
			  System.out.println(eee);
			  
		  }
		  
  	    System.setProperty(chromeSetProperty,chromePath);
  	    driver3 = new ChromeDriver();
  	    System.out.println(trkng1);
  	    
  	    
  	  if (service1.equals("Ground International") || service1.equals("Ground Domestic") || service1.equals("SmartPost")) {
			ground3=true;
			}
			else {
				express3=true;
				}

		if (!service2.equals("null")) {
			if (service2.equals("Ground International") || service2.equals("Ground Domestic") || service2.equals("SmartPost")) {
				ground3=true;
			}
			else {
				express3=true;
				}
			}
		
			if (express3==true && ground3==true) {
				combo3=true;
				ground3=false;
				express3=false;
			}

			System.out.println("Hello 1");
			doWork(driver3,wait3,CEDropDown3,alert3,r3,cc31,cc32,comboBoxesHandling3,isChecked3,count3,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,combo3,ground3,express3);
			

	    }  
	  
	  
	  
	  
	  
	  @Test(dataProvider="data-provider4")
	    public void testMethod4(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name) {
	//    @Test
	//    public void testMethod4() {
		  try {
			  
			  driver4.quit();
		  }
		  catch(Exception eee) {
			  System.out.println(eee);
			  
		  }
		  
  	    System.setProperty(chromeSetProperty,chromePath);
  	    driver4 = new ChromeDriver();
  	    System.out.println(trkng1);
  	    
  	    
  	  if (service1.equals("Ground International") || service1.equals("Ground Domestic") || service1.equals("SmartPost")) {
			ground4=true;
			}
			else {
				express4=true;
				}

		if (!service2.equals("null")) {
			if (service2.equals("Ground International") || service2.equals("Ground Domestic") || service2.equals("SmartPost")) {
				ground4=true;
			}
			else {
				express4=true;
				}
			}
		
			if (express4==true && ground4==true) {
				combo4=true;
				ground4=false;
				express4=false;
			}

			System.out.println("Hello 1");
			doWork(driver4,wait4,CEDropDown4,alert4,r4,cc41,cc42,comboBoxesHandling4,isChecked4,count4,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,combo4,ground4,express4);
			
	    }
	  
	  public void doWork(WebDriver driver, WebDriverWait wait,Select CEDropDown,Alert alert,Robot r,ArrayList<String> cc1,ArrayList<String> cc2, List<WebElement> comboBoxesHandling, Boolean isChecked,int count,String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name, Boolean combo,Boolean express,Boolean ground) {
		  //TEST ONLY
		  level=true;
		  System.out.println("Hello 2");
		  
			 //	driver.get(levelUrl);
		 	driver.get("https://testsso.secure.fedex.com/L3/PRSApps/");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait = new WebDriverWait( driver,10);
			driver.manage().window().maximize();     
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			driver.findElement(By.id("username")).sendKeys("477023");
			driver.findElement(By.id("password")).sendKeys("477023");
			driver.findElement(By.id("submit")).click();

		  
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
				driver.get("testsso.secure.fedex.com/L3/PRSApps/");
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				wait = new WebDriverWait( driver,10);
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

						System.out.println("MADE IT HERE");
						}
					
						
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
					//	a = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[3]/font")).get(count-1).getText();
					//	sheet=wb.getSheetAt(0);
					//	sheet.getRow(i).createCell(13).setCellValue(a);
					//	fout = new FileOutputStream(new File(filepath));
					//	wb.write(fout);
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
						Alert alertPopup =wait.until(ExpectedConditions.alertIsPresent());	
						alertPopup.accept();
					//	sheet.getRow(i).createCell(14).setCellValue("Processed");
					//	fout = new FileOutputStream(new File(filepath));
					//	wb.write(fout);	
					}
				
					catch (NoSuchElementException e)
					{
						System.out.println(e);
						if(url1.contentEquals("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp")||url2.contentEquals("https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp"))
						{
							
							System.out.println("NoSuchElementException "+e);
							/*
							sheet.getRow(i).createCell(14).setCellValue("Invalid Account Number");
							fout = new FileOutputStream(new File(filepath));
							wb.write(fout);
				*/
						}
						else
						{
							
							System.out.println("NoSuchElementException "+e);
							/*
							sheet.getRow(i).createCell(14).setCellValue("Try This Manually");
							fout = new FileOutputStream(new File(filepath));
							wb.write(fout);
							*/
					
						}
					}
					catch (WebDriverException h)
					{
					
						System.out.println("WebDriverException "+h);
						/*
						sheet.getRow(i).createCell(14).setCellValue("Try This Manually");
						fout = new FileOutputStream(new File(filepath));
						wb.write(fout);
					*/

					}
					
					
					catch (AWTException s)
					{
						
						System.out.println("AWTException "+s);
						/*
						sheet.getRow(i).createCell(14).setCellValue("Try This Manually");
						fout = new FileOutputStream(new File(filepath));
						wb.write(fout);
					*/

					}
				

					catch(NullPointerException k)
					{
						System.out.println("STEPHEN "+k);
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("InterruptedException "+e);
						//e.printStackTrace();
					}
	  }
	   
}















 


