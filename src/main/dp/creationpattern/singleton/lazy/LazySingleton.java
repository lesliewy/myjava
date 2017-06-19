package dp.creationpattern.singleton.lazy;

public class LazySingleton {
	private static LazySingleton m_instance = null;
	/**
	 * 私有的构造函数，保证外界无法调用
	 */
	private LazySingleton(){}
	/**
	 * 静态工厂方法，返回唯一实例
	 */
	synchronized public static LazySingleton getInstance(){
		if(m_instance == null){
			m_instance = new LazySingleton();
		}
		return m_instance;
	}
	
}
