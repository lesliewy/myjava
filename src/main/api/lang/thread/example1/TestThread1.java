package api.lang.thread.example1;

public class TestThread1 {
	/**
	 * * 使用线程有2种方式:  extends Thread    和 　　implement Runnable
	 *                      new GenThread1().start()    和  new Thread(new GenThread2()).start();
	 * * 线程执行顺序:   
	 *       设置priority 改变执行顺序.     但是好像也不是那样的，多次执行顺序仍然是随机的，不明白.
	 *       sleep 方法改变顺序.
	 * @throws InterruptedException 
	 *                
	 */
	public static void main(String[] args) throws InterruptedException {
		// test1();

		// test2();

//		test3();
		
//		test4();
		
//		test5();
		
//		test6();
		
		test7();

		System.out.println("2.================================");

		System.out.println("3.================================");
		// ThreadGroup group1 = new ThreadGroup("group1");
		// ThreadGroup group2 = new ThreadGroup(group1,"group2");
		// Thread t1 = new Thread(group2,new GenThread2());
		// Thread t2 = new Thread(group2,new GenThread2());
		// Thread t3= new Thread(group2,new GenThread2());
		// group2.getMaxPriority();
		/*
		 * 同步: 如果对oper 方法只加 synchronized ,是有问题的,husband 和 wife 仍是无序的.
		 * 必须再加static方法,让该方法不再重新生成,否则synchronized不起作用了. run 不可以加static,有语法错误:
		 * 因为每个线程都要用自己的run方法.
		 */
		System.out.println("4.===============================");
		// BlankSaving bs = new BlankSaving();
		// Operator o1 = new Operator("husband",bs);
		// Operator o2 = new Operator("wife",bs);
		// Thread t1 = new Thread(o1);
		// Thread t2 = new Thread(o2);
		// t1.start();
		// t2.start();
		// Thread.sleep(500);
		/*
		 * 同步: Object.wait()
		 * 使当前thread进入wait队列,直至Object.notify()来激活,使等待的thread进入Runnable.
		 * 使用这两个方法,线程必须获得锁,否则执行时报错:java.lang.IllegalMonitorStateException
		 * 如果WaitAndNotify2中改用synchronized(this) { this.notify();} 不会notify
		 * thread1,thread1会一直等在那里知道调用Object.notify(). * 必须是同一个Object,本例中都是in.
		 * 其他的object也不行 * 此例中如果t2先执行，那么t1将永远等待.
		 */
		// System.out.println("5.===============================");
		// int[] in = new int[0];
		// WaitAndNotify1 t1 = new WaitAndNotify1(in);
		// WaitAndNotify2 t2 = new WaitAndNotify2(in);
		// System.out.println("[t1]="+((Object)t1).toString()+" [t2]="+((Object)t2).toString());
		// System.out.println("[t1.lockWait]="+t1.lockWait.toString());
		// System.out.println("[t2.lockNotify]="+t2.lockNotify.toString());
		// t1.start();
		// t2.start();
	}

	private static void test1() {
		System.out.println("this is test1() begin...");
		new GenThread1().start(); // 默认名字是Thread-0
		new Thread(new GenThread1()).start(); // 名字是Thread-1
		new Thread(new GenThread2()).start();
		new Thread(new GenThread2()).start();
		/*
		 * run() 是一个普通方法而已， 直接调用run()并不会新起一个线程，而是仍在main中. start()是从操作系统层面新起一个线程.
		 */
		new Thread(new GenThread2()).run();
		System.out.println("this is test1() end...");
	}

	/**
	 * stop() Deprecated, 会立即停止并释放所有的锁，导致线程同步出问题.
	 * todo: 待完成.
	 * @author leslie    
	 * @since 1.0.0
	 */
	private static void test2() {
		Thread t1 = new Thread(new GenThread3());
		Thread t2 = new Thread(new GenThread3());
		t1.start();
		t1.stop();
		t2.start();
	}

	/**
	 * 中断.
	 * interrupt(): 不一定中断正在执行的线程，只是要求线程在合适的时候中断自己. 设置中断状态为true.
	 *    对可取消的阻塞: sleep()  wait()  join()等，线程收到中断信号后会抛出InterruptedException, 同时中断状态置为false.
	 * 
	 * @author leslie    
	 * @since 1.0.0
	 */
	private static void test3() {
		Thread t1 = new Thread("test3") {
			private int id = 0;

			public void run() {
				// 无法中断.
				/*
				while (true) {
					Thread.yield();
					System.out.println(id++);
				}
				*/
				
				// 优雅的方式中断线程. 相比stop()，保证了数据的一致性.
				while(true){
					System.out.println(id++);
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interrupted.");
						break;
					}
				}
				Thread.yield();
				
			}
		};
		t1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();  
		}
		t1.interrupt();
	}
	
	/**
	 * suspend resume: deprecated.  可能造成死锁.
	 * suspend不会释放锁, 直到被resume. 如果其他线程的方法先执行resume, 那么后运行suspend的线程将一直占用这把锁, 无法释放.
	 * 在这里, t1先获得lock,并进入suspend, 此时t2阻塞在lock获取上；   t1 resume后,线程就结束了。t2马上获取到t1释放的lock,而此时main线程仍在执行,
	 * 在t2执行suspend之前就执行了t1的resume操作.  导致t2进入suspend后，无法被main唤醒, 线程main也在等待t2结束， 发生了死锁.
	 * 如果main中没有最后的join, main线程会结束，但是t2永远不会.
	 *
	 * ps -ef|grep java or jps:  找出pid;
	 * jstack pid: 查看thread dump.
	 * 
	 * @author leslie    
	 * @throws InterruptedException 
	 * @since 1.0.0
	 */
	private static void test4() throws InterruptedException{
		// 
		Thread t1 = new GenThread4("t1");
		Thread t2 = new GenThread4("t2");
		
		t1.start();
		Thread.sleep(100);
		t2.start();
		t1.resume();
		// 添加后就没有了, 因为t1释放u后, 让t2有充足的时间进入suspend方法.
		// Thread.sleep(500);
		t2.resume();
		// 线程main 等待t1 t2 结束.
		t1.join();
		t2.join();
	}
	
	/**
	 * yield: Thread 静态方法，释放掉cpu，重新竞争.  基本上不会用到, 一般在debug，test时可能用到.
	 * join: 等待其他线程结束, 一直阻塞.
	 * 源码:
	 *       while (isAlive()) {
                wait(0);
            }
	 *  当一个线程结束后，会调用notifyAll 唤醒等待在当前线程实例上的所有线程，由jvm完成. 不建议使用wait 和 notify notifyAll, 因为jvm会自己调用.
	 *  
	 * @author leslie    
	 * @throws InterruptedException 
	 * @since 1.0.0
	 */
	private static int i = 0;
	private static void test5() throws InterruptedException{
		Thread t1 = new Thread("t1"){
			public void run(){
				for(i = 0; i < 10000000; i++){
					;
				}
			}
		};
		
		t1.start();
		// 没有join, i值会很小.
		t1.join();
		System.out.println("i: " + i);
	}
	
	/**
	 * daemon 守护进程:  后台默默完成一些系统性服务。如垃圾回收线程、JIT线程。
	 * 在java应用内，当所有非守护线程运行结束后，java虚拟机就会退出.
	 * 
	 * @author leslie    
	 * @throws InterruptedException 
	 * @since 1.0.0
	 */
	private static void test6() throws InterruptedException{
		Thread t1 = new Thread("t1"){
			public void run(){
				for(i = 0; i < 10000000; i++){
					;
				}
			}
		};
		// 设置后，控制台没有输出了.
		t1.setDaemon(true);
		t1.start();
		t1.join();
		System.out.println("i: " + i);
	}
	
	/**
	 * 
	 * 线程优先级.
	 * 不能完全保证高优先级比低优先级先执行，但占大部分.
	 * 
	 * @author leslie  
	 * @throws InterruptedException  
	 * @since 1.0.0
	 */
	private static void test7() throws InterruptedException{
		 int i = 0;
		 while(i++ < 10){
			 System.out.println("=========" + i + "========");
			 Thread t1 = new Thread(new GenThread2());
			 Thread t2 = new Thread(new GenThread2());		 
			 t1.setPriority(Thread.MAX_PRIORITY);
			 t1.setName("High");
			 t2.setPriority(Thread.MIN_PRIORITY);
			 t2.setName("Low");
			 t1.start();
			 t2.start();
			 Thread.sleep(500);
		 }
	}
}
