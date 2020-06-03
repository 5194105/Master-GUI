package rerate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import configuration.config;
import testng.testingonly;

public class rerateManualTest {

	
	public static void main(String[] args) {
	config c = new config();
	c.setSource("excel");
	c.setExcelPath("C:\\Users\\FedExUser\\Documents\\stephen\\L3C1\\rerate.xlsx");
	c.setStartDate("01/01/2020");
	c.setEndDate("05/27/2020");
	c.setDriverType("2");
	c.setCompatibleMode("");
	c.setLevel("3");
	c.setSessionCount("1");
	System.out.println("Booleans "+c.getLevel()+"      "+c.getSource());
	
	
	

	
	
    XmlSuite xmlSuite = new XmlSuite();
    xmlSuite.setName("Sample_Suite");
    Map<String, String> fieldValues = new HashMap<>();
    fieldValues.put("filepathExcel", c.getExcelPath());
    fieldValues.put("startDateText", c.getStartDate());
    fieldValues.put("endDateText", c.getEndDate());
    fieldValues.put("broswer", c.getDriverType());
    fieldValues.put("compatibleMode", c.getCompatibleMode());
    fieldValues.put("source", c.getSource());
    fieldValues.put("level", c.getLevel());
    fieldValues.put("sessionCount", c.getSessionCount());
    xmlSuite.setParameters(fieldValues);
    XmlTest xmlTest = new XmlTest(xmlSuite);
    xmlTest.setName("Rerate Test");
    //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
    xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(rerateTestNgSlow.class)));
    xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
    TestNG tng = new TestNG();
    tng.setXmlSuites(Collections.singletonList(xmlSuite));
    tng.run();
}
	}
