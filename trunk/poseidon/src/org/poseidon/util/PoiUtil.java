package org.poseidon.util;

/**
 * @author Bryan Zhang
 * POI组件操作类
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator.CellValue;

public class PoiUtil {
	private static boolean isAccountFormula = true;
	
	/**
	 * 从文件路径中读取xls的内容，以object二维数组形式返回
	 */
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
	
	/**
	 * 从HSSFWorkbook中读取xls内容，以object二维数组形式返回
	 */
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

	/**
	 * 把list转换成HSSFWorkbook对象,内部支持pojo和map
	 */
	public static HSSFWorkbook list2Xls(List<?> dataList, LinkedHashMap<String, String> headMap) 
				throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow curRow = null;
		HSSFCell cell = null;
		int rowCnt = 0;
		int colCnt = 0;
		HSSFSheet sheet = wb.createSheet();
		
		//写入列头
		curRow = sheet.createRow(rowCnt++);
		Iterator<String> it = headMap.values().iterator();
		while(it.hasNext()){
			cell = curRow.createCell(colCnt++);
			cell.setCellValue(new HSSFRichTextString(it.next()));
		}
		
		//写入主数据
		Object valueObj = null;
		Map<String, ?> mapItem = null;
		for(Object dataObj : dataList){
			curRow = sheet.createRow(rowCnt++);
			colCnt = 0;
			
			//针对spring的jdbctemplate和 hibernatetemplate所返回的item类型进行处理
			if (dataObj instanceof Map){
				mapItem = (Map<String, ?>)dataObj;
				for (it = headMap.keySet().iterator(); it.hasNext();) {
					valueObj = mapItem.get(it.next());
					setCellValue(curRow, colCnt, valueObj);
					colCnt++;
				}
			}else{
				for (Method m : dataObj.getClass().getMethods()){
					if (m.getName().indexOf("get") == 0) {
						if (headMap.keySet().contains(StringUtil.getMethodPar(m.getName()))){
							valueObj = m.invoke(dataObj);
							setCellValue(curRow, colCnt, valueObj);
							colCnt++;
						}
					}
				}
			}
		}
		return wb;
	}
	
	
	/**
	 * 把list写入文件,内部支持pojo和map
	 */
	public static boolean list2XlsFile(List<?> dataList, LinkedHashMap<String, String> headMap, String path){
		boolean flag = true;
		OutputStream os = null;
		try{
			HSSFWorkbook wb = list2Xls(dataList, headMap);
			os = new FileOutputStream(new File(path));
			wb.write(os);
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
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
	
	
	private static void setCellValue(HSSFRow curRow,int colCnt, Object valueObj){
		try{
			if (valueObj instanceof Date){
				curRow.createCell(colCnt).setCellValue(new HSSFRichTextString(DateUtil.dateToStr((Date)valueObj)));
			}else if (valueObj instanceof Long){
				curRow.createCell(colCnt).setCellValue((Long)valueObj);
			}else if (valueObj instanceof Integer){
				curRow.createCell(colCnt).setCellValue((Integer)valueObj);
			}else if (valueObj instanceof Double){
				curRow.createCell(colCnt).setCellValue((Double)valueObj);
			}else if (valueObj instanceof Boolean){
				curRow.createCell(colCnt).setCellValue((Boolean)valueObj);
			}else{
				curRow.createCell(colCnt).setCellValue(new HSSFRichTextString(valueObj.toString()));
			}
		}catch (Exception e){
			curRow.createCell(colCnt).setCellValue(new HSSFRichTextString());
		}
	}
}
