package api.io.filter;

public class AboutBufferedInputStream {
	/*
	 * 
	 * 1,缓冲流为I/O流增加了内存缓冲区，主要目的：
	 *     a,允许java一次不止操作一个字节,提高程序的性能.
	 *     b,使得在流上执行skip、mark和reset方法成为可能.
	 * 2,BufferedInputStream 和 BufferedOutputStream 是java提供的两个缓冲区包装类，不管底层系统是否使用了缓冲区，这两个类在自己的实例对象中创建了缓冲区。
	 *   该缓冲区每次只能读取一个byte，不像底层缓冲区那样可以读写大量的数据.
	 * 3, BufferedInputStream(InputStream in);
	 *    BufferedInputStream(InputStream in,int size);  // 指明缓冲区的大小.
	 */
}
