/**
 * Project Name:MyJava  
 * File Name:RetrievingClassObjects.java  
 * Package Name:api.lang.reflection.classes  
 * Date:Nov 13, 20139:33:30 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.reflection.classes;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RetrievingClassObjects {

    /*
     * 利用 Object.getClass():
     * Reference types all inherit from java.lang.Object.
     * Classes, enums, arrays, and interfaces are all reference types
     * 
     */
    @Test
    public void test1(){
        // 通过 instance 获取class;
        Class<?> c1 = "foo".getClass();
        Class<?> c2 = Calendar.getInstance().getClass();
        System.out.println("c1.isArray(): " + c1.isArray());
        System.out.println("c1.isInterface(): " + c1.isInterface());
        System.out.println("c1.isMemberClass(): " + c1.isMemberClass());
        System.out.println("c1.isInstance(): " + c1.isInstance("a"));
        
        // 数组也是 Object
        byte[] bytes = new byte[1024];
        Class<?> c3 = bytes.getClass();
        System.out.println("c3.isArray(): " + c3.isArray());
        
        // interface 是 reference type;
        Set<String> s = new HashSet<String>();
        Class<?> c4 = s.getClass();
        System.out.println(c4.getName());
    }
    
    /*
     * .class:
     * type is available but there is no instance;
     * primitive type;
     * 
     */
    @Test
    public void test2(){
        boolean b;
//        Class c1 = b.getClass();   // compile-time error
        Class<?> c2 = boolean.class;  // correct

        Class<?> c3 = java.io.PrintStream.class;

        Class<?> c4 = int[][][].class;
    }
    
    /*
     * Class.forName():
     * fully-qualified name of a class is available;
     * primitive types 不能使用;
     */
    @Test
    public void test3(){
        try {
            Class<?> c1 = Class.forName("com.duke.MyLocaleServiceProvider");
            
            Class<?> cDoubleArray = Class.forName("[D");  // 通 double[].class
            Class<?> cStringArray = Class.forName("[[Ljava.lang.String;");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        }
    }
    
    /*
     * TYPE Field:
     * for Primitive Type Wrappers;  或者直接使用 primitive type 的 .class;
     * 
     */
    @Test
    public void test4(){
        Class<?> c1 = Double.TYPE;  // 同 double.class;
        Class c2 = Void.TYPE;       // 同 void.class
    }
    
    /*
     * Methods that Return Classes:
     * Class.getSuperclass():
     * Class.getClasses():  指定class中所有的public class, interfaces, enums;
     * Class.getDeclaredClasses(): explicitly declared in this class
     * 
     * Class.getDeclaringClass()
     * java.lang.reflect.Field.getDeclaringClass()
     * java.lang.reflect.Method.getDeclaringClass()
     * java.lang.reflect.Constructor.getDeclaringClass()
     * 
     */
    @Test
    public void test5(){
        Class c = javax.swing.JButton.class.getSuperclass();
        Class<?>[] c2 = Character.class.getClasses();
    }
}
