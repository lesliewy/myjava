package api.util.concurrent.pool;

public class MyThread1 implements Runnable{
	//1  单线程池中的线程: pool-1-thread-1        多线程池: pool-1-thread-1 pool-1-thread-2 pool-1-thread-3
	public void run(){
		System.out.println(Thread.currentThread().getName()+" is running.");
		try {
			Thread.sleep(5L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
