/**
 * Project Name:MyJava  
 * File Name:TestDeque1.java  
 * Package Name:api.util.Deque  
 * Date:Jan 17, 201311:07:39 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

import org.junit.Test;


/**
 * ClassName: TestDeque1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 17, 2013 11:07:39 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestDeque1 {
    /*
     * add:     同addLast()
     * addLast: 
     * addFirst: 同addFirst
     * push:
     * 
     * 推荐使用:
     * offer:   同offerLast  without violating capacity restrictions
     * offerLast
     * offerFirst
     */
    @Test
    public void test1(){
       Deque<Integer> deque = new ArrayDeque<Integer>();
       deque.add(2);
       deque.addLast(3);
       deque.addFirst(4);
       deque.push(5);
       deque.offer(6);
       deque.offerFirst(7);
       System.out.println(deque);
    }
    
    /*
     * element:  if empty,throws NoSuchElementException  同getFirst()
     * getFirst:
     * peek:   returns null if empty. 同peekFirst
     * peekFirst:
     * 
     * getLast:  
     * peekLast:
     * 
     */
    @Test
    public void test2(){
//        Deque<String> arrayDeque = new ArrayDeque<String>(Arrays.asList("a","b","c"));
        Deque<String> arrayDeque = new ArrayDeque<String>();
        System.out.println(arrayDeque.element());    // if empty, throws java.util.NoSuchElementException.
        System.out.println(arrayDeque.getFirst());     // if empty, throws java.util.NoSuchElementException.
        System.out.println(arrayDeque.peek());         // if empty ,returns null.
        
        System.out.println(arrayDeque.getLast());    // if empty, throws java.util.NoSuchElementException.
        System.out.println(arrayDeque.peekLast());
    }
    
    /*
     * remove:      throws NoSuchElementException  同removeFirst,pop
     * removeFirst:
     * pop
     * poll:        if empty returns null,  同pollFirst
     * pollFirst
     * 
     * removeLast: throws NoSuchElementException 
     * pollLast:   if empty returns null
     * 
     * remove(Object): remove specified object.  同removeFirstOccurrence(Object)
     * removeFirstOccurrence:
     * removeLastOccurrence:
     * 
     * 
     */
    @Test
    public void test3(){
      Deque<String> arrayDeque = new ArrayDeque<String>(Arrays.asList("a","b","c"));
//        Deque<String> arrayDeque = new ArrayDeque<String>();
      System.out.println(arrayDeque.remove());
      System.out.println(arrayDeque.removeFirst());
      System.out.println(arrayDeque.removeLast());
      System.out.println(arrayDeque.poll());
      
      Deque<String> arrayDeque2 = new ArrayDeque<String>(Arrays.asList("a","b","c","a","c"));
      System.out.println(arrayDeque2.remove("b"));
      System.out.println(arrayDeque2.removeFirstOccurrence("a"));
      System.out.println(arrayDeque2.removeLastOccurrence("c"));
      System.out.println(arrayDeque2);
    }
    
    /*
     * size
     */
    @Test
    public void test4(){
        Deque<String> arrayDeque = new ArrayDeque<String>();
        System.out.println(arrayDeque.size());
    }
    
    /*
     * iterator()
     * descendingIterator(): 反序.
     * 
     */
    @Test
    public void test5(){
        Deque<String> arrayDeque = new ArrayDeque<String>(Arrays.asList("d","e","f"));
        Iterator iter = arrayDeque.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());
        iter = arrayDeque.descendingIterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
    
    /*
     * ArrayDeque:
     * LinkedBlockingDeque:
     * LinkedList:
     */
}
