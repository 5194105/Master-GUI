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

import org.openqa.selenium.WebDriver;

import configuration.config;
import configuration.mouse;

import javax.swing.JTextField;

public class rerateAutomationGui {

	private JFrame frame;

	 

    private JLabel label;
    private JLabel lblSource;
    private JLabel executeLabel;
    private JRadioButton l2RadioButton;
    private JRadioButton l3RadioButton;
    private JLabel label_2;
    private JLabel lblRerateAutomation;
    private JLabel excelLabel;
    private JLabel dbLabel;
    private JLabel browser;
    private JRadioButton ie;
    private JRadioButton chrome;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    public JTextField startDate;
    public JTextField endDate;
    gui g;
    config c;
	/**
	 * Create the application.
	 */
	public rerateAutomationGui( gui g,config c) {
		this.g=g;
		this.c=c;
		initialize();
		
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
	        
	        
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 718);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		excelLabel = new JLabel("New label");
		excelLabel.setBounds(300, 350, 78, 56);
	
		dbLabel = new JLabel("New label");
		dbLabel.setBounds(375, 350, 78, 56);

		executeLabel = new JLabel("New label");
		executeLabel.setBounds(375, 515, 284, 41);
		
		excelLabel.setName("excel");
		dbLabel.setName("db");
		executeLabel.setName("execute");
		
		
		lblRerateAutomation = new JLabel("Rerate Automation");
		lblRerateAutomation.setForeground(Color.WHITE);
		lblRerateAutomation.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lblRerateAutomation.setBounds(353, 196, 386, 64);
		frame.getContentPane().add(lblRerateAutomation);
		frame.getContentPane().setLayout(null);
		
		
		label = new JLabel("Level:");
		label.setBounds(200, 281, 78, 31);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(label);
		
		lblSource = new JLabel("Source:");
		lblSource.setForeground(Color.WHITE);
		lblSource.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSource.setBounds(192, 355, 99, 31);
		frame.getContentPane().add(lblSource);
		
		l2RadioButton = new JRadioButton("L2");
		l2RadioButton.setOpaque(false);
		l2RadioButton.setForeground(Color.WHITE);
		l2RadioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		l2RadioButton.setBounds(275, 281, 69, 29);
		frame.getContentPane().add(l2RadioButton);
		
		l3RadioButton = new JRadioButton("L3");
		l3RadioButton.setOpaque(false);
		l3RadioButton.setForeground(Color.WHITE);
		l3RadioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		l3RadioButton.setBounds(350, 282, 69, 29);
		frame.getContentPane().add(l3RadioButton);
		
		label_2 = new JLabel("Compatible Mode: ");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Segoe UI", Font.BOLD, 23));
		label_2.setBounds(192, 444, 212, 29);
		frame.getContentPane().add(label_2);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setOpaque(false);
		checkBox.setBounds(392, 444, 27, 29);
		frame.getContentPane().add(checkBox);
		
		browser = new JLabel("Browser:");
		browser.setForeground(Color.WHITE);
		browser.setFont(new Font("Segoe UI", Font.BOLD, 23));
		browser.setBounds(501, 444, 212, 29);
		frame.getContentPane().add(browser);
		
		ie = new JRadioButton("IE");
		ie.setOpaque(false);
		ie.setForeground(Color.WHITE);
		ie.setFont(new Font("Segoe UI", Font.BOLD, 22));
		ie.setBounds(612, 444, 69, 29);
		frame.getContentPane().add(ie);
		
		chrome = new JRadioButton("Chrome");
		chrome.setOpaque(false);
		chrome.setForeground(Color.WHITE);
		chrome.setFont(new Font("Segoe UI", Font.BOLD, 22));
		chrome.setBounds(671, 445, 150, 29);
		frame.getContentPane().add(chrome);
		
		startDateLabel = new JLabel("Start Date:");
		startDateLabel.setForeground(Color.WHITE);
		startDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		startDateLabel.setBounds(499, 276, 150, 31);
		frame.getContentPane().add(startDateLabel);
		
		endDateLabel = new JLabel("End Date:");
		endDateLabel.setForeground(Color.WHITE);
		endDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		endDateLabel.setBounds(499, 355, 113, 31);
		frame.getContentPane().add(endDateLabel);
		
		startDate = new JTextField();
		startDate.setBounds(617, 276, 170, 31);
		frame.getContentPane().add(startDate);
		startDate.setColumns(10);
		
		endDate = new JTextField();
		endDate.setColumns(10);
		endDate.setBounds(617, 355, 170, 31);
		frame.getContentPane().add(endDate);
		
		executeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               // System.out.println("Yay you clicked me");
               // System.out.println(startDate.getText());
               // System.out.println(endDate.getText());
            	c.setStartDate(startDate.getText());
        		c.setEndDate(endDate.getText());
        		
            	
            }

        });
		
		guiBase gb = new guiBase();
		mouse m = new mouse(gb,g,c,this,this);
		m.setFrame(frame);
		m.setupBaseIcons();
		m.addExcel(excelLabel);
		m.addDb(dbLabel);
		m.addExecute(executeLabel);
		m.setupBackground();
		
		
		l2RadioButton.addMouseListener(m.m3);
		l3RadioButton.addMouseListener(m.m3);
		
		
		
		//Sets Driver to Chrome
		if (ie.isSelected()==true) {
			c.setDriverType("1");
		}
		if (chrome.isSelected()==true) {
			c.setDriverType("2");
		} 

		
		
		if (checkBox.isSelected()==true) {
			c.setCompatibleMode(true);
		}
		else if (checkBox.isSelected()==false) {
			c.setCompatibleMode(false);
		}

		

	    frame.setVisible(true);
	    
	    
	

		
		
		
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
	    
		ie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ieActionPerformed(evt);
            }
        });
	    
	    chrome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	chromeActionPerformed(evt);
            }
        });
		
	    
	    
		
	}
	
		private void l2RadioButtonActionPerformed(java.awt.event.ActionEvent evt) { 
			System.out.println("L2 RADIO");
			if (l3RadioButton.isSelected()){
				l3RadioButton.setSelected(false);
		        }
			c.setLevel(false);
	}
		private void l3RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {   
			System.out.println("L3 RADIO");
			if (l2RadioButton.isSelected()){
				l2RadioButton.setSelected(false);
		        }
			c.setLevel(true);
		}
	
		
		
	
	
	
		private void ieActionPerformed(java.awt.event.ActionEvent evt) { 
			System.out.println("ie RADIO");
			if (chrome.isSelected()){
				chrome.setSelected(false);
		        }
			c.setDriverType("1");
	}
		private void chromeActionPerformed(java.awt.event.ActionEvent evt) {   
			System.out.println("chrome RADIO");
			if (ie.isSelected()){
				ie.setSelected(false);
		        }
			c.setDriverType("2");
		}

}


	

