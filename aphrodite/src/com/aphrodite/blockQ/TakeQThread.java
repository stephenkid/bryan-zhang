package com.aphrodite.blockQ;


public class TakeQThread extends Thread{
	public TakeQThread(String threadName){
		super(threadName);
	}
	
	public void run(){
		while(true){
			try{
				Object o = BlockQueue.takeBlockingQueue();
				if (o == null){
					System.out.println("object getted is null");
				}else{
					System.out.println("object is " + o.toString());
				}
			}catch(InterruptedException e1){
				break;
			}catch(Exception e){
				e.printStackTrace();
			}
			if (Thread.interrupted()){
				break;
			}
		}
	}
}
