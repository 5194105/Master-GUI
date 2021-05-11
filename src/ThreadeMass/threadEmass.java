package ThreadeMass;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;


public class threadEmass extends Thread{

	ArrayList<data> dataArray,dataArrayPup;
	config c;
	String homePath;
	WebDriver driver;
	String browser;
	String levelUrl;
	String headless;
	String databaseDisabled;
	WebDriverWait wait;
	String source;
	String result, svcType,descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType , company , prerate,workable, rowNumber,length,width,height,actualWeight;
	int waitTime;
	int attempts=0;
	int maxAttempts;
	String level;
	driverClass dc;
	validateClass vc;
	Boolean running=true;
	int runningCounter;
	data d;
	String emassOriginCd, emassPupEmpId,  emassPupRoute, emassFormId,  emassCosmoNbr, emassStopType,  emassDestCityShort, emassDestCountryCd, emassDestCountryPostal, emassBaseSvc, emassPackageType, emassHandlingCd, emassDelAddress;
	String emassPodDestCd, emassPodRoute, emassReceivedBy,   emassDelLoc, emassSigRecLineNbr, emassSigRecId,   emassStatDestCd, emassStatEmpId, emassStandardExport;

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
			 emassPup();
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

	    
	    	}
	    }
	    public void emassPup() {
	    	levelUrl="https://test.secure.fedex.com/L3/eShipmentGUI/DisplayLinkHandler?id=351";
	    	login();
	    	try {
	    		driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td/form/table[2]/tbody/tr/td/div/table/tbody/tr[2]/td/div/table/tbody/tr/td/div/div[3]/div/div[2]/div[1]")).click();
	    		
	    	}
	    	catch(Exception e) {
	    		
	    	}
	    }
}