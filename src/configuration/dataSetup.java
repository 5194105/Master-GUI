package configuration;

import org.testng.annotations.DataProvider;

public class dataSetup {
	 @DataProvider(name = "data-provider1")
	    public synchronized Object[][] dataProviderMethod1() { 
	    	Object [][] obj= new Object[1][1];
	    	obj[0][0]="Hello";
	    	return obj;
	    	
	    }
}
