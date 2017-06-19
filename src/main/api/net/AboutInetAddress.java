package api.net;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/*
 * 1, 3 个static method获得InetAddress.
 * public static InetAddress getByName(String hostName) throws UnknownHostException
 * public static InetAddress[] getAllByName(String hostName) throws UnknownHostException
 * public static InetAddress getLocalHost( ) throws UnknownHostException
 *
 *
 */
public class AboutInetAddress {
	public static void main(String[] args){
//		test1();
//		test2();
		test3();
    }
	public static void test1(){
		try{
			long start = System.currentTimeMillis();
			InetAddress inet = InetAddress.getByName("61.135.169.125");   // 61.135.169.125
			long endOfGetByName = System.currentTimeMillis();
			System.out.println("getByName elapsed time:"+(endOfGetByName-start));
			System.out.println("localhost:"+InetAddress.getLocalHost().toString());
			long endOfGetLocalHost = System.currentTimeMillis();
			System.out.println("getLocalHost elapsed time:"+(endOfGetLocalHost-endOfGetByName));
			System.out.println("hostAddr:"+inet.getHostAddress());
			System.out.println("hostName:"+inet.getHostName());
			System.out.println("cannonicalHostName:"+inet.getCanonicalHostName());
			long endOfGetCanonicalHostName = System.currentTimeMillis();
			System.out.println("getCanonicalHostName elapsed time:"+(endOfGetCanonicalHostName - endOfGetLocalHost));
			System.out.println("cannonicalHostName with cache:"+inet.getCanonicalHostName());
			System.out.println("networkaddress.cache.ttl :"+System.getProperty("networkaddress.cache.ttl"));
			System.out.println("networkaddress.cache.negative.ttl  :"+System.getProperty("networkaddress.cache.negative.ttl"));
		
		}catch (UnknownHostException e){
			e.printStackTrace();
		}
	}
	public static void test2(){
		SecurityManager  securityManager = new SecurityManager();
		securityManager.checkConnect("127.0.0.1", 35);
	}
	/*
	 * IP4 OR IP6
	 */
	public static void test3(){
		InetAddress inet;
		try {
			Date start = new Date();
			inet = InetAddress.getByName("www.baidu.com");
			byte[] inetip = inet.getAddress();
			System.out.println("inetip size:"+inetip.length);
//			for (int i=0; i<inetip.length;i++){
//				System.out.println("inetip["+i+"]="+inetip[i]);
//			}
			if(inet instanceof Inet4Address){
				System.out.println("it's a ip4.");
			}else if(inet instanceof Inet6Address){
				System.out.println("it's a ip6.");
			}else{
				System.out.println("it's neither.");
			}
			Date end = new Date();
			System.out.println("time:"+end.getTime());
			System.out.println("elapsed time:"+(end.getTime()-start.getTime()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
//		Inet4Address inet4 = Inet4Address.getByName("www.baidu.com");
	}
}
