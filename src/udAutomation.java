import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class udAutomation {

    JLabel lblNewLabel_1;
    JLabel lblNewLabel_2;
    JLabel lblNewLabel_3;
    JLabel lblNewLabel_4;
    
    BufferedImage mouseImage = null;
    Image mouseDimg = null;
    ImageIcon mouseimageIcon = null;
    JLabel mouseLabel;
    
    BufferedImage img = null;
    Image dimg;
    ImageIcon imageIcon;
    String homePath;
    String imagePath;

    String mouseLabelStringName;
    String mouseLabelStringLight;
    String mouseLabelStringDark;
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
    String upload="upload.png";
    String execute="execute.png";
    
    String libDirectoryDB,libDirectoryExcel,libDirectorySelenium;
    
    String GtmDbName,GtmDbResults,GtmDbPassword,retryAttempts,secondTimeout,rebillL2URL,rebillL3URL;
    String rtmDbName,rtmDbPassword;
    String rerateL2URL,rerateL3URL,prerateL2URL,prerateL3URL;
    String instantInvoiceL2URL,instantInvoiceL3URL;
    String rebillResultTable;
    String rtmBatchShippingResults;
	
	JFrame frame;
	private JLabel lblLevel;
	private JRadioButton rdbtnL_1;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblCompatibleMode;
	private JLabel lblType;
	private JLabel lblUnixPath;
	private JRadioButton radioButton_1;
	private JRadioButton rdbtnDom;
	private JRadioButton rdbtngreen;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public udAutomation() {
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
					
					

						} catch (Exception ee) {
							ee.printStackTrace();
					}
		    	}
		  	};
		
		
		  lblNewLabel_1.addMouseListener( ml );
		  lblNewLabel_2.addMouseListener( ml );
		  lblNewLabel_3.addMouseListener( ml );
		  lblNewLabel_4.addMouseListener( ml );
		
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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 718);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setBounds(327, 217, 69, 20);
		frame.getContentPane().add(lblLevel);
		
		JRadioButton rdbtnL = new JRadioButton("L2");
		rdbtnL.setForeground(Color.WHITE);
		rdbtnL.setOpaque(false);
		rdbtnL.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnL.setBounds(441, 213, 69, 29);
		frame.getContentPane().add(rdbtnL);
		
		rdbtnL_1 = new JRadioButton("L3");
		rdbtnL_1.setOpaque(false);
		rdbtnL_1.setForeground(Color.WHITE);
		rdbtnL_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnL_1.setBounds(517, 214, 69, 29);
		frame.getContentPane().add(rdbtnL_1);
		
		radioButton_1 = new JRadioButton("");
		radioButton_1.setOpaque(false);
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		radioButton_1.setBounds(555, 343, 40, 29);
		frame.getContentPane().add(radioButton_1);
		
		rdbtnDom = new JRadioButton("Dom");
		rdbtnDom.setOpaque(false);
		rdbtnDom.setForeground(Color.WHITE);
		rdbtnDom.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnDom.setBounds(435, 389, 87, 29);
		frame.getContentPane().add(rdbtnDom);
		
		rdbtngreen = new JRadioButton("$GREEN");
		rdbtngreen.setOpaque(false);
		rdbtngreen.setForeground(Color.WHITE);
		rdbtngreen.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtngreen.setBounds(543, 389, 127, 29);
		frame.getContentPane().add(rdbtngreen);
		
		lblCompatibleMode = new JLabel("Compatible Mode: ");
		lblCompatibleMode.setForeground(Color.WHITE);
		lblCompatibleMode.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblCompatibleMode.setBounds(327, 343, 212, 29);
		frame.getContentPane().add(lblCompatibleMode);
		
		lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblType.setBounds(327, 388, 69, 29);
		frame.getContentPane().add(lblType);
		
		lblUnixPath = new JLabel("Unix Path:");
		lblUnixPath.setForeground(Color.WHITE);
		lblUnixPath.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblUnixPath.setBounds(327, 430, 125, 29);
		frame.getContentPane().add(lblUnixPath);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblUsername.setBounds(327, 253, 125, 29);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblPassword.setBounds(327, 298, 112, 29);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(451, 258, 194, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(450, 298, 194, 26);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(445, 435, 259, 26);
		frame.getContentPane().add(textField_2);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(444, 478, 142, 41);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(386, 528, 284, 41);
		frame.getContentPane().add(lblNewLabel_6);
		
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
		
		frame.setVisible(true);
		
		addIcon(lblNewLabel_1,optionsDark);
		addIcon(lblNewLabel_2,dbDark);
		addIcon(lblNewLabel_3,linkDark);
		addIcon(lblNewLabel_4,infoDark);
		addIcon(lblNewLabel_5,upload);
		addIcon(lblNewLabel_6,execute);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 983, 662);
		frame.getContentPane().add(lblNewLabel);
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
}
