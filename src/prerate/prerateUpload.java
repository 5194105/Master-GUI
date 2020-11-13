package prerate;



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
import guis.prerateAutomationGui;
import guis.rebillAutomationGui;

public class prerateUpload implements Runnable {
       String[][] allData;
       int rowCount;
       int colCount;
       double total=0;
       int counter=1;
       String testInputNbr,tinCount,trkngnbr,results,description;
       Boolean resultBooleaan;
       config c;
       String[] resultArray = new String[2];
     //  JFrame frame;
       Object o;
       prerateAutomationGui pag;
       public prerateUpload(config c,Object o) {
              this.c=c;
              this.o=o;
              pag = (prerateAutomationGui)o;
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
                   results=allData[i][3];
                   description =allData[i][4];
                   
                   
                   System.out.println(allData[i][0]);
                   System.out.println(allData[i][1]);
                   System.out.println(allData[i][2]);
                   System.out.println(allData[i][3]);
                   System.out.println(allData[i][4]);
                   resultBooleaan=validateResults(trkngnbr);
                  
                   if (resultBooleaan==true) {
                	   resultArray[0]="pass";
                	   resultArray[1]="completed";
                			   
                   }
                   
                   else if (resultBooleaan==false) {
                	 if (description==null || description.equals("Could Not Find in IORE")  || description.equals("Result Not Found")) {
                	  
                	 
                	   resultArray[0]="fail";
                	   resultArray[1]="Result Not Found";
                	 
                   }
                	 else {
                	   resultArray[0]=results;
                  	   resultArray[1]=description;
                	 }
                	 
                   }
                   
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
      		   
      	pag.getProgressBar().setValue((int)total);
      	pag.getProgressBar().repaint(); 
      	     try{Thread.sleep(150);}
      	     catch(Exception e){}  
      	   
       }

       
       
       public synchronized void writeToDB(String testInputNbr,String tinCount,String trk,String[] resultArray) {
       Connection GTMcon=null;

       
       PreparedStatement stmt = null;
       try {
       
       GTMcon=c.getGtmRevToolsConnection();
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
        stmt=GTMcon.prepareStatement("insert into gtm_rev_tools.prerate_results (test_input_nbr,tin_count,trkngnbr,result,description) values (?,?,?,?,?)");  
		stmt.setString(1,testInputNbr);  
		stmt.setString(2,tinCount);  
		stmt.setString(3,trk);  
		stmt.setString(4,resultArray[0]);  
		stmt.setString(5,resultArray[1]);  

       
              stmt.executeUpdate();
       }
       catch(Exception e) {
            //  System.out.println(e);
       }
              
       
       
       try {
              //     update gtm_rev_tools.rebill_results set result='fail',description='6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables' where trkngnbr='566166113544';
    		stmt=GTMcon.prepareStatement("update prerate_results set result=?,description=? where trkngnbr=?");
    		
    		stmt.setString(1,resultArray[0]);  
    		stmt.setString(2,resultArray[1]); 
    		stmt.setString(3,trk); 
    		stmt.executeUpdate();
              
       }
       catch(Exception e) {
            //  System.out.println(e);
       }
       try {
                     stmt.close();
              } catch (SQLException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              }
       }
       
       
    public  Boolean validateResults(String trk) {
             counter++;
             Boolean result=null;
             Connection con = null;
             PreparedStatement ps = null;
             String[] resultArray = new String[2];
             System.out.println(trk);
             try {
             
                    if (c.getLevel().equals("2")){
                           
                     con=c.getEcL2DbConnection();
              }
              else if (c.getLevel().equals("3")){
                     
                           con=c.getEcL3DbConnection();
                    }
             
             }
             catch(Exception e) {
                    
                 //   System.out.println("Could Not Get ERA DB Connections");
             }
             

             PreparedStatement stmt = null;
            ResultSet rs = null;
             try {
                    
            	 ps = con.prepareStatement("select * from ec_intl_schema.ec_pre_rate_history where pkg_trkng_nbr =?");
                 ps.setString(1,trk);  
                    
             } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
             }
                   
            
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
                
              return result;      
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
       
       
       String databaseSqlCount="select count(*) as total from prerate_view where trkngnbr is not null ";
       String databaseSqlQuery="select  test_input_nbr, tin_count, trkngnbr,result,description from prerate_view where trkngnbr is not null ";
       
       
       
       
       
       
       

       try {
        //insert into gtm_rev_tools.rebill_results (test_input_nbr,tin_count,trkngnbr,result,description) values ('125335','1','566166113544','fail','6015   :   A Technical Error has been encountered retrieving Freight, Surcharge, and tax tables');
              
              stmt = GTMcon.createStatement();
           //   System.out.println(databaseSqlCount);
             rs = stmt.executeQuery(databaseSqlCount);
             rs.next();
             rowCount=rs.getInt("total");
             stmt = GTMcon.createStatement();
          //   System.out.println(databaseSqlQuery);
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
          //    System.out.println(e);
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
