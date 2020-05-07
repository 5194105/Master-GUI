package guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import configuration.config;
import configuration.mouse;

public class prerateAutomationExtendedGui {
	private JFrame frame;
	private JButton saveAndCLose;
	
	private JLabel statusLabel;
	private JLabel allLabel;
	private JLabel nullLabel;
	private JLabel failedLabel;
	private JLabel flavorLabel;
	private JLabel domesticLabel;
	private JLabel internationalLabel;
	private JLabel expressLabel;
	private JLabel groundLabel;
	private JLabel functionLabel;
	private JLabel normalLabel;
	private JLabel mfRetireLabel;

    config c;
  
    private JLabel saveAndClose;
 
    
    gui g;
    
    mouse m;
	public prerateAutomationExtendedGui(gui g,config c) {
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
	
		
		
		
		saveAndCLose =new JButton("Save and Close");  
		saveAndCLose.setBounds(450, 510, 284, 41);  
		frame.getContentPane().add(saveAndCLose);
		
		
		/*
		functionLabel = new JLabel("Work Type");
		functionLabel.setBounds(300, 440, 110, 31);
		functionLabel.setForeground(Color.WHITE);
		functionLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(functionLabel);
		
		
		normalLabel = new JLabel("Normal:");
		normalLabel.setBounds(300, 480, 110, 31);
		normalLabel.setForeground(Color.WHITE);
		normalLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(normalLabel);
		
		mfRetireLabel = new JLabel("MF Retire:");
		mfRetireLabel.setBounds(300, 520, 110, 31);
		mfRetireLabel.setForeground(Color.WHITE);
		mfRetireLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(mfRetireLabel);
		
		
		
		
		JCheckBox normalBox = new JCheckBox("");
		normalBox.setBounds(415, 480, 27, 29);
		normalBox.setOpaque(false);
		frame.getContentPane().add(normalBox);
		
		JCheckBox mfRetireBox = new JCheckBox("");
		mfRetireBox.setBounds(415, 520, 27, 29);
		mfRetireBox.setOpaque(false);
		frame.getContentPane().add(mfRetireBox);
		
	*/
		
		
		statusLabel = new JLabel("Status");
		statusLabel.setBounds(300, 280, 78, 31);
		statusLabel.setForeground(Color.WHITE);
		statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(statusLabel);
		
		
		allLabel = new JLabel("All:");
		allLabel.setBounds(300, 320, 78, 31);
		allLabel.setForeground(Color.WHITE);
		allLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(allLabel);
		
		nullLabel = new JLabel("Null:");
		nullLabel.setBounds(300, 360, 78, 31);
		nullLabel.setForeground(Color.WHITE);
		nullLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(nullLabel);
		
		failedLabel = new JLabel("Failed:");
		failedLabel.setBounds(300, 400, 78, 31);
		failedLabel.setForeground(Color.WHITE);
		failedLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(failedLabel);
		
		/*
		
		flavorLabel = new JLabel("Flavor");
		flavorLabel.setBounds(600, 280, 100, 31);
		flavorLabel.setForeground(Color.WHITE);
		flavorLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(flavorLabel);
		
		domesticLabel = new JLabel("Domestic:");
		domesticLabel.setBounds(600, 320, 120, 31);
		domesticLabel.setForeground(Color.WHITE);
		domesticLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(domesticLabel);
		
		internationalLabel = new JLabel("International:");
		internationalLabel.setBounds(600, 360, 150, 31);
		internationalLabel.setForeground(Color.WHITE);
		internationalLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(internationalLabel);
		
		expressLabel = new JLabel("Express:");
		expressLabel.setBounds(600, 400, 120, 31);
		expressLabel.setForeground(Color.WHITE);
		expressLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(expressLabel);
		
		groundLabel = new JLabel("Ground:");
		groundLabel.setBounds(600, 440, 120, 31);
		groundLabel.setForeground(Color.WHITE);
		groundLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		frame.getContentPane().add(groundLabel);
		*/
		/*
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(630, 405, 27, 29);
		checkBox.setOpaque(false);
		frame.getContentPane().add(checkBox);
		*/
		
		
		
		JCheckBox allBox = new JCheckBox("");
		allBox.setBounds(415, 320, 27, 29);
		allBox.setOpaque(false);
		frame.getContentPane().add(allBox);
		
		JCheckBox nullBox = new JCheckBox("");
		nullBox.setBounds(415, 360, 27, 29);
		nullBox.setOpaque(false);
		frame.getContentPane().add(nullBox);
		
		JCheckBox failedBox = new JCheckBox("");
		failedBox.setBounds(415, 400, 27, 29);
		failedBox.setOpaque(false);
		frame.getContentPane().add(failedBox);
		
		
		/*
		
		
		JCheckBox domBox = new JCheckBox("");
		domBox.setBounds(750, 320, 27, 29);
		domBox.setOpaque(false);
		frame.getContentPane().add(domBox);

		JCheckBox internationalBox = new JCheckBox("");
		internationalBox.setBounds(750, 360, 27, 29);
		internationalBox.setOpaque(false);
		frame.getContentPane().add(internationalBox);
		
		JCheckBox expressBox = new JCheckBox("");
		expressBox.setBounds(750, 400, 27, 29);
		expressBox.setOpaque(false);
		frame.getContentPane().add(expressBox);
		
		JCheckBox groundBox = new JCheckBox("");
		groundBox.setBounds(750, 440, 27, 29);
		groundBox.setOpaque(false);
		frame.getContentPane().add(groundBox);
		

	*/
		
		
		guiBase gb = new guiBase();
		mouse m = new mouse(gb,g,c,this,frame);
		m.setFrame(frame);
		m.setupBaseIcons();
		
		m.setupBackground();

	
		
		
	
		
		
	    frame.setVisible(true);
        
	    
	    
	    
	    
	    
	    
	    saveAndCLose.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  
	    	  if (allBox.isSelected()) {
	    		  c.setAllCheckBox("true");
	    	  }  else if(!allBox.isSelected()){
	    		  c.setAllCheckBox("false");
	    	  }
	    	  
	    	  
	    	  if (nullBox.isSelected()) {
	    		  c.setNullCheckBox("true");
	    	  }  else if(!nullBox.isSelected()){
	    		  c.setNullCheckBox("false");
	    	  }
	    	  
	    	  
	    	  
	    	  if (failedBox.isSelected()) {
	    		  c.setFailedCheckBox("true");
	    	  }  else if(!failedBox.isSelected()){
	    		  c.setFailedCheckBox("false");
	    	  }
	    	  
	    	  
	    	  /*
	    	  if (domBox.isSelected()) {
	    		  c.setDomesticCheckBox("true");
	    	  }  else if(!domBox.isSelected()){
	    		  c.setDomesticCheckBox("false");
	    	  }
	    	  
	    	  
	    	  
	    	  if (internationalBox.isSelected()) {
	    		  c.setInternationalCheckBox("true");
	    	  }  else if(!internationalBox.isSelected()){
	    		  c.setInternationalCheckBox("false");
	    	  }
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  if (expressBox.isSelected()) {
	    		  c.setExpressCheckBox("true");
	    	  }
	    	  else if(!expressBox.isSelected()){
	    		  c.setExpressCheckBox("false");
	    	  }
	    	  
	    	  
	    	  if (groundBox.isSelected()) {
	    		  c.setGroundCheckBox("true");
	    	  }
	    	  else if(!groundBox.isSelected()){
	    		  c.setGroundCheckBox("false");
	    	  }
	    	  
	    	  
	    	  
	    	  if (normalBox.isSelected()) {
	    		  c.setNormalCheckBox("true");
	    	  }
	    	  else if(!normalBox.isSelected()){
	    		  c.setNormalCheckBox("false");
	    	  }
	    	  
	    	  
	    	  
	    	  if (mfRetireBox.isSelected()) {
	    		  c.setMfRetireCheckBox("true");
	    	  }
	    	  else if(!mfRetireBox.isSelected()){
	    		  c.setMfRetireCheckBox("false");
	    	  }
	    	  */
 frame.setVisible(false);
	      }
	    });
	    
	}
	
}
