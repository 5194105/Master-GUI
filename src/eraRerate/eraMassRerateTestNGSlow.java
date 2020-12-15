package eraRerate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
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

public class eraMassRerateTestNGSlow {

	
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
	String eraWorkable;
	
	
	
	
	
	public void runDbQuery() {
		try {
		Connection GTMcon=c.getGtmRevToolsConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd=null;

    	String databaseSqlCount="select count(*) as total from era_rerate_mass ";
    	String databaseSqlQuery="select  result,  DESCRIPTION, test_Input_Nbr, tin_Count, trkngnbr, invoice_Nbr_1, invoice_Nbr_2, region, username , password,  rate_weight,wgt_type,length,height,width,dim_type, rerate_type, rs_Type ,company  , mass_rerate_combo from era_rerate_mass ";
    	
    	
   	     
    	
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
    	
    	if (eraWorkable.equals("true")) {
    		databaseSqlCount+="and workable='Y'";
    		databaseSqlQuery+="and workable='Y'";
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
            	 
        	}
        	catch(Exception e) {
        		System.out.println(e);
        	}
    //	GTMcon.close();
    //	stmt.close();
	//	rs.close();
		}
    	catch(Exception ee) {
    		System.out.println(ee);
    	}
		
	
	}
	
	  
	
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","sessionCount","customString","customCheckBox","databaseDisabled","headless","eraWorkable"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String sessionCount,String customString,String customCheckBox,String databaseDisabled,String headless,String eraWorkable) {
	

		try {
			importData id = new importData();
			c=id.getConfig();
			waitTime=Integer.parseInt(c.getEraRerateSecondTimeout());
	
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
	        	this.sessionCount=sessionCount;
	        	sessionCountInt=Integer.parseInt(sessionCount);
	        	this.customString=customString;
	        	this.customCheckBox=customCheckBox;
	        	this.databaseDisabled=databaseDisabled;
	        	this.headless=headless;
	        	this.eraWorkable=eraWorkable;
    	
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
	    public void testMethod1(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region,String username ,String password,String rate_weight,String wgt_type,String length,String height,String width,String dim_type,String rerate_type,String rsType ,String company ,String mass_rerate_combo,int rowNumber) {
	    	String databaseSqlQuery="select  result,  DESCRIPTION, test_Input_Nbr, tin_Count, trkngnbr, invoice_Nbr_1, invoice_Nbr_2, region, username , password,  rate_weight,wgt_type,length,height,width,dim_type rerate_type, rs_Type ,company  , mass_rerate_combo from era_rerate_mass ";
	    	
	    	System.out.println("Instance: 1");
	    	
	    	System.out.println(result+": "+result);
	    	System.out.println(descripiton+": "+descripiton);
	    	System.out.println(testInputNbr+": "+testInputNbr);
	    	System.out.println(tinCount+": "+tinCount);
	    	System.out.println(trk+": "+trk);
	    	System.out.println(invoiceNbr1+": "+invoiceNbr1);
	    	System.out.println(invoiceNbr2+": "+invoiceNbr2);
	    	System.out.println(region+": "+region);
	    	System.out.println(username+": "+username);
	    	System.out.println(password+": "+password);
	    	System.out.println(length+": "+length);
	    	System.out.println(rate_weight+": "+rate_weight);
	    	System.out.println(rerate_type+": "+rerate_type);
	    	System.out.println(rsType+": "+rsType);
	    	System.out.println(company+": "+company);	
	    	System.out.println(mass_rerate_combo+": "+mass_rerate_combo);
	    	System.out.println(rowNumber+": "+rowNumber);
	    	
	    	
	    /*
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
	  	  
	  	  */
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
		  
		    try {    
		        
		    	doMassRerate(driver1,wait1,  result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2, region , username , password,rate_weight,wgt_type,length,height,width,dim_type,rerate_type, rsType , company,mass_rerate_combo, rowNumber,1);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	    
	    
	    }
	    
	    @Test(dataProvider="data-provider2",retryAnalyzer = Retry.class)
	    public void testMethod2(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region,String username ,String password,String rate_weight,String wgt_type,String length,String height,String width,String dim_type,String rerate_type,String rsType ,String company ,String mass_rerate_combo,int rowNumber) {
	     
	    	System.out.println("Instance: 2");
	    	
	    	System.out.println(result);
	    	System.out.println(descripiton);
	    	System.out.println(testInputNbr);
	    	System.out.println(tinCount);
	    	System.out.println(trk);
	    	System.out.println(invoiceNbr1);
	    	System.out.println(invoiceNbr2);
	    	System.out.println(username);
	    	System.out.println(password);
	    	System.out.println(length);
	    	System.out.println(rate_weight);
	    	System.out.println(rerate_type);
	    	System.out.println(rsType);
	    	System.out.println(company);	
	    	System.out.println(mass_rerate_combo);
	    	System.out.println(rowNumber);
	    	
	    	
	    /*
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
	  	  
	  	  */
	    	try { 
	    		driver2.quit();
	    		driver2.close();
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
	        	driver2 = new FirefoxDriver(capabilities);
	    	}
	    	
	    	
	    	
	    	
	    	wait2 = new WebDriverWait(driver2,20);
		    login(driver2,wait2,username,password);
		  
		    try {    
		        
		    	doMassRerate(driver2,wait2,  result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2, region , username , password,rate_weight,wgt_type,length,height,width,dim_type,rerate_type, rsType , company,mass_rerate_combo, rowNumber,2);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	    
	    
	    }
	    
	    
	    @Test(dataProvider="data-provider3",retryAnalyzer = Retry.class)
	    public void testMethod3(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region,String username ,String password,String rate_weight,String wgt_type,String length,String height,String width,String dim_type,String rerate_type,String rsType ,String company ,String mass_rerate_combo,int rowNumber) {
	     
	    	System.out.println("Instance: 3");
	    	
	    	System.out.println(result);
	    	System.out.println(descripiton);
	    	System.out.println(testInputNbr);
	    	System.out.println(tinCount);
	    	System.out.println(trk);
	    	System.out.println(invoiceNbr1);
	    	System.out.println(invoiceNbr2);
	    	System.out.println(username);
	    	System.out.println(password);
	    	System.out.println(length);
	    	System.out.println(rate_weight);
	    	System.out.println(rerate_type);
	    	System.out.println(rsType);
	    	System.out.println(company);	
	    	System.out.println(mass_rerate_combo);
	    	System.out.println(rowNumber);
	    	
	    	
	    /*
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
	  	  
	  	  */
	    	try { 
	    		driver3.quit();
	    		driver3.close();
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
	        	driver3 = new FirefoxDriver(capabilities);
	    	}
	    	
	    	
	    	
	    	
	    	wait3 = new WebDriverWait(driver3,20);
		    login(driver3,wait3,username,password);
		  
		    try {    
		        
		    	doMassRerate(driver3,wait3,  result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2, region , username , password,rate_weight,wgt_type,length,height,width,dim_type,rerate_type, rsType , company,mass_rerate_combo, rowNumber,3);
					} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	    
	    
	    }
	   
	    @Test(dataProvider="data-provider4",retryAnalyzer = Retry.class)
	    public void testMethod4(String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region,String username ,String password,String rate_weight,String wgt_type,String length,String height,String width,String dim_type,String rerate_type,String rsType ,String company ,String mass_rerate_combo,int rowNumber) {
	     
	    	System.out.println("Instance: 4");
	    	
	    	System.out.println(result);
	    	System.out.println(descripiton);
	    	System.out.println(testInputNbr);
	    	System.out.println(tinCount);
	    	System.out.println(trk);
	    	System.out.println(invoiceNbr1);
	    	System.out.println(invoiceNbr2);
	    	System.out.println(username);
	    	System.out.println(password);
	    	System.out.println(length);
	    	System.out.println(rate_weight);
	    	System.out.println(rerate_type);
	    	System.out.println(rsType);
	    	System.out.println(company);	
	    	System.out.println(mass_rerate_combo);
	    	System.out.println(rowNumber);
	    	
	    	
	    /*
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
	  	  
	  	  */
	    	try { 
	    		driver4.quit();
	    		driver4.close();
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
	        	driver4 = new FirefoxDriver(capabilities);
	    	}
	    	
	    	
	    	
	    	
	    	wait4 = new WebDriverWait(driver4,20);
		    login(driver4,wait4,username,password);
		  
		    try {    
				doMassRerate(driver4,wait4, result,  descripiton, testInputNbr, tinCount, trk, invoiceNbr1, invoiceNbr2, region , username , password,rate_weight,wgt_type,length,height,width,dim_type,rerate_type, rsType , company,mass_rerate_combo, rowNumber,4);
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
				
				//	 /html/body/div[2]/div[2]/main/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/span/input					
				//driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/span/input")).sendKeys(login);
				//driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/form/div[1]/div[2]/div[2]/div[2]/span/input")).sendKeys(password);
				//driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/form/div[2]/input")).click();
				
				driver.findElement(By.id("okta-signin-username")).sendKeys(login);
				driver.findElement(By.id("okta-signin-password")).sendKeys(password);
				driver.findElement(By.id("okta-signin-submit")).click();
	    	}
	    	catch(Exception e) {
	    		System.out.println(e);
	    		 Assert.fail("Could Not Login");
	    	}
	    }
		
	    public void doMassRerate(WebDriver driver,WebDriverWait wait, String result, String descripiton,String testInputNbr,String tinCount,String trk,String invoiceNbr1,String invoiceNbr2,String region,String username ,String password,String rate_weight,String wgt_type,String length, String height, String width,String dim_type, String rerate_type,String rsType ,String company ,String mass_rerate_combo,int rowNumber, int instanceNumber) throws InterruptedException {


	    	if (rerate_type.equals("NA")) {
	    		 if(source.equals("excel")) {
	               //	 writeToExcel(rowNumber, 0,"fail");
	               	// writeToExcel(rowNumber, 1,"Prerate Code Not Added Yet");
	               	 }
	   				 if(databaseDisabled.equals("false")) {
     	   			 String[] resultArray = new String[2];
     	   			 	resultArray[0]="fail";
     	   				resultArray[1]="Not Info Info For Test Case";
     	   				 writeToDB(testInputNbr,tinCount,trk,resultArray);
                    	 }
    	return;	
	    	}
	    	
	   	   System.out.println("Inside of doMassRerate");
	    	System.out.println("result: "+result);
	    	System.out.println("descripiton: "+descripiton);
	    	System.out.println("testInputNbr: "+testInputNbr);
	    	System.out.println("tinCount: "+tinCount);
	    	System.out.println("trk: "+trk);
	    	System.out.println("invoiceNbr1: "+invoiceNbr1);
	    	System.out.println("invoiceNbr2: "+invoiceNbr2);
	    	System.out.println("region: "+region);
	    	System.out.println("username: "+username);
	    	System.out.println("password: "+password);
	    	
	    	System.out.println("rate_weight: "+rate_weight);
	    	System.out.println("wgt_type: "+wgt_type);
	    	System.out.println("length: "+length);
	    	System.out.println("height: "+height);
	    	System.out.println("width: "+width);
	    	System.out.println("dim_type: "+dim_type);
	    	System.out.println("rerate_type: "+rerate_type);
	    	System.out.println("rsType: "+rsType);
	    	System.out.println("company: "+company);	
	    	System.out.println("mass_rerate_combo: "+mass_rerate_combo);
	    	System.out.println("rowNumber: "+rowNumber);
	    
	    
	    	
	    	
	    	
	    	WebElement element=null;
	    	JavascriptExecutor js= (JavascriptExecutor) driver;
	    	
	    	int packageCounter=0;
	    	Boolean exist;
	    	WebElement scrollElement;
	    	
	    	wait=new WebDriverWait(driver,20);
	    
	    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    	try {
	    	//In order for clear button to be clickable need to scroll up
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        
	        
	       
	        int waittimer=3000;
	        Thread.sleep(waittimer);
	      //Will click on mass adjustments
	        driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[1]/ul/li[12]/a")).click();
	        Thread.sleep(waittimer);
	        //Will click on mass rerates
	        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[2]/button[3]")).click();
	        Thread.sleep(waittimer);
	        //Will type the trk number to text area
	        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(trk);
	        Thread.sleep(waittimer);
	        //Will click on go
	        driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[1]/button[1]")).click();
	        Thread.sleep(waittimer);
	        
	        //Will click on trk
	        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[6]/div[1]/div/div/div[1]/div/div[1]/div/div/div/div/div/div/div/div/div")).click();
	        Thread.sleep(waittimer);
	        String lowercaseRerateType=rerate_type.toLowerCase();
	        switch(lowercaseRerateType) {
	        
	      //Rating
	        case "rating" :
	        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[2]")).click();
	        break;
	        
	        		
	        //weight
	        case "weight" :
	        		
	        	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[3]")).click();
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[2]/div/div/input")).sendKeys(rate_weight);
	        		
	        		if (wgt_type.equals("LB")) {
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[3]/div[1]/div/label/span")).click();
	        		}
	        		if (wgt_type.equals("KG")) {
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[3]/div[2]/div/label/span")).click();
	        		}
	        		
	        		break;
	        
	        //Dims
	        case "dim" :
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[4]")).click();
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[1]/div/input")).sendKeys(length);
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[2]/div/input")).sendKeys(width);
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[3]/div/input")).sendKeys(height);
	        		
	        		if (dim_type.equals("IN")) {
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[4]/div[1]/div/label/span")).click();
	        		}
	        		if (dim_type.equals("CM")) {
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[4]/div[2]/div/label/span")).click();
	        		}
	        		break;
	        
    		//service
	        case "service" :
	        		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[5]")).click();
	        		
	        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[3]/div/div/div/div/div/select/optgroup[5]/option[2]")).click();
	        		
	        }
	        
	        Thread.sleep(waittimer);
	        System.out.println("RERATE");
	        //Click on rerate
	        driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[5]/div/div/button")).click();
        	
	        Thread.sleep(waittimer);
	    	String temp=driver.findElement(By.xpath(" /html/body/div[7]/div/div/div[1]/h3")).getText();;
	    	String requestID=temp.replaceAll("\\D+","");
	    	System.out.println( requestID);
	       
	    		String[] resultArray = new String[2];
			 	resultArray[0]="In Progress";
				resultArray[1]=requestID;
				writeToDB(testInputNbr,tinCount,trk,resultArray);
	        
	        
	    	} 
	    	catch(Exception e) {
	    		System.out.println("Failed on Entering Tracking Number");
	    		System.out.println("ERROR!!!!!!!!!");
	    		System.out.println(e);
	   		 if(source.equals("excel")) {
	          //	 writeToExcel(rowNumber, 0,"fail");
	          // 	 writeToExcel(rowNumber, 1,"Failed on Entering Tracking Number");
	           	 }
					 if(databaseDisabled.equals("false")) {
		   			 String[] resultArray = new String[2];
		   			 	resultArray[0]="fail";
		   				resultArray[1]="Failed on Entering Tracking Number";
		   				writeToDB(testInputNbr,tinCount,trk,resultArray);
	            	 }
	    		 Assert.fail("Failed on Entering Tracking Number");
	    		
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
	    	stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_mass_rerate) values (?,?,?,?,?,?)");  
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trk);  
			stmt.setString(4,resultArray[0]);  
			stmt.setString(5,resultArray[1]);  
			stmt.setString(6,"Y");  
			stmt.executeUpdate();
			//stmt.close();
	    	}
	    	catch(Exception e) {
	    		System.out.println(e);
	    	}
			
	    	
	    	
	    	try {
			//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
	    	stmt=GTMcon.prepareStatement("update era_results set result=?,description=? where trkngnbr=? and era_mass_rerate=?");  
			stmt.setString(1,resultArray[0]);  
			stmt.setString(2,resultArray[1]); 
			stmt.setString(3,trk); 
			stmt.setString(4,"Y"); 
			stmt.executeUpdate();
			//stmt.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	    	/*
	    	try {
				GTMcon.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	*/
	    }
	    
}
