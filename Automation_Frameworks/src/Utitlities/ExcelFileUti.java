package Utitlities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUti {
	
	Workbook wb;



//creating Constructor for excel path
public  ExcelFileUti(String excelpath) throws Throwable {
	FileInputStream fi = new FileInputStream(excelpath);
	 wb = WorkbookFactory.create(fi);
}
public int rowCount(String sheetname) {
	
}
public int cellCount(String sheetname) {
	
}
public String getCellData(String sheetname, int row, int Column) {
	String data= "";
	return data;
}

public void setCellData(String sheetname, int row,int Column,String Status, String writeexcel) {
	
}
}
	










































