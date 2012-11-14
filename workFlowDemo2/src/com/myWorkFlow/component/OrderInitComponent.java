package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class OrderInitComponent extends FlowComponent{
	public void excuteWithContext(FlowContext context) {
		System.out.println("订单初始化操作");
		
	}
}
