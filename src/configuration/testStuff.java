package configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import rebill.rebillData;

public class testStuff {

excel e;
	public testStuff(config c) {
		e = new excel("C:\\Users\\5194105\\Documents\\Eclipse Projects\\Master GUI\\test data\\UD_COMPARE.xlsx");
		e.setUpExcelWorkbook();
		try {
		e.createSheetUD("test");
		}
		catch(java.lang.IllegalArgumentException ee) {
			e.deleteSheet("test");
			e.createSheetUD("test");
		}
		
		e.setCellData(1, 1, "ABA");
		
}
}
