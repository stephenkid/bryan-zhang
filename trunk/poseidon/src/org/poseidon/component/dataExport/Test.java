package org.poseidon.component.dataExport;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("name", "姓名");
		headMap.put("age", "年龄");
		headMap.put("address", "地址");
		headMap.put("birthday", "出生日期");
		
		List<Map<String, ?>> dataList = new ArrayList<Map<String,?>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("name", "jack");
		dataMap.put("age", 31);
		dataMap.put("address", "center Avu");
		dataMap.put("birthday", new Date());
		dataList.add(dataMap);
		dataMap = new HashMap<String, Object>();
		dataMap.put("name", "rose");
		dataMap.put("age", 25);
		dataMap.put("address", "center Avu");
		dataMap.put("birthday", new Date());
		dataList.add(dataMap);
		
		DataExport dataExport = new XlsExportImpl();
		dataExport.convertList2File(dataList, headMap, "c:/tmp1.xls");

	}

}
