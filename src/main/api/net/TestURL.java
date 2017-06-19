package api.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import junit.framework.TestCase;

public class TestURL extends TestCase {
	/*
	 * new URL(String url) 中不保证url格式的正确，只要存在:且第一个:前的协议，是支持的，就不会抛出错误. 注意协议前的空格会去掉，后面的不会.
	 * 
	 */
	public void test1(){
		testProtocol("http://www.baidu.com");
		testProtocol("https://www.amazon.com/exec/obidos/order2/"); 
		testProtocol(
			     "jar:http://cafeaulait.org/books/javaio/ioexamples/javaio.jar!"
			         +"/com/macfaq/io/StreamCopier.class");
		testProtocol("mailto:elharo@metalab.unc.edu");
		testProtocol("nfs://utopia.poly.edu/usr/tmp/");
	}
	/*
	 * 1, getPath 与 getFile : getFile() = getPath() + getQuery()   都不包含 getRef() (# 后的内容)
	 * 
	 */
	public void test2(){
		try{
			URL url = new URL("http","www.leslie.com",8080,"ABC/def?username=leslie&password=123#88888");
//			URL url = new URL("http://www.leslie.com:8080/ABC/def");
			System.out.println("host:"+url.getHost());
			System.out.println("port:"+url.getPort());
			System.out.println("default port:"+url.getDefaultPort());
			System.out.println("query string:"+url.getQuery());
			System.out.println("file:"+url.getFile());
			System.out.println("path:"+url.getPath());
			System.out.println("ref:"+url.getRef());
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
	}
	public void test3(){
		InputStream in=null;
		byte[] b=new byte[100];
		try{
			URL url = new URL("http://www.baidu.com");
			in = url.openStream();
			for(int i=0; i<100;i++){
				b[i]=(byte) in.read();
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		for(int i=0;i< b.length;i++){
			System.out.println(b[i]);
		}
	}
	public void test4(){
		InputStream in = null;
		String line="";
		try{
			URL url = new URL("http://www.baidu.com");
			in = url.openStream();
			Reader reader = new InputStreamReader(in,"GBK");
			BufferedReader breader = new BufferedReader(reader);
			while((line=breader.readLine())!=null){
				System.out.println(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
	 * 1, URLEncoder.encode(): 尽量指定encode method(一般就是utf-8).　否则如果使用平台默认的，每个平台都不一致，仍与平台相关.  已经deprecated.
	 */
	public void testURLEncode(){
		String url="http://www.baidu.com !_.~*-:80/abc?name=leslie";
		try {
			System.out.println(URLEncoder.encode(url,"utf-8"));
		    System.out.println(URLEncoder.encode("This string has spaces","UTF-8"));
			System.out.println(URLEncoder.encode("This*string*has*asterisks",  
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This%string%has%percent%signs", 
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This+string+has+pluses",  
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This/string/has/slashes",  
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks", 
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This:string:has:colons",  
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This~string~has~tildes",  
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This(string)has(parentheses)",
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This.string.has.periods", 
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This=string=has=equals=signs", 
			              "UTF-8"));
			System.out.println(URLEncoder.encode("This&string&has&ampersands", 
			              "UTF-8"));
			System.out.println(URLEncoder.encode("Thiséstringéhasénon-ASCII characters", "UTF-8"));
			
			System.out.println("=================");
			System.out.println(URLDecoder.decode("http%3A%2F%2Fwww.baidu.com+%21_.%7E*-%3A80%2Fabc%3Fname%3Dleslie", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void testProtocol(String url){
		try{
			URL u = new URL(url);
			System.out.println(u.getProtocol()+" is surpported.");
		}catch (MalformedURLException  e){
			String protocol = url.substring(0, url.indexOf(":"));
			System.out.println("protocol:"+protocol+" is not surpported.");
		}
	}
}
