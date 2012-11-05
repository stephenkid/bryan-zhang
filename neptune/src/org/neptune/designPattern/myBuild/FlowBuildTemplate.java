package org.neptune.designPattern.myBuild;

public abstract class FlowBuildTemplate implements IFlowBuild{

	private Flow flow = new Flow();
	
	public void buildBiz() {
		System.out.println("template build biz");
		this.beforeBuildBiz();
		this.flow.add(new BizCompoment());
		
	}

	public void buildPay() {
		System.out.println("template build pay");
		this.beforeBuildPay();
		this.flow.add(new PayCompoment());
	}

	public void buildReverse() {
		System.out.println("template build reverse");
		this.beforeBuildReverse();
		this.flow.add(new ReverseCompoment());
	}
	
	protected abstract void beforeBuildBiz();
	protected abstract void beforeBuildPay();
	protected abstract void beforeBuildReverse();
	
	public Flow getResultFlow(){
		return this.flow;
	}

}
