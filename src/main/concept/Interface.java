package concept;
/*
 * 1,interface Runner:          Runner 是package acess,只能同一个package内可以访问.
 *   public interface Runner :  外部可以访问.
 *   这点和class是一样的.
 * 2, 无论extends  还是 implements interface,都不能降低访问权限, 即只能是public, 至少是package access.
 * 3, java　中可以同时implements 多个interface，也可以extends 多个 interface， 但是不可以同时 extends 多个 non-interface 的class.
 * 
 */
interface Runner {
	int ID =1;           // final 类型必须在使用前初始化.  考虑blank final 情形.
    abstract void run(); // abstract 关键字可以不加，默认就是 public abstract,而且只能这两个.
} 
// 接口也可以继承.
interface Animal extends Runner
{
	void breathe();
}
interface Flyer
{
	void fly();
}
// abstract 类型class测试.
abstract class airplane{
	int a;
	final int b=1;
	void fly(){
		
	}
	abstract void run();
}
class boeing extends airplane {

	@Override
	void run() {
		// TODO Auto-generated method stub
		
	}
	
}
class Fish implements Animal{
	  public void run()
	  {
		  System.out.println("fish is swimming");
	  }
	  public void breathe()
	  {
          System.out.println("fish is bubbling");
	  }
//	  abstract void a();
	  
	  public static void main(String[] args){
		  Fish fish= new Fish();
		  fish.breathe();
		  System.out.println("ID:"+Runner.ID);
//		  fish.ID=2;         // interface 中的成员变量是 public final 类型,不可以修改.
	  }
}
/*
 * 1,interface
 *   a,interface 中的成员变量类型是 public final,不可以更改,必须这个类型，这也是默认类型. 
 *     interface 中的方法都是public abstract的，这也是默认和必须的,且只有方法的声明没有body实现部分.
 *   b,可以将某个实现类(class bird,class tiger)向上转为相同的接口Runner,此时将只具有接口中的方法.  将无法判别是抽象类、接口还是普通的类.
 *
 * 2 abstract
 *   a,abstract 类型的class中的成员变量既可以带final,也可以不带final.
 *     abstract 类型的class中既可以存在abstract 方法，也可以存在带body部分的非abstract方法.
 *   b,abstract 的方法必须在abstract 类型的class中,且只需声明，不能body实现.
 *   c,abstract 类型的class 不可以被implements,也不能实例化，只能被extends.只有interface才可以.
 *     extends abstract类型的类，必须实现abstract 类型的方法.
 *   
 * 3,只实现interface中的部分方法时,该类是abstract class，必须要添加 abstract 关键字.
 * 4,类可以同时 extends 和 implements,extends 必须放在 implements 前.
 */
abstract class LandAnimal implements Animal{
	public void breathe()
	  {
	 	System.out.println("LandAnimal is breathing");
	  }
}
class Student2 extends Person implements Runner {
	public void run(){
		
	}
}
// 可以implements 多个interface.
class bird implements Runner,Flyer{
	public void run(){
		System.out.println("this is bird -> run");
	}
	public void fly(){
		System.out.println("this is bird -> fly");
	}
}
class tiger implements Runner{
	public void run(){
		System.out.println("this is tiger run!");
	}
}
class a3{
	public static void main(String[] args) {
		Runner b1 = new tiger();             // 可以向上转为 Interface Runner;
		Runner b2 = new bird();              // 可以向上转为 Interface Runner,此时只有 run(),没有了fly();
		b1.run();
		b2.run();
	}
}
