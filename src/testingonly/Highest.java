package testingonly;
import configuration.config;
import prerate.prerateTestNGSlow;
import rebill.rebillData;
import rebill.testngRebillSlow;
import rebill.testngRebillSlowMfRetire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Highest {

	 

	static ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
    public static void main(String[] args) throws InterruptedException {
   
    	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
	    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    capabilities.setCapability("ignoreZoomSetting", true);
	    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    capabilities.setCapability("initialBrowserUrl","https://testsso.secure.fedex.com/L3/PRSApps");
    	System.setProperty("webdriver.ie.driver", "C:\\Users\\FedExUser\\git\\MasterGUI\\drivers\\IEDriverServer.exe");
    	
    	WebDriver driver =  new InternetExplorerDriver();
    	
    	driver.get("https://testsso.secure.fedex.com/L3/PRSApps");
    	Thread.sleep(5000);
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	  	//driver.manage().window().maximize();     
		Thread.sleep(5000);
	
	//	/html/body/div/div/form/div/div[1]/fieldset/label[1]/input
		driver.findElement(By.id("username")).sendKeys("477023");
		driver.findElement(By.id("password")).sendKeys("477023");
		driver.findElement(By.id("submit")).click();

	}
}


 

