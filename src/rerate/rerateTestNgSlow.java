package rerate;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configuration.excel;


import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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
import org.testng.asserts.SoftAssert;
import configuration.config;


public class rerateTestNgSlow {
	  
	String tempFile,configFile;
	excel excelVar;
	 Boolean isPresent=false;
	int retryCounter=0;
	int i=0;
	Object o;
	WebDriverWait wait1,wait2,wait3,wait4;
	config c;
	

	long serviceLong1,serviceLong2;
	long invoiceLong1,invoiceLong2;
	long acctLong1,acctLong2;
	long trkngLong1,trkngLong2;
	String a ;
	int count1,count2,count3,count4 ;
	String url1 ="https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp";
	String url2="https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp";

	String  sh1;
	String filepath;
	String d="04/20/2020";
	String d2="06/05/2020";
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static FileInputStream fin;
	static FileOutputStream fout;
	public int  rowcount;
	//WebDriver driver;
	WebDriver driver1,driver2,driver3,driver4;
	static int sheetcount;
	static int j ;
	int totalRows1,totalRows2,totalRows3,totalRows4;
	SoftAssert softAssertion= new SoftAssert();
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
        
        Boolean chrome;
        
        
        WebElement element;
	
      String  homePath=System.getProperty("user.dir");
       String browser;
        String chromeSetProperty="webdriver.chrome.driver";
		String ieSetProperty="webdriver.ie.driver";
		String chromePath=homePath+"\\drivers\\chromedriver.exe";
		String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
        

		
		
	String levelUrl;
		int rowCount;
		int colCount;
		int total;
		int testCounter1,testCounter2,testCounter3,testCounter4;
		int totalMod;
		
		String filepathExcel;
		String startDateText;
		String endDateText;
		String broswer;
		String compatibleMode;
		String sessionCount;
		String source;
		String level;
		int sessionCountInt;
		String[][] allData;
		String allCheckBox,nullCheckBox,failedCheckBox;
		
	    String ed1;
	    String ei1;
	    String gd1;
	    String gi1;
	    String nt1;
	    String sp1;
	    
	    String ed2;
	    String ei2;
	    String gd2;
	    String gi2;
	    String nt2;
	    String sp2;
		
	    String databaseDisabled;
	
	    
		@BeforeClass
		@Parameters({"filepath","level","browser","source","compatibleMode","allCheckBox","nullCheckBox","failedCheckBox","sessionCount","databaseDisabled","startDateText","endDateText","ed1","ei1","gd1","gi1","nt1","sp1","ed2","ei2","gd2","gi2","nt2","sp2"})
		public void setupExcel(String filepath,String level,String browser,String source,String compatibleMode,String allCheckBox,String nullCheckBox,String failedCheckBox,String domesticCheckBox,String internationalCheckBox,String expressCheckBox,String groundCheckBox,String sessionCount,String databaseDisabled,String startDateText,String endDateText,String ed1, String ei1,String gd1, String gi1,String nt1,String sp1,String ed2,String ei2, String gd2, String gi2,String nt2, String sp2) {
			//public void setupExcel() {
			//or from eclipse.
			
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	
	        	this.browser=browser;
	        	this.level=level;
	        	this.compatibleMode=compatibleMode;
	        	this.source=source;
	        	this.allCheckBox=allCheckBox;
				this.nullCheckBox=nullCheckBox;
				this.failedCheckBox=failedCheckBox;
	        	this.sessionCount=sessionCount;
	        	this.startDateText=startDateText;
	        	this.endDateText=endDateText;
	        	this.databaseDisabled=databaseDisabled;
	        	
	        	
	        	this.ed1=ed1;
	     	    this.ei1=ei1;
	     	    this.gd1=gd1;
	     	    this.gi1=gi1;
	     	    this.nt1=nt1;
	     	    this.sp1=sp1;
	     	    
	     	    this.ed2=ed2;
	     	    this.ei2=ei2;
	     	    this.gd2=gd2;
	     	    this.gi2=gi2;
	     	    this.nt2=nt2;
	     	    this.sp2=sp2;
	        	
	        	sessionCountInt=Integer.parseInt(sessionCount);
	        	homePath=System.getProperty("user.dir");
	        	System.out.println("homePath" +System.getProperty("user.dir"));
	   		
	        	if(level.equals("2")) {
	        		levelUrl="https://devsso.secure.fedex.com/L2/PRSApps";
	        	}
	        	else if (level.equals("3")){
	        		levelUrl="https://testsso.secure.fedex.com/L3/PRSApps";
	        	}
	        
	    	
	    	if (source.equals("excel")) {
	    		
	    		this.filepath=filepath;	
        		excelVar = new excel(filepath);
	    		excelVar.setUpExcelWorkbook();
	    		excelVar.setUpExcelSheet(0);
	    		excelVar.setRowCountAutomatically(0);
	    		excelVar.setColCountAutomatically(0);
		    	rowCount=excelVar.getRowCount();
		    	colCount=excelVar.getColCount()-1;
		    	
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
	    	
	    	
	    	
	    	
	    	System.out.println(totalMod);
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
		}
		
		
		
		
		public void runDbQuery() {
			Connection GTMcon=null;
			Statement stmt = null;
			ResultSet rs = null;
			ResultSetMetaData rsmd=null;
			//Change to L3
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
			
	    	String databaseSqlCount="select count(*) as total from (select * from rerate_master where trk_no1 is not null and acct2 is null union all select * from rerate_master where trk_no1 is not null and trk_no2 is not null and acct2 is not null)";
	    	String databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,ACCT1,	ACCT2,	TRK_NO1,	TRK_NO2	,INVOICE_NBR_1,	INV_NO2,	SERVICE1,	SERVICE2,	REQUEST_TYPE,	ACCT_TYPE,	ACCNAME from (select * from rerate_master where trk_no1 is not null and acct2 is null union all select * from rerate_master where trk_no1 is not null and trk_no2 is not null and acct2 is not null)";
	    	
	    	
	    	if (allCheckBox.equals("false")) {
	    		databaseSqlCount+="where ";
	    		databaseSqlQuery+="where ";
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
	    	
	    	databaseSqlCount+="and service1 in (";
    		databaseSqlQuery+="and service1 in (";
	    	
    	
    		
	    	if(ed1.equals("true")) { 
	    		databaseSqlCount+="'Express Domestic',";
	    		databaseSqlQuery+="'Express Domestic',";
	    		
	    	}
    		
	    	if(ei1.equals("true")) { 
	    		databaseSqlCount+="'Express International',";
	    		databaseSqlQuery+="'Express International',";
	    	}
	    	if(gd1.equals("true")) { 
	    		databaseSqlCount+="'Ground Domestic',";
	    		databaseSqlQuery+="'Ground Domestic',";
	    	}
	    	if(gi1.equals("true")) {
	    		databaseSqlCount+="'Ground International',";
	    		databaseSqlQuery+="'Ground International',";
	    	}
	    	if(nt1.equals("true")) { 
	    		databaseSqlCount+="'NT',";
	    		databaseSqlQuery+="'NT',";
	    	}
	    	if(sp1.equals("true")) {
	    		databaseSqlCount+="'SmartPost',";
	    		databaseSqlQuery+="'SmartPost',";
	    	}
	    	
	    	databaseSqlCount+=") ";
	    	databaseSqlQuery+=") ";

	    	if (ed2.equals("true") || ei2.equals("true") ||gd2.equals("true") ||gi2.equals("true") ||nt2.equals("true") ||sp2.equals("true") ) {
	    		databaseSqlCount+="and service2 in (";
	    		databaseSqlQuery+="and service2 in (";
		    	
	    		
	    	if(ed2.equals("true")) { 
	    		databaseSqlCount+="'Express Domestic',";
	    		databaseSqlQuery+="'Express Domestic',";
	    		
	    	}
    		
	    	if(ei2.equals("true")) { 
	    		databaseSqlCount+="'Express International',";
	    		databaseSqlQuery+="'Express International',";
	    	}
	    	if(gd2.equals("true")) { 
	    		databaseSqlCount+="'Ground Domestic',";
	    		databaseSqlQuery+="'Ground Domestic',";
	    	}
	    	if(gi2.equals("true")) {
	    		databaseSqlCount+="'Ground International',";
	    		databaseSqlQuery+="'Ground International',";
	    	}
	    	if(nt2.equals("true")) { 
	    		databaseSqlCount+="'NT',";
	    		databaseSqlQuery+="'NT',";
	    	}
	    	if(sp2.equals("true")) {
	    		databaseSqlCount+="'SmartPost',";
	    		databaseSqlQuery+="'SmartPost',";
	    	}
	    	databaseSqlCount+=") ";
	    	databaseSqlQuery+=") ";
		}
	    	
	    	 databaseSqlCount = databaseSqlQuery.replace(",)",")");
	    	 databaseSqlQuery = databaseSqlQuery.replace(",)",")");
	    	 
	    	
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
	    
	    

	    
	  @Test(dataProvider="data-provider1")
	  public void testMethod(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name,int testCounter) {
	   // @Test
	  //  public void testMethod1() {
		  try {
			  
			  driver1.quit();
			  driver1.close();
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
  	    
  	 

			System.out.println("Hello 1");
			doWork(driver1,wait1,CEDropDown1,alert1,r1,cc11,cc12,comboBoxesHandling1,isChecked1,count1,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,testCounter);
			
  	   
	    }  
	    
	  
	  
	  
	  @Test(dataProvider="data-provider2")
	   public void testMethod2(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name,int testCounter) {
//@Test
//public void testMethod2() {
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
		//System.setProperty("webdriver.ie.driver", "C:\\Users\\FedExUser\\git\\MasterGUI\\drivers\\IEDriverServer.exe");
		driver2 =  new InternetExplorerDriver();
	}
	else if (browser.equals("2")) {
	 
		System.setProperty(chromeSetProperty,chromePath);
		driver2 = new ChromeDriver();
	}

  	    
 
			System.out.println("Hello 2");
			doWork(driver2,wait2,CEDropDown2,alert2,r2,cc21,cc22,comboBoxesHandling2,isChecked2,count2,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,testCounter);
			

	  	
	    }  
	  @Test(dataProvider="data-provider3")
	    public void testMethod3(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name,int testCounter) {
	//    @Test
	//    public void testMethod3() {
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
  	    
 
			System.out.println("Hello 3");
			doWork(driver3,wait3,CEDropDown3,alert3,r3,cc31,cc32,comboBoxesHandling3,isChecked3,count3,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,testCounter);
			

	    }  
	  
	  
	  
	  
	  
	  @Test(dataProvider="data-provider4")
	    public void testMethod4(String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name,int testCounter) {
	//    @Test
	//    public void testMethod4() {
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
  	    
  	

			System.out.println("Hello 4");
			doWork(driver4,wait4,CEDropDown4,alert4,r4,cc41,cc42,comboBoxesHandling4,isChecked4,count4,testInputNbr, tinCount, acct1, acct2, trkng1, trkng2, inv1, inv2, service1, service2, rerateType, acctType, name,testCounter);
			
	    }
	  
	  public void doWork(WebDriver driver, WebDriverWait wait,Select CEDropDown,Alert alert,Robot r,ArrayList<String> cc1,ArrayList<String> cc2, List<WebElement> comboBoxesHandling, Boolean isChecked,int count,String testInputNbr,String tinCount,String acct1,String acct2,String trkng1,String trkng2,String inv1,String inv2,String service1,String service2,String rerateType,String acctType,String name,int testCounter) {
	
		
		  
		  
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
				
		  
		  login(driver,wait);
		  firstPage(driver,CEDropDown,r,alert,name,acct1,acct2,acctType,d,d2,testCounter, testInputNbr, tinCount, trkng1);
		  secondPage(driver,comboBoxesHandling,service1, service2, rerateType, trkng1, trkng2, inv1, inv2, acct1,  acct2,testCounter, testInputNbr, tinCount);
		  thirdPage(driver,wait,testCounter,name,express,ground,combo, testInputNbr, tinCount, trkng1);

		  
		 
	  }
	  
	  
	  
	  
	  
	  public void login(WebDriver driver,WebDriverWait wait) {

		  try {

			  driver.get(levelUrl);
		  		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		  		wait = new WebDriverWait( driver,10);
		  		driver.manage().window().maximize();     
				
				
			//	/html/body/div/div/form/div/div[1]/fieldset/label[1]/input
				driver.findElement(By.id("username")).sendKeys("477023");
				driver.findElement(By.id("password")).sendKeys("477023");
				driver.findElement(By.id("submit")).click();

			  
				if (level.equals("2"))
				{                                                   
					driver.get("https://devsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
				}
				else if (level.equals("3"))
				{
					driver.get("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
				}
				
				try {
				isPresent = driver.findElements(By.xpath("//*[@id=\"form1\"]/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[1]/b/font")).size() > 0;
				//element = driver.findElement(By.xpath("//*[@id=\"form1\"]/table/tbody/tr[3]/td/table[3]/tbody/tr/td/table/tbody/tr[2]/td/table/tbody[1]/tr[1]/td[1]/b/font"));
				}
				
				catch(Exception e) {
					driver.quit();
					driver.get(levelUrl);	
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
					if (level.equals("2"))
					{                                                   
						driver.get("https://devsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
					}
					else if (level.equals("3"))
					{
						driver.get("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp?inbox_id=10");
					}
				}
				
			
		  }
		  catch(Exception e) {
			  System.out.println(e);
			  
		  }
				
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  public void firstPage(WebDriver driver,Select CEDropDown,Robot r,Alert alert,String name,String acct1,String acct2,String acctType,String d,String d2,int testCounter,String  testInputNbr,String  tinCount,String  trk) {
		  
		  
		  
		  
		  
		  
		  
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
				
				driver.findElement(By.name("beginDate")).sendKeys(startDateText);
				driver.findElement(By.name("RCDate")).sendKeys(endDateText);
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
			 Assert.fail("Failed during CE.");
			} 
		}
	}
}
 catch (Exception e) {
	 
	System.out.println(e);
	 if(source.equals("excel")) {
       	 writeToExcel(testCounter, 14,"Failed During First Page");
       	 }
  	String[] resultArray = new String[2];
  	resultArray[0]="fail";
  	resultArray[1]="Failed During First Page";
  	if(databaseDisabled.contentEquals("false")) {
  	writeToDB(testInputNbr,tinCount,trk,"ERROR",resultArray);
  	}
  	 Assert.fail("Failed during first page.");
	
 		}
 }

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

				
		public void secondPage(WebDriver driver, List<WebElement> comboBoxesHandling,String service1,String service2,String rerateType,String trkng1,String trkng2,String inv1,String inv2,String acct1, String acct2,int testCounter,String  testInputNbr,String  tinCount) {
			
			ArrayList<String>cc1= new ArrayList<String>();
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
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[13]/td[2]/font[1]/input")).sendKeys(endDateText);
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
			 if(source.equals("excel")) {
		       	 writeToExcel(testCounter, 14,"Failed During Second Page");
		       	 }
		  	String[] resultArray = new String[2];
		  	resultArray[0]="fail";
		  	resultArray[1]="Failed During Second Page";
		  	if(databaseDisabled.contentEquals("false")) {
		  	writeToDB(testInputNbr,tinCount,trkng1,"ERROR",resultArray);
		  	}
		  	 Assert.fail("Failed During Second Page");
		}
		}
	  
	  
	  
	  
	  
		
		
		
		
		
		public void thirdPage(WebDriver driver,WebDriverWait wait,int testCounter,String name,Boolean express,Boolean ground,Boolean combo,String testInputNbr,String tinCount,String trk) {
			int count=0;
			String requestId="";
			
			try {
		
			if (level.equals("2"))
			{
				driver.get("https://devsso.secure.fedex.com/L2/PRSApps/inbox/inbox_router.jsp?inbox_id=11");
			}
			else if (level.equals("3"))
			{
				driver.get("https://testsso.secure.fedex.com/L3/PRSApps/inbox/inbox_router.jsp?inbox_id=11");
			}
			
			
			  
	
			wait = new WebDriverWait( driver,60);
			
			
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
			
			
			
				Thread.sleep(1000);
			driver.findElement(By.name("btnAcceptTask")).click();
			Thread.sleep(1000);
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

		  	 if(source.equals("excel")) {
		  		writeToExcel(testCounter,13,requestId);
		       	 writeToExcel(testCounter, 14,"Processed");
		       	 }
		  	String[] resultArray = new String[2];
		  	resultArray[0]="pass";
		  	resultArray[1]="processed";
		  	if(databaseDisabled.contentEquals("false")) {
		  	writeToDB(testInputNbr,tinCount,trk,requestId,resultArray);
		  	}
		    Assert.assertTrue(true,"Test Case Completed.");
		       	 	
		       	 return;
		  	  
		  	  
			
		}
	
		catch (NoSuchElementException e)
		{
			System.out.println(e);
			
			if(url1.contentEquals("https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp")||url2.contentEquals("https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp"))
			{
				
				System.out.println("NoSuchElementException "+e);
	
				
				
				 if(source.equals("excel")) {
			       	 writeToExcel(testCounter, 14,"NoSuchElementException on third page");
			       	 }
			  	String[] resultArray = new String[2];
			  	resultArray[0]="fail";
			  	resultArray[1]="Try This Manually";
			  	if(databaseDisabled.contentEquals("false")) {
			  	writeToDB(testInputNbr,tinCount,trk,requestId,resultArray);
			  	}
			
			}
			else
			{
				
				System.out.println("NoSuchElementException "+e);
			
				 if(source.equals("excel")) {
			       	 writeToExcel(testCounter, 14,"NoSuchElementException on third page");
			       	 }
			  	String[] resultArray = new String[2];
			  	resultArray[0]="fail";
			  	resultArray[1]="NoSuchElementException on third page";
			  	if(databaseDisabled.contentEquals("false")) {
			  	writeToDB(testInputNbr,tinCount,trk,requestId,resultArray);
			  	}
			}
			 Assert.fail("Failed During Third Page");
		}
		catch (WebDriverException h)
		{
		
			System.out.println("WebDriverException "+h);
	
			 if(source.equals("excel")) {
		       	 writeToExcel(testCounter, 14,"Try This Manually");
		       	 }
		  	String[] resultArray = new String[2];
		  	resultArray[0]="fail";
		  	resultArray[1]="WebDriverException on third page";
		  	if(databaseDisabled.contentEquals("false")) {
		  	writeToDB(testInputNbr,tinCount,trk,requestId,resultArray);
		  	}
		  	 Assert.fail("Failed During Third Page");
		}

		catch(NullPointerException k)
		{
			System.out.println("STEPHEN "+k);
			 if(source.equals("excel")) {
		       	 writeToExcel(testCounter, 14,"NullPointerException on third page");
		       	 }
		  	String[] resultArray = new String[2];
		  	resultArray[0]="fail";
		  	resultArray[1]="NullPointerException on third page";
		  	if(databaseDisabled.contentEquals("false")) {
		  	writeToDB(testInputNbr,tinCount,trk,requestId,resultArray);
		  	}
		  	 Assert.fail("Failed During Third Page");
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("InterruptedException "+e);
			 if(source.equals("excel")) {
		       	 writeToExcel(testCounter, 14,"InterruptedException on third page");
		       	 }
		  	String[] resultArray = new String[2];
		  	resultArray[0]="fail";
		  	resultArray[1]="InterruptedException on third page";
		  	if(databaseDisabled.contentEquals("false")) {
		  	writeToDB(testInputNbr,tinCount,trk,requestId,resultArray);
		  	}
		  	 Assert.fail("Failed During Third Page");
		}
			
		}
		
		
	  
	  
	  
	  
	  
	  
	public synchronized int getRerateId(WebDriver driver, String name,int testCounter) {
		int count;
		String requestId;
		//System.out.println()
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]")).getText();
		count = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]")).size();
		requestId = driver.findElements(By.xpath("//a[contains(text(),'"+name+"')]/parent::font/parent::td/parent::tr/td[3]/font")).get(count-1).getText();
		 if(source.equals("excel")) {
		writeToExcel(testCounter,13,requestId);
		 }
		
		return count;
		
	}
	  
	
	
	
	
	 public synchronized void writeToDB(String testInputNbr,String tinCount,String trk,String rerateRequest, String[] resultArray) {
	    	Connection GTMcon=null;
	    //	String rerateRequestString = Integer.toString(rerateRequest);
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
	    	stmt=GTMcon.prepareStatement("insert into rerate_results (test_input_nbr,tin_count,trkngnbr,request_id,result,description) values (?,?,?,?,?,?)");  
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trk);  
			stmt.setString(4,rerateRequest); 
			stmt.setString(5,resultArray[0]);  
			stmt.setString(6,resultArray[1]);  
			stmt.executeUpdate();
	    	}
	    	catch(Exception e) {
	    		System.out.println(e);
	    	}
			
	    	
	    	
	    	try {
			//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
			
	    		System.out.println("update rerate_results set result="+resultArray[0]+",description="+resultArray[1]+" where trkngnbr="+trk);
	    		stmt=GTMcon.prepareStatement("update rerate_results set result=?,description=?,REQUEST_ID=? where trkngnbr=?");  
			
			stmt.setString(1,resultArray[0]);  
			stmt.setString(2,resultArray[1]); 
			stmt.setString(3,rerateRequest); 
			stmt.setString(4,trk); 
			stmt.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}

	    	
	    }
	  
	public synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
		
		excelVar.setCellData(rowCountExcel, colCountExcel, outputString);
		excelVar.writeCellData();
	}
	   
}










 


