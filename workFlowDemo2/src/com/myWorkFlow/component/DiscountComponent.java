package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class DiscountComponent extends FlowComponent {

	@Override
	public boolean excuteWithContext(FlowContext context) {
		System.out.println("Ö´ÐÐµÖ¿Û²Ù×÷");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context){
		System.out.println("»Ø¹öµÖ¿Û²Ù×÷");
	}

}
