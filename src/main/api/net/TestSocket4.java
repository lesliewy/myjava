package api.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

import junit.framework.TestCase;

public class TestSocket4 extends TestCase {
	/*
	 * 延迟绑定
	 */
	public void test1(){
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket();
			// do something
			SocketAddress socketAddress = new InetSocketAddress(80);
			serverSocket.bind(socketAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void test2(){
		int port =1313;
		ServerSocket serverSocket = null;
		/*
		 * 通常是BindException: 1 port已被占用 ; 2 unix-like 系统下非root用户绑定1-1024端口.
		 */
		try{
			serverSocket = new ServerSocket(port);
			/*
			 * 循环监听客户端连接.
			 */
			while(true){
				Socket socket = null;
				/*
				 * 这个try块出现错误,可能是close()的时候，对方已经关闭。 不管什么原因导致错误，一般不需要终止整个程序，登记下log.
				 */
				try{
					socket = serverSocket.accept();
					OutputStream output = socket.getOutputStream();
					Writer out = new OutputStreamWriter(output);
					Date date = new Date();
					out.write(date.toString()+"\r\n");
					out.flush();
					out.close();
				}catch(IOException e){
					// 登记log
				}finally{
					try{
						if(socket != null){
							socket.close();
					}
					}catch(IOException e){
						//登记log
						
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
	 * 与Socket一样:
	 * isBound()表示曾经是否绑定过端口.
	 * 要判断一个ServerSocket是否正在监听: isBound() && !isClosed()
	 */
	public void test3(){
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(1234);
			System.out.println("before close - isClosed:"+serverSocket.isClosed());
			System.out.println("before close - isBound:"+serverSocket.isBound());
			serverSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("after close - isClosed:"+serverSocket.isClosed());
		System.out.println("after close - isBound:"+serverSocket.isBound());
	}
}
