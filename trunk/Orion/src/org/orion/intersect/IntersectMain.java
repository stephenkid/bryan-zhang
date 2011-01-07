package org.orion.intersect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class IntersectMain {

	
	public static void main(String[] args) throws Exception {
		String xmlCtxPath = "classpath:spring/applicationContext.xml";
		System.out.println("Booting from " + xmlCtxPath);
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext(xmlCtxPath);
		IntersectWork work = (IntersectWork)ctx.getBean("intersectWork");

		work.findAndRecordData();
	}

}
