/**
 * 
 */
package api.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * NIO 三大核心: Channel, Buffer, Selector.
 * 数据总是从Channel读取到Buffer, 或者从Buffer写入到Channel中.
 * Selector 用于监听多个Channel的事件(比如连接的打开，数据到达等), 一个线程可以监听多个Channel。
 * 
 * 传统IO是面向流的, 面向流意味着每次从流中读取一个或多个字节，它们没有被缓存。此外，不能前后移动流中的数据, 要移动的话，需要先将它缓存
 * 缓存到缓冲区.
 * NIO是面向Buffer的, 需要时可以在Buffer中前后移动.
 * 
 * 传统IO各种流是阻塞式的。这意味着，当一个线程调用read(), write()等时，该线程会被阻塞，直到有一些数据被读取或数据被全部写入.
 * NIO是非阻塞式的，如果没有数据可以读取，就什么都不会获取，而不是阻塞线程，所以直至数据可读取之前，线程可以做其他事. 非阻塞写也是如此。
 * 线程通常将非阻塞IO的空闲时间用于在其他Channel上执行IO操作，所以一个线程可以管理多个输入和输出的Channel.
 * 
 * @author leslie
 *
 */
public class TestNio1 {

	/*
	 * 传统IO读取文件.
	 */
	@Test
	public void test1(){
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream("hotspot.log"));
		    byte[] buf = new byte[1024];
			int bytesRead = is.read(buf);
			while(bytesRead != -1){
				for(int i = 0; i < bytesRead; i++){
					System.out.print((char)buf[i]);
				}
				bytesRead = is.read(buf);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null){
					is.close();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Channel 和IO中的Stream差不多是一个等级。不过Steam是单向的，例如：InputStream, OutputStream. Channel是双向的,
	 * 既可以读，也可以写.
	 * Channel的主要实现：FileChannel   DatagramChannel  SocketChannel  ServerSocketChannel  分别用于文件IO、
	 * UDP 和 TCP(Server 和 Client).
	 * 
	 * Buffer的主要实现: ByteBuffer CharBuffer DoubleBuffer FloatBuffer IntBuffer LongBuffer ShortBuffer
	 * MappedByteBuffer HeapByteBuffer DirectByteBuffer等.
	 * 
	 * Client            Channel <—— ——> Channel           Server
	 *       \          /                       \         /
	 *        \        /                         \       /
	 *          Buffer                             Buffer
	 * 
	 * 向Buffer中写数据: 
	 *     从Channel写到Buffer (fileChannel.read(buf)).
	 *     通过Buffer的put()(buf.put()).
	 * 从Buffer中读数据:
	 *     从Buffer读取到Channel (channel.write(buf))
	 *     通过Buffer的get()(buf.get()).
	 * 
	 * Buffer中的几个变量:
	 *     capacity     缓冲区数组的总长度
	 *     position     下一个要操作的数据元素的位置.
	 *     limit        不可操作的下一个元素的位置 limit <= capacity
	 *     mark         记录当前position的前一个位置或者默认值0
	 * 
	 * ByteBuffer.allocate(11)初始状态:
	 *        position=0                    limit=capacity=11
	 *        x  x  x  x  x  x  x  x  x  x  x
	 * 写入5个byte后状态:
	 *                       position=5     limit=capacity=11
	 *        1  1  1  1  1  x  x  x  x  x  x
	 * buf.flip() 准备将5个byte写入Channel的状态:
 	 *        position=0     limit=5        capation=11
	 *        1  1  1  1  1  x  x  x  x  x  x
	 * 此时底层操作系统可以正确读取5个byte的数据并发送了.在下一次写数据之前调用clear(),使各个索引位置回到初始状态.
	 * 
	 * buf.compact() 不清空buffer，保留未读取的： 将所有未读的数据拷贝到buffer起始处, 然后将postion设到最后一个未读元素后一个, limit 像clear()方法一样，设置成capacity.
	 * buffer就可以继续写入，而且不会覆盖未读的数据了.
	 * 
	 * buf.mark(): 标记特定的position，之后可以调用reset()恢复position.
	 * buf.rewind(): 将position设为0, 重读buffer中的数据. limit 不变，仍表示能从buffer中读取多少个元素.
	 */
	@Test
	public void test2(){
		RandomAccessFile aFile = null;
		try {
			// FileInputStream 也可以 getChannel().
			aFile = new RandomAccessFile("hotspot.log", "rw");
			FileChannel fileChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			
			int bytesRead = fileChannel.read(buf);
			System.out.println(bytesRead);
			
			while(bytesRead != -1){
				buf.flip();
				while(buf.hasRemaining()){
					System.out.print((char)buf.get());
				}
				buf.compact();
				bytesRead = fileChannel.read(buf);
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(aFile != null){
					aFile.close();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * NIO的客户端
	 */
	@Test
	public void test3(){
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = null;
		try{
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);    // 阻塞模式可以配置.
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
			
			if(socketChannel.finishConnect()){
				int i = 0;
				while(true){
					TimeUnit.SECONDS.sleep(5);
					String info = "I'm " + i++ + "-th information from client";
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					while(buffer.hasRemaining()){
						System.out.println(buffer);
						socketChannel.write(buffer);    // 将write()放入while，因为无法保证一次能写入多少
					}
				}
			}
		} catch (IOException | InterruptedException e){
			e.printStackTrace();
		}  finally {
			try{
				if (socketChannel != null){
					socketChannel.close();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 传统IO的服务端.
	 */
	@Test
	public void test4(){
		ServerSocket serverSocket = null;
		InputStream in = null;
		try{
			serverSocket = new ServerSocket(8080);
			int recvMsgSize = 0;
			byte[] recvBuf = new byte[1024];
			while(true){
				Socket clntSocket = serverSocket.accept();
				SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);
				in = clntSocket.getInputStream();
				while((recvMsgSize = in.read(recvBuf)) != -1){
					byte[] temp = new byte[recvMsgSize];
					System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
					System.out.println(new String(temp));
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			try{
				if(serverSocket != null){
					serverSocket.close();
				}
				if(in != null){
					in.close();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
