package com.myWorkFlow.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowRelation;
import com.myWorkFlow.event.FlowRlaRegEvent;
import com.myWorkFlow.util.ApplicationContextHolder;

public class FlowRlaManage implements ApplicationListener {

	private static Map<String, FlowRelation> flowRlaMap = new HashMap<String, FlowRelation>();
	
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof FlowRlaRegEvent){
			FlowRelation fr = (FlowRelation)event.getSource();
			this.onBind(fr);
		}
	}
	
	public static FlowRelation getFlowRla(String key){
		FlowRelation flowRla = null;
		flowRla = flowRlaMap.get(key);
		//如果获取不到流程关系，则取默认的流程关系
		if (flowRla == null){
			flowRla = (FlowRelation)ApplicationContextHolder.getBean("defaultRelation");
		}
		return flowRla;
	}
	
	public static FlowRelation getFlow(FlowContext context){
		String key = null;
		//TODO从context里获取key
		return getFlowRla(key);
	}
	
	
	public void onBind(FlowRelation flowRla){
		List<String> keyList = flowRla.getKeyList();
		for (String key : keyList){
			this.flowRlaMap.put(key, flowRla);
		}
	}

}
