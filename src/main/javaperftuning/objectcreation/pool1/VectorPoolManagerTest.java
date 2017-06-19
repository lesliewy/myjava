package javaperftuning.objectcreation.pool1;

import java.util.Vector;

import junit.framework.TestCase;

public class VectorPoolManagerTest extends TestCase {
	public void test1(){
		int testSize =35;
		VectorPoolManager vpm = new VectorPoolManager();
		Vector v = null;
		for(int i=0;i<testSize;i++){
			v = vpm.getVector();
			v.add(i);
//			vpm.returnVector(v);
		}
		for(int i = 0;i<vpm.poolSize;i++){
			System.out.println("inUsed["+i+"]="+vpm.inUsed[i]);
			System.out.println("vector:"+vpm.pool[i].toString());
		}
		
	}
	/*
	 * 这样还是没办法测试,每个thread 都有自己的VectorPoolManager
	 */
	public void test2(){
		VectorPoolManager vpm = new VectorPoolManager();
		Thread thread1 = new Thread(new VectorPoolManagerTest1(vpm));
		Thread thread2 = new Thread(new VectorPoolManagerTest1(vpm));
		Thread thread3 = new Thread(new VectorPoolManagerTest1(vpm));
		Thread thread4 = new Thread(new VectorPoolManagerTest1(vpm));
		Thread thread5 = new Thread(new VectorPoolManagerTest1(vpm));
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		for(int i = 0; i< vpm.poolSize;i++){
			System.out.println("vectors["+i+"]="+vpm.pool[i].toString());
			System.out.println("inUsed["+i+"]="+vpm.inUsed[i]);
		}
	}
}
