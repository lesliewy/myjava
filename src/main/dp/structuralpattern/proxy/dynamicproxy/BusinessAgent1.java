/**
 * Project Name:MyJava  
 * File Name:BusinessAgent.java  
 * Package Name:dp.structuralpattern.proxy.dynamicproxy  
 * Date:Feb 19, 2017 1:32:56 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package dp.structuralpattern.proxy.dynamicproxy;

import dp.structuralpattern.proxy.staticproxy.Sell;
import dp.structuralpattern.proxy.staticproxy.Vendor;

/**
 * 通过静态代理实现我们的需求需要我们在每个方法中都添加相应的逻辑，这里只存在两个方法所以工作量还不算大，
 * 假如Sell接口中包含上百个方法呢？这时候使用静态代理就会编写许多冗余代码。通过使用动态代理，
 * 我们可以做一个“统一指示”，从而对所有代理类的方法进行统一处理，而不用逐一修改每个方法。
 * 
 * date: Feb 19, 2017 1:32:56 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class BusinessAgent1 implements Sell {
	private Vendor mVendor;

	public BusinessAgent1(Vendor vendor) {
		this.mVendor = vendor;
	}

	public void sell() {
		System.out.println("before");
		mVendor.sell();
		System.out.println("after");
	}

	public void ad() {
		System.out.println("before");
		mVendor.ad();
		System.out.println("after");
	}

}
