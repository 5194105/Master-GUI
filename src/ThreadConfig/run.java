package ThreadConfig;

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
	// 1 Single Rebill -- Works
	// 2 Mass Rebill -- Not Working
	// 3 Single Rerate -- Works
	// 4 Mass Rerate -- Not Working
	// 5 Credit Debit -- Works
	// 6 Instant Invoice -- Works
	// 7 Prerate Single -- Works
	// 8 Prerate Hold -- Works (Not Tested yet)
	// 9 PRS Rerate -- Not Working
	// 10 Instant Invoice Device -- Works
	// 22 ERA Rerate Upload to DB -- Works
	
	
	int function = 3;
	c.setSessionCount("1");
	c.setCustomCheckBox("true");
	c.setCustomString("test_input_nbr in ('238277', '137335', '194522', '236343', '236584', '235694', '237339', '240687', '236591', '237125', '244819', '167535', '188503', '242069', '242346', '219894', '226571') and defect_nbr='Drop Mail to TD'");
	c.setEraCase("3");
	
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
			
			
			break;
			
		case 2:
			
			break;
			
			
			
			//Single ERA Rerate
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
			c.setDatabaseDisabled("false");
		
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
			c.setHeadlessString("false");
			
			
			break;
			
		case 6:
			c.setDatabaseDisabled("false");
			c.setDriverType("2");
			c.setCompatibleMode("false");
			c.setAllCheckBox("false");
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
				
				
				break;
				
				case 8:
					
					c.setExcelPath("C:\\Users\\FedExUser\\Documents\\PRERATE_UPDATE.xlsx");
			    	c.setLevel("3");
			    	c.setDriverType("2");
			    	c.setCompatibleMode("true");
			    	c.setSource("db");
			    	c.setAllCheckBox("false");
			    	c.setNullCheckBox("true");
			    	c.setFailedCheckBox("true");
					c.setDatabaseDisabled("false");
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
				
				/*
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
				*/
				break;
				
				
				
			case 22:
				
				c.setDatabaseDisabled("false");
				
				
				
				 
		}
	
		

	
}
}
