package com.myWorkFlow.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.myWorkFlow.base.CmpTypeEnum;
import com.myWorkFlow.component.FlowComponent;
import com.myWorkFlow.event.FlowCmpRegEvent;

public class FlowCmpManage implements ApplicationListener {

	private static Map<String, List<FlowComponent>> flowCmpMap 
		= new HashMap<String, List<FlowComponent>>();

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof FlowCmpRegEvent){
			FlowComponent fc = (FlowComponent)event.getSource();
			this.onBind(fc);
		}
	}

	public static List<FlowComponent> getFlowCmp(String key){
		return flowCmpMap.get(key);
	}
	
	public void onBind(FlowComponent flowCmp){
		CmpTypeEnum type = flowCmp.getTypeEnum();
		List<String> keyList = flowCmp.getKeyList();
		
		for (String key : keyList){
			String unionKey = type.name() + "_" + key;
			
			if (flowCmpMap.containsKey(unionKey)){
				flowCmpMap.get(unionKey).add(flowCmp);
			}else{
				List<FlowComponent> cmpList = new ArrayList<FlowComponent>();
				cmpList.add(flowCmp);
				flowCmpMap.put(unionKey, cmpList);
			}
		}
	}
	
}
