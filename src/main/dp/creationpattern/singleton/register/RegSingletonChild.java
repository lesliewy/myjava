package dp.creationpattern.singleton.register;

public class RegSingletonChild extends RegSingleton {
	
	public RegSingletonChild(){
		
	}
	/**
	 * 静态工厂方法
	 */
	static public RegSingletonChild getInstance(){
		return (RegSingletonChild)(RegSingleton.getInstance("designpattern.singleton.register.RegSingletonChild"));
	}
	/**
	 * 示意性的方法
	 */
	public String about(){
		return "hello,i am RegSingletonChild.";
	}
}
