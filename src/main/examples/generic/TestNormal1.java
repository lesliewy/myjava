/**
 * 
 */
package examples.generic;

/**
 * @author leslie
 *
 */
public class TestNormal1 {
	
	// 普通类中的泛型方法. 要声明type parameter
	public <T> T getFirst(T a){
		return a;
	}
}
