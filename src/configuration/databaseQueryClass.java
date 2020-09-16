package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseQueryClass {
	config c;
	Connection GTMcon;
	String function,allCheckBox,failedCheckBox,nullCheckBox,customCheckBox,customString,domesticCheckBox,internationalCheckBox,expressCheckBox,groundCheckBox;
	public databaseQueryClass(config c) {
		
		this.c=c;
		this.function=c.getFunction();
		allCheckBox=c.getAllCheckBox();
		failedCheckBox=c.getFailedCheckBox();
		nullCheckBox=c.getNullCheckBox();
		customCheckBox=c.getCustomCheckBox();
		customString=c.getCustomString();
		domesticCheckBox=c.getDomesticCheckBox();
		internationalCheckBox=c.getInternationalCheckBox();
		expressCheckBox=c.getExpressCheckBox();
		groundCheckBox=c.getGroundCheckBox();
		setupVariables();
		
		
	
	}
	
public void setupVariables(){
	
	try {
		c.setGtmRevToolsConnection();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		GTMcon=c.getGtmRevToolsConnection();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
	
	
	
	public void runDbQuery() {
		try {
		
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd=null;
		String databaseSqlCount="";
		String databaseSqlQuery="";
		String[][] allData=null;
    		if(function.equals("rebill")) {
    			 databaseSqlCount="select count(*) as total from rebill_regression ";
    			 databaseSqlQuery="select result, description, test_input_nbr, tin_count, trkngnbr, reason_code, rebill_acct,invoice_nbr_1, invoice_nbr_2, mig, region,  login,   password,  rs_Type, company, worktype, ORIGIN_LOC,DEST_LOC,DIM_VOL,SHIPPER_REF,RECP_ADDRESS,SHIPPER_ADDRESS,ACC_NBR_DEL_STATUS,SVC_BASE, CREDIT_CARD_DTL,PRE_RATE_SCENARIOS,EXP_Pieces,EXP_ACTUAL_Weight,EXP_Adj_Weight,CREDIT_CARD_DTL from rebill_regression ";
    	
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
			    	}
		    	}
		    	else if (customCheckBox.equals("true")){
		    		databaseSqlCount+="where "+customString;
		    		databaseSqlQuery+="where "+customString;
		    	}
		    }
		    	

       	try {
        		
       			int rowCount,colCount;
        		stmt = GTMcon.createStatement();
        		System.out.println(databaseSqlCount);
            	rs = stmt.executeQuery(databaseSqlCount);
            	rs.next();
            	rowCount=rs.getInt("total");
            	stmt.close();
            	rs.close();
            	
            	stmt = GTMcon.createStatement();
        		System.out.println(databaseSqlQuery);
            	rs = stmt.executeQuery(databaseSqlQuery);
            	rsmd = rs.getMetaData();
            	colCount = rsmd.getColumnCount()+1;
            	int rowCountTemp=0;
            	allData = new String[rowCount][colCount+1];
            	 
            	 while(rs.next()) {
            	//	 rowCount++;
            		// rebillDataArray.add(new rebillData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
            		 for (int i=1;i<colCount;i++) {
            			System.out.println(rs.getString(i));
            			if (rs.getString(i)==null) {
            				allData[rowCountTemp][i-1]="";
            			}
            			else {
            			 allData[rowCountTemp][i-1]=rs.getString(i);
            			}
            		 }
            		 rowCountTemp++; 
            	 }
            	 c.setAllDataArray(allData);
        	}
        	catch(Exception e) {
        		System.out.println(e);
        	}
		}
    	catch(Exception ee) {
    		System.out.println(ee);
    	}
		
		
	}
	
}
