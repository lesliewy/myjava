/**
 * 
 */
package api.sql.Date;


import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @author leslie
 *
 */
public class TestSqlDate {
	
	@Test
	public void test1(){
		// 构造麻烦， sql date 不支持无参构造，必须是long类型.
		Date utilDate = new Date();
		Date sqlDate = new java.sql.Date(utilDate.getTime());
		Date sqlDate2 = new java.sql.Date(2015, 3, 15);   // 年份默认加上1900， 月份从0开始, 没有时、分、秒.
		System.out.println("sqlDate2: " + sqlDate2);
		
		// sql date 无法获取时、分、秒, 可以获取日、月、年.
//		int hour = sqlDate.getHours();   // exception
		int day = sqlDate.getDay();
		System.out.println("day: " + day);
		
		// 使用joda-time 代替java中的Date, Calendar
	}
}
