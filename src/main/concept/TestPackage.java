package concept;

//import leslie.Test1;

/*
 * 1,上面的package不可以删除,是必须的，因为此时TestPackage.java位于 concept 这个package下.
 * 2,不同package中的类名可以相同;
 *   不同package中import时，import的必须是public class;否则无法import;  不加public 的class只能在那个package下使用;
 *   
 * 3,同一package下面的不同文件中类名相同时,编译器不会报错,但是执行时可能会报错.同名的那些类中有的不会正常执行.
 *   同一package中的class可以互相使用，而不需要import,前提是引用的方法必须是protected、public .
 *   
 * 4,一旦有了package语句，则class的路径就变成了my/TestPackage了.
 * 5,import wy.* 只会把wy/ 目录下的所有class文件引入，不会引入wy/leslie/下的所有class,即不会引入子包中的类.
 * 6,java.lang package 是不需要import的，默认就import了，可能是其包含Object的缘故.
 * 
 * jar -cvf leslie.jar bin/leslie/ :  将leslie这个package 做成jar.
 * jar -tvf leslie.jar             :  查看leslie.jar内容.
 * jar -xvf leslie.jar             :  将leslie.jar 解压成目录.
 */
//import Test;

public class TestPackage
{
	public static void main(String [] args)
	{
		new TestPackageTest().print();
		new Test2.Test1().print1();        // 可以指明包名，此时无需 import.
//		new Test1().print1();
		
	}
}
class TestPackageTest
{
	public void print()
	{
		System.out.println("the program is demostrating how to using package!");
	}
}
