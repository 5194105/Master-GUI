package rebill_troubleshoot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import configuration.config;
import configuration.database;
import configuration.excel;
import rebill.rebillData;

public class rebillTroubleshoot {
	excel e;
	String homePath;
	ArrayList<rtData> rtDataArray = new ArrayList<rtData>();
	config c;
	Connection ciCon;
	ArrayList<rtData> rtDataArrayTemp = new ArrayList<rtData>();
	ArrayList<String> usedData = new  ArrayList<String>();
	Boolean ignoreBoolean=false;
	String tempTIN;
	String tempResult;
	String resultString;
	String lparDate;
	String trackTemp;
	
	String LEVELS;
	String CYCLE;
	String TIN_AMOUNT;
	String ELIGABLE_AMOUNT;
	String TEST_INPUT_NBR;
	String TRKNGNBR;
	String ELIGABLE	;
	String TYPE	;
	String INT	;
	String MIG	;
	String SRC_ORG	;
	String DEVICE	;
	String ORE_STATUS	;
	String INVOICE_NBR_1	;
	String INVOICE_NBR_2	;
	String REBILL_ACCT	;
	String COMMENTS;
	
	Boolean timePeriod,nonRM,notShipped,notDaily,nonDTT;
	Boolean tempNonRM;
	ResultSet rs;
	database db;
	PreparedStatement ps;
	
	Connection oreCon;
	Connection gtmCon;
	
	ResultSetMetaData rsmd;

	int columnsNumber,rowCounterSql;
	
	
	public rebillTroubleshoot(config c) throws ClassNotFoundException {
		c.setLevel(true);
		if (c.getLevel()==true){
			c.setOreL3DbConnection();
			oreCon=c.getOreL3DbConnection();
		}
		else if (c.getLevel()==false){
			//c.setOreL2DbConnection();
			oreCon=c.getOreL2DbConnection();
		}
	
	
		
		this.c=c;
		//ciCon=c.getCiDbConnection();
		 homePath=System.getProperty("user.dir");
		 e = new excel();
		 getDBData();
		
		 
		
		//Giving my excel path from GUI (path saved in config class... was passed through gui/mouse class)
		e = new excel(homePath+"\\test data\\rebilltroubleshoottest.xlsx");
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
			rtDataArray.add( new rtData(i,e.getCellData(i, 2),e.getCellData(i, 3),e.getCellData(i, 4),e.getCellData(i, 5),e.getCellData(i, 6),e.getCellData(i, 7),e.getCellData(i+1, 8),e.getCellData(i, 9),e.getCellData(i, 10),e.getCellData(i, 11),e.getCellData(i, 12),e.getCellData(i+1, 13),e.getCellData(i, 14),e.getCellData(i, 15),e.getCellData(i, 16)));
		}
		//Closes the excel sheet.
		addTempArray();
		System.out.println("End of Rebill TroubleShoot");
		
		
		
	}
	
	public void getDBData() {
		
		try {
			createExcelSheet() ;
			gtmCon=c.getGtmRevToolsConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ps=gtmCon.prepareStatement("select LEVELS,	CYCLE,	TIN_AMOUNT,	ELIGABLE_AMOUNT	,TEST_INPUT_NBR	,TRKNGNBR	,ELIGABLE,	TYPE,	INT,	MIG	,SRC_ORG,	DEVICE,	ORE_STATUS,	INVOICE_NBR_1,	INVOICE_NBR_2	,REBILL_ACCT from rebill_Troubleshoot order by test_input_nbr");
			rs=ps.executeQuery();
			rsmd = rs.getMetaData();
			columnsNumber = rsmd.getColumnCount();
			rowCounterSql=0;
			while (rs.next()) { 
				rowCounterSql++;
				
				for (int i=1;i<17;i++) {
					System.out.print(rs.getString(i)+"   ");
					//System.out.println("Counter "+rowCounterSql);
			e.setCellData(rowCounterSql, i-1, rs.getString(i));
		//	checkNull();
		}
				
		
						
					//Create new object and add it to my arraylist at same time.
					//uses the getCellData method which will use row,col paramter.
					//rtDataArray.add(new rtData(i,e.getCellData(i, 2),e.getCellData(i, 3),e.getCellData(i, 4),e.getCellData(i, 5),e.getCellData(i, 6),e.getCellData(i, 7),e.getCellData(i+1, 8),e.getCellData(i, 9),e.getCellData(i, 10),e.getCellData(i, 11),e.getCellData(i, 12),e.getCellData(i+1, 13),e.getCellData(i, 14),e.getCellData(i, 15),e.getCellData(i, 16)));
					
				
			//	rtDataArray.add(new rtData(rowCounterSql,rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),""));

				
				System.out.println();
			}
			e.saveAndClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void checkNull() {
		
		
		
		LEVELS=rs.getString(1);
		CYCLE
		TIN_AMOUNT
		ELIGABLE_AMOUNT
		TEST_INPUT_NBR
		TRKNGNBR
		ELIGABLE	
		TYPE	
		INT	
		MIG	
		SRC_ORG	
		DEVICE	
		ORE_STATUS	
		INVOICE_NBR_1	
		INVOICE_NBR_2	
		REBILL_ACCT	
		COMMENTS

		
	}
	
	public void createExcelSheet() {
		
		e = new excel(homePath+"\\test data\\rebilltroubleshoottest.xlsx");
		//Creates a workbook.
		e.setUpExcelWorkbook();
		//Sets up the sheet at the a particular index (0 = sheet 1)
		e.setUpExcelSheet(0);
		//e.createExcelWorkbook();
		//e.createSheet("rebill troubleshoot");
		//e.openExcel();
		
	}
	
	
	public void addTempArray() {
		for (int i=0;i<rtDataArray.size();i++) {
			ignoreBoolean=false;
			tempTIN="";
			rtDataArrayTemp.clear();
			for (int j=0;j<usedData.size();j++) {
				if (rtDataArray.get(i).getTestInputNbr().equals(usedData.get(j))) {
					ignoreBoolean=true;
				}
			}
			
			if (ignoreBoolean==false){
					tempTIN=rtDataArray.get(i).getTestInputNbr();
					for (int k=0;k<rtDataArray.size();k++) {
						if (rtDataArray.get(k).getTestInputNbr().equals(tempTIN)) {
							rtDataArrayTemp.add(rtDataArray.get(k));
							
						}
					}
					resetBooleans();
					doLogic();
					determineResult();
					results();
					usedData.add(tempTIN);
					System.out.println("RUN THROUGH THIS");
					
		}
			}
				
			//	if (ignoreBoolean==false && rtDataArrayTemp.isEmpty()==true) {
			//		rtDataArrayTemp.add(rtDataArray.get(i));
			//		break;
			//	}
	}
	
	public void doLogic() {
		
		for (int i=0;i<rtDataArrayTemp.size();i++) {
			trackTemp="";
			
			//TRK RESULT
			if(rtDataArrayTemp.get(i).getDevice().indexOf("DTT")!=-1) {
				
				System.out.println("DTT Track");
				for (int q=0;q<rtDataArray.size();q++) {
					if (rtDataArray.get(q).getTrkngnbr().equals(rtDataArrayTemp.get(i).getTrkngnbr())) {
				rtDataArray.get(q).setSingleComment("DTT Trk");
					}
				}
			}
			else {
				
				if(rtDataArrayTemp.get(i).getOreStatus().equals("RM")) {
					;
						for (int q=0;q<rtDataArray.size();q++) {
							if (rtDataArray.get(q).getTrkngnbr().equals(rtDataArrayTemp.get(i).getTrkngnbr())) {
								rtDataArray.get(q).setLparDate(getLparDate(rtDataArrayTemp.get(i).getTrkngnbr()));
								rtDataArray.get(q).setSingleComment(lparDate);
								
							}
						}
					}
				}
			
			
			
			
			
			//TIN RESULT
			if(rtDataArrayTemp.get(i).getTrkngnbr().equals("null")) {
				notShipped=true;
			}
			
			if (rtDataArrayTemp.get(i).getOreStatus().equals("RM")|| rtDataArrayTemp.get(i).getOreStatus().equals("Y")){
				if(rtDataArrayTemp.get(i).getDevice().indexOf("DTT")==-1) {
					nonRM=false;
				}	
			}
			
			if(rtDataArrayTemp.get(i).getDevice().indexOf("DTT")==-1) {
				nonDTT=true;
			
			}
			
		}
	}
	
	
	
	public String getLparDate(String trk){
		lparDate="";
		try {
		ps=oreCon.prepareStatement("select distinct LPAR_ENHCMNT_DT from INTL_EXPRS_ONLN_SCHEMA.INTL_package a join INTL_EXPRS_ONLN_SCHEMA.INTL_package_event b on b.ONLN_PKG_ID=a.ONLN_PKG_ID where pkg_trkng_nbr='549055903277'");
		//ps=oreL3Con.prepareStatement("select * from INTL_EXPRS_ONLN_SCHEMA.INTL_package");
		rs=ps.executeQuery();
		if (rs.next() == false) { 

			ps=oreCon.prepareStatement("select distinct LPAR_ENHCMNT_DT from DOM_EXPRS_ONLN_SCHEMA.package a join DOM_EXPRS_ONLN_SCHEMA.package_event b on b.ONLN_PKG_ID=a.ONLN_PKG_ID where pkg_trkng_nbr='549055903277'");
			rs=ps.executeQuery();
			
			if (rs.next() == false) { 
				lparDate="Not Found";
			}
			else {
				lparDate="LPAR DATE = "+rs.getString(1).toString();
				
			}
			
			} else {
		
		lparDate="LPAR DATE = "+rs.getString(1).toString();
			}
		}
		catch(Exception e) {
			lparDate="Unknown SQL Error";
					}
		
		return lparDate;
		
	}
	
	public void determineResult(){
		
		resultString="";
		if (notShipped==true) {
			
			resultString+="No Track Was Shipped ";
		}
		else {
		if (nonRM==true) {
					
					resultString+="No RM Device Trks ";
				}
		if (nonDTT==false) {
			
			resultString+="All DTT Tracks ";
		}
		if (notShipped==false && nonRM==false &&  nonDTT==true) {
			
			resultString+="Check Execution Date ";
		}
		}
		
	}
	
	public void resetBooleans() {
		tempNonRM=true;
		timePeriod=false;
		nonRM=true;
		notShipped=false;
		notDaily=false;
		nonDTT=false;
		
	}
	
	public void results() {
		
		tempResult="";
		
		if (notShipped==true) {
			
			tempResult+="Was Not Shipped";
		}
		
		for (int i=0;i<rtDataArrayTemp.size();i++) {
			e.setCellData(rtDataArrayTemp.get(i).getRowCounter(),17,resultString);
			if (rtDataArrayTemp.get(i).getSingleComment()!=null || !rtDataArrayTemp.get(i).getSingleComment().equals("")) {
				e.setCellData(rtDataArrayTemp.get(i).getRowCounter(),16,rtDataArrayTemp.get(i).getSingleComment());
				
			}
			}
		e.saveAndClose();
	}
	

	}
	

