package eraRerate;




import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import configuration.config;


public class eraRerateManualTest {

    public static void main(String[] args) {
    	config c = new config();
       	c.setExcelPath("C:\\Users\\FedExUser\\Documents\\rebill.xlsx");
    	c.setLevel("3");
    	c.setDriverType("2");
    	c.setCompatibleMode("false");
    	c.setSource("db");
    	c.setAllCheckBox("false");
    	c.setNullCheckBox("true");
    	c.setFailedCheckBox("false");
		c.setDomesticCheckBox("true");
		c.setInternationalCheckBox("true");
		c.setExpressCheckBox("true");
		c.setGroundCheckBox("true");
		c.setSessionCount("1");
		c.setDatabaseDisabled("false");
		c.setCustomCheckBox("true");
		//c.setCustomString("trkngnbr in ('597012059274',	'244496705687',	'794991365707',	'506365021201',	'757714581424',	'315459044390',	'777982821200')");
		c.setCustomString("trkngnbr in ('794993961067')");
		
		
		
		
		
		
		
		
		//c.setCustomString("rerate_type='service'");
    	
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
		String customCheckBox= c.getCustomCheckBox();
		String customString= c.getCustomString();
		String eraWorkable = c.getEraWorkable();
		
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
		if(eraWorkable==null) {
			eraWorkable="";
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
        fieldValues.put("sessionCount",sessionCount);
        fieldValues.put("customString",customString);
        fieldValues.put("customCheckBox",customCheckBox);
        fieldValues.put("databaseDisabled",databaseDisabled);
        fieldValues.put("eraWorkable",eraWorkable);
        fieldValues.put("domesticCheckBox", domesticCheckBox);
        fieldValues.put("internationalCheckBox", internationalCheckBox);
        fieldValues.put("expressCheckBox", expressCheckBox);
        fieldValues.put("groundCheckBox", groundCheckBox);
        xmlSuite.setParameters(fieldValues);
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("era Rerate Test");
        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(eraRerateTestNGSlow.class)));
        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
        TestNG tng = new TestNG();
        tng.setXmlSuites(Collections.singletonList(xmlSuite));
        tng.run();
        
	}
}
