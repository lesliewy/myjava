package api.lang.stringbuffer.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadSB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numOfThead = 20;
		ExecutorService pool = Executors.newFixedThreadPool(numOfThead);
		for (int i =0; i<numOfThead;i++){
		}
	}

}
