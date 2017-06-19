/**
 * Project Name:MyJava  
 * File Name:Son.java  
 * Package Name:examples.java.dynamicbind  
 * Date:Feb 18, 2017 1:56:55 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package examples.java.dynamicbind;

/**
 * ClassName: Son <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 18, 2017 1:56:55 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Son1 extends Father {
	public String name = "孩子属性";
	
	public void method() {
		System.out.println("子类方法" + this.getClass());
	}
	
	public static void staticMethod(){
		System.out.println("子类方法 staticMethod");
	}
}
