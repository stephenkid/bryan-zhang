package org.poseidon.component.dataRead;

import java.io.InputStream;

/**
 * @author Bryan zhang
 * 数据读取接口
 */

public interface DataRead {
	/**
	 * 从文件中读取数据，返回object二维数组
	 */
	public Object[][] readSheet(String fileName, int sheetNum) throws Exception;
	
	/**
	 * 从输入流中读取xls内容，以object二维数组形式返回
	 */
	public Object[][] readSheet(InputStream is, int sheetNum) throws Exception;
}
