package ThreadTest;

public class data {
	String result, description, testInputNbr, tinCount, trkngnbr, reasonCode,  billAcctNbr, invoiceNbr1, invoiceNbr2, region, username, password,  rs_type, company, rebillPrerate,  workable, defectFlg, defectNbr;
	String prerateTypeCd,  prerateAmt, currencyCd, approvalId, chrgCd1, chrgAmt1, chrgCd2,  chrgAmt2, chrgCd3, chrgAmt3,  chrgCd4, chrgAmt4;
	String rowcount;
	int counter;
	String rateWeight, actualWeight, wgtType,  length, width, height,   dimType, payor;
	String billActNbr, serviceType, packageType, rerateType, rsType,  valDesc, comments;
	String massRerateCombo;
	String creditFlg, debitFlg,  disputeFlg, resolveCreditFlg,rootCause,reasonCategory;
	String payorAcctNbr, itemPrcsCd, instantInvFlg,tinComment,podScan;
	String acct1, acct2,  trkngnbr1, trkngnbr2, service1, service2, requestType, acctType, acctName;
	//Function Type
	// 1 Single Rebill
	// 2 Mass Rebill
	// 3 Single Rerate
	// 4 Mass Rerate
	// 5 Credit Debit
	// 6 Instant Invoice
	// 7 Prerate Single
	// 8 Prerate Hold
	// 9 PRS Rerate
	
	//Rebill Single
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String reasonCode, String billAcctNbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password, String rs_type,String company,String rebillPrerate, String workable,String defectFlg,String defectNbr,int counter) {
		this.result=result;
		this.description=description;
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.trkngnbr=trkngnbr;
		this.reasonCode=reasonCode;
		this.billAcctNbr=billAcctNbr; 
		this.invoiceNbr1=invoiceNbr1;
		this.invoiceNbr2=invoiceNbr2;
		this.region=region;
		this.username=username;
		this.password=password;
		this.rs_type=rs_type;
		this.company=company;
		this.rebillPrerate=rebillPrerate;  
		this.workable=workable;
		this.defectFlg=defectFlg;
		this.defectNbr=defectNbr;
		this.counter=counter;
	}
	
	  // Rebill Mass
	public data(String result, String description,String testInputNbr,String rowcount,String trkngnbr,String reasonCode, String billAcctNbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password, String rs_type,String company,int counter) {
		this.result=result;
		this.description=description;
		this.trkngnbr=trkngnbr;
		this.rowcount=rowcount;
		this.reasonCode=reasonCode;
		this.billAcctNbr=billAcctNbr; 
		this.invoiceNbr1=invoiceNbr1;
		this.invoiceNbr2=invoiceNbr2;
		this.region=region;
		this.username=username;
		this.password=password;
		this.rs_type=rs_type;
		this.company=company;
		this.counter=counter;
	}
	
	
	
	//Single Rerate
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String rateWeight,String actualWeight,String wgtType, String length,String width,String height, String workable,String dimType,String payor,String billAcctNbr,String serviceType,String packageType,String rerateType,String region,String username,String password,String rsType,String company,String valDesc,String comments,int counter) {
		this.result=result;
		this.description=description;
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.trkngnbr=trkngnbr;
		this.invoiceNbr1=invoiceNbr1;
		this.invoiceNbr2=invoiceNbr2;
		this.rateWeight=rateWeight;
		this.actualWeight=actualWeight;
		this.wgtType=wgtType;
		this.length=length;
		this.width=width;
		this.workable=workable;
		this.dimType=dimType;
		this.payor=payor;
		this.billAcctNbr=billAcctNbr;
		this.serviceType=serviceType;
		this.packageType=packageType;
		this.rerateType=rerateType;
		this.region=region;
		this.username=username;
		this.password=password;
		this.rsType=rsType;
		this.company=company;
		this.valDesc=valDesc;
		this.comments=comments;
	
		
	
	}
	
	//Mass Rerate
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password,String rateWeight,String length, String height,String width,String dimType, String rerateType,String rsType,String company,String massRerateCombo,int counter) {
		this.result=result;
		this.description=description;
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.trkngnbr=trkngnbr;
		this.invoiceNbr1=invoiceNbr1;
		this.invoiceNbr2=invoiceNbr2;
		this.rateWeight=rateWeight;
		this.length=length;
		this.width=width;
		this.dimType=dimType;
		this.rerateType=rerateType;
		this.region=region;
		this.username=username;
		this.password=password;
		this.rsType=rsType;
		this.company=company;
		this.massRerateCombo=massRerateCombo;
	}
	
	//Credit/Debit

	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password,String creditFlg,String debitFlg, String disputeFlg,String resolveCreditFlg,String workable, String reasonCode,String reasonCategory,String rootCause,String valDesc,int counter) {
		this.result=result;
		this.description=description;
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.trkngnbr=trkngnbr;
		this.invoiceNbr1=invoiceNbr1;
		this.invoiceNbr2=invoiceNbr2;
		this.region=region;
		this.username=username;
		this.password=password;
		this.creditFlg=creditFlg;
		this.debitFlg=debitFlg;
		this.disputeFlg=disputeFlg;
		this.resolveCreditFlg=resolveCreditFlg;
		this.workable=workable;
		this.reasonCode=reasonCode;
		this.reasonCategory=reasonCategory;
		this.rootCause=rootCause;
		this.valDesc=valDesc;
	
	}
	
	
	
	//Instant Invoice
	public data(String testInputNbr,String trkngnbr,String payorAcctNbr,String itemPrcsCd,String instantInvFlg) {
		this.testInputNbr=testInputNbr;
		this.trkngnbr=trkngnbr;
		this.payorAcctNbr=payorAcctNbr;
		this.itemPrcsCd=itemPrcsCd;
		this.instantInvFlg=instantInvFlg;
		
	}
	
	
	
	//Prerate Single
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String prerateTypeCd, String prerateAmt,String currencyCd,String approvalId,String chrgCd1,String chrgAmt1,String chrgCd2, String chrgAmt2,String chrgCd3,String chrgAmt3, String chrgCd4,String chrgAmt4,int counter) {
		this.result=result;
		this.description=description;
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.trkngnbr=trkngnbr;
		this.prerateTypeCd=prerateTypeCd;
		this.prerateAmt=prerateAmt;
		this.currencyCd=currencyCd;
		this.approvalId=approvalId;
		this.chrgCd1=chrgCd1;
		this.chrgAmt1=chrgAmt1;
		this.chrgCd2=chrgCd2;
		this.chrgAmt2=chrgAmt2;
		this.chrgCd3=chrgCd3;
		this.chrgAmt3=chrgAmt3;
		this.chrgCd4=chrgCd4;
		this.chrgAmt4=chrgAmt4;
		this.counter=counter;
	}
	
	
	//Prerate Hold
	public data(String result, String description,String podScan,String testInputNbr,String tinCount,String trkngnbr,String tinComment) {
		this.result=result;
		this.description=description;
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.trkngnbr=trkngnbr;
		this.podScan=podScan;
		this.tinComment=tinComment;
		
	}
	
	//Prs rerate
	
	public data(String testInputNbr, String tinCount,String acct1,String acct2, String trkngnbr1,String trkngnbr2, String invoiceNbr1,String invoiceNbr2, String service1,String service2,String requestType,String acctType,String acctName) {
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.acct1=acct1;
		this.acct2=acct2;
		this.trkngnbr1=trkngnbr1;
		this.trkngnbr2=trkngnbr2;
		this.invoiceNbr1=invoiceNbr1;
		this.invoiceNbr2=invoiceNbr2;
		this.service1=service1;
		this.service2=service2;
		this.requestType=requestType;
		this.acctType=acctType;
		this.acctName=acctName;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTestInputNbr() {
		return testInputNbr;
	}
	public void setTestInputNbr(String testInputNbr) {
		this.testInputNbr = testInputNbr;
	}
	public String getTinCount() {
		return tinCount;
	}
	public void setTinCount(String tinCount) {
		this.tinCount = tinCount;
	}
	public String getTrkngnbr() {
		return trkngnbr;
	}
	public void setTrkngnbr(String trkngnbr) {
		this.trkngnbr = trkngnbr;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getBillAcctNbr() {
		return billAcctNbr;
	}
	public void setBillAcctNbr(String billAcctNbr) {
		this.billAcctNbr = billAcctNbr;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRs_type() {
		return rs_type;
	}
	public void setRs_type(String rs_type) {
		this.rs_type = rs_type;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRebillPrerate() {
		return rebillPrerate;
	}
	public void setRebillPrerate(String rebillPrerate) {
		this.rebillPrerate = rebillPrerate;
	}
	public String getWorkable() {
		return workable;
	}
	public void setWorkable(String workable) {
		this.workable = workable;
	}
	public String getDefectFlg() {
		return defectFlg;
	}
	public void setDefectFlg(String defectFlg) {
		this.defectFlg = defectFlg;
	}
	public String getDefectNbr() {
		return defectNbr;
	}
	public void setDefectNbr(String defectNbr) {
		this.defectNbr = defectNbr;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
}
