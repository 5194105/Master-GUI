/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prerate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import static prerate.Hold.driver;

public class Blank {

	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static	String type;
	public static	String cm4;
	public static	String cc4 ;
	public static	String cm3 ;
	public static	String cc3;
	public static	String cm2 ;
	public static	String cc2 ;
	public static String cm1;
	public static String cc1;
	public static	String id;
	public static	String currcode;
	public static	String amount;
	public static	String trk ;
	public static int j;
	public static int i;
	public static int k;
	public static FileOutputStream fout;
	public FileInputStream in;
	public static ArrayList<String> data;
	public static String []array ;
	public static  boolean flag ;
	public static  boolean flag2 ;
	public static boolean fl;
	public static boolean fl2;
	public static boolean sel;
	public static int count;
	public static WebDriverWait wait;
	public static int coloumn;
	public static JavascriptExecutor Executor;
	public static JavascriptExecutor Executor1;
	public static JavascriptExecutor Executor2;
	public static JavascriptExecutor Executor3;
	public static JavascriptExecutor Executor4 ;
	public static int rownum ;
	public static WebElement element;
	public static WebElement element1;
	public static WebElement element2;
	public static WebElement element3;
	public static WebElement element4;
	public String file;
	public String level;
	public String pass;
	public String userid;
        Boolean compatibleMode;
        Boolean chrome;
        String levelUrl;
        WebDriver driver;
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public Blank (String file,String level,String userid,String pass,Boolean compatibleMode,Boolean chrome) throws IOException, InterruptedException{
		this.file=file;
		this.level=level;
		this.pass=pass;
		this.userid=userid;
                this.compatibleMode=compatibleMode;
                this.chrome=chrome;
	}


	public void update() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
                /*
                String home=System.getProperty("user.dir");
		System.setProperty("webdriver.ie.driver", home+ "\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		*/
                
                
                
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
             
                
                driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		wait = new WebDriverWait( driver,10);
		//driver.get("https://testsso.secure.fedex.com/prerates-l2");
		/*
                if (level.contentEquals("L2")||level.contentEquals("l2"))
		{
			driver.get("https://testsso.secure.fedex.com/prerates-l2");
		}
		else if (level.contentEquals("L3")||level.contentEquals("l3"))
		{
			driver.get("https://testsso.secure.fedex.com/l3/prerates");
		}
*/
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys(userid);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("submit")).click();
		Thread.sleep(4000);
		//File file = new File("\\"+"\\corp.ds.fedex.com\\vdi-oz\\U3\\5249723\\Documents\\SELENIUM@\\PREBLANK.xlsx");
		FileInputStream in = new FileInputStream(file);
		wb = new XSSFWorkbook (in);
		sh = wb.getSheetAt(0);
		rownum = sh.getLastRowNum()-sh.getFirstRowNum();
		//System.out.println(rownum);
		for( i=1;i<=rownum;i++){
			try{
				fl=false;
				row=sh.getRow(i);
				coloumn = row.getLastCellNum()-row.getFirstCellNum();
				//System.out.println(coloumn);
				trk = row.getCell(5).getStringCellValue();
				amount = row.getCell(6).getStringCellValue();
				currcode = row.getCell(7).getStringCellValue();
				id = row.getCell(8).getStringCellValue();
				cc1 = row.getCell(9).getStringCellValue();
				cm1 = row.getCell(10).getStringCellValue();
				cc2 = row.getCell(11).getStringCellValue();
				cm2 = row.getCell(12).getStringCellValue();
				type = row.getCell(0).getStringCellValue();
				driver.switchTo().frame("header");
				driver.findElement(By.id("preRateEntrySelection")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("content");
				driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).sendKeys(trk);
				driver.findElement(By.xpath("//button[@id='preRateEntrySelForm:search_button']")).click();
				Thread.sleep(2000);
				try{
					//flag=driver.findElement(By.className("ui-faces-message-text")).isDisplayed();
					//System.out.println("Error flag"+flag);
					//System.out.println(driver.findElement(By.className("ui-faces-message-text")).getText());
					String er=driver.findElement(By.className("ui-faces-message-text")).getText();
					sh.getRow(i).createCell(1).setCellValue(er);
					fout = new FileOutputStream(file);
					wb.write(fout);
					driver.switchTo().defaultContent();
					continue;
				}
				catch (NoSuchElementException a){
					//System.out.println("Noerrormsg : " + flag);
				}
				//Thread.sleep(2000);

				driver.findElement(By.xpath("//a[@id='preRateEntryForm:actionId_link']")).click();
				element = driver.findElement(By.xpath("//div[@id='preRateEntryForm:actionId_div']/div/div[2]/span[2]"));
				Executor = (JavascriptExecutor)driver;
				Executor.executeScript("arguments[0].click();", element);
				Thread.sleep(1000);
				if (type.equals("ADDR CHANGE FEE")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
					element1 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[2]/span[2]"));
					Executor1 = (JavascriptExecutor)driver;
					Executor1.executeScript("arguments[0].click();", element1);
				}
				else if(type.equals("COLD CHAIN")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
					element2 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[4]/span[2]"));
					Executor2 = (JavascriptExecutor)driver;
					Executor2.executeScript("arguments[0].click();", element2);
				}
				else if(type.equals("Trucking Fees")){
					driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
					element3 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[3]/span[2]"));
					Executor3 = (JavascriptExecutor)driver;
					Executor3.executeScript("arguments[0].click();", element3);
				}
				else if(type.equals("PH LPC")){
					if(level.contentEquals("L2")||level.contentEquals("l2")){
						//L2 PHLPC=12 and L3 PHLPC=8
						driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
						element4 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[12]/span[2]"));
						JavascriptExecutor Executor4 = (JavascriptExecutor)driver;
						Executor4.executeScript("arguments[0].click();", element4);
					}
					else if (level.contentEquals("L3")||level.contentEquals("l3")){
						driver.findElement(By.xpath("//a[@id='preRateEntryForm:preRateTypeCd_link']")).click();
						element4 = driver.findElement(By.xpath("//div[@id='preRateEntryForm:preRateTypeCd_div']/div/div[8]/span[2]"));
						JavascriptExecutor Executor4 = (JavascriptExecutor)driver;
						Executor4.executeScript("arguments[0].click();", element4);

					}
				}
				driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:amountId:pymt_amnt_input']")).sendKeys(amount);
				driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:currCodeId:cuu_code_input']")).sendKeys(currcode);
				driver.findElement(By.xpath("//input[@id='preRateEntryForm:paymentComponent:rateApprover_input']")).sendKeys(id);
				driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde1_input']")).sendKeys(cc1);
				driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt1_input']")).sendKeys(cm1);
				driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde2_input']")).sendKeys(cc2);
				driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt2_input']")).sendKeys(cm2);
				if(coloumn==15){
					cc3 = row.getCell(13).getStringCellValue();
					cm3 = row.getCell(14).getStringCellValue();
					driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(cc3);
					driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(cm3);

				}
				else if (coloumn==17){
					if (row.getCell(13)!=null && row.getCell(15)!=null){
						cc3 = row.getCell(13).getStringCellValue();
						cm3 = row.getCell(14).getStringCellValue();
						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(cc3);
						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(cm3);
						cc4 = row.getCell(15).getStringCellValue();
						cm4 = row.getCell(16).getStringCellValue();
						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).sendKeys(cc4);
						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).sendKeys(cm4);

					}
					else  if (row.getCell(13)!=null){

						cc3 = row.getCell(13).getStringCellValue();
						cm3 = row.getCell(14).getStringCellValue();

						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde3_input']")).sendKeys(cc4);
						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt3_input']")).sendKeys(cm4);
					}
					else  if (row.getCell(15)!=null){

						cc4 = row.getCell(15).getStringCellValue();
						cm4 = row.getCell(16).getStringCellValue();

						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:ccde4_input']")).sendKeys(cc4);
						driver.findElement(By.xpath("//input[@id='preRateEntryForm:PreRateChargeDetailCompnent:amt4_input']")).sendKeys(cm4);
					}

				}
				driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
				Thread.sleep(6000);
				try{
					count=(driver.findElements(By.xpath("//input[@type='checkbox']")).size());
					//System.out.println(count);
					for( k=2;k<count;k++){
						sel=driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).isEnabled();
						if(sel==true){
							driver.findElements(By.xpath("//input[@type='checkbox']")).get(k).click();
						}
						else {
							//System.out.println("disabled");
							fl=true;
							break;
						}
					}
					driver.findElement(By.xpath("//button[@id='preRateEntryForm:PreRateEntrySubmit_button']")).click();
					Thread.sleep(6000);
				}
				catch (NoSuchElementException a){
					//System.out.println("just proceed further");
				}
				try{

					flag2=driver.findElement(By.xpath("//input[@id='preRateEntrySelForm:trackingNo_input']")).isDisplayed();
					sh.getRow(i).createCell(1).setCellValue("Completed");
					fout = new FileOutputStream(file);
					wb.write(fout);
					driver.switchTo().defaultContent();
					continue;
				}
				catch (NoSuchElementException a){
					if(fl==true){
						sh.getRow(i).createCell(1).setCellValue("Override Disabled");
						fout = new FileOutputStream(file);
						wb.write(fout);
						driver.switchTo().defaultContent();
						continue;
					}
					else{
						//System.out.println("Nosuccessfull : " + flag2);
						sh.getRow(i).createCell(1).setCellValue("Try this manually");
						fout = new FileOutputStream(file);
						wb.write(fout);
						driver.switchTo().defaultContent();
						continue;
					}
				}
			}
			catch(NoSuchElementException e){
				sh.getRow(i).createCell(1).setCellValue("Try this manually");
				fout = new FileOutputStream(file);
				wb.write(fout);
				driver.navigate().refresh();
				//driver.switchTo().defaultContent();
				continue;
			}
			catch (NullPointerException f)
			{
				sh.getRow(i).createCell(1).setCellValue("Try this manually");
				fout = new FileOutputStream(file);
				wb.write(fout);
				driver.switchTo().defaultContent();
				continue;
			}
			catch (WebDriverException h)
			{
				sh.getRow(i).createCell(1).setCellValue("Try this manually");
				fout = new FileOutputStream(file);
				wb.write(fout);
				driver.switchTo().defaultContent();
				continue;
			}
		}
		driver.quit();

	}
}
