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
	ArrayList<String> nullTins = new  ArrayList<String>();
	Boolean ignoreBoolean=false;
	String tempTIN;
	String tempResult;
	String resultString;
	String lparDate;
	String trackTemp;
	String timePeriodStatus;
	String setLparDate="",setTimeperiodStatus="",setSingleComment="",setTinComments="";
	
	Boolean timePeriod,nonRM,notShipped,notDaily,nonDTT;
	Boolean tempNonRM;
	
	database db;
	
	
	Connection oreCon;
	Connection gtmCon;
	
	ResultSetMetaData rsmd;

	int columnsNumber,rowCounterSql;
	Boolean connectionClose=true;
	
	
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
		closeConnections();
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
			PreparedStatement ps;
			ResultSet rs;
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
				if (rtDataArray.get(i).getTrkngnbr().equals("null")) {
					rtDataArray.get(i).setSingleComment("No Trk Was Shipped");
					rtDataArray.get(i).setFullComments("No Trk Was Shipped");
				}
				else {
					tempTIN=rtDataArray.get(i).getTestInputNbr();
					for (int k=0;k<rtDataArray.size();k++) {
						if (rtDataArray.get(k).getTestInputNbr().equals(tempTIN)) {
							rtDataArrayTemp.add(rtDataArray.get(k));
							
						}
					}
					resetBooleans();
					doLogic();
					determineResult(tempTIN);
				}
					//results();
					usedData.add(tempTIN);
					System.out.println("RUN THROUGH THIS");
					
		}
			}
				
		results();
		
			//	if (ignoreBoolean==false && rtDataArrayTemp.isEmpty()==true) {
			//		rtDataArrayTemp.add(rtDataArray.get(i));
			//		break;
			//	}
	}
	
	public void doLogic() {
		
		for (int i=0;i<rtDataArrayTemp.size();i++) {
				trackTemp="";

			if (rtDataArrayTemp.get(i).getEligable().equals("Y")) {
				
					setSingleComment="Is Eligable";
			}
			
			else {
				
			
			if(rtDataArrayTemp.get(i).getDevice().indexOf("DTT")!=-1) {
				
				setSingleComment="DTT Trk";

			}
			else {
				
				if(rtDataArrayTemp.get(i).getOreStatus().equals("RM")) {
					
					setLparDate=getLparDate(rtDataArrayTemp.get(i).getTrkngnbr());
					}

				 if(!rtDataArrayTemp.get(i).getOreStatus().equals("RM") && !rtDataArrayTemp.get(i).getOreStatus().equals("Y")){
					
					setSingleComment="Not in RM or Y Status";
				}
			}
		}
			
			
		
			if (rtDataArrayTemp.get(i).getOreStatus().equals("RM")|| rtDataArrayTemp.get(i).getOreStatus().equals("Y")){
				if(rtDataArrayTemp.get(i).getDevice().indexOf("DTT")==-1) {
					nonRM=false;
				}	
			}
			
			if(rtDataArrayTemp.get(i).getDevice().indexOf("DTT")==-1) {
				nonDTT=true;
			
			}
			
			
			setTimeperiodStatus=getTimeperiodStatus(rtDataArrayTemp.get(i).getTrkngnbr());
			
			for (int q=0;q<rtDataArray.size();q++) {
				if (rtDataArray.get(q).getTrkngnbr().equals(rtDataArrayTemp.get(i).getTrkngnbr())) {
					rtDataArray.get(q).setLparDate(setLparDate);
					rtDataArray.get(q).setTimeperiodStatus(timePeriodStatus);
					rtDataArray.get(q).setSingleComment(setSingleComment);
					}
				}
			 setLparDate="";
			 setTimeperiodStatus="";
			 setSingleComment="";
			 setTinComments="";
		
		}
	}
	
	
	
	public String getLparDate(String trk){
		lparDate="";
		try {
			
				PreparedStatement ps;
				ResultSet rs;
			
		System.out.println("TRACKING NUMBER "+trk);
		//ps=oreCon.prepareStatement("select distinct LPAR_ENHCMNT_DT from INTL_EXPRS_ONLN_SCHEMA.INTL_package a join INTL_EXPRS_ONLN_SCHEMA.INTL_package_event b on b.ONLN_PKG_ID=a.ONLN_PKG_ID where pkg_trkng_nbr='794945248595'");
		ps=oreCon.prepareStatement("select distinct LPAR_ENHCMNT_DT from INTL_EXPRS_ONLN_SCHEMA.INTL_package a join INTL_EXPRS_ONLN_SCHEMA.INTL_package_event b on b.ONLN_PKG_ID=a.ONLN_PKG_ID where pkg_trkng_nbr=?");
		ps.setString(1, trk);
		//ps=oreL3Con.prepareStatement("select * from INTL_EXPRS_ONLN_SCHEMA.INTL_package");
		rs=ps.executeQuery();
		if (rs.next() == false) { 

			ps=oreCon.prepareStatement("select distinct LPAR_ENHCMNT_DT from DOM_EXPRS_ONLN_SCHEMA.package a join DOM_EXPRS_ONLN_SCHEMA.package_event b on b.ONLN_PKG_ID=a.ONLN_PKG_ID where pkg_trkng_nbr=?");
			ps.setString(1, trk);
			rs=ps.executeQuery();
			
			if (rs.next() == false) { 
				lparDate="Not Found";
			}
			else {
				lparDate="LPAR DATE = "+rs.getString(1).toString();
				System.out.println(lparDate);
				
			}
			
			} else {
		
		lparDate="LPAR DATE = "+rs.getString(1).toString();
		System.out.println(lparDate);
			}
		ps.close();
		rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
			lparDate="Unknown SQL Error";
			System.out.println(lparDate);
		
					}
		

		return lparDate;
		
	}
	
	
	public String getTimeperiodStatus(String trk) {
		
		
		timePeriodStatus="";
		try {
			PreparedStatement ps;
			ResultSet rs;
		ps=oreCon.prepareStatement("select distinct case when cntry_cd is null then 'N' else 'Y' END as TimePeriodEligble from INTL_EXPRS_ONLN_SCHEMA.INTL_package b join INTL_EXPRS_ONLN_SCHEMA.intl_onln_cust_addr_info d on b.ONLN_REV_ITEM_ID=d.ONLN_REV_ITEM_ID left join INTL_EXPRS_ONLN_SCHEMA.time_period_country e on d.CUST_CNTRY_CD=e.cntry_cd where pkg_trkng_nbr=? and CUST_ROLE_TYPE_CD='B'");
		ps.setString(1, trk);
		rs=ps.executeQuery();
		if (rs.next() == false) { 

			ps=oreCon.prepareStatement("select distinct case when cntry_cd is null then 'N' else 'Y' END as TimePeriodEligble from INTL_EXPRS_ONLN_SCHEMA.INTL_package b join INTL_EXPRS_ONLN_SCHEMA.intl_onln_cust_addr_info d on b.ONLN_REV_ITEM_ID=d.ONLN_REV_ITEM_ID left join INTL_EXPRS_ONLN_SCHEMA.time_period_country e on d.CUST_CNTRY_CD=e.cntry_cd where pkg_trkng_nbr=? and CUST_ROLE_TYPE_CD='B'");
			ps.setString(1, trk);
			rs=ps.executeQuery();
			
			if (rs.next() == false) { 
				timePeriodStatus="Not Found";
			}
			else {
				timePeriodStatus=rs.getString(1).toString();
				
			}
			
			} else {
		
				timePeriodStatus=rs.getString(1).toString();
			}
		ps.close();
		rs.close();
		}
		catch(Exception e) {
			timePeriodStatus="Unknown SQL Error";
					}
	
		return timePeriodStatus;
		
	}
	
	
	public void closeConnections() {
		try {
	
			oreCon.close();
			gtmCon.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void determineResult(String tempTin){
		
		resultString="";
		
		if (nonRM==true) {
					
					resultString+="No RM Device Trks ";
				}
		if (nonDTT==false) {
			
			resultString+="All DTT Tracks ";
		}
		if (notShipped==false && nonRM==false &&  nonDTT==true) {
			
			resultString+="Check Execution Date ";
		}
		
		
		for (int q=0;q<rtDataArray.size();q++) {
			if (rtDataArray.get(q).getTestInputNbr().equals(tempTin)) {
				rtDataArray.get(q).setFullComments(resultString);
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
		/*
		for (int i=0;i<rtDataArrayTemp.size();i++) {
			e.setCellData(rtDataArrayTemp.get(i).getRowCounter(),16,rtDataArrayTemp.get(i).getTimeperiodStatus());
			e.setCellData(rtDataArrayTemp.get(i).getRowCounter(),18,resultString);
			if (rtDataArrayTemp.get(i).getSingleComment()!=null || !rtDataArrayTemp.get(i).getSingleComment().equals("")) {
				if(rtDataArrayTemp.get(i).getLparDate().equals("") || rtDataArrayTemp.get(i).getLparDate()==null) {
				e.setCellData(rtDataArrayTemp.get(i).getRowCounter(),17,rtDataArrayTemp.get(i).getSingleComment());
				}
				else {
					e.setCellData(rtDataArrayTemp.get(i).getRowCounter(),17,rtDataArrayTemp.get(i).getLparDate()+ "  "+rtDataArrayTemp.get(i).getSingleComment());
					
				}
				
			}
			}
		*/
		
		
		for (int i=0;i<rtDataArray.size();i++) {
			e.setCellData(rtDataArray.get(i).getRowCounter(),16,rtDataArray.get(i).getTimeperiodStatus());
			e.setCellData(rtDataArray.get(i).getRowCounter(),18,resultString);
			if (rtDataArray.get(i).getSingleComment()!=null || !rtDataArray.get(i).getSingleComment().equals("")) {
				if(rtDataArray.get(i).getLparDate().equals("") || rtDataArray.get(i).getLparDate()==null) {
				e.setCellData(rtDataArray.get(i).getRowCounter(),17,rtDataArray.get(i).getSingleComment());
				}
				else {
					e.setCellData(rtDataArray.get(i).getRowCounter(),17,rtDataArray.get(i).getLparDate()+ "  "+rtDataArray.get(i).getSingleComment());
					
				}
				
			}
			}
		
		e.saveAndClose();
	
	}
	

	}
	

