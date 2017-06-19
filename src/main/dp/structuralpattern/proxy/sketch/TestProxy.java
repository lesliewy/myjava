package dp.structuralpattern.proxy.sketch;

public class TestProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subject subject = new ProxySubject();
		subject.request();
	}

}
