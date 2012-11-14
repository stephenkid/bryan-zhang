package com.myWorkFlow.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowRelation;
import com.myWorkFlow.component.FlowComponent;
import com.myWorkFlow.event.FlowCmpRegEvent;
import com.myWorkFlow.event.FlowRlaRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public class FlowCmpManage implements ApplicationListener {

	private static Map<String, List<FlowComponent>> flowCmpMap = new HashMap<String, List<FlowComponent>>();

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof FlowCmpRegEvent){
			FlowComponent fc = (FlowComponent)event.getSource();
			this.onBind(fc);
		}
	}

	public static List<FlowComponent> getFlowCmpList(String key){
		List<FlowComponent> cmpList = flowCmpMap.get(key);
		return cmpList;
	}
	
	public void onBind(FlowComponent flowCmp){
		flowCmp.get
	}
	
}
