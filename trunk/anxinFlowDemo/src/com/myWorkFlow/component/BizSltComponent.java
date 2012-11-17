package com.myWorkFlow.component;

import java.util.Map;

import com.myWorkFlow.base.FlowContext;

public class BizSltComponent extends FlowComponent implements FlowSelector {
	
	private Map<String, FlowComponent> cmpSltMap;

	@Override
	public boolean excuteWithContext(FlowContext context) {
		return true;
	}

	@Override
	public FlowComponent getComponent(FlowContext context) {
		String version = context.getAttribute("version").toString();
		return this.cmpSltMap.get(version);
	}

	public Map<String, FlowComponent> getCmpSltMap() {
		return cmpSltMap;
	}

	public void setCmpSltMap(Map<String, FlowComponent> cmpSltMap) {
		this.cmpSltMap = cmpSltMap;
	}

}
