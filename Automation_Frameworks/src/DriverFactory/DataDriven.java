package DriverFactory;



import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
//we don't declare excel methods, because we already created in propertyFiles
//We just call those methods here
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utitlities.ExcelFileUtil;
public class DataDriven {
WebDriver driver;
String inputpath="C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\TestInput\\testpage.xlsx";
String outputpath= "C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\TestOutput\\Datadriven.xlsx";
ExtentReports report;
ExtentTest test;
@BeforeTest	
public void setUp() {
	report = new ExtentReports("./ExtentReports/Report.html");
	System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
}

@Test
public void VerifyLogin() throws Throwable {
	//create object for excel methods written in Environment.properties
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows
	int rc = xl.rowCount("Login");
	//count no of columns
	int cc= xl.celCount("Login");
	//log no of rows and columns
	Reporter.log("No of rows::"+rc+"  "+"No of columns::"+cc,true);

	for(int i=1;i<=rc;i++) {
		test= report.startTest("Validate Login");
		test.assignAuthor("Ranga");
		test.assignCategory("DataDriven");
		String username = xl.getCellData("Login",i,0);
		String password = xl.getCellData("Login",i,1);
		
		
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().window().maximize();
		driver.findElement(By.name("txtUsername")).sendKeys(username);
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.name("Submit")).click();
		Thread.sleep(4000);
		if(driver.getCurrentUrl().contains("dash")) {
			//Write as login success into results
			Reporter.log("Login success",true);
			xl.setCellData("Login", i, 2,"Login success", outputpath);
			//write as pass into Status cell
			xl.setCellData("Login",i,3, "Pass", outputpath);
			test.log(LogStatus.PASS,"Login success");
			
			
		}
		else {
		
	File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	org.openqa.selenium.io.FileHandler.copy(screen, new File("./Screens/photos/"+i+"loginpag.png"));
	
	//FileHandler.copy(screen,"./Screens/iteration/"+i+"loginpage.png");
			
			String message = driver.findElement(By.id("spanMessage")).getText();
			Reporter.log("Login Fail"+message,true);
			xl.setCellData("Login",i,2, "Login Fail"+message, outputpath);
			xl.setCellData("Login", i, 3,"Fail", outputpath);
			test.log(LogStatus.FAIL, "Login Fail");
			
	}
		
		report.endTest(test);
		report.flush();
	}
	
	
}

@AfterTest
public void teardown() {
	driver.close();
}

}














