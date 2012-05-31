package org.neptune.designPattern.decorator;

public class Sugar extends BeverageDecorator {

	public Sugar(Beverage beverage) {
		super(beverage);
	}
	
	public void cost(){
		super.cost();
		this.add();
	}
	
	private void add(){
		System.out.println("加糖");
	}
	
}
