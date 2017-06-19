/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.util.concurrent.copyonwrite  
 * Date:Feb 18, 2017 1:05:32 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.copyonwrite;

import java.util.ArrayList;
import java.util.List;

/**
 * 会抛出: Exception in thread "main" java.util.ConcurrentModificationException
 * Java中，List在遍历的时候，不可以同时被修改。
 * 
 * date: Feb 18, 2017 1:05:32 PM <br/>  
 * 
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		List<String> a = new ArrayList<String>();
		a.add("a");
		a.add("b");
		a.add("c");
		final ArrayList<String> list = new ArrayList<String>(a);
		Thread t = new Thread(new Runnable() {
			int count = -1;

			@Override
			public void run() {
				while (true) {
					list.add(count++ + "");
				}
			}
		});
		t.setDaemon(true);
		t.start();
		Thread.sleep(3);
		for (String s : list) {
			System.out.println(s);
		}
	}
}