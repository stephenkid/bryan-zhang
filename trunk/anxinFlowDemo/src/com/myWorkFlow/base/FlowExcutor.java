package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;
import com.myWorkFlow.component.FlowSelector;
import com.myWorkFlow.listener.FlowCmpManage;
import com.myWorkFlow.listener.FlowRlaManage;


public class FlowExcutor {
	private FlowContext context;

	public FlowExcutor(FlowContext context) {
		this.context = context;
	}

	//执行
	public void excute(String processNo) throws Throwable{
		Flow flow = this.buildFlow(processNo);
		//定义是否回滚标志位
		boolean isRollBack = false;
		//定义回滚组件顺序列表
		List<FlowComponent> rollBackCmpList = new ArrayList<FlowComponent>();
		//取到此flow里所有的组件列表
		List<FlowComponent> allCmpList = flow.getCmpList();
		for (FlowComponent cmp : allCmpList){
			//针对选取类组件处理
			if (cmp instanceof FlowSelector){
				FlowComponent cmpSlt = ((FlowSelector)cmp).getComponent(context);
				if (cmpSlt == null){
					throw new Exception("cannot get sellector component");
				}
				cmp = cmpSlt;
			}
			
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
		String unionKey = null;
		for(CmpTypeEnum ftEnum : flowRla.getCmpTypeEnumList()){
			String key = this.getKeyFromProcess(processNo, ftEnum);//取到组件对应的key
			//开始装配组件
			unionKey = ftEnum.name() + "_" + key;
			List<FlowComponent> cmpList = FlowCmpManage.getFlowCmp(unionKey);
			
			if (cmpList != null){
				//如果同一类型业务取到2个组件，则对index进行排序
				if (cmpList.size() > 1){
					Collections.sort(cmpList, new Comparator<FlowComponent>() {
						public int compare(FlowComponent o1, FlowComponent o2) {
							return (o1.getIndex() - o2.getIndex());
						};
					});
				}
				
				for (FlowComponent cmp : cmpList){
					if (cmp.isBuild(context)){//判断这个组件是否装配
						flow.addCmp(cmp);
					}
				}
			}
		}
		return flow;
	}
	
	private String getKeyFromProcess(String processNo, CmpTypeEnum flEnum){
		String[] processArray = processNo.split("_");
		if (flEnum.equals(CmpTypeEnum.biz)){
			return processArray[1];
		}else if(flEnum.equals(CmpTypeEnum.pay)){
			return processArray[2];
		}else if(flEnum.equals(CmpTypeEnum.orderInit)){
			return processArray[1];
		}else if(flEnum.equals(CmpTypeEnum.orderEnd)){
			return processArray[1];
		}else if(flEnum.equals(CmpTypeEnum.discount)){
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
