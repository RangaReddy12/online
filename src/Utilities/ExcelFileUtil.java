package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;
Sheet ws;
Row row;
Cell cell;
CellStyle style;
FileOutputStream fo;
//read path of excel
public ExcelFileUtil(String excelpath)throws Throwable
{
FileInputStream fi=new FileInputStream(excelpath);
wb=WorkbookFactory.create(fi);
}
//count no of rows in sheet
public int rowCount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
//count no of columns in rows
public int colCount(String shetname)
{
return wb.getSheet(shetname).getRow(0).getLastCellNum();	
}
//read data from cell
public String getCellData(String sheetname,int row,int column)
{
String data="";
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
{
int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
//convert celldata integer type into string
data=String.valueOf(celldata);
}
else
{
data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();	
}
return data;
}
//write results 
//writing into new File	
public void setcelldata(String Sheet,int row,int column,String data,String outputxlfile)
throws Throwable{

wb.getSheet(Sheet).getRow(row).createCell(column).setCellValue(data);
fo=new FileOutputStream(new File(outputxlfile));
wb.write(fo);

}
public void fillGreenColor(String sheetname, int rownum,int colnum,
		String excelpath) 
{
	try {
ws=wb.getSheet(sheetname);
row=ws.getRow(0);
style=wb.createCellStyle();
style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
style.setFillPattern(CellStyle.SOLID_FOREGROUND);
ws.getRow(rownum).getCell(colnum).setCellStyle(style);
fo=new FileOutputStream(excelpath);
wb.write(fo);
	}catch(IOException i)
	{
		System.out.println(i.getMessage());
	}
	}
public void fillRedColor(String sheetname, int rownum,int colnum,
		String excelpath) 
{
	try {
ws=wb.getSheet(sheetname);
row=ws.getRow(0);
style=wb.createCellStyle();
style.setFillForegroundColor(IndexedColors.RED.getIndex());
style.setFillPattern(CellStyle.SOLID_FOREGROUND);
ws.getRow(rownum).getCell(colnum).setCellStyle(style);
fo=new FileOutputStream(excelpath);
wb.write(fo);
	}catch(IOException i)
	{
System.out.println(i.getMessage());
	}
	}
public void closefiles() 
{
try {
fo.close();
wb.close();
}catch(IOException i)
{
System.out.println(i.getMessage());
}
}
}







