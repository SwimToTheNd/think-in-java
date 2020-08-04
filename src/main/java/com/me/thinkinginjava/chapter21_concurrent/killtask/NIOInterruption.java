/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.killtask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * NIO类提供了更人性化的I/O中断。被阻塞的nio通道会自动地响应中断
 * 
 * @author BloodFly
 * @date 2018年6月30日
 */
public class NIOInterruption {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
		// 创建一个socket通道并且连接到行程地址
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		Future<?> f = exec.submit(new NIOBlocked(sc1));
		exec.execute(new NIOBlocked(sc2));
		exec.shutdown();
		TimeUnit.SECONDS.sleep(1);
		// 将中断发送给线程，捕获ClosedByInterruptExceptionNIO中断阻塞
		f.cancel(true);
		TimeUnit.SECONDS.sleep(1);
		// 关闭底层的NIO通道通道资源，捕获AsynchronousCloseException异常中断阻塞
		sc2.close();

	}

}

class NIOBlocked implements Runnable {

	private final SocketChannel sc;

	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Waiting for read() in " + this);
		try {
			sc.read(ByteBuffer.allocate(1));
		} catch (ClosedByInterruptException e) {
			System.out.println("ClosedByInterruptException");
		} catch (AsynchronousCloseException e) {
			System.out.println("AsynchronousCloseException");
		} catch (IOException e) {
			throw new RuntimeException();
		}
		System.out.println("Exiting NIOBlocked.run() " + this);
	}

}
