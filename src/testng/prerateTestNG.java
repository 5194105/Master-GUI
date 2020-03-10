package testng;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import configuration.config;
import configuration.excel;

public class prerateTestNG {
  
  

	String tempFile,configFile;
	excel e;
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
	String d="02/20/2020";
	String d2="02/21/2020";;
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

	 Boolean isChecked1=false,isChecked2=false,isChecked3=false,isChecked4=false;
        Boolean compatibleMode;
        Boolean chrome;
        
        
        WebElement element;
	
       String  homePath=System.getProperty("user.dir");
       String browser="2";
        String chromeSetProperty="webdriver.chrome.driver";
		String ieSetProperty="webdriver.ie.driver";
		//String firefoxSetProperty="";
		

		
		String chromePath=homePath+"\\drivers\\chromedriver.exe";
	
		
	//	String chromePath="C:\\Users\\theth\\git\\Master-GUI\\drivers\\chromedriver.exe";
		String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
        
		String levelUrl="https://testsso.secure.fedex.com/L3/PRSApps";
		
		
		
		int rowCount;
		int colCount;
		int total;
		int testCounter1,testCounter2,testCounter3,testCounter4;
		

	
		int totalRows1,totalRows2,totalRows3,totalRows4;
	
	
		
		
		
		
		
		
		
		
		
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
	
		
	
		
		
	String level="3";
		
		
		
		
		
		
	
	
	
	@BeforeClass
	public void setupExcel() {
		//or from eclipse.
    	homePath=System.getProperty("user.dir");
    	System.out.println("homePath" +System.getProperty("user.dir"));
    	e = new excel(homePath+"\\test data\\PRERATE_UPDATE.xlsx");
    	//e = new excel(filepathExcel);
    	e.setUpExcelWorkbook();
    	e.setUpExcelSheet(0);
    	e.setRowCountAutomatically(0);
    	e.setColCountAutomatically(0);
    	rowCount=e.getRowCount();
    	colCount=e.getColCount()+1;
    	System.out.println("ROW COUNT "+rowCount);
    	System.out.println("COL COUNT "+colCount);
    	total= rowCount/4;
    	totalMod=total%4;
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
    	System.out.println("Total Rows for One: "+totalRows1);
    	System.out.println("Total Rows for Two: "+totalRows2);
    	System.out.println("Total Rows for Three: "+totalRows3);
    	System.out.println("Total Rows for Four: "+totalRows4);
    	
    	
	}
	
	
	
    @DataProvider(name = "data-provider1")
    public Object[][] dataProviderMethod1() { 
 
    	Object [][] obj = new Object[totalRows1][colCount];
    	int objCount=0;
    	for(int i=1;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    	
    			
    				obj[objCount][j]=e.getCellData(i, j);
    			
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}

    	return obj;
    
    }
    
    
    
    
    
    
    
    
    @DataProvider(name = "data-provider2")
    public Object[][] dataProviderMethod2() { 
    	
    

    	Object [][] obj = new Object[totalRows2][colCount];
    	int objCount=0;
    	for(int i=2;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    		//	System.out.println(j);
    		//	System.out.println(e.getCellData(i, j));
    			obj[objCount][j]=e.getCellData(i, j);
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}

    	return obj;
    
    }
    
    @DataProvider(name = "data-provider3")
    public Object[][] dataProviderMethod3() { 
    
    	Object [][] obj = new Object[totalRows3][colCount];
    	int objCount=0;
    	for(int i=3;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    			System.out.println(i);
    			System.out.println(e.getCellData(i, j));
    			obj[objCount][j]=e.getCellData(i, j);
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}

    	return obj;
    
    }
    
    @DataProvider(name = "data-provider4")
    public Object[][] dataProviderMethod4() { 
    	
    	Object [][] obj = new Object[totalRows4][colCount];
    	int objCount=0;
    	for(int i=4;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    			obj[objCount][j]=e.getCellData(i, j);
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
    	}

    	return obj;
    
    }
    
    
    
    
    
    
    @Test(dataProvider="data-provider1")
    public void testMethod1( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
    	try { 
    		driver1.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    System.setProperty(chromeSetProperty,chromePath);
    driver1 = new ChromeDriver();
    login(driver1,wait1);
    
    }
    @Test(dataProvider="data-provider2")
    public void testMethod2( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
   
    	try { 
    		driver2.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    System.setProperty(chromeSetProperty,chromePath);
    driver2 = new ChromeDriver();
    login(driver2,wait2);
    }
    @Test(dataProvider="data-provider3")
    public void testMethod3( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
   
    	try { 
    		driver3.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    System.setProperty(chromeSetProperty,chromePath);
    driver3 = new ChromeDriver();
    login(driver3,wait3);
    }
    @Test(dataProvider="data-provider4")
    public void testMethod4( String type, String comment,String podScan,String tinCount,String testInputNbr,String trk,String amount,String currcode,String approverID,String cc1,String cm1,String cc2 ,String cm2 ,String cc3,String cm3 ,String cc4 ,String cm4,int rowNumber) {
  
    	try { 
    		driver4.quit();
	  }
	  catch(Exception eee) {
		  System.out.println(eee);
		  
	  }
	  
    System.setProperty(chromeSetProperty,chromePath);
    driver4 = new ChromeDriver();
    
    
    
    login(driver4,wait4);
    
    }
    
    
    
    
    public void login(WebDriver driver,WebDriverWait wait) {
    	
    	
    	
        
    if (level.equals("2"))
	{
		levelUrl="https://testsso.secure.fedex.com/prerates-l2/";
	}
	else if (level.equals("3"))
	{
		levelUrl="https://testsso.secure.fedex.com/l3/prerates";
	}
        
 
  
/*
         if (chrome==false){
         if (compatibleMode==true){
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
	    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    capabilities.setCapability("ignoreZoomSetting", true);
	    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    capabilities.setCapability("initialBrowserUrl",levelUrl);
	
		System.setProperty("webdriver.ie.driver", webDriverPath+ "\\IEDriverServer.exe");
		 driver = new InternetExplorerDriver(capabilities);
             
                }
                else {
                System.setProperty("webdriver.ie.driver", webDriverPath+ "\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                driver.get(levelUrl);
                }
         }
          else {
          System.setProperty("webdriver.chrome.driver", webDriverPath+ "\\chromedriver.exe");
            driver= new ChromeDriver();
            driver.get(levelUrl);
        
        }
         */
            
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	wait = new WebDriverWait( driver,10);
	driver.manage().window().maximize();
	driver.findElement(By.id("username")).sendKeys("5194105");
	driver.findElement(By.id("password")).sendKeys("5194105");
	driver.findElement(By.id("submit")).click();
    	
    }
	
}
