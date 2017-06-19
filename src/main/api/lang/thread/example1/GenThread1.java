package api.lang.thread.example1;

class GenThread1 extends Thread{
	public void run(){
		System.out.println("My Name is "+Thread.currentThread().getName());
	}
}
