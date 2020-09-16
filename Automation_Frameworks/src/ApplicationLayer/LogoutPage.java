package ApplicationLayer;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
// we don't need constructor or to use webdriver methods

@FindBy(xpath="//tbody/tr/td[3]/a/img")
WebElement logout;

public void Logout(){
	this.logout.click();
}

}
