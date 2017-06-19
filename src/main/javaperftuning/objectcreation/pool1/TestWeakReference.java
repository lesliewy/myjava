package javaperftuning.objectcreation.pool1;

import java.lang.ref.WeakReference;
import java.util.Vector;

import junit.framework.TestCase;

public class TestWeakReference extends TestCase {
	/*
	 * 到了100000的时候，发生了gc,清理掉了vector中的WeakRefence对象.  同时i1,i2,i3,i4,i5也被清理掉了
	 */
	// 用来存储WeakReference的Object
	private static Vector v = new Vector();
	public void test1(){
		Integer ic = 0;
		int REPEAT =100000;

		// 设置一些无法被gc的Integer
		Integer i1 = getCanonicalInt(3);
		Integer i2 = getCanonicalInt(4);
		Integer i3 = getCanonicalInt(5);
		Integer i4 = getCanonicalInt(6);
		Integer i5 = getCanonicalInt(7);
		for (int i = 0; i < REPEAT; i++)
	        ic = getCanonicalInt(i);
		// 加这句差别很大.
//	    v.setElementAt(i1, 3);
	      for (int i = 0; i < 10; i++){
	        ic = getCanonicalInt(i);
	        System.out.println(ic);
	      }

	}
	private Integer getCanonicalInt(Integer i){
		if(i >= v.size()){
			v.setSize(i+1);
		}
		WeakReference ref = (WeakReference)v.get(i);
		
		Integer canonical_i = null;
		// 第一次，需要设置值.
		if(ref == null){
			canonical_i = new Integer(i);
			v.setElementAt(new WeakReference(canonical_i), i);
		}
		//之前设置过，但是gc了.
		else if ((canonical_i = (Integer) ref.get(  ))  == null){
			System.out.println("Resetting Integer:"+i);
			canonical_i = new Integer(i);
			v.setElementAt(new WeakReference(canonical_i), i);
		}
		return canonical_i;
	}
}
