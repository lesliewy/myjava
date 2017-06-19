package concept;

public class Polymorphism {
	/*
	 * 1,  运行时多态性是面向对象程序设计代码重用的一个最强大机制，Java多态性的概念也可以被说成“一个接口，多个方法”。
	 *     Java实现运行时多态性的基础是动态方法调度，它是一种在运行时而不是在编译期调用重载方法的机制
	 *
     * 2,  方法的重写Overriding和重载Overloading是Java多态性的不同表现。
	 *     重写Overriding是父类与子类之间多态性的一种表现，重载Overloading是一个类中多态性的一种表现。
	 *     如果在子类中定义某方法与其父类有相同的名称和参数，我们说该方法被重写 (Overriding)。子类的对象使用这个方法时，将调用子类中的定义，对它而言，父类中的定义如同被“屏蔽”了。
	 *     如果在一个类中定义了多个同名的方法，它们或有不同的参数个数或有不同的参数类型，则称为方法的重载(Overloading)。Overloaded的方法是可以改变返回值的类型。   
	 * 
	 * 3,  当超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，
	 *     但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法。 （但是如果强制把超类转换成子类的话，就可以调用子类中新添加而超类没有的方法了。）
	 * 
	 * 引用变量类型是父类，被引用对象是子类的情况下要注意重写方法的调用过程,如果反过来则是错误的.
	 * 多态性中overrides(重写)方法的调用过程：
	 * 先看被引用对象中是否存在该成员方法.
	 * 1   |- 存在,则再看父类中是否存在该方法, 必须保证方法名称,参数名称完全相同.
	 * 2        |- 存在,则执行被引用对像中的方法.
	 * 3        |- 不存在,转4
	 * 4   |- 不存在,按以下顺序依次验证存在性：
	 *        this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)
	 * 5         |- 找到,则再看被用用对象O中是否存在该成员方法(注意,包括从父类继承的所有方法).
	 * 6            |- 存在,则执行
	 * 7            |- 不存在,不存在该情况.
	 * 8         |- 未找到,则报错．
	 * a,都可以直接从第4步开始
	 * b,只有父类与子类同时存在，才会子类的方法.
	 * 
	 */
	public static void main(String[] args) {
	    A2 a1 = new A2();                                    // 先调用 A2的方法,再按照上面的顺序.
		A2 a2 = new B2();                                    // 先调用 B2的方法,再按照上面的顺序.
	//  引用对象        被引用对象
		B2 b = new B2();                                     // 不属于上面的情况.
		C2 c = new C2();   
		D2 d = new D2();   
		System.out.println(a1.show(b));  //A2 and A2
		System.out.println(a1.show(c));  //A2 and A2
		System.out.println(a1.show(d));  //A2 and D2
		System.out.println("================");
		System.out.println(a2.show(b));  //B2 and A2                     如果A2中存在 show(B2 b) 方法，则调用B2中的show(B2 b)
		System.out.println("================");
		System.out.println(a2.show(c));  //B2 and A2                     这时的(super)c是A2,不是B2
		System.out.println("================");
		System.out.println(a2.show(d));  //A2 and D2
		System.out.println(a2.name());   //B2B2                
		System.out.println(b.show(b));   //B2 and B2
		System.out.println(b.show(c));   //B2 and B2                         注意b.show(c) 与 a2.show(c)的区别:  b 不属于超类对象调用子类方法的情况.  没有的话，将c转成直接父类B2, 不是上面的A2.
		System.out.println(b.show(d));   //A2 and D2
		
		lion l = new lion();
		l.run();
		l.run(1, Integer.valueOf(2).toString());
		l.run(1,"2");
	}
}
class A2 {  
	public A2(){
		System.out.println("this is A2 Constructor");
	}
    public String show(D2 obj){  
	    return ("A2 and D2");  
	}   
	public String show(A2 obj){  
	    return ("A2 and A2");  
	}   
//	public String show(C2 obj){
//		return ("A2 and C2");
//	}
	public String name(){
		return "A2:"+getClass().getSimpleName();
	}
}   
class B2 extends A2{
	public B2(){
		System.out.println("this is B2 Constructor.");
	}
	public String show(B2 obj){  
	    return ("B2 and B2");  
    }  
//	public String show(C2 obj){
//		return ("B2 and C2");
//	}
    public String show(A2 obj){
    	return ("B2 and A2");  
    }  
    public String name(){
    	return "B2"+getClass().getSimpleName();
    }
}
class C2 extends B2{};
class D2 extends B2{};
/*
实际上这里涉及方法调用的优先问题 ，优先级由高到低依次为：
this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
让我们来看看它是怎么工作的。

比如④，a2.show(b)，a2是一个引用变量，类型为A，则this为a2，b是B的一个实例，于是它到类A里面找show(B obj)方法，没有找到，
于是到A的super(超类)找，而A没有超类，
因此转到第三优先级this.show((super)O)，this仍然是 a2，这里O为B，(super)O即(super)B即A，因此它到类A里面找show(A obj)的方法，类A有这个方法，
但是由于a2引用的是类B的一个对象，B覆盖了A的show(A obj)方法，因此最终锁定到类B的show(A obj)，输出为"B and A”。

再比如⑧，b.show(c)，b是一个引用变量，类型为B，则this为b，c是C的一个实例，于是它到类B找show(C obj)方法，没有找到，
转而到B的超类A里面找，A里面也没有，
因此也转到第三优先级this.show((super)O)，this为b，O为C， (super)O即(super)C即B，因此它到B里面找show(B obj)方法，找到了，
由于b引用的是类B的一个对象，因此直接锁定到类B的show(B obj)，输出为"B and B”。

开始第3点的进一步解释：
a2是一个引用变量，类型为A，它引用的是B的一个对象，因此这句话的意思是由B来决定调用的是哪个方法。
因此应该调用B的show(B obj)从而输出"B and B”才对。但是为什么跟前面的分析得到的结果不相符呢？！
问题在于我们不要忽略了蓝色字体的后半部分，那里特别指明：这个被调用的方法必须是在超类中定义过的，也就是被子类覆盖的方法。
B里面的show(B obj)在超类A中有定义吗？没有！那就更谈不上被覆盖了。
实际上这句话隐藏了一条信息：它仍然是按照方法调用的优先级来确定的。它在类A中找到了 show(A obj)，如果子类B没有覆盖show(A obj)方法，那么它就调用A的show(A obj)（由于B继承A，虽然没有覆盖这个方法，但从超类A那里继承了这个方法，从某种意义上说，还是由B确定调用的方法，只是方法是在A中实现而已）；现在子类B覆盖了show(A obj)，因此它最终锁定到B的show(A obj)。这就是那句话的意义所在，到这里，我们可以清晰的理解Java的多态性了。
*/


/*
 * 1, 方法的重载(overload):   参数类型、参数个数、参数顺序
 *    不包括 方法的返回值.
 * 2, 子类方法名、参数 与 父类完全一致，则返回值类型也必须一致.
 * 
 */
class animal1{
	int a;
	void run(){
		System.out.println("this is animal run");
	}
}
class lion extends animal1{
	// overrides(重写)，而非overload(重载)
	void run(){
		System.out.println("this is lion run");
	}
	void run(int a){
		System.out.println("this is lion : int ");
	}
	void run(String a){
		System.out.println("this is lion :String ");
	}
	void run(int a,int b){
		System.out.println("this is lion : int int");
	}
	void run (int a,String b){
		System.out.println("this is lion : int String");
	}
	void run (String a,int b){
		System.out.println("this is lion : String int");
	}
	
}
