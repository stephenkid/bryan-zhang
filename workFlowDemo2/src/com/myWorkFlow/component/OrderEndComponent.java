package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class OrderEndComponent extends FlowComponent {

	public void excuteWithContext(FlowContext context) {
		System.out.println("执行订单结束操作");
	}

}
