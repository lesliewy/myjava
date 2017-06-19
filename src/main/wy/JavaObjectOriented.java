/*
 * FileName: JavaObjectOriented.java
 * Author:   leslie
 * Date:     Jul 15, 2016 7:08:55 PM
 * Description: //模块目的、功能描述      
 */
   
package wy;
import org.junit.Test;


/**
 *
 * @author leslie
 */
public class JavaObjectOriented {
	
	/**
	 * static 方法和属性也属于类对象: Employee.class
	 * 所有Employee实例共用同一个Employee.class
	 */
	@Test
	@SuppressWarnings("static-access")
	public void test1(){
		Employee emp1 = new Employee();
		emp1.sayAge();
		// 通过emp1实例修改static属性.
		emp1.setAge(13);
		
		// 通过emp2访问static属性.
		Employee emp2 = new Employee();
		emp2.sayAge();
	}
	
	/**
	 * 原始类型的对象问题.
	 * 包装类;
	 * JVM内部,原始类型也是对象: 参看 java.lang.Class代码, Integer.TYPE 代码;
	 * 
	 * 使用原始类型的对象更快：JVM内部为原始类型创建的对象是很轻量级的，例如不能访问其方法.
	 */
	@Test
	public void test2(){
		long begin = System.currentTimeMillis();
//		Long sum = 0L;  // 24s
		long sum = 0L;  // 2s
		for(long i = 0; i <= Integer.MAX_VALUE; i++){
			sum += i;
		}
		System.out.println(sum);
		System.out.println("elapsed time: " + (System.currentTimeMillis() - begin)/1000 + "s");
	}
	
	@Test
	public void test3(){
		// new 的是不同的对象.
		System.out.println("new Integer(3) == new Integer(3): " + (new Integer(3) == new Integer(3)));
		System.out.println("new Integer(200) == new Integer(200): " + (new Integer(200) == new Integer(200)));
		// Integer.valueOf() 返回的是对象. [-128, 127] 之间的被缓存了，是同一个对象
		System.out.println("Integer.valueOf(3) == Integer.valueOf(3): " + (Integer.valueOf(3) == Integer.valueOf(3)));
		System.out.println("Integer.valueOf(200) == Integer.valueOf(200): " + (Integer.valueOf(200) == Integer.valueOf(200)));
		
		// 数值比较，不存在对象的问题.
		int a = 3;
		int b = 3;
		System.out.println("a = 3, b = 3, a == b: " + (a == b));
		
		a = 200;
		b = 200;
		System.out.println("a = 200, b = 200, a == b: " + (a == b));
		
		System.out.println("3 == 3: " + (3 == 3));
		System.out.println("200 == 200: " + (200 == 200));
	}
	
	
}

   