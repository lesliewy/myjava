package Reflection;

import java.lang.reflect.Method;

public class DumpMethods {

	/**
	 * @param args
	 */
	/*
	 * 1,Java中，无论生成某个类的多少个对象，这些对象都会对应于同一个Class对象.
	 * 
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		// 获得class对象. Class<?> 泛型 即 Extend Object
		Class<?> classType = Class.forName(args[0]);
		
		Method[] methods = classType.getDeclaredMethods();
		
		for(Method method:methods){
			System.out.println(method);
		}

	}

}
