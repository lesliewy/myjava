/**
 * 
 */
package api.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author leslie
 * 
 */
public class TestNio4 {

	/*
	 * gather 和 scatter scatter: 从channel中读取数据时，将读取的数据写入多个buffer中. gather:
	 * 将多个buffer中的数据写入同一个channel, 将多个buffer中的数据聚集后发给channel.
	 */
	@Test
	public void test1() {
		ByteBuffer header = ByteBuffer.allocate(10);
		ByteBuffer body = ByteBuffer.allocate(10);

		byte[] b1 = { '0', '1' };
		byte[] b2 = { '2', '3' };
		header.put(b1);
		body.put(b2);

		ByteBuffer[] buffs = { header, body };

		FileOutputStream os = null;
		FileChannel channel = null;
		try {
			os = new FileOutputStream("gather.txt");
			channel = os.getChannel();
			channel.write(buffs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (channel != null) {
					channel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * transferFrom(): 将数据从源channel传输到FileChannel中. transferTo():
	 * 将数据从FileChannel中传输到其他的channel中.
	 */
	@Test
	public void test2() {
		RandomAccessFile fromFile = null;
		RandomAccessFile toFile = null;
		FileChannel fromChannel = null;
		FileChannel toChannel = null;
		try {
			fromFile = new RandomAccessFile("hotspot.log", "rw");
			fromChannel = fromFile.getChannel();
			toFile = new RandomAccessFile("LogTest.log", "rw");
			toChannel = toFile.getChannel();

			long position = 0;
			long count = fromChannel.size();
			System.out.println("count: " + count);
			toChannel.transferFrom(fromChannel, position, count);
			// fromChannel.transferTo(position, count, toChannel);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fromFile != null) {
					fromFile.close();
				}
				if (fromChannel != null) {
					fromChannel.close();
				}
				if (toFile != null) {
					toFile.close();
				}
				if (toChannel != null) {
					toChannel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * DatagramChannel 发送端.
	 * DatagramChannel 可以收发UDP包. 因为UDP是无连接的网络协议，所以不能向其他通道那样读取和写入。它发送和接收的是数据包.
	 */
	@Test
	public void test3() {
		DatagramChannel channel = null;
		try {
			channel = DatagramChannel.open();
			String info = "I'm the Sender";
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.clear();
			buf.put(info.getBytes());
			buf.flip();

			int bytesSent = channel.send(buf, new InetSocketAddress(
					"127.0.0.1", 8888));
			System.out.println(bytesSent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (channel != null) {
					channel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * DatagramChannel 接收端.
	 */
	@Test
	public void test4() {
		DatagramChannel channel = null;
		try {
			channel = DatagramChannel.open();
			channel.socket().bind(new InetSocketAddress(8888));
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.clear();
			channel.receive(buf);

			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (channel != null) {
					channel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
