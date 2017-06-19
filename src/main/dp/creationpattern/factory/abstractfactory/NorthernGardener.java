package dp.creationpattern.factory.abstractfactory;

public class NorthernGardener implements Gardener {
	/**
	 * 水果的工厂方法
	 */
	public IFruit createFruit(String name){
		return new NorthernFruit(name);
	}
	/**
	 * 蔬菜的工厂方法
	 */
	public IVeggie createVeggie(String name){
		return new NorthernVeggie(name);
	}
}
