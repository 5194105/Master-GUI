package testingonly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testdb {
	static Connection ecL2Con=null,ecL3Con,oreL3Con,eraL2Con;
	 static PreparedStatement ps;
public static void main (String[] args) {
	
	//Statement stmt = null;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			eraL2Con=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/INVADJ_SVC_INT,cn=OracleContext,dc=ute,dc=fedex,dc=com","INVADJ_APP","apppwdli");
			PreparedStatement stmt=eraL2Con.prepareStatement("select * from invadj_schema.rdt_rebill_request where airbill_nbr=?");  
			stmt.setString(1,"794820100904");  
			ResultSet rs = stmt.executeQuery();
			/*
			stmt = eraL2Con.createStatement();
			 String sql = "select * from invadj_schema.rdt_rebill_request";
			 
			 */
			
			 while(rs.next()){
				 System.out.println();
		        
		      }
		      
			//eraL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00270.ute.fedex.com:1526/IDB00270.ute.fedex.com","INVADJ_APP", "apppwdli");
			
			//oreL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00261.ute.fedex.com:1526/PT1VD925","test_readonly", "perftest");                             
			//ecL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00271.ute.fedex.com:1526/IE2VD508","test_readonly", "perftest");
			
		//	ecL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00271.ute.fedex.com:1526/IE2VD991","test_readonly", "perftest");
		//	ecL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00299.ute.fedex.com:1526/PT1VD991","test_readonly", "perftest");
		//	ecL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00299.ute.fedex.com:1526/SDB00299.ute.fedex.com","test_readonly", "perftest");
			
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
				System.out.println("Failed up here 1 :"+e);

			}
	
		
	
		;

	
	try {
		//where airbill_nbr
		ps = ecL2Con.prepareStatement("select * from invadj_schema.rdt_rebill_request");
	} catch (Exception e) {
		System.out.println("Failed up here 2 :"+e);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	        //   "select * from INTL_EXPRS_ONLN_SCHEMA.intl_package");

	   /*
	       try {
			//ps.setString(1,"794820100904");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       */
	       ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			if (rs.next()==false){
			      System.out.println("Is NULL");
			       
			}
			   else{
			      System.out.println("IS NOT NULL");
			    
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
			}
}

