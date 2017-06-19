/**
 * Project Name:MyJava  
 * File Name:Test2.java  
 * Package Name:api.util.concurrent.callable  
 * Date:Feb 20, 2017 11:11:09 AM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ClassName: Test2 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 20, 2017 11:11:09 AM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test2 {
	public static void main(String[] args) {
      ExecutorService threadPool = Executors.newSingleThreadExecutor();
      Future<Integer> future = threadPool.submit(new Callable<Integer>() {
          public Integer call() throws Exception {
              return new Random().nextInt(100);
          }
      });
      try {
          Thread.sleep(5000);// 可能做一些事情
          System.out.println(future.get());
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }finally{
      	threadPool.shutdown();
      }
  }
}
