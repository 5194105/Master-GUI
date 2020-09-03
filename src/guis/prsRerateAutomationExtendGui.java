package guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import configuration.config;
import configuration.mouse;

public class prsRerateAutomationExtendGui {
	private JFrame frame;
	private JButton saveAndCLose;
	
	private JLabel statusLabel;

	private JLabel flavorLabel;

	
	private JLabel groundDomestic1;
	private JLabel groundInternational1;
	private JLabel n1;
	private JLabel smartPost1;
	private JLabel expressDomestic1;
	private JLabel expressInternational1;
	
	
	private JLabel groundDomestic2;
	private JLabel groundInternational2;
	private JLabel n2;
	private JLabel smartPost2;
	private JLabel expressDomestic2;
	private JLabel expressInternational2;
	
	
	private JLabel allLabel;
	private JLabel failLabel;
	private JLabel nullLabel;
	
	
	
    config c;
    Boolean checkList=true;
    
    
    private JLabel saveAndClose;
 
    
    gui g;
    
    mouse m;
	public prsRerateAutomationExtendGui(gui g,config c) {
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
		frame.setBounds(100, 100, 985, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		
		
		
		saveAndCLose =new JButton("Save and Close");  
		saveAndCLose.setBounds(450, 550, 284, 41);  
		frame.getContentPane().add(saveAndCLose);
		
		
		
		
	
		
		
		
		
	
	
		statusLabel = new JLabel("Service 1");
		statusLabel.setBounds(190, 280, 200, 31);
		statusLabel.setForeground(Color.WHITE);
		statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(statusLabel);
		
		
		
		
		
	
		
		expressDomestic1 = new JLabel("Express Domestic:");
		expressDomestic1.setBounds(190, 320, 200, 31);
		expressDomestic1.setForeground(Color.WHITE);
		expressDomestic1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(expressDomestic1);
		
		expressInternational1 = new JLabel("Express Intl:");
		expressInternational1.setBounds(190, 360, 200, 31);
		expressInternational1.setForeground(Color.WHITE);
		expressInternational1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(expressInternational1);
		
		groundDomestic1 = new JLabel("Ground Domestic:");
		groundDomestic1.setBounds(190, 400, 200, 31);
		groundDomestic1.setForeground(Color.WHITE);
		groundDomestic1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(groundDomestic1);
		
		groundInternational1 = new JLabel("Ground Intl:");
		groundInternational1.setBounds(190, 440, 200, 31);
		groundInternational1.setForeground(Color.WHITE);
		groundInternational1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(groundInternational1);
		
		n1 = new JLabel("Non Transport:");
		n1.setBounds(190, 480, 200, 31);
		n1.setForeground(Color.WHITE);
		n1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(n1);
		
		smartPost1 = new JLabel("SmartPost:");
		smartPost1.setBounds(190, 520, 200, 31);
		smartPost1.setForeground(Color.WHITE);
		smartPost1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(smartPost1);
		
		
		flavorLabel = new JLabel("Service 2");
		flavorLabel.setBounds(460, 280, 100, 31);
		flavorLabel.setForeground(Color.WHITE);
		flavorLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(flavorLabel);

		expressDomestic2 = new JLabel("Express Domestic:");
		expressDomestic2.setBounds(460, 320, 200, 31);
		expressDomestic2.setForeground(Color.WHITE);
		expressDomestic2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(expressDomestic2);
		
		expressInternational2 = new JLabel("Express Intl:");
		expressInternational2.setBounds(460, 360, 200, 31);
		expressInternational2.setForeground(Color.WHITE);
		expressInternational2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(expressInternational2);
		
		groundDomestic2 = new JLabel("Ground Domestic:");
		groundDomestic2.setBounds(460, 400, 200, 31);
		groundDomestic2.setForeground(Color.WHITE);
		groundDomestic2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(groundDomestic2);
		
		groundInternational2 = new JLabel("Ground Intl:");
		groundInternational2.setBounds(460, 440, 200, 31);
		groundInternational2.setForeground(Color.WHITE);
		groundInternational2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(groundInternational2);
		
		n2 = new JLabel("Non Transport:");
		n2.setBounds(460, 480, 200, 31);
		n2.setForeground(Color.WHITE);
		n2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(n2);
		
		smartPost2 = new JLabel("SmartPost:");
		smartPost2.setBounds(460, 520, 200, 31);
		smartPost2.setForeground(Color.WHITE);
		smartPost2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(smartPost2);
		
		
		flavorLabel = new JLabel("Status");
		flavorLabel.setBounds(725, 280, 100, 31);
		flavorLabel.setForeground(Color.WHITE);
		flavorLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(flavorLabel);
		
		
		allLabel = new JLabel("All:");
		allLabel.setBounds(725, 320, 100, 31);
		allLabel.setForeground(Color.WHITE);
		allLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(allLabel);
		
		
		nullLabel = new JLabel("Null:");
		nullLabel.setBounds(725, 360, 100, 31);
		nullLabel.setForeground(Color.WHITE);
		nullLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(nullLabel);
		
		
		failLabel = new JLabel("Failed:");
		failLabel.setBounds(725, 400, 100, 31);
		failLabel.setForeground(Color.WHITE);
		failLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(failLabel);
		
		
		
		
		JCheckBox ed1 = new JCheckBox("");
		ed1.setBounds(375,322, 27, 29);
		ed1.setOpaque(false);
		ed1.setSelected(true);
		frame.getContentPane().add(ed1);
		
		
		
		JCheckBox ei1 = new JCheckBox("");
		ei1.setBounds(375, 362, 27, 29);
		ei1.setOpaque(false);
		ei1.setSelected(true);
		frame.getContentPane().add(ei1);
		
		JCheckBox gd1 = new JCheckBox("");
		gd1.setBounds(375, 402, 27, 29);
		gd1.setOpaque(false);
		gd1.setSelected(true);
		frame.getContentPane().add(gd1);
		
		JCheckBox gi1 = new JCheckBox("");
		gi1.setBounds(375, 442, 27, 29);
		gi1.setOpaque(false);
		gi1.setSelected(true);
		frame.getContentPane().add(gi1);
		
		JCheckBox nt1 = new JCheckBox("");
		nt1.setBounds(375, 482, 27, 29);
		nt1.setOpaque(false);
		nt1.setSelected(true);
		frame.getContentPane().add(nt1);

		JCheckBox sp1 = new JCheckBox("");
		sp1.setBounds(375, 522, 27, 29);
		sp1.setOpaque(false);
		sp1.setSelected(true);
		frame.getContentPane().add(sp1);
		
		
		
		JCheckBox ed2 = new JCheckBox("");
		ed2.setBounds(655, 322, 27, 29);
		ed2.setOpaque(false);
		ed2.setSelected(true);
		frame.getContentPane().add(ed2);

		JCheckBox ei2 = new JCheckBox("");
		ei2.setBounds(655, 362, 27, 29);
		ei2.setOpaque(false);
		ei2.setSelected(true);
		frame.getContentPane().add(ei2);
		
		JCheckBox gd2 = new JCheckBox("");
		gd2.setBounds(655, 402, 27, 29);
		gd2.setOpaque(false);
		gd2.setSelected(true);
		frame.getContentPane().add(gd2);
		
		JCheckBox gi2 = new JCheckBox("");
		gi2.setBounds(655, 442, 27, 29);
		gi2.setOpaque(false);
		gi2.setSelected(true);
		frame.getContentPane().add(gi2);
		
		JCheckBox nt2 = new JCheckBox("");
		nt2.setBounds(655, 482, 27, 29);
		nt2.setOpaque(false);
		nt2.setSelected(true);
		frame.getContentPane().add(nt2);

		JCheckBox sp2 = new JCheckBox("");
		sp2.setBounds(655, 522, 27, 29);
		sp2.setOpaque(false);
		sp2.setSelected(true);
		frame.getContentPane().add(sp2);
		
		
		
		
		

		JCheckBox allCheckBox = new JCheckBox("");
		allCheckBox.setBounds(790, 322, 27, 29);
		allCheckBox.setOpaque(false);
		frame.getContentPane().add(allCheckBox);

		JCheckBox nullCheckBox = new JCheckBox("");
		nullCheckBox.setBounds(790, 362, 27, 29);
		nullCheckBox.setOpaque(false);
		nullCheckBox.setSelected(true);
		frame.getContentPane().add(nullCheckBox);
		
		JCheckBox failCheckBox = new JCheckBox("");
		failCheckBox.setBounds(790, 402, 27, 29);
		failCheckBox.setOpaque(false);
		failCheckBox.setSelected(true);
		frame.getContentPane().add(failCheckBox);
		
		
		
		
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
	    	  checkList=true;
	    	
	    	  if(ed1.isSelected()) {
	    		  c.setEd1("true");
	    	  }
	    	  else {
	    		  c.setEd1("false");
	    	  }
	    	  
 if(ei1.isSelected()) {
	  c.setEi1("true");
	    	  }
 else {
	 c.setEi1("false");
 }
 if(gd1.isSelected()) {
	  c.setGd1("true");
 }
 else {
	  c.setGd1("false");
 }
 if(gi1.isSelected()) {
	  c.setGi1("true");
 }
 else {
	  c.setGi1("false");
 }
 if(nt1.isSelected()) {
	  c.setNt1("true");
 }
 else {
	  c.setNt1("false");
 }
 if(sp1.isSelected()) {
	  c.setSp1("true");
 }
 else {
	  c.setSp1("false");
 }
 if(ed2.isSelected()) {
	  c.setEd2("true");
 }
 else {
	  c.setEd2("false");
 }
if(ei2.isSelected()) {
	  c.setEi2("true");
 }
else {
	  c.setEi2("false");
}
if(gd2.isSelected()) {
	  c.setGd2("true");
}
else {
	c.setGd2("false");
}
if(gi2.isSelected()) {
	  c.setGi2("true");
}
else {
	  c.setGi2("false");
}
if(nt2.isSelected()) {
	  c.setNt2("true");
}
else {
	  c.setNt2("false");
}

if(sp2.isSelected()) {
	  c.setSp2("true");
}
else {
	c.setSp2("false");
}

if(allCheckBox.isSelected()) {
c.setAllCheckBox("true");
}
else {
	c.setAllCheckBox("false");
}

if(nullCheckBox.isSelected())  {
	c.setNullCheckBox("true");
	}
	else {
		c.setNullCheckBox("false");
	}

if(failCheckBox.isSelected())  {
	c.setFailedCheckBox("true");
	}
	else {
		c.setFailedCheckBox("false");
	}
	    	  
      }
	    });
	    
	}
	
}
