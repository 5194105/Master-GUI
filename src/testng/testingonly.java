package testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import configuration.excel;

public class testingonly {
	  
	
	
	
	    @DataProvider(name = "data-provider")
	    public Object[][] dataProviderMethod() { 
	    	//excel e = new excel("C:\\Users\\theth\\git\\Master-GUI\\test data\\rerate2.xlsx");
	    	excel e = new excel("C:\\Users\\5194105\\Documents\\Eclipse Projects\\Master GUI\\test data\\rerate2.xlsx");
	    	
	    	
	    	e.setUpExcelWorkbook();
	    	
	    	e.setUpExcelSheet(0);
	    	e.setRowCountAutomatically(0);
	    	e.setColCountAutomatically(0);
	    	int rowCount=e.getRowCount();
	    	int colCount=e.getColCount()-2;
	    	Object [][] obj = new Object[rowCount][colCount];
	    	
	    	System.out.println("rowCount "+rowCount);
	    	System.out.println("colCount "+colCount);
	    	
	    	for(int i=0;i<rowCount;i++) {
	    		for(int j=0;j<colCount;j++) {
	    			//System.out.println(e.getCellData(i, j));
	    			obj[i][j]=e.getCellData(i, j);
	    		}
	    	}
	    	
	    	
	    	return obj;
	    	//return new Object[][] { { "data one" }, { "data two" } };
	    }
	 
	    @Test(dataProvider = "data-provider")
	    public void testMethod(String data1,String data2,String data3,String data4,String data5,String data6,String data7,String data8,String data9,String data10,String data11,String data12,String data13) {
	        System.out.println("Data is: " + data1);
	    }  
	
	
	
	
@Test
  public void f() {
  }

}



 


