package api.io.filter;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AboutPrintWriter {
	/*
	 * 1,与PrintStream对应，PrintWriter 即使遇到换行符 PrintWriter 也不会自动清空缓冲区.
	 * 2,PrintWriter的println方法,能根据操作系统的不同，生成不同的换行符.Windows: \r\n Linux: \n.
	 * 
	 * 3, Decorator 设计模式： 用一个对象包装另一个对象.
	 */
	
	public static void main(String[] args){
		// 制造一个异常.
		try{
			throw new Exception("test1");
		}catch (Exception e){
			StringWriter sw = new StringWriter();    // 字符串输出流.
			PrintWriter pw = new PrintWriter(sw);   
			e.printStackTrace(pw);
//			System.out.println(sw.toString());
			System.out.println(e.getMessage());
		}
		
	}

}
