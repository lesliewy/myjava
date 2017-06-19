package api.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;

public class TestURLConnection extends TestCase {
    public void test1(){
        try{
            URL url = new URL("http://www.baidu.com");
            URLConnection conn = url.openConnection();
            System.out.println("ContentType:"+conn.getContentType());
            System.out.println("ContentLength:"+conn.getContentLength());
            System.out.println("ContentEncoding:"+conn.getContentEncoding());
            System.out.println("Content:"+conn.getContent());
            System.out.println("ConnectTimeout:"+conn.getConnectTimeout());
            System.out.println("Date:"+conn.getDate());
            System.out.println("IfModifiedSince:"+conn.getIfModifiedSince());
            System.out.println("Expiration:"+conn.getExpiration());
            System.out.println("LastModified:"+conn.getLastModified());
            System.out.println("HeaderFields:"+conn.getHeaderFields());
            System.out.println("guessContentTypeFromName:"+conn.guessContentTypeFromName("a.pdf"));  // 根据后缀名判断MIME
        }catch (IOException e){
            e.printStackTrace(new PrintStream(System.out));
        }
    }
    /*
     * connect() getInputStream() getOutputStream() 默认情况下会打开连接. setConnectionTimeout 即此时间.
     */
    public void test2(){
        try{
            URL url = new URL("http://127.0.0.1:33");
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(10000);// 初始连接时间.  
            conn.setReadTimeout(20000);   // 等待数据时间.
//            conn.getInputStream();
            conn.getOutputStream();
            conn.connect();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            System.out.println("HeaderFields:"+conn.getHeaderFields());
            System.out.println("DoInput:"+conn.getDoInput());
            System.out.println("DoOutput:"+conn.getDoOutput());
            System.out.println("UseCaches:"+conn.getUseCaches());
            System.out.println("AllowUserInteraction:"+conn.getAllowUserInteraction());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void test3(){
        URL url = null;
        HttpURLConnection conn = null;
        try{
            url = new URL("http://www.baidu.com");
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("HEAD");   // 只返回head,而不返回body. setRequestMethod只能设置一次.
            System.out.println("getResponseMessage:"+conn.getResponseMessage());
            System.out.println("getResponseCode:"+conn.getResponseCode());
            System.out.println("lastModified:"+conn.getLastModified());
            conn.setRequestMethod("OPTIONS");
            System.out.println("options:"+conn.getHeaderFields());
            
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(conn != null){
                conn.disconnect();
            }
        }
    }
}
