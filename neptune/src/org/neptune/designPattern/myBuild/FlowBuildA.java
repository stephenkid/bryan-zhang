package org.neptune.designPattern.myBuild;

public class FlowBuildA extends FlowBuildTemplate {

	@Override
	protected void beforeBuildBiz() {
		System.out.println("flow A before build biz");

	}

	@Override
	protected void beforeBuildPay() {
		System.out.println("flow A before build pay");

	}

	@Override
	protected void beforeBuildReverse() {
		System.out.println("flow A before build reverse");

	}

}
