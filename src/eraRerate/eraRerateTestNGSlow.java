
package eraRerate;


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
import org.openqa.selenium.ie.InternetExplorerDriver;
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
import configuration.excel;
import configuration.importData;

public class eraRerateTestNGSlow {

	
	
    //False = Running from xml only
	//True = Running from GUI only
	Boolean testingMode=false;
	Boolean uploadTrkToDB=true;
	
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
	String filepath;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static FileInputStream fin;
	static FileOutputStream fout;
	public int  rowcount;
	int waitTime;
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
	String[][] allData;
	
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","sessionCount","customString","customCheckBox","databaseDisabled"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String sessionCount,String customString,String customCheckBox,String databaseDisabled) {
	importData id=new importData();
		c=id.getConfig();
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
    	
	
        	if(source.equals("excel")) {
        		excelVar = new excel(filepath);
        	}
	        	this.browser=browser;
	        	this.level=level;
	        	this.compatibleMode=compatibleMode;
	        	this.source=source;
	        	this.allCheckBox=allCheckBox;
				this.nullCheckBox=nullCheckBox;
				this.failedCheckBox=failedCheckBox;
				this.sessionCount=sessionCount;
				this.customString=customString;
	        	this.customCheckBox=customCheckBox;
	        	this.databaseDisabled=databaseDisabled;
	        	sessionCountInt=Integer.parseInt(sessionCount);
	        	waitTime=Integer.parseInt(c.getEraRerateSecondTimeout());
        
       
    	
    	if(source.equals("excel")) {
	    	excelVar.setUpExcelWorkbook();
	    	excelVar.setUpExcelSheet(0);
	    	excelVar.setRowCountAutomatically(2);
	    	excelVar.setColCountAutomatically(0);
	    	rowCount=excelVar.getRowCount();
	    	colCount=excelVar.getColCount()+1;
	    	
    	}
    	
    	else if(source.equals("db")) {
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
    		levelUrl=c.getEraRerateL2Url();
    		
    	}
    	else if (level.equals("3"))
    	{
    		levelUrl=c.getEraRerateL3Url();;
    	
    	}
       
    	
	}
	
	
	
	
	public void runDbQuery() {
		Connection GTMcon=null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd=null;
		//Change to L3
    	try {
			
			GTMcon=c.getGtmRevToolsConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
    	
    	String databaseSqlCount="select count(*) as total from era_rerate_view where trkngnbr is not null ";
    	//String databaseSqlQuery="select result, description, test_input_nbr, tin_count, trkngnbr, invoice_nbr_1,invoice_nbr_2,SVC_CHANGED,ACT_Wgt, CUST_Wgt, RERATE_COMMENTS, region,  USERNAME,   password from era_rerate_view where trkngnbr is not null ";
    	String databaseSqlQuery="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR,	TIN_COUNT,	TRKNGNBR,	INVOICE_NBR_1	,INVOICE_NBR_2,	RATE_WEIGHT,	ACTUAL_WEIGHT,	WGT_TYPE,	LENGTH,	WIDTH,	HEIGHT	,WORKABLE,	DIM_TYPE,	PAYOR	,BILL_ACCT_NBR	,SVC_TYPE,	SERVICE_NAME,	PACKAGE_TYPE	,RERATE_TYPE,	REGION,	USERNAME,	PASSWORD,	RS_TYPE,	COMPANY,	VAL_DESC,	COMMENTS from era_rerate_view where trkngnbr is not null ";
    	
    	
    	if (customCheckBox.equals("false")) {
    	
    	if (allCheckBox.equals("false")) {
    		databaseSqlCount+="and ";
    		databaseSqlQuery+="and ";
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
    	
    	}
    	else if (customCheckBox.equals("true")){
    		databaseSqlCount+="and "+customString;
    		databaseSqlQuery+="and "+customString;
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
    	
    	 obj= new Object[totalRows1][colCount];;
    	try {
    		String tempString="";
    	
    	int objCount=0;
    	for(int i=1;i<=rowCount;i+=sessionCountInt) {
    		for(int j=0;j<colCount-1;j++) {
    				if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					tempString=allData[i-1][j];
    				}
    					if (tempString == null || tempString.equals("null")){
    						tempString="";
    					}
    				obj[objCount][j]=tempString;
    			
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}  	
}catch(Exception e) {
	System.out.println(e);
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
    			if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					tempString=allData[i-1][j];
    				
    				}
					if (tempString == null || tempString.equals("null")){
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
    			if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					tempString=allData[i-1][j];
    				}
					if (tempString == null || tempString.equals("null")){
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
    			if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					
    					tempString=allData[i-1][j];
    				
    				}
					if (tempString == null || tempString.equals("null")){
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
    
    
    
    
   
    @Test(dataProvider="data-provider1",retryAnalyzer = Retry.class)
    public void testMethod1(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,
    		String rateWeight,String actualWeight,String weightType,String length,String width,String height,
    		String workable,String dimType,String payor,String billAcctNbr,String svcType,String svcName,String packageType,
    		String rerate_type,String region ,String username ,String password,
    		String rsType,String company,String valDesc,String comments,int rowNumber) {
     
    	
    	
    	
    	System.out.println("Instance: 1");
    	
    	System.out.println(result);
    	System.out.println(descripiton);
    	System.out.println(testInputNbr);
    	System.out.println(tinCount);
    	System.out.println(trk);
    
    	System.out.println(region);
    	System.out.println(username);
    	System.out.println(password);
 
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
    	       	 return;
    	  	  
    	  	  			}
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
    	wait1 = new WebDriverWait(driver1,20);
	    login(driver1,wait1,username,password);
	    
	    try {
	    	
	    	
	
    		
			doEraRerate(driver1,wait1,  result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2,
					rateWeight, actualWeight, weightType, length, width,height,
					workable,dimType,payor,billAcctNbr,svcType,svcName,packageType,
					rerate_type, region, username, password,
					rsType , company , valDesc,comments,rowNumber,1);
	
		
	    
	    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
   
    @Test(dataProvider="data-provider2",retryAnalyzer = Retry.class)
    public void testMethod2( String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String svcChanged,String dimDetails,String actWeight,String custWeight,String rerateComments,String region ,String login ,String password,int rowNumber) {
     
    	System.out.println("Instance: 2");
    	readTrk(trk);
    	
    	//Will Check if Trk is already successful;
  	  String[] resultArray = validateResults(trk);
  	  if ( resultArray[0].equals("pass")){
  		 if(source.equals("excel")) {
       	 writeToExcel(rowNumber, 0,"pass");
       	 writeToExcel(rowNumber, 1,"completed");
  		 }
       	 return;
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
	    	doRebill(driver2,wait2, result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1,invoiceNbr2,svcChanged, dimDetails, actWeight, custWeight, rerateComments, region , login , password,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
    @Test(dataProvider="data-provider3",retryAnalyzer = Retry.class)
    public void testMethod3( String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String svcChanged,String dimDetails,String actWeight,String custWeight,String rerateComments,String region ,String login ,String password,int rowNumber) {
    	System.out.println("Instance: 3");
    	readTrk(trk);
    	
    	
    	//Will Check if Trk is already successful;
  	  String[] resultArray = validateResults(trk);
  	  if ( resultArray[0].equals("pass")){
  		 if(source.equals("excel")) {
       	 writeToExcel(rowNumber, 0,"pass");
       	 writeToExcel(rowNumber, 1,"completed");
  		 }
       	 return;
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
    	doRebill(driver3,wait3, result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1,invoiceNbr2,svcChanged, dimDetails, actWeight, custWeight, rerateComments, region , login , password,3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider4",retryAnalyzer = Retry.class)
    public void testMethod4(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String svcChanged,String dimDetails,String actWeight,String custWeight,String rerateComments,String region ,String login ,String password,int rowNumber) {
    	System.out.println("Instance: 4");
    	//Will Check if Trk is already successful;
    	readTrk(trk);
    	
    	String[] resultArray = validateResults(trk);
    	  if ( resultArray[0].equals("pass")){
    			 if(source.equals("excel")) {
         	 writeToExcel(rowNumber, 0,"pass");
         	 writeToExcel(rowNumber, 1,"completed");
    			 }
         	 return;
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
    	
    	doRebill(driver4,wait4, result,  descripiton, testInputNbr, tinCount, trk,invoiceNbr1,invoiceNbr2, svcChanged, dimDetails, actWeight, custWeight, rerateComments, region , login , password, 4);
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
			driver.findElement(By.id("okta-signin-username")).sendKeys(login);
			driver.findElement(By.id("okta-signin-password")).sendKeys(password);
			driver.findElement(By.id("okta-signin-submit")).click();
			
    	}
    	catch(Exception e) {
    		
    		 Assert.fail("Could Not Login");
    	}
    }
   
    
    public void doEraRerate(WebDriver driver,WebDriverWait wait, 
    		String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,
    		String rateWeight,String actualWeight,String weightType,String length,String width,String height,
    		String workable,String dimType,String payor,String billAcctNbr,String svcType,String svcName,String packageType,
    		String rerateType,String region ,String username ,String password,
    		String rsType,String company,String valDesc,String comments,
    		int rowNumber,int instance) throws InterruptedException {
    	WebElement element=null;
    	JavascriptExecutor js= (JavascriptExecutor) driver;
    	
    	int packageCounter=0;
    	Boolean exist;
    	WebElement scrollElement;
    	
    	wait=new WebDriverWait(driver,20);
    	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
    	 /*
    	if(rerateType.equals("service")) {
        	 
    		 if(source.equals("excel")) {
	               	 writeToExcel(rowNumber, 0,"fail");
	               	 writeToExcel(rowNumber, 1,"Change Service Code Not Added Yet");
	               	 }
	   				 if(databaseDisabled.equals("false")) {
     	   			 String[] resultArray = new String[2];
     	   			 	resultArray[0]="fail";
     	   				resultArray[1]="Change Service Code Not Added Yet";
     	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                    	 }
	   				Assert.fail("Failed on Service Change");
    	}
    	
    	*/
    	
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
   		 if(source.equals("excel")) {
           	 writeToExcel(rowNumber, 0,"fail");
           	 writeToExcel(rowNumber, 1,"Failed on Entering Tracking Number");
           	 }
				 if(databaseDisabled.equals("false")) {
	   			 String[] resultArray = new String[2];
	   			 	resultArray[0]="fail";
	   				resultArray[1]="Failed on Entering Tracking Number";
	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
            	 }
    		 Assert.fail("Failed on Entering Tracking Number");
    		
    	}
    	
    	//Try to Click Package Tab
    	int counter1=0;
    	String tempString1;
    	driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
    	while (counter1<10) {
    	try {  
    		counter1++;
    		System.out.println("Trying to click package tab");
    		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
    		js.executeScript("arguments[0].click()", element);    
    		tempString1=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/div[1]/span[1]")).getText();
    		if(tempString1.equals("Charge Code Description")) {
    			System.out.println("Found Code Desc");
    			break;
    		}
    	}
    	catch(Exception e) {
    		try {  
    			System.out.println("Trying to click popup");
        		driver.findElement(By.xpath(" /html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys(invoiceNbr1);
        		Thread.sleep(1000);
        		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
        		driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
        		System.out.println("Found Pop Up");
        		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[3]/a"));
            	js.executeScript("arguments[0].click()", element);
        }	catch(Exception ee) {
        	System.out.println("Could Not Find Pop Up Or Continue To Charge Code Screen");
           // Assert.fail("Could Not Find Popup Or COntinue to Package Screen");
        	}
    	}
    	}
    	
    	if(counter1>=10) {
    		 if(source.equals("excel")) {
               	 writeToExcel(rowNumber, 0,"fail");
               	 writeToExcel(rowNumber, 1,"Could Not Get To Charge Code Details");
               	 }
    				 if(databaseDisabled.equals("false")) {
    	   			 String[] resultArray = new String[2];
    	   			 	resultArray[0]="fail";
    	   				resultArray[1]="Could Not Get To Charge Code Details";
    	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                	 }
        		
     		Assert.fail("Could Not Get To Charge Code Details");
     	}
    	
    	
    	/////NEW CODE TO VERIFY
    	
    	/*
    	System.out.println();
    	try {                              
	  		if (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/ul/li[6]")).getAttribute("class").contains("disabled")){	
	  		System.out.println("Is Not Enabled");
	  		}
	  		else if (!driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/ul/li[6]")).getAttribute("class").contains("disabled")) {
	  			
		  		System.out.println("Is Not enabled");
		  		
    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/ul/li[6]")).click();
	  		 if (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div")).getText().contains(" CR ")) {
	  			 String[] resultArray = new String[2];
	   			 	resultArray[0]="pass";
	   				resultArray[1]="completed";
	   				writeToDB(testInputNbr,tinCount,trk,resultArray);
	   				return;
	  		 } 
	  		 else if  (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div")).getText().contains("DN")) {
	  			 String[] resultArray = new String[2];
	   			 	resultArray[0]="pass";
	   				resultArray[1]="denied";
	   				writeToDB(testInputNbr,tinCount,trk,resultArray);
	   				return;
	  		 } 
	  		 else {
	  			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/ul/li[1]/a")).click();
	  		 }
	  			
	  		 }
    	}
    	catch(Exception e) {
    		System.out.println("Code for Remarks 1");
    		
    	}
    	*/
    	
    	
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
	         actionDropDown.selectByValue("RT");
	         Thread.sleep(5000);
      		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
      		 Thread.sleep(2000);
      		 
      		 
      		 try {
      			 driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        		 String error = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
        		  String[] resultArray = new String[2];
        		 resultArray[0]="fail";
        		 resultArray[1]=error;
        		 if(source.equals("excel")) {
                	 writeToExcel(rowNumber, 0, resultArray[0]);
                	 writeToExcel(rowNumber, 1, resultArray[1]);
                	 }
        		 writeToDB(testInputNbr,tinCount,trk,resultArray);
        		 return;
        		 
        	 }
        	 catch(Exception e) {
        		 System.out.println("No Popup");
        	 }
      		 
      		
	         
         }
      
      catch(Exception e) {
    	  
    	  System.out.println("Failed at Drop Down");
    	  Assert.fail("Failed at Drop Down");
      }
   
         driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        System.out.println();
        // driver.findElement(By.xpath("")).sendKeys();
       //  driver.findElement(By.xpath("")).click();
		 
         
     if(rerateType.contains("weight")) {
    	 if (!rateWeight.equals("")) {
    		
    		 if (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).isEnabled()) {
    			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).clear();		
    			 Thread.sleep(1000);
    			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).sendKeys(rateWeight);
    		 }
    		 else if (!driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).isEnabled()) {
    			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).clear();	
    			 Thread.sleep(1000);
    			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).sendKeys(rateWeight);
        		 }
    		 
    	 }
    	 
    	 if (!actualWeight.equals("")) {
    	 if ( driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).isEnabled()) {
    		 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).clear();	
    		 Thread.sleep(1000);
    		 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).sendKeys(actualWeight);
    		 }
    		 else if (! driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[2]/div/div/input")).isEnabled()) {
    			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).clear();	
    			 Thread.sleep(1000);
    			 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[2]/div[3]/div/div/input")).sendKeys(actualWeight);
    	    		 }
    	 }
     }
     
     if(rerateType.contains("dim")) {
    	 
    	
    	 if (!length.equals("")) {
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[2]/div[1]/div/input")).clear();	
    		 Thread.sleep(1000);
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[2]/div[1]/div/input")).sendKeys(length);
    	 }
    	 if (!width.equals("")) {
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[3]/div[1]/div/input")).clear();	
    		 Thread.sleep(1000);
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[3]/div[1]/div/input")).sendKeys(width);
    	 }
    	 if (!height.equals("")) {
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[4]/div[1]/div/input")).clear();	
    		 Thread.sleep(1000);
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[4]/div[4]/div[1]/div/input")).sendKeys(height);
    	 }
    	 
    	 if (!dimType.equals("")) {
    		 if (dimType.equals("LB")) {
    			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[5]/div[2]/div/div/label/span")).click();
    			
    			 
    		 }
    		 if (dimType.equals("CM")) {
    			 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[5]/div[3]/div/div/label/span")).click();
    			
    			 
    		 }
    	 }
    	 
    	 
     }
     
     
     if(rerateType.contains("payor")) {
    	 if (payor.equals("1")) {
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[2]/div[1]/div/label/span")).click();
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[2]/div[2]/div/input")).clear();	
    		 Thread.sleep(1000);
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[2]/div[2]/div/input")).sendKeys(billAcctNbr);
    	 }
    	 if (payor.equals("2")) {
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[3]/div[1]/div/label/span")).click();
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[3]/div[2]/div/input")).clear();	
    		 Thread.sleep(1000);
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[3]/div[2]/div/input")).sendKeys(billAcctNbr);
    	 }
    	 if (payor.equals("3")) {
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[4]/div[1]/div/label/span")).click();
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[4]/div[2]/div/input")).clear();	
    		 Thread.sleep(1000);
    		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[3]/div[4]/div[2]/div/input")).sendKeys(billAcctNbr);
    	 }
     
     }
     if(rerateType.contains("service")) {
    	 
    	Select sel = new Select(driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div[3]/div/div/div/select")));
    	sel.selectByVisibleText(svcName);
     }
     
     
     //Click Rerate
     driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[1]")).click();
    
     //Check Popup
     
     try {
    	 String tempErrorString="";
    	
    	List<WebElement> rowCounts =driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/div/div[1]/div[2]/div/div/div"));
    	if ( rowCounts.size()!=0) {
    	for(WebElement we:rowCounts) {
    		System.out.println(we.getText());
    		tempErrorString=tempErrorString+" "+we.getText();
    		System.out.println(tempErrorString);
    	}
    	// System.out.println("GETTING ERRROR!!!! "+driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[1]/div/div[2]/form/div[1]/div[1]")).getText());
    	 						//												/html/body/div[6]/div/div/div[2]/div/div/div[1]/div[2]/div/div/div
//    	 System.out.println("GETTING ERRROR!!!! "+driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/div[1]/div/div/div")).getText());
    	String tempErr;
    	
    	if (tempErrorString.length()>300) {
    		 tempErr=tempErrorString.substring(0,300);
    	}
    	else {
    		 tempErr=tempErrorString;
    	}
    		
    	
    	 if(source.equals("excel")) {
           	 writeToExcel(rowNumber, 0,"fail");
           	 writeToExcel(rowNumber, 1,tempErr);
           	 }
    			 if(databaseDisabled.equals("false")) {
       			 String[] resultArray = new String[2];
       			 	resultArray[0]="fail";
       				resultArray[1]=tempErr;
       				 writeToDB(testInputNbr,tinCount,trk,resultArray);
            	 }
    			 Assert.fail("After Rerate Click No Data Came -- Unknown Error");
    	}
     }
     catch(Exception e) {
    	 
    	 
    	 
    	 if(source.equals("excel")) {
           	 writeToExcel(rowNumber, 0,"fail");
           	 writeToExcel(rowNumber, 1,"After Rerate Click No Data Came -- Unknown Error");
           	 }
    			 if(databaseDisabled.equals("false")) {
       			 String[] resultArray = new String[2];
       			 	resultArray[0]="fail";
       				resultArray[1]="After Rerate Click No Data Came -- Unknown Error";
       				 writeToDB(testInputNbr,tinCount,trk,resultArray);
            	 }
    		
    		Assert.fail("After Rerate Click No Data Came -- Unknown Error");
    	 
    	 System.out.println("Cannot Find Popup");
     }
     
     
     String errorMessage="";
     try {
     List<WebElement> errorList;
	 errorList=driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/div/div[1]/div[2]/div/div/div"));
	 int popupCounter=1;
	
 for (WebElement ele: errorList){
	// if (popupCounter%2==1){
         System.out.println(ele.getText());
         errorMessage+=ele.getText()+" ";
      //   ele.click();
       //  if (ele.isSelected()){
        //	 System.out.println(ele.getText());
         //	}
       //  else{
        //	 System.out.println("ERROR POPUP");
        	// overrideBoolean=true;
      // }
	 }
 if(!errorMessage.equals("")) {
	 if(source.equals("excel")) {
       	 writeToExcel(rowNumber, 0,"fail");
       	 writeToExcel(rowNumber, 1,errorMessage);
       	 }
			 if(databaseDisabled.equals("false")) {
   			 String[] resultArray = new String[2];
   			 	resultArray[0]="fail";
   				resultArray[1]=errorMessage;
   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
        	 }
		
		Assert.fail(errorMessage);
 }
     }
     catch(Exception e) {
    	 System.out.println("no popup errors");
     }
     

     
     
     
     //Click Continue 
     driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[3]")).click();
    
     
     String getText =  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[3]/select[1]")).getText();
     System.out.println(getText);
     
     System.out.println(5000);
     //click process
    
     int i=0;
     try {
    while (i<10) {
    	 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[9]/div[1]/button")).click();
    	 i++;
    }
    }
     catch(Exception e) {
    	 
     }
     //Click Continue
     System.out.println(3000);
     try {
     driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[3]/button[2]")).click();
     }
     catch(Exception e) {
    	 System.out.println(e);
     }
     
     try {
    	 driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
    	 String tempE= driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[1]/h4")).getText();
    	    if (tempE!=null) {
    	 if(source.equals("excel")) {
        	 writeToExcel(rowNumber, 0,"fail");
        	 writeToExcel(rowNumber, 1,tempE);
        	return;
        	 }
			 if(databaseDisabled.equals("false")) {
   			 String[] resultArray = new String[2];
   			 	resultArray[0]="fail";
   				resultArray[1]=tempE;
   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
   				
         	 } 
			 Assert.fail(tempE);
     }
     }
     catch(Exception e) {
    	 
    	 System.out.println("No Error Before Phone");
     }
     
     
     try{
    	 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	 //Click on rebill RPI Complete, Phone, and Continue
          if (username.equals("5194105")){
        	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
         
        	  Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
              contactMethodDropDown.selectByValue("phone");  
              Thread.sleep(1500);  
              driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
            
          }
          else {
        	  
        	  Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
              contactMethodDropDown.selectByValue("phone");  
              Thread.sleep(1500);  
              driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
            
          }
      
          
      
     
       
     }
     catch(Exception e1) {
    	 System.out.println("Failed Selecting Contact Method and Clicking Continue");
     
    	 if(source.equals("excel")) {
        	 writeToExcel(rowNumber, 0,"fail");
        	 writeToExcel(rowNumber, 1,"Failed Selecting Contact Method and Clicking Continue");
        	return;
        	 }
			 if(databaseDisabled.equals("false")) {
   			 String[] resultArray = new String[2];
   			 	resultArray[0]="fail";
   				resultArray[1]="Failed Selecting Contact Method and Clicking Continue";
   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
   				
         	 } 
			 Assert.fail("Failed Selecting Contact Method and Clicking Continue");
     }
     
     Thread.sleep(15000);
     
     try {
    	 
	    	if (databaseDisabled.equals("false")) {
	    		Thread.sleep(5000);
	  		  String[] resultArray = validateResults(trk);
	  	  if ( resultArray[0].equals("pass")){
	       	 if(source.equals("excel")) {
	       	 writeToExcel(rowNumber, 0,"pass");
	       	 writeToExcel(rowNumber, 1,"completed");
	       	
	       	 }
	       	 writeToDB(testInputNbr,tinCount,trk,resultArray);
	       	 return;
	  	  
	  	  			}
	    	}
     }
	  	  
	  	  /*
	  	  Thread.sleep(15000);
	  	  //Check for Denied
	  	if ( resultArray[0].equals("fail")){
	  		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/ul/li[6]/a")).click();
	  		 if (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div")).getText().contains("DN")) {
	  			 
	   			 	resultArray[0]="pass";
	   				resultArray[1]="denied";
	   				writeToDB(testInputNbr,tinCount,trk,resultArray);
	   				
	  		 } 
	  		 else if (driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div")).getText().contains(" CR ")) {
	  			resultArray[0]="pass";
   				resultArray[1]="completed";
   				writeToDB(testInputNbr,tinCount,trk,resultArray);
   				
	  		 }
	  		 return;
	  	}
	  		
	    		}
	  	  }
	    */
	  	  catch(Exception e) {
	  		System.out.println(e);  
	  	  }
	    	
    	 }
     
     
     
     
     
     
     
     
         		 /*
     
              
              
         
            
            	 
            	
            	 driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[1]")).click();
            	
            
            	 //This is the popup for rerate
            	// driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[3]/button[1]")).click();
            	
            	 
            	 try {
            		 
            		 String abc = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h4")).getText();
            		 String error = driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div[2]/div")).getText();
            		 String[] resultArray = new String[2];
            		 resultArray[0]="fail";
            		 resultArray[1]=error;
            		 if(source.equals("excel")) {
                    	 writeToExcel(rowNumber, 0, resultArray[0]);
                    	 writeToExcel(rowNumber, 1, resultArray[1]);
                    	 }
            		 writeToDB(testInputNbr,tinCount,trk,resultArray);
            		 return;
            		 
            	 }
            	 catch(Exception e) {
            		 System.out.println("No Popup");
            	 }
            	 
            	 
            	 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div/button[3]")).click();
             	
            	 Thread.sleep(5000);
          		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
          		 Thread.sleep(2000);
            	 
            	 
          		 
          		 
          		  try{
                 	 //Click on rebill RPI Complete, Phone, and Continue
                       if (login.equals("5194105")|| login.equals("584168")){
                     	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
                       }
                   
                       
                    Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
                    contactMethodDropDown.selectByValue("phone");  
              	    driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
              	//    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
                  	}
              	   		catch(Exception e) {
              	   			System.out.println("Phone Details Failure...");
              	   		}
          		 Thread.sleep(10000);
             //If False.. think maybe there is stat codes to select.
            
             String[] resultArray = validateResults(trk);
             
             
             
             if ( resultArray[0].equals("pass")){
            	 if(source.equals("excel")) {
            	 writeToExcel(rowNumber, 0,"pass");
            	 writeToExcel(rowNumber, 1,"completed");
            	 }
            	 if(uploadTrkToDB==true) {
                 	  writeToDB(testInputNbr,tinCount,trk,resultArray);
                 	 }
            	 return;
            	 
             }
             if (resultArray[0].equals("fail")) {
            	 try {
            		 if(source.equals("excel")) {
                    	 writeToExcel(rowNumber, 0,"fail");
                    	 writeToExcel(rowNumber, 1,"Failed at Last Step");
                    	 }
                    	 if(uploadTrkToDB==true) {
                         	  writeToDB(testInputNbr,tinCount,trk,resultArray);
                         	 }
            	 }
            	 catch(Exception e) {
            		System.out.println("Did not find popup about continuing");
            		 
            	 }
            
          }
             */
             
        
    
    
    
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
    	stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_rerate) values (?,?,?,?,?,?)");  
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
		if (!resultArray[1].equals("")) {
    	stmt=GTMcon.prepareStatement("update era_results set result=?,description=?,ERA_RERATE='Y' where trkngnbr=?");  
		stmt.setString(1,resultArray[0]);  
		stmt.setString(2,resultArray[1]); 
		stmt.setString(3,trk); 
		stmt.executeUpdate();
		}
		else if (resultArray[1].equals("")) {
	    	stmt=GTMcon.prepareStatement("update era_results set result=?,ERA_RERATE='Y' where trkngnbr=?");  
			stmt.setString(1,resultArray[0]);  
			
			stmt.setString(2,trk); 
			stmt.executeUpdate();
			}
	}
	catch(Exception e) {
		System.out.println(e);
	}

    	
    }
    
    public String[] validateResults(String trk) {

    	Connection con = null;
    	String[] resultArray = new String[2];
    	
    	try {
    	
    		if (level.equals("2")){
    			 
       		   //  con=c.getOracleARL2DbConnection();
       	 }
       	 else if (level.equals("3")){
       		 	
       		 	con=c.getOracleARL3DbConnection();
       	 	}
    	
    	}
    	catch(Exception e) {
    		
    		System.out.println("Could Not Get ERA DB Connections");
    	}
    	

    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		stmt=con.prepareStatement("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER=?");  
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
    			      resultArray[1]="";
    			}
    			   else{
    				   
    				  String tempString= rs.getString("NOTES");
    				  if (tempString.contains("RDT CR")) {
    					  resultArray[0]="pass";
	    			      resultArray[1]="completed";
    				  }
    				  else  if (tempString.contains("RDT DN")) {
    					  resultArray[0]="pass";
	    			      resultArray[1]="denied";
    				  }
    				  else {
    					  resultArray[0]="na";
	    			      resultArray[1]="unable to validate";
    					  
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
    


