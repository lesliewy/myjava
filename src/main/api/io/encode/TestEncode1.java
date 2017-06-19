/**
 * 
 */
package api.io.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.junit.Test;

/**
 * Java 中主要有四个场景需要编码、解码:
 *    I/O操作;
 *    内存;
 *    数据库;
 *    javaWeb
 * 
 * @author leslie
 *
 */
public class TestEncode1 {

	/**
	 * 按字节
	 * 读取文件时，字节的编码取决于文件所使用的编码格式。 转换文件时也涉及编码问题，不一致的话就会出现乱码.
	 */
	@Test
	public void test1(){
		File file = new File("test1.txt");
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			StringBuffer sb = new StringBuffer();
			while(in.read(bytes) != -1){
				// 使用默认编码
//				sb.append(new String(bytes));
				sb.append(new String(bytes, "GBK"));
			}
			System.out.println(sb.toString());
			System.out.println(Charset.defaultCharset().name());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if( in != null){
					in.close();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 按字符
	 * 字符流可以看成是一种包装流，底层还是采用字节流来读取字节，然后使用指定的编码方式将读取的字节解码为字符。
	 * 在java中，Reader是字符流的超类.
	 */
	@Test
	public void test2(){
		
	}
	
	/**
	 * 字节字符转换.
	 * InputStreamReader  OutputStreamWriter 
	 * 字节流与字符流之间的桥梁.
	 * 
	 */
	@Test
	public void test3(){
		String file = "test1.txt";
		String charset = "UTF-8";
		// 写字符转换成字节流
		OutputStream os = null;
		OutputStreamWriter osw = null;
		try {
			os = new FileOutputStream(new File(file));
			osw = new OutputStreamWriter(os, charset);
			osw.write("我是leslie");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(osw != null){
					osw.close();
				}
				if(os != null){
					os.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		// 读取字节转换未字符.
		InputStream in = null;
		InputStreamReader isr = null;
		try {
			in = new FileInputStream(new File(file));
			isr = new InputStreamReader(in, charset);
			StringBuffer sb =  new StringBuffer();
			char[] buf = new char[1024];
			while(isr.read(buf) != -1){
				sb.append(buf);
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try{
				if(isr != null){
					isr.close();
				}
				if(in != null){
					in.close();
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
