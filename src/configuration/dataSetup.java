package configuration;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class dataSetup {

	config c;
	excel excelVar;
	String source;
	int rowCount,colCount,total,totalMod,totalRows1,totalRows2,totalRows3,totalRows4,sessionCountInt;
	String[][] allData;
	String function;
	String filePath;
	public dataSetup() {
		
		this.c=c;
		source=c.getSource();
		
		function=c.getFunction();
		sessionCountInt=Integer.parseInt(c.getSessionCount());
		filePath=c.getFilePath();
	
		if(source.equals("excel")) {
			
        	excelVar = new excel(filePath);
	    	excelVar.setUpExcelWorkbook();
	    	excelVar.setUpExcelSheet(0);
	    	excelVar.setRowCountAutomatically(2);
	    	excelVar.setColCountAutomatically(0);
	    	rowCount=excelVar.getRowCount();
	    	colCount=excelVar.getColCount()+1;
	    	
    	}
    	
    	else if(source.equals("db")) {
    		databaseQueryClass dqc = new databaseQueryClass(c);
    		allData=c.getAllDataArray();
    	}
	
		
    	total= rowCount/sessionCountInt;
    	totalMod=rowCount%sessionCountInt;
    	totalRows1=total;
    	totalRows2=total;
    	totalRows3=total;
    	totalRows4=total;
    	
    	switch(totalMod) {
	    	case 1:
	    		totalRows1++;
	    		break;
	    	case 2 :
	    		totalRows1++;
	    		totalRows2++;
	    		break;
	    	case 3:
	    		totalRows1++;
	    		totalRows2++;
	    		totalRows3++;
	    		break;
    	
    	}	
	
	}
	
	
	
	
	
	
	
	 @DataProvider(name = "data-provider1")
	    public synchronized Object[][] dataProviderMethod1() { 
	    	Object [][] obj=null;
	    	if (sessionCountInt>=1) {
	    	
	    	 obj= new Object[totalRows1][colCount];;
	    	try {
	    		String tempString="";
	    	
	    	int objCount=0;
	    	for(int i=1;i<=rowCount;i+=sessionCountInt) {
	    		for(int j=0;j<colCount-1;j++) {
	    				if(source.equals("excel")) {
	    				tempString=excelVar.getCellData(i, j);
	    				}
	    				else if (source.equals("db")) {
	    					tempString=allData[i-1][j];
	    				}
	    					if (tempString == null || tempString.equals("null")){
	    						tempString="";
	    					}
	    				obj[objCount][j]=tempString;
	    			
	    		}
	    		obj[objCount][colCount-1]=i;
	    		objCount++;
	    	}  	
	}catch(Exception e) {
		System.out.println(e);
	}
	    	}
	    	return obj;
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    @DataProvider(name = "data-provider2")
	    public synchronized Object[][] dataProviderMethod2() { 
	    	Object [][] obj=null;
	    	if (sessionCountInt>=2) {
	        	
	       	 
	    
	    	String tempString="";
	    	obj = new Object[totalRows2][colCount];
	    	int objCount=0;
	    	for(int i=2;i<=rowCount;i+=sessionCountInt) {
	    		for(int j=0;j<colCount-1;j++) {	
	    			if(source.equals("excel")) {
	    				tempString=excelVar.getCellData(i, j);
	    				}
	    				else if (source.equals("db")) {
	    					tempString=allData[i-1][j];
	    				
	    				}
						if (tempString == null || tempString.equals("null")){
							tempString="";
						}
					obj[objCount][j]=tempString;
	    		}
	    		obj[objCount][colCount-1]=i;
	    		objCount++;
	    	}
	    }
	    	return obj;
	    
	    }
	    
	    @DataProvider(name = "data-provider3")
	    public synchronized Object[][] dataProviderMethod3() { 
	    	Object [][] obj=null;
	    	if (sessionCountInt>=3) {
	        	
	       	 
	    	String tempString="";
	    	obj = new Object[totalRows3][colCount];
	    	int objCount=0;
	    	for(int i=3;i<=rowCount;i+=sessionCountInt) {
	    		for(int j=0;j<colCount-1;j++) {
	    			if(source.equals("excel")) {
	    				tempString=excelVar.getCellData(i, j);
	    				}
	    				else if (source.equals("db")) {
	    					tempString=allData[i-1][j];
	    				}
						if (tempString == null || tempString.equals("null")){
							tempString="";
						}
					obj[objCount][j]=tempString;
	    		}
	    		obj[objCount][colCount-1]=i;
	    		objCount++;
	    	}
	    }
	    	return obj;
	    
	    }
	    
	    @DataProvider(name = "data-provider4")
	    public synchronized Object[][] dataProviderMethod4() { 
	    	Object [][] obj=null;
	    	if (sessionCountInt>=4) {
	        	
	       	 
	    	String tempString="";
	    	obj = new Object[totalRows4][colCount];
	    	int objCount=0;
	    	for(int i=4;i<=rowCount;i+=sessionCountInt) {
	    		for(int j=0;j<colCount-1;j++) {
	    			if(source.equals("excel")) {
	    				tempString=excelVar.getCellData(i, j);
	    				}
	    				else if (source.equals("db")) {
	    					
	    					tempString=allData[i-1][j];
	    				
	    				}
						if (tempString == null || tempString.equals("null")){
							tempString="";
						}
					obj[objCount][j]=tempString;
	    		}
	    		obj[objCount][colCount-1]=i;
	    		objCount++;
	    	}
	    }
	    	return obj;
	    
	    }
	    
	    
	    
	    
}
