package DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import PageClass.LoginPage;
public class Orange {
WebDriver driver;
@BeforeTest
public void launch()
{
System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
driver=new ChromeDriver();
driver.get("http://orangehrm.qedgetech.com/");
driver.manage().window().maximize();
}
@Test
public void verifylogin()
{
LoginPage logintest=PageFactory.initElements(driver, LoginPage.class);
logintest.sendusername("admin1");
logintest.sendpassword("Qedge123!@#");
logintest.clicklogin();
if(driver.getCurrentUrl().contains("dash"))
{
Reporter.log("Login success",true);
}
else
{
Reporter.log(logintest.capturetext()+"  "+"Login Fail",true);	
}
}
@AfterTest
public void logout()
{
	driver.close();
}
}

