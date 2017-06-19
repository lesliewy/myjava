/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.util.concurrent.atomic  
 * Date:Feb 19, 2017 2:21:58 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

/**
 * date: Feb 19, 2017 2:21:58 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {

	@Test
	public void test1() {
		AtomicInteger ai = new AtomicInteger(1);
		System.out.println(ai.getAndIncrement());
		System.out.println(ai.get());
	}

	/**
	 * 原子更新数组类
		通过原子的方式更新数组里的某个元素，Atomic包提供了以下三个类：
		AtomicIntegerArray：原子更新整型数组里的元素。
		AtomicLongArray：原子更新长整型数组里的元素。
		AtomicReferenceArray：原子更新引用类型数组里的元素。
		
		AtomicIntegerArray类主要是提供原子的方式更新数组里的整型，其常用方法如下
			int addAndGet(int i, int delta)：以原子方式将输入值与数组中索引i的元素相加。
			boolean compareAndSet(int i, int expect, int update)：如果当前值等于预期值，则以原子方式将数组位置i的元素设置成update值。
	 * AtomicIntegerArray类需要注意的是，数组value通过构造方法传递进去，然后AtomicIntegerArray会将当前数组
	 * 复制一份，所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响到传入的数组。
	 * 
	 * @author leslie    
	 * @since 1.0.0
	 */
	@Test
	public void test2() {
		int[] value = new int[] { 1, 2 };
		AtomicIntegerArray ai = new AtomicIntegerArray(value);
		ai.getAndSet(0, 3);
		System.out.println(ai.get(0));
		System.out.println(value[0]);

	}

	/**
	 * 原子更新引用类型
		原子更新基本类型的AtomicInteger，只能更新一个变量，如果要原子的更新多个变量，就需要使用这个原子更新引用
		类型提供的类。Atomic包提供了以下三个类：
		AtomicReference：原子更新引用类型。
		AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
		AtomicMarkableReference：原子更新带有标记位的引用类型。可以原子的更新一个布尔类型的标记位和引用类型。
		构造方法是AtomicMarkableReference(V initialRef, boolean initialMark)
	 *  
	 * @author leslie    
	 * @since 1.0.0
	 */
	@Test
	public void test3() {
		User user = new User("conan", 15);
		AtomicReference<User> atomicUserRef = new AtomicReference<User>();
		atomicUserRef.set(user);
		User updateUser = new User("Shinichi", 17);
		atomicUserRef.compareAndSet(user, updateUser);
		System.out.println(atomicUserRef.get().getName());
		System.out.println(atomicUserRef.get().getOld());
	}

	/**
	 * 原子更新字段类
		如果我们只需要某个类里的某个字段，那么就需要使用原子更新字段类，Atomic包提供了以下三个类：
		AtomicIntegerFieldUpdater：原子更新整型的字段的更新器。
		AtomicLongFieldUpdater：原子更新长整型字段的更新器。
		AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，
			可用于原子的更数据和数据的版本号，可以解决使用CAS进行原子更新时，可能出现的ABA问题。
		原子更新字段类都是抽象类，每次使用都时候必须使用静态方法newUpdater创建一个更新器。
		原子更新类的字段的必须使用public volatile修饰符。
	 *  
	 * @author leslie    
	 * @since 1.0.0
	 */
	@Test
	public void test4() {
		AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(
				User.class, "old"); // pulic volatile 型.
		User conan = new User("conan", 10);
		System.out.println(a.getAndIncrement(conan));
		System.out.println(a.get(conan));
	}

	static class User {
		private String name;
		public volatile int old;

		public User(String name, int old) {
			this.name = name;
			this.old = old;
		}

		public String getName() {
			return name;
		}

		public int getOld() {
			return old;
		}
	}

}
