/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.util.concurrent.atomic  
 * Date:Feb 19, 2017 2:03:29 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: Test1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 19, 2017 2:03:29 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test2 extends Thread {

	private static final AtomicInteger TEST_INT = new AtomicInteger();
	private static int i = 0;
	
	@Override
	public void run() {
//		TEST_INT.incrementAndGet();
		i = i + 1;
	}

	/** 
	 * @param args 
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			Test2 demo = new Test2();
			demo.start();
			try {
				demo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("最终结果：" + i);
	}

}
