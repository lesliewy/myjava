package dp.creationpattern.factory.factorymethod;

public class Grape implements IFruit {
	private boolean seedless;
	@Override
	public void grow() {
		log("the grape is growing...");
	}

	@Override
	public void harvest() {
		log("the grape has been harvested...");
	}

	@Override
	public void plant() {
		log("the grape has been planted...");
	}
	/*
	 * 辅助方法
	 */
	private void log(String msg){
		System.out.println(msg);
	}

	public boolean isSeedless() {
		return seedless;
	}

	public void setSeedless(boolean seedless) {
		this.seedless = seedless;
	}
}
