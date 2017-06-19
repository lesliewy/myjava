package api.io;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
         * ClassName: AboutInputStreamReader <br/>  
         * Function: test InputStreamReader.
         * Reason: 
         * date: Jan 24, 2013 4:21:14 PM <br/>  
         *  
         * @author Leslie  
         * @version   
         * @since version 1.0
 */
public class AboutInputStreamReader {
    /*
     * InputStreamReader 连接了 byte streams 和 character streams.
     * 
     * 1, InputStreamReader 和 OutputStreamWriter ,是用于将字节流转换成字符流来读写的两个类.
     *    InputStreamReader 可以将一个字节流中的字节解码成字符后读取，
     *    OutputStreamWriter可以将字符编码成字节后写入到一个字节流中.
     *    
     * 2, InputStreamReader 两个构造函数:
     *    InputStreamReader(InputStream in);
     *    InputStreamReader(InputStream in,String charsetName);
     * 
     * 3,OutputStreamWriter 两个构造函数：
     *    OutputStreamWriter(OutputStream out);
     *    OutputStreamWriter(OutputStream out,String charsetName);
     *    
     * 4, 避免频繁的进行字节与字符之间的转换，最好不要使用InputStreamReader 和 OutputStreamWriter来读写数据,应尽量使用
     *    BufferedWriter 来包装OutputStreamWriter,用BufferedReader 来包装InputStreamReader.
     * 
     */
    @Test
    public void test1(){
        InputStreamReader in = null;
        int c = 0;
        try {
//            in = new InputStreamReader(new FileInputStream("c:\\xml.dat"));
            in = new InputStreamReader(new FileInputStream("c:\\xml.dat"), "UTF-8");
            System.out.println("encoding:" + in.getEncoding());
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
