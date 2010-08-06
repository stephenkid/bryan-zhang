package com.aphrodite.blockQ;


/**
 * @author bryan.zhang
 */
public class TakeQMain {
	public static void main(String[] args){
		Thread t1 = new TakeQThread("takeQThread");
		t1.start();
		Thread t2 = new  PutQThread("putQThread");
		t2.start();
	}
}
