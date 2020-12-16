package rebill;


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

import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class rebillManualClass {

	 
//I will choose Intl Ground Trks that are failed an null
	static ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
    public static void main(String[] args) {
    	config c = new config();
       	c.setExcelPath("C:\\Users\\FedExUser\\Desktop\\stephen\\R66\\L3C6\\rebill.xlsx");
    	c.setLevel("3");
    	c.setDriverType("2");	
    	c.setCompatibleMode("false");
    	c.setSource("db");
    	c.setAllCheckBox("false");
    	c.setNullCheckBox("true");
    	c.setFailedCheckBox("true");
		c.setDomesticCheckBox("true");
		c.setInternationalCheckBox("true");
		c.setExpressCheckBox("true");	
		c.setGroundCheckBox("true");
		//c.setNormalCheckBox("false"); Retired Value
		c.setDatabaseDisabled("false");
		
		//You can give custom query if want to
		c.setCustomCheckBox("true");
		c.setCustomString("company='EP'");
				
		c.setHeadlessString("false");

		c.setSessionCount("1");
    	
		c.setEraWorkable("true");
		
    	String filepath=c.getExcelPath();
		String level=c.getLevel();
		String browser=c.getDriverType();
		String compatibleMode=c.getCompatibleMode();
		String source = c.getSource();
		String allCheckBox=c.getAllCheckBox();
		String nullCheckBox=c.getNullCheckBox();
		String failedCheckBox=c.getFailedCheckBox();
		String domesticCheckBox=c.getDomesticCheckBox();
		String internationalCheckBox=c.getInternationalCheckBox();
		String expressCheckBox=c.getExpressCheckBox();
		String groundCheckBox=c.getGroundCheckBox();

		String sessionCount=c.getSessionCount();
		
		String databaseDisabled=	c.getDatabaseDisabled();
		String	customCheckBox= c.getCustomCheckBox();
		String	customString= c.getCustomString();
		
		String headless=c.getHeadlessString();
		
		String eraWorkable=c.getEraWorkable();
		
		System.out.println("filepath "+filepath);
		System.out.println("level "+level);
		System.out.println("browser "+browser);
		System.out.println("compatibleMode "+compatibleMode);
		System.out.println("source "+source);
		System.out.println("allCheckBox "+allCheckBox);
		System.out.println("nullCheckBox "+nullCheckBox);
		System.out.println("failedCheckBox "+failedCheckBox);
		System.out.println("domesticCheckBox "+domesticCheckBox);
		System.out.println("internationalCheckBox "+internationalCheckBox);
		System.out.println("expressCheckBox "+expressCheckBox);
		System.out.println("groundCheckBox "+groundCheckBox);
	
		System.out.println("sessionCount "+sessionCount);
		System.out.println("customString "+customString);
		System.out.println("customCheckBox "+customCheckBox);
		System.out.println("databaseDisabled "+databaseDisabled);
		System.out.println("headless "+headless);
		System.out.println("eraWorkable "+eraWorkable);
		
		
		if(filepath==null) {
			filepath="";
		}
		if(level==null) {
			level="";
		}
		if(browser==null) {
			browser="";
		}
		if(compatibleMode==null) {
			compatibleMode="";
		}
		if(allCheckBox==null) {
			allCheckBox="";
		}
		if(nullCheckBox==null) {
			nullCheckBox="";
		}
		if(failedCheckBox==null) {
			failedCheckBox="";
		}
		if(domesticCheckBox==null) {
			domesticCheckBox="";
		}
		if(internationalCheckBox==null) {
			internationalCheckBox="";
		}
		if(expressCheckBox==null) {
			expressCheckBox="";
		}
		if(groundCheckBox==null) {
			groundCheckBox="";
		}
	
		if(sessionCount==null) {
			sessionCount="";
		}
		
		if(customString==null) {
			customString="";
		}
		if(customCheckBox==null) {
			customCheckBox="";
		}
		if(databaseDisabled==null) {
			databaseDisabled="";
		}
		if(headless==null) {
			headless="";
		}
		if(eraWorkable==null) {
			eraWorkable="false";
		}
		
		

        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Sample_Suite");
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("filepath", filepath);
        fieldValues.put("level", level);
        fieldValues.put("browser", "2");
        fieldValues.put("compatibleMode", "");
        fieldValues.put("source", source);
        fieldValues.put("allCheckBox", allCheckBox);
        fieldValues.put("nullCheckBox", nullCheckBox);
        fieldValues.put("failedCheckBox", failedCheckBox);
        fieldValues.put("domesticCheckBox", domesticCheckBox);
        fieldValues.put("internationalCheckBox", internationalCheckBox);
        fieldValues.put("expressCheckBox", expressCheckBox);
        fieldValues.put("groundCheckBox", groundCheckBox);
        fieldValues.put("sessionCount",sessionCount);
        fieldValues.put("customString",customString);
        fieldValues.put("customCheckBox",customCheckBox);
        fieldValues.put("databaseDisabled",databaseDisabled);
        fieldValues.put("headless",headless);
        fieldValues.put("eraWorkable",eraWorkable);
        xmlSuite.setParameters(fieldValues);
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Rebill Test");
        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testngRebillFast.class)));
       // xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testngRebillSlow.class)));
        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
        TestNG tng = new TestNG();
        tng.setXmlSuites(Collections.singletonList(xmlSuite));
        tng.run();
        
	}
}


 

