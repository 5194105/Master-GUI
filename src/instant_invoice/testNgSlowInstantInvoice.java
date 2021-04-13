package instant_invoice;



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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import configuration.excel;
import configuration.importData;

public class testNgSlowInstantInvoice {

	
	
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
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox ,String sessionCount,String customString,String customCheckBox,String databaseDisabled) {
		importData id = new importData();
		c =id.getConfig();

		
		//Kill all running chromedrivers leftover from previous sessions
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
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
	        	this.sessionCount=sessionCount;
	        	sessionCountInt=Integer.parseInt(sessionCount);
	        	this.customString=customString;
	        	this.customCheckBox=customCheckBox;
	        	this.databaseDisabled=databaseDisabled;
        
       
    	
    	if(source.equals("excel")) {
	    	excelVar.setUpExcelWorkbook();
	    	excelVar.setUpExcelSheet(0);
	    	excelVar.setRowCountAutomatically(2);
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
    		levelUrl="https://testsso.secure.fedex.com/l2/instant-invoicing";
    		
    	}
    	else if (level.equals("3"))
    	{
    		levelUrl="https://testsso.secure.fedex.com/l3/instant-invoicing";
    	
    	}
       
    	
	}
	
	
	
	
	public void runDbQuery() {
		try {
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
		
    	String databaseSqlCount="select count(*) as total from instant_invoice_view ";
    	
    
    	String databaseSqlQuery="select TEST_INPUT_NBR	,TRKNGNBR	,PAYOR_ACCT_NBR,	ITEM_PRCS_CD	,INSTNT_INV_FLG from instant_invoice_view ";
    	
    	if (allCheckBox.equals("true")) {
    		databaseSqlCount+="where trkngnbr is not null and instnt_inv_flg is null ";
    		databaseSqlQuery+="where trkngnbr is not null and instnt_inv_flg is null";
    	}
    	
    	System.out.println(customCheckBox);
    	System.out.println(customString);
    	
    	if (customCheckBox.equals("false")) {
    	
    	if (allCheckBox.equals("false")) {
    		databaseSqlCount+="where  ";
    		databaseSqlQuery+="where  ";

    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
    		databaseSqlCount+="(instnt_inv_flg is null or instnt_inv_flg ='fail') ";
    		databaseSqlQuery+="(instnt_inv_flg is null or instnt_inv_flg ='fail') ";
    	}
    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
    		databaseSqlCount+="instnt_inv_flg is null ";
    		databaseSqlQuery+="instnt_inv_flg is null ";
    	}
    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
    		databaseSqlCount+="instnt_inv_flg ='fail' ";
    		databaseSqlQuery+="instnt_inv_flg ='fail' ";
    	}
    	
    	}
    	}
    	else if (customCheckBox.equals("true")){
    		databaseSqlCount+="where (instnt_inv_flg is null or instnt_inv_flg ='fail')  and "+customString;
    		databaseSqlQuery+="where (instnt_inv_flg is null or instnt_inv_flg ='fail')  and "+customString;
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
    	catch(Exception ee) {
    		System.out.println(ee);
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
    public void testMethod1(String testInputNbr, String trk ,String payorAcctNbr,String itemPrcsCd,String instntInvFlg,int rowNumber) {
     
    	
    	
    	System.out.println("Instance: 1");
    	
    	
    	//Will Check if Trk is already successful;
  	  try {
    	if (databaseDisabled.equals("false")) {
    
  		  String[] resultArray = validateResults(trk);
  	  if ( resultArray[0].equals("pass")){
       	 if(source.equals("excel")) {
       	 writeToExcel(rowNumber, 5,"pass");
       	 writeToExcel(rowNumber, 6,"completed");
       	
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
    	
    	
    	
    	String login="5194105";
    	String password="5194105";
    	wait1 = new WebDriverWait(driver1,20);
	    login(driver1,wait1,login,password);
	  
	    doInstantInvoice(driver1,wait1,  testInputNbr,  trk , payorAcctNbr, itemPrcsCd, instntInvFlg, rowNumber,1);
	    
    
    
    }
   
    @Test(dataProvider="data-provider2",retryAnalyzer = Retry.class)
    public void testMethod2( String testInputNbr, String trk ,String payorAcctNbr,String itemPrcsCd,String instntInvFlg,int rowNumber ) {
     
    	System.out.println("Instance: 2");
    	readTrk(trk);
    	
    	//Will Check if Trk is already successful;
  	 
  	 try {
     	if (databaseDisabled.equals("false")) {
     
   		  String[] resultArray = validateResults(trk);
   	  if ( resultArray[0].equals("pass")){
        	 if(source.equals("excel")) {
        	 writeToExcel(rowNumber, 5,"pass");
        	 writeToExcel(rowNumber, 6,"completed");
        	
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
    	String login="5194105";
    	String password="5194105";
    	 wait2 = new WebDriverWait(driver2,20);
    	login(driver2,wait2,login,password);
	    doInstantInvoice(driver2,wait2,  testInputNbr,  trk , payorAcctNbr, itemPrcsCd, instntInvFlg, rowNumber,2);
	    
    
    
    }
    @Test(dataProvider="data-provider3",retryAnalyzer = Retry.class)
    public void testMethod3( String testInputNbr, String trk ,String payorAcctNbr,String itemPrcsCd,String instntInvFlg,int rowNumber) {
    	System.out.println("Instance: 3");
    	readTrk(trk);
    	
    	
    	//Will Check if Trk is already successful;
    	 try {
    	    	if (databaseDisabled.equals("false")) {
    	    
    	  		  String[] resultArray = validateResults(trk);
    	  	  if ( resultArray[0].equals("pass")){
    	       	 if(source.equals("excel")) {
    	       	 writeToExcel(rowNumber, 5,"pass");
    	       	 writeToExcel(rowNumber, 6,"completed");
    	       	
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
    	String login="5194105";
    	String password="5194105";
    	 wait3 = new WebDriverWait(driver3,20);
    login(driver3,wait3,login,password);
    doInstantInvoice(driver3,wait3, testInputNbr,  trk , payorAcctNbr, itemPrcsCd, instntInvFlg, rowNumber,3);
	
    }
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider4",retryAnalyzer = Retry.class)
    public void testMethod4(String testInputNbr, String trk ,String payorAcctNbr,String itemPrcsCd,String instntInvFlg,int rowNumber) {
    	System.out.println("Instance: 4");
    	//Will Check if Trk is already successful;
    	readTrk(trk);
    	
    	 try {
    	    	if (databaseDisabled.equals("false")) {
    	    
    	  		  String[] resultArray = validateResults(trk);
    	  	  if ( resultArray[0].equals("pass")){
    	       	 if(source.equals("excel")) {
    	       	 writeToExcel(rowNumber, 5,"pass");
    	       	 writeToExcel(rowNumber, 6,"completed");
    	       	
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
    	String login="5194105";
    	String password="5194105";
    	 wait4 = new WebDriverWait(driver4,20);
    login(driver4,wait4,login,password);
    doInstantInvoice(driver4,wait4, testInputNbr,  trk , payorAcctNbr, itemPrcsCd, instntInvFlg, rowNumber,4);
    
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void doInstantInvoice(WebDriver driver,WebDriverWait wait, String testInputNbr,  String trk , String payorAcctNbr,String itemPrcsCd,String instntInvFlg,int rowNumber,int instance) {
    	
    	System.out.println("Made it to Instant.");
    	
    	System.out.println("testInputNbr "+ testInputNbr);
    	System.out.println("trk "+ trk);
    	System.out.println("payorAcctNbr "+ payorAcctNbr);
    	System.out.println("itemPrcsCd "+ itemPrcsCd);
    	System.out.println("instntInvFlg "+ instntInvFlg);
    	
    	
    	
    	
    	
    	try {				
        	//  driver.navigate().refresh();
          	  driver.switchTo().frame("content");
        	//WebElement dateWidget = driver.findElement(By.id("iiForm:fromDateII_input"));
        	//WebElement dateWidget = driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[1]/td[2]/span"));
          	 Date date = new Date();
             SimpleDateFormat formatter = new SimpleDateFormat("dd");
             String AB = formatter.format(date);
             int tempInt=Integer.parseInt(AB)+8;
        	
        	//List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
        //	System.out.println("HERE");
        	
             driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[1]/td[1]/span/span[1]/span[1]/input")).sendKeys(payorAcctNbr);
         	driver.findElement(By.xpath("/html/body/form[1]/table[1]/tbody/tr[4]/td/div/div[2]/table/tbody/tr[2]/td[1]/span/span[1]/span[1]/input")).sendKeys(trk);
         	
         	
         	
            driver.findElement(By.xpath("//input[@id='iiForm:fromDateII_input']")).click();
            Select dropdown = new Select (driver.findElement(By.name("iiForm:fromDateII_input_sel_month")));   

            //Just Choosing Jan. Will try this ten times before it ends.
          int  attempts=0;
            while (attempts<10){
                try{
                    dropdown.selectByVisibleText("Jan");
                    driver.findElements(By.className("ui-state-default")).get(9).click();  
                    break;
                    }
                    catch (Exception eee){
                    System.out.println(eee);
                    }
                    attempts++;
                }
        	
        	
Thread.sleep(3000);

           
            
            //Getting the second date. Will try 10 times
            attempts=0;
            while (attempts<10){
                try{  
                  
                    driver.findElement(By.xpath("//*[@id=\"iiForm:toDateII_input\"]")).click();
                    break;
                }
                catch (Exception eee){
                    System.out.println(eee);
                }
                attempts++;
                }
            
            
            attempts=0;
            while (attempts<10){
            try {
            //Will grab the calender and every date in it.. by default it wil select current date.
           
            //Loop through each date.
            int count = driver.findElements(By.className("ui-state-default")).size();
            //Loops through the second date until hits date we want
        
                    Thread.sleep(1000);
                   
                    driver.findElements(By.className("ui-state-default")).get(tempInt).click();
                    break;
            }
                  catch (Exception eee){
                    System.out.println(eee);
                }
                attempts++;
            }
      
        }
        	catch(Exception e) {
        		
       		System.out.println(e);
       	}
    	System.out.println("STOP");
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	 try{
             //Click on search button
             driver.findElement(By.xpath("//button[@id='iiForm:search_button']")).click();

             //Will this messages displays then no record is found.
             
                 try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 if(driver.findElement(By.xpath("//*[@id='iiForm:messageId_msg']/span[2]")).isDisplayed())
                 {
                     //FAILED.. Will not progress any further.
                     System.out.println("No Record found");
                     return;
                 }
             }
             catch(NoSuchElementException a){
                     
              System.out.println(a);   
           
             }
             

             
             //If it has passed up to this point it will continue to keep going.
             
                  try{
                     //Will see if there is a row visible for instant invoice. 
                     //Find out if element is disabled or not. 
                	 Boolean enabled = driver.findElement(By.xpath("//*[@id=\"iiForm:instantInvoiceDynTable:instantInvoiceTable:0:_t47\"]")).isEnabled(); 
                     System.out.println(enabled);
                     //If checkbox is enabled it will return true

                     if (enabled == true){
                         //Passed. Will click on checkbox and click on instant invoice. The program will now leave the if (res==true) condidition.
                                                               
                         driver.findElement(By.xpath("//*[@id='iiForm:instantInvoiceDynTable:instantInvoiceTable:_t44']")).click();
                         Thread.sleep(2000);
                         driver.findElement(By.xpath("//*[@id=\"iiForm:instanceInvoice_button\"]")).click();
                         
                     }                               
                     //if Checkbox is false.
                     else{
                         //FAILED. Will set res as false to fail our trk.. maybe get the desc for failure
                         System.out.println("Disabled");
                         return;
                       
                         }
                     }
                     catch(Exception ee){
                            
                             System.out.println(ee);
                             System.out.println("Could not submit");
                             return;
                           
                         }
                  
                  
                  
                  String[] resultArray = validateResults(trk);
                  
                  
                  
                  if ( resultArray[0].equals("pass")){
                 	 if(source.equals("excel")) {
                 	 writeToExcel(rowNumber, 5,"pass");
                 	 writeToExcel(rowNumber, 6,"completed");
                 	 }
                 	 if(databaseDisabled.equals("false")) {
                      	 // writeToDB(testInputNbr,tinCount,trk,resultArray);
                      	 }
                 	 return;
                 	 
                  }
                  else if ( resultArray[0].equals("fail"))
                	  if(source.equals("excel")) {
                       	 writeToExcel(rowNumber, 5,"pass");
                       	 writeToExcel(rowNumber, 6,"completed");
                       	 }
                       	 if(databaseDisabled.equals("false")) {
                            	  writeToDB(testInputNbr,tinCount,trk,resultArray);
                            	 }
                       	 return;
                     }
                  
                 
     // INSTANT INVOICE BUTTON XPATH               
     //driver.findElement(By.xpath("//button[//*[@id='iiForm:instanceInvoice_button']")).click();
    	
  
        
    
    
    
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
    //	stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");  
    		stmt=GTMcon.prepareStatement("update gtm_rev_tools.instant_invoice set INSTANT_INVOICE_COMMENTS=? where test_input_nbr=?");  
    	
		stmt.setString(1,resultArray[1]);  
		stmt.setString(2,testInputNbr);  
		 
	
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
    			
       		     con=c.getOreL2DbConnection();
       	 }
       	 else if (level.equals("3")){
       		
       		 	con=c.getIoreL3DbConnection();
       	 	}
    	
    	}
    	catch(Exception e) {
    		
    		System.out.println("Could Not Get ORE DB Connections");
    		System.out.println(e);
    	}
    	

    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		stmt=con.prepareStatement("select * from INTL_EXPRS_ONLN_SCHEMA.intl_online_revenue_item a join INTL_EXPRS_ONLN_SCHEMA.intl_package b on a.ONLN_REV_ITEM_ID =b.ONLN_REV_ITEM_ID  where pkg_trkng_nbr=? and INSTNT_INV_FLG ='Y'");  
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
    			      resultArray[1]="Not Instant Invoice in IORE";
	    			    
    			}
    			   else{
    				  
  	    			      resultArray[0]="pass";
  	    			      resultArray[1]="completed";
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
    

