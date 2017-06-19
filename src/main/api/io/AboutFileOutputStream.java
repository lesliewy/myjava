package api.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AboutFileOutputStream {
	/*
	 * 1,FileOutputStream 用来创建磁盘文件的输出流对象.  
	 * 2,FileOutputStream 创建实例对象时，指定的文件可以不存在;如果存在，这个文件中的内容将会被覆盖. 但是不能指定一个已经被其他程序打开的文件.
	 * 
	 * 3,
	 * 
	 */
	public static void main(String[] args){
		FileOutputStream out;
		try {
			out = new FileOutputStream("3.txt");
			out.write("hello world!".getBytes());
			out.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] buff = new byte[1024];
		int len=0;
		File f = new File("3.txt");
		FileInputStream in;
		try {
			in = new FileInputStream(f);
			len = in.read(buff);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new String(buff,0,len));
	}

}
