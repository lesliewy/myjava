/**
 * Project Name:MyJava  
 * File Name:Delegate1.java  
 * Package Name:  
 * Date:Jan 25, 20135:13:43 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */

/**
 * ClassName: Delegate1 <br/>  
 * Function: 测试 JVM 加载class的委派机制. 见: /MyJava/src/main/api/lang/ClassLoader/TestClassLoader.java
 * Reason: 
 * date: Jan 25, 2013 5:13:43 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class Delegate1 {

    /**
     * 委派模型
     * 
     * copy class 文件到AppClassLoader url, ExtClassLoader url, Bootstrap Loader url (TestClassLoader.java 中有输出)
     * 情形1 将 Delegate1.class copy 到 c:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\classes (bootstrap loader url)
     *       先调用AppClassLoader来查找Delegate1, AppClassLoader调用其parent ExtClassLoader, ExtClassLoader 调用其parent Bootstrap Loader,
     *       找到了Delegate1.class,由其加载;
     *       输出第一行 (null);
     *       由于加载 Delegate1 的是 Bootstrap Loader, 所以直接调用 Bootstrap Loader 来查找 Delegate2,没找到;
     *       报错:  java.lang.NoClassDefFoundError: Delegate2
     *       
     * 情形2 将 Delegate2.class  copy 到 c:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\classes (bootstrap loader url)     
     *       先调用AppClassLoader来查找Delegate1, AppClassLoader调用其parent ExtClassLoader, ExtClassLoader 调用其parent Bootstrap Loader,
     *       Bootstrap Loader 没找到 Delegate1.class, 交还给 ExtClassLoader, 没找到, 交还给 AppClassLoader, 找到了,由其加载; 
     *       输出第一行 (AppClassLoader);
     *       由于加载 Delegate1 的是 AppClassLoader, 所以调用 AppClassLoader 查找, 同理一直向上委派给Bootstrap Loader, 找到了, 尤其加载;
     *       输出第二行 (null);
     *       由于加载 Delegate1 的是 AppClassLoader, 所以调用 AppClassLoader 查找, 同理一直向上委派给Bootstrap Loader, 没找到, 交还给 ExtClassLoader, 没找到, 交还给 AppClassLoader, 找到了;
     *       输出第三行  (AppClassLoader);
     * 
     * 情形3 将 Delegate1.class copy 到 c:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\ext\api.jar (ExtClassLoader url)
     *       ExtClassLoader
     *       java.lang.NoClassDefFoundError: Delegate2
     *       
     * 情形4 将 Delegate3.class copy 到 c:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\classes( bootstrap loader url)
     *         Delegate2.class copy 到 c:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\ext\api.jar (ExtClassLoader url)
     *        AppClassLoader
     *        ExtClassLoader
     *        null
     * 
     * 情形5 将Delegate2.class copy 到 c:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\classes( bootstrap loader url)
     *       Delegate3.class copy 到  c:\Program Files (x86)\MyEclipse\Common\binary\com.sun.java.jdk.win32.x86_1.6.0.013\jre\lib\ext\api.jar (ExtClassLoader url)
     *       AppClassLoader
     *       null
     *       ExtClassLoader
     *       
     * 首先要看所在class文件由谁加载(即Delegate1的定义类加载器,Delegate2, Delegate3 的初始类加载器), 里面的所有其他对象首先交给该classloader,由它开始委派.(由初始类加载器开始委派)    
     * 
     * 
     * @author Leslie  
     * @param args  
     * @throws InterruptedException 
     * @since 1.0.0
     */
    public static void main(String args[]) throws InterruptedException{ 
        System.out.println("Delegate1 loaded by : " + Delegate1.class.getClassLoader()) ; 
        Delegate2 t2 = new Delegate2();
        t2.print(); 
        Delegate3 t3 = new Delegate3();
        t3.print();
    } 

}
