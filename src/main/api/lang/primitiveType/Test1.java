/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:api.lang.primitiveType  
 * Date:Jan 23, 20136:06:39 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.primitiveType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;


/**
 * ClassName: Test1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 23, 2013 6:06:39 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class Test1 {

    /*
     *  boxed 和 unboxed 类型做比较时,会先unbox,然后比较,所以有可能抛出 NullPointer 异常.
     */
    @Test
    public void test1(){
        int a = 0;
        Integer b = null;
        if (a == b){
            System.out.println("same value.");
        }
    }
    
    /*
     * char 和　int 作逻辑运算、算术运算时,　会将 char 先转成 int,即 ASCII码. 
     */
    @Test
    public void test2(){
        char c1 = 'a';
        if(c1 == 97){
            System.out.println("c1 == 97");
        }
        char c2 = (char)(c1 + 3);
        System.out.println("c2: " + c2);
            
        int i1 = c2 * 3;
        System.out.println("i1:" + i1);
        
        /*
         * 虽然可以,但是没意义.
         */
        char c3 = (char)-1;
        System.out.println("c3: " + c3);
        
        /*
         * 死循环: 一般文件中没有 ASCII　是 -1 的字符.
         */
        char c4;
        try {
            BufferedReader in = new BufferedReader(new FileReader("a.txt"));
            while((c4 = (char) in.read()) != -1){
                System.out.println(c4);
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }
    }
    
    /*
     * unbox primitive type:  可以将 int long 等做类型提升, 任意的numberic type 之间都可以转换.
     * boxed primitive: 不可以.
     */
    @Test
    public void test3(){
        int a = 130;
        long b = a;
        System.out.println("b: " + (byte)b);
        System.out.println("b: " + (short)b);
        System.out.println("b: " + (int)b);
        System.out.println("b: " + b);
        System.out.println("b: " + (char)b);
        System.out.println("b: " + (float)b);
        System.out.println("b: " + (double)b);
        
        Integer a1 = 3;
//        Long b1 = a1;
    }
    
    @Test
    public void test4(){
        int i1 = -2;
        int i2 = 2;
        int i3 = 4;
        System.out.println(i1 | i2 | i3);
    }
}
