package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class BizCompoment extends FlowComponent {

	public void excuteWithContext(FlowContext context) {
		System.out.println("获取Biz对象做业务");
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("回滚业务");
	}

}
