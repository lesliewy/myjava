package dp.creationpattern.factory.abstractfactory;

/**
 * 一个工厂生产多个产品，他们是一个产品族.
 * 
 * JDK:
 * javax.xml.parsers.DocumentBuilderFactory#newInstance()
 * 
 * @author leslie  
 * @version   
 * @since version 1.0
 */
public class TestFruit {
	
	public static void main(String[] args){
		TropicalGardener tropicalGardener = new TropicalGardener();
		NorthernGardener northernGardener = new NorthernGardener();
		
		TropicalFruit tropicalFruit = (TropicalFruit) tropicalGardener.createFruit("apple");
		NorthernFruit northerFruit=(NorthernFruit)northernGardener.createFruit("apple");
		tropicalFruit.setName("apple");
	}
}
