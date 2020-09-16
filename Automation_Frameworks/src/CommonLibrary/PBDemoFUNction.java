package CommonLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import Constants.PBConstants;
// //td//td//td//td[3]//a[1]//img[1]

public class PBDemoFUNction  extends PBConstants{
	//method for Admin login
	public static boolean verifyLogin(String username, String password) throws Throwable {

driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

		driver.findElement(By.xpath(p.getProperty("Objuser"))).sendKeys(username);
		driver.findElement(By.xpath(p.getProperty("Objpwd"))).sendKeys(password);

		driver.findElement(By.xpath(p.getProperty("Loginbtn"))).click();
		Thread.sleep(3000);
		String expected ="adminflow";

	
	Thread.sleep(3000);

	
			
		driver.switchTo().alert().accept();	
		Thread.sleep(4000);
	
String actual =	driver.getCurrentUrl();
System.out.println(actual+"current url ");
if(actual.toLowerCase().contains(expected.toLowerCase())) {
	Reporter.log("Login Success",true);
	return true;
}
else {
	Reporter.log("Login Fail",true);
	
	return false;

}

	}
		
	








//Method for logout
public static boolean verifyLogout() throws Throwable {
	driver.findElement(By.xpath(p.getProperty("Logoutbtn"))).click();
	Thread.sleep(4000);
	if(driver.findElement(By.xpath(p.getProperty("Logoutbtn"))).isDisplayed()) {
		Reporter.log("Logout success",true);
		return true;
	}
	else {
		Reporter.log("logout fail",true);
		return false;
	}
}

}