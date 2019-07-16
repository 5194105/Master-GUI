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

public class datapopAutomationGui {

	private JFrame frame;
	gui g;

	/**
	 * Create the application.
	 */
	public datapopAutomationGui(gui g) {
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
		frame.getContentPane().setLayout(null);
		
	
		
		frame.setVisible(true);
		
	}

	
}
