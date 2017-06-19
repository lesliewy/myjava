/**
 * Project Name:MyJava  
 * File Name:GenThread4.java  
 * Package Name:api.lang.thread.example1  
 * Date:Feb 16, 2017 12:43:56 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.lang.thread.example1;

/**
 * ClassName: GenThread4 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 16, 2017 12:43:56 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class GenThread4 extends Thread{
	private static Object u = new Object();
	
	public GenThread4(String name){
		setName(name);
	}
	
	@Override
	public void run(){
		synchronized(u){
			System.out.println("in " + getName());
			Thread.currentThread().suspend();
		}
	}
}
