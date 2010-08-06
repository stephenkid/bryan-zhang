package com.aphrodite.blockQ;

public class PutQ {
	public static void main(String[] args){
		try{
			BlockQueue.putBlockingQueue(1L);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
