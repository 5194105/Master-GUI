package guis;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openqa.selenium.WebDriver;

import configuration.config;
import configuration.mouse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class rerateAutomationGui {

	 JFrame frame;
	  config c;
	   
	     JLabel label;
	     JLabel moreOptionsTextLabel;
	     JLabel lblSource;
	     JLabel executeLabel;
	     JRadioButton l2RadioButton;
	     JRadioButton l3RadioButton;
	     JLabel label_2;
	     JLabel lblRebillAutomation;
	     JLabel excelLabel;
	     JLabel dbLabel;
	     JLabel moreOptionsLabel;
	     JLabel uploadResult;
	     JLabel disableDatabase;
	     JLabel sessionLabel;
	     JLabel browserLabel;
	     JTextField	sessionTextField;
	     JCheckBox compatibleCheckBox;
	     JCheckBox disableDatabaseCheckBox;
	     gui g;
	     JLabel ie,firefox,chrome;
	     JTextField	startDateTextField;
	     JTextField	endDateTextField;
	     JLabel startLabel,endLabel;
	     JRadioButton updateButton;
	     JRadioButton holdButton;
	     
	     mouse m;
	public rerateAutomationGui( gui g,config c) {
		this.g=g;
		this.c=c;
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	       
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate= formatter.format(date);
		System.out.println(strDate);
		
		
        
		c.setCompatibleMode("false");
		c.setDatabaseDisabled("false");
     
		frame = new JFrame();
		frame.setBounds(100, 100, 985, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		
		executeLabel = new JLabel("New label");
		executeLabel.setBounds(275, 550, 175, 41);
		uploadResult = new JLabel("New label");
		uploadResult.setBounds(575, 550, 175, 41);
		
		excelLabel = new JLabel("New label");
		excelLabel.setBounds(335, 340, 78, 56);
	
		dbLabel = new JLabel("New label");
		dbLabel.setBounds(415, 340, 78, 56);
		
		moreOptionsLabel = new JLabel("New label");
		moreOptionsLabel.setBounds(725, 353, 50, 30);
		
		
		ie = new JLabel("New label");
		ie.setBounds(350, 500, 40, 40);
		
		firefox = new JLabel("New label");
		firefox.setBounds(500, 500, 40, 40);
		
		chrome = new JLabel("New label");
		chrome.setBounds(650, 500, 40, 40);
		
		

		
		excelLabel.setName("excel");
		dbLabel.setName("db");
		executeLabel.setName("execute");
		moreOptionsLabel.setName("moreOptions");
		uploadResult.setName("uploadResult");
		
		
		ie.setName("ie");
		firefox.setName("firefox");
		chrome.setName("chrome");
		
		
	
	    
	     
	     
	    
		
	     
	     
		
		lblRebillAutomation = new JLabel("<HTML><U>Prerate Automation<HTML><U>");
		lblRebillAutomation.setForeground(Color.WHITE);
		lblRebillAutomation.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lblRebillAutomation.setBounds(353, 196, 400, 64);
		frame.getContentPane().add(lblRebillAutomation);
		
		label = new JLabel("Level:");
		label.setBounds(250, 281, 78, 31);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(label);
		
		lblSource = new JLabel("Source:");
		lblSource.setForeground(Color.WHITE);
		lblSource.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSource.setBounds(250, 340, 99, 31);
		frame.getContentPane().add(lblSource);
		
		disableDatabase = new JLabel("Disable Database:");
		disableDatabase.setForeground(Color.WHITE);
		disableDatabase.setFont(new Font("Segoe UI", Font.BOLD, 23));
		disableDatabase.setBounds(250, 410, 200, 31);
		frame.getContentPane().add(disableDatabase);
		
	   
		
		browserLabel = new JLabel("Browser:");
		browserLabel.setForeground(Color.WHITE);
		browserLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		browserLabel.setBounds(250, 500, 200, 31);
		frame.getContentPane().add(browserLabel);

		
		disableDatabaseCheckBox = new JCheckBox("");
		disableDatabaseCheckBox.setBounds(460, 415, 27, 29);
		disableDatabaseCheckBox.setOpaque(false);
		frame.getContentPane().add(disableDatabaseCheckBox);
		
		
		 startLabel = new JLabel("Start Date:");
		 startLabel.setBounds(250, 455, 150, 31);
		 startLabel.setForeground(Color.WHITE);
		 startLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		 frame.getContentPane().add(startLabel);
		 

		 endLabel = new JLabel("End Date:");
		 endLabel.setBounds(550, 455, 150, 31);
		 endLabel.setForeground(Color.WHITE);
		 endLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		 frame.getContentPane().add(endLabel);

			
		 startDateTextField= new JTextField(2);
		 startDateTextField.setText("01/01/2000");
		 startDateTextField.setBounds(375, 459, 100, 29);
	     frame.getContentPane().add(startDateTextField);
	     
	     
	     endDateTextField= new JTextField(2);
	     endDateTextField.setText(strDate);
	     endDateTextField.setBounds(660, 459, 100, 29);
	     frame.getContentPane().add(endDateTextField);
	     
	     
	     
	     
		 /*
		  * 
		 updateButton = new JRadioButton("Update");
	     updateButton.setOpaque(false);
	     updateButton.setForeground(Color.WHITE);
	     updateButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
	     updateButton.setBounds(360, 459, 150, 29);
	     updateButton.setName("update");
	     frame.getContentPane().add(updateButton);
		
		
		holdButton = new JRadioButton("Hold");
		holdButton.setOpaque(false);
		holdButton.setForeground(Color.WHITE);
		holdButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		holdButton.setBounds(485, 459, 150, 29);
		holdButton.setName("hold");
		frame.getContentPane().add(holdButton);
		*/
		
		l2RadioButton = new JRadioButton("L2");
		l2RadioButton.setOpaque(false);
		l2RadioButton.setForeground(Color.WHITE);
		l2RadioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		l2RadioButton.setBounds(325, 283, 69, 29);
		l2RadioButton.setName("l2");
		frame.getContentPane().add(l2RadioButton);
		
		
		l3RadioButton = new JRadioButton("L3");
		l3RadioButton.setOpaque(false);
		l3RadioButton.setForeground(Color.WHITE);
		l3RadioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		l3RadioButton.setBounds(390, 283, 69, 29);
		l3RadioButton.setName("l3");
		frame.getContentPane().add(l3RadioButton);
		
		label_2 = new JLabel("Compatible Mode: ");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Segoe UI", Font.BOLD, 23));
		label_2.setBounds(550, 281, 212, 29);
		frame.getContentPane().add(label_2);
		
		moreOptionsTextLabel = new JLabel("Filter Options: ");
		moreOptionsTextLabel.setForeground(Color.WHITE);
		moreOptionsTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		moreOptionsTextLabel.setBounds(550, 350, 212, 29);
		frame.getContentPane().add(moreOptionsTextLabel);
		
	
		sessionLabel = new JLabel("Parallel Sessions:");
		sessionLabel.setForeground(Color.WHITE);
		sessionLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		sessionLabel.setBounds(550, 410,  250, 31);
		frame.getContentPane().add(sessionLabel);
		
		sessionTextField= new JTextField(2);
		sessionTextField.setText("1");
		sessionTextField.setBounds(750, 418, 20, 20);
		frame.getContentPane().add(sessionTextField);
		
		
		
		compatibleCheckBox = new JCheckBox("");
		compatibleCheckBox.setBounds(750, 283, 27, 29);
		compatibleCheckBox.setOpaque(false);
		frame.getContentPane().add(compatibleCheckBox);
		
	
		
		
		guiBase gb = new guiBase();
		mouse m = new mouse(gb,g,c,this,frame);
		m.setFrame(frame);
		m.setupBaseIcons();
		m.addExcel(excelLabel);
		m.addDb(dbLabel);
		m.addExecute(executeLabel);
		m.addMoreOptions(moreOptionsLabel);
		m.adduploadResult(uploadResult);
		m.setupBackground();
		
		m.addIe(ie);
		m.addFirefox(firefox);
		m.addChrome(chrome);
		
		l2RadioButton.addMouseListener(m.m3);
		l3RadioButton.addMouseListener(m.m3);
	
		//Sets Driver to Chrome
		c.setDriverType("2");
	    c.setSessionCount(sessionTextField.getText());
	    
		
		
		    
		
		
	    frame.setVisible(true);
      
	    sessionTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				 c.setSessionCount(sessionTextField.getText());
		    	    System.out.println(c.getSessionCount());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				 c.setSessionCount(sessionTextField.getText());
		    	    System.out.println(c.getSessionCount());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				 c.setSessionCount(sessionTextField.getText());
		    	    System.out.println(c.getSessionCount());
			} 
	    	
	});
	    sessionTextField.addActionListener(new java.awt.event.ActionListener() {
	    	  public void actionPerformed(ActionEvent event) {
	    	    c.setSessionCount(sessionTextField.getText());
	    	    System.out.println(c.getSessionCount());
	    	  }
	    	});
	    
	
	    
	    l2RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	l2RadioButtonActionPerformed(evt);
            }
        });
	    
	    l3RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	l3RadioButtonActionPerformed(evt);
            }
        });
	    
	    compatibleCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	compatibleCheckBoxActionPerformed(evt);
            }
        });
	    
	    disableDatabaseCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	disableDatabaseCheckBoxActionPerformed(evt);
            }
        });
		
	}
	
	
	
	
	private void disableDatabaseCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {    

		if (disableDatabaseCheckBox.isSelected()) {
			c.setDatabaseDisabled("true");
		}
		else {
			c.setDatabaseDisabled("false");
		}
	}
	
	private void compatibleCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {    

		if (compatibleCheckBox.isSelected()) {
			c.setCompatibleMode("true");
		}
		else {
			c.setCompatibleMode("false");
		}

}
	
	
		private void l2RadioButtonActionPerformed(java.awt.event.ActionEvent evt) { 
			System.out.println("L2 RADIO");
			if (l3RadioButton.isSelected()){
				l3RadioButton.setSelected(false);
		        }
			c.setLevel("2");
	}
		private void l3RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {   
			System.out.println("L3 RADIO");
			if (l2RadioButton.isSelected()){
				l2RadioButton.setSelected(false);
		        }
			c.setLevel("3");
		}
		
		
}