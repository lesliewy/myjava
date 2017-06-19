package dp.creationpattern.singleton.register;

import java.util.HashMap;

import dp.creationpattern.singleton.lazy.LazySingleton;

public class RegSingleton {
	static private HashMap m_registy = new HashMap();
	static{
		RegSingleton x = new RegSingleton();
		m_registy.put(x.getClass().getName(), x);
	}
	/**
	 * 保护的默认构造函数
	 */
	protected RegSingleton(){}
	/**
	 * 静态工厂方法，返回唯一实例
	 */
	synchronized public static RegSingleton getInstance(String name){
		if(name == null){
			name = "designpattern.singleton.register.RegSingleton";
		}
		if(m_registy.get(name)==null){
			try{
				m_registy.put(name,Class.forName(name).newInstance());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return (RegSingleton)m_registy.get(name);
	}
}
