package configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;

public class config {
	
	Connection taa1Con,gtmRevToolsCon,ciCon,oreL2Con,oreL3Con;
	String chromeSetProperty,ieSetProperty,chromePath,ieDriverPath;
	WebDriver ieDriver,chromeDriver,tempWebDriver;
    String gtmDbName,gtmDbResults,gtmDbPassword,retryAttempts,secondTimeout,rebillL2URL,rebillL3URL;
    String rtmDbName,rtmDbPassword;
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

    String cycle;
    String homePath;

    String startDate,endDate;

    
    String compatible;
    
    String level,source;
	
	public config() {
		
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
	

	
	public void setGtmDbName(String gtmDbName) {
		
		this.gtmDbName=gtmDbName;
	}
	public void setGtmDbResults(String gtmDbResults) {
		
		this.gtmDbResults=gtmDbResults;
	}
	
	public void setGtmDbPassword(String gtmDbPassword) {
		
		this.gtmDbPassword=gtmDbPassword;
	}
	
	public void setRetryAttempts(String retryAttempts) {
		
		this.retryAttempts=retryAttempts;
	}
	
	public void setSecondTimeout(String secondTimeout) {
		
		this.secondTimeout=secondTimeout;
	}
	
	public void setRebillL2URL(String rebillL2URL) {
		
		this.rebillL2URL=rebillL2URL;
	}
	public void setRebillL3URL(String rebillL3URL) {
		
		this.rebillL3URL=rebillL3URL;
	}
	
	public void setRtmDbName(String rtmDbName) {
		
		this.rtmDbName=rtmDbName;
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
	
	
	public String getGtmDbName() {
		
		return gtmDbName;
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
	public String getRtmDbName() {
		
		return rtmDbName;
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
	
	public Connection getTaa1DbConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			taa1Con=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD1_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","ri","ri");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taa1Con;
	}
	
public void setGtmRevToolsConnection(String username,String password) throws ClassNotFoundException {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			gtmRevToolsCon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com",username,password);
		} catch (SQLException e) {
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


public void setOreL2DbConnection(String dbCon,String username,String password) {
	
	try {
		oreL2Con=DriverManager.getConnection("jdbc:oracle:thin:@//idb00248.ute.fedex.com:1526/IE2VD925","test_readonly","perftest");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//return ciCon;
}

public Connection getOreL2DbConnection() {

	return oreL2Con;
}



public void setOreL3DbConnection() {
	
	try {
		
		
		oreL3Con=DriverManager.getConnection("jdbc:oracle:thin:@//sdb00261.ute.fedex.com:1526/PT1VD925","test_readonly", "perftest");
		//oreL3Con=DriverManager.getConnection("jdbc:oracle:thin:sdb00261.ute.fedex.com:1526:PT1VD925","test_readonly", "perftest");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//return ciCon;
}

public Connection getOreL3DbConnection() {

	return oreL3Con;
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

public String getStartDate( ) {
	
	return startDate;
}

public String getEndDate( ) {
	
	return endDate;
}

}




//jdbc:oracle:thin:@<host>:<port>:<SID>

//Example: jdbc:oracle:thin:192.168.2.1:1521:X01A


//IE2VD393_T =
//(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=idb00296.ute.fedex.com)(PORT=1526)))(CONNECT_DATA=(SERVICE_NAME=IDB00296)))

//PT1VD393_T =
//(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=sdb00324.ute.fedex.com)(PORT=1526)))(CONNECT_DATA=(SERVICE_NAME=SDB00324)))

