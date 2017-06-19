package concept;
class A
{
	public void func1()
	{
		System.out.println("A func1 is calling");
	}
	public void func2()
	{
		func1();
	}
}
class B extends A
{
	public void func1()
	{
		System.out.println("B func1 is calling");
	}
    public void func3()
    {
		System.out.println("B func3 is calling");
	}
}
/*
 * 1, 子类对象可以自动转为父类对象,包括参数传递的方式.
 * 
 */
class C
{
	public static void main(String [] args)
	{
		B b=new B();
		A a = b;
		callA(a);
		callA(new B());
	}
	public static void callA(A a)
	{
		a.func1();
		a.func2();
//		a.func3();        // 即使传入的是 B 的对象也不可以，编译时就不通过.
	    if (a instanceof B){
	    	((B) a).func3();
	    }
	}
}