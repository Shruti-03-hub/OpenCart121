package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	public int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int cellCount = sheet.getRow(rownum).getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rownum, int cellnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			//data=cell.toString();
			data=formatter.formatCellValue(cell);// return formatted value as string type regardless of any data type 
		}catch(Exception e) 
		{
			data="InValid Data";
			e.getMessage();
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException
	{
		File xfile=new File(path);
		
		if(!xfile.exists()) //if file not exist create new file
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetName)==-1) //if sheet not exist create sheet{
			wb.createSheet(sheetName);
			sheet =wb.getSheet(sheetName);

		if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
			row=sheet.getRow(rownum);
			
			cell=row.createCell(cellnum);
			cell.setCellValue(data);
			fo=new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
			
	}
		
	public void fillRedColor(String sheetName, int rownum, int cellnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style=wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	
	}
	public void fillGreenColor(String sheetName, int rownum, int cellnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style=wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	
	}
	

}
