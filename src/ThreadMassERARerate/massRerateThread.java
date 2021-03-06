package ThreadMassERARerate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ThreadConfig.data;
import ThreadConfig.driverClass;
import ThreadConfig.validateClass;
import configuration.config;

public class massRerateThread extends Thread{
	config c;
	driverClass dc;
	validateClass vc;
	
	WebDriver driver;
	WebDriverWait wait;

	int waitTime,attempts=0,maxAttempts=3;

	String result,  description, testInputNbr, tinCount, trkngnbr, invoiceNbr1, invoiceNbr2,
	 rateWeight, actualWeight, wgtType,  length, width, height, 
	 workable, dimType, payor, billAcctNbr, serviceType, packageType,
	 rerateType, region, username, password, rsType, company, valDesc,comments,serviceName,massRerateCombo,
	 homePath,browser,levelUrl,headless,databaseDisabled,source,level;
	
	ArrayList<data> dataArray;
	ArrayList<data> dataArray2 = new ArrayList<data>();
	ArrayList<Integer> trkList = new ArrayList<Integer>();
	ArrayList<String> addedTrks = new ArrayList<String>();
	ArrayList<String> removedTrks = new ArrayList<String>();
	
	Boolean execute=false, running=true;
	
	public massRerateThread(ArrayList<data> dataArray,config c) {
		this.dataArray=dataArray;
		this.c=c;
		levelUrl=c.getRebillL3Url();
		databaseDisabled=c.getDatabaseDisabled();
		source=c.getSource();
		waitTime=Integer.parseInt(c.getRebillSecondTimeout());
		level=c.getLevel();
		dc = new driverClass(c,levelUrl,waitTime);
		vc= new validateClass(c,databaseDisabled,"era_rerate_mass");
	}
	public void run () {
		String tempTin="0";
		for(data d: dataArray) {
			if (d.getRunningResult().equals("false")) {
				running=true;
				break;
			}
		}
	
	
		while (running == true) {
			running=false;
			for(data d: dataArray) {
				if (d.getRunningResult().equals("false")) {
					running=true;
					break;
				}
			}
		
			for(data d: dataArray) {
			
				
				
				this.result=d.getResult();
				this.description=d.getDescription();
				this.testInputNbr=d.getTestInputNbr();
				this.tinCount=d.getTinCount();
				this.trkngnbr=d.getTrkngnbr();
				this.invoiceNbr1=d.getInvoiceNbr1();
				this.invoiceNbr2=d.getInvoiceNbr2();
				this.region=d.getRegion();
				this.username=d.getUsername();
				this.password=d.getPassword();
				this.rateWeight=d.getRateWeight();
				this.rerateType=d.getRerateType();
				this.wgtType=d.getWgtType();
				this.length=d.getLength();
				this.width=d.getWidth();
				this.height=d.getHeight();
				this.dimType=d.getDimType();
				this.rsType=d.getRsType();
				this.company=d.getCompany();
				this.massRerateCombo=d.getMassRerateCombo();
				
				
				if (d.getRunningResult().equals("false")) {
					//So this part is going to take all the trks that are part of the same
					//TIN and group them together in an arraylist. We have the tins sorted in
					//our inital query, so now we are just looping through the data.. which 
					//will be in order. If the new tin equals old tin, then we know it is grouped
					//together and will go to next trk. tempTin starts at zero for first tin.
					
					//Here if the tins dont match.. we will start a new tempTin and new ArrayList
					if (!testInputNbr.equals(tempTin)) {
						//So what is the point of this execute boolean? Well if we find another tin number.. we want to add it to new
						//ArrayList and start over... but we need to execute the old array list first. Since the old arraylist has not 
						//executed (cause it didnt know a new tin was coming) it will skip this block of code, execute the current array
						//list, then delete that array list and start it with the the new tin.
						if (execute == false) {
							tempTin=testInputNbr;
							dataArray2.add(d);
							execute=true;
						}
			    		else {
			    			try {
								doMassRerate();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    			//Once done with current array list, we want to tell program its beeen executed..
			    			//now we need to clear it and start over with new tin.
			    			execute=false;
			    			dataArray2.clear();
			    			dataArray2.add(d);
			    		}
			    	}
					//Here is the tin is the same.. just add it to arrayList.
			    	else {
			    		dataArray2.add(d);
			    	}
				}
			}
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
			driver.findElement(By.id("okta-signin-username")).sendKeys(username);
			driver.findElement(By.id("okta-signin-password")).sendKeys(password);
			driver.findElement(By.id("okta-signin-submit")).click();
		}
		catch(Exception e) {
			System.out.println("Unable to login");
		}
	}


	public void doMassRerate( ) throws InterruptedException {
	
		//Will attempt X amount of times.
		maxAttempts=3;
		for (int ii=0;ii<maxAttempts;ii++) {
			login();
			JavascriptExecutor js= (JavascriptExecutor) driver;
			wait=new WebDriverWait(driver,20);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		try {
			//In order for clear button to be clickable need to scroll up
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		    
		    Thread.sleep(3000);
		    int count=0;
		    //Will click on mass adjustments 10 times then just give up
		    while (count < 10) {
		    	count++;
		    	try {
		    		driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[1]/ul/li[12]/a")).click();
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea"));
		    		break;
			    	}
			    catch(Exception e) {
			    	try {
			    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea"));
			    	}
			    	catch(Exception ee) {}
			    	}
			 }
		    if (count>=10) {
		    	for(data d:dataArray2) {
		    		vc.writeToDb(d.getTestInputNbr(),d.getTinCount(),d.getTrkngnbr(),"fail","Unknown Error",null);
		    	}
		    }
			Thread.sleep(3000);
			//Will click on mass rerates
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[2]/button[3]")).click();
			Thread.sleep(3000);
			//Will type the trk number to text area
		
			String wgt_type="";
			String rate_weight="";
			String dim_type="";
			
			//Clear the trk text area
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).clear();
		
			for(data d:dataArray2) {
				//Printing trk to text area
	    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(d.getTrkngnbr());
    			//Going to next line
	    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[1]/div[5]/div/div/textarea")).sendKeys(Keys.chord(Keys.SHIFT,Keys.ENTER));
    			rerateType=d.getRerateType();
    			wgt_type=d.getWgtType();
    			rate_weight=d.getRateWeight();
    			dim_type=d.getDimType();
			}
		
		    //Will click on go
		    driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[2]/div[1]/button[1]")).click();
		    Thread.sleep(3000);
		    
		    //Will click on trk
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[6]/div[1]/div/div/div[1]/div/div[1]/div/div/div/div/div/div/div/div/div")).click();
		    Thread.sleep(3000);
		    String lowercaseRerateType=rerateType.toLowerCase();
		    switch(lowercaseRerateType) {
    
		    	//Rating
		    	case "rating" :
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[2]")).click();
		    		break;
    
		    	//weight
		    	case "weight" :
    		
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[3]")).click();
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[2]/div/div/input")).sendKeys(rate_weight);
    		
		    		if (wgt_type.equals("LB")) {
		    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[3]/div[1]/div/label/span")).click();
		    		}
		    		
		    		if (wgt_type.equals("KG")) {
		    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[3]/div[2]/div/label/span")).click();
		    		}
    		
		    		break;
    
		    	//Dims
		    	case "dim" :
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[1]/div/div/div/div/div/select/option[4]")).click();
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[1]/div/input")).sendKeys(length);
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[2]/div/input")).sendKeys(width);
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[2]/div/div/div/div[3]/div/input")).sendKeys(height);
		    		
		    		if (dim_type.equals("IN")) {
		    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[4]/div[1]/div/label/span")).click();
		    		}
		    		
		    		if (dim_type.equals("CM")) {
		    			driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[4]/div[4]/div[2]/div/label/span")).click();
		    		}
		    		break;
    
		    	//service
		    	case "service" :
		    		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[3]/div/div/div/div/div/select/optgroup[5]/option[2]")).click();
    		}
	    
	    Thread.sleep(3000);

	    //Click on rerate
	    driver.findElement(By.xpath(" /html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div/div/div/form/div/div/div[3]/div[5]/div/div/button")).click();
		
	    Thread.sleep(3000);
		//Getting request ID
	    String temp=driver.findElement(By.xpath(" /html/body/div[7]/div/div/div[1]/h3")).getText();;
		String requestID=temp.replaceAll("\\D+","");

		for (data d : dataArray2) {
			vc.writeToDb(d.getTestInputNbr(),d.getTinCount(),d.getTrkngnbr(),"In Progress","Rerate Created",requestID);
			d.setRunningResult("true");
		}
		//End of program
		return;
		} 
		catch(Exception e) {
			System.out.println(e);
				if(databaseDisabled.equals("false")) {
					for (data d : dataArray2) {
						vc.writeToDb(d.getTestInputNbr(),d.getTinCount(),d.getTrkngnbr(),"fail","Unknown Error",null);
						d.setRunningResult("true");
					}
					return;
				 }
			}
		}
	}
}


