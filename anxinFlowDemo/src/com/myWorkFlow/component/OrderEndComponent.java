package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class OrderEndComponent extends FlowComponent {

	public boolean excuteWithContext(FlowContext context) {
		System.out.println("执行订单结束操作");
		return true;
	}

}
