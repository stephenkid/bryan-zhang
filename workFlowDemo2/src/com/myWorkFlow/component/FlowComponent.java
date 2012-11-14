package com.myWorkFlow.component;

import java.util.List;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowTypeEnum;
import com.myWorkFlow.event.FlowCmpRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public abstract class FlowComponent {
	
	//组件属于的类型
	private FlowTypeEnum typeEnum;
	
	//组件对应的Key值
	private List<String> keyList; 
	
	//超类初始化方法，用于spring的监听机制，在OSGI不需要
	public void init(){
		FlowCmpRegEvent event = new FlowCmpRegEvent(this);
		ApplicationContextHolder.getApplicationContext().publishEvent(event);
	}
	
	public abstract void excuteWithContext(FlowContext context);
	
	public void rollbackWithContext(FlowContext context){
		//如果有就子类复写。没有就不复写
	}

	public FlowTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(FlowTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}
}
