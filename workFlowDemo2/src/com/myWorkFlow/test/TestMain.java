package com.myWorkFlow.test;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.myWorkFlow.base.FlowContext;
import com.myWorkFlow.base.FlowExcutor;

/**
 * 
 * 处理码测试暂时约定"消息类型_业务码_支付通道_序列号" 这种格式
 *
 */

public class TestMain {

	@Test
	//走默认流程
	public void test1(){
		String xmlCtxPath1 = "classpath:componentContext.xml";
		String xmlCtxPath2 = "classpath:flowContext.xml";
		
		new FileSystemXmlApplicationContext(new String[]{xmlCtxPath2,xmlCtxPath1});
		
		FlowContext context = new FlowContext();
		FlowExcutor excutor = new FlowExcutor(context);
		excutor.excute("0100_62_04_00");
		//excutor.excute("0100_50_04_00");
	}

	
	
}
