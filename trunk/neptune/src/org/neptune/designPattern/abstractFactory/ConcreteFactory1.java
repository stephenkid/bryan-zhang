package org.neptune.designPattern.abstractFactory;

public class ConcreteFactory1 extends AbstractFactory {

	@Override
	public IProductA getProductA() {
		return new ConcreteProductA1();
	}

	@Override
	public IProductB getProductB() {
		return new ConcreteProductB1();
	}

}
