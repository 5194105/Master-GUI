package ThreadSingleERARerate;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class eraRerateUpload extends Thread{
	
	ArrayList<data> dataArray;
	config c;
	String homePath;
	WebDriver driver;
	String browser;
	String levelUrl;
	String headless;
	String databaseDisabled;
	WebDriverWait wait;
	String source;
	String result, descripiton, testInputNbr, tinCount, trkngnbr, reasonCode, rebillAccount, invoiceNbr1, invoiceNbr2, region , login , password, rsType , company , prerate,workable, rowNumber,length,width,height,actualWeight;
	int waitTime;
	int attempts=0;
	int maxAttempts;
	String level;
	driverClass dc;
	validateClass vc;
	
	public eraRerateUpload(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		databaseDisabled=c.getDatabaseDisabled();
		level=c.getLevel();
		vc= new validateClass(c,databaseDisabled,"era_rerate");
}
	
	
public void run () {
		
		for(data d: dataArray) {
			
			//Declare Vars
			 
			testInputNbr=d.getTestInputNbr();
			tinCount=d.getTinCount();
			trkngnbr=d.getTrkngnbr();
			
			
			//Check if track is already successful
			
		    
		    if (vc.validateRerate(testInputNbr,tinCount,trkngnbr)==true) {
		    	continue;
		    }
		}
}
}
