package api.sql.Timestamp;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTimestamp {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test1(){
		/*
		 * valueOf(String) 这个适用于jdbc中,同样可以toString
		 */
		Timestamp ts1 = new Timestamp(2012-1900,9,30,15,00,22,0);
		String ts2_str = "2012-10-31 18:32:23";
		Timestamp ts2 = Timestamp.valueOf(ts2_str);
		System.out.println("ts1:"+ts1);
		System.out.println("ts2:"+ts2);
		
		Calendar cal = Calendar.getInstance();
//		cal.set(2012, 11, 1, 1, 1, 1);
		System.out.println(cal.getTime());
		System.out.println(cal.YEAR);
		System.out.println(cal.MONTH);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(cal.getTime()));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(new Date()));
	}

}
