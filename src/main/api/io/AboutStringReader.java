package api.io;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class AboutStringReader {
    /*
     * StringReader,StringWriter 用来以字符IO流的方式处理字符串,对应ByteArrayInputStream 和 ByteArrayOutputStream
     */
    @Test
    public void test1() throws IOException{
        StringReader sr = new StringReader("aabbbccccwejjjjkskskkkk");
        int c;
        while((c = sr.read()) != -1){
            System.out.print((char)c);
        }
        sr.reset();
        System.out.println();
        System.out.println("============");
        char[] ca = new char[5];
        sr.read(ca);
        System.out.println(ca);
        
        char[] ca1 = new char[8];
        sr.read(ca1, 3, ca1.length -3);
        System.out.println(ca1);
    }
}
