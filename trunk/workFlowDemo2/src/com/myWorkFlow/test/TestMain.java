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
	public void test1(){
		String xmlCtxPath1 = "classpath:componentContext.xml";
		String xmlCtxPath2 = "classpath:flowContext.xml";
		
		new FileSystemXmlApplicationContext(new String[]{xmlCtxPath2,xmlCtxPath1});
		
		FlowContext context = new FlowContext();
		FlowExcutor excutor = new FlowExcutor(context);
		//以下处理码走默认流程
//		excutor.excute("0100_62_04_00");//所有的type都有
//		excutor.excute("0100_50_04_00");//没有抵扣type
//		excutor.excute("0100_61_04_00");//走2个抵扣流程
		
		//以下处理码走relation1
		excutor.excute("0100_50_01_00");//走relation1流程，先做biz再做支付
		
		
	}

	
	
}
