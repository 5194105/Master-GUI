package guis;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openqa.selenium.WebDriver;

import configuration.config;
import configuration.mouse;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class rebillAutomationGui {

	private JFrame frame;

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
     
     mouse m;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public rebillAutomationGui(gui g,config c) {
		this.g=g;
		this.c=c;
		g.frame.setVisible(false);
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		c.setCompatibleMode("false");
		c.setDatabaseDisabled("false");
        
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 718);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		
		executeLabel = new JLabel("New label");
		executeLabel.setBounds(275, 530, 175, 41);
		uploadResult = new JLabel("New label");
		uploadResult.setBounds(575, 530, 175, 41);
		
		excelLabel = new JLabel("New label");
		excelLabel.setBounds(335, 340, 78, 56);
	
		dbLabel = new JLabel("New label");
		dbLabel.setBounds(415, 340, 78, 56);
		
		moreOptionsLabel = new JLabel("New label");
		moreOptionsLabel.setBounds(725, 353, 50, 30);
		
		
		ie = new JLabel("New label");
		ie.setBounds(350, 470, 40, 40);
		
		firefox = new JLabel("New label");
		firefox.setBounds(500, 470, 40, 40);
		
		chrome = new JLabel("New label");
		chrome.setBounds(650, 470, 40, 40);
		
		

		
		excelLabel.setName("excel");
		dbLabel.setName("db");
		executeLabel.setName("execute");
		moreOptionsLabel.setName("moreOptions");
		uploadResult.setName("uploadResult");
		
		
		ie.setName("ie");
		firefox.setName("firefox");
		chrome.setName("chrome");
		
		
		
		
		
		lblRebillAutomation = new JLabel("<HTML><U>Rebill Automation<HTML><U>");
		lblRebillAutomation.setForeground(Color.WHITE);
		lblRebillAutomation.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lblRebillAutomation.setBounds(353, 196, 386, 64);
		frame.getContentPane().add(lblRebillAutomation);
		
		label = new JLabel("Level:");
		label.setBounds(250, 281, 78, 31);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(label);
		
		lblSource = new JLabel("Source:");
		lblSource.setForeground(Color.WHITE);
		lblSource.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSource.setBounds(250, 350, 99, 31);
		frame.getContentPane().add(lblSource);
		
		disableDatabase = new JLabel("Disable Database:");
		disableDatabase.setForeground(Color.WHITE);
		disableDatabase.setFont(new Font("Segoe UI", Font.BOLD, 23));
		disableDatabase.setBounds(250, 420, 200, 31);
		frame.getContentPane().add(disableDatabase);
		
		browserLabel = new JLabel("Browser:");
		browserLabel.setForeground(Color.WHITE);
		browserLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		browserLabel.setBounds(250, 475, 200, 31);
		frame.getContentPane().add(browserLabel);
		
		disableDatabaseCheckBox = new JCheckBox("");
		disableDatabaseCheckBox.setBounds(450, 422, 27, 29);
		disableDatabaseCheckBox.setOpaque(false);
		frame.getContentPane().add(disableDatabaseCheckBox);
		
		
		
		
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
		sessionLabel.setBounds(550, 420,  250, 31);
		frame.getContentPane().add(sessionLabel);
		
		sessionTextField= new JTextField(2);
		sessionTextField.setText("1");
		sessionTextField.setBounds(750, 428, 20, 20);
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