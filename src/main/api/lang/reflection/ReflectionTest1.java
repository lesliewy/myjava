/**
 * Project Name:MyJava  
 * File Name:ReflectionTest1.java  
 * Package Name:api.lang.reflection  
 * Date:Oct 10, 20139:21:11 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

import api.lang.reflection.proxy.BusinessProcessorImpl;

/**
         * ClassName: ReflectionTest1 <br/>  
         * Function: TODO ADD FUNCTION. <br/>  
         * Reason: TODO ADD REASON(可选). <br/>  
         * date: Oct 10, 2013 9:21:11 AM <br/>  
         *  
         * @author Leslie  
         * @version   
         * @since version 1.0  
         */
public class ReflectionTest1 {

    /*
     * 模拟 instanceof 操作符;
     */
    @Test
    public void test1() {
        try {
            Class<?> cls = Class.forName(DumpMethods.class.getName());
            boolean b1 = cls.isInstance(new Integer(37));
            System.out.println(b1);
            boolean b2 = cls.isInstance(new DumpMethods());
            System.out.println(b2);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    /*
     * 找出类的方法, 都是通过 Method 类中的方法获取;
     * 
     */
    @Test
    public void test2() {
        try {
            Class<?> cls = Class.forName(InvokeTester.class.getName());
            //            Method methlist[] = cls.getDeclaredMethods(); // 该 class 中声明的方法;
            Method methlist[] = cls.getMethods(); // 所有的方法，包括继承的;
            for (int i = 0; i < methlist.length; i++) {
                Method m = methlist[i];
                System.out.println("name = " + m.getName()); // 方法名
                System.out.println("decl class = " + m.getDeclaringClass()); // 方法所在class
                Class<?> pvec[] = m.getParameterTypes(); // 所有的参数;
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);
                Class<?> evec[] = m.getExceptionTypes(); // 异常
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                System.out.println("return type = " + m.getReturnType()); // 返回类型;
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    /*
     * 获取构造器信息;  通过 Constructor 来获取;
     */
    @Test
    public void test3() {
        try {
            Class<?> cls = Class.forName(InvokeTester.class.getName());
            Constructor<?> ctorlist[] = cls.getDeclaredConstructors();
            for (int i = 0; i < ctorlist.length; i++) {
                Constructor<?> ct = ctorlist[i];
                System.out.println("name = " + ct.getName());
                System.out.println("decl class = " + ct.getDeclaringClass());
                Class<?> pvec[] = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);
                Class<?> evec[] = ct.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    /*
     * 获取类中的字段, 通过 Field 获取;
     */
    @Test
    public void test4() {
        try {
            Class<?> cls = Class.forName(InvokeTester.class.getName());
            //            Field fieldlist[] = cls.getDeclaredFields();  // 当前 class 中声明的属性
            Field fieldlist[] = cls.getFields(); // 所有的属性，包括继承的;
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                System.out.println("name = " + fld.getName()); // 属性名;
                System.out.println("decl class = " + fld.getDeclaringClass()); // 属性所在的 class
                System.out.println("type = " + fld.getType()); // 属性的类型 int, java.lang.String
                int mod = fld.getModifiers(); // 修饰符, public static final
                System.out.println("modifiers = " + Modifier.toString(mod));
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    /*
     * 创建新的对象
     * 
     */
    @Test
    public void test5() {
        try {
            Class<?> cls = Class.forName(InvokeTester.class.getName());
            Class<?> partypes[] = new Class[2];
            partypes[0] = Integer.TYPE;
            partypes[1] = String.class;
            // 获取指定的 constructor
            Constructor<?> ct = cls.getConstructor(partypes);
            Object arglist[] = new Object[2];
            arglist[0] = new Integer(37);
            arglist[1] = "aaa";
            // 调用 Constructor 的 newInstance,来执行constructor;
            Object retobj = ct.newInstance(arglist);
            System.out.println(retobj);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    /*
     * 改变字段的值
     */
    @Test
    public void test6() {
        try {
            Class<?> cls = Class.forName(InvokeTester.class.getName());
            Field fld = cls.getField("c");
            InvokeTester f2obj = new InvokeTester();
            System.out.println("c = " + f2obj.c);
            fld.set(f2obj, "adsds");
            System.out.println("c = " + f2obj.c);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    /*
     * 使用数组
     */
    @Test
    public void test7() {
        try {
            Class<?> cls = Class.forName("java.lang.String");
            Object arr = Array.newInstance(cls, 10); // new String[10];  将数组的引用赋值给 Object
            Array.set(arr, 5, "this is a test");  // String[5] = "this is a test"
            String s = (String) Array.get(arr, 5); // s = String[5];
            System.out.println(s);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
    
    /*
     * 泛型
     * Java 5 对 class 文件格式修订来支持泛型: 添加了Signature属性:  
     *    <E:Ljava/lang/Object;>Ljava/lang/Object;Ljava/util/Collection<TE;>;; List接口有一个类型参数E。 
     */
    @Test
    public void test8(){
        Field field;
        try {
            field = InvokeTester.class.getDeclaredField("myList");  //myList的类型是List
            Type type = field.getGenericType();  
            if (type instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) type;
                Type[] actualTypes = paramType.getActualTypeArguments();
                for (Type aType : actualTypes) {
                    if (aType instanceof Class<?>) {
                        Class<?> clz = (Class<?>) aType;              
                        System.out.println(clz.getName()); //输出java.lang.String   
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  
        } catch (SecurityException e) {
            e.printStackTrace();  
        } 
    }
    
    /*
     * 获取class实现的interface;
     */
    @Test
    public void test9(){
        try {
            Class<?> cls = Class.forName(BusinessProcessorImpl.class.getName());
            Class<?>[] interfaces = cls.getInterfaces();
            System.out.println("length:" + interfaces.length + ": " + interfaces[0]);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
