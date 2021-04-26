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
	String acct1, acct2,  trkngnbr1, trkngnbr2, service1, service2, requestType, acctType, acctName,serviceName,expectedStatus,eraCase;
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
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String reasonCode, String billAcctNbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password, String rs_type,String company,String rebillPrerate,String length,String width,String height,String actualWeight,String workable,String defectFlg,String defectNbr,int counter) {
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
		this.length=length;
		this.width=width;
		this.height=height;
		this.actualWeight=actualWeight;
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
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,
			String rateWeight,String actualWeight,String wgtType, String length,String width,String height, 
			String workable,String dimType,String payor,String billAcctNbr,String serviceType,String serviceName,String packageType,
			String rerateType,String region,String username,String password,String rsType,String company,String valDesc,String comments,int counter) {
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
		this.height=height;
		this.workable=workable;
		this.dimType=dimType;
		this.payor=payor;
		this.billAcctNbr=billAcctNbr;
		this.serviceType=serviceType;
		this.serviceName=serviceName;
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
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password,String rateWeight,String length, String height,String width,String dimType, String rerateType,String rsType,String company) {
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
		
	}
	
	
	
	
	
	//Credit/Debit

	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password,String workable, String reasonCategory,String reasonCode,String rootCause,String valDesc,String eraCase) {
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
		this.workable=workable;
		this.reasonCode=reasonCode;
		this.reasonCategory=reasonCategory;
		this.rootCause=rootCause;
		this.valDesc=valDesc;
		this.eraCase=eraCase;
	}
	
	
	
	//Instant Invoice
	public data(String testInputNbr,String trkngnbr,String payorAcctNbr,String itemPrcsCd,String instantInvFlg,String username,String password,int counter) {
		this.testInputNbr=testInputNbr;
		this.trkngnbr=trkngnbr;
		this.payorAcctNbr=payorAcctNbr;
		this.itemPrcsCd=itemPrcsCd;
		this.instantInvFlg=instantInvFlg;
		this.username=username;
		this.password=password;
		
	}
	
	//ERA Rerate Upload
	public data(String testInputNbr,String tinCount,String trkngnbr) {
		this.testInputNbr=testInputNbr;
		this.tinCount=tinCount;
		this.trkngnbr=trkngnbr;
		
		
	}
	
	
	
	
	//Prerate Single
	public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String prerateTypeCd, String prerateAmt,String currencyCd,String approvalId,String chrgCd1,String chrgAmt1,String chrgCd2, String chrgAmt2,String chrgCd3,String chrgAmt3, String chrgCd4,String chrgAmt4, String valDesc,String expectedStatus) {
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
		this.valDesc=valDesc;
		this.expectedStatus=expectedStatus;
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
	
	//Instant Invoice Device
	public data(String trkngnbr, String payorAcctNbr,String username,String password, int counter) {
		this.trkngnbr=trkngnbr;
		this.payorAcctNbr=payorAcctNbr;
		this.username=username;
		this.password=password;
		this.counter=counter;
		
	}
	
	public void setExpectedStatus(String expectedStatus) {
		this.expectedStatus=expectedStatus;
	}
	public String getExpectedStatus() {
		return expectedStatus;
	}
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getPrerateTypeCd() {
		return prerateTypeCd;
	}

	public void setPrerateTypeCd(String prerateTypeCd) {
		this.prerateTypeCd = prerateTypeCd;
	}

	public String getPrerateAmt() {
		return prerateAmt;
	}

	public void setPrerateAmt(String prerateAmt) {
		this.prerateAmt = prerateAmt;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getChrgCd1() {
		return chrgCd1;
	}

	public void setChrgCd1(String chrgCd1) {
		this.chrgCd1 = chrgCd1;
	}

	public String getChrgAmt1() {
		return chrgAmt1;
	}

	public void setChrgAmt1(String chrgAmt1) {
		this.chrgAmt1 = chrgAmt1;
	}

	public String getChrgCd2() {
		return chrgCd2;
	}

	public void setChrgCd2(String chrgCd2) {
		this.chrgCd2 = chrgCd2;
	}

	public String getChrgAmt2() {
		return chrgAmt2;
	}

	public void setChrgAmt2(String chrgAmt2) {
		this.chrgAmt2 = chrgAmt2;
	}

	public String getChrgCd3() {
		return chrgCd3;
	}

	public void setChrgCd3(String chrgCd3) {
		this.chrgCd3 = chrgCd3;
	}

	public String getChrgAmt3() {
		return chrgAmt3;
	}

	public void setChrgAmt3(String chrgAmt3) {
		this.chrgAmt3 = chrgAmt3;
	}

	public String getChrgCd4() {
		return chrgCd4;
	}

	public void setChrgCd4(String chrgCd4) {
		this.chrgCd4 = chrgCd4;
	}

	public String getChrgAmt4() {
		return chrgAmt4;
	}

	public void setChrgAmt4(String chrgAmt4) {
		this.chrgAmt4 = chrgAmt4;
	}

	public String getRowcount() {
		return rowcount;
	}

	public void setRowcount(String rowcount) {
		this.rowcount = rowcount;
	}

	public String getRateWeight() {
		return rateWeight;
	}

	public void setRateWeight(String rateWeight) {
		this.rateWeight = rateWeight;
	}

	public String getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(String actualWeight) {
		this.actualWeight = actualWeight;
	}

	public String getWgtType() {
		return wgtType;
	}

	public void setWgtType(String wgtType) {
		this.wgtType = wgtType;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getDimType() {
		return dimType;
	}

	public void setDimType(String dimType) {
		this.dimType = dimType;
	}

	public String getPayor() {
		return payor;
	}

	public void setPayor(String payor) {
		this.payor = payor;
	}

	public String getBillActNbr() {
		return billActNbr;
	}

	public void setBillActNbr(String billActNbr) {
		this.billActNbr = billActNbr;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getRerateType() {
		return rerateType;
	}

	public void setRerateType(String rerateType) {
		this.rerateType = rerateType;
	}

	public String getRsType() {
		return rsType;
	}

	public void setRsType(String rsType) {
		this.rsType = rsType;
	}

	public String getValDesc() {
		return valDesc;
	}

	public void setValDesc(String valDesc) {
		this.valDesc = valDesc;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getMassRerateCombo() {
		return massRerateCombo;
	}

	public void setMassRerateCombo(String massRerateCombo) {
		this.massRerateCombo = massRerateCombo;
	}

	public String getCreditFlg() {
		return creditFlg;
	}

	public void setCreditFlg(String creditFlg) {
		this.creditFlg = creditFlg;
	}

	public String getDebitFlg() {
		return debitFlg;
	}

	public void setDebitFlg(String debitFlg) {
		this.debitFlg = debitFlg;
	}

	public String getDisputeFlg() {
		return disputeFlg;
	}

	public void setDisputeFlg(String disputeFlg) {
		this.disputeFlg = disputeFlg;
	}

	public String getResolveCreditFlg() {
		return resolveCreditFlg;
	}

	public void setResolveCreditFlg(String resolveCreditFlg) {
		this.resolveCreditFlg = resolveCreditFlg;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getReasonCategory() {
		return reasonCategory;
	}

	public void setReasonCategory(String reasonCategory) {
		this.reasonCategory = reasonCategory;
	}

	public String getPayorAcctNbr() {
		return payorAcctNbr;
	}

	public void setPayorAcctNbr(String payorAcctNbr) {
		this.payorAcctNbr = payorAcctNbr;
	}

	public String getItemPrcsCd() {
		return itemPrcsCd;
	}

	public void setItemPrcsCd(String itemPrcsCd) {
		this.itemPrcsCd = itemPrcsCd;
	}

	public String getInstantInvFlg() {
		return instantInvFlg;
	}

	public void setInstantInvFlg(String instantInvFlg) {
		this.instantInvFlg = instantInvFlg;
	}

	public String getTinComment() {
		return tinComment;
	}

	public void setTinComment(String tinComment) {
		this.tinComment = tinComment;
	}

	public String getPodScan() {
		return podScan;
	}

	public void setPodScan(String podScan) {
		this.podScan = podScan;
	}

	public String getAcct1() {
		return acct1;
	}

	public void setAcct1(String acct1) {
		this.acct1 = acct1;
	}

	public String getAcct2() {
		return acct2;
	}

	public void setAcct2(String acct2) {
		this.acct2 = acct2;
	}

	public String getTrkngnbr1() {
		return trkngnbr1;
	}

	public void setTrkngnbr1(String trkngnbr1) {
		this.trkngnbr1 = trkngnbr1;
	}

	public String getTrkngnbr2() {
		return trkngnbr2;
	}

	public void setTrkngnbr2(String trkngnbr2) {
		this.trkngnbr2 = trkngnbr2;
	}

	public String getService1() {
		return service1;
	}

	public void setService1(String service1) {
		this.service1 = service1;
	}

	public String getService2() {
		return service2;
	}

	public void setService2(String service2) {
		this.service2 = service2;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
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
