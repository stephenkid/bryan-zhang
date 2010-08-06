package com.aphrodite.blockQ;

import java.util.Date;

public class PutQThread extends Thread {
	public PutQThread(String name){
		super(name);
	}
	
	public void run(){
		while(true){
			try{
				BlockQueue.putBlockingQueue(new Date());
				Thread.sleep(5000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
