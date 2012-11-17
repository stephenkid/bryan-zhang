
package com.myWorkFlow.base;

import java.util.HashMap;

public class FlowContext {
	
	private HashMap<String, Object> context=new HashMap<String, Object>();
	
	public static final String PAY_RESULT = "payResult";
	public static final String BIZ_RESULT = "bizResult";
	
	public Object getAttribute(String key) {
		return context.get(key);
	}
	public void setAttribute(String key, Object value) {
		context.put(key,value);
	}
	public boolean containsKey(String key) {
		return context.containsKey(key);
	}
	public void setPayResult(Object value){
		context.put(PAY_RESULT, value);
	}
	public void setBizResult(Object value){
		context.put(BIZ_RESULT, value);
	}
	
}
