package org.neptune.designPattern.myBuild;

public class FlowBuildB extends FlowBuildTemplate {

	@Override
	protected void beforeBuildBiz() {
		System.out.println("flow B before build biz");

	}

	@Override
	protected void beforeBuildPay() {
		System.out.println("flow B before build pay");

	}

	@Override
	protected void beforeBuildReverse() {
		System.out.println("flow B before build reverse");

	}
}
