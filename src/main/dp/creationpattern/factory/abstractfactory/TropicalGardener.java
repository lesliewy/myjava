package dp.creationpattern.factory.abstractfactory;

public class TropicalGardener implements Gardener {
	/**
	 * 水果的工厂方法
	 */
	public IFruit createFruit(String name){
		return new TropicalFruit(name);
	}
	/**
	 * 蔬菜的工厂方法
	 */
	public IVeggie createVeggie(String name){
		return new TropicalVeggie(name);
	}
}
