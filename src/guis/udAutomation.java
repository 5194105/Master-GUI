package guis;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import configuration.config;
import configuration.mouse;

public class udAutomation {

	
	JFrame frame;
	private JLabel lblLevel;
	private JRadioButton rdbtnL_1;
	private JLabel lblUsername;
	private JLabel lblCompatibleMode;
	private JLabel lblType;
	private JLabel lblUnixPath;
	private JRadioButton radioButton_1;
	private JRadioButton rdbtnDom;
	private JRadioButton rdbtngreen,rdbtnVDI,rdbtnRDP,rdbtnNAB,rdbtnAB,rdbtnNT,rdbtnCCAR;

	private JTextField textField;
	private JPasswordField textField_1;

	 private JLabel excelLabel;
	 private JLabel dbLabel;
	 private JLabel executeLabel;
	 private JLabel lblSource;
	 gui g;
	 
	 config c;
	 private JTextField unixPathText;
	 private JLabel lblPassword;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public udAutomation( gui g,config c) {
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
		frame.getContentPane().setLayout(null);
		
		lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setBounds(327, 217, 69, 20);
		frame.getContentPane().add(lblLevel);
		
			
			lblSource = new JLabel("Source:");
			lblSource.setForeground(Color.WHITE);
			lblSource.setFont(new Font("Segoe UI", Font.BOLD, 23));
			lblSource.setBounds(327, 433, 99, 31);
			frame.getContentPane().add(lblSource);
		
		
		//username
		textField = new JTextField();
		textField.setBounds(451, 258, 194, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		c.setUdUsername(textField.getText());
		
		
		//password
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(450, 298, 194, 26);
		frame.getContentPane().add(textField_1);
		c.setUdPassword(textField_1.getText());
		
		unixPathText = new JTextField();
		unixPathText.setBounds(451, 485, 269, 26);
		frame.getContentPane().add(unixPathText);
		unixPathText.setColumns(10);
		
		JRadioButton rdbtnL = new JRadioButton("L2");
		rdbtnL.setForeground(Color.WHITE);
		rdbtnL.setOpaque(false);
		rdbtnL.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnL.setBounds(441, 213, 69, 29);
		frame.getContentPane().add(rdbtnL);
		
		rdbtnL_1 = new JRadioButton("L3");
		rdbtnL_1.setOpaque(false);
		rdbtnL_1.setForeground(Color.WHITE);
		rdbtnL_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnL_1.setBounds(517, 214, 69, 29);
		frame.getContentPane().add(rdbtnL_1);
		
		
		
		
		rdbtnL.addActionListener(new java.awt.event.ActionListener() {
			
			  public void actionPerformed(java.awt.event.ActionEvent evt) {
				  c.setLevel(false);
	            }
			
		});
		
		rdbtnL_1.addActionListener(new java.awt.event.ActionListener() {
			
			  public void actionPerformed(java.awt.event.ActionEvent evt) {
				  c.setLevel(true);
	            }
			
		});
		
		
		ButtonGroup bg3 =new ButtonGroup();
		bg3.add(rdbtnL);
		bg3.add(rdbtnL_1);
		
		
		
		
		rdbtnDom = new JRadioButton("Dom");
		rdbtnDom.setOpaque(false);
		rdbtnDom.setForeground(Color.WHITE);
		rdbtnDom.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnDom.setBounds(435, 389, 87, 29);
		frame.getContentPane().add(rdbtnDom);
		
		rdbtngreen = new JRadioButton("$GREEN");
		rdbtngreen.setOpaque(false);
		rdbtngreen.setForeground(Color.WHITE);
		rdbtngreen.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtngreen.setBounds(543, 389, 127, 29);
		frame.getContentPane().add(rdbtngreen);
		
		ButtonGroup bg =new ButtonGroup();
		bg.add(rdbtnDom);
		bg.add(rdbtngreen);
		
		
		rdbtnNAB = new JRadioButton("NAB");
		rdbtnNAB.setOpaque(false);
		rdbtnNAB.setForeground(Color.WHITE);
		rdbtnNAB.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnNAB.setBounds(700, 220, 87, 29);
		frame.getContentPane().add(rdbtnNAB);
		
		
		
		rdbtnAB = new JRadioButton("AB");
		rdbtnAB.setOpaque(false);
		rdbtnAB.setForeground(Color.WHITE);
		rdbtnAB.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnAB.setBounds(700, 260, 87, 29);
		frame.getContentPane().add(rdbtnAB);
		
		
		rdbtnNT = new JRadioButton("NT");
		rdbtnNT.setOpaque(false);
		rdbtnNT.setForeground(Color.WHITE);
		rdbtnNT.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnNT.setBounds(700, 300, 87, 29);
		frame.getContentPane().add(rdbtnNT);
		
		
		rdbtnCCAR = new JRadioButton("CCAR");
		rdbtnCCAR.setOpaque(false);
		rdbtnCCAR.setForeground(Color.WHITE);
		rdbtnCCAR.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnCCAR.setBounds(700, 340, 87, 29);
		frame.getContentPane().add(rdbtnCCAR);
		
		
		
		
		
		
		ButtonGroup bg1 =new ButtonGroup();
		bg1.add(rdbtnNAB);
		bg1.add(rdbtnAB);
		bg1.add(rdbtnNT);
		bg1.add(rdbtnCCAR);
		
		rdbtnNAB.setVisible(false);
		rdbtnAB.setVisible(false);
		rdbtnNT.setVisible(false);
		rdbtnCCAR.setVisible(false);
		
		
		//Dom button action event
		rdbtnDom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNAB.setVisible(true);
				rdbtnAB.setVisible(true);
				rdbtnNT.setVisible(true);
				rdbtnCCAR.setVisible(true);
				
				
				
			}
		});
		
		//GREEN button action event
		rdbtngreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNAB.setVisible(false);
				rdbtnAB.setVisible(false);
				rdbtnNT.setVisible(false);
				rdbtnCCAR.setVisible(false);
				
				
				
			}
		});
		
		
		//type entry
		if(rdbtnDom.isSelected())
		{
			c.setType("DOM");
			
			
			
		}else if(rdbtngreen.isSelected())
		{
			c.setType("GREEN");
			
			
		}
		
		
		//Flavour entry
		
		if(rdbtnNAB.isSelected())
		{
			c.setFlavour("NA");
			
			
			
		}else if(rdbtnAB.isSelected())
		{
			c.setFlavour("AB");
			
			
		}else if(rdbtnNT.isSelected())
		{
			c.setFlavour("NT");
			
			
		}else if(rdbtnCCAR.isSelected())
		{
			c.setFlavour("CCAR");
			
			
		}
		
		
		
		
		lblCompatibleMode = new JLabel("Compatible :");
		lblCompatibleMode.setForeground(Color.WHITE);
		lblCompatibleMode.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblCompatibleMode.setBounds(327, 343, 150, 29);
		frame.getContentPane().add(lblCompatibleMode);
		
		
		rdbtnVDI = new JRadioButton("VDI");
		rdbtnVDI.setOpaque(false);
		rdbtnVDI.setForeground(Color.WHITE);
		rdbtnVDI.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnVDI.setBounds(500, 343,100, 29);
		frame.getContentPane().add(rdbtnVDI);
		
		
		rdbtnRDP = new JRadioButton("RDP");
		rdbtnRDP.setOpaque(false);
		rdbtnRDP.setForeground(Color.WHITE);
		rdbtnRDP.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnRDP.setBounds(600, 343, 150, 29);
		frame.getContentPane().add(rdbtnRDP);
		
		ButtonGroup bg2 =new ButtonGroup();
		bg2.add(rdbtnVDI);
		bg2.add(rdbtnRDP);
		
		
		//compatible mode entry
		
		if(rdbtnVDI.isSelected())
		{
			c.setCompatibleMode(false);
			
			
			
		}else if(rdbtnRDP.isSelected())
		{
			c.setCompatibleMode(true);
			
			
		}
		
		lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblType.setBounds(327, 388, 69, 29);
		frame.getContentPane().add(lblType);
		
		lblUnixPath = new JLabel("Unix Path:");
		lblUnixPath.setForeground(Color.WHITE);
		lblUnixPath.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblUnixPath.setBounds(327, 480, 125, 29);
		frame.getContentPane().add(lblUnixPath);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblUsername.setBounds(327, 253, 125, 29);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblPassword.setBounds(327, 295, 125, 29);
		frame.getContentPane().add(lblPassword);
	
		
		//Making built in incons... here you no longer need to set picture or filechooser. You just make a label and 
		//give it to addExcel() method.
		excelLabel = new JLabel("Excel");
		excelLabel.setBounds(415, 425, 78, 56);
		
	
		dbLabel = new JLabel("DB");
		dbLabel.setBounds(490, 425, 78, 56);

		executeLabel = new JLabel("Execute");
		executeLabel.setBounds(355, 550, 142, 41);
		
		excelLabel.setName("excel");
		dbLabel.setName("db");
		executeLabel.setName("execute");
		
		lblUnixPath.setVisible(false);
		unixPathText.setVisible(false);
		
		
		//This will load the data that is common for all GUI screens (the background, side bar)
		guiBase gb = new guiBase();
		
		//This will load the settings for mouse actions. If you want to add fuctionality to your GUI when clicking you
		//modify this file so it can be used across all GUIs
		mouse m = new mouse(gb,g,c,this);
		
		//Im giving this GUI into mouse options.
		m.setFrame(frame);
		//Im loading the JLabels that is common across all GUIS
		m.setupBaseIcons();
		//Im giving the excel,database, and execute buttons. This will load the Pics as well as some built in functionality.
		m.addExcel(excelLabel);
		m.addDb(dbLabel);
		m.addExecute(executeLabel);
		
		m.addAkshayUDStuff(lblUnixPath, unixPathText);
		
		//This loads the background.
		m.setupBackground();

		//Enables GUI to be visible.
		frame.setVisible(true);
		
		
		System.out.println("USERNAME: "+c.getUdUsername());
		System.out.println("PasswordE: "+c.getUdPassword());
		
	
	
	}
}
