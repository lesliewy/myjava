package dp.structuralpattern.facade.example;

public class Alarm {
	//启动警报器
	public void activate(){
		System.out.println("Activating the alarm...");
	}
	//关闭警报器
	public void deactivate(){
		System.out.println("Deactivating the alarm...");
	}
	//拉响警报器
	public void ring(){
		System.out.println("Ring the alarm...");
	}
	//停掉警报器
	public void stopRing(){
		System.out.println("Stopping the alarm...");
	}
}
