package javaperftuning.objectcreation.pool1;

import junit.framework.TestCase;


public class TestCanonical extends TestCase{
	/*
	 * 比较 identity 和 equals效率:
	 * 1000000:  identity  3
	 *           equals    11
	 * 10000000: identity  22
	 *           equals    106
	 */
	public void test1(){
	    String a = "a";
	    int num=10000000;
		long begin1 = System.currentTimeMillis();
		for (int i=0;i<num;i++){
			if(a=="a")
				;
		}
		long end1 = System.currentTimeMillis();
		System.out.println("==:"+(end1-begin1));
		for (int i=0;i<num;i++){
			if(a.equals("a"))
				;
		}
		long end2 = System.currentTimeMillis();
		System.out.println("equals:"+(end2-end1));
	}
}
