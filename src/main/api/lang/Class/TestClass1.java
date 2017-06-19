/**
 * Project Name:MyJava  
 * File Name:TestClass1.java  
 * Package Name:api.lang.Class  
 * Date:Jan 25, 20132:32:55 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.Class;

import org.junit.Test;


/**
 * ClassName: TestClass1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 25, 2013 2:32:55 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestClass1 {
    /*
     * java 程式的动态特性:
     *   * new Office();   装载 且 实例化;  装载只一次;
     *   * Class.forName():  既可以装载, 也可以实例化;
     *   * ClassLoader.load():  只做装载;
     * 可以自定义ClassLoader: URLClassLoader.
     * 
     * Class.forName("") 等同于 Class.forName(className, true, currentLoader), 装载 且 实例化;
     */
    @Test
    public void test1(){
        try {
            Class<?> cl= Class.forName("api.lang.Class.Office1");
            System.out.println("Class.forName has finished.");
            Object o = cl.newInstance();
            Office1 office1 = (Office1)o;
            office1.say();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        } catch (InstantiationException e) {
            e.printStackTrace();  
        } catch (IllegalAccessException e) {
            e.printStackTrace();  
        }
    }
    
    /*
     * Class.forName(cl1.getName(), false, cl1.getClassLoader());  用指定的classloader装载, 不作实例化,所以 Office1 中的 static 不会被执行.
     */
    @Test
    public void test2(){
        try {
            Class<?> cl1 = Office1.class;
            System.out.println("Office1.class has finished.");
//        Class.forName(cl1.getName(), true, null);  // java.lang.ClassNotFoundException:
//            Class.forName(cl1.getName(), true, cl1.getClassLoader());
            Class.forName(cl1.getName(), false, cl1.getClassLoader());
            System.out.println("Class.forName has finished.");
            Object o = cl1.newInstance();
            ((Office1)o).say();
            System.out.println("cl1.newInstance has been finished.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
}
