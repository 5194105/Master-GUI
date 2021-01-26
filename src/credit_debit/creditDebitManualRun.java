package credit_debit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import configuration.config;

public class creditDebitManualRun {
	public static void main(String[] args) {
	config c = new config();
   	c.setExcelPath("C:\\Users\\theth\\git\\Master-GUI\\test data\\credit_debit.xlsx");
	c.setLevel("3");
	c.setDriverType("2");
	c.setCompatibleMode("false");
	c.setSource("db");
	c.setAllCheckBox("false");
	c.setNullCheckBox("true");
	c.setFailedCheckBox("true");
	c.setCreditCheckBox("false");
	c.setDebitCheckBox("true");
	c.setDisputeCheckBox("false");
	c.setResolveCreditCheckBox("true");
	c.setDatabaseDisabled("false");
	c.setCustomCheckBox("true");
	c.setCustomString("trkngnbr in ('794993940242')");
	c.setHeadlessString("false");

	c.setSessionCount("1");
	
	String filepath=c.getExcelPath();
	String level=c.getLevel();
	String browser=c.getDriverType();
	String compatibleMode=c.getCompatibleMode();
	String source = c.getSource();
	String allCheckBox=c.getAllCheckBox();
	String nullCheckBox=c.getNullCheckBox();
	String failedCheckBox=c.getFailedCheckBox();
	String creditCheckBox=c.getCreditCheckBox();
	String debitCheckBox=c.getDebitCheckBox();
	String expressCheckBox=c.getExpressCheckBox();
	String groundCheckBox=c.getGroundCheckBox();

	String sessionCount=c.getSessionCount();
	
	String databaseDisabled=	c.getDatabaseDisabled();
	String	customCheckBox= c.getCustomCheckBox();
	String	customString= c.getCustomString();
	
	String headless=c.getHeadlessString();
	
	String disputeCheckBox=c.getDisputeCheckBox();
	String resolveCreditCheckBox=c.getExpressCheckBox();
	
	System.out.println("filepath "+filepath);
	System.out.println("level "+level);
	System.out.println("browser "+browser);
	System.out.println("compatibleMode "+compatibleMode);
	System.out.println("source "+source);
	System.out.println("allCheckBox "+allCheckBox);
	System.out.println("nullCheckBox "+nullCheckBox);
	System.out.println("failedCheckBox "+failedCheckBox);
	System.out.println("creditCheckBox "+creditCheckBox);
	System.out.println("debitCheckBox "+debitCheckBox);
	System.out.println("expressCheckBox "+expressCheckBox);
	System.out.println("groundCheckBox "+groundCheckBox);

	System.out.println("sessionCount "+sessionCount);
	System.out.println("customString "+customString);
	System.out.println("customCheckBox "+customCheckBox);
	System.out.println("databaseDisabled "+databaseDisabled);
	System.out.println("headless "+headless);
	
	
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
	if(creditCheckBox==null) {
		creditCheckBox="";
	}
	if(debitCheckBox==null) {
		debitCheckBox="";
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
	
	
	
	if(disputeCheckBox==null) {
		disputeCheckBox="";
	}
	
	if(resolveCreditCheckBox==null) {
		resolveCreditCheckBox="";
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
    fieldValues.put("creditCheckBox", creditCheckBox);
    fieldValues.put("debitCheckBox", debitCheckBox);
    fieldValues.put("disputeCheckBox", disputeCheckBox);
    fieldValues.put("resolveCreditCheckBox", debitCheckBox);
    fieldValues.put("sessionCount",sessionCount);
    fieldValues.put("customString",customString);
    fieldValues.put("customCheckBox",customCheckBox);
    fieldValues.put("databaseDisabled",databaseDisabled);
    fieldValues.put("headless",headless);
    xmlSuite.setParameters(fieldValues);
    XmlTest xmlTest = new XmlTest(xmlSuite);
    xmlTest.setName("Rebill Test");
    xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(creditDebitTestNGSlow.class)));
   // xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testngRebillSlow.class)));
    xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
    TestNG tng = new TestNG();
    tng.setXmlSuites(Collections.singletonList(xmlSuite));
    tng.run();
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
		"creditCheckBox",
		"debitCheckBox",
		"sessionCount",
		"customString",
		"customCheckBox",
		"databaseDisabled",
		"headless"})
	*/
}
