package dp.creationpattern.factory.simplefactory;

public class Strawberry implements IFruit {

	@Override
	public void grow() {
		log("the strawberry is growing...");
	}

	@Override
	public void harvest() {
		log("the strawberry has been harvested...");
	}

	@Override
	public void plant() {
		log("the stawberry has been planted...");
	}
	/*
	 * 辅助方法
	 */
	private void log(String msg){
		System.out.println(msg);
	}

}
