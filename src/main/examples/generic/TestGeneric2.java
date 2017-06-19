/**
 * 
 */
package examples.generic;

import java.util.Date;

import org.junit.Test;

/**
 * 
 * 
 * @author leslie
 *
 */
public class TestGeneric2 {

	@Test
	public void test1(){
		// 不能用基本类型实例化type parameter.
//		Pair<int, int> pair = new Pair<int, int>();
		
		
	}
	
	/*
	 * 参数化类型的数组是不合法的： TestGeneric1<String, Integer, String>[] testGeneric1Arr = new TestGeneric1<String, Integer, String>[10];
	 * 因为可以将它向上转型为Object[]， 即 TestGeneric1<Object, Object, Object>[], 这样就不是编码者的本意了，导致难以察觉的错误.
	 */
	@Test
	public void test2(){
		// 编译通过，但是执行时报错.
		String[] strs = new String[10];
		Object[] objs = strs;
		objs[0] = new Date();  // 抛出 java.lang.ArrayStoreException
		
		TestGeneric1<String, Integer, String> testGeneric1 = new TestGeneric1<String, Integer, String>();
		// 不合法的数组声明.
//		TestGeneric1<String, Integer, String>[] testGeneric1Arr = new TestGeneric1<String, Integer, String>[10];
		// 合法
		TestGeneric1<String, Integer, String>[] testGeneric1Arr = (TestGeneric1<String, Integer, String>[])new TestGeneric1[10];
	}
	
	/*
	 * 不能实例化类型变量: new T();  new T[]; T.class
	 * 因为存在类型擦除，会变成 new Object(), 这通常都不是本意.
	 */
	public <T> void test3(T t){
//		new T();
//		T[] arr = new T[];
		// 可以修改为
		T[] arr = (T[]) new Object[10];
	}
	
	/*
	 * generic class 的静态上下文中不能存在type parameter, 普通类可以使用。例如下面这个
	 * 因为假如可以，内存中可能存在 TestGeneric1<String, Integer, String> 同时，也存在 TestGeneric1<String, Integer, Integer>,
	 * 而静态方法和静态变量是所有的类实例共享的, 这样就会出现矛盾.
	 */
	public static <T> T test4(){
		return null;
	}
	
	/*
	 *  generic class 不能 extends Throwable, 因此无法抛出或捕获泛型类实例.
	 *  但可以在异常声明中使用type parameter
	 */
	public static <T extends Throwable> void test5(T t) throws T{
		
	}
	
	/*
	 * 通配符
	 * 1, Student 是 People 的子类, Pair<Student> 不是 Pair<People> 的子类.  type parameter 间不存在继承, 不存在 is-a关系
	 * 2, Pair<T> 与它的原始类型Pair之间存在 is-a 关系, 在任何情况下 Pair<T> 都可以转换成 Pair
	 * 
	 * <? extends Boundingtype> 叫做通配符的子类型限定.  调用setter方法是非法的, getter方法是合法的.
	 * <? super Boundingtype> 叫做通配符的超类型限定     调用getter方法是非法的, setter方法是合法的.
	 * <?>  无限定  调用setter getter 都是非法的.
	 * 
	 */
	public void test6(){
		
	}
}

class Pair<T>{
	T first;
	
	public static void printName1(Pair<People> pair){
		
	}
	
	public static void printName2(Pair<Student> pair){
		
	}
	
	// 不限定T的类型
	public void printName3(T t){
		
	}
	
	// 子类型限定.  必须是People的子类型.
	public static void printName(Pair<? extends People> pair){
		
	}
	
	void setFirst(){
		
	}
	
	void getFirst(){
		
	}
}

class People{
	String name;
}

class Student extends People{
	
}