package DriverFactory;
import java.io.IOException;
import org.testng.annotations.Test;

import CommonFunLibrary.PBFunctions;
import Constant.PbConstant;
import Utilities.ExcelFileUtil;

public class driverscrip1  extends PbConstant 
{
//storing xl path
String inputpath="E:\\OctOnline\\TestNgFramework\\Input\\Controller.xlsx";
String outpath="E:\\OctOnline\\TestNgFramework\\Output\\keyword.xlsx";
String tcsheet="TestCases";
String tssheet="TestSteps";
//creating reference object for all function library to call methods

@Test
public void keywordDrivenTest() throws  Throwable
{
//creating reference object for XlReader class
ExcelFileUtil xl=new ExcelFileUtil(inputpath);	
String Execute,TestCase_Id,Teststep_Id,keyword;
boolean res=false;
String tcres="";
//counting rows in testcase sheet
int tccount=xl.rowCount(tcsheet);
int tscount=xl.rowCount(tssheet);
//iterate all rows in testcase sheet
for (int i = 1; i <= tccount; i++) 
{
Execute=xl.getCellData(tcsheet, i, 2);
if (Execute.equalsIgnoreCase("Y")) 
{
//reading TestCase Id column from testcase sheet
TestCase_Id=xl.getCellData(tcsheet, i, 0);
//iterate all rows in sheet 2(TestSteps)
for (int j = 1; j <=tscount ; j++) 
{
Teststep_Id=xl.getCellData(tssheet, j, 0);
if (TestCase_Id.equalsIgnoreCase(Teststep_Id)) 
{
//reading keyword column from TestStep sheet
keyword=xl.getCellData(tssheet, j, 3);
switch (keyword.toUpperCase()) 
{
case "ADMINLOGIN":
res=PBFunctions.verifyLogin("Admin", "Admin");
break;
case "NEWBRANCHCREATION":
	PBFunctions.navigateBranch();		
res=PBFunctions.verifyBranch("Kadiri09","Kadiri", "76543", "INDIA", "Andhra Pradesh", "Hyderabad");
break;
case"UPDATEBRANCH":
PBFunctions.navigateBranch();
res=PBFunctions.verifyBranchupdation("Testing1234", "Ameerpet");
break;
case "ADMINLOGOUT":
res=PBFunctions.verifyLogout();
break;
}
String tsres=null;
if (res) 
{
tsres="Pass";
xl.setcelldata(tssheet, j, 4, tsres, outpath);
xl.fillGreenColor(tssheet, j, 4, outpath);
} else 
{
tsres="Fail";							
xl.setcelldata(tssheet, j, 4, tsres, outpath);
xl.fillRedColor(tssheet, j, 4, outpath);
}
//if not tcres equal to null then write as pass or fail into tcsheet
if (!tsres.equalsIgnoreCase("FAIL")) 
{
//assign teststep results to testcase results
	tsres=tcres;
}
}
}
xl.setcelldata(tcsheet, i, 3, tcres, outpath);
if (tcres.equalsIgnoreCase("PASS")) 
{
xl.fillGreenColor(tcsheet, i, 3, outpath);
}else 
xl.fillRedColor(tcsheet, i, 3, outpath);
}
else 
{
xl.setcelldata(tcsheet, i, 3, "Blocked", outpath);
}
}
}
}









