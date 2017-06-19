package examples.crypto.digest;

import examples.utils.MyConvertUtils;

/*
 * MD5:  计算摘要,不可逆, 本身不能对数据进行加解密.    只能做到防篡改,不能做到防抵赖,因为没法确定谁修改了.
 *   用于验证报文是否被篡改, 也用于密码等的验证过程中,这样系统并不知道密码是什么.
 *   
 */
public class MyDigest { 
	  public static void main(String[] args)  { 
		  MyDigest my=new MyDigest(); 
	      my.testDigest(); 
	  } 
	  public void testDigest(){ 
		  try { 
			  String myinfo="1abcd"; 
		      /*
		      * 有几种算法形式:
		      *  MessageDigest Algorithms:  摘要的主要有MD2,MD5,SHA系列.                       计算摘要,不是签名(因为不能防止抵赖)
		      *  KeyGenerator Algorithms:   产生key的主要有: AES,DES,DESede...                 单钥加密.
		      *  Signature Algorithms:      签名算法主要有: MD5withRSA, DSA,SHA1withRSA        签名算法.
		      *  Cipher (Encryption) Algorithms: AES,DES,DESede,RSA
		      *  KeyPairGenerator Algorithms: DiffieHellman,DSA,RSA
		      *  
		      *  后面3个主要是DSA,RSA用到,既可以签名, 又可以非对称的加密.
		      *  
		      *  下面的,MD2 MD5都是32位的,32位是指转为HEX后的digest是32位.  如果要用16位,可以取中间的16位: MyByteUtils.byte2hex(digestmd5).subString(8,24)
		      *  SHA-1 转为HEX是40位的.
		      *  SHA-256 转为HEX 是64位; 也就是原来的是32字节,256 bit.
		      *  SHA-384转为HEX 是96位; 也就是原来的是48字节,384 bit.
		      *  SHA-384转为HEX 是128位; 也就是原来的是64字节,512 bit.
		      *  
		      */
		      java.security.MessageDigest algmd2=java.security.MessageDigest.getInstance("MD2");
		      java.security.MessageDigest algmd5=java.security.MessageDigest.getInstance("MD5");
		      java.security.MessageDigest algsha1=java.security.MessageDigest.getInstance("SHA-1");
		      java.security.MessageDigest algsha256=java.security.MessageDigest.getInstance("SHA-256");
		      java.security.MessageDigest algsha384=java.security.MessageDigest.getInstance("SHA-384");
		      java.security.MessageDigest algsha512=java.security.MessageDigest.getInstance("SHA-512");
		      algmd2.update(myinfo.getBytes());   // 添加信息.
		      algmd5.update(myinfo.getBytes());
		      algsha1.update(myinfo.getBytes());
		      byte[] digestmd2=algmd2.digest();     // 计算摘要.
		      byte[] digestmd5=algmd5.digest();
		      byte[] digestsha1=algsha1.digest();
		      byte[] digestsha256=algsha256.digest();
		      byte[] digestsha384=algsha384.digest();
		      byte[] digestsha512=algsha512.digest();
		      System.out.println("md2摘要是 :"+MyConvertUtils.byte2hex(digestmd2));
		      System.out.println("md5摘要是 :"+MyConvertUtils.byte2hex(digestmd5)); 
		      System.out.println("sha1摘要是 :"+MyConvertUtils.byte2hex(digestsha1)); 
		      System.out.println("sha256摘要是 :"+MyConvertUtils.byte2hex(digestsha256)); 
		      System.out.println("sha384摘要是 :"+MyConvertUtils.byte2hex(digestsha384)); 
		      System.out.println("sha512摘要是 :"+MyConvertUtils.byte2hex(digestsha512)); 
//		      System.out.println("binary:"+MyByteUtils.byteToBinary(digestmd2));
		      
		      // 通过某种方式传给其他人你的信息 (myinfo) 和摘要 (digesta) 对方可以判断是否更改或传输正常
	//	      myinfo=myinfo+":";
		      java.security.MessageDigest algb=java.security.MessageDigest.getInstance("SHA-1"); 
		      algb.update(myinfo.getBytes()); 
		      if (java.security.MessageDigest.isEqual(digestmd2,algb.digest())) {
		    	  System.out.println("信息检查正常"); 
		      } else{
		    	   System.out.println("摘要不相同"); 
		       }
		   }catch (java.security.NoSuchAlgorithmException ex) { 
		     System.out.println("非法摘要算法"); 
		   } 
	  } 
}
	  
