/**
 * Project Name:MyJava  
 * File Name:AboutFileReader.java  
 * Package Name:api.io  
 * Date:Jan 24, 20134:20:39 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

/**
 * ClassName: AboutFileReader <br/>  
 * Function: test FileReader
 * Reason:
 * date: Jan 24, 2013 4:20:39 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class AboutFileReader {
    /*
     * 读入字符流, 无法指定编码格式, 使用默认的编码.
     */
    @Test
    public void test1(){
        FileReader in = null;
        int c = 0;
        try {
//            in = new FileReader("c:\\xml.dat");
            in = new FileReader(new File("c:\\xml.dat"));
            while ((c = in.read()) != -1){
                System.out.print((char) c);
            }
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
