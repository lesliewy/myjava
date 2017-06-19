package api.lang.Long;


import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLong {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test1(){
		/*
		 * 注意优先级,int diff = (int)((ts2.getTime() - ts1.getTime())/1000);  由于加了(),除以1000后再强制转为int.
		 * int diff = (int)(ts2.getTime() - ts1.getTime())/1000; :   相减后就会强制转为int,容易超出int的范围.
		 */
		System.out.println("Integer MAX_VALUE:"+Integer.MAX_VALUE);
		System.out.println("Integer MIN_VALUE:"+Integer.MIN_VALUE);
		Timestamp ts1 = new Timestamp(2012-1900,9,30,15,20,0,0);
		Timestamp ts2 = new Timestamp(2012-1900,10,30,15,20,0,0);
		int diff = (int)(ts2.getTime() - ts1.getTime())/1000;
		System.out.println("ts1:"+ts1);
		System.out.println("ts2:"+ts2);
		System.out.println("diff with Integer:"+diff);
		System.out.println("diff with Long:"+(ts2.getTime()-ts1.getTime()));
		System.out.println("Max days diff millsec with Ingeter:"+Integer.MAX_VALUE/1000/86400);
	}
}
