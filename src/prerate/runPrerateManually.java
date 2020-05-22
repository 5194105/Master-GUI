package prerate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import configuration.config;
import rebill.rebillData;
import rebill.testngRebillSlowMfRetire;

public class runPrerateManually {

	static ArrayList<rebillData> rebillDataArray= new ArrayList<rebillData>();
    public static void main(String[] args) {
    	config c = new config();
       	c.setExcelPath("C:\\Users\\FedExUser\\Documents\\rebill.xlsx");
    	c.setLevel("3");
    	c.setDriverType("2");
    	c.setDriverType("2");
    	c.setCompatibleMode("false");
    	c.setSource("db");
    	c.setAllCheckBox("false");
    	c.setNullCheckBox("true");
    	c.setFailedCheckBox("true");
  
		c.setDomesticCheckBox("false");
		c.setInternationalCheckBox("true");
		c.setExpressCheckBox("true");
		c.setGroundCheckBox("false");
		c.setNormalCheckBox("false");
		c.setMfRetireCheckBox("true");
		c.setSessionCount("1");
    	
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
		String normalCheckBox=c.getNormalCheckBox();
		String mfRetireCheckBox=c.getMfRetireCheckBox();
		String sessionCount=c.getSessionCount();
		
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
		System.out.println("normalCheckBox "+normalCheckBox);
		System.out.println("mfRetireCheckBox "+mfRetireCheckBox);
		System.out.println("sessionCount "+sessionCount);
		
		
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
		if(normalCheckBox==null) {
			normalCheckBox="";
		}
		if(mfRetireCheckBox==null) {
			mfRetireCheckBox="";
		}
		if(sessionCount==null) {
			sessionCount="";
		}
		
	
	

		
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Sample_Suite");
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("filepath", filepath);
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
        fieldValues.put("sessionCount",sessionCount);
        
        xmlSuite.setParameters(fieldValues);
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Rebill Test");
        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(prerateHoldTestNGSlow.class)));
        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
        TestNG tng = new TestNG();
        tng.setXmlSuites(Collections.singletonList(xmlSuite));
        tng.run();
        
	}
}
