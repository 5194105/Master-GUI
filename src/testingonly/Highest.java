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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Highest {

    public static void main(String[] args) throws InterruptedException {
    	 String  homePath=System.getProperty("user.dir");
    	String chromePath=homePath+"\\drivers\\chromedriver.exe";
    	 String chromeSetProperty="webdriver.chrome.driver";
    	System.setProperty(chromeSetProperty,chromePath);
    	WebDriver driver = new ChromeDriver();
    	WebDriverWait wait;
    	
    	try {
		    driver.get("https://testsso.secure.fedex.com/l3/instant-invoicing ");
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,10);
			driver.manage().window().maximize();
			driver.findElement(By.id("username")).sendKeys("5194105");
			driver.findElement(By.id("password")).sendKeys("5194105");
			driver.findElement(By.id("submit")).click();
    	}
    	catch(Exception e) {
    		
    		 Assert.fail("Could Not Login");
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	/*
    	  driver.findElement(By.xpath("//input[@id='iiForm:fromDateII_input']")).click();

    	//  driver.findElement(By.xpath("//input[@id='iiForm:fromDateII']")).click();
    	  // driver.findElement(By.xpath("//input[@id='label_iiForm:fromDateII']")).click();
       	
    	 
         //Will grab the calender and store as Element Select
     
    	   Select dropdown = new Select (driver.findElement(By.name("iiForm:fromDateII_input_sel_month")));   

         //Just Choosing Jan. Will try this ten times before it ends.
       int  attempts=0;
         while (attempts<10){
             try{
                 dropdown.selectByVisibleText("Jan");
                 driver.findElements(By.className("ui-state-default")).get(9).click();  
                 break;
                 }
                 catch (Exception eee){
                 System.out.println(eee);
                 }
                 attempts++;
             }
    */
    }
    
    }


 

