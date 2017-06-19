package api.lang.thread.example1;

public class WaitAndNotify2 extends Thread {
	Object lockNotify;
//	Object lockNotify2;
	public WaitAndNotify2(Object o){
		lockNotify=o;
	}
	public void run(){
		synchronized(lockNotify){
			System.out.println("Enter thread_2 and notify.");
			lockNotify.notify();
		}
	}
}
