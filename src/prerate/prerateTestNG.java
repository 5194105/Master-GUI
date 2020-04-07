package prerate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import configuration.config;
import configuration.excel;

public class prerateTestNG {
  
  

	String tempFile,configFile;
	excel excelVar;
	boolean fl;
	 Boolean isPresent=false;
	int retryCounter=0;
	int i=0;
	Object o;
	WebDriverWait wait1,wait2,wait3,wait4;
	
	config c;
	/*
	public  JavascriptExecutor Executor;
	public  JavascriptExecutor Executor1;
	public  JavascriptExecutor Executor2;
	public  JavascriptExecutor Executor3;
	public  JavascriptExecutor Executor4 ;
	public  WebElement elementAK;
	public  WebElement element1;
	public  WebElement element2;
	public  WebElement element3;
	public  WebElement element4;
	long serviceLong1,serviceLong2;
	long invoiceLong1,invoiceLong2;
	long acctLong1,acctLong2;
	long trkngLong1,trkngLong2;
	String a ;
	*/
	int count1,count2,count3,count4 ;
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

	WebDriver driver1,driver2,driver3,driver4;
	static int sheetcount;
	static int j ;
	 boolean flag2 ;

	 Boolean isChecked1=false,isChecked2=false,isChecked3=false,isChecked4=false;
        Boolean compatibleMode;
        Boolean chrome;
        
        
      //  WebElement element;
	
       String  homePath=System.getProperty("user.dir");
       String browser="2";
        String chromeSetProperty="webdriver.chrome.driver";
		String ieSetProperty="webdriver.ie.driver";
		//String firefoxSetProperty="";
		
		int k;
		boolean sel;
		
		String chromePath=homePath+"\\drivers\\chromedriver.exe";
	
		
	//	String chromePath="C:\\Users\\theth\\git\\Master-GUI\\drivers\\chromedriver.exe";
		String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
        
		String levelUrl="https://testsso.secure.fedex.com/L3/PRSApps";
		
		
		
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
	
		
	
		
		
	String level="3";
		
		
		
		
		
		
	
	
	
	@BeforeClass
	public void setupExcel() {
		//or from eclipse.
    	homePath=System.getProperty("user.dir");
    	System.out.println("homePath" +System.getProperty("user.dir"));
    	excelVar = new excel(homePath+"\\test data\\PRERATE_UPDATE.xlsx");
    	//e = new excel(filepathExcel);
    	excelVar.setUpExcelWorkbook();
    	excelVar.setUpExcelSheet(0);
    	excelVar.setRowCountAutomatically(0);
    	excelVar.setColCountAutomatically(0);
    	rowCount=excelVar.getRowCount();
    	colCount=excelVar.getColCount()+1;
    	System.out.println("ROW COUNT "+rowCount);
    	System.out.println("COL COUNT "+colCount);
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
    	System.out.println("Total Rows for One: "+totalRows1);
    	System.out.println("Total Rows for Two: "+totalRows2);
    	System.out.println("Total Rows for Three: "+totalRows3);
    	System.out.println("Total Rows for Four: "+totalRows4);
    	
    	
	}
	
	
	
    @DataProvider(name = "data-provider1")
    public synchronized Object[][] dataProviderMethod1() { 
    	String tempString="";
    	Object [][] obj = new Object[totalRows1][colCount];
    	int objCount=0;
    	for(int i=1;i<=rowCount;i+=4) {
    		for(int j=0;j<colCount-1;j++) {
    	
    				System.out.println("ROW:"+i+" COL:"+j);
    				tempString=excelVar.getCellData(i, j);
    					if (tempString.equals("null")){
    						tempString="";
    					}
    				obj[objCount][j]=tempString;
    			
    		}
    		obj[objCount][colCount-1]=i;
    		objCount++;
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
    			System.out.println("ROW:"+i+" COL:"+j);
				tempString=excelVar.getCellData(i, j);
					if (tempString.equals("null")){
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
    			
    			
    			System.out.println("ROW:"+i+" COL:"+j);
				tempString=excelVar.getCellData(i, j);
					if (tempString.equals("null")){
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
    			System.out.println("ROW:"+i+" COL:"+j);
				tempString=excelVar.getCellData(i, j);
					if (tempString.equals("null")){
						tempString="";
					}
				obj[objCount][j]=tempString;
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
    try {
		doPrerate(driver1,wait1,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
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
    try {
		doPrerate(driver2,wait2,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,2);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
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
    try {
		doPrerate(driver3,wait3,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
    try {
		doPrerate(driver4,wait4,type, comment,podScan,tinCount, testInputNbr, trk, amount, currcode, approverID, cc1, cm1, cc2 , cm2 , cc3, cm3 , cc4 , cm4, rowNumber,4);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void login(WebDriver driver,WebDriverWait wait) {
    	
    	
    	
        
    if (level.equals("2"))
	{
		levelUrl="https://testsso.secure.fedex.com/prerates-l2/";
	}
	else if (level.equals("3"))
	{
		levelUrl="https://testedcsso.secure.fedex.com/l3/prerates";
		//levelUrl="https://testsso.secure.fedex.com/l3/prerates";
	}
        
 
  //edc
    
    
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
    driver.get(levelUrl);
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	//wait = new WebDriverWait( driver,10);
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

  

    	
    	
    	driver.switchTo().frame("header");
		driver.findElement(By.id("preRateEntrySelection")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		System.out.println("Instance :"+instanceNumber+" Tracking Number: "+trk);
		driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).sendKeys(trk);
		driver.findElement(By.xpath("//button[@id='preRateEntrySelForm:search_button']")).click();
		
		try{
			
		
		//Fast way to move to next screen... fails if prerate input not there.
		checkVal(driver,By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[3]/td/span/div[3]/div[1]/div/div/span[1]"),By.className("ui-faces-message-text"),1,10,1,10);
	
		
		
		

		driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
		element = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[2]/span[2]"));
		Executor = (JavascriptExecutor)driver;
		Executor.executeScript("arguments[0].click();", element);
		Thread.sleep(1000);
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
			
			Assert.fail("Failed selecting dropdown menu...");
		}
		
		try {
		System.out.println("amount "+amount);
		System.out.println("currcode "+currcode);
		System.out.println("approverID "+approverID);
		System.out.println("cc1 "+cc1);
		System.out.println("cm1 "+cm1);
		System.out.println("cc2 "+cc2);
		System.out.println("cm2 "+cm2);
		System.out.println("cc3 "+cc3);
		System.out.println("cm3 "+cm3);
		System.out.println("cc4 "+cc4);
		System.out.println("cm4 "+cm4);

		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:amountId:pymt_amnt_input']")).sendKeys(amount);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:currCodeId:cuu_code_input']")).sendKeys(currcode);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:rateApprover_input']")).sendKeys(approverID);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde1_input']")).sendKeys(cc1);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt1_input']")).sendKeys(cm1);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde2_input']")).sendKeys(cc2);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt2_input']")).sendKeys(cm2);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(cc3);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(cm3);
	//	driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(cc3);
	//	driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(cm3);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).sendKeys(cc4);
		driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).sendKeys(cm4);
	//	driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(cc4);
	//	driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(cm4);
	//	driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).sendKeys(cc4);
	//	driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).sendKeys(cm4);
			
		
		
		//ENABLE THIS TO SUBMIT!!!!!!
		//driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
		
		
		
		}
		catch(Exception e) {
			 Assert.fail("Failed on the input menu...");
		}
		
		
		//Not sure what this is trying to find... we should check to see if successful first tho.
		try {
			
			checkVal(driver,By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]"),By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[6]/td/table/tbody/tr[2]/td/span/span/span/span[2]"),2,5,1,10);
			
			/*
			//String er=driver.findElement(By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[6]/td/table/tbody/tr[2]/td/span/span/span/span[2]")).getText();
			System.out.println(er);
			driver.switchTo().defaultContent();
			
			if(er!=null) {
				 Assert.fail(er);
			}
		
			*/
		}
		catch(NullPointerException e) {
			System.out.println("Didnt find external error...");
			
		}
		
		catch(NoSuchElementException e) {
			System.out.println("Didnt find external error...");
			
		}
		
		
		try {
			checkVal(driver,By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]"),By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[4]/td/span/div/div[1]/div[2]/div/table[2]/tbody/tr/td/span/span/span/span[2]"),2,5,1,10);
			
			/*
			//Not sure what this is trying to find... we should check to see if successful first tho.
			String er=driver.findElement(By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[4]/td/span/div/div[1]/div[2]/div/table[2]/tbody/tr/td/span/span/span/span[2]")).getText();
			System.out.println(er);
		//	driver.switchTo().defaultContent();
			
			if(er!=null) {
				 Assert.fail(er);
			}
		*/
			
		}
		catch(NullPointerException e) {
			System.out.println("Didnt find external error...");
			
		}
		
		catch(NoSuchElementException e) {
			System.out.println("Didnt find external error...");
			
		}
		
		
		
		try{
			count=(driver.findElements(By.xpath("//input[@type='checkbox']")).size());
			//System.out.println(count);
			for( k=2;k<count;k++){
				sel=driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).isEnabled();
				if(sel==true){
					driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).click();
				}
				else {
					//System.out.println("disabled");
					Assert.fail("Override Disabled.");
					fl=true;
					break;
				}
			}
			driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
			
		}
		catch (NoSuchElementException a){
			//System.out.println("just proceed further");
		}
		try{

			checkVal(driver,By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]"),By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[6]/td/table/tbody/tr[2]/td/span/span/span/span[2]"),2,5,1,10);	
			checkVal(driver,By.xpath("/html/body/form[1]/div/div/table/tbody/tr[1]/td/span/div/div[1]/div/div/span[1]"),By.xpath("/html/body/form[1]/div[1]/div/table/tbody/tr[4]/td/span/div/div[1]/div[2]/div/table[2]/tbody/tr/td/span/span/span/span[2]"),2,5,1,10);
			
			//flag2=driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).isDisplayed();
			//excelVar.setCellData(rowNumber, 1, "Completed");
			//excelVar.saveAndClose();
			writeToExcel(rowNumber,1,"Completed");
			driver.switchTo().defaultContent();
		
		}
		catch (NoSuchElementException a){
			if(fl==true){

				//excelVar.setCellData(rowNumber, 1, "Override Disabled");
				//excelVar.saveAndClose();
				writeToExcel(rowNumber,1,"Override Disabled");
				driver.switchTo().defaultContent();
		
			}
			else{
				//System.out.println("Nosuccessfull : " + flag2);
				//excelVar.setCellData(rowNumber, 1, "Try this manually");

				writeToExcel(rowNumber,1,"Try this manually");
				driver.switchTo().defaultContent();
		
			}
		}
	
	catch (NullPointerException f)
	{
		//excelVar.setCellData(rowNumber, 1, "Try this manually");
		writeToExcel(rowNumber,1,"Try this manually");
		driver.switchTo().defaultContent();
	
	}
	catch (WebDriverException h)
	{
		//excelVar.setCellData(rowNumber, 1, "Try this manually");
		writeToExcel(rowNumber,1,"Try this manually");
		
		driver.switchTo().defaultContent();
	}
}
    	
    
    
	public synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
		
		excelVar.setCellData(rowCountExcel, colCountExcel, outputString);
		excelVar.writeCellData();
	}
	
	
	
	
	
	
	
	//This is to help speed up program. It will look for the next object.. if found then continue.. if not quickly look for any error message.
	//The next object will have a wait, 
	public void checkVal(WebDriver driver,By elementNew,By elementOld,int caseNumber,int waitTimeLong,int waitTimeShort,int waitTimeDefault) {
		
		String newString,oldString;
		Boolean skip=false;
		WebElement we;
		switch(caseNumber) {
			
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
					Assert.fail(oldString);
				}
				catch(Exception e) {
					System.out.println("Could not find prerate input page...");
					Assert.fail("Unknown Error... Could not find prerate input page or Not Eligible Shipment Error");
				}
			}
			
			
		break;
		
		case 2:
			try {
				driver.findElement(elementNew);
				Assert.assertTrue(driver.findElement(elementNew).getText().equals("Shipment Selection for Pre-Rate Entry"), "Made it back to prerate entry screen.");
				skip=true;
			}
			catch(Exception e) {
				System.out.println("Could not go back to prerate entry screen...");
			}
	
			if (skip==false) {
			try {
				driver.manage().timeouts().implicitlyWait(waitTimeShort,TimeUnit.SECONDS);
				oldString=driver.findElement(elementOld).getText();
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
				driver.findElement(elementNew);
				Assert.assertTrue(driver.findElement(elementNew).getText().equals("Shipment Selection for Pre-Rate Entry"), "Made it back to prerate entry screen.");
				skip=true;
			}
			catch(Exception e) {
				System.out.println("Could not find top error....");
			}
	
			if (skip==false) {
			try {
				driver.manage().timeouts().implicitlyWait(waitTimeShort,TimeUnit.SECONDS);
				oldString=driver.findElement(elementOld).getText();
				Assert.fail(oldString);
			}
			catch(Exception e) {
				System.out.println("Could not find bottom error....");
				
			}
			break;
		}
			
		
			
			
			
		}
		  driver.manage().timeouts().implicitlyWait(waitTimeDefault,TimeUnit.SECONDS);
		}
	
    }
	
