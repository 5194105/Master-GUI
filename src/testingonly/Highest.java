package testingonly;
import configuration.config;
import prerate.prerateTestNGSlow;
import rebill.rebillData;
import rebill.testngRebillSlow;

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

public class Highest {

	 

	static ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
    public static void main(String[] args) {
    	config c = new config();
       	c.setExcelPath("");
    	c.setLevel("2");
    	c.setDriverType("2");
    	c.setCompatibleMode("false");
    	c.setSource("db");
    	c.setAllCheckBox("false");
    	c.setNullCheckBox("false");
    	c.setFailedCheckBox("true");
    	
    	String filepath=c.getExcelPath();
		String level=c.getLevel();
		String browser=c.getDriverType();
		String compatibleMode=c.getCompatibleMode();
		String source=c.getSource();
		String allCheckBox=c.getAllCheckBox();
		String nullCheckBox=c.getNullCheckBox();
		String failedCheckBox=c.getFailedCheckBox();
		
		
		
		System.out.println("filepath"+" "+filepath);
		System.out.println("level"+" "+level);
		System.out.println("broswer"+" "+browser);
		System.out.println("source"+" "+source);
		System.out.println("compatibleMode"+" "+compatibleMode);
		System.out.println("allCheckBox"+" "+allCheckBox);
		System.out.println("nullCheckBox"+" "+nullCheckBox);
		System.out.println("failedCheckBox"+" "+failedCheckBox);
	
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
		
		
	
		
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Sample_Suite");
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("filepath", filepath);
        fieldValues.put("level", level);
        fieldValues.put("browser", browser);
        fieldValues.put("source", source);
        fieldValues.put("compatibleMode", compatibleMode);
        fieldValues.put("allCheckBox", allCheckBox);
        fieldValues.put("nullCheckBox", nullCheckBox);
        fieldValues.put("failedCheckBox", failedCheckBox);
        
        xmlSuite.setParameters(fieldValues);
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Prerate Test");
        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(prerateTestNGSlow.class)));
        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
        TestNG tng = new TestNG();
        tng.setXmlSuites(Collections.singletonList(xmlSuite));
        tng.run();
	}
}


 

