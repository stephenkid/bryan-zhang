package com.myWorkFlow.base;

import java.util.List;

import com.myWorkFlow.event.FlowRlaRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public class FlowRelation {
	private List<FlowTypeEnum> FlowTypeEnumList;
	
	private List<String> keyList;

	//超类初始化方法，用于spring的监听机制，在OSGI不需要
	public void init(){
		FlowRlaRegEvent event = new FlowRlaRegEvent(this);
		ApplicationContextHolder.getApplicationContext().publishEvent(event);
	}
	
	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public List<FlowTypeEnum> getFlowTypeEnumList() {
		return FlowTypeEnumList;
	}

	public void setFlowTypeEnumList(List<FlowTypeEnum> flowTypeEnumList) {
		FlowTypeEnumList = flowTypeEnumList;
	}
}
