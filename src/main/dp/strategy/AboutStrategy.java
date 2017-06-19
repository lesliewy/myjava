package dp.strategy;
import java.lang.Class;

public class AboutStrategy {
	/*
	 * 1,策略设计模式：能够根据所传递的参数对象的不同而具有不同行为的方法。
	 *   这类方法包含所要执行的固定不变的部分，而“策略"包含变化的部分.策略就是要传递进入的参数对象，包含要执行的代码.
	 * 
	 */
	static void process(animal a,Object o){
		System.out.println("using class:"+a.name());
		System.out.println("output:"+a.run(o));
	}
	public static void main(String[] args) {
		animal a = new lion();
		animal b = new tiger();
		
		process(a, "leslie");
		process(b, "leslie");
	}
}
class animal{
	int a=10;
	Object run(Object input){
		return input;
	}
	String name(){
		return getClass().getSimpleName();
	}
}
class lion extends animal{
	public String run(Object input){
		return (((String)input).toString());
	}
}
class tiger extends animal{
	public String run(Object input){
		return(((String)input).toUpperCase().toString());
	}
}
