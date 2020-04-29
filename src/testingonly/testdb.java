package testingonly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testdb {
public static void main (String[] args) {
	
		Connection ecL2Con=null,ecL3Con,oreL3Con;
		try {
			
			//oreL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00261.ute.fedex.com:1526/PT1VD925","test_readonly", "perftest");                             
			//ecL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00271.ute.fedex.com:1526/IE2VD508","test_readonly", "perftest");
			
			ecL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00271.ute.fedex.com:1526/IE2VD991","test_readonly", "perftest");
		//	ecL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00299.ute.fedex.com:1526/PT1VD991","test_readonly", "perftest");
			ecL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00299.ute.fedex.com:1526/SDB00299.ute.fedex.com","test_readonly", "perftest");
			
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			}
		
		
		Boolean result=null;
		Connection con = ecL2Con;

	 PreparedStatement ps = null;
	try {
		ps = con.prepareStatement(
		         "select * from ec_intl_schema.ec_pre_rate_history where pkg_trkng_nbr =?");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	        //   "select * from INTL_EXPRS_ONLN_SCHEMA.intl_package");

	   
	       try {
			ps.setString(1,"458517422001");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			      result=false;   
			}
			   else{
			      System.out.println("IS NOT NULL");
			      result=true;
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
			}
}

