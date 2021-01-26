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
import org.openqa.selenium.Keys;
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

public class testngMassRebillSlow {

	
	
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
	int counter1=0,counter2=0;
	String headless;
	
	ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
	String[][] allData;
	config c;
	int waitTime;
	String eraWorkable;
	int min=0,max=0;
	  ArrayList<Integer> trkList = new ArrayList<Integer>();
	  ArrayList<String> addedTrks = new ArrayList<String>();
	  ArrayList<String> removedTrks = new ArrayList<String>();
	
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","domesticCheckBox","internationalCheckBox","expressCheckBox","groundCheckBox","sessionCount","customString","customCheckBox","databaseDisabled","headless","eraWorkable"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String domesticCheckBox,String internationalCheckBox,String expressCheckBox,String groundCheckBox,String sessionCount,String customString,String customCheckBox,String databaseDisabled,String headless,String eraWorkable) {
	

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
		
		String tempTin="0";
    	int tempNumber=0;
    	
    	for (int i=0;i<allData.length;i++){
    		System.out.println(allData[i][2] + "  "+ tempTin);
    		if (!allData[i][2].equals(tempTin)) {
    			tempTin=allData[i][2];
    			trkList.add(i);
    		}
    	}
    	
    	trkList.add(allData.length);
    	
    	for (int i=0;i<trkList.size();i++){
    		System.out.println(trkList.get(i));
    	
	}
    	System.out.println();
	}
	
	
	
	
	public void runDbQuery() {
		try {
		Connection GTMcon=c.getGtmRevToolsConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd=null;

		
		
    	String databaseSqlCount="select count(*) as total from rebill_regression_mass ";
    	String databaseSqlQuery="select result, description, test_input_nbr, rowcount, trkngnbr, reason_code, bill_acct_nbr,invoice_nbr_1, invoice_nbr_2,  region,  username,   password,  rs_Type, company from rebill_regression_mass";
    	
    	if (allCheckBox.equals("true")) {
    		databaseSqlCount+=" where trkngnbr is not null";
    		databaseSqlQuery+=" where trkngnbr is not null ";
    	}
    	
    	System.out.println(customCheckBox);
    	System.out.println(customString);
    	
    	if (customCheckBox.equals("false")) {
    	
    	if (allCheckBox.equals("false")) {
    		databaseSqlCount+=" where ";
    		databaseSqlQuery+=" where ";
    	
    	
    	
    	
    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
    		databaseSqlCount+=" (result is null or result ='fail') ";
    		databaseSqlQuery+=" (result is null or result ='fail') ";
    	}
    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
    		databaseSqlCount+=" result is null ";
    		databaseSqlQuery+=" result is null ";
    	}
    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
    		databaseSqlCount+=" result ='fail' ";
    		databaseSqlQuery+=" result ='fail' ";
    	}
    	if (domesticCheckBox.equals("true") && internationalCheckBox.equals("false")) {
    		databaseSqlCount+="a nd rs_type='DM' ";
    		databaseSqlQuery+=" and rs_type='DM' ";
    	}
    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("false")) {
    		databaseSqlCount+=" and rs_type='IL' ";
    		databaseSqlQuery+=" and rs_type='IL' ";
    	}
    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("true")) {
    		databaseSqlCount+=" and rs_type in ('DM','IL')";
    		databaseSqlQuery+=" and rs_type in ('DM','IL')";
    	}
    	
    	if (expressCheckBox.equals("true") && groundCheckBox.equals("false")) {
    		databaseSqlCount+=" and company='EP' ";
    		databaseSqlQuery+=" and company='EP' ";
    	}
    	if (groundCheckBox.equals("true") && expressCheckBox.equals("false")) {
    		databaseSqlCount+=" and company='GD' ";
    		databaseSqlQuery+=" and company='GD' ";
    	}
    	
    	if (groundCheckBox.equals("true") && expressCheckBox.equals("true")) {
    		databaseSqlCount+=" and company in ('GD','EP') ";
    		databaseSqlQuery+=" and company in ('GD','EP') ";
    	}
    
    	/*
    	if (eraWorkable.equals("true")) {
    		databaseSqlCount+=" and workable='Y' ";
    		databaseSqlQuery+=" and workable='Y' ";
    	}
    	*/
    		}
    			}
    	else if (customCheckBox.equals("true")){
    		databaseSqlCount+=" where trkngnbr is not null and "+customString;
    		databaseSqlQuery+=" where trkngnbr is not null and "+customString;
    	}
    	databaseSqlCount+=" order by test_input_nbr";
		databaseSqlQuery+=" order by test_input_nbr";
    	 
    	

       	try {
            //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
        		String tempTin;
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
            		// trkList.add(rs.getString("TEST_INPUT_NBR"));
            		// trkList.add(rs.getString("TEST_INPUT_NBR"));
            		 		
            	 }
            	 //colCount=17;
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
    
    
    
    
    
    String databaseSqlQuery="select result, description, test_input_nbr, tin_count, trkngnbr, reason_code, bill_acct_nbr,invoice_nbr_1, invoice_nbr_2,  region,  username,   password,  rs_Type, company, comments from rebill_regression ";
	
    
    @Test(dataProvider="data-provider1",retryAnalyzer = Retry.class)
    public void testMethod1(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String region ,String login ,String password,String rsType ,String company, int rowNumber) {
    	System.out.println("Debug");
    	if (counter1==trkList.get(counter2)) {
    		
    	
    		
    		
    		
    		
    		min=trkList.get(counter2);
    		max=trkList.get(counter2+1);	
    		System.out.println(min);
    		System.out.println(max);	
    	
    		for(int i=min;i<max;i++) {
    				
    			System.out.println("Test Input Number "+allData[i][2]);
    			System.out.println("Row Count "+allData[i][3]);
    			System.out.println("Tracking Number "+allData[i][4]);
    			System.out.println("Reason Code "+allData[i][5]);
    			System.out.println("Account Number "+allData[i][6]);
    			addedTrks.add(allData[i][4]);
    			
    			
    			
    			try {
    		    	if (databaseDisabled.equals("false")) {
    		    
    		  		  String[] resultArray = validateResults(allData[i][4]);
    		  	  if ( resultArray[0].equals("pass")){
    		       	 if(source.equals("excel")) {
    		       	 writeToExcel(rowNumber, 0,"pass");
    		       	 writeToExcel(rowNumber, 1,"completed");
    		       	
    		       	 }
    		       	 System.out.println(tinCount);
    		       	 writeToDB(testInputNbr,allData[i][3],allData[i][4],resultArray);
    		       	// return;
    		  	  addedTrks.remove(allData[i][4]);
    		  	  removedTrks.add(allData[i][4]);
    		  	  			}
    		    		}
    		  	  }
    		    
    		  	  catch(Exception e) {
    		  		System.out.println(e);  
    		  	  }
    			
    			 if(checkAmount(allData[i][4])==false) {
  		    	   System.out.println("Amount is zero");
  		      	   String[] resultArray = new String[3];
  		    	   resultArray[0]="fail";
  		    	   resultArray[1]="Amount Equals Zero";
  		    	   resultArray[2]="NA";
  		           	 
  		           			 if(source.equals("excel")) {
  		                	 writeToExcel(rowNumber, 0,"fail");
  		                	 writeToExcel(rowNumber, 1,"Amount Equals Zero");
  		           			 }
  		               	 if(databaseDisabled.equals("false")) {
  		               	 System.out.println(tinCount);
  		                    	  writeToDB(testInputNbr,allData[i][3],allData[i][4],resultArray);
  		                    	 }
  		               addedTrks.remove(allData[i][4]);
  	    		  	  removedTrks.add(allData[i][4]);
  		                 	
  		                 
  		    	   
  		       }
    			
    		}
    		
    		
    		
    	
    	System.out.println(result);
    	System.out.println(descripiton);
    	System.out.println(testInputNbr);
    	System.out.println(tinCount);
    	System.out.println(trk);
    	System.out.println(reasonCode);
    	System.out.println(rebillAccount);
    	System.out.println(invoiceNbr1);
    	System.out.println(invoiceNbr2);
    	System.out.println(region);
    	System.out.println(login);
    	System.out.println(password);
    	System.out.println(rsType);
    	System.out.println(company);
    	System.out.println(rowNumber);
    	
    	
    	
    
  	  
  	  
  	  
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
	    
	        if (addedTrks.size()>=1) {
			doRebill(driver1,wait1, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType , company ,rowNumber,1);
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    counter2++;
	    }
	    
	    counter1++;
    
    }
   
    @Test(dataProvider="data-provider2",retryAnalyzer = Retry.class)
    public void testMethod2(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String region ,String login ,String password,String rsType ,String company, int rowNumber) {
    	     
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
	    	doRebill(driver2,wait2, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType , company ,  rowNumber,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
    @Test(dataProvider="data-provider3",retryAnalyzer = Retry.class)
    public void testMethod3(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String region ,String login ,String password,String rsType ,String company, int rowNumber) {
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
    	doRebill(driver3,wait3, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2,region , login , password, rsType , company , rowNumber,3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	}
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider4",retryAnalyzer = Retry.class)
    public void testMethod4(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String region ,String login ,String password,String rsType ,String company, int rowNumber) {
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
    	doRebill(driver4,wait4, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2,  region , login , password, rsType , company ,  rowNumber,4);
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
			
										
			driver.findElement(By.id("okta-signin-username")).sendKeys(login);
			driver.findElement(By.id("okta-signin-password")).sendKeys(password);
			driver.findElement(By.id("okta-signin-submit")).click();
			}
    	catch(Exception e) {
    		
    		 Assert.fail("Could Not Login");
    	}
    }
    
    
    public void doRebill(WebDriver driver,WebDriverWait wait, String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String region ,String login ,String password,String rsType ,String company ,int rowNumber, int instanceNumber) throws InterruptedException {
    	
    	WebElement element=null;
    	JavascriptExecutor js= (JavascriptExecutor) driver;
 
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
	   				// writeToDB(testInputNbr,tinCount,trk,resultArray);
            	 }
    		 Assert.fail("Failed on Entering Tracking Number");
    		
    	}
    	
    	
    	
    	//Try to Mass Adjustment Package Tab
    	int counter1=0;
    	String tempString1;
    	driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
    	Thread.sleep(5000);
    	while (counter1<10) {
    	try {  
    		counter1++;
    		System.out.println("Trying to click mass adjustment tab");
    		element =driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[12]/a"));
    		js.executeScript("arguments[0].click()", element);    
    		tempString1=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[1]/div/div[1]/label")).getText();
    		if(tempString1.equals("Adjustment Type")) {
    			System.out.println("Found Adjustment Type");
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
    	   				resultArray[1]="Could Not Get To Mass Adjusment Details";
    	   			//	 writeToDB(testInputNbr,tinCount,trk,resultArray);
                	 }
        		
     		Assert.fail("Could Not Get To Charge Code Details");
     	}
    	
    	//driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[1]/button[1]")).getText();
    	
    	System.out.println("Made it to mass adjusmtent screen");
    	
    	
    	try {
    			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    		//  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[1]/button[1]")).click(); 
    		
    			
    			
    			
    			
    			System.out.println("HELLO "+	trkList.get(counter2));
        		

        		int max=0;
    			int min=0;
        		min=trkList.get(counter2);
        		
        		max=trkList.get(counter2+1);	
        		System.out.println(min);
        		System.out.println(max);	
        		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).clear();
        		int trkCounter=0;
        		for(int i=min;i<max;i++) {
        				if(addedTrks.contains(allData[i][4])) {
		        			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(allData[i][4]);
		        			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(Keys.chord(Keys.SHIFT,Keys.ENTER));
		        			System.out.println("Test Input Number "+allData[i][2]);
		        			System.out.println("Tracking Number "+allData[i][4]);
		        			System.out.println("Reason Code "+allData[i][5]);
		        			System.out.println("Account Number "+allData[i][6]);
		        			trkCounter++;
        				}
        		}
 
        	  Thread.sleep(5000);
    		  Select actionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"contry\"]")));
		      actionDropDown.selectByValue("RB"); 
		      driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[1]/button[1]")).click(); 
		      driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);  
		      Thread.sleep(10000);
		      
		      
		      Actions actions = new Actions(driver);
		      String tempTrk="";
		      int xpathCounter=0;
		      int rebillTrk=0;
		     
		      
		      for(int i=0;i<addedTrks.size();i++) {
		    	xpathCounter++;
		        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[5]/div[1]/div/div[2]/div[1]/div/div[2]/div/div["+xpathCounter+"]/div/div/div/div/div/div")).click();
		        element =driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[5]/div[1]/div/div[2]/div[2]/div[2]/div/div["+xpathCounter+"]/div/div/div[11]/div"));
		        actions = new Actions(driver);
		        actions.moveToElement(element);
		        actions.click();
		        actions.sendKeys("R");
		        actions.build().perform();
		        
		        element =driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[5]/div[1]/div/div[2]/div[2]/div[2]/div/div["+xpathCounter+"]/div/div/div[11]/ui-select-wrap/div/div[1]/input"));
			    actions.moveToElement(element);
		        actions.click();
		        actions.sendKeys(reasonCode);
		        actions.sendKeys(Keys.ENTER);
		        actions.build().perform();
		        
		        
		        element =driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[5]/div[1]/div/div[2]/div[2]/div[2]/div/div["+xpathCounter+"]/div/div/div[14]/div"));
			      
		        actions = new Actions(driver);
		        actions.moveToElement(element);
		        actions.click();
		        actions.sendKeys(rebillAccount);
		        actions.sendKeys(Keys.ENTER);
		        actions.build().perform();
		        Thread.sleep(1000);
		        
		        }
		      
		      
		      driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[5]/div[2]/div/button[4]")).click();
		      Thread.sleep(5000);
    	}
		      catch(Exception e)
    	{
		    	  System.out.println("Failed while entering mass data");
		    		for(int i=min;i<max;i++) {
						
		    			System.out.println("Test Input Number "+allData[i][2]);
		    			System.out.println("Row Count "+allData[i][3]);
		    			System.out.println("Tracking Number "+allData[i][4]);
		    			System.out.println("Reason Code "+allData[i][5]);
		    			System.out.println("Account Number "+allData[i][6]);
				  
		    			
		    			
				  		 if(addedTrks.contains(allData[i][4])) {
				  			
				  			String[] resultArray = new String[3];
				  			resultArray[0]= "fail";
				  			resultArray[1]="Failed while entering mass data";
				  			resultArray[2]="NA";
				  					
		                 if (resultArray[0].equals("fail")) {
		               		 if(source.equals("excel")) {
		               			 writeToExcel(rowNumber, 0,"fail");
		               			 writeToExcel(rowNumber, 1,resultArray[1]);
		               		 }
		                 	 if(databaseDisabled.equals("false")) {
		                 	  writeToDB(testInputNbr,allData[i][3],allData[i][4],resultArray);
		                 	 }
		    	}
				  		 }
				  		 
		    		}
		    		Assert.fail("Could Not Get To Charge Code Details");
		    	  
    	}
    	
    	
    	 int counterCart=0;
    	   try {
		     
		      
		      while(counterCart<10) {
		   
		    	  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[5]/div[2]/div/button[5]")).click();
		    	  Thread.sleep(5000);
		    	  String tempString =  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[1]/div/label/h4")).getText();
		    	  if (!tempString.equals("Cart Summary:")) {
		    		  counterCart++;  
		    	  }
		      
		      }
    	   }
		      catch(Exception e) {
		    	counterCart++;  
		      }
		      
		      
		 
    		

    	try {
		    int xpathCounter=0;
		  	 for(int i=0;i<addedTrks.size();i++) {
		  		 xpathCounter++;
		  		 driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div/div/div/div/div/div/div[1]/div/div[2]/div/div["+xpathCounter+"]/div/div/div/div/div")).click();
		  	 }  
		      
		      //Processes
		  	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[2]/button[3]")).click();
		      
		  
		  	
		  	//Validation stuff..
		      
		      
		  	
		  		
    	
    		
    	
    		for(int i=min;i<max;i++) {
    				
    			System.out.println("Test Input Number "+allData[i][2]);
    			System.out.println("Row Count "+allData[i][3]);
    			System.out.println("Tracking Number "+allData[i][4]);
    			System.out.println("Reason Code "+allData[i][5]);
    			System.out.println("Account Number "+allData[i][6]);
		  
    			
    			
		  		 if(addedTrks.contains(allData[i][4])) {
		  			 String[] resultArray = validateResults(allData[i][4]);
		  			 	if (resultArray[0].equals("pass")){
		  			 		if(source.equals("excel")) {
		  			 			writeToExcel(rowNumber, 0,"pass");
		  			 			writeToExcel(rowNumber, 1,"completed");
		  			 			}
		  			 		if(databaseDisabled.equals("false")) {
		  			 			writeToDB(testInputNbr,allData[i][3],allData[i][4],resultArray);
                    	 }
                 }
                 if (resultArray[0].equals("fail")) {
               		 if(source.equals("excel")) {
               			 writeToExcel(rowNumber, 0,"fail");
               			 writeToExcel(rowNumber, 1,resultArray[1]);
               		 }
                 	 if(databaseDisabled.equals("false")) {
                 	  writeToDB(testInputNbr,allData[i][3],allData[i][4],resultArray);
                 	 }
                 	//  Assert.fail("Faled At Last Rebill Screen: "+resultArray[1]);
               	  
                 }
                 
		  		 }
		  		 
		  		  }
    		}
           	  catch(Exception e) {
           		
          		System.out.println("Failed while entering mass data");
          		for(int i=min;i<max;i++) {
      				
          			System.out.println("Test Input Number "+allData[i][2]);
          			System.out.println("Row Count "+allData[i][3]);
          			System.out.println("Tracking Number "+allData[i][4]);
          			System.out.println("Reason Code "+allData[i][5]);
          			System.out.println("Account Number "+allData[i][6]);
      		  
          			
          			
      		  		 if(addedTrks.contains(allData[i][4])) {
      		  			
      		  			String[] resultArray = new String[3];
      		  			resultArray[0]= "fail";
      		  			resultArray[1]="Failed at last page";
      		  			resultArray[2]="NA";
      		  					
                       if (resultArray[0].equals("fail")) {
                     		 if(source.equals("excel")) {
                     			 writeToExcel(rowNumber, 0,"fail");
                     			 writeToExcel(rowNumber, 1,resultArray[1]);
                     		 }
                       	 if(databaseDisabled.equals("false")) {
                       	  writeToDB(testInputNbr,allData[i][3],allData[i][4],resultArray);
                       	 }
          	}
      		  		 }
      		  		 
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
    	stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_rebill_mass,request_id) values (?,?,?,?,?,?,?)");  
		stmt.setString(1,testInputNbr);  
		stmt.setString(2,tinCount);  
		stmt.setString(3,trk);  
		stmt.setString(4,resultArray[0]);  
		stmt.setString(5,resultArray[1]);  
		stmt.setString(6,"Y");  
		stmt.setString(7,resultArray[2]);  
		stmt.executeUpdate();
		//stmt.close();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
		
    	
    	
    	try {
		//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
    	stmt=GTMcon.prepareStatement("update era_results set result=?,description=? where trkngnbr=?");  
		stmt.setString(1,resultArray[0]);  
		stmt.setString(2,resultArray[1]); 
		stmt.setString(3,trk); 
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
    
    
    
    public Boolean checkAmount(String tempTrk) {
    	Boolean b=false;
    	Connection con = null;
    	
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
    		
    		stmt=con.prepareStatement("select amount_due from apps.xxfdx_eabr_airbil_details_v where airbill_number=?");  
			stmt.setString(1,tempTrk);  
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
    			     
    			}
    			   else{
    				   
    				  String tempString= rs.getString("AMOUNT_DUE");
    				  if (!tempString.contains("0")) {
    					  b=true;
    				  }
    			   }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	      
    	
    	return b;
    	
    }
    
    public String[] validateResults(String trk) {
    
    	Connection con = null;
    	String[] resultArray = new String[3];
    	
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
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;
        String batchID=null;
     
      
        try {
           stmt2=con.prepareStatement("select max(batch_Summary_nbr) as bsn from invadj_schema.mass_adj_detail where TRACKING_NBR =?");                     	  
     	   stmt2.setString(1,trk);  
     	   rs2 = stmt2.executeQuery();
     	  try {
        	  if (rs2.next()==false){
        		  resultArray[0]="fail";
                  resultArray[1]="Not In ERA Database";
                  System.out.println("Not In ERA Database");
        	  }
        	  else {
        		   batchID = rs2.getString("bsn");
                  
        	  }
     	   
        }
     	  catch(Exception e) {
         	 
    		}
        }catch(Exception e) {}
     	   
                
                try {
                  
                           //  System.out.println("Is NULL");
                            
                    	   stmt=con.prepareStatement("select * from invadj_schema.mass_adj_detail where tracking_nbr =? and batch_summary_nbr=?");                     	  
                    	   stmt.setString(1,trk); 
                    	   stmt.setString(2,batchID); 
                    	   
                    	   rs = stmt.executeQuery();
                    	   System.out.println(batchID);
                          try {
                        	  if (rs.next()==false){
                        		  resultArray[0]="fail";
                                  resultArray[1]="Not In ERA Database";
                                  System.out.println("Not In ERA Database");
                        	  }
                        	  else {
                        		  String statusDesc = rs.getString("RECORD_STATUS_DESC");
                                  String errorDesc = rs.getString("FAIL_REASON_DESC");   
                                  
                                  if (statusDesc==null){
                                 	 
                                 	 statusDesc="fail";
                                 	 errorDesc="In ERA DB But Error Not Set";
                                  }
                                  
                                if (statusDesc.equals("SUCCESS")) {
                                      resultArray[0]="pass";
                                      resultArray[1]="completed";
                                      resultArray[2]=batchID;
                                }
                                else {
                                      resultArray[0]="fail";
                                      resultArray[1]=errorDesc;
                                      resultArray[2]=batchID;
                                }
                        	  }
                          }
                          catch(Exception e) {
                        	 
                          		}
                          
                }
                       catch(Exception e) {
                      	 
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









    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    