
/*
 * FileName: TestVolatile1.java
 * Author:   leslie
 * Date:     Jul 10, 2016 4:40:51 PM
 * Description: //模块目的、功能描述      
 */

package api.lang.thread.volatileDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 
 * @author leslie
 */

public class TestVolatile1 {

	@Test
	public void test1() {
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 1000; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					Volatile1.incNum();
				}
			});
		}

		executor.shutdown();
		try {
			if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				System.out.println("some task are not terminated.");
			} else {
				System.out.println("result: " + Volatile1.counter);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2(){
		for(int i = 0; i <= 20; i++){
			test1();
			Volatile1.setCounter(0);
		}
	}
}
