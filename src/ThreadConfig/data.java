package ThreadConfig;

import java.util.ArrayList;

import ThreadCreditDebitDisputeResolve.debitDummyClass;
import ThreadEc.ecdummyClass;
import ThreadGFBO.gfboDummyClass;
import ThreadInstantInvoice.instantInvoiceDummy;
import ThreadMassERARerate.massRerateDummy;
import ThreadPRSRerate.prsRerateDummyClass;
import ThreadSingleERARerate.singleEraRerateDummy;
import ThreadSingleRebill.singleRebillDummy;
import ThreadeMass.threadEmassDummy;

public class data {
	String result, description, testInputNbr, tinCount, trkngnbr, reasonCode,  billAcctNbr, invoiceNbr1, invoiceNbr2, region, username, password,  rs_type, company, rebillPrerate,  workable, defectFlg, defectNbr;
	ArrayList<ecData> ecDataArray = new ArrayList<ecData>();
	String prerateTypeCd,  prerateAmt, currencyCd, approvalId, chrgCd1, chrgAmt1, chrgCd2,  chrgAmt2, chrgCd3, chrgAmt3,  chrgCd4, chrgAmt4;
	String rowcount;
	int counter;
	String rateWeight, actualWeight, wgtType,  length, width, height,   dimType, payor;
	String billActNbr, serviceType, packageType, rerateType, rsType,  valDesc, comments;
	String massRerateCombo;
	String creditFlg, debitFlg,  disputeFlg, resolveCreditFlg,rootCause,reasonCategory;
	String payorAcctNbr, itemPrcsCd, instantInvFlg,tinComment,podScan;
	String acct1, acct2,  trkngnbr1, trkngnbr2, service1, service2, requestType, acctType, acctName,serviceName,expectedStatus,eraCase;
	debitDummyClass ddc;
	String result2, description2,svcType;
	String gfboUsername;
	String gfboPassword;
	String gfboPaymentLevel;
	String gfboPaymentType;
	String gfboAccount,trkNo1,trkNo2;
	String gfboExpectedResult,requestId,ecWorkType,statCodeArray;
	Boolean override,ecOverrideUd;
	String runningResult="false",emassCaseData;
	String scenarioId, shipmentId;
	String emassOriginCd, emassPupEmpId,  emassPupRoute, emassFormId,  emassCosmoNbr, emassStopType,  emassDestCityShort, emassDestCountryCd, emassDestCountryPostal, emassBaseSvc, emassPackageType, emassHandlingCd, emassDelAddress;
	String emassPodDestCd, emassPodRoute, emassReceivedBy,   emassDelLoc, emassSigRecLineNbr, emassSigRecId,   emassStatDestCd, emassStatEmpId, emassStandardExport;
	
	
	//Function Type
			// 1 Single Rebill -- Works
			// 2 Mass Rebill -- Not Working
			// 3 Single Rerate -- Works
			// 4 Mass Rerate --  Works
			// 5 Credit Debit -- Works
			//	--1 Credit -- Works
			//	--2 Debit -- Works
			//	--3 Dispute -- Works
			//	--4 Resolve Credit -- Works
			//	--5 Resolve Rebill -- Works
			// 6 Instant Invoice -- Works
			// 7 Prerate Single -- Works
			// 8 Prerate Hold -- Works 
			// 9 PRS Rerate -- Works
			// 10 Instant Invoice Device -- Works
			// 11 GFBO --  Works
			// 12 EC UD --  Works
			// 13 EC Device --  Works
			// 14 emass -- doesnt work
			
			// 22 ERA Single Rerate Upload to DB -- Works
			// 23 ERA Mass Rerate Upload to DB -- Works
			// 24 ERA Single Rebill Upload to DB
			// 25 Prerate Single Upload to DB
	
	//Rebill Single
		public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String reasonCode, String billAcctNbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password, String rs_type,String company,String rebillPrerate,String svcType,String length,String width,String height,String actualWeight,String workable,String defectFlg,String defectNbr,singleRebillDummy srd) {
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
			this.length=length;
			this.width=width;
			this.height=height;
			this.actualWeight=actualWeight;
			this.svcType=svcType;
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
				String rerateType,String region,String username,String password,String rsType,String company,String valDesc,String comments ,singleEraRerateDummy serd) {
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
		public data(String result, String description,String requestId,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password,String rateWeight,String length, String height,String width,String dimType, String rerateType,String rsType,String company,massRerateDummy mrd) {
			this.result=result;
			this.description=description;
			this.testInputNbr=testInputNbr;
			this.requestId=requestId;
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
		
		

		//Credit/

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
		
		
		public data(String result, String description,String testInputNbr,String tinCount,String trkngnbr,String reasonCode,String billAcctNbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password,String comments,String valDesc,String eraCase,debitDummyClass ddc) {
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
			this.valDesc=valDesc;
			this.comments=comments;
			this.eraCase=eraCase;
			
		}
		
		//Debit
		public data(String result, String description,String result2, String description2,String testInputNbr,String tinCount,String trkngnbr,String invoiceNbr1,String invoiceNbr2,String region,String username,String password,String workable, String reasonCategory,String reasonCode,String rootCause,String valDesc,String eraCase,debitDummyClass ddc) {
			this.result=result;
			this.description=description;
			this.description2=description2;
			this.result2=result2;
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
		public data(String testInputNbr,String trkngnbr,String payorAcctNbr,String itemPrcsCd,String instantInvFlg,String username,String password, instantInvoiceDummy iid) {
			this.testInputNbr=testInputNbr;
			this.trkngnbr=trkngnbr;
			this.payorAcctNbr=payorAcctNbr;
			this.itemPrcsCd=itemPrcsCd;
			this.instantInvFlg=instantInvFlg;
			this.username=username;
			this.password=password;
			
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
		
				//prs rerate
				public data(String testInputNbr,String tinCount,String acct1,String acct2,String trkNo1,String trkNo2,String invoiceNbr1,String invoiceNbr2,String service1, String service2,String requestType,String acctType,String acctName,prsRerateDummyClass pdc) {
					this.testInputNbr=testInputNbr;
					this.tinCount=tinCount;
					this.acct1=acct1;
					this.acct2=acct2;
					this.trkNo1=trkNo1;
					this.trkNo2=trkNo2;
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
		
		
		//GFBO 
			public data(String result,String description,String gfboUsername,String gfboPassword,String gfboPaymentLevel,String gfboPaymentType,String gfboAccount,String gfboExpectedResult, gfboDummyClass gdc) {
				this.result=result;
				this.description=description;
				this.gfboUsername=gfboUsername;
				this.gfboPassword=gfboPassword;
				this.gfboPaymentLevel=gfboPaymentLevel;
				this.gfboPaymentType=gfboPaymentType;
				this.gfboAccount=gfboAccount;
				this.gfboExpectedResult=gfboExpectedResult;
			
				
				
			}
		
		
		
		//EC UD
		public data(String scenarioId,String shipmentId,String trkngnbr,ecdummyClass ecd  ) {
			
			this.trkngnbr=trkngnbr;
			this.scenarioId=scenarioId;
			this.shipmentId=shipmentId;
			
		}
	
	
		public void addEcDataArray(String ecScenarioid,String ecShipmentId,String ecField,String ecValue,String ecComments) {
			ecDataArray.add(new ecData( ecScenarioid, ecShipmentId, ecField, ecValue, ecComments));
		}
	

		

	
		
		//emass pup
			public data(String testInputNbr, String trkngnbr,String emassOriginCd,String emassPupEmpId, String emassPupRoute,String emassFormId, String emassCosmoNbr,String emassStopType, String emassDestCityShort,String emassDestCountryCd,String emassDestCountryPostal,String emassBaseSvc,String emassPackageType,String emassHandlingCd,String emassDelAddress,String emassCaseData, threadEmassDummy ted) {
				this.testInputNbr=testInputNbr;
				this.trkngnbr=trkngnbr;
				this.emassOriginCd=emassOriginCd;
				this.emassPupEmpId=emassPupEmpId;
				this.emassPupRoute=emassPupRoute;
				this.emassFormId=emassFormId;
				this.emassCosmoNbr=emassCosmoNbr;
				this.emassStopType=emassStopType;
				this.emassDestCityShort=emassDestCityShort;
				this.emassDestCountryCd=emassDestCountryCd;
				this.emassDestCountryPostal=emassDestCountryPostal;
				this.emassBaseSvc=emassBaseSvc;
				this.emassPackageType=emassPackageType;
				this.emassHandlingCd=emassHandlingCd;
				this.emassDelAddress=emassDelAddress;
				this.emassCaseData=emassCaseData;
					
				}
	
		
		
		//emass stat65
		public data(String testInputNbr, String trkngnbr, String emassStatDestCd,String emassStatEmpId,String emassStandardExport,String emassCaseData, threadEmassDummy ted) {
			this.testInputNbr=testInputNbr;
			this.trkngnbr=trkngnbr;
			this.emassStatEmpId=emassStatEmpId;
			this.emassStatDestCd=emassStatDestCd;
			this.emassStandardExport=emassStandardExport;
			this.emassCaseData=emassCaseData;
			
		}

		//emass pod
		public data(String testInputNbr, String trkngnbr, String emassPodDestCd,String emassPodRoute,String emassReceivedBy,String emassDelAddress, String emassDelLoc,String emassSigRecLineNbr,String emassSigRecId,String emassCaseData, threadEmassDummy ted) {
			this.testInputNbr=testInputNbr;
			this.trkngnbr=trkngnbr;
			this.emassPodDestCd=emassPodDestCd;
			this.emassPodRoute=emassPodRoute;
			this.emassReceivedBy=emassReceivedBy;
			this.emassDelAddress=emassDelAddress;
			this.emassDelLoc=emassDelLoc;
			this.emassSigRecLineNbr=emassSigRecLineNbr;
			this.emassSigRecId=emassSigRecId;
			this.emassCaseData=emassCaseData;
			
			
		}
		
		
		
		
	

		
		
		
		
		
		
		
		
		//ERA Rerate Upload
		public data(String testInputNbr,String tinCount,String trkngnbr) {
			this.testInputNbr=testInputNbr;
			this.tinCount=tinCount;
			this.trkngnbr=trkngnbr;
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	public String getRunningResult() {
		return runningResult;
	}

	public void setRunningResult(String runningResult) {
		this.runningResult = runningResult;
	}
	
	
	public Boolean getEcOverrideUd() {
		return ecOverrideUd;
	}

	public void setEcOverrideUd(Boolean ecOverrideUd) {
		this.ecOverrideUd = ecOverrideUd;
	}

	public String getStatCodeArray() {
		return statCodeArray;
	}

	public void setStatCodeArray(String statCodeArray) {
		this.statCodeArray = statCodeArray;
	}

	public Boolean getOverride() {
		return override;
	}

	public void setOverride(Boolean override) {
		this.override = override;
	}


	
	
	public ArrayList<ecData> getEcDataArray(){
		return ecDataArray;
	}
	public String getEmassCaseData() {
		return emassCaseData;
	}

	public void setEmassCaseData(String emassCaseData) {
		this.emassCaseData = emassCaseData;
	}

	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getEcWorkType() {
		return ecWorkType;
	}

	public void setEcWorkType(String ecWorkType) {
		this.ecWorkType = ecWorkType;
	}

	
	


	
	
	
	
	
	
	
	
	
	
	
	//Rebill Resolve
	
	public String getTrkNo1() {
		return trkNo1;
	}

	public void setTrkNo1(String trkNo1) {
		this.trkNo1 = trkNo1;
	}

	public String getTrkNo2() {
		return trkNo2;
	}

	public void setTrkNo2(String trkNo2) {
		this.trkNo2 = trkNo2;
	}

	
	
	
	
	public String getEraCase() {
		return eraCase;
	}

	public void setEraCase(String eraCase) {
		this.eraCase = eraCase;
	}

	public debitDummyClass getDdc() {
		return ddc;
	}

	public void setDdc(debitDummyClass ddc) {
		this.ddc = ddc;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	
	
	
	
	public String getGfboUsername() {
			return gfboUsername;
		}



		public void setGfboUsername(String gfboUsername) {
			this.gfboUsername = gfboUsername;
		}



		public String getGfboPassword() {
			return gfboPassword;
		}



		public void setGfboPassword(String gfboPassword) {
			this.gfboPassword = gfboPassword;
		}



		public String getGfboPaymentLevel() {
			return gfboPaymentLevel;
		}



		public void setGfboPaymentLevel(String gfboPaymentLevel) {
			this.gfboPaymentLevel = gfboPaymentLevel;
		}



		public String getGfboPaymentType() {
			return gfboPaymentType;
		}



		public void setGfboPaymentType(String gfboPaymentType) {
			this.gfboPaymentType = gfboPaymentType;
		}



		public String getGfboAccount() {
			return gfboAccount;
		}



		public void setGfboAccount(String gfboAccount) {
			this.gfboAccount = gfboAccount;
		}



		public String getGfboExpectedResult() {
			return gfboExpectedResult;
		}



		public void setGfboExpectedResult(String gfboExpectedResult) {
			this.gfboExpectedResult = gfboExpectedResult;
		}




	public String getEmassOriginCd() {
		return emassOriginCd;
	}

	public void setEmassOriginCd(String emassOriginCd) {
		this.emassOriginCd = emassOriginCd;
	}

	public String getEmassPupEmpId() {
		return emassPupEmpId;
	}

	public void setEmassPupEmpId(String emassPupEmpId) {
		this.emassPupEmpId = emassPupEmpId;
	}

	public String getEmassPupRoute() {
		return emassPupRoute;
	}

	public void setEmassPupRoute(String emassPupRoute) {
		this.emassPupRoute = emassPupRoute;
	}

	public String getEmassFormId() {
		return emassFormId;
	}

	public void setEmassFormId(String emassFormId) {
		this.emassFormId = emassFormId;
	}

	public String getEmassCosmoNbr() {
		return emassCosmoNbr;
	}

	public void setEmassCosmoNbr(String emassCosmoNbr) {
		this.emassCosmoNbr = emassCosmoNbr;
	}

	public String getEmassStopType() {
		return emassStopType;
	}

	public void setEmassStopType(String emassStopType) {
		this.emassStopType = emassStopType;
	}

	public String getEmassDestCityShort() {
		return emassDestCityShort;
	}

	public void setEmassDestCityShort(String emassDestCityShort) {
		this.emassDestCityShort = emassDestCityShort;
	}

	public String getEmassDestCountryCd() {
		return emassDestCountryCd;
	}

	public void setEmassDestCountryCd(String emassDestCountryCd) {
		this.emassDestCountryCd = emassDestCountryCd;
	}

	public String getEmassDestCountryPostal() {
		return emassDestCountryPostal;
	}

	public void setEmassDestCountryPostal(String emassDestCountryPostal) {
		this.emassDestCountryPostal = emassDestCountryPostal;
	}

	public String getEmassBaseSvc() {
		return emassBaseSvc;
	}

	public void setEmassBaseSvc(String emassBaseSvc) {
		this.emassBaseSvc = emassBaseSvc;
	}

	public String getEmassPackageType() {
		return emassPackageType;
	}

	public void setEmassPackageType(String emassPackageType) {
		this.emassPackageType = emassPackageType;
	}

	public String getEmassHandlingCd() {
		return emassHandlingCd;
	}

	public void setEmassHandlingCd(String emassHandlingCd) {
		this.emassHandlingCd = emassHandlingCd;
	}

	public String getEmassDelAddress() {
		return emassDelAddress;
	}

	public void setEmassDelAddress(String emassDelAddress) {
		this.emassDelAddress = emassDelAddress;
	}

	public String getEmassPodDestCd() {
		return emassPodDestCd;
	}

	public void setEmassPodDestCd(String emassPodDestCd) {
		this.emassPodDestCd = emassPodDestCd;
	}

	public String getEmassPodRoute() {
		return emassPodRoute;
	}

	public void setEmassPodRoute(String emassPodRoute) {
		this.emassPodRoute = emassPodRoute;
	}

	public String getEmassReceivedBy() {
		return emassReceivedBy;
	}

	public void setEmassReceivedBy(String emassReceivedBy) {
		this.emassReceivedBy = emassReceivedBy;
	}

	public String getEmassDelLoc() {
		return emassDelLoc;
	}

	public void setEmassDelLoc(String emassDelLoc) {
		this.emassDelLoc = emassDelLoc;
	}

	public String getEmassSigRecLineNbr() {
		return emassSigRecLineNbr;
	}

	public void setEmassSigRecLineNbr(String emassSigRecLineNbr) {
		this.emassSigRecLineNbr = emassSigRecLineNbr;
	}

	public String getEmassSigRecId() {
		return emassSigRecId;
	}

	public void setEmassSigRecId(String emassSigRecId) {
		this.emassSigRecId = emassSigRecId;
	}

	public String getEmassStatDestCd() {
		return emassStatDestCd;
	}

	public void setEmassStatDestCd(String emassStatDestCd) {
		this.emassStatDestCd = emassStatDestCd;
	}

	public String getEmassStatEmpId() {
		return emassStatEmpId;
	}

	public void setEmassStatEmpId(String emassStatEmpId) {
		this.emassStatEmpId = emassStatEmpId;
	}

	public String getEmassStandardExport() {
		return emassStandardExport;
	}

	public void setEmassStandardExport(String emassStandardExport) {
		this.emassStandardExport = emassStandardExport;
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
	  public String getSvcType() {
		return svcType;
	}

	public void setSvcType(String svcType) {
		this.svcType = svcType;
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
