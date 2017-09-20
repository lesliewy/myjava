/**
 * Project Name:MyJava  
 * File Name:Test4.java  
 * Package Name:com.test1  
 * Date:Sep 20, 2017 8:50:09 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package com.test1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * ClassName: Test4 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Sep 20, 2017 8:50:09 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test4 {

	private static int num = 0;
	
	@Test
	public void test1(){
		m1(new OOM()); // 9168
	}
	
	@Test
	public void test2(){
		List<OOM> a = new ArrayList<OOM>();
		int i = 0;
		while(true){
			i++;
			a.add(new OOM());
			if(i % 100 == 0){
				System.out.println(i);
			}
		}
	}
	
	@Test
	public void test3(){
		m2();
	}
	
	@Test
	public void test4(){
		Integer[] arr = new Integer[1000 * 1000 * 100];
		m3(arr);
	}
	
	
	private void m1(OOM o){
		num++;
		System.out.println(num);
		m1(new OOM());
	}
	
	private void m2(){
		List<OOM> a = new ArrayList<OOM>();
		int i = 0;
		while(true){
			i++;
			a.add(new OOM());
			if(i % 100 == 0){
				System.out.println(i);
			}
		}
	}
	
	private void m3(Integer[] arr){
		
	}
	
	
	class OOM{
		Integer[] arr = new Integer[1000 * 1000];
	}
}
