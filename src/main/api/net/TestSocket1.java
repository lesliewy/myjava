package api.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 * 客户端
 * 1. IO 中的read()是阻塞方法，没有数据时，程序会停在这里.
 */
public class TestSocket1 {
	public static void main(String[] args){
		Socket socket = null;
		OutputStream out = null;
		InputStream in = null;
		String host="127.0.0.1";
		int port = 10000;
		String data="hello";
		try{
		    socket = new Socket(host,port);
			// 发送
			out = socket.getOutputStream();
			out.write(data.getBytes());
			// 接收
			byte[] b = new byte[10];      // 只读取10个字节.
			in = socket.getInputStream();
			int n=0;
			// 循环读取数据，直至读取完成.
			while((n=in.read(b))!=-1){
				System.out.println("客户端接收内容是: "+new String(b,0,n));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				in.close();
				out.close();
				socket.close();
			 }catch(Exception e2){
				 e2.printStackTrace();
			 }
		}
	}
	/*
	 * 复用socket.
	 */
//	public static void main(String[] args) {
//		Socket socket = null;
//		OutputStream out = null;
//		InputStream in = null;
//		String host="127.0.0.1";
//		int port = 10000;
//		try{
//			socket = new Socket(host,port);
//			//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
