package javaperftuning.objectcreation.pool1;

import java.util.Vector;

/*
 * 1,使用pool技术减少object的创建.
 */
public class VectorPoolManager {
	public static int defaultPoolSize = 25;
	public Vector[] pool = null;
	public boolean[] inUsed = null;
	public int poolSize = defaultPoolSize;
	public VectorPoolManager(){
		pool = new Vector[defaultPoolSize];
		inUsed = new boolean[defaultPoolSize];
		for(int i=0;i<poolSize;i++){
			pool[i] = new Vector();
			inUsed[i] = false;
		}
	}
	
	public VectorPoolManager(int userSize){
		poolSize = userSize;
		inUsed = new boolean[userSize];
		pool = new Vector[poolSize];
		for (int i = 0;i < poolSize; i++){
			pool[i] = new Vector();
			inUsed[i] = false;
		}
	}
	/*
	 * synchronize 需要使用,还没有测试.
	 * 显然如果每个thread中都有自己的VectorPoolManager,就也不存在同步的问题了,因为不存在共享资源.
	 */
	public synchronized Vector getVector(){
		// 查找可用的vertor
		for (int i = 0; i < poolSize; i++){
			if(inUsed[i] == false){
				inUsed[i] = true;
				return pool[i];
			}
		}
		// 没有可用的,自增长pool
		int newPoolSize = poolSize + 10;
		Vector[] newPool = new Vector[newPoolSize];
		boolean[] newInUsed = new boolean[newPoolSize];
		System.arraycopy(pool, 0, newPool, 0, poolSize);
		System.arraycopy(inUsed, 0, newInUsed, 0, poolSize);
		// 初始化新增的
		for(int i = poolSize;i<newPoolSize;i++){
			newPool[i] = new Vector();
			newInUsed[i] = false;
		}
		newInUsed[poolSize] = true;
		Vector returnVector = newPool[poolSize];
	    pool = newPool;
	    poolSize = newPoolSize;
	    inUsed = newInUsed;
		return returnVector;
		
	}
	/*
	 * synchronize 需要使用,还没有测试不使用的后果.
	 */
	public void returnVector(Vector v){
		// 清空vector
		v.clear();
		for(int i = 0; i< poolSize; i++){
			if(inUsed[i]==true){
				inUsed[i]=false;
				break;
			}
		}
	}
}
