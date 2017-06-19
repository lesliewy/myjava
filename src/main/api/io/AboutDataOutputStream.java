package api.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AboutDataOutputStream {
	/*
	 * 1, DataOutputStream 提供了往各种输出流对象中写入各种类型的数据.
	 * 2, DataOutputStream 没有对应到具体的流设备,一定要给它传递一个对应具体流设备的输出流对象,完成类似DataOutputStream功能的类就是一个包装类,也叫过滤流类或处理流类.
	 * 3, Public DataOutputStream(OutputStream out);
	 * 
	 */
	public static void main(String[] args) throws Exception{
		FileOutputStream fos = new FileOutputStream("5.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeUTF("ab中国");
		dos.writeBytes("ab中国");
		dos.writeChars("ab中国");
		dos.close();
		
		FileInputStream fis = new FileInputStream("5.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		System.out.println("1========="+dis.readUTF());            // 可以读取 writeUTF 的内容.
		byte[] buf = new byte[1024];
		int len = dis.read(buf);
		System.out.println("2========="+new String(buf,0,len));
	}
	
}
