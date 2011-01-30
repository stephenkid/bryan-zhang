package org.poseidon.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator.CellValue;
import org.poseidon.pojo.Login;

public class PoiUtil {
	private static boolean isAccountFormula = true;

	public static Object[][] readSheet(String fileName, int sheetNum) {
		Object[][] xlsModel = null;
		try{
			FileInputStream fs = new FileInputStream(fileName);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			xlsModel = readSheet(wb, sheetNum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xlsModel;
	}
	
	public static Object[][] readSheet(HSSFWorkbook wb, int sheetNum) {
		Object[][] xlsModel = null;
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
			for (int x = 0; x < xlsModel[y].length; x++) {
				HSSFCell cell = row.getCell(x);
				readValue(xlsModel, y, x, cell, evaluator);
				// System.out.println("(" + y + "," + x + "):" +
				// xlsModel[y][x]);
			}
		}

		return xlsModel;
	}

	public static HSSFWorkbook list2Xls(List<?> dataList, LinkedHashMap<String, String> headMap){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow curRow = null;
		int rowCnt = 0;
		int colCnt = 0;
		HSSFSheet sheet = wb.createSheet();
		
		//写入列头
		curRow = sheet.createRow(rowCnt++);
		Iterator<String> it = headMap.values().iterator();
		while(it.hasNext()){
			curRow.createCell(colCnt++).setCellValue(new HSSFRichTextString(it.next()));
		}
		
		//写入主数据
		for(Object dataObj : dataList){
			curRow = sheet.createRow(rowCnt++);
			colCnt = 0;
			for (Method m : dataObj.getClass().getMethods()){
				if (m.getName().indexOf("get") == 0) {
					if (headMap.keySet().contains(StringUtil.getMethodPar(m.getName()))){
						try {
							curRow.createCell(colCnt++).setCellValue(new HSSFRichTextString(m.invoke(dataObj).toString()));
						} catch (Exception e) {
							e.printStackTrace();
							curRow.createCell(colCnt++).setCellValue(new HSSFRichTextString());
						} 
					}
				}
			}
			
		}
		return wb;
	}
	
	
	/***************************************************private method****************************************************/
	
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
	
	public static void main(String[] args) {
		List<Login> dataList = new ArrayList<Login>();
		Login login = new Login("test@163.com", 
				"111111", 
				"testName", 
				"65000000", 
				"137000000", 
				"address", 
				"12343", 
				true, 
				new Date(), 
				new Date(), 
				"这是备注");
		dataList.add(login);
		login = new Login("test2@163.com", 
				"34343", 
				"testname 222", 
				"6500454", 
				"1370004540", 
				"dizhi", 
				"d4545", 
				true, 
				new Date(), 
				new Date(), 
				"这是备注2");
		dataList.add(login);
		
		LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("loginEmail", "登陆名");
		headMap.put("loginName", "姓名");
		headMap.put("telephone", "电话");
		headMap.put("isAvail", "是否有效");
		headMap.put("inputDate", "放入日期");
		headMap.put("memo", "备注");
		HSSFWorkbook wb = list2Xls(dataList, headMap);
		
		
		Object[][] data = readSheet(wb, 0);
		for (Object[] lineData : data){
			for(Object obj : lineData){
				System.out.print(obj);
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
}
