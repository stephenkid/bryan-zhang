
package com.aphrodite.blockQ;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author bryan.zhang
 */

public class BlockQueue {
	private static BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<Object>(10);
	
	public static void putBlockingQueue(Object o) throws Exception{
		blockingQueue.put(o);
	}
	
	public static Object takeBlockingQueue() throws Exception{
		return blockingQueue.take();
	}
	
	public static void offerBlockingQueue(Object o, long to) throws Exception{
		blockingQueue.offer(o, to, TimeUnit.SECONDS);
	}
	
	public static void pollBlockingQueue(Object o, long to) throws Exception{
		blockingQueue.poll(to, TimeUnit.SECONDS);
	}
	
	
}
