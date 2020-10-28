package rebill;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configuration.config;
import configuration.dataSetup;
import configuration.excel;

public class dataSetupChange {

	
	
    //False = Running from xml only
	//True = Running from GUI only
	
	String tempFile,configFile;
	excel excelVar;
	boolean fl;
	Boolean isPresent=false;
	int retryCounter=0;
	int i=0;
	Object o;
	WebDriverWait wait1,wait2,wait3,wait4;
	
	config c;
	int count1,count2,count3,count4 ;
	String  sh1;
	String filePath;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static FileInputStream fin;
	static FileOutputStream fout;
	public int  rowcount;

	WebDriver driver1,driver2,driver3,driver4;
	static int sheetcount;
	static int j ;
	boolean flag2 ;

	Boolean isChecked1=false,isChecked2=false,isChecked3=false,isChecked4=false;
    String compatibleMode;
    Boolean chrome;
    String  homePath=System.getProperty("user.dir");
    String browser;
    String chromeSetProperty="webdriver.chrome.driver";
	String ieSetProperty="webdriver.ie.driver";
	
	int k;
	boolean sel;
	
	String chromePath=homePath+"\\drivers\\chromedriver.exe";
	String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
	String levelUrl;
	int rowCount;
	int colCount;
	int total;
	int testCounter1,testCounter2,testCounter3,testCounter4;
	int totalRows1,totalRows2,totalRows3,totalRows4;
	int count;
	String type;
	String comment;
	String podScan;
	String tinCount;
	String testInputNbr;
	String amount;
	String currcode;
	String approverID;
	String cc1;
	String cm1;
	String cc2 ;
	String cm2 ;
	String cc3;
	String cm3 ;
	String cc4 ;
	String cm4;
	int totalMod;
	String level;
	
	String allCheckBox;
	String nullCheckBox;
	String failedCheckBox;
	String domesticCheckBox;
	String internationalCheckBox;
	String expressCheckBox;
	String groundCheckBox;
	String normalCheckBox;
	String mfRetireCheckBox;
	String source;
	String sessionCount;
    String customString;
    String customCheckBox;
    String databaseDisabled;
	
	int sessionCountInt;
	
	ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
	String[][] allData;
	
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","domesticCheckBox","internationalCheckBox","expressCheckBox","groundCheckBox","sessionCount","customString","customCheckBox","databaseDisabled"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String domesticCheckBox,String internationalCheckBox,String expressCheckBox,String groundCheckBox,String sessionCount,String customString,String customCheckBox,String databaseDisabled) {
	c=new config();
	/*
	@BeforeClass
	public void setupExcel() {
*/
		
		//Kill all running chromedrivers leftover from previous sessions
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		homePath=System.getProperty("user.dir");
    	
	
        	
        		
	        	this.browser=browser;
	        	this.level=level;
	        	this.compatibleMode=compatibleMode;
	        	this.source=source;

	        	this.databaseDisabled=databaseDisabled;
        
	        	c.setFilePath(filepath);
	        	c.setSessionCount(sessionCount);
	        	c.setFilePath(filepath);
	        	c.setAllCheckBox(allCheckBox);
	        	c.setNullCheckBox(nullCheckBox);
	        	c.setFailedCheckBox(failedCheckBox);
	        	c.setDomesticCheckBox(domesticCheckBox);
	        	c.setInternationalCheckBox(internationalCheckBox);
	        	c.setExpressCheckBox(expressCheckBox);
	        	c.setGroundCheckBox(groundCheckBox);
	        	c.setCustomCheckBox(customCheckBox);
	        	c.setCustomString(customString);
	        	
       
    	if(source.equals("excel")) {
	    
    		c.setSource("excel");
	    	
    	}
    	
    	else if(source.equals("db")) {
    		c.setSource("db");
    		
    	}
    	
    	dataSetup ds = new dataSetup(c);

    	
        if (level.equals("2"))
    	{
    		levelUrl="https://testsso.secure.fedex.com/L2/eRA/index.html";
    		
    	}
    	else if (level.equals("3"))
    	{
    		levelUrl="https://testsso.secure.fedex.com/L3/eRA/index.html";
    	
    	}
       
    	
	}
	
	

    @Test(dataProvider="data-provider1",retryAnalyzer = Retry.class, dataProviderClass = dataSetup.class)
    public void testMethod1(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,String originLoc,String destLoc,String dimVol,String shipperRef,String recpAddress,String shipperAddress,String acctNbrDelStatus,String svcBase, String creditCardDtl,String preRateScenarios,String expPieces,String expActualWeight,String expAdjWeight,String creditCardDt,int rowNumber) {
     
    	System.out.println("Instance: 1");
    	
    	System.out.println(result);
    	System.out.println(descripiton);
    	System.out.println(testInputNbr);
    	System.out.println(tinCount);
    	System.out.println(trk);
    	System.out.println(reasonCode);
    	System.out.println(rebillAccount);
    	System.out.println(invoiceNbr1);
    	System.out.println(invoiceNbr2);
    	System.out.println(mig);
    	System.out.println(region);
    	System.out.println(login);
    	System.out.println(password);
    	System.out.println(rsType);
    	System.out.println(company);
    	System.out.println(worktype);
    	System.out.println(originLoc);
    	System.out.println(destLoc);
    	System.out.println(dimVol);
    	System.out.println(shipperRef);
    	System.out.println(recpAddress);
    	System.out.println(shipperAddress);
    	System.out.println(acctNbrDelStatus);
    	System.out.println(svcBase);
    	System.out.println(creditCardDtl);
    	System.out.println(preRateScenarios);
    	System.out.println(expPieces);
    	System.out.println(expActualWeight);
    	System.out.println(expAdjWeight);
    	System.out.println(creditCardDt);
    	System.out.println(rowNumber);
    	
    	//Will Check if Trk is already successful;
  	  try {
    	if (databaseDisabled.equals("false")) {
    
  		  String[] resultArray = validateResults(trk);
  	  if ( resultArray[0].equals("pass")){
       	 if(source.equals("excel")) {
       	 writeToExcel(rowNumber, 0,"pass");
       	 writeToExcel(rowNumber, 1,"completed");
       	
       	 }
       	 writeToDB(testInputNbr,tinCount,trk,resultArray);
       	 
       	 	writeToDB(testInputNbr,tinCount,trk,resultArray);
       	 return;
  	  
  	  }
  	  }
  	  }
    	//}
  	  catch(Exception e) {
  		System.out.println(e);  
  	  }
  	  
        
    	
    	try { 
    		driver1.quit();
    		driver1.close();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
    	if (browser.equals("1")) {
    		if (c.getCompatibleMode().equals("true")) {	
    			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    		    capabilities.setCapability("ignoreZoomSetting", true);
    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
    		}
    		System.setProperty(ieSetProperty, ieDriverPath);
    		driver1 =  new InternetExplorerDriver();
    	}
    	
    	
    	else if (browser.equals("2")) {
    	 
    		System.setProperty(chromeSetProperty,chromePath);
    		driver1 = new ChromeDriver();
    	}
    	
    	
    	else if (browser.equals("3")) {
    
        	FirefoxProfile profile = new FirefoxProfile(); 
        	profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
        	profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");
        	profile.setAcceptUntrustedCertificates(true); profile.setAssumeUntrustedCertificateIssuer(true);
        	DesiredCapabilities capabilities = new DesiredCapabilities(); 
        	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        	System.setProperty("webdriver.gecko.driver",  homePath+"\\drivers\\geckodriver.exe");
        	driver1 = new FirefoxDriver(capabilities);
    	}
    	
    	
    	
    	
    	wait1 = new WebDriverWait(driver1,20);
	    login(driver1,wait1,login,password);
	  
	    try {
	    	
	    
	        
			doRebill(driver1,wait1, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber, originLoc, destLoc, dimVol, shipperRef, recpAddress, shipperAddress, acctNbrDelStatus, svcBase,  creditCardDtl, preRateScenarios, expPieces, expActualWeight, expAdjWeight, creditCardDt,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
   
    @Test(dataProvider="data-provider2",retryAnalyzer = Retry.class)
    public void testMethod2( String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,String originLoc,String destLoc,String dimVol,String shipperRef,String recpAddress,String shipperAddress,String acctNbrDelStatus,String svcBase, String creditCardDtl,String preRateScenarios,String expPieces,String expActualWeight,String expAdjWeight,String creditCardDt,int rowNumber) {
     
    	System.out.println("Instance: 2");
    	readTrk(trk);
    	
    	//Will Check if Trk is already successful;
  	 
  	 try {
     	if (databaseDisabled.equals("false")) {
     
   		  String[] resultArray = validateResults(trk);
   	  if ( resultArray[0].equals("pass")){
        	 if(source.equals("excel")) {
        	 writeToExcel(rowNumber, 0,"pass");
        	 writeToExcel(rowNumber, 1,"completed");
        	
        	 }
        	 writeToDB(testInputNbr,tinCount,trk,resultArray);
        	 
        	 	writeToDB(testInputNbr,tinCount,trk,resultArray);
        	 return;
   	  
   	  }
   	  }
   	  }
     	//}
   	  catch(Exception e) {
   		System.out.println(e);  
   	  }
  	  
    	try { 
    		driver2.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    	if (browser.equals("1")) {
    		if (c.getCompatibleMode().equals("true")) {	
    			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    		    capabilities.setCapability("ignoreZoomSetting", true);
    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
    		}
    		System.setProperty(ieSetProperty, ieDriverPath);
    		driver2 =  new InternetExplorerDriver();
    	}
    	else if (browser.equals("2")) {
    	 
    		System.setProperty(chromeSetProperty,chromePath);
    		driver2 = new ChromeDriver();
    	}
    	 wait2 = new WebDriverWait(driver2,20);
    	login(driver2,wait2,login,password);
	    try {
	    	doRebill(driver2,wait2, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber, originLoc, destLoc, dimVol, shipperRef, recpAddress, shipperAddress, acctNbrDelStatus, svcBase,  creditCardDtl, preRateScenarios, expPieces, expActualWeight, expAdjWeight, creditCardDt,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
    @Test(dataProvider="data-provider3",retryAnalyzer = Retry.class)
    public void testMethod3( String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,String originLoc,String destLoc,String dimVol,String shipperRef,String recpAddress,String shipperAddress,String acctNbrDelStatus,String svcBase, String creditCardDtl,String preRateScenarios,String expPieces,String expActualWeight,String expAdjWeight,String creditCardDt,int rowNumber) {
    	System.out.println("Instance: 3");
    	readTrk(trk);
    	
    	
    	//Will Check if Trk is already successful;
    	 try {
    	    	if (databaseDisabled.equals("false")) {
    	    
    	  		  String[] resultArray = validateResults(trk);
    	  	  if ( resultArray[0].equals("pass")){
    	       	 if(source.equals("excel")) {
    	       	 writeToExcel(rowNumber, 0,"pass");
    	       	 writeToExcel(rowNumber, 1,"completed");
    	       	
    	       	 }
    	       	 writeToDB(testInputNbr,tinCount,trk,resultArray);
    	       	 
    	       	 	writeToDB(testInputNbr,tinCount,trk,resultArray);
    	       	 return;
    	  	  
    	  	  }
    	  	  }
    	  	  }
    	    	//}
    	  	  catch(Exception e) {
    	  		System.out.println(e);  
    	  	  }
    	try { 
    		driver3.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    	if (browser.equals("1")) {
    		if (c.getCompatibleMode().equals("true")) {	
    			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    		    capabilities.setCapability("ignoreZoomSetting", true);
    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
    		}
    		System.setProperty(ieSetProperty, ieDriverPath);
    		driver3 =  new InternetExplorerDriver();
    	}
    	else if (browser.equals("2")) {
    	 
    		System.setProperty(chromeSetProperty,chromePath);
    		driver3 = new ChromeDriver();
    	}
    	
    	 wait3 = new WebDriverWait(driver3,20);
    login(driver3,wait3,login,password);
    try {
    	doRebill(driver3,wait3, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber, originLoc, destLoc, dimVol, shipperRef, recpAddress, shipperAddress, acctNbrDelStatus, svcBase,  creditCardDtl, preRateScenarios, expPieces, expActualWeight, expAdjWeight, creditCardDt,3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider4",retryAnalyzer = Retry.class)
    public void testMethod4(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,String originLoc,String destLoc,String dimVol,String shipperRef,String recpAddress,String shipperAddress,String acctNbrDelStatus,String svcBase, String creditCardDtl,String preRateScenarios,String expPieces,String expActualWeight,String expAdjWeight,String creditCardDt,int rowNumber) {
    	System.out.println("Instance: 4");
    	//Will Check if Trk is already successful;
    	readTrk(trk);
    	
    	 try {
    	    	if (databaseDisabled.equals("false")) {
    	    
    	  		  String[] resultArray = validateResults(trk);
    	  	  if ( resultArray[0].equals("pass")){
    	       	 if(source.equals("excel")) {
    	       	 writeToExcel(rowNumber, 0,"pass");
    	       	 writeToExcel(rowNumber, 1,"completed");
    	       	
    	       	 }
    	       	 writeToDB(testInputNbr,tinCount,trk,resultArray);
    	       	 
    	       	 	writeToDB(testInputNbr,tinCount,trk,resultArray);
    	       	 return;
    	  	  
    	  	  }
    	  	  }
    	  	  }
    	    	//}
    	  	  catch(Exception e) {
    	  		System.out.println(e);  
    	  	  }
    	try { 
    		driver4.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    	if (browser.equals("1")) {
    		if (c.getCompatibleMode().equals("true")) {	
    			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    		    capabilities.setCapability("ignoreZoomSetting", true);
    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
    		}
    		System.setProperty(ieSetProperty, ieDriverPath);
    		driver4 =  new InternetExplorerDriver();
    	}
    	else if (browser.equals("2")) {
    	 
    		System.setProperty(chromeSetProperty,chromePath);
    		driver4 = new ChromeDriver();
    	}
    	
    	 wait4 = new WebDriverWait(driver4,20);
    login(driver4,wait4,login,password);
    try {
    	doRebill(driver4,wait4, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber, originLoc, destLoc, dimVol, shipperRef, recpAddress, shipperAddress, acctNbrDelStatus, svcBase,  creditCardDtl, preRateScenarios, expPieces, expActualWeight, expAdjWeight, creditCardDt,4);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    }
    
    
    
    public void login(WebDriver driver,WebDriverWait wait,String login,String password) {
    	
    	try {
		    driver.get(levelUrl);
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,10);
			driver.manage().window().maximize();
			driver.findElement(By.id("username")).sendKeys(login);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("submit")).click();
    	}
    	catch(Exception e) {
    		
    		 Assert.fail("Could Not Login");
    	}
    }
    
    
    public void doRebill(WebDriver driver,WebDriverWait wait, String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,int rowNumber,String originLoc,String destLoc,String dimVol,String shipperRef,String recpAddress,String shipperAddress,String acctNbrDelStatus,String svcBase, String creditCardDtl,String preRateScenarios,String expPieces,String expActualWeight,String expAdjWeight,String creditCardDt, int instanceNumber) throws InterruptedException {
    
    	JavascriptExecutor js= (JavascriptExecutor) driver;
    	By tempElement;
    	int packageCounter=0;
    	Boolean exist;
    	WebElement scrollElement;
    	Boolean packageTab=false;
    	wait=new WebDriverWait(driver,20);
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	if(!preRateScenarios.equals("")) {
    		 if(source.equals("excel")) {
	               	 writeToExcel(rowNumber, 0,"fail");
	               	 writeToExcel(rowNumber, 1,"Prerate Code Not Added Yet");
	               	 }
	   				 if(databaseDisabled.equals("false")) {
     	   			 String[] resultArray = new String[2];
     	   			 	resultArray[0]="fail";
     	   				resultArray[1]="Prerate Code Not Added Yet";
     	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                    	 }
    	return;	
    	}
    	
    	
    	
    	try {
    	//In order for clear button to be clickable need to scroll up
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        
        //Will hit the clear button. This is for whenever we switch to new tracking number    
        driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[2]/button/a")).click();

        
        //In login we verified this appears and sending our tracking number to it.
        driver.findElement(By.xpath("//*[@id=\"trackingID\"] ")).sendKeys(trk);

        //Click on Search
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button"))));
        driver.findElement(By.xpath("//*[@id=\"inquiry-form\"]/div[1]/div/div[2]/form/div[3]/div[1]/button")).click(); 
    	} 
    	catch(Exception e) {
    		System.out.println("Failed on Entering Tracking Number");
    		 Assert.fail("Failed on Entering Tracking Number");
    		
    	}
    	
    	//Try to Click Package Tab
    	try {  
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div")));
    	//	/html/body/div[2]/div/div/div/div/div[1]/div[1]/div/div[2]/form/div[1]/div[1]/div/div[2]/div/input
    		//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a")));
        	//element.click();
    		WebElement element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
    		js.executeScript("arguments[0].click()", element);
    		packageTab=true;
        	//driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a")).click();
    	}
    	catch(Exception e) {
    		System.out.println("Could Not Find PopUp..");
    		
    	}
    	
    	//Trying to find popup
    	if (packageTab==false) {
    	try {  
    		driver.findElement(By.xpath(" /html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys(invoiceNbr1);
    		Thread.sleep(1000);
    		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
    		driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
    		System.out.println("Found Pop Up");
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div")));
        		WebElement element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
        		js.executeScript("arguments[0].click()", element);
        	

    }	catch(Exception e) {
        Assert.fail("Could Not Find Popup Or COntinue to Package Screen");
    	}
	}
    	
    	
    	
    	//Getting all the charge codes..
    	try{
    		packageCounter=0;
                while(true){
                	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
                	exist= driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+packageCounter+"\"]")).isDisplayed();
                	packageCounter++;
                    System.out.println("packageCounter "+packageCounter);
                    
                }
            }
            catch(NoSuchElementException e){
                System.out.println("No Such Element... Got all the counters for charge codes");
        }
    	
    	/*
         catch(Exception e){
        	 System.out.println("Failed at Getting Charge Code Count");
        }
    	 */
    	
    	
    	//Click on all Charge Codes
    	 try {
    		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
             js.executeScript("window.scrollTo(0,500)");
             scrollElement =  driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]"));
             js.executeScript("arguments[0].scrollIntoView();", scrollElement);
             driver.findElement(By.xpath("//*[@id=\"packageLevelServicegridCheckBox"+(packageCounter-1)+"\"]")).click();

            
            
            System.out.println("Clicked All Stat Codes");
        }
        catch(Exception e){
        	 System.out.println("Could Not Clicked All Stat Codes");
            System.out.println(e);
            Assert.fail("Could Not Clicked All Stat Codes");

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
    	 System.out.println("Inside getDetails");
         //Getting Action Dropdown. Will RB everytime.
         Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
         actionDropDown.selectByValue("RB");
     
	         //Setting up the reasonCode Dropdown.
	         Select  reasonCodeDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
	       
	         /*******************************************************/
	         List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select"));
	    	 int counter=0;
	         for (WebElement option : options) {
	        	 
	    		 System.out.println(counter +":" +option.getText());
	    		 counter++;
	             }
	       
	         
	         /***************************************************************************/
	         
	         //For domestic.
	         if (login.equals("5194105") || login.equals("584168")){
	             switch (reasonCode){
         
                 case "RRA" :
                	
                 	//reasonCodeDropDown.selectByValue("RRA - REBILL RECIP ACCT   ");
                 								
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[2]")).click();
                     break;
                 case "RSA" :
                
        	        
                 //	reasonCodeDropDown.selectByVisibleText("RSA - REBILL SHIPPER ACCT ");
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[3]")).click();
                     break;
                 case "RTA" :
                
                 //	reasonCodeDropDown.selectByValue("RTA - REBILL THIRD PARTY  ");
                     driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[4]")).click();
                	 break;
                 case "RBS" :  
                
                 //	reasonCodeDropDown.selectByValue("RBS - REBILL SAME ACCOUNT");
                 	break;
                 }
             }
	         else {
              switch (reasonCode){
               
              	case "RRA" :
              		//reasonCodeDropDown.selectByValue("RRA - REBILL RECIP ACCT");
                         		
              			driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[14]")).click();
                     break;
                 case "RSA" :
                 //	reasonCodeDropDown.selectByValue("RSA - REBILL SHIPPER ACCT");
                 	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[17]")).click();
                     break;
                 case "RTA" :
                 //	reasonCodeDropDown.selectByValue("RTA - S,R INCORRECT BILLING-REBILL TO 3RD PART");
                 	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[21]")).click();
                     break;
                 case "KPR" :
                 //	reasonCodeDropDown.selectByValue("KPR - MANIFEST KEYPUNCH ERROR-ACCT NO/BILL OPT");
                    //driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[21]")).click();
                     break;
                 case "RSD" :
                 //	reasonCodeDropDown.selectByValue("RRSD - SHIPPER DECLINES-BILL RECIPIENT 3RD PART");
                    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[18]")).click();
                     break;
                 case "RBS" :  
                // 	reasonCodeDropDown.selectByValue("RBS - REBILL SAME ACCOUNT");
                 	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[6]")).click();
                    								  
                 	break;
                 }
	         }
         }
      
      catch(Exception e) {
    	  
    	  System.out.println("Failed at Drop Down");
    	  if(source.equals("excel")) {
            	 writeToExcel(rowNumber, 0,"fail");
            	 writeToExcel(rowNumber, 1,"Could Not Find Rebill Dropdown");
            	return;
            	 }
				 if(databaseDisabled.equals("false")) {
	   			 String[] resultArray = new String[2];
	   			 	resultArray[0]="fail";
	   				resultArray[1]="Could Not Find Rebill Dropdown";
	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
	   				
             	 } 
    	  Assert.fail("Failed at Drop Down");
      }
   
         
        
         
         
         
    	 /*
    	 *****************************************************************************
    	 *
    	 *Getting to phone detail screen
    	 *
    	 *
    	 ****************************************************************************
    	 */
         
        
         	try {
         		//tHIS BUTTON IS BUGGY!!!! click many times!!!!!!!!
         		 Thread.sleep(5000);
         		// driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
         		// driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
         		// driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
         		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
         		 Thread.sleep(2000);
                 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
                 
         }
         	catch(Exception e1) {
	
         		System.out.println("Could Not Click Rebill After Action Code");
         		 try {
         			 Thread.sleep(1000);
         			 String tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
         			 if (tempError.equals("Trying To Rebill A Partial Amount")) {
         				 System.out.println(tempError);
         				 if(source.equals("excel")) {
         	               	 writeToExcel(rowNumber, 0,"fail");
         	               	 writeToExcel(rowNumber, 1,"Trying To Rebill A Partial Amount");
         	               	 }
         	   				 if(databaseDisabled.equals("false")) {
                 	   			 String[] resultArray = new String[2];
                 	   			 	resultArray[0]="fail";
                 	   				resultArray[1]="Trying To Rebill A Partial Amount";
                 	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                                	 }
         		}
         		 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
         		 System.out.println("Found Pop Up");
         		
         		 
         		 try {
         		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
         		 }
         		 catch(Exception e3) {
         			 
         			 tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
         			 if (tempError.indexOf("interline")!=-1) {
         				 System.out.println(tempError);
         				 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
         			 }
         		 }
         			 
         			 
         			 try {
                 		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
                 		 }
                 		 catch(Exception e4) {
                 			 
                 			 tempError= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
                 			 if (tempError.indexOf("specialist")!=-1) {
                 				 System.out.println(tempError);
                 				 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
                 }
                 		 }
         	}
         	catch(Exception e5) {
         		System.out.println("Could Not find Popup"+e5);
         		 Assert.fail(e5 +" Could Not find Popup");
         	}
        } 
         		 
         		 
         	
         		 
         		 /*
             	 *****************************************************************************
             	 *
             	 *Getting to phone detail
             	 *
             	 *
             	 ****************************************************************************
             	 */
         		 
         		 
      //   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
             try{
            	 //Click on rebill RPI Complete, Phone, and Continue
                  if (login.equals("5194105")|| login.equals("584168")){
                	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
                  }
              
                  
               Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
               contactMethodDropDown.selectByValue("phone");  
         	   driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
         	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
             	}
         	   		catch(Exception e) {
         	   			try {
         	   				System.out.println("Could not select phone or click RPI..");
         	   				String tempError= driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/h4")).getText();
         	   				if (tempError.indexOf("Management approval")!=-1) {
         	   					System.out.println(tempError);
         	   				 if(source.equals("excel")) {
         	               	 writeToExcel(rowNumber, 0,"fail");
         	               	 writeToExcel(rowNumber, 1,"Management approval");
         	               	 }
         	   				 if(databaseDisabled.equals("false")) {
                 	   			 String[] resultArray = new String[2];
                 	   			 	resultArray[0]="fail";
                 	   				resultArray[1]="Management approval";
                 	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                                	 }
         	   			 Assert.fail("Management approval");
         	   					}
         	   				driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
         	   				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
         	   				}
         	   				catch(Exception ee) {
         	   				 System.out.println(ee+"Could Not Get to Rebill Screen");
         	   			 if(databaseDisabled.equals("false")) {
         	   			 String[] resultArray = new String[2];
         	   			 	resultArray[0]="fail";
         	   				resultArray[1]="Could Not Get to Rebill Screen";
         	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                        	 }
         	   		 if(source.equals("excel")) {
     	               	 writeToExcel(rowNumber, 0,"fail");
     	               	 writeToExcel(rowNumber, 1,"Could Not Get to Rebill Screen");
     	               	 }
         	   				 Assert.fail(ee+" Could Not Get to Rebill Screen");
         	   				}
         	   			}
         	   		
             
             
             
             
             
             			/*
                     	 *****************************************************************************
                     	 *
                     	 *Rebill Screen
                     	 *
                     	 *
                     	 ****************************************************************************
                     	 */	
         	   			
         	   			
         	   			
            
              
              
         
        	
             Boolean validated;
             try{    
            	
             switch (reasonCode){
             		case "RRA" :
                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reciptacct_number\"]")));
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).clear();
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(rebillAccount);
                       break;
                    case "RSA" :
                       //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[7]/di[1]/label")));
                       //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[7]/div[1]/label")));
                       //*[@id="shipacct_number"] 
                       //TESTING PURPOSE ONLY! DELETE WHEN RUNNING.
                       //driver.findElement(By.xpath("//*[@id=\"shipacct_number\"]")).clear();
                       //driver.findElement(By.xpath("//*[@id=\"shipacct_number\"]")).sendKeys("39466825");
                       //Thread.sleep(2000);
                        break;
                    case "RTA" :
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"thirdacct_number\"]")));
                        driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).clear();
                        driver.findElement(By.xpath("//*[@id=\"thirdacct_number\"]")).sendKeys(rebillAccount);
                        break;
                    }

             
             System.out.println("MF TEST");
             if(worktype.equals("MFRETIRE")) {
           /*
            	 String originLoc,
            	 String destLoc,
            	 String dimVol,
            	 String shipperRef,
            	 String recpAddress,
            	 String shipperAddress,
            	 String acctNbrDelStatus,
            	 String svcBase, 
            	 String creditCardDtl,
            	 String preRateScenarios,
            	 String expPieces,
            	 String expActualWeight,
            	 String expAdjWeight,
            	 String creditCardDt,
            	 */
            	 
            	
            	 if (!preRateScenarios.equals("")) {
         		 	//driver.findElement(By.xpath("//*[@id=\"origin\"]")).sendKeys(originLoc);
            		  System.out.println("Prerate TEst");
            		 return;
         	 }
            	 
            	 if (!originLoc.equals("")) {
            		 	driver.findElement(By.xpath("//*[@id=\"origin\"]")).sendKeys(originLoc);
            		 
            	 }
            	 if (!destLoc.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"destination\"]")).sendKeys(destLoc);
            	 }
            	 if (!dimVol.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"rb_volume\"]")).sendKeys(dimVol);
            	 }
            	 if (!shipperRef.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"reference_notes\"]")).sendKeys(shipperRef);
            	 }
            	 if (!recpAddress.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(recpAddress);
            	 }
            	 if (!shipperAddress.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"shipacct_number\"]")).sendKeys(shipperAddress);
            	 }
            	 if (!acctNbrDelStatus.equals("")) {
            		// driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).sendKeys(arg0);
            	 }
            	 if (!svcBase.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"service_base\"]")).sendKeys(svcBase);
            	 }
            	 if (!creditCardDtl.equals("")) {
            		 String[] splitCC = creditCardDtl.split(" ");
            		 driver.findElement(By.xpath("//*[@id=\"cc_num1\"]")).sendKeys(splitCC[0]);
            		 driver.findElement(By.xpath("//*[@id=\"cc_num2\"]")).sendKeys(splitCC[1]);
            		 driver.findElement(By.xpath("//*[@id=\"cc_num3\"]")).sendKeys(splitCC[2]);
            		 driver.findElement(By.xpath("//*[@id=\"cc_num4\"]")).sendKeys(splitCC[3]);
            	 }
            	 if (!expPieces.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"rb_totalEXPPIECES\"]")).sendKeys(expPieces);
            	 }
            	 if (!expActualWeight.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"rb_totalEXPweight\"]")).sendKeys(expActualWeight);
            	 }
            	 if (!expAdjWeight.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"rb_totalEXPADJWeight\"]")).sendKeys(expAdjWeight);
            	 }
            	 if (!creditCardDt.equals("")) {
            		 driver.findElement(By.xpath("//*[@id=\"exp_date\"]")).sendKeys(creditCardDt);
            	 }
             }
             Thread.sleep(2000);
             	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();
             	Thread.sleep(15000);
             	}
             	catch(Exception e) {
             		System.out.println("Failed Trying to Rebill..");
             		 Assert.fail("Failed Trying to Rebill..");
             	}

            
             try {
            	 
            //Check For Validation
             }
             catch (Exception e) {
            	 System.out.println("Failed Validating in DB");
            	 Assert.fail("Failed Validating in DB");
             }
            
             //If False.. think maybe there is stat codes to select.
            
             String[] resultArray = validateResults(trk);
             
             
             
             if ( resultArray[0].equals("pass")){
            	 if(source.equals("excel")) {
            	 writeToExcel(rowNumber, 0,"pass");
            	 writeToExcel(rowNumber, 1,"completed");
            	 }
            	 if(databaseDisabled.equals("false")) {
                 	  writeToDB(testInputNbr,tinCount,trk,resultArray);
                 	 }
            	 return;
            	 
             }
             if (resultArray[0].equals("fail")) {
            	 try {
            		 //Sometimes just needs to click continue to rebill
            		String tempString =  driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
            		if (tempString.indexOf("Click Rebill to continue")!=-1) {
             		   	driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[1]")).click();
             		   	driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")).click();    
             		}
            	 }
            	 catch(Exception e) {
            		System.out.println("Did not find popup about continuing");
            		 
            	 }
            
            //If Rebill Is Not Successful
            
            	 try {
            		 Boolean overrideBoolean;
            		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span")));
            		 List<WebElement> errorList;
            		 errorList=driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/label/span"));
            		 int popupCounter=1;
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
            	System.out.println("Did not reach override errors or failed here");
            	 Assert.fail("Faled At Last Rebill Screen: Did not reach override errors or failed here");
            }
            	 
            	 //Check For Validation again and save result.
            	  resultArray = validateResults(trk);
            	
            	  try {
            	  if (resultArray[0].equals("pass")){
            			 if(source.equals("excel")) {
                 	 writeToExcel(rowNumber, 0,"pass");
                 	 writeToExcel(rowNumber, 1,"completed");
            			 }
                	 if(databaseDisabled.equals("false")) {
                     	  writeToDB(testInputNbr,tinCount,trk,resultArray);
                     	 }
                  }
                  if (resultArray[0].equals("fail")) {
                		 if(source.equals("excel")) {
                	  writeToExcel(rowNumber, 0,"fail");
                  	  writeToExcel(rowNumber, 1,resultArray[1]);
                		 }
                  	 if(databaseDisabled.equals("false")) {
                  	  writeToDB(testInputNbr,tinCount,trk,resultArray);
                  	 }
                  	//  Assert.fail("Faled At Last Rebill Screen: "+resultArray[1]);
                	  
                  }
            	  }
            	  catch(Exception e) {
            		  System.out.println("FAILED AT END?");
            	  }
          }
             
             
        }
    
    
    
    public synchronized void writeToDB(String testInputNbr,String tinCount,String trk,String[] resultArray) {
    	Connection GTMcon=null;
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			GTMcon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	PreparedStatement stmt = null;
    	

    	try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
    	stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");  
		stmt.setString(1,testInputNbr);  
		stmt.setString(2,tinCount);  
		stmt.setString(3,trk);  
		stmt.setString(4,resultArray[0]);  
		stmt.setString(5,resultArray[1]);  
	
		stmt.executeUpdate();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
		
    	
    	
    	try {
		//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
		stmt=GTMcon.prepareStatement("update rebill_results set result=?,description=? where trkngnbr=?");  
		
		stmt.setString(1,resultArray[0]);  
		stmt.setString(2,resultArray[1]); 
		stmt.setString(3,trk); 
		stmt.executeUpdate();
		
	}
	catch(Exception e) {
		System.out.println(e);
	}

    	
    }
    
    public String[] validateResults(String trk) {
    	
    	Boolean result=null;
    	Connection con = null;
    	String[] resultArray = new String[2];
    	
    	try {
    	
    		if (level.equals("2")){
    			 c.setEraL2DbConnection();
       		     con=c.getEraL2DbConnection();
       	 }
       	 else if (level.equals("3")){
       		 	c.setEraL3DbConnection();
       		 	con=c.getEraL3DbConnection();
       	 	}
    	
    	}
    	catch(Exception e) {
    		
    		System.out.println("Could Not Get ERA DB Connections");
    	}
    	

    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		stmt=con.prepareStatement("select * from invadj_schema.rdt_rebill_request where airbill_nbr=?");  
			stmt.setString(1,trk);  
			rs = stmt.executeQuery();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	      
    	   
    		try {
    			rs = stmt.executeQuery();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	       try {
    			if (rs.next()==false){
    			      System.out.println("Is NULL");
    			      resultArray[0]="fail";
    			      resultArray[1]="Not In ERA Database";
    			}
    			   else{
    				    String statusDesc = rs.getString("STATUS_DESC");
    	                String errorDesc = rs.getString("ERROR_DESC"); 	    	                
    	                System.out.println(statusDesc +"    "+errorDesc);
    	              
    	              if (statusDesc.equals("SUCCESS")) {
  	    			      resultArray[0]="pass";
  	    			      resultArray[1]="completed";
    	              }
    	              else {
  	    			      resultArray[0]="fail";
  	    			      resultArray[1]=errorDesc;
    	              }
    			   }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	 return resultArray;      
}    
    
public synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
		
		excelVar.setCellData(rowCountExcel, colCountExcel, outputString);
		excelVar.writeCellData();
	}

public void readTrk(String trk) {
	System.out.println(trk);
}
    }
    

