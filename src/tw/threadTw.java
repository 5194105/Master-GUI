package tw;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;


public class threadTw {
	
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
	
	public threadTw(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl="twitch.tv/drsarco";
		databaseDisabled="true";
		source="";
		waitTime=10;
		dc = new driverClass(c,levelUrl,waitTime);
		//vc= new validateClass(c,databaseDisabled,"era_rebill");
	}
	
public void run () {
		
		for(data d: dataArray) {
			loginToT();
			}
		}
public void loginToT() {
	driver=dc.getDriver();
    driver.get(levelUrl);
    driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
	wait = new WebDriverWait(driver,waitTime);
	driver.manage().window().maximize();
}
}



