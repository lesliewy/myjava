package api.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import junit.framework.TestCase;

public class TestSocket3 extends TestCase {
	public void test1(){
		Socket socket = null;
		String host = "localhost";
		int port=0;
		for (port=1;port<1024;port++){
			try{
				socket = new Socket(host,port);
				System.out.println("there is a server program on port "+port);
			}catch(UnknownHostException e){
				System.out.println("UnknownHostException:"+e);
			}catch(IOException e){
				System.out.println("IOException:"+e);
			}
		}
	}
	public void test2(){
		try{
			Socket socket = new Socket("www.baidu.com",80);
			System.out.println("port:"+socket.getPort());
			System.out.println("local port:"+socket.getLocalPort());
			System.out.println("ReceiveBufferSize:"+socket.getReceiveBufferSize());
			System.out.println("SendBufferSize:"+socket.getSendBufferSize());
			System.out.println("SoTimeout:"+socket.getSoTimeout());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
	 * 判断socket是否正在连接:  socket.isConnected && !socket.isClosed() 
	 * socket.isConnected()表示该socket曾经是否连接过,而不是当前是否连接.      remote
	 * socket.isBound()表示该socket曾经是否绑定过本地端口,与isConnected()类似. local
	 * 即使socket已经关闭，仍然可以获取socket相关信息,但是不能使用socket.getInputStream()和socket.getOutputStream()
	 */
	public void test3(){
		Socket socket = null;
		try{
		    socket = new Socket("localhost",80);
			System.out.println("before close - socket.isConnected:"+socket.isConnected());
			System.out.println("before close - socket.isClosed:"+socket.isClosed());
			System.out.println("before close - socket.isBound:"+socket.isBound());
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("local port:"+socket.getLocalPort());
		System.out.println("after close - socket.isConnected:"+socket.isConnected());
		System.out.println("before close - socket.isClosed:"+socket.isClosed());
		System.out.println("before close - socket.isBound:"+socket.isBound());
	}
}
