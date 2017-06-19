package dp.creationpattern.singleton.eager;

public class EagerSingleton {
	/**
	 * 该类被加载时，唯一实例就被创建了.
	 */
	private static final EagerSingleton m_instance = new EagerSingleton();
	/**
	 * 私有的默认构造函数
	 */
	private EagerSingleton(){
		
	}
	/**
	 * 静态工厂方法
	 */
	public static EagerSingleton getInstance(){
		return m_instance;
	}
}
