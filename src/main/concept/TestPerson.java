package concept;
//class Person
//{
//	private int age;       //仅内部方法可以访问，如果main 在内部的话，也可以访问.
//	public void setAge(int i)
//	{
//		if(i<0 || i>130)
//			return;
//		age = i; 
//	}
//	public int getAge()
//	{ 
//		return age;
//	}
//}
//public class TestPerson
//{
//	public static void main(String args[])
//	{
//		Person p1 = new Person();
//		p1.setAge(3);
//		p1.setAge(-6);
//		System.out.println(p1.getAge());
//	}
//}
//
//class Person
//{
//    public Person()
//    {
//		System.out.println("the constructor 1 is calling!");
//	}
//	private int age = 10;
//	public void shout()
//	{
//		System.out.println("age is "+age); 
//	}
//}
//class TestPerson
//{
//	public static void main(String[] args)
//	{
//		Person p1=new Person();
//		p1.shout();
//		Person p2=new Person();
//		p2.shout();
//		Person p3=new Person();
//		p3.shout();
//	}
//}
// 构造函数重载.
class Person
{
	private String name="unknown";
	private int age = -1;
	public Person()
	{
		System.out.println("constructor1 is calling");
	}
    public Person(String n)
	{
        name = n;
        System.out.println("constructor2 is calling");
		System.out.println("name is "+name);
    }
	public Person(String n,int a)
	{
        name = n;
        age = a;
        System.out.println("constructor3 is calling");
		System.out.println("name and age is "+name+";"+age);
    }
        public void shout()
	    {
		    System.out.println("listen to me!!"); 
		}
}
class TestPerson
{
	public static void main(String[] args)
	{
		Person p1=new Person();
		p1.shout();
		Person p2=new Person("Jack");
		p2.shout();
		Person p3=new Person("Tom",18);
		p3.shout();
	}
}
//
//class Person
//{
//    private Person()
//    {
//		System.out.println("the constructor 1 is calling!");
//	}
//}
//class TestPerson
//{
//	public static void main(String[] args)
//	{
//		Person p1=new Person();
//	}
//}
class abc implements TestInterface1{
	
}
