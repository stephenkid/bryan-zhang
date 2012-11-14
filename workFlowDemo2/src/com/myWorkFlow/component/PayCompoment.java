package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class PayCompoment extends FlowComponent {

	public void excuteWithContext(FlowContext context) {
		System.out.println("调用pf去完成支付");
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("回滚支付");
		
	}

}
