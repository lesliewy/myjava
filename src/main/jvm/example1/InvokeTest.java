/**
 * 
 */
package jvm.example1;

/**
 * javap -v jvm/example1/InvokeTest.class
 * invokespecial vs invokevirtual: 
 * invokespecial: 实例的初始化调用，class的private方法调用, super class的方法调用.
 * invokevirtual: 基于Object 类的方法调用，就是最普通的方法调用.
 * 
 * @author leslie
 * 
 */
public class InvokeTest {

	public static void main(String args[]) {

		SubClass b = new SubClass();
		SuperClass supper = b; // 向上转型引用
		System.out.println(supper.method());    // invokevirtual，当前引用的对象是b

		/*
		 * 调用b中的方法. 没有的话找super class中. 有就调用自身的。比如otherMethod没有，就调用super class, 但是otherMethod里调用的method在b中有，仍然调用的是b的method
		 */
		b.subMethod();
		b.otherMethod();
	}

}

class SuperClass {
	public String method() {
		return "from SuperClass...";
	}

	public void otherMethod() {
		System.out.println("In SuperClass otherMethod()...");
		System.out.println("SuperClass otherMethod() calls method(): "
				+ method());
	}
}

class SubClass extends SuperClass {
	public String method() {
		return "from SubClass...";
	}

	public void subMethod() {
		// call SuperClass method()
		System.out.println("SubClass calls super.method(): " + super.method());
	}
}
