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


public class guiBase {

	
	JLabel menuLabel,databaseLabel,linkLabel,infoLabel,backLabel,background;

	udAutomation ud;
    rebillAutomationGui rebill;
    rerateAutomationGui rerate;
    prerateAutomationGui prerate;
    instantInvoiceAutomationGui instantInvoice;
    datapopAutomationGui datapop;
	 
    

	ArrayList<labelClass> labelClassArray = new ArrayList<labelClass>();
     
     String homePath;
     String imagePath;
	 BufferedImage img = null;
     Image dimg;
     ImageIcon imageIcon;

     JLabel lblNewLabel_1;
     JLabel lblNewLabel_2;
     JLabel lblNewLabel_3;
     JLabel lblNewLabel_4;
     String menuDefault="options.png";
     String menuAlt="darkoptions.png";
     String dbDefault="db.png";
     String dbAlt="darkdb.png";
     String linkDefault="link.png";
     String linkAlt="darklink.png";
     String infoDefault="info.png";
     String infoAlt="darkinfo.png";
     String backDefault="back.png";
     String backAlt="darkBack.png";
     
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

     String backgroundDefault="default_template.png";
     

	

	
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






