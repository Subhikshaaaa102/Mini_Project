package miniProject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Excel {
	public static XSSFCell c1;
	public static XSSFCell c2;
	public String search;
	public String option;
	public void dataExtract() throws IOException {
		File excelFile = new File("C:\\Users\\2318392\\eclipse-workspace\\MiniProject\\Input.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		XSSFRow r = sheet.getRow(1);
		c1 = r.getCell(0);
		c2 = r.getCell(1);
		search = String.valueOf(c1);
		option = String.valueOf(c2);
		wb.close();
		
		
	}
}
