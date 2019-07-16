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
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class rebillAutomationGui extends guiSuper {

	private JFrame frame;

	
     String execute="execute.png";
     String dbPic="database.png";
     String dbPicSelected="databaseSelected.png";
     String excelPic="excel.png";
     String excelPicSelected="excelSelected.png";
     
     config c;
    
 
     private JLabel label;
     private JLabel lblSource;
     private JLabel executeLabel;
     private JRadioButton radioButton;
     private JRadioButton radioButton_1;
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
	public rebillAutomationGui(gui g) {
		this.g=g;
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
		
		excelLabel = new JLabel("New label");
		excelLabel.setBounds(512, 335, 78, 56);
	
		dbLabel = new JLabel("New label");
		dbLabel.setBounds(619, 332, 78, 56);

		executeLabel = new JLabel("New label");
		executeLabel.setBounds(413, 489, 284, 41);
		
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
		
		radioButton = new JRadioButton("L2");
		radioButton.setOpaque(false);
		radioButton.setForeground(Color.WHITE);
		radioButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		radioButton.setBounds(512, 281, 69, 29);
		frame.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("L3");
		radioButton_1.setOpaque(false);
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		radioButton_1.setBounds(588, 282, 69, 29);
		frame.getContentPane().add(radioButton_1);
		
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
		mouse m = new mouse(gb,g);
		m.setFrame(frame);
		m.setupBaseIcons();
		m.addExcel(excelLabel);
		m.addDb(dbLabel);
		m.addExecute(executeLabel);
		m.setupBackground();

	    frame.setVisible(true);

		
	}
}
