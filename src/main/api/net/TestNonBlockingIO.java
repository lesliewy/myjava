package api.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

import junit.framework.TestCase;

public class TestNonBlockingIO extends TestCase{
	/*
	 * for client : 使用了java new io 中的SocketChannel ByteBuffer WritableByteBuffer 等
	 */
	public void test1(){
		String host = "127.0.0.1";
		int port = 12345;
		SocketAddress socketAddr = new InetSocketAddress(host,port);
		try{
			SocketChannel sc = SocketChannel.open(socketAddr);
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);
			
			/*
			 *  阻塞方式
			 */
//			while(sc.read(buffer)!=-1){
//				// write之前需要先flip.
//				buffer.flip();
//				out.write(buffer);
//				buffer.clear();
//			}
			
			/*
			 *  配置channel为非阻塞方式.
			 */
			sc.configureBlocking(false);
			int n=0;
			while(true){
				// do something
				n = sc.read(buffer);
				// 有数据.
				if(n>0){
					buffer.flip();
					out.write(buffer);
					buffer.clear();
				}
				// 没有数据
				else if(n == 0){
					// do something
				}
				// server 挂了.
				else{
					// do something
					break;
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
