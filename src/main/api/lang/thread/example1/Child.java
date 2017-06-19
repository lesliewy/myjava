/**
 * Project Name:MyJava  
 * File Name:Child.java  
 * Package Name:api.lang.thread.example1  
 * Date:Feb 16, 2017 2:35:52 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.lang.thread.example1;

/**
 * synchronized 是可重入锁:
 * 	如果线程进入由线程已经拥有的监控器保护的 synchronized 块，就允许线程继续进行，当线程退出第二个（或者后续） synchronized 块的时候，不释放锁，
 * 只有线程退出它进入的监控器保护的第一个synchronized 块时，才释放锁。
 * 
 * 
 * date: Feb 16, 2017 2:35:52 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Child extends Father implements Runnable {
	final static Child child = new Child();// 为了保证锁唯一
	static int i = 0;
	public static void main(String[] args) {
		for ( i = 0; i < 10; i++) {
			new Thread(child).start();
		}
	}
	
	public synchronized void doSomething(int i) {
		System.out.println("=======" + i + "========");
		System.out.println("1child.doSomething()");
		doAnotherThing(); // 调用自己类中其他的synchronized方法
	}

	private synchronized void doAnotherThing() {
		super.doSomething(); // 调用父类的synchronized方法
		System.out.println("3child.doAnotherThing()");
	}

	@Override
	public void run() {
		doSomething(i);
	}
}

class Father {
	public synchronized void doSomething() {
		System.out.println("2father.doSomething()");
	}
}
