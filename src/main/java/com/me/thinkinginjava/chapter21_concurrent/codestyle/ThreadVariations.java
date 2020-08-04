/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.codestyle;

import java.util.concurrent.TimeUnit;

/**
 * @author BloodFly
 * @date 2018年4月6日
 */
public class ThreadVariations {

	/**
	 * 使用内部类来将线程代码隐藏在类中
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();

	}

}

// Using a named inner class
class InnerThread1 {
	private int countDown = 5;
	private Inner inner;

	private class Inner extends Thread {
		public Inner(String name) {
			super(name);
			start();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#toString()
		 */
		@Override
		public String toString() {
			return getName() + ": " + countDown;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			while (true) {
				System.out.println(this);
				if (--countDown == 0) {
					return;
				}
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Interrupted");
				}
			}
		}
	}

	public InnerThread1(String name) {
		inner = new Inner(name);
	}
}

// Using an anonymous(匿名) inner class
class InnerThread2 {
	private int countDown = 5;
	private Thread t;

	public InnerThread2(String name) {
		t = new Thread(name) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0) {
							return;
						}
						sleep(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("sleep() interrupted");
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#toString()
			 */
			@Override
			public String toString() {
				return getName() + ": " + countDown;
			}
		};
	}
}

// Using a named Runnable implementation:
class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;

	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}

	// Selef Managed
	private class Inner implements Runnable {
		Thread t;

		public Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return t.getName() + ": " + countDown;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Sleep() interrupted");
			}
		}

	}
}

// Using an anonymous Runnable implementation:
class InnerRunnable2 {
	private int countDown = 5;
	private Thread thread;

	public InnerRunnable2(String name) {
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0) {
							return;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Sleep() interrupted");
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return Thread.currentThread().getName() + ": " + countDown;
			}
		}, name);
		thread.start();
	}
}

// A separate method to run some code as a task
class ThreadMethod {
	private int countDown = 5;
	private Thread thread;
	private String name;

	public ThreadMethod(String name) {
		this.name = name;
	}

	public void runTask() {
		if (thread == null) {
			thread = new Thread(name) {
				/*
				 * (non-Javadoc)
				 * 
				 * @see java.lang.Thread#run()
				 */
				@Override
				public void run() {
					try {
						while (true) {
							System.out.println(this);
							if (--countDown == 0) {
								return;
							}
							sleep(10);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println("sleep() interrupted");
					}
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.lang.Thread#toString()
				 */
				@Override
				public String toString() {
					return getName() + ": " + countDown;
				}
			};
			thread.start();
		}
	}

}