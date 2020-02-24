package testng;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ISuiteListener;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

public class playAround implements ISuiteListener{
		
	WebDriver driver=null;
	String chromeSetProperty="webdriver.chrome.driver";
	String ieSetProperty="webdriver.ie.driver";
	String chromePath="C:\\Users\\theth\\git\\Master-GUI\\drivers\\chromedriver.exe";
	String levelUrl="https://testsso.secure.fedex.com/l3/prerates";
	Map<String, String> testClassParameters;
	
	
	TestNG testSuite = new TestNG();

	
	
	 @Test
     @Parameters("name")
     public void hi(String name) {
        System.out.println(name);
     }
	 
	 
	 
	
	@Test
  public void test1() {
  
		System.out.println("Ran SUITE");
		
		/*
	System.setProperty(chromeSetProperty,chromePath);
    driver = new ChromeDriver();
    driver.get(levelUrl);
	testSuite.setTestClasses(new Class[] { classExample.class });
	testSuite.addListener(new playAround());
	testSuite.setDefaultSuiteName("My Test Suite");
	testSuite.setDefaultTestName("My Test");
	testSuite.setOutputDirectory("/Users/pankaj/temp/testng-output");
	testSuite.run();
	testSuite.
	*/
	
   // justTest();

	}
	
	@Test
	  public void test2() {
	  
		System.setProperty(chromeSetProperty,chromePath);
	    driver = new ChromeDriver();
	    driver.get(levelUrl);

		}
	
	@Test
	  public void test3() {
	  
		System.setProperty(chromeSetProperty,chromePath);
	    driver = new ChromeDriver();
	    driver.get(levelUrl);

		}
	
	@Test
	  public void test4() {
	  
		System.setProperty(chromeSetProperty,chromePath);
	    driver = new ChromeDriver();
	    driver.get(levelUrl);

		}
	
	
	public void justTest() {
		
		Assert.assertEquals("String", "Re-rating and Invoice Adjustment","Could Not Find Home Page");
	}
	
	
}
