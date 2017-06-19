package api.io;

import org.junit.Test;

public class AboutInputStream {
	/* 输入流： 程序可以从中连续读取字节的对象 for reading streams of raw bytes. 
	 * 1,InputStream 描述所有输入流的抽象概念,是个抽象类.
	 * 2,System.in 连接到键盘,是InputStream类型的实例对象,System.out连接到显示器,是PrintStream类的实例对象.
	 * 3,InputStream的read()总是返回-1表示输入流的结束.
	 * 4,在Windows中，Ctrl+Z产生键盘输入流的结束标记;
	 *     linux 中, Ctrl+D 产生键盘输入流的结束标记.
	 * 
	 */
    @Test
    public void test1(){}

}
