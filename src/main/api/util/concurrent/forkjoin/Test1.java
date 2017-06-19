/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.util.concurrent.forkjoin  
 * Date:Feb 18, 2017 3:29:48 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinTask：我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。它提供在任务中执行fork()和join()操作的机制，通常情况下我们不需要直接继承ForkJoinTask类，
 * 而只需要继承它的子类，Fork/Join框架提供了以下两个子类：
	RecursiveAction：用于没有返回结果的任务。
	RecursiveTask ：用于有返回结果的任务。
	ForkJoinPool ：ForkJoinTask需要通过ForkJoinPool来执行，任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。
	当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务。
 * 
 * ForkJoinTask与一般的任务的主要区别在于它需要实现compute方法，在这个方法里，首先需要判断任务是否足够小，如果足够小就直接执行任务。
 * 如果不足够小，就必须分割成两个子任务，每个子任务在调用fork方法时，又会进入compute方法，看看当前子任务是否需要继续分割成孙任务，
 * 如果不需要继续分割，则执行当前子任务并返回结果。使用join方法会等待子任务执行完并得到其结果。
 * 
 * date: Feb 18, 2017 3:29:48 PM <br/>  
 * 
 * @author leslie
 * @version
 * @since version 1.0
 */
public class Test1 extends RecursiveTask<Long> {
	/**
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	public final static int THRESHOLD = 10_000_000;
	private int start;
	private int end;

	public Test1(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	protected Long compute() {
		long sum = 0L;
		boolean devide = (end - start) <= THRESHOLD;
		if (devide) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int mid = (start + end) >> 1;
			Test1 leftTask = new Test1(start, mid);
			Test1 rightTask = new Test1(mid + 1, end);
			leftTask.fork();
			rightTask.fork();
			sum += leftTask.join();
			sum += rightTask.join();
		}
		return sum;
	}

	private long cal(int start, int end) {
		long sum = 0L;
		for (int i = start; i <= end; i++) {
			sum += i;
		}
		return sum;
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		int end = 999_999_999;
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Test1 task = new Test1(1, end);
		Future<Long> future = forkJoinPool.submit(task);
		long t1 = System.currentTimeMillis();
		long result1 = future.get();
		long t2 = System.currentTimeMillis();
		long result2 = task.cal(1, end);
		long t3 = System.currentTimeMillis();

		System.out.println("1:" + result1 + "   " + (t2 - t1) + "ms");
		System.out.println("2:" + result2 + "   " + (t3 - t2) + "ms");
	}
}
