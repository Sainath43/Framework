package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BranchCreationPage {
WebDriver driver;
public BranchCreationPage(WebDriver driver) {
	this.driver = driver;
	}

@FindBy(xpath ="//*[@id=\"Table_01\"]/tbody/tr[2]/td/table/tbody/tr[2]/td/a/img")
WebElement branchesbtn;

@FindBy(xpath="/html/body/form/div/input")
WebElement newbranchesbtn;

@FindBy(id="txtbName")
WebElement enterbname;

@FindBy(id="txtAdd1")
WebElement enterAddress1; 

@FindBy(id="Txtadd2")
WebElement enterAddress2;

@FindBy(id ="txtadd3")
WebElement enterAddress3;

@FindBy(id="txtZip")
WebElement enterzipcode;

@FindBy(id="txtArea")
WebElement enterArea;

@FindBy(id="lst_counrtyU")
WebElement enterCountry;

@FindBy(name="lst_stateI")
WebElement enterstate;

@FindBy(name="lst_cityI")
WebElement entercity;

@FindBy(name ="btn_insert")
WebElement clicksubmit;


public void verifyBranch(String bname,String Addr1,String Add2,String Add3, String zip, String Area,String country,String State,String city) throws Throwable {
this.branchesbtn.click();
Thread.sleep(4000);
this.newbranchesbtn.click();
Thread.sleep(3000);
this.enterbname.sendKeys(bname);

this.enterAddress1.sendKeys(Addr1);
this.enterAddress2.sendKeys(Add2);
this.enterAddress3.sendKeys(Add3);
this.enterArea.sendKeys(Area);
this.enterzipcode.sendKeys(zip);
new Select(this.enterCountry).selectByVisibleText(country);
Thread.sleep(3000);
new Select(this.enterstate).selectByVisibleText(State);
Thread.sleep(3000);
new Select(this.entercity).selectByVisibleText(city);
Thread.sleep(3000);
this.clicksubmit.click();
Thread.sleep(3000);
}

}