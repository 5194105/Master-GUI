package ud_compare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import configuration.config;
import configuration.excel;

public class udcompare {
	config c;
	Connection taa1Con;
	Connection oreCon;
	ArrayList<udData> udDataArray = new ArrayList<udData>();
	
	
	String scenario_id, shipment_id,event_id,tbl_name,	col_name,value; 
	String trkngnbr;
	String domesticSchema="DOM_EXPRS_ONLN_SCHEMA.";
	String intlSchema="INTL_EXPRS_ONLN_SCHEMA.";
	String level,cycle;
	excel e;
	int shipmentCounter=0;
	
	
	public udcompare(config c) {
		this.c=c;
		taa1Con=c.getTaa1DbConnection();
		c.setHomePath("C:\\Users\\5194105\\Documents\\Eclipse Projects\\Master GUI");
		scenario_id="1615";
		shipment_id="1";
		c.setLevel(true);
		c.setCycle("10");
		
		
		
		if(c.getLevel()==true) {
			level="3";
		}
		else {
			level="2";
		}
		
		cycle=c.getCycle();
		
		
		
		c.setLevel(true);
		if (c.getLevel()==true){
			c.setOreL3DbConnection();
			oreCon=c.getOreL3DbConnection();
		}
		else if (c.getLevel()==false){
			//c.setOreL2DbConnection();
			oreCon=c.getOreL2DbConnection();
		}
		
		
		
		
		setUpExcel();
		addResultBaseData();
	//	getScenarioTrk();
	//	getOreData();
		closeConnections();
		writeToExcel();
		System.out.println("Finished");
	}
	
	
	
	
	
	
	
	public void addResultBaseData() {
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=taa1Con.prepareStatement("select SCENARIO_ID, SHIPMENT_ID, EVENT_ID, TBLNAME, COLNAME, VALUE from rtm.ri_event_result where scenario_id=?");
			ps.setString(1, "2209");
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		try {
			while(rs.next()) {
			try {
			
				scenario_id=rs.getString(1);
				shipment_id=rs.getString(2);
				event_id=rs.getString(3);
				tbl_name=rs.getString(4);
				col_name=rs.getString(5);
				
				try {
				value=rs.getString(6); 
				}
				catch(NullPointerException e) {
					value="null";
				}
				
				if (Integer.parseInt(shipment_id)!=shipmentCounter) {
					
					getOreData();
					shipmentCounter=Integer.parseInt(shipment_id);
					
				}
							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
				
				catch (Exception e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}
			
			
			udDataArray.add(new udData(scenario_id, shipment_id,event_id,tbl_name,col_name,value,trkngnbr)); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		
	}


	
	
	public void closeConnections() {
		try {
			taa1Con.close();
			oreCon.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	

	public void setUpExcel() {
		e = new excel(c.getHomePath()+"\\test data\\UD_COMPARE.xlsx");
		e.setUpExcelWorkbook();
		try {
		e.createSheetUD(scenario_id);
		}
		catch(java.lang.IllegalArgumentException ee) {
			e.deleteSheet(scenario_id);
			e.createSheetUD(scenario_id);
		}
		
	}
	
	public void writeToExcel() {
		
		for (int i=0;i<udDataArray.size();i++) {
			e.setCellData(i+1,0,udDataArray.get(i).getScenario_id());
			e.setCellData(i+1,1,udDataArray.get(i).getShipment_id());
			e.setCellData(i+1,2,udDataArray.get(i).getEvent_id());
			e.setCellData(i+1,3,udDataArray.get(i).getTbl_name());
			e.setCellData(i+1,4,udDataArray.get(i).getCol_name());
			e.setCellData(i+1,5,trkngnbr);
			e.setCellData(i+1,6,udDataArray.get(i).getValue());
			e.setCellData(i+1,7,udDataArray.get(i).getActualStatus());
			
		}
		
		e.saveAndClose();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void getOreData() {
		
		for (int i=0;i<udDataArray.size();i++) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		col_name=udDataArray.get(i).getCol_name();
		tbl_name=udDataArray.get(i).getTbl_name();
		
		
		
		try {
			try {
			ps=oreCon.prepareStatement("select distinct a."+udDataArray.get(i).getCol_name()+" from "+intlSchema+udDataArray.get(i).getTbl_name()+" a join INTL_EXPRS_ONLN_SCHEMA.intl_package b on on a.ONLN_REV_ITEM_ID = b.ONLN_REV_ITEM_ID where pkg_trkng_nbr='"+udDataArray.get(i).getTrkngnbr()+"'");

			rs=ps.executeQuery();
				} catch (SQLException e) {
				ps=oreCon.prepareStatement("select distinct a."+udDataArray.get(i).getCol_name()+" from "+intlSchema+udDataArray.get(i).getTbl_name()+" a join INTL_EXPRS_ONLN_SCHEMA.intl_package b on a.ONLN_PKG_ID = b.ONLN_PKG_ID where pkg_trkng_nbr='"+udDataArray.get(i).getTrkngnbr()+"'");

				rs=ps.executeQuery();
			
				}
			} catch (Exception e) {
				udDataArray.get(i).setActualStatus("Could not find value");
				
		}
		
		try {
			
			while(rs.next()) {
				//System.out.println(col_name + ": "+rs.getString(1));
				//System.out.println(col_name +" "+rs.getString(1));
				udDataArray.get(i).setActualStatus(rs.getString(1));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			udDataArray.get(i).setActualStatus("Could not find value");
		}
		
		
		
		
		try {
			rs.close();
			ps.close();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
	}
	}
	
	
	public void getScenarioTrk() {
		PreparedStatement ps=null;
		ResultSet rs=null;
		System.out.println("GETTING TRK");
		try {
			ps=taa1Con.prepareStatement("select trkngnbr from ( select a.scenario_id, a.trkngnbr, d.shipment_id, c.item_prcs_cd, c.last_updt_dt, ROW_NUMBER()OVER(Partition BY a.scenario_id,d.shipment_id Order By c.last_updt_dt DESC)AS rowcount from rtm.ri_shipment d join rtm.shipping_results a on d.test_input_nbr=a.test_input_nbr join intl_EXPRS_ONLN_SCHEMA.intl_package@L3_ORE b on b.PKG_TRKNG_NBR=a.trkngnbr join intl_EXPRS_ONLN_SCHEMA.intl_online_revenue_item@L3_ORE c on b.onln_rev_item_id=c.onln_rev_item_id where levels in ('"+level+"') and cycle in ('"+cycle+"') and a.device='UD' and d.scenario_id='"+scenario_id+"' and d.shipment_id='"+shipment_id+"') where rowcount='1'");
			rs=ps.executeQuery();
			
				while(rs.next()) {
					
					System.out.println("HOST_NM: "+rs.getString(1));
					trkngnbr=rs.getString(1);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DONE TRK");
	}
	
}
	
	

