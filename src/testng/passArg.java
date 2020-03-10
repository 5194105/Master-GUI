package testng;

import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import configuration.config;
import configuration.excel;

public class passArg implements ISuiteListener{

	  
	static	String homePath,tempFile,configFile;
	static	excel e;
		 Boolean isPresent=false;
		int retryCounter=0;
		int i=0;
		Object o;
		WebDriverWait wait;
		config c;
		
		Boolean level;
		long serviceLong1,serviceLong2;
		long invoiceLong1,invoiceLong2;
		long acctLong1,acctLong2;
		long trkngLong1,trkngLong2;
		String a ;
		int count ;
		String url1 ="https://testsso.secure.fedex.com/L3/PRSApps/rerate/iscreen/rrAERerateMain.jsp";
		String url2="https://destsso.secure.fedex.com/L2/PRSApps/rerate/iscreen/rrAERerateMain.jsp";
		String  name;
		String trkng1,trkng2;
		String acct1,acct2;
		String inv1,inv2;
		String service1,service2;
		String rerateType;
		String acctType;
		String  sh1;
		String filepath;
		String d;
		String d2;
		static XSSFWorkbook wb;
		static XSSFSheet sheet;
		static XSSFRow row;
		static XSSFCell cell;
		static FileInputStream fin;
		static FileOutputStream fout;
		public static int  rowcount;
		//WebDriver driver;
		WebDriver driver,driver1,driver2,driver3,driver4;
		static int sheetcount;
		static int j ;
		static Boolean combo,express,ground;
		String temp;
		 Select CEDropDown;
		 Robot r;
		 List<WebElement> comboBoxesHandling = new ArrayList<WebElement>();
		 ArrayList<String> cc1 = new ArrayList<String>();
		 ArrayList<String> cc2 = new ArrayList<String>();
		 Alert alert;
		 Boolean isChecked=false;
	        Boolean compatibleMode;
	        Boolean chrome;
	        
	        
	        WebElement element;
		
	        
	       String browser="2";
	        String chromeSetProperty="webdriver.chrome.driver";
			String ieSetProperty="webdriver.ie.driver";
			//String firefoxSetProperty="";
			
			String chromePath="C:\\Users\\theth\\git\\Master-GUI\\drivers\\chromedriver.exe";
			String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
	        
			String levelUrl="https://testsso.secure.fedex.com/L3/PRSApps";
			
			
			classExample ce;
		static	int rowCount;
		
		static	int colCount;
		static int abc;
	//static TestNG testSuite = new TestNG();
	//static XmlSuite suite = new XmlSuite();
	//static List<XmlSuite> suites = new ArrayList<XmlSuite>();
	
	
	public static void main(String[] args) {
	
		
		/*
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Sample_Suite");
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("name", "Hello");
        xmlSuite.setParameters(fieldValues);
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Sample_test");
       // xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
           xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testingonly.class)));
        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
        TestNG tng = new TestNG();
        tng.setXmlSuites(Collections.singletonList(xmlSuite));
        tng.run();
		
		*/
		
		/*
		    XmlSuite xmlSuite = new XmlSuite();
	        xmlSuite.setName("Sample_Suite");
	        Map<String, String> fieldValues = new HashMap<>();
	        fieldValues.put("name", "Hello");
	        xmlSuite.setParameters(fieldValues);
	        XmlTest xmlTest = new XmlTest(xmlSuite);
	        xmlTest.setName("Sample_test");
	       // xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
	        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testingonly.class)));
	        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
	        TestNG tng = new TestNG();
	        tng.setXmlSuites(Collections.singletonList(xmlSuite));
	        tng.run();
	        */
	        
	        
			
	        TestListenerAdapter tla = new TestListenerAdapter();
	        TestNG testng = new TestNG();
	        List<String> suites = Lists.newArrayList();
	        suites.add("C:\\Users\\theth\\git\\Master-GUI\\test-output\\testng-failed.xml");//path to xml..
	       // suites.add("c:/tests/testng2.xml");
	        testng.setTestSuites(suites);
	        testng.run();
        
      
        
		
	
	}


	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}
	
}
