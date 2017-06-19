/**
 * 
 */
package jvm.example1;

import org.junit.Test;

/**
 * Java中一切参数都是按值传递的。
 * 
 * @author leslie
 * 
 */
public class TestB {

	@Test
	public void test1() {
		// 声明的是一个指向Dog对象的指针, 而不是对象.
		Dog myDog = new Dog("Rover");
		foo(myDog);
		System.out.println(myDog);
	}

	/*
	 * java传递的是对象的引用，但这些引用是按值传递的.
	 * 在这里someDog 是指向Dog对象的指针, someDog可以被改变，指向其他对象.
	 */
	private void foo(Dog someDog) {
		someDog.setName("Max");      // 假设地址为42
		someDog = new Dog("Fifi");   // 假设地址为72.
		someDog.setName("Rowlf");
	}

	class Dog {
		private String name;

		Dog(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String toString() {
			return "name: " + name;
		}
	}
}