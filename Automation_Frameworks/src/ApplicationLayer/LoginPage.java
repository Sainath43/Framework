package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//What is constructor overriding
//WebDriver methods can be initialised in any class

//The purpose of constructor is to initialize the object of a class
//while the purpose of a method is to perform a task by executing java code. 
//Constructors cannot be abstract, final, static and synchronised while methods can be.
import org.openqa.selenium.support.FindBy;

public class LoginPage { 
WebDriver driver;
//Creating constructor to override Webdriver methods into a class
public LoginPage(WebDriver driver)
{
	//'this'for constructor overriding
	this.driver = driver;
	}
//define Repository
@FindBy(name ="txtuId")
WebElement username;
@FindBy(name ="txtPword")
WebElement password;
@FindBy(name ="login")
WebElement clickLogin;

//java method for login method
public void verifyLogin(String usernamefield,String passwordfield) {
this.username.sendKeys(usernamefield);
this.password.sendKeys(passwordfield);
clickLogin.click();
}

	}
