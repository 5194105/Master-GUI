package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel {

	String filePath;
	File excelFile;
	FileInputStream excelInputStream;
	FileOutputStream excelOutputStream;
	XSSFWorkbook  excelWorkBook;
	XSSFSheet  excelSheet;
	Cell c = null;
	String cellData;
	int sheetNumber;
	
	int rowCount,colCount;
	
	public excel(String filePath) {
		this.filePath=filePath;
		excelFile = new File(filePath);
		
		try {
			excelInputStream = new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void setUpExcelWorkbook() {
		
		try {
			excelWorkBook= new XSSFWorkbook(excelInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
	public void setUpExcelSheet(int x) {
		sheetNumber=x;
		excelSheet= excelWorkBook.getSheetAt(x);  
	}
	
	
	public void openExcel() {
		
		try {
			excelFile = new File(filePath);
			excelInputStream = new FileInputStream(excelFile);
			excelWorkBook= new XSSFWorkbook(excelInputStream);
			excelSheet= excelWorkBook.getSheetAt(sheetNumber);  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public String getCellData(int x, int y) {
		
			cellData="";
			try {
				c=excelSheet.getRow(x).getCell(y);
		 
				cellData=c.toString();
			}
			catch(Exception e) {
				System.out.println(e);
				cellData="null";
				
			}
				return cellData;
		
	}
	
	public void setCellData(int x, int y, String data) {
		
		c = excelSheet.getRow(x).createCell(y);
        c = excelSheet.getRow(x).getCell(y);
        c.setCellValue(data);
	}
	
	
	public void saveAndClose() {
		
		try {
			excelOutputStream =new FileOutputStream(excelFile);
			excelWorkBook.write(excelOutputStream);
			excelOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void setRowCountAutomatically(int y) {
		
		int couter=1;
		rowCount=-1;
		cellData="a";
		
		while(!cellData.equals("")) {
			System.out.println("Inside Row Count Auto");
			getCellData(couter,y);
			rowCount++;
			couter++;
		}
		
		
	}
	
	public void setColCountAutomatically(int x) {
		
		int couter=0;
		cellData="a";
		colCount=-1;
		System.out.println("Inside Col Count Auto");
		while(!cellData.equals("")) {
			
			getCellData(x,couter);
			colCount++;
			couter++;
			
		}
		
	}
	
	
	public void setRowCountManually(int rowCount) {
		this.rowCount=rowCount;
		
	}
	public void setColCountManually(int colCount) {
		this.colCount=colCount;
		
	}
	public int getRowCount() {
		return rowCount;
		
	}
	public int getColCount() {
		
		return colCount;
	}
	
}
