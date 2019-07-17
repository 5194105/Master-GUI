package configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import rebill.rebillData;

public class testStuff {

	static ArrayList<rebillData> arrayData = new ArrayList<rebillData>();
	
	public static void main(String[] args) {
		
		
		/*
		excel e = new excel("C:\\Users\\5194105\\Documents\\Eclipse Projects\\Master GUI\\test data\\rebill.xlsx");
		e.setUpExcelWorkbook();
		e.setUpExcelSheet(0);
		e.setRowCountAutomatically(2);
		e.setColCountAutomatically(0);
		System.out.println("Row Count : "+e.getRowCount());
		System.out.println("Col Count : "+e.getColCount());
		
		for (int i=1;i<e.getRowCount()+1;i++) {

			arrayData.add( new rebillData(e.getCellData(i, 0),e.getCellData(i, 1),e.getCellData(i, 2),e.getCellData(i, 3),e.getCellData(i, 4),e.getCellData(i, 5),e.getCellData(i, 6),e.getCellData(i, 7),e.getCellData(i+1, 8),e.getCellData(i, 9),e.getCellData(i, 10),e.getCellData(i, 11),e.getCellData(i, 12),e.getCellData(i+1, 13),e.getCellData(i, 14),e.getCellData(i, 15)));
		}
		e.saveAndClose();
		
		for (int i=0;i<arrayData.size();i++) {
		System.out.println(arrayData.get(i).getString());
	}
		for (int i=1;i<e.getRowCount()+1;i++) {
			e.openExcel();
			arrayData.get(i-1).setDescription("pass");
			e.setCellData(i, 1, arrayData.get(i-1).getDescription());
			e.saveAndClose();
		}
		System.out.println("The End");
		
		*/
	
		
		/*
		config c = new config();
		database db = new database(c.getGtmRevToolsConnection("GTM_REV_TOOLS", "Wr4l3pP5gWVd7apow8eZwnarI3s4e1"));
		db.setupDatabase("select * from rebill_regression where trkngnbr is not null");
		ResultSet rs;
		rs=db.getResultSet();
		String temp;
		try {
			while (rs.next()) {
				
				//temp = rs.getString(1);
				//System.out.println(temp);
				arrayData.add(new rebillData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
				
			}
			
			for (int i=0;i<arrayData.size();i++) {
				System.out.println(arrayData.get(i).getString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		
}
}
