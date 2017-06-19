package api.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import junit.framework.TestCase;

/*
 * 服务端
 * 1. ServerSocket中的accept()是阻塞方法，如果没有连接请求，程序会停在这里.
 */
public class TestSocket2 extends TestCase{
	public void test1() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		OutputStream out = null;
		InputStream in = null;
		int port =10000;
		try {
			//监听.
		    serverSocket = new ServerSocket(port);
		    //获取socket,释放对port的占用.
		    socket = serverSocket.accept();
		    //接收内容.
		    in = socket.getInputStream();
		    byte[] b = new byte[1024];
		    int n = in.read(b);
		    System.out.println("服务端接收内容是:"+new String(b,0,n));
		    // 发送内容.
		    out = socket.getOutputStream();
		    out.write("hello from server".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try{
				in.close();
				out.close();
				socket.close();
				serverSocket.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void test2(){
		ServerSocket server = null;
		int port =12345;
		try{
			server = new ServerSocket(port);
			System.out.println("listening on port :"+port);
			OutputStream out = null;
			while(true){
				Socket socket = server.accept();
				out = socket.getOutputStream();
				out.write("abcdefg".getBytes());
				out.close();
				socket.close();
			}
		}catch(IOException e){
			System.err.println("Server port " + port +" bind error.");
		}finally{
			try{
				if(server != null){
					server.close();
				}
			}catch(IOException e){
				System.err.println("error when server socket closes.");
			}
		}
	}
}
