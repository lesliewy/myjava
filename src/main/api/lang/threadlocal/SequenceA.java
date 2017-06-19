/*
 * FileName: SequenceA.java
 * Author:   leslie
 * Date:     Jul 10, 2016 3:51:30 PM
 * Description: //模块目的、功能描述      
 */

package api.lang.threadlocal;

/**
 * 使用static变量，线程之间会共享.
 * 
 * @author leslie
 */

public class SequenceA implements Sequence {

	/*
	 * 这里不管是不是static变量，都一样. 线程共享. 同样不管是不是volatile，也一样，会有同步问题. private static
	 * volatile int number = 0; 和 private int number = 0; 都有线程同步问题.
	 */
	private int number = 0;

	@Override
	public int getNumber() {
		number = number + 1;
		return number;
	}
}
