/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.util.concurrent.countdownlatch  
 * Date:Feb 18, 2017 3:10:02 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 的作用和 Thread.join() 方法类似，可用于一组线程和另外一组线程的协作。例如，主线程在做一项工作之前需要一系列的准备工作，
 * 只有这些准备工作都完成，主线程才能继续它的工作。这些准备工作彼此独立，所以可以并发执行以提高速度。在这个场景下就可以使用 CountDownLatch 协调线程之间的调度了。
 * 在直接创建线程的年代（Java 5.0 之前），我们可以使用 Thread.join()。在 JUC 出现后，因为线程池中的线程不能直接被引用，所以就必须使用 CountDownLatch 了。
 * 
 * CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * countDown方法，当前线程调用此方法，则计数减一
	await方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
	
 * date: Feb 18, 2017 3:10:02 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {
	final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);// 两个工人的协作
		Worker worker1 = new Worker("zhang san", 5000, latch);
		Worker worker2 = new Worker("li si", 8000, latch);
		worker1.start();//
		worker2.start();//
		latch.await();// 等待所有工人完成工作
		System.out.println("all work done at " + sdf.format(new Date()));
	}

	static class Worker extends Thread {
		String workerName;
		int workTime;
		CountDownLatch latch;

		public Worker(String workerName, int workTime, CountDownLatch latch) {
			this.workerName = workerName;
			this.workTime = workTime;
			this.latch = latch;
		}

		public void run() {
			System.out.println("Worker " + workerName + " do work begin at "
					+ sdf.format(new Date()));
			doWork();// 工作了
			System.out.println("Worker " + workerName + " do work complete at "
					+ sdf.format(new Date()));
			latch.countDown();// 工人完成工作，计数器减一

		}

		private void doWork() {
			try {
				Thread.sleep(workTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
