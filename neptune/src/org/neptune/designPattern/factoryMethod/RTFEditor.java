package org.neptune.designPattern.factoryMethod;

public class RTFEditor extends AbstractEditor {

	@Override
	public IDocument createDocument() {
		return new RTFDocument();
	}

}
