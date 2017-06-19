package dp.structuralpattern.adapter.classadapter;

public class Adapter extends Adaptee implements Target {

	/**
	 * 由于源类没有这个方法，适配器补充上smapleOperation2.
	 */
	@Override
	public void sampleOperation2() {
		// TODO Auto-generated method stub
	}

}
