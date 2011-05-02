package org.neptune.designPattern.abstractFactory;

public class ConcreteFactory2 extends AbstractFactory {

	@Override
	public IProductA getProductA() {
		return new ConcreteProductA2();
	}

	@Override
	public IProductB getProductB() {
		return new ConcreteProductB2();
	}

}
