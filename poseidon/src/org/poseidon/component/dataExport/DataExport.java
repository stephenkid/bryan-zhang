package org.poseidon.component.dataExport;

/**
 * @author Bryan Zhang
 * 数据导出接口
 */

import java.util.LinkedHashMap;
import java.util.List;

public interface DataExport {
	
	/**
	 * 根据原始数据，列头转换成需要的导出对象
	 */
	public <T,O> O convertList(List<T> dataList, LinkedHashMap<String, String> headMap) 
		throws Exception;

	/**
	 * 根据原始数据，列头，文件输出路径转换
	 */
	public <T> boolean convertList2File(List<T> dataList, LinkedHashMap<String, String> headMap, String path)
		throws Exception;
}
