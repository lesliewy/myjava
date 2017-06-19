package dp.creationpattern.factory.factorymethod;

public class Apple implements IFruit {
	private int treeAge;
	@Override
	public void grow() {
		log("the apple is growing...");
	}

	@Override
	public void harvest() {
		log("the apple has been harvested...");
	}

	@Override
	public void plant() {
		log("the apple has been planted...");
	}
	
	// 辅助方法
	private void log(String msg){
		System.out.println(msg);
	}

	public int getTreeAge() {
		return treeAge;
	}

	public void setTreeAge(int treeAge) {
		this.treeAge = treeAge;
	}
}
