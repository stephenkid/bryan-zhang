package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;
import com.myWorkFlow.listener.FlowCmpManage;
import com.myWorkFlow.listener.FlowRlaManage;


public class FlowExcutor {
	private FlowContext context;

	public FlowExcutor(FlowContext context) {
		this.context = context;
	}

	//执行
	public void excute(String processNo){
		Flow flow = this.buildFlow(processNo);
		//定义是否回滚标志位
		boolean isRollBack = false;
		//定义回滚组件顺序列表
		List<FlowComponent> rollBackCmpList = new ArrayList<FlowComponent>();
		//取到此flow里所有的按顺序执行的组件列表
		List<FlowComponent> allCmpList = flow.getAllComponent();
		for (FlowComponent cmp : allCmpList){
			boolean excuteFlag = cmp.excuteWithContext(context);
			rollBackCmpList.add(cmp);
			if (!excuteFlag){
				isRollBack = true;
				break;
			}
		}
		
		//开始回滚操作
		if (isRollBack){
			//倒序排列处理
			Collections.reverse(rollBackCmpList);
			for (FlowComponent cmp : rollBackCmpList){
				cmp.rollbackWithContext(context);
			}
		}
	}
	
	//组装Flow
	private Flow buildFlow(String processNo){
		Flow flow = new Flow();
		FlowRelation flowRla = FlowRlaManage.getFlowRla(processNo);
		FlowType ft = null;
		for(FlowTypeEnum ftEnum : flowRla.getFlowTypeEnumList()){
			ft = new FlowType();
			ft.setTypeEnum(ftEnum);//设置组件类型
			String key = this.getKeyFromProcess(processNo, ftEnum);//取到组件对应的key
			
			//开始装配组件
			List<FlowComponent> cmpList = FlowCmpManage.getFlowCmp(ftEnum, key);
			if (cmpList != null){
				for (FlowComponent cmp : cmpList){
					if (cmp.isBuild(context)){//判断这个组件是否装配
						ft.addComponent(cmp);
					}
				}
			}
			flow.addFlowType(ft);
		}
		return flow;
	}
	
	private String getKeyFromProcess(String processNo, FlowTypeEnum flEnum){
		String[] processArray = processNo.split("_");
		if (flEnum.equals(FlowTypeEnum.biz)){
			return processArray[1];
		}else if(flEnum.equals(FlowTypeEnum.pay)){
			return processArray[2];
		}else if(flEnum.equals(FlowTypeEnum.orderInit)){
			return processArray[1];
		}else if(flEnum.equals(FlowTypeEnum.orderEnd)){
			return processArray[1];
		}else if(flEnum.equals(FlowTypeEnum.discount)){
			return processArray[1];
		}
		return null;
	}
	
	public FlowContext getContext() {
		return context;
	}

	public void setContext(FlowContext context) {
		this.context = context;
	}
}
