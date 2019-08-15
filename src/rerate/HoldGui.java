package rerate;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.filechooser.FileSystemView;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.awt.Font;
import javax.swing.JRadioButton;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class HoldGui {

	String level;
	String file;
	
	
	


	private JFrame frame;
	private JTextField textField_3;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoldGui window = new HoldGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HoldGui () {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(216, 191, 216));
		frame.setBackground(new Color(0, 0, 0));
		frame.getContentPane().setForeground(new Color(139, 69, 19));
		frame.setBounds(100, 100, 578, 309);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblRegressionRerate = new JLabel("Prerate HOLD");
		lblRegressionRerate.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblRegressionRerate = new GridBagConstraints();
		gbc_lblRegressionRerate.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegressionRerate.gridx = 4;
		gbc_lblRegressionRerate.gridy = 0;
		frame.getContentPane().add(lblRegressionRerate, gbc_lblRegressionRerate);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		frame.getContentPane().add(label, gbc_label);

		JLabel lblLevel = new JLabel("Level(L2/L3)");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 1;
		gbc_lblLevel.gridy = 2;
		frame.getContentPane().add(lblLevel, gbc_lblLevel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 2;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblFile = new JLabel("File");
		lblFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFile = new GridBagConstraints();
		gbc_lblFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblFile.gridx = 1;
		gbc_lblFile.gridy = 3;
		frame.getContentPane().add(lblFile, gbc_lblFile);

		JButton btnNewButton = new JButton("Select");
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//System.out.println(FileSystemView.getFileSystemView().getHomeDirectory());

				JFileChooser jFileChooser1 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				File file2=null;
				jFileChooser1.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
				int returnVal = jFileChooser1.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					file2=jFileChooser1.getSelectedFile();
					file=file2.getAbsolutePath();
					textField_3.setText(file);
				}
				else
				{
					System.out.println("File access Cancelled by Uuser.");

				}

				//System.out.println(filepath);	
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 3;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 3;
		frame.getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 5;
		frame.getContentPane().add(label_1, gbc_label_1);

		ButtonGroup b = new ButtonGroup();
		JButton btnNewButton_1 = new JButton("RUN");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				level = textField.getText();
				
			/*	if(rdbtnFirefox.isSelected()){
					browser=true;
				}
				else if(rdbtnInternetexplorer.isSelected()){
					browser=false;
				}
*/
				try
				{
					Hold b = new Hold(file,level);
					b.Hold1();

				}
				catch(InterruptedException | IOException | NoAlertPresentException | UnreachableBrowserException | StaleElementReferenceException e)  
				{
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_1.gridwidth = 5;
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 9;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		JLabel lblEnterTheCorrect = new JLabel("Enter the correct details ");
		lblEnterTheCorrect.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblEnterTheCorrect = new GridBagConstraints();
		gbc_lblEnterTheCorrect.insets = new Insets(0, 0, 0, 5);
		gbc_lblEnterTheCorrect.gridx = 4;
		gbc_lblEnterTheCorrect.gridy = 11;
		frame.getContentPane().add(lblEnterTheCorrect, gbc_lblEnterTheCorrect);
	}

}
