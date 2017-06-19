/**
 * Project Name:MyJava  
 * File Name:DynamicProxy.java  
 * Package Name:dp.structuralpattern.proxy.dynamicproxy  
 * Date:Feb 19, 2017 1:35:11 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package dp.structuralpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import dp.structuralpattern.proxy.staticproxy.Sell;
import dp.structuralpattern.proxy.staticproxy.Vendor;

/**
 * 在使用动态代理时，我们需要定义一个位于代理类与委托类之间的中介类，这个中介类被要求实现InvocationHandler接口
 *
 * 通过聚合方式持有委托类对象引用，把外部对invoke的调用最终都转为对委托类对象的调用。这不就是我们上面介绍的静态代理的一种实现方式吗？
 * 实际上，中介类与委托类构成了静态代理关系，在这个关系中，中介类是代理类，委托类就是委托类；
	代理类与中介类也构成一个静态代理关系，在这个关系中，中介类是委托类，代理类是代理类。也就是说，动态代理关系由两组静态代理关系组成，这就是动态代理的原理。
 * 
 * date: Feb 19, 2017 1:35:11 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class DynamicProxy implements InvocationHandler {
	private Object obj; // obj为委托类对象；

	public DynamicProxy(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before");
		Object result = method.invoke(obj, args);
		System.out.println("after");
		return result;
	}

	public static void main(String[] args) {
		// 创建中介类实例
		DynamicProxy inter = new DynamicProxy(new Vendor());
		// 加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
		// 获取代理类实例sell
		Sell sell = (Sell) (Proxy.newProxyInstance(Sell.class.getClassLoader(),
				new Class[] { Sell.class }, inter));
		// 通过代理类对象调用代理类方法，实际上会转到invoke方法调用
		sell.sell();
		sell.ad();
	}

}
