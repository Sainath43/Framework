package Sep16;

import org.testng.annotations.Test;



import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class DataProvide_using {
	WebDriver driver;
  @Test(dataProvider = "supplyData")
  public void verifyLogin(String username, String password) 
  {
	  driver.get("http://orangehrm.qedgetech.com/symfony/web/index.php/auth/login");
	 driver.findElement(By.name("txtUsername")).sendKeys(username);
	 driver.findElement(By.name("txtPassword")).sendKeys(password);
	 driver.findElement(By.name("Submit")).click();
	 if(driver.getCurrentUrl().contains("dashboard")) 
	 {
		 Reporter.log("Login Success:",true);
	 }
	 else {
		 String message = driver.findElement(By.id("spanMessage")).getText();
		 Reporter.log(message,true);
	 }
  }
  
//Test Data stored in Data Provider
  @DataProvider
  public Object[][] supplyData() 
  {
	  //store data in Object Array
  Object login[][]= new Object[4][2];//no of rows=4 and no of columns=2  
  login[0][0]="Admin";
  login[0][1]="Qedge123!@#";
  login[1][0]="Admin";
  login[1][1]="Qge123!@#";
  login[2][0]="Admin";
  login[2][1]="Qedge123!@#";
  login[3][0]="Amin";
  login[3][1]="Qedge123!#";
  
  return login;
  
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
	  driver = new ChromeDriver();
  }

  @AfterTest
  public void afterTest() {
	driver.close();  
  }

}






















