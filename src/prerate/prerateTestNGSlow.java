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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configuration.excel;


public class prerateTestNGSlow {
  
    //False = Running from xml only
	//True = Running from GUI only
	Boolean testingMode,testingDB;
	
	
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
	

	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","sessionCount"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String sessionCount) {
		this.filepath=filepath;
		this.level=level;
		this.browser=browser;
		this.compatibleMode=compatibleMode;
		this.source=source;
		this.allCheckBox=allCheckBox;
		this.nullCheckBox=nullCheckBox;
		this.failedCheckBox=failedCheckBox;
		this.sessionCount=sessionCount;
		sessionCountInt=Integer.parseInt(sessionCount);
		System.out.println(sessionCountInt);
	}

	@BeforeClass
	public void setupExcel() {

	
		testingMode=true;
		testingDB=false;
	

		testingMode=false;
		testingDB=true;
		if (testingMode==true) {
			filepath=homePath+"\\test data\\PRERATE_UPDATE.xlsx";
			level="3";
			browser="2";
			compatibleMode="false";
			source="excel";
			allCheckBox="false";
			nullCheckBox="false";
			failedCheckBox="false";
			sessionCountInt=1;
		}

		
		
		filepath=homePath+"\\test data\\prerate_update_pc.xlsx";
		level="3";
		browser="2";
		compatibleMode="false";
		source="excel";
		allCheckBox="false";
		nullCheckBox="false";
		failedCheckBox="false";
    	excelVar = new excel(filepath);
		
		
		
		
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (testingMode==true && source.equals("excel")){

        }
	  
    else {
    	if (source.equals("db")) {}
    	else if (source.equals("excel")) {
    		excelVar = new excel(filepath);
    	}
    	
    	this.browser=browser;
    	this.level=level;
    	this.compatibleMode=compatibleMode;
    	this.source=source;
    	this.allCheckBox=allCheckBox;
		this.nullCheckBox=nullCheckBox;
		this.failedCheckBox=failedCheckBox;
	
    	
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
    		levelUrl="https://testsso.secure.fedex.com/l2/prerates";
    	}
    	else if (level.equals("3"))
    	{
    		levelUrl="https://testedcsso.secure.fedex.com/l3/prerates";
    		//levelUrl="https://testsso.secure.fedex.com/l3/prerates";
    	}
            
    	
	}
	
	
	
	
	public void runDbQuery() {
		Connection GTMcon=null;
		Statement stmt = null;
		ResultSet rs = null;
		 ResultSetMetaData rsmd=null;

	
    	try {
    		if (testingDB==true) {
    			Class.forName("oracle.jdbc.driver.OracleDriver");
    			GTMcon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
    		}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
  	 String databaseSqlCount="select count(*) as total from prerate_view ";
  	 String databaseSqlQuery="select PRE_RATE_TYPE_CD, DESCRIPTION,POD_SCAN ,TIN_COUNT ,TEST_INPUT_NBR ,TRKNGNBR ,PRERATE_AMT, CURRENCY_CD, APPROVER_ID ,CHRG_CD1 ,CHRG_AMT1 ,CHRG_CD2 ,CHRG_AMT2 ,CHRG_CD3, CHRG_AMT3, CHRG_CD4, CHRG_AMT4 from prerate_view ";
    
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
       	
    	
    	
    	if (testingDB==true) {
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
    public void testMethod1( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
    	if (testingDB==true) {
    	 try {
      	  if (validateEC(trk)==true){
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
    	}
      	  
    	try { 
    		driver1.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
    	if (browser.equals("1")) {
    		if (compatibleMode.equals("true")) {	
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
    	
	    login(driver1,wait1);
	  
	    try {
			doPrerate(driver1,wait1,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
   
    @Test(dataProvider="data-provider2")
    public void testMethod2( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
    	if (testingDB==true) {
    	try {
         	  if (validateEC(trk)==true){
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
    	}
      	 
      	 
    	try { 
    		driver2.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    	if (browser.equals("1")) {
    		if (compatibleMode.equals("true")) {	
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
	    login(driver2,wait2);
	    try {
			doPrerate(driver2,wait2,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
    @Test(dataProvider="data-provider3")
    public void testMethod3( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
    	if (testingDB==true) {
    	try {
      		 if (validateEC(trk)==true){
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
    	}
      	 
      	 
    	try { 
    		driver3.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    	if (browser.equals("1")) {
    		if (compatibleMode.equals("true")) {	
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
    	
    	
    login(driver3,wait3);
    try {
		doPrerate(driver3,wait3,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider4")
    public void testMethod4( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
    	if (testingDB==true) {
    	try {
         	  if (validateEC(trk)==true){
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
    	}
      	 
      	 
    	try { 
    		driver4.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    	if (browser.equals("1")) {
    		if (compatibleMode.equals("true")) {	
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
    	
    	
    login(driver4,wait4);
    try {
		doPrerate(driver4,wait4,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,4);
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
    
    
    
    
    
    
    
    
    
    public void doPrerate(WebDriver driver,WebDriverWait wait, String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber, int instanceNumber) throws InterruptedException {
    	
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
    	wait = new WebDriverWait(driver,10);
    	
    	try {
	    	driver.switchTo().frame("header");
			driver.findElement(By.id("preRateEntrySelection")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("content");
			System.out.println("Instance :"+instanceNumber+" Tracking Number: "+trk);
			driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).sendKeys(trk);
			driver.findElement(By.xpath("//button[@id='preRateEntrySelForm:search_button']")).click();
    		}
    	catch(Exception e){
    		
    		System.out.println("Failed on Home Page");
    		System.out.println(e);
    		String[] resultArray = new String[2];
			 resultArray[0]="fail";
			 resultArray[1]="Failed on Home Page";
			 writeToDB(testInputNbr,tinCount,trk,resultArray);
    		Assert.fail("Failed on Home Page");
    		}
		
    	try{
			
		
		//Fast way to move to next screen... fails if prerate input not there.
		driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
		element = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[2]/span[2]"));
		Executor = (JavascriptExecutor)driver;
		Executor.executeScript("arguments[0].click();", element);
	
		if (type.equals("ADDR CHANGE FEE")){
			driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
			element1 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[2]/span[2]"));
			Executor1 = (JavascriptExecutor)driver;
			Executor1.executeScript("arguments[0].click();", element1);
		}
		else if(type.equals("COLD CHAIN")){
			driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
			element2 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[4]/span[2]"));
			Executor2 = (JavascriptExecutor)driver;
			Executor2.executeScript("arguments[0].click();", element2);
		}
		else if(type.equals("Trucking Fees")){
			driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
			element3 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[3]/span[2]"));
			Executor3 = (JavascriptExecutor)driver;
			Executor3.executeScript("arguments[0].click();", element3);
		}
		else if(type.equals("PH LPC")){
			if(level.contentEquals("2")){
				//L2 PHLPC=12 and L3 PHLPC=8
				driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
				element4 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[12]/span[2]"));
				Executor4 = (JavascriptExecutor)driver;
				Executor4.executeScript("arguments[0].click();", element4);
			}
			else if (level.contentEquals("3")){
				driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
				element4 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[8]/span[2]"));
				Executor4 = (JavascriptExecutor)driver;
				Executor4.executeScript("arguments[0].click();", element4);

			}
		}
		} catch(Exception e) {
			try{
				String errorMessageDropdown;
				By dropDownError;
				dropDownError=By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[5]/td/span/div/div/table/tbody/tr/td/div/div/div/div[2]/table/tbody/tr/td[3]/div/span");
				errorMessage=driver.findElement(dropDownError).getText();
				writeToExcel(rowNumber,1, errorMessage);
				 String[] resultArray = new String[2];
				 resultArray[0]="fail";
				 resultArray[1]=errorMessage;
				 if (testingDB==true) {
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				 }
				 Assert.fail(errorMessage);
				}
			catch(Exception ee) {
				System.out.println("Didnt find error message from dropdown menu...");
				//driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
					writeToExcel(rowNumber,1, "Failed selecting dropdown menu...");
					String[] resultArray = new String[2];
					resultArray[0]="fail";
					resultArray[1]="Failed selecting dropdown menu...";
					if (testingDB==true) {
					writeToDB(testInputNbr,tinCount,trk,resultArray);
					}
					Assert.fail("Failed selecting dropdown menu...");
			}
			
		}
		
		try {
	

		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:amountId:pymt_amnt_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:amountId:pymt_amnt_input']")).sendKeys(amount);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:currCodeId:cuu_code_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:currCodeId:cuu_code_input']")).sendKeys(currcode);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:rateApprover_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:rateApprover_input']")).sendKeys(approverID);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde1_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde1_input']")).sendKeys(cc1);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt1_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt1_input']")).sendKeys(cm1);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde2_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde2_input']")).sendKeys(cc2);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt2_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt2_input']")).sendKeys(cm2);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(cc3);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(cm3);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).sendKeys(cc4);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).clear();
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).sendKeys(cm4);


		//ENABLE THIS TO SUBMIT!!!!!!
		driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
	
		}
		catch(Exception e) {
			if (testingDB==true) {
			 if (validateEC(trk)==true) {
				 writeToExcel(rowNumber,1, "pass");
				 String[] resultArray = new String[2];
				 resultArray[0]="pass";
				 resultArray[1]="completed";
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				 return;
			 }
			 writeToExcel(rowNumber,1, "Failed on the input menu...");
			 String[] resultArray = new String[2];
			 resultArray[0]="fail";
			 resultArray[1]="Failed on the input menu...";
			 writeToDB(testInputNbr,tinCount,trk,resultArray);
			 Assert.fail("Failed on the input menu...");
		}
		}
		
		//Tries to see if back to homepage indicating success
		try {
			/*
				webElementTemp=By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]");
				if (driver.findElement(webElementTemp).getText().equals("Shipment Selection for Pre-Rate Entry")) {
					 writeToExcel(rowNumber,1, "Passed");
					 return;
				}
				*/
			 Thread.sleep(10000);
			
			
			 wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]")), "Shipment Selection for Pre-Rate Entry"));
			 if (testingDB==true) {
				 if (validateEC(trk)==true) {
				 writeToExcel(rowNumber,1, "pass");
				 String[] resultArray = new String[2];
				 resultArray[0]="pass";
				 resultArray[1]="completed";
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				 return;
			 }
			 }
			 else {
				 System.out.println("Could not find in EC DB...");
			 }
		}
		catch(Exception e) {
				System.out.println("Did Not Find Prerate Home Page First Time Around");	
				System.out.println(e);	
			}
			

		
		
		
		
		try{
			Thread.sleep(2000);
			count=(driver.findElements(By.xpath("//input[@type='checkbox']")).size());
			//System.out.println(count);
			for( k=2;k<count;k++){
				sel=driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).isEnabled();
				if(sel==true){
					driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).click();
				}
				else {
					if (testingDB==true) {
					 if (validateEC(trk)==true) {
						 writeToExcel(rowNumber,1, "pass");
						 String[] resultArray = new String[2];
						 resultArray[0]="pass";
						 resultArray[1]="completed";
						 writeToDB(testInputNbr,tinCount,trk,resultArray);
						Assert.fail("Override Disabled.");
						 return;
					 }
					writeToExcel(rowNumber,1, "Override Disabled.");
					 String[] resultArray = new String[2];
					 resultArray[0]="fail";
					 resultArray[1]="Override Disabled";
					 writeToDB(testInputNbr,tinCount,trk,resultArray);
					Assert.fail("Override Disabled.");
					break;
				}
				}
			}
			driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
			
		}
		catch (NoSuchElementException a){
			//System.out.println("just proceed further");
		
		}
	

		
		   
		//Check again for success
		try{
			Thread.sleep(10000);
			wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]")), "Shipment Selection for Pre-Rate Entry"));
			if (testingDB==true) {
			if (validateEC(trk)==true) {
				 writeToExcel(rowNumber,1, "pass");
				 String[] resultArray = new String[2];
				 resultArray[0]="pass";
				 resultArray[1]="completed";
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				 return;
		}
			}
			 else {
					 System.out.println("Could not find in EC DB...");
				 }
		}
			catch(Exception e) {
				System.out.println("Did not find homepage after trying to enable stat codes");	
				System.out.println(e);	
			}
			
			
		
		
		//Check either upper or lower error
		try{
			webElementTemp=By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[6]/td/table/tbody/tr[2]/td/span/span/span/span[2]");
			errorMessage=driver.findElement(webElementTemp).getText();
			if (testingDB==true) {
			if (validateEC(trk)==true) {
				
				 writeToExcel(rowNumber,1, "pass");
				 
				 String[] resultArray = new String[2];
				 resultArray[0]="pass";
				 resultArray[1]="completed";
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				 return;
			 }
			 
			}
			 writeToExcel(rowNumber,1, errorMessage);
			 
			 String[] resultArray = new String[2];
			 resultArray[0]="fail";
			 resultArray[1]=errorMessage;
			 if (testingDB==true) {
			 writeToDB(testInputNbr,tinCount,trk,resultArray);
			 }
			Assert.fail(errorMessage);

		}
			catch(Exception e) {
				System.out.println("Did not find either upper or lower error");	
				System.out.println(e);	
			}
					
			
		
		
		//Check either upper or lower error
		try{
			webElementTemp=By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[4]/td/span/div/div[1]/div[2]/div/table[2]/tbody/tr/td/span/span/span/span[2]");
			errorMessage=driver.findElement(webElementTemp).getText();
			if (testingDB==true) {
			 if (validateEC(trk)==true ) {
				
				 writeToExcel(rowNumber,1, "pass");
				 
				 String[] resultArray = new String[2];
				 resultArray[0]="pass";
				 resultArray[1]="completed";
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				 return;
			 }
			}
			 writeToExcel(rowNumber,1, errorMessage);
			 
			 String[] resultArray = new String[2];
			 resultArray[0]="fail";
			 resultArray[1]=errorMessage;
			 if (testingDB==true) {
			 writeToDB(testInputNbr,tinCount,trk,resultArray);
			 }
			Assert.fail(errorMessage);
		}
			catch(Exception e) {
				System.out.println("Did not find either upper or lower error");	
				System.out.println(e);	
			}

		
		
		try {
			//Actually Should not reach here.
			if (testingDB==true) {
			if (validateEC(trk)==true) {
				
				writeToExcel(rowNumber,1, "Passed");
				
				 String[] resultArray = new String[2];
				 resultArray[0]="pass";
				 resultArray[1]="completed";
				 writeToDB(testInputNbr,tinCount,trk,resultArray);
				 return;
			 }
			}
			 writeToExcel(rowNumber,1,"Failed Somewhere... No Error Found Tho");
			 String[] resultArray = new String[2];
			 resultArray[0]="fail";
			 resultArray[1]="Failed Somewhere... No Error Found Tho";
			 if (testingDB==true) {
			 writeToDB(testInputNbr,tinCount,trk,resultArray);
			 }
			Assert.fail("No Error Found, but still failed");
		
		}
		catch (NoSuchElementException a){
		
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
		//stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");  
		stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.prerate_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");  
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
	//	stmt=GTMcon.prepareStatement("update rebill_results set result=?,description=? where trkngnbr=?"); 
		stmt=GTMcon.prepareStatement("update prerate_results set result=?,description=? where trkngnbr=?");
		
		stmt.setString(1,resultArray[0]);  
		stmt.setString(2,resultArray[1]); 
		stmt.setString(3,trk); 
		stmt.executeUpdate();
		
	}
	catch(Exception e) {
		System.out.println(e);
	}

	}
	
	
	
	
	
	
	

	
	
	
	
public  Boolean validateEC(String trkngnbr){
	Boolean result=null;
	
	PreparedStatement ps = null;
	Connection ecCon= null;
 if (level.equals("2")){
       // con=c.getOreL2DbConnection();
       // con=c.getEcL2DbConnection();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ecCon=DriverManager.getConnection("jdbc:oracle:thin:@//idb00271.ute.fedex.com:1526/IE2VD991","test_readonly", "perftest");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 }
 else if  (level.equals("3")){
	 	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ecCon=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00299.ute.fedex.com:1526/sdb00299.ute.fedex.com","test_readonly", "perftest");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}

try {
	ps = ecCon.prepareStatement("select * from ec_intl_schema.ec_pre_rate_history where pkg_trkng_nbr =?");
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
	
 
	
	
	
	
	
	
	
    }
	
