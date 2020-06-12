package configuration;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import UD.UdExecution;
import guis.datapopAutomationGui;
import guis.eraRerateAutomationExtendGui;
import guis.eraRerateAutomationGui;
import guis.gui;
import guis.guiBase;
import guis.instantInvoiceAutomationGui;
import guis.prerateAutomationExtendedGui;
import guis.prerateAutomationGui;
import guis.prsRerateAutomationExtendGui;
import guis.rebillAutomationExtendGUI;
import guis.rebillAutomationGui;
import guis.rerateAutomationGui;
import guis.udAutomation;
import prerate.prerateHoldTestNGSlow;
import prerate.prerateTestNGSlow;
import rebill.rebillMain;
import rebill.testngRebillSlow;
import rebill.testngRebillSlowMfRetire;
import rebill.updateRebillDb;
import rebill_troubleshoot.rebillTroubleshoot;
import rerate.abc;
import rerate.rerateTestNgSlow;
import testng.testingonly;
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
    rebillTroubleshoot rt;
     
	 
    

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
     String moreOptionsPic="moreOptions.png";
     String uploadResultPic="uploadResultPic.png";
     Boolean excelBoolean=false;
     Boolean databaseBoolean=false;
     Boolean ieBoolean=false;
     Boolean firefoxBoolean=false;
     Boolean chromeBoolean=false;
     
     Boolean c1=false,c2=false,b1=false,b2=false,b3=false;;
   
    
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
     String iePic="ie.png";
     String iePicSelected="ieselected.png";
     String firefoxPic="firefox.png";
     String firefoxPicSelected="firefoxselected.png";
     String chromePic="chrome.png";
     String chromePicSelected="chromeselected.png";
   
     
     JLabel jLabelExcel,jLabelDatabase,jLabelIe,jLabelFirefox,jLabelChrome;
     

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
	JFrame currentFrame;
	JFrame infoPane;  
	
	eraRerateAutomationGui eraRerate;
	
    public rerateAutomationGui rag;
	
	public mouse(guiBase gb,gui g,config c,Object obj,JFrame currentFrame) {
		this.g=g;
		this.gb=gb;
		this.c=c;
		this.obj=obj;
		this.currentFrame=currentFrame;
		 
		homePath=System.getProperty("user.dir");
	      if (System.getProperty("user.dir").indexOf("dist")==-1){
	    	  imagePath=System.getProperty("user.dir");
	        }
	      else {
	    	  imagePath=homePath.substring(0,homePath.length()-5);
	        }
	      setupMouseListener();
		
	}
	
	//rerate
	public mouse(guiBase gb,gui g,config c,Object obj,rerateAutomationGui rag) {
		this.g=g;
		this.gb=gb;
		this.c=c;
		this.obj=obj;
		this.rag=rag;
		 
		homePath=System.getProperty("user.dir");
	      if (System.getProperty("user.dir").indexOf("dist")==-1){
	    	  imagePath=System.getProperty("user.dir");
	        }
	      else {
	    	  imagePath=homePath.substring(0,homePath.length()-5);
	        }
	      setupMouseListener();
		
	}
	
	
	public void setupRerate(rerateAutomationGui rag) {
		this.rag=rag;
		
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



public void addFirefox(JLabel jlabel) {
	jLabelFirefox=jlabel;
	try {
		
		if(firefoxBoolean==false) {
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+firefoxPic));
		}
		else {
			 img = ImageIO.read(new File(imagePath+"\\assets\\"+firefoxPicSelected));
			
		}
	} catch (IOException e) {
	    
	    e.printStackTrace();
	}
		dimg = img.getScaledInstance(jLabelFirefox.getWidth(), jLabelFirefox.getHeight(),
	        Image.SCALE_SMOOTH);
	  imageIcon = new ImageIcon(dimg);
	  jlabel.setIcon(imageIcon);
	  
	  frame.getContentPane().add(jLabelFirefox);
	 
	  
	  jLabelFirefox.getParent(). setComponentZOrder(
			  jLabelFirefox, 0);
	  
	  
	  
	  
	  
	  if (b1==false) {
		  jLabelFirefox.addMouseListener(m2);
	  b1=true;
	  
	  }
	  
}

public void addIe(JLabel jlabel) {
	jLabelIe=jlabel;
	try {
		
		if(ieBoolean==false) {
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+iePic));
		}
		else {
			 img = ImageIO.read(new File(imagePath+"\\assets\\"+iePicSelected));
			
		}
	} catch (IOException e) {
	    
	    e.printStackTrace();
	}
		dimg = img.getScaledInstance(jLabelIe.getWidth(), jLabelIe.getHeight(),
	        Image.SCALE_SMOOTH);
	  imageIcon = new ImageIcon(dimg);
	  jlabel.setIcon(imageIcon);
	  
	  frame.getContentPane().add(jLabelIe);
	 
	  
	  jLabelIe.getParent(). setComponentZOrder(
			  jLabelIe, 0);
	  
	  jLabelIe.addMouseListener(m2);
	 
	  if (b2==false) {
		  jLabelIe.addMouseListener(m2);
	  b2=true;
	  
	  }
}

public void addChrome(JLabel jlabel) {
	jLabelChrome=jlabel;
	try {
		
		if(chromeBoolean==false) {
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+chromePic));
		}
		else {
			 img = ImageIO.read(new File(imagePath+"\\assets\\"+chromePicSelected));
			
		}
	} catch (IOException e) {
	    
	    e.printStackTrace();
	}
		dimg = img.getScaledInstance(jLabelChrome.getWidth(), jLabelChrome.getHeight(),
	        Image.SCALE_SMOOTH);
	  imageIcon = new ImageIcon(dimg);
	  jlabel.setIcon(imageIcon);
	  
	  frame.getContentPane().add(jLabelChrome);
	 
	  
	  jLabelChrome.getParent(). setComponentZOrder(
			  jLabelChrome, 0);
	  
	  
	 
	  jLabelChrome.addMouseListener(m2);
	  
	  if (b3==false) {
		  jLabelChrome.addMouseListener(m2);
		  b3=true;
	  
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


public void addMoreOptions(JLabel jlabel) {
	
	try {
	
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+moreOptionsPic));
		
	

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


public void adduploadResult(JLabel jlabel) {
	
	try {
	
	    img = ImageIO.read(new File(imagePath+"\\assets\\"+uploadResultPic));
		
	

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
	try {
	//unixPathText.setText(filePath);
	}
	catch (Exception e) {
		//unixPathText.setText("");
		
	}
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
			    		//if (mouseLabel.getName().equals("datapop")) {
			    			//datapop = new datapopAutomationGui(g,c);
			    		//}
			    		if (mouseLabel.getName().equals("eraRerate")) {
			    			eraRerate = new eraRerateAutomationGui(g,c);
			    		}
			    		
			    		if (mouseLabel.getName().equals("rebillTroubleshoot")) {
			    			JOptionPane.showMessageDialog(frame, "Started");
			    			rt = new rebillTroubleshoot(c);
			    			JOptionPane.showMessageDialog(frame, "Finished");
			    		}
			    		
			    		//Info Label
			    		if (mouseLabel.getName().equals("info")) {		
			    			 infoPane=new JFrame();  
			    			    JOptionPane.showMessageDialog(infoPane,"Hello from GTMC Revenue Execution Onlines Team!");  
			    		}
			    		
  		
			    		//Excel
			    		if (mouseLabel.getName().equals("excel")) {
			    	
			    			
			    		} 		
			    		//Database
			    		if (mouseLabel.getName().equals("db")) {		
	    		            excelBoolean=false;
	    		            addExcel(mouseLabel);
			    		}
			    		
			    		//Execute
			    			if (mouseLabel.getName().equals("execute")) {
			    			}
			    			if (mouseLabel.getName().equals("back")) {
			    				if (!obj.getClass().getCanonicalName().equals("guis.gui")) {
			    					g.frame.setVisible(true);
			    					//currentFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			    					currentFrame.setVisible(false);
			    				}
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
						System.out.println("LABEL NAME HERE: "+mouseLabel.getName());
						

						
						
						
						
						
						
						
						//MoreOptions
						if (mouseLabel.getName().equals("moreOptions")) {
							if (obj.getClass().getCanonicalName().equals("guis.rebillAutomationGui")) {
									rebillAutomationExtendGUI raeg= new rebillAutomationExtendGUI(g,c);
							
							}
							if (obj.getClass().getCanonicalName().equals("guis.prerateAutomationGui")) {
								prerateAutomationExtendedGui raeg= new prerateAutomationExtendedGui(g,c);
						
						}
							
							if (obj.getClass().getCanonicalName().equals("guis.eraRerateAutomationGui")) {
								eraRerateAutomationExtendGui erae= new eraRerateAutomationExtendGui(g,c);
						
						}
							if (obj.getClass().getCanonicalName().equals("guis.rerateAutomationGui")) {
								prsRerateAutomationExtendGui prae= new prsRerateAutomationExtendGui(g,c);
						
						}
							
							}
						
						if (mouseLabel.getName().equals("uploadResult")) {
							if (obj.getClass().getCanonicalName().equals("guis.rebillAutomationGui")) {
								if (c.getLevel()==null) {
									JOptionPane.showMessageDialog(frame, "Must Select A Level");
								}
								else {
									updateRebillDb urd= new updateRebillDb(c);
								}
							}
						}
						
						
						//Save and Close
	
							
						
				    		
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
				    		            c.setSource("excel");
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
				    			c.setSource("db");
				    			//addRemoveAkshayUDStuff(false);
				    			
				    		}
				    		
				    		
				    		
				    		
				    		
				    		if (mouseLabel.getName().equals("ie")) {
				    			ieBoolean=true;
				    			addIe(mouseLabel);
		    		            firefoxBoolean=false;
		    		            chromeBoolean=false;
				    			addChrome(jLabelChrome);
				    			addFirefox(jLabelFirefox);
				    			c.setDriverType("1");
				    			
				    			
				    		}
				    		
				    		if (mouseLabel.getName().equals("firefox")) {
				    			firefoxBoolean=true;
				    			addFirefox(mouseLabel);
		    		            ieBoolean=false;
		    		            chromeBoolean=false;
				    			addChrome(jLabelChrome);
				    			addIe(jLabelIe);
				    			c.setDriverType("2");
				    			
				    			
				    		}
				    		
				    		if (mouseLabel.getName().equals("chrome")) {
				    			chromeBoolean=true;
				    			addChrome(mouseLabel);
		    		            firefoxBoolean=false;
		    		            ieBoolean=false;
				    			addIe(jLabelIe);
				    			addFirefox(jLabelFirefox);
				    			c.setDriverType("3");
				    			
				    			
				    		}
				    		
				    		
				    		
				    		//Execute
				    		
				    		if (mouseLabel.getName().equals("execute")) {
				    			System.out.println(obj.getClass().getCanonicalName());
				    			if (obj.getClass().getCanonicalName().equals("guis.rebillAutomationGui")) {
				    				System.out.println("Booleans "+c.getLevel()+"      "+c.getSource());
				    				if(c.getLevel()!=null && c.getSource()!=null) {
				    					//JOptionPane.showMessageDialog(frame, "Started Rebill");
				    					
				    					String filepath=c.getExcelPath();
					    				String level=c.getLevel();
					    				String browser=c.getDriverType();
					    				String compatibleMode=c.getCompatibleMode();
					    				String source = c.getSource();
					    				String allCheckBox=c.getAllCheckBox();
					    				String nullCheckBox=c.getNullCheckBox();
					    				String failedCheckBox=c.getFailedCheckBox();
					    				String domesticCheckBox=c.getDomesticCheckBox();
					    				String internationalCheckBox=c.getInternationalCheckBox();
					    				String expressCheckBox=c.getExpressCheckBox();
					    				String groundCheckBox=c.getGroundCheckBox();
					    				String normalCheckBox=c.getNormalCheckBox();
					    				String mfRetireCheckBox=c.getMfRetireCheckBox();
					    				String sessionCount=c.getSessionCount();
					    				
					    				System.out.println("filepath "+filepath);
					    				System.out.println("level "+level);
					    				System.out.println("browser "+browser);
					    				System.out.println("compatibleMode "+compatibleMode);
					    				System.out.println("source "+source);
					    				System.out.println("allCheckBox "+allCheckBox);
					    				System.out.println("nullCheckBox "+nullCheckBox);
					    				System.out.println("failedCheckBox "+failedCheckBox);
					    				System.out.println("domesticCheckBox "+domesticCheckBox);
					    				System.out.println("internationalCheckBox "+internationalCheckBox);
					    				System.out.println("expressCheckBox "+expressCheckBox);
					    				System.out.println("groundCheckBox "+groundCheckBox);
					    				System.out.println("normalCheckBox "+normalCheckBox);
					    				System.out.println("mfRetireCheckBox "+mfRetireCheckBox);
					    				System.out.println("sessionCount "+sessionCount);
					    				
					    				
					    				if(filepath==null) {
					    					filepath="";
					    				}
					    				if(level==null) {
					    					level="";
					    				}
					    				if(browser==null) {
					    					browser="";
					    				}
					    				if(compatibleMode==null) {
					    					compatibleMode="";
					    				}
					    				if(allCheckBox==null) {
					    					allCheckBox="";
					    				}
					    				if(nullCheckBox==null) {
					    					nullCheckBox="";
					    				}
					    				if(failedCheckBox==null) {
					    					failedCheckBox="";
					    				}
					    				if(domesticCheckBox==null) {
					    					domesticCheckBox="";
					    				}
					    				if(internationalCheckBox==null) {
					    					internationalCheckBox="";
					    				}
					    				if(expressCheckBox==null) {
					    					expressCheckBox="";
					    				}
					    				if(groundCheckBox==null) {
					    					groundCheckBox="";
					    				}
					    				if(normalCheckBox==null) {
					    					normalCheckBox="";
					    				}
					    				if(mfRetireCheckBox==null) {
					    					mfRetireCheckBox="";
					    				}
					    				if(sessionCount==null) {
					    					sessionCount="";
					    				}
					    				
					    			
					    			

					    				
					    		        XmlSuite xmlSuite = new XmlSuite();
					    		        xmlSuite.setName("Sample_Suite");
					    		        Map<String, String> fieldValues = new HashMap<>();
					    		        fieldValues.put("filepath", filepath);
					    		        fieldValues.put("level", level);
					    		        fieldValues.put("browser", "2");
					    		        fieldValues.put("source", source);
					    		        fieldValues.put("compatibleMode", "");
					    		        fieldValues.put("allCheckBox", allCheckBox);
					    		        fieldValues.put("nullCheckBox", nullCheckBox);
					    		        fieldValues.put("failedCheckBox", failedCheckBox);
					    		        fieldValues.put("domesticCheckBox", domesticCheckBox);
					    		        fieldValues.put("internationalCheckBox", internationalCheckBox);
					    		        fieldValues.put("expressCheckBox", expressCheckBox);
					    		        fieldValues.put("groundCheckBox", groundCheckBox);
					    		        fieldValues.put("normalCheckBox", normalCheckBox);
					    		        fieldValues.put("mfRetireCheckBox",mfRetireCheckBox);
					    		        fieldValues.put("sessionCount",sessionCount);
					    		        
					    		        xmlSuite.setParameters(fieldValues);
					    		        XmlTest xmlTest = new XmlTest(xmlSuite);
					    		        xmlTest.setName("Rebill Test");
					    		        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
					    		        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testngRebillSlowMfRetire.class)));
					    		        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
					    		        TestNG tng = new TestNG();
					    		        tng.setXmlSuites(Collections.singletonList(xmlSuite));
					    		        tng.run();
					    		        
					    		        
					    		        
					    		        
					    		        
					    		        
				    					
				    				}
				    				else {
				    					JOptionPane.showMessageDialog(frame, "Please choose Level and Source");
				    					
				    				}
				    			}
				    			
				    			if (obj.getClass().getCanonicalName().equals("guis.udAutomation")) {
					    			//DO UD STUFF
				    				
				    				
				    				if(c.getLevel().equals("2"))
				    				{
				    					level1="L2";
				    				}else if(c.getLevel().equals("3"))
				    				{
				    					level1="L3";
				    				}
				    				
				    				
				    				if(c.getCompatibleMode().equals("false"))
				    				{
				    					compatibleMode="LOCAL";
				    				}
				    				else {
				    					compatibleMode="RDP";
				    				}
				    				if(c.getSource().equals("")) {
				    					c.setUnixPath("NA");
				    				}else {
				    					c.setUnixPath(unixPathText.getText());
				    				}
				    				
				    				//UdExecution ud=new UdExecution(level1,c.getType(),c.getUnixPath(),c.getUdUsername(),c.getUdPassword(),c.getExcelPath(),compatibleMode,c.getFlavour());
				    				
				    				
				    				
				    				
				    				
					    			}
				    			
				    			if (obj.getClass().getCanonicalName().equals("guis.rerateAutomationGui")) {
				    				c.setSource("excel");
				    				
				    				System.out.println("Booleans "+c.getLevel()+"      "+c.getSource());
				    				
				    				if(c.getLevel()!=null && c.getSource()!=null) {
				    					  XmlSuite xmlSuite = new XmlSuite();
				    					    xmlSuite.setName("Sample_Suite");
				    					    Map<String, String> fieldValues = new HashMap<>();
				    					    fieldValues.put("filepathExcel", c.getExcelPath());
				    					    fieldValues.put("startDateText", c.getStartDate());
				    					    fieldValues.put("endDateText", c.getEndDate());
				    					    fieldValues.put("broswer", c.getDriverType());
				    					    fieldValues.put("compatibleMode", c.getCompatibleMode());
				    					    fieldValues.put("source", c.getSource());
				    					    fieldValues.put("level", c.getLevel());
				    					    fieldValues.put("sessionCount", c.getSessionCount());
				    					    xmlSuite.setParameters(fieldValues);
				    					    XmlTest xmlTest = new XmlTest(xmlSuite);
				    					    xmlTest.setName("Rerate Test");
				    					    //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
				    					    xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(rerateTestNgSlow.class)));
				    					    xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
				    					    TestNG tng = new TestNG();
				    					    tng.setXmlSuites(Collections.singletonList(xmlSuite));
				    					    tng.run();
				    					}
				    				
				    				
					    				}
				    			
				    			
				    			
				    			
				    			
				    			if (obj.getClass().getCanonicalName().equals("guis.instantInvoiceAutomationGui")) {
				    				
				    				System.out.println("STARTED INSTANT INVOICE");

					    				}
				    			
				    			
				    			
				    			
				    			if (obj.getClass().getCanonicalName().equals("guis.prerateAutomationGui")) {
				    				System.out.println("Booleans "+c.getLevel()+"      "+c.getSource());		    				
				    				if(c.getLevel()!=null && c.getSource()!=null) {
				    				JOptionPane.showMessageDialog(frame, "Started Prerate");
				    				String filepath=c.getExcelPath();
				    				String level=c.getLevel();
				    				String browser=c.getDriverType();
				    				String compatibleMode=c.getCompatibleMode();
				    				String source = c.getSource();
				    				String allCheckBox=c.getAllCheckBox();
				    				String nullCheckBox=c.getNullCheckBox();
				    				String failedCheckBox=c.getFailedCheckBox();
				    				String prerateType=c.getPrerateType();
				    				String sessionCount=c.getSessionCount();
				    				
				    				System.out.println("filepath"+" "+filepath);
				    				System.out.println("level"+" "+level);
				    				System.out.println("broswer"+" "+browser);
				    				System.out.println("compatibleMode"+" "+compatibleMode);
				    				System.out.println("source"+" "+source);
				    				System.out.println("allCheckBox"+" "+allCheckBox);
				    				System.out.println("nullCheckBox"+" "+nullCheckBox);
				    				System.out.println("failedCheckBox"+" "+failedCheckBox);
				    				System.out.println("prerateType"+" "+prerateType);
				    				System.out.println("sessionCount"+" "+sessionCount);
				    				
				    				
				    				if(prerateType==null) {
				    					prerateType="";
				    				}
				    				
				    				if(filepath==null) {
				    					filepath="";
				    				}
				    				if(level==null) {
				    					level="";
				    				}
				    				if(browser==null) {
				    					browser="";
				    				}
				    				if(compatibleMode==null) {
				    					compatibleMode="";
				    				}
				    				if(allCheckBox==null) {
				    					allCheckBox="";
				    				}
				    				if(nullCheckBox==null) {
				    					nullCheckBox="";
				    				}
				    				if(failedCheckBox==null) {
				    					failedCheckBox="";
				    				}
				    				if(sessionCount==null) {
				    					sessionCount="1";
				    				}
				    				
				    				
				    		        XmlSuite xmlSuite = new XmlSuite();
				    		        xmlSuite.setName("Sample_Suite");
				    		        Map<String, String> fieldValues = new HashMap<>();
				    		        fieldValues.put("filepath", filepath);
				    		        fieldValues.put("level", level);
				    		        fieldValues.put("browser", browser);
				    		        fieldValues.put("compatibleMode", compatibleMode);
				    		        fieldValues.put("source", source);
				    		        fieldValues.put("allCheckBox", allCheckBox);
				    		        fieldValues.put("nullCheckBox", nullCheckBox);
				    		        fieldValues.put("failedCheckBox", failedCheckBox);
				    		        fieldValues.put("sessionCount", sessionCount);
				    		        xmlSuite.setParameters(fieldValues);
				    		        XmlTest xmlTest = new XmlTest(xmlSuite);
				    		        xmlTest.setName("Prerate Test");
				    		        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
				    		        if (prerateType.equals("update")) {
				    		        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(prerateTestNGSlow.class)));
				    		        }
				    		        else  if (prerateType.equals("hold")) {
					    		        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(prerateHoldTestNGSlow.class)));
					    		        }
				    		        
				    		        xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
				    		        TestNG tng = new TestNG();
				    		        tng.setXmlSuites(Collections.singletonList(xmlSuite));
				    		        tng.run();
					    			
				    				}
				    				
				    				
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

