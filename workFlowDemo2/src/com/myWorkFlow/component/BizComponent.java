package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class BizComponent extends FlowComponent {

	public boolean excuteWithContext(FlowContext context) {
		System.out.println("获取Biz对象做业务");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("回滚业务");
	}

}
