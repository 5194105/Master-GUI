import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class config {
	
	Connection taa1Con,gtmRevToolsCon;
	String chromeSetProperty,ieSetProperty,chromePath,ieDriverPath;
	WebDriver driver;
    String gtmDbName,gtmDbResults,gtmDbPassword,retryAttempts,secondTimeout,rebillL2URL,rebillL3URL;
    String rtmDbName,rtmDbPassword;
    String rerateL2URL,rerateL3URL,prerateL2URL,prerateL3URL;
    String instantInvoiceL2URL,instantInvoiceL3URL;
    String rebillResultTable;
    String rtmBatchShippingResults;
	
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
	
	
	
	
	
	
	
	public void setIEDriver(String property,String path){
		
		System.out.println(property);
		System.out.println(path);
		System.setProperty(property, path);
		driver= new InternetExplorerDriver();
	}
	
	public void setChromeDriver(String property,String path){
		System.setProperty(property, path);
		driver= new ChromeDriver();
	}
	
	public WebDriver getIEDriver(){
		
		return driver;
	}
	
	public WebDriver getChromeDriver(){
		
		return driver;
	}
	
	public Connection getTaa1DbConnection(String dbCon,String username,String password) {
		
		try {
			taa1Con=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD1_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taa1Con;
	}
	
	public Connection getGtmRevToolsConnection(String dbCon,String username,String password) {
		
		try {
			gtmRevToolsCon=DriverManager.getConnection("jdbc:oracle:thin:@ldap://oid.inf.fedex.com:3060/GTM_PROD5_SVC1_L3,cn=OracleContext,dc=ute,dc=fedex,dc=com","GTM_REV_TOOLS","Wr4l3pP5gWVd7apow8eZwnarI3s4e1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gtmRevToolsCon;
	}
	
}
