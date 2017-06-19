package Reflection;

import java.lang.reflect.Method;

public class InvokeTester {
	/*
	 * 1, 获取class对象方法：
	 *   a,Class<?> classType = Class.forName(args[0]); 获取Class对象.
	 *   b,Class<?> classType = InvokeTester.class;
	 *     Object invokerTester = classType.newInstance();
	 * 
	 */
	public int add(int param1,int param2){
		return param1+param2;
	}
	public String echo(String message){
		return "hello:"+message;
	}
	public static void main(String[] args)throws Exception {
//		InvokeTester test = new InvokeTester();
//		System.out.println(test.add(1, 2));
//		System.out.println(test.echo("tom"));
		// 获取 class 对象.
		Class<?> classType = InvokeTester.class;
		Object invokeTester = classType.newInstance();
		// 获取 Method 对象.   需指定 方法名、方法参数的class数组(因为存在方法的重载)
		Method addMethod = classType.getMethod("add", new Class[]{int.class,int.class});
		// 调用 add 方法.
		Object result = addMethod.invoke(invokeTester, new Object[]{1,2});
		
		System.out.println((Integer)result);
		System.out.println("=======================");
		
		Method  echoMethod = classType.getMethod("echo", new Class[]{String.class});
		Object result2 = echoMethod.invoke(invokeTester, new Object[]{"tom"});
		System.out.println((String)result2);
		
		
	}
	

}
