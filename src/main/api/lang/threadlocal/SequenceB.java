
/*
 * FileName: SequenceB.java
 * Author:   leslie
 * Date:     Jul 10, 2016 3:59:14 PM
 * Description: //模块目的、功能描述      
 */

package api.lang.threadlocal;

/**
 * ThreadLocal 为每个线程提供一个独立的副本，虽然 threadLocal 本身也是static变量.
 * 
 * 
 * @author leslie
 */

public class SequenceB implements Sequence {

	private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {
		/*
		 * 返回线程局部变量的初始值.
		 */
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	@Override
	public int getNumber() {
		// 设置线程局部变量
		numberContainer.set(numberContainer.get() + 1);
		// 获取线程局部变量.
		return numberContainer.get();
	}
}
