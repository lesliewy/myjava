package dp.creationpattern.factory.factorymethod;

public class AppleGardener implements FruitGardener {

	@Override
	public IFruit factory() {
		return new Apple();
	}

}
