package ThreadConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import configuration.config;

public class validateClass {
	Boolean result=false,oracleBoolean=false,eraBoolean=false,prerateBoolean=false,denialBoolean=false,instantInvoiceBoolean=false,sepBoolean=false,sepBooleanPup=true,sepBooleanStat65=true,sepBooleanPod=true;
	String databaseDisabled,flag,disputeNumber,sepResults;
	config c;
	
	//Constructor
	public validateClass(config c,String databaseDisabled,String flag) {
		this.c=c;
		this.databaseDisabled=databaseDisabled;
		this.flag=flag;
	}
	
	//This will let the program know what function this is.. wouldnt it be better to just pass the func number -- will check this later
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	//This is for SEP eMass.. not yet working
	public Boolean validateSep(String testInputNbr,String trkngnbr) {
		sepBoolean=false;
		Connection con1=null, con2=null, con3=null;
		Statement stmt1=null,stmt2=null,stmt3=null;
		ResultSet rs1=null,rs2=null,rs3=null;
		String sqlQuery1=null,sqlQuery2=null,sqlQuery3=null;
		
			sqlQuery1="select * from sep_l3_schema.package where PACKAGE_SCAN_DESC like '%08000%' and TRACKING_ITEM_NBR in('"+trkngnbr+"')";
			//sqlQuery1="select * from sep_l3_schema.package";
			System.out.println(sqlQuery1);
		
	
			sqlQuery2="select * from sep_l3_schema.package where PACKAGE_SCAN_DESC like '%07000%' and TRACKING_ITEM_NBR in('"+trkngnbr+"')";
			System.out.println(sqlQuery2);
			
		
			sqlQuery3="select * from sep_l3_schema.package where PACKAGE_SCAN_DESC like '%20000%' and TRACKING_ITEM_NBR in('"+trkngnbr+"')";
			System.out.println(sqlQuery3);
			
		try {
			c.getSepL3DbConnection();
		//	c.setSepL3DbConnection("SEP_L3_DATA_APP", "DAPP_Test_Env_Rocks_987..WordD");
			con1=c.getSepL3DbConnection();
			stmt1=con1.createStatement();
			con2=c.getSepL3DbConnection();
			stmt2=con2.createStatement();
			con3=c.getSepL3DbConnection();
			stmt3=con3.createStatement();
		//	rs=stmt.executeQuery(sqlQuery);
			rs1=stmt1.executeQuery(sqlQuery1);
			rs2=stmt2.executeQuery(sqlQuery2);
			rs3=stmt3.executeQuery(sqlQuery3);
			String finalResult="";
			String finalDesc="";
			
			if (rs1.next()==false){
				sepBooleanPup=false;   
			
			}
			   else{
			      System.out.println("IS NOT NULL");
			      sepBooleanPup=true;
			    //  finalResult="pass";
            	//  finalDesc="completed";
            	//  writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null); 
			   }
			
			if (rs2.next()==false){
				sepBooleanStat65=false;   
			
			}
			   else{
			      System.out.println("IS NOT NULL");
			      sepBooleanStat65=true;
			    //  finalResult="pass";
            	 // finalDesc="completed";
            	 // writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null); 
			   }
			
			if (rs3.next()==false){
				sepBooleanPod=false;   
			
			}
			   else{
			      System.out.println("IS NOT NULL");
			      sepBooleanPod=true;
			     // finalResult="pass";
            	 // finalDesc="completed";
            	  //writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null); 
			   }
			
			
			
			
			if (flag.equals("emass_pup")) {
				if (sepBooleanPup==true) {
					 sepBoolean=true;
					 finalResult="in progress";
	            	 finalDesc="pup completed";
	            	 writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null); 
					
				}
			}
			if (flag.equals("emass_stat65")) {
				if (sepBooleanPup==true) {
					if (sepBooleanStat65==true) {
						 sepBoolean=true;
						 finalResult="in progress";
		            	 finalDesc="pup and stat completed";
		            	 writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null);
					}
					else {
						System.out.println("Pup applied, but stat has not... continuing with stat");
					}
				}
				else {
					 sepBoolean=true;
					 finalResult="na";
	            	 finalDesc="pup not yet completed";
	            	 writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null);
				}
				
				
			}
			
			if (flag.equals("emass_pod")) {
				if (sepBooleanPup==true) {
					if (sepBooleanStat65==true) {
						if (sepBooleanPod==true) {
							 sepBoolean=true;
							 finalResult="completed";
			            	 finalDesc="pup and stat and pod completed";
			            	 writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null);
					}
			}
					else {
						 sepBoolean=true;
						 finalResult="na";
		            	 finalDesc="stat not yet completed";
		            	 writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null);
					}
				}
				else {
					 sepBoolean=true;
					 finalResult="na";
	            	 finalDesc="pup and stat not yet completed";
	            	 writeToDb(testInputNbr, "1", trkngnbr, finalResult, finalDesc, null);
				}
			
		}
		}
			catch(Exception e) {
				System.out.println(e);
			}
		return sepBoolean;
	}
	

	
	
	
	
	//Will validate the rebill. Will check if it is in Oracle and/or era DB
	public Boolean validateRebill(String testInputNbr,String tinCount,String trkngnbr) {
		//If in oracle or era DB, will mark as true.
		oracleBoolean=false;
		eraBoolean=false;
		
		
		if (databaseDisabled.equals("false")) {
			//Searching in Oracle DB
			searchOracleDB("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER="+trkngnbr,testInputNbr,tinCount,trkngnbr);
			 		
			 if (oracleBoolean==false) {
				 //Searching ERA DB if not found in Oracle
				 searchEraDB("select * from invadj_schema.rdt_rebill_store where rb_trkng_nbr="+trkngnbr,testInputNbr,tinCount,trkngnbr,1);
				 
				 if (eraBoolean==false) {
					//Searching ERA DB if not found in first ERA DB
					 searchEraDB("select * from invadj_schema.rdt_rebill_request where airbill_nbr="+trkngnbr+" order by LAST_UPDT_TMSTP desc",testInputNbr,tinCount,trkngnbr,2);
		         }
			 }
		//If Found then marking result as true
		if (oracleBoolean ==true|| eraBoolean==true) {
			result=true;
			}
			else {
				 result=false;
			}
		}
		return result;
	}
	
	
	
	//Will validate Instant Invoice
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
	
	
	//Checks through IORE for Instant Invoice
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
	
	
	
	
	//Validates for ERA Rerate
	public Boolean validateRerate(String testInputNbr,String tinCount,String trkngnbr) {
		oracleBoolean=false;
		if (databaseDisabled.equals("false")) {
			searchOracleDB("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER="+trkngnbr,testInputNbr,tinCount,trkngnbr);
			
		}
		return oracleBoolean;
		
	}
	
	
	//This doesnt actually work.. i need to check on this
	public Boolean validateMassRerate(String testInputNbr,String tinCount,String trkngnbr) {
		oracleBoolean=false;
		if (databaseDisabled.equals("false")) {
			searchEraRerateMass("select FAILED_REQ_QTY, SUCCESS_REQ_QTY ,TOTAL_REQUEST_QTY,RERATE_STATUS_CD, RERATE_OUTBOUND_TMSTP,batch_summary_nbr from invadj_schema.mass_adj_summary a join (select max(batch_summary_nbr) as bsr from invadj_schema.mass_rerate_detail where airbill_nbr ="+trkngnbr+") b on b.bsr=a.batch_summary_nbr",testInputNbr,tinCount,trkngnbr);
			
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
			searchEcDB("select * from ec_intl_schema.ec_pre_rate_history where pkg_trkng_nbr ="+trkngnbr+"and EVENT_TYPE_CD_DESC='"+type+"'",testInputNbr,tinCount,trkngnbr);
			}
		return prerateBoolean;
	}
	
	
	
	
	
	
	
	//This will check EC DB for Prerate.
	public void searchEcDB(String sqlQuery,String testInputNbr,String tinCount,String trkngnbr) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		System.out.println(sqlQuery);
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
				checkCursorError(e);
			}
	}
	
	
	//If we manage to get a cursor error then just close the program because it might overwrite valid results.
	public void checkCursorError(Exception  e) {
		if (e.getMessage().contains("maximum open cursors exceeded")) {
			System.out.println("Ending Program Due to Max Open Cursors");
			System.exit(0);
		}
		
	}

	//Search ERA DB.
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
	            	  writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
	              }
			break;
			     }
			}
		}
		catch(Exception e) {
			System.out.println(e);
			checkCursorError(e);
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


//Does not work currently
	public void searchEraRerateMass(String sqlQuery,String testInputNbr,String tinCount,String trkngnbr) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String finalResult="";
		String finalDesc="";
		String requestID="";
		if (trkngnbr.equals("119976543235")) {
			System.out.println("Debug");
		}
		try {
			  System.out.println(trkngnbr);
			  con=c.getEraL3DbConnection();
				stmt=con.createStatement();
				rs=stmt.executeQuery(sqlQuery);
				
		
		  }
			  catch (Exception e) {
				  }
			 try {
				if (rs.next()==false){
				   
					
					System.out.println("Is NULL");
					finalResult="fail";
					finalDesc="Not Found in ERA DB";
				}
				   else{
					   
					  String SUCCESS_REQ_QTY = rs.getString("SUCCESS_REQ_QTY");
					 String TOTAL_REQUEST_QTY = rs.getString("TOTAL_REQUEST_QTY");
					 String batch_summary_nbr = rs.getString("batch_summary_nbr");
					 
					 
					 
					 
					 if(SUCCESS_REQ_QTY==null || SUCCESS_REQ_QTY.equals("")) {
						SUCCESS_REQ_QTY="";
					 }
					
					 if (SUCCESS_REQ_QTY.equals(TOTAL_REQUEST_QTY)) {
						 finalResult="pass";
						 finalDesc="completed";
					 }
					 else if (SUCCESS_REQ_QTY.equals("")) {
						 finalResult="pending";
						 finalDesc="Still Processing";
					 }
					
					  else {
						  finalResult="na";
						  finalDesc="Check Manually";
						  
					  }
					 requestID=batch_summary_nbr;
					  
				   }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeToDb( testInputNbr, tinCount, trkngnbr, finalResult, finalDesc, requestID);
			  }
		      


	//Searching oracle DB for rerate,rebill,credit,debit,dispute,etc
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
							oracleBoolean=true;
						}
						else if (tempString.contains("RDT DN")) {
						  	finalResult="pass";
						  	finalDesc="denied";
						  	oracleBoolean=true;
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
			checkCursorError(e);
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

//WIP -- Not in use.
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



	public void setDisputeCase(String disputeNumber) {
		this.disputeNumber=disputeNumber;
	}
	public String getDisputeCase() {
		return disputeNumber;
	}
	
	//Write to our GTM DB for GFBO
	public void writeToDb(String gfboUsername,String gfboPaymentLevel,String gfboPaymentType,String gfboResult,String gfboDescription,String level,String cycle) {
		try {
			if (databaseDisabled.equals("false")) {
				Connection con=null;
				try {
					con = c.getGtmRevToolsConnection();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		}
	
		PreparedStatement stmt = null;
		Random r = new Random();
		int low=1;
		int high=2147483645;
		int randomNumber=r.nextInt(high-low)+low; 
		String randomTrk = String.valueOf(randomNumber);
		
		 stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,GFBO_USERNAME,GFBO_PAYMENT_LEVEL,GFBO_PAYMENT_TYPE,result,description,gfbo_levels,gfbo_cycle) values ('11111','1','"+randomTrk+"',?,?,?,?,?,?,?)");  
		
		 //stmt.setString(1,flag); 
		 stmt.setString(1,gfboUsername);  
		 stmt.setString(2,gfboPaymentLevel);  
		 stmt.setString(3,gfboPaymentType);  
		 stmt.setString(4,gfboResult);  
		 stmt.setString(5,gfboDescription);  
		 stmt.setString(6,level);  
		 stmt.setString(7,cycle);  
		 stmt.executeUpdate();
			
		 stmt=con.prepareStatement("update era_results set result=?,description=? where  GFBO_USERNAME=? and GFBO_PAYMENT_LEVEL=? and GFBO_PAYMENT_TYPE=? and gfbo_levels=? and gfbo_cycle=?");  
		 stmt.setString(1,gfboResult);  
		 stmt.setString(2,gfboDescription); 
		 stmt.setString(3,gfboUsername);  
		 stmt.setString(4,gfboPaymentLevel); 
		 stmt.setString(5,gfboPaymentType); 
		 stmt.setString(6,level); 
		 stmt.setString(7,cycle); 
		 stmt.executeUpdate();
			
			}
		}
		catch(Exception e) {
			System.out.println(e);
			checkCursorError(e);
		}
	}



//Write to GTM DB For all but GFBO
	public void writeToDb(String testInputNbr,String tinCount,String trkngnbr,String finalResult,String finalDesc,String requestID) {
		if (databaseDisabled.equals("false")) {
		Connection con=null;
		try {
			con = c.getGtmRevToolsConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PreparedStatement stmt = null;
		
		try {
			if (flag.equals("era_rebill")) {
				stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,request_id,era_rebill) values (?,?,?,?,?,?,?)");  
				stmt.setString(1,testInputNbr);  
				stmt.setString(2,tinCount);  
				stmt.setString(3,trkngnbr);  
				stmt.setString(4,finalResult);  
				stmt.setString(5,finalDesc);  
				stmt.setString(6,requestID);  
				stmt.setString(7,"Y");  
				stmt.executeUpdate();
				}
			
			if (flag.equals("emass_pup")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,emass_pup) values (?,?,?,?,?,?)");  
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			stmt.setString(6,"Y");  
			stmt.executeUpdate();
			}
		
			if (flag.equals("emass_stat65")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,emass_stat) values (?,?,?,?,?,?)");  
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		if (flag.equals("emass_pod")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,emass_pod) values (?,?,?,?,?,?)");  
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		
		if (flag.equals("era_rerate_mass")) {
			stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,request_id,era_mass_rerate) values (?,?,?,?,?,?,?)");  
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
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		
		if (flag.equals("era_resolve")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_resolve) values (?,?,?,?,?,?)");  
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		
		if (flag.equals("era_rebill_resolve")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,era_rebill_resolve) values (?,?,?,?,?,?)");  
			stmt.setString(1,testInputNbr);  
			stmt.setString(2,tinCount);  
			stmt.setString(3,trkngnbr);  
			stmt.setString(4,finalResult);  
			stmt.setString(5,finalDesc);  
			stmt.setString(6,"Y");  
			stmt.executeUpdate();
		}
		
		if (flag.equals("prs_rerate")) {
		    stmt=con.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,request_id,prs_rerate) values (?,?,?,?,?,?,?)");  
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
			checkCursorError(e);
		}
		
		
		
		try {
			if (flag.equals("era_rebill")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,era_rebill='Y' where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,trkngnbr); 
				stmt.executeUpdate();
			}
		
			if (flag.equals("emass_pup")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,emass_pup='Y' where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,trkngnbr); 
				stmt.executeUpdate();
			}
		
			if (flag.equals("emass_stat65")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,emass_stat='Y' where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,trkngnbr); 
				stmt.executeUpdate();
			}
		
			if (flag.equals("emass_pod")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,emass_pod='Y' where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,trkngnbr); 
				stmt.executeUpdate();
			}
			
			if (flag.equals("prs_rerate")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,prs_rerate='Y',request_id=? where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,requestID); 
				stmt.setString(4,trkngnbr); 
				stmt.executeUpdate();
			}
			
			
			if (flag.equals("era_rerate_mass")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,era_mass_rerate='Y',tin_count=?,request_id=? where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,tinCount);
				stmt.setString(4,requestID);
				stmt.setString(5,trkngnbr); 
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
				stmt=con.prepareStatement("update era_results set result2=?,description2=?,era_debit='Y',tin_count=? where trkngnbr=?");  
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
			
			if (flag.equals("era_resolve")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,era_resolve='Y',tin_count=? where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,tinCount);
				stmt.setString(4,trkngnbr); 
				stmt.executeUpdate();
			}
			
			if (flag.equals("era_rebill_resolve")) {
				stmt=con.prepareStatement("update era_results set result=?,description=?,era_rebill_resolve='Y',tin_count=? where trkngnbr=?");  
				stmt.setString(1,finalResult);  
				stmt.setString(2,finalDesc); 
				stmt.setString(3,tinCount);
				stmt.setString(4,trkngnbr); 
				stmt.executeUpdate();
			}
				
		}
		catch(Exception e) {
			System.out.println(e);
			checkCursorError(e);
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
				checkCursorError(e);
			}
	}
	
	try {
			con.close();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	




	//Will check if invoice is in error
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
				writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
				boo=true;
			}
			else{
				String tempString= rs.getString("PROCESS_STATUS_CD");
				if (!tempString.equals("S")) {
					finalResult="fail";
					finalDesc="Invoice Failed in Oracle DB";
					writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					boo=true;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
			checkCursorError(e);
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
			setDisputeCase("");
			Boolean disputeBoolean=false;
				while (rs.next()) {
					String tempString= rs.getString("NOTES");
					System.out.println(tempString);
					if (tempString.contains("RDT D ")){
						disputeBoolean=true;
						String temp = tempString;
						temp = temp.substring(temp.indexOf("[")+1);
						temp = temp.substring(0,temp.indexOf("]"));
						System.out.println(temp);
						setDisputeCase(temp);
					 }
					 
					if (tempString.contains("RDT CR") && denialBoolean==false && flag.equals("era_credit")) {
						finalResult="pass";
						finalDesc="completed";
						oracleBoolean=true;
						writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
						break;
					}
						  
					else if (tempString.contains("RDT DB") && denialBoolean==false && flag.equals("era_debit")) {
							finalResult="pass";
							finalDesc="completed";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
					}
					else if (tempString.contains("RDT RC") && denialBoolean==false && flag.equals("era_resolve")) {
							finalResult="pass";
							finalDesc="completed";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						  
						else if (tempString.contains("RDT RR") && denialBoolean==false && flag.equals("era_rebill_resolve")) {
							finalResult="pass";
							finalDesc="completed";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						  
						else if (tempString.contains("RDT D") && denialBoolean==false && flag.equals("era_dispute")) {
							finalResult="pass";
							finalDesc="completed";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						else if (tempString.contains("RDT DN") && denialBoolean==true) {
							finalResult="pass";
							finalDesc="denied";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						
						else if (tempString.contains("RDT CR") && denialBoolean==true) {
							finalResult="fail";
							finalDesc="got CR but should be denied";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						else if (tempString.contains("RDT DB") && denialBoolean==true) {
							finalResult="fail";
							finalDesc="got DB but should be denied";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						else if (tempString.contains("RDT D") && denialBoolean==true) {
							finalResult="fail";
							finalDesc="got D but should be denied";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						else if (tempString.contains("RDT RC") && denialBoolean==true) {
							finalResult="fail";
							finalDesc="got RC but should be denied";
							oracleBoolean=true;
							writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
							break;
						}
						else {
							finalResult="na";
							finalDesc="unable to validate";
							//oracleBoolean=false;
							//writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					 }
				}	  
				if (finalResult.equals("na")) {
					writeToDb(testInputNbr,tinCount,trkngnbr,finalResult,finalDesc,null);
					}  
			}
		catch(Exception e) {
			System.out.println(e);
			checkCursorError(e);
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


