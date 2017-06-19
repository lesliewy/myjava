/**
 * Project Name:MyJava  
 * File Name:Vendor.java  
 * Package Name:dp.structuralpattern.proxy.staticproxy  
 * Date:Feb 19, 2017 1:29:19 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package dp.structuralpattern.proxy.staticproxy;

/**
 * ClassName: Vendor <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 19, 2017 1:29:19 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Vendor implements Sell {
	public void sell() {
		System.out.println("In sell method");
	}

	public void ad() {
		System.out.println("ad method");
	}
}
