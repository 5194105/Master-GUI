package prerate;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configuration.config;
import configuration.excel;
import configuration.importData;


public class prerateHoldTestNGSlow {
  
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
	
	
	int count1,count2,count3,count4 ;
	String  sh1;
	String filepath;
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
	//String firefoxSetProperty="";
	
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
	String[][] allData;
	int sessionCountInt;
    String customString;
    String customCheckBox;
    String databaseDisabled;
    config c;
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","domesticCheckBox","internationalCheckBox","expressCheckBox","groundCheckBox","sessionCount","customString","customCheckBox","databaseDisabled"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String domesticCheckBox,String internationalCheckBox,String expressCheckBox,String groundCheckBox,String sessionCount,String customString,String customCheckBox,String databaseDisabled) {
		
		
		importData id = new importData();
		c=id.getConfig();
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		homePath=System.getProperty("user.dir");
    	
	
        	if (source.equals("excel")) {
        		this.filepath=filepath;	
        		excelVar = new excel(filepath);
        	}
        	
	        	this.browser=browser;
	        	this.level=level;
	        	this.compatibleMode=compatibleMode;
	        	this.source=source;
	        	this.allCheckBox=allCheckBox;
				this.nullCheckBox=nullCheckBox;
				this.failedCheckBox=failedCheckBox;
				this.domesticCheckBox=domesticCheckBox;
				this.internationalCheckBox=internationalCheckBox;
				this.expressCheckBox=expressCheckBox;
				this.groundCheckBox=groundCheckBox;
	        	this.sessionCount=sessionCount;
	        	sessionCountInt=Integer.parseInt(sessionCount);
	        	this.customString=customString;
	        	this.customCheckBox=customCheckBox;
	        	this.databaseDisabled=databaseDisabled;

try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

    	
    }
        
        
        
    	homePath=System.getProperty("user.dir");
    	System.out.println("homePath" +System.getProperty("user.dir"));
    	
    	if (source.equals("excel")) {
    	excelVar.setUpExcelWorkbook();
    	excelVar.setUpExcelSheet(0);
    	excelVar.setRowCountAutomatically(0);
    	excelVar.setColCountAutomatically(0);
    	rowCount=excelVar.getRowCount();
    	colCount=excelVar.getColCount()+1;
    	}
    	else if (source.equals("db")) {
    		runDbQuery();
    	}
    	total= rowCount/sessionCountInt;
    	totalMod=rowCount%sessionCountInt;
    	totalRows1=total;
    	totalRows2=total;
    	totalRows3=total;
    	totalRows4=total;
    	
    	switch(totalMod) {
	    	case 1:
	    		totalRows1++;
	    		break;
	    	case 2 :
	    		totalRows1++;
	    		totalRows2++;
	    		break;
	    	case 3:
	    		totalRows1++;
	    		totalRows2++;
	    		totalRows3++;
	    		break;
    	
    	}	
    	
    	
    	
        if (level.equals("2"))
    	{
    		levelUrl=c.getPrerateL2Url();
    	}
    	else if (level.equals("3"))
    	{
    		levelUrl=c.getPrerateL3Url();
    		
    	}
            
    	
	}
	
	
	
	
	public void runDbQuery() {
		Connection GTMcon=null;
		Statement stmt = null;
		ResultSet rs = null;
		 ResultSetMetaData rsmd=null;

	
    	try {
    	
    			
    			GTMcon=c.getGtmRevToolsConnection();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
  	 String databaseSqlCount="select count(*) as total from prerate_hold_view ";
  	 String databaseSqlQuery="select RESULT, DESCRIPTION,POD_SCAN,TEST_INPUT_NBR,TIN_COUNT,TRKNGNBR,TIN_COMMENT from prerate_hold_view ";
    
    	if (allCheckBox.equals("false")) {
    		databaseSqlCount+="where trkngnbr is not null and ";
    		databaseSqlQuery+="where trkngnbr is not null and ";
    	}
    
    	 if (allCheckBox.equals("true")) {
     		databaseSqlCount+="where trkngnbr is not null  ";
     		databaseSqlQuery+="where trkngnbr is not null   ";
     	}
    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
    		databaseSqlCount+="(result is null or result ='fail') ";
    		databaseSqlQuery+="(result is null or result ='fail') ";
    	}
    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
    		databaseSqlCount+="result is null ";
    		databaseSqlQuery+="result is null ";
    	}
    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
    		databaseSqlCount+="result ='fail' ";
    		databaseSqlQuery+="result ='fail' ";
    
    	}
       	
    	
    	
    	
    	try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
    		
    		stmt = GTMcon.createStatement();
    		System.out.println(databaseSqlCount);
        	rs = stmt.executeQuery(databaseSqlCount);
        	rs.next();
        	rowCount=rs.getInt("total");
        	stmt.close();
        	rs.close();
        	
        	stmt = GTMcon.createStatement();
    		System.out.println(databaseSqlQuery);
        	rs = stmt.executeQuery(databaseSqlQuery);
        	rsmd = rs.getMetaData();
        	colCount = rsmd.getColumnCount()+1;
        	int rowCountTemp=0;
        	allData = new String[rowCount][colCount+1];
        	 
        	 while(rs.next()) {
        	//	 rowCount++;
        		// rebillDataArray.add(new rebillData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
        		 for (int i=1;i<colCount;i++) {
        			System.out.println(rs.getString(i));
        			if (rs.getString(i)==null) {
        				allData[rowCountTemp][i-1]="";
        			}
        			else {
        			 allData[rowCountTemp][i-1]=rs.getString(i);
        			}
        		 }
        		 rowCountTemp++; 
        	 }
        	 //colCount=17;
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}

    	
	}
	
	
	
	
	
    @DataProvider(name = "data-provider1")
    public synchronized Object[][] dataProviderMethod1() { 
    	Object [][] obj=null;
    	if (sessionCountInt>=1) {
    	String tempString="";
    	obj = new Object[totalRows1][colCount];
    	int objCount=0;
    	for(int i=1;i<=rowCount;i+=sessionCountInt) {
    		for(int j=0;j<colCount-1;j++) {
    			if (source.equals("excel")) {	
    			tempString=excelVar.getCellData(i, j);
    			}
    			else if (source.equals("db")) {
    				tempString=allData[i-1][j];
    			}
    			
    					if (tempString.equals("null")){
    						tempString="";
    					}
    				obj[objCount][j]=tempString;
    			
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}
    	}
    	return obj;
    
    }
    
    
    
    
    
    
    
    
    @DataProvider(name = "data-provider2")
    public synchronized Object[][] dataProviderMethod2() { 
    	Object [][] obj=null;
    	if (sessionCountInt>=2) {
    
    	String tempString="";
    	obj = new Object[totalRows2][colCount];
    	int objCount=0;
    	for(int i=2;i<=rowCount;i+=sessionCountInt) {
    		for(int j=0;j<colCount-1;j++) {	
    			if (source.equals("excel")) {	
        			tempString=excelVar.getCellData(i, j);
        			}
        			else if (source.equals("db")) {
        				tempString=allData[i-1][j];
        			}
					if (tempString.equals("null")){
						tempString="";
					}
				obj[objCount][j]=tempString;
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}
    	}
    	return obj;
    
    }
    
    @DataProvider(name = "data-provider3")
    public synchronized Object[][] dataProviderMethod3() { 
    	Object [][] obj=null;
    	if (sessionCountInt>=3) {
    	String tempString="";
    	obj = new Object[totalRows3][colCount];
    	int objCount=0;
    	for(int i=3;i<=rowCount;i+=sessionCountInt) {
    		for(int j=0;j<colCount-1;j++) {
    			if (source.equals("excel")) {	
        			tempString=excelVar.getCellData(i, j);
        			}
        			else if (source.equals("db")) {
        				tempString=allData[i-1][j];
        			}
					if (tempString.equals("null")){
						tempString="";
					}
				obj[objCount][j]=tempString;
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}
    	}
    	return obj;
    
    }
    
    @DataProvider(name = "data-provider4")
    public synchronized Object[][] dataProviderMethod4() { 
    
    	Object [][] obj=null;
    	if (sessionCountInt>=4) {
    	String tempString="";
    	obj = new Object[totalRows4][colCount];
    	int objCount=0;
    	for(int i=4;i<=rowCount;i+=sessionCountInt) {
    		for(int j=0;j<colCount-1;j++) {
    			if (source.equals("excel")) {	
        			tempString=excelVar.getCellData(i, j);
        			}
        			else if (source.equals("db")) {
        				tempString=allData[i-1][j];
        			}
					if (tempString.equals("null")){
						tempString="";
					}
				obj[objCount][j]=tempString;
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}
    	}
    	return obj;
    
    }
    
    
    
  
    
    
    @Test(dataProvider="data-provider1")
    public void testMethod1( String result, String description,String podScan,String testInputNbr,String tinCount,String trk,String tinComment,int rowNumber) {
    	
    	 try {
      	  if (validateEC(trk,tinComment)==true){
      		if (source.equals("excel")) {
      		  writeToExcel(rowNumber,1, "pass");
      		}
      		 String[] resultArray = new String[2];
			 resultArray[0]="pass";
			 resultArray[1]="completed";
			 writeToDB(testInputNbr,tinCount,trk,resultArray);
           	 return;
            }
    	 }
    	 catch(Exception e) {
    		 System.out.println(e);
    	 }
    	
      	  
    	try { 
    		driver1.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
		  
		  if (browser.equals("1")) {
			  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			  if (compatibleMode.equals("true")) {	
	    			
	    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
	    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    		    capabilities.setCapability("ignoreZoomSetting", true);
	    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
	    		   // capabilities.setCapability("nativeEvents",false);
	    		 
	    		}
	    		
	    	//System.setProperty(ieSetProperty, ieDriverPath);
	    		System.setProperty("webdriver.ie.driver", homePath+"\\drivers\\IEDriverServer.exe");
	    		try {
	    			driver1 =  new InternetExplorerDriver(capabilities);
	    		
	    		}
	    		catch(Exception e) {
	    			System.out.println(e);
	    		}
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
	    
    	
	    login(driver1,wait1);
	  
	    try {
			doPrerate(driver1,wait1,result, description,podScan,testInputNbr, tinCount, trk, tinComment, rowNumber,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
   
    @Test(dataProvider="data-provider2")
    public void testMethod2(String result, String description,String podScan,String testInputNbr,String tinCount,String trk,String tinComment,int rowNumber) {
    	
    	try {
         	  if (validateEC(trk,tinComment)==true){
         		 writeToExcel(rowNumber,1, "pass");
         		 String[] resultArray = new String[2];
   			 resultArray[0]="pass";
   			 resultArray[1]="completed";
   			 writeToDB(testInputNbr,tinCount,trk,resultArray);
              	 return;
               }
       	 }
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
			  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			  if (compatibleMode.equals("true")) {	
	    			
	    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
	    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    		    capabilities.setCapability("ignoreZoomSetting", true);
	    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
	    		   // capabilities.setCapability("nativeEvents",false);
	    		 
	    		}
	    		
	    	//System.setProperty(ieSetProperty, ieDriverPath);
	    		System.setProperty("webdriver.ie.driver", homePath+"\\drivers\\IEDriverServer.exe");
	    		try {
	    			driver2 =  new InternetExplorerDriver(capabilities);
	    		
	    		}
	    		catch(Exception e) {
	    			System.out.println(e);
	    		}
	    	}
	    	else if (browser.equals("2")) {
	    	 
	    		System.setProperty(chromeSetProperty,chromePath);
	    		driver2 = new ChromeDriver();
	    	}
	     	else if (browser.equals("3")) {
	         	 
	    		
	        	
	        	FirefoxProfile profile = new FirefoxProfile(); 
	        	profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
	        	profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");
	        	profile.setAcceptUntrustedCertificates(true); profile.setAssumeUntrustedCertificateIssuer(true);
	        	DesiredCapabilities capabilities = new DesiredCapabilities(); 
	        	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        	System.setProperty("webdriver.gecko.driver",  homePath+"\\drivers\\geckodriver.exe");
	        	driver2 = new FirefoxDriver(capabilities);
	    	}
	    
	    login(driver2,wait2);
	    try {
			doPrerate(driver2,wait2,result, description,podScan,testInputNbr, tinCount, trk, tinComment,rowNumber,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
    @Test(dataProvider="data-provider3")
    public void testMethod3(String result, String description,String podScan,String testInputNbr,String tinCount,String trk,String tinComment,int rowNumber) {
    	
    	try {
      		 if (validateEC(trk,tinComment)==true){
         		 writeToExcel(rowNumber,1, "pass");
         		 String[] resultArray = new String[2];
   			 resultArray[0]="pass";
   			 resultArray[1]="completed";
   			 writeToDB(testInputNbr,tinCount,trk,resultArray);
              	 return;
               }
       	 }
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
			  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			  if (compatibleMode.equals("true")) {	
	    			
	    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
	    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    		    capabilities.setCapability("ignoreZoomSetting", true);
	    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
	    		   // capabilities.setCapability("nativeEvents",false);
	    		 
	    		}
	    		
	    	//System.setProperty(ieSetProperty, ieDriverPath);
	    		System.setProperty("webdriver.ie.driver", homePath+"\\drivers\\IEDriverServer.exe");
	    		try {
	    			driver3 =  new InternetExplorerDriver(capabilities);
	    		
	    		}
	    		catch(Exception e) {
	    			System.out.println(e);
	    		}
	    	}
	    	else if (browser.equals("2")) {
	    	 
	    		System.setProperty(chromeSetProperty,chromePath);
	    		driver3 = new ChromeDriver();
	    	}
	     	else if (browser.equals("3")) {
	         	 
	    		
	        	
	        	FirefoxProfile profile = new FirefoxProfile(); 
	        	profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
	        	profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");
	        	profile.setAcceptUntrustedCertificates(true); profile.setAssumeUntrustedCertificateIssuer(true);
	        	DesiredCapabilities capabilities = new DesiredCapabilities(); 
	        	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        	System.setProperty("webdriver.gecko.driver",  homePath+"\\drivers\\geckodriver.exe");
	        	driver3 = new FirefoxDriver(capabilities);
	    	}
	    
    	
    login(driver3,wait3);
    try {
		doPrerate(driver3,wait3,result, description,podScan,testInputNbr, tinCount, trk, tinComment,rowNumber,3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider4")
    public void testMethod4(String result, String description,String podScan,String testInputNbr,String tinCount,String trk,String tinComment,int rowNumber) {
    	
    	try {
         	  if (validateEC(trk,tinComment)==true){
         		 writeToExcel(rowNumber,1, "pass");
         		 String[] resultArray = new String[2];
   			 resultArray[0]="pass";
   			 resultArray[1]="completed";
   			 writeToDB(testInputNbr,tinCount,trk,resultArray);
              	 return;
               }
       	 }
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
			  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			  if (compatibleMode.equals("true")) {	
	    			
	    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
	    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    		    capabilities.setCapability("ignoreZoomSetting", true);
	    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
	    		   // capabilities.setCapability("nativeEvents",false);
	    		 
	    		}
	    		
	    	//System.setProperty(ieSetProperty, ieDriverPath);
	    		System.setProperty("webdriver.ie.driver", homePath+"\\drivers\\IEDriverServer.exe");
	    		try {
	    			driver4 =  new InternetExplorerDriver(capabilities);
	    		
	    		}
	    		catch(Exception e) {
	    			System.out.println(e);
	    		}
	    	}
	    	else if (browser.equals("2")) {
	    	 
	    		System.setProperty(chromeSetProperty,chromePath);
	    		driver4 = new ChromeDriver();
	    	}
	     	else if (browser.equals("3")) {
	         	 
	    		
	        	
	        	FirefoxProfile profile = new FirefoxProfile(); 
	        	profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
	        	profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");
	        	profile.setAcceptUntrustedCertificates(true); profile.setAssumeUntrustedCertificateIssuer(true);
	        	DesiredCapabilities capabilities = new DesiredCapabilities(); 
	        	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        	System.setProperty("webdriver.gecko.driver",  homePath+"\\drivers\\geckodriver.exe");
	        	driver4 = new FirefoxDriver(capabilities);
	    	}
	    
    	
    	
    login(driver4,wait4);
    try {
		doPrerate(driver4,wait4,result, description,podScan,testInputNbr, tinCount, trk, tinComment, rowNumber,4);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void login(WebDriver driver,WebDriverWait wait) {
    	
    	
    	

    driver.get(levelUrl);
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	wait = new WebDriverWait(driver,10);
	driver.manage().window().maximize();
	driver.findElement(By.id("username")).sendKeys("5194105");
	driver.findElement(By.id("password")).sendKeys("5194105");
	driver.findElement(By.id("submit")).click();
    	
    }
    
    
    
    
    
    
    
    
    
    public void doPrerate(WebDriver driver,WebDriverWait wait, String result, String description,String podScan,String testInputNbr,String tinCount,String trk,String tinComment,int rowNumber, int instanceNumber) throws InterruptedException {
    	
    	WebElement element;
    	JavascriptExecutor Executor;
 
    	
    	
    	String errorMessage;
    	wait = new WebDriverWait(driver,10);
    	
    	try {
	    	driver.switchTo().frame("header");
			driver.findElement(By.id("preRateEntrySelection")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("content");
			System.out.println("Instance :"+instanceNumber+" Tracking Number: "+trk);
			driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).sendKeys(trk);
			driver.findElement(By.xpath("//button[@id='preRateEntrySelForm:search_button']")).click();
			Thread.sleep(5000);
    		}
    	catch(Exception e){
    		
    		System.out.println("Failed on Home Page");
    		System.out.println(e);
			 String[] resultArray =checkFailure(trk);
				if (resultArray[1].equals("")) {
					 resultArray[1]="Failed Somewhere... No Error Found Tho";
				}
		
		
			 writeToDB(testInputNbr,tinCount,trk,resultArray);
    		Assert.fail("Failed on Home Page");
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
    		
    		try {
           	  if (validateEC(trk,tinComment)==true){
           		 writeToExcel(rowNumber,1, "pass");
           		 String[] resultArray = new String[2];
     			 resultArray[0]="pass";
     			 resultArray[1]="completed";
     			 writeToDB(testInputNbr,tinCount,trk,resultArray);
                	 return;
                 }
         	 }
         	 catch(Exception e) {
         		 System.out.println(e);
         	 
      	}
        	 
    		
		} catch(Exception e) {
			try{
				String errorMessageDropdown;
				By dropDownError;
				dropDownError=By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[5]/td/span/div/div/table/tbody/tr/td/div/div/div/div[2]/table/tbody/tr/td[3]/div/span");
				errorMessage=driver.findElement(dropDownError).getText();
				writeToExcel(rowNumber,1, errorMessage);
				 String[] resultArray =checkFailure(trk);
					if (resultArray[1].equals("")) {
						 resultArray[1]="Failed Somewhere... No Error Found Tho";
					}
			
			
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				
				
				 
				 Assert.fail(errorMessage);
				}
			catch(Exception ee) {
				System.out.println("Didnt find error message from dropdown menu...");
				//driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
					writeToExcel(rowNumber,1, "Failed selecting dropdown menu...");
					 String[] resultArray =checkFailure(trk);
						if (resultArray[1].equals("")) {
							 resultArray[1]="Failed Somewhere... No Error Found Tho";
						}
				
				
					 writeToDB(testInputNbr,tinCount,trk,resultArray);
					Assert.fail("Failed selecting dropdown menu...");
			}
		}
			
	}
		
		
    	
    
    
	public synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
		if (source.equals("excel")) {
		excelVar.setCellData(rowCountExcel, colCountExcel, outputString);
		excelVar.writeCellData();
		}
	}
	
	
	
	
	
	
	
	public synchronized void writeToDB(String testInputNbr,String tinCount,String trk,String[] resultArray) {
		Connection GTMcon=null;
		try {
			
			GTMcon=c.getGtmRevToolsConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PreparedStatement stmt = null;
		

		try {
	    //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
		//stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");  
		stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.prerate_results (test_input_nbr,tin_count,trkngnbr,result,description,hold) values (?,?,?,?,?,?)");  
		stmt.setString(1,testInputNbr);  
		stmt.setString(2,tinCount);  
		stmt.setString(3,trk);  
		stmt.setString(4,resultArray[0]);  
		stmt.setString(5,resultArray[1]);  
		stmt.setString(6,"Y");  

		stmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

		try {
		//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
	//	stmt=GTMcon.prepareStatement("update rebill_results set result=?,description=? where trkngnbr=?"); 
		stmt=GTMcon.prepareStatement("update prerate_results set result=?,description=?,hold=? where trkngnbr=?");
		
		stmt.setString(1,resultArray[0]);  
		stmt.setString(2,resultArray[1]); 
		stmt.setString(3,"Y"); 
		stmt.setString(4,trk); 
		stmt.executeUpdate();
		
	}
	catch(Exception e) {
		System.out.println(e);
	}

	}
	
	
	
	
	
	
	

	
	
	
	
public  Boolean validateEC(String trkngnbr,String tinComment){
	Boolean result=null;
	
	PreparedStatement ps = null;
	Connection ecCon= null;
 if (level.equals("2")){
	 ecCon=c.getEcL2DbConnection();
		
 }
 else if  (level.equals("3")){
	 	
			
			ecCon=c.getEcL3DbConnection();
}

try {
	ps = ecCon.prepareStatement("select * from ec_intl_schema.ec_pre_rate_history where pkg_trkng_nbr =? and EVENT_TYPE_CD_DESC=?");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	System.out.println(e);
	e.printStackTrace();
}
        //   "select * from INTL_EXPRS_ONLN_SCHEMA.intl_package");

   
       try {
		ps.setString(1,trkngnbr);
		ps.setString(2,tinComment);
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
		      result=false;   
		}
		   else{
		      System.out.println("IS NOT NULL");
		      result=true;
		   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       return result;
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	//This is to help speed up program. It will look for the next object.. if found then continue.. if not quickly look for any error message.
	//The next object will have a wait, 
	public Boolean checkVal(WebDriver driver,By elementNew,By elementOld,int caseNumber,int waitTimeLong,int waitTimeShort,int waitTimeDefault,String trkngnbr,int rowNumber) {
		
		String newString,oldString;
		Boolean skip=false;
		WebElement we;
		switch(caseNumber) {
			
		
		//This checks to see if we made it to prerate input screen
		case 1:
			try {
					driver.findElement(elementNew);
					skip=true;
					
				}
				catch(Exception e) {
					System.out.println("Could not find prerate input page...");
				}
			if (skip==false) {
				try {
					driver.manage().timeouts().implicitlyWait(waitTimeShort,TimeUnit.SECONDS);
					oldString=driver.findElement(elementOld).getText();
				
					writeToExcel(rowNumber,1, oldString);
				
					Assert.fail(oldString);
				
				}
				catch(Exception e) {
					System.out.println("Could not find prerate input page...");
					if (source.equals("excel")) {
					writeToExcel(rowNumber,1, "Unknown Error... Could not find prerate input page or Not Eligible Shipment Error");
					}
					Assert.fail("Unknown Error... Could not find prerate input page or Not Eligible Shipment Error");
					}
			}
			
			
		break;
		
		
		
		
		
		case 2:
			try {	
				if (driver.findElement(elementNew).getText().equals("Shipment Selection for Pre-Rate Entry")) {
					/*
					if (validateORE(trkngnbr)==true) {
						Assert.assertTrue(true);
					}
					*/
					
					writeToExcel(rowNumber,1, "Passed");
					
					Assert.assertTrue(true);
					skip=true;
				}
				
			}
			catch(Exception e) {
				System.out.println("Could not go back to prerate entry screen...");
			}
	
			if (skip==false) {
			try {
				driver.manage().timeouts().implicitlyWait(waitTimeShort,TimeUnit.SECONDS);
				oldString=driver.findElement(elementOld).getText();
				
				writeToExcel(rowNumber,1, oldString);
				
				Assert.fail(oldString);
			}
			catch(Exception e) {
				System.out.println("Could not go back to prerate entry screen....");
				//Assert.fail("Unknown Error... Could not go back to prerate entry screen...");
			}
			break;
		}
		case 3:
			try {
				if (driver.findElement(elementNew).getText().equals("Shipment Selection for Pre-Rate Entry")) {
					/*
					if (validateORE(trkngnbr)==true) {
						
						Assert.assertTrue(true);
					}
					*/
					Assert.assertTrue(true);
					skip=true;
			}
			}
			catch(Exception e) {
				System.out.println("Could not find top error....");
			}
	
			if (skip==false) {
			try {
				driver.manage().timeouts().implicitlyWait(waitTimeShort,TimeUnit.SECONDS);
				oldString=driver.findElement(elementOld).getText();
				
				writeToExcel(rowNumber,1, "Passed");
				
				Assert.fail(oldString);
			}
			catch(Exception e) {
				System.out.println("Could not find bottom error....");
				
			}
			break;
		}
		case 4:
			break;
		
			
			
			
		}
		  driver.manage().timeouts().implicitlyWait(waitTimeDefault,TimeUnit.SECONDS);
		  return skip;
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
	 if (level.equals("2")){
	    
		 oreCon=c.getOreL2DbConnection();
			
	 }
	 else if  (level.equals("3")){
		 
			
				oreCon=c.getOreL3DbConnection();
		
			
	}

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
			      resultArray[0]="fail"; 
			      resultArray[1]="Could Not Find in IORE";
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
				   if (!rs.getString(4).equals("OR")) {
					   System.out.println("ITEM_PRCS_CD is "+rs.getString(4));
					   resultString+="ITEM_PRCS_CD is "+rs.getString(4);
					   
					  
				   }
				   
				   
				   	  resultArray[0]="fail"; 
				   	  if (resultString.equals("")) {
				      resultArray[1]="";
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
	
