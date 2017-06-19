/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:examples.java.transient1  
 * Date:Feb 20, 2017 11:55:41 AM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package examples.java.transient1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * java 的transient关键字为我们提供了便利，你只需要实现Serilizable接口，将不需要序列化的属性前添加关键字transient，
 * 序列化对象的时候，这个属性就不会序列化到指定的目的地中。
 * 
 * 1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
	2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
	3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
 * date: Feb 20, 2017 11:55:41 AM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {
	@Test
	public void test1(){
		User user = new User();
		user.setUserName("leslie");
		user.setPassword("123456");
		System.out.println("before Serializable: ");
		System.out.println("userName: " + user.getUserName());
		System.out.println("password: " + user.getPassword());
		System.out.println("address: " + User.getAddress());
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("a.txt"));
			os.writeObject(user);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();  
		}
		
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("a.txt"));
			user = (User)is.readObject();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("after Serializable: ");
		System.out.println("userName: " + user.getUserName());
		System.out.println("password: " + user.getPassword());
		System.out.println("address: " + user.getAddress());
	}
	
	/**
	 * 第三点可能有些人很迷惑，因为发现在User类中的username字段前加上static关键字后，程序运行结果依然不变，即static类型的username也读出来为“Alexia”了，
	 * 这不与第三点说的矛盾吗？实际上是这样的：第三点确实没错（一个静态变量不管是否被transient修饰，均不能被序列化），
	 * 反序列化后类中static型变量username的值为当前JVM中对应static变量的值，这个值是JVM中的不是反序列化得出的
	 * @author leslie    
	 * @since 1.0.0
	 */
	@Test
	public void test2(){
		User user = new User();
		user.setUserName("leslie");
		user.setPassword("123456");
		System.out.println("before Serializable: ");
		System.out.println("userName: " + user.getUserName());
		System.out.println("password: " + user.getPassword());
		System.out.println("address: " + User.getAddress());
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("a.txt"));
			os.writeObject(user);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			user.setAddress("BeiJing");
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("a.txt"));
			user = (User)is.readObject();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("after Serializable: ");
		System.out.println("userName: " + user.getUserName());
		System.out.println("password: " + user.getPassword());
		System.out.println("address: " + user.getAddress());
	}
	
	/**
	 * Java中，对象的序列化可以通过实现两种接口来实现，若实现的是Serializable接口，则所有的序列化将会自动进行，
	 * 若实现的是Externalizable接口，则没有任何东西可以自动序列化，需要在writeExternal方法中进行手工指定所要序列化的变量，
	 * 这与是否被transient修饰无关。因此第二个例子输出的是变量content初始化的内容，而不是null。
	 * @author leslie
	 * @since 1.0.0
	 */
	@Test
	public void test3(){
		ExternalUser extUser = new ExternalUser();
		try {
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("a.txt"));
			out.writeObject(extUser);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();  
		}
		
		try {
			ObjectInput in = new ObjectInputStream(new FileInputStream("a.txt"));
			ExternalUser newExtUser = (ExternalUser)in.readObject();
			System.out.println(newExtUser.getContent());
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();  
		}
	}
}
