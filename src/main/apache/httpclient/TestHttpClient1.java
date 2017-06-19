/**
 * Project Name:MyJava  
 * File Name:TestHttpClient1.java  
 * Package Name:apache.httpclient  
 * Date:Jan 22, 201310:57:45 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package apache.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;


/**
 * ClassName: TestHttpClient1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 22, 2013 10:57:45 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
public class TestHttpClient1 {
    /*
     * http各方面:
     * 基本的get post 方式提交　(QuickStart.java)
     * 
     */
    
    /*
     * 必须调用method的releaseConnection(),才可以开启另一个连接.
     */
    @Test
    public void test1(){
        HttpClient client = new DefaultHttpClient();
        HttpGet getMethod = new HttpGet("http://java.sun.com"); // get方法.
        try {
            HttpResponse getMethodResp = client.execute(getMethod);
            System.out.println(getMethodResp.getStatusLine());
        } catch (ClientProtocolException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        }finally{
            getMethod.releaseConnection();
        }
        HttpPost postMethod = new HttpPost("http://java.sun.com");  // post 方法。
        try {
            HttpResponse postMethodResp = client.execute(postMethod);
            System.out.println(postMethodResp.getStatusLine());
        } catch (ClientProtocolException e) {
            e.printStackTrace();  
                  
        } catch (IOException e) {
            e.printStackTrace();  
        }finally{
            postMethod.releaseConnection();
        }

    }
}
