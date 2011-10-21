package org.poseidon.component.dataRead;

/**
 * Xls读取器
 * @author Bryan Zhang
 */

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator.CellValue;
import org.springframework.stereotype.Component;

@Component("XlsReader")
public class XlsReader{
	private static boolean isAccountFormula = true;
	
	/**
	 * 从文件路径中读取xls的内容，以object二维数组形式返回
	 */
	public Object[][] readSheet(String fileName, int sheetNum) throws Exception{
		Object[][] xlsModel = null;
		FileInputStream fs = new FileInputStream(fileName);
		xlsModel = readSheet(fs, sheetNum);
		return xlsModel;
	}
	
	/**
	 * 从输入流中读取xls内容，以object二维数组形式返回
	 */
	public Object[][] readSheet(InputStream is, int sheetNum) throws Exception{
		Object[][] xlsModel = null;
		HSSFWorkbook wb = new HSSFWorkbook(is);
		String tableName = wb.getSheetName(sheetNum);
		HSSFSheet sheet = wb.getSheet(tableName);
		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(wb);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = 0;
		for (int y = 0; y < rowCount; y++) {
			if (sheet.getRow(y) != null)
				if (colCount < sheet.getRow(y).getLastCellNum())
					colCount = sheet.getRow(y).getLastCellNum();
		}
		xlsModel = new Object[rowCount][colCount];

		for (int y = 0; y < xlsModel.length; y++) {
			HSSFRow row = sheet.getRow(y);
			if (row == null)
				continue;
			for (int x = 0; x < xlsModel[y].length; x++) {
				HSSFCell cell = row.getCell(x);
				readValue(xlsModel, y, x, cell, evaluator);
			}
		}

		return xlsModel;
	}
	
/***************************************************private method****************************************************/
	
	private static void readValue(Object[][] xlsModel, int y, int x, HSSFCell cell, HSSFFormulaEvaluator evaluator) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:// String
				xlsModel[y][x] = cell.getRichStringCellValue().getString();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:// double
				xlsModel[y][x] = new Double(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:// FORMULA String
				if (isAccountFormula) {
					CellValue cellValue = evaluator.evaluate(cell);
					switch (cellValue.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:// String
						xlsModel[y][x] = cellValue.getStringValue();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:// double
						xlsModel[y][x] = new Double(cellValue.getNumberValue());
						break;
					case HSSFCell.CELL_TYPE_ERROR:// byte
						if (!Double.isNaN(cell.getNumericCellValue())) {
							xlsModel[y][x] = new Double(cell.getNumericCellValue());
						} else if (!"".equals(xlsModel[y][x] = cell.getRichStringCellValue().getString())) {
							xlsModel[y][x] = xlsModel[y][x] = cell.getRichStringCellValue().getString();
						}
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:// boolean
						xlsModel[y][x] = new Boolean(cellValue.getBooleanValue());
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						xlsModel[y][x] = null;
						break;
					default:
						break;
					}
				} else {
					xlsModel[y][x] = cell.getCellFormula();
				}
				break;
			case HSSFCell.CELL_TYPE_ERROR:// byte
				xlsModel[y][x] = new Byte(cell.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:// boolean
				xlsModel[y][x] = new Boolean(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				xlsModel[y][x] = null;
				break;
			default:
				break;
			}
		}
	}
}
