package dp.creationpattern.factory.factorymethod;

public class GrapeGardener implements FruitGardener {

	@Override
	public IFruit factory() {
		return new Grape();
	}

}
