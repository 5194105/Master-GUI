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
	 importData id = new importData();
	 c=id.getConfig();
	 customConfig(1);
	 base b = new base(c,1);
}
public static void customConfig (int function) {
		switch (function) {
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
		}
	
	
}
}
