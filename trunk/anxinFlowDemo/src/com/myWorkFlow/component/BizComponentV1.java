package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class BizComponentV1 extends FlowComponent {

	public boolean excuteWithContext(FlowContext context) {
		System.out.println("获取Biz对象做业务V1");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("回滚业务");
	}

}
