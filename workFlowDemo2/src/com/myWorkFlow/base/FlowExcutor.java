package com.myWorkFlow.base;

import com.myWorkFlow.listener.FlowRlaManage;


public class FlowExcutor {
	private FlowContext context;

	public FlowExcutor() {
		this.context = new FlowContext();
	}

	public void excute(String processNo){
		
	}
	
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
