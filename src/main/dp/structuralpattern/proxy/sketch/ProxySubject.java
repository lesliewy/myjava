package dp.structuralpattern.proxy.sketch;


public class ProxySubject extends Subject {
	private RealSubject realSubject;
	/**
	 * 构造函数
	 */
	public ProxySubject(){
		
	}
	/**
	 * 实现请求方法
	 */
	@Override
	public void request() {
		preRequest();
		if(realSubject==null){
			realSubject=new RealSubject();
		}
		realSubject.request();
		postRequest();
	}
	/**
	 * 请求前的操作
	 */
	private void preRequest(){
		// something you want to do  before request
	}
	/**
	 * 请求后的操作
	 */
	private void postRequest(){
		// something you want to do after request.
	}

}
