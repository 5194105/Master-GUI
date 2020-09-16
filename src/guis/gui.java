package guis;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openqa.selenium.WebDriver;

import UD.UdExecution;
import configuration.config;
import configuration.importData;
import configuration.mouse;
import configuration.testStuff;
import rebill.rebillMain;
import rebill_troubleshoot.rebillTroubleshoot;
import ud_compare.udcompare;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui {

	 BufferedImage img = null;
     Image dimg;
     ImageIcon imageIcon;
     String homePath;
     String imagePath;
     BufferedImage mouseImage = null;
     Image mouseDimg = null;
     ImageIcon mouseimageIcon = null;
     JLabel mouseLabel;
     String mouseLabelStringName;
     String mouseLabelStringLight;
     String mouseLabelStringDark;
     JLabel lblNewLabel_1;
     JLabel lblNewLabel_2;
     JLabel lblNewLabel_3;
     JLabel lblNewLabel_4;
     String optionsDark="options.png";
     String dbDark="db.png";
     String linkDark="link.png";
     String infoDark="info.png";
     String optionsLight="darkoptions.png";
     String dbLight="darkdb.png";
     String linkLight="darklink.png";
     String infoLight="darkinfo.png";
     String selectionBar="selectionbar.png";
     String backgroundPic="default_template.png";
     
     
     String rebillDefault="rebill.png";
     String rerateDefault="rerate.png";
     String instantDefault="instant.png";
     String datapopDefault="datapop.png";
     String udDefault="ud.png";
     String prerateDefault="prerate.png";
     String eraRerateDefault="eraRerate.png";
     
     String rebillAlt="rebillHighlight.png";
     String rerateAlt="rerateHighlight.png";
     String instantAlt="instantHighlight.png";
     String datapopAlt="datapopHighlight.png";
     String udAlt="udHighlight.png";
     String prerateAlt="prerateHighlight.png";
     String eraRerateAlt="eraRerateHighlight.png";
    
   
    
     public String rebillTroubleshootPic="rebillTroubleshootPic.png";
     public String rebillTroubleshootAlt="rebillTroubleshootPicHighlight.png";

     
     String libDirectoryDB,libDirectoryExcel,libDirectorySelenium;
     
     String GtmDbName,GtmDbResults,GtmDbPassword,retryAttempts,secondTimeout,rebillL2URL,rebillL3URL;
     String rtmDbName,rtmDbPassword;
     String rerateL2URL,rerateL3URL,prerateL2URL,prerateL3URL;
     String instantInvoiceL2URL,instantInvoiceL3URL;
     String rebillResultTable;
     String rtmBatchShippingResults;
     //Hi aksshay checking github
     
     WebDriver ieDriver;
     WebDriver firefoxDriver;
     WebDriver chromeDriver;
     
     BufferedReader br;
     
     File tempFile,configFile;
     
     udAutomation ud;
     rebillAutomationGui rebill;
     rerateAutomationGui rerate;
     prerateAutomationGui prerate;
     instantInvoiceAutomationGui instantInvoice;
     datapopAutomationGui datapop;
     eraRerateAutomationGui eraRerate;
  
     
     
     Object obj;
     
     config c;
	
	public JFrame frame;
	
	String udUsername,udPassword;

	
	JLabel rebillGUI,rerateGUI,prerateGUI,instantInvoiceGUI,udAutomationGUI,datapopGUI,background,rebillTroubleShootGUI,eraRerateGUI;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		importData id = new importData(); 
		c=id.getConfig();
		initialize();
		
		  	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
	      /*
	      libDirectoryDB=homePath+"\\libs+\\DB";
	      libDirectoryExcel=homePath+"\\libs+\\Excel";
	      libDirectorySelenium=homePath+"\\libs+\\Selenium";
	      
	      System.out.println(homePath);
	      System.out.println(imagePath);
	        
	        */
		frame = new JFrame();
		frame.setBounds(100, 100, 985, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		
		
		
		frame.setName("menu");				
						
		rebillGUI = new JLabel("New label");
		rebillGUI.setBounds(171, 309, 338, 64);
		rebillGUI.setName("rebill");

				
		rerateGUI = new JLabel("New label");
		rerateGUI.setBounds(171, 389, 338, 58);
		rerateGUI.setName("rerate");

		prerateGUI = new JLabel("New label");
		prerateGUI.setBounds(171, 463, 338, 54);
		prerateGUI.setName("prerate");
		
		rebillTroubleShootGUI = new JLabel("New label");
		rebillTroubleShootGUI.setBounds(171, 525, 338, 54);
		rebillTroubleShootGUI.setName("rebillTroubleshoot");
		

		instantInvoiceGUI = new JLabel("New label");
		instantInvoiceGUI.setBounds(561, 309, 338, 64);
		instantInvoiceGUI.setName("instant");
				
		udAutomationGUI = new JLabel("New label");
		udAutomationGUI.setBounds(561, 389, 338, 58);
		udAutomationGUI.setName("ud");
				
		eraRerateGUI = new JLabel("New label");
		eraRerateGUI.setBounds(561, 463, 338, 54);
		eraRerateGUI.setName("eraRerate");
		
		
		
		
		
		
		
	
		
		
		
						
								
		guiBase gb = new guiBase();
		mouse m = new mouse(gb,this,c,this,frame);
		m.setFrame(frame);
		m.setupBaseIcons();
		
		m.addIconWithMouse(rebillGUI,"rebill",rebillDefault,rebillAlt);
		m.addIconWithMouse(rerateGUI,"rerate",rerateDefault,rerateAlt);
		m.addIconWithMouse(prerateGUI,"prerate",prerateDefault,prerateAlt);
		m.addIconWithMouse(instantInvoiceGUI,"instant",instantDefault,instantAlt);
		m.addIconWithMouse(udAutomationGUI,"ud",udDefault,udAlt);
		m.addIconWithMouse(eraRerateGUI,"eraRerate",eraRerateDefault,eraRerateAlt);	
		//m.addIconWithMouse(datapopGUI,"datapop",datapopDefault,datapopAlt);	

		m.addIconWithMouse(rebillTroubleShootGUI,"rebillTroubleshoot",rebillTroubleshootPic,rebillTroubleshootAlt);	
		
		m.setupBackground();
	
	    frame.setVisible(true);

	}
	
	//Sets up config file
	/*
	public void setUp() throws ClassNotFoundException {
		
	
		 homePath=System.getProperty("user.dir");
	      if (System.getProperty("user.dir").indexOf("dist")==-1){
	    	  imagePath=System.getProperty("user.dir");
	        }
	      else {
	    	  imagePath=homePath.substring(0,homePath.length()-5);
	        }
		
		//Gets path of config file in project directory. this is to get it wether you run from jar file
		//or from eclipse.
	    tempFile = new File(System.getProperty("user.dir")+"\\config.txt"); 
        if (tempFile.exists()==true){
            configFile=tempFile;
        }
        else{
             configFile=new File(new File(tempFile.getParent()).getParent()+"\\config.txt");
        }

        
        //Sets up to read from config file
        try {
        	br = new BufferedReader(new FileReader(configFile));
        } catch (FileNotFoundException ex) {
        	 System.out.println(ex);
     }
 
          String st;
          int counter=0;
          String tempString="";
          
     //While line is not null     
     try {
         while ((st = br.readLine()) != null) {
             counter++;
             
             //Once it sees the '=' symbol it will save the rest of line to a variable
             tempString=st.substring(st.indexOf("=")+1);
             
             
             
             System.out.println(tempString);
             System.out.println(st);
             System.out.println("");
             
             //case statement is each line. first line GTM DB NAME so i saved as GtmDBName variable
             switch(counter){
                 case 1 :
                     GtmDbName=tempString;
                 case 2 :
                	 GtmDbPassword=tempString;
                 case 3 :
                     rtmDbName=tempString;
                 case 4 :
                	  rtmDbPassword=tempString; 
                 case 5 :
                	 retryAttempts=tempString;
                 case 6 :
                	 secondTimeout=tempString;
                 case 7 :
                	 rebillL2URL=tempString; 
                 case 8 :
                	 rebillL3URL=tempString;
                 case 9 :
                	 rerateL2URL=tempString;
                 case 10 :
                	 rerateL3URL=tempString;
                 case 11 :
                	 prerateL2URL=tempString;
                 case 12 :
                	 prerateL3URL=tempString;
                 case 13 :
                	 instantInvoiceL2URL=tempString;
                 case 14 :
                	 instantInvoiceL3URL=tempString;
                 case 15 :
                	 rebillResultTable=tempString;
                 case 16 :
                	 rtmBatchShippingResults=tempString;
                 case 17 :
                	 udUsername=tempString;
                 case 18 :
                	 udPassword=tempString;
                	 }
         }
     } catch (IOException ex) {
    	 System.out.println(ex);
     }
     
     
     
     

		
		String chromeSetProperty="webdriver.chrome.driver";
		String ieSetProperty="webdriver.ie.driver";
		//String firefoxSetProperty="";
		
		String chromePath=homePath+"\\drivers\\chromedriver.exe";
		String ieDriverPath=homePath+"\\drivers\\IEDriverServer.exe";
		//String firefoxPath="";
		
		
		//Set up object so that we can reference this data throughout the program
		c = new config();
		
	    c.setGtmDbName( GtmDbName);
	    c.setGtmDbPassword( GtmDbPassword);
	   // c.setGtmRevToolsConnection(GtmDbName, GtmDbPassword);
	    c.setRetryAttempts( retryAttempts);
	    c.setSecondTimeout( retryAttempts);
	    c.setRebillL2URL( rebillL2URL);
	    c.setRebillL3URL( rebillL3URL);
	    c.setRtmDbName( rtmDbName);
	    c.setRtmDbPassword( rtmDbPassword);
	    c.setRerateL2URL( rerateL2URL);
	    c.setRerateL3URL( rerateL3URL);
	    c.setPrerateL2URL( prerateL2URL);
	    c.setPrerateL3URL( prerateL3URL);
	    c.setInstantInvoiceL2URL( instantInvoiceL2URL);
	    c.setInstantInvoiceL3URL( instantInvoiceL3URL);
	 	c.setRebillResultTable( rebillResultTable);
	 	c.setRtmBatchShippingResults( rtmBatchShippingResults);	
		c.setChromeProperty(chromeSetProperty);
		c.setIeProperty(ieSetProperty);
		c.setChromePath(chromePath);
		c.setIeDriverPath(ieDriverPath);
		c.setUdUsername(udUsername);
		c.setUdPassword(udPassword);
		//c.setIEDriver(ieSetProperty,ieDriverPath );
		//c.setChromeDriver(chromeSetProperty,chromePath);
		c.setCiUsername("test_readonly");
		c.setCiPassword("perftest");
		c.setCiDbString("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/CVM_QUERY_SVC1_CL,cn=OracleConect,dc=ute,dc=fedex,dc=com");

	//	c.setCiDbConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/CVM_QUERY_SVC1_CL,cn=OracleConect,dc=ute,dc=fedex,dc=com","test_readonly","perftest");

		
		
		
	}
	*/
}
