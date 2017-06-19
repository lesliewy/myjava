package api.lang.Double;

import org.junit.Test;


public class TestDouble {
	@Test
	public void test1(){
		/*
		 * Double 好像不会出现BigDecimal的那种问题.  会出现: 下面的d3就有问题.
		 */
		Double d1 = 2.9452;
		Double d2 = new Double(2.9452);
		Double d3 = 1.00d - 9*.10d;
		System.out.println("d1:"+d1);
		System.out.println("d2:"+d2);
		System.out.println("d3:"+d3);
	}
}
