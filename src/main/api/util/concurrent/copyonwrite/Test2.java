/**
 * Project Name:MyJava  
 * File Name:Test2.java  
 * Package Name:api.util.concurrent.copyonwrite  
 * Date:Feb 18, 2017 1:14:09 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.copyonwrite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * hashcode变化了多次，说明了list已经不是原来的list对象了。这说明了CopyOnWriteArrayList类的add函数在执行的时候确实是修改了list的数组对象。
 * date: Feb 18, 2017 1:14:09 PM <br/>  
 *  
 * @author leslie  
 * @version
 * @since version 1.0  
 */
public class Test2 {

	public static void main(String[] args) throws InterruptedException {
		List<String> a = new ArrayList<String>();
		a.add("a");
		a.add("b");
		a.add("c");
		final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(
				a);
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
			System.out.println(list.hashCode());
			System.out.println(s);
		}
	}
}
