import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class config {
	String chromeSetProperty,ieSetProperty,chromePath,ieDriverPath;
	WebDriver driver;
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
	
	
}
