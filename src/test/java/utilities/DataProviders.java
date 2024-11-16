package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public Object [][] getData() throws IOException
	{
		String path=".\\testData\\excelsheet.xlsx"; //taking excel path
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating obj of excelUtility class 
		
		int totalRows=xlutil.getRowCount("Sheet1");
		int totalCells=xlutil.getCellCount("Sheet1", 1);
		System.out.println("Total Rows: " + totalRows);  // Log total rows count
		System.out.println("Total Cells: " + totalCells);  // Log total columns count
		
		Object logindata [][]= new String[totalRows-1][totalCells]; //created 2D string array which can store data from excel
		
		for(int i=1;i<totalRows;i++)
		{
			for(int j=0;j<totalCells;j++)
			{
				try {
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
				//System.out.println("Row: " + i + ", Column: " + j + " Data: " + data);
			}catch(Exception e)
				{
				e.getMessage();
				}}
		}
		return logindata;
	}
	}



