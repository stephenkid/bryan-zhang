package org.poseidon.component.dataExport;

/**
 * xls导出组件
 * @author Bryan Zhang
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.poseidon.util.DateUtil;
import org.poseidon.util.PoiUtil;
import org.poseidon.util.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

@SuppressWarnings({"unchecked"})
@Component("XlsExportor")
public class XlsExportor{
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	private static final String CSV_JOIN_STR = ",";
	
	/**
	 * 把list转换成HSSFWorkbook对象,内部支持pojo和map
	 */
	public <T> HSSFWorkbook convertList(List<T> dataList, LinkedHashMap<String, String> headMap, Map<Integer, Integer> colWidthMap, 
	                                    XlsHeadDeal headDeal, XlsBottomDeal bottomDeal){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow curRow = null;
		HSSFCell cell = null;
		Integer rowCnt = 0;
		Integer colCnt = 0;
		HSSFSheet sheet = wb.createSheet();
		
		//设置列宽度
		if (colWidthMap != null){
		    for (Entry<Integer, Integer> entry : colWidthMap.entrySet()){
		        sheet.setColumnWidth(entry.getKey(), entry.getValue());
		    }
		}
		
		try{
		    //头部处理
		    if (headDeal != null){
		        rowCnt = headDeal.dealHead(sheet, rowCnt);
		    }
		    
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
	                    PoiUtil.setCellValue(curRow, colCnt, valueObj);
	                    colCnt++;
	                }
	            }else{
	                for (Entry<String, String> headEntry : headMap.entrySet()){
	                    Method m = dataObj.getClass().getMethod("get" + StringUtil.upperFirstChar(headEntry.getKey()));
	                    if (m != null){
	                        valueObj = m.invoke(dataObj);
                            PoiUtil.setCellValue(curRow, colCnt, valueObj);
                            colCnt++;
	                    }
	                }
	            }
	        }
	        
	        //底部处理
	        if (bottomDeal != null){
	            rowCnt = bottomDeal.dealBottom(sheet, rowCnt);
	        }
		}catch(Throwable t){
		    t.printStackTrace();
		    wb = null;
		}
		return wb;
	}
	
	
	/**
	 * 把list写入文件,内部支持pojo和map
	 */
	public <T> boolean convertList2File(List<T> dataList, LinkedHashMap<String, String> headMap,
	                                    Map<Integer, Integer> colWidthMap, XlsHeadDeal headDeal, XlsBottomDeal bottomDeal,
	                                    String path){
		boolean flag = true;
		OutputStream os = null;
		try{
			HSSFWorkbook wb = (HSSFWorkbook)this.convertList(dataList, headMap, colWidthMap, headDeal, bottomDeal);
			os = new FileOutputStream(new File(path));
			wb.write(os);
		}catch(Throwable t){
			t.printStackTrace();
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
	
	/**
	 * 根据sql生成大容量csv格式文件，采用fetch的方式
	 */
	public void generateFileFromSql(CharSequence sql, List<Object> argList, String path){
	    Object[] args = null;
	    OutputStream os = null;
		try{
		    os = new FileOutputStream(new File(path), true);
		    PrintWriter pw = new PrintWriter(os, true);
		    if (argList != null && argList.isEmpty() == false){
	            args = argList.toArray();
	        }
	        this.jdbcTemplate.query(sql.toString(), args, new CsvRowCallbackHandler(pw));
		}catch(Throwable e){
		    e.printStackTrace();
		}finally{
		    try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
	
	/***************************************************private class*****************************************************/
	
	/**
	 * csv Fetch处理器
	 */
	private class CsvRowCallbackHandler implements RowCallbackHandler{
	    
	    private PrintWriter pw;
	    
	    private int rowCnt = 0;
	    
	    public CsvRowCallbackHandler(PrintWriter pw){
	        this.pw = pw;
	    }
	    
	    public void processRow(ResultSet rs) throws SQLException {
	        if (this.rowCnt == 0){
	            rs.setFetchSize(1000);
	            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
	                if (i == rs.getMetaData().getColumnCount() - 1){
	                    this.writeToFile(pw, rs.getMetaData().getColumnName(i+1), true);
	                }else{
	                    this.writeToFile(pw, rs.getMetaData().getColumnName(i+1), false);
	                }
                }
	            pw.println();
                this.rowCnt++;
	        }
	        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
	            if (i == rs.getMetaData().getColumnCount() - 1){
	                this.writeToFile(pw, rs.getObject(i+1), true);
                }else{
                    this.writeToFile(pw, rs.getObject(i+1), false);
                }
            }
	        pw.println();
	        this.rowCnt++;
        }
	    
	    private void writeToFile(PrintWriter pw, Object valueObj, boolean isLineEnd){
	        if (valueObj == null) {
	            pw.print(CSV_JOIN_STR);
	            return;
	        }
	        if (valueObj instanceof Date){
                pw.print(DateUtil.dateToStr((Date)valueObj));
            }else{
                pw.print(valueObj.toString());
            }
	        if (!isLineEnd) pw.print(CSV_JOIN_STR);
	    }
	}
}
