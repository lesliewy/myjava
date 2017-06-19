package concept;

// import package, Enhanced for loop,  public/protected/private,
import java.util.ArrayList;

//class Compare
//{
//	 public static void main(String[] args)
//	  {
//			String str1 = new String("abc");
//			String str2 = new String("abc");
//			String str3 = str1;
//			if(str1==str2)
//				System.out.println("str1==str2");
//			else
//				System.out.println("str1!=str2");	
//			if(str1==str3)
//				System.out.println("str1==str3");
//			else 
//				System.out.println("str1!=str3");	
//	}
//}

/*
 * 
 * 
 */
class Compare
{
	 public static void main(String[] args)
	  {
			String str1 = new String("abc");
			String str2 = new String("abc");
			String str3 = str1;
			if(str1.equals(str2))
				System.out.println("str1 equal str2");
			else
				System.out.println("str1 not equal str2");	
			if(str1.equals(str3))
				System.out.println("str1 equal str3");
			else
				System.out.println("str1 not equal str3");	
	}
}

/*
 * Enhanced for loop
 * 1,for(char ch:charray)    for each char in charray
 * 2,没有新生成对象,是原来Collection中对象的reference.
 * 
 */
class EhanForLoop{
	public static void main(String[] args){
//		test3 male = new test3(2);
//		male.eat();
		char[] charray ={'a','b','c','d'};
		for(Character ch:charray){
			Character.toUpperCase(ch);
			System.out.println(ch);
		}
		ArrayList<EhanForLoop1> en = new ArrayList<EhanForLoop1>();
		EhanForLoop1 a = new EhanForLoop1();
		EhanForLoop1 b = new EhanForLoop1();
		en.add(a);
		en.add(b);
		System.out.println("EhanForLoop1 a:"+a);
		System.out.println("EhanForLoop1 b:"+b);
		System.out.println("===============");
		for (Object ch:en){
			((EhanForLoop1)ch).a=0;
			en.remove(0);
			System.out.println(((EhanForLoop1)ch));
		}
		for (Object ch:en){
			System.out.println(((EhanForLoop1)ch));
		}
	}
}
class EhanForLoop1{
	public  EhanForLoop1(){
		System.out.println("Constructor");
	}
	int a;
	
	
}

/*
 * 1,
 *   类的访问控制符有两种：public 和默认（friendly）
             属性和方法的访问控制符由：public,private,protected,private protected 和默认
 * 作用域              当前类        同一package    子孙类        其他package 
 *  public       √         √            √            √ 
 *  protected    √         √            √            × 
 *  friendly     √         √            ×            × 
 *  private      √         ×            ×            × 
 *  a, friendly 不是一个key word,只是一种默认的说法,也可以说default、package private.
 *     一般来说 friendly 说法只用于 class上,上图表示是正确的.
 *  b, 如果class 不明确写明public,则是默认(friendly),即在同一package中都可以      Man = new Man();
 *     如果属性成员变量没有写明访问控制符：则是默认,属于protected,是friendly 说法的超集，因为还可以被子类访问.
 *     
 * 
 */
class Man{
	protected int pro1;
    int b;
	protected void eat(){
		System.out.println("man eat");
	}
}
public class test3 extends Man{
    public test3(int a){
		pro1 = a;
		b = 1;
	}
   
   void eat(int a){
	   eat();
	   System.out.println("male eat");
   }
   public static void main(String[] args){
	   test3 male = new test3(2);
	   male.eat(2);
	   
	  
   }
}

