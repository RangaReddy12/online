package PageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
this.driver=driver;

}
//Using FindBy for locating elements
@FindBy(name="txtUsername")
WebElement enterusername;
@FindBy(name="txtPassword")
WebElement enterpassword;
@FindBy(name="Submit")
WebElement login;
@FindBy(id="spanMessage")
WebElement getmessage;
//Defining all the user actions (Methods) that can be performed in the orangehrm home page
public void sendusername(String username)
{
	enterusername.sendKeys(username);
}
public void sendpassword(String password)
{
	enterpassword.sendKeys(password);
}
public void clicklogin()
{
	login.click();
}
public String capturetext()
{
String message=getmessage.getText();
return message;	
}
}
