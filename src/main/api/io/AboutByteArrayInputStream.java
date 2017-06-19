package api.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class AboutByteArrayInputStream {
    /* ByteArrayInputStream 和 ByteArrayOutputStream 用于以IO流的方式来完成对字节数组内容的读写,来支持类似虚拟文件或者内存印象文件的功能.
     * 
     */

    public static void output(InputStream in) {
        int ch;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            while ((ch = in.read()) != -1) {
                try {
                    out.write(ch);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] result = out.toByteArray();
        System.out.println("output: " + new String(result));
    }
    
    public static void transform1(InputStream in, OutputStream out) {
        int ch;
        try {
            while ((ch = in.read()) != -1) {
                try {
                    int upperCh = Character.toUpperCase(ch);
                    // 跳过'N'的下一个字母;
                    if(upperCh == 'N'){
                        in.skip(1);
                    }
                    // 还剩几个未读
                    System.out.println("available: " + in.available());
                    out.write(upperCh);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /*
     * in.read(): 读取下一字节, buf[pos++];
     * in.skip(1): 跳过buf[]数组中的1个字节;
     * in.available(): 未读的字节数, count - pos;
     */
    @Test
    public void test1(){
        String tmp = "leslieWANGYANG";
        byte[] src = tmp.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(src);
//        ByteArrayInputStream input = new ByteArrayInputStream(src,3,4);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        transform1(input, output);
        byte[] result = output.toByteArray();
        System.out.println(new String(result));
    }
    
    /*
     * mark(888):  设置的888没有用;  标识mark=pos; reset()回到mark处，而不是开始处; 
     */
    @Test
    public void test2(){
        String tmp = "leslieWANGYANG";
        byte[] src = tmp.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(src);
        input.read();
        input.read();
//        input.mark(88);
        input.reset();
        output(input);
    }

}
