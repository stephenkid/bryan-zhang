package com.myWorkFlow.listener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.myWorkFlow.base.CmpTypeEnum;
import com.myWorkFlow.component.FlowComponent;
import com.myWorkFlow.event.FlowCmpRegEvent;

public class FlowCmpManage implements ApplicationListener {

	private static Map<CmpTypeEnum, Map<String, List<FlowComponent>>> flowCmpMap 
		= new HashMap<CmpTypeEnum, Map<String,List<FlowComponent>>>();

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof FlowCmpRegEvent){
			FlowComponent fc = (FlowComponent)event.getSource();
			this.onBind(fc);
		}
	}

	public static List<FlowComponent> getFlowCmp(CmpTypeEnum type, String key){
		List<FlowComponent> cmpList = null;
		Map<String, List<FlowComponent>> cmpMap = flowCmpMap.get(type);
		if (cmpMap != null && !cmpMap.isEmpty()){
			cmpList = cmpMap.get(key);
		}
		return cmpList;
	}
	
	public void onBind(FlowComponent flowCmp){
		CmpTypeEnum type = flowCmp.getTypeEnum();
		List<String> keyList = flowCmp.getKeyList();
		
		for (String key : keyList){
			if (flowCmpMap.containsKey(type)){
				if (flowCmpMap.get(type).containsKey(key)){
					flowCmpMap.get(type).get(key).add(flowCmp);
				}else{
					flowCmpMap.get(type).put(key, Arrays.asList(new FlowComponent[]{flowCmp}));
				}
			}else{
				Map<String, List<FlowComponent>> innerMap = new HashMap<String, List<FlowComponent>>();
				innerMap.put(key, Arrays.asList(new FlowComponent[]{flowCmp}));
				flowCmpMap.put(type, innerMap);
			}
		}
	}
	
}
