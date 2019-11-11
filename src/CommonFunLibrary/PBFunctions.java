package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.PbConstant;

public class PBFunctions extends PbConstant {
	//public static WebDriver driver;
public static boolean verifyLogin(String username,String password)
throws Throwable{
driver.findElement(By.name("txtuId")).sendKeys(username);
driver.findElement(By.name("txtPword")).sendKeys(password);
driver.findElement(By.name("login")).click();
Thread.sleep(4000);
String Actval="adminflow";
String Expval=driver.getCurrentUrl();
if(Expval.contains(Actval))
{
System.out.println("Login success");
return true;
}
else
{
System.out.println("Login Fail");
return false;
}
}
public static void navigateBranch()throws Throwable
{
driver.findElement(By.xpath("//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")).click();
Thread.sleep(4000);
}
public static boolean verifyBranch(String bname,String address1,
	String zipcode,String country,String state,String city)throws Throwable
{
driver.findElement(By.xpath("//input[@id='BtnNewBR']")).click();
Thread.sleep(4000);
driver.findElement(By.name("txtbName")).sendKeys(bname);
driver.findElement(By.name("txtAdd1")).sendKeys(address1);
driver.findElement(By.name("txtZip")).sendKeys(zipcode);
driver.findElement(By.name("lst_counrtyU")).sendKeys(country);
driver.findElement(By.name("lst_stateI")).sendKeys(state);
driver.findElement(By.name("lst_cityI")).sendKeys(city);
driver.findElement(By.name("btn_insert")).click();
//get alert text
String alertmessage=driver.switchTo().alert().getText();
System.out.println(alertmessage);
Thread.sleep(4000);
driver.switchTo().alert().accept();
Thread.sleep(4000);
if(alertmessage.contains("New Branch"))
{
System.out.println("New Branch Creation is success");
return true;
}
else
{
System.out.println("New Branch Creation is Fail");
return false;
}
}
public static boolean verifyBranchupdation(String bname,String address)
throws Throwable{
driver.findElement(By.xpath("//tr[3]//td[7]//a[1]//img[1]")).click();
Thread.sleep(4000);
WebElement branchn=driver.findElement(By.name("txtbnameU"));
branchn.clear();
branchn.sendKeys(bname);
WebElement add=driver.findElement(By.name("txtadd1u"));
add.clear();
add.sendKeys(address);
driver.findElement(By.name("btnupdate")).click();
//get alert text
String alertmessage=driver.switchTo().alert().getText();
System.out.println(alertmessage);
Thread.sleep(4000);
driver.switchTo().alert().accept();
Thread.sleep(4000);
if(alertmessage.contains("Branch updated"))
{
System.out.println("Branch Updated is success");
return true;
}
else
{
System.out.println(" Branch Updated is Fail");
return false;
}
}
public static boolean verifyLogout()throws Throwable
{
driver.findElement(By.xpath("//*/tbody/tr/td[3]/a/img")).click();
Thread.sleep(4000);
if(driver.findElement(By.name("login")).isDisplayed())
{
System.out.println("Logout success");
return true;
}
else
{
System.out.println("Logout Fail");
return false;
}
}
}
