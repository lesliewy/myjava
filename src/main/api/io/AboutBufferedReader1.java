/**
 * Project Name:MyJava  
 * File Name:TestBufferedReader1.java  
 * Package Name:api.io  
 * Date:Jan 23, 20134:23:56 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.junit.Test;


/**
 * ClassName: TestBufferedReader1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 23, 2013 4:23:56 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class AboutBufferedReader1 {
    /*
     * 1,BufferedReader(Reader) 的通常用法.  使用 readLine 读取整行;  最后要 close(),　释放系统资源.
     *   建议构造 Reader 时使用 InputStreamReader 而不用 FileReader, 因为InputStreamReader可以指定Charset.
     */
    @Test
    public void test1(){
        String line = "";
        BufferedReader in1 = null;
        BufferedReader in2 = null;
        try {
            in1 = new BufferedReader(
                    new InputStreamReader(new FileInputStream("c:\\xml.dat"), "UTF-8"));
            in2 = new BufferedReader(new FileReader("c:\\xml.dat"));

            System.out.println("in1:========");
            while ((line = in1.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("in2:========");
            while ((line = in2.readLine()) != null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try {
                if (in1 != null){
                    in1.close();
                }
                if (in2 != null){
                    in2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
            in1 = new BufferedReader(
                    new InputStreamReader(new FileInputStream("c:\\xml.dat"), "UTF-8"));
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try{
                if ( in1 != null){
                    in1.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /*
     * mark:   
     * reset:
     * 
     * read():  read a single character.
     * read(char[],offset,len): 读入到char[]中,  返回读入的字符数，需要判断该数目是否是预期的, 有可能出现文件已经读完的情形.
     * skip(long): 跳过n个char, no matter read() or readLine();   同样也要对返回的字符数判断.
     * 
     */
    @Test
    public void test2(){
        BufferedReader in = null;
        int c;
        char[] ca = new char[2000];
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\xml.dat"), "UTF-8"));
            if (in.markSupported()){
                System.out.println("support mark");
                in.mark(1);
                in.reset();
            }
            int i1 = in.read(ca, 0, 2000);
            System.out.println("i1:" + i1);
//            in.reset();
            in.skip(2);
            System.out.println(in.readLine());
            System.out.println("after readline===========");
            while ((c = in.read()) != -1){
                System.out.print((char) c);
            }
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();  
            }
        }
    }
}
