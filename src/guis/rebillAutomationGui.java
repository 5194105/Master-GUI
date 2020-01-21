package guis;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openqa.selenium.WebDriver;

import configuration.config;
import configuration.mouse;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class rebillAutomationGui {

	private JFrame frame;

     config c;
   
     private JLabel label;
     private JLabel lblSource;
     private JLabel executeLabel;
     private JRadioButton l2RadioButton;
     private JRadioButton l3RadioButton;
     private JLabel label_2;
     private JLabel lblRebillAutomation;
     private JLabel excelLabel;
     private JLabel dbLabel;
     
     gui g;
     
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

     
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 718);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		executeLabel = new JLabel("New label");
		executeLabel.setBounds(413, 489, 284, 41);
		
		
		excelLabel = new JLabel("New label");
		excelLabel.setBounds(512, 335, 78, 56);
	
		dbLabel = new JLabel("New label");
		dbLabel.setBounds(619, 332, 78, 56);

		
		excelLabel.setName("excel");
		dbLabel.setName("db");
		executeLabel.setName("execute");
		
		
		lblRebillAutomation = new JLabel("Rebill Automation");
		lblRebillAutomation.setForeground(Color.WHITE);
		lblRebillAutomation.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lblRebillAutomation.setBounds(353, 196, 386, 64);
		frame.getContentPane().add(lblRebillAutomation);
		
		label = new JLabel("Level:");
		label.setBounds(413, 281, 78, 31);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(label);
		
		lblSource = new JLabel("Source:");
		lblSource.setForeground(Color.WHITE);
		lblSource.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSource.setBounds(413, 344, 99, 31);
		frame.getContentPane().add(lblSource);
		
		l2RadioButton = new JRadioButton("L2");
		l2RadioButton.setOpaque(false);
		l2RadioButton.setForeground(Color.WHITE);
		l2RadioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		l2RadioButton.setBounds(512, 281, 69, 29);
		l2RadioButton.setName("l2");
		frame.getContentPane().add(l2RadioButton);
		
		
		l3RadioButton = new JRadioButton("L3");
		l3RadioButton.setOpaque(false);
		l3RadioButton.setForeground(Color.WHITE);
		l3RadioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		l3RadioButton.setBounds(588, 282, 69, 29);
		l3RadioButton.setName("l3");
		frame.getContentPane().add(l3RadioButton);
		
		label_2 = new JLabel("Compatible Mode: ");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Segoe UI", Font.BOLD, 23));
		label_2.setBounds(413, 405, 212, 29);
		frame.getContentPane().add(label_2);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(630, 405, 27, 29);
		checkBox.setOpaque(false);
		frame.getContentPane().add(checkBox);
		
	
		
		
		guiBase gb = new guiBase();
		mouse m = new mouse(gb,g,c,this,frame);
		m.setFrame(frame);
		m.setupBaseIcons();
		m.addExcel(excelLabel);
		m.addDb(dbLabel);
		m.addExecute(executeLabel);
		m.setupBackground();
		l2RadioButton.addMouseListener(m.m3);
		l3RadioButton.addMouseListener(m.m3);
		
		//Sets Driver to Chrome
		c.setDriverType("2");
		
		
	
		
		
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
}