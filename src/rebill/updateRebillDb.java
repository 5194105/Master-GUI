package rebill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import configuration.config;
import configuration.excel;

public class updateRebillDb {
	static String  homePath=System.getProperty("user.dir");
	static config c;
	static excel e;
	static Connection con,GTMcon;
	static Boolean writeToDatabaseBoolean;
	static ArrayList<rebillData> arrayData = new ArrayList<rebillData>();
	static String trackingNumber,reasonCode,rebillAccountNumber,login,password,tempLogin,
     result, description, test_input_nbr, tin_count,popUpString="Default", invoice_nbr1;
	static String[] resultArray = new String[2];
	
	public static void main(String args[]) {
	//public updateRebillDb(config c) {
		c = new config();
		//this.c=c;
		/* 
		 * TESTINGGGGGGG
		 */
		c.setExcelPath(homePath+"\\test data\\rebillUploadTest.xlsx");
		c.setLevel("2");
		c.setSource("excel");
		writeToDatabaseBoolean=true;
		/* 
		 * TESTINGGGGGGG
		 */
		
		
		setUpDatabaseConnection();
		createDataExcel();
		if (writeToDatabaseBoolean==true) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			GTMcon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		/*
		
		if (writeToDatabaseBoolean==true) {
			try {
				c.setGtmRevToolsConnection("GTM_REV_TOOLS", "Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GTMcon=c.getEraL2DbConnection();
			
		}
		
		*/
		if (c.getSource().equals("excel")) {
			for (int i=0;i<e.getRowCount();i++) {
	    		setUpNextTrackingNumber(i);
	    		getStatus();
	    		writeToExcel(i+1,0,resultArray[0]);
	    		writeToExcel(i+1,1,resultArray[1]);
	    			if(writeToDatabaseBoolean==true) {
	    				writeToDatabase();
	    			}
	    	}
		}
	}
	
	public static void writeToDatabase() {
		
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
   
    	
    	try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
    	stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");  
		stmt.setString(1,test_input_nbr);  
		stmt.setString(2,tin_count);  
		stmt.setString(3,trackingNumber);  
		stmt.setString(4,resultArray[0]);  
		stmt.setString(5,resultArray[1]);  
	//	rs = stmt.executeQuery();
		stmt.executeUpdate();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
		
    	
    	
    	try {
		//	update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
		stmt=GTMcon.prepareStatement("update rebill_results set result=?,description=? where trkngnbr=?");  
		
		stmt.setString(1,resultArray[0]);  
		stmt.setString(2,resultArray[1]); 
		stmt.setString(3,trackingNumber); 
		stmt.executeUpdate();
		//rs = stmt.executeQuery();
	}
	catch(Exception e) {
		System.out.println(e);
	}

	}
	
	public static void getStatus() {
		    	
		    	Boolean result=null;
		    	PreparedStatement stmt = null;
		    	ResultSet rs = null;
		    	
		    	resultArray[0]="";
		    	resultArray[1]="";
		    	
		    	try {
		    		
		    		stmt=con.prepareStatement("select * from invadj_schema.rdt_rebill_request where airbill_nbr=?");  
					stmt.setString(1,trackingNumber);  
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
		    	    
		}    
		    
		public  static  synchronized void writeToExcel(int rowCountExcel,int colCountExcel,String outputString){
				
				e.setCellData(rowCountExcel, colCountExcel, outputString);
				e.writeCellData();
			}
		    
		    

		
	
	
	
	
	
	
	
	
	
	public  static  void setUpDatabaseConnection() {
		
		if (c.getLevel().equals("2")) {
			c.setEraL2DbConnection();
			con=c.getEraL2DbConnection();
		}
		if (c.getLevel().equals("3")) {
			c.setEraL3DbConnection();
			con=c.getEraL3DbConnection();
		}
	}
	
	
	public  static  void createDataExcel() {
		if(c.getSource()=="excel"){
    		//Giving my excel path from GUI (path saved in config class... was passed through gui/mouse class)
    		e = new excel(c.getExcelPath());
    		//Creates a workbook.
    		e.setUpExcelWorkbook();
    		//Sets up the sheet at the a particular index (0 = sheet 1)
    		e.setUpExcelSheet(0);
    		//Counts how much data there is. Parameter is which column it should count. This means will count
    		//how many values/rows are there for column 3 (column 3 is never null.. so will get total rows).
    		//This will exclude row 1 which is for headers.
    		e.setRowCountAutomatically(2);
    		//Get number of columns.
    		e.setColCountAutomatically(0);
    		
    		//You can also give  a fixed number of rows/columns using e.setRowCountManually(x) and e.setColCountManually(x)
    		
    		//This will save all my data into objects from rebillData class. RebillData class will have
    		//getters and setters for every column in excel sheet (tin, tin count, trk, reason code, etc).
    		
    		//I go through each row in excel and save that entire row into a new object and at same time
    		//add that object into an array list. This array list will hold each object (row) of data.
    		for (int i=1;i<e.getRowCount()+1;i++) {
    			//Create new object and add it to my arraylist at same time.
    			//uses the getCellData method which will use row,col paramter.
    			arrayData.add( new rebillData(e.getCellData(i, 0),e.getCellData(i, 1),e.getCellData(i, 2),e.getCellData(i, 3),e.getCellData(i, 4),e.getCellData(i, 5),e.getCellData(i, 6),e.getCellData(i, 7),e.getCellData(i+1, 8),e.getCellData(i, 9),e.getCellData(i, 10),e.getCellData(i, 11),e.getCellData(i, 12),e.getCellData(i+1, 13),e.getCellData(i, 14),e.getCellData(i, 15)));
    		}
    		//Closes the excel sheet.
    		e.saveAndClose();

    	}
		
		else if (c.getSource()=="db"){
		
			}
		}
	
	
	public  static  void setUpNextTrackingNumber(int trackingNumberCounter) {   
		  

        //If no tracking number is left then program is completed.
        try{
      	  System.out.println(arrayData.size());
      	  System.out.println(trackingNumberCounter);
      	  trackingNumber=arrayData.get(trackingNumberCounter).getTrkngnbr();
        if (trackingNumber.equals("") || trackingNumber==null ){
            System.out.println("REACHED HERE TEJESH!!!!!!!!!!!!!!!");
            JOptionPane.showMessageDialog(null, "Program Completed");
            System.exit(0);
            
        }}
        catch(Exception e){
            System.out.println("REACHED HERE TEJESH EXCEPTION!!!!!!!!!!!!!!!");
            JOptionPane.showMessageDialog(null, "Program Completed");
         System.exit(0);
        }
        
        
        
        reasonCode=arrayData.get(trackingNumberCounter).getReason_code();
        rebillAccountNumber=arrayData.get(trackingNumberCounter).getRebill_acct();
        login=arrayData.get(trackingNumberCounter).getLogin();
        password=arrayData.get(trackingNumberCounter).getPassword();
        result=arrayData.get(trackingNumberCounter).getResult();
        description=arrayData.get(trackingNumberCounter).getDescription();
        test_input_nbr=arrayData.get(trackingNumberCounter).getTest_input_nbr();
        tin_count=arrayData.get(trackingNumberCounter).getTin_count();
        invoice_nbr1=arrayData.get(trackingNumberCounter).getInvoice_nbr_1();
        
        
	}
	
}
