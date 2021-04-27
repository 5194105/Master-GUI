package ThreadConfig;

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
	
	
	
	
/////////////////////PRERATE HOLD
	
	public Boolean validatePrerateHold(String testInputNbr,String tinCount,String trkngnbr,String type) {
		prerateBoolean=false;
		
		if (databaseDisabled.equals("false")) {
			searchEcDB("select * from ec_intl_schema.ec_pre_rate_history where pkg_trkng_nbr ="+trkngnbr+"and EVENT_TYPE_CD_DESC="+type,testInputNbr,tinCount,trkngnbr);
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
			finalDesc="Could Not Find in Oracle DB";
		}
		else{
			String tempString= rs.getString("NOTES");
								
			
			
			
			
				if (flag.equals("era_rebill")) {
				  if (tempString.contains("RDT RB")) {
					  finalResult="pass";
					  finalDesc="completed";
					  oracleBoolean=true;
					  writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
				  		}
					}
				if (flag.equals("era_rerate")) {
				if (tempString.contains("RDT CR")) {
					finalResult="pass";
					finalDesc="completed";
				  }
				  else  if (tempString.contains("RDT DN")) {
					  finalResult="pass";
					  finalDesc="denied";
				  }
				  else {
					  finalResult="na";
					  finalDesc="unable to validate";
					  
				  }
				  writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
				
				}		
								
								
						System.out.println(finalResult);		
						System.out.println(finalDesc);		
								
								
								
								
								
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









public void searchOracleDBRerateTemp(String sqlQuery,String testInputNbr,String tinCount,String trkngnbr) {
	  
   

    	Connection con = null;
    	String[] resultArray = new String[2];
    	
       	con=c.getOracleARL3DbConnection();
       	 	
    	
    	
    	
    	

    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
    		
    		stmt=con.prepareStatement("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER=?");  
			stmt.setString(1,trkngnbr);  
			rs = stmt.executeQuery();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	      
    	   
    		try {
    			rs = stmt.executeQuery();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	       try {
    			if (rs.next()==false){
    			   
    				
    				System.out.println("Is NULL");
    			      resultArray[0]="fail";
    			      resultArray[1]="Not Found In Oracle DB";
    			}
    			   else{
    				   
    				  String tempString= rs.getString("NOTES");
    				  if (tempString.contains("RDT CR")) {
    					  resultArray[0]="pass";
	    			      resultArray[1]="completed";
    				  }
    				  else  if (tempString.contains("RDT DN")) {
    					  resultArray[0]="pass";
	    			      resultArray[1]="denied";
    				  }
    				  else {
    					  resultArray[0]="na";
	    			      resultArray[1]="unable to validate";
    					  
    				  }
    				  writeToDb(testInputNbr,tinCount,trkngnbr, resultArray[0],resultArray[1],null);
    				  
    			   }
    		} catch (SQLException e) {
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
		
		if (flag.equals("era_rerate")) {
			 stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,request_id,era_rerate) values (?,?,?,?,?,?,?)");  
				
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
		if (flag.equals("era_credit")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_credit) values (?,?,?,?,?,?)");  
			
			//stmt.setString(1,flag); 
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			 stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		if (flag.equals("era_debit")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_debit) values (?,?,?,?,?,?)");  
			
			//stmt.setString(1,flag); 
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			 stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		if (flag.equals("era_dispute")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_dispute) values (?,?,?,?,?,?)");  
			
			//stmt.setString(1,flag); 
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			 stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		if (flag.equals("era_resolve_credit")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_resolve) values (?,?,?,?,?,?)");  
			
			//stmt.setString(1,flag); 
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			 stmt.setString(6,"Y");  
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
		
		stmt.setString(3,trkngnbr); 
		stmt.executeUpdate();
		}
		
		if (flag.equals("era_rerate")) {
			stmt=con.prepareStatement("update era_results set result=?,description=?,era_rerate='Y' where trkngnbr=?");  
			stmt.setString(1,finalResult);  
			stmt.setString(2,finalDesc); 
			stmt.setString(3,trkngnbr); 
			stmt.executeUpdate();
		}
		if (flag.equals("era_credit")) {
			stmt=con.prepareStatement("update era_results set result=?,description=?,era_credit='Y',tin_count=? where trkngnbr=?");  
			stmt.setString(1,finalResult);  
			stmt.setString(2,finalDesc); 
			stmt.setString(3,tinCount);
			stmt.setString(4,trkngnbr); 
			stmt.executeUpdate();
		}
		
		if (flag.equals("era_debit")) {
			stmt=con.prepareStatement("update era_results set result=?,description=?,era_debit='Y',tin_count=? where trkngnbr=?");  
			stmt.setString(1,finalResult);  
			stmt.setString(2,finalDesc); 
			stmt.setString(3,tinCount);
			stmt.setString(4,trkngnbr); 
			stmt.executeUpdate();
		}
		if (flag.equals("era_dispute")) {
			stmt=con.prepareStatement("update era_results set result=?,description=?,era_dispute='Y',tin_count=? where trkngnbr=?");  
			stmt.setString(1,finalResult);  
			stmt.setString(2,finalDesc); 
			stmt.setString(3,tinCount);
			stmt.setString(4,trkngnbr); 
			 
			stmt.executeUpdate();
		}
		if (flag.equals("era_resolove_credit")) {
			stmt=con.prepareStatement("update era_results set result=?,description=?,era_resolve='Y',tin_count=? where trkngnbr=?");  
			stmt.setString(1,finalResult);  
			stmt.setString(2,finalDesc); 
			stmt.setString(3,tinCount);
			stmt.setString(4,trkngnbr); 
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
	
System.out.println(trkngnbr +" : "+finalResult+" : "+finalDesc);
if (finalDesc.equals("")) {
	finalDesc="Unknown Error";
}

if (flag.equals("prerate_single")) {
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
}
if (flag.equals("prerate_hold")) {
	try {
	    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,prerate_hold) values (?,?,?,?,?,?)");  
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
}
	
	
if (flag.equals("prerate_single")) {
	try {
		stmt=con.prepareStatement("update era_results set result=?,description=?,tin_Count=?,prerate_single='Y' where trkngnbr=?");  
		stmt.setString(1,finalResult);  
		stmt.setString(2,finalDesc); 
		stmt.setString(3,tinCount); 
		stmt.setString(4,trkngnbr); 
		stmt.executeUpdate();
		
}
catch(Exception e) {
	System.out.println(e);
}
}

if (flag.equals("prerate_hold")) {
	try {
		stmt=con.prepareStatement("update era_results set result=?,description=?,tin_Count=?,prerate_hold='Y' where trkngnbr=?");  
		stmt.setString(1,finalResult);  
		stmt.setString(2,finalDesc); 
		stmt.setString(3,tinCount); 
		stmt.setString(4,trkngnbr); 
		stmt.executeUpdate();
		
}
catch(Exception e) {
	System.out.println(e);
}
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







public Boolean searchOracleDBError(String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr) {
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	Boolean boo=false;;
	try {
		con=c.getOracleARL3DbConnection();
		stmt=con.createStatement();
		rs=stmt.executeQuery("select process_Status_cd from apps.fdx_ar_invoice_interface_all where  length(invoice_sequence_nbr) =16 and invoice_nbr='"+invoiceNbr+"'");
		String finalResult="";
		String finalDesc="";
		if (rs.next()==false){
			
			finalResult="fail";
			finalDesc="Invoice Failed Not In Oracle DB";
			  //oracleBoolean=true;
			writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
			boo=true;
		}
		else{
			String tempString= rs.getString("PROCESS_STATUS_CD");
				  if (!tempString.equals("S")) {
					  finalResult="fail";
					  finalDesc="Invoice Failed in Oracle DB";
					  //oracleBoolean=true;
					  writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					  boo=true;
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
	return boo;
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
				 System.out.println(tempString);
				 							  
					  if (tempString.contains("RDT CR") && denialBoolean==false && flag.equals("era_credit")) {
						finalResult="pass";
						finalDesc="completed";
						oracleBoolean=true;
						writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
						
					}
					else if (tempString.contains("RDT DB") && denialBoolean==false && flag.equals("era_debit")) {
						finalResult="pass";
						finalDesc="completed";
						oracleBoolean=true;
						writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					}
					else if (tempString.contains("RDT CR") && denialBoolean==false && flag.equals("era_resolve")) {
						finalResult="pass";
						finalDesc="completed";
						oracleBoolean=true;
						writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					}
					else if (tempString.contains("RDT D") && denialBoolean==false && flag.equals("era_dispute")) {
						finalResult="pass";
						finalDesc="completed";
						oracleBoolean=true;
						writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					}
					else  if (tempString.contains("RDT DN") && denialBoolean==true) {
						finalResult="pass";
						finalDesc="denied";
						oracleBoolean=true;
						writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					}
					else {
						finalResult="na";
						finalDesc="unable to validate";
						oracleBoolean=false;
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

