package api.util.Calendar;


import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCalendar {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test1(){
		// 使用Calendar也不是太方便，构造一个指定日期的需要先构造当前的.
		Calendar cal = Calendar.getInstance();
		System.out.println("default calendar:"+cal);
		System.out.println("default calendar date:"+cal.getTime()); // 获取 Date
		cal.set(2012, 9, 30, 14, 30, 22);
		System.out.println("after set calendar date:"+cal.getTime());
		
		// 计算后的日期需要再次构造当前的.
//		Calendar after = cal.add(Calendar.YEAR, 2);   // add等方法没有返回值.
		Calendar after = Calendar.getInstance();
		after.add(Calendar.YEAR, 2);
		System.out.println("after: " + after.getTime());
		
		// 对某个日期计算.
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2015, 3, 15, 15, 0, 0);
		cal2.add(Calendar.YEAR, 2);
		System.out.println("cal2: " + cal2.getTime());
	}
	
	@Test
	public void test2(){
	    Calendar cal = Calendar.getInstance();
	    System.out.println("hours: " + cal.get(Calendar.HOUR) + ";HOUR_OF_DAY " + cal.get(Calendar.HOUR_OF_DAY));
	    
	    Timestamp s = new Timestamp(System.currentTimeMillis());
	    System.out.println(s);
	}

}
