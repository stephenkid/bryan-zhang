package org.poseidon.tool.poi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Test {
	public static void main(String[] args){
		List dataList = new ArrayList();
		
		Map dataItem = new HashMap();
		dataItem.put("loginEmail", "we@163.com");
		dataItem.put("loginPassword", "111111");
		dataItem.put("loginName", "张三");
		dataItem.put("telephone", "1234567");
		dataItem.put("mobile", "13764095731");
		dataItem.put("address", "xx路xx街");
		dataItem.put("tax", "sdfd454");
		dataItem.put("isAvail", true);
		dataItem.put("inputDate", new Date());
		dataItem.put("memo", "这是备注");
		dataItem.put("number", 45);
		dataList.add(dataItem);
		
		dataItem = new HashMap();
		dataItem.put("loginEmail", "we2@163.com");
		dataItem.put("loginPassword", "114441");
		dataItem.put("loginName", "李四");
		dataItem.put("telephone", "1244467");
		dataItem.put("mobile", "13764095731");
		dataItem.put("address", "xx路xx街");
		dataItem.put("tax", "ttttt");
		dataItem.put("isAvail", false);
		dataItem.put("inputDate", null);
		dataItem.put("memo", "这是备注12");
		dataItem.put("number", 45.5);
		dataList.add(dataItem);
		
		LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("loginEmail", "登陆名");
		headMap.put("loginName", "姓名");
		headMap.put("telephone", "电话");
		headMap.put("isAvail", "是否有效");
		headMap.put("inputDate", "放入日期");
		headMap.put("memo", "备注");
		headMap.put("number", "数字");
		
		
		boolean flag = ExcelTool.list2XlsFile(dataList, headMap,"c:/temp.xls");
		
		Object[][] data = ExcelTool.readSheet("c:/temp.xls", 0);
		for (Object[] line : data){
			for(Object item : line){
				System.out.print(item);
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
}
