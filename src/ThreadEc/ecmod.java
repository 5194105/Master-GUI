package ThreadEc;





import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.ecData;
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
	Boolean ecOverrideUd;
	Boolean firstTry=true;
	public ecmod(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		this.dataArray=dataArray;
		this.c=c;
		levelUrl="https://testsso.secure.fedex.com/L3/revportal/home";
		databaseDisabled=c.getDatabaseDisabled();
		//source=c.getSource();
		waitTime=10;
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
			ecOverrideUd=d.getEcOverrideUd();
			System.out.println("EC TRK: "+trkngnbr);
			try {
				if (ecOverride==true || c.getRunAllEc().equals("true") || ecOverrideUd==true) {
					if (ecWorkType.equals("INTL_AUTO") || ecWorkType.equals("INTL_AB"))
					getEc(d);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void login() {
		try {
			try { 
				driver.manage().deleteAllCookies(); //delete all cookies
				Thread.sleep(7000);
				for(Cookie ck : driver.manage().getCookies())							
	            {			
	               System.out.println(ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure());																									
	                        
	            }	
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
	
	public void getEc(data d) throws InterruptedException {
	System.out.println();
		
		if (firstTry==true) {
			login();
			}

if (firstTry==false) {
		try {
			WebElement frame=driver.findElement(By.xpath("//*[@id=\"transactionSuccessModalId\"]"));
			driver.switchTo().frame(frame);
			driver.findElement(By.xpath("//*[@id=\"okButtonId\"]")).click();
		}
		catch(Exception e) {
			System.out.println("Error");
			System.out.println(e);
			login();
		}
}
else {
	firstTry=false;
}
		
		
		
	try {
		driver.findElement(By.xpath("//*[@id=\"ECGUI\"]/img")).click();
				/*
				 File FilePath = new File("E:\\ErrorCorrection\\EC_OVR\\ER_input_1.xlsx");
				 FileInputStream fs = new FileInputStream(FilePath);
				 XSSFWorkbook wb = new XSSFWorkbook(fs);

				XSSFSheet sh= wb.getSheetAt(0);
					int rowCount=  sh.getLastRowNum()-sh.getFirstRowNum();
					
				    System.out.println(rowCount);
				    */
					
				  Select s=new Select(driver.findElement(By.id("workTypeId")));
				  
				  if (ecWorkType.equals("INTL_AUTO")) {
				  s.selectByValue("INTL_AUTO");
				  }
				  else if (ecWorkType.equals("INTL_AB")) {
					  s.selectByValue("INTL_AB");
					  }
				 
				  
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
					
					
					if (ecOverrideUd==true) {
						try {
							WebElement ecElement = null;
							for (ecData ed : d.getEcDataArray()) {
								if(!ed.getEcField().equals("override")) {
							switch (ed.getEcField()){
							case "dest_country":
								ecElement=	driver.findElement(By.id("destinationCountryId"));
								break;
							case "uom":
								ecElement=	driver.findElement(By.id("generalUomId"));
								break;
							case "nbr_addr":
								ecElement=	driver.findElement(By.id("nbrAddressId"));
								break;
							case "credit_card_num":
								ecElement=	driver.findElement(By.id("creditCardID"));
								break;
							case "cc_expr_date":
								ecElement=	driver.findElement(By.id("expirationDateId"));
								break;
							case "box_count":
								ecElement=	driver.findElement(By.id("boxCountId"));
								break;
							case "handl_units":
								ecElement=	driver.findElement(By.id("handlingUnitsId"));
								break;
							case "dec_carr_value":
								ecElement=	driver.findElement(By.id("generalDeclaredValueId"));
								break;
							case "curr2":
								ecElement=	driver.findElement(By.id("generalCurrencyTypeId"));
								break;
							case "cust_value":
								ecElement=	driver.findElement(By.id("customerValueId"));
								break;
							case "cust_cur":
								ecElement=	driver.findElement(By.id("customerCurrencyCodeId"));
								break;
							case "form_id":
								ecElement=	driver.findElement(By.id("formId"));
								break;
							case "ship_date":
								ecElement=	driver.findElement(By.id("shipDateId"));
								break;
							case "pickup_stop_type":
								ecElement=	driver.findElement(By.id("pickupStopTypeId"));
								break;
							case "s_name":
								ecElement=	driver.findElement(By.id("shipperNameId"));
								break;
							case "s_account":
								ecElement=	driver.findElement(By.id("shipperAccountNumberId"));
								break;
							case "s_company":
								ecElement=	driver.findElement(By.id("shipperCompanyNameId"));
								break;
							case "s_address1":
								ecElement=	driver.findElement(By.id("shipperAddressId_1"));
								break;
							case "s_address2":
								ecElement=	driver.findElement(By.id("shipperAddressId_2"));
								break;
							case "s_city":
								ecElement=	driver.findElement(By.id("shipperCityId"));
								break;
							case "s_state":
								ecElement=	driver.findElement(By.id("shipperStateId"));
								break;
							case "s_zip":
								ecElement=	driver.findElement(By.id("shipperZipCodeId"));
								break;
							case "s_reference":
								ecElement=	driver.findElement(By.id("referenceId_1"));
								break;
							case "s_country":
								ecElement=	driver.findElement(By.id("shipperCountryId"));
								break;
							case "r_name":
								ecElement=	driver.findElement(By.id("recipientNameId"));
								break;
							case "r_company":
								ecElement=	driver.findElement(By.id("recipientCompanyNameId"));
								break;
							case "r_address1":
								ecElement=	driver.findElement(By.id("recipientAddressId_1"));
								break;
							case "r_address2":
								ecElement=	driver.findElement(By.id("recipientAddressId_2"));
								break;
							case "r_city":
								ecElement=	driver.findElement(By.id("recipientCityId"));
								break;
							case "r_state":
								ecElement=	driver.findElement(By.id("recipientStateId"));
								break;
							case "r_zip":
								ecElement=	driver.findElement(By.id("recipientZipCodeId"));
								break;
							case "r_country":
								ecElement=	driver.findElement(By.id("recipientCountryId"));
								break;
							case "payor":
								ecElement=	driver.findElement(By.id("paymentPayerId"));
								break;
							case "method":
								ecElement=	driver.findElement(By.id("paymentMethodId"));
								break;
							case "rec_third_acct":
								ecElement=	driver.findElement(By.id("paymentRecAccountNumberId"));
								break;
							case "tot_pymt_amt":
								ecElement=	driver.findElement(By.id("totalPaymentAmountId"));
								break;
							case "curr":
								ecElement=	driver.findElement(By.id("currencyCodeId"));
								break;
							case "base":
								ecElement=	driver.findElement(By.id("serviceBaseId"));
								break;
							case "pkg_type":
								ecElement=	driver.findElement(By.id("packageTypeId"));
								break;
							case "packages":
								ecElement=	driver.findElement(By.id("generalTotalPackagesId"));
								break;
							case "total_weight":
								ecElement=	driver.findElement(By.id("generalTotalWeightId"));
								break;
							case "origin_loc":
								ecElement=	driver.findElement(By.id("originLocationId"));
								break;
							case "origin_country":
								ecElement=	driver.findElement(By.id("originCountryId"));
								break;
							case "dest_loc":
								ecElement=	driver.findElement(By.id("destinationLocationId"));
								break;
							}
							ecElement.clear();
							ecElement.sendKeys(ed.getEcValue());
					}
							}
						}
						catch(Exception e) {
							System.out.println(e);
						}
					
					}
					
					
					
					WebElement tablebody=driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody"));
					List<WebElement> rows=tablebody.findElements(By.tagName("tr"));
					
					
					
					System.out.println("size"+rows.size());
					
					
					try {
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
					}
					catch(Exception e) {
						System.out.println("could not click checkbox");
					}
					
					
					
					
					driver.findElement(By.id("submitButtonId")).click();
					System.out.println("clicked");
					
					Thread.sleep(10000);
					
					
					
					
					
					
					
			
					
					
					


					
					
					
					
					
					
					
					
					if (driver.findElement(By.id("trackingNbrId")).getText().equals(d.getTrkngnbr())) {
					try {
					
						 tablebody=driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody"));
						 rows=tablebody.findElements(By.tagName("tr"));
						
						
						
						System.out.println("size"+rows.size());
						
						
						try {
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
						
					}
						
						catch(Exception e) {
							System.out.println("could not click checkbox");
						}
					}
					catch(Exception e) {
						System.out.println("could not click checkbox");
					}
	}
				
					
					
					
					
					try {
						WebElement frame=driver.findElement(By.xpath("//*[@id=\"transactionSuccessModalId\"]"));
						driver.switchTo().frame(frame);
						driver.findElement(By.xpath("//*[@id=\"okButtonId\"]")).click();
						
					}
					catch(Exception e) {
						System.out.println("Error");
					}		
					
					
					
					
					
					
					
					
					
					
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



/*

WebElement ecElement
switch (temp){
case "dest_country":
	ecElement=	driver.findElement(By.id("destinationCountryId"));
	break;
case "uom":
	ecElement=	driver.findElement(By.id("generalUomId"));
	break;
case "nbr_addr":
	ecElement=	driver.findElement(By.id("nbrAddressId"));
	break;
case "credit_card_num":
	ecElement=	driver.findElement(By.id("creditCardID"));
	break;
case "cc_expr_date":
	ecElement=	driver.findElement(By.id("expirationDateId"));
	break;
case "box_count":
	ecElement=	driver.findElement(By.id("boxCountId"));
	break;
case "handl_units":
	ecElement=	driver.findElement(By.id("handlingUnitsId"));
	break;
case "dec_carr_value":
	ecElement=	driver.findElement(By.id("generalDeclaredValueId"));
	break;
case "curr2":
	ecElement=	driver.findElement(By.id("generalCurrencyTypeId"));
	break;
case "cust_value":
	ecElement=	driver.findElement(By.id("customerValueId"));
	break;
case "cust_cur":
	ecElement=	driver.findElement(By.id("customerCurrencyCodeId"));
	break;
case "form_id":
	ecElement=	driver.findElement(By.id("formId"));
	break;
case "ship_date":
	ecElement=	driver.findElement(By.id("shipDateId"));
	break;
case "pickup_stop_type":
	ecElement=	driver.findElement(By.id("pickupStopTypeId"));
	break;
case "s_name":
	ecElement=	driver.findElement(By.id("shipperNameId"));
	break;
case "s_account":
	ecElement=	driver.findElement(By.id("shipperAccountNumberId"));
	break;
case "s_company":
	ecElement=	driver.findElement(By.id("shipperCompanyNameId"));
	break;
case "s_address1":
	ecElement=	driver.findElement(By.id("shipperAddressId_1"));
	break;
case "s_address2":
	ecElement=	driver.findElement(By.id("shipperAddressId_2"));
	break;
case "s_city":
	ecElement=	driver.findElement(By.id("shipperCityId"));
	break;
case "s_state":
	ecElement=	driver.findElement(By.id("shipperStateId"));
	break;
case "s_zip":
	ecElement=	driver.findElement(By.id("shipperZipCodeId"));
	break;
case "s_reference":
	ecElement=	driver.findElement(By.id("referenceId_1"));
	break;
case "s_country":
	ecElement=	driver.findElement(By.id("shipperCountryId"));
	break;
case "r_name":
	ecElement=	driver.findElement(By.id("recipientNameId"));
	break;
case "r_company":
	ecElement=	driver.findElement(By.id("recipientCompanyNameId"));
	break;
case "r_address1":
	ecElement=	driver.findElement(By.id("recipientAddressId_1"));
	break;
case "r_address2":
	ecElement=	driver.findElement(By.id("recipientAddressId_2"));
	break;
case "r_city":
	ecElement=	driver.findElement(By.id("recipientCityId"));
	break;
case "r_state":
	ecElement=	driver.findElement(By.id("recipientStateId"));
	break;
case "r_zip":
	ecElement=	driver.findElement(By.id("recipientZipCodeId"));
	break;
case "r_country":
	ecElement=	driver.findElement(By.id("recipientCountryId"));
	break;
case "payor":
	ecElement=	driver.findElement(By.id("paymentPayerId"));
	break;
case "method":
	ecElement=	driver.findElement(By.id("paymentMethodId"));
	break;
case "rec_third_acct":
	ecElement=	driver.findElement(By.id("paymentRecAccountNumberId"));
	break;
case "tot_pymt_amt":
	ecElement=	driver.findElement(By.id("totalPaymentAmountId"));
	break;
case "curr":
	ecElement=	driver.findElement(By.id("currencyCodeId"));
	break;
case "base":
	ecElement=	driver.findElement(By.id("serviceBaseId"));
	break;
case "pkg_type":
	ecElement=	driver.findElement(By.id("packageTypeId"));
	break;
case "packages":
	ecElement=	driver.findElement(By.id("generalTotalPackagesId"));
	break;
case "total_weight":
	ecElement=	driver.findElement(By.id("generalTotalWeightId"));
	break;
case "origin_loc":
	ecElement=	driver.findElement(By.id("originLocationId"));
	break;
case "origin_country":
	ecElement=	driver.findElement(By.id("originCountryId"));
	break;
case "dest_loc":
	ecElement=	driver.findElement(By.id("destinationLocationId"));
	break;
}



WebElement dest_country
WebElement uom
WebElement nbr_addr
WebElement credit_card_num
WebElement cc_expr_date
WebElement box_count
WebElement handl_units
WebElement dec_carr_value
WebElement curr2
WebElement cust_value
WebElement cust_cur
WebElement form_id
WebElement ship_date
WebElement pickup_stop_type
WebElement s_name
WebElement s_account
WebElement s_company
WebElement s_address1
WebElement s_address2
WebElement s_city
WebElement s_state
WebElement s_zip
WebElement s_reference
WebElement s_country
WebElement r_name
WebElement r_company
WebElement r_address1
WebElement r_address2
WebElement r_city
WebElement r_state
WebElement r_zip
WebElement r_country
WebElement payor
WebElement method
WebElement rec_third_acct
WebElement tot_pymt_amt
WebElement curr
WebElement base
WebElement pkg_type
WebElement packages
WebElement total_weight
WebElement origin_loc
WebElement origin_country
WebElement dest_loc
*/