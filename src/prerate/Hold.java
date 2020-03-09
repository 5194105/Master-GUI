package prerate;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Hold {
	static XSSFWorkbook wb;
	static XSSFSheet sh;
	static XSSFRow row;
	static XSSFCell cell;
	static int i;
	static int rownum;
	static String error;
	static FileOutputStream fout;
	static FileInputStream in;
	static String trk;
	static String trk2;
	static	JavascriptExecutor Executor;
	static JavascriptExecutor Executor2;
	static WebElement element;
	static WebElement element2;
	static WebDriver driver;
	public String file;
	public String level ;
	public boolean flag2;
	public String er;
        String levelUrl;
        Boolean compatibleMode;
	//public File file;
        public String pass;
	public String userid;
        Boolean chrome;
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * 
	 */
	public Hold (String file,String level,String userid,String pass,Boolean compatibleMode,Boolean chrome) throws IOException, InterruptedException{
		this.file=file;
		this.level=level;
		this.pass=pass;
		this.userid=userid;
                this.compatibleMode=compatibleMode;
                this.chrome=chrome;

	}	
	public void  Hold1() throws IOException, InterruptedException {

		
                 if (level.contentEquals("L2")||level.contentEquals("l2"))
		{
			levelUrl="https://testsso.secure.fedex.com/prerates-l2/";
		}
		else if (level.contentEquals("L3")||level.contentEquals("l3"))
		{
			levelUrl="https://testsso.secure.fedex.com/l3/prerates";
		}
            
                 String webDriverPath;
        String homePath=System.getProperty("user.dir");
        
        if (System.getProperty("user.dir").indexOf("dist")==-1){
            webDriverPath=System.getProperty("user.dir");
        }
        else {
        webDriverPath=homePath.substring(0,homePath.length()-5);
        }
        
            if (chrome==false){
             if (compatibleMode==true){
                    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		    capabilities.setCapability("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", true);
		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		    capabilities.setCapability("ignoreZoomSetting", true);
		    capabilities.setCapability("ignoreProtectedModeSettings", true);
		    capabilities.setCapability("initialBrowserUrl",levelUrl);
			
		    
		    
			System.setProperty("webdriver.ie.driver", webDriverPath+ "\\IEDriverServer.exe");
			 driver = new InternetExplorerDriver(capabilities);
                    }
                    else {
                    System.setProperty("webdriver.ie.driver", webDriverPath+ "\\IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    driver.get(levelUrl);
                    }
            }
            
            else {
              System.setProperty("webdriver.chrome.driver", webDriverPath+ "\\chromedriver.exe");
                driver= new ChromeDriver();
                driver.get(levelUrl);
            
            }
            

		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys(userid);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("submit")).click();
		//driver.switchTo().frame("content");
		//Thread.sleep(4000);
		//File file1 = new File(file);
		in = new FileInputStream(file);
		wb = new XSSFWorkbook (in);
		sh = wb.getSheetAt(0);
		rownum = sh.getLastRowNum()-sh.getFirstRowNum();
		for( i=1;i<=rownum;i++){
			try{
				row = sh.getRow(i);
				trk = row.getCell(2).getStringCellValue();
				trk2 = row.getCell(1).getStringCellValue();
				if (trk.isEmpty()){
					sh.getRow(i).createCell(3).setCellValue("Tracking number not found");
					fout = new FileOutputStream(file);
					wb.write(fout);
					continue;	
				}
				//Thread.sleep(3000);
				driver.switchTo().frame("header");
				driver.findElement(By.id("preRateEntrySelection")).click();
				//Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("content");
				driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).sendKeys(trk);
				driver.findElement(By.xpath("//button[@id='preRateEntrySelForm:search_button']")).click();
				Thread.sleep(3000);
				try{
					//flag=driver.findElement(By.className("ui-faces-message-text")).isDisplayed();
					//System.out.println("Error flag"+flag);
					//System.out.println(driver.findElement(By.className("ui-faces-message-text")).getText());
					 er=driver.findElement(By.className("ui-faces-message-text")).getText();
					sh.getRow(i).createCell(3).setCellValue(er);
					fout = new FileOutputStream(file);
					wb.write(fout);
					driver.switchTo().defaultContent();
					continue;
				}
				catch (NoSuchElementException a){
					//System.out.println("Noerrormsg : " + flag);
				}
				//driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
				//driver.switchTo().defaultContent();
				//driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
				if (trk2.equals("Remove pre rate hold from pre rate GUI")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
					element2 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[4]/span[2]"));
					Executor2 = (JavascriptExecutor)driver;
					Executor2.executeScript("arguments[0].click();", element2);
					Thread.sleep(1000);
					driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
					Thread.sleep(4000);

				}
				else {
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
					element = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[3]/span[2]"));
					Executor = (JavascriptExecutor)driver;
					Executor.executeScript("arguments[0].click();", element);
					Thread.sleep(1000);
					driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
					Thread.sleep(4000);

				}
				try{

					flag2=driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).isDisplayed();
					sh.getRow(i).createCell(3).setCellValue("Completed");
					fout = new FileOutputStream(file);
					wb.write(fout);
					driver.switchTo().defaultContent();
					continue;
				}
				catch (NoSuchElementException a){

					sh.getRow(i).createCell(3).setCellValue("Try This Manually or check once done or not");
					fout = new FileOutputStream(file);
					wb.write(fout);
					driver.switchTo().defaultContent();
					continue;


				}
			}
			catch(NullPointerException k)
			{
				sh.getRow(i).createCell(3).setCellValue("Tracking no not found");
				fout = new FileOutputStream(file);
				wb.write(fout);
				continue;
			}
			catch( NoSuchElementException e){


				sh.getRow(i).createCell(3).setCellValue("Try this manually");
				fout = new FileOutputStream(file);
				wb.write(fout);	
				driver.switchTo().defaultContent();
				continue;

			}
			catch (WebDriverException h)
			{
				sh.getRow(i).createCell(3).setCellValue("Try wThis Manually");
				fout = new FileOutputStream(file);
				wb.write(fout);
				continue;
			}
		}
		driver.quit();
	}
}









