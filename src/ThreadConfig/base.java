package ThreadConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.internal.Classes;

import ThreadCreditDebitDisputeResolve.creditDebitThread;
import ThreadCreditDebitDisputeResolve.debitDummyClass;
import ThreadEc.ecdummyClass;
import ThreadEc.ecmod;
import ThreadGFBO.gfboDummyClass;
import ThreadGFBO.gfboThread;
import ThreadInstantInvoice.instantInvoiceDummy;
import ThreadInstantInvoice.instantInvoiceThread;
import ThreadMassERARerate.eraMassRerateUpload;
import ThreadMassERARerate.massRerateDummy;
import ThreadMassERARerate.massRerateThread;
import ThreadPRSRerate.prsRerateDummyClass;
import ThreadPRSRerate.prsRerateThread;
import ThreadSingleERARerate.eraRerateUpload;
import ThreadSingleERARerate.singleEraRerateDummy;
import ThreadSingleERARerate.singleRerateThread;
import ThreadSinglePrerate.prerateHoldThread;
import ThreadSinglePrerate.prerateSingleUpload;
import ThreadSinglePrerate.prerateThread;
import ThreadSingleRebill.eraRebillUpload;
import ThreadSingleRebill.rebillThread;
import ThreadSingleRebill.singleRebillDummy;
import ThreadeMass.threadEmass;
import ThreadeMass.threadEmassDummy;
import configuration.config;

public class base {
	//Global Variables
	ArrayList<data> dataArray;
	ArrayList<Object> threadArray= new ArrayList<Object>();
	int sqlCount,tempCounter=0,function;
	int [][]minxMaxArray;
	config c;
	String[][] ecArray;
	String eraCase,allCheckBox,customCheckBox,customString,nullCheckBox,failedCheckBox,domesticCheckBox,internationalCheckBox,expressCheckBox,groundCheckBox,eraWorkable,databaseSqlCount,databaseSqlQuery,databaseSqlCount2,databaseSqlQuery2,databaseSqlCount3,databaseSqlQuery3;
	int dbVal=1;
	//Constructor
	public base(config c,int function) {
		//Setting initial variables needed later.
		int low=0;
		int high=0;
		this.c=c;
		this.function=function;
		setVars();
		
		//Amount of threads user selected
		int threadCount=Integer.parseInt(c.getSessionCount());
		
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
		
		//Gets the query from view depending on function selected.
		//This is where you will differeniate between different 
		//data pulled from GTM REV TOOLS (and to small extent EC DB).
		setSqlQuery();
		
		getDataDb();


		
		//Gets which segements data is in based on thread count.
		minMaxArrayMath(threadCount);
		
		for(int i=0;i<minxMaxArray.length;i++) {
			//About to break up data into chunks based on partition.
			
			//So now we need to take segmented amount of data from our full array.
			//For this we will create a new data array that will contain
			//Only a segmented part and we will store this into another array later.
			ArrayList<data>	dataArrayPartition = new ArrayList<data>();	
			low= minxMaxArray[i][0];
			high=minxMaxArray[i][1];
			//System.out.println(low);
			//System.out.println(high);
			int cnt=0;
			//Will get each segments top and bottom value
			for (int j=low;j<=high;j++) {
					cnt++;
					try {
						//Adding parts of a big array into a smaller array
						dataArrayPartition.add(dataArray.get(j));
					}
					catch(Exception e) {
						System.out.println("Out of data.");
					}
				}
			
				//Will Create the Objects for each function X amount times based on thread count.. does not start the actual execution yet.
				//So this will be an array that contains the actual executable class and in that we passed the smaller segmented array into.
				//Going with our 100 data with 4 threads example.. there will be 4 arrays that contain 4 executable Classes.
				// In those excutable classes we passed the segments array that contained the 25 test sets.
				// Why do it like this?? Because we need to setup each instance of the executable class but not actually start the execution!
				//This way we can start all at the same time using thread method!
				switch (function) {
					case 1:	
						threadArray.add(new rebillThread(dataArrayPartition,c));
						break;
					case 2:	
						//	threadArray.add(new massRebillThread(dataArrayPartition,c));
						break;
					case 3:	
						threadArray.add(new singleRerateThread(dataArrayPartition,c));
						break;
					case 4:	
						threadArray.add(new massRerateThread(dataArrayPartition,c));
						break;
					case 5:	
						threadArray.add(new creditDebitThread(dataArrayPartition,c));
						break;
					case 6:	
						threadArray.add(new instantInvoiceThread(dataArrayPartition,c));
						break;
					case 7:	
						threadArray.add(new prerateThread(dataArrayPartition,c));
						break;
					case 8:	
						threadArray.add(new prerateHoldThread(dataArrayPartition,c));
						break;
					case 9:	
						threadArray.add(new prsRerateThread(dataArrayPartition,c));
						break;
					case 10:	
						threadArray.add(new instantInvoiceThread(dataArrayPartition,c));
						break;
					case 11:	
						threadArray.add(new gfboThread(dataArrayPartition,c));
						break;
					case 12:
						threadArray.add(new ecmod(dataArrayPartition,c));
						break;
					case 13:
						threadArray.add(new ecmod(dataArrayPartition,c));
						break;
					case 14:
						threadArray.add(new threadEmass(dataArrayPartition,c));
						break;
					case 22:	
						threadArray.add(new eraRerateUpload(dataArrayPartition,c));
						break;
					case 23:	
						threadArray.add(new eraMassRerateUpload(dataArrayPartition,c));
						break;
					case 24:	
						threadArray.add(new eraRebillUpload(dataArrayPartition,c));
						break;
					case 25:	
						threadArray.add(new prerateSingleUpload(dataArrayPartition,c));
						break;
	}
				
			}
			
		//Now will begin the thread execution.
		
		for (Object rt: threadArray) {
			try {
			((Thread) rt).start();
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
			
		
		
	}
	/*
	public  void getDataExcel() {
		try {
		excel e = new excel();
		e.setUpExcelWorkbook("C:\\Users\\5194105\\Documents\\L3 Test Data.xlsx");
		e.setUpExcelSheet(0);
		e.setRowCountAutomatically(2);
		System.out.println(e.getRowCount());
		tempCounter=0;
		dataArray = new ArrayList<data>();
		for (int i=1;i<e.getRowCount();i++) {
		switch(function) {
		case 1:
			dataArray.add(new data(nullCheck(e.getCellData(i, 0)),nullCheck(e.getCellData(i, 1)),nullCheck(e.getCellData(i, 2)),nullCheck(e.getCellData(i, 3)),nullCheck(e.getCellData(i, 4)),nullCheck(e.getCellData(i, 5)),nullCheck(e.getCellData(i, 6)),nullCheck(e.getCellData(i, 7)),nullCheck(e.getCellData(i, 8)),nullCheck(e.getCellData(i, 9)),nullCheck(e.getCellData(i, 10)),nullCheck(e.getCellData(i, 11)),nullCheck(e.getCellData(i, 12)),nullCheck(e.getCellData(i, 13)),nullCheck(e.getCellData(i, 14)),nullCheck(e.getCellData(i, 15)),nullCheck(e.getCellData(i, 16)),nullCheck(e.getCellData(i, 17)),nullCheck(e.getCellData(i, 18)),nullCheck(e.getCellData(i, 19)),nullCheck(e.getCellData(i, 20)),nullCheck(e.getCellData(i, 21)),nullCheck(e.getCellData(i, 22)),tempCounter));	
			
			//dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(21)),tempCounter));	
			
		//	dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(21)),tempCounter));	
		break;
		}
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	*/
	
	public  void setVars() {
		
	
		 allCheckBox=c.getAllCheckBox();
		 nullCheckBox=c.getNullCheckBox();
		 failedCheckBox=c.getFailedCheckBox();
		 domesticCheckBox=c.getDomesticCheckBox();
		 internationalCheckBox=c.getInternationalCheckBox();
		 expressCheckBox=c.getExpressCheckBox();
		 groundCheckBox=c.getGroundCheckBox();
		 customCheckBox= c.getCustomCheckBox();
		 customString= c.getCustomString();	
		 eraWorkable=c.getEraWorkable();
		

		
	}
	
	//This will segment the data based on how many threads are to be ran.
	public  void minMaxArrayMath(int threadCount){
		
		System.out.println("Data Count:"+sqlCount+"   Thread Count:"+threadCount);
		
		//Getting parition amount.. basically means how many rows of data will be seperated.
		//Easy example. If we have 100 trks and want 4 threads.. then each segment will have 25 trks each.
		int partition = sqlCount/threadCount; 
		
		//This is going to be a 2D array which will contain the starting and end point of each segment. Here is a example:
		//We want to run 100 data and run 4 threads. This means we want each thread to run 25 test cases each (100 data divided by 4 threads).
		minxMaxArray = new int[threadCount][2];
		int lowLimit=0;
		
		for(int i = 0;i<threadCount; i++)
	    {
			/*
			If we are just starting, then setting low limit to 0, else we will increase it by 1 from previous low limit
			why? Say our first segment ended at 25. well our next low limit will start at 25. 25 is the high limit for 
			previous segment, so we should add one to make it 26 as low limit for next segment.
			So our array will have 4 rows.. for each thread. Now we need to know the min and max for each segment (thread).
			We want to make sure each segment contains different data.. so we need to know which data to take. 
			Look at this ilistration:
			minMaxArray[0][24]
			minMaxArray[25][49]
			minMaxArray[50][74]
			minMaxArray[75][99]
			
			So what does this exactly mean? well since we have all our data stored in an arraylist, each thread now knows which data to take.
			We will pass data 0-24 into thread one then stop. then we will send data 25-49 to next thread, and so on.
			*/
			
			if(i==0) {
				minxMaxArray[i][0]=lowLimit;
			}
			else {
				minxMaxArray[i][0]=(lowLimit+1);
			}
				//This is the high limit.. meaning it will take max it can take for this segment.
				minxMaxArray[i][1]=(lowLimit += partition);
	         //   System.out.println("Part: "+(i+1)+" min: "+minxMaxArray[i][0]+" max: "+minxMaxArray[i][1]);
	          
	    }
		//This makes it where it wont break if someone chooses to run more threads than actual data rows.
		if(threadCount<sqlCount) {
			minxMaxArray[threadCount-1][1]=sqlCount-1;
		}
	}
	
	
	
	
	
	public  void getDataDb() {
		Connection con=null;
		Connection con2=null;
		Connection con3=null;
		
		try {
			//dbVal is set based on function.. if you look into when getting sql query
			//there it will be set (or default as 1 will initialized)
			if (dbVal==1) {
				con=c.getGtmRevToolsConnection();
			}
			if (dbVal==2) {
				con=c.getRtmCon();
				}
		
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			//This array will store data object.. this is a class that will contain ALL the data
			//which we need to refer to. This is taken almost exclusively from GTM VIEWS.
			dataArray = new ArrayList<data>();
			Statement stmt = null;
			ResultSet rs = null;
		
			//Function 14, which is emass, functions differently. 
			if (function!=14) {
				try {
					//We got the DB Query in previous method.
					stmt = con.createStatement();
	            	rs = stmt.executeQuery(databaseSqlQuery);
           		
	            	//We will store all data into a data object and then give our data array this object.
	            	//Each piece of data first goes through a nullcheck method I set up to ensure there 
	            	//are no nulls. Nulls do not play nice with Java, so instead of null they are set to "".
	            	//There are some that end with  a "dummy class". This is temporary while I try best
	            	//to figure out how to create Object contstructors tell the difference for each
	            	//set of data i provide.
	            	while(rs.next()) {
	            		tempCounter++;
	            		switch(function) {
	            			case 1:
	            				singleRebillDummy srd = null;
	            				dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(21)),nullCheck(rs.getString(22)),nullCheck(rs.getString(23)), srd));	
	            				break;
	            		
	            			case 3:
	            				singleEraRerateDummy serd = null;
	            				dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19)),nullCheck(rs.getString(20)),nullCheck(rs.getString(21)),nullCheck(rs.getString(22)),nullCheck(rs.getString(23)),nullCheck(rs.getString(24)),nullCheck(rs.getString(25)),nullCheck(rs.getString(26)),nullCheck(rs.getString(27)),nullCheck(rs.getString(28)),serd));	
	            				break;
	            		
	                		case 4:
	                			massRerateDummy mrd = null;
	                			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19)),mrd));	
	                			break;
	                		
	                		
	            		case 5:
	            			eraCase = c.getEraCase();
	            			if (eraCase.equals("1") || eraCase.equals("3") || eraCase.equals("4")) {
	                    	dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),eraCase));	
	                    	break;
	            			}
	            			if (eraCase.equals("2")) {
	            				debitDummyClass ddc = null;
	                        	dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),eraCase,ddc));	
	                        	break;
	                			}
	            			if (eraCase.equals("5")) {
	            				debitDummyClass ddc = null;
	            				dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),eraCase,ddc));	
	                        	
	            				break;
	            			}
	                		
	                    	
	            		 case 6:
	            			 instantInvoiceDummy iid  = null;   			 
	              			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),"5194105","5194105",iid));	
	              		     	break;
	                    	
	            		case 7:
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),nullCheck(rs.getString(16)),nullCheck(rs.getString(17)),nullCheck(rs.getString(18)),nullCheck(rs.getString(19))));	
	            		     	break;
	            		     	
	            		     	
	            		case 8:
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7))));	
	            		     	break;
	            		        
	            		case 9:
	            			prsRerateDummyClass pdc=null;
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),pdc));	
	            		     	break;
	            		
	            		case 10:
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),"5194105","5194105",tempCounter));	
	         		     	break;
	            	
	            	 
	            		case 11 :
	            			gfboDummyClass gdc = null;
	          				dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),gdc));	
	          		     	break;
	         		
	            		case 12:
	            			ecdummyClass ecd=null;
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),ecd));
	            			break;
	            		case 13:
	            			dataArray.add(new data(null,null,nullCheck(rs.getString(1)),null));
	            		 break;
	            	
	            		case 22:
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3))));	
	           		     	break;
	           		     	
	            		case 23:
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3))));	
	         		     	break;
	         		     	
	            		case 24:
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3))));	
	          		     	break;
	          		     	
	            		case 25:
	            			dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3))));	
	           		     	break;
	            		}
	  	          	}
	        	}
	        	catch(Exception e) {
	        		System.out.println(e);
	        	}
	       	
				
		//This will get amount of rows returned.. ie, number of test cases being used.		
     	try {
           	stmt = con.createStatement();
        	rs = stmt.executeQuery(databaseSqlCount);
            
        	while(rs.next()) {
        		sqlCount=rs.getInt(1);
        	 }
        
     		}
     		catch(Exception e) {
     			System.out.println(e);
     		}
       	}
			
			
			
			//This is for emass only.. will revist later
			if (function==14) {
				threadEmassDummy ted = new threadEmassDummy();
				try {
					if(c.getEmassCase().equals("1") || c.getEmassCase().equals("4")) {
						stmt = con.createStatement();
						rs = stmt.executeQuery(databaseSqlQuery);
						
						while(rs.next()) {
							dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),nullCheck(rs.getString(10)),nullCheck(rs.getString(11)),nullCheck(rs.getString(12)),nullCheck(rs.getString(13)),nullCheck(rs.getString(14)),nullCheck(rs.getString(15)),"1",ted));	
						}
            		}
					
					if(c.getEmassCase().equals("2") || c.getEmassCase().equals("4")) {
						stmt = con.createStatement();
						rs = stmt.executeQuery(databaseSqlQuery2);
						
						while(rs.next()) {
							dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),"2",ted));	
						}
            		}
					
					if(c.getEmassCase().equals("3") || c.getEmassCase().equals("4")) {
						stmt = con.createStatement();
						rs = stmt.executeQuery(databaseSqlQuery3);
					
						while(rs.next()) {
							dataArray.add(new data(nullCheck(rs.getString(1)),nullCheck(rs.getString(2)),nullCheck(rs.getString(3)),nullCheck(rs.getString(4)),nullCheck(rs.getString(5)),nullCheck(rs.getString(6)),nullCheck(rs.getString(7)),nullCheck(rs.getString(8)),nullCheck(rs.getString(9)),"3",ted));	
						}
            		}
				}
				catch(Exception e) {
					System.out.println(e);
				}
				sqlCount=0;
				for (data d:dataArray) {
					sqlCount++;
				}
			}
			
		//This is for EC part where I get all the EC data.. which is little complicated so broke it off to its own method.
		if (function==12 || function==13) {
       		getEcData();
			}
       	
       	
       	//Closing all connections we used since it is no longer needed.
       	try {
       		con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public String nullCheck(String temp) {
		if (temp==null){
			temp="";
		}
		return temp;
	}
	
	//Getting EC Override data
	public void getEcData() {
 		
		Connection con=null,con2=null,con3=null;
		Statement stmt = null;
		ResultSet rs = null;
		 int ecCount=0;
		try {
			con=c.getGtmRevToolsConnection();
			con2=c.getEcL3DbConnection();
			con3=c.getEcL3DbConnection();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//This is getting all the errors that can be overridden and will store those. First Query is to get the total count of them.
		//This is because I will make a 2d array and need to know how many rows to make. will explain more later.
   		try {
   			stmt = con2.createStatement();
   			rs = stmt.executeQuery("select count(*) from (select distinct stat_cd,stat_ovrd_type_cd from ec_schema.stat_cd_detl where work_type_cd like '%INTL%')");
           
       	 while(rs.next()) {
       		 ecCount=rs.getInt(1);
       	 }
       	 ecArray = new String[ecCount][2];
       	 
   			
       	 //Now we will actuall store stat code and override value. if stat_ovrd_type_cd is 'O' then it is overridable
       	 databaseSqlQuery="select distinct stat_cd,stat_ovrd_type_cd from ec_schema.stat_cd_detl where work_type_cd like '%INTL%'";
			stmt = con3.createStatement();
			rs = stmt.executeQuery(databaseSqlQuery);
			
			int counter=0;
			//Make our 2d array with stat code and override value.
			while(rs.next()) {
				ecArray[counter][0]=rs.getString(1);
				ecArray[counter][1]=rs.getString(2);
				counter++;
				}
		   	} 
   		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
   		
   		//sqlCount counts how many records we are going through. After around 600 records the DB will timeout.
   		//Instead of timinging out, we will start a new connection after 500 rows.
   		int sqlcount=0;
   		
   		//Will iterate through all the data we pulled earlier from DB (means our data.. our trks, tins, etc)
   		for (data d : dataArray) {
   			try {
   				//Determines if I should start new connection so it wont timeout.
   				if ((sqlcount%500)==0) {
   					con2 = c.getEcL3DbConnection();
    			}
   				
   				sqlcount++;
   				
   				//This query will pull all the fucntional error stat codes and if it is airbill or auto type for each trk.
   				databaseSqlQuery="select WORK_TYPE_CD,STAT_CD_ARRAY_DESC from ec_schema.shipment a join ec_schema.package b on a.ONLN_REV_ITEM_ID=b.ONLN_REV_ITEM_ID join ec_schema.pkg_stat_cd_array c on b.ONLN_PKG_ID=c.ONLN_PKG_ID where ARRAY_TYPE_CD='F' and pkg_trkng_nbr="+d.getTrkngnbr();
				stmt = con2.createStatement();
				rs = stmt.executeQuery(databaseSqlQuery);
				
				//So here we are going to store these stat codes and whether it is airbill or auto. This is stored on the data object. We already created data object 
				//and called it to get the trk.. now just storing more info on it.
				//Still note at this point we have NOT determined if the trk only has override errors.
				while(rs.next()) {
			        d.setEcWorkType(rs.getString(1));
	        		d.setStatCodeArray(rs.getString(2));
	        		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		}
   	
   	
   	//This is where we are going to determine if this shipment can be overridable.
   		//First, iterate through the array for each object.. ie each trk.
   		//Now remember when we stored EVERY stat code and if it is overridable in a 2d array?
   		//Well now we will compare the functional errors for each trk to the 2d array list of errors.
   		//If there is even a single functional error that isnt overridable while comparing,
   		//it will mark this test case as not overridable.. meaning in actual program it will be skipped.
   	for (data d : dataArray) {
   		d.setOverride(true);
   		d.setEcOverrideUd(false);
   		//System.out.println(d.getTrkngnbr());
   		//System.out.println(d.getEcWorkType());
   		//System.out.println(d.getStatCodeArray());
   	
   		try {
   			//Since in EC DB all func errors are stored in one row per trk, we will need to split them up and store each one to a string
   			String tempArrayError=d.getStatCodeArray();
   			String[] tempSplit = tempArrayError.split(",");
   			
   			//Iterating through the new set of strings made for each func error
   			for(String s: tempSplit) {
   				//Looping through entire func error array.
   				for (int i=0;i<ecArray.length;i++){
   					//If matches our func error on trk to func error in total list.
   					if (s.equals(ecArray[i][0])) {
   						//If it matches with a N.. meaning not overridable, then we will mark the test case as false for overridable.
   						//In the EC program it will then skip this test case.
   						if (ecArray[i][1].equals("N")) {
   							d.setOverride(false);
   							break;
   						}
   					}
   				}
   			}
   		}
   		catch(Exception e) {
   			System.out.println(e);
   			d.setOverride(false);
   		}
   		//System.out.println("Tracking Number: " +d.getTrkngnbr()+" --  Work Type: "+ d.getEcWorkType()+ " Overridable: "+d.getOverride());
   	}
   		
   	//This is for EC UDS.. it will look to see if we have overridable steps for a trks.
   	if(function==12) {
   		//Go through each test case
   		for (data d : dataArray) {
   			try {
   				//Pulling from our EC UD DB with the different steps.
				databaseSqlQuery="select * from ec_temp where scenario_id='"+d.getScenarioId()+"' and shipment_id='"+d.getShipmentId()+"'";
				Statement stmtEc = con.createStatement();
				ResultSet rsEC = stmtEc.executeQuery(databaseSqlQuery);
				System.out.println(databaseSqlQuery);
				//If the scenario id and scenario shipment matches what we have in our DB then it will store this data and be using in program.
				//The data is stored in an array in our object data.
				while(rsEC.next()) {
					//This boolean is very important. Normally we will not let a shipment that doesnt have an overridable shipment to get processed,
					//but since we have the correction steps, we will make an exception.. this boolean will allow shipments that dont have
					//overridable error still pass through.
					d.setEcOverrideUd(true);
					d.addEcDataArray(rsEC.getString(1), rsEC.getString(2), rsEC.getString(3), rsEC.getString(4), rsEC.getString(5));
					}
    	
   			}
   			catch(Exception e) {
			System.out.println(e);
   			}
		/*
   		for(ecData ed: d.getEcDataArray()) {
			System.out.println(ed.toString());
		}
		*/
   			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public  void setSqlQuery() {
	//	System.out.println("Inside QUERY");
		//sets up default query
			switch (function) {
				case 1:	
					 databaseSqlCount="select count(*) as total from rebill_regression ";
				     databaseSqlQuery="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR	,TIN_COUNT	,TRKNGNBR,	REASON_CODE	,BILL_ACCT_NBR	,INVOICE_NBR_1,	INVOICE_NBR_2	,REGION	,USERNAME,	PASSWORD,	RS_TYPE,	COMPANY	,rebill_prerate,svc_type, length,width,height,actual_weight,	WORKABLE,	DEFECT_FLG,	DEFECT_NBR	,DEFECT_CONTACT from rebill_regression ";
				    	
				    	if (allCheckBox.equals("true")) {
				    		databaseSqlCount+="where trkngnbr is not null";
				    		databaseSqlQuery+="where trkngnbr is not null ";
				    	}
				    	
				    	//System.out.println(customCheckBox);
				    	//System.out.println(customString);
				    	
				    	if (customCheckBox.equals("false")) {
				    	
				    	if (allCheckBox.equals("false")) {
				    		databaseSqlCount+="where ";
				    		databaseSqlQuery+="where ";

				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="(result is null or result ='fail') ";
				    		databaseSqlQuery+="(result is null or result ='fail') ";
				    	}
				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
				    		databaseSqlCount+="result is null ";
				    		databaseSqlQuery+="result is null ";
				    	}
				    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="result ='fail' ";
				    		databaseSqlQuery+="result ='fail' ";
				    	}
				    	if (domesticCheckBox.equals("true") && internationalCheckBox.equals("false")) {
				    		databaseSqlCount+="and rs_type='DM' ";
				    		databaseSqlQuery+="and rs_type='DM' ";
				    	}
				    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("false")) {
				    		databaseSqlCount+="and rs_type='IL' ";
				    		databaseSqlQuery+="and rs_type='IL' ";
				    	}
				    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("true")) {
				    		databaseSqlCount+="and rs_type in ('DM','IL')";
				    		databaseSqlQuery+="and rs_type in ('DM','IL')";
				    	}
				    	
				    	if (expressCheckBox.equals("true") && groundCheckBox.equals("false")) {
				    		databaseSqlCount+="and company='EP' ";
				    		databaseSqlQuery+="and company='EP' ";
				    	}
				    	if (groundCheckBox.equals("true") && expressCheckBox.equals("false")) {
				    		databaseSqlCount+="and company='GD' ";
				    		databaseSqlQuery+="and company='GD' ";
				    	}
				    	
				    	if (groundCheckBox.equals("true") && expressCheckBox.equals("true")) {
				    		databaseSqlCount+="and company in ('GD','EP') ";
				    		databaseSqlQuery+="and company in ('GD','EP') ";
				    	}
				    		}
				    			}
				    	else if (customCheckBox.equals("true")){
				    		databaseSqlCount+="where trkngnbr is not null and "+customString;
				    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
				    	}
				    	break;
				
				case 2:	
					databaseSqlQuery="select result, description, test_input_nbr, rowcount, trkngnbr, reason_code, bill_acct_nbr,invoice_nbr_1, invoice_nbr_2,  region,  username,   password,  rs_Type, company from rebill_regression_mass ";
					databaseSqlCount="select count(*) from  rebill_regression_mass ";
				break;
				
				//Single ERA Rerate
				case 3:	
					 databaseSqlCount="select count(*) as total from era_rerate_view where trkngnbr is not null ";
			    	 databaseSqlQuery="select RESULT,	DESCRIPTION,	TEST_INPUT_NBR,	TIN_COUNT,	TRKNGNBR,	INVOICE_NBR_1	,INVOICE_NBR_2,	RATE_WEIGHT,	ACTUAL_WEIGHT,	WGT_TYPE,	LENGTH,	WIDTH,	HEIGHT	,WORKABLE,	DIM_TYPE,	PAYOR	,BILL_ACCT_NBR	,SERVICE_TYPE,	SERVICE_NAME,	PACKAGE_TYPE	,RERATE_TYPE,	REGION,	USERNAME,	PASSWORD,	RS_TYPE,	COMPANY,	VAL_DESC,	COMMENTS from era_rerate_view where trkngnbr is not null ";
			    	
			    	if (allCheckBox.equals("true")) {
			    		databaseSqlCount+="where trkngnbr is not null";
			    		databaseSqlQuery+="where trkngnbr is not null ";
			    	}
			    	
			    	System.out.println(customCheckBox);
			    	System.out.println(customString);
			    	
			    	if (customCheckBox.equals("false")) {
			    	
			    	if (allCheckBox.equals("false")) {
			    		databaseSqlCount+="and ";
			    		databaseSqlQuery+="and ";
			    
			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="(result is null or result ='fail') ";
			    		databaseSqlQuery+="(result is null or result ='fail') ";
			    	}
			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
			    		databaseSqlCount+="result is null ";
			    		databaseSqlQuery+="result is null ";
			    	}
			    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="result ='fail' ";
			    		databaseSqlQuery+="result ='fail' ";
			    	}
			    	if (domesticCheckBox.equals("true") && internationalCheckBox.equals("false")) {
			    		databaseSqlCount+="and rs_type='DM' ";
			    		databaseSqlQuery+="and rs_type='DM' ";
			    	}
			    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("false")) {
			    		databaseSqlCount+="and rs_type='IL' ";
			    		databaseSqlQuery+="and rs_type='IL' ";
			    	}
			    	if (internationalCheckBox.equals("true") && domesticCheckBox.equals("true")) {
			    		databaseSqlCount+="and rs_type in ('DM','IL')";
			    		databaseSqlQuery+="and rs_type in ('DM','IL')";
			    	}
			    	
			    	if (expressCheckBox.equals("true") && groundCheckBox.equals("false")) {
			    		databaseSqlCount+="and company='EP' ";
			    		databaseSqlQuery+="and company='EP' ";
			    	}
			    	if (groundCheckBox.equals("true") && expressCheckBox.equals("false")) {
			    		databaseSqlCount+="and company='GD' ";
			    		databaseSqlQuery+="and company='GD' ";
			    	}
			    	
			    	if (groundCheckBox.equals("true") && expressCheckBox.equals("true")) {
			    		databaseSqlCount+="and company in ('GD','EP') ";
			    		databaseSqlQuery+="and company in ('GD','EP') ";
			    	}
			    	if (eraWorkable.equals("true")) {
			    		databaseSqlCount+="and workable='Y'";
			    		databaseSqlQuery+="and workable='Y'";
			    	}
			    		}
			    			}
			    	else if (customCheckBox.equals("true")){
			    		databaseSqlCount+=" and "+customString;
			    		databaseSqlQuery+=" and "+customString;
			    	}
			       	
				break;
				
			
			case 4:	
					databaseSqlQuery="select  result,  DESCRIPTION, request_id, test_Input_Nbr,tin_Count, trkngnbr, invoice_Nbr_1, invoice_Nbr_2, region, username , password,  rate_weight,wgt_type,length,height,width,dim_type, rerate_type, rs_Type ,company  , mass_rerate_combo from era_rerate_mass ";
					databaseSqlCount="select count(*) from  era_rerate_mass ";
				
					if (allCheckBox.equals("true")) {
			    		databaseSqlCount+="where trkngnbr is not null";
			    		databaseSqlQuery+="where trkngnbr is not null ";
			    	}
			    	
			    	System.out.println(customCheckBox);
			    	System.out.println(customString);
			    	
			    	if (customCheckBox.equals("false")) {
			    	
			    	if (allCheckBox.equals("false")) {
			    		databaseSqlCount+="where trkngnbr is not null and ";
			    		databaseSqlQuery+="where trkngnbr is not null and ";
			    	
			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="(result is null or result ='fail') ";
			    		databaseSqlQuery+="(result is null or result ='fail') ";
			    	}
			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
			    		databaseSqlCount+="result is null ";
			    		databaseSqlQuery+="result is null ";
			    	}
			    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="result ='fail' ";
			    		databaseSqlQuery+="result ='fail' ";
			    	}
			    	
			    	if (eraWorkable.equals("true")) {
			    		databaseSqlCount+="and workable='Y'";
			    		databaseSqlQuery+="and workable='Y'";
			    	}
			    		}
			    			}
			    	else if (customCheckBox.equals("true")){
			    		databaseSqlCount+="where trkngnbr is not null and "+customString;
			    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
			    	}
			    	
			    	databaseSqlCount+=" order by test_input_nbr";
					databaseSqlQuery+=" order by test_input_nbr";
			    	break;
				
		
				
				//Credit Debit
				case 5:	
					 
					eraCase = c.getEraCase();

					switch(eraCase) {
					case "1":
						databaseSqlCount="select count(*) as total from era_credit ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_credit " ;
					break;
					case "2":
						databaseSqlCount="select count(*) as total from era_debit ";
						databaseSqlQuery="select credit_result, credit_description,debit_result, debit_description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_debit " ;
						break;
					case "3":
						databaseSqlCount="select count(*) as total from era_dispute ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_dispute " ;
						break;
					case "4":
						databaseSqlCount="select count(*) as total from era_resolve_credit ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,	WORKABLE ,REASON_CATEGORY,	Reason_code, ROOT_CAUSE,VAL_DESC from era_resolve_credit " ;
						break;
					case "5":
						databaseSqlCount="select count(*) as total from rebill_resolve_view ";
						databaseSqlQuery="select result, description, TEST_INPUT_NBR,	TIN_COUNT	,TRKNGNBR,	reason_code,bill_acct_nbr,INVOICE_NBR_1,	INVOICE_NBR_2,	REGION,	USERNAME,	PASSWORD,comments,val_Desc from rebill_resolve_view " ;
						
											
						break;
					}
				
						
					
					if (allCheckBox.equals("true")) {
						databaseSqlCount+="where trkngnbr is not null";
						databaseSqlQuery+="where trkngnbr is not null ";
					}
					
					System.out.println(customCheckBox);
					System.out.println(customString);
					
					if (customCheckBox.equals("false")) {
					
					if (allCheckBox.equals("false")) {
						databaseSqlCount+="where trkngnbr is not null and ";
						databaseSqlQuery+="where trkngnbr is not null and ";
					
					
					
					
					if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
						databaseSqlCount+="(result is null or result ='fail') and (";
						databaseSqlQuery+="(result is null or result ='fail') and (";
					}
					if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
						databaseSqlCount+="result is null and (";
						databaseSqlQuery+="result is null and (";
					}
					if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
						databaseSqlCount+="result ='fail' and (";
						databaseSqlQuery+="result ='fail' and (";
					}
						}
							}
					else if (customCheckBox.equals("true")){
						databaseSqlCount+="where trkngnbr is not null and "+customString;
						databaseSqlQuery+="where trkngnbr is not null and "+customString;
					}
				break;
				
				
				
				case 6:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TRKNGNBR	,PAYOR_ACCT_NBR,	ITEM_PRCS_CD	,INSTNT_INV_FLG from instant_invoice_view   ";
					databaseSqlCount="select count(*) from  instant_invoice_view ";
					
					if (allCheckBox.equals("true")) {
			    		databaseSqlCount+="where trkngnbr is not null and instnt_inv_flg is null ";
			    		databaseSqlQuery+="where trkngnbr is not null and instnt_inv_flg is null";
			    	}
			    	
			    	System.out.println(customCheckBox);
			    	System.out.println(customString);
			    	
			    	if (customCheckBox.equals("false")) {
			    	
			    	if (allCheckBox.equals("false")) {
			    		databaseSqlCount+="where  ";
			    		databaseSqlQuery+="where  ";

			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="(instnt_inv_flg is null or instnt_inv_flg ='fail') ";
			    		databaseSqlQuery+="(instnt_inv_flg is null or instnt_inv_flg ='fail') ";
			    	}
			    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
			    		databaseSqlCount+="instnt_inv_flg is null ";
			    		databaseSqlQuery+="instnt_inv_flg is null ";
			    	}
			    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
			    		databaseSqlCount+="instnt_inv_flg ='fail' ";
			    		databaseSqlQuery+="instnt_inv_flg ='fail' ";
			    	}
			    	
			    	}
			    	}
			    	else if (customCheckBox.equals("true")){
			    		databaseSqlCount+="where (instnt_inv_flg is null or instnt_inv_flg ='fail')  and "+customString;
			    		databaseSqlQuery+="where (instnt_inv_flg is null or instnt_inv_flg ='fail')  and "+customString;
			    	}
			    	
					
				break;
			
				//Prerate Single
				case 7:	
					
					
					
					 databaseSqlCount="select count(*) as total from prerate_view ";
				  	 databaseSqlQuery="select result,description,TEST_INPUT_NBR,TIN_COUNT ,TRKNGNBR ,PRE_RATE_TYPE_CD,PRERATE_AMT, CURRENCY_CD, APPROVER_ID ,CHRG_CD1 ,CHRG_AMT1 ,CHRG_CD2 ,CHRG_AMT2 ,CHRG_CD3, CHRG_AMT3, CHRG_CD4, CHRG_AMT4,val,expected_status from prerate_view ";
				    
				  	 if (customCheckBox.equals("true")) {
				  		databaseSqlCount+="where trkngnbr is not null and "+customString;
			    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
				  		 
				  	 }
				  	 else
				  	 {
				    	if (allCheckBox.equals("false")) {
				    		databaseSqlCount+="where trkngnbr is not null and ";
				    		databaseSqlQuery+="where trkngnbr is not null and ";
				    	}
				    
				    	 if (allCheckBox.equals("true")) {
				     		databaseSqlCount+="where trkngnbr is not null  ";
				     		databaseSqlQuery+="where trkngnbr is not null   ";
				     	}
				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="(result is null or result ='fail') ";
				    		databaseSqlQuery+="(result is null or result ='fail') ";
				    	}
				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
				    		databaseSqlCount+="result is null ";
				    		databaseSqlQuery+="result is null ";
				    	}
				    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="result ='fail' ";
				    		databaseSqlQuery+="result ='fail' ";
				    
				    	}}
				    	
				break;
				
				
				
				//Prerate Hold
				case 8:	
					databaseSqlQuery="select RESULT, DESCRIPTION,POD_SCAN,TEST_INPUT_NBR,TIN_COUNT,TRKNGNBR,TIN_COMMENT from prerate_hold_view  ";
					databaseSqlCount="select count(*) from  prerate_hold_view ";
					
					
					
					if (customCheckBox.equals("true")) {
				  		databaseSqlCount+="where trkngnbr is not null and "+customString;
			    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
				  		 
				  	 }
					else {
				    	if (allCheckBox.equals("false")) {
				    		databaseSqlCount+="where trkngnbr is not null and ";
				    		databaseSqlQuery+="where trkngnbr is not null and ";
				    	}
				    
				    	 if (allCheckBox.equals("true")) {
				     		databaseSqlCount+="where trkngnbr is not null  ";
				     		databaseSqlQuery+="where trkngnbr is not null   ";
				     	}
				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="(result is null or result ='fail') ";
				    		databaseSqlQuery+="(result is null or result ='fail') ";
				    	}
				    	if (nullCheckBox.equals("true") && failedCheckBox.equals("false")) {
				    		databaseSqlCount+="result is null ";
				    		databaseSqlQuery+="result is null ";
				    	}
				    	if (nullCheckBox.equals("false") && failedCheckBox.equals("true")) {
				    		databaseSqlCount+="result ='fail' ";
				    		databaseSqlQuery+="result ='fail' ";
				    	}
				    	}
					
				break;
				case 9:	
					 databaseSqlCount="select count(*) as total from (select * from rerate_master where trk_no1 is not null and acct2 is null union all select * from rerate_master where trk_no1 is not null and trk_no2 is not null and acct2 is not null)";
			    	 databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,ACCT1,	ACCT2,	TRK_NO1,	TRK_NO2	,INVOICE_NBR_1,	INV_NO2,	SERVICE1,	SERVICE2,	REQUEST_TYPE,	ACCT_TYPE,	ACCNAME from (select * from rerate_master where trk_no1 is not null and acct2 is null union all select * from rerate_master where trk_no1 is not null and trk_no2 is not null and acct2 is not null)";
			    	
			    	 if (customCheckBox.equals("true")){
			    		databaseSqlCount+="where trk_no1 is not null and "+customString;
			    		databaseSqlQuery+="where trk_no1 is not null and "+customString;
			    	}
				break;
				
				
				
				case 10:	
					databaseSqlQuery="select trkngnbr,PAYOR_ACCT_NBR from ( select c.test_input_nbr,c.trkngnbr, PAYOR_ACCT_NBR,INSTNT_INV_FLG, case when cntry_cd is null then 'N' else 'Y' END as TimePeriodEligble, TO_CHAR(LPAR_ENHCMNT_DT + 2-(5/24), 'YYYY-MM-DD HH:MI:SS AM') as RM_TIME_STAMP from INTL_EXPRS_ONLN_SCHEMA.INTL_online_revenue_item@L3_IORE a join INTL_EXPRS_ONLN_SCHEMA.INTL_package@L3_IORE b on a.ONLN_REV_ITEM_ID = b.ONLN_REV_ITEM_ID  join rtm.batch_shipping_results c on c.trkngnbr=b.pkg_trkng_nbr join INTL_EXPRS_ONLN_SCHEMA.intl_onln_cust_addr_info@L3_IORE d on b.ONLN_REV_ITEM_ID=d.ONLN_REV_ITEM_ID left join INTL_EXPRS_ONLN_SCHEMA.time_period_country@L3_IORE e on d.CUST_CNTRY_CD=e.cntry_cd join  intl_EXPRS_ONLN_SCHEMA.intl_package_event@L3_IORE f on b.onln_pkg_id = f.onln_pkg_id  join INTL_EXPRS_ONLN_SCHEMA.intl_rev_item_payor@L3_IORE i on b.ONLN_REV_ITEM_ID=i.ONLN_REV_ITEM_ID where CYCLE = '"+c.getCycle()+"' and LEVELS = '3' and rs_type = 'IL' and company = 'EP' and src_org='B' and CUST_ROLE_TYPE_CD='B'  and item_prcs_cd in ('OR','ER') ) where TimePeriodEligble='Y' and INSTNT_INV_FLG is null order by trkngnbr desc";
					databaseSqlCount="select count(*) from ( select c.test_input_nbr,c.trkngnbr, PAYOR_ACCT_NBR,INSTNT_INV_FLG, case when cntry_cd is null then 'N' else 'Y' END as TimePeriodEligble, TO_CHAR(LPAR_ENHCMNT_DT + 2-(5/24), 'YYYY-MM-DD HH:MI:SS AM') as RM_TIME_STAMP from INTL_EXPRS_ONLN_SCHEMA.INTL_online_revenue_item@L3_IORE a join INTL_EXPRS_ONLN_SCHEMA.INTL_package@L3_IORE b on a.ONLN_REV_ITEM_ID = b.ONLN_REV_ITEM_ID  join rtm.batch_shipping_results c on c.trkngnbr=b.pkg_trkng_nbr join INTL_EXPRS_ONLN_SCHEMA.intl_onln_cust_addr_info@L3_IORE d on b.ONLN_REV_ITEM_ID=d.ONLN_REV_ITEM_ID left join INTL_EXPRS_ONLN_SCHEMA.time_period_country@L3_IORE e on d.CUST_CNTRY_CD=e.cntry_cd join  intl_EXPRS_ONLN_SCHEMA.intl_package_event@L3_IORE f on b.onln_pkg_id = f.onln_pkg_id  join INTL_EXPRS_ONLN_SCHEMA.intl_rev_item_payor@L3_IORE i on b.ONLN_REV_ITEM_ID=i.ONLN_REV_ITEM_ID where CYCLE = '"+c.getCycle()+"' and LEVELS = '3' and rs_type = 'IL' and company = 'EP' and src_org='B' and CUST_ROLE_TYPE_CD='B'  and item_prcs_cd in ('OR','ER')) where TimePeriodEligble='Y' and INSTNT_INV_FLG is null order by trkngnbr desc ";
					dbVal=2;
					break;
				
					
					
					
					
					
					
				case 11:	
					databaseSqlQuery="select result,description,GFBO_USERNAME,GFBO_PASSWORD,GFBO_PAYMENT_LEVEL,GFBO_PAYMENT_TYPE,GFBO_ACCOUNT,GFBO_EXPECTED_RESULT from gfbo_regression_view  ";
					databaseSqlCount="select count(*) from  gfbo_regression_view ";
					
					if (customCheckBox.equals("true")) {
				  		databaseSqlCount+="where "+customString;
			    		databaseSqlQuery+="where "+customString;
				  		 
				  	 }
					
				break;
					
				case 12:	
				databaseSqlQuery="select distinct * from (select scenario_id,shipment_id,trkngnbr from ud_green_er union all select scenario_id,shipment_id,trkngnbr from ud_rebs_er) ";
					databaseSqlCount="select count(*) from (select distinct scenario_id,shipment_id,trkngnbr from ud_green_er union all select distinct scenario_id,shipment_id,trkngnbr from ud_rebs_er) ";
					
					if (customCheckBox.equals("true")) {
				  		databaseSqlCount+="where "+customString;
			    		databaseSqlQuery+="where "+customString;
				  		 
				  	 }
				  	 
				break;
				
				
				
				
				
				case 13:	
				
					databaseSqlQuery="select b.pkg_trkng_nbr from INTL_EXPRS_ONLN_SCHEMA.INTL_online_revenue_item@L3_IORE a join INTL_EXPRS_ONLN_SCHEMA.INTL_package@L3_IORE b on a.ONLN_REV_ITEM_ID = b.ONLN_REV_ITEM_ID  join rtm.batch_shipping_results@RTM_PROD c on c.trkngnbr=b.pkg_trkng_nbr join  intl_EXPRS_ONLN_SCHEMA.intl_package_event@L3_IORE d on b.onln_pkg_id = d.onln_pkg_id where CYCLE = '"+c.getCycle()+"' and LEVELS = '3' and rs_type = 'IL' and company = 'EP'  and src_org='B'and DEVICE not in ('UD','NRB','PAPER','DTT') and a.ITEM_PRCS_CD in ('ER') and LPAR_ENHCMNT_DT is not null";
					databaseSqlCount="select count(*) from INTL_EXPRS_ONLN_SCHEMA.INTL_online_revenue_item@L3_IORE a join INTL_EXPRS_ONLN_SCHEMA.INTL_package@L3_IORE b on a.ONLN_REV_ITEM_ID = b.ONLN_REV_ITEM_ID  join rtm.batch_shipping_results@RTM_PROD c on c.trkngnbr=b.pkg_trkng_nbr join  intl_EXPRS_ONLN_SCHEMA.intl_package_event@L3_IORE d on b.onln_pkg_id = d.onln_pkg_id where CYCLE = '"+c.getCycle()+"' and LEVELS = '3' and rs_type = 'IL' and company = 'EP' and src_org='B'and DEVICE not in ('UD','NRB','PAPER','DTT') and a.ITEM_PRCS_CD in ('ER') and LPAR_ENHCMNT_DT is not null";
									
				break;
				
				case 14:	
				
					switch(c.getEmassCase()) {
					case "1":
					databaseSqlQuery="select TEST_INPUT_NBR,	TRKNGNBR,	EMASS_ORIGIN_CD,	EMASS_PUP_EMP_ID,	EMASS_PUP_ROUTE,	EMASS_FORM_ID	,EMASS_COSMO_NBR	,EMASS_STOP_TYPE	,EMASS_DEST_CITY_SHORT,	EMASS_DEST_COUNTRY_CD, EMASS_DEST_COUNTRY_POSTAL,	EMASS_BASE_SVC,	EMASS_PACKAGE_TYPE,	EMASS_HANDLING_CD,	EMASS_DEL_ADDRESS from emass_pup ";
					databaseSqlCount="select count(*) from emass_pup ";
					break;
					case "2":
					databaseSqlQuery2="select TEST_INPUT_NBR,	TRKNGNBR,	EMASS_STAT_DEST_CD,	EMASS_STAT_EMP_ID,	EMASS_STANDARD_EXPORT from emass_stat65 ";
					databaseSqlCount2="select count(*) from emass_stat65 ";
					break;
					case "3":
						
					databaseSqlQuery3="select TEST_INPUT_NBR,	TRKNGNBR,		EMASS_POD_DEST_CD,	EMASS_POD_EMP_ID,	EMASS_POD_ROUTE,	EMASS_RECEIVED_BY,	EMASS_DEL_ADDRESS,	EMASS_DEL_LOC,	EMASS_SIG_REC_LINE_NBR,	EMASS_SIG_REC_ID from emass_pod ";
					databaseSqlCount3="select count(*) from emass_pod ";
					break;
					
					case "4":
						databaseSqlQuery="select TEST_INPUT_NBR,	TRKNGNBR,	EMASS_ORIGIN_CD,	EMASS_PUP_EMP_ID,	EMASS_PUP_ROUTE,	EMASS_FORM_ID	,EMASS_COSMO_NBR	,EMASS_STOP_TYPE	,EMASS_DEST_CITY_SHORT,	EMASS_DEST_COUNTRY_CD, EMASS_DEST_COUNTRY_POSTAL,	EMASS_BASE_SVC,	EMASS_PACKAGE_TYPE,	EMASS_HANDLING_CD,	EMASS_DEL_ADDRESS from emass_pup ";
						databaseSqlCount="select count(*) from emass_pup ";
						databaseSqlQuery2="select TEST_INPUT_NBR,	TRKNGNBR,	EMASS_STAT_DEST_CD,	EMASS_STAT_EMP_ID,	EMASS_STANDARD_EXPORT from emass_stat65 ";
						databaseSqlCount2="select count(*) from emass_stat65 ";
						databaseSqlQuery3="select TEST_INPUT_NBR,	TRKNGNBR,	EMASS_POD_DEST_CD,	EMASS_POD_EMP_ID,	EMASS_POD_ROUTE,	EMASS_RECEIVED_BY,	EMASS_DEL_ADDRESS,	EMASS_DEL_LOC,	EMASS_SIG_REC_LINE_NBR,	EMASS_SIG_REC_ID from emass_pod ";
						databaseSqlCount3="select count(*) from emass_pod ";
						
						
						break;
					}
				
					if (customCheckBox.equals("true")) {
				  		databaseSqlCount+="where "+customString;
			    		databaseSqlQuery+="where "+customString;
			    		databaseSqlCount2+="where "+customString;
			    		databaseSqlQuery2+="where "+customString;
			    		databaseSqlCount3+="where "+customString;
			    		databaseSqlQuery3+="where "+customString;
				  		 
				  	 }
					
				break;
				
				
			


					
					
				case 22:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,trkngnbr from era_rerate_view  ";
					databaseSqlCount="select count(*) from  era_rerate_view ";
					
					
					 if (customCheckBox.equals("true")) {
					  		databaseSqlCount+="where trkngnbr is not null and "+customString;
				    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
					  		 
					  	 }
					
				break;
				
				
				case 23:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,trkngnbr from era_rerate_mass  ";
					databaseSqlCount="select count(*) from  era_rerate_mass ";
					
					
					 if (customCheckBox.equals("true")) {
					  		databaseSqlCount+="where trkngnbr is not null and "+customString;
				    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
					  		 
					  	 }
					
				break;
				
				case 24:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,trkngnbr from rebill_regression  ";
					databaseSqlCount="select count(*) from  rebill_regression ";
					
					
					 if (customCheckBox.equals("true")) {
					  		databaseSqlCount+="where trkngnbr is not null and "+customString;
				    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
					  		 
					  	 }
					 break;
					 
				case 25:	
					databaseSqlQuery="select TEST_INPUT_NBR	,TIN_COUNT	,trkngnbr from prerate_view  ";
					databaseSqlCount="select count(*) from  prerate_view ";
					
					
					 if (customCheckBox.equals("true")) {
					  		databaseSqlCount+="where trkngnbr is not null and "+customString;
				    		databaseSqlQuery+="where trkngnbr is not null and "+customString;
					  		 
					  	 }
					
				break;
				
				}
				
				System.out.println(databaseSqlQuery);
				System.out.println(databaseSqlCount);
	
	}
}
