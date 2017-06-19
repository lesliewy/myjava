/**
 * Project Name:MyJava  
 * File Name:BusinessProcessorHandler.java  
 * Package Name:api.lang.reflection.proxy  
 * Date:Oct 11, 20139:06:28 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BusinessProcessorHandler implements InvocationHandler {

	private Object target = null;

	BusinessProcessorHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out
				.println("You can do something here before process your business");
		Object result = method.invoke(target, args);
		System.out
				.println("You can do something here after process your business");
		return result;
	}

	/** 
	 * 获取目标对象的代理对象  使用时可以直接getProxy.
	 * @return 代理对象 
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread()
				.getContextClassLoader(), target.getClass().getInterfaces(), this);
	}
}
