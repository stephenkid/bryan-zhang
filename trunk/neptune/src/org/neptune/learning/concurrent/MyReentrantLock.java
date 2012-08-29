package org.neptune.learning.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock extends Thread {
	TestReentrantLock lock;
	private int id;

	public MyReentrantLock(int i, TestReentrantLock test) {
		this.id = i;
		this.lock = test;
	}

	public void run() {
		lock.print(id);
	}

	public static void main(String args[]) {
		ExecutorService service = Executors.newCachedThreadPool();
		TestReentrantLock lock = new TestReentrantLock();
		for (int i = 0; i < 10; i++) {
			service.submit(new MyReentrantLock(i, lock));
		}
		service.shutdown();
	}
}

class TestReentrantLock {
	private ReentrantLock lock = new ReentrantLock();

	public void print(int str) {
		try {
//		    ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的等待而直接返回，
//		           这时不用获取锁，而会抛出一个InterruptedException。   
//		    ReentrantLock.lock方法不允许Thread.interrupt中断,即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。
//		          只是在最后获取锁成功后再把当前线程置为interrupted状态,然后再中断线程。
//			lock.lock();
		    lock.lockInterruptibly();
			System.out.println(str + "获得");
			Thread.sleep((int) (Math.random() * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(str + "释放");
			lock.unlock();
		}
	}
}