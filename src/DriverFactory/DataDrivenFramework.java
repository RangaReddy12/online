package DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Utilities.ExcelFileUtil;
public class DataDrivenFramework {
WebDriver driver;
ExtentReports report;
ExtentTest test;
String inputpath="E:\\OctOnline\\Maven_Project\\Input\\Logindata.xlsx";
String outputpath="E:\\OctOnline\\Maven_Project\\Output\\Datadriven.xlsx";
@BeforeTest
public void launch()
{
report=new ExtentReports("./Reports/login.html");
System.setProperty("webdriver.chrome.driver", "E:\\OctOnline\\Maven_Project\\Drivers\\chromedriver.exe");
driver=new ChromeDriver();
}
@Test
public void verifyLogin()throws Throwable
{
//to access excel methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of rows
	int rc=xl.rowCount("Login");
	//count no of columns in rows
	int cc=xl.colCount("Login");
Reporter.log("no of rows are::"+rc+" "+"no of columns are::"+cc,true);
for(int i=1;i<=rc;i++)
{
	//test case starts
test=report.startTest("Login Test");
driver.get("http://orangehrm.qedgetech.com/");
driver.manage().window().maximize();
String username=xl.getCellData("Login", i, 0);
String password=xl.getCellData("Login", i, 1);
driver.findElement(By.name("txtUsername")).sendKeys(username);
driver.findElement(By.name("txtPassword")).sendKeys(password);
driver.findElement(By.name("Submit")).click();
Thread.sleep(4000);
if(driver.getCurrentUrl().contains("dash"))
{
Reporter.log("Login Success",true);
test.log(LogStatus.PASS, "Login Success");
//write as pass into results
xl.setcelldata("Login", i, 2, "Pass", outputpath);
xl.fillGreenColor("Login", i, 2, outputpath);
}
else 
{
//get error message
String message=driver.findElement(By.id("spanMessage")).getText();
test.log(LogStatus.FAIL, message);
Reporter.log(message,true);
//write as pass into results
xl.setcelldata("Login", i, 2, "Fail", outputpath);
xl.fillRedColor("Login", i, 2, outputpath);
}
report.flush();
report.endTest(test);
}
}
@AfterTest
public void teardown()
{
	driver.close();
}
}










