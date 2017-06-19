/**
 * Project Name:MyJava  
 * File Name:Test3.java  
 * Package Name:api.util.concurrent.callable  
 * Date:Feb 20, 2017 11:29:20 AM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.util.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  执行多个带返回值的任务，并取得多个返回值.
 *  
 *  其实也可以不使用CompletionService，可以先创建一个装Future类型的集合，用Executor提交的任务返回值添加到集合中，最后遍历集合取出数据，代码略。
 *  这里再阐述一下：提交到CompletionService中的Future是按照完成的顺序排列的，这种做法中Future是按照添加的顺序排列的。
 * date: Feb 20, 2017 11:29:20 AM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class Test3 {
	public static void main(String[] args) {
      ExecutorService threadPool = Executors.newCachedThreadPool();
      CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
      for(int i = 1; i < 5; i++) {
          final int taskID = i;
          cs.submit(new Callable<Integer>() {
              public Integer call() throws Exception {
                  return taskID;
              }
          });
      }
      // 可能做一些事情
      for(int i = 1; i < 5; i++) {
          try {
              System.out.println(cs.take().get());
          } catch (InterruptedException e) {
              e.printStackTrace();
          } catch (ExecutionException e) {
              e.printStackTrace();
          }
      }
      
      threadPool.shutdown();
  }
}
