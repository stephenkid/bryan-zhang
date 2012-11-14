package com.myWorkFlow.base;

import java.util.List;

import com.myWorkFlow.component.FlowComponent;

public class FlowType {
	//组件类型对应的具体组件，大多数情况下只有一个
	private List<FlowComponent> componentList;

	//组件类型所属的类型
	private FlowTypeEnum typeEnum;
	
	public void addComponent(FlowComponent component){
		this.componentList.add(component);
	}
	
	public void removeComponent(FlowComponent component){
		this.componentList.remove(component);
	}
	
	public List<FlowComponent> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<FlowComponent> componentList) {
		this.componentList = componentList;
	}

	public FlowTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(FlowTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
}
