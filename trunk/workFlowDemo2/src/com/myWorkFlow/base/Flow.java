package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;

public class Flow {
	private List<FlowType> flowTypeList;
	
	//获取此FLOW里所有的顺序Component
	public List<FlowComponent> getAllComponent(){
		List<FlowComponent> cmpList = new ArrayList<FlowComponent>();
		List<FlowComponent> cmpInnerList = null;
		for (FlowType type : flowTypeList){
			cmpInnerList = type.getComponentList();
			//根据index排序
			Collections.sort(cmpInnerList, new Comparator<FlowComponent>() {
				public int compare(FlowComponent o1, FlowComponent o2) {
					return (o1.getIndex() - o2.getIndex());
				};
			});
			for (FlowComponent cmp : cmpInnerList){
				cmpList.add(cmp);
			}
		}
		return cmpList;
	}
	
	public List<FlowType> getFlowTypeList() {
		return flowTypeList;
	}

	public void setFlowTypeList(List<FlowType> flowTypeList) {
		this.flowTypeList = flowTypeList;
	}

}
