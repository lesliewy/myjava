/**
 * 
 */
package examples.generic;

/**
 * 泛型
 * JDK1.5 之前没有泛型, 使用继承实现.  例如ArrayList中, 都是使用的Object对象. 这样必须进行强制转换.
 * type parameter: T(type), E(element), K(key), V(value) 也可以使用其他的.
 * generic class: 带type parameter 的class
 * generic method: 带type parameter 的method, 既可以在generic class 中，也可以在普通类中。 注意在普通类中，要声明type parameter
 * 进一步限定: <T extends BoundingType> BoundingType： class or interface
 * 类型擦除: 把所有type parameter 替换为 BoundingType(未限定就替换为Object).
 * 
 * 泛型和JVM基本上没有关系，和编译器有关系;  在JVM中, generic class 进行了类型擦除;
 * 泛型是通过编译器来实现的: 一是在编译时可以进行检查； 二是编译器为我们进行了强制转换;
 * 
 * @author leslie
 *
 */
public class TestGeneric1<T, E, F> {
	
	private T first;
	
	private E second;
	
	// 静态上下文中不可以使用type parameter.
//	private static F a;
	
	public T getFrist(){
		return first;
	}
	
	public E getSecond(){
		return second;
	}
	
	public void other(F other){

	}
}
