package org.neptune.designPattern.myBuild;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Flow {
	private Queue<IFlowCompoment> excuteQueue = new LinkedBlockingQueue<IFlowCompoment>();
	
	public void add(IFlowCompoment c){
		this.excuteQueue.add(c);
	}
	
	public void excute(){
		while(true){
			if (this.excuteQueue.isEmpty() == true){
				break;
			}else{
				this.excuteQueue.poll().excute();
			}
		}
		
	}
}
