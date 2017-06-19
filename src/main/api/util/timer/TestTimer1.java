/**
 * 
 */
package api.util.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;

/**
 * 简单的定时可以用Timer, 切记: 同一个timer内只有一个线程执行所有的task
 * 
 * @author leslie
 */
public class TestTimer1 {

	long start = System.currentTimeMillis();
	
	public static void main(String[] args) {
		System.out.println("timer begin: " + new Date());
		TestTimer1 testTimer1 = new TestTimer1();
		// 指定delay 后执行一次某个task.
//		testTimer1.schedualAfterDelay();
		
		// 指定时间执行一次task.
//		testTimer1.schedualAtSpeitime();
		
		// 指定delay后每隔period执行一次task.
//		testTimer1.schedualAfterDelayPeriod();
		
		/*
		 * schedule() 与 scheduleAtFixedRate()的区别: 当间隔执行时，前者实际执行时间 = 实际完成时间 + period;  而后者，不考虑task的执行时间长短，始终是按计划执行.
		 */
		
		/*
		 * timer 的缺陷1: 每个timer内只有一个线程执行所有的task， 所以,如果有某一个task比较复杂，用时较长，会导致后面的task的执行不在想要的执行时间. 
		 */
		/*
		Timer timer = new Timer();
		testTimer1.schedualDefect1a(timer);
		testTimer1.schedualDefect1b(timer);
		*/
		
		/*
		 * timer 的缺陷2: 由于每个timer内只有一个线程执行所有的task， 所以如果某个task抛出runtimer异常，后面其他的task不会被执行.
		 */
		
		/* 
		 * 用ScheduledExecutorService替代Timer，
		 */
		ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(2);
		testTimer1.schedualExecutor1a(scheduledService);
		testTimer1.schedualExecutor1b(scheduledService);
		
	}

	// timer 不能用JUnit来测试.
	/*
	 * @Test public void test1(){ System.out.println("timer begin..."); Timer
	 * timer = new Timer(); timer.schedule(new TimerTaskTest1(), 3 * 1000); }
	 */
	public void schedualAfterDelay(){
		Timer timer = new Timer();
		timer.schedule(new TimerTaskTest1(), 3 * 1000);
	}
	
	public void schedualAtSpeitime(){
		Timer timer = new Timer();
		timer.schedule(new TimerTaskTest2(), getTime());
	}
	
	public void schedualAfterDelayPeriod(){
		Timer timer = new Timer();
		timer.schedule(new TimerTaskTest3(), 3 * 1000, 5 * 1000);
	}
	
	public void schedualDefect1a(Timer timer){
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("timer 的缺陷1, long time task, elapsed time: " + (System.currentTimeMillis() - start)/1000 + " s.");
			}
		}, 3 * 1000);
	}
	
	public void schedualDefect1b(Timer timer){
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("timer 的缺陷1, short time task, elapsed time: " + (System.currentTimeMillis() - start)/1000 + " s.");
			}
		}, 4 * 1000);
	}
	
	public void schedualExecutor1a(ScheduledExecutorService scheduledService){
		scheduledService.schedule(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("用ScheduledExecutorService替代Timer, long time task, elapsed time: " + (System.currentTimeMillis() - start)/1000 + " s.");
			}
		}, 3 * 1000, TimeUnit.MILLISECONDS);
	}
	
	public void schedualExecutor1b(ScheduledExecutorService scheduledService){
		scheduledService.schedule(new Runnable(){
			@Override
			public void run() {
				System.out.println("用ScheduledExecutorService替代Timer, short time task, elapsed time: " + (System.currentTimeMillis() - start)/1000 + " s.");
			}
		}, 3 * 1000, TimeUnit.MILLISECONDS);
	}
	
	private Date getTime(){
		DateTime dt = new DateTime();
		return dt.plusMillis(5*1000).toDate();
	}
}

class TimerTaskTest1 extends TimerTask {
	@Override
	public void run() {
		System.out.println("指定delay 后执行一次某个task.");
	}
}

class TimerTaskTest2 extends TimerTask {
	@Override
	public void run() {
		System.out.println("指定时间执行线程任务.");
	}
}

class TimerTaskTest3 extends TimerTask {
	@Override
	public void run() {
		System.out.println("指定delay后按间隔period执行task: " + new Date(this.scheduledExecutionTime()));
	}
}
