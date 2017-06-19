package concept;
/*
 * this is a test class<br>
 * @author leslie_wy<br>
 * @param name: leslie<br>
 * @param version: wy<br>
 * 
 * 
 * javadoc -d doc -author -version test1.java:   利用javadoc命令生成类似于jdk documentation的文档,并将其放在doc目录下.
 */
public class test1{
	 public static void main(String [] args){
		 byte b=66;
		 char c=66;
		 float f=3.5f;
		 
		 System.out.println(b);
		 System.out.println(c-1);
		 System.out.println("this is test1 class");
	 }
	
}
class test2{
	 public static void main(String [] args){
		 System.out.println("this is test2 class");
//		 System.out.println(3%0);      // 不能取模0
	 }
}
/*
 *  重载:  方法名相同,   参数个数、类型         不同.
 *         返回类型不同没有用.
 */
class TestOverload{
	public void func1(){
		System.out.println("this is func1.");
	}
	public void func1(String a){
		System.out.println("this is func1 with a para.");
	}
//	public String func1(){
//		return "this is func3";
//	}
}
/*
 * nonation: 
 * @Override : 表明后面的方法是重写父类中的同名方法,如果名字，参数，返回类型不同，都会报错.
 */
class t extends TestOverload{
	@Override
	public void func1(String a){
		
	}
	
}