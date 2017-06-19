/**
 * Project Name:MyJava  
 * File Name:TestJavaCompiler1.java  
 * Package Name:api.javax.tools  
 * Date:Feb 3, 20138:41:18 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.javax.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;
import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;


/**
 * ClassName: TestJavaCompiler1 <br/>  
 * Function: test JavaCompiler  
 * Reason:   
 * date: Feb 3, 2013 8:41:18 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestJavaCompiler1 {
    
    /*
     * JDK 6 中才可以使用JavaCompiler API.
     */
    @Test
    public void test1() throws InstantiationException{
        String source  = "package api.javax.tools;  public class Main { public int test1() {System.out.println(\"Hello World!\"); return 2;} }"; 
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); 
        StandardJavaFileManager fileManager =  compiler.getStandardFileManager(null, null, null); 
        StringSourceJavaObject sourceObject = new StringSourceJavaObject("api.javax.tools.Main", source); 
        Iterable <? extends JavaFileObject> fileObjects = Arrays.asList(sourceObject); 
        CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileObjects); 
        boolean result = task.call();
        
        String classname = "Main";
        String methodName = "test1";
        try {
            if (result) {
               System.out.println("编译成功。"); 
               ClassLoader cl = this.getClass().getClassLoader();
               Class<?> c = cl.loadClass(classname);
               Method method = c.getMethod(methodName, new Class<?>[]{});
               System.out.println(method.invoke((Object)c.newInstance(), new Object[]{}));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        } catch (SecurityException e) {
            e.printStackTrace();  
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {
            e.printStackTrace();  
        } catch (IllegalAccessException e) {
            e.printStackTrace();  
        } catch (InvocationTargetException e) {
            e.printStackTrace();  
        }
     } 
}
