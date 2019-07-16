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

public class test {

	 BufferedImage img = null;
     Image dimg;
     ImageIcon imageIcon;
     String homePath;
     String imagePath;
     BufferedImage mouseImage = null;
     Image mouseDimg = null;
     ImageIcon mouseimageIcon = null;
     JLabel mouseLabel;
     String mouseLabelStringName;
     String mouseLabelStringLight;
     String mouseLabelStringDark;
     JLabel lblNewLabel_1;
     JLabel lblNewLabel_2;
     JLabel lblNewLabel_3;
     JLabel lblNewLabel_4;
     String optionsDark="options.png";
     String dbDark="db.png";
     String linkDark="link.png";
     String infoDark="info.png";
     String optionsLight="darkoptions.png";
     String dbLight="darkdb.png";
     String linkLight="darklink.png";
     String infoLight="darkinfo.png";
     String selectionBar="selectionbar.png";
     String rebillPic="rebill.png";
     String reratePic="rerate.png";
     String instantPic="instant.png";
     String datapopPic="datapop.png";
     String udPic="ud.png";
     String preratePic="prerate.png";
     String rebillPicHighlight="rebillHighlight.png";
     String reratePicHighlight="rerateHighlight.png";
     String instantPicHighlight="instantHighlight.png";
     String datapopPicHighlight="datapopHighlight.png";
     String udPicHighlight="udHighlight.png";
     String preratePicHighlight="prerateHighlight.png";
     String backPic="back.png";
     String backPicHighlight="darkBack.png";
     
     String libDirectoryDB,libDirectoryExcel,libDirectorySelenium;
     
     String GtmDbName,GtmDbResults,GtmDbPassword,retryAttempts,secondTimeout,rebillL2URL,rebillL3URL;
     String rtmDbName,rtmDbPassword;
     String rerateL2URL,rerateL3URL,prerateL2URL,prerateL3URL;
     String instantInvoiceL2URL,instantInvoiceL3URL;
     String rebillResultTable;
     String rtmBatchShippingResults;
     //Hi aksshay checking github
     
     WebDriver ieDriver;
     WebDriver firefoxDriver;
     WebDriver chromeDriver;
     
     BufferedReader br;
     
     File tempFile,configFile;
     
     guiBase gb;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
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
	public test() {
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
		
		//gb = new guiBase();
		//gb.setFrame(frame);
		//gb.setUpIcons();
		
		
	}
}
