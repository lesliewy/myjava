
/*
 * FileName: SequenceC.java
 * Author:   leslie
 * Date:     Jul 10, 2016 3:59:14 PM
 * Description: //模块目的、功能描述      
 */

package api.lang.threadlocal;

/**
 * 使用自己的ThreadLocal.
 * @author leslie
 */

public class SequenceC implements Sequence {

	private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>() {
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
