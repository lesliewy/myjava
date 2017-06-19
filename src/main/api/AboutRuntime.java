package api;

import java.io.IOException;

public class AboutRuntime {
	/*
	 * 1,Runtime 构造方法私有化，外部无法实例化.  单态设计模式.
	 * 
	 */
	public static void main(String[] args ){
		try {
			Process p = Runtime.getRuntime().exec("notepad.exe");
			Thread.sleep(5000);
			p.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
