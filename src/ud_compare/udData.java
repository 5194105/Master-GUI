package ud_compare;

public class udData {
	String scenario_id, shipment_id,  event_id,  tbl_name, col_name,  value, trkngnbr,actualStatus;
	
	public udData(String scenario_id,String shipment_id, String event_id, String tbl_name,String col_name, String value,String trkngnbr) {
		this.scenario_id=scenario_id;
		this.shipment_id=shipment_id;
		this.event_id=event_id;
		this.tbl_name=tbl_name;
		this.col_name=col_name;
		this.value=value;
		this.trkngnbr=trkngnbr;
	}
	
	public String getScenario_id() {
		return scenario_id;
	}
	public void setScenario_id(String scenario_id) {
		this.scenario_id = scenario_id;
	}
	public String getShipment_id() {
		return shipment_id;
	}
	public void setShipment_id(String shipment_id) {
		this.shipment_id = shipment_id;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getTbl_name() {
		return tbl_name;
	}
	public void setTbl_name(String tbl_name) {
		this.tbl_name = tbl_name;
	}
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public void setActualStatus(String actualStatus) {
		this.actualStatus=actualStatus;
		
	}
	public String getActualStatus() {
		return actualStatus;
	}
	
	
	public String getTrkngnbr() {
		return trkngnbr;
	}
	public void setTrkngnbr(String trkngnbr) {
		this.trkngnbr = trkngnbr;
	}
	
	@Override
	public String toString() {
		return "udData [scenario_id=" + scenario_id + ", shipment_id=" + shipment_id + ", event_id=" + event_id
				+ ", tbl_name=" + tbl_name + ", col_name=" + col_name + ", value=" + value + "]";
	}
	
}
