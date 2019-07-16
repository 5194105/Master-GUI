import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class udAutomation {

	
	JFrame frame;
	private JLabel lblLevel;
	private JRadioButton rdbtnL_1;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblCompatibleMode;
	private JLabel lblType;
	private JLabel lblUnixPath;
	private JRadioButton radioButton_1;
	private JRadioButton rdbtnDom;
	private JRadioButton rdbtngreen;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	 private JLabel excelLabel;
	 private JLabel dbLabel;
	 private JLabel executeLabel;
	 private JLabel lblSource;
	 gui g;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public udAutomation( gui g) {
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
		
		lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setBounds(327, 217, 69, 20);
		frame.getContentPane().add(lblLevel);
		
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
		
		radioButton_1 = new JRadioButton("");
		radioButton_1.setOpaque(false);
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		radioButton_1.setBounds(555, 343, 40, 29);
		frame.getContentPane().add(radioButton_1);
		
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
		
		lblCompatibleMode = new JLabel("Compatible Mode: ");
		lblCompatibleMode.setForeground(Color.WHITE);
		lblCompatibleMode.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblCompatibleMode.setBounds(327, 343, 212, 29);
		frame.getContentPane().add(lblCompatibleMode);
		
		lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblType.setBounds(327, 388, 69, 29);
		frame.getContentPane().add(lblType);
		
		lblUnixPath = new JLabel("Unix Path:");
		lblUnixPath.setForeground(Color.WHITE);
		lblUnixPath.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblUnixPath.setBounds(327, 430, 125, 29);
		frame.getContentPane().add(lblUnixPath);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblUsername.setBounds(327, 253, 125, 29);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblPassword.setBounds(327, 298, 112, 29);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(451, 258, 194, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(450, 298, 194, 26);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(445, 435, 259, 26);
		frame.getContentPane().add(textField_2);
	
		
		lblSource = new JLabel("Source:");
		lblSource.setForeground(Color.WHITE);
		lblSource.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSource.setBounds(327, 475, 99, 31);
		frame.getContentPane().add(lblSource);
	
		excelLabel = new JLabel("New label");
		excelLabel.setBounds(400, 478, 78, 56);
	
		dbLabel = new JLabel("New label");
		dbLabel.setBounds(475, 478, 78, 56);

		executeLabel = new JLabel("New label");
		executeLabel.setBounds(355, 550, 142, 41);
		
		excelLabel.setName("excel");
		dbLabel.setName("db");
		executeLabel.setName("execute");
		
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
