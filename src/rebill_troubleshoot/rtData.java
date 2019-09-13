package rebill_troubleshoot;





public class rtData {
	String tinAmount,eligableAmount,testInputNbr,trkngnbr,eligable,type,ints,mig,srcOrg,device,oreStatus,invoiceNbr1,invoiceNbr2,rebillAcct,comments;
	String lparDate="";String singleComment="",timePeriodStatus="", fullComments="";
	int rowCounter;
	
	public rtData(int rowCounter,String tinAmount,String eligableAmount,String testInputNbr,String trkngnbr,String eligable,String type,String ints,String mig,String srcOrg,String device,String oreStatus,String invoiceNbr1,String invoiceNbr2,String rebillAcct,String comments) {
		this.rowCounter=rowCounter;
		this.tinAmount=tinAmount;
		this.eligableAmount=eligableAmount;
		this.testInputNbr=testInputNbr;
		this.trkngnbr=trkngnbr;
		this.eligable=eligable;
		this.type=type;
		this.ints=ints;
		this.mig=mig;
		this.srcOrg=srcOrg;
		this.device=device;
		this.oreStatus=oreStatus;
		this.invoiceNbr1=invoiceNbr1;
		this.invoiceNbr2=invoiceNbr2;
		this.rebillAcct=rebillAcct;
		this.comments=comments;
		this.lparDate="";
	}


	
	
	public int getRowCounter() {
		return rowCounter;
	}




	public void setRowCounter(int rowCounter) {
		this.rowCounter = rowCounter;
	}




	public String getTinAmount() {
		return tinAmount;
	}


	public void setTinAmount(String tinAmount) {
		this.tinAmount = tinAmount;
	}


	public String getEligableAmount() {
		return eligableAmount;
	}


	public void setEligableAmount(String eligableAmount) {
		this.eligableAmount = eligableAmount;
	}


	public String getTestInputNbr() {
		return testInputNbr;
	}


	public void setTestInputNbr(String testInputNbr) {
		this.testInputNbr = testInputNbr;
	}


	public String getTrkngnbr() {
		return trkngnbr;
	}


	public void setTrkngnbr(String trkngnbr) {
		this.trkngnbr = trkngnbr;
	}


	public String getEligable() {
		return eligable;
	}


	public void setEligable(String eligable) {
		this.eligable = eligable;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getInts() {
		return ints;
	}


	public void setInts(String ints) {
		this.ints = ints;
	}


	public String getMig() {
		return mig;
	}


	public void setMig(String mig) {
		this.mig = mig;
	}


	public String getSrcOrg() {
		return srcOrg;
	}


	public void setSrcOrg(String srcOrg) {
		this.srcOrg = srcOrg;
	}


	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}


	public String getOreStatus() {
		return oreStatus;
	}


	public void setOreStatus(String oreStatus) {
		this.oreStatus = oreStatus;
	}


	public String getInvoiceNbr1() {
		return invoiceNbr1;
	}


	public void setInvoiceNbr1(String invoiceNbr1) {
		this.invoiceNbr1 = invoiceNbr1;
	}


	public String getInvoiceNbr2() {
		return invoiceNbr2;
	}


	public void setInvoiceNbr2(String invoiceNbr2) {
		this.invoiceNbr2 = invoiceNbr2;
	}


	public String getRebillAcct() {
		return rebillAcct;
	}


	public void setRebillAcct(String rebillAcct) {
		this.rebillAcct = rebillAcct;
	}


	public String getComments() {
		return comments;
	}


	public void setFullComments(String fullComments) {
		this.fullComments = fullComments;
	}
	
	public String getFullComments() {
		return fullComments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	public void setLparDate(String lparDate) {
		this.lparDate=lparDate;
		
	}
	public String getLparDate() {
		
		return lparDate;
	}
	
	
	public void setSingleComment(String singleComment) {
		this.singleComment=singleComment;
		
	}
	public String getSingleComment() {
		
		return singleComment;
	}
	
	public void setTimeperiodStatus(String timePeriodStatus) {
		this.timePeriodStatus=timePeriodStatus;
		
	}
	public String getTimeperiodStatus() {
		return timePeriodStatus;
	}
}
