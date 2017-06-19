/**
 * Project Name:MyJava  
 * File Name:Test2.java  
 * Package Name:api.util.concurrent.countdownlatch  
 * Date:Feb 19, 2017 12:49:54 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * ClassName: Test2 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 19, 2017 12:49:54 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test2 {
	private static int N = 10;
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		for (int i = 0; i < N; ++i)
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		doSomethingElse(); // don't let run yet
		startSignal.countDown(); // let all threads proceed
		doSomethingElse(); // 继续做其他的.
		System.out.print("wait for all to finish...");
		doneSignal.await(); // wait for all to finish
	}

	private static void doSomethingElse() throws InterruptedException{
		System.out.println("this is doSomethingElse...");
		Thread.sleep(300);
	}
}

class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
		} // return;
	}

	private void doWork() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " do work...");
		Thread.sleep(300);
	}
}
