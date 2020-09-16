package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonLibrary.PBFunctionLibrary;
import Constants.PBConstants;
import Utitlities.ExcelFileUtil;

public class DriverScriptHybrid extends PBConstants{
	ExtentReports report;
	ExtentTest test;
	//path for reading excel file
	String inputpath = "F:\\eclipse-workspace\\Automation_Frameworks\\TestInput\\HybridTest.xlsx";
	//path for writing results
	String outputpath= "F:\\eclipse-workspace\\Automation_Frameworks\\TestOutput\\Hybridresult.xlsx";
			
//C:\Users\hp\eclipse-workspace\Automation_Frameworks\TestInput\Controller.xlsx
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	@Test
	public void startTest() throws Throwable {
		//generate report  
		report = new ExtentReports("./ExtentReport/hybridframework.html");
		boolean res = false;
		String tcres = null;
		//create Object for excel file util 
		ExcelFileUtil xl = new ExcelFileUtil(inputpath); 
		//count no of rows in TCSheet
		int TCcount = xl.rowCount(TCSheet);
		int TScount = xl.rowCount(TSSheet);
		Reporter.log("No of rows in Testcases sheet:"+TCcount+"      "+"No of rows in TestSheet:"+TScount);
		System.out.println(TCcount+"   "+TScount);
		Thread.sleep(3000);
		//iterate all rows in a TC Sheet 
		for(int i=1; i<=TCcount; i++) {
			//start test case
			test= report.startTest(" HybridFramework, Test Started: I am in  i loop");
			test.assignAuthor("Sainath");
			String execute = xl.getCellData(TCSheet, i, 2);
			if(execute.equalsIgnoreCase("Y")) {
				//read Testcase id column from TCSheet 
				String tcid =	xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TestSheet
				for(int j=1; j<=TScount;j++) {
					//read description cell
					String description =xl.getCellData(TSSheet, j, 2);
					String tsid= xl.getCellData(TSSheet,j, 0);
					if(tcid.equalsIgnoreCase(tsid)) {
						//read keyword from TSSheet
						String keyword = xl.getCellData(TSSheet, j,4);
						//Call Function Library methods
						if(keyword.equalsIgnoreCase("AdminLogin")) {

						String username = xl.getCellData(TSSheet, j, 5);
						String password =xl.getCellData(TSSheet, j, 6);
						res = PBFunctionLibrary.verifyLogin(username, password);
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("NewBranchCreation")) {
							PBFunctionLibrary.clickBranches();
							String branchname= xl.getCellData(TSSheet, j, 5);
							String Addr1  = xl.getCellData(TSSheet, j, 6);
							String Addr2 = xl.getCellData(TSSheet, j, 7);
							String  Addr3= xl.getCellData(TSSheet, j, 8);
							String  area= xl.getCellData(TSSheet, j, 9);
							String  zipcod= xl.getCellData(TSSheet, j, 10);
							String country = xl.getCellData(TSSheet, j, 11);
							String  state= xl.getCellData(TSSheet, j, 12);
							String city = xl.getCellData(TSSheet, j, 13);
							
							res =PBFunctionLibrary.newBranchCreation(branchname, Addr1, Addr2, Addr3, area, zipcod, country, state, city);
							test.log(LogStatus.INFO, description);
						}
		
						else if(keyword.equalsIgnoreCase("updateBranch")){
							PBFunctionLibrary.clickBranches();
							String branchNa = xl.getCellData(TSSheet, j, 5);
							String zip = xl.getCellData(TSSheet, j, 10);
							res=PBFunctionLibrary.verifyBranchUpdation(branchNa, zip);
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout")) {
							
							res = PBFunctionLibrary.verifyLogout();
							test.log(LogStatus.INFO, description);
						}


						String tsres = null;
						if(res) {
							tsres = "Pass";
							//Write as pass in TSSheet
							xl.setCellData(TSSheet, j, 3, tsres, outputpath);
							test.log(LogStatus.PASS, description);
						}
						else {
							//Write as fail in TSsheet
							tsres= "Fail";
							xl.setCellData(TSSheet, j, 3, tsres, outputpath);
							test.log(LogStatus.FAIL, description);
						}
						if(!tsres.equalsIgnoreCase("Fail")) {
							tcres = tsres;

						}

					}
					report.endTest(test);
					report.flush();
				}

				//write as tcres in TCSheet after j loop
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}



			else {
				xl.setCellData(TCSheet,i, 3, "Blocked", outputpath);
			}
		}


	}


}
