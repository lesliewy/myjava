package dp.structuralpattern.proxy.sketch;

public class RealSubject extends Subject {

	/**
	 * 构造函数
	 */
	public RealSubject(){
		
	}
	@Override
	public void request() {
		System.out.println("From RealSubject...");
	}

}
