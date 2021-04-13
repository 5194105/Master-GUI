package ThreadTest;

import java.io.File;
import java.io.IOException;

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
	// 10 Instant Invoice Device

	int function = 10;
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
			
			
		//Credit and Debit
		case 5:
			
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
			c.setCustomString("trkngnbr is not null and CREDIT_FLG='Y'");
			
			c.setHeadlessString("false");

			c.setSessionCount("1");
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
				c.setDatabaseDisabled("false");
				c.setCustomCheckBox("false");
				c.setCustomString("");
				c.setSessionCount("2");
				
				break;
				
				case 8:
				break;
				
			case 9:
				break;
				
			case 10:
				c.setDatabaseDisabled("false");
				c.setDriverType("2");
				c.setCompatibleMode("false");
				c.setSessionCount("16");
				
				
				File file = new File("E:\\Everyone Workspace Folders\\stephen\\Master-GUI\\instantInvoice.txt");
				if (file.exists()) {
					file.delete();
				}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				break;
		}
	
		

	
}
}
