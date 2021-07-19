package ThreadConfig;

public class ecData {
	String ecScenarioId,ecShipmentId,ecField, ecValue, ecValueComments;
	public ecData(String ecScenarioId,String ecShipmentId,String ecField,String ecValue,String ecValueComments) {
		this.ecScenarioId=ecScenarioId;
		this.ecShipmentId=ecShipmentId;
		this.ecField=ecField;
		this.ecValue=ecValue;
		this.ecValueComments=ecValueComments;
	}
	
	public String getEcScenarioId() {
		return ecScenarioId;
	}
	public void setEcScenarioId(String ecScenarioId) {
		this.ecScenarioId = ecScenarioId;
	}
	public String getEcShipmentId() {
		return ecShipmentId;
	}
	public void setEcShipmentId(String ecShipmentId) {
		this.ecShipmentId = ecShipmentId;
	}
	public String getEcField() {
		return ecField;
	}
	public void setEcField(String ecField) {
		this.ecField = ecField;
	}
	public String getEcValue() {
		return ecValue;
	}
	public void setEcValue(String ecValue) {
		this.ecValue = ecValue;
	}
	public String getEcValueComments() {
		return ecValueComments;
	}
	public void setEcValueComments(String ecValueComments) {
		this.ecValueComments = ecValueComments;
	}
	
	@Override
	public String toString() {
		return "ecData [ecScenarioId=" + ecScenarioId + ", ecShipmentId=" + ecShipmentId + ", ecField=" + ecField
				+ ", ecValue=" + ecValue + ", ecValueComments=" + ecValueComments + "]";
	}
}
