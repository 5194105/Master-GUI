package ThreadTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.config;

public class validateClass {
	Boolean result=false,oracleBoolean=false,eraBoolean=false;
	String databaseDisabled,flag;
	config c;
	
	
	public validateClass(config c,String databaseDisabled,String flag) {
		this.c=c;
		this.databaseDisabled=databaseDisabled;
		this.flag=flag;
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



public void writeToDb(String testInputNbr,String tinCount,String trkngnbr,String finalResult,String  finalDesc,String requestID) {
	if (databaseDisabled.equals("true")) {
	Connection con=null;
	try {
		con = c.getGtmRevToolsConnection();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	PreparedStatement stmt = null;
	

	try {
	    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,request_id,?) values (?,?,?,?,?,?)");  
		
		stmt.setString(1,flag); 
		stmt.setString(2,testInputNbr);  
		stmt.setString(3,tinCount);  
		stmt.setString(4,trkngnbr);  
		stmt.setString(5,finalResult);  
		stmt.setString(6,finalDesc);  
		stmt.setString(7,requestID);  
		stmt.setString(8,"Y");  
		stmt.executeUpdate();
	}
	catch(Exception e) {
		System.out.println(e);
	}
	
	
	
	try {
		stmt=con.prepareStatement("update era_results set result=?,description=?,?=? where trkngnbr=?");  
		stmt.setString(1,requestID);  
		stmt.setString(2,finalDesc); 
		stmt.setString(3,flag);
		stmt.setString(4,"Y");
		stmt.setString(5,trkngnbr); 
		stmt.executeUpdate();
		
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

}


