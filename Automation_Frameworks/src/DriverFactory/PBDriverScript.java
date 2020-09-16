package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonLibrary.PBFunctionLibrary;
import Constants.PBConstants;
import Utitlities.ExcelFileUtil;

// This PBDriverScript is an executable Script which will drive all files and execute and generate results within framework structure
// we extend PBConstants for associating preconditions and post conditions
//
public class PBDriverScript extends PBConstants {
	ExtentReports report;
	ExtentTest test;
	//path for reading excel file
	String inputpath = "C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\TestInput\\Controller.xlsx";
	//path for writing results
	String outputpath= "C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\TestOutput\\keywordframework.xlsx";
//C:\Users\hp\eclipse-workspace\Automation_Frameworks\TestInput\Controller.xlsx
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	@Test
	public void startTest() throws Throwable {
		//generate report  
		report = new ExtentReports("./ExtentReport/keywordframework.html");
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
			test= report.startTest(" KeywordFramework, Test Started: I am in  i loop");
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
						String keyword = xl.getCellData(TSSheet, j,3);
						//Call Function Library methods
						if(keyword.equalsIgnoreCase("AdminLogin")) {

							res =	PBFunctionLibrary.verifyLogin("Admin", "Admin");
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("NewBranchCreation")) {
							PBFunctionLibrary.clickBranches();
							res = PBFunctionLibrary.newBranchCreation("Hyderabad","Secundera", "Secundera2", "Secunde3", "Areaads", "12344", "INDIA", "Delhi", "Delhi");
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("updateBranch")){
							PBFunctionLibrary.clickBranches();
							res =PBFunctionLibrary.verifyBranchUpdation("Sagar1","87655");
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
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.PASS, description);
						}
						else {
							//Write as fail in TSsheet
							tsres= "Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
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