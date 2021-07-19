package ThreadeMass;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class threadEmass extends Thread{

	config c;
	driverClass dc;
	validateClass vc;
	data d;
	
	ArrayList<data> dataArray,dataArrayPup,dataArrayStat65,dataArrayPod;
	
	WebDriver driver;
	WebDriverWait wait;

	String result,emassCaseData, svcType,descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2,emassDelAddress,emassPackageType,
	region , login , password, rsType , company , prerate,workable, rowNumber,length,width,height,actualWeight,browser,homePath,levelUrl,headless,databaseDisabled,source,
	level, emassOriginCd, emassPupEmpId,  emassPupRoute, emassFormId,  emassCosmoNbr, emassStopType,  emassDestCityShort, emassDestCountryCd, emassDestCountryPostal, emassBaseSvc,
	emassPodDestCd, emassPodRoute, emassReceivedBy,   emassDelLoc, emassSigRecLineNbr, emassSigRecId,   emassStatDestCd, emassStatEmpId, emassStandardExport,emassHandlingCd;
	
	int attempts=0,waitTime,runningCounter,maxAttempts;

	public threadEmass(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl="https://test.secure.fedex.com/L3/eShipmentGUI/DisplayLinkHandler?id=351";
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"emass");
	}
	
	public void run () {
		
		for(data d: dataArray) {
			this.trkngnbr=d.getTrkngnbr();
			this.testInputNbr=d.getTestInputNbr();
			this.emassCaseData = d.getEmassCaseData();
			
			switch (emassCaseData) {
				case "1":
					vc.setFlag("emass_pup");
					break;
				case "2":
					vc.setFlag("emass_stat65");
				 	break;
				case "3":
					vc.setFlag("emass_pod");
					break;
			 }
			 
			if(vc.validateSep(testInputNbr,trkngnbr)==true) {
				d.setRunningResult("true");
			}
			System.out.println("Tracking Number="+trkngnbr+" -- Emass Type="+emassCaseData+ " -- Emass Result="+d.getRunningResult());
		}
		try {
			emassPup();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void login() {
	    	
		//Will See if even need to login.. if we can navigate to home page then skips.
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
			driver.findElement(By.id("username")).sendKeys("5194105");
			driver.findElement(By.id("password")).sendKeys("5194105");
			driver.findElement(By.id("submit")).click();
		}
		catch(Exception e) {
			System.out.println("Can not login");
		}
	 }
	
	//Start Emass
	public void emassPup() throws InterruptedException {
	    	levelUrl="https://test.secure.fedex.com/L3/eShipmentGUI/DisplayLinkHandler?id=351";
	    	String tempLocCd="";
	    	for(data d: dataArray) {
	    		 this.emassOriginCd=d.getEmassOriginCd();
				 this.emassPupEmpId=  d.getEmassPupEmpId();
				 this.emassPupRoute= d.getEmassPupRoute();
				 this.emassFormId=  d.getEmassFormId();
				 this.emassCosmoNbr= d.getEmassCosmoNbr();
				 this.emassStopType=  d.getEmassStopType();
				 this.emassDestCityShort= d.getEmassDestCityShort();
				 this.emassDestCountryCd=d.getEmassDestCountryCd();
				 this.emassDestCountryPostal=d.getEmassDestCountryPostal();
				 this.emassBaseSvc=d.getEmassBaseSvc();
				 this.emassPackageType=d.getEmassPackageType();
				 this.emassHandlingCd=d.getEmassHandlingCd();
				 this.emassDelAddress=d.getEmassDelAddress();
				 this.emassPodDestCd= d.getEmassPodDestCd();
				 this.emassPodRoute= d.getEmassPodRoute();
				 this.emassReceivedBy=d.getEmassReceivedBy();
				 this.emassDelLoc=d.getEmassDelLoc();
				 this.emassSigRecLineNbr=d.getEmassSigRecLineNbr();
				 this.emassSigRecId=  d.getEmassSigRecId();
				 this.emassStatDestCd=d.getEmassStatDestCd();
				 this.emassStatEmpId= d.getEmassStatEmpId();
				 this.emassStandardExport=d.getEmassStandardExport();
				 this.trkngnbr=d.getTrkngnbr();
				 this.testInputNbr=d.getTestInputNbr();
	    		
				 if (!d.getEmassOriginCd().equals(tempLocCd)) {
					 tempLocCd=d.getEmassOriginCd();
					 login();
					 
					 driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td[2]/table/tbody/tr[1]/td/input")).sendKeys(d.getEmassOriginCd());
					 driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td[2]/table/tbody/tr[1]/td/button")).click();
					
					 //Remove when wsso is done.
					 driver.get("https://test.secure.fedex.com/L3/Event-Entry-5539C/");
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[1]/div/div/div/ul/li[4]")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath(" /html/body/table/tbody/tr[2]/td/form/span[1]/div/div/div/ul/li[4]/ul/li[1]/a/span/span")).click();
					
					 try {
					 emassInput(" /html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/table/tbody/tr/td/div/div[2]/table/tbody/tr[3]/td[2]/input",d.getEmassPupEmpId());
					 emassInput(" /html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/table/tbody/tr/td/div/div[2]/table/tbody/tr[5]/td[2]/input",d.getEmassPupRoute());
					 emassInput(" /html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[5]/div/table/tbody/tr[1]/td/span/input",d.getTrkngnbr());	
					 emassInput(" /html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[5]/div/table/tbody/tr[2]/td/span/input",d.getEmassFormId());
					 emassInput(" /html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[9]/div/table/tbody/tr[2]/td/span/input",d.getEmassCosmoNbr());
						
					 Select sel = new Select(driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[10]/div/table/tbody/tr/td/span/select")));
					 sel.selectByValue("R");
					 
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[1]/td/span/input")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[1]/td/span/input")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[1]/td/span/input")).sendKeys(d.getEmassDestCityShort());
					 Thread.sleep(2000);	
					 
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[2]/td/span/input")).click();
					 Thread.sleep(2000);
					
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[2]/td/span/input")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[2]/td/span/input")).sendKeys(d.getEmassDestCountryCd());
					 Thread.sleep(2000);
					 
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[3]/td/span/input")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[3]/td/span/input")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[3]/td/span/input")).sendKeys(d.getEmassDestCountryPostal());
						
					 sel = new Select(driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[12]/div/table/tbody/tr[1]/td/span/select")));
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[2]/td/span/input")).click();
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[11]/div/table/tbody/tr[2]/td/span/input")).click();
					
					 sel.selectByValue(d.getEmassBaseSvc());
					 sel = new Select(driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/span[2]/div[1]/div[2]/div/div/div/table/tbody/tr/td[12]/div/table/tbody/tr[2]/td/span/select")));
					 sel.selectByValue(d.getEmassPackageType());
					 }
					 catch(Exception e) {
						 System.out.println(e);
					 }
				}
			}
		}
	public void emassInput(String we,String input) throws InterruptedException {
	    	
	 Thread.sleep(1000); 
	 driver.findElement(By.xpath(we)).click();
	 Thread.sleep(4000);
	 driver.findElement(By.xpath(we)).click();
	 Thread.sleep(2500); 
	 driver.findElement(By.xpath(we)).sendKeys(input);
	 }
}