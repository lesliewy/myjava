package api.util.concurrent.pool;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestPool2 {
	private static int produceTaskSleepTime = 2;
	private static int produceTaskMaxNumber = 10;
	public static void main(String[] args){
		// 构造一个线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,4,3,TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(3),new ThreadPoolExecutor.DiscardOldestPolicy());
		for (int i=1;i<produceTaskMaxNumber;i++){
			try{
				// 产生任务,加入线程池.
				String task = "task@" + i;
				System.out.println("put "+task);
				threadPool.execute(new ThreadPoolTask(task));
				// 便于观察，等待一段时间
				Thread.sleep(produceTaskSleepTime);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}


}
/*
 * 线程池执行任务.
 */
class ThreadPoolTask implements Runnable,Serializable{
	private static int consumeTaskSleepTime = 2000;
	// 保存任务需要的数据.
	private Object threadPoolTaskData;
	public ThreadPoolTask(String task){
		this.threadPoolTaskData = task;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.out.println("Start..."+threadPoolTaskData);
		try{
			Thread.sleep(consumeTaskSleepTime);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Object getTask(){
		return this.threadPoolTaskData;
	}
}
