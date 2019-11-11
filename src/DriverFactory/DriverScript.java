package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import CommonFunLibrary.PBFunctions;
import Constant.PbConstant;
import Utilities.ExcelFileUtil;

public class DriverScript extends PbConstant {
String readexcel="E:\\OctOnline\\TestNgFramework\\Input\\Controller.xlsx";
String writedata="E:\\OctOnline\\TestNgFramework\\Output\\keyword.xlsx";
String TcSheet="TestCases";
String TSsheet="TestSteps";
@Test
public void statrtTest()throws Throwable
{
	//access Excel methods
	ExcelFileUtil xl=new ExcelFileUtil(readexcel);
	boolean res=false;
	String tcres="";
	
	int TCcount=xl.rowCount(TcSheet);
	int TScount=xl.rowCount(TcSheet);
	System.out.println(TCcount+"   "+TScount);
	for(int i=1;i<=TCcount;i++)
	{
String excute=xl.getCellData(TcSheet, i, 2);
if(excute.equalsIgnoreCase("Y"))
{
String tcid=xl.getCellData(TcSheet, i, 0);
for(int j=1;j<=TScount;j++)
{
String tsid=xl.getCellData(TSsheet, j, 0);
String description=xl.getCellData(TSsheet, j, 2);
if(tcid.equalsIgnoreCase(tsid))
{
String keyword=xl.getCellData(TSsheet, j, 3);
if(keyword.equalsIgnoreCase("AdminLogin"))
{
	//call login method
res=PBFunctions.verifyLogin("Admin","Admin");
}
else if(keyword.equalsIgnoreCase("NewBranchCreation"))
{
PBFunctions.navigateBranch();
res=PBFunctions.verifyBranch("Kukatpalli", "Hyderabad", "50004", "INDIA", "Andhra Pradesh", "Hyderabad");
}
else if(keyword.equalsIgnoreCase("UpdateBranch"))
{
PBFunctions.navigateBranch();
res=PBFunctions.verifyBranchupdation("Ameerpet", "Hyderabad2");
}
else if(keyword.equalsIgnoreCase("AdminLogout"))
{
res=PBFunctions.verifyLogout();
}
String tsres=null;
if(res)
{
 tsres="Pass";
xl.setcelldata(TSsheet, j, 4, tsres, writedata);
xl.fillGreenColor(TSsheet, j, 4, writedata);
}
else 
{
 tsres="Fail";
xl.setcelldata(TSsheet, j, 4, tsres, writedata);	
xl.fillRedColor(TSsheet, j, 4, writedata);
}
//if not tcres equal to null then write as pass or fail into tcsheet
if(!tsres.equalsIgnoreCase("Fail"))
{
//assign teststep results to testcase results
	tsres=tcres;	
}
}
}
xl.setcelldata(TcSheet, i, 3, tcres, writedata);
if (tcres.equalsIgnoreCase("PASS")) 
{
xl.fillGreenColor(TcSheet, i, 3, writedata);
}else 
xl.fillRedColor(TcSheet, i, 3, writedata);
}
else
{
//write as not excuted in results coulmn
xl.setcelldata(TcSheet, i, 3, "Not Executed", writedata);
}
}
}
}
