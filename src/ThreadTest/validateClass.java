package ThreadTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.config;

public class validateClass {
	Boolean result=false,oracleBoolean=false,eraBoolean=false,prerateBoolean=false,denialBoolean=false,instantInvoiceBoolean=false;
	String databaseDisabled,flag;
	config c;
	
	
	public validateClass(config c,String databaseDisabled,String flag) {
		this.c=c;
		this.databaseDisabled=databaseDisabled;
		this.flag=flag;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	
	public Boolean validateRebill(String testInputNbr,String tinCount,String trkngnbr) {
		oracleBoolean=false;
		eraBoolean=false;
		if (databaseDisabled.equals("false")) {
			searchOracleDB("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER="+trkngnbr,testInputNbr,tinCount,trkngnbr);
			 		
			 if (oracleBoolean==false) {
				 searchEraDB("select * from invadj_schema.rdt_rebill_store where rb_trkng_nbr="+trkngnbr,testInputNbr,tinCount,trkngnbr,1);
				 if (eraBoolean==false) {
					 searchEraDB("select * from invadj_schema.rdt_rebill_request where airbill_nbr="+trkngnbr+" order by LAST_UPDT_TMSTP desc",testInputNbr,tinCount,trkngnbr,2);
		         }
			 }
				 if (oracleBoolean ==true|| eraBoolean==true) {
					 result=true;
					 }
				 
			 else {
				 result=false;
				 }
			 }
		return result;
	}
	
	
	
	
	public Boolean validateInstantInvoice(String trkngnbr) {
		instantInvoiceBoolean=false;
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (databaseDisabled.equals("false")) {
			searchIoreDB("select * from INTL_EXPRS_ONLN_SCHEMA.intl_online_revenue_item a join INTL_EXPRS_ONLN_SCHEMA.intl_package b on a.ONLN_REV_ITEM_ID =b.ONLN_REV_ITEM_ID  where INSTNT_INV_FLG ='Y' and  pkg_trkng_nbr='"+trkngnbr+"'");
			}
		return instantInvoiceBoolean;
	}
	
	
	public void searchIoreDB(String sqlQuery) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		System.out.println(sqlQuery);
		try {
			con=c.getIoreL3DbConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sqlQuery);
			String finalResult="";
			String finalDesc="";
			
			if (rs.next()==false){
			      System.out.println("Is NULL");
			      instantInvoiceBoolean=false;   
			}
			   else{
			      System.out.println("IS NOT NULL");
			      instantInvoiceBoolean=true;
			      finalResult="pass";
            	  finalDesc="completed";
			     
			   }
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	
	
	
	
	
	public Boolean validateRerate(String testInputNbr,String tinCount,String trkngnbr) {
		oracleBoolean=false;
		if (databaseDisabled.equals("false")) {
			searchOracleDB("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER="+trkngnbr,testInputNbr,tinCount,trkngnbr);
			
		}
		return oracleBoolean;
		
	}
	
	
	
	
	
	/////////////////////PRERATE
	
	public Boolean validatePrerate(String testInputNbr,String tinCount,String trkngnbr) {
		prerateBoolean=false;
		
		if (databaseDisabled.equals("false")) {
			searchEcDB("select * from ec_intl_schema.ec_pre_rate_history where pkg_trkng_nbr ="+trkngnbr,testInputNbr,tinCount,trkngnbr);
			}
		return prerateBoolean;
	}
	
	
	
	
	
	public void searchEcDB(String sqlQuery,String testInputNbr,String tinCount,String trkngnbr) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=c.getEcL3DbConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sqlQuery);
			String finalResult="";
			String finalDesc="";
			
			if (rs.next()==false){
			      System.out.println("Is NULL");
			      prerateBoolean=false;   
			}
			   else{
			      System.out.println("IS NOT NULL");
			      prerateBoolean=true;
			      finalResult="pass";
            	  finalDesc="completed";
			      writeToDbPrerate(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc);
			   }
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	
	
	
	

public void searchEraDB(String sqlQuery,String testInputNbr,String tinCount,String trkngnbr,int methodNumber) {
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	try {
		con=c.getEraL3DbConnection();
		stmt=con.createStatement();
		rs=stmt.executeQuery(sqlQuery);
		String finalResult="";
		String finalDesc="";
		
		switch(methodNumber) {
		case 1:
		if (rs.next()==false){
			finalResult="fail";
			finalDesc="";
		}
		else{
			finalResult="pass";
			finalDesc="completed";
			eraBoolean=true;
			writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
				 }
		break;
		case 2:
			if (rs.next()==false){
				finalResult="fail";
				finalDesc="Not In ERA Database";
                System.out.println("Not In ERA Database");
      	  }
      	  else {
      		  String statusDesc = rs.getString("STATUS_DESC");
                String errorDesc = rs.getString("ERROR_DESC");   
                
                if (statusDesc==null){
               	 
               	 statusDesc="fail";
               	 errorDesc="In ERA DB But Error Not Set";
                }
                
              if (statusDesc.equals("SUCCESS")) {
            	 eraBoolean=true;
            	  finalResult="pass";
            	  finalDesc="completed";
              }
              else {
            	  finalResult="fail";
            	  finalDesc=errorDesc;
              }
		break;
		     }
		}
	}
	catch(Exception e) {
		System.out.println(e);
	}
	try {
	//	con.close();
	//	stmt.close();
	//	rs.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}






public void searchOracleDB(String sqlQuery,String testInputNbr,String tinCount,String trkngnbr) {
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	try {
		con=c.getOracleARL3DbConnection();
		stmt=con.createStatement();
		rs=stmt.executeQuery(sqlQuery);
		String finalResult="";
		String finalDesc="";
		if (rs.next()==false){
			finalResult="fail";
			finalDesc="";
		}
		else{
			String tempString= rs.getString("NOTES");
				  if (tempString.contains("RDT RB")) {
					  finalResult="pass";
					  finalDesc="completed";
					  oracleBoolean=true;
					  writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
				 }
		}
	}
	catch(Exception e) {
		System.out.println(e);
		}
	try {
	//	con.close();
	//	stmt.close();
	//	rs.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public void writeToDb(String testInputNbr,String tinCount,String trkngnbr,String finalResult,String finalDesc,String requestID) {
	if (databaseDisabled.equals("false")) {
	Connection con=null;
	try {
		c.setGtmRevToolsConnection();
		con = c.getGtmRevToolsConnection();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	PreparedStatement stmt = null;
	

	try {
		if (flag.equals("era_rebill")) {
	    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,request_id,era_rebill) values (?,?,?,?,?,?,?)");  
		
		//stmt.setString(1,flag); 
		stmt.setString(1,testInputNbr);  
		stmt.setString(2,tinCount);  
		stmt.setString(3,trkngnbr);  
		stmt.setString(4,finalResult);  
		stmt.setString(5,finalDesc);  
		stmt.setString(6,requestID);  
		stmt.setString(7,"Y");  
		stmt.executeUpdate();
	}
		}
	catch(Exception e) {
		System.out.println(e);
	}
	
	
	
	try {
		if (flag.equals("era_rebill")) {
		stmt=con.prepareStatement("update era_results set result=?,description=?,era_rebill='Y' where trkngnbr=?");  
		stmt.setString(1,finalResult);  
		stmt.setString(2,finalDesc); 
		////stmt.setString(3,flag);
		//stmt.setString(4,"Y");
		stmt.setString(3,trkngnbr); 
		stmt.executeUpdate();
		}
}
catch(Exception e) {
	System.out.println(e);
}
	
	try {
	//	con.close();
	//	stmt.close();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}




public void writeToDbPrerate(String testInputNbr,String tinCount,String trkngnbr,String finalResult,String finalDesc) {
	if (databaseDisabled.equals("false")) {
	Connection con=null;
	try {
		c.setGtmRevToolsConnection();
		con = c.getGtmRevToolsConnection();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	PreparedStatement stmt = null;
	

	try {
	    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,prerate_single) values (?,?,?,?,?,?)");  
		stmt.setString(1,testInputNbr);  
		stmt.setString(2,tinCount);  
		stmt.setString(3,trkngnbr);  
		stmt.setString(4,finalResult);  
		stmt.setString(5,finalDesc);  
		stmt.setString(6,"Y");  
		  
		stmt.executeUpdate();
	}
	catch(Exception e) {
		System.out.println(e);
	}
	
	
	
	try {
		stmt=con.prepareStatement("update era_results set result=?,description=? where trkngnbr=?");  
		stmt.setString(1,finalResult);  
		stmt.setString(2,finalDesc); 
		stmt.setString(3,trkngnbr); 
		stmt.executeUpdate();
		
}
catch(Exception e) {
	System.out.println(e);
}
	
	try {
		con.close();
	//	stmt.close();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}


















public Boolean validateCreditDebit(String testInputNbr,String tinCount,String trkngnbr,String valDesc) {
	oracleBoolean=false;
	denialBoolean=false;
	if (databaseDisabled.equals("false")) {
	
		if(valDesc.equals("Denial Expected")) {
			denialBoolean=true;
		}
		
		searchOracleDBDebitCredit("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER="+trkngnbr,testInputNbr,tinCount,trkngnbr,denialBoolean);
		}
	return oracleBoolean;
	
}








public void searchOracleDBDebitCredit(String sqlQuery,String testInputNbr,String tinCount,String trkngnbr,Boolean denialBoolean) {
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	try {
		con=c.getOracleARL3DbConnection();
		stmt=con.createStatement();
		rs=stmt.executeQuery(sqlQuery);
		String finalResult="";
		String finalDesc="";
		if (rs.next()==false){
			finalResult="fail";
			finalDesc="";
		}
		else{
			String tempString= rs.getString("NOTES");
				  if (tempString.contains("RDT RB")) {
					  if (tempString.contains("RDT CR") && denialBoolean==false && flag.equals("ERA_CREDIT")) {
						finalResult="pass";
						finalDesc="completed";
					}
					else if (tempString.contains("RDT DB") && denialBoolean==false && flag.equals("ERA_DEBIT")) {
						finalResult="pass";
						finalDesc="completed";
					}
					else if (tempString.contains("RDT CR") && denialBoolean==false && flag.equals("ERA_RESOLVE")) {
						finalResult="pass";
						finalDesc="completed";
					}
					else if (tempString.contains("RDT D") && denialBoolean==false && flag.equals("ERA_DISPUTE")) {
						finalResult="pass";
						finalDesc="completed";
					}
					else  if (tempString.contains("RDT DN") && denialBoolean==true) {
						finalResult="pass";
						finalDesc="denied";
					}
					else {
						finalResult="na";
						finalDesc="unable to validate";
						  
					}
					  writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
				 }
		}
	}
	catch(Exception e) {
		System.out.println(e);
		}
	try {
	//	con.close();
	//	stmt.close();
	//	rs.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}











}


