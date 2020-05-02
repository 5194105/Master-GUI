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
	static String level="2";
    public static void main(String[] args) {
     
    	
    }
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
    	
    public static Object[] validateResults(String trkngnbr) {
    	
    	    	Boolean result=null;
    	    	Connection con = null;
    	    	Object[] resultArray = new Object[2];
    	    	
    	    	try {
    	    	
    	    		if (level.equals("2")){
    	       		 
    	       		 con=c.getEraL2DbConnection();
    	       	 }
    	       	 else if (level.equals("3")){
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
    				stmt.setString(1,"794820100904");  
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

}
 

