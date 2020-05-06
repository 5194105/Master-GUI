package testingonly;
import configuration.config;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Highest {

	 
	static config c = new config();
	static String level="2";
	
	static Boolean allCheckBox;
	static Boolean nullCheckBox;
	static Boolean failedCheckBox;
	static Boolean domesticCheckBox;
	static Boolean internationalCheckBox;
	static Boolean expressCheckBox;
	static Boolean groundCheckBox;
	static int rowCount;
	static int colCount;
	static int total;
	static int testCounter1,testCounter2,testCounter3,testCounter4;
	static int totalRows1,totalRows2,totalRows3,totalRows4;
	static int count;
	static String source;
	static int totalMod;
	static String databaseSqlQuery,databaseSqlCount;
	
    public static void main(String[] args) {
     
     source="db";
   	 allCheckBox=false;
   	 nullCheckBox=true;
   	 failedCheckBox=true;
   	 domesticCheckBox=true;
   	 internationalCheckBox=false;
   	 expressCheckBox=false;
   	 groundCheckBox=true;
   	 
   	 
   	 
  	if(source.contentEquals("excel")) {
    	
    	}
    	else if(source.contentEquals("db")) {
    	
    		rowCount=getDbCount();
    	
    	}
    	total= rowCount/4;
    	totalMod=rowCount%4;
    	totalRows1=total;
    	totalRows2=total;
    	totalRows3=total;
    	totalRows4=total;
    	
    	switch(totalMod) {
	    	case 1:
	    		totalRows1++;
	    		break;
	    	case 2 :
	    		totalRows1++;
	    		totalRows2++;
	    		break;
	    	case 3:
	    		totalRows1++;
	    		totalRows2++;
	    		totalRows3++;
	    		break;
    	
    	}	

    	
        if (level.equals("2"))
    	{
    		//levelUrl="https://testsso.secure.fedex.com/L2/eRA/index.html";
    		//c.setEraL2DbConnection();
    	}
    	else if (level.equals("3"))
    	{
    		//levelUrl="https://testsso.secure.fedex.com/L3/eRA/index.html";
    		//c.setEraL3DbConnection();
    	}
        
        
        
       
    }
    
    public static int getDbCount() {
		Connection GTMcon=null;
		Statement stmt = null;
		ResultSet rs = null;
		int total=0;
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
		
    	
      	 allCheckBox=false;
    	 nullCheckBox=true;
    	 failedCheckBox=true;
    	 domesticCheckBox=true;
    	 internationalCheckBox=false;
    	 expressCheckBox=false;
    	 groundCheckBox=true;
    	 
    	 databaseSqlCount="select count(*) as total from rebill regression ";
    	
    	if (allCheckBox==false) {
    		databaseSqlCount+="where ";
    	}
    	if (nullCheckBox==true && failedCheckBox==true) {
    		databaseSqlCount+=" (result is null or result ='failed') ";
    	}
    	if (nullCheckBox==true && failedCheckBox==false) {
    		databaseSqlCount+="result is null ";
    	}
    	if (nullCheckBox==false && failedCheckBox==true) {
    		databaseSqlCount+="result ='failed' ";
    	}
    	if (domesticCheckBox==true) {
    		databaseSqlCount+="and rs_type='DM' ";
    	}
    	if (internationalCheckBox==true) {
    		databaseSqlCount+="and rs_type='IL' ";
    	}
    	if (expressCheckBox==true) {
    		databaseSqlCount+="and company='EP' ";
    	}
    	if (groundCheckBox==true) {
    		databaseSqlCount+="and rs_type='GD' ";
    	}
    	
    	

    	try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
    		 stmt = GTMcon.createStatement();
        	 rs = stmt.executeQuery(databaseSqlCount);
        	 
        	 while(rs.next()) {
        		 System.out.println(rs.getInt("total"));
        		 total=rs.getInt("total");
        	 }
        
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
		return total;
	}

}
 

