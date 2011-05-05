package org.neptune.designPattern.decorator;

public class BeverageDecorator implements Beverage {

	protected Beverage beverage;
	
	public BeverageDecorator(Beverage beverage){
		this.beverage = beverage;
	}
	
	public void cost() {
		beverage.cost();
	}
}
