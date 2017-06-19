/**
 * Project Name:MyJava  
 * File Name:TestClassLoader.java  
 * Package Name:api.lang.ClassLoader  
 * Date:Jan 25, 20133:15:13 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.ClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;


/**
 * ClassName: TestClassLoader <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 25, 2013 3:15:13 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestClassLoader {
    
    /*
     * 层级关系: Bootstrap Loader -> ExtClassLoader -> AppClassLoader， 不是装载关系.
     * 
     * ExtClassLoader, AppClassLoader 均由 Bootstrap Loader装载,只是设置了 parent 关系.
     * 
     *  ClassLoader#loadClass() 装载, 不实例化;  同 Class.forName("",false,classloader);
     */
    @Test
    public void test1(){
//        Office1 office1 = new Office1();
        
        Class<?> office1Class = Office1.class;
        System.out.println("new Office1() has finished.");
        ClassLoader loader = office1Class.getClassLoader();
        try {
            Class<?> office2Class = loader.loadClass(Office2.class.getName());
            System.out.println("loadClass Office2.class has finished.");
            Object o1 = office2Class.newInstance();
            Object o2 = office2Class.newInstance();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        } catch (InstantiationException e) {
            e.printStackTrace();  
        } catch (IllegalAccessException e) {
            e.printStackTrace();  
        }
        System.out.println("=========hierarchy===========");
        ClassLoader cl1 = TestClassLoader.class.getClassLoader();
        System.out.println(cl1);
        ClassLoader cl2 = cl1.getParent();
        System.out.println(cl2);
        ClassLoader cl3 = cl2.getParent();
        System.out.println(cl3);
        
        System.out.println("===================");
        System.out.println("AppClassLoader is loaded by :" + cl1.getClass().getClassLoader());
        System.out.println("ExtClassLoader is loaded by :" + cl2.getClass().getClassLoader());
        
        System.out.println("===================");
        System.out.println("AppClassLoader url: " + System.getProperty("java.class.path"));       // -cp  or -classpath; 如果是jar,必须指定到jar名称; 如果不是jar名称,搜索该目录下的class, 不递归;
        System.out.println("ExtClassLoader url: " + System.getProperty("java.ext.dirs"));         // 搜索的是该目录下的jar, 而不是class; 可以不写jar名称.
        System.out.println("Bootstrap Loader url: " + System.getProperty("sun.boot.class.path")); // 如果是jar,必须指定到jar名称; 如果不是jar名称,搜索该目录下的class, 不递归;
    }
    /*
     * AppClassLoader url: 下面是我的 myeclipse 中 appclassloader url. 首先包含本项目的 bin(可修改); 还有 build path 的 lib 中配置的, 除了 JRE, 因为JRE 是自动去找的;
C:\IBM\my\java\MyJava\bin;
C:\Program Files (x86)\MyEclipse\Common\plugins\org.junit4_4.5.0.v20090824\junit.jar;
C:\Program Files (x86)\MyEclipse\Common\plugins\org.hamcrest.core_1.1.0.v20090501071000.jar;
C:\IBM\my\Myjar\self\other\commons-digester3-3.2-with-deps.jar;
C:\IBM\my\Myjar\self\other\commons-lang-2.3.jar;
C:\IBM\my\Myjar\self\other\dom4j-1.6.1.jar;
C:\IBM\my\Myjar\self\other\jdom.jar;
C:\IBM\my\Myjar\self\Json\json-lib-2.2.2-jdk13.jar;
C:\IBM\my\Myjar\self\Json\commons-lang-2.3.jar;
C:\IBM\my\Myjar\self\Json\commons-logging-1.1.jar;
C:\IBM\my\Myjar\self\Json\ezmorph-1.0.5.jar;
C:\IBM\my\Myjar\self\Json\commons-collections-3.2.1.jar;
C:\IBM\my\Myjar\self\Json\commons-beanutils-core.jar;
C:\IBM\my\Myjar\self\Json\xom-1.1.jar;
C:\IBM\my\Myjar\self\Apache\httpcomponents-client\httpcomponents-client-4.2.3\lib\httpclient-4.2.3.jar;
C:\IBM\my\Myjar\self\Apache\httpcomponents-client\httpcomponents-client-4.2.3\lib\httpcore-4.2.2.jar;
/C:/Program Files (x86)/MyEclipse/configuration/org.eclipse.osgi/bundles/643/1/.cp/;
/C:/Program Files (x86)/MyEclipse/configuration/org.eclipse.osgi/bundles/641/1/.cp/;
/C:/Program Files (x86)/MyEclipse/configuration/org.eclipse.osgi/bundles/642/1/.cp/
 * 
 * ExtClassLoader url: 属于JRE, 会自动装载; 下面的必须是jar, 会自动查找这些目录下的jar;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\ext;
C:\windows\Sun\Java\lib\ext

 * Bootstrap Loader url: 属于JRE, 会自动装载;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\resources.jar;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\rt.jar;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\sunrsasign.jar;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\jsse.jar;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\jce.jar;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\charsets.jar;
C:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\classes
     
  build path -> libraries 中的 JRE System Library 包括: ExtClassLoader url 目录下的所有jar; Bootstrap Loader url;
     *
     */
   
   @Test
   public void test2(){
    try {
        MyClassLoader1 loader = new MyClassLoader1();
        Class c;
        c = loader.loadClass(Office2.class.getName(), false);
        System.out.println(c);
        Object o = c.newInstance();
        System.out.println(o);
        Method m = c.getMethod("print", java.lang.String.class);
        m.invoke(o, "bitan");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();  
    } catch (InstantiationException e) {
        e.printStackTrace();  
    } catch (IllegalAccessException e) {
        e.printStackTrace();  
    } catch (NoSuchMethodException e) {
        e.printStackTrace();  
    } catch (SecurityException e) {
        e.printStackTrace();  
    } catch (IllegalArgumentException e) {
        e.printStackTrace();  
    } catch (InvocationTargetException e) {
        e.printStackTrace();  
    }
   }
}
