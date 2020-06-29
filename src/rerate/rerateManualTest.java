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
import rebill.testngRebillSlowMfRetire;
import testng.testingonly;

public class rerateManualTest {

	
	public static void main(String[] args) {
	
		
		
		
	config c = new config();
   	c.setExcelPath("C:\\Users\\FedExUser\\Documents\\rerate.xlsx");
	c.setLevel("3");
	c.setDriverType("1");
	c.setCompatibleMode("true");
	c.setSource("excel");
	c.setAllCheckBox("false");
	c.setNullCheckBox("true");
	c.setFailedCheckBox("true");
	c.setDomesticCheckBox("true");
	c.setInternationalCheckBox("true");
	c.setExpressCheckBox("true");
	c.setGroundCheckBox("true");
	c.setStartDate("01/01/2020");
<<<<<<< HEAD
	c.setEndDate("06/24/2020");

=======
	c.setEndDate("06/25/2020");
>>>>>>> branch 'master' of https://github.com/5194105/Master-GUI.git
	c.setSessionCount("1");
	c.setDatabaseDisabled("false");
	c.setEd1("true");
	c.setEi1("true");
	c.setGd1("true");
	c.setGi1("true");
	c.setNt1("true");
	c.setSp1("true");
	c.setEd2("true");
	c.setEi2("true");
	c.setGd2("true");
	c.setGi2("true");
	c.setNt2("true");
	c.setSp2("true");
	
	
	
	
	
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
	String startDateText=c.getStartDate();
	String endDateText=c.getEndDate();
	String databaseDisabled=c.getDatabaseDisabled();
	
	 String ed1=c.getEd1String();
	 String ed2=c.getEd2String();
	 String ei1=c.getEi1String();
	 String ei2=c.getEi2String();
	 String gd1=c.getGd1String();
	 String gd2=c.getGd2String();
	 String gi1=c.getGi1String();
	 String gi2=c.getGi2String();
	 String nt1=c.getNt1String();
	 String nt2=c.getNt2String();
	 String sp1=c.getSp1String();
	 String sp2=c.getSp2String();
		
	
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
	System.out.println("startDateText "+startDateText);
	System.out.println("endDate "+endDateText);
	System.out.println("databaseDisabled "+databaseDisabled);

	
	System.out.println("ed1 "+ed1);
	System.out.println("ei1 "+ei1);
	System.out.println("gd1 "+gd1);
	System.out.println("gi1 "+gi1);
	System.out.println("nt1 "+nt1);
	System.out.println("sp1 "+sp1);
	
	System.out.println("ed2 "+ed2);
	System.out.println("ei2 "+ei2);
	System.out.println("gd2 "+gd2);
	System.out.println("gi2 "+gi2);
	System.out.println("nt2 "+nt2);
	System.out.println("sp2 "+sp2);
	
	
	if(startDateText==null) {
		startDateText="";
	}
	if(endDateText==null) {
		endDateText="";
	}
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
	
	if(ed1==null) {
		ed1="";
	}
	if(ei1==null) {
		ei1="";
	}
	if(gd1==null) {
		gd1="";
	}
	if(gi1==null) {
		gi1="";
	}
	if(nt1==null) {
		nt1="";
	}
	if(sp1==null) {
		sp1="";
	}
	
	if(ed2==null) {
		ed2="";
	}
	if(ei2==null) {
		ei2="";
	}
	if(gd2==null) {
		gd2="";
	}
	if(gi2==null) {
		gi2="";
	}
	if(nt2==null) {
		nt2="";
	}
	if(sp2==null) {
		sp2="";
	}
	

	if(databaseDisabled==null) {
		databaseDisabled="";
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
    fieldValues.put("sessionCount",sessionCount);
    fieldValues.put("databaseDisabled",databaseDisabled);
    fieldValues.put("startDateText", startDateText);
    fieldValues.put("endDateText",endDateText);
    
    fieldValues.put("ed1",ed1);
    fieldValues.put("ei1",ei1);
    fieldValues.put("gd1",gd1);
    fieldValues.put("gi1",gi1);
    fieldValues.put("nt1",nt1);
    fieldValues.put("sp1",sp1);
  
    fieldValues.put("ed2",ed2);
    fieldValues.put("ei2",ei2);
    fieldValues.put("gd2",gd2);
    fieldValues.put("gi2",gi2);
    fieldValues.put("nt2",nt2);
    fieldValues.put("sp2",sp2);
    
    xmlSuite.setParameters(fieldValues);
    XmlTest xmlTest = new XmlTest(xmlSuite);
    xmlTest.setName("Rebill Test");
    //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
    xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(rerateTestNgSlow.class)));
    xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
    TestNG tng = new TestNG();
    tng.setXmlSuites(Collections.singletonList(xmlSuite));
    tng.run();
}
	}
