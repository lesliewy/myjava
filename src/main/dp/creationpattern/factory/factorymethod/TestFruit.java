package dp.creationpattern.factory.factorymethod;

/**
 * 工厂模式无法解决产品族和产品等级结构的问题.
 *  
 * @author leslie  
 * @version   
 * @since version 1.0
 */
public class TestFruit {

	public static void main(String[] args) {
		FruitGardener appleGardener = new AppleGardener();
		FruitGardener grapeGardener = new GrapeGardener();
		IFruit apple = appleGardener.factory();
		IFruit grape = grapeGardener.factory();
		
		apple.grow();
		grape.plant();
	}

}
