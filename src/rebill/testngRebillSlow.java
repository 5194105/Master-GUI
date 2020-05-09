package rebill;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class testngRebillSlow {

	
	
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
	
	String databaseSqlQuery,databaseSqlCount;
	
	
	ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();

	
	@BeforeClass
	@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox","failedCheckBox","domesticCheckBox","internationalCheckBox","expressCheckBox","groundCheckBox","normalCheckBox","mfRetireCheckBox"})
	public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,String failedCheckBox,String domesticCheckBox,String internationalCheckBox,String expressCheckBox,String groundCheckBox,String normalCheckBox,String mfRetireCheckBox) {
	c=new config();
	/*
	@BeforeClass
	public void setupExcel() {
*/
		/*
		System.out.println("filepathExcelParameter "+filepathExcelParameter);
		System.out.println("levelParameter "+levelParameter);
		System.out.println("broswerParameter "+broswerParameter);
		System.out.println("compatibleModeParameter "+compatibleModeParameter);
		System.out.println("source "+source);
		System.out.println("allCheckBox "+allCheckBox);
		System.out.println("nullCheckBox "+nullCheckBox);
		System.out.println("failedCheckBox "+failedCheckBox);
		System.out.println("domesticCheckBox "+domesticCheckBox);
		System.out.println("internationalCheckBox "+internationalCheckBox);
		System.out.println("expressCheckBox "+expressCheckBox);
		System.out.println("groundCheckBox "+groundCheckBox);
		System.out.println("normalCheckBox "+normalCheckBox);
		System.out.println("mfRetireCheckBox "+mfRetireCheckBox);
	//public void setupExcel() {
	*/
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
        
        if (testingMode==true){
        	c=new config();
        	browser="2";
        	level="2";
        	
        	excelVar = new excel(homePath+"\\test data\\rebill.xlsx");
        	
        	 source="db";
        	 allCheckBox="false";
        	 nullCheckBox="true";
        	 failedCheckBox="true";
        	 domesticCheckBox="false";
        	 internationalCheckBox="true";
        	 expressCheckBox="false";
        	 groundCheckBox="true";
        	 normalCheckBox="true";
        	 mfRetireCheckBox="false";
        	
        	
        }
        else {
        	if (source.equals("db")) {}
        	else if (source.equals("excel")) {
        		//excelVar = new excel(homePath+"\\test data\\rebill.xlsx");
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
			this.normalCheckBox=normalCheckBox;
			this.mfRetireCheckBox=groundCheckBox;
        	
        }
        
        
       
    	homePath=System.getProperty("user.dir");
    	System.out.println("homePath" +System.getProperty("user.dir"));
    	
    	if(source.equals("excel")) {
    	excelVar.setUpExcelWorkbook();
    	excelVar.setUpExcelSheet(0);
    	excelVar.setRowCountAutomatically(2);
    	excelVar.setColCountAutomatically(0);
    	rowCount=excelVar.getRowCount();
    	colCount=excelVar.getColCount()+1;
    	}
    	else if(source.equals("db")) {
    	
    		
    		runDbQuery();;
    		
    	
    	}
    	total= rowCount/4;
    	totalMod=rowCount%4;
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
    		levelUrl="https://testsso.secure.fedex.com/L2/eRA/index.html";
    		//c.setEraL2DbConnection();
    	}
    	else if (level.equals("3"))
    	{
    		levelUrl="https://testsso.secure.fedex.com/L3/eRA/index.html";
    	//.setEraL3DbConnection();
    	}
       
    	
	}
	
	
	
	
	public void runDbQuery() {
		Connection GTMcon=null;
		Statement stmt = null;
		ResultSet rs = null;
		int[] total= new int[2];
		
	
		String result, description, test_input_nbr, tin_count, trkngnbr, reason_code, rebill_acct,
		 invoice_nbr_1, invoice_nbr_2, mig, region,  login,   password,  rsType, company, worktype;
		
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
		
  	 
    	 databaseSqlCount="select result, description, test_input_nbr, tin_count, trkngnbr, reason_code, rebill_acct,invoice_nbr_1, invoice_nbr_2, mig, region,  login,   password,  rs_Type, company, worktype from rebill_regression ";
    	
    	if (allCheckBox.equals("false")) {
    		databaseSqlCount+="where ";
    	}
    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
    		databaseSqlCount+="(result is null or result ='fail') ";
    	}
    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
    		databaseSqlCount+="result is null ";
    	}
    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
    		databaseSqlCount+="result ='fail' ";
    	}
    	if (domesticCheckBox.equals("true")) {
    		databaseSqlCount+="and rs_type='DM' ";
    	}
    	if (internationalCheckBox.equals("true")) {
    		databaseSqlCount+="and rs_type='IL' ";
    	}
    	if (expressCheckBox.equals("true")) {
    		databaseSqlCount+="and company='EP' ";
    	}
    	if (groundCheckBox.equals("true")) {
    		databaseSqlCount+="and company='GD' ";
    	}
       	if (normalCheckBox.equals("true") && mfRetireCheckBox.equals("false")) {
    		databaseSqlCount+="and worktype='NORMAL' ";
    	}
       	if (mfRetireCheckBox.equals("true") && normalCheckBox.equals("false")) {
    		databaseSqlCount+="and worktype='MFRETIRE' ";
    	}
       	if (mfRetireCheckBox.equals("true") && normalCheckBox.equals("true")) {
    		databaseSqlCount+="and worktype in ('MFRETIRE','NORMAL') ";
    	}
       	
    	
    	

    	try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
    		 stmt = GTMcon.createStatement();
    		 System.out.println(databaseSqlCount);
        	 rs = stmt.executeQuery(databaseSqlCount);
        	 
        	 while(rs.next()) {
        		 rowCount++;
        		 rebillDataArray.add(new rebillData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
        	 }
        	 colCount=17;
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}

    	for (int i=0;i<rebillDataArray.size();i++) {
    		System.out.println(rebillDataArray.get(i).getString());
    	
    	}
    	
	}
	
	
	
    @DataProvider(name = "data-provider1")
    public synchronized Object[][] dataProviderMethod1() { 
    	Object [][] obj= new Object[totalRows1][colCount];;
try {
    	String tempString="";
    	
    	int objCount=0;
    	for(int i=1;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    				if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					
    					
    					switch(j) {
    					case 0:
    					tempString=rebillDataArray.get(i-1).getResult();
    					break;
    					case 1:
        					tempString=rebillDataArray.get(i-1).getDescription();
        					break;
    					case 2:
        					tempString=rebillDataArray.get(i-1).getTest_input_nbr();
        					break;
    					case 3:
        					tempString=rebillDataArray.get(i-1).getTin_count();
        					break;
    					case 4:
        					tempString=rebillDataArray.get(i-1).getTrkngnbr();
        					break;
    					case 5:
        					tempString=rebillDataArray.get(i-1).getReason_code();
        					break;
    					case 6:
        					tempString=rebillDataArray.get(i-1).getRebill_acct();
        					break;
    					case 7:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_1();
        					break;
    					case 8:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_2();
        					break;
    					case 9:
        					tempString=rebillDataArray.get(i-1).getMig();
        					break;
    					case 10:
        					tempString=rebillDataArray.get(i-1).getRegion();
        					break;
    					case 11:
        					tempString=rebillDataArray.get(i-1).getLogin();
        					break;
    					case 12:
        					tempString=rebillDataArray.get(i-1).getPassword();
        					break;
    					case 13:
        					tempString=rebillDataArray.get(i-1).getRsType();
        					break;
    					case 14:
        					tempString=rebillDataArray.get(i-1).getCompany();
        					break;
    					case 15:
        					tempString=rebillDataArray.get(i-1).getWorktype();
        					break;
    					
    					}
    				
    				
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
    	return obj;

    }
    
    
    
    
    
    
    
    
    @DataProvider(name = "data-provider2")
    public synchronized Object[][] dataProviderMethod2() { 
    	
    
    	String tempString="";
    	Object [][] obj = new Object[totalRows2][colCount];
    	int objCount=0;
    	for(int i=2;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {	
    			if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					
    					
    					switch(j) {
    					case 0:
    					tempString=rebillDataArray.get(i-1).getResult();
    					break;
    					case 1:
        					tempString=rebillDataArray.get(i-1).getDescription();
        					break;
    					case 2:
        					tempString=rebillDataArray.get(i-1).getTest_input_nbr();
        					break;
    					case 3:
        					tempString=rebillDataArray.get(i-1).getTin_count();
        					break;
    					case 4:
        					tempString=rebillDataArray.get(i-1).getTrkngnbr();
        					break;
    					case 5:
        					tempString=rebillDataArray.get(i-1).getReason_code();
        					break;
    					case 6:
        					tempString=rebillDataArray.get(i-1).getRebill_acct();
        					break;
    					case 7:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_1();
        					break;
    					case 8:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_2();
        					break;
    					case 9:
        					tempString=rebillDataArray.get(i-1).getMig();
        					break;
    					case 10:
        					tempString=rebillDataArray.get(i-1).getRegion();
        					break;
    					case 11:
        					tempString=rebillDataArray.get(i-1).getLogin();
        					break;
    					case 12:
        					tempString=rebillDataArray.get(i-1).getPassword();
        					break;
    					case 13:
        					tempString=rebillDataArray.get(i-1).getRsType();
        					break;
    					case 14:
        					tempString=rebillDataArray.get(i-1).getCompany();
        					break;
    					case 15:
        					tempString=rebillDataArray.get(i-1).getWorktype();
        					break;
    					
    					}
    				
    				
    				}
					if (tempString == null || tempString.equals("null")){
						tempString="";
					}
				obj[objCount][j]=tempString;
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}

    	return obj;
    
    }
    
    @DataProvider(name = "data-provider3")
    public synchronized Object[][] dataProviderMethod3() { 
    	String tempString="";
    	Object [][] obj = new Object[totalRows3][colCount];
    	int objCount=0;
    	for(int i=3;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    			if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					
    					
    					switch(j) {
    					case 0:
    					tempString=rebillDataArray.get(i-1).getResult();
    					break;
    					case 1:
        					tempString=rebillDataArray.get(i-1).getDescription();
        					break;
    					case 2:
        					tempString=rebillDataArray.get(i-1).getTest_input_nbr();
        					break;
    					case 3:
        					tempString=rebillDataArray.get(i-1).getTin_count();
        					break;
    					case 4:
        					tempString=rebillDataArray.get(i-1).getTrkngnbr();
        					break;
    					case 5:
        					tempString=rebillDataArray.get(i-1).getReason_code();
        					break;
    					case 6:
        					tempString=rebillDataArray.get(i-1).getRebill_acct();
        					break;
    					case 7:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_1();
        					break;
    					case 8:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_2();
        					break;
    					case 9:
        					tempString=rebillDataArray.get(i-1).getMig();
        					break;
    					case 10:
        					tempString=rebillDataArray.get(i-1).getRegion();
        					break;
    					case 11:
        					tempString=rebillDataArray.get(i-1).getLogin();
        					break;
    					case 12:
        					tempString=rebillDataArray.get(i-1).getPassword();
        					break;
    					case 13:
        					tempString=rebillDataArray.get(i-1).getRsType();
        					break;
    					case 14:
        					tempString=rebillDataArray.get(i-1).getCompany();
        					break;
    					case 15:
        					tempString=rebillDataArray.get(i-1).getWorktype();
        					break;
    					
    					}
    				
    				
    				}
					if (tempString == null || tempString.equals("null")){
						tempString="";
					}
				obj[objCount][j]=tempString;
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}

    	return obj;
    
    }
    
    @DataProvider(name = "data-provider4")
    public synchronized Object[][] dataProviderMethod4() { 
    	String tempString="";
    	Object [][] obj = new Object[totalRows4][colCount];
    	int objCount=0;
    	for(int i=4;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    			if(source.equals("excel")) {
    				tempString=excelVar.getCellData(i, j);
    				}
    				else if (source.equals("db")) {
    					
    					
    					switch(j) {
    					case 0:
    					tempString=rebillDataArray.get(i-1).getResult();
    					break;
    					case 1:
        					tempString=rebillDataArray.get(i-1).getDescription();
        					break;
    					case 2:
        					tempString=rebillDataArray.get(i-1).getTest_input_nbr();
        					break;
    					case 3:
        					tempString=rebillDataArray.get(i-1).getTin_count();
        					break;
    					case 4:
        					tempString=rebillDataArray.get(i-1).getTrkngnbr();
        					break;
    					case 5:
        					tempString=rebillDataArray.get(i-1).getReason_code();
        					break;
    					case 6:
        					tempString=rebillDataArray.get(i-1).getRebill_acct();
        					break;
    					case 7:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_1();
        					break;
    					case 8:
        					tempString=rebillDataArray.get(i-1).getInvoice_nbr_2();
        					break;
    					case 9:
        					tempString=rebillDataArray.get(i-1).getMig();
        					break;
    					case 10:
        					tempString=rebillDataArray.get(i-1).getRegion();
        					break;
    					case 11:
        					tempString=rebillDataArray.get(i-1).getLogin();
        					break;
    					case 12:
        					tempString=rebillDataArray.get(i-1).getPassword();
        					break;
    					case 13:
        					tempString=rebillDataArray.get(i-1).getRsType();
        					break;
    					case 14:
        					tempString=rebillDataArray.get(i-1).getCompany();
        					break;
    					case 15:
        					tempString=rebillDataArray.get(i-1).getWorktype();
        					break;
    					
    					}
    				
    				
    				}
					if (tempString == null || tempString.equals("null")){
						tempString="";
					}
				obj[objCount][j]=tempString;
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}

    	return obj;
    
    }
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider1",retryAnalyzer = Retry.class)
    public void testMethod1(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,int rowNumber) {
     
    	System.out.println("Instance: 1");
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
	    login(driver1,wait1,login,password);
	  
	    try {
	    	
	    
	        
			doRebill(driver1,wait1, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
   
    @Test(dataProvider="data-provider2",retryAnalyzer = Retry.class)
    public void testMethod2( String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,int rowNumber) {
     
    	System.out.println("Instance: 2");
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
	    	doRebill(driver2,wait2, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    
    
    }
    @Test(dataProvider="data-provider3",retryAnalyzer = Retry.class)
    public void testMethod3( String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,int rowNumber) {
    	System.out.println("Instance: 3");
    	
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
    	doRebill(driver3,wait3, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber,3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    
    
    
    
    
    
    @Test(dataProvider="data-provider4",retryAnalyzer = Retry.class)
    public void testMethod4(String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,int rowNumber) {
    	System.out.println("Instance: 4");
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
    	doRebill(driver4,wait4, result,  descripiton, testInputNbr, tinCount, trk, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, mig, region , login , password, rsType , company , worktype, rowNumber,4);
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
    
    
    public void doRebill(WebDriver driver,WebDriverWait wait, String result, String descripiton,String testInputNbr,String tinCount,String trk,String reasonCode,String rebillAccount,String invoiceNbr1,String invoiceNbr2,String mig,String region ,String login ,String password,String rsType ,String company ,String worktype,int rowNumber, int instanceNumber) throws InterruptedException {
    
    	JavascriptExecutor js= (JavascriptExecutor) driver;
    	By tempElement;
    	int packageCounter=0;
    	Boolean exist;
    	WebElement scrollElement;
    	Boolean packageTab=false;
    	wait=new WebDriverWait(driver,20);
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

    	
    	
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
	         if (login.equals("5194105")){
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
                         		
              			driver.findElement(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[2]/div[2]/div[5]/div[1]/select/option[15]")).click();
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
         				 //Assert Fail
         		}
         		 driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
         		 System.out.println("Found Pop Up");
         		
         	}
         	catch(Exception e2) {
         		System.out.println("Could Not find Popup"+e2);
         		 Assert.fail(e2 +" Could Not find Popup");
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
                  if (login.equals("5194105")){
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
         	   					//Assert Fail
         	   					}
         	   				driver.findElement(By.xpath(" /html/body/div[6]/div/div/div[2]/button[1]")).click();
         	   				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoice-grid\"]/div/div/div[2]/div/div/div/div/form/div[4]/div[8]/div[3]/button[1]")));
         	   				}
         	   				catch(Exception ee) {
         	   					System.out.println(ee+"Could Not Get to Rebill Screen");
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
            	 if(uploadTrkToDB==true) {
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
                	 if(uploadTrkToDB==true) {
                     	  writeToDB(testInputNbr,tinCount,trk,resultArray);
                     	 }
                  }
                  if (resultArray[0].equals("fail")) {
                		 if(source.equals("excel")) {
                	  writeToExcel(rowNumber, 0,"fail");
                  	  writeToExcel(rowNumber, 1,resultArray[1]);
                		 }
                  	 if(uploadTrkToDB==true) {
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
    }
    

