package org.neptune.designPattern.myBuild;

public class FlowDirector implements IFlowDirector {

	private IFlowBuild flowBuild;
	
	public FlowDirector(IFlowBuild flowBuild) {
		this.flowBuild = flowBuild;
	}

	public void construct() {
		flowBuild.buildPay();
		flowBuild.buildBiz();
		flowBuild.buildReverse();
	}

}
