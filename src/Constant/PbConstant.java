package Constant;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PbConstant {
public static WebDriver driver;
@BeforeMethod
public void launch()
{
System.setProperty("webdriver.chrome.driver", "E:\\OctOnline\\TestNgFramework\\Drivers\\chromedriver.exe");
driver=new ChromeDriver();
driver.get("http://primusbank.qedgetech.com");
driver.manage().window().maximize();
}
@AfterMethod
public void teardown()
{
driver.close();
}
}
