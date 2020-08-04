/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

/**
 * 在run内部打印一个消息，然后调用yield()。
 * <p>
 * 重复这个操作三次，然后从run()返回。
 * <p>
 * 在构造器中放置一条启动消息，并且放置一条在任务终止时的关闭消息。
 * <p>
 * 使用线程创建大量的这种任务并驱动它们。
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class Practice01_PrintMessage implements Runnable {

	public Practice01_PrintMessage() {
		System.out.println(this + " print message start: ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Hello thread [" + this + "] is printing a message");
		Thread.yield();
		Thread.yield();
		Thread.yield();
		System.out.println("[" + this + "] end");
	}

	public static void main(String[] args) {
		int threadCount = 10000;
		for (int i = 0; i < threadCount; i++) {
			new Thread(new Practice01_PrintMessage()).start();
		}
		System.out.println("Wating for task to print message");
	}

}
