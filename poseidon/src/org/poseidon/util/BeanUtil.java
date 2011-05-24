package org.poseidon.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
	public static <O> Map<String, ?> bean2map(O o){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Field[] fieldArray = o.getClass().getDeclaredFields();
		for (Field f : fieldArray){
			f.setAccessible(true);
			try{
				returnMap.put(f.getName(), f.get(o));
			}catch(Exception e){
				e.printStackTrace();
				returnMap = null;
			}
		}
		return returnMap;
	}
}
