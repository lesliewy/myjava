/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:examples.java.dynamicbind  
 * Date:Feb 18, 2017 1:57:49 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package examples.java.dynamicbind;

import org.junit.Test;

/**
 * JAVA虚拟机调用一个类方法(static)时，它会基于对象引用的类型(通常在编译时可知, 这里是Father instance 中的Father)来选择所调用的方法;
 *     虚拟机调用一个实例方法时，它会基于对象实际的类型(只能在运行时得知 这里是Father instance = new Son1()中的Son1)来选择所调用的方法;
 *     这就是动态绑定，是多态的一种。
 * 
 * 过程: 
 * 1. 首先，编译器根据对象的声明类型和方法名，搜索相应类(Son)及其父类(Father)的“方法表”，找出所有访问属性为public的method方法。
　　　 可能存在多个方法名为method的方法，只是参数类型或数量不同。
　　 2. 然后，根据方法的“签名”找出完全匹配的方法。
　　　  方法的名称和参数列表称为方法的签名。
　　　  在Java SE 5.0 以前的版本中，覆盖父类的方法时，要求返回类型必须是一样的。现在子类覆盖父类的方法时，允许其返回类型定义为原始类型的子类型。
	  public Father getFather(){...}  //父类中的方法
	  public Son getFather(){...}     //子类覆盖父类中的getFather()方法
　　 3. 如果是private、static、final 方法或者是构造器，则编译器明确地知道要调用哪儿个方法，这种调用方式成为“静态调用”。
　　 4. 调用方法。
　　　 如果子类Son中定义了 method() 的方法，则直接调用子类中的相应方法；如果子类Son中没有定义相应的方法，则到其父类中寻找method()方法。    
 *     
 * date: Feb 18, 2017 1:57:49 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {
	/**
	 * 子类重写父类中的方法，调用子类中的方法
	 * 即使通过实例调用类方法，也还是基于对象引用的类型来调用.
	 * @author leslie    
	 * @since 1.0.0
	 */
	@Test
	public void test1() {
		Father instance = new Son1();
		instance.method();
		instance.staticMethod();
		Son1.staticMethod();
		Father.staticMethod();
	}
	
	/**
	 * 
	 *  子类没有重写父类中的方法，所以到父类中寻找相应的方法
	 * @author leslie    
	 * @since 1.0.0
	 */
	@Test
	public void test2() {
		Father instance = new Son2();
		instance.method();
		
		instance.staticMethod();
		Son2.staticMethod();
		Father.staticMethod();
	}
	
	/**
	 * 动态绑定只是针对对象的方法，对于属性无效。因为属性不能被重写。
	 * @author leslie    
	 * @since 1.0.0
	 */
	@Test
	public void test3() {
		Father instance = new Son1();
		System.out.println(instance.name);
	}
}
