package concept;

class A1
{
	private static int a=33;
	/*
	 * 1,static block与变量同时初始化,然后才是constructor.  如果是public final class ，则在该调用该final class时执行该final class 中的static块。 如：conn = JdbcUtils.getConnection();如果JdbcUtils是第一次调用，会执行其中的static代码块.
	 * 2,static block只能初始化一次,new A(),以后再new A()时不会再被初始化.
	 */
	static {
		System.out.println("print a:"+a);
	}
	public A1(){
		System.out.println("This is A constructor.");
	}
	public  void func1()
	{
		System.out.println("A func1 is calling");
	}
	public void func2()
	{
		func1();   // 此处仍然按照this.show(o) super.show(o) this.show(super(o)) super.show(super(o))的原则进行调用.  故调用B1.func1()
	}

}
class B1 extends A1
{
	public  void func1()
	{
		System.out.println("B func1 is calling");
	}
  public void func3()
  {
		System.out.println("B func3 is calling");
	}
	public static void func4()
	{
	    ;
	}
}
class C1
{
	/*
	 * 1,static 方法中只能访问static成员和static方法，原因是非static成员和方法必须要通过对象类访问，而static则不需要.
	 * 2,static 方法不能被继承,也不能在子类overrides的方法前加static.
	 * 3,static 方法中不能使用 this 和 super ,原因是使用它们说明说必定存在调用的对象.
	 * 3,非static方法中可以访问static成员变量和方法.
	 */
	public static void main(String [] args)
	{
		B1 b=new B1();
		A1 a = b;
		callA(a);
		callA(new B1());
	}
	public static void callA(A1 a)
	{
		a.func1();
		a.func2();
	}
}