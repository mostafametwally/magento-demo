package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static Object[][] readExcel(String filePath, String fileName, String sheetName) throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook Workbook = null;

		Workbook = new XSSFWorkbook(inputStream);

		// Read sheet inside the workbook by its name

		Sheet Sheet = Workbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = Sheet.getLastRowNum();
		int columnCount = Sheet.getRow(0).getLastCellNum();

		Object[][] DataArray = new Object[rowCount][columnCount];

		// Create a loop over all the rows of excel file to read it

		DataFormatter formatter = new DataFormatter();

		for (int i = 0; i < rowCount; i++) {
			Row row = Sheet.getRow(i + 1);
			for (int j = 0; j < columnCount; j++) {
				DataArray[i][j] = formatter.formatCellValue(row.getCell(j));
			}
		}
		return DataArray; 
	}

}
