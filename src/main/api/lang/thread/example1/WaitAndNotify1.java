package api.lang.thread.example1;

public class WaitAndNotify1 extends Thread {
	Object lockWait;
	public WaitAndNotify1(Object o){
		lockWait=o;
	}
	public void run(){
//		System.out.println("My Name is "+Thread.currentThread().getName());
	    try{
	    	synchronized(lockWait){
	    		System.out.println("Enter thread_1 and wait.");
	    		lockWait.wait();
	    		System.out.println("be notified.");
	    	}
	    }catch (InterruptedException ie){
	    	ie.printStackTrace();
	    }
	}
}
