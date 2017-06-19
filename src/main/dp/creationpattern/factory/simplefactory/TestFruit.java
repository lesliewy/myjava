package dp.creationpattern.factory.simplefactory;

/**
 * 不同产品在同一工厂生产，随着产品增加给扩展带来麻烦.
 * 
 * JDK中: 
 * java.util.Calendar#getInstance()  
 * java.text.NumberFormat#getInstance()
 *  
 * @author leslie  
 * @version   
 * @since version 1.0
 */
public class TestFruit {

	public static void main(String[] args) {
		try{
			IFruit apple=FruitGardener.factory("apple");
			IFruit grape=FruitGardener.factory("grape");
			IFruit strawberry=FruitGardener.factory("strawberry");
			apple.grow();
			grape.plant();
			strawberry.harvest();
		}catch(BadFruitException e){
			e.printStackTrace();
		}
	}

}
