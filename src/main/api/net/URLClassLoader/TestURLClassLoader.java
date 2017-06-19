/**
 * Project Name:MyJava  
 * File Name:TestURLClassLoader.java  
 * Package Name:api.net.URLClassLoader  
 * Date:Jan 25, 20133:33:21 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.net.URLClassLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;


/**
 * ClassName: TestURLClassLoader <br/>  
 * Function: test URLClassLoader. 
 * Reason: 
 * date: Jan 25, 2013 3:33:21 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestURLClassLoader {
    /*
     * 自定义的ClassLoader.
     * 为什么用不同的classloader, Office1却没有实例化2次 ？？？
     * 
     */
    @Test
    public void test1(){
        try {
            URL[] urlArr = {new URL("file:/c:/IBM/my/java/MyJava/bin/")};
            URLClassLoader classLoader = new URLClassLoader(urlArr);
            Class office1Class = classLoader.loadClass("api.net.URLClassLoader.Office1");
            System.out.println("classLoader.loadClass has finished.");
            Office1 office1 = (Office1)office1Class.newInstance();
            Office1 office2 = (Office1)office1Class.newInstance();
            System.out.println(office1.getClass().getClassLoader());
            
            URL[] urlArr1 = {new URL("file:/c:/IBM/my/java/MyJava/bin/")};
            URLClassLoader classLoader1 = new URLClassLoader(urlArr1);
            Class office1Class1 = classLoader1.loadClass("api.net.URLClassLoader.Office1");
            System.out.println("classLoader1.loadClass has finished.");
            Object o3 = office1Class1.newInstance();
            Object o4 = office1Class1.newInstance();
            System.out.println(office1Class1.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        } catch (MalformedURLException e) {
            e.printStackTrace();  
        } catch (InstantiationException e) {
            e.printStackTrace();  
        } catch (IllegalAccessException e) {
            e.printStackTrace();  
        }
    }
    /*
     * TestURLClassLoader: 由 sun.misc.Launcher$AppClassLoader@6b97fd 装载.
     * URL URLClassLoader 均由 bootstrap loader 装载.
     * c asm  : 由 sun.misc.Launcher$AppClassLoader@6b97fd 装载, 为什么不是 ucl(URLClassLoader) ???
     *          下面的  c1 asm1 和上面的都是AppClassLoader, 奇怪 ???
     */
    @Test
    public void test2(){
        URL u;
        try {
            u = new URL("file:/c:/IBM/my/java/MyJava/bin/");
            URLClassLoader ucl = new URLClassLoader(new URL[]{ u }) ; 
            Class c = ucl.loadClass(Assembly.class.getName()) ; 
            Assembly asm = (Assembly) c.newInstance(); 
            asm.start() ; 
            URL u1 = new URL("file:/c:/IBM/my/java/MyJava/bin/");     
            URLClassLoader ucl1 = new URLClassLoader(new URL[]{ u1 }) ; 
            Class c1 = ucl1.loadClass("api.net.URLClassLoader.Assembly"); 
            Assembly asm1 = (Assembly) c1.newInstance() ; 
            asm1.start(); 
            System.out.println(TestURLClassLoader.class.getClassLoader()) ; 
            System.out.println(u.getClass().getClassLoader()) ; 
            System.out.println(ucl.getClass().getClassLoader()) ; 
            System.out.println(c.getClassLoader()) ; 
            System.out.println(asm.getClass().getClassLoader()) ; 
            System.out.println(u1.getClass().getClassLoader()) ; 
            System.out.println(ucl1.getClass().getClassLoader()) ; 
            System.out.println(c1.getClassLoader()); 
            System.out.println(asm1.getClass().getClassLoader()) ; 
        } catch (MalformedURLException e) {
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        } catch (InstantiationException e) {
            e.printStackTrace();  
        } catch (IllegalAccessException e) {
            e.printStackTrace();  
        }
    }
}
