package org.neptune.designPattern.myBuild;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IFlowBuild b = new FlowBuildA();
		IFlowDirector flowDirector = new FlowDirector(b);
		flowDirector.construct();
		Flow flow = b.getResultFlow();
		flow.excute();

	}

}
