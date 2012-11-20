package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;

public class Flow {
	private List<FlowComponent> cmpList = new ArrayList<FlowComponent>();
	
	public void addCmp(FlowComponent cmp){
		this.cmpList.add(cmp);
	}

	public List<FlowComponent> getCmpList() {
		return cmpList;
	}

	public void setCmpList(List<FlowComponent> cmpList) {
		this.cmpList = cmpList;
	}
}
