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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

@SuppressWarnings({"unchecked","deprecation"})
@Component("XlsExportImpl")
public class XlsExportImpl implements DataExport {
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 把list转换成HSSFWorkbook对象,内部支持pojo和map
	 */
	public <T,O> O convertList(List<T> dataList, LinkedHashMap<String, String> headMap){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow curRow = null;
		HSSFCell cell = null;
		short rowCnt = 0;
		short colCnt = 0;
		HSSFSheet sheet = wb.createSheet();
		
		try{
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
	                    sheet.autoSizeColumn(colCnt);
	                    colCnt++;
	                }
	            }else{
	                for (Method m : dataObj.getClass().getMethods()){
	                    if (m.getName().indexOf("get") == 0) {
	                        if (headMap.keySet().contains(StringUtil.getMethodPar(m.getName()))){
	                            valueObj = m.invoke(dataObj);
	                            PoiUtil.setCellValue(curRow, colCnt, valueObj);
	                            sheet.autoSizeColumn(colCnt);
	                            colCnt++;
	                        }
	                    }
	                }
	            }
	        }
		}catch(Exception  e){
		    e.printStackTrace();
		    return null;
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
	
	/**
	 * 根据sql生成大容量xls，采用fetch的方式
	 */
	public void generateFileFromSql(CharSequence sql, List<Object> argList, String path){
	    Object[] args = null;
	    OutputStream os = null;
		try{
		    os = new FileOutputStream(new File(path));
		    if (argList != null && argList.isEmpty() == false){
	            args = argList.toArray();
	        }
	        this.jdbcTemplate.query(sql.toString(), args, new XlsRowCallbackHandler(os));
		}catch(Exception e){
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
	
	private class XlsRowCallbackHandler implements RowCallbackHandler{
	    
	    private HSSFWorkbook wb;
	    
	    private OutputStream os;
	    
	    private int rowCnt = 0;
	    
	    public XlsRowCallbackHandler(OutputStream os){
	        this.wb = new HSSFWorkbook();
	        this.os = os;
	        
	        wb.createSheet();
	    }
	    
	    public void processRow(ResultSet rs) throws SQLException {
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow curRow = null;
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            System.out.println(this.rowCnt);
            if (this.rowCnt == 0){
                curRow = sheet.createRow(this.rowCnt);
                HSSFCell cell = null;
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
                    cell = curRow.createCell(i);
                    cell.setCellValue(new HSSFRichTextString(rs.getMetaData().getColumnName(i+1)));
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    style.setFont(font);
                    cell.setCellStyle(style);
                }
                this.rowCnt++;
            }else{
                curRow = sheet.createRow(this.rowCnt);
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
                    PoiUtil.setCellValue(curRow, i, rs.getObject(i+1));
                }
                this.rowCnt++;
            }
            this.writeWorkbook();
        }
	    
	    private void writeWorkbook(){
	        try {
                this.wb.write(this.os);
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
	}
}
