package org.neptune.designPattern.adapter.classAdapter;

public class Adapter implements Target {

	private Adaptee adaptee;
	
	public Adapter(Adaptee adaptee){
		super();
		this.adaptee = adaptee;
	}
	
	
	public void sampleOperation1() {
		adaptee.sampleOperation1();

	}

	public void sampleOperation2() {
		

	}

}
