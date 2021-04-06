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
	 ArrayList<data> dataArray;
	
	 int sqlCount,tempCounter=0,function;;
	 int [][]minxMaxArray;
	 ArrayList<Object> threadArray= new ArrayList<Object>();
	//static ArrayList<rebillThread> singleRebillThreadArray= new ArrayList<rebillThread>();
	
	 config c;
	String allCheckBox,customCheckBox,customString,nullCheckBox,failedCheckBox,domesticCheckBox,internationalCheckBox,expressCheckBox,groundCheckBox,eraWorkable,databaseSqlCount,databaseSqlQuery;
	//public static void main(String args[]) {
	public base(config c,int function) {
		int low=0;
		int high=0;
		this.c=c;
		this.function=function;
		setVars();
		
		//Amount of threads
		int threadCount=Integer.parseInt(c.getSessionCount());
		
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
		
		
	
		setSqlQuery();
		//Stores data to a data object then puts it in array
		getDataDb();
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
	
	
	
	
	public  void setVars() {
		
	
		 allCheckBox=c.getAllCheckBox();
		 nullCheckBox=c.getNullCheckBox();
		 failedCheckBox=c.getFailedCheckBox();
		 domesticCheckBox=c.getDomesticCheckBox();
		 internationalCheckBox=c.getInternationalCheckBox();
		 expressCheckBox=c.getExpressCheckBox();
		 groundCheckBox=c.getGroundCheckBox();
		 customCheckBox= c.getCustomCheckBox();
		 customString= c.getCustomString();	
		 eraWorkable=c.getEraWorkable();
		

		
	}
	
	
	public  void minMaxArrayMath(int threadCount){
		System.out.println("Data Count:"+sqlCount+"   Thread Count:"+threadCount);
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
	
	public  void getDataDb() {
		Connection con=null;
		try {
			con=c.getGtmRevToolsConnection();		
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			dataArray = new ArrayList<data>();
			Statement stmt = null;
			ResultSet rs = null;
		

       	try {
           	
        		stmt = con.createStatement();
            	rs = stmt.executeQuery(databaseSqlQuery);
            	
            	 
            	 while(rs.next()) {
            		 tempCounter++;
            		dataArray.add(new data(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),tempCounter));	
            		 }
            	
        	}
        	catch(Exception e) {
        		System.out.println(e);
        	}
       	
     	try {
           	
    		stmt = con.createStatement();
        	rs = stmt.executeQuery(databaseSqlCount);
            
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public  void setSqlQuery() {
		System.out.println("Inside QUERY");
		//sets up default query
				switch (function) {
				case 1:	
					 
					 databaseSqlCount="select count(*) as total from rebill_regression ";
				     databaseSqlQuery="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR	,TIN_COUNT	,TRKNGNBR,	REASON_CODE	,BILL_ACCT_NBR	,INVOICE_NBR_1,	INVOICE_NBR_2	,REGION	,USERNAME,	PASSWORD,	RS_TYPE,	COMPANY	,rebill_prerate,	WORKABLE,	DEFECT_FLG,	DEFECT_NBR	,DEFECT_CONTACT from rebill_regression ";
				    	
				    	if (allCheckBox.equals("true")) {
				    		databaseSqlCount+="where trkngnbr is not null";
				    		databaseSqlQuery+="where trkngnbr is not null ";
				    	}
				    	
				    	System.out.println(customCheckBox);
				    	System.out.println(customString);
				    	
				    	if (customCheckBox.equals("false")) {
				    	
				    	if (allCheckBox.equals("false")) {
				    		databaseSqlCount+="where ";
				    		databaseSqlQuery+="where ";

				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="(result is null or result ='fail') ";
				    		databaseSqlQuery+="(result is null or result ='fail') ";
				    	}
				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
				    		databaseSqlCount+="result is null ";
				    		databaseSqlQuery+="result is null ";
				    	}
				    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="result ='fail' ";
				    		databaseSqlQuery+="result ='fail' ";
				    	}
				    	if (domesticCheckBox.equals("true") && internationalCheckBox.equals("false")) {
				    		databaseSqlCount+="and rs_type='DM' ";
				    		databaseSqlQuery+="and rs_type='DM' ";
				    	}
				    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("false")) {
				    		databaseSqlCount+="and rs_type='IL' ";
				    		databaseSqlQuery+="and rs_type='IL' ";
				    	}
				    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("true")) {
				    		databaseSqlCount+="and rs_type in ('DM','IL')";
				    		databaseSqlQuery+="and rs_type in ('DM','IL')";
				    	}
				    	
				    	if (expressCheckBox.equals("true") && groundCheckBox.equals("false")) {
				    		databaseSqlCount+="and company='EP' ";
				    		databaseSqlQuery+="and company='EP' ";
				    	}
				    	if (groundCheckBox.equals("true") && expressCheckBox.equals("false")) {
				    		databaseSqlCount+="and company='GD' ";
				    		databaseSqlQuery+="and company='GD' ";
				    	}
				    	
				    	if (groundCheckBox.equals("true") && expressCheckBox.equals("true")) {
				    		databaseSqlCount+="and company in ('GD','EP') ";
				    		databaseSqlQuery+="and company in ('GD','EP') ";
				    	}
				    	/*
				    	if (eraWorkable.equals("true")) {
				    		databaseSqlCount+="and workable='Y'";
				    		databaseSqlQuery+="and workable='Y'";
				    	}
				    	*/
				    		}
				    			}
				    	else if (customCheckBox.equals("true")){
				    		databaseSqlCount+="where trkngnbr is not null and "+customString;
				    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
				    	}
				    	
					 
					 
					 
					 
					 
				break;
				case 2:	
					databaseSqlQuery="select result, description, test_input_nbr, rowcount, trkngnbr, reason_code, bill_acct_nbr,invoice_nbr_1, invoice_nbr_2,  region,  username,   password,  rs_Type, company from rebill_regression_mass ";
					databaseSqlCount="select count(*) from  rebill_regression_mass ";
				break;
				case 3:	
					databaseSqlQuery="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR,	TIN_COUNT,	TRKNGNBR,	INVOICE_NBR_1	,INVOICE_NBR_2,	RATE_WEIGHT,	ACTUAL_WEIGHT,	WGT_TYPE,	LENGTH,	WIDTH,	HEIGHT	,WORKABLE,	DIM_TYPE,	PAYOR	,BILL_ACCT_NBR	,SERVICE_TYPE,	SERVICE_NAME,	PACKAGE_TYPE	,RERATE_TYPE,	REGION,	USERNAME,	PASSWORD,	RS_TYPE,	COMPANY,	VAL_DESC,	COMMENTS from era_rerate_view  ";
					databaseSqlCount="select count(*) from  era_rerate_view  ";
				break;
				case 4:	
					databaseSqlQuery="select  result,  DESCRIPTION, test_Input_Nbr, tin_Count, trkngnbr, invoice_Nbr_1, invoice_Nbr_2, region, username , password,  rate_weight,wgt_type,length,height,width,dim_type, rerate_type, rs_Type ,company  , mass_rerate_combo from era_rerate_mass ";
					databaseSqlCount="select count(*) from  era_rerate_mass";
				break;
				case 5:	
					databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	CREDIT_FLG,	DEBIT_FLG,	DISPUTE_FLG,	RESOLVE_CREDIT_FLG,	WORKABLE ,REASON_CODE,	REASON_CATEGORY, ROOT_CAUSE,VAL_DESC from era_credit_debit  ";
					databaseSqlCount="select count(*) from  era_credit_debit ";
				break;
				case 6:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TRKNGNBR	,PAYOR_ACCT_NBR,	ITEM_PRCS_CD	,INSTNT_INV_FLG from instant_invoice_view   ";
					databaseSqlCount="select count(*) from  instant_invoice_view ";
				break;
				case 7:	
					databaseSqlQuery="select PRE_RATE_TYPE_CD, DESCRIPTION,POD_SCAN ,TIN_COUNT ,TEST_INPUT_NBR ,TRKNGNBR ,PRERATE_AMT, CURRENCY_CD, APPROVER_ID ,CHRG_CD1 ,CHRG_AMT1 ,CHRG_CD2 ,CHRG_AMT2 ,CHRG_CD3, CHRG_AMT3, CHRG_CD4, CHRG_AMT4 from prerate_view  ";
					databaseSqlCount="select count(*) from  prerate_view ";
				break;
				case 8:	
					databaseSqlQuery="select RESULT, DESCRIPTION,POD_SCAN,TEST_INPUT_NBR,TIN_COUNT,TRKNGNBR,TIN_COMMENT from prerate_hold_view  ";
					databaseSqlCount="select count(*) from  prerate_hold_view ";
				break;
				case 9:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,ACCT1,	ACCT2,	TRK_NO1,	TRK_NO2	,INVOICE_NBR_1,	INV_NO2,	SERVICE1,	SERVICE2,	REQUEST_TYPE,	ACCT_TYPE,	ACCNAME from rerate_master  ";
					databaseSqlCount="select count(*) from  rerate_master ";
				break;
				
				
				}
				
				System.out.println(databaseSqlQuery);
				System.out.println(databaseSqlCount);
				
				
				
		
	}
	

}
