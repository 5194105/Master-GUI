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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Highest {

<<<<<<< HEAD
	static  String  homePath=System.getProperty("user.dir");

	static ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
=======
	 
	static String  homePath=System.getProperty("user.dir");
<<<<<<< HEAD
	static Connection L3Ar;
=======
	
>>>>>>> branch 'master' of https://github.com/5194105/Master-GUI.git
>>>>>>> branch 'master' of https://github.com/5194105/Master-GUI.git
    public static void main(String[] args) throws InterruptedException {
<<<<<<< HEAD
   /*
=======
   
    	/*
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		//eraL2Con=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/INVADJ_SVC_INT,cn=OracleContext,dc=ute,dc=fedex,dc=com","INVADJ_APP","apppwdli");
    		L3Ar=DriverManager.getConnection("jdbc:oracle:thin:@//scd03051-vip.ute.fedex.com:1531/SCD0305","test_readonly", "perftest");
    		} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
<<<<<<< HEAD
    */
=======
>>>>>>> branch 'master' of https://github.com/5194105/Master-GUI.git
    	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
	    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    capabilities.setCapability("ignoreZoomSetting", true);
	    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    capabilities.setCapability("initialBrowserUrl","https://testsso.secure.fedex.com/L3/PRSApps");
<<<<<<< HEAD
    	*/
    	System.setProperty("webdriver.ie.driver", homePath+"\\drivers\\IEDriverServer.exe");
=======
	    
    	System.setProperty("webdriver.ie.driver", "C:\\Users\\FedExUser\\git\\MasterGUI\\drivers\\IEDriverServer.exe");
>>>>>>> branch 'master' of https://github.com/5194105/Master-GUI.git
>>>>>>> branch 'master' of https://github.com/5194105/Master-GUI.git
    	
    	String[] resultArray=validateResults("5829065673110");
    	
    	System.out.println(resultArray[0]);
    	System.out.println(resultArray[1]);
    	
    	
    	
    	
	}
    
    public static String[] validateResults(String trk) {
    	
    	Boolean result=null;
    	Connection con = null;
    	String[] resultArray = new String[2];
    	String level="3";
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
    	
    		if (level.equals("2")){
    			con=DriverManager.getConnection("jdbc:oracle:thin:@//scd03051-vip.ute.fedex.com:1531/SCD0305","appsread", "appsread");
    	    	
       	 }
       	 else if (level.equals("3")){
       		con=DriverManager.getConnection("jdbc:oracle:thin:@//scd03051-vip.ute.fedex.com:1531/SCD0305","appsread", "appsread");
        	
       	 	}
    	
    	}
    	catch(Exception e) {
    		
    		System.out.println("Could Not Get ERA DB Connections");
    	}
    	

    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		stmt=con.prepareStatement("select * from xxfdx.fdx_ar_adjustment_irdt where airbill_nbr=?");  
			stmt.setString(1,trk);  
			rs = stmt.executeQuery();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	      
    	   
    		try {
    			rs = stmt.executeQuery();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	       try {
    			if (rs.next()==false){
    			      System.out.println("Is NULL");
    			      resultArray[0]="fail";
    			      resultArray[1]="Not In ERA Database";
    			}
    			   else{
    				  
    	             
  	    			      resultArray[0]="pass";
  	    			      resultArray[1]="completed";
    	              }   	          
    			   
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	 return resultArray;      
}   
}


 

