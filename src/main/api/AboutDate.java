package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AboutDate {
	public static void main(String[] args) throws ParseException{
		Calendar cal= Calendar.getInstance();
		System.out.println("YEAR:"+cal.get(Calendar.YEAR));
		System.out.println("MONTH:"+cal.get(Calendar.MONTH));
		System.out.println("DAY_OF_MONTH:"+cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("HOUR:"+cal.get(Calendar.HOUR));
		System.out.println("MINUTE:"+cal.get(Calendar.MINUTE));
		System.out.println("SECOND:"+cal.get(Calendar.SECOND));
		System.out.println("DATE:"+cal.get(Calendar.DATE));
		
		// 日期的格式化.
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-DD");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/DD");
		Date date1= sdf1.parse("2011-01-03");
		System.out.println("date:"+sdf2.format(date1));
		
		/*Timer && TimerTask
		 * 
		 * 
		 */
		new Timer().schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					Runtime.getRuntime().exec("calc.exe");
				}catch (Exception e){
					e.printStackTrace();
				}
			    
			}
			
			
		}, 3000);
	}
}
