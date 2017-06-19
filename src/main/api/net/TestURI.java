package api.net;

import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.TestCase;

public class TestURI extends TestCase {
	/*
	 * 1,new URI(String) 根据://   :   ?   # 来区分,但不要求protocol合法.
	 */
	public void test1(){
		try{
			URI uri = new URI("abc://12345:80/abc/def?name=leslie#123");
			System.out.println("host:"+uri.getHost());
			System.out.println("port:"+uri.getPort());
			System.out.println("path:"+uri.getPath());
			System.out.println("query string:"+uri.getQuery());
			System.out.println("fragment:"+uri.getFragment());
		    
		}catch(URISyntaxException e) {
		   e.printStackTrace();
	    }
	}
}
