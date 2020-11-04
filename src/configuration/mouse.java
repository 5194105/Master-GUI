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
import guis.eraCreditAndDebitAutomationGui;
import guis.eraCreditAndDebitExtendedAutomationGui;
import guis.eraRerateAutomationExtendGui;
import guis.eraRerateAutomationGui;
import guis.gui;
import guis.guiBase;
import guis.instantInvoiceAutomationExtendGui;
import guis.instantInvoiceAutomationGui;
import guis.prerateAutomationExtendedGui;
import guis.prerateAutomationGui;
import guis.prsRerateAutomationExtendGui;
import guis.rebillAutomationExtendGUI;
import guis.rebillAutomationGui;
import guis.rerateAutomationGui;
import guis.udAutomation;
import instant_invoice.testNgSlowInstantInvoice;
import prerate.prerateHoldTestNGSlow;
import prerate.prerateTestNGSlow;
import rebill.rebillMain;
import rebill.testngRebillFast;
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
	
    rerateAutomationGui rag;
    eraCreditAndDebitAutomationGui creditAndDebit;
    
    
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
		
		addIconWithMouse(gb.menuLabel,"menu",gb.menuDefault,gb.menuAlt,"",true);
		addIconWithMouse(gb.databaseLabel,"db",gb.dbDefault,gb.dbAlt,"",true);
		addIconWithMouse(gb.linkLabel,"link",gb.linkDefault,gb.linkAlt,"",true);
		addIconWithMouse(gb.infoLabel,"info",gb.infoDefault,gb.infoAlt,"",true);
		addIconWithMouse(gb.backLabel,"back",gb.backDefault,gb.backAlt,"",true);	
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
	if(c.getAdmin()==true) {
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
	else {
		JOptionPane.showMessageDialog(frame, "You Dont Have Admin Access. Certain Functionality Have Been Disabled.");
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



	public void addIconWithMouse(JLabel jl,String name,String defaultPic,String altPic,String disabledPic,Boolean enabled) {
		
		labelClassArray.add(new labelClass(jl,name,defaultPic,altPic,disabledPic,enabled));
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
				        	if (labelClassArray.get(i).getEnabled()==true) {
			    			 mouseLabelStringLight=labelClassArray.get(i).getAltPic();
				        	}
				        	else {
				        		mouseLabelStringLight=labelClassArray.get(i).getDisabledPic();
				        	}
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
			    			if(c.getRebillEnabled()==true) {
			    			rebill = new rebillAutomationGui(g,c);
			    			}
			    			else {
			    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
			    			}
			    		}
			    		if (mouseLabel.getName().equals("rerate")) {
			    			if(c.getRebillEnabled()==true) {
			    			rerate = new rerateAutomationGui(g,c);
			    		}
		    			else {
		    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
		    			}
			    		}
			    		if (mouseLabel.getName().equals("prerate")) {
			    			if(c.getPrerateEnabled()==true) {
			    			prerate = new prerateAutomationGui(g,c);
			    		}
		    			else {
		    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
		    			}
			    		}
			    		if (mouseLabel.getName().equals("instant")) {
			    			if(c.getInstantInvoiceEnabled()==true) {
			    			instantInvoice = new instantInvoiceAutomationGui(g,c);
			    		}
		    			else {
		    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
		    			}
			    		}
			    		if (mouseLabel.getName().equals("ud")) {
			    			if(c.getUdEnabled()==true) {
			    			ud= new udAutomation(g,c);
			    		}
		    			else {
		    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
		    			}
			    		}
			    		//if (mouseLabel.getName().equals("datapop")) {
			    			//datapop = new datapopAutomationGui(g,c);
			    		//}
			    		if (mouseLabel.getName().equals("eraRerate")) {
			    			if(c.getEraRerateEnabled()==true) {
			    			eraRerate = new eraRerateAutomationGui(g,c);
			    		}
		    			else {
		    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
		    			}
			    		}
			    		
			    		
			    		if (mouseLabel.getName().equals("creditAndDebit")) {
			    			if(c.getCreditAndDebitEnabled()==true) {
			    			creditAndDebit = new eraCreditAndDebitAutomationGui(g,c);
			    		}
		    			else {
		    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
		    			}
			    		}
			    		
			    		
			    		
			    		
			    		
			    		if (mouseLabel.getName().equals("rebillTroubleshoot")) {
			    			if(c.getRebillTroubleshootEnabled()==true) {
			    			JOptionPane.showMessageDialog(frame, "Started");
			    			rt = new rebillTroubleshoot(c);
			    			JOptionPane.showMessageDialog(frame, "Finished");
			    		}
			    			else {
			    				JOptionPane.showMessageDialog(frame, "You Do Not Have Access To This Section");
			    			}
			    		}
			    			
			    			
			    			
			    			
			    			
			    			
			    			
			    			
			    			
			    			
			    		
			    		//Info Label
			    		if (mouseLabel.getName().equals("info")) {		
			    			 infoPane=new JFrame();  
			    			 if (JOptionPane.showConfirmDialog(null, "Do You Want To Check For An Update?", "WARNING",
			    				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    				 updateVersion uv= new updateVersion(c);
			    				    // yes option
			    				} else {
			    				    // no option
			    				}
			    		}
			    		
			    		//Info Label
			    		if (mouseLabel.getName().equals("link")) {		
			    			// infoPane=new JFrame();  
			    			 //   JOptionPane.showMessageDialog(infoPane,"Hello from GTMC Revenue Execution Onlines Team!");  
			    			homePath=System.getProperty("user.dir");
			    			ProcessBuilder pb = new ProcessBuilder("Notepad.exe", homePath+"\\config.txt");
			    			pb.start();
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
							
							if (obj.getClass().getCanonicalName().equals("guis.eraCreditAndDebitAutomationGui")) {
								eraCreditAndDebitExtendedAutomationGui ecad= new eraCreditAndDebitExtendedAutomationGui(g,c);
						
						}
							
							if (obj.getClass().getCanonicalName().equals("guis.instantInvoiceAutomationGui")) {
								instantInvoiceAutomationExtendGui iieag= new instantInvoiceAutomationExtendGui(g,c);
						
						}
							
							
							
							}
						
						if (mouseLabel.getName().equals("uploadResult")) {
							if (obj.getClass().getCanonicalName().equals("guis.rebillAutomationGui")) {
								if (c.getLevel()==null) {
									JOptionPane.showMessageDialog(frame, "Must Select A Level");
								}
								else {
									updateRebillDb urd= new updateRebillDb(c,obj);
									Thread t1 = new Thread(urd);
									t1.start();
								
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
				    			c.setDriverType("3");
				    			
				    			
				    		}
				    		
				    		if (mouseLabel.getName().equals("chrome")) {
				    			chromeBoolean=true;
				    			addChrome(mouseLabel);
		    		            firefoxBoolean=false;
		    		            ieBoolean=false;
				    			addIe(jLabelIe);
				    			addFirefox(jLabelFirefox);
				    			c.setDriverType("2");
				    			
				    			
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
				    				//	String normalCheckBox=c.getNormalCheckBox();
				    				//	String mfRetireCheckBox=c.getMfRetireCheckBox();
				    					String sessionCount=c.getSessionCount();
				    					
				    				String databaseDisabled=	c.getDatabaseDisabled();
				    				String	customCheckBox= c.getCustomCheckBox();
				    				String	customString= c.getCustomString();
				    				String headless=c.getHeadlessString();
				    				String eraWorkable=c.getEraWorkable();
				    				
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
				    			
				    				System.out.println("sessionCount "+sessionCount);
				    				System.out.println("customString "+customString);
				    				System.out.println("customCheckBox "+customCheckBox);
				    				System.out.println("databaseDisabled "+databaseDisabled);
				    				System.out.println("headless "+headless);
				    				System.out.println("eraWorkable "+eraWorkable);
				    				
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
				    				/*
				    				if(normalCheckBox==null) {
				    					normalCheckBox="";
				    				}
				    				if(mfRetireCheckBox==null) {
				    					mfRetireCheckBox="";
				    				}
				    				*/
				    				if(sessionCount==null) {
				    					sessionCount="";
				    				}
				    				
				    				
				    				if(customString==null) {
				    					customString="";
				    				}
				    				if(customCheckBox==null) {
				    					customCheckBox="";
				    				}
				    				if(databaseDisabled==null) {
				    					databaseDisabled="";
				    				}
				    			
				    				if(headless==null) {
				    					headless="";
				    				}
				    				if(eraWorkable==null) {
				    					eraWorkable="false";
				    				}
				    				
				    		/*
				    				@Parameters({"filepath","level","browser","compatibleMode","source","allCheckBox","nullCheckBox",
				    				"failedCheckBox","domesticCheckBox","internationalCheckBox","expressCheckBox","groundCheckBox",
				    				"sessionCount","customString","customCheckBox","databaseDisabled"})
				    			
				    				public void setupExcel(String filepath,String level,String browser,String compatibleMode,String source,String allCheckBox,String nullCheckBox,
				    				String failedCheckBox,String domesticCheckBox,String internationalCheckBox,String expressCheckBox,String groundCheckBox,
				    				String sessionCount,String customString,String customCheckBox,String databaseDisabled) {
				    				
				    					*/
				    		        XmlSuite xmlSuite = new XmlSuite();
				    		        xmlSuite.setName("Sample_Suite");
				    		        Map<String, String> fieldValues = new HashMap<>();
				    		        fieldValues.put("filepath", filepath);
				    		        fieldValues.put("level", level);
				    		        fieldValues.put("browser", "2");
				    		        fieldValues.put("compatibleMode", "");
				    		        fieldValues.put("source", source);
				    		        fieldValues.put("allCheckBox", allCheckBox);
				    		        fieldValues.put("nullCheckBox", nullCheckBox);
				    		        fieldValues.put("failedCheckBox", failedCheckBox);
				    		        fieldValues.put("domesticCheckBox", domesticCheckBox);
				    		        fieldValues.put("internationalCheckBox", internationalCheckBox);
				    		        fieldValues.put("expressCheckBox", expressCheckBox);
				    		        fieldValues.put("groundCheckBox", groundCheckBox);
				    		        fieldValues.put("sessionCount",sessionCount);
				    		        fieldValues.put("customString",customString);
				    		        fieldValues.put("customCheckBox",customCheckBox);
				    		        fieldValues.put("databaseDisabled",databaseDisabled);
				    		        fieldValues.put("headless",headless);
				    		        fieldValues.put("eraWorkable",eraWorkable);
				    		        xmlSuite.setParameters(fieldValues);
				    		        XmlTest xmlTest = new XmlTest(xmlSuite);
				    		        xmlTest.setName("Rebill Test");
				    		        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
				    		        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testngRebillFast.class)));
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
				    			
				    				
				    				System.out.println("Booleans "+c.getLevel()+"      "+c.getSource());
//c.setSource("excel");
				    				
				    				System.out.println("Booleans "+c.getLevel()+"      "+c.getSource());
				    				
				    				if(c.getLevel()!=null && c.getSource()!=null) {

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
				    					String startDateText=c.getStartDate();
				    					String endDateText=c.getEndDate();
				    					String databaseDisabled=c.getDatabaseDisabled();
				    					
				    					 String ed1=c.getEd1String();
				    					 String ed2=c.getEd2String();
				    					 String ei1=c.getEi1String();
				    					 String ei2=c.getEi2String();
				    					 String gd1=c.getGd1String();
				    					 String gd2=c.getGd2String();
				    					 String gi1=c.getGi1String();
				    					 String gi2=c.getGi2String();
				    					 String nt1=c.getNt1String();
				    					 String nt2=c.getNt2String();
				    					 String sp1=c.getSp1String();
				    					 String sp2=c.getSp2String();
				    						
				    					
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
				    					System.out.println("startDateText "+startDateText);
				    					System.out.println("endDate "+endDateText);
				    					System.out.println("databaseDisabled "+databaseDisabled);

				    					
				    					System.out.println("ed1 "+ed1);
				    					System.out.println("ei1 "+ei1);
				    					System.out.println("gd1 "+gd1);
				    					System.out.println("gi1 "+gi1);
				    					System.out.println("nt1 "+nt1);
				    					System.out.println("sp1 "+sp1);
				    					
				    					System.out.println("ed2 "+ed2);
				    					System.out.println("ei2 "+ei2);
				    					System.out.println("gd2 "+gd2);
				    					System.out.println("gi2 "+gi2);
				    					System.out.println("nt2 "+nt2);
				    					System.out.println("sp2 "+sp2);
				    					
				    					
				    					if(startDateText==null) {
				    						startDateText="";
				    					}
				    					if(endDateText==null) {
				    						endDateText="";
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
				    					
				    					if(ed1==null) {
				    						ed1="";
				    					}
				    					if(ei1==null) {
				    						ei1="";
				    					}
				    					if(gd1==null) {
				    						gd1="";
				    					}
				    					if(gi1==null) {
				    						gi1="";
				    					}
				    					if(nt1==null) {
				    						nt1="";
				    					}
				    					if(sp1==null) {
				    						sp1="";
				    					}
				    					
				    					if(ed2==null) {
				    						ed2="";
				    					}
				    					if(ei2==null) {
				    						ei2="";
				    					}
				    					if(gd2==null) {
				    						gd2="";
				    					}
				    					if(gi2==null) {
				    						gi2="";
				    					}
				    					if(nt2==null) {
				    						nt2="";
				    					}
				    					if(sp2==null) {
				    						sp2="";
				    					}
				    					

				    					if(databaseDisabled==null) {
				    						databaseDisabled="";
				    					}
				    					
				    					
				    					
				    					
				    					
				    					/*
				    					@Parameters({
				    						"filepath",
				    						"level",
				    						"browser"
				    						,"source",
				    						"compatibleMode"
				    						,"allCheckBox"
				    						,"nullCheckBox",
				    						"failedCheckBox",
				    						"sessionCount",
				    						"databaseDisabled"
				    						,"startDateText",
				    						"endDateText",
				    						"ed1"
				    						,"ei1"
				    						,"gd1"
				    						,"gi1"
				    						,"nt1"
				    						,"sp1"
				    						,"ed2"
				    						,"ei2"
				    						,"gd2"
				    						,"gi2"
				    						,"nt2"
				    						,"sp2"})
				    					}
				    					public void setupExcel(
				    							String filepath,
				    							String level,
				    							String browser,
				    							String source,
				    							String compatibleMode
				    							,String allCheckBox,
				    							String nullCheckBox,
				    							String failedCheckBox,
				    					
				    							String sessionCount,
				    							String databaseDisabled,
				    							String startDateText,
				    							String endDateText,
				    							String ed1, 
				    							String ei1,
				    							String gd1,
				    							String gi1,
				    							String nt1,
				    							String sp1,
				    							String ed2,
				    							String ei2,
				    							String gd2,
				    							String gi2,
				    							String nt2, 
				    							String sp2) {
				    					
				    					*/
				    					
				    				    XmlSuite xmlSuite = new XmlSuite();
				    				    xmlSuite.setName("Sample_Suite");
				    				    Map<String, String> fieldValues = new HashMap<>();
				    				    fieldValues.put("filepath", filepath);
				    				    fieldValues.put("level", level);
				    				    fieldValues.put("browser", browser);
				    				    fieldValues.put("source", source);
				    				    fieldValues.put("compatibleMode", compatibleMode);
				    				    fieldValues.put("allCheckBox", allCheckBox);
				    				    fieldValues.put("nullCheckBox", nullCheckBox);
				    				    fieldValues.put("failedCheckBox", failedCheckBox);
				    				    fieldValues.put("sessionCount",sessionCount);
				    				    fieldValues.put("databaseDisabled",databaseDisabled);
				    				    fieldValues.put("startDateText", startDateText);
				    				    fieldValues.put("endDateText",endDateText);
				    				    
				    				    fieldValues.put("ed1",ed1);
				    				    fieldValues.put("ei1",ei1);
				    				    fieldValues.put("gd1",gd1);
				    				    fieldValues.put("gi1",gi1);
				    				    fieldValues.put("nt1",nt1);
				    				    fieldValues.put("sp1",sp1);
				    				  
				    				    fieldValues.put("ed2",ed2);
				    				    fieldValues.put("ei2",ei2);
				    				    fieldValues.put("gd2",gd2);
				    				    fieldValues.put("gi2",gi2);
				    				    fieldValues.put("nt2",nt2);
				    				    fieldValues.put("sp2",sp2);
				    				    
				    				    xmlSuite.setParameters(fieldValues);
				    				    XmlTest xmlTest = new XmlTest(xmlSuite);
				    				    xmlTest.setName("Rebill Test");
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

				    				String sessionCount=c.getSessionCount();
				    				
				    			String databaseDisabled=	c.getDatabaseDisabled();
				    			String	customCheckBox= c.getCustomCheckBox();
				    			String	customString= c.getCustomString();
				    				

				    			
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

				    			System.out.println("sessionCount "+sessionCount);
				    			System.out.println("customString "+customString);
				    			System.out.println("customCheckBox "+customCheckBox);
				    			System.out.println("databaseDisabled "+databaseDisabled);
				    			
				    			
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

				    			if(sessionCount==null) {
				    				sessionCount="";
				    			}
				    			
				    			
				    			if(customString==null) {
				    				customString="";
				    			}
				    			if(customCheckBox==null) {
				    				customCheckBox="";
				    			}
				    			if(databaseDisabled==null) {
				    				databaseDisabled="";
				    			}

				    		/*
				    			@Parameters({
				    				"filepath",
				    				"level",
				    				"browser",
				    				"compatibleMode",
				    				"source",
				    				"allCheckBox",
				    				"nullCheckBox",
				    				"failedCheckBox",
				    				"domesticCheckBox",
				    				"internationalCheckBox",
				    				"expressCheckBox",
				    				"groundCheckBox",
				    				"sessionCount",
				    				"customString",
				    				"customCheckBox",
				    				"databaseDisabled"})
				    			}
				    			public void setupExcel(
				    					String filepath,
				    					String level,
				    					String browser,
				    					String compatibleMode,
				    					String source,
				    					String allCheckBox,
				    					String nullCheckBox,
				    					String failedCheckBox,
				    					String domesticCheckBox,
				    					String internationalCheckBox,
				    					String expressCheckBox,
				    					String groundCheckBox,
				    					String sessionCount,
				    					String customString,
				    					String customCheckBox,
				    					String databaseDisabled) {
				    			
				    				*/
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
				    		   
				    		    fieldValues.put("sessionCount",sessionCount);
				    		    fieldValues.put("customString",customString);
				    		    fieldValues.put("customCheckBox",customCheckBox);
				    		    fieldValues.put("databaseDisabled",databaseDisabled);
				    		    
				    		    xmlSuite.setParameters(fieldValues);
				    		    XmlTest xmlTest = new XmlTest(xmlSuite);
				    		    xmlTest.setName("Instant Invoice Test");
				    		   
				    		 
				    		    xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(testNgSlowInstantInvoice.class)));
				    		  
				    		    xmlTest.setParallel(XmlSuite.ParallelMode.METHODS);
				    		    TestNG tng = new TestNG();
				    		    tng.setXmlSuites(Collections.singletonList(xmlSuite));
				    		    tng.run();
					    				}
				    			
				    			
				    			
				    			
				    			if (obj.getClass().getCanonicalName().equals("guis.prerateAutomationGui")) {
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
				    			
				    					String sessionCount=c.getSessionCount();
				    					
				    				String databaseDisabled=	c.getDatabaseDisabled();
				    				String	customCheckBox= c.getCustomCheckBox();
				    				String	customString= c.getCustomString();
				    					
				    		
				    				
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
				    			
				    				System.out.println("sessionCount "+sessionCount);
				    				System.out.println("customString "+customString);
				    				System.out.println("customCheckBox "+customCheckBox);
				    				System.out.println("databaseDisabled "+databaseDisabled);
				    				
				    				
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
				    			
				    				if(sessionCount==null) {
				    					sessionCount="";
				    				}
				    				
				    				
				    				if(customString==null) {
				    					customString="";
				    				}
				    				if(customCheckBox==null) {
				    					customCheckBox="";
				    				}
				    				if(databaseDisabled==null) {
				    					databaseDisabled="";
				    				}
				    			
				    			/*
				    				@Parameters({
				    					"filepath",
				    					"level",
				    					"browser",
				    					"compatibleMode",
				    					"source",
				    					"allCheckBox",
				    					"nullCheckBox",
				    					"failedCheckBox",
				    					"domesticCheckBox",
				    					"internationalCheckBox",
				    					"expressCheckBox",
				    					"groundCheckBox",
				    					"sessionCount",
				    					"customString",
				    					"customCheckBox",
				    					"databaseDisabled"})
				    				}
				    				public void setupExcel(
				    						String filepath,
				    						String level,
				    						String browser,
				    						String compatibleMode,
				    						String source,
				    						String allCheckBox,
				    						String nullCheckBox,
				    						String failedCheckBox,
				    						String domesticCheckBox,
				    						String internationalCheckBox,
				    						String expressCheckBox,
				    						String groundCheckBox,
				    						String sessionCount,
				    						String customString,
				    						String customCheckBox,
				    						String databaseDisabled) {
				    				
				    					*/
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
				    		        fieldValues.put("domesticCheckBox", domesticCheckBox);
				    		        fieldValues.put("internationalCheckBox", internationalCheckBox);
				    		        fieldValues.put("expressCheckBox", expressCheckBox);
				    		        fieldValues.put("groundCheckBox", groundCheckBox);
				    		        fieldValues.put("sessionCount",sessionCount);
				    		        fieldValues.put("customString",customString);
				    		        fieldValues.put("customCheckBox",customCheckBox);
				    		        fieldValues.put("databaseDisabled",databaseDisabled);
				    		        
				    		        xmlSuite.setParameters(fieldValues);
				    		        XmlTest xmlTest = new XmlTest(xmlSuite);
				    		        xmlTest.setName("Rebill Test");
				    		        //xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(playAround.class)));
				    		        if (c.getPrerateType().equals("update")) {
				    		        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(prerateTestNGSlow.class)));
				    		        }
				    		        else if (c.getPrerateType().equals("hold")) {
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

