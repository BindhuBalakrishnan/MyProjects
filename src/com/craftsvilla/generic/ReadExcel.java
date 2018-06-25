package com.craftsvilla.generic;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel
{
	public static String[][] getData(String filename, String sheetname) 
//	this method is 2dimentional array and it should return string so the method type is String[row][column]
	{
		try
		{
			//in java.io.File package there is a class called file which is used to handle all types of files/folder and the signature/arguments is the fillename
			File file = new File(filename);  //file name comes from autoconst.java class
			FileInputStream fin = new FileInputStream(file);//fileinputstream class used to read data from file
			
			Workbook wb = new XSSFWorkbook(fin);//workbook is an interface and the implementation class is XSSFWorkbook(pass the path of the file by fin objext to read the file)
			Sheet sh = wb.getSheet(sheetname);//class called sheet to read each and every sheet in excel(workbook) and there is a method called getsheet()to read that particular sheet.
			int rowNum = sh.getLastRowNum();//to capture the no.of rows utilized there is a method called getLastRowNum
			int cellNum = sh.getRow(0).getLastCellNum(); //column is based on rows ,to capture no of columns utilized v have to get the row and from that v get the no of cells have been used.
			
			String[][] data = new String[rowNum][cellNum];//size of 2d array row*column 
			//since it is rows and cloumn v need 2 for loops
			
			for(int i = 0; i<rowNum; i++)//this is for row
			{
				Row r = sh.getRow(i);//to capture the values of each row in each sheet v are creating object of type Row(ss user model)
				for(int j = 0; j<cellNum; j++)//this iterate each and every cell this is for column -first inner loop executed and then it ll go outer loop.
				{
					Cell c = r.getCell(j); //cell is a interface -getcell() is used to get the cell num.this address will be stored in the cell object
					String value = new DataFormatter().formatCellValue(c);//to read a string value v assign a variable called value.. this dataformatter is from ss packagethis class dataformatter used to convert any type of date to string that the reason v go with ss and not sl package
					data[i][j] = value;//assigning the string value to a data object for the first time it is 0throw and 0th cloumn..next innerloop 0row and 1st column
				}
			}
			
			return data;// this ll return data object..
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
	}

}
