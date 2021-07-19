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
	config c;
	driverClass dc;
	validateClass vc;
	
	Select s;
	
	ArrayList<data> dataArray;
	ArrayList<String> windowHandles;
	
	String levelUrl,databaseDisabled,source,level,trkngnbr,ecWorkType;
	
	int waitTime,attempts=0,maxAttempts=3;

	WebDriver driver;
	WebDriverWait wait;
	WebElement tempElement,ecElement;
	List<WebElement> tempRows;
	
	Actions actions;
	
	Boolean ecOverride,ecOverrideUd,firstTry=true;

	public ecmod(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl="https://testsso.secure.fedex.com/L3/revportal/home";
		databaseDisabled=c.getDatabaseDisabled();
		waitTime=10;
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"ec");
	}
	
	public void run () {
		
		for (data d: dataArray) {
			trkngnbr=d.getTrkngnbr();
			ecWorkType=d.getEcWorkType();
			ecOverride=d.getOverride();
			ecOverrideUd=d.getEcOverrideUd();
			System.out.println("EC TRK: "+trkngnbr);
			try {
				//This is defined on base and run class
				if (ecOverride==true || c.getRunAllEc().equals("true") || ecOverrideUd==true) {
					if (ecWorkType.equals("INTL_AUTO") || ecWorkType.equals("INTL_AB"))
						getEc(d);
				}
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void login() {
		try {
			try { 
				//This is to help login.. deleting cookies.
				driver.manage().deleteAllCookies(); //delete all cookies
				Thread.sleep(7000);
				for(Cookie ck : driver.manage().getCookies()){			
					//System.out.println(ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure());																									
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
			driver.findElement(By.id("username")).sendKeys("5194105");;
			driver.findElement(By.id("password")).sendKeys("5194105");
			driver.findElement(By.id("submit")).click();
		}
		catch(Exception e) {
			 System.out.println(e);
		}
	}
	
	
	public void getEc(data d) throws InterruptedException {
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
				System.out.println(e);
				login();
			}
		}
		else {
			firstTry=false;
		}
		
		try {
			driver.findElement(By.xpath("//*[@id=\"ECGUI\"]/img")).click();
			s=new Select(driver.findElement(By.id("workTypeId")));
			
			if (ecWorkType.equals("INTL_AUTO")) {
				s.selectByValue("INTL_AUTO");
			}
			else if (ecWorkType.equals("INTL_AB")) {
				s.selectByValue("INTL_AB");
			}

			windowHandles = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(windowHandles.get(0));
            Thread.sleep(3000);
            tempElement= driver.findElement(By.xpath("//*[@id=\"countriesPanel\"]/div[1]/label/span"));
            actions = new Actions(driver);
            actions.moveToElement(tempElement).click().perform();
            actions.moveToElement(tempElement).click().perform();
            Thread.sleep(3000);
            driver.findElement(By.id("applyButtonId")).click();
			
            try {
            	Thread.sleep(3000);
            	tempElement=driver.findElement(By.id("errCorrFormId:templateSearchId"));
            	tempElement.clear();
            	tempElement.click();
            	tempElement.sendKeys(trkngnbr);
            	tempElement.sendKeys(Keys.ENTER);
				Thread.sleep(15000);
            }
            catch(Exception e) {
            	System.out.println("Could not enter the tracking number");
            }
				if (ecOverrideUd==true) {
					try {
						ecElement = null;
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
				
				//Override and submit method
				overrideAndSubmit();
				if (driver.findElement(By.id("trackingNbrId")).getText().equals(d.getTrkngnbr())) {
					overrideAndSubmit();
				}  
			}
            catch(Exception e) {
            	System.out.println("Failed on correction screen");
            }
		
		
			//Dont know what happens after this
		
		
			try {
				tempElement=driver.findElement(By.xpath("//*[@id=\"transactionSuccessModalId\"]"));
				driver.switchTo().frame(tempElement);
				driver.findElement(By.xpath("//*[@id=\"okButtonId\"]")).click();
			}
			catch(Exception e) {
				System.out.println("Error");
			}
			
			try {
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
				driver.findElement(By.id("submitButtonId")).click();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr[1]/td[1]/label/span")).click();
				driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr[2]/td[1]/label/span")).click();
				driver.findElement(By.id("submitButtonId")).click();
			}
			catch(Exception e2) {
				return;
			}
			
			try {
				driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr/td[1]/label/span")).click();
				driver.findElement(By.id("submitButtonId")).click();
			}
			catch(Exception e2) {
				return;
		}
	}

	
	
	
	
	public void overrideAndSubmit() {
		try {
			tempElement=driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody"));
			tempRows=tempElement.findElements(By.tagName("tr"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			for(int m=1;m<=tempRows.size();m++) {
				driver.findElement(By.xpath("//*[@id=\"transactionCorrectionDynamicTableId\"]/tbody/tr["+m+"]/td[1]/label/span")).click();
			}  
		}
		catch(Exception e) {
			System.out.println("could not click checkbox");
		}
		
		try {
			//Submit
			driver.findElement(By.id("submitButtonId")).click();
			Thread.sleep(10000);
		}
		catch(Exception e) {
			System.out.println("Cound not click submit");
		}
	}	
}
	
	
