package api.util.concurrent.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestPool1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 固定线程数线程池
		 */
		System.out.println("1.=============================");
//		ExecutorService pool = Executors.newFixedThreadPool(2);
//		Thread t1 = new Thread(new MyThread1());
//		Thread t2 = new Thread(new MyThread1());
//		Thread t3 = new Thread(new MyThread1());
//		Thread t4 = new Thread(new MyThread1());
//		Thread t5 = new Thread(new MyThread1());
//		// 放入池中
//		pool.execute(t1);
//		pool.execute(t2);
//		pool.execute(t3);
//		pool.execute(t4);
//		pool.execute(t5);
//		// 关闭线程池.
//		pool.shutdown();
	    /*
	     * 单线程的线程池.
	     */
		System.out.println("2.=============================");
//		ExecutorService pool = Executors.newSingleThreadExecutor();
//		Thread t1 = new Thread(new MyThread1());
//		Thread t2 = new Thread(new MyThread1());
//		Thread t3 = new Thread(new MyThread1());
//		Thread t4 = new Thread(new MyThread1());
//		Thread t5 = new Thread(new MyThread1());
//		// 放入池中
//		pool.execute(t1);
//		pool.execute(t2);
//		pool.execute(t3);
//		pool.execute(t4);
//		pool.execute(t5);
//		// 关闭线程池.
//		pool.shutdown();
		/*
		 * 可变尺寸的线程池.
		 */
//		System.out.println("3.=============================");
//		ExecutorService pool = Executors.newCachedThreadPool();
//		Thread t1 = new Thread(new MyThread1());
//		Thread t2 = new Thread(new MyThread1());
//		Thread t3 = new Thread(new MyThread1());
//		Thread t4 = new Thread(new MyThread1());
//		Thread t5 = new Thread(new MyThread1());
//		// 放入池中
//		pool.execute(t1);
//		pool.execute(t2);
//		pool.execute(t3);
//		pool.execute(t4);
//		pool.execute(t5);
//		// 关闭线程池.
//		pool.shutdown();
		/*
		 * 延迟线程池
		 */
		System.out.println("4.=============================");
//		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
//		Thread t1 = new Thread(new MyThread1());
//		Thread t2 = new Thread(new MyThread1());
//		Thread t3 = new Thread(new MyThread1());
//		Thread t4 = new Thread(new MyThread1());
//		Thread t5 = new Thread(new MyThread1());
//		// 放入池中
//		pool.execute(t1);
//		pool.execute(t2);
//		pool.execute(t3);
//		// 延迟执行
//		pool.schedule(t4, 3000, TimeUnit.MILLISECONDS);
//		pool.schedule(t5,5000,TimeUnit.MILLISECONDS);
//		// 关闭线程池.
//		pool.shutdown();
		/*
		 * 单任务延迟线程池
		 */
//		System.out.println("5.==================================");
//		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
//		Thread t1 = new Thread(new MyThread1());
//		Thread t2 = new Thread(new MyThread1());
//		Thread t3 = new Thread(new MyThread1());
//		Thread t4 = new Thread(new MyThread1());
//		Thread t5 = new Thread(new MyThread1());
//		// 放入池中
//		pool.execute(t1);
//		pool.execute(t2);
//		pool.execute(t3);
//		// 延迟执行
//		pool.schedule(t4, 3000, TimeUnit.MILLISECONDS);
//		pool.schedule(t5,5000,TimeUnit.MILLISECONDS);
//		// 关闭线程池.
//		pool.shutdown();
		/*
		 * 自定义线程池
		 */
		System.out.println("6.==================================");
		BlockingQueue bQueue = new ArrayBlockingQueue(20);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,20,TimeUnit.MILLISECONDS,bQueue);
		Thread t1 = new Thread(new MyThread1());
		Thread t2 = new Thread(new MyThread1());
		Thread t3 = new Thread(new MyThread1());
		Thread t4 = new Thread(new MyThread1());
		Thread t5 = new Thread(new MyThread1());
		// 放入池中
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池.
		pool.shutdown();
	}
}
