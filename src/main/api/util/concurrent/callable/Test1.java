/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.util.concurrent.callable  
 * Date:Feb 20, 2017 10:35:36 AM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * Callable接口类似于Runnable，从名字就可以看出来了，但是Runnable不会返回结果，并且无法抛出返回结果的异常，
 * 而Callable功能更强大一些，被线程执行后，可以返回值，这个返回值可以被Future拿到，也就是说，
 * Future可以拿到异步执行任务的返回值。
 * 
 * FutureTask实现了两个接口，Runnable和Future，所以它既可以作为Runnable被线程执行，
 * 又可以作为Future得到Callable的返回值，那么这个组合的使用有什么好处呢？假设有一个很耗时的返回值需要计算，
 * 并且这个返回值不是立刻需要的话，那么就可以使用这个组合，用另一个线程去计算返回值，而当前线程在使用这个返回值
 * 之前可以做其它的操作，等到需要这个返回值时，再通过Future得到
 * date: Feb 20, 2017 10:35:36 AM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {
	public static void main(String[] args) {
      Callable<Integer> callable = new Callable<Integer>() {
          public Integer call() throws Exception {
              return new Random().nextInt(100);
          }
      };
      FutureTask<Integer> future = new FutureTask<Integer>(callable);
      new Thread(future).start();
      try {
          Thread.sleep(5000);// 可能做一些事情
          System.out.println(future.get());
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }
  }
}
