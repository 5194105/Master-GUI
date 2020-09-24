package configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JOptionPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class updateVersion {
	config c;
	String userDir;
	String hostname ;
    String username; 
    String password; 
    String copyTo;
    String copyFrom;
	public updateVersion(config c) {
		this.c=c;
		userDir=  System.getProperty("user.dir");
		hostname = c.getUpdateHostname();
        username = c.getUpdateUser(); 
        password = c.getUpdatePassword(); 
        copyTo = userDir;
		downloadVersion();
		
		if (checkVersion()==true) {
			 if (JOptionPane.showConfirmDialog(null, "Do You Want To Check For An Update?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				 if (JOptionPane.showConfirmDialog(null, "Newer Version Found. Do You Want To Download It?", "WARNING",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					 downloadNewVersion();
					 JOptionPane.showMessageDialog(null, "Update Downloaded. Please Check Downloads Folder To Install.");
				    // yes option
				 } else {
					    // no option
					}
				 } else {
				    // no option
				}
		}
		if (checkVersion()==false) {
			JOptionPane.showMessageDialog(null, "Your Program Is Up To Date");
			
		}
		
	}
	
	
	
	
	public void downloadVersion() {
		 	
	        copyFrom = c.getUpdatePath()+"version.txt"; 
	        
	        JSch jsch = new JSch();
	        Session session = null;
	        System.out.println("Trying to connect.....");
	        try {
	            session = jsch.getSession(username, hostname, 22);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setPassword(password);
	            session.connect(); 
	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel; 
	            
	            sftpChannel.get(copyFrom, copyTo);  //this can be used to download the file from linux
	            
	        }
	        catch(Exception e) {
	        	System.out.println(e);
	        }
	}
	
	public Boolean checkVersion() {
		Boolean versionBoolean;
		File versionFile;
    	BufferedReader br = null;
    	String version = null;
    	String st;
    	versionFile = new File(System.getProperty("user.dir")+"\\version.txt");    
    	        try {
    	        	br = new BufferedReader(new FileReader(versionFile));
    	        } catch (FileNotFoundException ex) {
    	        	 System.out.println(ex);
    	     }
    	        
    	        try {
    		         while ((st = br.readLine()) != null) {
    		        version=st;	 
    		         }
    	        }catch(Exception e) {
    	        	return false;
    	        }
	
    	        if(!version.equals(c.getVersion())) {
    	        	return true;
    	        }
    	        else {
    	        	return false;
    	        }
    	        
	
	}
	
	
	public void downloadNewVersion() {
		
		  copyFrom = c.getUpdatePath()+"*exe"; 
		  copyTo=System.getProperty("user.home")+"\\Downloads";
		  
	        JSch jsch = new JSch();
	        Session session = null;
	        System.out.println("Trying to connect.....");
	        try {
	            session = jsch.getSession(username, hostname, 22);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setPassword(password);
	            session.connect(); 
	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel; 
	            
	            sftpChannel.get(copyFrom, copyTo);  //this can be used to download the file from linux
	            
	        }
	        catch(Exception e) {
	        	System.out.println(e);
	        }
	}
}



/*


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
package unix.up.and.down;


import javax.swing.JOptionPane;


public class UnixUpAndDown {

    
    public static void main(String[] args) {
        // TODO code application logic here
        String hostname = "srh22157.ute.fedex.com";
        String username = "f5194105"; 
        String password = "Online36"; 

       
        String  copyTo = "C:\\Users\\5194105\\Documents\\R63\\MISC\\UT";
        String copyFrom = "/home/f5194105/UnixTest/*"; 
       //String copyFrom = "/var/fedex/srs/fds/data/outfiles/settlement/prnt_rdy_files/dom_orig/*.07329.pdf"; 
      
        
        //connection setup============================================
        
        JSch jsch = new JSch();
        Session session = null;
        System.out.println("Trying to connect.....");
        try {
            session = jsch.getSession(username, hostname, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect(); 
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel; 
            
            
            
          //fetching the file names in local folder and uploading the files   
           /*
            File[] fileList = allFilename(copyFrom) ;
            
            for (int i = 0; i < fileList.length; i++) {
  		      if (fileList[i].isFile()) {
  		    	String local = copyFrom+fileList[i].getName();
  		        System.out.println("uploading .......... " + fileList[i].getName());
  		      sftpChannel.put(local, copyTo);
  		    System.out.println("Done !!");
  		      } 
  		    }
           */
            
           /* 
       //  sftpChannel.get(copyFrom, copyTo);  //this can be used to download the file from linux
          
         sftpChannel.get(copyFrom, copyTo);  //this can be used to download the file from linux
           
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        System.out.println("All Done !!");
      //  JOptionPane.showMessageDialog(null, "Finished");
        System.exit(0);
    }
    
        
        
        
        
    }
    

*/