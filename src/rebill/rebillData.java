package rebill;

import configuration.database;
import configuration.excel;

public class rebillData {
	
	
	 String result, description, test_input_nbr, tin_count, trkngnbr, reason_code,rebill_acct, login,
     invoice_nbr_1,invoice_nbr_2,password;
	 
	String mig,region,rsType,company,worktype;
	 

	
	

	public rebillData(String result,String description,String test_input_nbr,String tin_count,String trkngnbr,String reason_code,String rebill_acct,
			String invoice_nbr_1,String invoice_nbr_2,String mig,String region, String login,  String password, String rsType,String company,String worktype) {
	
		 this.result=result;
		 this.description=description;
         this.test_input_nbr=test_input_nbr;
         this.tin_count=tin_count;
         this.trkngnbr=trkngnbr;
         this.reason_code=reason_code;
         this.rebill_acct=rebill_acct;
     
         this.login=login;                        
         this.invoice_nbr_1=invoice_nbr_1;
         this.invoice_nbr_2=invoice_nbr_2;
         this.password=password;
         
         this.mig=mig;
         this.region=region;
         this.rsType=rsType;
         this.company=company;
         this.worktype=worktype;
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

    public String getTest_input_nbr() {
        return test_input_nbr;
    }

    public void setTest_input_nbr(String test_input_nbr) {
        this.test_input_nbr = test_input_nbr;
    }

    public String getTin_count() {
        return tin_count;
    }

    public void setTin_count(String tin_count) {
        this.tin_count = tin_count;
    }

    public String getTrkngnbr() {
        return trkngnbr;
    }

    public void setTrkngnbr(String trkngnbr) {
        this.trkngnbr = trkngnbr;
    }

    public String getReason_code() {
        return reason_code;
    }

    public void setReason_code(String reason_code) {
        this.reason_code = reason_code;
    }

    public String getRebill_acct() {
        return rebill_acct;
    }

    public void setRebill_acct(String rebill_acct) {
        this.rebill_acct = rebill_acct;
    }
/*
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
*/
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getInvoice_nbr_1() {
        return invoice_nbr_1;
    }

    public void setInvoice_nbr_1(String invoice_nbr_1) {
        this.invoice_nbr_1 = invoice_nbr_1;
    }

    public String getInvoice_nbr_2() {
        return invoice_nbr_2;
    }

    public void setInvoice_nbr_2(String invoice_nbr_2) {
        this.invoice_nbr_2 = invoice_nbr_2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRsType() {
		return rsType;
	}

	public void setRsType(String rsType) {
		this.rsType = rsType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
    
    public String getString(){
         return result +" "+ description +" "+ test_input_nbr +" "+ tin_count +" "+ 
                trkngnbr +" "+ reason_code +" "+ rebill_acct +" "+ 
                 invoice_nbr_1 +" "+invoice_nbr_2 +" "+mig+" "+region+" "+login +" "+ password +" "+rsType+" "+
                 " "+company+" "+worktype;
        
    }
	
}
