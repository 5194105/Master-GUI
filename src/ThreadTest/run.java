package ThreadTest;

import configuration.config;
import configuration.importData;

public class run {
	static config c;
public static void main (String args[]) {
	//Function Type
	// 1 Single Rebill
	// 2 Mass Rebill
	// 3 Single Rerate
	// 4 Mass Rerate
	// 5 Credit Debit
	// 6 Instant Invoice
	// 7 Prerate Single
	// 8 Prerate Hold
	// 9 PRS Rerate

	int function = 7;
	importData id = new importData();
	 c=id.getConfig();
	 customConfig(function);
	 base b = new base(c,function);
}
public static void customConfig (int function) {
		switch (function) {
		//REBILL SINGLE	
		case 1:	
			c.setLevel("3");
			c.setDriverType("2");	
			c.setCompatibleMode("false");
			c.setSource("db");
			c.setDatabaseDisabled("false");
			c.setHeadlessString("false");
			c.setExcelPath("C:\\Users\\FedExUser\\Desktop\\stephen\\R66\\L3C6\\rebill.xlsx");
			c.setAllCheckBox("false");
			c.setNullCheckBox("true");
			c.setFailedCheckBox("true");
			c.setDomesticCheckBox("true");
			c.setInternationalCheckBox("true");
			c.setExpressCheckBox("true");	
			c.setGroundCheckBox("true");
			c.setSessionCount("1");
			c.setEraWorkable("true");
			c.setCustomCheckBox("false");
			c.setCustomString("description ='Failed on Entering Tracking Number'");
			break;
			
		case 2:
			
			break;
			
		case 3:
			
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
			c.setCustomString("trkngnbr in ('794993961067')");
			break;
			
		case 4:
			break;
			
		case 5:
			break;
			
		case 6:
			break;
			
		
			
			
			//PRERATE SINGLE
			case 7:
				c.setExcelPath("C:\\Users\\FedExUser\\Documents\\PRERATE_UPDATE.xlsx");
		    	c.setLevel("3");
		    	c.setDriverType("2");
		    	c.setCompatibleMode("true");
		    	c.setSource("db");
		    	c.setAllCheckBox("false");
		    	c.setNullCheckBox("true");
		    	c.setFailedCheckBox("true");
				c.setDomesticCheckBox("false");
				c.setInternationalCheckBox("false");
				c.setExpressCheckBox("false");
				c.setGroundCheckBox("false");
				c.setDatabaseDisabled("false");
				c.setCustomCheckBox("false");
				c.setCustomString("");
				c.setSessionCount("1");
				c.setPrerateType("update");
				break;
				
				
				
			case 8:
				break;
				
			case 9:
				break;
		}
	
		

	
}
}
