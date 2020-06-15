package rebill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import configuration.config;
import configuration.excel;

public class updateRebillDb {
	 String[][] allData;
	 int rowCount;
	 int colCount;
	 String testInputNbr,tinCount,trkngnbr;
	 config c;
	 String[] resultArray = new String[2];
	public updateRebillDb(config c) {
		this.c=c;
		c.setLevel("3");
		getData();
	  	 
		for (int i=0;i<=rowCount;i++) {
				testInputNbr=allData[i][0];
				tinCount=allData[i][1];
				trkngnbr=allData[i][2];
				resultArray=validateResults(trkngnbr);
	    		writeToDB(testInputNbr,tinCount,trkngnbr,resultArray);
    		}
    		
	}
	
	
	public synchronized void writeToDB(String testInputNbr,String tinCount,String trk,String[] resultArray) {
    	Connection GTMcon=null;
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
		
    	PreparedStatement stmt = null;
    	

    	try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
    	stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description,ERA_REBILL) values (?,?,?,?,?,?)");  
		stmt.setString(1,testInputNbr);  
		stmt.setString(2,tinCount);  
		stmt.setString(3,trk);  
		stmt.setString(4,resultArray[0]);  
		stmt.setString(5,resultArray[1]);  
		stmt.setString(6,"Y");  
	
		stmt.executeUpdate();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
		
    	
    	
    	try {
		//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
		stmt=GTMcon.prepareStatement("update rebill_results set result=?,description=?,ERA_REBILL='Y' where trkngnbr=?");  
		
		stmt.setString(1,resultArray[0]);  
		stmt.setString(2,resultArray[1]); 
		stmt.setString(3,trk); 
		stmt.executeUpdate();
		
	}
	catch(Exception e) {
		System.out.println(e);
	}
	}
	
	
    public  String[] validateResults(String trk) {
       	
       	Boolean result=null;
       	Connection con = null;
       	String[] resultArray = new String[2];
       	
       	try {
       	
       		if (c.getLevel().equals("2")){
       			 c.setEraL2DbConnection();
          		 con=c.getEraL2DbConnection();
          	 }
          	 else if (c.getLevel().equals("3")){
          		 	c.setEraL3DbConnection();
          		 	con=c.getEraL3DbConnection();
          	 	}
       	
       	}
       	catch(Exception e) {
       		
       		System.out.println("Could Not Get ERA DB Connections");
       	}
       	

       	PreparedStatement stmt = null;
       	ResultSet rs = null;
       	try {
       		
       		stmt=con.prepareStatement("select * from invadj_schema.rdt_rebill_request where airbill_nbr=?");  
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
       			      resultArray[1]="Not In ERA Database";
       			}
       			   else{
       				    String statusDesc = rs.getString("STATUS_DESC");
       	                String errorDesc = rs.getString("ERROR_DESC"); 	    	                
       	                System.out.println(statusDesc +"    "+errorDesc);
       	              
       	              if (statusDesc.equals("SUCCESS")) {
     	    			      resultArray[0]="pass";
     	    			      resultArray[1]="completed";
       	              }
       	              else {
     	    			      resultArray[0]="fail";
     	    			      resultArray[1]=errorDesc;
       	              }
       			   }
       		} catch (SQLException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
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
	
	
	String databaseSqlCount="select count(*) as total from rebill_regression where trkngnbr is not null ";
	String databaseSqlQuery="select  test_input_nbr, tin_count, trkngnbr from rebill_regression where trkngnbr is not null ";
	
	
	
	
   	
	
	

   	try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
    		
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
        	 //colCount=17;
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
	
	
}
	
}
