package org.poseidon.component.dataExport;

/**
 * @author Bryan Zhang
 * POI导出实现器
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.poseidon.util.DateUtil;
import org.poseidon.util.StringUtil;

public class XlsExportImpl implements DataExport {
	/**
	 * 把list转换成HSSFWorkbook对象,内部支持pojo和map
	 */
	@SuppressWarnings("unchecked")
	public <O,T> O convertList(List<T> dataList, LinkedHashMap<String, String> headMap) 
				throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow curRow = null;
		HSSFCell cell = null;
		short rowCnt = 0;
		short colCnt = 0;
		HSSFSheet sheet = wb.createSheet();
		
		//写入列头
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		curRow = sheet.createRow(rowCnt++);
		Iterator<String> it = headMap.values().iterator();
		while(it.hasNext()){
			cell = curRow.createCell(colCnt);
			cell.setCellValue(new HSSFRichTextString(it.next()));
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			cell.setCellStyle(style);
			colCnt++;
		}
		
		//写入主数据
		Object valueObj = null;
		Map<String, ?> mapItem = null;
		for(T dataObj : dataList){
			curRow = sheet.createRow(rowCnt++);
			colCnt = 0;
			
			//针对spring的jdbctemplate和 hibernatetemplate所返回的item类型进行处理
			if (dataObj instanceof Map){
				mapItem = (Map<String, ?>)dataObj;
				for (it = headMap.keySet().iterator(); it.hasNext();) {
					valueObj = mapItem.get(it.next());
					setCellValue(curRow, colCnt, valueObj);
					sheet.autoSizeColumn(colCnt);
					colCnt++;
				}
			}else{
				for (Method m : dataObj.getClass().getMethods()){
					if (m.getName().indexOf("get") == 0) {
						if (headMap.keySet().contains(StringUtil.getMethodPar(m.getName()))){
							valueObj = m.invoke(dataObj);
							setCellValue(curRow, colCnt, valueObj);
							sheet.autoSizeColumn(colCnt);
							colCnt++;
						}
					}
				}
			}
		}
		return (O)wb;
	}
	
	
	/**
	 * 把list写入文件,内部支持pojo和map
	 */
	public <T> boolean convertList2File(List<T> dataList, LinkedHashMap<String, String> headMap, String path){
		boolean flag = true;
		OutputStream os = null;
		try{
			HSSFWorkbook wb = (HSSFWorkbook)this.convertList(dataList, headMap);
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
	
	private void setCellValue(HSSFRow curRow,int colCnt, Object valueObj){
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
