package emass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public  class PupEntry {
	static   WebElement trk,fedexid =null,route,formid,cosmosnbr,destcity,stoptype,destcountry,destzip,destadd,basesvr,pkgtype,deliveryadd;
	static  JavascriptExecutor js = (JavascriptExecutor)Loginemass.driver;
	static  int j =0;
	
	public static  void dataentry() {
		
		 trk = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:trkNo_inputtext"));
		 fedexid = Loginemass.driver.findElement(By.xpath("//*[@id = 'massEntryForm:empNbr_inputtext']"));
		 route = Loginemass.driver.findElement(By.xpath("//*[@id='massEntryForm:route_inputtext']"));
		 formid = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:formCd_inputtext"));
		 cosmosnbr = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:cosmosNbr_inputtext"));
		 destcity = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:destCityCd_inputtext"));
		 destcountry = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:destCountryCd_inputtext"));
		 destzip = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:destZipCd_inputtext"));
		 destadd = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:deliveryAddr_inputtext"));
		 stoptype = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:stopType_selectonemenu"));
		
		 basesvr = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:baseSvc_selectonemenu"));
		
		 pkgtype = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:pkgType_selectonemenu"));
		
		 stoptype = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:stopType_selectonemenu"));
		 deliveryadd = Loginemass.driver.findElement(By.id("massEntryForm:pup_repeatdatatable:0:deliveryAddr_inputtext"));
		 
		 //avinash
			fedexid.sendKeys("0000607383");
			/*
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			//route = Loginemass.driver.findElement(By.xpath("//*[@id='massEntryForm:route_inputtext']"));
			route.sendKeys("123");
		//	js.executeScript("document.getElementById('"+route+"').value='123'");
			
			
			trk.sendKeys("794993969528");
			formid.sendKeys("0430");
			cosmosnbr.sendKeys("12345");
			Select st = new Select(stoptype);
			st.selectByValue("R");
			destcity.sendKeys("0");
			destcountry.sendKeys("US");
			destzip.sendKeys("32810");
			
	}
	
	public static  void scroll1() throws Exception {
		//j=20;
		System.out.println(j);
		Thread.sleep(1000);
		fedexid.sendKeys("0000607383");
		route.sendKeys("123");
		trk.sendKeys("794993969528");
		formid.sendKeys("0430");
		cosmosnbr.sendKeys("12345");
		Select st = new Select(stoptype);
		st.selectByValue("R");
		destcity.sendKeys("0");
		destcountry.sendKeys("US");
		destzip.sendKeys("32810");
		
		
		
	}
	
	public static  void scroll2() {
		
		//((JavascriptExecutor)
				
				js.executeScript("window.scrollBy(1000,0)");
		Select bs = new Select(basesvr);
		bs.selectByValue("2A");
		
		Select pt = new Select(pkgtype);
		pt.selectByValue("01");
		
		
		
	}
	
	public static  void scroll3() {
		
		js.executeScript("window.scrollBy(1000,0)");
		deliveryadd.sendKeys("123 Main Street");
	}
		
		
		
		
	}
	
	
		
		
	
	
	

