
      
/*
 * FileName: TestInteger.java
 * Author:   leslie
 * Date:     Jun 5, 2016 11:12:28 PM
 * Description: //模块目的、功能描述      
 */
   
package api.lang.Integer;

import org.junit.Test;


/**
 *
 * @author leslie
 */

public class TestInteger {

	@Test
	public void test1(){
		int a = 100; // 101
		int b = 50;
		int c = a/50; 
		float d = a/50.0f;    // 结果取较高精度的.
		float e = a/Float.valueOf(b);
		System.out.println("c: " + c + " d: " + d + " d > c: " + (d > c) + " e > c: " + (e > c) + " e == c: " + (e==c));
	}
}

   