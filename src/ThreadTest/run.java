package ThreadTest;

import java.io.File;
import java.io.IOException;

import configuration.config;
import configuration.importData;

public class run {
	static config c;
public static void main (String args[]) {
	importData id = new importData();
	c=id.getConfig();
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

	int function = 5;
	
	c.setSessionCount("1");
	c.setCustomCheckBox("true");
	c.setCustomString("trkngnbr ='580150901558'");
	
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
			c.setInternationalCheckBox("false");
			c.setExpressCheckBox("true");	
			c.setGroundCheckBox("false");
			c.setEraWorkable("false");
			//c.setCustomCheckBox("true");
			//c.setCustomString("trkngnbr is not null and region='USA' and result='fail'");
			
			
			c.setSessionCount("2");
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
		//	c.setCustomCheckBox("true");
			//c.setCustomString("trkngnbr in ('794993961067')");
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
			//c.setCustomString("trkngnbr is not null and (result='fail' or result='na' or result is null)");
			//c.setCustomString("trkngnbr is not null and (result='fail' or result='na' or result is null)");
			
			
			c.setHeadlessString("false");
			c.setEraCase("3");
			
			break;
			
		case 6:
			c.setDatabaseDisabled("false");
			c.setDriverType("2");
			c.setCompatibleMode("false");
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
				//c.setCustomCheckBox("true");
				//c.setCustomString("trkngnbr is not null and result is null");
				//c.setSessionCount("2");
				
				break;
				
				case 8:
				break;
				
			case 9:
				c.setDatabaseDisabled("false");
				c.setDriverType("2");
				c.setCompatibleMode("false");
				
				break;
				
			case 10:
				c.setDatabaseDisabled("false");
				c.setDriverType("2");
				c.setCompatibleMode("false");
				c.setSessionCount("10");
				
				
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
				
				
				
			case 22:
				
				c.setDatabaseDisabled("false");
				c.setCustomCheckBox("true");
				c.setCustomString("trkngnbr is not null");
				
				
				 
		}
	
		

	
}
}
