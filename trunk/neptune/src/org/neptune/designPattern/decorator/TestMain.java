package org.neptune.designPattern.decorator;

public class TestMain {

	public static void main(String[] args) {
		
		
		BeverageDecorator teaPlusSugar = new Sugar(new Tea());
		teaPlusSugar.cost();
		
		BeverageDecorator milkPlusSalt = new Salt(new Milk());
		milkPlusSalt.cost();

	}

}
