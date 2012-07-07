package org.neptune.designPattern.strategy;

public class StrategyContext {

	private IStrategy strategy;
	
	public StrategyContext(IStrategy s) {
		this.strategy = s;
	}

	public void doSomthing() throws Exception{
		this.strategy.doSomething();
	}
}
