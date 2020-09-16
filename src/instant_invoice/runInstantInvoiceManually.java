package instant_invoice;


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


public class runInstantInvoiceManually {

	 public static void main(String[] args) {
    	config c = new config();
       	c.setExcelPath("C:\\Users\\FedExUser\\Desktop\\stephen\\R66\\L3C6\\instant_invoice.xlsx");
    	c.setLevel("3");
    	c.setDriverType("2");
    	c.setCompatibleMode("false");
    	c.setSource("excel");
    	c.setAllCheckBox("false");
    	c.setNullCheckBox("true");
    	c.setFailedCheckBox("true");
		c.setDatabaseDisabled("false");
		c.setCustomCheckBox("false");
		c.setCustomString("");
		c.setSessionCount("1");
	//	c.setPrerateType("hold");
		
		
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

/*
	@Parameters({
		"filepath",
		"level",
		"browser",
		"compatibleMode",
		"source",
		"allCheckBox",
		"nullCheckBox",
		"failedCheckBox",
		"domesticCheckBox",
		"internationalCheckBox",
		"expressCheckBox",
		"groundCheckBox",
		"sessionCount",
		"customString",
		"customCheckBox",
		"databaseDisabled"})
	}
	public void setupExcel(
			String filepath,
			String level,
			String browser,
			String compatibleMode,
			String source,
			String allCheckBox,
			String nullCheckBox,
			String failedCheckBox,
			String domesticCheckBox,
			String internationalCheckBox,
			String expressCheckBox,
			String groundCheckBox,
			String sessionCount,
			String customString,
			String customCheckBox,
			String databaseDisabled) {
	
		*/
    XmlSuite xmlSuite = new XmlSuite();
    xmlSuite.setName("Sample_Suite");
    Map<String, String> fieldValues = new HashMap<>();
    fieldValues.put("filepath", filepath);
    fieldValues.put("level", level);
    fieldValues.put("browser", browser);
    fieldValues.put("compatibleMode", compatibleMode);
    fieldValues.put("source", source);
    fieldValues.put("allCheckBox", allCheckBox);
    fieldValues.put("nullCheckBox", nullCheckBox);
    fieldValues.put("failedCheckBox", failedCheckBox);
   
    fieldValues.put("sessionCount",sessionCount);
    fieldValues.put("customString",customString);
    fieldValues.put("customCheckBox",customCheckBox);
    fieldValues.put("databaseDisabled",databaseDisabled);
    
    xmlSuite.setParameters(fieldValues);
    XmlTest xmlTest = new XmlTest(xmlSuite);
    xmlTest.setName("Instant Invoice Test");
   
 
    xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testNgSlowInstantInvoice.class)));
  
    xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
    TestNG tng = new TestNG();
    tng.setXmlSuites(Collections.singletonList(xmlSuite));
    tng.run();
    
        
	}
}
