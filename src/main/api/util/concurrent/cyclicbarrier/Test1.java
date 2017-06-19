/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.util.concurrent.cyclicbarrier  
 * Date:Feb 19, 2017 12:58:48 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier
 * 我已经到达了屏障，然后当前线程被阻塞。
 * 
 * date: Feb 19, 2017 12:58:48 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {
	static CyclicBarrier c = new CyclicBarrier(2);

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}
		}).start();
		try {
			c.await();
		} catch (Exception e) {
		}
		System.out.println(2);
	}
}
