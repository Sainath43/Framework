package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ApplicationLayer.BranchCreationPage;
import ApplicationLayer.LoginPage;
import ApplicationLayer.LogoutPage;

public class TestScript {
WebDriver driver;
@BeforeTest
public void setUp() throws Throwable {
	System.setProperty("webdriver.chrome.driver","F:\\eclipse-workspace\\Automation_Frameworks\\Drivers\\chromedriver.exe");
driver = new ChromeDriver();
driver.get("http://primusbank.qedgetech.com/");
driver.manage().window().maximize();

//Calling page class
LoginPage login = PageFactory.initElements(driver,LoginPage.class);
login.verifyLogin("Admin","Admin");
Thread.sleep(3000);
}
@Test
public void branches() throws Throwable {
	BranchCreationPage page = PageFactory.initElements(driver,BranchCreationPage.class);
	page.verifyBranch("Dubailas", "olcifs","alksdjf","kjkasdj", "98374","aifkaksd", "INDIA", "Delhi", "Delhi");
String alert = driver.switchTo().alert().getText();
System.out.println(alert);
driver.switchTo().alert().accept();

}
@AfterTest
public void tearDown() {
	LogoutPage logout = PageFactory.initElements(driver,LogoutPage.class);
	logout.Logout();
}
}
