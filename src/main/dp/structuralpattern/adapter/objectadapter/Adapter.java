package dp.structuralpattern.adapter.objectadapter;

public class Adapter implements Target {

	private Adaptee adaptee;
	public Adapter(Adaptee adaptee){
		super();
		this.adaptee=adaptee;
	}
	/**
	 * 源类有方法sampleOperation1,因此适配器直接委派即可.
	 */
	public void sampleOperation1() {
		adaptee.sampleOperation1();
	}
	/**
	 * 由于源类没有这个方法，适配器补充上smapleOperation2.
	 */
	public void sampleOperation2() {
		// TODO Auto-generated method stub
	}
	

}
