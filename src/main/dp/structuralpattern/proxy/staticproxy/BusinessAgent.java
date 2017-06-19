/**
 * Project Name:MyJava  
 * File Name:BusinessAgent.java  
 * Package Name:dp.structuralpattern.proxy.staticproxy  
 * Date:Feb 19, 2017 1:29:58 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package dp.structuralpattern.proxy.staticproxy;

/**
 * 静态代理可以通过聚合来实现，让代理类持有一个委托类的引用即可
 * 可以实现客户与委托类间的解耦，在不修改委托类代码的情况下能够做一些额外的处理。
 * 
 * 静态代理的局限在于运行前必须编写好代理类
 * 
 * date: Feb 19, 2017 1:29:58 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class BusinessAgent implements Sell {
	private Vendor mVendor;

	public BusinessAgent(Vendor vendor) {
		mVendor = vendor;
	}

	public void sell() {
		mVendor.sell();
	}

	public void sell1() {
		if (isCollegeStudent()) {
			mVendor.sell();
		}
	}

	public void ad() {
		mVendor.ad();
	}

	private boolean isCollegeStudent() {
		return true;
	}
}
