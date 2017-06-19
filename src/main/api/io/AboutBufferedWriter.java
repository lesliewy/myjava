/**
 * Project Name:MyJava  
 * File Name:AboutBufferedWriter.java  
 * Package Name:api.io  
 * Date:Jan 29, 20137:34:01 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

/**
 * ClassName: AboutBufferedWriter <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 29, 2013 7:34:01 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class AboutBufferedWriter {
    
    /*
     * 用BufferedWriter 来包装 StringWriter, 实现带缓冲的StringWriter.
     */
    @Test
    public void test1(){
        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        try {
            bw.append("a");
            bw.append('b');
            bw.newLine();
            bw.write("abcdef", 2, 2);
            bw.close();
            System.out.println("bw:" + bw.toString());
            System.out.println("sw:" + sw.toString());
        } catch (IOException e) {
            e.printStackTrace();  
        }
        
    }
}
