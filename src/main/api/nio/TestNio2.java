/**
 * 
 */
package api.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.junit.Test;

/**
 * 传统方式来处理多个客户端时，使用的方法是循环地一个一个地去检查所有的客户端是否有I/O操作。如果有，可能把当前客户端扔给线程池去处理。当所有的客户端轮询过了又接着从头开始轮询.
 * 一个Selector 可以管理多个I/O, 当一个信道有I/O操作时，它会通知selector, selector 就是记住这个信道又I/O操作，并且直到是何种操作，读？写？还是接收新连接？。selector返回
 * 两种情况, 一种是0，即在调用时刻没有任何客户端I/O操作； 另一种就是一组需要操作的客户端，比主动轮询的方式高效的多.
 * 这就是select模型.
 * 
 * @author leslie
 *
 */
public class TestNio2 {

	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8080;
	private static final int TIMEOUT = 3000;
	
	/*
	 * NIO的服务端.
	 * 必须将channel注册到selector, 实现非阻塞，所以FileChannel不可以使用selector, 因为它不能切换到非阻塞模式，而套接字可以.
	 */
	@Test
	public void test1(){
		Selector selector = null;
		ServerSocketChannel ssc = null;
		try{
			selector = Selector.open();
			ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(PORT));
			ssc.configureBlocking(false);
			/*
			 *  返回SelectionKey, 其中包含信息:
			 *      interest集合: 四种不同类型事件: Connect   Accept   Read   Write
			 *      ready集合: Channel已经准备就绪的事件的集合. 在一次selection后，首先访问的就是这个ready set. isAcceptable 等方法.
			 *      Channel: 获得channel, channel()
			 *      Selector: 获得selector, selector()
			 *      附加对象: 附加对象到selectionkey上.   
			 *          selectionKey.attach(theObject);
			 *          selectionKey.attachment(); // 获取附加的对象.
			 *
			 */
			ssc.register(selector, SelectionKey.OP_ACCEPT);     // 将channel注册到selector， selecor可以监听channel.
			
			while(true){
				/*
				 * select出已经准备就绪的那些通道。
				 *    select(): 阻塞到至少有一个通道在你注册的事件上就绪;
				 *    select(timeout): 同select(), 但是最长阻塞timeout毫秒.
				 *    selectNow(): 不会阻塞
				 * 返回int表示多少条通道就绪.
				 * 之后可以调用selectedKeys 获取相关信息.
				 */
				if(selector.select(TIMEOUT) == 0){
					System.out.println("==");
					continue;
				}
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while(iter.hasNext()){
					SelectionKey key = iter.next();
					// 检测事件是否就绪.
					if(key.isAcceptable()){
						handleAccept(key);
					}
					if(key.isReadable()){
						handleRead(key);
					}
					if(key.isWritable() && key.isValid()){
						handleWrite(key);
					}
					// 必须自己移除该key, 下次select()会再次放入其中.
					iter.remove();
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			try{
				if(selector != null){
					selector.close();
				}
				if(ssc != null){
					ssc.close();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void handleAccept(SelectionKey key) throws IOException{
		// 将channel转成需要的类型.
		ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
		SocketChannel sc = ssChannel.accept();
		sc.configureBlocking(false);
		sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
	}
	
	private void handleRead(SelectionKey key) throws IOException{
		SocketChannel sc = (SocketChannel)key.channel();
		ByteBuffer buf = (ByteBuffer)key.attachment();
		long bytesRead = sc.read(buf);
		while(bytesRead > 0){
			buf.flip();
			while(buf.hasRemaining()){
				System.out.print((char)buf.get());
			}
			System.out.println();
			buf.clear();
			bytesRead = sc.read(buf);
		}
		if(bytesRead == -1){
			sc.close();
		}
	}
	
	private void handleWrite(SelectionKey key) throws IOException{
		ByteBuffer buf = (ByteBuffer)key.attachment();
		buf.flip();
		SocketChannel sc = (SocketChannel)key.channel();
		while(buf.hasRemaining()){
			sc.write(buf);
		}
		buf.compact();
	}
}
