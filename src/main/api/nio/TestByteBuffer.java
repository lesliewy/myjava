/**
 * Project Name:MyJava  
 * File Name:TestByteBuffer.java  
 * Package Name:api.nio  
 * Date:Jan 28, 20135:43:38 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;


/**
 * ClassName: TestByteBuffer <br/>  
 * Function: 测试 ByteBuffer
 * Reason: 
 * date: Jan 28, 2013 5:43:38 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestByteBuffer {
    
    /*
     * ByteBuffer.allocate() 静态工厂方法获得 buffer;
     * ByteBuffer.wrap()获得buffer;
     */
    @Test
    public void test1(){
        ByteBuffer buffer = ByteBuffer.allocate(32); 
        CharBuffer charBuffer = buffer.asCharBuffer(); 
        String content = charBuffer.put("Hello ").put("World").flip().toString(); 
        System.out.println(content); 
    }
    
    /*
     * channel对象提供了数据源与缓冲区之间的读写操作，是数据源与缓冲区的一个桥梁，通过这个桥梁，数据在两者之间流动
     * put(): 往缓冲区中写;
     */
    @Test
    public void test2(){
        try {
            File file = new File("C:\\test.txt");  
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            FileChannel outputChannel = fos.getChannel();  
            String str = "Hello,just a test";
            ByteBuffer bb = ByteBuffer.allocate(48);
            bb.wrap(str.getBytes());
//            ByteBuffer bb = ByteBuffer.wrap(str.getBytes());
            // 不能添加flip()
//            bb.flip();
            outputChannel.write(bb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        }
    }
    
    /*
     * 从文件读;
     */
    @Test
    public void test3() throws IOException{
        // 获取FileChannel
        FileChannel channel = new FileInputStream("C:\\test.txt").getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48); 
        channel.read(buf);
        buf.flip();
        System.out.println(buf.getChar());
//        while(channel.read(buf) != -1){
//            //....从buf中提取出数据；for example:  
//            buf.flip();
//            System.out.println(buf.asCharBuffer().toString());
//            buf.clear();
//        }
    }
    
    /*
     * 
     */
    @Test
    public void test4() throws IOException{
        int i = 10;  
        long l = 10L;  
        double d = 29.01;  
        char ch = 'c';  
        ByteBuffer buffer = ByteBuffer.allocate(100);  
        buffer.putInt(i).putLong(l).putDouble(d).putChar(ch);  
        FileOutputStream fos = new FileOutputStream(new File("C:/test4.txt"));  
        FileChannel outputChannel = fos.getChannel();  
        buffer.flip();
        outputChannel.write(buffer);
    }
    
    @Test
    public void test5() throws IOException{
        int i;
        long l;
        double d;
        char ch;
        ByteBuffer buffer = ByteBuffer.allocate(100);
        FileInputStream fis = new FileInputStream(new File("C:/test4.txt"));
        FileChannel inputChannel = fis.getChannel();
        inputChannel.read(buffer);
        buffer.flip();
        i = buffer.getInt();
        l = buffer.getLong();
        d = buffer.getDouble();
        ch = buffer.getChar();
        System.out.println("i=" + i + ",l=" + l + ",d=" + d + ",ch=" + ch);  
    }
}
