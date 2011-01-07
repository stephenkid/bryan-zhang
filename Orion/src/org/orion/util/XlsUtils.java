package org.orion.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;

public class XlsUtils {
	private static boolean isAccountFormula = true;

	public static Object[][] readSheet(String fileName, int sheetNum) {
		Object[][] xlsModel = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
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
			// System.out.println(rowCount + "----------------" + colCount);

			for (int y = 0; y < xlsModel.length; y++) {
				HSSFRow row = sheet.getRow(y);
				if (row == null)
					continue;
				//evaluator.setCurrentRow(row);
				for (short x = 0; x < xlsModel[y].length; x++) {
					HSSFCell cell = row.getCell(x);
					readValue(xlsModel, y, x, cell, evaluator);
					// System.out.println("(" + y + "," + x + "):" +
					// xlsModel[y][x]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return xlsModel;
	}
	
	public static void writeCol(String fileUrl, int sheetNum, int colNum, List data){
		try{
			FileInputStream fi = new FileInputStream(new File(fileUrl));
			HSSFWorkbook wb = new HSSFWorkbook(fi);
			HSSFSheet sheet = wb.getSheetAt(sheetNum);
			HSSFRow row = null;
			
			for (int i = 0; i < data.size(); i++){
				row = sheet.createRow(i);
				writeCell(wb, row.createCell(0), data.get(i));
			}
			
			OutputStream os = new FileOutputStream(fileUrl);
			wb.write(os);
			os.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static void readValue(Object[][] xlsModel, int y, int x, HSSFCell cell, HSSFFormulaEvaluator evaluator) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:// String
				//xlsModel[y][x] = cell.getStringCellValue();
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
						// xlsModel[y][x] = new Byte(cellValue.getErrorValue());
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
	
	private static void writeCell(HSSFWorkbook wb, HSSFCell cell, Object obj) {
		HSSFCellStyle centerStyle = wb.createCellStyle();

		centerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		if (obj instanceof Date) {
			centerStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cell.setCellStyle(centerStyle);
			cell.setCellValue(new HSSFRichTextString(StringUtil.format("yyyy-MM-dd HH:mm:ss", obj)));
		} else if (obj instanceof BigDecimal) {
			HSSFDataFormat format = wb.createDataFormat();
			centerStyle.setDataFormat(format.getFormat("#,##0.00"));
			cell.setCellStyle(centerStyle);
			cell.setCellValue(Double.valueOf(StringUtil.format("0.00", obj)));
		} else if (obj instanceof Integer) {
			centerStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cell.setCellStyle(centerStyle);
			cell.setCellValue(Integer.valueOf(obj.toString()));
		} else {
			centerStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cell.setCellStyle(centerStyle);
			cell.setCellValue(new HSSFRichTextString((String) obj));
		}
	}

	/**
	 * 处理公式
	 * 
	 * @param hssfWorkbook
	 * @param cell
	 * @param forum
	 */
	private static void writeCellForum(HSSFWorkbook hssfWorkbook, HSSFCell cell, String forum) {
		HSSFCellStyle centerStyle = hssfWorkbook.createCellStyle();

		centerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		HSSFDataFormat format = hssfWorkbook.createDataFormat();
		centerStyle.setDataFormat(format.getFormat("#,##0.00"));

		cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(forum);
		cell.setCellStyle(centerStyle);
	}
	
	public static List returnColList(Object[][] data, int colIdx){
		List colList = new ArrayList();
		for (Object[] rowData : data){
			colList.add(rowData[colIdx]);
		}
		return colList;
	}
	
	public static void main(String[] args) {
		Object[][] table = XlsUtils.readSheet("D:/tmp001.xls", 0);
		System.out.println(XlsUtils.returnColList(table, 0));
		for (int i = 0; i < table.length; i++){
			for (int j = 0; j < table[i].length; j++){
				System.out.print(table[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
