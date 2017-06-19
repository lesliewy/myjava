package javaperftuning.objectcreation.pool1;

import java.util.Vector;

public class VectorPoolManagerTest1 extends Thread {
	VectorPoolManager vpm =null;
	public VectorPoolManagerTest1(VectorPoolManager vpm){
		this.vpm = vpm;
	}
	public VectorPoolManagerTest1(){

	}
	public void run(){
		Vector v = null;
		for (int i=0;i<1;i++){
		    v = vpm.getVector();
			v.add(i);
		}
		System.out.println("in thread vector[0]="+v.toString());
	}
}
