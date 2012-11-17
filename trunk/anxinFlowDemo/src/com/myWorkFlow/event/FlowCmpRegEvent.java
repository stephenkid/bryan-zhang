package com.myWorkFlow.event;

import org.springframework.context.ApplicationEvent;

public class FlowCmpRegEvent extends ApplicationEvent {

	public FlowCmpRegEvent(Object source) {
		super(source);
	}
	
}
