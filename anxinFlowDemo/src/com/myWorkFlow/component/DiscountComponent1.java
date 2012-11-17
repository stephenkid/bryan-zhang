package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class DiscountComponent1 extends FlowComponent {

	@Override
	public boolean excuteWithContext(FlowContext context) {
		System.out.println("Ö´ÐÐµÖ¿Û²Ù×÷1");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context){
		System.out.println("»Ø¹öµÖ¿Û²Ù×÷1");
	}

}
