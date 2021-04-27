package ThreadConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ThreadCreditDebitDisputeResolve.creditDebitThread;
import ThreadInstantInvoice.instantInvoiceThread;
import ThreadMassERARerate.massRerateThread;
import ThreadSingleERARerate.eraRerateUpload;
import ThreadSingleERARerate.singleRerateThread;
import ThreadSinglePrerate.prerateHoldThread;
import ThreadSinglePrerate.prerateThread;
import ThreadSingleRebill.rebillThread;
import configuration.config;
import configuration.importData;

public class base {
	 ArrayList<data> dataArray;
	
	 int sqlCount,tempCounter=0,function;;
	 int [][]minxMaxArray;
	 ArrayList<Object> threadArray= new ArrayList<Object>();
	//static ArrayList<rebillThread> singleRebillThreadArray= new ArrayList<rebillThread>();
	int  dbVal=1;
	 config c;
	 String eraCase;
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
		// 10 Instant Invoice Device
		
	
		//DbVal 
		// 1 GTM
		// 2 RTM
		dbVal=1;
		
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
					threadArray.add(new singleRerateThread(dataArrayPartition,c));
					break;
					case 4:	
					threadArray.add(new massRerateThread(dataArrayPartition,c));
					break;
					case 5:	
					threadArray.add(new creditDebitThread(dataArrayPartition,c));
					break;
					case 6:	
					threadArray.add(new instantInvoiceThread(dataArrayPartition,c));
					break;
					case 7:	
					threadArray.add(new prerateThread(dataArrayPartition,c));
					break;
					case 8:	
					threadArray.add(new prerateHoldThread(dataArrayPartition,c));
					break;
					case 9:	
				//	threadArray.add(new prsRerateThread(dataArrayPartition,c));
					break;
					case 10:	
					threadArray.add(new instantInvoiceThread(dataArrayPartition,c));
					break;
					
					case 22:	
						threadArray.add(new eraRerateUpload(dataArrayPartition,c));
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
			if (dbVal==1) {
			con=c.getGtmRevToolsConnection();
			}
			if (dbVal==2) {
				con=c.getRtmCon();
				}
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
            		switch(function) {
            		case 1:
            		dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),tempCounter));	
            		break;
            		
            		case 3:
                		dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(21)),nullCheck(rs.getString(22)),nullCheck(rs.getString(23)),nullCheck(rs.getString(24)),nullCheck(rs.getString(25)),nullCheck(rs.getString(26)),nullCheck(rs.getString(27)),nullCheck(rs.getString(28)),tempCounter));	
                		break;
            		
                		
            		case 5:
            			eraCase = c.getEraCase();
                    	dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),eraCase));	
                    	break;
                		
                    	
            		 case 6:
            			       			 
              			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),"5194105","5194105",tempCounter));	
              		     	break;
                    	
            		case 7:
            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19))));	
            		     	break;
            		     	
            		     	
            		case 8:
            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7))));	
            		     	break;
            		     	
            		
            		
            	 case 10:
         			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),"5194105","5194105",tempCounter));	
         		     	break;
         		
            		
            	 case 22:
           			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3))));	
           		     	break;
            		}
            		
            		
            	
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
	
	
	
	
	public String nullCheck(String temp) {
		if (temp==null){
			temp="";
		}
		return temp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public  void setSqlQuery() {
		System.out.println("Inside QUERY");
		//sets up default query
				switch (function) {
				case 1:	
					 
					 databaseSqlCount="select count(*) as total from rebill_regression ";
				     databaseSqlQuery="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR	,TIN_COUNT	,TRKNGNBR,	REASON_CODE	,BILL_ACCT_NBR	,INVOICE_NBR_1,	INVOICE_NBR_2	,REGION	,USERNAME,	PASSWORD,	RS_TYPE,	COMPANY	,rebill_prerate, length,width,height,actual_weight,	WORKABLE,	DEFECT_FLG,	DEFECT_NBR	,DEFECT_CONTACT from rebill_regression ";
				    	
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
				
				//Single ERA Rerate
				case 3:	
					 databaseSqlCount="select count(*) as total from era_rerate_view where trkngnbr is not null ";
			    	 databaseSqlQuery="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR,	TIN_COUNT,	TRKNGNBR,	INVOICE_NBR_1	,INVOICE_NBR_2,	RATE_WEIGHT,	ACTUAL_WEIGHT,	WGT_TYPE,	LENGTH,	WIDTH,	HEIGHT	,WORKABLE,	DIM_TYPE,	PAYOR	,BILL_ACCT_NBR	,SERVICE_TYPE,	SERVICE_NAME,	PACKAGE_TYPE	,RERATE_TYPE,	REGION,	USERNAME,	PASSWORD,	RS_TYPE,	COMPANY,	VAL_DESC,	COMMENTS from era_rerate_view where trkngnbr is not null ";
			    	
			    	if (allCheckBox.equals("true")) {
			    		databaseSqlCount+="where trkngnbr is not null";
			    		databaseSqlQuery+="where trkngnbr is not null ";
			    	}
			    	
			    	System.out.println(customCheckBox);
			    	System.out.println(customString);
			    	
			    	if (customCheckBox.equals("false")) {
			    	
			    	if (allCheckBox.equals("false")) {
			    		databaseSqlCount+="and ";
			    		databaseSqlQuery+="and ";
			    	
			    	
			    	
			    	
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
			    	if (eraWorkable.equals("true")) {
			    		databaseSqlCount+="and workable='Y'";
			    		databaseSqlQuery+="and workable='Y'";
			    	}
			    	
			    		}
			    			}
			    	else if (customCheckBox.equals("true")){
			    		databaseSqlCount+=" and "+customString;
			    		databaseSqlQuery+=" and "+customString;
			    	}
			       	
				break;
				
				
				
				
				
				
				
				
				
				case 4:	
					databaseSqlQuery="select  result,  DESCRIPTION, test_Input_Nbr, tin_Count, trkngnbr, invoice_Nbr_1, invoice_Nbr_2, region, username , password,  rate_weight,wgt_type,length,height,width,dim_type, rerate_type, rs_Type ,company  , mass_rerate_combo from era_rerate_mass ";
					databaseSqlCount="select count(*) from  era_rerate_mass";
				
					  	
			    	
			    	if (allCheckBox.equals("true")) {
			    		databaseSqlCount+="where trkngnbr is not null";
			    		databaseSqlQuery+="where trkngnbr is not null ";
			    	}
			    	
			    	System.out.println(customCheckBox);
			    	System.out.println(customString);
			    	
			    	if (customCheckBox.equals("false")) {
			    	
			    	if (allCheckBox.equals("false")) {
			    		databaseSqlCount+="where trkngnbr is not null and ";
			    		databaseSqlQuery+="where trkngnbr is not null and ";
			    	
			    	
			    	
			    	
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
			    	
			    	if (eraWorkable.equals("true")) {
			    		databaseSqlCount+="and workable='Y'";
			    		databaseSqlQuery+="and workable='Y'";
			    	}
			    	
			    		}
			    			}
			    	else if (customCheckBox.equals("true")){
			    		databaseSqlCount+="where trkngnbr is not null and "+customString;
			    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
			    	}
			    	
			    	databaseSqlCount+=" order by test_input_nbr";
					databaseSqlQuery+=" order by test_input_nbr";
			    	 
					
					break;
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//Credit Debit
				case 5:	
					 
					eraCase = c.getEraCase();

					switch(eraCase) {
					case "1":
						databaseSqlCount="select count(*) as total from era_credit ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_credit " ;
					break;
					case "2":
						databaseSqlCount="select count(*) as total from era_debit ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_debit " ;
						break;
					case "3":
						databaseSqlCount="select count(*) as total from era_dispute ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_dispute " ;
						break;
					case "4":
						databaseSqlCount="select count(*) as total from era_resolve_credit ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_resolve_credit " ;
						break;
					}
						
					
					if (allCheckBox.equals("true")) {
						databaseSqlCount+="where trkngnbr is not null";
						databaseSqlQuery+="where trkngnbr is not null ";
					}
					
					System.out.println(customCheckBox);
					System.out.println(customString);
					
					if (customCheckBox.equals("false")) {
					
					if (allCheckBox.equals("false")) {
						databaseSqlCount+="where trkngnbr is not null and ";
						databaseSqlQuery+="where trkngnbr is not null and ";
					
					
					
					
					if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
						databaseSqlCount+="(result is null or result ='fail') and (";
						databaseSqlQuery+="(result is null or result ='fail') and (";
					}
					if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
						databaseSqlCount+="result is null and (";
						databaseSqlQuery+="result is null and (";
					}
					if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
						databaseSqlCount+="result ='fail' and (";
						databaseSqlQuery+="result ='fail' and (";
					}
						
					/*
					if (creditCheckBox.equals("true")){
						databaseSqlCount+=" CREDIT_FLG='Y' or ";
						databaseSqlQuery+=" CREDIT_FLG='Y' or ";
						kounter++;
					}
					if (creditCheckBox.equals("true")){
						databaseSqlCount+=" DEBIT_FLG='Y' or ";
						databaseSqlQuery+=" DEBIT_FLG='Y' or ";
						kounter++;
					}
					if (creditCheckBox.equals("true")){
						databaseSqlCount+=" DISPUTE_FLG='Y' or ";
						databaseSqlQuery+=" DISPUTE_FLG='Y' or ";
						kounter++;
					}
					if (creditCheckBox.equals("true")){
						databaseSqlCount+=" RESOLVE_CREDIT_FLG='Y' ";
						databaseSqlQuery+=" RESOLVE_CREDIT_FLG='Y' ";
						
					}
					
					databaseSqlCount+=")";
					databaseSqlQuery+=")";
					if ( kounter<=2 ) {
						databaseSqlQuery = databaseSqlQuery.replace("or", "");
						databaseSqlCount = databaseSqlCount.replace("or", "");
					}

					*/
						}
						
							}
					else if (customCheckBox.equals("true")){
						databaseSqlCount+="where trkngnbr is not null and "+customString;
						databaseSqlQuery+="where trkngnbr is not null and "+customString;
					}
				break;
				
				
				
				case 6:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TRKNGNBR	,PAYOR_ACCT_NBR,	ITEM_PRCS_CD	,INSTNT_INV_FLG from instant_invoice_view   ";
					databaseSqlCount="select count(*) from  instant_invoice_view ";
					
					if (allCheckBox.equals("true")) {
			    		databaseSqlCount+="where trkngnbr is not null and instnt_inv_flg is null ";
			    		databaseSqlQuery+="where trkngnbr is not null and instnt_inv_flg is null";
			    	}
			    	
			    	System.out.println(customCheckBox);
			    	System.out.println(customString);
			    	
			    	if (customCheckBox.equals("false")) {
			    	
			    	if (allCheckBox.equals("false")) {
			    		databaseSqlCount+="where  ";
			    		databaseSqlQuery+="where  ";

			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="(instnt_inv_flg is null or instnt_inv_flg ='fail') ";
			    		databaseSqlQuery+="(instnt_inv_flg is null or instnt_inv_flg ='fail') ";
			    	}
			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
			    		databaseSqlCount+="instnt_inv_flg is null ";
			    		databaseSqlQuery+="instnt_inv_flg is null ";
			    	}
			    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="instnt_inv_flg ='fail' ";
			    		databaseSqlQuery+="instnt_inv_flg ='fail' ";
			    	}
			    	
			    	}
			    	}
			    	else if (customCheckBox.equals("true")){
			    		databaseSqlCount+="where (instnt_inv_flg is null or instnt_inv_flg ='fail')  and "+customString;
			    		databaseSqlQuery+="where (instnt_inv_flg is null or instnt_inv_flg ='fail')  and "+customString;
			    	}
			    	
					
				break;
			
				//Prerate Single
				case 7:	
					
					
					
					 databaseSqlCount="select count(*) as total from prerate_view ";
				  	 databaseSqlQuery="select result,description,TEST_INPUT_NBR,TIN_COUNT ,TRKNGNBR ,PRE_RATE_TYPE_CD,PRERATE_AMT, CURRENCY_CD, APPROVER_ID ,CHRG_CD1 ,CHRG_AMT1 ,CHRG_CD2 ,CHRG_AMT2 ,CHRG_CD3, CHRG_AMT3, CHRG_CD4, CHRG_AMT4,val,expected_status from prerate_view ";
				    
				  	 if (customCheckBox.equals("true")) {
				  		databaseSqlCount+="where trkngnbr is not null and "+customString;
			    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
				  		 
				  	 }
				  	 else
				  	 {
				    	if (allCheckBox.equals("false")) {
				    		databaseSqlCount+="where trkngnbr is not null and ";
				    		databaseSqlQuery+="where trkngnbr is not null and ";
				    	}
				    
				    	 if (allCheckBox.equals("true")) {
				     		databaseSqlCount+="where trkngnbr is not null  ";
				     		databaseSqlQuery+="where trkngnbr is not null   ";
				     	}
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
				    
				    	}}
				    	
				break;
				
				
				
				//Prerate Hold
				case 8:	
					databaseSqlQuery="select RESULT, DESCRIPTION,POD_SCAN,TEST_INPUT_NBR,TIN_COUNT,TRKNGNBR,TIN_COMMENT from prerate_hold_view  ";
					databaseSqlCount="select count(*) from  prerate_hold_view ";
					
					
					
					if (customCheckBox.equals("true")) {
				  		databaseSqlCount+="where trkngnbr is not null and "+customString;
			    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
				  		 
				  	 }
					else {
				    	if (allCheckBox.equals("false")) {
				    		databaseSqlCount+="where trkngnbr is not null and ";
				    		databaseSqlQuery+="where trkngnbr is not null and ";
				    	}
				    
				    	 if (allCheckBox.equals("true")) {
				     		databaseSqlCount+="where trkngnbr is not null  ";
				     		databaseSqlQuery+="where trkngnbr is not null   ";
				     	}
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
				    	}
					
				break;
				case 9:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,ACCT1,	ACCT2,	TRK_NO1,	TRK_NO2	,INVOICE_NBR_1,	INV_NO2,	SERVICE1,	SERVICE2,	REQUEST_TYPE,	ACCT_TYPE,	ACCNAME from rerate_master  ";
					databaseSqlCount="select count(*) from  rerate_master ";
				break;
				
				
				
				case 10:	
					databaseSqlQuery="select trkngnbr,PAYOR_ACCT_NBR from ( select c.test_input_nbr,c.trkngnbr, PAYOR_ACCT_NBR,INSTNT_INV_FLG, case when cntry_cd is null then 'N' else 'Y' END as TimePeriodEligble, TO_CHAR(LPAR_ENHCMNT_DT + 2-(5/24), 'YYYY-MM-DD HH:MI:SS AM') as RM_TIME_STAMP from INTL_EXPRS_ONLN_SCHEMA.INTL_online_revenue_item@L3_IORE a join INTL_EXPRS_ONLN_SCHEMA.INTL_package@L3_IORE b on a.ONLN_REV_ITEM_ID = b.ONLN_REV_ITEM_ID  join rtm.batch_shipping_results c on c.trkngnbr=b.pkg_trkng_nbr join INTL_EXPRS_ONLN_SCHEMA.intl_onln_cust_addr_info@L3_IORE d on b.ONLN_REV_ITEM_ID=d.ONLN_REV_ITEM_ID left join INTL_EXPRS_ONLN_SCHEMA.time_period_country@L3_IORE e on d.CUST_CNTRY_CD=e.cntry_cd join  intl_EXPRS_ONLN_SCHEMA.intl_package_event@L3_IORE f on b.onln_pkg_id = f.onln_pkg_id  join INTL_EXPRS_ONLN_SCHEMA.intl_rev_item_payor@L3_IORE i on b.ONLN_REV_ITEM_ID=i.ONLN_REV_ITEM_ID where CYCLE = '1' and LEVELS = '3' and rs_type = 'IL' and company = 'EP' and src_org='B' and CUST_ROLE_TYPE_CD='B'  and item_prcs_cd in ('OR','ER') and device like '%DTT%') where TimePeriodEligble='Y' and INSTNT_INV_FLG is null order by trkngnbr desc";
					databaseSqlCount="select count(*) from ( select c.test_input_nbr,c.trkngnbr, PAYOR_ACCT_NBR,INSTNT_INV_FLG, case when cntry_cd is null then 'N' else 'Y' END as TimePeriodEligble, TO_CHAR(LPAR_ENHCMNT_DT + 2-(5/24), 'YYYY-MM-DD HH:MI:SS AM') as RM_TIME_STAMP from INTL_EXPRS_ONLN_SCHEMA.INTL_online_revenue_item@L3_IORE a join INTL_EXPRS_ONLN_SCHEMA.INTL_package@L3_IORE b on a.ONLN_REV_ITEM_ID = b.ONLN_REV_ITEM_ID  join rtm.batch_shipping_results c on c.trkngnbr=b.pkg_trkng_nbr join INTL_EXPRS_ONLN_SCHEMA.intl_onln_cust_addr_info@L3_IORE d on b.ONLN_REV_ITEM_ID=d.ONLN_REV_ITEM_ID left join INTL_EXPRS_ONLN_SCHEMA.time_period_country@L3_IORE e on d.CUST_CNTRY_CD=e.cntry_cd join  intl_EXPRS_ONLN_SCHEMA.intl_package_event@L3_IORE f on b.onln_pkg_id = f.onln_pkg_id  join INTL_EXPRS_ONLN_SCHEMA.intl_rev_item_payor@L3_IORE i on b.ONLN_REV_ITEM_ID=i.ONLN_REV_ITEM_ID where CYCLE = '1' and LEVELS = '3' and rs_type = 'IL' and company = 'EP' and src_org='B' and CUST_ROLE_TYPE_CD='B'  and item_prcs_cd in ('OR','ER') and device like '%DTT%') where TimePeriodEligble='Y' and INSTNT_INV_FLG is null order by trkngnbr desc ";
					dbVal=2;
					break;
				
				case 22:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,trkngnbr from era_rerate_view  ";
					databaseSqlCount="select count(*) from  era_rerate_view ";
					
					
					 if (customCheckBox.equals("true")) {
					  		databaseSqlCount+="where trkngnbr is not null and "+customString;
				    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
					  		 
					  	 }
					
				break;
				}
				
				System.out.println(databaseSqlQuery);
				System.out.println(databaseSqlCount);
				
				
				
		
	}
	

}
