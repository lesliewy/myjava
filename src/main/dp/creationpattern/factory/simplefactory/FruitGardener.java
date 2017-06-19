package dp.creationpattern.factory.simplefactory;

public class FruitGardener {
	
	/**
	 * 静态工厂方法
	 */
	public static IFruit factory(String which)throws BadFruitException{
		if(which.equalsIgnoreCase("apple")){
			return new Apple();
		}else if(which.equalsIgnoreCase("grape")){
			return new Grape();
		}else if (which.equalsIgnoreCase("strawberry")){
			return new Strawberry();
		}else {
			throw new BadFruitException("Bad Fruit Request.");
		}
	}
}
