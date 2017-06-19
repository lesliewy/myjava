/**
 * Project Name:MyJava  
 * File Name:ReentrantLock1.java  
 * Package Name:api.lang.thread.producer  
 * Date:Feb 16, 2017 2:59:47 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.lang.thread.producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://my.oschina.net/hosee/blog/485121#OSC_h4_4
 * date: Feb 16, 2017 2:59:47 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class ReentrantLock1 {
	private static Integer count = 0;
	private final Integer FULL = 10;
	final Lock lock = new ReentrantLock();
	final Condition NotFull = lock.newCondition();
	final Condition NotEmpty = lock.newCondition();

	class Producer implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				lock.lock();
				try {
					while (count == FULL) {
						try {
							NotFull.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					count++;
					System.out.println(Thread.currentThread().getName()
							+ "生产者生产，目前总共有" + count);
					NotEmpty.signal();
				} finally {
					lock.unlock();
				}

			}
		}
	}

	class Consumer implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				lock.lock();
				try {
					while (count == 0) {
						try {
							NotEmpty.await();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
					count--;
					System.out.println(Thread.currentThread().getName()
							+ "消费者消费，目前总共有" + count);
					NotFull.signal();
				} finally {
					lock.unlock();
				}

			}

		}

	}

	public static void main(String[] args) throws Exception {
		ReentrantLock1 hosee = new ReentrantLock1();
		new Thread(hosee.new Producer()).start();
//		new Thread(hosee.new Consumer()).start();
		new Thread(hosee.new Producer()).start();
//		new Thread(hosee.new Consumer()).start();

		new Thread(hosee.new Producer()).start();
		new Thread(hosee.new Consumer()).start();
		new Thread(hosee.new Producer()).start();
		new Thread(hosee.new Consumer()).start();
	}

}
