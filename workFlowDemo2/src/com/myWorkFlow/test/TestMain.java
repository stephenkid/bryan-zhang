package com.myWorkFlow.test;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowExcutor;


public class TestMain {

	@Test
	public void test1(){
		String xmlCtxPath1 = "classpath:componentContext.xml";
		String xmlCtxPath2 = "classpath:flowContext.xml";
		
		new FileSystemXmlApplicationContext(new String[]{xmlCtxPath2,xmlCtxPath1});
		
		FlowContext context = new FlowContext();
		FlowExcutor excutor = new FlowExcutor(context);
		excutor.excute("0100_50_04_00");
	}

}
