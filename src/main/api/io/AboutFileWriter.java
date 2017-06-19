/**
 * Project Name:MyJava  
 * File Name:AboutFileWriter.java  
 * Package Name:api.io  
 * Date:Jan 29, 20137:26:47 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.io;

import java.io.FileReader;
import java.io.FileWriter;

import org.junit.Test;

/**
 * ClassName: AboutFileWriter <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 29, 2013 7:26:47 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class AboutFileWriter {
    /*
     * 1, write(byte[]) write(char[]) 内部会调用flush()方法，所以即使不用flush()、close()也会输出到文件,
     *    但是write(String) 没有调用flush()方法.
     * 
     */
    @Test
    public void test1(){
        try {
            FileWriter fw = new FileWriter("4.txt");
            /*
             * 内部会调用flush.
             */
            fw.write("hello world 我们很好1!");     
            fw.close();            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        char[] buff = new char[1024];
        int len = 0;
        try {
            FileReader fr = new FileReader("4.txt");
            len = fr.read(buff);
            System.out.println(new String(buff, 0, len));
            fr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
