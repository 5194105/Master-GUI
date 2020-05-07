package testingonly;
import configuration.config;
import rebill.rebillData;
import rebill.testngRebillSlow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Highest {

	 
	static config c = new config();
	static String level="2";
	
	static Boolean allCheckBox;
	static Boolean nullCheckBox;
	static Boolean failedCheckBox;
	static Boolean domesticCheckBox;
	static Boolean internationalCheckBox;
	static Boolean expressCheckBox;
	static Boolean groundCheckBox;
	static int rowCount;
	static int colCount;
	static int total;
	static int testCounter1,testCounter2,testCounter3,testCounter4;
	static int totalRows1,totalRows2,totalRows3,totalRows4;
	static int count;
	static String source;
	static int totalMod;
	static String databaseSqlQuery,databaseSqlCount;
	static ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
    public static void main(String[] args) {
     try {
    	config c = new config();
    	//rebillMain rm = new rebillMain(c);
		String filepath;
		c.setSource("db");
		String source =c.getSource();
		if (c.getSource().equals("db")) {
			 filepath="";
		}
		else if (c.getSource().equals("excel")) {
		 filepath=c.getExcelPath();
		}
		c.setLevel("2");
		String level=c.getLevel();
		String broswer=c.getDriverType();
		String compatibleMode=c.getCompatibleMode();
	
		
		c.setAllCheckBox("false");
		c.setNullCheckBox("true");
		c.setFailedCheckBox("true");
		c.setDomesticCheckBox("true");
		c.setInternationalCheckBox("false");
		c.setExpressCheckBox("false");
		c.setGroundCheckBox("true");
		c.setNormalCheckBox("true");
		c.setMfRetireCheckBox("false");
		
		String allCheckBox=c.getAllCheckBox();
		String nullCheckBox=c.getNullCheckBox();
		String failedCheckBox=c.getFailedCheckBox();
		String domesticCheckBox=c.getDomesticCheckBox();
		String internationalCheckBox=c.getInternationalCheckBox();
		String expressCheckBox=c.getExpressCheckBox();
		String groundCheckBox=c.getGroundCheckBox();
		String normalCheckBox=c.getNormalCheckBox();
		String mfRetireCheckBox=c.getMfRetireCheckBox();
		
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Sample_Suite");
        Map<String, String> fieldValues = new HashMap<>();
       
        fieldValues.put("filepath", "");  
        fieldValues.put("level", level);
        fieldValues.put("browser", "2");
        fieldValues.put("source", source);
        fieldValues.put("compatibleMode", "");
        fieldValues.put("allCheckBox", allCheckBox);
        fieldValues.put("nullCheckBox", nullCheckBox);
        fieldValues.put("failedCheckBox", failedCheckBox);
        fieldValues.put("domesticCheckBox", domesticCheckBox);
        fieldValues.put("internationalCheckBox", internationalCheckBox);
        fieldValues.put("expressCheckBox", expressCheckBox);
        fieldValues.put("groundCheckBox", groundCheckBox);
        fieldValues.put("normalCheckBox", normalCheckBox);
        fieldValues.put("mfRetireCheckBox",mfRetireCheckBox);
        
        /*
        fieldValues.put("filepath", "");
        fieldValues.put("level", "");
        fieldValues.put("broswer", "");
        fieldValues.put("source", "");
        fieldValues.put("compatibleMode", "");
        fieldValues.put("allCheckBox", "");
        fieldValues.put("nullCheckBox", "");
        fieldValues.put("failedCheckBox", "");
        fieldValues.put("domesticCheckBox", "");
        fieldValues.put("internationalCheckBox", "");
        fieldValues.put("expressCheckBox", "");
        fieldValues.put("groundCheckBox", "");
        fieldValues.put("normalCheckBox", "");
        fieldValues.put("mfRetireCheckBox","");
        
        */
        xmlSuite.setParameters(fieldValues);
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Rebill Test");
        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testngRebillSlow.class)));
        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
        TestNG tng = new TestNG();
        tng.setXmlSuites(Collections.singletonList(xmlSuite));
        tng.run();
     }
     catch(Exception e) {
    	 System.out.println(e);
     }
    	
    	
	}

}
 

