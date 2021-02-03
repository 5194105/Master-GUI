package eraRerate;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import configuration.config;
import configuration.excel;
import guis.eraRerateAutomationGui;
import guis.rebillAutomationGui;

public class eraRerateUpload implements Runnable {
       String[][] allData;
       int rowCount;
       int colCount;
       double total=0;
       int counter=1;
       String testInputNbr,tinCount,trkngnbr,requestID;
       config c;
       String[] resultArray = new String[2];
     //  JFrame frame;
       Object o;
       eraRerateAutomationGui erag;
       public eraRerateUpload(config c,Object o) {
              this.c=c;
              this.o=o;
              erag = (eraRerateAutomationGui)o;
              System.out.println("era mass rerate: "+ c.getEraMassRerate());
         //     this.frame=frame;
       //     c.setLevel("3");
       }
       
              
       @Override
       public void run() {
       	// TODO Auto-generated method stub.
    	  // System.out.println("HELLO FROM RUN");
    	   
    
    	   
    	   getData();
    	      for (int i=0;i<rowCount;i++) {
    	    	   iterate(i+1,rowCount);
                  
    	    	   testInputNbr=allData[i][0];
                   tinCount=allData[i][1];
                   trkngnbr=allData[i][2];
                   requestID=allData[i][3];
                   resultArray=validateResults(trkngnbr,requestID);
                   writeToDB(testInputNbr,tinCount,trkngnbr,resultArray);
              }
              
       }
        
       public void iterate(int counterTemp,int rowCountTemp){    
    	 
    	// System.out.println("HELLO FROM ITERATE");
    	// System.out.println("i "+counterTemp);
    	// System.out.println("rowcount "+rowCountTemp);
      	 total=((double)counterTemp/(double)rowCountTemp)*100;
      //	 System.out.println("total" +total);
      	 
      	 //    frame.b.setValue(i);   
      		   
      	erag.getProgressBar().setValue((int)total);
      	erag.getProgressBar().repaint(); 
      	     try{Thread.sleep(150);}
      	     catch(Exception e){}  
      	   
       }

       
       
       public synchronized void writeToDB(String testInputNbr,String tinCount,String trk,String[] resultArray) {
       Connection GTMcon=null;

       
       PreparedStatement stmt = null;
       try {
       
       GTMcon=c.getGtmRevToolsConnection();
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
       if (c.getEraMassRerate().equals("true")) {
       stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,ERA_mass_rerate,request_id) values (?,?,?,?,?,?,?)");  
       }
       if (c.getEraMassRerate().equals("false")) {
    	   stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.era_results (test_input_nbr,tin_count,trkngnbr,result,description,ERA_rerate) values (?,?,?,?,?,?)");  
       }
       stmt.setString(1,testInputNbr);  
              stmt.setString(2,tinCount);  
              stmt.setString(3,trk);  
              stmt.setString(4,resultArray[0]);  
              stmt.setString(5,resultArray[1]);  
              stmt.setString(6,"Y");  
              if (c.getEraMassRerate().equals("true")) {
            	  stmt.setString(7,resultArray[2]);  
              }
              
       
              stmt.executeUpdate();
       }
       catch(Exception e) {
            //  System.out.println(e);
       }
              
       
       
       try {
              //     update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
             
    	   if (c.getEraMassRerate().equals("true")) {
    		   stmt=GTMcon.prepareStatement("update era_results set result=?,description=?,ERA_mass_rerate='Y',request_id=? where trkngnbr=?");    
    	       }
    	       if (c.getEraMassRerate().equals("false")) {
    	    	   stmt=GTMcon.prepareStatement("update era_results set result=?,description=?,ERA_rerate='Y' where trkngnbr=?");   
    	       }
    	   
    	   
              
              stmt.setString(1,resultArray[0]);  
              stmt.setString(2,resultArray[1]); 
              stmt.setString(3,trk); 
              if (c.getEraMassRerate().equals("true")) {
            	  stmt.setString(4,resultArray[2]);  
              }
              stmt.executeUpdate();
              
       }
       catch(Exception e) {
              System.out.println(e);
       }
       try {
                     stmt.close();
              } catch (SQLException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              }
       }
       
       
    public  String[] validateResults(String trk,String requestID) {
             counter++;
             Boolean result=null;
             Connection con = null;
         	String[] resultArray = new String[3];
         	
         	try {
         	
         		if (c.getLevel().equals("2") && c.getEraMassRerate().equals("false")){
         			 
            		   //  con=c.getOracleARL2DbConnection();
            	 }
            	 else if (c.getLevel().equals("3") && c.getEraMassRerate().equals("false")){
            		 	
            		 	con=c.getOracleARL3DbConnection();
            	 	}
         		

         		if (c.getLevel().equals("2") && c.getEraMassRerate().equals("true")){
         			 
            		   //  con=c.getOracleARL2DbConnection();
            	 }
            	 else if (c.getLevel().equals("3") && c.getEraMassRerate().equals("true")){
            		 	
            		 	con=c.getEraL3Con();
            	 	}
         	
         	}
         	catch(Exception e) {
         		
         		System.out.println("Could Not Get ERA DB Connections");
         	}
         	

         	PreparedStatement stmt = null;
         	ResultSet rs = null;
         	  if (c.getEraMassRerate().equals("false")) {
         	
         	try {
         		System.out.println("Trk: "+trk);
         		stmt=con.prepareStatement("select * from apps.xxfdx_eabr_airbill_notes_v where AIRBILL_NUMBER=?");  
     			stmt.setString(1,trk);  
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
         			      resultArray[1]="";
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
         	             
         				  
         			   }
         		} catch (SQLException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
         		
         		
    }
         	  if (c.getEraMassRerate().equals("true")) {
         		  try {
         			  if(trk.equals("794994229050") || trk.equals("794993961972")) {
         				  System.out.println("HOLD");
         			  }
         			  System.out.println(trk);
         			 stmt=con.prepareStatement("select FAILED_REQ_QTY, SUCCESS_REQ_QTY ,TOTAL_REQUEST_QTY,RERATE_STATUS_CD, RERATE_OUTBOUND_TMSTP,batch_summary_nbr from invadj_schema.mass_adj_summary a join (select max(batch_summary_nbr) as bsr from invadj_schema.mass_rerate_detail where airbill_nbr =?) b on b.bsr=a.batch_summary_nbr");  
          			stmt.setString(1,trk);  
          			rs = stmt.executeQuery();
         	
         	  }
         		  catch (Exception e) {
         			  }
         		 try {
          			if (rs.next()==false){
          			   
          				
          				System.out.println("Is NULL");
          			      resultArray[0]="fail";
          			      resultArray[1]="Not Found in ERA DB";
          			}
          			   else{
          				   
          				  String SUCCESS_REQ_QTY = rs.getString("SUCCESS_REQ_QTY");
          				 String TOTAL_REQUEST_QTY = rs.getString("TOTAL_REQUEST_QTY");
          				 String batch_summary_nbr = rs.getString("batch_summary_nbr");
          				 
          				 
          				 
          				 
          				 if(SUCCESS_REQ_QTY==null || SUCCESS_REQ_QTY.equals("")) {
          					SUCCESS_REQ_QTY="";
          				 }
          				
          				 if (SUCCESS_REQ_QTY.equals(TOTAL_REQUEST_QTY)) {
          					  resultArray[0]="pass";
    	    			      resultArray[1]="completed";
          				 }
          				 else if (SUCCESS_REQ_QTY.equals("")) {
          					  resultArray[0]="pending";
      	    			      resultArray[1]="Still Processing";
          				 }
          				
          				  else {
          					  resultArray[0]="na";
      	    			      resultArray[1]="Check Manually";
          					  
          				  }
          				 resultArray[2]=batch_summary_nbr;
          				  
          			   }
          		} catch (SQLException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          		}
         		  }
         	 return resultArray;      
     
   }    
       
       
       
       
public  void getData() {
       Connection GTMcon=null;
       Statement stmt = null;
       ResultSet rs = null;
       ResultSetMetaData rsmd=null;

       //Change to L3
       try {
              Class.forName("oracle.jdbc.driver.OracleDriver");
       GTMcon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
              
       } catch (ClassNotFoundException e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
       } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
       }
       String databaseSqlCount="";
       String databaseSqlQuery="";
       
       if (c.getEraMassRerate().equals("true")) {
    	    databaseSqlCount="select count(*) as total from era_rerate_mass where trkngnbr is not null ";
            databaseSqlQuery="select  test_input_nbr, tin_count, trkngnbr,request_ID from era_rerate_mass where trkngnbr is not null "; 
           }
           if (c.getEraMassRerate().equals("false")) {
        	    databaseSqlCount="select count(*) as total from era_rerate_view where trkngnbr is not null ";
                databaseSqlQuery="select  test_input_nbr, tin_count, trkngnbr from era_rerate_view where trkngnbr is not null ";
           }
      
       
       
       
       
       
       
       

       try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
              
              stmt = GTMcon.createStatement();
              System.out.println(databaseSqlCount);
             rs = stmt.executeQuery(databaseSqlCount);
             rs.next();
             rowCount=rs.getInt("total");
             stmt = GTMcon.createStatement();
            System.out.println(databaseSqlQuery);
             rs = stmt.executeQuery(databaseSqlQuery);
             rsmd = rs.getMetaData();
             colCount = rsmd.getColumnCount()+1;
             int rowCountTemp=0;
             allData = new String[rowCount][colCount+1];
              
              while(rs.next()) {
             //     rowCount++;
                    // rebillDataArray.add(new rebillData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
                     for (int i=1;i<colCount;i++) {
                        //   System.out.println(rs.getString(i));
                           if (rs.getString(i)==null) {
                                  allData[rowCountTemp][i-1]="";
                           }
                           else {
                            allData[rowCountTemp][i-1]=rs.getString(i);
                           }
                     }
                     rowCountTemp++; 
              }
              //colCount=17;
       }
       catch(Exception e) {
              System.out.println(e);
       }

    try {
                           rs.close();
                           stmt.close();
                     } catch (SQLException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                     }
       
}
public void closeConnections(Connection con,ResultSet rs,Statement s, PreparedStatement ps) {
       try {
              con.close();
              rs.close();
              s.close();
              ps.close();
       }
       catch(Exception e) {
          //    System.out.println(e);
       }
}




       
}
