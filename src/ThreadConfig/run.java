package ThreadConfig;

import configuration.config;
import configuration.importData;

public class run {
	static config c;
	
	public static void main (String args[]) {
		System.out.println("Starting automation...");
		importData id = new importData();
		c=id.getConfig();
		//Function Type
		// 1 Single Rebill -- Works
		// 2 Mass Rebill -- Not Working
		// 3 Single Rerate -- Works
		// 4 Mass Rerate --  Works
		// 5 Credit Debit -- Works
		//	--1 Credit -- Works
		//	--2 Debit -- Works
		//	--3 Dispute -- Works
		//	--4 Resolve Credit -- Works
		//	--5 Resolve Rebill -- Works
		// 6 Instant Invoice -- Works
		// 7 Prerate Single -- Works
		// 8 Prerate Hold -- Works 
		// 9 PRS Rerate -- Works
		// 10 Instant Invoice Device -- Works
		// 11 GFBO --  Works
		// 12 EC UD --  Works
		// 13 EC Device --  Works
		// 14 emass -- doesnt work
		
		// 22 ERA Single Rerate Upload to DB -- Works
		// 23 ERA Mass Rerate Upload to DB -- Works
		// 24 ERA Single Rebill Upload to DB
		// 25 Prerate Single Upload to DB
		
		//Set of common custom values.
		int function = 1;
		c.setSessionCount("1");
		c.setCustomCheckBox("true");
		c.setCustomString("trkngnbr is not null");
		c.setSource("db");
		c.setLevel("3");
		c.setCycle("11");
		c.setRunAllEc("false");
		c.setEraCase("2");
		c.setEmassCase("4");
		c.setDatabaseDisabled("false");
		
		
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
				
			//REBILL MASS
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
				
			//Mass Rerate
			case 4:
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
				
				
			//Credit, Debit, Dispute, Credit Resolve, Rebill Resolve
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
				
			//Instant Invoice (DB ONLY)
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
				
			//Prerate Hold
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
				
			//PRS RERATE
			case 9:
				c.setDatabaseDisabled("false");
				c.setDriverType("1");
				c.setCompatibleMode("false");
				c.setStartDate("01/01/2000");
				c.setEndDate("05/07/2021");
				break;
				
			//Instant Invoice All	
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
					
			//GFBO
			case 11:
				c.setDatabaseDisabled("false");
				c.setDriverType("2");
				c.setCompatibleMode("false");
				break;
				
			//EC UD
			case 12:
				c.setDatabaseDisabled("false");
				c.setDriverType("2");
				c.setCompatibleMode("false");
				break;
				
			//EC DEVICE
			case 13:
				c.setDatabaseDisabled("false");
				c.setDriverType("2");
				c.setCompatibleMode("false");
				break;
				
			//EMASS
			case 14:
				c.setDatabaseDisabled("false");
				c.setDriverType("2");
				c.setCompatibleMode("false");
				break;
				
			//ERA Single Rerate Upload to DB -- Works
			case 22:
				c.setDatabaseDisabled("false");
				break;
				
			//ERA Mass Rerate Upload to DB
			case 23:
				c.setDatabaseDisabled("false");
				break;
				
			//ERA Single Rebill Upload to DB
			case 24:
				c.setDatabaseDisabled("false");
				break;
				
			//Prerate Single Upload to DB
			case 25:
				c.setDatabaseDisabled("false");
				break;
				 
			}
		}
	}
