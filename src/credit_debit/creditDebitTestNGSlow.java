package credit_debit;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import rebill.Retry;
import rebill.rebillData;






public class creditDebitTestNGSlow {
	
	
	
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

	String creditCheckBox,debitCheckBox;
	
	
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","creditCheckBox","debitCheckBox","disputeCheckBox","resolveCreditCheckBox","sessionCount","customString","customCheckBox","databaseDisabled","headless"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String creditCheckBox,String debitCheckBox,String disputeCheckBox,String resolveCreditCheckBox,String sessionCount,String customString,String customCheckBox,String databaseDisabled,String headless) {
	

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
				
				this.creditCheckBox=creditCheckBox;
				this.debitCheckBox=debitCheckBox;
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
	int kounter=0;
	Connection GTMcon=c.getGtmRevToolsConnection();
	Statement stmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd=null;

	String databaseSqlCount="select count(*) as total from era_credit_debit ";
	String databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	CREDIT_FLG,	DEBIT_FLG,	DISPUTE_FLG,	RESOLVE_CREDIT_FLG,	WORKABLE ,REASON_CODE,	REASON_CATEGORY, ROOT_CAUSE from era_credit_debit " ;
	
		
	
	if (allCheckBox.equals("true")) {
		databaseSqlCount+="where trkngnbr is not null";
		databaseSqlQuery+="where trkngnbr is not null ";
	}
	
	System.out.println(customCheckBox);
	System.out.println(customString);
	
	if (customCheckBox.equals("false")) {
	
	if (allCheckBox.equals("false")) {
		databaseSqlCount+="where trkngnbr is not null and ";
		databaseSqlQuery+="where trkngnbr is not null and ";
	
	
	
	
	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
		databaseSqlCount+="(result is null or result ='fail') and (";
		databaseSqlQuery+="(result is null or result ='fail') and (";
	}
	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
		databaseSqlCount+="result is null and (";
		databaseSqlQuery+="result is null and (";
	}
	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
		databaseSqlCount+="result ='fail' and (";
		databaseSqlQuery+="result ='fail' and (";
	}
		
	/*
	if (creditCheckBox.equals("true")){
		databaseSqlCount+=" CREDIT_FLG='Y' or ";
		databaseSqlQuery+=" CREDIT_FLG='Y' or ";
		kounter++;
	}
	if (creditCheckBox.equals("true")){
		databaseSqlCount+=" DEBIT_FLG='Y' or ";
		databaseSqlQuery+=" DEBIT_FLG='Y' or ";
		kounter++;
	}
	if (creditCheckBox.equals("true")){
		databaseSqlCount+=" DISPUTE_FLG='Y' or ";
		databaseSqlQuery+=" DISPUTE_FLG='Y' or ";
		kounter++;
	}
	if (creditCheckBox.equals("true")){
		databaseSqlCount+=" RESOLVE_CREDIT_FLG='Y' ";
		databaseSqlQuery+=" RESOLVE_CREDIT_FLG='Y' ";
		
	}
	
	databaseSqlCount+=")";
	databaseSqlQuery+=")";
	if ( kounter<=2 ) {
		databaseSqlQuery = databaseSqlQuery.replace("or", "");
		databaseSqlCount = databaseSqlCount.replace("or", "");
	}

	*/
		}
		
			}
	else if (customCheckBox.equals("true")){
		databaseSqlCount+="where trkngnbr is not null and "+customString;
		databaseSqlQuery+="where trkngnbr is not null and "+customString;
	}
	
	System.out.println(databaseSqlCount);

   	try {
     		
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
public void testMethod1(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region ,String username ,String password,String creditFlg,String debitFlg,String disputeFlg, String resolveCreditFlg,String workable,String reasonCode,String reasonCategory,String rootCause,int rowNumber) throws InterruptedException {
 
	
	
	System.out.println(result);
	System.out.println(descripiton);
	System.out.println(testInputNbr);
	System.out.println(tinCount);
	System.out.println(trk);
	System.out.println(invoiceNbr1);
	System.out.println(invoiceNbr2);
	System.out.println(region);
	System.out.println(password);
	System.out.println(rowNumber);
	System.out.println(creditFlg);
	System.out.println(debitFlg);
	System.out.println(disputeFlg);
	System.out.println(resolveCreditFlg);
	System.out.println(workable);
	System.out.println(reasonCode);
	System.out.println(reasonCategory);
	System.out.println(rootCause);
	
	
	
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
    login(driver1,wait1,username,password);
    doCreditDebit(driver1,wait1, result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2,  region , username , password,  creditFlg, debitFlg, disputeFlg, resolveCreditFlg,workable,reasonCode,reasonCategory,rootCause,rowNumber,1);



}

@Test(dataProvider="data-provider2",retryAnalyzer = Retry.class)
public void testMethod2(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region ,String username ,String password,String creditFlg,String debitFlg,String disputeFlg, String resolveCreditFlg,String workable,String reasonCode,String reasonCategory,String rootCause,int rowNumber) {
 
	System.out.println("Instance: 2");
	
	
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
	login(driver2,wait2,username,password);
    try {
    //	doCreditDebit(driver2,wait2, result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2,  region , username , password,  creditFlg, debitFlg, disputeFlg, resolveCreditFlg,rowNumber,2);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    


}
@Test(dataProvider="data-provider3",retryAnalyzer = Retry.class)
public void testMethod3(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region ,String username ,String password,String creditFlg,String debitFlg,String disputeFlg, String resolveCreditFlg,String workable,String reasonCode,String reasonCategory,String rootCause,int rowNumber) {
	System.out.println("Instance: 3");
	
	
	
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
login(driver3,wait3,username,password);
try {
//	doCreditDebit(driver3,wait3, result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2,  region , username , password,  creditFlg, debitFlg, disputeFlg, resolveCreditFlg,rowNumber,3);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}







@Test(dataProvider="data-provider4",retryAnalyzer = Retry.class)
public void testMethod4(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region ,String username ,String password,String creditFlg,String debitFlg,String disputeFlg, String resolveCreditFlg,String workable,String reasonCode,String reasonCategory,String rootCause,int rowNumber) {
	System.out.println("Instance: 4");
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
login(driver4,wait4,username,password);
try {
	//doCreditDebit(driver4,wait4, result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2,  region , username , password,  creditFlg, debitFlg, disputeFlg, resolveCreditFlg,rowNumber,4);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
}



public synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
	
	excelVar.setCellData(rowCountExcel, colCountExcel, outputString);
	excelVar.writeCellData();
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
	stmt=GTMcon.prepareStatement("insert into era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_credit_debit) values (?,?,?,?,?,?)");  
	stmt.setString(1,testInputNbr);  
	stmt.setString(2,tinCount);  
	stmt.setString(3,trk);  
	stmt.setString(4,resultArray[0]);  
	stmt.setString(5,resultArray[1]);  
	stmt.setString(6,"Y");  
	stmt.executeUpdate();
	stmt.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
	
	
	
	try {
	//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
	stmt=GTMcon.prepareStatement("update era_results set result=?,description=?,era_credit_debit=? where trkngnbr=?");  
	stmt.setString(1,resultArray[0]);  
	stmt.setString(2,resultArray[1]); 
	stmt.setString(3,"Y"); 
	stmt.setString(4,trk); 
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


public String[] validateResults(String temp) {
	return null;
}




public void login(WebDriver driver,WebDriverWait wait,String username,String password) {
	
	try {
	    driver.get(levelUrl);
	    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,waitTime);
		driver.manage().window().maximize();
		driver.findElement(By.id("okta-signin-username")).sendKeys(username);
		driver.findElement(By.id("okta-signin-password")).sendKeys(password);
		driver.findElement(By.id("okta-signin-submit")).click();
	}
	catch(Exception e) {
		
		 Assert.fail("Could Not Login");
	}
	
}
	



public void doCreditDebit(WebDriver driver,WebDriverWait wait, String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2, String region ,String username ,String password,String creditFlg,String debitFlg,String disputeFlg,String resolveCreditFlg,String workable,String reasonCode,String reasonCategory,String rootCause,int rowNumber, int instanceNumber) {
							
	WebElement element=null;
	JavascriptExecutor js= (JavascriptExecutor) driver;
	
	int packageCounter=0;
	Boolean exist;
	WebElement scrollElement;
	
	wait=new WebDriverWait(driver,20);
	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);

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
	/*
	/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[1]/ul/li[2]/a
	 * 
	 */
	
	//*[@id="main-tabs"]/li[2]/a
	
 	//Try to Click Package Tab
	int counter1=0;
	String tempString1;
	driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
	while (counter1<10) {
	try {  
		counter1++;
		System.out.println("Trying to click package tab");
		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[2]/a"));
		js.executeScript("arguments[0].click()", element);    
		tempString1=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/form/div/div[6]/div[1]/label")).getText();
		System.out.println(tempString1);
		if(tempString1.equals("Action")) {
			System.out.println("Action");
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
    		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[2]/a"));
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
	
	System.out.println("STOP HERE");
	 try{
	  Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"pkgaction\"]")));
	if (creditFlg.equals("Y")) {
		 actionDropDown.selectByValue("CR");
	}
	if (debitFlg.equals("Y")) {
		 actionDropDown.selectByValue("DB");
	}
	if (disputeFlg.equals("Y")) {
		 actionDropDown.selectByValue("D");
	}
	if (resolveCreditFlg.equals("Y")) {
		 actionDropDown.selectByValue("RC");
	}
	    	 System.out.println("Inside getDetails");
	         //Getting Action Dropdown. Will RB everytime.
	  }
	  catch(Exception e) {
		  System.out.println(e);
	  }
	
	/*
	//*[@id="invAction"]
    try{
   	 System.out.println("Inside getDetails");
        //Getting Action Dropdown. Will RB everytime.
        Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"invAction\"]")));
   
   	List<WebElement> aaa= actionDropDown.getOptions();
   	for (WebElement we: aaa) {
 	   System.out.println("|||"+we.getText()+"|||");
   		if(we.getText().toLowerCase().contains(function.toLowerCase())) {
   			actionDropDown.selectByVisibleText(we.getText());
   			break;
   		}
   	}

}
catch(Exception e) {
	System.out.println(e);
	}
    
    
     driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/form/div/div[6]/div[7]/button")).click();
   
    
    


    
    
    
    
    
    
    
    
    
    
    
 	wait=new WebDriverWait(driver,waitTime); 
	    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);

 	
 		 
 		
 		 
 		 System.out.println("Phone Details");
//   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
     try{
    	 //Click on rebill RPI Complete, Phone, and Continue
          if (login.equals("5194105")|| login.equals("584168")){
        	//  driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div[2]/div[2]/label[1]")).click();
        	  driver.findElement(By.xpath("//*[@id=\"invoice-level\"]/div[2]/div/form/div/div[5]/div/div/div/div/div/div/div/div[2]/div[2]/label[1]")).click();
              
        	//*[@id="invoice-level"]/div[2]/div/form/div/div[5]/div/div/div/div/div/div/div/div[2]/div[2]/label[1]
        	  }
      
          
       Select contactMethodDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"rmrks\"]")));
       contactMethodDropDown.selectByValue("phone");  
       Thread.sleep(1500);
 	   driver.findElement(By.xpath("//*[@id=\"invoice-level\"]/div[2]/div/form/div/div[5]/div/div/div/div/div/div/div/div[2]/div[2]/div/button[1]")).click();
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
    */
}
}


















