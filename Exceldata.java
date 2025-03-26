package Automation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

		public class Exceldata {
		    private static XSSFWorkbook workbook;
		    private static XSSFSheet sheet;

		    public static void loadExcel(String filepath, String sheetName) throws IOException {
		        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";
		        File file = new File(filePath);

		        // Debugging Statement
		        System.out.println("File Exists: " + file.exists());

		        if (!file.exists()) {
		            throw new IOException("Excel file not found at: " + filePath);
		        }

		        FileInputStream fileInputStream = new FileInputStream(file);
		        workbook = new XSSFWorkbook(fileInputStream);
		        sheet = workbook.getSheet(sheetName);
		    }

		    public static int getRowCount() {
		        return sheet.getPhysicalNumberOfRows(); // Get total number of rows
		    }

		    public static String getCellData(int row, int col) {
		        return sheet.getRow(row).getCell(col).getStringCellValue();
		    }

		    public static Object[][] getTestData() {
		        int rows = getRowCount();
		        Object[][] data = new Object[rows - 1][2]; // Ignore header row

		        for (int i = 1; i < rows; i++) { // Start from row 1 (skip headers)
		            data[i - 1][0] = getCellData(i, 0); // User name
		            data[i - 1][1] = getCellData(i, 1); // Password
		        }
		        return data;
		    }

			public static Object[][] readExcelData(String string, String string2) {
				// TODO Auto-generated method stub
				return null;
			}
		}
