package concept;
 class Person1
{

	/*
	 * 1,标记final的类不可以被继承、标记final的方法不可以overrides、final变量必须初始化(可以在每一个constructor中、但不可以在方法中)且只能初始化一次.
	 *
	 */
    final public int age=18;
    public String name;
	  public Person1(String name,int age)
	 {
		  System.out.println("This is Person1 constructor with parameters.");
		  this.name=name;
//		  this.age=age;
	  }
	  // 
	  public Person1() //如果不写这个构造函数，看看对类Student有什么影响。
	  {
		  System.out.println("This is Person1 constructor with no parameters.");
	  }
    public void getInfo() 
    {
//    	age=18;
     	System.out.println(name);    
    	System.out.println(age);    
    }
}
class Person2
{
    public String name;
    public int age;
	  public Person2(String name,int age)
	 {
		this.name=name;
		this.age=age;
	  }
	  // 
	  public Person2() 
	  {
	  }
    public void getInfo()
    {
     	System.out.println(name);    
    	System.out.println(age);    
    }
}
class Student extends Person1
{
	private String sex="M";
	private int age;
	public String name;
	/* 子类实例化过程:
	 * 1,new Student()时先看Student的构造函数中有没有通过this()调用本类中其他的构造函数的,有则执行该constructor;
	 * 2,再看该构造函数中有没有super(),有则执行相应的父类constructor,没有则添加默认的不带参数的super(),并执行父类的constructor.
	 * 3,初始化该类中的成员变量.
	 * 4,执行constructor中下面的代码.
	 * 5,同一个constructor中不可以同时存在this()和super(); 且this()或者super存在的话必须在第一句;
	 */
	public Student(){
		this("leslie1",10);
//		super();   
		System.out.println("This is Student constructor with no parameters.");

	}
	public Student(String name,int age){
		super("leslie",12);      
		System.out.println("This is Student constructor with  parameters.");
		System.out.println("SEX:"+sex);
	}
    public void study()
    {
    	    System.out.println("Studding");
    }
    /*
     *  1,overrides 父类的方法:访问权限不能降低
     *  2,不写的话默认为 protected 访问权限.
     */
    public void getInfo(){
        super.getInfo();
    }
    public static void main(String[] args)
    {
//    	Person1 p=new Person1();
//    	p.name="Person1";
//    	p.age=30;
//    	p.getInfo();
    	
    	Student s=new Student();
      s.name="student";
      s.age=16;
      s.getInfo();
      s.study();
    }
}




//
//
//
//
//
//


//《Java就业培训教程》P141源码
//程序清单：Student.java
//class Student
//{
//	String name;
//  int age;
//  boolean equals(Object obj)
//{
//	Student st=null;
//	if(obj instanceof Student)
//		st = (Student)obj;
//	else
//		return false;
//  	if(st.name==this.name && st.age==this.age)
//  	   return true;
//		else
//  	   return false;
//  }
//  
//	public static void main(String[] args)
//	{  
//		Student p=new Student();
//		Student q=new Student();
//		p.name="xyz";
//		p.age=13;
//		q.name="xyz";
//		q.age=13;
//		if(p.equals(q))
//		  System.out.println("p 与 q 相等");
//		else
//		  System.out.println("p 与 q 不等");
//	}
//}
//
//
//《Java就业培训教程》P144源码
//程序清单：Interface.java
//interface PCI
//{
//	void start();
//	void stop();
//}
//class NetworkCard implements PCI
//{
//	public void start()
//	{
//		System.out.println("Send ...");
//	}
//	public void stop()
//	{
//		System.out.println("Network Stop.");
//	}
//}
//class SoundCard implements PCI
//{
//	public void start()
//	{
//		System.out.println("Du du...");
//	}
//	public void stop()
//	{
//		System.out.println("Sound Stop.");
//	}
//}
//class MainBoard 
//{
//	public void usePCICard(PCI p)
//	{
//		p.start();
//		p.stop();
//	}
//}
//class Assembler
//{
//	public static void main(String [] args)
//	{
//		MainBoard mb=new MainBoard();
//		NetworkCard nc=new NetworkCard();
//		mb.usePCICard(nc);
//		SoundCard sc=new SoundCard();
//		mb.usePCICard(sc);
//	}
//}
//
//《Java就业培训教程》P149源码
//public class TestException
//{
//	public static void main(String [] args)
//	{
//		try
//		{
//			int reslut = new Test().devide( 3, 0 );
//			System.out.println("the result is" + reslut );
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
//		System.out.println("program is running here ,that is normal !");
//	}
//}
//class Test
//{
//	public int devide(int x, int y)
//	{
//		int result = x/y;
//		return x/y;
//	}
//}
//
//《Java就业培训教程》P154源码
//public class TestException
//{
//	public static void main(String [] args)
//	{
//		try
//		{
//			int result = new Test().devide( 3, 0 );
//			//int result = new Test().devide( 3, -1 );
//			//int result = new Test().devide( 3, 1 );
//			System.out.println("the result is " + result );
//		}
//		catch(DevideByMinusException e)
//		{
//			System.out.println("program is running into"+
//			 "DevideByMinusException");
//			System.out.println(e.getMessage());
//			System.out.println("the devisor is " +
//			 e. getDevisor());
//		}
//		catch(ArithmeticException e)
//		{
//			System.out.println("program is running into"+ 
//			  "DevideByMinusException");
//			System.out.println(e.getMessage());
//		}
//		catch(Exception e)
//		{
//			System.out.println("program is running into"+
//			  "other unknowned Exception");
//			System.out.println(e.getMessage());
//		}
//		System.out.println("program is running here ,that is normal !");
//	}
//}
//
//《Java就业培训教程》P158源码
//package org.it315;
//public class TestPackage
//{
//	public static void main(String [] args)
//	{
//		new Test().print();
//	}
//}
//class Test
//{
//	public void print()
//	{
//		System.out.println("the program is demostrating how to using package!");
//	}
//}