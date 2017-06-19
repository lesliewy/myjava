/**
 * 
 */
package api.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * JAVA处理大文件, 一般用BufferedReader, BufferedInputStream 这类带缓冲的IO类, 如果文件超级大的话，更快的方式是用MappedByteBuffer.
 * MappedByteBuffer 是文件内存映射, 读写性能极高.
 * 
 * ByteBuffer有两种方式分配内存：直接和间接.  
 * 间接模式最典型的就是HeapByteBuffer,即操作堆内存。对于超大文件显然不适用。
 * 直接模式就是MappedByteBuffer, 将文件映射到内存(虚拟内存，并不是物理内存). 
 * 
 * 注意，MappedByteBuffer有资源释放的问题，被MappedByteBuffer打开的文件只有在垃圾回收时才会被关闭，而这个点是不确定的.
 * @author leslie
 * 
 */
public class TestNio3 {

	/*
	 * 采用ByteBuffer读取大文件.
	 */
	@Test
	public void test1() {
		RandomAccessFile aFile = null;
		FileChannel fc = null;
		try {
			aFile = new RandomAccessFile("tmp.50m", "rw");
			fc = aFile.getChannel();

			long timeBegin = System.currentTimeMillis();
			ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
			buff.clear();
			fc.read(buff);
			long timeEnd = System.currentTimeMillis();
			System.out.println("Read time: " + (timeEnd - timeBegin) + "ms.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (aFile != null) {
					aFile.close();
				}
				if (fc != null) {
					fc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * MappedByteBuffer 读取大文件.
	 * 通过FileChannel的map()把文件映射未内存文件.
	 * map(int mode, long position, long size):
	 *    mode: READ_ONLY   READ_WRITE   PRIVATE   PRIVAT表示对缓冲区文件的更改不会传播到文件，并且该更改对映射到同一文件的其他程序不可见.
	 * 
	 */
	@Test
	public void test2() {
		RandomAccessFile aFile = null;
		FileChannel fc = null;
		try {
			aFile = new RandomAccessFile("tmp.50m", "rw");
			fc = aFile.getChannel();
			long timeBegin = System.currentTimeMillis();
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					aFile.length());
			long timeEnd = System.currentTimeMillis();
			System.out.println("Read time: " + (timeEnd - timeBegin) + " ms.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (aFile != null) {
					aFile.close();
				}
				if (fc != null) {
					fc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
