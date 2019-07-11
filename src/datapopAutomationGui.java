import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openqa.selenium.WebDriver;

public class datapopAutomationGui {

	private JFrame frame;

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

     
     WebDriver ieDriver;
     WebDriver firefoxDriver;
     WebDriver chromeDriver;
     
     BufferedReader br;
     
     File tempFile,configFile;
     
     udAutomation ud;
     
     Object obj;
     
     config c;
     
     private JLabel lblNewLabel_12;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					datapopAutomationGui window = new datapopAutomationGui();
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
	public datapopAutomationGui() {
		initialize();
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
	};
			
				  lblNewLabel_1.addMouseListener( ml );
				  lblNewLabel_2.addMouseListener( ml );
				  lblNewLabel_3.addMouseListener( ml );
				  lblNewLabel_4.addMouseListener( ml );
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
		
		lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setBounds(0, 587, 99, 75);
		frame.getContentPane().add(lblNewLabel_12);

		addIcon(lblNewLabel_1,optionsDark);
		addIcon(lblNewLabel_2,dbDark);
		addIcon(lblNewLabel_3,linkDark);
		addIcon(lblNewLabel_4,infoDark);
		addIcon(lblNewLabel_12,backPic);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 983, 662);
		frame.getContentPane().add(lblNewLabel);
		addIcon(lblNewLabel,"default_template.png");
		
		frame.setVisible(true);
		
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
	
}
