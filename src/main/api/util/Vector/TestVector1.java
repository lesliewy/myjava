/**
 * Project Name:MyJava  
 * File Name:TestVector1.java  
 * Package Name:api.util.Vector  
 * Date:Aug 28, 201310:57:53 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.Vector;

import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;

public class TestVector1 {

    @Test
    public void test1() {
        /*
         * 1,指明index时要注意，要么在当前Vector的末尾，要么小于Vector的长度.
         * 2,Vector 多线程访问时是安全的，支持多线程的同步.
         * 
         *
         */

        Vector v = new Vector();
        v.add(new Integer(1));
        v.add(0, "abc");
        v.add(2, new Integer(5));
        v.addElement(new Integer(4));
        System.out.println("vector size:" + v.size());

        Enumeration<Integer> e = v.elements();
        System.out.println("enumeration 1:" + e.nextElement());
        System.out.println("enumeration 2:" + e.nextElement());
        //此时将会从第3个数开始
        while (e.hasMoreElements()) {
            System.out.println("enumeration :" + e.nextElement());
        }
    }
}
