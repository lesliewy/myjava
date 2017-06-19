package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Vector;

public class AboutSet {

	public static void main(String[] args){
		/*
		 * 1,指明index时要注意，要么在当前Vector的末尾，要么小于Vector的长度.
		 * 2,Vector 多线程访问时是安全的，支持多线程的同步.
		 * 
		 *
		 */
		
		Vector v = new Vector();
		v.add(new Integer(1));
		v.add(0, "abc");         
		v.add(2, new Integer(5));         
		v.addElement(new Integer(4));
		System.out.println("vector size:"+v.size());
		
		
		Enumeration<Integer> e =v.elements();
		System.out.println("enumeration 1:"+e.nextElement());
		System.out.println("enumeration 2:"+e.nextElement());
		//此时将会从第3个数开始
		while(e.hasMoreElements()){
			System.out.println("enumeration :"+e.nextElement());
		}
		
		/*
		 * 1,HashSet 中add 的值不可以重复,重复时 add 返回false，Set 内容保持不变.
		 * 2,HashSet 底层用HashMap实现.当使用add方法时，实际上将该对象作为底层维护的Map对象的key,而value都是同一个对象(该对象没有用上).
		 */
		HashSet hs = new HashSet();
		hs.add("a");
		hs.add("b");
		hs.add(new Integer(1));
//			hs.add(new Integer(1));
		System.out.println(hs.add(new Integer(1)));  // 返回false，Set 内容不变.
		
		System.out.println(hs);
		Iterator iter = hs.iterator();
		for (;iter.hasNext();){
			System.out.println(iter.next());
		}
		
	}
}
