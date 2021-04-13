package configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;

public class config {
	
	Connection rtmCon,gtmRevToolsCon,ciCon,oreL2Con,doreL3Con,ioreL3Con,ecL2Con,ecL3Con,eraL2Con,eraL3Con,oracleARL3Con;
	String chromeSetProperty,ieSetProperty,chromePath,ieDriverPath;
	WebDriver ieDriver,chromeDriver,tempWebDriver;
    String gtmDbUsername,gtmDbResults,gtmDbPassword,retryAttempts,secondTimeout,rebillL2URL,rebillL3URL;
    String rtmDbUsername,rtmDbPassword;
    String rerateL2URL,rerateL3URL,prerateL2URL,prerateL3URL;
    String instantInvoiceL2URL,instantInvoiceL3URL;
    String rebillResultTable;
    String rtmBatchShippingResults;
    String udUsername,udPassword;
    String excelPath;
    String type,flavour;
    String temp;
    String unixPath;
    String driverType;
    String ciUsername;
    String ciPassword;
    String ciDbString;
    String lparDate="";
    String allCheckBox;
    String nullCheckBox;
    String failedCheckBox;
    String domesticCheckBox;
    String internationalCheckBox;
    String expressCheckBox;
    String groundCheckBox;
    String normalCheckBox;
    String mfRetireCheckBox;
    String sessionCount;
    String prerateType;
    String customCheckBox;
    String customString;
    String whatever;
    String cycle;
    String homePath;

    String startDate,endDate;

    
    String compatible;
    
    String level,source;
    
    String databaseDisabled;
    
    String ed1,ed2,ei1,ei2,gd1,gd2,gi1,gi2,nt1,nt2,sp1,sp2;
	
    String[][] allData;
    
    String function;
    
    String filePath;
    
    int rowCount,colCount;
    
    String oreL2DbUsername,oreL3DbUsername,ecL2DbUsername,ecL3DbUsername,eraL2DbUsername,eraL3DbUsername;
    String oreL2DbPassword,oreL3DbPassword,ecL2DbPassword,ecL3DbPassword,eraL2DbPassword,eraL3DbPassword;
    String prsReratePaL2Url,prsReratePaL3Url,prsRerateCreateL2Url,prsRerateCreateL3Url,prsRerateMainL2Url,prsRerateMainL3Url;
    String eraRerateL2Url,eraRerateL3Url,prerateL2Url,prerateL3Url,rebillL2Url,rebillL3Url,instantInvoiceL2Url,instantInvoiceL3Url;
    
    String eraRerateSecondTimeout,rebillSecondTimeout,prerateSecondTimeout,instantInvoiceSecondTimeout,prsRerateSecondTimeout;
    String prerateRetryAttempts,eraRerateRetryAttempts,prsRerateRetryAttempts,instantInvoiceRetryAttempts,rebillRetryAttempts;
    
    String headless;
    
    Boolean rerateEnabled,rebillEnabled,prerateEnabled,eraRerateEnabled,instantInvoiceEnabled,udEnabled,rebillTroubleshootEnabled,creditAndDebitEnabled;
    
    Boolean fedexNetwork,admin;
    
    String version,updateUsername,updatePassword;
    
    String updateHostname,updatePath;
    String creditCheckBox,debitCheckBox;
    
    String eraWorkable;
    
    String eraMassRerate,eraMassRebill;
    String resolveCreditCheckBox,disputeCheckBox;
    
    String stephenTest;
    
	public config() {
		
	}
	
	
	
	public void setStephenTest(String stephenTest) {
		this.stephenTest=stephenTest;
	}
	
	public String getStephenTest() {
		return stephenTest;
		
	}
	
	
	public void setHeadlessString(String headless) {
		this.headless=headless;
	}
	
	public String getHeadlessString() {
		return headless;
		
	}
	
	public void setSessionCount(String sessionCount) {
		
		this.sessionCount=sessionCount;
	}
	
	public String getSessionCount() {
	
	return sessionCount;
}
	
	public void setChromeProperty(String chromeSetProperty) {
		
		this.chromeSetProperty=chromeSetProperty;
	}
	
	public void setIeProperty(String ieSetProperty) {
		
		this.ieSetProperty=ieSetProperty;
	}
	
	public void setChromePath(String chromePath) {
		
		this.chromePath=chromePath;
	}
	
	public void setIeDriverPath(String ieDriverPath) {
		
		this.ieDriverPath=ieDriverPath;
	}
	
	public String getChromeProperty() {
		
		return chromeSetProperty;
	}
	
	public String getIeProperty() {
		
		return ieSetProperty;
	}
	
	public String getChromeDriverPath() {
	
	return chromePath;
	}
	
	public String getIeDriverPath() {
	
	return ieDriverPath;
	}
	

	
	public void setGtmDbUsername(String gtmDbUsername) {
		
		this.gtmDbUsername=gtmDbUsername;
		
		
	}
	public void setOreL2DbUsername(String oreL2DbUsername) {
		
		this.oreL2DbUsername=oreL2DbUsername;
	}
	public void setOreL3DbUsername(String oreL3DbUsername) {
	
	this.oreL3DbUsername=oreL3DbUsername;
	}
	
	public void setEcL2DbUsername(String ecL2DbUsername) {
	
	this.ecL2DbUsername=ecL2DbUsername;
	}
	
	public void setEcL3DbUsername(String ecL3DbUsername) {
		
		this.ecL3DbUsername=ecL3DbUsername;
	}
	
	public void setEraL2DbUsername(String eraL2DbUsername) {
		
	this.eraL2DbUsername=eraL2DbUsername;
	}
	
	public void setEraL3DbUsername(String eraL3DbUsername) {
		
		this.eraL3DbUsername=eraL3DbUsername;
	}
	
	public void setOreL2DbPassword(String oreL2DbPassword) {
		
		this.oreL2DbPassword=oreL2DbPassword;
	}
	public void setOreL3DbPassword(String oreL3DbPassword) {
	
	this.oreL3DbPassword=oreL3DbPassword;
	}
	public void setEcL2DbPassword(String ecL2DbPassword) {
	
	this.ecL2DbPassword=ecL2DbPassword;
	}
	
	public void setEcL3DbPassword(String ecL3DbPassword) {
		
		this.ecL3DbPassword=ecL3DbPassword;
	}
	public void setEraL2DbPassword(String eraL2DbPassword) {
		
	this.eraL2DbPassword=eraL2DbPassword;
	}
	
	public void setEraL3DbPassword(String eraL3DbPassword) {
		
		this.eraL3DbPassword=eraL3DbPassword;
	}
	
	
	public void setGtmDbPassword(String gtmDbPassword) {
		
		this.gtmDbPassword=gtmDbPassword;
	}
	
	
	
	
	
	public void setRebillRetryAttempts(String rebillRetryAttempts) {
		
		this.rebillRetryAttempts=rebillRetryAttempts;
	}
	
	public void setRebillSecondTimeout(String rebillSecondTimeout) {
		
		this.rebillSecondTimeout=rebillSecondTimeout;
	}
public void setPrsRerateRetryAttempts(String prsRerateRetryAttempts) {
		
		this.prsRerateRetryAttempts=prsRerateRetryAttempts;
	}
	
	public void setPrsRerateSecondTimeout(String prsRerateSecondTimeout) {
		
		this.prsRerateSecondTimeout=prsRerateSecondTimeout;
	}
public void setInstantInvoiceRetryAttempts(String instantInvoiceRetryAttempts) {
		
		this.instantInvoiceRetryAttempts=instantInvoiceRetryAttempts;
	}
	
	public void setInstantInvoiceSecondTimeout(String instantInvoiceSecondTimeout) {
		
		this.instantInvoiceSecondTimeout=instantInvoiceSecondTimeout;
	}
public void setPrerateRetryAttempts(String prerateRetryAttempts) {
		
		this.prerateRetryAttempts=prerateRetryAttempts;
	}
	
	public void setPrerateSecondTimeout(String prerateSecondTimeout) {
		
		this.prerateSecondTimeout=prerateSecondTimeout;
	}
public void setEraRerateRetryAttempts(String eraRerateRetryAttempts) {
		
		this.eraRerateRetryAttempts=eraRerateRetryAttempts;
	}
	
	public void setEraRerateSecondTimeout(String eraRerateSecondTimeout) {
		
		this.eraRerateSecondTimeout=eraRerateSecondTimeout;
	}
	
	
	public String getRebillRetryAttempts() {
		return rebillRetryAttempts;
	}
	
	public String getPrerateRetryAttempts() {
		return prerateRetryAttempts;
	}
	
	public String getEraRerateRetryAttempts() {
		return eraRerateRetryAttempts;
	}
	
	public String getPrsRerateRetryAttempts() {
		return prsRerateRetryAttempts;
	}
	
	public String getInstantInvoiceRetryAttempts() {
		return instantInvoiceRetryAttempts;
	}
	
	
	public String getRebillSecondTimeout() {
		return rebillSecondTimeout;
	}
	
	public String getPrerateSecondTimeout() {
		return prerateSecondTimeout;
	}
	
	public String getEraRerateSecondTimeout() {
		return eraRerateSecondTimeout;
	}
	
	public String getPrsRerateSecondTimeout() {
		return prsRerateSecondTimeout;
	}
	
	public String getInstantInvoiceSecondTimeout() {
		return instantInvoiceSecondTimeout;
	}
	
	
	
	
	public void setStephenPassword(String whatever) {
		
		this.whatever=whatever;
	}
	
	public String getStephenPassword() {
		
		return whatever;
	}
	
	
	public void setRebillL2Url(String rebillL2Url) {
		
		this.rebillL2Url=rebillL2Url;
	}
	public void setRebillL3Url(String rebillL3Url) {
		
		this.rebillL3Url=rebillL3Url;
	}
	
	public void setPrerateL2Url(String prerateL2Url) {
		
		this.prerateL2Url=prerateL2Url;
	}
	public void setPrerateL3Url(String prerateL3Url) {
		
		this.prerateL3Url=prerateL3Url;
	}
	
	public void setInstantInvoiceL2Url(String instantInvoiceL2Url) {
		
		this.instantInvoiceL2Url=instantInvoiceL2Url;
	}
	public void setInstantInvoiceL3Url(String instantInvoiceL3Url) {
		
		this.instantInvoiceL3Url=instantInvoiceL3Url;
	}
	
	public void setEraRerateL2Url(String eraRerateL2Url) {
		
		this.eraRerateL2Url=eraRerateL2Url;
	}
	public void setEraRerateL3Url(String eraRerateL3Url) {
		
		this.eraRerateL3Url=eraRerateL3Url;
	}
	
	public void setPrsRerateMainL2Url(String prsRerateMainL2Url) {
		
		this.prsRerateMainL2Url=prsRerateMainL2Url;
	}
	public void setPrsRerateMainL3Url(String prsRerateMainL3Url) {
		
		this.prsRerateMainL3Url=prsRerateMainL3Url;
	}
	
	public void setPrsRerateCreateL2Url(String prsRerateCreateL2Url) {
		
		this.prsRerateCreateL2Url=prsRerateCreateL2Url;
	}
	public void setPrsRerateCreateL3Url(String prsRerateCreateL3Url) {
		
		this.prsRerateCreateL3Url=prsRerateCreateL3Url;
	}
	
	public void setPrsReratePaL2Url(String prsReratePaL2Url) {
		
		this.prsReratePaL2Url=prsReratePaL2Url;
	}
	public void setPrsReratePaL3Url(String prsReratePaL3Url) {
		
		this.prsReratePaL3Url=prsReratePaL3Url;
	}
	
	
	
	
	
	
	
	public void setRtmDbUsername(String rtmDbUsername) {
		
		this.rtmDbUsername=rtmDbUsername;
	}
	
	public void setRtmDbPassword(String rtmDbPassword) {
		
		this.rtmDbPassword=rtmDbPassword;
	}
	
	public void setRerateL2URL(String rerateL2URL) {
		
		this.rerateL2URL=rerateL2URL;
	}
	
	public void setRerateL3URL(String rerateL3URL) {
		
		this.rerateL3URL=rerateL3URL;
	}
	
	public void setPrerateL2URL(String prerateL2URL) {
		
		this.prerateL2URL=prerateL2URL;
	}
	
	public void setPrerateL3URL(String prerateL3URL) {
		
		this.prerateL3URL=prerateL3URL;
	}
	
	public void setInstantInvoiceL2URL(String instantInvoiceL2URL) {
		
		this.instantInvoiceL2URL=instantInvoiceL2URL;
	}
	public void setInstantInvoiceL3URL(String instantInvoiceL3URL) {
		
		this.instantInvoiceL3URL=instantInvoiceL3URL;
	}
	public void setRebillResultTable(String rebillResultTable) {
		
		this.rebillResultTable=rebillResultTable;
	}
	public void setRtmBatchShippingResults(String rtmBatchShippingResults) {
		
		this.rtmBatchShippingResults=rtmBatchShippingResults;
	}
	
	
	public String getGtmDbUsername() {
		
		return gtmDbUsername;
		}
	public String getGtmDbResults() {
		
		return gtmDbResults;
		}
	public String getGtmDbPassword() {
		
		return gtmDbPassword;
		}
	public String getRetryAttempts() {
		
		return retryAttempts;
		}
	public String getSecondTimeout() {
		
		return secondTimeout;
		}
	public String getRebillL2URL() {
		
		return rebillL2URL;
		}
	public String getRebillL3URL() {
		
		return rebillL3URL;
		}
	public String getRtmDbUsername() {
		
		return rtmDbUsername;
		}
	public String getRtmDbPassword() {
		
		return rtmDbPassword;
		}
	public String getRerateL2URL() {
		
		return rerateL2URL;
		}
	public String getRerateL3URL() {
		
		return rerateL3URL;
		}
	public String getPrerateL2URL() {
		
		return prerateL2URL;
		}
	public String getPrerateL3URL() {
		
		return prerateL3URL;
		}
	public String getInstantInvoiceL2URL() {
		
		return instantInvoiceL2URL;
		}
	public String getInstantInvoiceL3URL() {
		
		return instantInvoiceL3URL;
		}
	public String getRebillResultTable() {
		
		return rebillResultTable;
		}
	public String getRtmBatchShippingResults() {
		
		return rtmBatchShippingResults;
		}

	
	public void setUdUsername(String udUsername) {
		
		this.udUsername=udUsername;
		}
	public void  setUdPassword(String udPassword)  {
		
		this.udPassword=udPassword;
		}
	
	public String getUdUsername() {
		
		return udUsername;
		}
	public String getUdPassword() {
		
		return udPassword;
		}
	
	
	public Connection getRtmCon() {
		return rtmCon;
	}



	public Connection getCiCon() {
		return ciCon;
	}

	public Connection getOreL2Con() {
		return oreL2Con;
	}

	

	public Connection getEcL2Con() {
		return ecL2Con;
	}

	public Connection getEcL3Con() {
		return ecL3Con;
	}

	public Connection getEraL2Con() {
		return eraL2Con;
	}

	public Connection getEraL3Con() {
		return eraL3Con;
	}

	public String getChromeSetProperty() {
		return chromeSetProperty;
	}

	public String getIeSetProperty() {
		return ieSetProperty;
	}

	public String getChromePath() {
		return chromePath;
	}

	public WebDriver getIeDriver() {
		return ieDriver;
	}

	public WebDriver getTempWebDriver() {
		return tempWebDriver;
	}

	public String getTemp() {
		return temp;
	}

	public String getCompatible() {
		return compatible;
	}

	public String getEd1() {
		return ed1;
	}

	public String getEd2() {
		return ed2;
	}

	public String getEi1() {
		return ei1;
	}

	public String getEi2() {
		return ei2;
	}

	public String getGd1() {
		return gd1;
	}

	public String getGd2() {
		return gd2;
	}

	public String getGi1() {
		return gi1;
	}

	public String getGi2() {
		return gi2;
	}

	public String getNt1() {
		return nt1;
	}

	public String getNt2() {
		return nt2;
	}

	public String getSp1() {
		return sp1;
	}

	public String getSp2() {
		return sp2;
	}

	public String[][] getAllData() {
		return allData;
	}

	public String getOreL2DbUsername() {
		return oreL2DbUsername;
	}

	public String getOreL3DbUsername() {
		return oreL3DbUsername;
	}

	public String getEcL2DbUsername() {
		return ecL2DbUsername;
	}

	public String getEcL3DbUsername() {
		return ecL3DbUsername;
	}

	public String getEraL2DbUsername() {
		return eraL2DbUsername;
	}

	public String getEraL3DbUsername() {
		return eraL3DbUsername;
	}

	public String getOreL2DbPassword() {
		return oreL2DbPassword;
	}

	public String getOreL3DbPassword() {
		return oreL3DbPassword;
	}

	public String getEcL2DbPassword() {
		return ecL2DbPassword;
	}

	public String getEcL3DbPassword() {
		return ecL3DbPassword;
	}

	public String getEraL2DbPassword() {
		return eraL2DbPassword;
	}

	public String getEraL3DbPassword() {
		return eraL3DbPassword;
	}

	public String getPrsReratePaL2Url() {
		return prsReratePaL2Url;
	}

	public String getPrsReratePaL3Url() {
		return prsReratePaL3Url;
	}

	public String getPrsRerateCreateL2Url() {
		return prsRerateCreateL2Url;
	}

	public String getPrsRerateCreateL3Url() {
		return prsRerateCreateL3Url;
	}

	public String getPrsRerateMainL2Url() {
		return prsRerateMainL2Url;
	}

	public String getPrsRerateMainL3Url() {
		return prsRerateMainL3Url;
	}

	public String getEraRerateL2Url() {
		return eraRerateL2Url;
	}

	public String getEraRerateL3Url() {
		return eraRerateL3Url;
	}

	public String getPrerateL2Url() {
		return prerateL2Url;
	}

	public String getPrerateL3Url() {
		return prerateL3Url;
	}

	public String getRebillL2Url() {
		return rebillL2Url;
	}

	public String getRebillL3Url() {
		return rebillL3Url;
	}

	public String getInstantInvoiceL2Url() {
		return instantInvoiceL2Url;
	}

	public String getInstantInvoiceL3Url() {
		return instantInvoiceL3Url;
	}

	public void setHomePath(String homePath) {
		
		this.homePath=homePath;
	}
	public String getHomePath() {
		
		return homePath;
	}
	
	
	public void setCycle(String cycle) {
		
		this.cycle=cycle;
	}
	public String getCycle() {
		
		return cycle;
	}
	
	
	
	public void setLevel(String level) {
		
		this.level=level;
	}
	public void setSource(String source) {
		
		this.source=source;
	}
	public void setCompatibleMode(String compatible) {
		
		this.compatible=compatible;
	}
	
	public String getLevel() {
		
		return level;
	}
	public String getSource() {	
		
		return 	source;
		}
	public String getCompatibleMode() {	
		
		return compatible;
		}
	
	public void setExcelPath(String excelPath) {
		
		this.excelPath=excelPath;
	}
	
	public String getExcelPath() {
		
		return excelPath;
	}
	
public void setFlavour(String flavour) {
		
		this.flavour=flavour;
	}
	
	public String getFlavour() {
		
		return flavour;
	}
	
	
	
	//type is for DOMESTIC or GREEN
	public void setType(String type) {
		
		this.type=type;
	}
	
	public String getType() {
		
		return type;
	}
	
	
	
	
	public void setDriverType(String driverType) {
		
		this.driverType=driverType;
	}
	
	
	
	public String getDriverType() {
		
		return driverType;
		
	}
	
	public void setUnixPath(String unixPath) {
		
		this.unixPath=unixPath;
	}
	
	public String getUnixPath() {
		
		return unixPath;
	}
	
	
	public WebDriver setUpWebDriver() {
		
		if (temp.equals("1")) {
			tempWebDriver=ieDriver;
		}
		else if (temp.equals("2")) {
			tempWebDriver=chromeDriver;
		}
		else if (temp.equals("3")) {
			//tempWebDriver=fireFoxDriver;
		}
		return tempWebDriver;
		
	}
	
	
	
	public void setIEDriver(String property,String path){
		
		System.out.println(property);
		System.out.println(path);
		System.setProperty(property, path);
		//ieDriver= new InternetExplorerDriver();
	}
	
	public void setChromeDriver(String property,String path){
		System.setProperty(property, path);
		//chromeDriver= new ChromeDriver();
	}
	
	public void setProperty(String property,String path) {
		
		System.setProperty(property, path);
	}
	
	public WebDriver getIEDriver(){
		
		return ieDriver;
	}
	
	public WebDriver getChromeDriver(){
		
		return chromeDriver;
	}
	
	public void setRtmDbConnection(String username,String password) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				rtmCon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD1_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com",username,password);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
public void setGtmRevToolsConnection(String username,String password) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			gtmRevToolsCon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com",username,password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

public void setGtmRevToolsConnection() {
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		gtmRevToolsCon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}


	
	public Connection getGtmRevToolsConnection() throws ClassNotFoundException {
		
	
		return gtmRevToolsCon;
	}
	
	
	public String getCiUsername() {
		
		return ciUsername;
	}
	public String getCiPassword() {
		return ciPassword;
	}
	public String getCiDbString() 
	{
		return ciDbString;
	}

	public void setCiUsername(String ciUsername) {
		this.ciUsername=ciUsername;
		
	}
	public void setCiPassword(String ciPassword) {
		this.ciPassword=ciPassword;
	}
	public void setCiDbString(String ciDbString) {
		this.ciDbString=ciDbString;
			}
	
	
	public void setCiDbConnection(String dbCon,String username,String password) {
		/*
		try {
		//	ciCon=DriverManager.getConnection(dbCon,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return ciCon;
		 
		 */
		 
	}

	public Connection getCiDbConnection() {

		return ciCon;
	}


public void setOreL2DbConnection(String username,String password) {
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		oreL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00248.ute.fedex.com:1526:IE2VD925",username,password);
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//return ciCon;
}

public Connection getOreL2DbConnection() {

	return oreL2Con;
}



public void setDoreL3DbConnection(String username,String password) {
	
	try {
		
		Class.forName("oracle.jdbc.OracleDriver");
		
		
		doreL3Con=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/PT1DORE,cn=OracleContext,dc=ute,dc=fedex,dc=com","test_readonly","perftest");
			} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//return ciCon;
}



public void setIoreL3DbConnection(String username,String password) {
	
	try {
		
		Class.forName("oracle.jdbc.OracleDriver");
		ioreL3Con=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/PT1IORE,cn=OracleContext,dc=ute,dc=fedex,dc=com","test_readonly","perftest");
			} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//return ciCon;
}

public Connection getDoreL3DbConnection() {

	return doreL3Con;
}

public Connection getIoreL3DbConnection() {

	return ioreL3Con;
}



public void setEcL2DbConnection(String username,String password) {
	
	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ecL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00271.ute.fedex.com:1526/IE2VD991",username,password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

public Connection getEcL2DbConnection() {

	return ecL2Con;
}


public void setEcL3DbConnection(String username,String password) {
	
	try {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		ecL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00299.ute.fedex.com:1526/SDB00299.ute.fedex.com",username,password);
			} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public Connection getEcL3DbConnection() {

	return ecL3Con;
}



public void setEraL2DbConnection(String username,String password) {
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		eraL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00270.ute.fedex.com:1526/IDB00270.ute.fedex.com",username,password);
		} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
}

public Connection getEraL2DbConnection() {

	return eraL2Con;
}


public void setEraL3DbConnection(String username,String password) {
	

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			eraL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00325.ute.fedex.com:1526/SDB00325.ute.fedex.com",username,password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
}






public Connection getEraL3DbConnection() {

	return eraL3Con;
}

public void setOracleARL3DbConnection(String username,String password) {
	

	String url="jdbc:oracle:thin:@ldap://hdsoid.ute.fedex.com:3060/ENBL_SVC1_LVL3,CN=OracleContext,DC=ute,DC=fedex,DC=com";
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		    oracleARL3Con=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
}

public Connection getOracleARL3DbConnection() {

	return oracleARL3Con;
}

public void setLparDate(String lparDate) {
	
	this.lparDate=lparDate;
}

public String getLparDate( ) {
	
	return lparDate;
}

public void setStartDate(String startDate) {
	this.startDate=startDate;
}
public void setEndDate(String endDate) {
	this.endDate=endDate;
}



public void setPrerateType(String prerateType) {
	this.prerateType=prerateType;
}
public String getPrerateType() {
	return prerateType;
}


public void setDatabaseDisabled(String databaseDisabled) {
	
	this.databaseDisabled=databaseDisabled;
}

public String getDatabaseDisabled() {
	
	return databaseDisabled;
}



public String getStartDate( ) {
	
	return startDate;
}

public String getEndDate( ) {
	
	return endDate;
}







public void setAllCheckBox(String allCheckBox) {
	this.allCheckBox=allCheckBox;
}

public void setNullCheckBox(String nullCheckBox) {
	this.nullCheckBox=nullCheckBox;
}

public void setFailedCheckBox(String failedCheckBox) {
	this.failedCheckBox=failedCheckBox;
}

public void setDomesticCheckBox(String domesticCheckBox) {
	this.domesticCheckBox=domesticCheckBox;
}

public void setInternationalCheckBox(String internationalCheckBox) {
	this.internationalCheckBox=internationalCheckBox;
}

public void setExpressCheckBox(String expressCheckBox) {
	this.expressCheckBox=expressCheckBox;
}

public void setGroundCheckBox(String groundCheckBox) {
	this.groundCheckBox=groundCheckBox;
}

public void setNormalCheckBox(String normalCheckBox) {
	this.normalCheckBox=normalCheckBox;
}

public void setMfRetireCheckBox(String mfRetireCheckBox) {
	this.mfRetireCheckBox=mfRetireCheckBox;
}


public void setCreditCheckBox(String creditCheckBox) {
	this.creditCheckBox=creditCheckBox;
}

public void setDebitCheckBox(String debitCheckBox) {
	this.debitCheckBox=debitCheckBox;
}
public String getCreditCheckBox() {
	return creditCheckBox;
}
public String getDebitCheckBox() {
	return debitCheckBox;
}








public String getAllCheckBox() {
	return allCheckBox;
}

public String getNullCheckBox() {
	return nullCheckBox;
}

public String getFailedCheckBox() {
	return failedCheckBox;
}

public String getDomesticCheckBox() {
	return domesticCheckBox;
}

public String getInternationalCheckBox() {
	return internationalCheckBox;
}

public String getExpressCheckBox() {
	return expressCheckBox;
}

public String getGroundCheckBox() {
	return groundCheckBox;
}


public String getNormalCheckBox() {
	return normalCheckBox;
}

public String getMfRetireCheckBox() {
	return mfRetireCheckBox;
}


public void setCustomCheckBox(String customCheckBox){
	this.customCheckBox=customCheckBox;
}
public void setCustomString(String customString){
	this.customString=customString;


	
}
public String getCustomCheckBox(){
	return customCheckBox;
}
public String getCustomString(){
	return customString;
}





public void setEd1(String ed1){
	this.ed1=ed1;
}

public void setEi1(String ei1){
	this.ei1=ei1;
}

public void setGd1(String gd1){
	this.gd1=gd1;
}

public void setGi1(String gi1){
	this.gi1=gi1;
}

public void setNt1(String nt1){
	this.nt1=nt1;
}

public void setSp1(String sp1){
	this.sp1=sp1;
}


public void setEd2(String ed2){
	this.ed2=ed2;
}

public void setEi2(String ei2){
	this.ei2=ei2;
}

public void setGd2(String gd2){
	this.gd2=gd2;
}

public void setGi2(String gi2){
	this.gi2=gi2;
}

public void setNt2(String nt2){
	this.nt2=nt2;
}

public void setSp2(String sp2){
	this.sp2=sp2;
}



public String getEd1String(){
	return ed1;
}

public String getEi1String(){
	return ei1;
}

public String getGd1String(){
	return gd1;
}

public String getGi1String(){
	return gi1;
}

public String getNt1String(){
	return nt1;
}

public String getSp1String(){
	return sp1;
}



public String getEd2String(){
	return ed2;
}

public String getEi2String(){
	return ei2;
}

public String getGd2String(){
	return gd2;
}

public String getGi2String(){
	return gi2;
}

public String getNt2String(){
	return nt2;
}

public String getSp2String(){
	return sp2;
}



public void setAllDataArray(String[][] allData) {
	this.allData=allData;
}

public String[][] getAllDataArray() {
	return allData;
}


public void setFunction(String function) {
	this.function=function;
}

public String getFunction() {
	return function;
}

public void setFilePath(String filePath) {
	this.filePath=filePath;
}

public String getFilePath() {
	return filePath;
}


public void setRowCount(int rowCount) {
	this.rowCount=rowCount;
}

public int getRowCount() {
	return rowCount;
}


public void setFedexNetwork(Boolean fedexNetwork) {
	this.fedexNetwork=fedexNetwork;
}

public Boolean getFedexNetwork() {
	return fedexNetwork;
}

public void setAdmin(Boolean admin) {
	this.admin=admin;
}

public Boolean getAdmin() {
	return admin;
}


public void setRebillEnabled(Boolean rebillEnabled) {
	this.rebillEnabled=rebillEnabled;
}

public Boolean getRebillEnabled() {
	return rebillEnabled;
}


public void setPrerateEnabled(Boolean prerateEnabled) {
	this.prerateEnabled=prerateEnabled;
}

public Boolean getPrerateEnabled() {
	return prerateEnabled;
}

public void setEraRerateEnabled(Boolean eraRerateEnabled) {
	this.eraRerateEnabled=eraRerateEnabled;
}

public Boolean getEraRerateEnabled() {
	return eraRerateEnabled;
}

public void setInstantInvoiceEnabled(Boolean instantInvoiceEnabled) {
	this.instantInvoiceEnabled=instantInvoiceEnabled;
}

public Boolean getInstantInvoiceEnabled() {
	return instantInvoiceEnabled;
}

public void setUdEnabled(Boolean udEnabled) {
	this.udEnabled=udEnabled;
}

public Boolean getUdEnabled() {
	return udEnabled;
}

public void setRerateEnabled(Boolean rerateEnabled) {
	this.rerateEnabled=rerateEnabled;
}

public Boolean getRerateEnabled() {
	return rerateEnabled;
}

public void setRebillTroubleshootEnabled(Boolean rebillTroubleshootEnabled) {
	this.rebillTroubleshootEnabled=rebillTroubleshootEnabled;
}

public Boolean getRebillTroubleshootEnabled() {
	return rebillTroubleshootEnabled;
}

public void setCreditAndDebitEnabled(Boolean creditAndDebitEnabled) {
	this.creditAndDebitEnabled=creditAndDebitEnabled;
}

public Boolean getCreditAndDebitEnabled() {
	return creditAndDebitEnabled;
}


public void setVersion(String version) {
	this.version=version;
}

public String getVersion() {
	return version;
}


public void setUpdateUser(String updateUser) {
	this.updateUsername=updateUser;
}

public String getUpdateUser() {
	return updateUsername;
}

public void setUpdatePassword(String updatePassword) {
	this.updatePassword=updatePassword;
}

public String getUpdatePassword() {
	return updatePassword;
}


public void setUpdateHostname(String updateHostname) {
	this.updateHostname=updateHostname;
}

public String getUpdateHostname() {
	return updateHostname;
}

public void setUpdatePath(String updatePath) {
	this.updatePath=updatePath;
}

public String getUpdatePath() {
	return updatePath;
}




public void setEraWorkable(String eraWorkable){
	this.eraWorkable=eraWorkable;
}



public String getEraWorkable(){
	return eraWorkable;
}

public void setEraMassRerate(String eraMassRerate){
	this.eraMassRerate=eraMassRerate;
}



public String getEraMassRerate(){
	return eraMassRerate;
}


public void setEraMassRebill(String eraMassRebill){
	this.eraMassRebill=eraMassRebill;
}



public String getEraMassRebill(){
	return eraMassRebill;
}



public void setDisputeCheckBox(String disputeCheckBox){
	this.disputeCheckBox=disputeCheckBox;
}



public String getDisputeCheckBox(){
	return disputeCheckBox;
}


public void setResolveCreditCheckBox(String resolveCreditCheckBox){
	this.resolveCreditCheckBox=resolveCreditCheckBox;
}



public String getResolveCreditCheckBox(){
	return resolveCreditCheckBox;
}
}

//jdbc:oracle:thin:@<host>:<port>:<SID>

//Example: jdbc:oracle:thin:192.168.2.1:1521:X01A


//IE2VD393_T =
//(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=idb00296.ute.fedex.com)(PORT=1526)))(CONNECT_DATA=(SERVICE_NAME=IDB00296)))

//PT1VD393_T =
//(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=sdb00324.ute.fedex.com)(PORT=1526)))(CONNECT_DATA=(SERVICE_NAME=SDB00324)))

