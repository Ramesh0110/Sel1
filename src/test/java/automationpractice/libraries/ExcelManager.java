package automationpractice.libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	public ArrayList<String> getData(String TCID) throws IOException {

		ArrayList<String> a = new ArrayList<String>(); // Arraylist to store data
		FileInputStream fis = new FileInputStream("src/test/resources/DataFile/testData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // to read xlxs
		//HSSFWorkbook workbook1=new HSSFWorkbook(fis); // to read xls

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();// sheet contains rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row contains cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {
						coloumn = k;
					}

					k++;
				}
					while (rows.hasNext()) { // once column is found loop through the row
					Row r = rows.next();
					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(TCID)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {
								a.add(c.getStringCellValue());//store data on arrayList 'a'
							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));// convert if it gets number into text or string and add to arraylist

							}
						}
					}

				}

			}
		}
		return a;// return type arrayList

	}
}