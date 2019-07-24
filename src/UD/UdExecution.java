package UD;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import configuration.config;
import configuration.database;
import configuration.excel;


public class UdExecution {

	static String filepath;
	private Robot robot;

	static Process p;
	static String level;
	static String username;
	static String password;
	static String serverString;
	static String path;
	static String command;
	static String release;
	static int count;
	static String flavour, filename;
	static String cycle, puttypath;

	Connection con;
	PreparedStatement ps1 = null;
	ResultSet rs1 = null;
	
	config c;

	
	
	
	

	public UdExecution(String level, String type,String unixPath, String username, String password, String filePath, String place,
			String flavour) throws Exception {

		database db = new database();
		
		System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		
		System.out.println(c.getGtmDbName()+"------------------"+ c.getGtmDbPassword());
		
		db.openDB(c.getGtmRevToolsConnection("GTM_REV_TOOLS", "Wr4l3pP5gWVd7apow8eZwnarI3s4e1"));
		
		
		String dbname = "";
		if (flavour == "NA") {
			dbname = "ud_non_airbill_pending";
		} else if (flavour == "AB") {
			dbname = "ud_airbill_pending";
		} else if (flavour == "NT") {
			dbname = "ud_NT_pending";
		} else if (flavour == "CCAR") {
			dbname = "ud_CCAR_pending";
		} else {
			dbname = "ud_GREEN_pending";
		}

		
		db.readData("select * from " + dbname + " where item_prcs_cd is null or item_prcs_cd='OR'");
        ResultSet rs;
        rs=db.getResultSet();
       
      
        if(unixPath=="NA")
        {
        	path="/home/sqaatt/onlines/udpending";
        }else
        {
        	path=unixPath;
        	
        	
        }
        
        
          
		// Adding Flavour
		

		if (flavour == "NA") {
			filename = "NA";
		} else if (flavour == "AB") {
			filename = "AB";
		} else if (flavour == "NT") {
			filename = "NT";
		} else if (flavour == "CCAR") {
			filename = "CCAR";
		} else {
			filename = "G";
		}
		// ---------------------------------------------------

		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Compatible mode
		if (place.equals("LOCAL")) {
			puttypath = "C:\\Users\\Public\\Desktop\\putty.exe";
		}
		if (place.equals("RDP")) {
			puttypath = "C:\\Users\\FedExUser\\Desktop\\putty.exe";
		}
		// ----------------------------------------------------
		
		//==========================================For Database reading==========================================
		if(c.getSource()) {
		
		if (level == "L2") {

//			dbconnect();
			
			
			
			
			
			

			// for spliting UD's into files
			serverString = "irh22076.ute.fedex.com";

			Runtime r1 = Runtime.getRuntime();

			String s1 = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";

			try {

				p = r1.exec(s1);
				Thread.sleep(3000);

			} catch (Exception f) {
				System.out.println(f);
				f.printStackTrace();

			}

			type("sudo su - sqaatt");
			enter();
			Thread.sleep(150);
			type(password);
			enter();
			Thread.sleep(5000);
			type("cd " + path);
			enter();
			Thread.sleep(5000);
			type("cat > " + filename);
			Thread.sleep(5000);
			enter();
			while (rs.next()) {
				String s2 = rs.getString(1);

				int d = s2.length();
				count = d / 20;

				type(s2);
				enter();

			}
			Thread.sleep(10000);
			doType(KeyEvent.VK_CONTROL, KeyEvent.VK_D);
			Thread.sleep(9000);

			enter();
			type("split -l 20 -d -a 2 " + filename + " " + filename);
			Thread.sleep(5000);

			enter();

			db.closeDB();
			// ----------------------------------------------------------------
			if (type == "Domestic") {

				for (int i = 0; i < count; i++) {

					String formatted = String.format("%02d", i);
					System.out.println("========================================================================");
					command = "non_airbill_SOE.sh ";
					serverString = "irh22076.ute.fedex.com";

					Runtime r = Runtime.getRuntime();

					String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";

					try {
						
						p = r.exec(s);
						Thread.sleep(3000);

					} catch (Exception f) {
						System.out.println(f);
						f.printStackTrace();

					}

					type("sudo su - sqaatt");
					enter();
					Thread.sleep(150);
					type(password);
					enter();
					Thread.sleep(5000);
					type("cd " + path);
					enter();
					Thread.sleep(150);
					System.out.println(command + filename + formatted);
					type(command + filename + formatted);
					enter();
				}

			} else if (type == "$GREEN") {

				command = "execute_green.sh ";
				serverString = "irh22089.ute.fedex.com";

				for (int i = 0; i < count; i++) {

					String formatted = String.format("%02d", i);
					System.out.println("========================================================================");

					Runtime r = Runtime.getRuntime();

					String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";
					try {
						
						p = r.exec(s);
						Thread.sleep(3000);

					} catch (Exception f) {
						System.out.println(f);
						f.printStackTrace();

					}

					type("sudo su - sqaatt");
					enter();
					Thread.sleep(150);
					type(password);
					enter();
					Thread.sleep(5000);
					type("cd " + path);
					enter();
					Thread.sleep(150);
					System.out.println(command + filename + formatted);
					type(command + filename + formatted);
					enter();

				}
			}

			ps1.close();
			rs1.close();
			con.close();

		}

		else if (level == "L3") {

		
			// for spliting UD's into files

			serverString = "srh22080.ute.fedex.com";

			Runtime r1 = Runtime.getRuntime();

			String s1 = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";

			try {

				p = r1.exec(s1);
				Thread.sleep(3000);

			} catch (Exception f) {
				System.out.println(f);
				f.printStackTrace();

			}

			type("sudo su - sqaatt");
			enter();
			Thread.sleep(150);
			type(password);
			enter();
			Thread.sleep(5000);
			type("cd " + path);
			enter();
			Thread.sleep(5000);
			type("cat > " + filename);
			Thread.sleep(5000);
			enter();
			int d = 0;
			while (rs.next()) {
				String s2 = rs.getString(2);

				d = d + 1;

				
				type(s2);
				enter();

				

			}
			count = d / 20;

			Thread.sleep(10000);

			doType(KeyEvent.VK_CONTROL, KeyEvent.VK_D);
			enter();

			Thread.sleep(5000);
			enter();
			type("split -l 20 -d -a 2 " + filename + " " + filename);
			Thread.sleep(5000);
			enter();

			db.closeDB();

			// -------------------------------------
			if (type == "Domestic") {

				for (int i = 0; i < count; i++) {

					String formatted = String.format("%02d", i);

					System.out.println("========================================================================");
					command = "non_airbill_SOE.sh ";
					serverString = "srh22080.ute.fedex.com";

					Runtime r = Runtime.getRuntime();

					String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";
					try {
						
						p = r.exec(s);
						Thread.sleep(3000);

					} catch (Exception f) {
						System.out.println(f);
						f.printStackTrace();

					}

					type("sudo su - sqaatt");
					enter();
					Thread.sleep(150);
					type(password);
					enter();
					Thread.sleep(5000);
					type("cd " + path);
					enter();
					Thread.sleep(150);
					System.out.println(command + filename + formatted);

					type(command + filename + formatted);

					
					enter();

				}
			}

			else if (type == "$GREEN") {

				command = "execute_green.sh ";
				serverString = "srh22097.ute.fedex.com";

				for (int i = 0; i < count; i++) {

					String formatted = String.format("%02d", i);
					System.out.println("========================================================================");

					Runtime r = Runtime.getRuntime();

					String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";
					try {
						
						p = r.exec(s);
						Thread.sleep(3000);

					} catch (Exception f) {
						System.out.println(f);
						f.printStackTrace();

					}

					type("sudo su - sqaatt");
					enter();
					Thread.sleep(150);
					type(password);
					enter();
					Thread.sleep(5000);
					type("cd " + path);
					enter();
					Thread.sleep(150);
					System.out.println(command + filename + formatted);
					type(command + filename + formatted);

					
					enter();

				}

			}

			db.closeDB();
			
			//==========================================Databse completed================================================
		}	

		}else if(c.getSource()==false)
			{
			
			
			if(level == "L2") {

			//==========================================ForExcel reading==========================================
				
			
			excel e = new excel(c.getExcelPath());
            e.setUpExcelWorkbook();
            //Sets up the sheet at the a particular index (0 = sheet 1)
            e.setUpExcelSheet(0);
            e.setRowCountAutomatically(2);
         
            
    
           

				// for spliting UD's into files
				serverString = "irh22076.ute.fedex.com";

				Runtime r1 = Runtime.getRuntime();

				String s1 = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";

				try {

					p = r1.exec(s1);
					Thread.sleep(3000);

				} catch (Exception f) {
					System.out.println(f);
					f.printStackTrace();

				}

				type("sudo su - sqaatt");
				enter();
				Thread.sleep(150);
				type(password);
				enter();
				Thread.sleep(5000);
				type("cd " + path);
				enter();
				Thread.sleep(5000);
				type("cat > " + filename);
				Thread.sleep(5000);
				enter();
				while (rs.next()) {
					String s2 = rs.getString(1);

					
				

					type(s2);
					enter();

				}
				Thread.sleep(10000);
				doType(KeyEvent.VK_CONTROL, KeyEvent.VK_D);
				Thread.sleep(9000);

				enter();
				type("split -l 20 -d -a 2 " + filename + " " + filename);
				Thread.sleep(5000);

				enter();

				ps1.close();
				rs1.close();
				con.close();
				// ----------------------------------------------------------------
				if (type == "Domestic") {

					for (int i=1;i<e.getRowCount()+1;i++) {
						
						String file=e.getCellData(i, 0);

						
						System.out.println("========================================================================");
						command = "non_airbill_SOE.sh ";
						serverString = "irh22076.ute.fedex.com";

						Runtime r = Runtime.getRuntime();

						String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";

						try {
							
							p = r.exec(s);
							Thread.sleep(3000);

						} catch (Exception f) {
							System.out.println(f);
							f.printStackTrace();

						}

						type("sudo su - sqaatt");
						enter();
						Thread.sleep(150);
						type(password);
						enter();
						Thread.sleep(5000);
						type("cd " + path);
						enter();
						Thread.sleep(150);
						System.out.println(command + file);
						type(command + file);
						enter();
					}

				} else if (type == "$GREEN") {

					command = "execute_green.sh ";
					serverString = "irh22089.ute.fedex.com";

					for (int i=1;i<e.getRowCount()+1;i++) {
						
						String file=e.getCellData(i, 0);

						
						System.out.println("========================================================================");

						Runtime r = Runtime.getRuntime();

						String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";
						try {
							
							p = r.exec(s);
							Thread.sleep(3000);

						} catch (Exception f) {
							System.out.println(f);
							f.printStackTrace();

						}

						type("sudo su - sqaatt");
						enter();
						Thread.sleep(150);
						type(password);
						enter();
						Thread.sleep(5000);
						type("cd " + path);
						enter();
						Thread.sleep(150);
						System.out.println(command + file);
						type(command + file);
						enter();

					}
				}

				ps1.close();
				rs1.close();
				con.close();

			}

			else if (level == "L3") {
				
				
				excel e = new excel(c.getExcelPath());
	            e.setUpExcelWorkbook();
	            //Sets up the sheet at the a particular index (0 = sheet 1)
	            e.setUpExcelSheet(0);
	            e.setRowCountAutomatically(2);
	         
	            
	            

			
				// for spliting UD's into files

				serverString = "srh22080.ute.fedex.com";

				Runtime r1 = Runtime.getRuntime();

				String s1 = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";

				try {

					p = r1.exec(s1);
					Thread.sleep(3000);

				} catch (Exception f) {
					System.out.println(f);
					f.printStackTrace();

				}

				type("sudo su - sqaatt");
				enter();
				Thread.sleep(150);
				type(password);
				enter();
				Thread.sleep(5000);
				type("cd " + path);
				enter();
				Thread.sleep(5000);
				type("cat > " + filename);
				Thread.sleep(5000);
				enter();
				
				while (rs.next()) {
					String s2 = rs.getString(2);

					

					type(s2);
					enter();

					

				}
				

				Thread.sleep(10000);

				doType(KeyEvent.VK_CONTROL, KeyEvent.VK_D);
				enter();

				Thread.sleep(5000);
				enter();
				type("split -l 20 -d -a 2 " + filename + " " + filename);
				Thread.sleep(5000);
				enter();

				

				// -------------------------------------
				if (type == "Domestic") {

					for (int i=1;i<e.getRowCount()+1;i++) {
						
						String file=e.getCellData(i, 0);

						

						System.out.println("========================================================================");
						command = "non_airbill_SOE.sh ";
						serverString = "srh22080.ute.fedex.com";

						Runtime r = Runtime.getRuntime();

						String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";
						try {
							
							p = r.exec(s);
							Thread.sleep(3000);

						} catch (Exception f) {
							System.out.println(f);
							f.printStackTrace();

						}

						type("sudo su - sqaatt");
						enter();
						Thread.sleep(150);
						type(password);
						enter();
						Thread.sleep(5000);
						type("cd " + path);
						enter();
						Thread.sleep(150);
						System.out.println(command + file);
						type(command + file);

						
						enter();

					}
				}

				else if (type == "$GREEN") {

					command = "execute_green.sh ";
					serverString = "srh22097.ute.fedex.com";

					for (int i=1;i<e.getRowCount()+1;i++) {
						
						String file=e.getCellData(i, 0);

						
						System.out.println("========================================================================");

						Runtime r = Runtime.getRuntime();

						String s = puttypath + " -ssh -l " + "f" + username + " -pw " + password + " " + serverString + "";
						try {
							
							p = r.exec(s);
							Thread.sleep(3000);

						} catch (Exception f) {
							System.out.println(f);
							f.printStackTrace();

						}

						type("sudo su - sqaatt");
						enter();
						Thread.sleep(150);
						type(password);
						enter();
						Thread.sleep(5000);
						type("cd " + path);
						enter();
						Thread.sleep(150);
						System.out.println(command + file);
						type(command + file);

						
						enter();

					}

				}

				ps1.close();
				rs1.close();
				con.close();
				
				//==========================================Excel completed================================================
				

			}
		
			}
		
		

	}

	public UdExecution() throws AWTException

	{

		this.robot = new Robot();

		robot.delay(500);

		robot.setAutoDelay(2);

		robot.setAutoWaitForIdle(true);

	}

	public UdExecution(Robot robot)

	{

		this.robot = robot;

	}

	public void type(CharSequence characters)

	{

		int length = characters.length();

		for (int i = 0; i < length; i++)

		{

			char character = characters.charAt(i);

			type(character);

		}

	}

	public void enter()

	{

		robot.keyPress(KeyEvent.VK_ENTER);

	}

	public void type(char character)

	{

		switch (character) {

		case 'a':
			doType(KeyEvent.VK_A);
			break;

		case 'b':
			doType(KeyEvent.VK_B);
			break;

		case 'c':
			doType(KeyEvent.VK_C);
			break;

		case 'd':
			doType(KeyEvent.VK_D);
			break;

		case 'e':
			doType(KeyEvent.VK_E);
			break;

		case 'f':
			doType(KeyEvent.VK_F);
			break;

		case 'g':
			doType(KeyEvent.VK_G);
			break;

		case 'h':
			doType(KeyEvent.VK_H);
			break;

		case 'i':
			doType(KeyEvent.VK_I);
			break;

		case 'j':
			doType(KeyEvent.VK_J);
			break;

		case 'k':
			doType(KeyEvent.VK_K);
			break;

		case 'l':
			doType(KeyEvent.VK_L);
			break;

		case 'm':
			doType(KeyEvent.VK_M);
			break;

		case 'n':
			doType(KeyEvent.VK_N);
			break;

		case 'o':
			doType(KeyEvent.VK_O);
			break;

		case 'p':
			doType(KeyEvent.VK_P);
			break;

		case 'q':
			doType(KeyEvent.VK_Q);
			break;

		case 'r':
			doType(KeyEvent.VK_R);
			break;

		case 's':
			doType(KeyEvent.VK_S);
			break;

		case 't':
			doType(KeyEvent.VK_T);
			break;

		case 'u':
			doType(KeyEvent.VK_U);
			break;

		case 'v':
			doType(KeyEvent.VK_V);
			break;

		case 'w':
			doType(KeyEvent.VK_W);
			break;

		case 'x':
			doType(KeyEvent.VK_X);
			break;

		case 'y':
			doType(KeyEvent.VK_Y);
			break;

		case 'z':
			doType(KeyEvent.VK_Z);
			break;

		case 'A':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
			break;

		case 'B':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;

		case 'C':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_C);
			break;

		case 'D':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_D);
			break;

		case 'E':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_E);
			break;

		case 'F':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
			break;

		case 'G':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_G);
			break;

		case 'H':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_H);
			break;

		case 'I':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_I);
			break;

		case 'J':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_J);
			break;

		case 'K':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_K);
			break;

		case 'L':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_L);
			break;

		case 'M':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_M);
			break;

		case 'N':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_N);
			break;

		case 'O':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_O);
			break;

		case 'P':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_P);
			break;

		case 'Q':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Q);
			break;

		case 'R':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
			break;

		case 'S':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_S);
			break;

		case 'T':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_T);
			break;

		case 'U':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_U);
			break;

		case 'V':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_V);
			break;

		case 'W':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_W);
			break;

		case 'X':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_X);
			break;

		case 'Y':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Y);
			break;

		case 'Z':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Z);
			break;

		case '`':
			doType(KeyEvent.VK_BACK_QUOTE);
			break;

		case '0':
			doType(KeyEvent.VK_0);
			break;

		case '1':
			doType(KeyEvent.VK_1);
			break;

		case '2':
			doType(KeyEvent.VK_2);
			break;

		case '3':
			doType(KeyEvent.VK_3);
			break;

		case '4':
			doType(KeyEvent.VK_4);
			break;

		case '5':
			doType(KeyEvent.VK_5);
			break;

		case '6':
			doType(KeyEvent.VK_6);
			break;

		case '7':
			doType(KeyEvent.VK_7);
			break;

		case '8':
			doType(KeyEvent.VK_8);
			break;

		case '9':
			doType(KeyEvent.VK_9);
			break;

		case '-':
			doType(KeyEvent.VK_MINUS);
			break;

		case '=':
			doType(KeyEvent.VK_EQUALS);
			break;

		case '~':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE);
			break;

		case '!':
			doType(KeyEvent.VK_EXCLAMATION_MARK);
			break;

		case '@':
			doType(KeyEvent.VK_AT);
			break;

		case '#':
			doType(KeyEvent.VK_NUMBER_SIGN);
			break;

		case '$': {
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_4);
			break;
		}

		case '%':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_5);
			break;

		case '^':
			doType(KeyEvent.VK_CIRCUMFLEX);
			break;

		case '&':
			doType(KeyEvent.VK_AMPERSAND);
			break;

		case '*':
			doType(KeyEvent.VK_ASTERISK);
			break;

		case '(':
			doType(KeyEvent.VK_LEFT_PARENTHESIS);
			break;

		case ')':
			doType(KeyEvent.VK_RIGHT_PARENTHESIS);
			break;

		case '_': {
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS);
			break;
		}
		case '+':
			doType(KeyEvent.VK_PLUS);
			break;

		case '\t':
			doType(KeyEvent.VK_TAB);
			break;

		case '\n':
			doType(KeyEvent.VK_ENTER);
			break;

		case '[':
			doType(KeyEvent.VK_OPEN_BRACKET);
			break;

		case ']':
			doType(KeyEvent.VK_CLOSE_BRACKET);
			break;

		case '\\':
			doType(KeyEvent.VK_BACK_SLASH);
			break;

		case '{':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
			break;

		case '}':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);
			break;

		case '|':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH);
			break;

		case ';':
			doType(KeyEvent.VK_SEMICOLON);
			break;

		case ':':
			doType(KeyEvent.VK_COLON);
			break;

		case '\'':
			doType(KeyEvent.VK_QUOTE);
			break;

		case '"':
			doType(KeyEvent.VK_QUOTEDBL);
			break;

		case ',':
			doType(KeyEvent.VK_COMMA);
			break;

		case '<':
			doType(KeyEvent.VK_LESS);
			break;

		case '.':
			doType(KeyEvent.VK_PERIOD);
			break;

		case '>': {
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD);
			break;
		}

		case '/':
			doType(KeyEvent.VK_SLASH);
			break;

		case '?':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH);
			break;

		case ' ':
			doType(KeyEvent.VK_SPACE);
			break;

		default:

			throw new IllegalArgumentException("Cannot type character " + character);

		}

	}

	private void doType(int... keyCodes)

	{

		doType(keyCodes, 0, keyCodes.length);

	}

	private void doType(int[] keyCodes, int offset, int length)

	{

		if (length == 0)

			return;

		robot.keyPress(keyCodes[offset]);

		doType(keyCodes, offset + 1, length - 1);

		robot.keyRelease(keyCodes[offset]);

	}

}
