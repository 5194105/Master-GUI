package ThreadTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.config;

public class driverClass {
	WebDriver driver=null;
	config c;
	String browser;
	String headless;
	String compatibleMode;
	String homePath;
	String levelUrl;
	int waitTime;
	public driverClass(config c,String levelUrl,int waitTime) {
		this.c=c;
		this.levelUrl=levelUrl;
		this.waitTime=waitTime;
		browser=c.getDriverType();
		headless=c.getHeadlessString();
		compatibleMode=c.getCompatibleMode();
		homePath=System.getProperty("user.dir");
		
		
	}
	
	
	
public void setDriver() {
		
		if (browser.equals("1")) {
    		if (compatibleMode.equals("true")) {	
    			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
    		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    		    capabilities.setCapability("ignoreZoomSetting", true);
    		    capabilities.setCapability("ignoreProtectedModeSettings", true);
    		    capabilities.setCapability("initialBrowserUrl",levelUrl);
    		}
    		
    		System.out.println(c.getIeProperty()+"     "+c.getIeDriverPath());
    		System.setProperty(c.getIeProperty(), c.getIeDriverPath());
    		driver =  new InternetExplorerDriver();
    	}
    	
    	
    	else if (browser.equals("2")) {
    		//System.out.println(c.getChromeProperty());
    		//System.out.println(c.getChromePath());
    		System.setProperty(c.getChromeProperty(),c.getChromePath());
    		driver = new ChromeDriver();
    	/*
    		if(headless.equals("true")) {
    		ChromeOptions options = new ChromeOptions();
    	    options.addArguments("headless");
    	    options.addArguments("window-size=1200x600");
    		driver = new ChromeDriver(options);
    	}
    	else {
    		driver = new ChromeDriver();
    	}
    		*/
    	}
    	
    	
    	else if (browser.equals("3")) {
    
        	FirefoxProfile profile = new FirefoxProfile(); 
        	profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
        	profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");
        	profile.setAcceptUntrustedCertificates(true); profile.setAssumeUntrustedCertificateIssuer(true);
        	DesiredCapabilities capabilities = new DesiredCapabilities(); 
        	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        	System.setProperty("webdriver.gecko.driver",  homePath+"\\drivers\\geckodriver.exe");
        	driver = new FirefoxDriver(capabilities);
    	}
    	
		WebDriverWait wait=new WebDriverWait(driver,20);
    	driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);
	
	}
	
	public WebDriver getDriver() {
		setDriver();
		return driver;
	}
	
	
}
