package ThreadTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import configuration.config;
import configuration.importData;

public class base {
	static ArrayList<data> dataArray;
	
	static int sqlCount,tempCounter=0,function;;
	static int [][]minxMaxArray;
	static ArrayList<Object> threadArray= new ArrayList<Object>();
	//static ArrayList<rebillThread> singleRebillThreadArray= new ArrayList<rebillThread>();
	static String sqlQueryBase1="",sqlQueryBase2="",sqlQuery1="",sqlQuery2="";
	static config c;
	public static void main(String args[]) {
		int low=0;
		int high=0;
		
		importData id = new importData();
		c=id.getConfig();
		//Update your config commands 
		customConfig();
		
		//Amount of threads
		int threadCount=1;
		
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
		function=1;
		
		//Set your custom query start with 'where' here
		setSqlCustomQuery("where trkngnbr ='410287221702'");	
		
		//Stores data to a data object then puts it in array
		getDataDb(sqlQuery1,sqlQuery2);
		//Gets which segements data is in based on thread count.
		minMaxArrayMath(threadCount);
		
		
		
		for(int i=0;i<minxMaxArray.length;i++) {
			for(int j=0;j<minxMaxArray[i].length;j++) {
				System.out.println(minxMaxArray[i][j]);
			}
		}
		
		for(int i=0;i<minxMaxArray.length;i++) {
				
				//About to break up data into chunks based on partition
				ArrayList<data>	dataArrayPartition = new ArrayList<data>();	
				low= minxMaxArray[i][0];
				high=minxMaxArray[i][1];
				System.out.println(low);
				System.out.println(high);
				//Will get each segments top and bottom value
				for (int j=low;j<high;j++) {
					dataArrayPartition.add(dataArray.get(j));
				}
				//Will Create the Objects for each function X amount times based on thread count.. does not start the actual execution yet.
				switch (function) {
					case 1:	
					threadArray.add(new rebillThread(dataArrayPartition,c));
					break;
					case 2:	
				//	threadArray.add(new massRebillThread(dataArrayPartition,c));
					break;
					case 3:	
				//	threadArray.add(new singleRerateThread(dataArrayPartition,c));
					break;
					case 4:	
				//	threadArray.add(new massRerateThread(dataArrayPartition,c));
					break;
					case 5:	
				//	threadArray.add(new creditDebitThread(dataArrayPartition,c));
					break;
					case 6:	
				//	threadArray.add(new instantInvoiceThread(dataArrayPartition,c));
					break;
					case 7:	
				//	threadArray.add(new prerateSingleThread(dataArrayPartition,c));
					break;
					case 8:	
				//	threadArray.add(new prerateHoldThread(dataArrayPartition,c));
					break;
					case 9:	
				//	threadArray.add(new prsRerateThread(dataArrayPartition,c));
					break;
	}
			}
			
		//Now will begin the thread execution.
		
		for (Object rt: threadArray) {
			((Thread) rt).start();
		}
			
		
		
	}
	
	
	public static void setSqlCustomQuery(String sqlWhereQuery) {
		
		//sets up default query
				switch (function) {
				case 1:	
					 sqlQueryBase1="select RESULT, DESCRIPTION, TEST_INPUT_NBR	,TIN_COUNT	,TRKNGNBR,	REASON_CODE	,BILL_ACCT_NBR	,INVOICE_NBR_1,	INVOICE_NBR_2	,REGION	,USERNAME,	PASSWORD,	RS_TYPE,	COMPANY	,rebill_prerate,	WORKABLE,	DEFECT_FLG,	DEFECT_NBR  from rebill_regression ";
					 sqlQueryBase2="select count(*) from rebill_regression  ";
				break;
				case 2:	
					sqlQueryBase1="select result, description, test_input_nbr, rowcount, trkngnbr, reason_code, bill_acct_nbr,invoice_nbr_1, invoice_nbr_2,  region,  username,   password,  rs_Type, company from rebill_regression_mass ";
					sqlQueryBase2="select count(*) from  rebill_regression_mass ";
				break;
				case 3:	
					sqlQueryBase1="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR,	TIN_COUNT,	TRKNGNBR,	INVOICE_NBR_1	,INVOICE_NBR_2,	RATE_WEIGHT,	ACTUAL_WEIGHT,	WGT_TYPE,	LENGTH,	WIDTH,	HEIGHT	,WORKABLE,	DIM_TYPE,	PAYOR	,BILL_ACCT_NBR	,SERVICE_TYPE,	SERVICE_NAME,	PACKAGE_TYPE	,RERATE_TYPE,	REGION,	USERNAME,	PASSWORD,	RS_TYPE,	COMPANY,	VAL_DESC,	COMMENTS from era_rerate_view  ";
					sqlQueryBase2="select count(*) from  era_rerate_view  ";
				break;
				case 4:	
					sqlQueryBase1="select  result,  DESCRIPTION, test_Input_Nbr, tin_Count, trkngnbr, invoice_Nbr_1, invoice_Nbr_2, region, username , password,  rate_weight,wgt_type,length,height,width,dim_type, rerate_type, rs_Type ,company  , mass_rerate_combo from era_rerate_mass ";
					sqlQueryBase2="select count(*) from  era_rerate_mass";
				break;
				case 5:	
					sqlQueryBase1="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	CREDIT_FLG,	DEBIT_FLG,	DISPUTE_FLG,	RESOLVE_CREDIT_FLG,	WORKABLE ,REASON_CODE,	REASON_CATEGORY, ROOT_CAUSE,VAL_DESC from era_credit_debit  ";
					sqlQueryBase2="select count(*) from  era_credit_debit ";
				break;
				case 6:	
					sqlQueryBase1="select TEST_INPUT_NBR	,TRKNGNBR	,PAYOR_ACCT_NBR,	ITEM_PRCS_CD	,INSTNT_INV_FLG from instant_invoice_view   ";
					sqlQueryBase2="select count(*) from  instant_invoice_view ";
				break;
				case 7:	
					sqlQueryBase1="select PRE_RATE_TYPE_CD, DESCRIPTION,POD_SCAN ,TIN_COUNT ,TEST_INPUT_NBR ,TRKNGNBR ,PRERATE_AMT, CURRENCY_CD, APPROVER_ID ,CHRG_CD1 ,CHRG_AMT1 ,CHRG_CD2 ,CHRG_AMT2 ,CHRG_CD3, CHRG_AMT3, CHRG_CD4, CHRG_AMT4 from prerate_view  ";
					sqlQueryBase2="select count(*) from  prerate_view ";
				break;
				case 8:	
					sqlQueryBase1="select RESULT, DESCRIPTION,POD_SCAN,TEST_INPUT_NBR,TIN_COUNT,TRKNGNBR,TIN_COMMENT from prerate_hold_view  ";
					sqlQueryBase2="select count(*) from  prerate_hold_view ";
				break;
				case 9:	
					sqlQueryBase1="select TEST_INPUT_NBR	,TIN_COUNT	,ACCT1,	ACCT2,	TRK_NO1,	TRK_NO2	,INVOICE_NBR_1,	INV_NO2,	SERVICE1,	SERVICE2,	REQUEST_TYPE,	ACCT_TYPE,	ACCNAME from rerate_master  ";
					sqlQueryBase2="select count(*) from  rerate_master ";
				break;
				
				
				}
				
				//Gets custom where clause
				sqlQuery1=sqlQueryBase1+sqlWhereQuery;
				sqlQuery2=sqlQueryBase2+sqlWhereQuery;
		
	}
	
	public static void minMaxArrayMath(int threadCount){
		System.out.println(sqlCount+"   "+threadCount);
		int partition = sqlCount/threadCount; 
		minxMaxArray = new int[threadCount][2];
		int lowLimit=0;
		
		for(int i = 0;i<threadCount; i++)
	    {
			if(i==0) {
				minxMaxArray[i][0]=lowLimit;
			}
			else {
				minxMaxArray[i][0]=(lowLimit+1);
			}
				
				minxMaxArray[i][1]=(lowLimit += partition);
	            System.out.println("Part: "+(i+1)+" min: "+minxMaxArray[i][0]+" max: "+minxMaxArray[i][1]);
	          
	    }
		if(threadCount<sqlCount) {
			minxMaxArray[threadCount-1][1]=sqlCount-1;
		}
	}
	
	public static void getDataDb(String sql1,String sql2) {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			dataArray = new ArrayList<data>();
			Statement stmt = null;
			ResultSet rs = null;
		

       	try {
           	
        		stmt = con.createStatement();
            	rs = stmt.executeQuery(sql1);
            	
            	 
            	 while(rs.next()) {
            		 tempCounter++;
            		dataArray.add(new data(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),tempCounter));	
            		 //rebillThreadArrayAll.add(new rebillThread(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18)));
            	 }
            	
        	}
        	catch(Exception e) {
        		System.out.println(e);
        	}
       	
     	try {
           	
    		stmt = con.createStatement();
        	rs = stmt.executeQuery(sql2);
            
        	 while(rs.next()) {
        	
        		 sqlCount=rs.getInt(1);
        	 }
        
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
       	
       	
       	
       	
       	
       	try {
       		con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
       	
       	
       	
	}
	
	public static void customConfig () {
		
    	c.setLevel("3");
    	c.setDriverType("2");	
    	c.setCompatibleMode("false");
    	c.setSource("db");
    	c.setDatabaseDisabled("false");
		c.setHeadlessString("false");

	
		
		
	}
}
