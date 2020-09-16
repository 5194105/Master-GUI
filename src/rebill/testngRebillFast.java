package rebill;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
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

public class testngRebillFast {

	
	
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
	
	String headless;
	
	ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
	String[][] allData;
	config c;
	int waitTime;
	
	
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","domesticCheckBox","internationalCheckBox","expressCheckBox","groundCheckBox","sessionCount","customString","customCheckBox","databaseDisabled","headless"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String domesticCheckBox,String internationalCheckBox,String expressCheckBox,String groundCheckBox,String sessionCount,String customString,String customCheckBox,String databaseDisabled,String headless) {
	

		try {
	importData id = new importData();
	c=id.getConfig();
	waitTime=Integer.parseInt(c.getRebillSecondTimeout());
	
		//Kill all running chromedrivers leftover from previous sessions
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
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
	        	this.headless=headless;
       
    	
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
    		levelUrl=c.getRebillL2Url();		
    	}
    	else if (level.equals("3"))
    	{
    		levelUrl=c.getRebillL3Url();	   	
    	}
		}
		catch(Exception e) {
			System.out.println(e);
		}
    	
	}
	
	
	
	
	public void runDbQuery() {
		try {
		Connection GTMcon=c.getGtmRevToolsConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd=null;

    	String databaseSqlCount="select count(*) as total from rebill_regression ";
    	String databaseSqlQuery="select result, description, test_input_nbr, tin_count, trkngnbr, reason_code, rebill_acct,invoice_nbr_1, invoice_nbr_2, mig, region,  login,   password,  rs_Type, company, worktype, ORIGIN_LOC,DEST_LOC,DIM_VOL,SHIPPER_REF,RECP_ADDRESS,SHIPPER_ADDRESS,ACC_NBR_DEL_STATUS,SVC_BASE, CREDIT_CARD_DTL,PRE_RATE_SCENARIOS,EXP_Pieces,EXP_ACTUAL_Weight,EXP_Adj_Weight,CREDIT_CARD_DTL from rebill_regression ";
    	
    	if (allCheckBox.equals("true")) {
    		databaseSqlCount+="where trkngnbr is not null";
    		databaseSqlQuery+="where trkngnbr is not null ";
    	}
    	
    	System.out.println(customCheckBox);
    	System.out.println(customString);
    	
    	if (customCheckBox.equals("false")) {
    	
    	if (allCheckBox.equals("false")) {
    		databaseSqlCount+="where ";
    		databaseSqlQuery+="where ";
    	
    	
    	
    	
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
    	if (domesticCheckBox.equals("true") && internationalCheckBox.equals("false")) {
    		databaseSqlCount+="and rs_type='DM' ";
    		databaseSqlQuery+="and rs_type='DM' ";
    	}
    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("false")) {
    		databaseSqlCount+="and rs_type='IL' ";
    		databaseSqlQuery+="and rs_type='IL' ";
    	}
    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("true")) {
    		databaseSqlCount+="and rs_type in ('DM','IL')";
    		databaseSqlQuery+="and rs_type in ('DM','IL')";
    	}
    	
    	if (expressCheckBox.equals("true") && groundCheckBox.equals("false")) {
    		databaseSqlCount+="and company='EP' ";
    		databaseSqlQuery+="and company='EP' ";
    	}
    	if (groundCheckBox.equals("true") && expressCheckBox.equals("false")) {
    		databaseSqlCount+="and company='GD' ";
    		databaseSqlQuery+="and company='GD' ";
    	}
    	
    	if (groundCheckBox.equals("true") && expressCheckBox.equals("true")) {
    		databaseSqlCount+="and company in ('GD','EP') ";
    		databaseSqlQuery+="and company in ('GD','EP') ";
    	}
    		}
    			}
    	else if (customCheckBox.equals("true")){
    		databaseSqlCount+="where trkngnbr is not null and "+customString;
    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
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
    	GTMcon.close();
    	stmt.close();
		rs.close();
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
       	 return;
  	  
  	  			}
    		}
  	  }
    
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
    	if(headless.equals("true")) {
    		ChromeOptions options = new ChromeOptions();
    	    options.addArguments("headless");
    	    options.addArguments("window-size=1200x600");
    		driver1 = new ChromeDriver(options);
    	}
    	else {
    		driver1 = new ChromeDriver();
    	}
    		//driver1 = new ChromeDriver();
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
        	if(headless.equals("true")) {
        		ChromeOptions options = new ChromeOptions();
        	    options.addArguments("headless");
        	    options.addArguments("window-size=1200x600");
        		driver2 = new ChromeDriver(options);
        	}
        	else {
        		driver2 = new ChromeDriver();
        	}
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
        	if(headless.equals("true")) {
        		ChromeOptions options = new ChromeOptions();
        	    options.addArguments("headless");
        	    options.addArguments("window-size=1200x600");
        		driver3 = new ChromeDriver(options);
        	}
        	else {
        		driver3 = new ChromeDriver();
        	}
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
        	if(headless.equals("true")) {
        		ChromeOptions options = new ChromeOptions();
        	    options.addArguments("headless");
        	    options.addArguments("window-size=1200x600");
        		driver4 = new ChromeDriver(options);
        	}
        	else {
        		driver4 = new ChromeDriver();
        	}
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
		    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,waitTime);
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
    	WebElement element=null;
    	JavascriptExecutor js= (JavascriptExecutor) driver;
    	
    	int packageCounter=0;
    	Boolean exist;
    	WebElement scrollElement;
    	
    	wait=new WebDriverWait(driver,20);
    	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
    
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
    	
    	
 
    	
    	
    	
    	//Getting all the charge codes..
    	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
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
     	
    	//Click on all Charge Codes
    	 try {
    		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
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
     
	       
		         /*
		         //Setting up the reasonCode Dropdown.
	         Select  reasonCodeDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select")));
	       
	        
	         List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select"));
	    	 int counter=0;
	         for (WebElement option : options) {
	        	 
	    		 System.out.println(counter +":" +option.getText());
	    		 counter++;
	             }
	       
	         
	         */
		         
		         
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
    	     if(!preRateScenarios.equals("")) {
         try {
        	 
    	     driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[9]/label[2]/span[1]")).click();
    	     Thread.sleep(5000);
    	     driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();
     		 Thread.sleep(2000);
    	     driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[2]/label[2]/span")).click();
    	     Thread.sleep(2000);
    	   
    	    /* 
    	     Actions actions = new Actions(driver);
    	     actions.moveToElement(driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[3]/div")));
    	     actions.click();
    	     actions.sendKeys("100");
    	     actions.build().perform();
    	   
    	   */
    	  WebElement elementJS=  driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[3]/div"));
    	  elementJS.click();
    	  elementJS.click();
    	  System.out.println("plz");
    	  js.executeScript("arguments[0].click()", elementJS);
    	  js.executeScript("arguments[0].click()", elementJS);
    	  
    	  
    	 // js.executeScript("arguments[0].setAttribute('value', arguments[1])", elementJS, "100");
    	 //	js.executeScript("arguments[0].setAttribute('value', arguments[1])", elementJS, "100");
    	   //  driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[3]/div")).sendKeys("100");
    	     							 //  /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[3]/div	
    	     							   ///html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[1]/div
       System.out.println("plz");
         }
         catch(Exception e) {
        	 System.out.println(e);
         }
    	     }
    	     
    	     
    	     
    	     else {
    	 /*
    	 *****************************************************************************
    	 *
    	 *Getting to phone detail screen
    	 *
    	 *
    	 ****************************************************************************
    	 */
        
        
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    	 
    	    wait=new WebDriverWait(driver,1); 
    	    driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

         	try {
         		//tHIS BUTTON IS BUGGY!!!! click many times!!!!!!!!
         		 Thread.sleep(5000);
         		 driver.findElement(By.xpath("//*[@id=\"viewInvBtn\"]")).click();         	
         		}
         	catch(Exception e0) {
         		System.out.println("Clould not click after dropdown");
         		
         	}
         		
         	
         	
         	
         	
         	counter1=0;
         	while(counter1>10) {
         	try { 
         		counter1++;
         		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")));
                break; 
         }
         	catch(Exception e1) {
	
         		System.out.println("Could Not Click Rebill After Action Code");
         		 try {
         			 
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
         	   			 Assert.fail("Trying To Rebill A Partial Amount");
         		}
         			
         				 if (tempError.indexOf("interline")==-1) {
         				 System.out.println(tempError);
         				 if(source.equals("excel")) {
         	               	 writeToExcel(rowNumber, 0,"fail");
         	               	 writeToExcel(rowNumber, 1,"interline acct");
         	               	 }
         	   				 if(databaseDisabled.equals("false")) {
                 	   			 String[] resultArray = new String[2];
                 	   			 	resultArray[0]="fail";
                 	   				resultArray[1]="interline acct";
                 	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                                	 }
         	   			 Assert.fail("interline acct");
         		} 
         				 
         				 if (tempError.indexOf("specialist")==-1) {
             				 System.out.println(tempError);
             				 if(source.equals("excel")) {
             	               	 writeToExcel(rowNumber, 0,"fail");
             	               	 writeToExcel(rowNumber, 1,"specialist error");
             	               	 }
             	   				 if(databaseDisabled.equals("false")) {
                     	   			 String[] resultArray = new String[2];
                     	   			 	resultArray[0]="fail";
                     	   				resultArray[1]="specialist error";
                     	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                                    	 }
             	   			 Assert.fail("specialist error");
             		} 
 
         		 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
         		 System.out.println("Found Pop Up");
         	    
  
         			 
         			
         		 }
         		 catch(Exception e) {
         			 System.out.println("Could not move on past dropdown details");
         			 }
         		 }
         	}
         	if(counter1>=10) {
         		 if(source.equals("excel")) {
                	 writeToExcel(rowNumber, 0,"fail");
                	 writeToExcel(rowNumber, 1,"Could Not go to phone detail screen");
                	return;
                	 }
    				 if(databaseDisabled.equals("false")) {
    	   			 String[] resultArray = new String[2];
    	   			 	resultArray[0]="fail";
    	   				resultArray[1]="Could Not go to phone detail screen";
    	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
    	   				
                 	 } 
         		Assert.fail("Could Not go to phone detail screen");
         	}
         	
         	
         	
         	
         	
         	
         	
         	
         	
         	
         	
         	wait=new WebDriverWait(driver,waitTime); 
     	    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
	
         	
         		 
         		 /*
             	 *****************************************************************************
             	 *
             	 *Getting to phone detail
             	 *
             	 *
             	 ****************************************************************************
             	 */
         		 
         		 System.out.println("Phone Details");
      //   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
             try{
            	 //Click on rebill RPI Complete, Phone, and Continue
                  if (login.equals("5194105")|| login.equals("584168")){
                	  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
                  }
              
                  
               Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
               contactMethodDropDown.selectByValue("phone");  
               Thread.sleep(1500);
         	   driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[3]/button[1]")).click();
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
         	   
         	
             wait=new WebDriverWait(driver,1);
             driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
             counter1=0;
         	  
             
             while(counter1<=10) {   
         	   counter1++;
         	   try {
         	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
             	break;
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
         	   					}
         	   				catch(Exception ee) {
         	   				 System.out.println(ee+"Could Not Get to Rebill Screen");
         	   				
         	   				}
         	   			}
             		}
             if (counter1>=10){
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
 	   	 Assert.fail("Could Not Get to Rebill Screen");
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
         	   			
         	   			
         	   			
            
              
              
         
    	     wait=new WebDriverWait(driver,waitTime);
    	     driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
          
             try{    
            	
             switch (reasonCode){
             		case "RRA" :
                       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reciptacct_number\"]")));
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).clear();
                       driver.findElement(By.xpath("//*[@id=\"reciptacct_number\"]")).sendKeys(rebillAccount);
                       break;
                    case "RSA" :
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
             
             
             
             if(source.equals("excel")) {
            	 writeToExcel(rowNumber, 0,"fail");
            	 writeToExcel(rowNumber, 1,"Made it to the end test");
            	return;
            	 }
				 if(databaseDisabled.equals("false")) {
	   			 String[] resultArray = new String[2];
	   			 	resultArray[0]="fail";
	   				resultArray[1]="Made it to the end test";
	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
	   				 System.out.println("Made it to the end.");
	   			  Assert.fail("Made it to the end.");
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
			GTMcon = c.getGtmRevToolsConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		stmt.close();
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
		stmt.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
    	try {
			GTMcon.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public String[] validateResults(String trk) {
    
    	Connection con = null;
    	String[] resultArray = new String[2];
    	
    	try {
    	
    		if (level.equals("2")){
    			 
       		     con=c.getEraL2DbConnection();
       	 }
       	 else if (level.equals("3")){
       		 	
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
    	       try {
				con.close();
				stmt.close();
	    	    rs.close();
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
    

