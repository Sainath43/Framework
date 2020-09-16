package Utitlities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	//creating workbook object	
	Workbook wb;
	//constructor is created to initiate an Object
	//Constructor should not contain any return type
	//Constructor(Contructor name should be same as class) for reading excel path-(Constructor and method are different)
	public ExcelFileUtil(String excelpath) throws Throwable {
		FileInputStream fi = new FileInputStream(excelpath);
		wb= WorkbookFactory.create(fi);
	}

	//Counting no of rows in a sheet
	public int rowCount(String sheetname) {
		return wb.getSheet(sheetname).getLastRowNum();
	}

	//Counting no of columns in a sheet
	public int celCount(String sheetname) {
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}

	//get cell data from sheet
	public String getCellData(String sheetname, int row, int column) 
	{
		//.getCellType()==Cell.CELL_TYPE_NUMERIC) 
		String data= "";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {
			int celldata = (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else {
			data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//Method for set status
	public void setCellData(String sheetname, int row, int column,String Status, String writeexcel) throws Throwable {
		//get sheet from workbook
		Sheet ws = wb.getSheet(sheetname);
		//get row from sheet
		Row rownum = ws.getRow(row);
		//Create cell in sheet
		Cell cell = rownum.createCell(column);
		//Write status in a cell
		cell.setCellValue(Status);
		//
		if(Status.equalsIgnoreCase("Pass"))
		{
			//create cell style(INterface)
			CellStyle style = wb.createCellStyle();
			//create font(interface)
			Font font = wb.createFont();
			//Apply colour to the text
			font.setColor(IndexedColors.GREEN.getIndex());
			//Apply bold to the text
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);

		}	
		else if(Status.equalsIgnoreCase("Fail"))
		{
			//create cell style(INterface)
			CellStyle style = wb.createCellStyle();
			//create font(interface)
			Font font = wb.createFont();
			//Apply colour to the text
			font.setColor(IndexedColors.RED.getIndex());
			//Apply bold to the text
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);

		}
		else if(Status.equalsIgnoreCase("Blocked")) 
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}


		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);
		fo.close();
	}

}
