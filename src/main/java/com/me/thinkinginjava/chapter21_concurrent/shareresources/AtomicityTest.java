/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <h1>原子性写易变性</h1>
 * 原子操作是不能被线程调度机制中断的操作，一旦操作开始，那么它一定可以在可能发生的“上下文切换”之前（切换到其他线程执行）执行完毕。
 * <p>
 * 原子性可以应用于除long和double之外的所有基本类型之上的“简单操作”。
 * 对于读取和写入除long和double之外的基本类型变量这样的操作，可以保证它们会被当作不可分（原子）的操作来操作内存。
 * <p>
 * 可视性问题：一个任务做的修改，即使在不中断的意义上讲是原子性的，对其他任务也可能是不可视的（例如修改只是暂时性地存储在本地处理器的缓存中），
 * 因此不同的任务对应用的状态有不同的视图。
 * <p>
 * 另一方面，同步机制强制在处理器系统中，在一个任务做出的修改必须在应用中是可视的。如果没有同步机制，那么修改时可视将无法确定。
 * <p>
 * <h1>volatile</h1>
 * 关键字还确保了应用中的可视性。如果你将一个域声明为volatile的，那么只要对这个域产生了写操作，那么所有的读操作就都可以看到这个修改。
 * 即便使用了本地缓存，volatile域会立即被定稿到主存中，而读取操作就发生在主存中。
 * 在非volatile域上的原子操作不会刷新到主存中去，因此其他读取该域的任务也不会看到这个新值。如果多个任务在同时访问某个域，那么这个域就应该是volatile的。
 * 否则这个域应该只能经由同步来访问。同步也会导致向主存中刷新，因此如果一个域完全由synchronized方法或语句块来防护，那就不必将其设置为是volatile的。
 * <p>
 * 一个任务所作的任何写入操作对个任务来说都是可视的，因此如果它只需要在这个任务内部可视，那么就不需要将其设置为volatile的。
 * <p>
 * 当一个域的值依赖于它之前的值时（例如递增一个计数器），volatile就无法工作了。如果某个域的受到其他域的值的限制，那么volatile也无法工作。
 * 例如Range类的lower和upper边界就必须遵循lower<=upper的限制。
 * <p>
 * 使用volatile而不是synchronized的唯一安全的情况是类中只有一个可变的域。
 * 
 * <p>
 * 盲目的使用原子性概念。
 * 
 * @author BloodFly
 * @date 2018年5月6日
 */
public class AtomicityTest implements Runnable {
	private int i = 0;

	// 错误的使用原子性
	public int getValue() {
		return i;
	}

	private synchronized void evenIncrement() {
		i++;
		i++;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
