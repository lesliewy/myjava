/**
 * Project Name:MyJava  
 * File Name:GenThread3.java  
 * Package Name:api.lang.thread.example1  
 * Date:Feb 16, 2017 12:03:21 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.lang.thread.example1;

/**
 * date: Feb 16, 2017 12:03:21 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class GenThread3 extends Thread{
	private Apple apple = new Apple();
	public void run(){
		apple.setId(2);
		apple.setName("apple1");
		System.out.println(apple);
	}
}
