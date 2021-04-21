package configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;

public class importData {
	config c;
	File tempFile,configFile;
	BufferedReader br;
	
	public importData() {
		c=new config();
	      tempFile = new File(System.getProperty("user.dir")+"\\config.txt"); 
	        if (tempFile.exists()==true){
	            configFile=tempFile;
	        }
	        else{
	             configFile=new File(new File(tempFile.getParent()).getParent()+"\\config.txt");
	        }
	        
	        
	        try {
	        	br = new BufferedReader(new FileReader(configFile));
	        } catch (FileNotFoundException ex) {
	        	 System.out.println(ex);
	     }
	 
	          String st;
	          int counter=0;
	          String tempStringPostFix="";
	          String tempStringPreFix="";
	          String[] parts=null;
	          
	          
	      
	          
	     //While line is not null     
	     try {
	         while ((st = br.readLine()) != null) {
	        	  
	        	
	        	 parts = st.split("=");
	        	 tempStringPreFix = parts[0]; 
	        	 tempStringPostFix = parts[1];
	        	 System.out.println (tempStringPreFix);
	        //System.out.println ("POST: "+tempStringPostFix);
	        	// tempStringPreFix=st.substring(st.indexOf("=")+1);
	        	// tempStringPostFix=st.substring(st.indexOf("=")+1);
	        	 
	        	 switch(tempStringPreFix) {
	        	 
	        	 case "GTM DB Username" :
	        		c.setGtmDbUsername(tempStringPostFix);
	        		 break;
	        	 case "GTM DB Password" :
	        		 c.setGtmDbPassword(tempStringPostFix);
	        		 break;
	        	 case "RTM DB Username" :
	        		 c.setRtmDbUsername(tempStringPostFix);
	        		 break;
	        	 case "RTM DB Password" :
	        		 c.setRtmDbPassword(tempStringPostFix);
	        		 break;
	        	 case "ORE L2 DB Username" :
	        		 c.setOreL2DbUsername(tempStringPostFix);
	        		 break;
	        	 case "ORE L2 DB Password" :
	        		 c.setOreL2DbPassword(tempStringPostFix);
	        		 break;
	        	 case "ORE L3 DB Username" :
	        		 c.setOreL3DbUsername(tempStringPostFix);
	        		 break;
	        	 case "ORE L3 DB Password" :
	        		 c.setOreL3DbPassword(tempStringPostFix);
	        		 break;
	        	 case "EC L2 DB Username" :
	        		 c.setEcL2DbUsername(tempStringPostFix);
	        		 break;
	        	 case "EC L2 DB Password" :
	        		 c.setEcL2DbPassword(tempStringPostFix);
	        		 break;
	        	 case "EC L3 DB Username" :
	        		 c.setEcL3DbUsername(tempStringPostFix);
	        		 break;
	        	 case "EC L3 DB Password" :
	        		 c.setEcL3DbPassword(tempStringPostFix);
	        		 break;
	        	 case "RDT L2 DB Username" :
	        		 c.setEraL2DbUsername(tempStringPostFix);
	        		 break;
	        	 case "RDT L2 DB Password" :
	        		 c.setEraL2DbPassword(tempStringPostFix);
	        		 break;
	        	 case "RDT L3 DB Username" :
	        		 c.setEraL3DbUsername(tempStringPostFix);
	        		 break;
	        	 case "RDT L3 DB Password" :
	        		 c.setEraL3DbPassword(tempStringPostFix);
	        		 break;
	        	 case "Rebill Retry Attempts" :
	        		 c.setRebillRetryAttempts(tempStringPostFix);
	        		 break;
	        	 case "Rebill Seconds Timeout" :
	        		 c.setRebillSecondTimeout(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate Retry Attempts" :
	        		 c.setPrsRerateRetryAttempts(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate Seconds Timeout" :
	        		 c.setPrsRerateSecondTimeout(tempStringPostFix);
	        		 break;
	        	 case "ERA Rerate Retry Attempts" :
	        		 c.setEraRerateRetryAttempts(tempStringPostFix);
	        		 break;
	        	 case "ERA Rerate Seconds Timeout" :
	        		 c.setEraRerateSecondTimeout(tempStringPostFix);
	        		 break;
	        	 case "Prerate Retry Attempts" :
	        		 c.setPrerateRetryAttempts(tempStringPostFix);
	        		 break;
	        	 case "Prerate Seconds Timeout" :
	        		 c.setPrerateSecondTimeout(tempStringPostFix);
	        		 break;
	        	 case "Instant Invoice Retry Attempts" :
	        		 c.setInstantInvoiceRetryAttempts(tempStringPostFix);
	        		 break;
	        	 case "Instant Invoice Seconds Timeout" :
	        		 c.setInstantInvoiceSecondTimeout(tempStringPostFix);
	        		 break;
	        	 case "Rebill L2 URL" :
	        		c.setRebillL2Url(tempStringPostFix);
	        		 break;
	        	 case "Rebill L3 URL" :
	        			c.setRebillL3Url(tempStringPostFix);
	        		 break;
	        	 case "ERA Rerate L2 URL" :
	        			c.setEraRerateL2Url(tempStringPostFix);
	        		 break;
	        	 case "ERA Rerate L3 URL" :
	        			c.setEraRerateL3Url(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate L2 Main URL" :
	        		 c.setPrsRerateMainL2Url(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate L2 Create URL" :
	        		 c.setPrsRerateCreateL2Url(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate L2 PA URL" :
	        		 c.setPrsReratePaL2Url(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate L3 Main URL" :
	        		 c.setPrsRerateMainL3Url(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate L3 Create URL" :
	        		 c.setPrsRerateCreateL3Url(tempStringPostFix);
	        		 break;
	        	 case "PRS Rerate L3 PA URL" :
	        		 c.setPrsReratePaL3Url(tempStringPostFix);
	        		 break;
	        	 case "Prerate L2 URL" :
	        		 c.setPrerateL2Url(tempStringPostFix);
	        		 break;
	        	 case "Prerate L3 URL" :
	        		 c.setPrerateL3Url(tempStringPostFix);
	        		 break;
	        	 case "Instant Invoice L2 URL" :
	        		 c.setInstantInvoiceL2Url(tempStringPostFix);
	        		 break;
	        	 case "Instant Invoice L3 URL" :
	        		 c.setInstantInvoiceL3Url(tempStringPostFix);
	        		 break;
	        	 case "UD Username" :
	        		 c.setUdUsername(tempStringPostFix);
	        		 break;
	        	 case "UD Password" :
	        		 c.setUdPassword(tempStringPostFix);
	        		 break;
	        	 case "Stephen Password" :
	        		 c.setStephenPassword(tempStringPostFix);
	        		 break;
	        		 
	        		 
	        	 case "Fedex Network" :
	        		 c.setFedexNetwork(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "Admin" :
	        		 c.setAdmin(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "Rebill Enabled" :
	        		 c.setRebillEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "Prerate Enabled" :
	        		 c.setPrerateEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "Rerate Enabled" :
	        		 c.setRerateEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "ERA Rerate Enabled" :
	        		 c.setEraRerateEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "Instant Invoice Enabled" :
	        		 c.setInstantInvoiceEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "UD Enabled" :
	        		 c.setUdEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        	 case "Rebill Troubleshoot" :
	        		 c.setRebillTroubleshootEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        		 
	        	 case "Update Hostname" :
	        		 c.setUpdateHostname(tempStringPostFix);
	        		 break;
	        	 case "Version" :
	        		 c.setVersion(tempStringPostFix);
	        		 break;
	        		 
	        	 case "Update Path" :
	        		 c.setUpdatePath(tempStringPostFix);
	        		 break;
	        	 case "Update Username" :
	        		 c.setUpdateUser(tempStringPostFix);
	        		 break;
	        	 case "Update Password" :
	        		 c.setUpdatePassword(tempStringPostFix);
	        		 break;
	        	 case "Credit And Debit" :
	        		 c.setCreditAndDebitEnabled(covertBoolean(tempStringPostFix));
	        		 break;
	        		 
	        	 case "stephen example" :
	        		 c.setStephenTest(tempStringPostFix);
	        		 break;
	        		 
	        	 case "Chrome Property" :
	        		 c.setChromeProperty(tempStringPostFix);
	        		 break;
	        		 
	        	 case "Chrome Path" :
	        		 c.setChromePath(System.getProperty("user.dir")+tempStringPostFix);
	        		 break;
	        		 
	        		 
	        	 case "IE Property" :
	        		 c.setIeProperty(tempStringPostFix);
	        		 break;
	        		 
	        	 case "IE Path" :
	        		 c.setIeDriverPath(System.getProperty("user.dir")+tempStringPostFix);
	        		 break;
	        		
	        		 
	        
	        		 
	        		 
	        		 
	        		 
	        	 }
	         }
	     } catch(Exception e) {
	    	 System.out.println(e);
	     }
	         
	     
	     //Setup DBs for use.
	     
	     if (c.getFedexNetwork()==true) {
		     c.setRtmDbConnection(c.getRtmDbUsername(),c.getRtmDbPassword());
		     c.setGtmRevToolsConnection(c.getGtmDbUsername(),c.getGtmDbPassword());
		   //  c.setOreL2DbConnection(c.getOreL2DbUsername(),c.getOreL2DbPassword());
		     c.setDoreL3DbConnection(c.getOreL3DbUsername(),c.getOreL3DbPassword());
		     c.setIoreL3DbConnection(c.getOreL3DbUsername(),c.getOreL3DbPassword());
		    // c.setEcL2DbConnection(c.getEcL2DbUsername(),c.getEcL2DbPassword());
		     c.setEcL3DbConnection(c.getEcL3DbUsername(),c.getEcL3DbPassword());
		    // c.setEraL2DbConnection(c.getEraL2DbUsername(),c.getEraL2DbPassword());
		     c.setEraL3DbConnection(c.getEraL3DbUsername(),c.getEraL3DbPassword());
		    // c.setOracleARL2DbConnection(c.getOracleARL2DbUsername(),c.getOracleARL2DbPassword());
		     c.setOracleARL3DbConnection("appsread","appsread");
		     
	     }
	   
	
	}
	public Boolean covertBoolean(String tempBooleanString) {
		if (tempBooleanString.equals("true")) {
			return true; 
			}
			else {
				return false;
		}
			
	}


public config getConfig() {
	return c;
}
}
