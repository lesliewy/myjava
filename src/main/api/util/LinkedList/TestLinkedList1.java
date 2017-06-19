/**
 * Project Name:MyJava  
 * File Name:TestLinkedList1.java  
 * Package Name:api.util.LinkedList  
 * Date:Aug 28, 201310:43:18 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.LinkedList;

import java.util.LinkedList;

import org.junit.Test;

public class TestLinkedList1 {

    @Test
    public void test1() {
        /*
         * 1, LinkedList 底层采用双向链表实现.
         * 2, remove方法执行效率比ArrayList高.
         * 
         */
        LinkedList list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("中国");
        list.add(1, new Integer(33));
        list.addLast("美国");
        list.addFirst("英国");
        list.remove("美国");
        String val = (String) list.get(1);
        list.set(1, val + "changed");
        System.out.println(list);
    }
}
