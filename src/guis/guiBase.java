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
    eraRerateAutomationGui eraRerate;
	Object obj;
    

    public ArrayList<labelClass> labelClassArray = new ArrayList<labelClass>();
     
	public String homePath;
     public String imagePath;
     public BufferedImage img = null;
     public Image dimg;
     public ImageIcon imageIcon;


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
     

     public  String backgroundDefault="default_template.png";
     


	
	public guiBase() {
		
		 homePath=System.getProperty("user.dir");
	      if (System.getProperty("user.dir").indexOf("dist")==-1){
	    	  imagePath=System.getProperty("user.dir");
	        }
	      else {
	    	  imagePath=homePath.substring(0,homePath.length()-5);
	        }
	      //Y,X,W,H
	    menuLabel = new JLabel("menu");
	  	menuLabel.setBounds(0, 0, 500, 64);
		databaseLabel = new JLabel("db");
		databaseLabel.setBounds(0, 60, 105, 64);
		linkLabel = new JLabel("link");
		linkLabel.setBounds(0, 126, 105, 64);
		infoLabel = new JLabel("info");
		infoLabel.setBounds(0, 192, 105, 64);
		backLabel = new JLabel("back");
		backLabel.setBounds(0, 595, 105, 64);
		background = new JLabel("New label");
		background.setBounds(0, 0, 983, 662);
				
		menuLabel.setName("menu");
		databaseLabel.setName("db");
		linkLabel.setName("link");
		infoLabel.setName("info");
		backLabel.setName("back");
		background.setName("background");
		//this.obj=obj;
      
		}	
	}






