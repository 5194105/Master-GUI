package ThreadEc;





import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;


public class ecmod extends Thread {
	ArrayList<data> dataArray;
	config c;
	String levelUrl,databaseDisabled,source,level;
	int waitTime;
	int attempts=0;
	int maxAttempts=3;
	WebDriver driver;
	WebDriverWait wait;
	driverClass dc;
	validateClass vc;
	String trkngnbr,ecWorkType;
	Boolean ecOverride;
	public ecmod(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		this.dataArray=dataArray;
		this.c=c;
		levelUrl="https://testsso.secure.fedex.com/L3/revportal/home";
		databaseDisabled=c.getDatabaseDisabled();
		//source=c.getSource();
		waitTime=20;
		//level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"era_rerate_mass");
	}
	
	public void run () {
		//login();
		
		for (data d: dataArray) {
			trkngnbr=d.getTrkngnbr();
			ecWorkType=d.getEcWorkType();
			ecOverride=d.getOverride();
			System.out.println("EC TRK: "+trkngnbr);
			try {
				if (ecOverride==true) {
					getEc();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void login() {
		try {
			try { 
	    		driver.quit();
	    		driver.close();
		  }
		  catch(Exception e) {
			  System.out.println(e);
			  
		  }
			driver=dc.getDriver();
		    driver.get(levelUrl);
		    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,waitTime);
			driver.manage().window().maximize();
			
										
			 WebElement  id=driver.findElement(By.id("username"));
			 id.sendKeys("5194105");
			 WebElement  pass=driver.findElement(By.id("password"));
			 pass.sendKeys("5194105");
			 
			driver.findElement(By.id("submit")).click();
			}
		catch(Exception e) {
			 System.out.println(e);
			
		}
	}
	
	public void getEc() throws InterruptedException {
	
		
		login();
		
	//	Thread.sleep(10000);
		//928073574535
		try {
			WebElement frame=driver.findElement(By.xpath("//*[@id=\"transactionSuccessModalId\"]"));
			driver.switchTo().frame(frame);
			driver.findElement(By.xpath("//*[@id=\"okButtonId\"]")).click();
		}
		catch(Exception e) {
			System.out.println("Error");
			System.out.println(e);
		}
		
		driver.findElement(By.xpath("//*[@id=\"ECGUI\"]/img")).click();
		
	try {
				/*
				 File FilePath = new File("E:\\ErrorCorrection\\EC_OVR\\ER_input_1.xlsx");
				 FileInputStream fs = new FileInputStream(FilePath);
				 XSSFWorkbook wb = new XSSFWorkbook(fs);

				XSSFSheet sh= wb.getSheetAt(0);
					int rowCount=  sh.getLastRowNum()-sh.getFirstRowNum();
					
				    System.out.println(rowCount);
				    */
				  Select s=new Select(driver.findElement(By.id("workTypeId")));
				  s.selectByValue("INTL_AUTO");
				  
//				  Select s1=new Select(driver.findElement(By.id("userPrefRegionsId")));
//				  s1.selectByValue("EMEA");
//				  driver.findElement(By.id("selectAll")).click();
				  
//				  Thread.sleep(3000);
//				  driver.findElement(By.id("applyButtonId")).click();
				  
				  ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
                  driver.switchTo().window(windowHandles.get(0));
                 
                  Thread.sleep(3000);
                 
                WebElement drop7= driver.findElement(By.xpath("//*[@id=\"countriesPanel\"]/div[1]/label/span"));
                Actions actions = new Actions(driver);
                  actions.moveToElement(drop7).click().perform();
                  actions.moveToElement(drop7).click().perform();
                 
               
                Thread.sleep(3000);
                driver.findElement(By.id("applyButtonId")).click();
				  
				   // for(int i=1;i<=rowCount;i++)
				   // {
				    	  try {
				    	
				    	// Cell c1=sh.getRow(i).getCell(0);
				    	 
				    	//c1.setCellType(Cell.CELL_TYPE_STRING);
							
				           // String tracking=c1.getStringCellValue();
				            
				            Thread.sleep(3000);
				            
				    		WebElement search=driver.findElement(By.id("errCorrFormId:templateSearchId"));
				    		search.clear();
				    		search.click();
				    		search.sendKeys(trkngnbr);
				    		search.sendKeys(Keys.ENTER);
				    		
				    		Thread.sleep(5000);
				    		System.out.println(trkngnbr);
				    		
				    	     Thread.sleep(5000);

				
				
				
				
					Thread.sleep(10000);
					
					WebElement tablebody=driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody"));
					List<WebElement> rows=tablebody.findElements(By.tagName("tr"));
					
					
					
					System.out.println("size"+rows.size());
					if(rows.size()==1) {
					driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
					}
					else {
					for(int m=1;m<=rows.size();m++) {
						
					
				                            
					System.out.println(m);	
					driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr["+m+"]/td[1]/label/span")).click();
					
					System.out.println("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr["+m+"]/td[1]/label/span");
					
					//}
					}  
					}
					
					driver.findElement(By.id("submitButtonId")).click();
					System.out.println("clicked");
					try {
						WebElement frame=driver.findElement(By.xpath("//*[@id=\"transactionSuccessModalId\"]"));
						driver.switchTo().frame(frame);
						driver.findElement(By.xpath("//*[@id=\"okButtonId\"]")).click();
						
					}
					catch(Exception e) {
						System.out.println("Error");
					}
					Thread.sleep(10000);
					/*
					////*[@id="overrideTypeCode_id"]
					XSSFRow row=sh.getRow(i);
					Cell upd=row.getCell(1);
					upd.setCellValue("overriden");
					
					FileOutputStream fs1 = new FileOutputStream(FilePath);
					
					wb.write(fs1);
					fs1.close();
					*/
				//}
				    }	
				    catch(Exception e) {
				    	e.printStackTrace();
				    	try {
				    		System.out.println("hii");
				    		Thread.sleep(5000);
				    	driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
						driver.findElement(By.id("submitButtonId")).click();
						
				    	}
				    	catch(Exception e1) {
				    		e1.printStackTrace();
						/*
				    		XSSFRow row=sh.getRow(i);
						Cell upd=row.getCell(1);
						upd.setCellValue("Error Occured");
						
						FileOutputStream fs1 = new FileOutputStream(FilePath);
						
						wb.write(fs1);
						fs1.close();
						continue;
						*/
				    	}
				    	try {
				    		Thread.sleep(5000);
					    	driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr[1]/td[1]/label/span")).click();
					    	driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr[2]/td[1]/label/span")).click();
							
					    	driver.findElement(By.id("submitButtonId")).click();
							////*[@id="transactionCorrectionDynamicTableId"]/tbody/tr[1]/td[1]/label/span
					    	////*[@id="transactionCorrectionDynamicTableId"]/tbody/tr[2]/td[1]/label/span
					    	
				    	}
				    	catch(Exception e2) {
				    		return;
				    	}
				    	try {
				    		//*[@id="transactionCorrectionDynamicTableId"]/tbody/tr/td[1]/label/span
                         driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
							
					    	driver.findElement(By.id("submitButtonId")).click();
				
				    	}
				    	catch(Exception e2) {
				    		return;
				    	}
				    	}
				    	
				    }
	catch(Exception e) {
		System.out.println(e);
	}
	}
	
		
}
	
	
	/*
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","E:\\ErrorCorrection\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		 String baseUrl = "https://testsso.secure.fedex.com/L3/revportal/home";
		 driver.get(baseUrl);
		 
		 WebElement  id=driver.findElement(By.id("username"));
		 id.sendKeys("772772");
		 WebElement  pass=driver.findElement(By.id("password"));
		 pass.sendKeys("772772");
		 
		driver.findElement(By.id("submit")).click();
		Thread.sleep(10000);
		//928073574535
		try {
			WebElement frame=driver.findElement(By.xpath("//*[@id=\"transactionSuccessModalId\"]"));
			driver.switchTo().frame(frame);
			driver.findElement(By.xpath("//*[@id=\"okButtonId\"]")).click();
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		
		driver.findElement(By.xpath("//*[@id=\"ECGUI\"]/img")).click();
		
	
				
				 File FilePath = new File("E:\\ErrorCorrection\\EC_OVR\\ER_input_1.xlsx");
				 FileInputStream fs = new FileInputStream(FilePath);
				 XSSFWorkbook wb = new XSSFWorkbook(fs);

				XSSFSheet sh= wb.getSheetAt(0);
					int rowCount=  sh.getLastRowNum()-sh.getFirstRowNum();
					
				    System.out.println(rowCount);
				    
				  Select s=new Select(driver.findElement(By.id("workTypeId")));
				  s.selectByValue("INTL_AUTO");
				  
//				  Select s1=new Select(driver.findElement(By.id("userPrefRegionsId")));
//				  s1.selectByValue("EMEA");
//				  driver.findElement(By.id("selectAll")).click();
				  
//				  Thread.sleep(3000);
//				  driver.findElement(By.id("applyButtonId")).click();
				  
				  ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
                  driver.switchTo().window(windowHandles.get(0));
                 
                  Thread.sleep(3000);
                 
                WebElement drop7= driver.findElement(By.xpath("//*[@id=\"countriesPanel\"]/div[1]/label/span"));
                Actions actions = new Actions(driver);
                  actions.moveToElement(drop7).click().perform();
                  actions.moveToElement(drop7).click().perform();
                 
               
                Thread.sleep(3000);
                driver.findElement(By.id("applyButtonId")).click();
				  
				    for(int i=1;i<=rowCount;i++)
				    {
				    	  try {
				    	
				    	 Cell c1=sh.getRow(i).getCell(0);
				    	 
				    	 c1.setCellType(Cell.CELL_TYPE_STRING);
							
				            String tracking=c1.getStringCellValue();
				            
				            Thread.sleep(3000);
				            
				    		WebElement search=driver.findElement(By.id("errCorrFormId:templateSearchId"));
				    		search.clear();
				    		search.click();
				    		search.sendKeys(tracking);
				    		search.sendKeys(Keys.ENTER);
				    		
				    		Thread.sleep(5000);
				    		System.out.println(tracking);
				    		
				    	     Thread.sleep(5000);

				
				
				
				
					Thread.sleep(10000);
					
					WebElement tablebody=driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody"));
					List<WebElement> rows=tablebody.findElements(By.tagName("tr"));
					
					
					
					System.out.println("size"+rows.size());
					if(rows.size()==1) {
					driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
					}
					else {
					for(int m=1;m<=rows.size();m++) {
						
					
				                            
					System.out.println(m);	
					driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr["+m+"]/td[1]/label/span")).click();
					
					System.out.println("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr["+m+"]/td[1]/label/span");
					
					//}
					}  
					}
					
					driver.findElement(By.id("submitButtonId")).click();
					System.out.println("clicked");
					try {
						WebElement frame=driver.findElement(By.xpath("//*[@id=\"transactionSuccessModalId\"]"));
						driver.switchTo().frame(frame);
						driver.findElement(By.xpath("//*[@id=\"okButtonId\"]")).click();
						
					}
					catch(Exception e) {
						System.out.println("Error");
					}
					Thread.sleep(10000);
					////*[@id="overrideTypeCode_id"]
					XSSFRow row=sh.getRow(i);
					Cell upd=row.getCell(1);
					upd.setCellValue("overriden");
					
					FileOutputStream fs1 = new FileOutputStream(FilePath);
					
					wb.write(fs1);
					fs1.close();
				//}
				    }	
				    catch(Exception e) {
				    	e.printStackTrace();
				    	try {
				    		System.out.println("hii");
				    		Thread.sleep(5000);
				    	driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
						driver.findElement(By.id("submitButtonId")).click();
						
				    	}
				    	catch(Exception e1) {
				    		e1.printStackTrace();
						XSSFRow row=sh.getRow(i);
						Cell upd=row.getCell(1);
						upd.setCellValue("Error Occured");
						
						FileOutputStream fs1 = new FileOutputStream(FilePath);
						
						wb.write(fs1);
						fs1.close();
						continue;
				    	}
				    	try {
				    		Thread.sleep(5000);
					    	driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr[1]/td[1]/label/span")).click();
					    	driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr[2]/td[1]/label/span")).click();
							
					    	driver.findElement(By.id("submitButtonId")).click();
							////*[@id="transactionCorrectionDynamicTableId"]/tbody/tr[1]/td[1]/label/span
					    	////*[@id="transactionCorrectionDynamicTableId"]/tbody/tr[2]/td[1]/label/span
					    	
				    	}
				    	catch(Exception e2) {
				    		continue;
				    	}
				    	try {
				    		//*[@id="transactionCorrectionDynamicTableId"]/tbody/tr/td[1]/label/span
                         driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
							
					    	driver.findElement(By.id("submitButtonId")).click();
				
				    	}
				    	catch(Exception e2) {
				    		continue;
				    	}
				    	}
				    	
				    }
	}

*/

