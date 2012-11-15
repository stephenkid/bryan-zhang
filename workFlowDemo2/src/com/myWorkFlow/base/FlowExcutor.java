package com.myWorkFlow.base;

import com.myWorkFlow.listener.FlowRlaManage;


public class FlowExcutor {
	private FlowContext context;

	public FlowExcutor() {
		this.context = new FlowContext();
	}

	//Ö´ÐÐ
	public void excute(String processNo){
		
	}
	
	//×é×°Flow
	private Flow buildFlow(String processNo){
		FlowRelation flowRla = FlowRlaManage.getFlowRla(processNo);
		//TODO
		return null;
	}
	
	public FlowContext getContext() {
		return context;
	}

	public void setContext(FlowContext context) {
		this.context = context;
	}
}
