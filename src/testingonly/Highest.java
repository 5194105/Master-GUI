package testingonly;
import configuration.config;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Highest {

	 
	static config c = new config();
    public static void main(String[] args) {
        /*
    	try {
    		Runtime.getRuntime().exec("taskkill /F /IM Google Chrome.exe");
    	    String line;
    	    Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
    	    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	    while ((line = input.readLine()) != null) {
    	    	
    	     //   System.out.println(line); //<-- Parse data here.
    	      //  if(line.indexOf("chromedriver.exe")!=-1) {
    	        	System.out.println(line);
    	        	//Runtime.getRuntime().exec(KILL + line);
    	        	//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
    	        	Runtime.getRuntime().exec("taskkill /F /IM  chrome.exe");
    	      //  }
    	    }
    	    input.close();
    	} catch (Exception err) {
    	    err.printStackTrace();
    	}
    }
*/
    	
    	
    	
    	    	Boolean result=null;
    	    	Connection con = null;
    	    	
    	    	try {
    	    	
    	    	
    	    		 con=c.getEraL2DbConnection();
    	    	
    	    	}
    	    	catch(Exception e) {
    	    		
    	    		System.out.println("Could Not Get ERA DB Connections");
    	    	}
    	    	

    	    	PreparedStatement ps = null;
    	    	try {
    	    		
    	    		ps = con.prepareStatement(
    	    		         "select STATUS_DESC,ERROR_DESC from invadj_schema.rdt_rebill_request where airbill_nbr =?");
    	    	} catch (SQLException e) {
    	    		// TODO Auto-generated catch block
    	    		e.printStackTrace();
    	    	}
    	    	      
    	    	       try {
    	    			ps.setString(1,"794820100904");
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
    	    				    String statusDesc = rs.getString("STATUS_DESC");
    	    	                String errorDesc = rs.getString("ERROR_DESC");
    	    	                
    	    	                System.out.println(statusDesc +"    "+errorDesc);
    	    			   }
    	    		} catch (SQLException e) {
    	    			// TODO Auto-generated catch block
    	    			e.printStackTrace();
    	    		}
    	    	       
    	    	    
}
}
 

