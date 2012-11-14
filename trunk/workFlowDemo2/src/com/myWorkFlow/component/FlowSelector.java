package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public interface FlowSelector {
	public FlowComponent getComponent(FlowContext context);
}
