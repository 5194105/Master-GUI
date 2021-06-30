package testingonly;
import configuration.config;
import configuration.dataSetup;
import configuration.importData;
import prerate.prerateTestNGSlow;
import rebill.Retry;
import rebill.rebillData;


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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

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
  static Connection oracleARL3Con;
  
    	public static void main (String[] arg) {
    		
    		
    		
    		for (int i=0;i<2000;i++) {
    			System.out.println(i%500);
    			if ((i%500)==0) {
    				System.out.println(i);
    				System.out.println("Mod is zero");
    			}
    		}
    		
    		
    		importData id = new importData();
    		config c=id.getConfig();
    		c.setEcL3DbConnection("test_readonly", "perftest");
    		Connection con = c.getEcL3DbConnection();
    		for (int i=0;i<2000;i++) {
    		try {
    			if (i==500 || i==1000 || i==1500) {
    			//con.close();
    			//c.setEcL3DbConnection("test_readonly", "perftest");
        		//con = c.getEcL3DbConnection();
    			}
    			String databaseSqlQuery="select WORK_TYPE_CD,STAT_CD_ARRAY_DESC from ec_schema.shipment a join ec_schema.package b on a.ONLN_REV_ITEM_ID=b.ONLN_REV_ITEM_ID join ec_schema.pkg_stat_cd_array c on b.ONLN_PKG_ID=c.ONLN_PKG_ID where ARRAY_TYPE_CD='F' and pkg_trkng_nbr="+i;
    			Statement stmt = con.createStatement();
    			ResultSet rs = stmt.executeQuery(databaseSqlQuery);
    			System.out.println(i);
    			while(rs.next()) {
    				//  d.setEcWorkType(rs.getString(1));
	        		//d.setStatCodeArray(rs.getString(2));
	        		//See if stat code is overridable 
        		}
        	 }
    		catch(Exception e) {
    			System.out.println(e);
    			
    			if (e.getMessage().contains("maximum open cursors exceeded")) {
    				System.out.println("Ending Program Due to Max Open Cursors");
    				System.exit(0);
    			}
    		}
    	}
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
 

