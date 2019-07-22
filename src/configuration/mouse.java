package configuration;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.openqa.selenium.WebDriver;

import UD.UdExecution;
import guis.datapopAutomationGui;
import guis.gui;
import guis.guiBase;
import guis.instantInvoiceAutomationGui;
import guis.prerateAutomationGui;
import guis.rebillAutomationGui;
import guis.rerateAutomationGui;
import guis.udAutomation;
public class mouse {
	
	JLabel unixPath;
	JTextField unixPathText;
	
	JLabel menuLabel,databaseLabel,linkLabel,infoLabel,backLabel,background;

	udAutomation ud;
    rebillAutomationGui rebill;
    rerateAutomationGui rerate;
    prerateAutomationGui prerate;
    instantInvoiceAutomationGui instantInvoice;
    datapopAutomationGui datapop;
	 
    

	ArrayList<labelClass> labelClassArray = new ArrayList<labelClass>();
	ArrayList<labelClass> labelClassBase = new ArrayList<labelClass>(); 
	
	
	 	File file;
	    String filePath="";

	    config c;

	    String level,date1,date2;
	    
	    String level1,compatibleMode;
	
     String homePath;
     String imagePath;
	 BufferedImage img = null;
     Image dimg;
     ImageIcon imageIcon;
     BufferedImage mouseImage = null;
     Image mouseDimg = null;
     ImageIcon mouseimageIcon = null;
     JLabel mouseLabel;
     JRadioButton mouseRadioButton;
     String mouseLabelStringName;
     String mouseLabelStringLight;
     String mouseLabelStringDark;
     JLabel lblNewLabel_1;
     JLabel lblNewLabel_2;
     JLabel lblNewLabel_3;
     JLabel lblNewLabel_4;
     String optionsDefault="options.png";
     String optionsAlt="darkoptions.png";
     String dbDefault="db.png";
     String dbAlt="darkdb.png";
     String linkDefault="link.png";
     String linkAlt="darklink.png";
     String infoDefault="info.png";
     String infoAlt="darkinfo.png";
     String backDefault="back.png";
     String backAlt="darkBack.png";
     
     String executePic="execute.png";
     String dbPic="database.png";
     String dbPicSelected="databaseSelected.png";
     String excelPic="excel.png";
     String excelPicSelected="excelSelect.png";
     
     Boolean excelBoolean=false;
     Boolean databaseBoolean=false;
     
     Boolean c1=false,c2=false;
   
    
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
     
     JLabel jLabelExcel,jLabelDatabase;
     

     String backgroundPic="default_template.png";
     
     String libDirectoryDB,libDirectoryExcel,libDirectorySelenium;
     
     String GtmDbName,GtmDbResults,GtmDbPassword,retryAttempts,secondTimeout,rebillL2URL,rebillL3URL;
     String rtmDbName,rtmDbPassword;
     String rerateL2URL,rerateL3URL,prerateL2URL,prerateL3URL;
     String instantInvoiceL2URL,instantInvoiceL3URL;
     String rebillResultTable;
     String rtmBatchShippingResults;
     //Hi aksshay checking github
     
     JFileChooser jFileChooser1 = new javax.swing.JFileChooser();
     
     WebDriver ieDriver;
     WebDriver firefoxDriver;
     WebDriver chromeDriver;
     
     BufferedReader br;
     
     File tempFile,configFile;
     
     MouseListener ml,m2 ;

	public MouseListener m3;
	
	JFrame frame;
	
	guiBase gb;
	gui g;
	Object obj;
	
	
	public mouse(guiBase gb,gui g,config c,Object obj) {
		this.g=g;
		this.gb=gb;
		this.c=c;
		this.obj=obj;
		 
		homePath=System.getProperty("user.dir");
	      if (System.getProperty("user.dir").indexOf("dist")==-1){
	    	  imagePath=System.getProperty("user.dir");
	        }
	      else {
	    	  imagePath=homePath.substring(0,homePath.length()-5);
	        }
	      setupMouseListener();
		
	}
	
	public void setupBaseIcons() {
		
		addIconWithMouse(gb.menuLabel,"menu",gb.menuDefault,gb.menuAlt);
		addIconWithMouse(gb.databaseLabel,"db",gb.dbDefault,gb.dbAlt);
		addIconWithMouse(gb.linkLabel,"link",gb.linkDefault,gb.linkAlt);
		addIconWithMouse(gb.infoLabel,"info",gb.infoDefault,gb.infoAlt);
		addIconWithMouse(gb.backLabel,"back",gb.backDefault,gb.backAlt);	
	}
	
	public void setupBackground() {
		
		addIcon(gb.background,gb.backgroundDefault);
		
	}
	
	
public void setFrame(JFrame frame) {
	
	this.frame=frame;
	
}

public JFrame getFrame() {
	
	return frame;
}
	
public void addIcon(JLabel jlabel,String tempPic) {
		
		try {
		    img = ImageIO.read(new File(imagePath+"\\assets\\"+tempPic));
		    System.out.println(tempPic);
		} catch (IOException e) {
		    
		    e.printStackTrace();
		}
			dimg = img.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(),
		        Image.SCALE_SMOOTH);
		  imageIcon = new ImageIcon(dimg);
		  jlabel.setIcon(imageIcon);
		  
		  frame.getContentPane().add(jlabel);

	}


public void addExcel(JLabel jlabel) {
	jLabelExcel=jlabel;
	try {
		if(excelBoolean==false) {
	
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+excelPic));
		}
		else {
			  img = ImageIO.read(new File(imagePath+"\\assets\\"+excelPicSelected));
		
		}

	} catch (IOException e) {
	    
	    e.printStackTrace();
	}
		dimg = img.getScaledInstance(jLabelExcel.getWidth(), jLabelExcel.getHeight(),
	        Image.SCALE_SMOOTH);
	  imageIcon = new ImageIcon(dimg);
	  jLabelExcel.setIcon(imageIcon);
	  
	  frame.getContentPane().add(jLabelExcel);
	
	  
	  jLabelExcel.getParent(). setComponentZOrder(
			  jLabelExcel, 0);
	  if (c1==false) {
	  jLabelExcel.addMouseListener(m2);
	  c1=true;
	  }
}

public void addDb(JLabel jlabel) {
	jLabelDatabase=jlabel;
	try {
		
		if(databaseBoolean==false) {
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+dbPic));
		}
		else {
			 img = ImageIO.read(new File(imagePath+"\\assets\\"+dbPicSelected));
			
		}
	} catch (IOException e) {
	    
	    e.printStackTrace();
	}
		dimg = img.getScaledInstance(jLabelDatabase.getWidth(), jLabelDatabase.getHeight(),
	        Image.SCALE_SMOOTH);
	  imageIcon = new ImageIcon(dimg);
	  jlabel.setIcon(imageIcon);
	  
	  frame.getContentPane().add(jLabelDatabase);
	 
	  
	  jLabelDatabase.getParent(). setComponentZOrder(
			  jLabelDatabase, 0);
	  
	  
	  if (c2==false) {
	  jLabelDatabase.addMouseListener(m2);
	  c2=true;
	  }
	  
}

public void addExecute(JLabel jlabel) {
	
	try {
	
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+executePic));
		
	

	} catch (IOException e) {
	    
	    e.printStackTrace();
	}
		dimg = img.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(),
	        Image.SCALE_SMOOTH);
	  imageIcon = new ImageIcon(dimg);
	  jlabel.setIcon(imageIcon);
	  
	  frame.getContentPane().add(jlabel);
	  jlabel.addMouseListener(m2);
}



public void addAkshayUDStuff(JLabel unixPath,JTextField unixPathText) {
	
	this.unixPath=unixPath;
	this.unixPathText=unixPathText;
	
	
}
	

public void addRemoveAkshayUDStuff(Boolean addRemove){
	if (addRemove==false){
	unixPath.setVisible(false);
	unixPathText.setVisible(false);
	}
	else if (addRemove==true) {	
		unixPath.setVisible(true);
	unixPathText.setVisible(true);
	}
	
	
	
}



	public void addIconWithMouse(JLabel jl,String name,String defaultPic,String altPic) {
		
		labelClassArray.add(new labelClass(jl,name,defaultPic,altPic));
		addIcon(jl,defaultPic);
		jl.addMouseListener( ml );
	}
	
	
	public config getConfig() {
		
		return c;
	}

	
	
public void setupMouseListener() {
		
		ml = new MouseAdapter()
		{
		            
		    @Override
		    public void mouseEntered(MouseEvent e)
		    {
		       
		        try {
		        	
		        	 mouseLabel = (JLabel)e.getSource();
		        	
			        for (int i=0;i<labelClassArray.size();i++) {
			    		if (labelClassArray.get(i).getName().equals(mouseLabel.getName())) {
			    			 mouseLabelStringDark=labelClassArray.get(i).getDefaultPic();
				        	 mouseLabelStringLight=labelClassArray.get(i).getAltPic();
				        	  mouseImage = ImageIO.read(new File(imagePath+"\\assets\\"+mouseLabelStringLight));
					            mouseDimg = mouseImage.getScaledInstance(mouseLabel.getWidth(), mouseLabel.getHeight(),
					    		        Image.SCALE_SMOOTH);
					    		        mouseimageIcon = new ImageIcon(mouseDimg);
					    		        mouseLabel.setIcon(mouseimageIcon);  
					             } 
			    		}
			        }
		        catch (IOException ee) {
	                //ee.printStackTrace();
	            }
		        	
		          
		      
		    }

		    @Override
		    public void mouseExited(MouseEvent e)
		    {
		      mouseLabel = (JLabel)e.getSource();
		        try {
		            mouseImage = ImageIO.read(new File(imagePath+"\\assets\\"+mouseLabelStringDark));
		            mouseDimg = mouseImage.getScaledInstance(mouseLabel.getWidth(), mouseLabel.getHeight(),
		    		        Image.SCALE_SMOOTH);
		    		        mouseimageIcon = new ImageIcon(mouseDimg);
		    		        mouseLabel.setIcon(mouseimageIcon);  
		             } catch (IOException ee) {
		               // ee.printStackTrace();
		            }
		       
		    }


		
		  @Override
		   public void mouseClicked(MouseEvent e)
		  {
				try {
					
					System.out.println("LABEL NAME : "+mouseLabel.getName());
					
			    		if (mouseLabel.getName().equals("rebill")) {
			    			rebill = new rebillAutomationGui(g,c);
			    		}
			    		if (mouseLabel.getName().equals("rerate")) {
			    			rerate = new rerateAutomationGui(g,c);
			    		}
			    		if (mouseLabel.getName().equals("prerate")) {
			    			prerate = new prerateAutomationGui(g,c);
			    		}
			    		if (mouseLabel.getName().equals("instant")) {
			    			instantInvoice = new instantInvoiceAutomationGui(g,c);
			    		}
			    		if (mouseLabel.getName().equals("ud")) {
			    			ud= new udAutomation(g,c);
			    		}
			    		if (mouseLabel.getName().equals("datapop")) {
			    			datapop = new datapopAutomationGui(g,c);
			    		}
			    		
			    		
			    		
			    		//Excel
			    		if (mouseLabel.getName().equals("excel")) {
			    			System.out.println("Clicked Excel");
			    			  jFileChooser1.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
			    		        int returnVal = jFileChooser1.showOpenDialog(mouseLabel);
			    		        if (returnVal == JFileChooser.APPROVE_OPTION) {
			    		            file = jFileChooser1.getSelectedFile();
			    		            // What to do with the file, e.g. display it in a TextArea
			    		            filePath=file.getAbsolutePath();

			    		           

			    		        } else {
			    		            System.out.println("File access cancelled by user.");
			    		        }        // TODO add your handling code here:
			    			
			    		}
			    		
			    		
			    		
			    		
			    		//Database
			    		if (mouseLabel.getName().equals("db")) {
			    			
	    		            excelBoolean=false;
	    		            addExcel(mouseLabel);
			    		}
			    		
			    		//Execute
			    			if (mouseLabel.getName().equals("execute")) {
			    				
			    				
			    				
			    			}
			    	
			    		
			    		
			    		
			    		
			        }
						 catch (Exception ee) {
							ee.printStackTrace();	
					}
		  		}
			};
		
			
			
			
			
			
			
			
			
			
			
			
			m2 = new MouseAdapter()
			{
				
			    @Override
			    public void mouseEntered(MouseEvent e)
			    {
	
			    }

			    @Override
			    public void mouseExited(MouseEvent e)
			    {
			     
			    
			       
			    }


			
			  @Override
			   public void mouseClicked(MouseEvent e)
			  {
					try {
						mouseLabel = (JLabel)e.getSource();  
						System.out.println("LABEL NAME : "+mouseLabel.getName());
						

				    		
				    		//Excel
				    		if (mouseLabel.getName().equals("excel")) {
				    			System.out.println("Clicked Excel");
				    			  jFileChooser1.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
				    		        int returnVal = jFileChooser1.showOpenDialog(mouseLabel);
				    		        if (returnVal == JFileChooser.APPROVE_OPTION) {
				    		            file = jFileChooser1.getSelectedFile();
				    		            // What to do with the file, e.g. display it in a TextArea
				    		            filePath=file.getAbsolutePath();
				    		            c.setExcelPath(filePath);
				    		            System.out.println("SOURCE!!!!!!!");
				    		            c.setSource(false);
				    		            excelBoolean=true;
				    		            addExcel(mouseLabel);
				    		            databaseBoolean=false;
						    			addDb(jLabelDatabase);
						    			addRemoveAkshayUDStuff(true);
						    			
						    			
						    			

				    		        } else {
				    		            System.out.println("File access cancelled by user.");
				    		        }        // TODO add your handling code here:
				    			
				    		}
				    		
				    		
				    		
				    		
				    		//Database
				    		if (mouseLabel.getName().equals("db")) {
				    			databaseBoolean=true;
				    			addDb(mouseLabel);
		    		            excelBoolean=false;
				    			addExcel(jLabelExcel);
				    			System.out.println("SOURCE!!!!!!!");
				    			c.setSource(true);
				    			addRemoveAkshayUDStuff(false);
				    			
				    		}
				    		
				    		//Execute
				    		
				    		if (mouseLabel.getName().equals("execute")) {
				    			System.out.println(obj.getClass().getCanonicalName());
				    			if (obj.getClass().getCanonicalName().equals("guis.rebillAutomationGui")) {
				    				System.out.println("Booleans "+c.getLevel()+"      "+c.getSource());
				    				if(c.getLevel()!=null && c.getSource()!=null) {
				    					JOptionPane.showMessageDialog(frame, "Started Rebill");
				    					
				    					//rebillMain rm = new rebillMain(c);
				    				}
				    				else {
				    					JOptionPane.showMessageDialog(frame, "Please choose Level and Source");
				    					
				    				}
				    			}
				    			
				    			if (obj.getClass().getCanonicalName().equals("guis.udAutomation")) {
					    			//DO UD STUFF
				    				
				    				
				    				if(c.getLevel()==false)
				    				{
				    					level1="L2";
				    				}else if(c.getLevel()==true)
				    				{
				    					level1="L3";
				    				}
				    				
				    				
				    				if(c.getCompatibleMode())
				    				{
				    					compatibleMode="LOCAL";
				    				}
				    				else {
				    					compatibleMode="RDP";
				    				}
				    				
				    				
				    				UdExecution ud=new UdExecution(level1,c.getType(),c.getUdUsername(),c.getUdPassword(),c.getExcelPath(),compatibleMode,c.getFlavour());
				    				
				    				
				    				
				    				
				    				
					    			}
				    			
				    			if (obj.getClass().getCanonicalName().equals("guis.rerateAutomationGui")) {
				    				JOptionPane.showMessageDialog(frame, "Started Rerates");
					    				}
				    			
				    			
				    		}
				    		
				    		
				    		
				    		
				        }
							 catch (Exception ee) {
								ee.printStackTrace();	
						}
			  		}
				};
				
				
				
				
				m3 = new MouseAdapter()
				{
					
					
				    @Override
				    public void mouseEntered(MouseEvent e)
				    {
		
				    }

				    @Override
				    public void mouseExited(MouseEvent e)
				    {
				     
				    
				       
				    }


				
				  @Override
				   public void mouseClicked(MouseEvent e)
				  {
					  /*
					  mouseRadioButton = (JRadioButton)e.getSource();  
					  
					  if(mouseRadioButton.getName().contentEquals("l2"))
					  
						if (mouseRadioButton.isSelected()==true) {
							System.out.println("TRUE");
							c.setLevel(false);
						}
		*/
				  		}
				  		
					};
			
		
		}
	}

