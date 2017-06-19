/**
 * Project Name:MyJava  
 * File Name:BlockingQueue.java  
 * Package Name:api.lang.thread.producer  
 * Date:Feb 16, 2017 3:21:41 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.lang.thread.producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ClassName: BlockingQueue <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 16, 2017 3:21:41 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class BlockingQueue1 {
	private static Integer count = 0;
	final BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(10);
	class Producer implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					bq.put(1);
					count++;
					System.out.println(Thread.currentThread().getName()
							+ "生产者生产，目前总共有" + count);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class Consumer implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					bq.take();
					count--;
					System.out.println(Thread.currentThread().getName()
							+ "消费者消费，目前总共有" + count);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BlockingQueue1 hosee = new BlockingQueue1();
		new Thread(hosee.new Producer()).start();
		new Thread(hosee.new Consumer()).start();
//		new Thread(hosee.new Producer()).start();
//		new Thread(hosee.new Consumer()).start();
//
//		new Thread(hosee.new Producer()).start();
//		new Thread(hosee.new Consumer()).start();
//		new Thread(hosee.new Producer()).start();
//		new Thread(hosee.new Consumer()).start();
	}

}
