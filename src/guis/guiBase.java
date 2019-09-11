package guis;
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
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openqa.selenium.WebDriver;

import configuration.labelClass;


public class guiBase {

	
	public JLabel menuLabel;

	public JLabel databaseLabel;

	public JLabel linkLabel;

	public JLabel infoLabel;

	public JLabel backLabel;

	public JLabel background;

	udAutomation ud;
    rebillAutomationGui rebill;
    rerateAutomationGui rerate;
    prerateAutomationGui prerate;
    instantInvoiceAutomationGui instantInvoice;
    datapopAutomationGui datapop;
	 
    

    public ArrayList<labelClass> labelClassArray = new ArrayList<labelClass>();
     
	public String homePath;
     public String imagePath;
     public BufferedImage img = null;
     public Image dimg;
     public ImageIcon imageIcon;

     public  JLabel lblNewLabel_1;
     public  JLabel lblNewLabel_2;
     public  JLabel lblNewLabel_3;
     public  JLabel lblNewLabel_4;
     public String menuDefault="options.png";
     public String menuAlt="darkoptions.png";
     public String dbDefault="db.png";
     public String dbAlt="darkdb.png";
     public String linkDefault="link.png";
     public  String linkAlt="darklink.png";
     public  String infoDefault="info.png";
     public  String infoAlt="darkinfo.png";
     public  String backDefault="back.png";
     public  String backAlt="darkBack.png";
     
     public String selectionBar="selectionbar.png";
     public String rebillPic="rebill.png";
     public String reratePic="rerate.png";
     public  String instantPic="instant.png";
     public String datapopPic="datapop.png";
     public String udPic="ud.png";
     public String preratePic="prerate.png";
     public String rebillPicHighlight="rebillHighlight.png";
     public String reratePicHighlight="rerateHighlight.png";
     public String instantPicHighlight="instantHighlight.png";
     public String datapopPicHighlight="datapopHighlight.png";
     public String udPicHighlight="udHighlight.png";
     public String preratePicHighlight="prerateHighlight.png";

     public  String backgroundDefault="default_template.png";
     
     public String rebillTroubleshootPic="rebillTroubleshootPic.png";
     public String rebillTroubleshootPicHighlight="rebillTroubleshootPicHighlight.png";

	

	
	public guiBase() {
		
		 homePath=System.getProperty("user.dir");
	      if (System.getProperty("user.dir").indexOf("dist")==-1){
	    	  imagePath=System.getProperty("user.dir");
	        }
	      else {
	    	  imagePath=homePath.substring(0,homePath.length()-5);
	        }
	      
	    menuLabel = new JLabel("menu");
	  	menuLabel.setBounds(0, 0, 112, 64);
		databaseLabel = new JLabel("db");
		databaseLabel.setBounds(0, 67, 112, 64);
		linkLabel = new JLabel("link");
		linkLabel.setBounds(0, 135, 112, 69);
		infoLabel = new JLabel("info");
		infoLabel.setBounds(0, 203, 112, 75);
		backLabel = new JLabel("back");
		backLabel.setBounds(0, 580, 99, 75);
		background = new JLabel("New label");
		background.setBounds(0, 0, 983, 662);
				
		menuLabel.setName("menu");
		databaseLabel.setName("db");
		linkLabel.setName("link");
		infoLabel.setName("info");
		backLabel.setName("back");
		background.setName("background");
      
		}	
	}






