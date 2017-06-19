package dp.behavioralpattern.strategy.sketch;

public class Context {
	private Strategy strategy;
	/**
	 * 策略方法
	 */
	public void contextInterface(){
		strategy.strategyInterface();
	}
}
