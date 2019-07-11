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
     String rebillPic="rebill.png";
     String reratePic="rerate.png";
     String instantPic="instant.png";
     String datapopPic="datapop.png";
     String udPic="ud.png";
     String preratePic="prerate.png";
     String rebillPicHighlight="rebillHighlight.png";
     String reratePicHighlight="rerateHighlight.png";
     String instantPicHighlight="instantHighlight.png";
     String datapopPicHighlight="datapopHighlight.png";
     String udPicHighlight="udHighlight.png";
     String preratePicHighlight="prerateHighlight.png";
     String backPic="back.png";
     String backPicHighlight="darkBack.png";
     
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
     
     Object obj;
     
     config c;
	
	private JFrame frame;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;

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
		initialize();
		setUp();

		  MouseListener ml = new MouseAdapter()
		  {
		              
		      @Override
		      public void mouseEntered(MouseEvent e)
		      {
		         
		          mouseLabel = (JLabel)e.getSource();

		          	  if (mouseLabel==lblNewLabel_1) {     
		        	  mouseLabelStringDark=optionsDark;
		        	  mouseLabelStringLight=optionsLight;
		          	  }       	  
		        	  else if (mouseLabel==lblNewLabel_2) {	        		  
		        		  mouseLabelStringDark=dbDark;
			        	  mouseLabelStringLight=dbLight;	        		  
		        	  }
		        	  else if (mouseLabel==lblNewLabel_3) {	        		  
		        		  mouseLabelStringDark=linkDark;
			        	  mouseLabelStringLight=linkLight;			     	  
		        	  }		      	        	  
		        	  else if (mouseLabel==lblNewLabel_4) {
		        		  mouseLabelStringDark=infoDark;
			        	  mouseLabelStringLight=infoLight;		        		  
		        	  }
		          	  if (mouseLabel==lblNewLabel_6) {     
		        	  mouseLabelStringDark=rebillPic;
		        	  mouseLabelStringLight=rebillPicHighlight;
		        	  
		          	  }       	  
		        	  else if (mouseLabel==lblNewLabel_7) {	        		  
		        		  mouseLabelStringDark=reratePic;
			        	  mouseLabelStringLight=reratePicHighlight;	        		  
		        	  }
		        	  else if (mouseLabel==lblNewLabel_8) {	        		  
		        		  mouseLabelStringDark=preratePic;
			        	  mouseLabelStringLight=preratePicHighlight;			     	  
		        	  }		      	        	  
		        	  else if (mouseLabel==lblNewLabel_9) {
		        		  mouseLabelStringDark=instantPic;
			        	  mouseLabelStringLight=instantPicHighlight;		        		  
		        	  }
		        	  else if (mouseLabel==lblNewLabel_10) {     
		        	  mouseLabelStringDark=udPic;
		        	  mouseLabelStringLight=udPicHighlight;
		        	  obj=ud;
		          	  }       	  
		        	  else if (mouseLabel==lblNewLabel_11) {	        		  
		        		  mouseLabelStringDark=datapopPic;
			        	  mouseLabelStringLight=datapopPicHighlight;	        		  
		        	  }
		        	      
		        	  else if (mouseLabel==lblNewLabel_12) {	        		  
		        		  mouseLabelStringDark=backPic;
			        	  mouseLabelStringLight=backPicHighlight;	        		  
		        	  }
		        	
		          
		          try {
		              mouseImage = ImageIO.read(new File(imagePath+"\\assets\\"+mouseLabelStringLight));
		               } catch (IOException ee) {
		                  ee.printStackTrace();
		              }
		          mouseDimg = mouseImage.getScaledInstance(mouseLabel.getWidth(), mouseLabel.getHeight(),
		          Image.SCALE_SMOOTH);
		          mouseimageIcon = new ImageIcon(mouseDimg);
		          mouseLabel.setIcon(mouseimageIcon);  
		      }

		      @Override
		      public void mouseExited(MouseEvent e)
		      {
		        mouseLabel = (JLabel)e.getSource();
		          try {
		              mouseImage = ImageIO.read(new File(imagePath+"\\assets\\"+mouseLabelStringDark));
		               } catch (IOException ee) {
		                  ee.printStackTrace();
		              }
		          mouseDimg = mouseImage.getScaledInstance(mouseLabel.getWidth(), mouseLabel.getHeight(),
		          Image.SCALE_SMOOTH);
		          mouseimageIcon = new ImageIcon(mouseDimg);
		          mouseLabel.setIcon(mouseimageIcon);  
		      }
		
		  
		  
		    @Override
		     public void mouseClicked(MouseEvent e)
		    {
				try {
					
					
					 if (mouseLabel==lblNewLabel_6) {     
			        	  rebill = new rebillAutomationGui();
			          	  }       	  
			        	  else if (mouseLabel==lblNewLabel_7) {	        		  
			        		  rerate = new rerateAutomationGui();
			        	  }
			        	  else if (mouseLabel==lblNewLabel_8) {	        		  
			        		  prerate = new prerateAutomationGui();
			        	  }		      	        	  
			        	  else if (mouseLabel==lblNewLabel_9) {
			        		  instantInvoice= new instantInvoiceAutomationGui();
			        	  }
			        	  else if (mouseLabel==lblNewLabel_10) { 
			        	  ud = new udAutomation();
			        	//  ud.frame.setVisible(true);
			          	  }       	  
			        	  else if (mouseLabel==lblNewLabel_11) {	   
			        		  datapop= new datapopAutomationGui();
			        	  }

						} catch (Exception ee) {
							ee.printStackTrace();
					}
		    	}
		  	};
		
		
		  lblNewLabel_1.addMouseListener( ml );
		  lblNewLabel_2.addMouseListener( ml );
		  lblNewLabel_3.addMouseListener( ml );
		  lblNewLabel_4.addMouseListener( ml );
		  
		  lblNewLabel_6.addMouseListener( ml );
		  lblNewLabel_7.addMouseListener( ml );
		  lblNewLabel_8.addMouseListener( ml );
		  lblNewLabel_9.addMouseListener( ml );
		  lblNewLabel_10.addMouseListener( ml );
		  lblNewLabel_11.addMouseListener( ml );
		  
		  lblNewLabel_12.addMouseListener( ml );
		  
		  	  
		  
		  	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		 homePath=System.getProperty("user.dir");
	      if (System.getProperty("user.dir").indexOf("dist")==-1){
	    	  imagePath=System.getProperty("user.dir");
	        }
	      else {
	    	  imagePath=homePath.substring(0,homePath.length()-5);
	        }
	      
	      libDirectoryDB=homePath+"\\libs+\\DB";
	      libDirectoryExcel=homePath+"\\libs+\\Excel";
	      libDirectorySelenium=homePath+"\\libs+\\Selenium";
	      
	      System.out.println(homePath);
	      System.out.println(imagePath);
	        
	        
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 718);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setBounds(0, 587, 99, 75);
		frame.getContentPane().add(lblNewLabel_12);
		
		
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 112, 64);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, 67, 112, 64);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(0, 135, 112, 69);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(0, 203, 112, 75);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(320, 231, 442, 47);
		frame.getContentPane().add(lblNewLabel_5);
				
				lblNewLabel_6 = new JLabel("New label");
				lblNewLabel_6.setBounds(171, 309, 338, 64);
				frame.getContentPane().add(lblNewLabel_6);
						
						
						lblNewLabel_7 = new JLabel("New label");
						lblNewLabel_7.setBounds(171, 389, 338, 58);
						frame.getContentPane().add(lblNewLabel_7);
						
						lblNewLabel_8 = new JLabel("New label");
						lblNewLabel_8.setBounds(171, 463, 338, 54);
						frame.getContentPane().add(lblNewLabel_8);
						
						lblNewLabel_9 = new JLabel("New label");
						lblNewLabel_9.setBounds(561, 309, 338, 64);
						frame.getContentPane().add(lblNewLabel_9);
						
						lblNewLabel_10 = new JLabel("New label");
						lblNewLabel_10.setBounds(561, 389, 338, 58);
						frame.getContentPane().add(lblNewLabel_10);
						
						lblNewLabel_11 = new JLabel("New label");
						lblNewLabel_11.setBounds(561, 463, 338, 54);
						frame.getContentPane().add(lblNewLabel_11);
						
						JLabel lblNewLabel = new JLabel("New label");
						lblNewLabel.setBounds(0, 0, 983, 662);
						frame.getContentPane().add(lblNewLabel);
							
						
						addIcon(lblNewLabel_1,optionsDark);
						addIcon(lblNewLabel_2,dbDark);
						addIcon(lblNewLabel_3,linkDark);
						addIcon(lblNewLabel_4,infoDark);
						addIcon(lblNewLabel_5,selectionBar);
						addIcon(lblNewLabel_6,rebillPic);
						addIcon(lblNewLabel_7,reratePic);
						addIcon(lblNewLabel_8,preratePic);
						addIcon(lblNewLabel_9,instantPic);
						addIcon(lblNewLabel_10,udPic);
						addIcon(lblNewLabel_11,datapopPic);
						addIcon(lblNewLabel_12,backPic);
						addIcon(lblNewLabel,"default_template.png");
		
		

	}
	
	public void addIcon(JLabel jlabel,String tempPic) {
		
		try {
		    img = ImageIO.read(new File(imagePath+"\\assets\\"+tempPic));
		} catch (IOException e) {
		    
		    e.printStackTrace();
		}
			dimg = img.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(),
		        Image.SCALE_SMOOTH);
		  imageIcon = new ImageIcon(dimg);
		  jlabel.setIcon(imageIcon);
	}
	
	
	public void setUp() {
		
		
		
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
          String tempString="";
     try {
         while ((st = br.readLine()) != null) {
             counter++;
             tempString=st.substring(st.indexOf("=")+1);
             System.out.println(tempString);
             System.out.println(st);
             System.out.println("");
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
		
		c = new config();
		
	    c.setGtmDbName( GtmDbName);
	    c.setGtmDbPassword( GtmDbPassword);
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

		

		
		
		
	}
	
}
