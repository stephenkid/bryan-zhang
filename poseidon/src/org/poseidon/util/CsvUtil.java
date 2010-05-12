package org.poseidon.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CsvUtil {
	public static String SPLIT_SIGN = ",";
	public static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formList(List<Map<String, Object>> list) {
		StringBuffer sb = new StringBuffer();
		if (list == null || list.isEmpty()) {
			return sb.toString();
		}

		Set<String> keys = list.get(0).keySet();
		for (String key : keys) {
			sb.append(key + SPLIT_SIGN);
		}
		if (!keys.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("\r\n");

		for (Map<String, Object> map : list) {
			keys = map.keySet();
			for (String key : keys) {
				Object value = map.get(key);
				String valueStr = "";
				if(value!=null){
					if(value instanceof Date){
						valueStr=simpleDateFormat.format(value);
					}else{
						valueStr=value.toString();
					}
					valueStr=valueStr.replaceAll(",", "，").replaceAll("(\r\n|\n\r|\r|\n)", "");
				}
				sb.append(valueStr + SPLIT_SIGN);
			}
			if (!keys.isEmpty()) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}
}
