
      
/*
 * FileName: TestThreadLocal.java
 * Author:   leslie
 * Date:     Jul 10, 2016 3:41:16 PM
 * Description: //模块目的、功能描述      
 */
   
package api.lang.threadlocal;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 *
 * @author leslie
 */

public class TestThreadLocal {

	@Test
	public void test1() throws InterruptedException{
		Sequence sequence = new SequenceA();
		
		List<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < 10; i++){
			SequenceThread1 thread1 = new SequenceThread1(sequence);
			thread1.start();
			threads.add(thread1);
		}
		
		for(Thread thread : threads){
			thread.join();
		}
	}
	
	@Test
	public void test2(){
		Sequence sequence = new SequenceB();
		SequenceThread1 thread1 = new SequenceThread1(sequence);
		SequenceThread1 thread2 = new SequenceThread1(sequence);
		SequenceThread1 thread3 = new SequenceThread1(sequence);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3(){
		Sequence sequence = new SequenceC();
		SequenceThread1 thread1 = new SequenceThread1(sequence);
		SequenceThread1 thread2 = new SequenceThread1(sequence);
		SequenceThread1 thread3 = new SequenceThread1(sequence);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

   