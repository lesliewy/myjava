
      
/*
 * FileName: Volatile1.java
 * Author:   leslie
 * Date:     Jul 10, 2016 4:37:16 PM
 * Description: //模块目的、功能描述      
 */
   
package api.lang.thread.volatileDemo;


/**
 * java 内存模型图:
 *    java 线程     <--->      工作内存     <--->  -|  
 *    java 线程     <--->      工作内存     <--->   | <---> 主内存    
 *    java 线程     <--->      工作内存     <--->  _|  
 * 工作内存通常是CPU高速缓存
 * 每个工作内存中都有一份主存变量的副本，线程对变量的操作都在工作内存中完成，避免访问主内存，提高性能.
 * 每个线程的工作内存都是独立的，不可见的, 通过将工作内存中的值刷新到主内存实现线程间的交互。
 * 
 * 并发情况下要保证: 
 *    原子性
 *    可见性: 在对线程内的工作内存中的共享变量操作时，保证刷新到主内存，且其他线程的工作内存中的该变量缓存失效。
 *    有序性: 指令重排.
 * volatile 可以保证可见性，以及一定程度上的可见性.  但不保证原子性.
 * synchronized, lock 可以保证原子性、可见性、有序性.
 * 
 * volatile 确保了 counter 各个线程间的可见性，将直接通过主内存读取或写入，线程从主内存中加载的值都是最新的。
 * 被volatile修饰的变量必须独立于程序的其他状态
 * volatile 不保证操作的原子性，这里的counter++ 并不是原子操作.
 * 也就是说适用于volatile的场景必须保证对共享变量的原子性操作.
 * 
 * 
 * @author leslie
 */

public class Volatile1 {

	/*
	 * volatile 是轻量的synchronized，开销比synchronized要小.
	 * volatile 保证线程间的可见性，但不保证操作的原子性. 这里仍然是线程不安全的.
	public static volatile int counter;
	public static void incNum(){
		counter++;
	}
	*/
	
	// synchronized 保证变量操作的原子性， 不需要volatile
	public static int counter;
	public synchronized static void incNum(){
		counter++;
	}
	
	public static void setCounter(int newCounter){
		counter = newCounter;
	}
}

   