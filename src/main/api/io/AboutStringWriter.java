package api.io;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

public class AboutStringWriter {
    /*
     * 将字符流写入到 StringBuffer 中.
     * flush(),close() 方法实际上是空的.
     */
    @Test
    public void test1(){
        int i = 65;
        StringWriter sw = new StringWriter();
        sw.append('b');
        sw.append("abc");
        sw.flush();
        try {
            sw.close();
        } catch (IOException e) {
            e.printStackTrace();  
        }
        sw.write(i);
        StringBuffer sb = sw.getBuffer();
        System.out.println("sw:" + sw.toString());
        System.out.println("sb capacity:" + sb.capacity());
    }
}
