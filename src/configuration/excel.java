package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel {

	String filePath;
	File excelFile;
	FileInputStream excelInputStream;
	FileOutputStream excelOutputStream;
	XSSFWorkbook  excelWorkBook;
	XSSFSheet  excelSheet;
	XSSFCell cell = null;
	String cellData="a";
	int sheetNumber;
	XSSFRow row;
	 XSSFWorkbook newWorkbook;
	 XSSFSheet newSheet;
	
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
	
	public excel() {}
	
	
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
				cell=excelSheet.getRow(x).getCell(y);
		 
				cellData=cell.toString();
				System.out.println("Cell Data "+cellData);
			}
			catch(Exception e) {
				System.out.println(e);
				cellData="null";
				
			}
				return cellData;
		
	}
	
	public void setCellData(int x, int y, String data) {
		
		try {
		//System.out.println (data);
		if (data.equals("") || data==null) {
			data="null";
		}
		}
		catch(Exception e) {
			System.out.println(e);
			data="null";
			
		}
		System.out.println("Sheet Name"+ excelSheet.getSheetName());
		System.out.println(x);
		System.out.println(y);
		System.out.println("data" +data);
		//Row r;
		
		//Row row = excelSheet.getRow(x);
		//Cell cell = row.createCell(y); 
		
		//cell.setCellValue(data);
		
	
		try {
		row=excelSheet.getRow(x);
		cell = row.createCell(y);
		}
		catch(Exception ee) {
			row = excelSheet.createRow(x);
			cell = row.createCell(y);
		}
		cell.setCellValue(data);
		
		//c = excelSheet.getRow(x).createCell(y);
       // c = excelSheet.getRow(x).getCell(y);
       // c.setCellValue(data);
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
		
		
		while(!cellData.equals("") && cellData!=null && !cellData.equals("null")) {
			System.out.println("Inside Row Count Auto");
			try {
			getCellData(couter,y);
			rowCount++;
			couter++;
			}
			catch(NullPointerException e) {
				System.out.println("Hit Null");
			}
		}
		
		
	}
	
	public void setColCountAutomatically(int x) {
		
		int couter=0;
		cellData="a";
		colCount=-1;
		System.out.println("Inside Col Count Auto");
		while(!cellData.equals("") && cellData!=null && !cellData.equals("null")) {
			
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
	
	public void createExcelWorkbook() {
		  
			newWorkbook = new XSSFWorkbook();
			excelWorkBook=newWorkbook;
		

	}
	public void createSheet(String sheetName) {
		excelSheet = newWorkbook.createSheet(sheetName);
	}
	
	public void createSheetUD(String sheetName) {
		excelSheet = excelWorkBook.createSheet(sheetName);
	}
	
	
	public void deleteSheet(String sheetName) {
		excelWorkBook.removeSheetAt(excelWorkBook.getSheetIndex(sheetName));
	}
	
}
