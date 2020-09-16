package CommonLibrary;

//In PBFunctionLibrary class we write all static methods(to call in other classes) for
//#1 Login(using boolean)
//#2 Click on Branches(using void)
//#3 verifynewBranchcreation(using boolean)
//#4 verifyBranchUpdation(using boolean)
//#5 verifyLogout(using boolean)

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constants.PBConstants;

public class PBFunctionLibrary extends PBConstants {
	//method for Admin login
	public static boolean verifyLogin(String username, String password) throws Throwable {
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(p.getProperty("Objuser"))).sendKeys(username);
		driver.findElement(By.xpath(p.getProperty("Objpwd"))).sendKeys(password);
		driver.findElement(By.xpath(p.getProperty("Loginbtn"))).click();
		Thread.sleep(6000);
		String expected ="adminflow";
		
		String actual = driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase())) {
			Reporter.log("Login Success",true);
			return true;
		}
		else {
			Reporter.log("Login Fail",true);
			return false;
		}

	}
	
	
	

	//Method for click branches in admin page(No return so, we write void)
	public static void clickBranches() throws Throwable {
		driver.findElement(By.xpath(p.getProperty("Branchesbtn"))).click();
		Thread.sleep(3000);
	}
	
	
	
	//Method for new Branch creation
	public static boolean newBranchCreation(String bname,String Address1, String Address2,String Address3,String Area,String zipcode, String Country, String state, String city) throws Throwable {
		driver.findElement(By.xpath(p.getProperty("NewBranchbtn"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(p.getProperty("ObjName"))).sendKeys(bname);
		driver.findElement(By.xpath(p.getProperty("ObjAdd1"))).sendKeys(Address1);
		driver.findElement(By.xpath(p.getProperty("ObjAdd2"))).sendKeys(Address2);
		driver.findElement(By.xpath(p.getProperty("ObjAdd3"))).sendKeys(Address2);
		driver.findElement(By.xpath(p.getProperty("Objzip"))).sendKeys(zipcode);
		driver.findElement(By.xpath(p.getProperty("ObjArea"))).sendKeys(Area);
		
		new Select(driver.findElement(By.xpath(p.getProperty("ObjCountry")))).selectByVisibleText(Country);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(p.getProperty("Objstate")))).selectByVisibleText(state);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(p.getProperty("ObjCity")))).selectByVisibleText(city);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(p.getProperty("Objsubmit"))).click();

		Thread.sleep(4000);
		String alert = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		Thread.sleep(4000);
		
		String expected ="New Branch with";
Thread.sleep(3000);
		if(alert.toLowerCase().contains(expected.toLowerCase())) {
			Reporter.log("New branch creation success",true);

			return true;
		}

		else {
			Reporter.log("New branch creation Fail",true);
			return false;
		}
		
	}
		//Method for branch updation
		public static boolean verifyBranchUpdation(String branchname, String editzipcode) throws Throwable {
			


			driver.findElement(By.xpath(p.getProperty("Objedit"))).click();
			Thread.sleep(4000);
			WebElement editnam = driver.findElement(By.xpath(p.getProperty("Objeditname")));
			editnam.clear();
			editnam.sendKeys(branchname);
			Thread.sleep(2000);
			WebElement editzip = driver.findElement(By.xpath(p.getProperty("Objeditzip")));
			editzip.clear();
			editzip.sendKeys(editzipcode);
			Thread.sleep(2000);
			driver.findElement(By.xpath(p.getProperty("Objupdatebtn"))).click();	
			Thread.sleep(4000);
			
			String updatetext = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			String expected = "Branch updated";
if(updatetext.toLowerCase().contains(expected.toLowerCase())) {
	Reporter.log("Update succeeded",true);
	return true;
			}
else {
	Reporter.log("Update failed",true);
return false;
}

	}
//Method for		
		
//Method for logout
		public static boolean verifyLogout() throws Throwable {
			
			driver.findElement(By.xpath(p.getProperty("Logoutbtn"))).click();
			
			Thread.sleep(4000);
			if(driver.findElement(By.xpath(p.getProperty("Loginbtn"))).isDisplayed()) {
				Reporter.log("Logout success",true);
			return true;
			}
			else {
		Reporter.log("logout fail",true);
		return false;
			}
		}

}




















