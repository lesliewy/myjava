/**
 * Project Name:MyJava  
 * File Name:AboutBufferedInputStream.java  
 * Package Name:api.io  
 * Date:Jan 24, 20135:37:14 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;


/**
 * ClassName: AboutBufferedInputStream <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 24, 2013 5:37:14 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class AboutBufferedInputStream {
    /*
     * 
     * 1,缓冲流为I/O流增加了内存缓冲区，主要目的：
     *     a,允许java一次不止操作一个字节,提高程序的性能.
     *     b,使得在流上执行skip、mark和reset方法成为可能.
     * 2,BufferedInputStream 和 BufferedOutputStream 是java提供的两个缓冲区包装类，不管底层系统是否使用了缓冲区，这两个类在自己的实例对象中创建了缓冲区。
     *   该缓冲区每次只能读取一个byte，不像底层缓冲区那样可以读写大量的数据.
     * 3, BufferedInputStream(InputStream in);
     *    BufferedInputStream(InputStream in,int size);  // 指明缓冲区的大小. 默认8K.
     */
    @Test
    public void test1(){
        BufferedInputStream bin = null;
        int c = 0;
        try {
//            bin = new BufferedInputStream(new FileInputStream("c:\\xml.dat"));
            bin = new BufferedInputStream(new FileInputStream("c:\\xml.dat"),500);
            while ((c = bin.read()) != -1){
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try {
                if (bin != null){
                    bin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();  
            }
        }
    }
    
    /*
     * skip: 跳过多少个字节.
     * mark: 标记当前位置, 参数表示多少个字节后失效.
     * 
     */
    @Test
    public void test2(){
        BufferedInputStream bin = null;
        int c = 0;
        try {
            bin = new BufferedInputStream(new FileInputStream("c:\\xml.dat"),500);
            bin.skip(5);
            bin.mark(Integer.MAX_VALUE);
            while ((c = bin.read()) != -1){
                System.out.print((char) c);
            }
            bin.reset();
            System.out.println("======================");
            while ((c = bin.read()) != -1){
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try {
                if (bin != null){
                    bin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();  
            }
        }
    }
}
