package concept;

import java.util.Enumeration;

//1, 类中定义的内部类中访问变量： 先查看display方法中有无变量的定义；再看Inner中有无变量的定义;最后看Outer中有无变量的定义.  就是从里到外.
//2, 外部类不能访问内部类中的成员变量和方法.
//3, 方法中定义的内部类只能访问定义为final的成员和方法.???
class Outer
{
	private int outer_i = 100;
	void test()
	{
		Inner1 in = new Inner1();
		outer_i=200;
		in.display();
	}
	public  Inner1<V> elements(){
		return getInner1();
	}
	private <T> Inner1<T>  getInner1(){
		return new Inner1();
	}

	private class Inner1<T>
	{
		public int a=10;
		void display()
		{
			System.out.println("display: outer_i = " + outer_i+"==="+a);
		}
	}

}
class Inner1<T>{
	int a=11;
	void display(){
		System.out.println("this is Inner2  a="+a);
	}
}
class Test{
	public static void main(String[] args)
	{
		Outer outer = new Outer();
		outer.test();
		Inner1 inner1=outer.getInner1();
		inner1.display();
	}
}