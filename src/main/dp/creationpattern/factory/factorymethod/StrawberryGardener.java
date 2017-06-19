package dp.creationpattern.factory.factorymethod;

public class StrawberryGardener implements FruitGardener {

	@Override
	public IFruit factory() {
		return new Strawberry();
	}

}
