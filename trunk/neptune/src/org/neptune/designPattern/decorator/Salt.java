package org.neptune.designPattern.decorator;

public class Salt extends BeverageDecorator {
	public Salt(Beverage beverage) {
		super(beverage);
	}
	
	public void cost(){
		super.cost();
		this.add();
	}
	
	private void add(){
		System.out.println("╪сян");
	}
}
