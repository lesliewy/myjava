package api.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.security.Security;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import junit.framework.TestCase;

public class TestSSLSocket1 extends TestCase {
	/*
	 * SSLSocket 只有创建的时候不一样，其他都一样.
	 */
	public void test1(){
		int defaultPort = 443;
		String host="localhost";
		// This statement is only needed if you didn't add 
		  // security.provider.3=com.sun.net.ssl.internal.ssl.Provider 
		  // to your java.security file.
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider( ));
			SSLSocketFactory factory =  (SSLSocketFactory) SSLSocketFactory.getDefault();
			Socket socket = (SSLSocket)factory.createSocket(host, defaultPort);
			
			   // enable all the suites
		      String[] supported = ((SSLSocket) socket).getSupportedCipherSuites( );
		      ((SSLSocket) socket).setEnabledCipherSuites(supported);
		      
		      Writer out = new OutputStreamWriter(socket.getOutputStream( ));

		      // https requires the full URL in the GET line
		      out.write("GET http://" + host + "/ HTTP/1.1\r\n");
		      out.write("Host: " + host + "\r\n");
		      out.write("\r\n");
		      out.flush( ); 

		      // read response
		      BufferedReader in = new BufferedReader(
		        new InputStreamReader(socket.getInputStream( )));
		      // read the header
		      String s;
		      while (!(s = in.readLine( )).equals("")) {
		          System.out.println(s);
		      }
		      System.out.println( );
		      
		      // read the length
		      String contentLength = in.readLine( );
		      int length = Integer.MAX_VALUE;
		      try {
		        length = Integer.parseInt(contentLength.trim( ), 16);
		      }
		      catch (NumberFormatException ex) {
		        // This server doesn't send the content-length
		        // in the first line of the response body
		      }
		      System.out.println(contentLength);
		      
		      int c;
		      int i = 0;
		      while ((c = in.read( )) != -1 && i++ < length) {
		        System.out.write(c);
		      }
		      System.out.println( );
		      out.close( );                  
		      in.close( );
		      socket.close( );
		    } 
		    catch (IOException ex) {
		      System.err.println(ex);
		    }
	}
	public void test2(){
		SSLSocket socket = null;
		try{
			SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			socket = (SSLSocket)factory.createSocket("www.baidu.com", 443);
			System.out.println("socket.getSupportedCipherSuites:"+socket.getSupportedCipherSuites());
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
