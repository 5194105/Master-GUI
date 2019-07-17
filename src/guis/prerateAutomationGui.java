package guis;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.openqa.selenium.WebDriver;

import configuration.config;
import configuration.mouse;

public class prerateAutomationGui {

	private JFrame frame;
	    private JLabel label;
	    private JLabel lblSource;
	    private JLabel executeLabel;
	    private JRadioButton radioButton;
	    private JRadioButton radioButton_1;
	    private JLabel label_2;
	    private JLabel lblRerateAutomation;
	    private JLabel excelLabel;
	    private JLabel dbLabel;
	    private JLabel browser;
	    private JRadioButton ie;
	    private JRadioButton chrome;
	    private JLabel startDateLabel;
	    private JLabel endDateLabel;
	    private JTextField startDate;
	    private JTextField endDate;
	    private JLabel lblPrerate;
	    private JRadioButton rdbtnUpdate;
	    private JRadioButton rdbtnHold;
	    gui g;
	    config c;
	
	public prerateAutomationGui( gui g,config c) {
		this.g=g;
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
		
		
		lblRerateAutomation = new JLabel("Prerate Automation");
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
		
		radioButton = new JRadioButton("L2");
		radioButton.setOpaque(false);
		radioButton.setForeground(Color.WHITE);
		radioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		radioButton.setBounds(275, 281, 69, 29);
		frame.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("L3");
		radioButton_1.setOpaque(false);
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		radioButton_1.setBounds(350, 282, 69, 29);
		frame.getContentPane().add(radioButton_1);
		
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
		browser.setBounds(499, 404, 212, 29);
		frame.getContentPane().add(browser);
		
		ie = new JRadioButton("IE");
		ie.setOpaque(false);
		ie.setForeground(Color.WHITE);
		ie.setFont(new Font("Segoe UI", Font.BOLD, 22));
		ie.setBounds(610, 404, 69, 29);
		frame.getContentPane().add(ie);
		
		chrome = new JRadioButton("Chrome");
		chrome.setOpaque(false);
		chrome.setForeground(Color.WHITE);
		chrome.setFont(new Font("Segoe UI", Font.BOLD, 22));
		chrome.setBounds(669, 405, 150, 29);
		frame.getContentPane().add(chrome);
		
		startDateLabel = new JLabel("Start Date:");
		startDateLabel.setForeground(Color.WHITE);
		startDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		startDateLabel.setBounds(499, 276, 150, 31);
		frame.getContentPane().add(startDateLabel);
		
		endDateLabel = new JLabel("End Date:");
		endDateLabel.setForeground(Color.WHITE);
		endDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		endDateLabel.setBounds(499, 343, 113, 31);
		frame.getContentPane().add(endDateLabel);
		
		startDate = new JTextField();
		startDate.setBounds(617, 276, 170, 31);
		frame.getContentPane().add(startDate);
		startDate.setColumns(10);
		
		endDate = new JTextField();
		endDate.setColumns(10);
		endDate.setBounds(617, 340, 170, 31);
		frame.getContentPane().add(endDate);
		
		lblPrerate = new JLabel("Prerate:");
		lblPrerate.setForeground(Color.WHITE);
		lblPrerate.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblPrerate.setBounds(499, 453, 212, 29);
		frame.getContentPane().add(lblPrerate);
		
		rdbtnUpdate = new JRadioButton("Update");
		rdbtnUpdate.setOpaque(false);
		rdbtnUpdate.setForeground(Color.WHITE);
		rdbtnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnUpdate.setBounds(610, 453, 120, 29);
		frame.getContentPane().add(rdbtnUpdate);
		
		rdbtnHold = new JRadioButton("Hold");
		rdbtnHold.setOpaque(false);
		rdbtnHold.setForeground(Color.WHITE);
		rdbtnHold.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnHold.setBounds(745, 454, 150, 29);
		frame.getContentPane().add(rdbtnHold);
		
		guiBase gb = new guiBase();
		mouse m = new mouse(gb,g,c,this);
		m.setFrame(frame);
		m.setupBaseIcons();
		m.addExcel(excelLabel);
		m.addDb(dbLabel);
		m.addExecute(executeLabel);
		m.setupBackground();

	    frame.setVisible(true);
	}
	
}
