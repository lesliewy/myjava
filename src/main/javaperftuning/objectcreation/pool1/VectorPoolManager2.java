package javaperftuning.objectcreation.pool1;

import java.util.Iterator;
import java.util.Vector;

/*
 * 同一个Thread中getVector()返回同一个Vector:  1,ThreadLocal    2. Singleton模式
 *   
 */
public class VectorPoolManager2 implements Runnable {
	// 使用ThreadLocal,必须是static,否则多个.
	public static ThreadLocal vectors = new ThreadLocal();
	static int threadCount=0;
	public void run(){
		String s = "This is thread " + threadCount++;
	    Vector v = getVector(  );
	    v.addElement(s);
	    v = getVector(  );
	    v.addElement(s);
	    try{Thread.sleep(2000);}catch(Exception e){  }
	    v = getVector(  );
	    v.addElement(s);
	    System.out.println(v);

	}
	private Vector getVector(){
		// 使用ThreadLocal中的get(),来获取之前set()的值.
		Vector vector = (Vector) vectors.get();
		// 第一次
		if (vector == null){
			vectors.set(new Vector());
		}
		return (Vector)vectors.get();
	}
	public static void main(String[] args){
		Thread t1 = new Thread(new VectorPoolManager2());
		Thread t2 = new Thread(new VectorPoolManager2());
		Thread t3 = new Thread(new VectorPoolManager2());
		t1.start();
		t2.start();
		t3.start();
		//下面这个会报NullPointerException,因为vectors在每个thread中,而VectorPoolManager2中的就是一个ThreadLocal
//		Iterator iter = ((Vector)VectorPoolManager2.vectors.get()).iterator();
//		while(iter.hasNext()){
//			System.out.println(iter.next());
//		}
	}
}
