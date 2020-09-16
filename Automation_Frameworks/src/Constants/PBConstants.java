package Constants;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBConstants {
	
	//Declare  WebDriver object
public static WebDriver driver;
//Declare  properties class using p object which is further used to associate
public static Properties p;
//declare file input 
public static FileInputStream fi;

@BeforeMethod(enabled = true)
public void setUp() throws Throwable{
	
	// Associating properties class at setUp method level
	p = new Properties();
	//giving path of file in properties
	fi = new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\PropertyFile\\Environment.properties");
	//load properties file using p object
	p.load(fi);
	//Accessing keys 
	if(p.getProperty("Browser").equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
	}
	else if(p.getProperty("Browser").equalsIgnoreCase("msedge")){
		System.setProperty("webdriver.edge.driver","C:\\Users\\hp\\eclipse-workspace\\Automation_Frameworks\\Drivers\\msedgedriver.exe");
		
		driver = new EdgeDriver();
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
	}
	else {
		System.out.println("Browser is not matching...");
	}
}


@AfterMethod(enabled =true)
public void tearDown() {
	driver.close();
}

}
