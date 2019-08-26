package rebill_troubleshoot;

import java.util.ArrayList;

import configuration.excel;
import rebill.rebillData;

public class rebillTroubleshoot {
	excel e;
	String homePath;
	ArrayList<rtData> rtDataArray = new ArrayList<rtData>();
	public rebillTroubleshoot() {
		
		
		 homePath=System.getProperty("user.dir");
	     
		
		//Giving my excel path from GUI (path saved in config class... was passed through gui/mouse class)
		e = new excel(homePath+"\\test data\\troubleshoot.xlsx");
		//Creates a workbook.
		e.setUpExcelWorkbook();
		//Sets up the sheet at the a particular index (0 = sheet 1)
		e.setUpExcelSheet(0);
		//Counts how much data there is. Parameter is which column it should count. This means will count
		//how many values/rows are there for column 3 (column 3 is never null.. so will get total rows).
		//This will exclude row 1 which is for headers.
		e.setRowCountAutomatically(4);
		//Get number of columns.
		e.setColCountAutomatically(0);
		
		//You can also give  a fixed number of rows/columns using e.setRowCountManually(x) and e.setColCountManually(x)
		
		//This will save all my data into objects from rebillData class. RebillData class will have
		//getters and setters for every column in excel sheet (tin, tin count, trk, reason code, etc).
		
		//I go through each row in excel and save that entire row into a new object and at same time
		//add that object into an array list. This array list will hold each object (row) of data.
		for (int i=1;i<e.getRowCount()+1;i++) {
			//Create new object and add it to my arraylist at same time.
			//uses the getCellData method which will use row,col paramter.
			rtDataArray.add( new rtData(e.getCellData(i, 2),e.getCellData(i, 3),e.getCellData(i, 4),e.getCellData(i, 5),e.getCellData(i, 6),e.getCellData(i, 7),e.getCellData(i+1, 8),e.getCellData(i, 9),e.getCellData(i, 10),e.getCellData(i, 11),e.getCellData(i, 12),e.getCellData(i+1, 13),e.getCellData(i, 14),e.getCellData(i, 15),e.getCellData(i, 16)));
		}
		//Closes the excel sheet.
		e.saveAndClose();
	}
}
