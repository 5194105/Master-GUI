package testingonly;
import configuration.config;
import configuration.dataSetup;
import configuration.importData;
import prerate.prerateTestNGSlow;
import rebill.Retry;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Highest {

  public Highest() {}
  static String  homePath=System.getProperty("user.dir");
  static  String browser;
  static  String chromeSetProperty="webdriver.chrome.driver";
  static  String chromePath=homePath+"\\drivers\\chromedriver.exe";
  static String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
    
    	public static void main (String[] arg) {
    	String temp="Your Mass Rerate batch number is:64897";
    	String str=temp.replaceAll("\\D+","");
    	System.out.println( str);
    	}
	
    }
    
    


 

