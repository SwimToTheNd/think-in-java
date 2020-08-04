/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.killtask;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 对于阻塞了的任务，可以通过关闭发生阻塞的底层资源从而关闭任务
 * 
 * @author BloodFly
 * @date 2018年6月30日
 */
public class ColseResource {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		// 获取服务器localhost:8080的输入流
		InputStream socketInput = new Socket("localhost", 8080).getInputStream();
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Shutting down all threads");
		// 关闭线程池
		exec.shutdownNow(); // 中断IO阻塞
		TimeUnit.SECONDS.sleep(1);
		// 在关闭线程后手动关闭底层的被阻塞的资源
		System.out.println("Closing: "+ socketInput.getClass().getName());
		socketInput.close();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing: "+System.in.getClass().getName());
		// 未抛出IOException
		System.in.close();
	}

}
