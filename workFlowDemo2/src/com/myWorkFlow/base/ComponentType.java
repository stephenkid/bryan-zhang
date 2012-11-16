package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;

public class ComponentType {
	//组件类型对应的具体组件，大多数情况下只有一个
	private List<FlowComponent> componentList = new ArrayList<FlowComponent>();

	//组件类型所属的类型
	private CmpTypeEnum typeEnum;
	
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

	public CmpTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(CmpTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
}
